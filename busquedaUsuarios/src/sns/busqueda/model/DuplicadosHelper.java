package sns.busqueda.model;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bd.AccesoBdConsulta;
import sns.comun.bean.RegistroCompletoBean;
import sns.comun.config.InicializacionBusqueda;


public class DuplicadosHelper {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public void managerBBDD(String path, String ficheroDestino) throws Exception {
		//
		int contadorRegistros = 0;
		//
		AccesoBd accesoBd                 = null;
		AccesoBdConsulta accesoBdConsulta = null;
		//
		FileWriter fileWriter          = null;
		//
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd         = new AccesoBd();
			accesoBdConsulta = new AccesoBdConsulta();
			//
			fileWriter     = new FileWriter(path + ficheroDestino);
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT  COD_USUARIO_SNS, ");
			sbQuery.append("         DNI_NIE, ");
			sbQuery.append("         DNIDUP, ");
			sbQuery.append("         PASAPORTE, ");
			sbQuery.append("         NAF, ");
			sbQuery.append("         NAF_TITULAR, ");
			sbQuery.append("         NOMBRE, ");
			sbQuery.append("         APELLIDO1, ");
			sbQuery.append("         APELLIDO2, ");
			sbQuery.append("         SEXO, ");
			sbQuery.append("         FECHA_NAC, ");
			sbQuery.append("         ID_SSALUD, ");
			sbQuery.append("         EXTRANJERO, ");
			sbQuery.append("         FECHA_ALTA, ");
			sbQuery.append("         FECHA_ULT_MOD, ");
			sbQuery.append("         PAIS_NACIMIENTO, ");
			sbQuery.append("         CA_NAC, ");
			sbQuery.append("         COD_NACIONALIDAD, ");
			sbQuery.append("         COD_USUARIO_SNS_BUS, ");
			sbQuery.append("         COD_ESTADO_BUS, ");
			sbQuery.append("         ID_EN_SSALUD_BUS, ");
			sbQuery.append("         COD_CA_ISO_BUS, ");
			sbQuery.append("         NOMBRE_BUS, ");
			sbQuery.append("         APELLIDO1_BUS, ");
			sbQuery.append("         APELLIDO2_BUS, ");
			sbQuery.append("         COD_SEXO_BUS, ");
			sbQuery.append("         FECHA_NAC_BUS, ");
			sbQuery.append("         RAIZ_BUS, ");
			sbQuery.append("         DNI_NIE_BUS, ");
			sbQuery.append("         PASAPORTE_BUS, ");
			sbQuery.append("         NAF_BUS, ");
			sbQuery.append("         NAF_TITULAR_BUS, ");
			sbQuery.append("         COD_TITULO_BUS, ");
			sbQuery.append("         COD_SITUACION_BUS, ");
			sbQuery.append("         COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("         COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("         COD_TIPO_PROCEDENCIA ");
			sbQuery.append(" FROM     CRUCE_PAIS_VASCO_NUEVO c ");
			sbQuery.append(" WHERE    C.TIPORESULTADO = 'DUPLICADOS_ESTUDIO' ");
			sbQuery.append(" and      C.TIPOCAMPO     = 'DNI_NIE' ");
			sbQuery.append(" ORDER BY C.DNI_NIE ");
			query = sbQuery.toString();			
			//
			//parametros.put("1", Misc.nz(""));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBdConsulta.consultaRaw(query);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				contadorRegistros++;
				RegistroCompletoBean registroCompletoBean  = new RegistroCompletoBean (resultSet);
				logger.debug(registroCompletoBean.view());
			}
			//
			logger.debug("contadorRegistros: " + contadorRegistros);
			//
			//this.verResultados (mapCamposBusqueda);
			//
			logger.debug("FIN");
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
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
			try {
				if (accesoBdConsulta != null) {
					accesoBdConsulta.cerrar();
				}
			}
			catch (Exception e2) {
				accesoBdConsulta = null;
			}
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			}
			catch (Exception e4) {
				fileWriter = null;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
