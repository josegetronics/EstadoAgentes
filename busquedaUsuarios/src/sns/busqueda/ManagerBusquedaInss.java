package sns.busqueda;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import sns.busqueda.model.BusquedaHelperInss;
import sns.comun.config.InicializacionBusqueda;


public class ManagerBusquedaInss {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String path            = "D:/Procesos/BusquedaAproximacion/2014-07-07 Inss/";
			String ficheroOrigen   = "prueba.txt";
			String ficheroDestino  = "resultadoPrueba.txt";
			//
			BusquedaHelperInss busquedaHelperInss = new BusquedaHelperInss ();
			busquedaHelperInss.managerBBDD(path, ficheroDestino);
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



