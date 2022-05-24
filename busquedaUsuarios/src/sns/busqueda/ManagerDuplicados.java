package sns.busqueda;

import org.apache.log4j.Logger;
import sns.busqueda.model.DuplicadosHelper;
import sns.comun.config.InicializacionBusqueda;


public class ManagerDuplicados {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String path            = InicializacionBusqueda.PATH_FICHEROS;
			String ficheroDestino  = "dup.txt";
			//
			DuplicadosHelper duplicadosHelper = new DuplicadosHelper ();
			duplicadosHelper.managerBBDD(path, ficheroDestino); 
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
}
