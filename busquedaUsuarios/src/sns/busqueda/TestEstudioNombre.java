package sns.busqueda;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import gasai.util.Misc;
import org.apache.log4j.Logger;
import sns.busqueda.model.NombreHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.InicializacionBusqueda;


public class TestEstudioNombre {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//	
			String x = InicializacionBusqueda.APELLIDO2_REVISAR;
			
			
			String urlDestinoTxt = "D:/Procesos/EstudioNombre/2015-02-19/fichero.txt";			
			//
			//TestEstudioNombre testEstudioNombre = new TestEstudioNombre ();
			//testEstudioNombre.compararPalabras(urlDestinoTxt);
			
			
			
			
			NombreHelper nombreHelper = new NombreHelper ();			
			NombreComparacionBean nombreComparacionBean = nombreHelper.managerNombre("TRIANA", "ANA");
			//
			
			//
			logger.debug(nombreComparacionBean.write2());
			logger.debug(nombreComparacionBean.getRevision());
			//nombreComparacionBean.view();
			
			
			
			/*
			nombreComparacionBean = nombreHelper.managerNombre("ESTER", "ESTHER");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("OIANE", "OIHANE");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("AINHOA", "AYNHOA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MELANY", "MELANI");
			nombreComparacionBean.view();
			//
			nombreComparacionBean = nombreHelper.managerNombre("FELISA", "FELIPA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MARISA", "MARINA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("ISMAEL", "ISRAEL");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("ISABEL", "ISRAEL");
			nombreComparacionBean.view();
			
			///////////////////////////////////
			
			
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			
			nombreComparacionBean = nombreHelper.managerNombre("MARIA", "MARTA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MARTINA", "MIRANDA");
			nombreComparacionBean.view();
 			nombreComparacionBean = nombreHelper.managerNombre("MARIA", "MARINA");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("LAURA", "LARA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MARCO", "MAURO");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("MARCO", "MARIO");
			nombreComparacionBean.view();	
			
			
				
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");

					
			nombreComparacionBean = nombreHelper.managerNombre("ESPERANZA TRIANA", "ANA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("IAN", "JOAQUIN SEBASTIAN");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("ALI", "IBRAHIM EL KHALIL");
			nombreComparacionBean.view();			
 			nombreComparacionBean = nombreHelper.managerNombre("HIDAYA", "AYA");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("ALAE", "WALAE");
			nombreComparacionBean.view();
 			nombreComparacionBean = nombreHelper.managerNombre("MAITANE", "ANE");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("JOAQUIN SEBASTIAN", "IAN");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MOUHAMET", "AMET");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("ALIN", "MADALIN");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("MARIA VICTORIA", "MAR");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("ANA", "AITANA");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("ANA", "CAYETANA");
			nombreComparacionBean.view();
			
			
			
			
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");

			
			nombreComparacionBean = nombreHelper.managerNombre("FREDDIE", "CHRISTIAN FREDDIERICK");
			nombreComparacionBean.view();	
			nombreComparacionBean = nombreHelper.managerNombre("AULA", "PAULA");
			nombreComparacionBean.view();
			
 			nombreComparacionBean = nombreHelper.managerNombre("TERESITA", "TERESITA DEL NIÑO JESUS");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("MAGDA", "MAGDALENA");
			nombreComparacionBean.view();
 			nombreComparacionBean = nombreHelper.managerNombre("LUZ DIVINA", "LUDIVINA");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("SAMARA DEL CARMEN", "CARMEN");
			nombreComparacionBean.view();
			nombreComparacionBean = nombreHelper.managerNombre("M C", "MARI CARMEN");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("GUIMAR", "GUIOMAR");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("R AFRICA", "MARIA AFRICA");
			nombreComparacionBean.view();			
			nombreComparacionBean = nombreHelper.managerNombre("EL HASANIYA", "HASNA");
			nombreComparacionBean.view();
 			nombreComparacionBean = nombreHelper.managerNombre("ISSA", "ALI SABANE");
			nombreComparacionBean.view();			
				*/
			
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	
	
	private void compararPalabras (String urlDestinoTxt) throws Exception {
		//
		int contadorRegistros = 0;
		//
		FileWriter fileWriter = null;
		//
		String query = "";
		//
		AccesoBd   accesoBd = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		//
		try {
			logger.debug("INICIO");
			//
			fileWriter = new FileWriter(urlDestinoTxt);
			//
			accesoBd = new AccesoBd();
			//	
			NombreHelper nombreHelper = new NombreHelper ();
			//
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" SELECT   Z.NOMBRE, Z.NOMBRE_SNS, Z.COD_USUARIO_SNS ");
			strBuffer.append(" from     Z_Z_Z_BENEFICIARIOS_B_RES z, Z_Z_Z_BENS_RES_NOMBRES c ");
			strBuffer.append(" where    Z.COD_USUARIO_SNS   = C.COD_USUARIO_SNS   ");
			//strBuffer.append(" and      rownum   <  1001 ");
			//
			query = strBuffer.toString();
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 5000 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				//
				String nombreInss = Misc.nz(resultSet.getString("NOMBRE"));		
				String nombreSns  = Misc.nz(resultSet.getString("NOMBRE_SNS"));
				String codSns     = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
				//																	
				NombreComparacionBean nombreComparacionBean = nombreHelper.managerNombre(nombreInss, nombreSns);
				nombreComparacionBean.setCodigo(codSns);
				//
				fileWriter.write(nombreComparacionBean.write());
				fileWriter.flush();				
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		finally {			
			//
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e3) {
				resultSet = null;
			}
			//
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e4) {
				preparedStatement = null;
			}
			//
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (Exception e5) {
				fileWriter = null;
			}		
			//
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
		}
	}
	
	
	
	
	
	
}
