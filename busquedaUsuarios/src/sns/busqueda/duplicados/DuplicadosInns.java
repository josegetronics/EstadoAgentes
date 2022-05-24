package sns.busqueda.duplicados;

import gasai.util.Misc;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.duplicados.DuplicadosBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class DuplicadosInns {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static final void main(String[] args) throws Exception {
		//
		try {
			logger.debug("Inicio");
			//
			String urlDestinoTxt = "D:/Procesos/duplicadosInss/ficheroDni_2.txt";
			//
			DuplicadosInns duplicadosInns = new DuplicadosInns ();
			duplicadosInns.getFile(urlDestinoTxt);
			//
			logger.debug("FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	public void getFile(String urlDestinoTxt) throws Exception {
		//
		AccesoBd   accesoBd = null;
		//
		try {
			logger.debug("Inicio");
			//
			accesoBd = new AccesoBd();
			//
			// DNI
			// NAF
			// DNI y NAF	
			//
			//this.getDniDuplicados(accesoBd, urlDestinoTxt);	
			//this.getNafDuplicados(accesoBd, urlDestinoTxt);
			this.getDniNafDuplicados(accesoBd, urlDestinoTxt);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		finally {
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
	  
	
	private void getDniDuplicados(AccesoBd accesoBd, String urlDestinoTxt) {
		//
		int contadorRegistros = 0;
		//
		String query = "";
		//
		FileWriter fileWriter = null;
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//
			fileWriter = new FileWriter(urlDestinoTxt);
			//		
			//
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select z.DNI_NIE "); 
			strBuffer.append(" from   Z_Z_Z_DNI_DUP_INSS z");
			strBuffer.append(" where  Z.TOTAL = 2");
			strBuffer.append(" and    not exists (");
			strBuffer.append("                     select 'x'");
			strBuffer.append("                     from   Z_Z_Z_DNI_NAF_DUP_INSS d");
			strBuffer.append("                     where  d.dni_nie = z.dni_nie");
			strBuffer.append("                   )");			
			strBuffer.append(" order by z.DNI_NIE "); 
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
				if (contadorRegistros % 1000 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				String dniNie = Misc.nz(resultSet.getString("DNI_NIE"));	
				//
				this.getInfoDniDuplicado(accesoBd, fileWriter, dniNie);				
			}
			logger.debug("FIN  Registros procesados: " + contadorRegistros);
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
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
		}
	}	
	
	
	private void getNafDuplicados(AccesoBd accesoBd, String urlDestinoTxt) {
		//
		int contadorRegistros = 0;
		//
		String query = "";
		//
		FileWriter fileWriter = null;
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//
			fileWriter = new FileWriter(urlDestinoTxt);
			//			
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select z.NAF "); 
			strBuffer.append(" from   Z_Z_Z_NAF_DUP_INSS z ");
			strBuffer.append(" where  Z.TOTAL = 2 ");
			strBuffer.append(" and    not exists ( ");
			strBuffer.append("                     select 'x' ");
			strBuffer.append("                     from   Z_Z_Z_DNI_NAF_DUP_INSS d ");
			strBuffer.append("                     where  d.NAF = z.NAF ");
			strBuffer.append("                   ) ");
			strBuffer.append(" order by z.NAF "); 
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
				if (contadorRegistros % 1000 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				String naf = Misc.nz(resultSet.getString("NAF"));	
				//
				this.getInfoNafDuplicado(accesoBd, fileWriter, naf);				
			}
			logger.debug("FIN  Registros procesados: " + contadorRegistros);
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
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
		}
	}	
	
	
	private void getDniNafDuplicados(AccesoBd accesoBd, String urlDestinoTxt) {
		//
		int contadorRegistros = 0;
		//
		String query = "";
		//
		FileWriter fileWriter = null;
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//
			fileWriter = new FileWriter(urlDestinoTxt);
			//		
			//
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select z.DNI_NIE, z.NAF "); 
			strBuffer.append(" from   Z_Z_Z_DNI_NAF_DUP_INSS z "); 
			strBuffer.append(" where  Z.TOTAL = 2 "); 
			strBuffer.append(" order by z.DNI_NIE "); 
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
				if (contadorRegistros % 1000 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				String dniNie = Misc.nz(resultSet.getString("DNI_NIE"));
				String naf    = Misc.nz(resultSet.getString("NAF"));
				//
				this.getInfoDniNafDuplicado(accesoBd, fileWriter, dniNie, naf);				
			}
			logger.debug("FIN  Registros procesados: " + contadorRegistros);
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
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
		}
	}	
	
	
	private void getInfoDniDuplicado(AccesoBd accesoBd, FileWriter fileWriter, String dniNie) throws Exception {
		//
		int contadorRegistros = 0;
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
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select   i.id_inss, S.CRITERIO_IDENTIFICACION_SNS CRITERIO,I.NAF, S.COD_USUARIO_SNS, i.DNI_NIE, I.NAF, I.NAF_SEC1, i.NOMBRE, i.APELLIDO1, i.APELLIDO2, i.SEXO, I.FECHA_NACIMIENTO_RAW    "); 
			strBuffer.append(" from     inss_tit i, inss_sns_tit s "); 
			strBuffer.append(" where    i.DNI_NIE  = ? "); 
			strBuffer.append(" and      i.COD_SITUACION = 'A'"); 
			strBuffer.append(" and      S.ID_INSS  = I.ID_INSS "); 
			strBuffer.append(" order by 1 "); 
			query = strBuffer.toString();
			//
			parametros.put("1", dniNie);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 100 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				DuplicadosBean duplicadosBean = new DuplicadosBean (resultSet);
				//
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(duplicadosBean.getNombre());
				consultaBean.setApellido1(duplicadosBean.getApellido1());
				consultaBean.setApellido2(duplicadosBean.getApellido2());
				consultaBean.setCodSexo(duplicadosBean.getCodSexo());
				consultaBean.setFechaNac(duplicadosBean.getFechaNac());		
				consultaBean.setDniNie(duplicadosBean.getDniNie());
				consultaBean.setNaf(duplicadosBean.getNaf());
				//
				///////////////////////////////////////////////////////////////////
				//
				DuplicadosBean duplicadosBeanComparar = null;
				//
				if(resultSet.next()) {
					duplicadosBeanComparar = new DuplicadosBean (resultSet);
					
				}
				else {
					logger.debug("ERROR: " + duplicadosBean.getDniNie());
					logger.debug(duplicadosBean.toString());
				}
				//
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(duplicadosBeanComparar.getNombre());
				consultaBeanComparar.setApellido1(duplicadosBeanComparar.getApellido1());
				consultaBeanComparar.setApellido2(duplicadosBeanComparar.getApellido2());
				consultaBeanComparar.setCodSexo(duplicadosBeanComparar.getCodSexo());
				consultaBeanComparar.setFechaNac(duplicadosBeanComparar.getFechaNac());
				consultaBeanComparar.setDniNie(duplicadosBeanComparar.getDniNie());
				consultaBeanComparar.setNaf(duplicadosBeanComparar.getNaf());
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
				//
				//if(busquedaAproxResultadoBean.getPorcentaje() > 60) {
					escribirFichero (fileWriter, duplicadosBean, duplicadosBeanComparar, busquedaAproxResultadoBean, "DNI_NIE");
				//}
			}
			//logger.debug("FIN  Registros procesados: " + contadorRegistros);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());

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
		}
	}	
	
	
	private void getInfoNafDuplicado (AccesoBd accesoBd, FileWriter fileWriter, String naf) {
		//
		int contadorRegistros = 0;
		//
		String query                       = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select   I.ID_INSS, S.CRITERIO_IDENTIFICACION_SNS CRITERIO,I.NAF, S.COD_USUARIO_SNS, i.DNI_NIE, I.NAF, I.NAF_SEC1, i.NOMBRE, i.APELLIDO1, i.APELLIDO2, i.SEXO, I.FECHA_NACIMIENTO_RAW "); 
			strBuffer.append(" from     inss_tit i, inss_sns_tit s "); 
			strBuffer.append(" where    i.naf           = ? ");    
			strBuffer.append(" and      i.COD_SITUACION = 'A' ");   
			strBuffer.append(" and      S.ID_INSS       = I.ID_INSS "); 
			strBuffer.append(" order by i.NAF    "); 
			query = strBuffer.toString();
			//
			parametros.put("1", naf);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 100 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				DuplicadosBean duplicadosBean = new DuplicadosBean (resultSet);
				//
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(duplicadosBean.getNombre());
				consultaBean.setApellido1(duplicadosBean.getApellido1());
				consultaBean.setApellido2(duplicadosBean.getApellido2());
				consultaBean.setCodSexo(duplicadosBean.getCodSexo());
				consultaBean.setFechaNac(duplicadosBean.getFechaNac());		
				consultaBean.setDniNie(duplicadosBean.getDniNie());
				consultaBean.setNaf(duplicadosBean.getNaf());
				//
				///////////////////////////////////////////////////////////////////
				//
				DuplicadosBean duplicadosBeanComparar = null;
				//
				if(resultSet.next()) {
					duplicadosBeanComparar = new DuplicadosBean (resultSet);
				}
				else {
					logger.debug("ERROR: " + duplicadosBean.getNaf());
					logger.debug(duplicadosBean.toString());
				}
				//
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(duplicadosBeanComparar.getNombre());
				consultaBeanComparar.setApellido1(duplicadosBeanComparar.getApellido1());
				consultaBeanComparar.setApellido2(duplicadosBeanComparar.getApellido2());
				consultaBeanComparar.setCodSexo(duplicadosBeanComparar.getCodSexo());
				consultaBeanComparar.setFechaNac(duplicadosBeanComparar.getFechaNac());
				consultaBeanComparar.setDniNie(duplicadosBeanComparar.getDniNie());
				consultaBeanComparar.setNaf(duplicadosBeanComparar.getNaf());
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
				//
				if(busquedaAproxResultadoBean.getPorcentaje() > 60) {
					escribirFichero (fileWriter, duplicadosBean, duplicadosBeanComparar, busquedaAproxResultadoBean, "NAF");
				}
			}
			//logger.debug("FIN  Registros procesados: " + contadorRegistros);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
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
		}
	}	
	
	
	private void getInfoDniNafDuplicado(AccesoBd accesoBd, FileWriter fileWriter, String dniNie, String naf) throws Exception {
		//
		int contadorRegistros = 0;
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
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select   i.id_inss, S.CRITERIO_IDENTIFICACION_SNS CRITERIO,I.NAF, S.COD_USUARIO_SNS, i.DNI_NIE, I.NAF, I.NAF_SEC1, i.NOMBRE, i.APELLIDO1, i.APELLIDO2, i.SEXO, I.FECHA_NACIMIENTO_RAW    "); 
			strBuffer.append(" from     inss_tit i, inss_sns_tit s "); 
			strBuffer.append(" where    i.DNI_NIE  = ? "); 
			strBuffer.append(" and      i.naf      = ? "); 
			strBuffer.append(" and      i.COD_SITUACION = 'A'"); 
			strBuffer.append(" and      S.ID_INSS  = I.ID_INSS "); 
			strBuffer.append(" order by 1 "); 
			query = strBuffer.toString();
			//
			parametros.put("1", dniNie);
			parametros.put("2", naf);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 100 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				DuplicadosBean duplicadosBean = new DuplicadosBean (resultSet);
				//
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(duplicadosBean.getNombre());
				consultaBean.setApellido1(duplicadosBean.getApellido1());
				consultaBean.setApellido2(duplicadosBean.getApellido2());
				consultaBean.setCodSexo(duplicadosBean.getCodSexo());
				consultaBean.setFechaNac(duplicadosBean.getFechaNac());		
				consultaBean.setDniNie(duplicadosBean.getDniNie());
				consultaBean.setNaf(duplicadosBean.getNaf());
				//
				///////////////////////////////////////////////////////////////////
				//
				DuplicadosBean duplicadosBeanComparar = null;
				//
				if(resultSet.next()) {
					duplicadosBeanComparar = new DuplicadosBean (resultSet);
					
				}
				else {
					logger.debug("ERROR: " + duplicadosBean.getDniNie());
					logger.debug(duplicadosBean.toString());
				}
				//
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(duplicadosBeanComparar.getNombre());
				consultaBeanComparar.setApellido1(duplicadosBeanComparar.getApellido1());
				consultaBeanComparar.setApellido2(duplicadosBeanComparar.getApellido2());
				consultaBeanComparar.setCodSexo(duplicadosBeanComparar.getCodSexo());
				consultaBeanComparar.setFechaNac(duplicadosBeanComparar.getFechaNac());
				consultaBeanComparar.setDniNie(duplicadosBeanComparar.getDniNie());
				consultaBeanComparar.setNaf(duplicadosBeanComparar.getNaf());
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
				//
				//if(busquedaAproxResultadoBean.getPorcentaje() > 60) {
					escribirFichero (fileWriter, duplicadosBean, duplicadosBeanComparar, busquedaAproxResultadoBean, "DNI_NIE#NAF");
				//}
			}
			//logger.debug("FIN  Registros procesados: " + contadorRegistros);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());

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
		}
	}	
	
	
	private void escribirFichero (FileWriter fileWriter, DuplicadosBean duplicadosBean, DuplicadosBean duplicadosBeanComparar, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoCampo) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			//
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			//
			StringBuffer str = new StringBuffer ();
			str.append(duplicadosBean.toString());
			str.append(duplicadosBeanComparar.toString());
			str.append(busquedaAproxResultadoBean.toString());
			str.append(tipoCampo + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append("\n");
			//
			fileWriter.write(str.toString());
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	

	
	
}
