package sns.comun.util;

import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import sns.util.Misc;


public class Fecha {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static String cambiarFormato (String fecha) throws Exception {
		//
		String fechaAux = "";
		//
		try {
			//logger.debug("INICIO");
			//
			//logger.error("fecha: " + fecha);
			//
			if(!Misc.esVacio(fecha) && fecha.length()>7){
				String anio = fecha.substring(0, 4);
				String mes  = fecha.substring(4, 6);
				String dia  = fecha.substring(6, 8);
				//
				//logger.error("anio: " + anio + ", mes: " + mes + ", dia: " + dia);
				//
				fechaAux = anio + ConstantesBusqueda.SEPARADOR_FECHA + mes + ConstantesBusqueda.SEPARADOR_FECHA + dia;
			}
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return fechaAux;
	}
	
	
	
	
	
	

}
