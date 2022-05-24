package sns.busqueda.model;

import gasai.util.Misc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.SnsBean;
import sns.comun.config.InicializacionBusqueda;


public class SnsBdHelper {
	
	
	private String cabecera = "";
	private String from     = ""; 
	
	
	// false = todos los estados
	// true solo estados sin error ni duplicidad:  0, 1, 2, 7
	private static final boolean estadosSinErrorDuplicidad = false;
	
	
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	public SnsBdHelper () {
		StringBuffer sbCabecera = new StringBuffer();
		sbCabecera.append(" SELECT   u.COD_USUARIO_SNS,  ");
		sbCabecera.append("          u.COD_ESTADO, ");
		sbCabecera.append("          TO_CHAR(U.FECHA_ULT_ACTUALIZACION, 'YYYY-MM-DD') as FECHA_ULT_ACTUALIZACION, ");
		sbCabecera.append("          iu.ID_EN_SSALUD, ");
		sbCabecera.append("          (SELECT COD_CA_ISO FROM   snsalud.CA_PRESTACION_SERVICIO cps, snsalud.COMUNIDADES_AUTONOMAS ca WHERE  u.COD_PRESTACION_SERVICIO = cps.COD_PRESTACION_SERVICIO  AND cps.COD_COMUNIDAD = ca.COD_COMUNIDAD) CODCAISO, ");
		sbCabecera.append("          dp.APELLIDO1,  ");
		sbCabecera.append("          dp.APELLIDO2,  ");
		sbCabecera.append("          dp.NOMBRE, ");
		sbCabecera.append("          dp.COD_SEXO, ");
		sbCabecera.append("          TO_CHAR(dp.FECHA_NAC, 'YYYY-MM-DD') as FECHA_NAC, ");
		sbCabecera.append("          dp.RAIZ, ");
		sbCabecera.append("          dp.DNI_NIE,  ");
		sbCabecera.append("          dp.PASAPORTE, ");
		sbCabecera.append("          dc.COD_TITULO, ");
		sbCabecera.append("          dc.COD_SITUACION,  ");
		sbCabecera.append("          dc.NAF,  ");
		sbCabecera.append("          dc.NAF_TITULAR, ");
		sbCabecera.append("          df.COD_INDICADOR_DE_FARMACIA, ");
		sbCabecera.append("          df.COD_SUBINDICADOR_DE_FARMACIA, ");
		sbCabecera.append("          df.COD_TIPO_PROCEDENCIA ");
		cabecera = sbCabecera.toString();
		//
		StringBuffer sbfrom = new StringBuffer();
		sbfrom.append(" from     snsalud.usuarios                u,  ");
		sbfrom.append("          snsalud.datos_personales        dp,  ");
		sbfrom.append("          snsalud.datos_cobertura         dc,  ");
		sbfrom.append("          snsalud.datos_farmacia          df,  ");
		sbfrom.append("          snsalud.identificadores_usuario iu,  ");
		sbfrom.append("          snsalud.desglose_agentes        da ");
		from = sbfrom.toString();
	}
	
	
	public ArrayList <SnsBean> getInfoSnsByDni (AccesoBd accesoBd, String dni) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(cabecera);
			sbQuery.append(from);
			sbQuery.append(" where    dp.DNI_NIE                 = ? ");
			sbQuery.append(" AND      dc.COD_USUARIO_SNS         = dp.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      u.COD_USUARIO_SNS          = dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			sbQuery.append(" AND      df.COD_USUARIO_SNS    (+)  = dp.COD_USUARIO_SNS ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = dp.COD_USUARIO_SNS ");
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(dni));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("arrayList.soze: "  +  arrayList.size());
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}	
	
	
	
	public SnsBean getInfoSnsByNaf (AccesoBd accesoBd, String naf) throws Exception {
		//
		SnsBean snsBean = null;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(cabecera);
			sbQuery.append(from);
			sbQuery.append(" where    dc.NAF                     = ? ");
			sbQuery.append(" AND      dp.COD_USUARIO_SNS         = dc.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      df.COD_USUARIO_SNS    (+)  = dc.COD_USUARIO_SNS ");
			sbQuery.append(" AND      u.COD_USUARIO_SNS          = dc.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(naf));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			Vector vResp = accesoBd.consulta(query, parametros);
			if (!vResp.isEmpty()) {
				snsBean = new SnsBean((HashMap) vResp.elementAt(0));
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		return snsBean;
	}	
	
	
	public SnsBean getInfoSnsByCodUsuario (AccesoBd accesoBd, String codUsuario) throws Exception {
		//
		SnsBean snsBean = null;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(cabecera);
			sbQuery.append(from);
			sbQuery.append(" where    u.COD_USUARIO_SNS          = ? ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			sbQuery.append(" AND      dp.COD_USUARIO_SNS         = u.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      dc.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      df.COD_USUARIO_SNS    (+)  = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(codUsuario));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			Vector vResp = accesoBd.consulta(query, parametros);
			if (!vResp.isEmpty()) {
				snsBean = new SnsBean((HashMap) vResp.elementAt(0));
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		return snsBean;
	}
	
	
	public SnsBean getInfoSnsByIdSsalud (AccesoBd accesoBd, String idSsalud) throws Exception {
		//
		SnsBean snsBean = new SnsBean ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(cabecera);
			sbQuery.append(from);
			sbQuery.append(" where    iu.ID_EN_SSALUD            = ? ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			sbQuery.append(" AND      dp.COD_USUARIO_SNS         = u.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      dc.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      df.COD_USUARIO_SNS    (+)  = u.COD_USUARIO_SNS ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(idSsalud));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			if (resultSet.next()) {
				snsBean = new SnsBean (resultSet);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return snsBean;
	}
	
	
	public ArrayList <SnsBean> getInfoSnsByNaftitular (AccesoBd accesoBd, String nafTitular) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer stringBuffer = new StringBuffer();
			
			stringBuffer.append(" SELECT   s.COD_USUARIO_SNS,   ");
			stringBuffer.append("          '' COD_ESTADO,  ");
			stringBuffer.append("          '' FECHA_ULT_ACTUALIZACION,  ");
			stringBuffer.append("          i.ID_INSS_BEN ID_EN_SSALUD,  ");
			stringBuffer.append("          '' CODCAISO,  ");
			stringBuffer.append("          i.APELLIDO1,   ");
			stringBuffer.append("          i.APELLIDO2,   ");
			stringBuffer.append("          i.NOMBRE,  ");
			stringBuffer.append("          i.SEXO COD_SEXO,  ");
			//stringBuffer.append("          i.FECHA_NACIMIENTO_RAW, null, null, '18790000', null, '18850000', null, '18800000', null, '00000000', null, to_char(to_date(i.FECHA_NACIMIENTO_RAW, 'yyyymmdd'), 'yyyy-mm-dd')) FECHA_NAC,  ");
			stringBuffer.append("          i.FECHA_NACIMIENTO_RAW  FECHA_NAC, ");
			stringBuffer.append("          '' RAIZ,  ");
			stringBuffer.append("          i.DNI_NIE,   ");
			stringBuffer.append("          i.PASAPORTE,  ");
			stringBuffer.append("          I.COD_TIPO_ASEGURADO COD_TITULO,  ");
			stringBuffer.append("          i.COD_SITUACION,   ");
			stringBuffer.append("          i.NAF,   ");
			stringBuffer.append("          i.NAF_TITULAR,  ");
			stringBuffer.append("          i.COD_INDICADOR_DE_FARMACIA,  ");
			stringBuffer.append("          i.COD_SUBINDICADOR_DE_FARMACIA,  ");
			stringBuffer.append("          '' COD_TIPO_PROCEDENCIA ");
			stringBuffer.append(" FROM     inss_ben i, inss_sns_ben s ");
			stringBuffer.append(" where    i.NAF_TITULAR         = ? ");
			stringBuffer.append(" and      S.ID_INSS_BEN         = i.ID_INSS_BEN  ");
			
			//
			/*
			stringBuffer.append(" SELECT   Z.CODIGO_BADAS COD_USUARIO_SNS,    ");
			stringBuffer.append("          Z.TIPO_MOVIMIENTO COD_ESTADO,   ");
			stringBuffer.append("          '' FECHA_ULT_ACTUALIZACION,   ");
			stringBuffer.append("          Z.ID_BEN ID_EN_SSALUD,   ");
			stringBuffer.append("          '' CODCAISO,   ");
			stringBuffer.append("          z.APELLIDO1,    ");
			stringBuffer.append("          z.APELLIDO2,    ");
			stringBuffer.append("          z.NOMBRE,   ");
			stringBuffer.append("          z.SEXO COD_SEXO,   ");
			stringBuffer.append("          z.FECHA_NACIMIENTO_RAW  FECHA_NAC,  ");
			stringBuffer.append("          '' RAIZ,   ");
			stringBuffer.append("          z.DNI_NIE,    ");
			stringBuffer.append("          z.PASAPORTE,   ");
			stringBuffer.append("          z.COD_TIPO_ASEGURADO COD_TITULO,   ");
			stringBuffer.append("          z.COD_SITUACION,    ");
			stringBuffer.append("          z.NAF,    ");
			stringBuffer.append("          z.NAF_TITULAR,   ");
			stringBuffer.append("          z.COD_INDICADOR_DE_FARMACIA,   ");
			stringBuffer.append("          z.COD_SUBINDICADOR_DE_FARMACIA,   ");
			stringBuffer.append("          Z.COD_TIPO_ASEGURADO COD_TIPO_PROCEDENCIA  ");
			stringBuffer.append(" FROM     Z_INSS_MOV_BEN_SEPT z              ");
			stringBuffer.append(" where    Z.NAF_TITULAR        =  ?          ");	
			stringBuffer.append(" AND      Z.COD_TIPO_ASEGURADO =  'B'        ");
			stringBuffer.append(" and      Z.TIPO_MOVIMIENTO    IN ('A', 'M') ");
			*/
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				//
				String fechaNacimiento = Misc.nz(snsBean.getFechaNacimiento());
				//
				if(!Misc.esVacio(fechaNacimiento) && Misc.nz(fechaNacimiento).length() > 7) {
					String year = fechaNacimiento.substring(0, 4);
					String mes  = fechaNacimiento.substring(4, 6);
					String dia  = fechaNacimiento.substring(6, 8);
					//logger.debug("year: " + year + ", mes: " + mes + ", dia: " + dia);
					fechaNacimiento = year + "-" + mes + "-" + dia;
					//logger.debug("fechaNacimiento: " + fechaNacimiento);
				}
				else {
					fechaNacimiento = "0000-00-00";
				}
				snsBean.setFechaNacimiento(fechaNacimiento);
				//
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}	
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitRaiz (AccesoBd accesoBd, String raiz, String nafTitular) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append(from);
			stringBuffer.append(" where    DC.NAF_TITULAR             =  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      dp.RAIZ                    like  ?  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			parametros.put("2", Misc.nz(raiz));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}
	
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitNombre (AccesoBd accesoBd, String nombre, String nafTitular, String fechaNac) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//			
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append(from);
			stringBuffer.append(" where    DC.NAF_TITULAR                      =  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS                  =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      dp.NOMBRE                           = ? ");
			stringBuffer.append(" AND      TO_CHAR(dp.FECHA_NAC, 'YYYY-MM-DD') = ? ");
			
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			parametros.put("2", Misc.nz(nombre));
			parametros.put("3", Misc.nz(fechaNac));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}
	
	
	
	public ArrayList <SnsBean> getInfoSnsByRaizNombre (AccesoBd accesoBd, String raiz, String nombre, String fechaNac) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append(from);
			stringBuffer.append(" where    dp.RAIZ                             =  ? ");
			stringBuffer.append(" AND      dp.NOMBRE                           =  ? ");
			//
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(nombre));
			//parametros.put("3", Misc.nz(fechaNac));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}
	
	
	
	public ArrayList <SnsBean> getInfoSnsByRaizApellidos (AccesoBd accesoBd, String raiz, String apellido1, String apellido2) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append(from);
			stringBuffer.append(" where    dp.RAIZ                    =  ? ");
			stringBuffer.append(" AND      dp.APELLIDO1               =  ? ");
			stringBuffer.append(" AND      dp.APELLIDO2               =  ? ");
			//
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(apellido1));
			parametros.put("3", Misc.nz(apellido2));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}
	
	
	
	public ArrayList <SnsBean> getInfoSnsByOtros (AccesoBd accesoBd, String raiz, String apellido1) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//				
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append( from );
			stringBuffer.append(" where    dp.RAIZ                             =  ? ");
			stringBuffer.append(" AND      dp.APELLIDO1                        =  ? ");	
			//
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			//String raizSubcadena = raiz.substring(0, 4) + "%";
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(apellido1));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitPrefijoRaiz (AccesoBd accesoBd, String raiz, String nafTitular) throws Exception {
		//
		ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cabecera);
			stringBuffer.append(from);
			stringBuffer.append(" where    DC.NAF_TITULAR             =  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      ( dc.naf is null  OR dc.naf <> DC.NAF_TITULAR ) ");
			stringBuffer.append(" AND      dp.RAIZ                    like  ?  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(" AND      u.COD_ESTADO               IN (0, 1, 2, 7) ");
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS    (+)  =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			String raiz2 = raiz.substring(0, 4) + "%";
			//logger.debug("raiz2: " + raiz2);
			parametros.put("2", Misc.nz(raiz2));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				SnsBean snsBean = new SnsBean (resultSet);
				arrayList.add(snsBean);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
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
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
		}
		return arrayList;
	}	
	
	
	
}
