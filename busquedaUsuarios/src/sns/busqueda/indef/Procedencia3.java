package sns.busqueda.indef;

import gasai.util.Misc;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.busqueda.model.IndefBdHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bd.AccesoBdIndef;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.indef.IndefBean;
import sns.comun.config.InicializacionBusqueda;

public class Procedencia3 {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String path            = "D:/Procesos/Procedencia3/";
			//
			String ficheroDestino  = "procedencia.txt";
			//
			Procedencia3 procedencia3 = new Procedencia3 ();
			procedencia3.manager(path, ficheroDestino);			
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	
	public void manager(String path, String ficheroDestino) throws Exception {
		//
		int contadorRegistros               = 0;
		int contadorBaja                    = 0;
		//
		AccesoBd accesoBd                   = null;
		AccesoBdIndef accesoBdIndef         = null;
		//
		FileWriter fileWriter               = null;
		//
		String query                        = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet                 = null;
		//
		try {
			logger.debug("INICIO");
			//
			IndefBdHelper indefBdHelper = new IndefBdHelper ();
			//
			accesoBd       = new AccesoBd();
			accesoBdIndef  = new AccesoBdIndef ();
			//
			fileWriter     = new FileWriter(path + ficheroDestino);
			//
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select COD_USUARIO_SNS, DNI_NIE, NOMBRE, APELLIDO1, APELLIDO2, COD_SEXO, FECHA_NAC ");
            stringBuffer.append(" from   Z_Z_Z_PROCEDENCIA_3 z ");
            stringBuffer.append(" where  z.DNI_NIE is not null ");
            //stringBuffer.append(" and    rownum < 11 ");
           	query = stringBuffer.toString();
           	//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet 		   = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				if (contadorRegistros % 5000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
					fileWriter.flush();
				}		
				//
				String codUsuario  = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
				String dniNie      = Misc.nz(resultSet.getString("DNI_NIE"));				
				String nombre      = Misc.nz(resultSet.getString("NOMBRE"));
				String apellido1   = Misc.nz(resultSet.getString("APELLIDO1"));
				String apellido2   = Misc.nz(resultSet.getString("APELLIDO2"));
				String sexo        = Misc.nz(resultSet.getString("COD_SEXO"));
				String fechaNac    = Misc.nz(resultSet.getString("FECHA_NAC"));
				//				
				ConsultaBean consultaBean = new ConsultaBean (nombre, apellido1, apellido2, sexo, fechaNac, dniNie, "");
				//		
				// Se busca en el Indice nacional de defunciones
				if(!Misc.esVacio(dniNie)){
					IndefBean indefBean = indefBdHelper.getUsuarioInfoByDni (accesoBdIndef, dniNie);
					//	
					if(indefBean != null) {
						ConsultaBean consultaBeanComparar = new ConsultaBean ();
						consultaBeanComparar.setNombre(indefBean.getNombre());
						consultaBeanComparar.setApellido1(indefBean.getApellido1());
						consultaBeanComparar.setApellido2(indefBean.getApellido2());
						consultaBeanComparar.setCodSexo(indefBean.getCodSexo());
						consultaBeanComparar.setFechaNac(indefBean.getFechaNacimiento());
						consultaBeanComparar.setDniNie(indefBean.getDniNie());
						consultaBeanComparar.setNaf("");
						//
						BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
						BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
						//				
						StringBuffer str = new StringBuffer ();
						str.append(codUsuario + "|");
						str.append(consultaBean.write());
						str.append(consultaBeanComparar.write());
						str.append(busquedaAproxResultadoBean.toString());
						str.append("\n");
						//
						fileWriter.write(str.toString());
						fileWriter.flush();
					}
				}
			}	
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			e.printStackTrace();
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
				if (accesoBdIndef != null) {
					accesoBdIndef.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBdIndef = null;
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
