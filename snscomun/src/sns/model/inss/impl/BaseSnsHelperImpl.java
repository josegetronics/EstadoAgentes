package sns.model.inss.impl;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Logger;

import sns.config.Constantes;
import sns.dto.DatosCobertura;
import sns.dto.DatosDomicilio;
import sns.dto.DatosPersonales;
import sns.dto.GeneraCodSns;
import sns.dto.NafControl;
import sns.dto.RegistroOperaciones;
import sns.dto.Usuarios;
import sns.exception.DiferenteSnsException;
import sns.exception.MultipleNafException;
import sns.exception.MultiplesTitularesInssException;
import sns.exception.MultiplesUsuariosSnsEncontradosException;
import sns.exception.NafExistenteException;
import sns.exception.TitularInssSinNafException;
import sns.exception.TitularNoEncontradoException;
import sns.exception.TituloNoEncontradoException;
import sns.exception.TsiException;
import sns.exception.UsuarioNoEncontradoException;
import sns.exception.UsuarioSegSocialException;
import sns.model.CamposAfectados;
import sns.model.ITransaccionesHelper;
import sns.model.ListaCamposAfectadosVisiblesToComunidad;
import sns.model.impl.TransaccionesHelper;
import sns.model.inss.CriteriosBusqueda;
import sns.model.inss.CriteriosBusquedaExtNafSec;
import sns.model.inss.IInss;
import sns.model.inss.IInssHelper;
import sns.model.inss.ISnsHelper;
import sns.model.inss.InssCargaSnsExt;
import sns.model.inss.InssHelperFactory;
import sns.model.inss.ResultadoActualizacionEnSns;
import sns.model.inss.dto.DatosCoberturaJoinDatosFarmaciaEstadoBean;
import sns.model.inss.dto.DatosCoberturaSnsBean;
import sns.model.inss.dto.DatosSnsBean;
import sns.model.inss.dto.DatosSnsExtBean;
import sns.model.inss.dto.IDatosSns;
import sns.model.inss.dto.ResultadoCruceExtSnsBean;
import sns.model.inss.dto.ResultadoCruceSnsBean;
import sns.util.ComparaDatosSns;
import sns.util.GeneraCodigoSns;
import sns.util.Localizador;
import sns.util.OperacionesSns;
import sns.util.ValidaNaf;

public abstract class BaseSnsHelperImpl implements ISnsHelper {

	protected abstract Logger getLogger();
	protected abstract Logger getLoggerSnsError();

	protected abstract String getCaIsoFromCaIne(String codCaIne);

	protected abstract String getTituloPorTipoAseguramiento(String tipoAseguramiento); 
	
	private final static String SQL_QUERY_NAF_SNS = "select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE "
			+ "from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA "
			+ "where dc.naf=? "
			+ "and u.cod_usuario_sns=dc.cod_usuario_sns "
			+ "and dp.cod_usuario_sns=dc.cod_usuario_sns "
			+ "and df.cod_usuario_sns(+)=dc.cod_usuario_sns "
			+ "and I.COD_USUARIO_SNS=u.cod_usuario_sns "
			+ "AND DA.COD_PRESTACION_SERVICIO=U.COD_PRESTACION_SERVICIO " 
			+ "AND I.COD_AGENTE=DA.COD_AGENTE";

	private final static String SQL_QUERY_NAF_DATOS_COBERTURA_SNS = "select DC.COD_USUARIO_SNS,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO " + "from datos_cobertura dc "
			+ "where dc.naf=? ";

	private final static String SQL_QUERY_SNS = "select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE "
			+ "from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA "
			+ "where dc.COD_USUARIO_SNS=? "
			+ "and u.cod_usuario_sns=dc.cod_usuario_sns "
			+ "and dp.cod_usuario_sns=dc.cod_usuario_sns "
			+ "and df.cod_usuario_sns(+)=dc.cod_usuario_sns "
			+ "and I.COD_USUARIO_SNS=u.cod_usuario_sns "
			+ "AND DA.COD_PRESTACION_SERVICIO=U.COD_PRESTACION_SERVICIO " 
			+ "AND I.COD_AGENTE=DA.COD_AGENTE";

	private final static String SQL_QUERY_BENEFICIARIOS = "select dc.*,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,U.COD_ESTADO "
			+ "from DATOS_COBERTURA DC,DATOS_FARMACIA DF,USUARIOS U " 
			+ "WHERE DC.COD_USUARIO_SNS_TITULAR=? AND DC.INTERNO='N' " 
			+ "AND DC.COD_USUARIO_SNS!=DC.COD_USUARIO_SNS_TITULAR "
			+ "AND DF.COD_USUARIO_SNS(+)=DC.COD_USUARIO_SNS " 
			+ "AND U.COD_USUARIO_SNS=DC.COD_USUARIO_SNS ";

	private final static String SQL_QUERY_BENEFICIARIOS2 = "select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DP.COD_SEXO,DP.FECHA_NAC,DP.COD_COMUNIDAD,DP.COD_PAIS,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE "
		+ "from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA "
		+ "where DC.COD_USUARIO_SNS_TITULAR=? AND DC.INTERNO='N' "
		+ "AND DC.COD_USUARIO_SNS!=DC.COD_USUARIO_SNS_TITULAR "
		+ "and u.cod_usuario_sns=dc.cod_usuario_sns "
		+ "and dp.cod_usuario_sns=dc.cod_usuario_sns "
		+ "and df.cod_usuario_sns(+)=dc.cod_usuario_sns "
		+ "and I.COD_USUARIO_SNS=u.cod_usuario_sns "
		+ "AND DA.COD_PRESTACION_SERVICIO=U.COD_PRESTACION_SERVICIO " 
		+ "AND I.COD_AGENTE=DA.COD_AGENTE";
	
	private final static String SQL_UPDATE_DATOS_FARMACIA = "update DATOS_FARMACIA " + " set COD_INDICADOR_DE_FARMACIA = ?, " + " COD_SUBINDICADOR_DE_FARMACIA = ?, " + " COD_TIPO_PROCEDENCIA=? "
			+ " where COD_USUARIO_SNS=? ";
	private final static String SQL_INSERT_DATOS_FARMACIA = "INSERT INTO DATOS_FARMACIA VALUES (?,?,?,?)";

	private final static String SQL_UPDATE_DATOS_COBERTURA = "update DATOS_COBERTURA " + " set NAF = ?, " + " NAF_TITULAR = ?, " + " COD_USUARIO_SNS_TITULAR=?, " + " COD_TITULO=? "
			+ " where COD_USUARIO_SNS=? ";

	private final static String SQL_UPDATE_DATOS_COBERTURA_BEN = "update DATOS_COBERTURA " + " set NAF=?, NAF_TITULAR=?,COD_TITULO=?,COD_USUARIO_SNS_TITULAR=? " + " where COD_USUARIO_SNS=? ";

	private final static String SQL_UPDATE_USUARIOS = "update USUARIOS " + " set COD_ESTADO = ?, " + " FECHA_ULT_ACTUALIZACION = ? " + " where COD_USUARIO_SNS=? ";

	private final static String SQL_UPDATE_DATOS_PERSONALES = "update DATOS_PERSONALES " + " set DNI_NIE = ?, " + " PASAPORTE = ? " + " where COD_USUARIO_SNS=? ";

	private final String SQL_INSERT_HISTORICO = "insert into HISTORICO_MOD_USUARIOS (COD_MODIFICACION,COD_TIPO_MODIFICACION,COD_USUARIO_SNS,COD_OPERACION,FECHA_APLICACION_SOLICITUD,CAMPOS_AFECTADOS,VALOR_ANTERIOR,VALOR_SOLICITUD) values (HISTMODUSER.nextval,?,?,?,sysdate,?,?,?)";

	private ResultadoCruceSnsBean buscarPorNaf(String naf, AccesoBD bd) throws MultipleNafException, SQLException {
		getLogger().debug("buscando el naf: " + naf);
		if (Misc.esVacio(naf)) {
			return new ResultadoCruceSnsBean();
		}
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		Vector vDatos = bd.consulta(SQL_QUERY_NAF_SNS, hParam);
		if (vDatos.size() == 1) {
			return new ResultadoCruceSnsBean((HashMap) vDatos.get(0));
		} else if (vDatos.size() > 1) {
			/**
			 * errrrrrrrrrrrrror solo puede haber un naf
			 */
			throw new MultipleNafException();
		} else {
			/**
			 * no se ha encontrado
			 */
			getLogger().debug("no se ha encontrado el " + naf);
			return new ResultadoCruceSnsBean();
		}

	}

