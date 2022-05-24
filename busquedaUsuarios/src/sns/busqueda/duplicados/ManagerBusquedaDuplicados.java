package sns.busqueda.duplicados;

import org.apache.log4j.Logger;
import sns.comun.config.InicializacionBusqueda;


public class ManagerBusquedaDuplicados {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String urlDestinoTxt = "D:/Procesos/DUPLICADOS/";
			//
			DuplicadosDos duplicadosDos = new DuplicadosDos ();
			duplicadosDos.comprobarDuplicadosDos(urlDestinoTxt);
			//
			//DuplicadosDeMasDeDos duplicadosDeMasDeDos = new DuplicadosDeMasDeDos ();			
			//duplicadosDeMasDeDos.comprobarDuplicadosDos(urlDestinoTxt);
			//			
			//
			logger.debug("FIN"); 
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}	
	
}
