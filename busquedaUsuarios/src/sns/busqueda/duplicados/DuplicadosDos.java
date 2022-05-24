package sns.busqueda.duplicados;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.DatosLecturaBean;
import sns.comun.config.InicializacionBusqueda;


public class DuplicadosDos {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public void comprobarDuplicadosDos(String urlPath) {
		//
		int contadorRegistros = 0;
		//
		FileWriter fileWriter = null;
		FileWriter fileWriter2 = null;
		//
		AccesoBd   accesoBd = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "";
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd = new AccesoBd();
			//
			fileWriter  = new FileWriter(urlPath + "resultados.txt");
			fileWriter2 = new FileWriter(urlPath + "registros.txt");
			//			
			StringBuffer strBuffer=new StringBuffer();
			//
			DuplicadosDeMasDeDos duplicadosDeMasDeDos = new DuplicadosDeMasDeDos ();
			//
			/*
			// ID_SSALUD
			strBuffer.append(" select   D.COD_USUARIO_SNS,");    
			strBuffer.append("          D.COD_ESTADO,     ");     
			strBuffer.append("          d.DNI_NIE,        ");     
			strBuffer.append("          D.PASAPORTE,      ");     
			strBuffer.append("          d.naf,            ");     
			strBuffer.append("          d.NAF_TITULAR,    ");     
			strBuffer.append("          d.NOMBRE,         ");      
			strBuffer.append("          d.APELLIDO1,      ");     
			strBuffer.append("          d.APELLIDO2,      ");     
			strBuffer.append("          D.SEXO,           ");     
			strBuffer.append("          D.FECHA_NACIMIENTO_RAW, ");
			strBuffer.append("          D.ID_EN_SSALUD, ");
			strBuffer.append("          D.cod_agente, ");
			strBuffer.append("          D.cod_estado ");
			strBuffer.append(" from     Y_Y_Y_Y_Y_DUP_IDSSALUD_DATOS d, Y_Y_Y_Y_Y_DUP_IDSSALUD_COD c, Y_Y_Y_Y_Y_DUP_IDSSALUD i");
			strBuffer.append(" where    I.TOTAL           = 2");
			strBuffer.append(" and      I.ID_EN_SSALUD    = C.ID_EN_SSALUD");
			strBuffer.append(" and      I.COD_AGENTE      = C.COD_AGENTE");
			strBuffer.append(" and      C.COD_USUARIO_SNS = d.COD_USUARIO_SNS");
			strBuffer.append(" order by ID_EN_SSALUD"); 
			*/
			
			// DNI_NIE
			/*
			strBuffer.append(" select   D.COD_USUARIO_SNS,  ");
			strBuffer.append("          D.COD_ESTADO,  ");
			strBuffer.append("          d.DNI_NIE,  ");
			strBuffer.append("          D.PASAPORTE,  ");
			strBuffer.append("          d.naf,  ");
			strBuffer.append("          d.NAF_TITULAR, ");
			strBuffer.append("          d.NOMBRE,  ");
			strBuffer.append("          d.APELLIDO1,  ");
			strBuffer.append("          d.APELLIDO2,  ");
			strBuffer.append("          D.SEXO,  ");
			strBuffer.append("          D.FECHA_NACIMIENTO_RAW, ");
			strBuffer.append("          '' ID_EN_SSALUD, ");
			strBuffer.append("          '' COD_AGENTE ");
			strBuffer.append(" FROM     Y_Y_Y_Y_Y_DUP_DNI_DATOS D, Y_Y_Y_Y_Y_DUP_DNI DD, Y_Y_Y_Y_Y_DUP_DNI_COD c ");
			strBuffer.append(" where    DD.TOTAL            = 2 ");
			strBuffer.append(" and      DD.DNI_NIE          = D.DNI_NIE ");
			strBuffer.append(" AND      C.COD_USUARIO_SNS   = D.COD_USUARIO_SNS ");
			strBuffer.append(" AND      D.COD_ESTADO IN    (0, 1, 7) ");	
			strBuffer.append(" AND      exists ( ");                   
			strBuffer.append("                             select   D1.DNI_NIE ");
			strBuffer.append("                             FROM     Y_Y_Y_Y_Y_DUP_DNI_DATOS D1, Y_Y_Y_Y_Y_DUP_DNI DD1, Y_Y_Y_Y_Y_DUP_DNI_COD c1 ");
			strBuffer.append("                             where    DD1.TOTAL            = 2 ");
			strBuffer.append("                             and      DD1.DNI_NIE          = D1.DNI_NIE ");
			strBuffer.append("                             AND      C1.COD_USUARIO_SNS   = D1.COD_USUARIO_SNS ");
			strBuffer.append("                             AND      D1.COD_ESTADO IN    (0, 1, 7) ");
			strBuffer.append("                             and      D.DNI_NIE            = d1.dni_nie ");
			strBuffer.append("                             GROUP BY D1.DNI_NIE ");
			strBuffer.append("                             HAVING   COUNT(*)=2 ");
			strBuffer.append("                          )  ");
			strBuffer.append(" and     not exists( ");
			strBuffer.append("       			  select 'X' ");
			strBuffer.append("     				  from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");
			strBuffer.append("         			  where  Z.COD_USUARIO_SNS = c.COD_USUARIO_SNS ");
			strBuffer.append("       			 )    ");
			//strBuffer.append(" and  rownum < 2001 ");
			strBuffer.append(" ORDER BY DD.DNI_NIE ");
			*/
			