	/**
	 * 
	 * @param codUsuarioSns
	 * @param bd
	 * @return
	 * @throws Exception
	 */
	public ResultadoCruceExtSnsBean findByCodUsuarioSns(String codUsuarioSns,AccesoBD bd) throws SQLException {
		//
		//
		String query = "";
		HashMap parametros = new HashMap();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = new ResultadoCruceExtSnsBean();
		//
		try {
			// logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE  ");
			sbQuery.append(" , TO_CHAR(dp.COD_SEXO) COD_SEXO, TO_CHAR(dp.FECHA_NAC, 'YYYYMMDD') FECHA_NAC ");
			sbQuery.append(" from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA  ");
			sbQuery.append(" where u.cod_usuario_sns = ?  ");
			sbQuery.append(" AND u.cod_estado              IN (0, 1, 2, 7) ");
			sbQuery.append(" and dp.cod_usuario_sns          = u.cod_usuario_sns  ");
			sbQuery.append(" and dc.cod_usuario_sns         = dp.cod_usuario_sns  ");
			sbQuery.append(" and df.cod_usuario_sns     (+) = dc.cod_usuario_sns  ");
			sbQuery.append(" and I.COD_USUARIO_SNS          = u.cod_usuario_sns  ");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND I.COD_AGENTE               = DA.COD_AGENTE  ");
			query = sbQuery.toString();
			//
			parametros.put("1", Misc.nz(codUsuarioSns));
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			int contadorRegistros = 0;
			//
			if (resultSet.next()) {
				contadorRegistros++;
				//
				resultadoCruceExtSnsBean = new ResultadoCruceExtSnsBean(resultSet);
			}
			//
			// logger.debug("FIN");
		} catch (SQLException e) {
			getLogger().error("Exception:  " + e.getMessage());
			getLogger().error("query:      " + query);
			getLogger().error("parametros: " + parametros);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return resultadoCruceExtSnsBean;
	}
	
	private String buscarCodUsuarioSnsPorNaf(String naf, AccesoBD bd) throws MultipleNafException, SQLException {
		getLogger().debug("buscando el naf: " + naf);
		if (Misc.esVacio(naf)) {
			return "";
		}
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		Vector vDatos = bd.consulta("select COD_USUARIO_SNS FROM DATOS_COBERTURA WHERE NAF=?", hParam);
		if (vDatos.size() == 1) {
			return Misc.nz(((HashMap) vDatos.get(0)).get("COD_USUARIO_SNS"));
		} else if (vDatos.size() > 1) {
			/**
			 * errrrrrrrrrrrrror solo puede haber un naf
			 */
			throw new MultipleNafException();
		} else {
			/**
			 * no se ha encontrado
			 */
			getLogger().debug("no se ha encontrado el " + naf);
			return "";
		}

	}

	private DatosCoberturaSnsBean buscarDatosCoberturaNaf(String naf, AccesoBD bd) throws MultipleNafException, SQLException {
		getLogger().debug("buscando el naf: " + naf);
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		Vector vDatos = bd.consulta(SQL_QUERY_NAF_DATOS_COBERTURA_SNS, hParam);
		if (vDatos.size() == 1) {
			return new DatosCoberturaSnsBean((HashMap) vDatos.get(0));
		} else if (vDatos.size() > 1) {
			/**
			 * errrrrrrrrrrrrror solo puede haber un naf
			 */
			throw new MultipleNafException();
		} else {
			/**
			 * no se ha encontrado
			 */
			getLogger().debug("no se ha encontrado el " + naf);
			return new DatosCoberturaSnsBean();
		}

	}

	public DatosSnsBean buscarCodUsuarioSns(String codUsuarioSns, AccesoBD bd) throws Exception {
		getLogger().debug("buscando el codUsuarioSns: " + codUsuarioSns);
		HashMap hParam = new HashMap();
		hParam.put("1", codUsuarioSns);
		Vector vDatos = bd.consulta(SQL_QUERY_SNS, hParam);
		if (vDatos.size() == 1) {
			return new DatosSnsBean((HashMap) vDatos.get(0));
		} else if (vDatos.size() > 1) {
			/**
			 * errrrrrrrrrrrrror solo puede haber un naf
			 */
			throw new Exception();
		} else {
			/**
			 * no se ha encontrado
			 */
			getLogger().debug("no se ha encontrado el codUsuarioSns -> " + codUsuarioSns);
			return new DatosSnsBean();
		}

	}
	
	private ResultadoCruceSnsBean findByNafs(CriteriosBusquedaExtNafSec criteriosBusquedaExtNafSec,AccesoBD bd) throws MultipleNafException, SQLException {
		/**
		 * si el registro no tiene naf principal no buscamos en el SNS
		 */
		if (Misc.esVacio(criteriosBusquedaExtNafSec.getNaf())) {
			getLogger().debug("no se busca naf vacio [" + criteriosBusquedaExtNafSec.getNaf() + "]");
			return new ResultadoCruceSnsBean();
		}
		/**
		 * buscamos primero por naf principal
		 */
		ResultadoCruceSnsBean resultadoCruceSnsBean = buscarPorNaf(criteriosBusquedaExtNafSec.getNaf(), bd);
		if (resultadoCruceSnsBean.isVacio()) {
			/**
			 * buscamos por los naf secundarios que tenga
			 */
			boolean encontrado = false;
			for (int i = 1; i <= 9 && !encontrado; i++) {
				String nafSecundario = "";
				switch (i) {
				case 1:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec1();
					break;
				case 2:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec2();
					break;
				case 3:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec3();
					break;
				case 4:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec4();
					break;
				case 5:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec5();
					break;
				case 6:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec6();
					break;
				case 7:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec7();
					break;
				case 8:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec8();
					break;
				case 9:
					nafSecundario = criteriosBusquedaExtNafSec.getNafSec9();
					break;
				}
				if (!Misc.esVacio(nafSecundario)) {
					resultadoCruceSnsBean = buscarPorNaf(nafSecundario, bd);
					if (!resultadoCruceSnsBean.isVacio()) {
						/**
						 * si no es vacio hemos encontrado algo, paramos la
						 * busqueda para hacer delante el zoom de si se
						 * encuentra por DNI_NIE o PASAPORTE
						 */
						encontrado = true;
						resultadoCruceSnsBean.setCriterioIdentificacion("NAF_SEC" + i);
					}
				}
			}
		} else {
			resultadoCruceSnsBean.setCriterioIdentificacion("NAF");
		}
		return resultadoCruceSnsBean;
	}

//	public ResultadoCruceSnsBean findByRegistroInssEnSns(RegistroInss registroInss, AccesoBD bd) throws SQLException, TsiException {
//		return findByRegistroInssEnSns(registroInss.getIfiRegistro(), bd);
//	}
	
	public ResultadoCruceSnsBean findByRegistroInssEnSns(IInss inssBean, AccesoBD bd) throws SQLException, TsiException {
//		try {
			CriteriosBusquedaExtNafSec criteriosBusquedaExtNafSec=new CriteriosBusquedaExtNafSec();
			criteriosBusquedaExtNafSec.setNaf(inssBean.getNaf());
			criteriosBusquedaExtNafSec.setNafSec1(inssBean.getNafSec1());
			criteriosBusquedaExtNafSec.setNafSec2(inssBean.getNafSec2());
			criteriosBusquedaExtNafSec.setNafSec3(inssBean.getNafSec3());
			criteriosBusquedaExtNafSec.setNafSec4(inssBean.getNafSec4());
			criteriosBusquedaExtNafSec.setNafSec5(inssBean.getNafSec5());
			criteriosBusquedaExtNafSec.setNafSec6(inssBean.getNafSec6());
			criteriosBusquedaExtNafSec.setNafSec7(inssBean.getNafSec7());
			criteriosBusquedaExtNafSec.setNafSec8(inssBean.getNafSec8());
			criteriosBusquedaExtNafSec.setNafSec9(inssBean.getNafSec9());
			
			ResultadoCruceSnsBean resultadoCruceSnsBean = findByNafs(criteriosBusquedaExtNafSec,bd);


			if (!resultadoCruceSnsBean.isVacio()) {
				getLogger().debug("hay un registro del SNS parecido -> " + resultadoCruceSnsBean);
				/**
				 * hemos encontrado un registro miramos si tiene el mismo
				 * DNI_NIE o PASAPORTE
				 */
				String identificacionPorZoom = zoomIdentificacion(inssBean, Misc.nz(resultadoCruceSnsBean.getDniNie()), Misc.nz(resultadoCruceSnsBean.getPasaporte()));
				if (Misc.esVacio(identificacionPorZoom)) {
					/**
					 * el registro no tiene el mismo dni/nie o pasaporte se
					 * trata como no encontrado
					 */
					return new ResultadoCruceSnsBean();
				} else {
					/**
					 * hemos encontrado el registro por dni/nie o pasaporte
					 */
					resultadoCruceSnsBean.setCriterioIdentificacion(resultadoCruceSnsBean.getCriterioIdentificacion() + "|" + identificacionPorZoom);
					return resultadoCruceSnsBean;
				}

			}
			return new ResultadoCruceSnsBean();

//		} catch (Exception e) {
//			throw e;
//		}
	}

	private String zoomIdentificacion(IInss inssBean, String dniNieNuevo, String pasaporteNuevo) {
		String identificacionZoom = "";
		getLogger().debug("[dniNieNuevo,ifiFicheroBean.getDniNie()] [" + dniNieNuevo + "," + inssBean.getDniNie() + "]");
		getLogger().debug("[pasaporteNuevo,ifiFicheroBean.getPasaporte()] [" + pasaporteNuevo + "," + inssBean.getPasaporte() + "]");
		if (!Misc.esVacio(dniNieNuevo)) {
			if (Misc.nz(inssBean.getDniNie()).equals(dniNieNuevo)) {
				identificacionZoom = "DNI_NIE";
			}
		}

		if (!Misc.esVacio(pasaporteNuevo)) {
			if (Misc.nz(inssBean.getPasaporte()).equals(pasaporteNuevo)) {
				identificacionZoom = "PASAPORTE";
			}
		}
		return identificacionZoom;

	}

	/*
	 * Devuelve un arraylist con los codigosSns que hay que notificar un N010
	 * Ira el titular y los beneficiarios (non-Javadoc)
	 * 
	 * @see
	 * sns.model.inss.ISnsHelper#actualizar(sns.model.inss.dto.ResultadoCruceSnsBean
	 * , sns.model.gestion.RegistroInss, gasai.bd.AccesoBD)
	 */
	// public ResultadoActualizacionEnSns actualizar(ResultadoCruceSnsBean
	// resultadoCruceSnsBean, RegistroInss registroInss, AccesoBD bd) throws
	// Exception {
	// ResultadoActualizacionEnSns resultadoActualizacionEnSns=new
	// ResultadoActualizacionEnSns();
	// TransaccionesHelper tx=new TransaccionesHelper();
	// /**
	// * si el registro del sns es titular
	// */
	// if
	// (resultadoCruceSnsBean.getCodUsuarioSns().equals(resultadoCruceSnsBean.getCodUsuarioSnsTitular()))
	// {
	// /**
	// * bloqueamos el codigo sns del titular
	// */
	// tx.start(resultadoCruceSnsBean.getCodUsuarioSns(),
	// registroInss.getCodOperacion());
	// /**
	// * buscamos a los beneficiarios que tenga
	// */
	// ArrayList listBen =
	// findBeneficiarios(resultadoCruceSnsBean.getCodUsuarioSns(), bd);
	//
	// ArrayList listaListaCamposAfectados = new ArrayList();
	// for (int i = 0; i < listBen.size(); i++) {
	// DatosCoberturaJoinDatosFarmaciaBean datosFarmaciaBenBean =
	// (DatosCoberturaJoinDatosFarmaciaBean) listBen.get(i);
	//
	// /**
	// * bloqueamos los codigos sns de este beneficiarios
	// */
	// tx.start(datosFarmaciaBenBean.getCodUsuarioSns(),
	// registroInss.getCodOperacion());
	// /**
	// * les cambiamos la aportacion de la farmacia
	// */
	// ListaCamposAfectadosVisiblesToComunidad
	// listaCamposAfectados=actualizarBeneficiarioDatosFarmacia(registroInss,resultadoCruceSnsBean,
	// datosFarmaciaBenBean, bd);
	// listaListaCamposAfectados.add(listaCamposAfectados);
	// /**
	// * apuntamos en el historico los cambios
	// */
	// guardarHistorico(datosFarmaciaBenBean.getCodUsuarioSns(),
	// listaCamposAfectados,registroInss.getCodOperacion(), bd);
	// }
	//
	// /**
	// * cambiamos la aportacion de la farmacia al titular
	// */
	// ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitular =
	// actualizarTitularDatosFarmacia(Misc.nz(registroInss.getIfiRegistro().getCodIndicadorDeFarmacia()),Misc.nz(registroInss.getIfiRegistro().getCodSubindicadorDeFarmacia()),resultadoCruceSnsBean,
	// bd);
	//				
	// /**
	// * apuntamos en el historico los cambios
	// */
	// guardarHistorico(resultadoCruceSnsBean.getCodUsuarioSns(),
	// listaCamposAfectadosTitular,registroInss.getCodOperacion(), bd);
	//
	// /**
	// * desbloqueamos el codigo sns del titular
	// */
	// tx.release(resultadoCruceSnsBean.getCodUsuarioSns(),
	// registroInss.getCodOperacion());
	//
	// listaCamposAfectadosTitular.setCodUsuarioSns(resultadoCruceSnsBean.getCodUsuarioSns());
	// resultadoActualizacionEnSns.setListaCamposAfectadosTitular(listaCamposAfectadosTitular);
	//
	// /**
	// * desbloqueamos los codigos sns de esos beneficiarios
	// */
	// for (int i = 0; i < listBen.size(); i++) {
	// DatosCoberturaJoinDatosFarmaciaBean datosFarmaciaBenBean =
	// (DatosCoberturaJoinDatosFarmaciaBean) listBen.get(i);
	// tx.release(datosFarmaciaBenBean.getCodUsuarioSns(),
	// registroInss.getCodOperacion());
	//
	// ListaCamposAfectadosVisiblesToComunidad
	// listaCamposAfectados=(ListaCamposAfectadosVisiblesToComunidad)listaListaCamposAfectados.get(i);
	// if (listaCamposAfectados.isActualizacionVisibleComunidad()) {
	// listaCamposAfectados.setCodUsuarioSns(datosFarmaciaBenBean.getCodUsuarioSns());
	// resultadoActualizacionEnSns.addBeneficiarioToNotificar(listaCamposAfectados);
	// }
	//
	// }
	//				
	// }else{
	// /**
	// * @todo tratar la discrepancia que se encuentra
	// */
	// }
	// return resultadoActualizacionEnSns;
	// }

	/**
		 * 
		 */
	public Integer crearRegistroOperacion(String motivoMantenimiento, AccesoBD bdTx) throws Exception {
		try {

			String sqlUpdate = "Insert into REGISTRO_OPERACIONES "
					+ "   (COD_OPERACION, COD_OPERACION_MAESTRA, COD_ESTADO, COD_AGENTE_ORIGEN, COD_AGENTE_DESTINO, FECHA_OPERACION, COD_TIPO_OPERACION, MENSAJE_XML) " + " Values "
					+ "   (?, ?, 1, 99, 99, " + "    sysdate, 'M001', '<mensaje id=\"M001\" key=\"" + System.currentTimeMillis() + "\" fecha=\"" + Misc.fechaSistemaXml()
					+ "\"> <motivo_mantenimiento>" + motivoMantenimiento + "</motivo_mantenimiento></mensaje>')";

			/**
			 * obtenemos el id COD_OPERACION de la secuencia REGOPERACION
			 */
			BigDecimal codOperacion = bdTx.getNextCodigoSecuencia("REGOPERACION");
			getLogger().info("CodOperacion: " + codOperacion);

			HashMap hParam = new HashMap();
			hParam.put("1", codOperacion);
			hParam.put("2", codOperacion);

			/**
			 * insertamos en la tabla REGISTRO_OPERACIONES
			 */
			bdTx.actualizaSinCommit(sqlUpdate.toString(), hParam);
			return new Integer(codOperacion.intValue());

		} catch (Exception e) {
			throw e;
		} finally {
		}

	}

	public ResultadoActualizacionEnSns restaurarBeneficiario(IInss inssBean, Integer codOperacion, AccesoBD bd) throws MultiplesTitularesInssException,MultipleNafException,DiferenteSnsException,UsuarioNoEncontradoException,MultiplesUsuariosSnsEncontradosException, SQLException, TitularNoEncontradoException {
		ResultadoActualizacionEnSns resultadoActualizacionEnSns = new ResultadoActualizacionEnSns();
		TransaccionesHelper tx = new TransaccionesHelper();
		IInssHelper inssHelper=InssHelperFactory.getInstance();

		String codTituloBeneficiario=getTituloPorTipoAseguramiento(inssBean.getTipoAseguramiento());
		
		/**
		 * buscamos por NAF y DNI_NIE o PASAPORTE
		 */
		ResultadoCruceSnsBean resultadoCruceSnsBean= null;
		
		try {
			resultadoCruceSnsBean=findByRegistroInssEnSns(inssBean, bd);
		} catch (TsiException e) {
			/**
			 * puede que salte la exception TsiException que realmente siempre va a ser
			 * una MultipleNafException, se propaga para que se meta en cuarentena
			 */
			throw new MultipleNafException();
		}
		
		
		if (resultadoCruceSnsBean.isVacio()) {
			/**
			 * buscamos por aproximacion
			 */
			resultadoCruceSnsBean=buscarPorAproximacion(inssBean, bd);
		}
		
		/**
		 * hemos encontrado un registro, si no se encuentra o hay multiples saltara UsuarioNoEncontradoException,MultiplesUsuariosSnsEncontradosException
		 */

		/**
		 * miramos si el usuario que hemos encontrado tiene el mismo cod_usuario_sns del que no han mandado el INSS
		 * si no es saltamos la exception para meterlo en cuarentena
		 */
		if (!Misc.esVacio(inssBean.getCodUsuarioSns()) 
				&& !inssBean.getCodUsuarioSns().equals(resultadoCruceSnsBean.getCodUsuarioSns())) {
			throw new DiferenteSnsException(inssBean.getCodUsuarioSns(),resultadoCruceSnsBean);
		}
		
		/**
		 * buscamos al titular por el naf_titular en el SNS
		 */
		ResultadoCruceSnsBean resultadoTitCruceSnsBean=buscarPorNaf(inssBean.getNafTitular(), bd);
		
		/**
		 * si se encuentra mas de un titular con ese naf saltara la exception MultipleNafException
		 */
		
		/**
		 * si no hemos encontrado ningun titular con ese naf en el SNS
		 * lo buscamos en el INSS_TIT para intentar encontrarlo por los NAFS secundarios
		 */
		if (resultadoTitCruceSnsBean.isVacio()) {
			CriteriosBusqueda criteriosBusqueda=new CriteriosBusqueda();
			criteriosBusqueda.setNumeroMaximoResultados(2);
			criteriosBusqueda.setCodSituacion("A");
			criteriosBusqueda.setNaf(inssBean.getNafTitular());
			ArrayList coincidentes=inssHelper.findBy(criteriosBusqueda, bd);
			if (coincidentes.size()==0) {
				throw new TitularNoEncontradoException(inssBean.getNafTitular());
			}else if (coincidentes.size()>1) {
				throw new MultiplesTitularesInssException();
			}else{
				/**
				 * hemos encontrado un unico registro en el INSS_TIT
				 */
				
				ResultadoInssExtBean resultadoInssExtBean=(ResultadoInssExtBean)coincidentes.get(0);
				
				CriteriosBusquedaExtNafSec criteriosBusquedaExtNafSec=new CriteriosBusquedaExtNafSec();
				criteriosBusquedaExtNafSec.setNaf(resultadoInssExtBean.getNaf());
				criteriosBusquedaExtNafSec.setNafSec1(resultadoInssExtBean.getNafSec1());
				criteriosBusquedaExtNafSec.setNafSec2(resultadoInssExtBean.getNafSec2());
				criteriosBusquedaExtNafSec.setNafSec3(resultadoInssExtBean.getNafSec3());
				criteriosBusquedaExtNafSec.setNafSec4(resultadoInssExtBean.getNafSec4());
				criteriosBusquedaExtNafSec.setNafSec5(resultadoInssExtBean.getNafSec5());
				criteriosBusquedaExtNafSec.setNafSec6(resultadoInssExtBean.getNafSec6());
				criteriosBusquedaExtNafSec.setNafSec7(resultadoInssExtBean.getNafSec7());
				criteriosBusquedaExtNafSec.setNafSec8(resultadoInssExtBean.getNafSec8());
				criteriosBusquedaExtNafSec.setNafSec9(resultadoInssExtBean.getNafSec9());
				
				resultadoTitCruceSnsBean=findByNafs(criteriosBusquedaExtNafSec,bd);// throws MultipleNafException, SQLException {
				
				/**
				 * se ha encontrado un titular en el SNS o ninguno
				 */

				if (resultadoTitCruceSnsBean.isVacio()) {
					/**
					 * si no se ha encontrado ninguno se crea un alta de titular ficticio en el SNS
					 */
					String codUsuarioSnsTitular=altaTitularFicticio(inssBean.getNafTitular(),codOperacion,bd);
					resultadoTitCruceSnsBean.setCodUsuarioSns(codUsuarioSnsTitular);
					/**
					 * cargamos los datos del tipo aseguramiento (traducido a titulo)
					 * y la aportacion de lo que viene del beneficiario
					 */
					resultadoTitCruceSnsBean.setNaf(inssBean.getNafTitular());
					resultadoTitCruceSnsBean.setNafTitular(inssBean.getNafTitular());
					resultadoTitCruceSnsBean.setCodTitulo(new BigDecimal(codTituloBeneficiario));
					resultadoTitCruceSnsBean.setCodIndicadorDeFarmacia(inssBean.getCodIndicadorDeFarmacia());
					resultadoTitCruceSnsBean.setCodSubindicadorDeFarmacia(inssBean.getCodSubindicadorDeFarmacia());
					resultadoTitCruceSnsBean.setCodTipoProcedencia(Constantes.TIPO_PROCEDENCIA_FARMACIA_MINISTERIO_SANIDAD);
				}

			}
		}
		
		/**
		 * miramos si el TSI y tipo de asguramiento del Beneficiario para ver si coincide
		 * si no coincide saltamos una alerta  
		 */
		if (!Misc.esIgual(inssBean.getCodIndicadorDeFarmacia(), resultadoTitCruceSnsBean.getCodIndicadorDeFarmacia())
			|| !Misc.esIgual(inssBean.getCodSubindicadorDeFarmacia(), resultadoTitCruceSnsBean.getCodSubindicadorDeFarmacia())
			|| !Misc.esIgual(codTituloBeneficiario, Misc.nz(resultadoTitCruceSnsBean.getCodTitulo()))) {
			/**
			 * @TODO meter en una tabla de alertas
			 */
		}
		
		/**
		 * calculamos la procedencia nueva
		 */
		BigDecimal tipoProcedenciaBen=Constantes.TIPO_PROCEDENCIA_FARMACIA_MINISTERIO_SANIDAD;
		if (resultadoTitCruceSnsBean.getCodTipoProcedencia().intValue()==Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS.intValue()) {
			tipoProcedenciaBen=Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS_BENEFICIARIO;
		}
		
		/**
		 * actualizamos la nueva condicion en el SNS
		 */
		/**
		 * cargamos el bean para utilizar los metodos
		 */
//		DatosCoberturaJoinDatosFarmaciaEstadoBean benNuevaCondicion=new DatosCoberturaJoinDatosFarmaciaEstadoBean();
		DatosSnsBean benNuevaCondicion=new DatosSnsBean();
		//cargando datos_farmacia
		benNuevaCondicion.setCodUsuarioSns(resultadoCruceSnsBean.getCodUsuarioSns());
		benNuevaCondicion.setCodIndicadorDeFarmacia(resultadoCruceSnsBean.getCodIndicadorDeFarmacia());
		benNuevaCondicion.setCodSubindicadorDeFarmacia(resultadoCruceSnsBean.getCodSubindicadorDeFarmacia());
		benNuevaCondicion.setCodTipoProcedencia(resultadoCruceSnsBean.getCodTipoProcedencia());
		//cargando datos_cobertura
		benNuevaCondicion.setCodTitulo(resultadoCruceSnsBean.getCodTitulo());
		benNuevaCondicion.setNaf(resultadoCruceSnsBean.getNaf());
		benNuevaCondicion.setCodUsuarioSnsTitular(resultadoCruceSnsBean.getCodUsuarioSnsTitular());
		benNuevaCondicion.setNafTitular(resultadoCruceSnsBean.getNafTitular());
		/**
		 * bloqueamos el codigo SNS para cambiarle la condicion
		 */
		tx.start(benNuevaCondicion.getCodUsuarioSns(), codOperacion);

		/**
		 * cambiamos la aportacion de la farmacia
		 */
		getLogger().debug("Antes de cambiar DF -> " + benNuevaCondicion.getCodUsuarioSns());
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosAntiguoTitular = actualizarBeneficiarioDatosFarmacia(resultadoTitCruceSnsBean.getCodIndicadorDeFarmacia(),
				resultadoTitCruceSnsBean.getCodSubindicadorDeFarmacia(), 
				benNuevaCondicion,tipoProcedenciaBen, bd);
		getLogger().debug("DC cambios -> " + listaCamposAfectadosAntiguoTitular.isActualizacionVisibleComunidad());

		/**
		 * cambiamos los datos de cobertura
		 */
		getLogger().debug("Antes de cambiar DC -> " + benNuevaCondicion.getCodUsuarioSns());
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosAntiguoTitularAux = actualizarBeneficiarioDatosCobertura(resultadoTitCruceSnsBean.getCodUsuarioSns(),
																												  resultadoTitCruceSnsBean.getNaf(),
																												  resultadoTitCruceSnsBean.getCodTitulo().toString(), benNuevaCondicion,inssBean.getNaf(),true, bd);
		
		getLogger().debug("DC cambios -> " + listaCamposAfectadosAntiguoTitularAux.isActualizacionVisibleComunidad());
		listaCamposAfectadosAntiguoTitular.concat(listaCamposAfectadosAntiguoTitularAux);
		/**
		 * apuntamos en el historico los cambios
		 */
		guardarHistorico(benNuevaCondicion.getCodUsuarioSns(), listaCamposAfectadosAntiguoTitular,Constantes.TIPO_CAMBIO_MODIFICACION, codOperacion, bd);

		/**
		 * buscamos los beneficiarios de ese codUsuarioSns
		 */
		ArrayList listBen = findBeneficiarios(resultadoCruceSnsBean.getCodUsuarioSns(), bd);
		for (int i = 0; i < listBen.size(); i++) {
			DatosSnsExtBean ben = (DatosSnsExtBean) listBen.get(i);
			/**
			 * bloqueamos los codigos sns de este beneficiario
			 */
			tx.start(ben.getCodUsuarioSns(), codOperacion);
			/**
			 * cambiamos la aportacion de la farmacia
			 */
			ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosVisiblesToComunidad = actualizarBeneficiarioDatosFarmacia(resultadoTitCruceSnsBean.getCodIndicadorDeFarmacia(),
					resultadoTitCruceSnsBean.getCodSubindicadorDeFarmacia(), 
					ben,tipoProcedenciaBen, bd);

			ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitulo = actualizarBeneficiarioDatosCobertura(resultadoTitCruceSnsBean.getCodUsuarioSns(),
																													  resultadoTitCruceSnsBean.getNaf(),
																													  resultadoTitCruceSnsBean.getCodTitulo().toString(), ben,"",false, bd);
			listaCamposAfectadosVisiblesToComunidad.concat(listaCamposAfectadosTitulo);
			/**
			 * apuntamos en el historico los cambios
			 */
			guardarHistorico(ben.getCodUsuarioSns(), listaCamposAfectadosVisiblesToComunidad,Constantes.TIPO_CAMBIO_MODIFICACION_DE_TITULAR, codOperacion, bd);

			tx.release(ben.getCodUsuarioSns(), codOperacion);

			/**
			 * si el beneficiario tiene un estado visible se mete en el
			 * array para mandar la notificacion
			 */
			if (ben.getCodEstado().intValue() == Constantes.ESTADO_NORMAL || ben.getCodEstado().intValue() == Constantes.ESTADO_BAJA_SERVICIO_SALUD
					|| ben.getCodEstado().intValue() == Constantes.ESTADO_BAJA_POR_DEFUNCION) {
				/**
				 * guardamos la lista de campos afectados para ver si hay
				 * que mandar la notificacion
				 */
				if (listaCamposAfectadosVisiblesToComunidad.isActualizacionVisibleComunidad()) {
					listaCamposAfectadosVisiblesToComunidad.setCodUsuarioSns(ben.getCodUsuarioSns());
					listaCamposAfectadosVisiblesToComunidad.setLocalizador(getLocalizador(ben));
					resultadoActualizacionEnSns.addBeneficiarioToNotificar(listaCamposAfectadosVisiblesToComunidad);
				}
			}
		}

		/**
		 * desbloqueamos el codigo SNS que se le ha cambiado la condicion
		 */
		tx.release(benNuevaCondicion.getCodUsuarioSns(), codOperacion);


		listaCamposAfectadosAntiguoTitular.setCodUsuarioSns(resultadoCruceSnsBean.getCodUsuarioSns());
		resultadoActualizacionEnSns.setListaCamposAfectadosTitular(listaCamposAfectadosAntiguoTitular);
		resultadoActualizacionEnSns.setDatosSnsBean(resultadoCruceSnsBean);

		return resultadoActualizacionEnSns;
	}
	
	private String altaTitularFicticio(String nafTitular,Integer codOperacionMaestra,AccesoBD bd) throws SQLException, MultipleNafException {

		BigDecimal numCodSns=bd.getNextCodigoSecuencia(GeneraCodSns.NOMBRE_SECUENCIA_ASOCIADA);
		GeneraCodigoSns generaCodigoSns=new GeneraCodigoSns(numCodSns.toString());
		String codUsuarioSnsTitular=generaCodigoSns.genera();

		/**
		 * nafControl
		 */
		NafControl nafControl = new NafControl();
		nafControl.setCodUsuarioSns(codUsuarioSnsTitular);
		nafControl.setNaf(nafTitular);
		try {
			bd.actualizaSinCommit(NafControl.QUERY_INSERT, nafControl.getParametrosInsert());
		}catch (SQLException e) {
			if (e.getErrorCode()==1) {
				/**
				 * si se da una torta solo puede ser que haya un ficticio ya creado
				 * lo buscamos y devolvemos el codUsuarioSns
				 */
				return buscarCodUsuarioSnsPorNaf(nafTitular,bd);
			}else{
				throw e;
			}
		}
		
		/**
		 * usuarios
		 */
		Usuarios usuarios=new Usuarios();
		usuarios.setCodUsuarioSns(codUsuarioSnsTitular);
		usuarios.setCodPrestacionServicio(new BigDecimal("99"));
		usuarios.setCodEstado(new BigDecimal(0));
		bd.actualizaSinCommit(Usuarios.QUERY_INSERT, usuarios.getParametrosInsert());

		/**
		 * datosPersonales
		 */
		DatosPersonales datosPersonales=new DatosPersonales();
		datosPersonales.setCodUsuarioSns(codUsuarioSnsTitular);
		bd.actualizaSinCommit(DatosPersonales.QUERY_INSERT, datosPersonales.getParametrosInsert());
		
		/**
		 * datosPersonales
		 */
		DatosCobertura datosCobertura = new DatosCobertura();
		datosCobertura.setCodUsuarioSns(codUsuarioSnsTitular);
		datosCobertura.setCodUsuarioSnsTitular(codUsuarioSnsTitular);
		datosCobertura.setInterno(Constantes.USUARIO_INTERNO);
		datosCobertura.setNaf(nafTitular);
		datosCobertura.setNafTitular(nafTitular);
		bd.actualizaSinCommit(DatosCobertura.QUERY_INSERT, datosCobertura.getParametrosInsert());

		/**
		 * datosDomicilio
		 */
		DatosDomicilio datosDomicilio = new DatosDomicilio();
		datosDomicilio.setCodUsuarioSns(codUsuarioSnsTitular);
		bd.actualizaSinCommit(DatosDomicilio.QUERY_INSERT, datosDomicilio.getParametrosInsert());

		if (codOperacionMaestra != null) {
			BigDecimal codOperacion=bd.getNextCodigoSecuencia(RegistroOperaciones.NOMBRE_SECUENCIA_ASOCIADA);
			
			RegistroOperaciones registroOperaciones = new RegistroOperaciones();
			registroOperaciones.setCodAgenteDestino(new BigDecimal(99));
			registroOperaciones.setCodAgenteOrigen(new BigDecimal(99));
			registroOperaciones.setCodEstado(new BigDecimal(1));
			registroOperaciones.setCodOperacion(codOperacion);
			registroOperaciones.setCodOperacionMaestra(new BigDecimal(codOperacionMaestra.intValue()));
			registroOperaciones.setCodTipoOperacion(Constantes.A001);
			registroOperaciones.setFechaOperacion(new Timestamp(System.currentTimeMillis()));
			registroOperaciones.setMensajeXml("<sns>" + codUsuarioSnsTitular + "</sns><naf>" + nafTitular + "</naf>");
			
			bd.actualizaSinCommit(RegistroOperaciones.QUERY_INSERT, registroOperaciones.getParametrosInsert());
		}
		
		return codUsuarioSnsTitular; 

	}
	
	public abstract ITransaccionesHelper getTransaccionesHelper();
	
	public ResultadoActualizacionEnSns actualizarDiferido(ResultadoInssExtBean resultadoInssExtBean, Integer codOperacion, AccesoBD bd) throws Exception {
		DatosSnsBean datosSnsBean = this.buscarCodUsuarioSns(resultadoInssExtBean.getCodUsuarioSns(), bd);
		if (datosSnsBean.isVacio()) {
			getLogger().debug("datosSnsBean vacio!!! -> " + resultadoInssExtBean.getCodUsuarioSns());
			throw new UsuarioNoEncontradoException(resultadoInssExtBean.getCodUsuarioSns());
		}
		return actualizarDiferido(resultadoInssExtBean,datosSnsBean,codOperacion,bd);
		
	}

	public ResultadoActualizacionEnSns actualizarDiferido(InssCargaSnsExt inssCargaSnsExt, Integer codOperacion, AccesoBD bd) throws Exception {
		return actualizarDiferido(inssCargaSnsExt.getResultadoInssExtBean(),inssCargaSnsExt.getDatosSns(),codOperacion,bd);
	}

	private ResultadoActualizacionEnSns actualizarDiferido(ResultadoInssExtBean resultadoInssExtBean,DatosSnsBean datosSnsBean,Integer codOperacion, AccesoBD bd) throws Exception {
		ResultadoActualizacionEnSns resultadoActualizacionEnSns = new ResultadoActualizacionEnSns();
		ITransaccionesHelper tx = getTransaccionesHelper();

		resultadoActualizacionEnSns.setDatosSnsBean(datosSnsBean);
		/**
		 * bloqueamos el codigo sns del titular
		 */
		tx.start(datosSnsBean.getCodUsuarioSns(), codOperacion);

		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitular = new ListaCamposAfectadosVisiblesToComunidad();
		/**
		 * solo cambiamos los datos del usuario referente a coberturas, datos personales y datos de farmacia si
		 * la situacion del INSS es de alta
		 * 
		 * Si es de baja se actualiza solo el estado del usuario
		 */
		if (!"B".equals(resultadoInssExtBean.getCodSituacion())) {

			/**
			 * cambiamos los datos de cobertura puede que de una excepcion, por eso
			 * se ejecuta primero para que no haga cosas raras la transaccion
			 */
			getLogger().debug("Antes de cambiar DC -> " + resultadoInssExtBean.getCodUsuarioSns());
			listaCamposAfectadosTitular = actualizarDatosCobertura(resultadoInssExtBean, datosSnsBean, bd);
			getLogger().debug("DC cambios -> " + listaCamposAfectadosTitular.isActualizacionVisibleComunidad());
	
			/**
			 * cambiamos la aportacion de la farmacia
			 */
			ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitularAux = actualizarDatosFarmacia(resultadoInssExtBean.getCodIndicadorDeFarmacia(), resultadoInssExtBean
					.getCodSubindicadorDeFarmacia(), datosSnsBean, bd);
			listaCamposAfectadosTitular.concat(listaCamposAfectadosTitularAux);
			getLogger().debug("DF cambios -> " + listaCamposAfectadosTitular.isActualizacionVisibleComunidad());
	
			/**
			 * cambiamos los datos de personales
			 */
			listaCamposAfectadosTitularAux = actualizarDatosPersonales(resultadoInssExtBean, datosSnsBean, bd);
			listaCamposAfectadosTitular.concat(listaCamposAfectadosTitularAux);
			getLogger().debug("DP cambios -> " + listaCamposAfectadosTitular.isActualizacionVisibleComunidad());
	
		}else {
			/**
			 * tenemos que ver si el usuario tiene temporalidades y si tiene darlas de baja
			 */
			//TODO
		}

		/**
		 * cambiamos en los datos de usuario
		 * 
		 */
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitularAux = actualizarUsuarios(resultadoInssExtBean, datosSnsBean, bd);
		listaCamposAfectadosTitular.concat(listaCamposAfectadosTitularAux);
		getLogger().debug("U cambios -> " + listaCamposAfectadosTitular.isActualizacionVisibleComunidad());

		/**
		 * apuntamos en el historico los cambios
		 */
		guardarHistorico(datosSnsBean.getCodUsuarioSns(), listaCamposAfectadosTitular,Constantes.TIPO_CAMBIO_MODIFICACION, codOperacion, bd);

		/**
		 * Si la situacion del titular no es de Baja modificamos los
		 * beneficiarios
		 */
		if (!"B".equals(resultadoInssExtBean.getCodSituacion())) {
			/**
			 * buscamos los beneficiarios
			 */
			ArrayList listBen = findBeneficiarios(datosSnsBean.getCodUsuarioSns(), bd);
			String codTituloTraducido = getTituloPorTipoAseguramiento(resultadoInssExtBean.getTipoAseguramiento());
			if (Misc.esVacio(codTituloTraducido)) {
				/**
				 * si la traduccion del tipo de aseguramiento a titulo no existe se
				 * lanza exception para abortar proceso
				 */
				throw new TituloNoEncontradoException(resultadoInssExtBean.getTipoAseguramiento());
			}

			for (int i = 0; i < listBen.size(); i++) {
				DatosSnsExtBean ben = (DatosSnsExtBean) listBen.get(i);
				/**
				 * bloqueamos los codigos sns de este beneficiarios
				 */
				tx.start(ben.getCodUsuarioSns(), codOperacion);
				/**
				 * cambiamos la aportacion de la farmacia
				 */
				ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosVisiblesToComunidad = actualizarBeneficiarioDatosFarmacia(resultadoInssExtBean.getCodIndicadorDeFarmacia(),resultadoInssExtBean.getCodSubindicadorDeFarmacia(), ben,Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS_BENEFICIARIO, bd);

				ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitulo = actualizarBeneficiarioDatosCobertura(datosSnsBean.getCodUsuarioSns(),
																															resultadoInssExtBean.getNaf(),
																															codTituloTraducido,
																															ben,"",false, bd);
				listaCamposAfectadosVisiblesToComunidad.concat(listaCamposAfectadosTitulo);
				/**
				 * apuntamos en el historico los cambios
				 */
				guardarHistorico(ben.getCodUsuarioSns(), listaCamposAfectadosVisiblesToComunidad,Constantes.TIPO_CAMBIO_MODIFICACION, codOperacion, bd);

				tx.release(ben.getCodUsuarioSns(), codOperacion);

				/**
				 * si el beneficiario tiene un estado visible se mete en el
				 * array para mandar la notificacion
				 */
				if (ben.getCodEstado().intValue() == Constantes.ESTADO_NORMAL || ben.getCodEstado().intValue() == Constantes.ESTADO_BAJA_SERVICIO_SALUD
						|| ben.getCodEstado().intValue() == Constantes.ESTADO_BAJA_POR_DEFUNCION) {
					/**
					 * guardamos la lista de campos afectados para ver si hay
					 * que mandar la notificacion
					 */
					if (listaCamposAfectadosVisiblesToComunidad.isActualizacionVisibleComunidad()) {
						listaCamposAfectadosVisiblesToComunidad.setCodUsuarioSns(ben.getCodUsuarioSns());
						listaCamposAfectadosVisiblesToComunidad.setLocalizador(getLocalizador(ben));
						resultadoActualizacionEnSns.addBeneficiarioToNotificar(listaCamposAfectadosVisiblesToComunidad);
					}
				}
			}

		}

		/**
		 * desbloqueamos el codigo sns del titular
		 */
		tx.release(datosSnsBean.getCodUsuarioSns(), codOperacion);

		listaCamposAfectadosTitular.setCodUsuarioSns(datosSnsBean.getCodUsuarioSns());
		resultadoActualizacionEnSns.setListaCamposAfectadosTitular(listaCamposAfectadosTitular);

		return resultadoActualizacionEnSns;
	}

	protected ListaCamposAfectadosVisiblesToComunidad actualizarDatosCobertura(ResultadoInssExtBean resultadoInssExtBean, IDatosSns datosSnsBean, AccesoBD bd) throws Exception {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		boolean actualizar = false;
		String codUsuarioSnsTitular = datosSnsBean.getCodUsuarioSnsTitular();

		if ("T".equals(resultadoInssExtBean.getCodTipoAsegurado())) {
			/**
			 * no podemos meter en el SNS un usuario titular con naf vacio si
			 * viene es que tiene que ser del colectivo titulares > 26 anios
			 */

			if (Misc.esVacio(resultadoInssExtBean.getNaf())) {

				if ("0501".equals(resultadoInssExtBean.getTipoAseguramiento())) {
					/**
					 * generamos naf ficticio comenzando por 88
					 */
					ValidaNaf validaNaf = new ValidaNaf();
					OperacionesSns sns = new OperacionesSns(datosSnsBean.getCodUsuarioSns());
					String numero = Misc.rellenarIzq(sns.convierte(), "0", 8);
					String digitosControlNaf = validaNaf.digitosControl("88" + numero);
					resultadoInssExtBean.setNaf("88" + numero + digitosControlNaf);
				} else {
					throw new TitularInssSinNafException(resultadoInssExtBean.getTipoAseguramiento());
				}

			}
			/**
			 * si es titular ponemos el naf_titular como el naf porque el INSS
			 * no nos indica el naf_titular de los titulares
			 */
			resultadoInssExtBean.setNafTitular(resultadoInssExtBean.getNaf());
			codUsuarioSnsTitular = datosSnsBean.getCodUsuarioSns();
		}

		String sValorOld = Misc.nz(datosSnsBean.getNaf());
		if (!sValorOld.equals(Misc.nz(resultadoInssExtBean.getNaf()))) {

			if (!Misc.esVacio(resultadoInssExtBean.getNaf())) {
				/**
				 * hay un cambio de NAF, hay que buscar no vaya a tenerlo ya
				 * otro registro en el SNS
				 */
				DatosCoberturaSnsBean datosCoberturaSnsBean = buscarDatosCoberturaNaf(resultadoInssExtBean.getNaf(), bd);
				getLogger().debug("SNS ENCONTRADO PARA EL NAF -> " + datosCoberturaSnsBean.getCodUsuarioSns() + " " + datosCoberturaSnsBean.getNaf() + " - " + datosCoberturaSnsBean.getInterno());

				/**
				 * puede que sea vacio porque es un interno, se duplicara el
				 * titular.
				 */
				if (!datosCoberturaSnsBean.isVacio()) {

					if (Constantes.USUARIO_REAL.equals(datosCoberturaSnsBean.getInterno())) {
						/**
						 * si no es ficticio lanzamos la exception
						 */
						throw new NafExistenteException(resultadoInssExtBean.getNaf());
					} else {
						/**
						 * coincide con un interno hay que eliminarlo y vincular
						 * a este codigo sns a los beneficiarios
						 */
						fusion(datosSnsBean.getCodUsuarioSns(), datosCoberturaSnsBean.getCodUsuarioSns(), bd);
					}
				}
			}

			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.NAF, sValorOld, Misc.nz(resultadoInssExtBean.getNaf())));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		} else {
			getLogger().debug("No cambia el NAF");
		}

