package sns.busqueda.model;

import gasai.util.Misc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.InssBean;
import sns.comun.config.InicializacionBusqueda;


public class InssBdHelper {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public InssBean getInfoInssByCodUsuario (AccesoBd accesoBd, String codUsuario) throws Exception {
		//
		InssBean inssBean = null;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select i.ID_INSS, ");
			sbQuery.append("        COD_TIPO_ASEGURADO, ");
			sbQuery.append("        IPF, ");
			sbQuery.append("        DNI_NIE, ");
			sbQuery.append("        PASAPORTE, ");
			sbQuery.append("        NAF, ");
			sbQuery.append("        NAF_SEC1, ");
			sbQuery.append("        NAF_SEC2, ");
			sbQuery.append("        NAF_SEC3, ");
			sbQuery.append("        NAF_SEC4, ");
			sbQuery.append("        NAF_SEC5, ");
			sbQuery.append("        NAF_SEC6, ");
			sbQuery.append("        NAF_SEC7, ");
			sbQuery.append("        NAF_SEC8, ");
			sbQuery.append("        NAF_SEC9, ");
			sbQuery.append("        INDICATIVO_NOMBRE, ");
			sbQuery.append("        APELLIDOS_NOMBRE, ");
			sbQuery.append("        APELLIDO1, ");
			sbQuery.append("        APELLIDO2, ");
			sbQuery.append("        NOMBRE, ");
			sbQuery.append("        NACIONALIDAD, ");
			sbQuery.append("        FECHA_NACIMIENTO, ");
			sbQuery.append("        SEXO, ");
			sbQuery.append("        INDICATIVO_DOMICILIO, ");
			sbQuery.append("        DOMICILIO, ");
			sbQuery.append("        TIPO_ASEGURAMIENTO, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SITUACION, ");
			sbQuery.append("        TO_CHAR(FECHA_EFECTO_SITUACION, 'YYYY-MM-DD') FECHA_EFECTO_SITUACION, ");
			sbQuery.append("        COD_TIPO_BENEFICIARIO, ");
			sbQuery.append("        IPF_TITULAR, ");
			sbQuery.append("        NAF_TITULAR, ");
			sbQuery.append("        NUMERO_SECUENCIA, ");
			sbQuery.append("        FECHA_NACIMIENTO_RAW, ");
			sbQuery.append("        COD_TIPO_MOTIVO_BAJA, ");
			sbQuery.append("        S.COD_USUARIO_SNS, ");
			sbQuery.append("        S.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   inss_tit i, inss_sns_tit s ");
			//
			sbQuery.append(" where  S.COD_USUARIO_SNS = ? ");
			sbQuery.append(" and    S.ID_INSS         = I.ID_INSS ");
			sbQuery.append(" and    I.COD_SITUACION   = 'A' ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(codUsuario));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			Vector vResp = accesoBd.consulta(query, parametros);
			if (!vResp.isEmpty()) {
				inssBean = new InssBean((HashMap) vResp.elementAt(0));
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
		return inssBean;
	}
	
	
	public ArrayList <InssBean> getInfoInssByDni (AccesoBd accesoBd, String dni) throws Exception {
		//
		ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
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
			sbQuery.append(" select i.ID_INSS, ");
			sbQuery.append("        COD_TIPO_ASEGURADO, ");
			sbQuery.append("        IPF, ");
			sbQuery.append("        DNI_NIE, ");
			sbQuery.append("        PASAPORTE, ");
			sbQuery.append("        NAF, ");
			sbQuery.append("        NAF_SEC1, ");
			sbQuery.append("        NAF_SEC2, ");
			sbQuery.append("        NAF_SEC3, ");
			sbQuery.append("        NAF_SEC4, ");
			sbQuery.append("        NAF_SEC5, ");
			sbQuery.append("        NAF_SEC6, ");
			sbQuery.append("        NAF_SEC7, ");
			sbQuery.append("        NAF_SEC8, ");
			sbQuery.append("        NAF_SEC9, ");
			sbQuery.append("        INDICATIVO_NOMBRE, ");
			sbQuery.append("        APELLIDOS_NOMBRE, ");
			sbQuery.append("        APELLIDO1, ");
			sbQuery.append("        APELLIDO2, ");
			sbQuery.append("        NOMBRE, ");
			sbQuery.append("        NACIONALIDAD, ");
			sbQuery.append("        FECHA_NACIMIENTO, ");
			sbQuery.append("        SEXO, ");
			sbQuery.append("        INDICATIVO_DOMICILIO, ");
			sbQuery.append("        DOMICILIO, ");
			sbQuery.append("        TIPO_ASEGURAMIENTO, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SITUACION, ");
			sbQuery.append("        TO_CHAR(FECHA_EFECTO_SITUACION, 'YYYY-MM-DD') FECHA_EFECTO_SITUACION, ");
			sbQuery.append("        COD_TIPO_BENEFICIARIO, ");
			sbQuery.append("        IPF_TITULAR, ");
			sbQuery.append("        NAF_TITULAR, ");
			sbQuery.append("        NUMERO_SECUENCIA, ");
			sbQuery.append("        FECHA_NACIMIENTO_RAW, ");
			sbQuery.append("        COD_TIPO_MOTIVO_BAJA, ");
			sbQuery.append("        S.COD_USUARIO_SNS, ");
			sbQuery.append("        S.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   inss_tit i, inss_sns_tit s ");
			//
			sbQuery.append(" where I.DNI_NIE       = ? ");
			sbQuery.append(" and   I.COD_SITUACION = 'A' ");
			sbQuery.append(" and   S.ID_INSS       = I.ID_INSS ");
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
				InssBean inssBean = new InssBean (resultSet);
				arrayList.add(inssBean);
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
	
	
	
	public ArrayList <InssBean> getInfoInssByNaf (AccesoBd accesoBd, String naf) throws Exception {
		//
		ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
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
			sbQuery.append(" select i.ID_INSS, ");
			sbQuery.append("        COD_TIPO_ASEGURADO, ");
			sbQuery.append("        IPF, ");
			sbQuery.append("        DNI_NIE, ");
			sbQuery.append("        PASAPORTE, ");
			sbQuery.append("        NAF, ");
			sbQuery.append("        NAF_SEC1, ");
			sbQuery.append("        NAF_SEC2, ");
			sbQuery.append("        NAF_SEC3, ");
			sbQuery.append("        NAF_SEC4, ");
			sbQuery.append("        NAF_SEC5, ");
			sbQuery.append("        NAF_SEC6, ");
			sbQuery.append("        NAF_SEC7, ");
			sbQuery.append("        NAF_SEC8, ");
			sbQuery.append("        NAF_SEC9, ");
			sbQuery.append("        INDICATIVO_NOMBRE, ");
			sbQuery.append("        APELLIDOS_NOMBRE, ");
			sbQuery.append("        APELLIDO1, ");
			sbQuery.append("        APELLIDO2, ");
			sbQuery.append("        NOMBRE, ");
			sbQuery.append("        NACIONALIDAD, ");
			sbQuery.append("        FECHA_NACIMIENTO, ");
			sbQuery.append("        SEXO, ");
			sbQuery.append("        INDICATIVO_DOMICILIO, ");
			sbQuery.append("        DOMICILIO, ");
			sbQuery.append("        TIPO_ASEGURAMIENTO, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SITUACION, ");
			sbQuery.append("        TO_CHAR(FECHA_EFECTO_SITUACION, 'YYYY-MM-DD') FECHA_EFECTO_SITUACION, ");
			sbQuery.append("        COD_TIPO_BENEFICIARIO, ");
			sbQuery.append("        IPF_TITULAR, ");
			sbQuery.append("        NAF_TITULAR, ");
			sbQuery.append("        NUMERO_SECUENCIA, ");
			sbQuery.append("        FECHA_NACIMIENTO_RAW, ");
			sbQuery.append("        COD_TIPO_MOTIVO_BAJA, ");
			sbQuery.append("        S.COD_USUARIO_SNS, ");
			sbQuery.append("        S.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   inss_tit i, inss_sns_tit s ");
			//
			sbQuery.append(" where I.naf             = ? ");
			sbQuery.append(" and   I.COD_SITUACION   = 'A' ");
			sbQuery.append(" and   S.ID_INSS         = I.ID_INSS ");
			
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(naf));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			//
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				InssBean inssBean = new InssBean (resultSet);
				arrayList.add(inssBean);
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
	
	
	public ArrayList <InssBean> getInfoInssByNafTit (AccesoBd accesoBd, String nafTitular) throws Exception {
		//
		ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
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
			sbQuery.append(" select i.ID_INSS, ");
			sbQuery.append("        COD_TIPO_ASEGURADO, ");
			sbQuery.append("        IPF, ");
			sbQuery.append("        DNI_NIE, ");
			sbQuery.append("        PASAPORTE, ");
			sbQuery.append("        NAF, ");
			sbQuery.append("        NAF_SEC1, ");
			sbQuery.append("        NAF_SEC2, ");
			sbQuery.append("        NAF_SEC3, ");
			sbQuery.append("        NAF_SEC4, ");
			sbQuery.append("        NAF_SEC5, ");
			sbQuery.append("        NAF_SEC6, ");
			sbQuery.append("        NAF_SEC7, ");
			sbQuery.append("        NAF_SEC8, ");
			sbQuery.append("        NAF_SEC9, ");
			sbQuery.append("        INDICATIVO_NOMBRE, ");
			sbQuery.append("        APELLIDOS_NOMBRE, ");
			sbQuery.append("        APELLIDO1, ");
			sbQuery.append("        APELLIDO2, ");
			sbQuery.append("        NOMBRE, ");
			sbQuery.append("        NACIONALIDAD, ");
			sbQuery.append("        FECHA_NACIMIENTO, ");
			sbQuery.append("        SEXO, ");
			sbQuery.append("        INDICATIVO_DOMICILIO, ");
			sbQuery.append("        DOMICILIO, ");
			sbQuery.append("        TIPO_ASEGURAMIENTO, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SITUACION, ");
			sbQuery.append("        TO_CHAR(FECHA_EFECTO_SITUACION, 'YYYY-MM-DD') FECHA_EFECTO_SITUACION, ");
			sbQuery.append("        COD_TIPO_BENEFICIARIO, ");
			sbQuery.append("        IPF_TITULAR, ");
			sbQuery.append("        NAF_TITULAR, ");
			sbQuery.append("        NUMERO_SECUENCIA, ");
			sbQuery.append("        FECHA_NACIMIENTO_RAW, ");
			sbQuery.append("        COD_TIPO_MOTIVO_BAJA, ");
			sbQuery.append("        S.COD_USUARIO_SNS, ");
			sbQuery.append("        S.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   inss_tit i, inss_sns_tit s ");
			//
			sbQuery.append(" where I.naf             = ? ");
			sbQuery.append(" and   I.COD_SITUACION   = 'A' ");
			sbQuery.append(" and   S.ID_INSS         = I.ID_INSS ");
			
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(nafTitular));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			//
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				InssBean inssBean = new InssBean (resultSet);
				arrayList.add(inssBean);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList <InssBean> getInfoInssByNafSec (AccesoBd accesoBd, String naf) throws Exception {
		//
		ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
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
			sbQuery.append(" select i.ID_INSS, ");
			sbQuery.append("        COD_TIPO_ASEGURADO, ");
			sbQuery.append("        IPF, ");
			sbQuery.append("        DNI_NIE, ");
			sbQuery.append("        PASAPORTE, ");
			sbQuery.append("        NAF, ");
			sbQuery.append("        NAF_SEC1, ");
			sbQuery.append("        NAF_SEC2, ");
			sbQuery.append("        NAF_SEC3, ");
			sbQuery.append("        NAF_SEC4, ");
			sbQuery.append("        NAF_SEC5, ");
			sbQuery.append("        NAF_SEC6, ");
			sbQuery.append("        NAF_SEC7, ");
			sbQuery.append("        NAF_SEC8, ");
			sbQuery.append("        NAF_SEC9, ");
			sbQuery.append("        INDICATIVO_NOMBRE, ");
			sbQuery.append("        APELLIDOS_NOMBRE, ");
			sbQuery.append("        APELLIDO1, ");
			sbQuery.append("        APELLIDO2, ");
			sbQuery.append("        NOMBRE, ");
			sbQuery.append("        NACIONALIDAD, ");
			sbQuery.append("        FECHA_NACIMIENTO, ");
			sbQuery.append("        SEXO, ");
			sbQuery.append("        INDICATIVO_DOMICILIO, ");
			sbQuery.append("        DOMICILIO, ");
			sbQuery.append("        TIPO_ASEGURAMIENTO, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SITUACION, ");
			sbQuery.append("        TO_CHAR(FECHA_EFECTO_SITUACION, 'YYYY-MM-DD') FECHA_EFECTO_SITUACION, ");
			sbQuery.append("        COD_TIPO_BENEFICIARIO, ");
			sbQuery.append("        IPF_TITULAR, ");
			sbQuery.append("        NAF_TITULAR, ");
			sbQuery.append("        NUMERO_SECUENCIA, ");
			sbQuery.append("        FECHA_NACIMIENTO_RAW, ");
			sbQuery.append("        COD_TIPO_MOTIVO_BAJA, ");
			sbQuery.append("        S.COD_USUARIO_SNS, ");
			sbQuery.append("        S.CRITERIO_IDENTIFICACION_SNS ");
			sbQuery.append(" from   inss_tit i, inss_sns_tit s ");
			//
			sbQuery.append(" where  (i.NAF_SEC1       = ? OR i.NAF_SEC2 = ? OR i.NAF_SEC3 = ? OR i.NAF_SEC4 = ? OR i.NAF_SEC5 = ? OR i.NAF_SEC6 = ? OR i.NAF_SEC7 = ? OR i.NAF_SEC8 = ? OR i.NAF_SEC9 = ?) ");
			sbQuery.append(" and    S.ID_INSS         = I.ID_INSS ");
			sbQuery.append(" and    I.COD_SITUACION   = 'A' ");
			query = sbQuery.toString();			
			//
			parametros.put("1", Misc.nz(naf));
			parametros.put("2", Misc.nz(naf));
			parametros.put("3", Misc.nz(naf));
			parametros.put("4", Misc.nz(naf));
			parametros.put("5", Misc.nz(naf));
			parametros.put("6", Misc.nz(naf));
			parametros.put("7", Misc.nz(naf));
			parametros.put("8", Misc.nz(naf));
			parametros.put("9", Misc.nz(naf));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				InssBean inssBean = new InssBean (resultSet);
				arrayList.add(inssBean);
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
