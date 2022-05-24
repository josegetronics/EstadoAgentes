package sns.busqueda.duplicados;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.duplicados.EstudioDuplicadosBean;
import sns.comun.bean.duplicados.SnsBean;
import sns.comun.config.InicializacionBusqueda;


public class DuplicadosVinculados {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static final void main(String[] args) throws Exception {
		//
		try {
			logger.debug("Inicio");
			//
			String urlDestinoTxt = "D:/Procesos/duplicadosInss/ficheroEstudio22.txt";
			//
			DuplicadosVinculados duplicadosVinculados = new DuplicadosVinculados ();
			duplicadosVinculados.getFile(urlDestinoTxt);
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
			this.comprobarVinculados(accesoBd, urlDestinoTxt);	
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
	  
	
	private void comprobarVinculados(AccesoBd accesoBd, String urlDestinoTxt) {
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
			strBuffer.append(" select ID_INSS, ");
			strBuffer.append("        COD_USUARIO_SNS, ");  
			strBuffer.append("        NOMBRE,  ");
			strBuffer.append("        APELLIDO1,  ");
			strBuffer.append("        APELLIDO2,  ");
			strBuffer.append("        SEXO, "); 
			strBuffer.append("        FECHA_NAC,  ");
			strBuffer.append("        NAF, "); 
			strBuffer.append("        DNI_NIE  ");
			strBuffer.append(" from   Z_Z_Z_CRUCE_DUPLICADOS_INSS z  ");
			strBuffer.append(" where  Z.COD_USUARIO_SNS is not null ");
			strBuffer.append(" union all ");
			strBuffer.append(" select Z.ID_INSS_COMP        ID_INSS, ");
			strBuffer.append("        COD_USUARIO_SNS_COMP  COD_USUARIO_SNS,  "); 
			strBuffer.append("        NOMBRE_COMP           NOMBRE, "); 
			strBuffer.append("        APELLIDO1_COMP        APELLIDO1, "); 
			strBuffer.append("        APELLIDO2_COMP        APELLIDO2, "); 
			strBuffer.append("        SEXO_COMP             SEXO, "); 
			strBuffer.append("        FECHA_NAC_COMP        FECHA_NAC, "); 
			strBuffer.append("        NAF_COMP              NAF, "); 
			strBuffer.append("        DNI_NIE_COMP          DNI_NIE ");        
			strBuffer.append(" from   Z_Z_Z_CRUCE_DUPLICADOS_INSS z  ");
			strBuffer.append(" where  Z.COD_USUARIO_SNS_COMP is not null ");
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
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				EstudioDuplicadosBean estudioDuplicadosBean = new EstudioDuplicadosBean (resultSet);
				//
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(estudioDuplicadosBean.getNombre());
				consultaBean.setApellido1(estudioDuplicadosBean.getApellido1());
				consultaBean.setApellido2(estudioDuplicadosBean.getApellido2());
				consultaBean.setCodSexo(estudioDuplicadosBean.getCodSexo());
				consultaBean.setFechaNac(estudioDuplicadosBean.getFechaNac());		
				consultaBean.setDniNie(estudioDuplicadosBean.getDniNie());
				consultaBean.setNaf(estudioDuplicadosBean.getNaf());
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				//
				SnsBean snsBean = this.getInfoSns(accesoBd, estudioDuplicadosBean.getCodUsuariosSns());
				//
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(snsBean.getNombre());
				consultaBeanComparar.setApellido1(snsBean.getApellido1());
				consultaBeanComparar.setApellido2(snsBean.getApellido2());
				consultaBeanComparar.setCodSexo(snsBean.getCodSexo());
				consultaBeanComparar.setFechaNac(snsBean.getFechaNacimiento());
				consultaBeanComparar.setDniNie(snsBean.getDniNie());
				consultaBeanComparar.setNaf(snsBean.getNaf());
				//////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
				//			
				//
				escribirFichero (fileWriter, estudioDuplicadosBean, snsBean, busquedaAproxResultadoBean);
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
	
	
	private SnsBean getInfoSns(AccesoBd accesoBd, String codUsuarioSns) throws Exception {
		//
		SnsBean snsBean = null;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//		
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select U.COD_USUARIO_SNS,  "); 
			strBuffer.append("        U.COD_ESTADO, ");  
			strBuffer.append("        NOMBRE, ");  
			strBuffer.append("        APELLIDO1, ");  
			strBuffer.append("        APELLIDO2, ");  
			strBuffer.append("        COD_SEXO SEXO,  "); 
			strBuffer.append("        to_char(FECHA_NAC, 'yyyy-mm-dd') FECHA_NAC,  "); 
			strBuffer.append("        NAF,  "); 
			strBuffer.append("        DNI_NIE,  "); 
			strBuffer.append("        RAIZ,  "); 
			strBuffer.append("        PASAPORTE, "); 
			strBuffer.append("        (SELECT COD_CA_ISO FROM   snsalud.CA_PRESTACION_SERVICIO cps, snsalud.COMUNIDADES_AUTONOMAS ca WHERE  u.COD_PRESTACION_SERVICIO = cps.COD_PRESTACION_SERVICIO  AND cps.COD_COMUNIDAD = ca.COD_COMUNIDAD) CODCAISO "); 
			strBuffer.append(" from   usuarios u, datos_personales dp, datos_cobertura dc ");  
			strBuffer.append(" where  U.COD_USUARIO_SNS  = ? "); 
			strBuffer.append(" and    DP.COD_USUARIO_SNS = U.COD_USUARIO_SNS "); 
			strBuffer.append(" and    Dc.COD_USUARIO_SNS = U.COD_USUARIO_SNS "); 
			query = strBuffer.toString();
			//
			parametros.put("1", codUsuarioSns);
			//
			Vector vResp = accesoBd.consulta(query, parametros);
			if (!vResp.isEmpty()) {
				snsBean = new SnsBean((HashMap) vResp.elementAt(0));
			}
			//logger.debug("FIN  Registros procesados: " + contadorRegistros);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());

		}
		return snsBean;
	}	
	
		
	private void escribirFichero (FileWriter fileWriter, EstudioDuplicadosBean estudioDuplicadosBean, SnsBean snsBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
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
			str.append(estudioDuplicadosBean.toString());
			str.append(snsBean.toString());
			str.append(busquedaAproxResultadoBean.toString());
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
