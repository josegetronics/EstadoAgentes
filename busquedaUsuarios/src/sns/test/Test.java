package sns.test;

import org.apache.log4j.Logger;

import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.busqueda.model.BusquedaAproximadaUtil;
import sns.comun.config.InicializacionBusqueda;


public class Test {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public static void main(String[] args) throws Exception {
		//
		try {
			logger.debug("INICIO");
			//
			BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper();

			/*
			String nombre              = "ANTO—IO";
			String nombreComparar      = "ANTONIO";
			
			String apellido1           = "MARTI—EZ";
			String apellido1Comparar   = "MARTINEZ";
			String apellido2           = "MARTINEZ";
			String apellido2Comparar   = "MARTINEZ";
			String codSexo             = "1";
			String codSexoComparar     = "0";
			String fechaNac            = "1976-05-09";
			String fechaNacComparar    = "1970-05-09";
			//
			//
			boolean esAproximadaCadena = busquedaAproximadaHelper.esAproximadaCadena(nombre, nombreComparar);
			//
			logger.debug("nombre: " + nombre + ", nombreComparar: " + nombreComparar);
			logger.debug("esAproximadaCadena: " + esAproximadaCadena);
			//
			boolean esAproximadoSexo = busquedaAproximadaHelper.esAproximadoSexo(codSexo, codSexoComparar, nombre, nombreComparar);
			//
			logger.debug("codSexo: " + codSexo + ", codSexoComparar: " + codSexoComparar + ", nombre: " + nombre + ", nombreComparar: " + nombreComparar);
			logger.debug("esAproximadoSexo: " + esAproximadoSexo);
			*/
			
			
			String nombre         = "ANTONIO";
			boolean borrarLetraN  = false;
			//
			logger.debug("nombre: " + nombre + ", borrarLetraN: " + borrarLetraN);
			//
			// CONTIENE LA LETRA —  O TRATAMIENTO PARA POSIBLE CARACTER SUSTITUIDO POR —
			nombre         = "ANTO—IO";
			// EMPIEZAN POR X
			nombre         = "X";
			nombre         = "XX";
			nombre         = "XXX";
			nombre         = "XXXXXXXXXXXXXXXXXXXX";
			// EMPIEZAN POR LETRA+PUNTO O  LETRA+ESPACIO
			nombre         = "J. ANTONIO";
			nombre         = "J ANTONIO";		
			// CARACTER PUNTO O ESPACIO EN BLANCO
			nombre         = "ANTONIO JOSE M.";
			// VOCALES  
			nombre         = "¿NT‘NÕ‹‘";
			//
			//  PARTICULAS  // DE, DEL, EL, LA, LAS, LO, LOS
			nombre         = "ANTONIO DE JOSE";
			nombre         = "ANTONIO LAS JOSE";
			nombre         = "LO ANTONIO";
			nombre         = "LAS ANTONIO";
			nombre         = "ANTONIO DE";
			nombre         = "ANTONIO LAS";
			//
			// CARACTERES REPETIDOS
			//nombre         = "AANTTOOOONIIO";
			//
		    // CARACTERES EXTRA—OS
			//nombre         = "AA•NTTø*OÔNIIO√`IøΩ";
			//
			// CONFIGURAR
			//nombre         = "FCO ANTONIO GARCIA JACHOZ";
			//
			//
			logger.debug("\n\n\n");
			String cadenaFinal = BusquedaAproximadaUtil.sustituirSubcadenas(nombre, borrarLetraN);
			logger.debug("nombre: [" + nombre + "]  borrarLetraN: " + borrarLetraN);
			logger.debug("cadenaFinal: [" + cadenaFinal+ "]");
			//
			logger.debug("FIN");
			logger.debug("\n\n");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
}
