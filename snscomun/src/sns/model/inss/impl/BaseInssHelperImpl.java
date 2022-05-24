package sns.model.inss.impl;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import sns.config.Constantes;
import sns.exception.MultipleIpfException;
import sns.exception.MultipleNafException;
import sns.exception.SincronizacionSQLException;
import sns.exception.TsiException;
import sns.exception.UsuarioSegSocialException;
import sns.exception.VinculacionInssSnsException;
import sns.model.UsuarioSns;
import sns.model.gestion.RegistroInss;
import sns.model.inss.CamposAfectadosInss;
import sns.model.inss.CriteriosBusqueda;
import sns.model.inss.IInss;
import sns.model.inss.ListaCamposAfectadosInss;
import sns.model.inss.dto.InssAsFicherosBean;
import sns.model.inss.dto.InssBean;
import sns.model.inss.dto.InssCamposAfectadosBean;
import sns.model.inss.dto.InssCuarentenaCruceSns;
import sns.model.inss.dto.InssHistoricoBean;
import sns.model.inss.dto.InssProcesandoBean;
import sns.model.inss.dto.InssSnsBean;
import sns.model.inss.dto.ResultadoCruceSnsBean;
import sns.model.inss.ifi.IfiFicheroBean;

public abstract class BaseInssHelperImpl {
	
	protected abstract org.apache.log4j.Logger getLogger();
	protected static final SimpleDateFormat FORMAT_FECHA_INSS = new SimpleDateFormat("yyyyMMdd");

	protected abstract String getNombreTablaInssBajas();
	protected abstract String getNombreTablaInss();
	protected abstract String getNombreTablaInssSns();
	
	protected abstract AccesoBD getAccesoBd();
	protected abstract AccesoBD getAccesoBdTx();
	
	protected abstract String getTituloByTipoAseguramiento(String tipoAseguramiento);
	

