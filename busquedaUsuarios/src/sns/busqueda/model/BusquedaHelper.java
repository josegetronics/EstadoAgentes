package sns.busqueda.model;

import gasai.util.Misc;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import org.apache.log4j.Logger;
import sns.comun.bean.DatosLecturaBean;
import sns.comun.bean.InssBean;
import sns.comun.bean.ResultadosCampoBean;
import sns.comun.bean.SnsBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class BusquedaHelper {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
		
	public boolean esMismaPersona (BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
		//
		boolean criterioEsMismaPersona = true;  
		//
		try {
			//logger.debug("INICIO");
			//
			if(busquedaAproxResultadoBean != null) {
				//
				String criterio        = Misc.nz(busquedaAproxResultadoBean.getCriterio());
				int camposCoincidentes = busquedaAproxResultadoBean.getCamposCoincidentes();
				int porcentaje         = busquedaAproxResultadoBean.getPorcentaje();
				//
				if(camposCoincidentes < InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS) {
					criterioEsMismaPersona = false;
				}
				//else {
					//logger.debug("camposCoincidentes >= " + InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS);
				//}
				//
				if(porcentaje < InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE) {	
					criterioEsMismaPersona = false;
				}
				//else {
				//	logger.debug("camposCoincidentes >= " + InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE);
				//}
				//
				//logger.debug("criterio: " + criterio);
				//
				
				//
				//if (criterio.indexOf("APELLIDO1#APELLIDO2#COD_SEXO#FECHA_NAC#") != -1) {
				//	logger.debug("Se comprueba si es gemelo, criterio: " + criterio);
				//	//
				//	if (criterio.indexOf("NOMBRE#") == -1) {
	            //		criterioEsMismaPersona = false;
	            //		logger.debug("Coincide todo menos el nombre, puede ser un gemelo." + criterio);
	            //	}
				//}	
				
				if(InicializacionBusqueda.MAP_CAMPOS_OBLIGATORIOS.size()>0) {
		            Set <String> setCampos = InicializacionBusqueda.MAP_CAMPOS_OBLIGATORIOS.keySet();
		            Iterator <String> iteratorCampos = setCampos.iterator();
		            //           
		            while (criterioEsMismaPersona && iteratorCampos.hasNext()) {
		            	String campo  = (String) iteratorCampos.next();
		            	//
		            	//logger.debug("campo: " + campo);
		                //	            	
		            	if (criterio.indexOf(campo) == -1) {
		            		criterioEsMismaPersona = false;
		            	}
		            }
				}		
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return criterioEsMismaPersona;
	}
	

	
	protected void escribirFichero (FileWriter fileWriter, DatosLecturaBean datosLecturaBean, SnsBean snsBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoCampo, String tipoResultado, boolean encontradoBaja) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			int encontradoBajaInt = 0;
			if (encontradoBaja) {
				encontradoBajaInt = 1;
			}
			//
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			//
			StringBuffer str = new StringBuffer ();
			str.append(datosLecturaBean.getLineaEntrada());
			str.append(snsBean.carga());
			str.append(busquedaAproxResultadoBean.toString());
			str.append(tipoCampo         + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(tipoResultado     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append("||||||||||||||||||||||||||||||||||||");
			//
			str.append(encontradoBajaInt + ConstantesBusqueda.SEPARADOR_CAMPOS);
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
		
	
	protected void escribirFichero (FileWriter fileWriter, DatosLecturaBean datosLecturaBean, SnsBean snsBean, InssBean inssBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, BusquedaAproxResultadoBean busquedaAproxResultadoBeanInss, String tipoCampo, String tipoResultado, String tipoResultadoInss, boolean encontradoBaja) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			int encontradoBajaInt = 0;
			if (encontradoBaja) {
				encontradoBajaInt = 1;
			}
			//
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			//
			StringBuffer str = new StringBuffer ();
			str.append(datosLecturaBean.getLineaEntrada());
			str.append(snsBean.carga());
			if(busquedaAproxResultadoBean == null) {
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			str.append(busquedaAproxResultadoBean.toString());
			str.append(tipoCampo     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(tipoResultado + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(inssBean.carga());
			if(busquedaAproxResultadoBeanInss == null) {
				busquedaAproxResultadoBeanInss = new BusquedaAproxResultadoBean ();
			}
			str.append(busquedaAproxResultadoBeanInss.toString());
			str.append(tipoResultadoInss + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(encontradoBajaInt + ConstantesBusqueda.SEPARADOR_CAMPOS);
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
	
	
	
	protected void escribirFichero (FileWriter fileWriter, SnsBean snsBean, InssBean inssBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoCampo, String tipoResultado) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			//
			StringBuffer str = new StringBuffer ();
			str.append(snsBean.carga());
			str.append(inssBean.carga());
			str.append(busquedaAproxResultadoBean.toString());
			str.append(tipoCampo     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(tipoResultado + ConstantesBusqueda.SEPARADOR_CAMPOS);
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
	
	
	protected void verResultados (LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda, int bajas) throws Exception {
		//
		try {
			logger.debug("INICIO");
			//
			logger.debug("bajas: " + bajas);
			//
		    Set <String> setCamposBusqueda           = mapCamposBusqueda.keySet();
		    Iterator <String> iteratorCamposBusqueda = setCamposBusqueda.iterator();
		    //           
		    while (iteratorCamposBusqueda.hasNext()) {
		    	String camposBusqueda  = (String) iteratorCamposBusqueda.next();
		    	ResultadosCampoBean resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(camposBusqueda);
		    	//
		    	logger.debug("--- --- --- --- --- " + camposBusqueda);  
		    	//
		    	if(resultadosCampoBean.getVacios()!=0) {
		    		logger.debug("camposBusqueda: " + camposBusqueda + ", vacios:                 " + resultadosCampoBean.getVacios());
				}
				//
	    		if(resultadosCampoBean.getDuplicados()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", duplicados:             " + resultadosCampoBean.getDuplicados());
				}
	    		if(resultadosCampoBean.getDuplicadosEstudio()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", duplicadosEstudio:      " + resultadosCampoBean.getDuplicadosEstudio());
				}
		    	//
		    	if(resultadosCampoBean.getNoEncontrados()!=0) {
		    		logger.debug("camposBusqueda: " + camposBusqueda + ", noEncontrados:          " + resultadosCampoBean.getNoEncontrados());
				}
				//
	    		if(resultadosCampoBean.getNoCoincidenteTotal()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", noCoincidenteTotal:          " + resultadosCampoBean.getNoCoincidenteTotal());
				}
				//
	    		if(resultadosCampoBean.getNoCoincidente()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", noCoincidente:          " + resultadosCampoBean.getNoCoincidente());
				}
	    		//
	    		if(resultadosCampoBean.getNoCoincidenteDniNaf()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", noCoincidenteDniNaf:    " + resultadosCampoBean.getNoCoincidenteDniNaf());
				}
	    		//
	    		//
	    		//
		    	if(resultadosCampoBean.getVaciosInss()!=0) {
		    		logger.debug("camposBusquedaInss: " + camposBusqueda + ", vaciosInss:                  " + resultadosCampoBean.getVaciosInss());
				}
				//
	    		if(resultadosCampoBean.getDuplicadosInss()!=0) {
	    			logger.debug("camposBusquedaInss: " + camposBusqueda + ", duplicadosInss:              " + resultadosCampoBean.getDuplicadosInss());
				}
	    		if(resultadosCampoBean.getDuplicadosEstudioInss()!=0) {
	    			logger.debug("camposBusquedaInss: " + camposBusqueda + ", duplicadosEstudioInss:       " + resultadosCampoBean.getDuplicadosEstudioInss());
				}
	    		if(resultadosCampoBean.getIncorrectosInss()!=0) {
	    			logger.debug("camposBusquedaInss: " + camposBusqueda + ", incorrectosInss:             " + resultadosCampoBean.getIncorrectosInss());
				}	
	    		//
		    	if(resultadosCampoBean.getNoEncontradosInss()!=0) {
		    		logger.debug("camposBusquedaInss: " + camposBusqueda + ", noEncontradosInss:           " + resultadosCampoBean.getNoEncontradosInss());
				}
	    		if(resultadosCampoBean.getNoCoincidenteInss()!=0) {
	    			logger.debug("camposBusquedaInss: " + camposBusqueda + ", noCoincidenteInss:           " + resultadosCampoBean.getNoCoincidenteInss());
				}  		
	    		//
		    	if(resultadosCampoBean.getCoincidenteInss()!=0) {
		    		logger.debug("camposBusquedaInss: " + camposBusqueda + ", coincidenteInss:             " + resultadosCampoBean.getCoincidenteInss());
				}
				//
		    	if(resultadosCampoBean.getCoincidenteMismaPersonaInss()!=0) {
		    		logger.debug("camposBusquedaInss: " + camposBusqueda + ", coincidenteMismaPersonaInss: " + resultadosCampoBean.getCoincidenteMismaPersonaInss());
				}
	    		//
	    		//	    		
				//
		    	if(resultadosCampoBean.getCoincidente()!=0) {
		    		logger.debug("camposBusqueda: " + camposBusqueda + ", coincidente:            " + resultadosCampoBean.getCoincidente());
		    	}
		    	if(resultadosCampoBean.getCoincidenteMismaCA()!=0) {
		    		logger.debug("camposBusqueda: " + camposBusqueda + ", coincidenteMismaCA:     " + resultadosCampoBean.getCoincidenteMismaCA());
				}
		    	if(resultadosCampoBean.getCoincidenteDiferenteCA()!=0) {
		    		logger.debug("camposBusqueda: " + camposBusqueda + ", coincidenteDiferenteCA: " + resultadosCampoBean.getCoincidenteDiferenteCA());
				}
		    	//
	    		if(resultadosCampoBean.getIncorrectos()!=0) {
	    			logger.debug("camposBusqueda: " + camposBusqueda + ", incorrectos:            " + resultadosCampoBean.getIncorrectos());
				}
	    		//    		
		    }
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}
	
	
	
	
}