			/*
			// NAF TITULAR + RAIZ 
			strBuffer.append(" select D.COD_USUARIO_SNS,  ");
			strBuffer.append("         D.COD_ESTADO,  ");
			strBuffer.append("         d.DNI_NIE,  ");
			strBuffer.append("         D.PASAPORTE,  ");
			strBuffer.append("         d.naf,  ");
			strBuffer.append("         d.NAF_TITULAR, ");
			strBuffer.append("         d.NOMBRE,  ");
			strBuffer.append("         d.APELLIDO1,  ");
			strBuffer.append("         d.APELLIDO2,  ");
		    strBuffer.append("         D.SEXO,  ");
		    strBuffer.append("         D.RAIZ ID_EN_SSALUD,  ");
		    strBuffer.append("         '' COD_AGENTE,  ");
		    strBuffer.append("         D.FECHA_NACIMIENTO_RAW ");
		    strBuffer.append("  FROM   Y_Y_Y_Y_Y_DUP_RAIZ_NAFT_DATOS D, Y_Y_Y_Y_Y_DUP_RAIZ_NAFT dd, Y_Y_Y_Y_Y_DUP_RAIZ_NAFT_COD c ");
		    strBuffer.append("  where    DD.TOTAL            = 2    ");                     
		    strBuffer.append("  and      DD.RAIZ             = D.RAIZ ");
		    strBuffer.append("  and      DD.NAF_TITULAR      = D.NAF_TITULAR   ");                       
		    strBuffer.append("  AND      C.COD_USUARIO_SNS   = D.COD_USUARIO_SNS ");
		    strBuffer.append("  AND      D.COD_ESTADO IN    (0, 1, 7) ");
		    strBuffer.append("  and      not exists( ");
		    strBuffer.append("                      select 'X' ");
		    strBuffer.append("                      from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");
		    strBuffer.append("                      where  Z.COD_USUARIO_SNS = c.COD_USUARIO_SNS ");
		    //strBuffer.append("                      where  (Z.COD_USUARIO_SNS = c.COD_USUARIO_SNS OR ( Z.NAF_TITULAR = d.NAF_TITULAR and Z.ID_SSALUD = d.raiz   ) ) ");
		    strBuffer.append("                     )  ");                                                 
		    strBuffer.append("  ORDER BY dd.NAF_TITULAR, dd.RAIZ ");		
		    */						
			//
			/*
			// RAIZ + NOMBRE 
			strBuffer.append(" select D.COD_USUARIO_SNS,  ");
			strBuffer.append("         D.COD_ESTADO,  ");
			strBuffer.append("         d.DNI_NIE,  ");
			strBuffer.append("         D.PASAPORTE,  ");
			strBuffer.append("         d.naf,  ");
			strBuffer.append("         d.NAF_TITULAR, ");
			strBuffer.append("         d.NOMBRE,  ");
			strBuffer.append("         d.APELLIDO1,  ");
			strBuffer.append("         d.APELLIDO2,  ");
		    strBuffer.append("         D.SEXO,  ");
		    strBuffer.append("         D.RAIZ ID_EN_SSALUD,  ");
		    strBuffer.append("         '' COD_AGENTE,  ");
		    strBuffer.append("         D.FECHA_NACIMIENTO_RAW ");
			strBuffer.append(" FROM     Y_Y_Y_Y_Y_DUP_RAIZ_NOMBRE_DATO D, Y_Y_Y_Y_Y_DUP_RAIZ_NOMBRE dd, Y_Y_Y_Y_Y_DUP_RAIZ_NOMBRE_COD c ");	
			strBuffer.append(" where    DD.TOTAL            = 2  ");	                       
			strBuffer.append(" and      DD.RAIZ             = D.RAIZ ");	
			strBuffer.append(" and      DD.NOMBRE           = D.NOMBRE    ");	                      
			strBuffer.append(" AND      C.COD_USUARIO_SNS   = D.COD_USUARIO_SNS ");	
			strBuffer.append(" AND      D.COD_ESTADO IN    (0, 1, 7) ");	
			strBuffer.append(" and      not exists( ");	
			strBuffer.append("                     select 'X' ");	
			strBuffer.append("                     from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");	
			strBuffer.append("                     where  Z.COD_USUARIO_SNS = c.COD_USUARIO_SNS ");	
			strBuffer.append("                    ) ");	                                           
			strBuffer.append(" ORDER BY dd.RAIZ, dd.NOMBRE    ");	
			*/
			
			
			
			
			
			
			