		sValorOld = Misc.nz(datosSnsBean.getNafTitular());
		if (!sValorOld.equals(Misc.nz(resultadoInssExtBean.getNafTitular()))) {
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.NAF_TITULAR, sValorOld, Misc.nz(resultadoInssExtBean.getNafTitular())));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;

			if ("T".equals(resultadoInssExtBean.getCodTipoAsegurado())) {
				/**
				 * si es titular igualo el cod_usuario_sns_titular al
				 * cod_usuario_sns
				 * 
				 */
				codUsuarioSnsTitular = datosSnsBean.getCodUsuarioSns();

			} else {
				/**
				 * si es beneficiario busco el cod_usuario_sns_titular en las
				 * tablas del INSS por NAF_TITULAR
				 */
				IInssHelper inssHelper = InssHelperFactory.getInstance();

				/**
				 * puede saltar una exception por no encontrado o por encontrado
				 * multiple se eleva para que falle la transaccion y se haga
				 * rollback
				 */
				ResultadoInssExtBean resultadoInssExtTitularBean = null;
				try {
					resultadoInssExtTitularBean = inssHelper.findByNafPrincipal(Misc.nz(resultadoInssExtBean.getNafTitular()), bd);
					codUsuarioSnsTitular = resultadoInssExtTitularBean.getCodUsuarioSns();
				} catch (UsuarioSegSocialException e) {
					/**
					 * no lo he encontrado por el principal, lo busco por los
					 * secundarios
					 */
					resultadoInssExtTitularBean = inssHelper.findNafSecundarios(Misc.nz(resultadoInssExtBean.getNafTitular()), bd);
					codUsuarioSnsTitular = resultadoInssExtTitularBean.getCodUsuarioSns();
				}
				if (Misc.esVacio(codUsuarioSnsTitular)) {
					throw new TitularNoEncontradoException(Misc.nz(resultadoInssExtBean.getNafTitular()));
				}
			}
		}

		sValorOld = Misc.nz(datosSnsBean.getCodTitulo());
		String codTituloTraducido = getTituloPorTipoAseguramiento(resultadoInssExtBean.getTipoAseguramiento());
		if (Misc.esVacio(codTituloTraducido)) {
			/**
			 * si la traduccion del tipo de aseguramiento a titulo no existe se
			 * lanza exception para abortar proceso
			 */
			throw new TituloNoEncontradoException(resultadoInssExtBean.getTipoAseguramiento());
		}
		if (!sValorOld.equals(codTituloTraducido)) {
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.TITULO, sValorOld, codTituloTraducido));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		if (!codUsuarioSnsTitular.equals(datosSnsBean.getCodUsuarioSnsTitular())) {
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}

		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", Misc.nz(resultadoInssExtBean.getNaf()));
			hParam.put("2", Misc.nz(resultadoInssExtBean.getNafTitular()));
			hParam.put("3", codUsuarioSnsTitular);
			hParam.put("4", codTituloTraducido);
			hParam.put("5", datosSnsBean.getCodUsuarioSns());
			bd.actualizaSinCommit(SQL_UPDATE_DATOS_COBERTURA, hParam);
		}

		return listaCamposAfectados;
	}

	private void fusion(String usuarioPrevalece, String usuarioAeliminar, AccesoBD bd) throws SQLException {
		String queryUpdate = "update datos_cobertura set cod_usuario_sns_titular='" + usuarioPrevalece + "' where cod_usuario_sns_titular ='" + usuarioAeliminar + "'";
		String queryDel = "delete from usuarios where cod_usuario_sns='" + usuarioAeliminar + "'";
		String queryDelDc = "delete from datos_cobertura where cod_usuario_sns='" + usuarioAeliminar + "'";
		String queryDelDp = "delete from datos_personales where cod_usuario_sns='" + usuarioAeliminar + "'";
		String queryDelDd = "delete from datos_domicilio where cod_usuario_sns='" + usuarioAeliminar + "'";
		String queryDelIu = "delete from identificadores_usuario where cod_usuario_sns='" + usuarioAeliminar + "'";

		bd.actualizaSinCommit(queryUpdate, null);
		bd.actualizaSinCommit(queryDel, null);
		bd.actualizaSinCommit(queryDelDc, null);
		bd.actualizaSinCommit(queryDelDp, null);
		bd.actualizaSinCommit(queryDelDd, null);
		bd.actualizaSinCommit(queryDelIu, null);
	}

	protected ListaCamposAfectadosVisiblesToComunidad actualizarUsuarios(ResultadoInssExtBean resultadoInssExtBean, IDatosSns datosSnsBean, AccesoBD bd) throws Exception {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();

		String codEstado = Misc.nz(datosSnsBean.getCodEstado());

		if ("B".equals(resultadoInssExtBean.getCodSituacion())) {
			/**
			 * estamos procesando una baja
			 */

			/**
			 * si el usuario esta de activo o en baja en servicio de salud damos
			 * de baja por aseguramiento y si no no hacer nada (puede estar
			 * fallecido u otros estados)
			 */
			if (datosSnsBean.getCodEstado().intValue() == Constantes.ESTADO_NORMAL || datosSnsBean.getCodEstado().intValue() == Constantes.ESTADO_BAJA_SERVICIO_SALUD) {
				listaCamposAfectados
						.addCampo(new CamposAfectados(CamposAfectados.COD_ESTADO, Misc.nz(datosSnsBean.getCodEstado()), Integer.toString(Constantes.ESTADO_BAJA_POR_FALTA_DE_ASEGURAMIENTO)));
				/**
				 * marcamos el cambio para tenerlo en cuenta en las
				 * notificaciones
				 */
				listaCamposAfectados.setActualizacionVisibleComunidad(true);
				codEstado = Integer.toString(Constantes.ESTADO_BAJA_POR_FALTA_DE_ASEGURAMIENTO);
			}

		} else {
			/**
			 * estamos procesando un alta
			 */
			/**
			 * si el usuario esta en baja por aseguramiento cambiamos el estado
			 * a activo y si no no hacemos nada
			 */
			if (datosSnsBean.getCodEstado().intValue() == Constantes.ESTADO_BAJA_POR_FALTA_DE_ASEGURAMIENTO) {
				listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.COD_ESTADO, Misc.nz(datosSnsBean.getCodEstado()), Integer.toString(Constantes.ESTADO_NORMAL)));
				/**
				 * marcamos el cambio para tenerlo en cuenta en las
				 * notificaciones
				 */
				listaCamposAfectados.setActualizacionVisibleComunidad(true);
				codEstado = Integer.toString(Constantes.ESTADO_NORMAL);
			}
		}

		HashMap hParam = new HashMap();
		hParam.put("1", codEstado);
		hParam.put("2", new Timestamp(System.currentTimeMillis()));
		hParam.put("3", datosSnsBean.getCodUsuarioSns());
		bd.actualizaSinCommit(SQL_UPDATE_USUARIOS, hParam);

		return listaCamposAfectados;
	}

	public ListaCamposAfectadosVisiblesToComunidad actualizarUsuarios(String codUsuarioSns,int codEstadoActual,int codEstadoNuevo, AccesoBD bd) throws Exception {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();

		if (codEstadoActual!= codEstadoNuevo) {
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.COD_ESTADO, Integer.toString(codEstadoActual), Integer.toString(codEstadoNuevo)));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las
			 * notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
		}


		if (!listaCamposAfectados.isEmpty()) {
			HashMap hParam = new HashMap();
			hParam.put("1", Integer.toString(codEstadoNuevo));
			hParam.put("2", new Timestamp(System.currentTimeMillis()));
			hParam.put("3", codUsuarioSns);
			bd.actualizaSinCommit(SQL_UPDATE_USUARIOS, hParam);
		}

		return listaCamposAfectados;
	}
	
	private ListaCamposAfectadosVisiblesToComunidad actualizarDatosPersonales(ResultadoInssExtBean resultadoInssExtBean, IDatosSns datosSnsBean, AccesoBD bd) throws Exception {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		boolean actualizar = false;

		String dniNie = Misc.nz(datosSnsBean.getDniNie());
		String pasaporte = Misc.nz(datosSnsBean.getPasaporte());

		String sValorOld = Misc.nz(datosSnsBean.getDniNie());
		if (!sValorOld.equals(resultadoInssExtBean.getDniNie())) {
			/**
			 * si no es vacio se actualiza y si el nuevo es vacio se deja como
			 * estaba
			 */
			if (!Misc.esVacio(resultadoInssExtBean.getDniNie())) {
				listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.DNI, sValorOld, Misc.nz(resultadoInssExtBean.getDniNie())));
				/**
				 * marcamos el cambio para tenerlo en cuenta en las
				 * notificaciones
				 */
				// listaCamposAfectados.setActualizacionVisibleComunidad(true);
				actualizar = true;
				dniNie = Misc.nz(resultadoInssExtBean.getDniNie());
			}
		}

		sValorOld = Misc.nz(datosSnsBean.getPasaporte());
		if (!sValorOld.equals(resultadoInssExtBean.getPasaporte())) {
			/**
			 * si no es vacio se actualiza y si el nuevo es vacio se deja como
			 * estaba
			 */
			if (!Misc.esVacio(resultadoInssExtBean.getPasaporte())) {
				listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.PASAPORTE, sValorOld, Misc.nz(resultadoInssExtBean.getPasaporte())));
				/**
				 * marcamos el cambio para tenerlo en cuenta en las
				 * notificaciones
				 */
				// listaCamposAfectados.setActualizacionVisibleComunidad(true);
				actualizar = true;
				pasaporte = Misc.nz(resultadoInssExtBean.getPasaporte());
			}
		}

		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", dniNie);
			hParam.put("2", pasaporte);
			hParam.put("3", datosSnsBean.getCodUsuarioSns());
			bd.actualizaSinCommit(SQL_UPDATE_DATOS_PERSONALES, hParam);
		}

		return listaCamposAfectados;
	}

	protected ListaCamposAfectadosVisiblesToComunidad actualizarDatosFarmacia(String codIndicadorDeFarmacia, String codSubindicadorDeFarmacia, IDatosSns datosSnsBean, AccesoBD bd)
			throws SQLException {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		boolean actualizar = false;
		String sValorOld = Misc.nz(datosSnsBean.getCodIndicadorDeFarmacia());
		if (!sValorOld.equals(Misc.nz(codIndicadorDeFarmacia))) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.INDICADOR_DE_FARMACIA, sValorOld, codIndicadorDeFarmacia));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			getLogger().debug("cambiando INDICADOR_DE_FARMACIA " + sValorOld + "|" + codIndicadorDeFarmacia);
			actualizar = true;
		}
		sValorOld = Misc.nz(datosSnsBean.getCodSubindicadorDeFarmacia());
		if (!sValorOld.equals(Misc.nz(codSubindicadorDeFarmacia))) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.SUBINDICADOR_DE_FARMACIA, sValorOld, codSubindicadorDeFarmacia));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			getLogger().debug("cambiando SUBINDICADOR_DE_FARMACIA " + sValorOld + "|" + codSubindicadorDeFarmacia);
			actualizar = true;
		}
		sValorOld = Misc.nz(datosSnsBean.getCodTipoProcedencia());
		if (!sValorOld.equals(Misc.nz(Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS))) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.TIPO_PROCEDENCIA_INDICADOR_DE_FARMACIA, sValorOld, Misc.nz(Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS)));
			actualizar = true;
		}
		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", codIndicadorDeFarmacia);
			hParam.put("2", codSubindicadorDeFarmacia);
			hParam.put("3", Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS);
			hParam.put("4", datosSnsBean.getCodUsuarioSns());
			Vector vDatos = bd.actualizaSinCommit(SQL_UPDATE_DATOS_FARMACIA, hParam);
			Integer registroAfectados = (Integer) vDatos.get(0);
			if (registroAfectados.intValue() == 0) {
				try {
					hParam = new HashMap();
					hParam.put("1", datosSnsBean.getCodUsuarioSns());
					hParam.put("2", codIndicadorDeFarmacia);
					hParam.put("3", codSubindicadorDeFarmacia);
					hParam.put("4", Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS);
					bd.actualizaSinCommit(SQL_INSERT_DATOS_FARMACIA, hParam);
				} catch (SQLException e) {
					if (e.getErrorCode() != 1) {
						throw e;
					} else {
						getLoggerSnsError().error(datosSnsBean.getCodUsuarioSns() + "|DF-" + Misc.nz(e.getMessage()).replace('\n', '_'));
					}
				}
			}
		}

		return listaCamposAfectados;
	}

	private ListaCamposAfectadosVisiblesToComunidad actualizarTitularDatosFarmacia(String codIndicadorDeFarmacia, String codSubindicadorDeFarmacia, ResultadoCruceSnsBean resultadoCruceSnsBean,
			AccesoBD bd) throws SQLException {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		boolean actualizar = false;
		String sValorOld = Misc.nz(resultadoCruceSnsBean.getCodIndicadorDeFarmacia());
		if (!sValorOld.equals(codIndicadorDeFarmacia)) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.INDICADOR_DE_FARMACIA, sValorOld, codIndicadorDeFarmacia));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		sValorOld = Misc.nz(resultadoCruceSnsBean.getCodSubindicadorDeFarmacia());
		if (!sValorOld.equals(codSubindicadorDeFarmacia)) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.SUBINDICADOR_DE_FARMACIA, sValorOld, codSubindicadorDeFarmacia));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		sValorOld = Misc.nz(resultadoCruceSnsBean.getCodTipoProcedencia());
		if (!sValorOld.equals(Misc.nz(Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS))) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.TIPO_PROCEDENCIA_INDICADOR_DE_FARMACIA, sValorOld, Misc.nz(Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS)));
			actualizar = true;
		}
		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", codIndicadorDeFarmacia);
			hParam.put("2", codSubindicadorDeFarmacia);
			hParam.put("3", Constantes.TIPO_PROCEDENCIA_FARMACIA_INSS);
			hParam.put("4", resultadoCruceSnsBean.getCodUsuarioSns());
			bd.actualizaSinCommit(SQL_UPDATE_DATOS_FARMACIA, hParam);
		}

		return listaCamposAfectados;
	}
	
	public void guardarHistorico(String codUsuarioSns, ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados,BigDecimal codTipoModificacion, Integer codOperacion, AccesoBD bd) throws SQLException {
		if (!listaCamposAfectados.isEmpty()) {
			HashMap hParamHist = new HashMap();
			hParamHist.put("1", codTipoModificacion);
			hParamHist.put("2", codUsuarioSns);
			hParamHist.put("3", codOperacion);
			hParamHist.put("4", listaCamposAfectados.getIdCampos());
			hParamHist.put("5", listaCamposAfectados.getValoresAnteriores());
			hParamHist.put("6", listaCamposAfectados.getValoresSolicitud());

			bd.actualizaSinCommit(SQL_INSERT_HISTORICO, hParamHist);
		}
	}

	private ArrayList findBeneficiarios2(String codSnsTitular, AccesoBD bd) throws SQLException {
		ArrayList arrayBen = new ArrayList();

		HashMap hParam = new HashMap();
		hParam.put("1", codSnsTitular);
		Vector vResp = bd.consulta(SQL_QUERY_BENEFICIARIOS, hParam);
		for (int i = 0; i < vResp.size(); i++) {
			arrayBen.add(new DatosCoberturaJoinDatosFarmaciaEstadoBean((HashMap) vResp.elementAt(i)));
		}
		return arrayBen;
	}
	
	public ArrayList findBeneficiarios(String codSnsTitular, AccesoBD bd) throws SQLException {
		ArrayList arrayBen = new ArrayList();

		HashMap hParam = new HashMap();
		hParam.put("1", codSnsTitular);
		Vector vResp = bd.consulta(SQL_QUERY_BENEFICIARIOS2, hParam);
		for (int i = 0; i < vResp.size(); i++) {
			arrayBen.add(new DatosSnsExtBean((HashMap) vResp.elementAt(i)));
		}
		return arrayBen;
	}

	private ListaCamposAfectadosVisiblesToComunidad actualizarBeneficiarioDatosFarmacia(String codIndicadorDeFarmaciaTitular,String codSubindicadorDeFarmaciaTitular, IDatosSns datosFarmaciaBenBean, BigDecimal tipoProcedencia, AccesoBD bd)
			throws SQLException {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		listaCamposAfectados.setCodUsuarioSns(datosFarmaciaBenBean.getCodUsuarioSns());
		boolean actualizar = false;
		String sValorOld = Misc.nz(datosFarmaciaBenBean.getCodIndicadorDeFarmacia());
		if (!sValorOld.equals(codIndicadorDeFarmaciaTitular)) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.INDICADOR_DE_FARMACIA, sValorOld, codIndicadorDeFarmaciaTitular));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		sValorOld = Misc.nz(datosFarmaciaBenBean.getCodSubindicadorDeFarmacia());
		if (!sValorOld.equals(Misc.nz(codSubindicadorDeFarmaciaTitular))) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.SUBINDICADOR_DE_FARMACIA, sValorOld, Misc.nz(codSubindicadorDeFarmaciaTitular)));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}

		sValorOld = Misc.nz(datosFarmaciaBenBean.getCodTipoProcedencia());
		/**
		 * la procedencia del titular en este proceso es siempre INSS por lo que
		 * los beneficiarios seran 4 indicando que son beneficiarios del INSS
		 */
		if (!sValorOld.equals(tipoProcedencia.toString())) {
			// marcamos el codTipoDeIndicador
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.TIPO_PROCEDENCIA_INDICADOR_DE_FARMACIA, sValorOld, tipoProcedencia.toString()));
			actualizar = true;
		}
		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", codIndicadorDeFarmaciaTitular);
			hParam.put("2", codSubindicadorDeFarmaciaTitular);
			hParam.put("3", tipoProcedencia);

			hParam.put("4", datosFarmaciaBenBean.getCodUsuarioSns());

			Vector vDatos = bd.actualizaSinCommit(SQL_UPDATE_DATOS_FARMACIA, hParam);
			Integer registroAfectados = (Integer) vDatos.get(0);
			if (registroAfectados.intValue() == 0) {
				try {
					hParam = new HashMap();
					hParam.put("1", datosFarmaciaBenBean.getCodUsuarioSns());
					hParam.put("2", codIndicadorDeFarmaciaTitular);
					hParam.put("3", codSubindicadorDeFarmaciaTitular);
					hParam.put("4", tipoProcedencia);
					bd.actualizaSinCommit(SQL_INSERT_DATOS_FARMACIA, hParam);
				} catch (SQLException e) {
					getLoggerSnsError().error(datosFarmaciaBenBean.getCodUsuarioSns() + "|DF-Ben " + Misc.nz(e.getMessage()).replace('\n', '_'));
				}
			}

		}

		return listaCamposAfectados;
	}

	private ListaCamposAfectadosVisiblesToComunidad actualizarBeneficiarioDatosCobertura(String codUsuarioSnsTitular,
			String nafTitular, String codTituloTitular, IDatosSns datosFarmaciaBenBean, String nafNuevo, boolean tenerEnCuentaNafNuevo,
			AccesoBD bd) throws SQLException {
		ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados = new ListaCamposAfectadosVisiblesToComunidad();
		boolean actualizar = false;

		String sValorOld = Misc.nz(datosFarmaciaBenBean.getCodTitulo());
		if (!sValorOld.equals(codTituloTitular)) {
			listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.TITULO, sValorOld, codTituloTitular));
			/**
			 * marcamos el cambio para tenerlo en cuenta en las notificaciones
			 */
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		/**
		 * la variable tenerEnCuentaNafNuevo nos indica si hay que tener en cuenta
		 * el nafNuevo pasado por parametro o hay que ignorarlo
		 */
		if (tenerEnCuentaNafNuevo) {
			sValorOld = Misc.nz(datosFarmaciaBenBean.getNaf());
			if (!sValorOld.equals(nafNuevo)) {
				listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.NAF, sValorOld, nafNuevo));
				/**
				 * marcamos el cambio para tenerlo en cuenta en las notificaciones
				 */
				listaCamposAfectados.setActualizacionVisibleComunidad(true);
				actualizar = true;
			}
		}else{
			/**
			 * aunque ignoremos el nafNuevo asignamos con el valor de naf que viene del SNS
			 * para no quitar ese valor en el update
			 */
			nafNuevo=Misc.nz(datosFarmaciaBenBean.getNaf());
		}
		
		/**
		 * igual el titular ha cambiado el naf y por lo tanto hay que cambiarlo
		 * al beneficiario
		 */
		sValorOld = Misc.nz(datosFarmaciaBenBean.getNafTitular());
		if (!sValorOld.equals(Misc.nz(nafTitular))) {
			/**
			 * puede que estemos cambiando de titular porque es una restauracion a beneficiario
			 */
			if (!datosFarmaciaBenBean.getCodUsuarioSnsTitular().equals(codUsuarioSnsTitular)) {
				listaCamposAfectados.addCampo(new CamposAfectados(CamposAfectados.NAF_TITULAR, sValorOld, nafTitular));
			}
			listaCamposAfectados.setActualizacionVisibleComunidad(true);
			actualizar = true;
		}
		if (actualizar) {
			HashMap hParam = new HashMap();
			hParam.put("1", nafNuevo);
			hParam.put("2", nafTitular);
			hParam.put("3", codTituloTitular);
			hParam.put("4", codUsuarioSnsTitular);
			hParam.put("5", datosFarmaciaBenBean.getCodUsuarioSns());
			bd.actualizaSinCommit(SQL_UPDATE_DATOS_COBERTURA_BEN, hParam);
		}

		return listaCamposAfectados;
	}

	/**
	 * Buscamos en el SNS por otra cosa que no sea la pareja NAF y (DNI_NIE o
	 * PASAPORTE)
	 * 
	 * @param ifiRegistro
	 * @param bd
	 * @return
	 * @throws UsuarioNoEncontradoException
	 * @throws MultiplesUsuariosSnsEncontradosException
	 */
	public ResultadoCruceSnsBean buscarPorAproximacion(IInss inssBean, AccesoBD bd) throws UsuarioNoEncontradoException, MultiplesUsuariosSnsEncontradosException {
		// 
		ResultadoCruceSnsBean resultadoCruceSnsBean = new ResultadoCruceSnsBean();
		ArrayList arrayResultadoCruceSnsBean = new ArrayList();
		//
		try {
			getLogger().debug("INICIO");
			//
			if (!Misc.esVacio(inssBean.getDniNie())) {
				ArrayList arrayResultadoCruceSnsBeanDni = this.buscarDniNie(bd, inssBean.getDniNie());
				// logger.debug("arrayResultadoCruceSnsBeanDni.size():    " +
				// arrayResultadoCruceSnsBeanDni.size());
				if (arrayResultadoCruceSnsBeanDni.size() > 0) {
					for (int i = 0; i < arrayResultadoCruceSnsBeanDni.size(); i++) {
						ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = (ResultadoCruceExtSnsBean) arrayResultadoCruceSnsBeanDni.get(i);
						int camposCoincidentes = ComparaDatosSns.compararDatos(inssBean, resultadoCruceExtSnsBean, "DNI_NIE");
						if (camposCoincidentes > 3) {
							arrayResultadoCruceSnsBean.add((ResultadoCruceSnsBean) resultadoCruceExtSnsBean);
						}else{
							getLogger().debug("Usuario no encontrado por mas criterios: " + resultadoCruceExtSnsBean.toString());
							getLogger().debug(inssBean.toString());
						}
					}
				}
			} else {
				if (!Misc.esVacio(inssBean.getPasaporte())) {
					ArrayList arrayResultadoCruceSnsBeanPasaporte = this.buscarPasaporte(bd, inssBean.getPasaporte());
					// logger.debug("arrayResultadoCruceSnsBeanPasaporte.size():    "
					// + arrayResultadoCruceSnsBeanPasaporte.size());
					if (arrayResultadoCruceSnsBeanPasaporte.size() > 0) {
						for (int i = 0; i < arrayResultadoCruceSnsBeanPasaporte.size(); i++) {
							ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = (ResultadoCruceExtSnsBean) arrayResultadoCruceSnsBeanPasaporte.get(i);
							int camposCoincidentes = ComparaDatosSns.compararDatos(inssBean, resultadoCruceExtSnsBean, "PASAPORTE");
							if (camposCoincidentes > 3) {
								arrayResultadoCruceSnsBean.add((ResultadoCruceSnsBean) resultadoCruceExtSnsBean);
							}else{
								getLogger().debug("Usuario no encontrado por mas criterios: " + resultadoCruceExtSnsBean.toString());
								getLogger().debug(inssBean.toString());
							}
						}
					}
				}
			}
			//
			ArrayList arrayResultadoCruceSnsBeanNaf = this.buscarNaf(bd, inssBean);
			// logger.debug("arrayResultadoCruceSnsBeanNaf():    " +
			// arrayResultadoCruceSnsBeanNaf.size());
			if (arrayResultadoCruceSnsBeanNaf.size() > 0) {
				for (int i = 0; i < arrayResultadoCruceSnsBeanNaf.size(); i++) {
					ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = (ResultadoCruceExtSnsBean) arrayResultadoCruceSnsBeanNaf.get(i);
					int camposCoincidentes = ComparaDatosSns.compararDatos(inssBean, resultadoCruceExtSnsBean, resultadoCruceExtSnsBean.getValorNafCoincidente());
					if (camposCoincidentes > 3) {
						arrayResultadoCruceSnsBean.add((ResultadoCruceSnsBean) resultadoCruceExtSnsBean);
					}else{
						getLogger().debug("Usuario no encontrado por mas criterios: " + resultadoCruceExtSnsBean.toString());
						getLogger().debug(inssBean.toString());
					}
				}
			}
			//
			//
			//
			for (int i = 0; i < arrayResultadoCruceSnsBean.size(); i++) {
				ResultadoCruceSnsBean resultadoCruceSnsBeanAux = (ResultadoCruceSnsBean) arrayResultadoCruceSnsBean.get(i);
				getLogger().debug("resultadoCruceSnsBeanAux: " + resultadoCruceSnsBeanAux.toString());
			}
			//
			if (arrayResultadoCruceSnsBean.size() == 1) {
				resultadoCruceSnsBean = (ResultadoCruceSnsBean) arrayResultadoCruceSnsBean.get(0);
			} else {
				if (arrayResultadoCruceSnsBean.size() == 0) {
					throw new UsuarioNoEncontradoException(inssBean.getCodUsuarioSns());
				} else {
					if (arrayResultadoCruceSnsBean.size() > 1) {
						throw new MultiplesUsuariosSnsEncontradosException(arrayResultadoCruceSnsBean);
					}
				}
			}
			//
			getLogger().debug("FIN");
		} catch (UsuarioNoEncontradoException e) {
//			getLogger().error("Exception:  " + e.getMessage(), e);
			throw e;
		} catch (MultiplesUsuariosSnsEncontradosException e) {
//			getLogger().error("Exception:  " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			getLogger().error("Exception:  " + e.getMessage(), e);
		}
		return resultadoCruceSnsBean;
	}

	private ArrayList buscarNaf(AccesoBD bd, IInss inssBean) throws Exception {
		//
		//
		HashMap mapNaf = new HashMap();
		//		
		ArrayList arrayResultadoCruceSnsBean = new ArrayList();
		//
		String query = "";
		HashMap parametros = new HashMap();
		//
		try {
			// logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery
					.append(" select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE  ");
			sbQuery.append(" , TO_CHAR(dp.COD_SEXO) COD_SEXO, TO_CHAR(dp.FECHA_NAC, 'YYYYMMDD') FECHA_NAC ");

			sbQuery.append(" from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA  ");
			//
			sbQuery.append(" where ( ");

			sbQuery.append(" dc.naf = ?  ");
			parametros.put("1", Misc.nz(inssBean.getNaf()));
			mapNaf.put(Misc.nz(inssBean.getNaf()), "NAF");
			//
			// Se comprueba los Naf secundarios, si no hay naf_secn no se
			// comprueba el siguiente
			//
			if (!Misc.esVacio(inssBean.getNafSec1())) {
				sbQuery.append(" OR dc.naf = ?  ");
				parametros.put("2", Misc.nz(inssBean.getNafSec1()));
				mapNaf.put(Misc.nz(inssBean.getNafSec1()), "NAF_SEC1");
				//
				if (!Misc.esVacio(inssBean.getNafSec2())) {
					sbQuery.append(" OR dc.naf = ?  ");
					parametros.put("3", Misc.nz(inssBean.getNafSec2()));
					mapNaf.put(Misc.nz(inssBean.getNafSec2()), "NAF_SEC2");
					//
					if (!Misc.esVacio(inssBean.getNafSec3())) {
						sbQuery.append(" OR dc.naf = ?  ");
						parametros.put("4", Misc.nz(inssBean.getNafSec3()));
						mapNaf.put(Misc.nz(inssBean.getNafSec3()), "NAF_SEC3");
						//
						if (!Misc.esVacio(inssBean.getNafSec4())) {
							sbQuery.append(" OR dc.naf = ?  ");
							parametros.put("5", Misc.nz(inssBean.getNafSec4()));
							mapNaf.put(Misc.nz(inssBean.getNafSec4()), "NAF_SEC4");
							//
							if (!Misc.esVacio(inssBean.getNafSec5())) {
								sbQuery.append(" OR dc.naf = ?  ");
								parametros.put("6", Misc.nz(inssBean.getNafSec5()));
								mapNaf.put(Misc.nz(inssBean.getNafSec5()), "NAF_SEC5");
								//
								if (!Misc.esVacio(inssBean.getNafSec6())) {
									sbQuery.append(" OR dc.naf = ?  ");
									parametros.put("7", Misc.nz(inssBean.getNafSec6()));
									mapNaf.put(Misc.nz(inssBean.getNafSec6()), "NAF_SEC6");
									//
									if (!Misc.esVacio(inssBean.getNafSec7())) {
										sbQuery.append(" OR dc.naf = ?  ");
										parametros.put("8", Misc.nz(inssBean.getNafSec7()));
										mapNaf.put(Misc.nz(inssBean.getNafSec7()), "NAF_SEC7");
										//
										if (!Misc.esVacio(inssBean.getNafSec8())) {
											sbQuery.append(" OR dc.naf = ?  ");
											parametros.put("9", Misc.nz(inssBean.getNafSec8()));
											mapNaf.put(Misc.nz(inssBean.getNafSec8()), "NAF_SEC8");
											//
											if (!Misc.esVacio(inssBean.getNafSec9())) {
												sbQuery.append(" OR dc.naf = ?  ");
												parametros.put("10", Misc.nz(inssBean.getNafSec9()));
												mapNaf.put(Misc.nz(inssBean.getNafSec9()), "NAF_SEC9");
											}
										}
									}
								}
							}
						}
					}
				}
			}
			sbQuery.append("        ) ");
			//
			sbQuery.append(" and u.cod_usuario_sns          = dc.cod_usuario_sns  ");
			sbQuery.append(" AND u.cod_estado              IN (0, 1, 2, 7) ");
			sbQuery.append(" and dp.cod_usuario_sns         = dc.cod_usuario_sns  ");
			sbQuery.append(" and df.cod_usuario_sns     (+) = dc.cod_usuario_sns  ");
			sbQuery.append(" and I.COD_USUARIO_SNS          = u.cod_usuario_sns  ");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND I.COD_AGENTE               = DA.COD_AGENTE  ");
			query = sbQuery.toString();
			//
			Vector vDatos = bd.consulta(query, parametros);
			if (vDatos.size() > 0) {
				for (int i = 0; i < vDatos.size(); i++) {
					ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = new ResultadoCruceExtSnsBean((HashMap) vDatos.get(i));

					String criterio = "";
					if (mapNaf.containsKey(resultadoCruceExtSnsBean.getNaf())) {
						criterio = (String) mapNaf.get(resultadoCruceExtSnsBean.getNaf());
					}
					resultadoCruceExtSnsBean.setValorNafCoincidente(criterio);
					// logger.debug("setValorNafCoincidente: " + criterio);
					//
					arrayResultadoCruceSnsBean.add(resultadoCruceExtSnsBean);
				}
			}
			//
			// logger.debug("FIN");
		} catch (Exception e) {
			getLogger().error("Exception:  " + e.getMessage());
			getLogger().error("query:      " + query);
			getLogger().error("parametros: " + parametros);
			throw e;
		}
		return arrayResultadoCruceSnsBean;
	}

	private ArrayList buscarDniNie(AccesoBD bd, String dniNie) throws Exception {
		//
		ArrayList arrayResultadoCruceSnsBean = new ArrayList();
		//
		String query = "";
		HashMap parametros = new HashMap();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			// logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery
					.append(" select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE  ");
			sbQuery.append(" , TO_CHAR(dp.COD_SEXO) COD_SEXO, TO_CHAR(dp.FECHA_NAC, 'YYYYMMDD') FECHA_NAC ");
			sbQuery.append(" from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA  ");
			//
			sbQuery.append(" where  dp.dni_nie = ?  ");

			sbQuery.append(" and u.cod_usuario_sns          = dp.cod_usuario_sns  ");
			sbQuery.append(" AND u.cod_estado              IN (0, 1, 2, 7) ");
			sbQuery.append(" and dc.cod_usuario_sns         = dp.cod_usuario_sns  ");
			sbQuery.append(" and df.cod_usuario_sns     (+) = dc.cod_usuario_sns  ");
			sbQuery.append(" and I.COD_USUARIO_SNS          = u.cod_usuario_sns  ");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND I.COD_AGENTE               = DA.COD_AGENTE  ");
			sbQuery.append(" AND ROWNUM                     < 100  ");
			query = sbQuery.toString();
			//
			parametros.put("1", Misc.nz(dniNie));
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			int contadorRegistros = 0;
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = new ResultadoCruceExtSnsBean(resultSet);
				arrayResultadoCruceSnsBean.add(resultadoCruceExtSnsBean);
			}
			//
			// logger.debug("FIN");
		} catch (Exception e) {
			getLogger().error("Exception:  " + e.getMessage());
			getLogger().error("query:      " + query);
			getLogger().error("parametros: " + parametros);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayResultadoCruceSnsBean;
	}

	private ArrayList buscarPasaporte(AccesoBD bd, String pasaporte) throws Exception {
		//
		ArrayList arrayResultadoCruceSnsBean = new ArrayList();
		//
		String query = "";
		HashMap parametros = new HashMap();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			// logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery
					.append(" select U.COD_USUARIO_SNS,u.COD_PRESTACION_SERVICIO,u.COD_ESTADO,DC.COD_USUARIO_SNS_TITULAR,DC.NAF,DC.NAF_TITULAR,DC.INTERNO,DC.COD_TITULO,DP.DNI_NIE,DP.PASAPORTE,DP.NOMBRE,DP.APELLIDO1,DP.APELLIDO2,DF.COD_INDICADOR_DE_FARMACIA,DF.COD_SUBINDICADOR_DE_FARMACIA,DF.COD_TIPO_PROCEDENCIA,I.ID_EN_SSALUD,I.COD_AGENTE  ");
			sbQuery.append(" , TO_CHAR(dp.COD_SEXO) COD_SEXO, TO_CHAR(dp.FECHA_NAC, 'YYYYMMDD') FECHA_NAC ");
			sbQuery.append(" from usuarios u, datos_personales dp, datos_cobertura dc,datos_farmacia df,IDENTIFICADORES_USUARIO I,DESGLOSE_AGENTES DA  ");
			//
			sbQuery.append(" where  dp.pasaporte = ?  ");
			sbQuery.append(" and u.cod_usuario_sns          = dp.cod_usuario_sns  ");
			sbQuery.append(" AND u.cod_estado              IN (0, 1, 2, 7) ");
			sbQuery.append(" and dc.cod_usuario_sns         = dp.cod_usuario_sns  ");
			sbQuery.append(" and df.cod_usuario_sns     (+) = dc.cod_usuario_sns  ");
			sbQuery.append(" and I.COD_USUARIO_SNS          = u.cod_usuario_sns  ");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND I.COD_AGENTE               = DA.COD_AGENTE  ");
			query = sbQuery.toString();
			//
			parametros.put("1", Misc.nz(pasaporte));
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			int contadorRegistros = 0;
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				ResultadoCruceExtSnsBean resultadoCruceExtSnsBean = new ResultadoCruceExtSnsBean(resultSet);
				arrayResultadoCruceSnsBean.add(resultadoCruceExtSnsBean);
			}
			//
			// logger.debug("contadorRegistros:    " + contadorRegistros);
			//
			// logger.debug("FIN");
		} catch (Exception e) {
			getLogger().error("Exception:  " + e.getMessage());
			getLogger().error("query:      " + query);
			getLogger().error("parametros: " + parametros);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayResultadoCruceSnsBean;
	}

	private Localizador getLocalizador(DatosSnsExtBean datosSnsBean) {
		Localizador localizador=new Localizador();
		localizador.setApellido1(datosSnsBean.getApellido1());
		localizador.setApellido2(datosSnsBean.getApellido2());
		localizador.setNombre(datosSnsBean.getNombre());
		localizador.setCodEstado(Misc.nz(datosSnsBean.getCodEstado()));
		localizador.setCodIdssalud(Misc.nz(datosSnsBean.getCodAgente()));
		localizador.setDni(Misc.nz(datosSnsBean.getDniNie()));
		if (datosSnsBean.getFechaNacimiento()!=null) {
			Date fechaNac=new Date(datosSnsBean.getFechaNacimiento().getTime());
			localizador.setFecha_nac(Misc.nz(fechaNac));
		}else{
			localizador.setFecha_nac("");
		}
		localizador.setId_ssalud(Misc.nz(datosSnsBean.getIdEnSsalud()));
		localizador.setIndicadorDeFarmacia(Misc.nz(datosSnsBean.getCodIndicadorDeFarmacia()));
		localizador.setNaf(Misc.nz(datosSnsBean.getNaf()));
		localizador.setNaf_titular(Misc.nz(datosSnsBean.getNafTitular()));
		localizador.setNombre(Misc.nz(datosSnsBean.getNombre()));
		localizador.setPasaporte(Misc.nz(datosSnsBean.getPasaporte()));
		localizador.setSexo(Misc.nz(datosSnsBean.getCodSexo()));
		localizador.setSns(datosSnsBean.getCodUsuarioSns());
		localizador.setSubIndicadorDeFarmacia(Misc.nz(datosSnsBean.getCodSubindicadorDeFarmacia()));
		localizador.setCa_nac(getCaIsoFromCaIne(Misc.nz(datosSnsBean.getCodComunidad())));
		localizador.setPaisNac(Misc.nz(datosSnsBean.getPaisNac()));
		return localizador;
	}
}
