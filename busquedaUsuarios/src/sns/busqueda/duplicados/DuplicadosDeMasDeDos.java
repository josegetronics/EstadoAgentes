package sns.busqueda.duplicados;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.busqueda.duplicados.bean.BusquedaBean;
import sns.busqueda.duplicados.bean.ResultadoComparacionBean;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.busqueda.model.BusquedaHelperSns;
import sns.busqueda.model.NombreHelper;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.DatosLecturaBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import sns.util.Misc;


public class DuplicadosDeMasDeDos {

	
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
			// IDSSALUD
			/*
			strBuffer.append(" select   D.COD_USUARIO_SNS,  ");    
			strBuffer.append("          D.COD_ESTADO,       ");    
			strBuffer.append("          D.COD_AGENTE,       "); 
			strBuffer.append("          d.DNI_NIE,          ");   
			strBuffer.append("          D.PASAPORTE,        ");    
			strBuffer.append("          d.naf,              ");    
			strBuffer.append("          d.NAF_TITULAR,      ");    
			strBuffer.append("          d.NOMBRE,           ");    
			strBuffer.append("          d.APELLIDO1,        ");    
			strBuffer.append("          d.APELLIDO2,        ");    
			strBuffer.append("          D.SEXO,             ");   
			strBuffer.append("          D.FECHA_NACIMIENTO_RAW,  ");
			strBuffer.append("          D.ID_EN_SSALUD  ");
			strBuffer.append(" from     Y_Y_Y_Y_Y_DUP_IDSSALUD_DATOS d, Y_Y_Y_Y_Y_DUP_IDSSALUD_COD c, Y_Y_Y_Y_Y_DUP_IDSSALUD i ");
			strBuffer.append(" where    I.TOTAL           > 2 ");
			strBuffer.append(" and      I.ID_EN_SSALUD    = C.ID_EN_SSALUD ");
			strBuffer.append(" and      I.COD_AGENTE      = C.COD_AGENTE ");
			strBuffer.append(" and      C.COD_USUARIO_SNS = d.COD_USUARIO_SNS ");
			//strBuffer.append(" and      D.ID_EN_SSALUD in ('AN1462015837', 'BDFR391248903014') ");
			strBuffer.append(" order by ID_EN_SSALUD ");
			*/
			
			
			//
			//  DNI_NIE
			/*
			strBuffer.append(" select   D.COD_USUARIO_SNS, "); 
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
			strBuffer.append("          '' COD_AGENTE, ");
			strBuffer.append("          '' ID_EN_SSALUD ");
			strBuffer.append(" FROM     Y_Y_Y_Y_Y_DUP_DNI_DATOS_M2_X D ");	
			strBuffer.append(" where    not exists( ");
			strBuffer.append("       			   select 'X' ");
			strBuffer.append("     				   from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");
			strBuffer.append("         			   where  Z.COD_USUARIO_SNS = d.COD_USUARIO_SNS ");
			strBuffer.append("       			  )    ");
			strBuffer.append(" ORDER BY D.DNI_NIE ");
			*/						
			