			//
			//
			query = strBuffer.toString();
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				ArrayList <DatosLecturaBean> array = new ArrayList <DatosLecturaBean> ();
				//
				contadorRegistros++;
				//
				/////////////////////////////////////////////////////////////////////////////////////////				
				DatosLecturaBean datosLecturaBean = new DatosLecturaBean (resultSet, contadorRegistros);
				/////////////////////////////////////////////////////////////////////////////////////////
				//				
				array.add(datosLecturaBean);
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 100 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				if(resultSet.next()) {
					/////////////////////////////////////////////////////////////////////////////////////////
					DatosLecturaBean datosLecturaBean2 = new DatosLecturaBean (resultSet, contadorRegistros);
					/////////////////////////////////////////////////////////////////////////////////////////
					//				
					array.add(datosLecturaBean2);
					//
					// Se llama al metodo para comparar los elementos
					duplicadosDeMasDeDos.comprobarDuplicadosDosArray(fileWriter, fileWriter2, array);
				}
				else {
					logger.debug("ERROR ERROR ERROR ERROR Registros procesados: " + contadorRegistros);
				}		
			}
			logger.debug("FIN Registros procesados: " + contadorRegistros);
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
			//
			try {
				if (fileWriter2 != null) {
					fileWriter2.close();
				}
			} catch (Exception e5) {
				fileWriter2 = null;
			}
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
	
	/*
	private void escribirFichero (FileWriter fileWriter, DatosLecturaBean datosLecturaBean, DatosLecturaBean datosLecturaBean2, BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
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
			str.append(datosLecturaBean.getLineaEntrada());
			str.append(datosLecturaBean2.getLineaEntrada());
			str.append(busquedaAproxResultadoBean.toString());
			//
			NombreComparacionBean nombreComparacionBean = new NombreComparacionBean ();
			if(busquedaAproxResultadoBean!=null){
				nombreComparacionBean = busquedaAproxResultadoBean.getNombreComparacionBean();
			}
			//
			str.append(nombreComparacionBean.write2());
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
	*/
	
}
