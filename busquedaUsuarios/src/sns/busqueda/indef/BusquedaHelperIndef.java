package sns.busqueda.indef;

import org.apache.log4j.Logger;
import sns.busqueda.model.IndefBdHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bd.AccesoBdIndef;
import sns.comun.bean.indef.IndefBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import gasai.util.Misc;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


public class BusquedaHelperIndef {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String path            = "D:/Procesos/BusquedaIndef/";
			//path            = "D:/Procesos/Andalucia/";= "solucionIvan.txt";
			String ficheroDestino  = "andalucia_2.txt";
			//
			BusquedaHelperIndef busquedaHelperIndef = new BusquedaHelperIndef ();
			busquedaHelperIndef.manager(path, ficheroDestino);
			
			/*
			AccesoBdIndef accesoBdIndef  = new AccesoBdIndef ();
			try {
				String dniNie = "31483573T";
				IndefBdHelper indefBdHelper = new IndefBdHelper ();
				boolean encontradoBaja =  indefBdHelper.getUsuarioInDefByDni (accesoBdIndef, dniNie);
				logger.debug("encontradoBaja: " + encontradoBaja);
			}
			catch (Exception e) {
			}
			finally {
				accesoBdIndef.cerrar();
			}
			*/
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
		//HashMap<String, String> parametros  = new HashMap<String, String>();
		//
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
			
			/*
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select COD_USUARIO_SNS, ID_EN_SSALUD, COD_ESTADO, DNI_NIE  "); 
            stringBuffer.append(" from   Z_Z_Z_Z_Z_Z_BAJA_ANDALUCIA z ");
            stringBuffer.append(" where  COD_ESTADO = 2 ");
            stringBuffer.append(" and    DNI_NIE    is not null ");
           	query = stringBuffer.toString();
			*/
           	
			StringBuffer stringBuffer = new StringBuffer();
           	stringBuffer.append(" SELECT Z.COD_USUARIO_SNS, Z.COD_ESTADO, DP.DNI_NIE ");
           	stringBuffer.append(" FROM   Z_Z_Z_Z_BEN_SNS_NO_ENC_2 Z, DATOS_PERSONALES DP ");
           	stringBuffer.append(" WHERE  DP.COD_USUARIO_SNS = Z.COD_USUARIO_SNS ");
           	stringBuffer.append(" AND    DP.DNI_NIE IS NOT NULL ");
           	stringBuffer.append(" AND    Z.COD_ESTADO = 0 ");
           	query = stringBuffer.toString();
			
			/*
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select   z.COD_USUARIO_SNS, dp.DNI_NIE, z.COD_ESTADO  "); 
            stringBuffer.append(" from     Z_Z_Z_Z_BEN_SNS_NO_ENC z, datos_personales dp  "); 
            stringBuffer.append(" where    Z.COD_USUARIO_SNS    = DP.COD_USUARIO_SNS  "); 
            stringBuffer.append(" and      DP.DNI_NIE           is not null  "); 
            stringBuffer.append(" order by 1 ");            
           	query = stringBuffer.toString();
           	*/
           	
            /*    
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select   z.COD_USUARIO_SNS, z.DNI_NIE, z.COD_ESTADO  "); 
            stringBuffer.append(" from     Z_Z_MAYORES_65_DESCART_IVAN z ");
            stringBuffer.append(" where    z.cod_estado = 0 ");
            stringBuffer.append(" order by 1, 2 ");            
           	query = stringBuffer.toString(); 
                       
                       
            stringBuffer.append(" select z.COD_USUARIO_SNS, z.DNI_NIE, U.COD_ESTADO   "); 
            stringBuffer.append(" from   Z_Z_MAYORES_65_DESCARTADOS Z, usuarios u    "); 
            stringBuffer.append(" where  Z.DNI_NIE         is not null  "); 
            stringBuffer.append(" and    Z.COD_USUARIO_SNS = U.COD_USUARIO_SNS  ");   
             
            stringBuffer.append(" select z.COD_USUARIO_SNS, z.DNI_NIE, U.COD_ESTADO "); 
           	stringBuffer.append(" from   Z_Z_CARGA_MAYORES_65 z, usuarios u  "); 
           	stringBuffer.append("  where  Z.DNI_NIE         is not null "); 
           	stringBuffer.append(" and    Z.COD_USUARIO_SNS = U.COD_USUARIO_SNS ");
           	*/ 
            //
            //stringBuffer.append(" SELECT  COD_USUARIO_SNS, ID, ESTADO, INDEF, DNI_NIE ");
            //stringBuffer.append(" FROM    Z_Z_COMPROBAR_RESUL_2  Z ");
						
			//
			//parametros.put("1", Misc.nz(""));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet 		   = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);					
				}		
				//
				String codUsuario  = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
				//String cip         = Misc.nz(resultSet.getString("ID_EN_SSALUD"));
				String estadoSns   = Misc.nz(resultSet.getInt("COD_ESTADO"));
				String dniNie      = Misc.nz(resultSet.getString("DNI_NIE"));
				//
				boolean encontradoBaja = false;
				// Se busca en el Indice nacional de defunciones
				//
				if(!Misc.esVacio(dniNie)){
					//
					IndefBean indefBean = indefBdHelper.getUsuarioInfoByDni(accesoBdIndef, dniNie);
					//
					if(indefBean !=null) {
						encontradoBaja	= true;
					}
				}
				if(encontradoBaja) {
					contadorBaja++;
				}				
				StringBuffer str = new StringBuffer ();
				str.append(codUsuario      + ConstantesBusqueda.SEPARADOR_CAMPOS);
				//str.append(cip             + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append(estadoSns       + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append(dniNie          + ConstantesBusqueda.SEPARADOR_CAMPOS);
				//
				str.append(encontradoBaja  + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append("\n");
				//
				fileWriter.write(str.toString());
				fileWriter.flush();
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
	
	
	public void manager2(String path, String ficheroDestino) throws Exception {
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
		//HashMap<String, String> parametros  = new HashMap<String, String>();
		//
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
            //stringBuffer.append(" select COD_USUARIO_SNS, DNI_NIE ");
            //stringBuffer.append(" from   Z_REVISION_MAYORES_65 c ");
           	//stringBuffer.append(" where  cod_estado = 0 ");
            //
           
            stringBuffer.append(" SELECT  z.COD_USUARIO_SNS, Z.CIP, Z.DNI_NIE ");
            stringBuffer.append(" FROM   Z_CRUCE_ARAGON_2 Z ");
			query = stringBuffer.toString();			
			//
			//parametros.put("1", Misc.nz(""));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet 		   = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
					fileWriter.flush();
					
				}		
				//
				String codUsuario  = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
				String cip         = Misc.nz(resultSet.getString("CIP"));
				String dniNie      = Misc.nz(resultSet.getString("DNI_NIE"));
				//
				boolean encontradoBaja = false;
				// Se busca en el Indice nacional de defunciones
				//
				if(!Misc.esVacio(dniNie)){
					encontradoBaja =  indefBdHelper.getUsuarioInDefByDni (accesoBdIndef, dniNie);
				}
				if(encontradoBaja) {
					contadorBaja++;
				}				
				StringBuffer str = new StringBuffer ();
				str.append(codUsuario      + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append(cip             + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append(dniNie          + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append(encontradoBaja  + ConstantesBusqueda.SEPARADOR_CAMPOS);
				str.append("\n");
				//
				fileWriter.write(str.toString());
			}	
			fileWriter.flush();
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
