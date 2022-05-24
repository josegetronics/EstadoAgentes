package sns.busqueda.model;

import gasai.util.Misc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.SnsBean;
import sns.comun.config.InicializacionBusqueda;


public class SnsBdHelperNoElMismo {
	
	
	private String cabecera = "";
	private String from     = ""; 
	
	
	// false = todos los estados
	// true solo estados sin error ni duplicidad:  0, 1, 2, 7
	private static final boolean estadosSinErrorDuplicidad = true;
	
	private static final String CADENA_ESTADOS = " AND      u.COD_ESTADO               IN (0, 1, 2, 7) ";
	//private static final String CADENA_ESTADOS = " AND      u.COD_ESTADO               IN (3, 4, 5, 6) ";
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	public SnsBdHelperNoElMismo () {
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
		sbCabecera.append("          dcTit.NAF NAF_TITULAR, ");
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
		sbfrom.append("          snsalud.datos_cobertura         dcTit,  ");
		sbfrom.append("          snsalud.identificadores_usuario iu,  ");
		sbfrom.append("          snsalud.desglose_agentes        da ");
		from = sbfrom.toString();
	}
	
	
	public SnsBean getInfoSnsByIdSsalud (AccesoBd accesoBd, String idSsalud, String cod) throws Exception {
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
			sbQuery.append(" where    iu.ID_EN_SSALUD            =  ? ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         <> ? ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(CADENA_ESTADOS);
			}
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			sbQuery.append(" AND      dp.COD_USUARIO_SNS         = u.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      dc.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      df.COD_USUARIO_SNS         = u.COD_USUARIO_SNS ");
			sbQuery.append(" AND      dcTit.cod_usuario_sns      = dc.COD_USUARIO_SNS_TITULAR  ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(idSsalud));
			parametros.put("2", Misc.nz(cod));
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
	
	
	public ArrayList <SnsBean> getInfoSnsByDni (AccesoBd accesoBd, String dni, String cod) throws Exception {
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
			sbQuery.append(" where    dp.DNI_NIE                 =  ? ");
			sbQuery.append(" AND      dp.COD_USUARIO_SNS         <> ? ");
			sbQuery.append(" AND      dc.COD_USUARIO_SNS         = dp.COD_USUARIO_SNS  ");
			sbQuery.append(" AND      u.COD_USUARIO_SNS          = dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				sbQuery.append(CADENA_ESTADOS);
			}
			sbQuery.append(" AND      df.COD_USUARIO_SNS         = dp.COD_USUARIO_SNS ");
			sbQuery.append(" AND      iu.COD_USUARIO_SNS         = dp.COD_USUARIO_SNS ");
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND      iu.cod_agente              = da.cod_agente ");
			sbQuery.append(" AND      dcTit.cod_usuario_sns      = dc.COD_USUARIO_SNS_TITULAR  ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(dni));
			parametros.put("2", Misc.nz(cod));
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
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitRaiz (AccesoBd accesoBd, String raiz, String nafTitular, String cod) throws Exception {
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
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         <> ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      dp.RAIZ                    like  ?  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			parametros.put("2", Misc.nz(cod));
			parametros.put("3", Misc.nz(raiz));
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
	
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitNombre (AccesoBd accesoBd, String nombre, String nafTitular, String fechaNac, String cod) throws Exception {
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
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS                 <>  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS                  =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      dp.NOMBRE                           =  ? ");
			//
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			parametros.put("2", Misc.nz(cod));
			parametros.put("3", Misc.nz(nombre));
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
	
	
	
	public ArrayList <SnsBean> getInfoSnsByRaizNombre (AccesoBd accesoBd, String raiz, String nombre, String fechaNac, String cod) throws Exception {
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
			stringBuffer.append(" AND      dp.COD_USUARIO_SNS        <>  ? ");
			stringBuffer.append(" AND      dp.NOMBRE                  =  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(cod));
			parametros.put("3", Misc.nz(nombre));
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
	
	
	
	public ArrayList <SnsBean> getInfoSnsByRaizApellidos (AccesoBd accesoBd, String raiz, String apellido1, String apellido2, String cod) throws Exception {
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
			stringBuffer.append(" AND      dp.COD_USUARIO_SNS        <>  ? ");
			stringBuffer.append(" AND      dp.APELLIDO1               =  ? ");
			stringBuffer.append(" AND      dp.APELLIDO2               =  ? ");
			//
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(cod));
			parametros.put("3", Misc.nz(apellido1));
			parametros.put("4", Misc.nz(apellido2));
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
	
	
	
	public ArrayList <SnsBean> getInfoSnsByOtros (AccesoBd accesoBd, String raiz, String apellido1, String cod) throws Exception {
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
			stringBuffer.append(" where    dp.RAIZ                    =  ? ");
			stringBuffer.append(" AND      dp.COD_USUARIO_SNS        <>  ? ");
			stringBuffer.append(" AND      dp.APELLIDO1               =  ? ");	
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			//String raizSubcadena = raiz.substring(0, 4) + "%";
			parametros.put("1", Misc.nz(raiz));
			parametros.put("2", Misc.nz(cod));
			parametros.put("3", Misc.nz(apellido1));
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
	
	
	public ArrayList <SnsBean> getInfoSnsByNafTitPrefijoRaiz (AccesoBd accesoBd, String raiz, String nafTitular, String cod) throws Exception {
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
			stringBuffer.append(" AND      dp.COD_USUARIO_SNS        <>  ? ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS  ");
			stringBuffer.append(" AND      dc.COD_USUARIO_SNS         <> DC.COD_USUARIO_SNS_TITULAR  ");
			stringBuffer.append(" AND      dp.RAIZ                    like  ?  ");
			stringBuffer.append(" AND      u.COD_USUARIO_SNS          =  dp.COD_USUARIO_SNS ");
			if(estadosSinErrorDuplicidad) {
				stringBuffer.append(CADENA_ESTADOS);
			}
			stringBuffer.append(" AND      iu.COD_USUARIO_SNS         =  dp.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      df.COD_USUARIO_SNS         =  u.COD_USUARIO_SNS ");
			stringBuffer.append(" AND      da.COD_PRESTACION_SERVICIO =  u.COD_PRESTACION_SERVICIO ");
			stringBuffer.append(" AND      iu.cod_agente              =  da.cod_agente ");
			stringBuffer.append(" AND      dcTit.cod_usuario_sns      =  dc.COD_USUARIO_SNS_TITULAR ");
			query = stringBuffer.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			parametros.put("2", Misc.nz(cod));
			String raiz2 = raiz.substring(0, 4) + "%";
			//logger.debug("raiz2: " + raiz2);
			parametros.put("3", Misc.nz(raiz2));
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