	/*
	 * Constantes.INSS_HISTORICO_PROCEDENCIA_DESVINCULACION_MANUAL
	 */
	public void desvincularCodSns(String codUsuarioSns, Integer opMaestra, BigDecimal codTipoProcedencia,AccesoBD bd) throws SQLException {
		String sqlBusqueda="select * from " + getNombreTablaInssSns() +" where COD_USUARIO_SNS=?";
		String sqlUpdate="update " + getNombreTablaInssSns() +" set COD_USUARIO_SNS=null,CRITERIO_IDENTIFICACION_SNS=null where ID_INSS=?";
		HashMap hParam=new HashMap();
		hParam.put("1", codUsuarioSns);
		getLogger().debug("query desvincularCodSns -> " + sqlBusqueda + " " + hParam);
		Vector vDatos=bd.consulta(sqlBusqueda, hParam);
		getLogger().debug("Resultado -> " + vDatos);
		if (vDatos.size()>0) {
			InssSnsBean inssSnsBean=new InssSnsBean((HashMap)vDatos.get(0));
			hParam=new HashMap();
			hParam.put("1", inssSnsBean.getIdInss());
			bd.actualizaSinCommit(sqlUpdate, hParam);
			
			ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, inssSnsBean.getCriterioIdentificacionSns(), ""));
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, inssSnsBean.getCodUsuarioSns(), ""));
			guardarHistorico(inssSnsBean.getIdInss(), listaCamposAfectadosInss, new BigDecimal(opMaestra.toString()),codTipoProcedencia, bd);
		}
	}

	/**
	 * buscamos en el INSS la persona por los datos identificativos del SNS
	 * en esta busqueda se hara cuando en el SNS no se haya encontrado y se vaya
	 * a proceder a dar un alta de un registro con titulo que no esta gestionado por el SNS
	 *  
	 * @param usuarioSnsIn
	 * @param bd
	 * @return
	 */
	public ResultadoInssExtBean findByDatosSns(UsuarioSns usuarioSnsIn, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
		ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean();
		/**
		 * buscamos en el INSS a la persona 
		 */
		HashMap hParam=new HashMap();
		boolean esTitular = usuarioSnsIn.getNafNuevo().equals(usuarioSnsIn.getNafTitularNuevo());
		/**
		 * se mira si hay algun resgistro con
		 * igual naf_titular, nombre, apellido1, 
		 * si tenemos dni_nie se mira tambien por ese campo
		 * si no tenemos dni_nie y si pasaporte se mira tambien por ese campo
		 * si tenemos naf se mira tambien por ese campo
		 */
		
		StringBuffer queryBen = new StringBuffer();
		queryBen.append("select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS ");
		queryBen.append("from ").append(getNombreTablaInss()).append(" i,");
		queryBen.append(getNombreTablaInssSns()).append(" b ");
		queryBen.append("where i.NOMBRE = ? ");
		queryBen.append("and i.APELLIDO1 = ? ");
		int siguienteParametro=3;
		if (esTitular) {
			queryBen.append("and i.COD_TIPO_ASEGURADO = 'T' ");
		}else{
			queryBen.append("and i.NAF_TITULAR = ? ");
			queryBen.append("and i.COD_TIPO_ASEGURADO = 'B' ");
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafTitularNuevo());
			siguienteParametro++;
		}
		queryBen.append("and i.cod_situacion='A' ");
		queryBen.append("and b.ID_INSS=i.ID_INSS ");
		hParam.put("1", usuarioSnsIn.getNombreNuevo());
		hParam.put("2", usuarioSnsIn.getApellido1Nuevo());
		if (!Misc.esVacio(usuarioSnsIn.getDniNieNuevo())) {
			queryBen.append("and i.DNI_NIE = ? ");
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getDniNieNuevo());
			siguienteParametro++;
		}
		if (Misc.esVacio(usuarioSnsIn.getDniNieNuevo())
			&& !Misc.esVacio(usuarioSnsIn.getPasaporteNuevo())) {
			queryBen.append("and i.PASAPORTE = ? ");
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getPasaporteNuevo());
			siguienteParametro++;
		}
		if (!Misc.esVacio(usuarioSnsIn.getNafNuevo())) {
			queryBen.append("and (i.NAF = ? OR i.NAF_SEC1 = ? OR i.NAF_SEC2 = ? OR i.NAF_SEC3 = ? OR i.NAF_SEC4 = ? OR i.NAF_SEC5 = ? OR i.NAF_SEC6 = ? OR i.NAF_SEC7 = ? OR i.NAF_SEC8 = ? OR i.NAF_SEC9 = ?) ");
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC1
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC2
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC3
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC4
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC5
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC6
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC7
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC8
			siguienteParametro++;
			hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC9
			siguienteParametro++;
		}
		getLogger().debug("queryBen: " + queryBen.toString());
		Vector vResp = bd.consulta(queryBen.toString(), hParam);
		getLogger().debug("vResp: " + vResp);

		if (!vResp.isEmpty()) {
			resultadoInssExtBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
		} else {
			throw new UsuarioSegSocialException("Usuario no encontrado para usuarioSnsIn -> [" + usuarioSnsIn.toString() + "]");
		}

		return resultadoInssExtBean;
	}

	/**
	 * Buscamos en el INSS por el CodUsuarioSns para ver si ya hay algun
	 * registro vinculado
	 * si no se encuentra se busca por naf + dni_nie o pasaporte y si se encuentra se vincula
	 * @param usuarioSnsIn
	 * @return
	 */
	 public UsuarioSns buscarTitularParaModificacion(UsuarioSns usuarioSnsIn) throws Exception {
		ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean();
		AccesoBD bd=null;
		try {
			bd=getAccesoBdTx();
			/**
			 * esta variable indicara si realmente hay un registro en el INSS encontrado
			 * pero esta con situacion en B, en tal caso para que los duplicados se vayan
			 * arreglando solos se pasa al metodo buscarMasVincularTitular para que desvincule
			 * el de estado en Baja
			 */
			boolean registroInssAnteriorEnBaja=false;
			try {
				resultadoInssExtBean=findByCodUsuarioSns(usuarioSnsIn.getCodSns(), bd);
				if (!resultadoInssExtBean.isVacio()
					&& !"A".equals(resultadoInssExtBean.getCodSituacion())) {
					/**
					 * puede que el registro con el que hemos encontrado
					 * ese codigo sns este de Baja por lo que es como si no fuese encontrado
					 */
					resultadoInssExtBean=new ResultadoInssExtBean();
					
					/**
					 * indicamos que hemos encontrado un tio pero que esta en situacion de Baja
					 */
					registroInssAnteriorEnBaja=true;
				}
			}catch (UsuarioSegSocialException e) {
				//usuario no encontrado por codSns
			}
			if (resultadoInssExtBean.isVacio()) {
				/**
				 * si es titular futuro
				 */
				if (Misc.esIgual(usuarioSnsIn.getNafNuevo(),usuarioSnsIn.getNafTitularNuevo())) {
					usuarioSnsIn=buscarMasVincularTitular(usuarioSnsIn,registroInssAnteriorEnBaja,bd);
				}
			}else{
				// TODO puede falta que cuando sea mayor de edad
				resultadoInssExtBean.setCriterioIdentificacionSns(Constantes.INSS_IDENTIFICACION_COD_USUARIO_SNS);
				usuarioSnsIn.setResultadoInssExtBean(resultadoInssExtBean);
			}
		}catch (UsuarioSegSocialException e) {
			//usuario no encontrado
		}catch (Exception e) {
			throw e;
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
		return usuarioSnsIn;

	}

	/**
	 * Buscamos al titular en el INSS y si se encuentra se vincula
	 * En el objeto de vuelta se devuelve tambien el registro encontrado en el INSS
	 * asi como el titulo correcto para introducirlo en la BD
	 * @param usuarioSnsIn
	 * @param bd
	 * @return
	 * @throws Exception
	 */
	public UsuarioSns buscarMasVincularTitular(UsuarioSns usuarioSnsIn, AccesoBD bd) throws Exception {
		/**
		 * se pone fijo false porque solo queremos vincular el codSNS
		 * no desvincular si ya existia
		 */
		return buscarMasVincularTitular(usuarioSnsIn,false,bd);
	}
	 
	 /**
	  * 
	  * @param usuarioSnsIn
	  * @param registroInssAnteriorEnBaja esta variable indicara si realmente hay un registro en el INSS encontrado
	  *  pero esta con situacion en B, en tal caso para que los duplicados se vayan
	  *  arreglando solos se pasa al metodo buscarMasVincularTitular para que desvincule
	  *  el de estado en Baja
	  * @param bd
	  * @return
	  * @throws Exception
	  */
	 private UsuarioSns buscarMasVincularTitular(UsuarioSns usuarioSnsIn,boolean registroInssAnteriorEnBaja, AccesoBD bd) throws Exception {
		 
		 /**
		  * vamos a ver si el que estamos buscando es menor
		  */
		 boolean peke=false;
		 if (sns.util.Misc.esMenor(usuarioSnsIn.getFechaNacNuevo().getTime())) {
			 peke=true;
		 }
		 
		ResultadoInssExtBean resultadoInssExtBean=findByDatosSnsTitular(usuarioSnsIn,peke, bd);
		if (resultadoInssExtBean.isVacio()) {
			/**
			 * no hemos encontrado al titular en el INSS
			 */
			throw new UsuarioSegSocialException();
		}else {
			/**
			 * vemos si es peke, si es asi el registro encontrado en el INSS debe
			 * ser del grupo de aseguramiento 0201 o 0701 si se ha encontrado solo por NAF
			 */
			if (peke) {
				if (Misc.esVacio(usuarioSnsIn.getDniNieNuevo())
					 && Misc.esVacio(usuarioSnsIn.getPasaporte())) {
					/**
					 * hemos encontrado solo por NAF vemos el grupo de aseguramiento
					 * si no coincide rechazamos
					 */
					if (!"0201".equals(resultadoInssExtBean.getTipoAseguramiento())
						&& !"0701".equals(resultadoInssExtBean.getTipoAseguramiento())) {
						throw new UsuarioSegSocialException();
					}
				}
			}
		}
		if (Misc.esVacio(resultadoInssExtBean.getCodUsuarioSns())) {
			/**
			 * hemos encontrado el registro en el INSS
			 * pero en la tabla INSS_SNS no esta vinculado a ningun codigo SNS
			 */
			try {
				/**
				 * si es false la variable solo se intenta vincular
				 * si es true significa que hay un registro en el INSS pero con situacion B
				 * con lo que potencialmente es un error del INSS. Desvinculamos ese codSns
				 * y se asigna a este nuevo registro del INSS que estara en Alta
				 */
				if (!registroInssAnteriorEnBaja) {
					vincularSoloCodSns(resultadoInssExtBean, usuarioSnsIn.getCodSns(), "DEL_SNS", bd);
				}else{
					vincularCodSns(resultadoInssExtBean, usuarioSnsIn.getCodSns(), "DEL_SNS", bd);
				}
				resultadoInssExtBean.setCriterioIdentificacionSns(Constantes.INSS_IDENTIFICACION_NAF_DNI_NIE);
				usuarioSnsIn.setResultadoInssExtBean(resultadoInssExtBean);
			}catch (SQLException e) {
				if (e.getErrorCode()!=1) {
					throw e;
				}else{
					/**
					 * se ha intentado vincular el registro del INSS 
					 * con el registro del SNS 
					 * y no se ha podido vincular porque lo tiene otro registro del inss
					 */
					/**
					 * marcar la alerta de no vinculacion y posible duplicado
					 */
					throw new VinculacionInssSnsException(usuarioSnsIn.getCodSns());
				}
			}
			String codTituloTraducido=getTituloByTipoAseguramiento(resultadoInssExtBean.getTipoAseguramiento());
			if (!codTituloTraducido.equals(Misc.nz(usuarioSnsIn.getCodTitulo()))) {
				/**
				 * el titulo no corresponde con el que nos ha indicado el Servicio de Salud
				 * Se cambia y se marca para enviar el N010
				 */
				usuarioSnsIn.setCodTitulo(new BigDecimal(codTituloTraducido));
				usuarioSnsIn.setConversionRealizadaTituloNuevoRDL(true);
			}
		}else{
			/**
			 * Hemos encontrado un registro en el INSS
			 * pero que tiene otro codigo SNS vinculado
			 */
			/**
			 * marcar la alerta como posible duplicado
			 */
			throw new VinculacionInssSnsException(resultadoInssExtBean.getCodUsuarioSns());
		}
		return usuarioSnsIn;
	 }
	 

	 /**
	 * buscamos en el INSS la persona por los datos identificativos del SNS
	 * en esta busqueda se hara cuando en el SNS no se haya encontrado y se vaya
	 * a proceder a dar un alta de un registro con titulo que no esta gestionado por el SNS
	 *  
	 * @param usuarioSnsIn
	 * @param bd
	 * @return
	 */
	 public ResultadoInssExtBean findByDatosSnsTitular(UsuarioSns usuarioSnsIn, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
		return findByDatosSnsTitular(usuarioSnsIn,false,bd);
	 }

	 /**
		 * buscamos en el INSS la persona por los datos identificativos del SNS
		 * en esta busqueda se hara cuando en el SNS no se haya encontrado y se vaya
		 * a proceder a dar un alta de un registro con titulo que no esta gestionado por el SNS
		 * Si peke es true no se tiene en cuenta que no tenga dni_nie o pasaporte y se busca solo por NAF
		 *  
		 * @param usuarioSnsIn
		 * @param peke
		 * @param bd
		 * @return
		 */
	 private ResultadoInssExtBean findByDatosSnsTitular(UsuarioSns usuarioSnsIn, boolean peke, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
			ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean();
			/**
			 * buscamos en el INSS a la persona 
			 */
			HashMap hParam=new HashMap();
			/**
			 * se mira si hay algun resgistro con
			 * igual naf_titular, nombre, apellido1, 
			 * si tenemos dni_nie se mira tambien por ese campo
			 * si no tenemos dni_nie y si pasaporte se mira tambien por ese campo
			 * si tenemos naf se mira tambien por ese campo
			 */
			
			StringBuffer queryTit = new StringBuffer();
			queryTit.append("select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS ");
			queryTit.append("from ").append(getNombreTablaInss()).append(" i,");
			queryTit.append(getNombreTablaInssSns()).append(" b ");
			int siguienteParametro=1;
			queryTit.append("where i.COD_TIPO_ASEGURADO = 'T' ");
			queryTit.append("and i.cod_situacion='A' ");
			queryTit.append("and b.ID_INSS=i.ID_INSS ");
			if (!Misc.esVacio(usuarioSnsIn.getDniNieNuevo())) {
				queryTit.append("and i.DNI_NIE = ? ");
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getDniNieNuevo());
				siguienteParametro++;
			}
			if (Misc.esVacio(usuarioSnsIn.getDniNieNuevo())
				&& !Misc.esVacio(usuarioSnsIn.getPasaporteNuevo())) {
				queryTit.append("and i.PASAPORTE = ? ");
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getPasaporteNuevo());
				siguienteParametro++;
			}
			/**
			 * si no es peke y no hay ni dni_nie, ni pasaporte se lanza la excepcion
			 */
			if (siguienteParametro==1 && !peke) {
				/**
				 * el titular tiene que tener obligatoriamente DNI_NIE o PASAPORTE
				 * si no no se busca en el INSS y se da por no encontrado
				 */
				throw new UsuarioSegSocialException("Usuario no encontrado para usuarioSnsIn -> [" + usuarioSnsIn.toString() + "]");
			}
			if (!Misc.esVacio(usuarioSnsIn.getNafNuevo())) {
				queryTit.append("and (i.NAF = ? OR i.NAF_SEC1 = ? OR i.NAF_SEC2 = ? OR i.NAF_SEC3 = ? OR i.NAF_SEC4 = ? OR i.NAF_SEC5 = ? OR i.NAF_SEC6 = ? OR i.NAF_SEC7 = ? OR i.NAF_SEC8 = ? OR i.NAF_SEC9 = ?) ");
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC1
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC2
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC3
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC4
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC5
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC6
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC7
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC8
				siguienteParametro++;
				hParam.put(Integer.toString(siguienteParametro), usuarioSnsIn.getNafNuevo()); //NAF_SEC9
				siguienteParametro++;
			}
			queryTit.append(" order by i.COD_INDICADOR_DE_FARMACIA,i.COD_SUBINDICADOR_DE_FARMACIA ");
			getLogger().debug("queryTit: " + queryTit.toString());
			Vector vResp = bd.consulta(queryTit.toString(), hParam);
			getLogger().debug("vResp: " + vResp);

			if (!vResp.isEmpty()) {
				resultadoInssExtBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
			} else {
				throw new UsuarioSegSocialException("Usuario no encontrado para usuarioSnsIn -> [" + usuarioSnsIn.toString() + "]");
			}

			return resultadoInssExtBean;
			
			
		}

		public ListaCamposAfectadosInss vincularSoloCodSns(ResultadoInssExtBean resultadoInss,String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SQLException {
			getLogger().debug("codUsuarioSns -> " + codUsuarioSns);
			getLogger().debug("resultadoInss.getIdInss() -> " + resultadoInss.getIdInss());
			getLogger().debug("criterioIdentificacion -> " + criterioIdentificacion);
			
			ListaCamposAfectadosInss listaCamposAfectadosInss=new ListaCamposAfectadosInss();
			
			if (esDiferente(resultadoInss.getCodUsuarioSns(), codUsuarioSns)) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, resultadoInss.getCodUsuarioSns(), codUsuarioSns));
			}
			if (esDiferente(resultadoInss.getCriterioIdentificacionSns(), criterioIdentificacion)) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, resultadoInss.getCriterioIdentificacionSns(), criterioIdentificacion));
			}

			try {
				actualizarInssSns(resultadoInss.getIdInss(), codUsuarioSns, criterioIdentificacion, bd);
			}catch (SQLException e) {
				throw new SincronizacionSQLException(e,codUsuarioSns,"InssSnsBean_UPDATE");
			}
			
			return listaCamposAfectadosInss;
		}
		
		public ListaCamposAfectadosInss vincularCodSns(ResultadoInssExtBean resultadoInss,String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SQLException {
			getLogger().debug("codUsuarioSns -> " + codUsuarioSns);
			getLogger().debug("resultadoInss.getIdInss() -> " + resultadoInss.getIdInss());
			getLogger().debug("criterioIdentificacion -> " + criterioIdentificacion);
			
			ListaCamposAfectadosInss listaCamposAfectadosInss=new ListaCamposAfectadosInss();
			
			if (esDiferente(resultadoInss.getCodUsuarioSns(), codUsuarioSns)) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, resultadoInss.getCodUsuarioSns(), codUsuarioSns));
			}
			if (esDiferente(resultadoInss.getCriterioIdentificacionSns(), criterioIdentificacion)) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, resultadoInss.getCriterioIdentificacionSns(), criterioIdentificacion));
			}

			try {
				Integer numActualizados=actualizarInssSns(resultadoInss.getIdInss(), codUsuarioSns, criterioIdentificacion, bd);
				if (numActualizados.intValue()==0) {
					/**
					 * si es nuevo no existira todavia ese registro en INSS_TIT_SNS
					 */
					/**
					 * desvinculamos el codSNS para dejarlo libre para este registro
					 */
					desvincularCodSns(codUsuarioSns,resultadoInss.getIdInss(),bd);
					
				}
			}catch (SincronizacionSQLException e) {
				if (e.getErrorCode()!=1) {
					throw e;
				}else{
					/**
					 * desvinculamos el codSNS para dejarlo libre para este registro
					 */
					desvincularCodSns(codUsuarioSns,resultadoInss.getIdInss(),bd);
					/**
					 * ARREGLO DEL BUG QUE NO INSERTABA EN EL INSS_SNS
					 */
					actualizarInssSns(resultadoInss.getIdInss(), codUsuarioSns, criterioIdentificacion, bd);
				}
			}
			

