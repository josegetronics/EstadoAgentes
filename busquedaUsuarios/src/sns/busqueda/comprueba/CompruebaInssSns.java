package sns.busqueda.comprueba;

import gasai.util.Misc;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.comun.bd.AccesoBdCruces;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.duplicados.EstudioDuplicadosBean;
import sns.comun.bean.duplicados.SnsBean;
import sns.comun.config.InicializacionBusqueda;


public class CompruebaInssSns {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static final void main(String[] args) throws Exception {
		//
		try {
			logger.debug("Inicio");
			//
			String urlDestinoTxt = "D:/Procesos/BusquedaAproximacion/2014-03-12  Pais Vasco - SinNaf/comprueba/fichero.txt";
			//
			CompruebaInssSns compruebaInssSns = new CompruebaInssSns ();
			compruebaInssSns.getFile(urlDestinoTxt);
			//
			logger.debug("FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	public void getFile(String urlDestinoTxt) throws Exception {
		//
		AccesoBdCruces  accesoBd = null;
		//
		try {
			logger.debug("Inicio");
			//
			accesoBd = new AccesoBdCruces();
			//
			this.comprobarInssSns(accesoBd, urlDestinoTxt);	
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
	  
	
	private void comprobarInssSns (AccesoBdCruces accesoBd, String urlDestinoTxt) {
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
			strBuffer.append(" select      COD_USUARIO_SNS_BUS, ");   
			strBuffer.append("             C.NOMBRE_BUS,   ");
			strBuffer.append("             C.APELLIDO1_BUS,   ");
			strBuffer.append("             C.APELLIDO2_BUS, ");  
			strBuffer.append("             C.cod_SEXO_BUS,   ");
			strBuffer.append("             to_char(C.FECHA_NAC_BUS, 'yyyy-mm-dd') FECHA_NAC,   ");
			strBuffer.append("             C.NAF_BUS,   ");
			strBuffer.append("             C.DNI_NIE_BUS,  ");
			strBuffer.append("             c.ID_INSS,  "); 
			strBuffer.append("             C.NOMBRE_INSS,   ");
			strBuffer.append("             C.APELLIDO1_INSS,   ");
			strBuffer.append("             C.APELLIDO2_INSS,   ");
			strBuffer.append("             C.SEXO_INSS,  "); 
			strBuffer.append("             C.FECHA_NACIMIENTO_RAW, ");  
			strBuffer.append("             C.NAF_INSS,   ");
			strBuffer.append("             C.DNI_NIE_INSS   ");
			strBuffer.append(" FROM        CRUCE_CA_SNS_INSS_SINNAF c ");
			strBuffer.append(" where       tiporesultado                  = 'NO_COINCIDENTES_BA'   ");               
			strBuffer.append(" and         tipocampo                      = 'DNI_NIE'  ");                     
			strBuffer.append(" and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'  ");                                                         
			strBuffer.append(" and         C.CRITERIO_IDENTIFICACION_SNS  is not null  "); 
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
				//
				String codUsuarioSns  = Misc.nz(resultSet.getString("COD_USUARIO_SNS_BUS"));
				String idInss         = Misc.nz(resultSet.getString("ID_INSS"));
				//
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(Misc.nz(resultSet.getString("NOMBRE_BUS")));
				consultaBean.setApellido1(Misc.nz(resultSet.getString("APELLIDO1_BUS")));
				consultaBean.setApellido2(Misc.nz(resultSet.getString("APELLIDO2_BUS")));
				consultaBean.setCodSexo(Misc.nz(resultSet.getString("cod_SEXO_BUS")));
				consultaBean.setFechaNac(Misc.nz(resultSet.getString("FECHA_NAC")));		
				consultaBean.setDniNie(Misc.nz(resultSet.getString("DNI_NIE_BUS")));
				consultaBean.setNaf(Misc.nz(resultSet.getString("NAF_BUS")));
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(Misc.nz(resultSet.getString("NOMBRE_INSS")));
				consultaBeanComparar.setApellido1(Misc.nz(resultSet.getString("APELLIDO1_INSS")));
				consultaBeanComparar.setApellido2(Misc.nz(resultSet.getString("APELLIDO2_INSS")));
				consultaBeanComparar.setCodSexo(Misc.nz(resultSet.getString("SEXO_INSS")));
				consultaBeanComparar.setFechaNac(Misc.nz(resultSet.getString("FECHA_NACIMIENTO_RAW")));
				consultaBeanComparar.setDniNie(Misc.nz(resultSet.getString("DNI_NIE_INSS")));
				consultaBeanComparar.setNaf(Misc.nz(resultSet.getString("NAF_INSS")));
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);	
				//
				StringBuffer str = new StringBuffer ();
				str.append(codUsuarioSns                          + "|");
				str.append(consultaBean.getNombre()               + "|");
				str.append(consultaBean.getApellido1()            + "|");
				str.append(consultaBean.getApellido2()            + "|");
				str.append(consultaBean.getCodSexo()              + "|");
				str.append(consultaBean.getFechaNac()             + "|");
				str.append(consultaBean.getDniNie()               + "|");
				str.append(consultaBean.getNaf()                  + "|");
				//
				str.append(idInss                                 + "|");
				str.append(consultaBeanComparar.getNombre()       + "|");
				str.append(consultaBeanComparar.getApellido1()    + "|");
				str.append(consultaBeanComparar.getApellido2()    + "|");
				str.append(consultaBeanComparar.getCodSexo()      + "|");
				str.append(consultaBeanComparar.getFechaNac()     + "|");
				str.append(consultaBeanComparar.getDniNie()       + "|");
				str.append(consultaBeanComparar.getNaf()          + "|");
				str.append(busquedaAproxResultadoBean.toString());
				str.append("\n");
				//			
				fileWriter.write(str.toString());
				fileWriter.flush();
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
	
	
}
