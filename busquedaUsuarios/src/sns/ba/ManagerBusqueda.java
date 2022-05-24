package sns.ba;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sns.ba.config.ConstantesBA;
import sns.ba.config.InicializacionBA;
import sns.ba.impl.BusquedaAproximadaTraspasosImpl;
import sns.model.impl.DatosIdentificativosImpl;



public class ManagerBusqueda {
		
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);

	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO xx");
			//
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantesBA.MASCARA_FECHA);
			//
			//
			String  nombre         = "ANTONIO A";
			String  apellido1      = "";
			String  apellido2      = "RAMIREZ";
			String  codSexo        = "2";
			String fecha           = "1976-05-09";
			Date    fechaNac       = new java.sql.Date (simpleDateFormat.parse(fecha).getTime());
			//
			String  dniNie         = "";
			String  pasaporte      = "";
			String  naf            = "";
			String  nafTitular     = "";
			DatosIdentificativosImpl personaActual = null;
			personaActual = new DatosIdentificativosImpl(nombre, apellido1, apellido2, codSexo, fechaNac, dniNie, pasaporte, naf, nafTitular);
			//
			//
			String  nombre_1       = "ANTONIO";
			String  apellido1_1    = "MARTINEZ";
			String  apellido2_1    = "RAMIREZ jIMENENZ";
			String  codSexo_1      = "2";
			Date    fechaNac_1     = null;
			String fecha2          = "1976-05-09";
			fechaNac_1             = new java.sql.Date (simpleDateFormat.parse(fecha2).getTime());
			String  dniNie_1       = ""; //"44394419A";
			String  pasaporte_1    = "";
			String  naf_1          = "";
			String  nafTitular_1   = "";
			DatosIdentificativosImpl candidatoExistente = null;
			candidatoExistente = new DatosIdentificativosImpl(nombre_1 , apellido1_1 , apellido2_1 , codSexo_1 , fechaNac_1 , dniNie_1 , pasaporte_1 , naf_1 , nafTitular_1 );
			//
			//
			ArrayList<String> criterioEmparejamiento = new ArrayList<String> ();
			//criterioEmparejamiento.add("DNI_NIE");
			//criterioEmparejamiento.add("PASAPORTE");
			criterioEmparejamiento.add("NAF_SEC");			
			//
			//
			//
			//
			BusquedaAproximadaTraspasosImpl busquedaAvanzadaTraspasosImpl = new BusquedaAproximadaTraspasosImpl ();
			IRespuestaBa respuestaBaImpl = busquedaAvanzadaTraspasosImpl.comparar(personaActual, candidatoExistente, criterioEmparejamiento);
			//
			
			
			logger.debug("----------------- InicializacionBA.BUSQUEDA_APROXIMADA_FECHA: " + InicializacionBA.BUSQUEDA_APROXIMADA_FECHA);
			
			
			logger.debug("");
			logger.debug("");
			respuestaBaImpl.toString();
			logger.debug("");
			logger.debug("");
			//
			logger.debug("FIN xx");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
}