//			bd.actualizaSinCommit(InssSnsBean.QUERY_UPDATE, hParam);
			return listaCamposAfectadosInss;
		}
		
	/**
	 * Busca en el INSS por el ipf
	 * @param ipf
	 * @param bd
	 * @return
	 * @throws MultipleIpfException
	 * @throws UsuarioSegSocialException
	 * @throws SQLException
	 */
	public ResultadoInssExtBean findByIpf(String ipf, AccesoBD bd) throws MultipleIpfException,UsuarioSegSocialException, SQLException {
		String query = "select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns()
				+ " b where i.IPF = ? and b.ID_INSS=i.ID_INSS";
		getLogger().debug(query);
		try {
			if (Misc.esVacio(ipf.trim())) {
				throw new UsuarioSegSocialException("Usuario con ipf en blanco -> [" + ipf + "]");
			}
			ResultadoInssExtBean inssBean = new ResultadoInssExtBean();
			HashMap hParam = new HashMap();
			hParam.put("1", ipf);
			Vector vResp = bd.consulta(query, hParam);
			if (vResp.size()>1) {
				throw new MultipleIpfException(ipf);
			}else if (vResp.size()==1) {
				inssBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
				// inssBean.setNafIdentificado("naf");

			} else {
				throw new UsuarioSegSocialException("Usuario no encontrado para ipf -> [" + ipf + "]");
			}
			return inssBean;
		} catch (SQLException e) {
			getLogger().error(query);
			throw e;
		}
	}
	
	/**
	 * Busca en las tablas del INSS_TIT y INSS_SNS_TIT con los criterios de busqueda pasados por parametro
	 * independientemente de la situacion del registro
	 * @param criteriosBusqueda
	 * @param bd
	 * @return
	 * @throws SQLException
	 */
	public ArrayList findBy(CriteriosBusqueda criteriosBusqueda, AccesoBD bd) throws SQLException {
		int contadorCriterios=0;
		HashMap hParam=new HashMap();
		ArrayList candidatos=new ArrayList();
		StringBuffer query = new StringBuffer(); 
		query.append("select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS ");
		query.append("from ").append(getNombreTablaInss()).append(" i,").append(getNombreTablaInssSns()).append(" b ");
		/**
		 * criterio por codSns
		 */
		if (!Misc.esVacio(criteriosBusqueda.getCodUsuarioSns())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" b.COD_USUARIO_SNS = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getCodUsuarioSns());
		}
		/**
		 * criterio por ipf
		 */
		if (!Misc.esVacio(criteriosBusqueda.getIpf())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" i.ipf = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getIpf());
		}
		/**
		 * criterio por dni_nie
		 */
		if (!Misc.esVacio(criteriosBusqueda.getDniNie())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" i.dni_nie = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getDniNie());
		}
		/**
		 * criterio por pasaporte
		 */
		if (!Misc.esVacio(criteriosBusqueda.getPasaporte())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" i.pasaporte = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getPasaporte());
		}

		/**
		 * criterio por naf
		 */
		if (!Misc.esVacio(criteriosBusqueda.getNaf())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" (");
			query.append(" i.naf = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec1 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec2 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec3 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec4 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec5 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec6 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec7 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec8 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			contadorCriterios++;
			query.append(" or i.naf_sec9 = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getNaf());
			query.append(" ) ");
		}
		/**
		 * criterio por naf_titular
		 * @TODO de momento no esta habilitado esta busqueda
		 */
		
		/**
		 * criterio cod_situacion
		 */
		if (!Misc.esVacio(criteriosBusqueda.getCodSituacion())) {
			if (contadorCriterios==0) {
				query.append("where ");
			}else{
				query.append(" and ");
			}
			contadorCriterios++;
			query.append(" i.COD_SITUACION = ? ");
			hParam.put(Integer.toString(contadorCriterios), criteriosBusqueda.getCodSituacion().toUpperCase());
		}
		
		
		/**
		 * JOIN de INSS_TIT y INSS_SNS_TIT
		 */
		query.append(" and b.ID_INSS=i.ID_INSS");
		
		/**
		 * si en el criterio de busqueda se indica un numero maximo se aplica el ROWNUM
		 */
		if (criteriosBusqueda.getNumeroMaximoResultados()!=0) {
			query.append(" and ROWNUM <= ");
			query.append(criteriosBusqueda.getNumeroMaximoResultados());
		}

		getLogger().debug(query);

		/**
		 * si hay criterios de busqueda se lanza la query a base de datos
		 */
		if (contadorCriterios!=0) {
			
			Vector vDatos=bd.consulta(query.toString(), hParam);
			for (int i=0;i<vDatos.size();i++) {
				ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean((HashMap)vDatos.get(i));
				candidatos.add(resultadoInssExtBean);
			}
			
		}
		
		return candidatos;
	}

	/**
	 * Busca en el INSS por codUsuarioSns
	 * @param codUsuarioSns
	 * @param bd
	 * @return
	 * @throws UsuarioSegSocialException
	 * @throws SQLException
	 */
	public ResultadoInssExtBean findByCodUsuarioSns(String codUsuarioSns, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
		String query = "select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns()
				+ " b where b.COD_USUARIO_SNS = ? and b.ID_INSS=i.ID_INSS";
		getLogger().debug(query);
		try {
			ResultadoInssExtBean inssBean = new ResultadoInssExtBean();
			HashMap hParam = new HashMap();
			hParam.put("1", codUsuarioSns);
			Vector vResp = bd.consulta(query, hParam);
			if (!vResp.isEmpty()) {
				/**
				 * solo puede venir uno porque en la base de datos es indice unico
				 */
				inssBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
//				inssBean.setCriterioIdentificacionSns("COD_USUARIO_SNS");

			} else {
				throw new UsuarioSegSocialException("Usuario no encontrado para codUsuarioSns -> [" + codUsuarioSns + "]");
			}
			return inssBean;
		} catch (SQLException e) {
			getLogger().error(query);
			throw e;
		}
	}

	/**
	 * Busca en el INSS por idInss
	 * @param idInss
	 * @param bd
	 * @return
	 * @throws UsuarioSegSocialException
	 * @throws SQLException
	 */
	public ResultadoInssExtBean findByPrimaryKey(BigDecimal idInss, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
		ResultadoInssExtBean inssBean = new ResultadoInssExtBean();
		String query = "select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns()
		+ " b where i.ID_INSS = ? and b.ID_INSS=i.ID_INSS";
		HashMap hParam = new HashMap();
		hParam.put("1", idInss);
		Vector vResp = bd.consulta(query, hParam);
		if (!vResp.isEmpty()) {
			inssBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
		} else {
			throw new UsuarioSegSocialException("Usuario no encontrado para el idInss -> [" + idInss + "]");
		}
		return inssBean;
	}

	
	public ResultadoInssExtBean findTitularByNaf(String naf) throws MultipleNafException, UsuarioSegSocialException, SQLException {
		getLogger().debug("Buscamos en el inss por [naf] [" + naf + "]");
		ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean();
		AccesoBD bd=null;
		try {
			bd=getAccesoBd();
			try {
				resultadoInssExtBean=findByNafPrincipal(naf, bd);
				if (!resultadoInssExtBean.isVacio()) {
					resultadoInssExtBean.setNafIgualEnInss(naf);
				}else{
					resultadoInssExtBean=findNafSecundarios(naf, bd);
					if (!resultadoInssExtBean.isVacio()) {
						if ("naf_sec1".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec1());
						}
						if ("naf_sec2".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec2());
						}
						if ("naf_sec3".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec3());
						}
						if ("naf_sec4".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec4());
						}
						if ("naf_sec5".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec5());
						}
						if ("naf_sec6".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec6());
						}
						if ("naf_sec7".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec7());
						}
						if ("naf_sec8".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec8());
						}
						if ("naf_sec9".equals(resultadoInssExtBean.getNafIdentificado())) {
							resultadoInssExtBean.setNafIgualEnInss(resultadoInssExtBean.getNafSec9());
						}
					}
				}

			}catch (UsuarioSegSocialException e) {
				//es no encontrado, buscamos por naf_secundarios
			}catch (MultipleNafException e) {
				getLogger().error("Error nafs multiples buscando la aportacion por NAF en la tabla del INSS",e);
			}
			
		}catch (Exception e) {
			getLogger().error("Error buscando el tipo de aportacion en la tabla del INSS",e);
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
		return resultadoInssExtBean;
	}
	
	/**
	 * Busca en el INSS por ese naf, como principal
	 * @param naf
	 * @param bd
	 * @return
	 * @throws MultipleNafException
	 * @throws UsuarioSegSocialException
	 * @throws SQLException
	 */
	public ResultadoInssExtBean findByNafPrincipal(String naf, AccesoBD bd) throws MultipleNafException, UsuarioSegSocialException, SQLException {
		ResultadoInssExtBean inssBean = new ResultadoInssExtBean();
		
		String query = "select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns()
						+ " b where i.NAF = ?  and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS";
		
		getLogger().debug(query);
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		Vector vResp = bd.consulta(query, hParam);
		if (!vResp.isEmpty()) {
			if (vResp.size()>1) {
				throw new MultipleNafException();
			}
			inssBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
			inssBean.setNafIdentificado("naf");

		} else {
			throw new UsuarioSegSocialException("Usuario no encontrado para naf -> [" + naf + "]");
		}
		return inssBean;
	}
	
	/**
	 * Busca en el INSS ese naf como secundario
	 * @param naf
	 * @param bd
	 * @return
	 * @throws UsuarioSegSocialException
	 * @throws SQLException
	 */
	public ResultadoInssExtBean findNafSecundarios(String naf, AccesoBD bd) throws UsuarioSegSocialException, SQLException {
		ResultadoInssExtBean inssBean = new ResultadoInssExtBean();
		String[] querySecundarios = { "select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC1 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS", 
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC2 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC3 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC4 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC5 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC6 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC7 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC8 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS",
				"select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS from " + getNombreTablaInss() + " i," + getNombreTablaInssSns() + " b where i.NAF_SEC9 = ? and i.COD_SITUACION<>'B' and b.ID_INSS=i.ID_INSS" };
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		boolean encontrado = false;
		for (int i = 0; i < querySecundarios.length && !encontrado; i++) {
			Vector vResp = bd.consulta(querySecundarios[i], hParam);
			if (!vResp.isEmpty()) {
				inssBean = new ResultadoInssExtBean((HashMap) vResp.elementAt(0));
				inssBean.setNafIdentificado("naf_sec" + (i + 1));
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new UsuarioSegSocialException("Usuario no encontrado para naf -> [" + naf + "]");
		}
		return inssBean;
	}
	
	/**
	 * Guarda en el inss_historico los cambios que se han producido en el registro del INSS
	 * @param idInss
	 * @param listaCamposAfectadosInss
	 * @param procedencia
	 * @param codTipoProcedencia
	 * @param bd
	 * @throws SQLException
	 */
	public void guardarHistorico(BigDecimal idInss, ListaCamposAfectadosInss listaCamposAfectadosInss, BigDecimal procedencia,BigDecimal codTipoProcedencia,AccesoBD bd) throws SQLException {
		if (!listaCamposAfectadosInss.isEmpty()) {
			/**
			 * insertamos en la tabla INSS_HISTORICO
			 */
			BigDecimal idInssHistorico = bd.getNextCodigoSecuencia(InssHistoricoBean.NOMBRE_SECUENCIA_ASOCIADA);
			HashMap hParam = new HashMap();
			hParam.put(InssHistoricoBean.INDICE_INSERT_ID_INSS, idInss);
			hParam.put(InssHistoricoBean.INDICE_INSERT_ID_INSS_HISTORICO, idInssHistorico);
			hParam.put(InssHistoricoBean.INDICE_INSERT_FECHA_APLICACION_SOLICITUD, new Timestamp(System.currentTimeMillis()));
			hParam.put(InssHistoricoBean.INDICE_INSERT_COD_TIPO_PROCEDENCIA, codTipoProcedencia);
			hParam.put(InssHistoricoBean.INDICE_INSERT_PROCEDENCIA, procedencia);

			bd.actualizaSinCommit(InssHistoricoBean.QUERY_INSERT, hParam);

			/**
			 * insertamos los campos afectados en la tabla INSS_CAMPOS_AFECTADOS
			 */
			ArrayList listaCampos = listaCamposAfectadosInss.getListaCamposAfectados();
			for (int i = 0; i < listaCampos.size(); i++) {
				CamposAfectadosInss camposAfectadosInss = (CamposAfectadosInss) listaCampos.get(i);
				hParam = new HashMap();
				hParam.put(InssCamposAfectadosBean.INDICE_INSERT_COD_CAMPO, camposAfectadosInss.getCodCampo());
				hParam.put(InssCamposAfectadosBean.INDICE_INSERT_VALOR_ANTERIOR, camposAfectadosInss.getValorAnterior());
				hParam.put(InssCamposAfectadosBean.INDICE_INSERT_VALOR_NUEVO, camposAfectadosInss.getValorNuevo());
				hParam.put(InssCamposAfectadosBean.INDICE_INSERT_ID_INSS_HISTORICO, idInssHistorico);

				bd.actualizaSinCommit(InssCamposAfectadosBean.QUERY_INSERT, hParam);
			}
		}

	}

	/**
	 * Inserta en la tabla INSS_PROCESANDO el registro que ha venido en el IFI
	 * @param registroInss
	 * @throws SQLException
	 */
	public void insertarLineaProcesando(RegistroInss registroInss) throws SQLException {
		AccesoBD bd = null;
		try {
			bd = getAccesoBd();
			HashMap hParam = new HashMap();
			hParam.put(InssProcesandoBean.INDICE_INSERT_COD_TIPO_MOVIMIENTO, registroInss.getIfiRegistro().getTipoMovimiento());
			hParam.put(InssProcesandoBean.INDICE_INSERT_ID_INSS_FICHERO, registroInss.getIdInssFichero());
			hParam.put(InssProcesandoBean.INDICE_INSERT_LINEA, registroInss.getIfiRegistro().getLinea());
			hParam.put(InssProcesandoBean.INDICE_INSERT_NUMERO_LINEA, Integer.toString(registroInss.getIfiRegistro().getNumLinea()));

			try {
				hParam.put(InssProcesandoBean.INDICE_INSERT_FECHA_EFECTO_SITUACION, new java.sql.Date(FORMAT_FECHA_INSS.parse(registroInss.getIfiRegistro().getFechaEfectoSituacion()).getTime()));
			} catch (ParseException e) {
				hParam.put(InssProcesandoBean.INDICE_INSERT_FECHA_EFECTO_SITUACION, null);
			}
			bd.actualizaSinCommit(InssProcesandoBean.QUERY_INSERT, hParam);
		} finally {
			if (bd != null) {
				bd.cerrar();
			}
		}
	}
	
	/**
	 * Inserta en la tabla INSS_AS_FICHEROS que ese idInss viene en ese fichero
	 * 
	 * @param idInss
	 * @param registroInss
	 * @param bd
	 * @throws SQLException
	 */
	protected void insertarInssAsFicheros(BigDecimal idInss, RegistroInss registroInss, AccesoBD bd) throws SQLException {
		try {
			HashMap hParam = new HashMap();
			hParam.put(InssAsFicherosBean.INDICE_INSERT_ID_INSS, idInss);
			hParam.put(InssAsFicherosBean.INDICE_INSERT_ID_INSS_FICHERO, registroInss.getIdInssFichero());
			hParam.put(InssAsFicherosBean.INDICE_INSERT_NUMERO_LINEA, Integer.toString(registroInss.getIfiRegistro().getNumLinea()));
			bd.actualizaSinCommit(InssAsFicherosBean.QUERY_INSERT, hParam);
		}catch (SQLException e) {
			if (e.getErrorCode()!=1) {
				throw e;
			}
		}
	}

	/**
	 * Busca en el INSS por el dniNie. Retorna un array list con los usuarios encontrados y otro caso el arraylist sera vacio
	 * 
	 * @param dniNie
	 * @param bd
	 * @return
	 * @throws Exception
	 */
	public ArrayList findByDniNie(String dniNie,AccesoBD bd) throws Exception {
		//
		ArrayList arrayList = new ArrayList ();
		//
		String query   = "";
		HashMap parametros = new HashMap();
		//
		PreparedStatement ps = null;
		ResultSet rs = null;
		//	
		try {
			getLogger().debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   INSS_TIT i, INSS_SNS_TIT b ");
			sbQuery.append(" where  i.dni_nie = ?  ");
			sbQuery.append(" and    b.ID_INSS = i.ID_INSS ");
			query = sbQuery.toString();
			//
			parametros.put("1", dniNie);
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			ps = (PreparedStatement) hashMapRaw.get("ps");
			rs = (ResultSet) hashMapRaw.get("rs");
			//
			//
			while (rs.next()) {
				ResultadoInssExtBean resultadoInssExtBean = new ResultadoInssExtBean(rs);
				arrayList.add(resultadoInssExtBean);
			}
			getLogger().debug("FIN");
		}
		catch (Exception e) {
			getLogger().error(query + " -> " + parametros,e);
			throw e;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e1) {
				rs = null;
			}
			try {
				if (ps != null) {
					ps.close();
				}
			}
			catch (Exception e2) {
				ps = null;
			}
		}
		return arrayList;
	}
	
	/**
	 * Busca en el INSS por el dniNie. Retorna un array list con los usuarios encontrados y otro caso el arraylist sera vacio
	 * 
	 * @param pasaporte
	 * @param bd
	 * @return
	 * @throws Exception
	 */
	public ArrayList findByPasaporte(String pasaporte,AccesoBD bd) throws Exception {
		//
		ArrayList arrayList = new ArrayList ();
		//
		String query   = "";
		HashMap parametros = new HashMap();
		//
		PreparedStatement ps = null;
		ResultSet rs = null;
		//	
		try {
			getLogger().debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select i.*,b.COD_USUARIO_SNS,b.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   INSS_TIT i, INSS_SNS_TIT b ");
			sbQuery.append(" where  i.pasaporte  = ?  ");
			sbQuery.append(" and    b.ID_INSS    = i.ID_INSS ");
			query = sbQuery.toString();
			//
			parametros.put("1", pasaporte);
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			ps = (PreparedStatement) hashMapRaw.get("ps");
			rs = (ResultSet) hashMapRaw.get("rs");
			//
			//
			while (rs.next()) {
				ResultadoInssExtBean resultadoInssExtBean = new ResultadoInssExtBean(rs);
				arrayList.add(resultadoInssExtBean);
			}
			getLogger().debug("FIN");
		}
		catch (Exception e) {
			getLogger().error(query + " -> " + parametros,e);
			throw e;
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e1) {
				rs = null;
			}
			try {
				if (ps != null) {
					ps.close();
				}
			}
			catch (Exception e2) {
				ps = null;
			}
		}
		return arrayList;
	}

	/**
	 * 
	 * @param ipf
	 * @param idInssProcedencia esta formada por las 6 primeras posiciones que corresponde al id_inss_fichero y las 8 siguientes que es la linea dentro del fichero 
	 * @param bd
	 * @throws SQLException,TsiException
	 */
	public void desvincularCodSnsPorConstraintIpfUnico(String ipf,BigDecimal idInssProcedencia,AccesoBD bd) throws SQLException,TsiException {
		desvincularCodSnsGenerico(ipf,idInssProcedencia,Constantes.INSS_HISTORICO_PROCEDENCIA_IPF_UNICO_CONSTRAINT,bd);
	}

	/**
	 * 
	 * @param ipf
	 * @param idInssProcedencia esta formada por las 6 primeras posiciones que corresponde al id_inss_fichero y las 8 siguientes que es la linea dentro del fichero 
	 * @param bd
	 * @throws SQLException,TsiException
	 */
	public void desvincularCodSnsPorDuplicidad(String ipf,BigDecimal idInssFichero,AccesoBD bd) throws SQLException {
		try {
			desvincularCodSnsGenerico(ipf,idInssFichero,Constantes.INSS_HISTORICO_PROCEDENCIA_FICHERO,bd);
		}catch (TsiException e) {
			/**
			 * si se da una torta porque no se encuentra el IPF no hacer nada, ni decir nada
			 */
		}
	}
	
	private void desvincularCodSnsGenerico(String ipf,BigDecimal idInssProcedencia,BigDecimal codProcedencia,AccesoBD bd) throws SQLException,TsiException {
		String sqlUpdate="update " + getNombreTablaInssSns() +" set COD_USUARIO_SNS=null,CRITERIO_IDENTIFICACION_SNS=null where ID_INSS=?";

		/**
		 * puede saltar un SQLException o un TsiException si no se encuentra ese IPF o existe pero multiple
		 */
		ResultadoInssExtBean inssExtBean = findByIpf(ipf, bd);
		
		HashMap hParam=new HashMap();
		hParam.put("1", inssExtBean.getIdInss());
		bd.actualizaSinCommit(sqlUpdate, hParam);
			
		ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();
		listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, inssExtBean.getCriterioIdentificacionSns(), ""));
		listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, inssExtBean.getCodUsuarioSns(), ""));
		guardarHistorico(inssExtBean.getIdInss(), listaCamposAfectadosInss,idInssProcedencia,codProcedencia, bd);
	}

	
	public void moverAbajas(String ipf,String codMotivoBaja,BigDecimal idInssFichero,AccesoBD bd) throws SQLException {
		StringBuffer sqlInsert=new StringBuffer();
		sqlInsert.append("insert into " + getNombreTablaInssBajas() + " ");
		sqlInsert.append("  select  ");
		sqlInsert.append("  ID_INSS, ");
		sqlInsert.append("  COD_TIPO_ASEGURADO, ");
		sqlInsert.append("  IPF, ");
		sqlInsert.append("  DNI_NIE, ");
		sqlInsert.append("  PASAPORTE, ");
		sqlInsert.append("  NAF, ");
		sqlInsert.append("  NAF_SEC1, ");
		sqlInsert.append("  NAF_SEC2, ");
		sqlInsert.append("  NAF_SEC3, ");
		sqlInsert.append("  NAF_SEC4, ");
		sqlInsert.append("  NAF_SEC5, ");
		sqlInsert.append("  NAF_SEC6, ");
		sqlInsert.append("  NAF_SEC7, ");
		sqlInsert.append("  NAF_SEC8, ");
		sqlInsert.append("  NAF_SEC9, ");
		sqlInsert.append("  INDICATIVO_NOMBRE, ");
		sqlInsert.append("  APELLIDOS_NOMBRE, ");
		sqlInsert.append("  APELLIDO1, ");
		sqlInsert.append("  APELLIDO2, ");
		sqlInsert.append("  NOMBRE, ");
		sqlInsert.append("  NACIONALIDAD, ");
		sqlInsert.append("  FECHA_NACIMIENTO, ");
		sqlInsert.append("  SEXO, ");
		sqlInsert.append("  INDICATIVO_DOMICILIO, ");
		sqlInsert.append("  DOMICILIO, ");
		sqlInsert.append("  TIPO_ASEGURAMIENTO, ");
		sqlInsert.append("  COD_INDICADOR_DE_FARMACIA, ");
		sqlInsert.append("  COD_SUBINDICADOR_DE_FARMACIA, ");
		sqlInsert.append("  COD_SITUACION, ");
		sqlInsert.append("  FECHA_EFECTO_SITUACION, ");
		sqlInsert.append("  COD_TIPO_BENEFICIARIO, ");
		sqlInsert.append("  IPF_TITULAR, ");
		sqlInsert.append("  NAF_TITULAR, ");
		sqlInsert.append("  NUMERO_SECUENCIA, ");
		sqlInsert.append("  FECHA_NACIMIENTO_RAW, ");
		sqlInsert.append("  '").append(codMotivoBaja).append("' COD_TIPO_MOTIVO_BAJA, ");
		sqlInsert.append("  sysdate, ");
		sqlInsert.append("  ").append(idInssFichero).append(" ID_INSS_FICHERO ");
		sqlInsert.append("  FROM INSS_TIT ");
		sqlInsert.append("  where ipf=? ");

		String sqlDeleteInss="DELETE FROM " + getNombreTablaInss() + " where ipf = ?";
		HashMap hParam=new HashMap();
		hParam.put("1", ipf);
		/**
		 * volcamos los datos de la tabla INSS_TIT a INSS_TIT_BAJAS
		 */
		bd.actualizaSinCommit(sqlInsert.toString(), hParam);
		/**
		 * borramos de la tabla INSS_TIT e INSS_SNS_TIT
		 */
		bd.actualizaSinCommit(sqlDeleteInss, hParam);
	}
	
	/**
	 * Inserta en el INSS_CUARENTENA_CRUCE_SNS el registro del INSS recibido, porque o bien no se encuentra
	 * o con estos criterios se encuentran multiples
	 * @param idInssFichero
	 * @param numLinea
	 * @param ipf
	 * @param coincidente
	 * @param causa
	 * @param bd
	 * @throws SQLException
	 */
	public void meterCuarentena(BigDecimal idInssFichero,int numLinea,String ipf, ResultadoCruceSnsBean coincidente, String causa, AccesoBD bd) throws SQLException {
		BigDecimal idInssCuarentena=bd.getNextCodigoSecuencia(InssCuarentenaCruceSns.NOMBRE_SECUENCIA_ASOCIADA);
		InssCuarentenaCruceSns inssCuarentenaCruceSns=new InssCuarentenaCruceSns();
		inssCuarentenaCruceSns.setIdInssCuarentena(idInssCuarentena);
		inssCuarentenaCruceSns.setIdInssFichero(idInssFichero);
		inssCuarentenaCruceSns.setNumeroLinea(new BigDecimal(numLinea));
		inssCuarentenaCruceSns.setIpf(ipf);
		if (coincidente!=null) {
			inssCuarentenaCruceSns.setCodUsuarioSns(coincidente.getCodUsuarioSns());
			inssCuarentenaCruceSns.setCriterioIdentificacionSns(coincidente.getCriterioIdentificacion());
		}
		inssCuarentenaCruceSns.setCausa(causa);
		
		bd.actualizaSinCommit(InssCuarentenaCruceSns.QUERY_INSERT, inssCuarentenaCruceSns.getParametrosInsert());
	}

	public void actualizarSituacionBaja(ResultadoInssExtBean resultadoInss, RegistroInss registroInss,AccesoBD bd) throws SQLException {
		try {
			String QUERY_UPDATE = "UPDATE "
				+ getNombreTablaInss()
				+ " set COD_SITUACION = ?, FECHA_EFECTO_SITUACION = ?, COD_TIPO_MOTIVO_BAJA = ? where ID_INSS = ?";
			IfiFicheroBean ifiFicheroBean = registroInss.getIfiRegistro();
			ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();

			if (esDiferente(resultadoInss.getCodSituacion(), ifiFicheroBean.getCodSituacion())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_SITUACION, resultadoInss.getCodSituacion(), ifiFicheroBean.getCodSituacion()));
			}
			try {
				if (esDiferente(FORMAT_FECHA_INSS.format(new Date(resultadoInss.getFechaEfectoSituacion().getTime())), ifiFicheroBean.getFechaEfectoSituacion())) {
					listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.FECHA_EFECTO_SITUACION, FORMAT_FECHA_INSS.format(new Date(resultadoInss.getFechaEfectoSituacion()
							.getTime())), ifiFicheroBean.getFechaEfectoSituacion()));
				}
			}catch (Exception e) {}
			if (esDiferente(resultadoInss.getCodTipoMotivoBaja(), ifiFicheroBean.getCodTipoMotivoBaja())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_TIPO_MOTIVO_BAJA, resultadoInss.getCodTipoMotivoBaja(), ifiFicheroBean.getCodTipoMotivoBaja()));
			}

			
			if (!listaCamposAfectadosInss.isEmpty()) {

				HashMap hParam = new HashMap();
				hParam.put("1", ifiFicheroBean.getCodSituacion());
				try {
					hParam.put("2", new java.sql.Date(FORMAT_FECHA_INSS.parse(ifiFicheroBean.getFechaEfectoSituacion()).getTime()));
				} catch (Exception e) {
					hParam.put("2", null);
				}

				hParam.put("3", ifiFicheroBean.getCodTipoMotivoBaja());
				hParam.put("4", resultadoInss.getIdInss());

				bd.actualizaSinCommit(QUERY_UPDATE, hParam);

				guardarHistorico(resultadoInss.getIdInss(), listaCamposAfectadosInss,registroInss.getIdInssFichero(),Constantes.INSS_HISTORICO_PROCEDENCIA_FICHERO, bd);

				try {
					/**
					 * insertamos en la tabla de INSS_AS_FICHEROS
					 */
					insertarInssAsFicheros(resultadoInss.getIdInss(), registroInss, bd);
				}catch (SQLException e) {
					if (e.getErrorCode()!=1) {
						throw e;
					}
				}
			}
		} catch (SQLException e) {
//			logger.error("Error en la actualizacion", e);
			throw e;
		}

	}
	
	
	public void actualizar(ResultadoInssExtBean resultadoInss, RegistroInss registroInss, ResultadoCruceSnsBean resultadoCruceSnsBean,AccesoBD bd) throws SQLException {
		try {
			String QUERY_UPDATE = "UPDATE "
				+ getNombreTablaInss()
				+ " set COD_TIPO_ASEGURADO = ?, IPF = ?, DNI_NIE = ?, PASAPORTE = ?, NAF = ?, NAF_SEC1 = ?, NAF_SEC2 = ?, NAF_SEC3 = ?, NAF_SEC4 = ?, NAF_SEC5 = ?, NAF_SEC6 = ?, NAF_SEC7 = ?, NAF_SEC8 = ?, NAF_SEC9 = ?, INDICATIVO_NOMBRE = ?, APELLIDOS_NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, NOMBRE = ?, NACIONALIDAD = ?, FECHA_NACIMIENTO = ?, SEXO = ?, INDICATIVO_DOMICILIO = ?, DOMICILIO = ?, TIPO_ASEGURAMIENTO = ?, COD_INDICADOR_DE_FARMACIA = ?, COD_SUBINDICADOR_DE_FARMACIA = ?, COD_SITUACION = ?, FECHA_EFECTO_SITUACION = ?, COD_TIPO_BENEFICIARIO = ?, IPF_TITULAR = ?, NAF_TITULAR = ?, NUMERO_SECUENCIA = ?, FECHA_NACIMIENTO_RAW = ?, COD_TIPO_MOTIVO_BAJA = ? where ID_INSS = ?";
			IfiFicheroBean ifiFicheroBean = registroInss.getIfiRegistro();
			ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();

			if (esDiferente(resultadoInss.getCodTipoAsegurado(), ifiFicheroBean.getCodTipoAsegurado())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_TIPO_ASEGURADO, resultadoInss.getCodTipoAsegurado(), ifiFicheroBean.getCodTipoAsegurado()));
			}
			if (esDiferente(resultadoInss.getIpf(), ifiFicheroBean.getIpf())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.IPF, resultadoInss.getIpf(), ifiFicheroBean.getIpf()));
			}
			if (esDiferente(resultadoInss.getDniNie(), ifiFicheroBean.getDniNie())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.DNI_NIE, resultadoInss.getDniNie(), ifiFicheroBean.getDniNie()));
			}
			if (esDiferente(resultadoInss.getPasaporte(), ifiFicheroBean.getPasaporte())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.PASAPORTE, resultadoInss.getPasaporte(), ifiFicheroBean.getPasaporte()));
			}
			if (esDiferente(resultadoInss.getNaf(), ifiFicheroBean.getNaf())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF, resultadoInss.getNaf(), ifiFicheroBean.getNaf()));
			}
			if (esDiferente(resultadoInss.getNafSec1(), ifiFicheroBean.getNafSec1())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC1, resultadoInss.getNafSec1(), ifiFicheroBean.getNafSec1()));
			}
			if (esDiferente(resultadoInss.getNafSec2(), ifiFicheroBean.getNafSec2())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC2, resultadoInss.getNafSec2(), ifiFicheroBean.getNafSec2()));
			}
			if (esDiferente(resultadoInss.getNafSec3(), ifiFicheroBean.getNafSec3())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC3, resultadoInss.getNafSec3(), ifiFicheroBean.getNafSec3()));
			}
			if (esDiferente(resultadoInss.getNafSec4(), ifiFicheroBean.getNafSec4())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC4, resultadoInss.getNafSec4(), ifiFicheroBean.getNafSec4()));
			}
			if (esDiferente(resultadoInss.getNafSec5(), ifiFicheroBean.getNafSec5())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC5, resultadoInss.getNafSec5(), ifiFicheroBean.getNafSec5()));
			}
			if (esDiferente(resultadoInss.getNafSec6(), ifiFicheroBean.getNafSec6())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC6, resultadoInss.getNafSec6(), ifiFicheroBean.getNafSec6()));
			}
			if (esDiferente(resultadoInss.getNafSec7(), ifiFicheroBean.getNafSec7())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC7, resultadoInss.getNafSec7(), ifiFicheroBean.getNafSec7()));
			}
			if (esDiferente(resultadoInss.getNafSec8(), ifiFicheroBean.getNafSec8())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC8, resultadoInss.getNafSec8(), ifiFicheroBean.getNafSec8()));
			}
			if (esDiferente(resultadoInss.getNafSec9(), ifiFicheroBean.getNafSec9())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_SEC9, resultadoInss.getNafSec9(), ifiFicheroBean.getNafSec9()));
			}
			if (esDiferente(resultadoInss.getIndicativoNombre(), ifiFicheroBean.getIndicativoFormatoNombre())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.INDICATIVO_NOMBRE, resultadoInss.getIndicativoNombre(), ifiFicheroBean.getIndicativoFormatoNombre()));
			}
			if (esDiferente(resultadoInss.getApellidosNombre(), ifiFicheroBean.getApellidosNombre())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.APELLIDOS_NOMBRE, resultadoInss.getApellidosNombre(), ifiFicheroBean.getApellidosNombre()));
			}
			if (esDiferente(resultadoInss.getApellido1(), ifiFicheroBean.getApellido1())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.APELLIDO1, resultadoInss.getApellido1(), ifiFicheroBean.getApellido1()));
			}
			if (esDiferente(resultadoInss.getApellido2(), ifiFicheroBean.getApellido2())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.APELLIDO2, resultadoInss.getApellido2(), ifiFicheroBean.getApellido2()));
			}
			if (esDiferente(resultadoInss.getNombre(), ifiFicheroBean.getNombre())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NOMBRE, resultadoInss.getNombre(), ifiFicheroBean.getNombre()));
			}
			if (esDiferente(resultadoInss.getNacionalidad(), ifiFicheroBean.getNacionalidad())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NACIONALIDAD, resultadoInss.getNacionalidad(), ifiFicheroBean.getNacionalidad()));
			}
			if (esDiferente(resultadoInss.getSexo(), ifiFicheroBean.getCodSexo())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.SEXO, Misc.nz(resultadoInss.getSexo()), Misc.nz(ifiFicheroBean.getCodSexo())));
			}
			if (esDiferente(resultadoInss.getIndicativoDomicilio(), ifiFicheroBean.getIndicativoFormatoDomicilio())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.INDICATIVO_DOMICILIO, resultadoInss.getIndicativoDomicilio(), ifiFicheroBean
						.getIndicativoFormatoDomicilio()));
			}
			if (esDiferente(resultadoInss.getDomicilio(), ifiFicheroBean.getDomicilio())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.DOMICILIO, resultadoInss.getDomicilio(), ifiFicheroBean.getDomicilio()));
			}
			if (esDiferente(resultadoInss.getTipoAseguramiento(), ifiFicheroBean.getTipoAseguramiento())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.TIPO_ASEGURAMIENTO, resultadoInss.getTipoAseguramiento(), ifiFicheroBean.getTipoAseguramiento()));
			}
			if (esDiferente(resultadoInss.getCodIndicadorDeFarmacia(), ifiFicheroBean.getCodIndicadorDeFarmacia())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_INDICADOR_DE_FARMACIA, resultadoInss.getCodIndicadorDeFarmacia(), ifiFicheroBean
						.getCodIndicadorDeFarmacia()));
			}
			if (esDiferente(resultadoInss.getCodSubindicadorDeFarmacia(), ifiFicheroBean.getCodSubindicadorDeFarmacia())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_SUBINDICADOR_DE_FARMACIA, resultadoInss.getCodSubindicadorDeFarmacia(), ifiFicheroBean
						.getCodSubindicadorDeFarmacia()));
			}
			if (esDiferente(resultadoInss.getCodSituacion(), ifiFicheroBean.getCodSituacion())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_SITUACION, resultadoInss.getCodSituacion(), ifiFicheroBean.getCodSituacion()));
			}
			try {
			if (esDiferente(FORMAT_FECHA_INSS.format(new Date(resultadoInss.getFechaEfectoSituacion().getTime())), ifiFicheroBean.getFechaEfectoSituacion())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.FECHA_EFECTO_SITUACION, FORMAT_FECHA_INSS.format(new Date(resultadoInss.getFechaEfectoSituacion()
						.getTime())), ifiFicheroBean.getFechaEfectoSituacion()));
			}
			}catch (Exception e) {}
			if (esDiferente(Misc.rellenarIzq(Misc.nz(resultadoInss.getCodTipoBeneficiario()), "0", 2), ifiFicheroBean.getTipoBeneficiario())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_TIPO_BENEFICIARIO, Misc.nz(resultadoInss.getCodTipoBeneficiario()), ifiFicheroBean
						.getTipoBeneficiario()));
			}
			if (esDiferente(resultadoInss.getIpfTitular(), ifiFicheroBean.getIpfTitular())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.IPF_TITULAR, resultadoInss.getIpfTitular(), ifiFicheroBean.getIpfTitular()));
			}
			if (esDiferente(resultadoInss.getNafTitular(), ifiFicheroBean.getNafTitular())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NAF_TITULAR, resultadoInss.getNafTitular(), ifiFicheroBean.getNafTitular()));
			}
			if (esDiferente(Misc.rellenarIzq(Misc.nz(resultadoInss.getNumeroSecuencia()), "0", 2), ifiFicheroBean.getNumeroSecuencia())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.NUMERO_SECUENCIA, Misc.nz(resultadoInss.getNumeroSecuencia()), ifiFicheroBean.getNumeroSecuencia()));
			}