			/*
			// NAF TITULAR + RAIZ 
			strBuffer.append(" select  D.COD_USUARIO_SNS,  ");
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
			strBuffer.append(" FROM    Y_Y_Y_Y_Y_DUP_RAIZ_NAFT_D_M2_X D ");	
			strBuffer.append(" where   not exists( ");
			strBuffer.append("       			   select 'X' ");
			strBuffer.append("     				   from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");
			strBuffer.append("         			   where  Z.COD_USUARIO_SNS = d.COD_USUARIO_SNS ");
			//strBuffer.append("                     where  (Z.COD_USUARIO_SNS = c.COD_USUARIO_SNS OR ( Z.NAF_TITULAR = d.NAF_TITULAR and Z.ID_SSALUD = d.raiz   ) ) ");			
			strBuffer.append("       			  )    ");
			strBuffer.append(" ORDER BY d.NAF_TITULAR, ID_EN_SSALUD ");						
			*/
			
			
			// RAIZ + NOMBRE
			strBuffer.append(" select  D.COD_USUARIO_SNS,  ");
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
			strBuffer.append(" FROM    Y_Y_Y_Y_Y_DUP_RAIZ_NOM_D_M2_X D ");	
			strBuffer.append(" where   not exists( ");
			strBuffer.append("       			   select 'X' ");
			strBuffer.append("     				   from   Z_Z_Z_Z_Z_Z_Z_Z_Z_DUPLICADOS z ");
			strBuffer.append("         			   where  Z.COD_USUARIO_SNS = d.COD_USUARIO_SNS ");		
			strBuffer.append("       			  )    ");
			strBuffer.append(" ORDER BY ID_EN_SSALUD, NOMBRE ");						
			//
			query = strBuffer.toString();
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			boolean comprobar = false;
			String idAnterior = "";
			ArrayList <DatosLecturaBean> array   = null;
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
				DatosLecturaBean datosLecturaBean = new DatosLecturaBean (resultSet, contadorRegistros);
				//
				//logger.debug(datosLecturaBean.toLinea());
				//
				//String idSsalud = Misc.nz(datosLecturaBean.getIdSsalud());
				//String idSsalud = Misc.nz(datosLecturaBean.getDniNie());
				//String idSsalud = Misc.nz(datosLecturaBean.getNafTitular()) + Misc.nz(datosLecturaBean.getIdSsalud());
				String idSsalud = Misc.nz(Misc.nz(datosLecturaBean.getIdSsalud() + datosLecturaBean.getNombre()));
				//
				// Si es vacio es el primer duplicado
				if(Misc.esVacio(idAnterior)){
					//logger.debug("Misc.esVacio(idAnterior)");
					idAnterior  = Misc.nz(idSsalud);
					array       = new ArrayList <DatosLecturaBean> ();
					array.add(datosLecturaBean);
				}
				else {
					// Si es igual es otro duplicado mas
					if(idSsalud.equals(idAnterior)){
						//logger.debug("idSsalud.equals(idAnterior)");
						array.add(datosLecturaBean);
					}
					else {
						//logger.debug("else");
						// Si es diferente, se llama al metodo para comparar los elementos
						this.comprobarDuplicadosDosArray(fileWriter, fileWriter2, array);
						//		
						// Una vez terminada la comparacion se resetean los valores 
						idAnterior  = Misc.nz(idSsalud);
						array       = new ArrayList <DatosLecturaBean> ();
						array.add(datosLecturaBean);
					}
				}					
				//
				//logger.debug("idAnterior: " + idAnterior + ", array: " + array.size());				
			}
			if(array.size()>0) {
				// Solo para cuando es el ultimo caso, o solo hay uno
				this.comprobarDuplicadosDosArray(fileWriter, fileWriter2, array);
			}
			logger.debug("Registros procesados: " + contadorRegistros);
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
	
	
	
	public void comprobarDuplicadosDosArray(FileWriter fileWriter, FileWriter fileWriter2, ArrayList <DatosLecturaBean> arrayDuplicados) {
		//
		ArrayList <ResultadoComparacionBean> arrayResultados = new ArrayList <ResultadoComparacionBean> ();
		//
		try {
			//logger.debug("INICIO");
			//
			for(int contador=0 ; contador<arrayDuplicados.size() ; contador++) {
				//
				boolean encontrado = false;
				//
				DatosLecturaBean datosLecturaBean = arrayDuplicados.get(contador);
				//
				BusquedaHelperSns busquedaHelperSns = new BusquedaHelperSns ();
				//
				//logger.debug("Elemento: " + (contador+1) + ", encontrado: " + datosLecturaBean.isEncontrado());
				//
				for (int i=(contador+1) ; i<arrayDuplicados.size() ; i++) {
					//
					DatosLecturaBean datosLecturaBeanAux = arrayDuplicados.get(i);
					//
					if(!datosLecturaBeanAux.isEncontrado()) {
						//
						//
						ConsultaBean consultaBeanLectura = new ConsultaBean (datosLecturaBean.getNombre(),    datosLecturaBean.getApellido1(),    datosLecturaBean.getApellido2(),    datosLecturaBean.getSexo(),    datosLecturaBean.getFechaNac());
						ConsultaBean consultaBeanSns     = new ConsultaBean (datosLecturaBeanAux.getNombre(), datosLecturaBeanAux.getApellido1(), datosLecturaBeanAux.getApellido2(), datosLecturaBeanAux.getSexo(), datosLecturaBeanAux.getFechaNac());
						//
						// 1) Se realiza la busqueda aproximada
						BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
						BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);		
						//
						// 2) Si el SEXO es igual se puede realizar el estudio del NOMBRE
						if(Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_COD_SEXO) != -1 &&
						   Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_NOMBRE)   == -1    ) {
							NombreHelper nombreHelper = new NombreHelper ();
							NombreComparacionBean nombreComparacionBean = nombreHelper.managerNombre(consultaBeanLectura.getNombre(), consultaBeanSns.getNombre());
							busquedaAproxResultadoBean.setRevisionNombre(nombreComparacionBean.getRevision());
						}						
						//
						boolean esIgual = busquedaHelperSns.esMismaPersona (busquedaAproxResultadoBean);
						//
						int j = i + 1;
						int k = contador + 1;
						//logger.debug("contador: " + k + ", i: " + j + ", esIgual: " + esIgual);
						//
						String resultado = "DIFERENTE";	
						//
						if(esIgual) {
							encontrado = true;
							resultado = "COINCIDENTE";
							datosLecturaBeanAux.setEncontrado(true);
							arrayDuplicados.set(i, datosLecturaBeanAux);
						}
						//
						ResultadoComparacionBean resultadoComparacionBean = new ResultadoComparacionBean (datosLecturaBean, datosLecturaBeanAux, busquedaAproxResultadoBean, resultado);
						arrayResultados.add(resultadoComparacionBean);							
						//}
					}
				}
				if(encontrado){
					datosLecturaBean.setEncontrado(true);
					arrayDuplicados.set(contador, datosLecturaBean);
					
				}					
				//
				//logger.debug("-----------------");
			}	
			//
			//////////////////////////////////////////////////////////////////////
			//logger.debug("----------------- FIN Paso 1");
			//logger.debug(arrayDuplicados.size());
			for (int i=0 ; i<arrayDuplicados.size() ; i++) {
				int j = i + 1;
				DatosLecturaBean datosLecturaBeanAux = arrayDuplicados.get(i);
				
				//
				fileWriter2.write(datosLecturaBeanAux.toLineaDup() + "\n");		
				fileWriter2.flush();
			}
			//
			for (int i=0 ; i<arrayResultados.size() ; i++) {
				int j = i + 1;
				ResultadoComparacionBean resultadoComparacionBean = arrayResultados.get(i);				
				//
				this.escribirFichero (fileWriter, resultadoComparacionBean);
			}
			//logger.debug("----------------- FIN Paso 1");;
			//////////////////////////////////////////////////////////////////////
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
		finally {			
		}
	}	
	
	
	public void comprobarDuplicadosDosArray22() {
		//
		try {
			logger.debug("INICIO");
			//
			ArrayList <BusquedaBean>  arrayInicial = new ArrayList <BusquedaBean> ();
			BusquedaBean busquedaBean1 = new BusquedaBean ("uno");
			BusquedaBean busquedaBean2 = new BusquedaBean ("uno");
			BusquedaBean busquedaBean3 = new BusquedaBean ("uno");
			BusquedaBean busquedaBean4 = new BusquedaBean ("dos");
			BusquedaBean busquedaBean5 = new BusquedaBean ("dos");
			BusquedaBean busquedaBean6 = new BusquedaBean ("uno");
			BusquedaBean busquedaBean7 = new BusquedaBean ("tres");
			//
			arrayInicial.add(busquedaBean1); //1
			arrayInicial.add(busquedaBean2); //2
			arrayInicial.add(busquedaBean3); //3
			arrayInicial.add(busquedaBean4); //4
			arrayInicial.add(busquedaBean5); //5
			arrayInicial.add(busquedaBean6); //6
			arrayInicial.add(busquedaBean7);//7		
			//
			//////////////////////////////////////////////////////////////////////
			logger.debug("-----------------");
			for (int i=0 ; i<arrayInicial.size() ; i++) {
				int j = i + 1;
				BusquedaBean busquedaBean = arrayInicial.get(i); 
				logger.debug("i: " + j + ", valor: " + busquedaBean.getCadena() + ", encontrado: " + busquedaBean.isEncontrado());
			}
			logger.debug("-----------------");
			//////////////////////////////////////////////////////////////////////
			//
			for(int contador=0 ; contador<arrayInicial.size() ; contador++) {
				//
				boolean encontrado = false;
				//
				BusquedaBean busquedaBean = arrayInicial.get(contador);
				//
				logger.debug("Elemento: " + (contador+1) + ", encontrado: " + busquedaBean.isEncontrado());
				//
				//if(!busquedaBean.isEncontrado()) {
					//
					String cadena             = Misc.nz(busquedaBean.getCadena());
					//
					for (int i=(contador+1) ; i<arrayInicial.size() ; i++) {
						//
						BusquedaBean busquedaBeanAux = arrayInicial.get(i);
						//
						if(!busquedaBeanAux.isEncontrado()) {
							//
							String cadenaAux = Misc.nz(busquedaBeanAux.getCadena());
							//
							boolean esIgual = cadena.equals(cadenaAux);
							//
							int j = i + 1;
							int k = contador + 1;
							logger.debug("contador: " + k + ", i: " + j + ", cadena: " + cadena + ", cadenaAux: " + cadenaAux + ", esIgual: " + esIgual);
							//
							if(esIgual) {
								encontrado = true;
								busquedaBeanAux.setEncontrado(true);
								arrayInicial.set(i, busquedaBeanAux);
							}
						}
					}
					if(encontrado){
						busquedaBean.setEncontrado(true);
						arrayInicial.set(contador, busquedaBean);
						logger.debug("00000000000000000000000000000000000000000");
					}					
				//}
				logger.debug("-----------------");
			}	
			//
			//////////////////////////////////////////////////////////////////////
			logger.debug("----------------- FIN Paso 1");
			for (int i=0 ; i<arrayInicial.size() ; i++) {
				int j = i + 1;
				BusquedaBean busquedaBeanAux = arrayInicial.get(i); 
				logger.debug("i: " + j + ", valor: " + busquedaBeanAux.getCadena() + ", encontrado: " + busquedaBeanAux.isEncontrado());
			}
			logger.debug("----------------- FIN Paso 1");;
			//////////////////////////////////////////////////////////////////////
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
		finally {			
		}
	}	
	
		
	
	protected void escribirFichero (FileWriter fileWriter, ResultadoComparacionBean resultadoComparacionBean) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			StringBuffer str = new StringBuffer ();
			str.append(resultadoComparacionBean.getLinea());
			str.append("\n");
			//
			fileWriter.write(str.toString());
			fileWriter.flush();
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}		
	
	
	
	
	
}