//			if (esDiferente(resultadoInss.getCodUsuarioSns(), resultadoCruceSnsBean.getCodUsuarioSns())) {
//				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, resultadoInss.getCodUsuarioSns(), resultadoCruceSnsBean.getCodUsuarioSns()));
//			}
//			if (esDiferente(resultadoInss.getCriterioIdentificacionSns(), resultadoCruceSnsBean.getCriterioIdentificacion())) {
//				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, resultadoInss.getCriterioIdentificacionSns(), resultadoCruceSnsBean
//						.getCriterioIdentificacion()));
//			}
			if (esDiferente(resultadoInss.getFechaNacimientoRaw(), ifiFicheroBean.getFechaNac())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.FECHA_NACIMIENTO, resultadoInss.getFechaNacimientoRaw(), ifiFicheroBean.getFechaNac()));
			}

			if (esDiferente(resultadoInss.getCodTipoMotivoBaja(), ifiFicheroBean.getCodTipoMotivoBaja())) {
				listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_TIPO_MOTIVO_BAJA, resultadoInss.getCodTipoMotivoBaja(), ifiFicheroBean.getCodTipoMotivoBaja()));
			}

			
			if (!listaCamposAfectadosInss.isEmpty()) {
				String codUsuarioSns = "";
				String criterioIdentificacion = "";
				if (!resultadoCruceSnsBean.isVacio()) {
					codUsuarioSns = resultadoCruceSnsBean.getCodUsuarioSns();
					criterioIdentificacion = resultadoCruceSnsBean.getCriterioIdentificacion();
				}

				HashMap hParam = new HashMap();
				hParam.put(InssBean.INDICE_UPDATE_ID_INSS, resultadoInss.getIdInss());
				hParam.put(InssBean.INDICE_UPDATE_COD_TIPO_ASEGURADO, ifiFicheroBean.getCodTipoAsegurado());
				hParam.put(InssBean.INDICE_UPDATE_IPF, ifiFicheroBean.getIpf());
				hParam.put(InssBean.INDICE_UPDATE_DNI_NIE, ifiFicheroBean.getDniNie());
				hParam.put(InssBean.INDICE_UPDATE_PASAPORTE, ifiFicheroBean.getPasaporte());
				hParam.put(InssBean.INDICE_UPDATE_NAF, ifiFicheroBean.getNaf());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC1, ifiFicheroBean.getNafSec1());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC2, ifiFicheroBean.getNafSec2());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC3, ifiFicheroBean.getNafSec3());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC4, ifiFicheroBean.getNafSec4());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC5, ifiFicheroBean.getNafSec5());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC6, ifiFicheroBean.getNafSec6());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC7, ifiFicheroBean.getNafSec7());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC8, ifiFicheroBean.getNafSec8());
				hParam.put(InssBean.INDICE_UPDATE_NAF_SEC9, ifiFicheroBean.getNafSec9());
				hParam.put(InssBean.INDICE_UPDATE_INDICATIVO_NOMBRE, ifiFicheroBean.getIndicativoFormatoNombre());
				hParam.put(InssBean.INDICE_UPDATE_APELLIDOS_NOMBRE, ifiFicheroBean.getApellidosNombre());
				hParam.put(InssBean.INDICE_UPDATE_APELLIDO1, ifiFicheroBean.getApellido1());
				hParam.put(InssBean.INDICE_UPDATE_APELLIDO2, ifiFicheroBean.getApellido2());
				hParam.put(InssBean.INDICE_UPDATE_NOMBRE, ifiFicheroBean.getNombre());
				hParam.put(InssBean.INDICE_UPDATE_NACIONALIDAD, ifiFicheroBean.getNacionalidad());
				hParam.put(InssBean.INDICE_UPDATE_FECHA_NACIMIENTO, null);
				hParam.put(InssBean.INDICE_UPDATE_SEXO, ifiFicheroBean.getCodSexo());
				hParam.put(InssBean.INDICE_UPDATE_INDICATIVO_DOMICILIO, ifiFicheroBean.getIndicativoFormatoDomicilio());
				hParam.put(InssBean.INDICE_UPDATE_DOMICILIO, ifiFicheroBean.getDomicilio());
				hParam.put(InssBean.INDICE_UPDATE_TIPO_ASEGURAMIENTO, ifiFicheroBean.getTipoAseguramiento());
				hParam.put(InssBean.INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA, ifiFicheroBean.getCodIndicadorDeFarmacia());
				hParam.put(InssBean.INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA, ifiFicheroBean.getCodSubindicadorDeFarmacia());
				hParam.put(InssBean.INDICE_UPDATE_COD_SITUACION, ifiFicheroBean.getCodSituacion());
				try {
					hParam.put(InssBean.INDICE_UPDATE_FECHA_EFECTO_SITUACION, new java.sql.Date(FORMAT_FECHA_INSS.parse(ifiFicheroBean.getFechaEfectoSituacion()).getTime()));
				} catch (Exception e) {
					hParam.put(InssBean.INDICE_UPDATE_FECHA_EFECTO_SITUACION, null);
				}

				hParam.put(InssBean.INDICE_UPDATE_COD_TIPO_BENEFICIARIO, ifiFicheroBean.getTipoBeneficiario());
				hParam.put(InssBean.INDICE_UPDATE_IPF_TITULAR, ifiFicheroBean.getIpfTitular());
				hParam.put(InssBean.INDICE_UPDATE_NAF_TITULAR, ifiFicheroBean.getNafTitular());
				hParam.put(InssBean.INDICE_UPDATE_NUMERO_SECUENCIA, ifiFicheroBean.getNumeroSecuencia());
				hParam.put(InssBean.INDICE_UPDATE_FECHA_NACIMIENTO_RAW, ifiFicheroBean.getFechaNac());
				hParam.put(InssBean.INDICE_UPDATE_COD_TIPO_MOTIVO_BAJA, ifiFicheroBean.getCodTipoMotivoBaja());

				bd.actualizaSinCommit(QUERY_UPDATE, hParam);

				ListaCamposAfectadosInss listaCamposAfectadosInssVincular=new ListaCamposAfectadosInss();
				try {
					/**
					 * intentamos vincular con el codigo SNS encontrado
					 */
					listaCamposAfectadosInssVincular=vincularSoloCodSns(resultadoInss,codUsuarioSns,criterioIdentificacion,bd);
				}catch (SQLException e) {
					if (e.getErrorCode()==1) {
						/**
						 * hemos intentado vincular un codSns que ya estaba vinculado
						 * tratamos el vinculado
						 */
						listaCamposAfectadosInssVincular=tratamientoDuplicado(resultadoInss, registroInss.getIfiRegistro() , codUsuarioSns,criterioIdentificacion,bd);
					}else{
						throw e;
					}
				}
				
				/**
				 * concatenamos los posibles cambios de codSns para apuntarlo en el historico
				 */
				listaCamposAfectadosInss.concat(listaCamposAfectadosInssVincular);

				guardarHistorico(resultadoInss.getIdInss(), listaCamposAfectadosInss,registroInss.getIdInssFichero(),Constantes.INSS_HISTORICO_PROCEDENCIA_FICHERO, bd);

				try {
					/**
					 * insertamos en la tabla de INSS_AS_FICHEROS
					 */
					insertarInssAsFicheros(resultadoInss.getIdInss(), registroInss, bd);
				}catch (SQLException e) {
					if (e.getErrorCode()!=1) {
						throw e;
					}
				}
			}else{
				/**
				 * no hay cambios aparentes vemos si hay cambio en el codigo SNS vinculado
				 */
				if ((Misc.esVacio(resultadoInss.getCodUsuarioSns()) 
						&& !Misc.nz(resultadoInss.getCodUsuarioSns()).equals(resultadoCruceSnsBean.getCodUsuarioSns()))
					|| 
						/**
						 * se añade esta condicion porque puede ser una recolocacion del codigo sns
						 * se identifica porque el criterio de identificacion comienza siempre por COD_USUARIO_SNS
						 */
						(resultadoInss.getCriterioIdentificacionSns().startsWith("COD_USUARIO_SNS")) ) {

					ListaCamposAfectadosInss listaCamposAfectadosInssVincular=new ListaCamposAfectadosInss();
					try {
						/**
						 * intentamos vincular con el codigo SNS encontrado
						 */
						listaCamposAfectadosInssVincular=vincularSoloCodSns(resultadoInss,resultadoCruceSnsBean.getCodUsuarioSns(),resultadoCruceSnsBean.getCriterioIdentificacion(),bd);
					}catch (SQLException e) {
						if (e.getErrorCode()==1) {
							/**
							 * hemos intentado vincular un codSns que ya estaba vinculado
							 * tratamos el vinculado
							 */
							listaCamposAfectadosInssVincular=tratamientoDuplicado(resultadoInss, registroInss.getIfiRegistro() , resultadoCruceSnsBean.getCodUsuarioSns(),resultadoCruceSnsBean.getCriterioIdentificacion(),bd);
						}else{
							throw e;
						}
					}
					guardarHistorico(resultadoInss.getIdInss(), listaCamposAfectadosInssVincular,registroInss.getIdInssFichero(),Constantes.INSS_HISTORICO_PROCEDENCIA_FICHERO, bd);
					try {
						/**
						 * insertamos en la tabla de INSS_AS_FICHEROS
						 */
						insertarInssAsFicheros(resultadoInss.getIdInss(), registroInss, bd);
					}catch (SQLException e) {
						if (e.getErrorCode()!=1) {
							throw e;
						}
					}

				}
			}
		} catch (SQLException e) {
//			logger.error("Error en la actualizacion", e);
			throw e;
		}

	}

	private boolean esDiferente(Object campo1, Object campo2) {
		return !Misc.nz(campo1).equals(Misc.nz(campo2));
	}

	public void insertar(RegistroInss registroInss, ResultadoCruceSnsBean resultadoCruceSnsBean, AccesoBD bd) throws SQLException {
		String QUERY_INSERT_INSS = "INSERT INTO " + getNombreTablaInss() + " (ID_INSS,COD_TIPO_ASEGURADO,IPF,DNI_NIE,PASAPORTE,NAF,NAF_SEC1,NAF_SEC2,NAF_SEC3,NAF_SEC4,NAF_SEC5,NAF_SEC6,NAF_SEC7,NAF_SEC8,NAF_SEC9,INDICATIVO_NOMBRE,APELLIDOS_NOMBRE,APELLIDO1,APELLIDO2,NOMBRE,NACIONALIDAD,FECHA_NACIMIENTO,SEXO,INDICATIVO_DOMICILIO,DOMICILIO,TIPO_ASEGURAMIENTO,COD_INDICADOR_DE_FARMACIA,COD_SUBINDICADOR_DE_FARMACIA,COD_SITUACION,FECHA_EFECTO_SITUACION,COD_TIPO_BENEFICIARIO,IPF_TITULAR,NAF_TITULAR,NUMERO_SECUENCIA,FECHA_NACIMIENTO_RAW,COD_TIPO_MOTIVO_BAJA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			/**
			 * insertamos en la tabla de INSS
			 */
			BigDecimal idInss = bd.getNextCodigoSecuencia(InssBean.NOMBRE_SECUENCIA_ASOCIADA);

			String codUsuarioSns = "";
			String criterioIdentificacion = "";
			if (!resultadoCruceSnsBean.isVacio()) {
				codUsuarioSns = resultadoCruceSnsBean.getCodUsuarioSns();
				criterioIdentificacion = resultadoCruceSnsBean.getCriterioIdentificacion();
			}

			IfiFicheroBean ifiFicheroBean = registroInss.getIfiRegistro();
			HashMap hParam = new HashMap();
			hParam.put(InssBean.INDICE_INSERT_ID_INSS, idInss);
			hParam.put(InssBean.INDICE_INSERT_COD_TIPO_ASEGURADO, ifiFicheroBean.getCodTipoAsegurado());
			hParam.put(InssBean.INDICE_INSERT_IPF, ifiFicheroBean.getIpf());
			hParam.put(InssBean.INDICE_INSERT_DNI_NIE, ifiFicheroBean.getDniNie());
			hParam.put(InssBean.INDICE_INSERT_PASAPORTE, ifiFicheroBean.getPasaporte());
			hParam.put(InssBean.INDICE_INSERT_NAF, ifiFicheroBean.getNaf());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC1, ifiFicheroBean.getNafSec1());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC2, ifiFicheroBean.getNafSec2());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC3, ifiFicheroBean.getNafSec3());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC4, ifiFicheroBean.getNafSec4());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC5, ifiFicheroBean.getNafSec5());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC6, ifiFicheroBean.getNafSec6());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC7, ifiFicheroBean.getNafSec7());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC8, ifiFicheroBean.getNafSec8());
			hParam.put(InssBean.INDICE_INSERT_NAF_SEC9, ifiFicheroBean.getNafSec9());
			hParam.put(InssBean.INDICE_INSERT_INDICATIVO_NOMBRE, ifiFicheroBean.getIndicativoFormatoNombre());
			hParam.put(InssBean.INDICE_INSERT_APELLIDOS_NOMBRE, ifiFicheroBean.getApellidosNombre());
			hParam.put(InssBean.INDICE_INSERT_APELLIDO1, ifiFicheroBean.getApellido1());
			hParam.put(InssBean.INDICE_INSERT_APELLIDO2, ifiFicheroBean.getApellido2());
			hParam.put(InssBean.INDICE_INSERT_NOMBRE, ifiFicheroBean.getNombre());
			hParam.put(InssBean.INDICE_INSERT_NACIONALIDAD, ifiFicheroBean.getNacionalidad());
			hParam.put(InssBean.INDICE_INSERT_FECHA_NACIMIENTO, null);
			hParam.put(InssBean.INDICE_INSERT_SEXO, ifiFicheroBean.getCodSexo());
			hParam.put(InssBean.INDICE_INSERT_INDICATIVO_DOMICILIO, ifiFicheroBean.getIndicativoFormatoDomicilio());
			hParam.put(InssBean.INDICE_INSERT_DOMICILIO, ifiFicheroBean.getDomicilio());
			hParam.put(InssBean.INDICE_INSERT_TIPO_ASEGURAMIENTO, ifiFicheroBean.getTipoAseguramiento());
			hParam.put(InssBean.INDICE_INSERT_COD_INDICADOR_DE_FARMACIA, ifiFicheroBean.getCodIndicadorDeFarmacia());
			hParam.put(InssBean.INDICE_INSERT_COD_SUBINDICADOR_DE_FARMACIA, ifiFicheroBean.getCodSubindicadorDeFarmacia());
			hParam.put(InssBean.INDICE_INSERT_COD_SITUACION, ifiFicheroBean.getCodSituacion());
			try {
				hParam.put(InssBean.INDICE_INSERT_FECHA_EFECTO_SITUACION, new java.sql.Date(FORMAT_FECHA_INSS.parse(ifiFicheroBean.getFechaEfectoSituacion()).getTime()));
			} catch (Exception e) {
				hParam.put(InssBean.INDICE_INSERT_FECHA_EFECTO_SITUACION, null);
			}

			hParam.put(InssBean.INDICE_INSERT_COD_TIPO_BENEFICIARIO, ifiFicheroBean.getTipoBeneficiario());
			hParam.put(InssBean.INDICE_INSERT_IPF_TITULAR, ifiFicheroBean.getIpfTitular());
			hParam.put(InssBean.INDICE_INSERT_NAF_TITULAR, ifiFicheroBean.getNafTitular());
			hParam.put(InssBean.INDICE_INSERT_NUMERO_SECUENCIA, ifiFicheroBean.getNumeroSecuencia());
			hParam.put(InssBean.INDICE_INSERT_FECHA_NACIMIENTO_RAW, ifiFicheroBean.getFechaNac());
			hParam.put(InssBean.INDICE_INSERT_COD_TIPO_MOTIVO_BAJA, ifiFicheroBean.getCodTipoMotivoBaja());

			bd.actualizaSinCommit(QUERY_INSERT_INSS, hParam);

			/**
			 * insertamos en la tabla INSS_SNS_TIT
			 */
			try {
//				logger.info("metiendo en INSS_SNS_TIT idInss,codUsuarioSns,criterioIdentificacion -> [" + idInss + "," + codUsuarioSns + "," + criterioIdentificacion + "]");
				insertarInssSns(idInss, codUsuarioSns, criterioIdentificacion, bd);
			}catch (SincronizacionSQLException e) {
				
				if (e.getErrorCode()==1) {
					/**
					 * si el problema es que ya existe el codUsuarioSns
					 * metemos en INSS_SNS_TIT sin esos valores
					 */
					ListaCamposAfectadosInss listaCamposAfectadosInss=tratamientoDuplicado(transformarResultadoInssExtIfiFicheroBean(ifiFicheroBean,idInss),ifiFicheroBean,codUsuarioSns,criterioIdentificacion,bd);
					if (listaCamposAfectadosInss.isEmpty()) {
						/**
						 * si es vacio significa que no es favorable la aportacion
						 * y no vinculamos ni hacemos nada 
						 */
//						logger.info("metiendo en INSS_SNS_TIT duplicado idInss,codUsuarioSns,criterioIdentificacion -> [" + idInss + "," + codUsuarioSns + "," + criterioIdentificacion + "]");
						insertarInssSns(idInss, "", "", bd);
					}else{
						/**
						 * si no esta vacio significa que es favorable la aportacion
						 * y vinculamos porque el update lanzado habra fallado 
						 */
						insertarInssSns(idInss, codUsuarioSns, criterioIdentificacion, bd);
					}
				}else{
					throw e;
				}
			}

			/**
			 * insertamos en la tabla de INSS_AS_FICHEROS
			 */
			insertarInssAsFicheros(idInss, registroInss, bd);
		} catch (SincronizacionSQLException e) {
//			logger.error("Error en la actualizacion", e);
			throw e;
		}

	}

	private void desvincularCodSns(String codUsuarioSns,BigDecimal idInssProcedencia,AccesoBD bd) throws SQLException {
		String sqlBusqueda="select * from " + getNombreTablaInssSns() +" where COD_USUARIO_SNS=?";
		HashMap hParam=new HashMap();
		hParam.put("1", codUsuarioSns);

		Vector vDatos=bd.consulta(sqlBusqueda, hParam);
		if (vDatos.size()>0) {
			InssSnsBean inssSnsBean=new InssSnsBean((HashMap)vDatos.get(0));
			
			actualizarInssSns(inssSnsBean.getIdInss(), "", "", bd);
			
			ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.CRITERIO_IDENTIFICACION_SNS, inssSnsBean.getCriterioIdentificacionSns(), ""));
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.COD_USUARIO_SNS, inssSnsBean.getCodUsuarioSns(), ""));
			guardarHistorico(inssSnsBean.getIdInss(), listaCamposAfectadosInss,idInssProcedencia,Constantes.INSS_HISTORICO_PROCEDENCIA_OTRO_ID_INSS, bd);
		}
	}
	
	private Integer actualizarInssSns(BigDecimal idInss, String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SincronizacionSQLException {
		String QUERY_UPDATE="UPDATE " + getNombreTablaInssSns() + " set COD_USUARIO_SNS = ?, CRITERIO_IDENTIFICACION_SNS = ? where ID_INSS = ?";
		HashMap hParam = new HashMap();
		try {
			hParam.put(InssSnsBean.INDICE_UPDATE_ID_INSS, idInss);
			hParam.put(InssSnsBean.INDICE_UPDATE_COD_USUARIO_SNS, codUsuarioSns);
			hParam.put(InssSnsBean.INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS, criterioIdentificacion);

			Vector vDatos=bd.actualizaSinCommit(QUERY_UPDATE, hParam);
			return (Integer)vDatos.elementAt(0);
		}catch (SQLException e) {
			throw new SincronizacionSQLException(e,codUsuarioSns,"InssSnsBean_UPDATE");
		}
	}

	private ResultadoInssExtBean transformarResultadoInssExtIfiFicheroBean(IfiFicheroBean ifiFicheroBean,BigDecimal idInss) {
		ResultadoInssExtBean registroInss=new ResultadoInssExtBean();
		registroInss.setIdInss(idInss);
		registroInss.setApellido1(ifiFicheroBean.getApellido1());
		registroInss.setApellido2(ifiFicheroBean.getApellido2());
		registroInss.setApellidosNombre(ifiFicheroBean.getApellidosNombre());
		registroInss.setCodIndicadorDeFarmacia(ifiFicheroBean.getCodIndicadorDeFarmacia());
		registroInss.setCodUsuarioSns("");
		registroInss.setCodSubindicadorDeFarmacia(ifiFicheroBean.getCodSubindicadorDeFarmacia());
		registroInss.setDniNie(ifiFicheroBean.getDniNie());
		registroInss.setDomicilio(ifiFicheroBean.getDomicilio());
		try {
			registroInss.setFechaEfectoSituacion(new Timestamp(FORMAT_FECHA_INSS.parse(ifiFicheroBean.getFechaEfectoSituacion()).getTime()));
		}catch (Exception e) {
			registroInss.setFechaEfectoSituacion(null);
		}
		registroInss.setFechaNacimientoRaw(ifiFicheroBean.getFechaNac());
		registroInss.setIndicativoDomicilio(ifiFicheroBean.getIndicativoFormatoDomicilio());
		registroInss.setIndicativoNombre(ifiFicheroBean.getIndicativoFormatoNombre());
		registroInss.setIpf(ifiFicheroBean.getIpf());
		registroInss.setIpfTitular(ifiFicheroBean.getIpfTitular());
		registroInss.setCodTipoMotivoBaja(ifiFicheroBean.getCodTipoMotivoBaja());
		registroInss.setNacionalidad(ifiFicheroBean.getNacionalidad());
		registroInss.setNaf(ifiFicheroBean.getNaf());
		registroInss.setNafSec1(ifiFicheroBean.getNafSec1());
		registroInss.setNafSec2(ifiFicheroBean.getNafSec2());
		registroInss.setNafSec3(ifiFicheroBean.getNafSec3());
		registroInss.setNafSec4(ifiFicheroBean.getNafSec4());
		registroInss.setNafSec5(ifiFicheroBean.getNafSec5());
		registroInss.setNafSec6(ifiFicheroBean.getNafSec6());
		registroInss.setNafSec7(ifiFicheroBean.getNafSec7());
		registroInss.setNafSec8(ifiFicheroBean.getNafSec8());
		registroInss.setNafSec9(ifiFicheroBean.getNafSec9());
		registroInss.setNafTitular(ifiFicheroBean.getNafTitular());
		registroInss.setNombre(ifiFicheroBean.getNombre());
		registroInss.setNumeroSecuencia(new BigDecimal(Misc.nz(ifiFicheroBean.getNumeroSecuencia(),"0")));
		registroInss.setPasaporte(ifiFicheroBean.getPasaporte());
		registroInss.setSexo(new BigDecimal(Misc.nz(ifiFicheroBean.getCodSexo(),"0")));
		registroInss.setCodSituacion(ifiFicheroBean.getCodSituacion());
		registroInss.setCodTipoAsegurado(ifiFicheroBean.getCodTipoAsegurado());
		registroInss.setTipoAseguramiento(ifiFicheroBean.getTipoAseguramiento());
		registroInss.setCodTipoBeneficiario(new BigDecimal(Misc.nz(ifiFicheroBean.getTipoBeneficiario(),"0")));
		
		return registroInss;
	}

	private void insertarInssSns(BigDecimal idInss, String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SincronizacionSQLException {
		String QUERY_INSERT_INSS_SNS="INSERT INTO " + getNombreTablaInssSns() + " (ID_INSS,COD_USUARIO_SNS,CRITERIO_IDENTIFICACION_SNS) VALUES (?,?,?)";
		try {
			HashMap hParam = new HashMap();
			hParam.put(InssSnsBean.INDICE_INSERT_ID_INSS, idInss);
			hParam.put(InssSnsBean.INDICE_INSERT_COD_USUARIO_SNS, codUsuarioSns);
			hParam.put(InssSnsBean.INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS, criterioIdentificacion);

			bd.actualizaSinCommit(QUERY_INSERT_INSS_SNS, hParam);
		}catch (SQLException e) {
			throw new SincronizacionSQLException(e,codUsuarioSns,"InssSnsBean_INSERT");
//			if (e.getErrorCode()!=1) {
//				throw e;
//			}else{
//				desvincularCodSns(codUsuarioSns,idInss,bd);
//				/**
//				 * ARREGLO DEL BUG QUE NO INSERTABA EN EL INSS_SNS
//				 */
//				bd.actualizaSinCommit(InssSnsBean.QUERY_INSERT, hParam);
//			}
		}
	}

	/**
	 * Devuelve true si la situacion del registroActual es mas favorable que el registroExistente
	 * TSI 001 < TSI 002 01 < TSI 002 02 < TSI 005 03 < TSI 003 < TSI 004 < TSI 005
	 * @param registroLineaIfi
	 * @param registroExistente
	 * @return
	 */
	public boolean esFavorableSituacionActual(IInss registroLineaIfi,IInss registroExistente) {
		/**
		 * si el registro actual esta en baja y el que viene esta en alta se trata como favorable
		 */
		if ("B".equals(Misc.nz(registroExistente.getCodSituacion()))
			&& !"B".equals(Misc.nz(registroLineaIfi.getCodSituacion()))) {
			return true;
		}
		/**
		 * partimos de la premisa que el nuevo registro es el mas actual y correcto
		 * se examina que el que tenemos en la bd sea igual o menos favorable que el que viene
		 */
		/**
		 * TSI 001 < TSI 002 < TSI 003 < TSI 004 < TSI 005
		 */
		if (Constantes.TSI_001.equals(registroLineaIfi.getCodIndicadorDeFarmacia()) ) {
			
			if (
				Constantes.TSI_001.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_002.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_003.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
				) {
				return true;
			}
		}
		/**
		 * TSI 002 01 < TSI 002 02 < TSI 003 < TSI 004 < TSI 005
		 */
		if (Constantes.TSI_002.equals(registroLineaIfi.getCodIndicadorDeFarmacia())
			&& Constantes.TSI_002_SUB01.equals(registroLineaIfi.getCodSubindicadorDeFarmacia()) )  {
			
			if ( 
				(Constantes.TSI_002.equals(registroExistente.getCodIndicadorDeFarmacia()) 
							&& Constantes.TSI_002_SUB01.equals(registroExistente.getCodSubindicadorDeFarmacia()))
				|| (Constantes.TSI_002.equals(registroExistente.getCodIndicadorDeFarmacia()) 
					&& Constantes.TSI_002_SUB02.equals(registroExistente.getCodSubindicadorDeFarmacia()))
					|| Constantes.TSI_003.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
				) {
				return true;
			}
		}
		/**
		 * TSI 002 02 < TSI 003 < TSI 004 < TSI 005
		 */
		if (Constantes.TSI_002.equals(registroLineaIfi.getCodIndicadorDeFarmacia())
			&& Constantes.TSI_002_SUB02.equals(registroLineaIfi.getCodSubindicadorDeFarmacia()) )  {
			
			if ( (Constantes.TSI_002.equals(registroExistente.getCodIndicadorDeFarmacia()) 
					&& Constantes.TSI_002_SUB02.equals(registroExistente.getCodSubindicadorDeFarmacia()))
					|| Constantes.TSI_003.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
				) {
				return true;
			}
		}
		/**
		 * TSI 005 03 < TSI 003 < TSI 004 < TSI 005
		 */
		if (Constantes.TSI_005.equals(registroLineaIfi.getCodIndicadorDeFarmacia())
			&& Constantes.TSI_005_SUB03.equals(registroLineaIfi.getCodSubindicadorDeFarmacia()) )  {
			
			if (  (Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
					&& Constantes.TSI_005_SUB03.equals(registroExistente.getCodSubindicadorDeFarmacia()) )
					
					|| Constantes.TSI_003.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| ( Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
							&& Misc.esVacio(registroExistente.getCodSubindicadorDeFarmacia()))
				) {
				return true;
			}
		}
		/**
		 * TSI 003 < TSI 004 < TSI 005
		 */
		if (Constantes.TSI_003.equals(registroLineaIfi.getCodIndicadorDeFarmacia()) )  {
			
			if ( Constantes.TSI_003.equals(registroExistente.getCodIndicadorDeFarmacia()) 
					|| Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
					|| ( Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
							&& Misc.esVacio(registroExistente.getCodSubindicadorDeFarmacia()))
				) {
				return true;
			}
		}
		/**
		 * TSI 004 < TSI 005
		 */
		if (Constantes.TSI_004.equals(registroLineaIfi.getCodIndicadorDeFarmacia()) )  {
			
			if ( Constantes.TSI_004.equals(registroExistente.getCodIndicadorDeFarmacia())
				 || Constantes.TSI_005.equals(registroExistente.getCodIndicadorDeFarmacia())
							&& Misc.esVacio(registroExistente.getCodSubindicadorDeFarmacia())
				) {
				return true;
			}
		}
		return false;
		
	}
	
	public ListaCamposAfectadosInss tratamientoDuplicado(ResultadoInssExtBean registroActual,IInss registroLineaIfi,String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SQLException {
		ListaCamposAfectadosInss listaCamposAfectadosInss=new ListaCamposAfectadosInss();
		CriteriosBusqueda criteriosBusqueda=new CriteriosBusqueda();
		criteriosBusqueda.setCodUsuarioSns(codUsuarioSns);
		ArrayList candidatos=findBy(criteriosBusqueda, bd);
		if (candidatos.size()==1) {
			ResultadoInssExtBean registroExistente=(ResultadoInssExtBean)candidatos.get(0);
			if (esFavorableSituacionActual(registroLineaIfi,registroExistente)) {
				/**
				 * es favorable la situacion actual
				 * por lo que desvinculamos el codigoSNS del registroExistente
				 */
				listaCamposAfectadosInss=vincularCodSns(registroActual, codUsuarioSns, criterioIdentificacion, bd);
				
			}
		}
		return listaCamposAfectadosInss;
	}
	
	public ListaCamposAfectadosInss actualizarIpf(RegistroInss registroInss,ResultadoInssExtBean resultadoInssBean, AccesoBD bd) throws SQLException {
		
		/**
		 * buscamos el registro para sacar el idInss
		 */

		String queryActualizarIpf = "update " + getNombreTablaInss() + " set IPF=?,DNI_NIE=?,PASAPORTE=? where ID_INSS=?";
		HashMap hParam = new HashMap();
		hParam.put("1", registroInss.getIfiRegistro().getIpf());
		hParam.put("2", Misc.nz(registroInss.getIfiRegistro().getDniNie()));
		hParam.put("3", Misc.nz(registroInss.getIfiRegistro().getPasaporte()));
		hParam.put("4", resultadoInssBean.getIdInss());

		bd.actualizaSinCommit(queryActualizarIpf, hParam);

		ListaCamposAfectadosInss listaCamposAfectadosInss = new ListaCamposAfectadosInss();
		listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.IPF, registroInss.getIfiRegistro().getIpfAnterior(), registroInss.getIfiRegistro().getIpf()));

		/**
		 * al cambiar el IPF cambia tambien el DNI_NIE o PASAPORTE
		 */
		String valorAnterior=Misc.nz(resultadoInssBean.getDniNie());
		if (!valorAnterior.equals(Misc.nz(registroInss.getIfiRegistro().getDniNie()))) {
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.DNI_NIE, valorAnterior, Misc.nz(registroInss.getIfiRegistro().getDniNie())));			
		}
		valorAnterior=Misc.nz(resultadoInssBean.getPasaporte());
		if (!valorAnterior.equals(Misc.nz(registroInss.getIfiRegistro().getPasaporte()))) {
			listaCamposAfectadosInss.addCampo(new CamposAfectadosInss(CamposAfectadosInss.PASAPORTE, valorAnterior, Misc.nz(registroInss.getIfiRegistro().getPasaporte())));			
		}
		
		/**
		 * insertamos en la tabla de INSS_AS_FICHEROS
		 */
		insertarInssAsFicheros(resultadoInssBean.getIdInss(), registroInss, bd);
		return listaCamposAfectadosInss;
	}
	

}
