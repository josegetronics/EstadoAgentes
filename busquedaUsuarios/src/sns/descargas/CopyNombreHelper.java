package sns.descargas;

import org.apache.log4j.Logger;
import sns.ba.config.InicializacionBA;


public class CopyNombreHelper {

	
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);
	
	
	public static void main(String[] args) throws Exception {
		//
		NombreHelper nombreHelper = new NombreHelper ();
		//
		try {
			logger.debug("INICIO");
			//
			
			boolean esIgual         = false;
			///////////////////////////////////////
			String nombre           = "MANUEL ANTONIO";
			String nombreComparar   = "MIGUEL ANTONIO";
			boolean masDeUnapalabra = false;
			///////////////////////////////////////
			//
			if (nombre.equals(nombreComparar)) {
				esIgual = true;
			}
			//
			if(!esIgual) {
				char[] aCaracteres1 = nombre.toCharArray();
				char[] aCaracteres2 = nombreComparar.toCharArray();
				//
				// Se comparan de dos maneras las letras de las cadenas
				//
				// 1) Se comprueban por posiciones a ver si coinciden la letras (contadorPosicionIguales)
				// 2) Se buscan cuantas letras de una cadena estan en la otra    (contadorLetrasIguales)
				//
/*
				//1)///////////////////////////////////
				int contadorPosicionIguales_1 = nombreHelper.obtenerPosicionIguales(nombre, nombreComparar);
				int contadorLetrasIguales_1   = nombreHelper.obtenerLetrasIguales(nombre, nombreComparar);
				//
				int contadorPosicionIguales_2 = nombreHelper.obtenerPosicionIguales(nombreComparar, nombre);
				int contadorLetrasIguales_2   = nombreHelper.obtenerLetrasIguales(nombreComparar, nombre);
	*/			
				
				
				logger.debug("-------------------------------------------------------------");
				//

				//
				logger.debug("-------------------------------------------------------------");
				//
				for (int i=0;i<aCaracteres1.length && i<aCaracteres2.length ;i++) {
					char a = aCaracteres1[i];
					char b = aCaracteres2[i];
					logger.debug("a: " + a + ", b: " + b);
				}
			}
			
			logger.debug("\n\n");
			
			///////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////			
			///////////////////////////////////////////////////////////////////////////////
			char[] aCaracteres1 = nombre.toCharArray();
			char[] aCaracteres2 = nombreComparar.toCharArray();
			for (int i=0;i<aCaracteres1.length && i<aCaracteres2.length ;i++) {
				char a = aCaracteres1[i];
				char b = aCaracteres2[i];
				logger.debug("a: " + a + ", b: " + b);
			}
			
			logger.debug("nombre:  " + nombre +  ", longitud: " + nombre.length());
			logger.debug("nombreComparar: " + nombreComparar + ", longitud: " + nombreComparar.length());
			//
			//logger.debug("contadorPosicioniguales: " + contadorPosicionIguales);
			//logger.debug("contadorLetrasIguales:   " + contadorLetrasIguales);
			///////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////
			
			/*
			String cadena2 = "Prueba111";
			String cadena  = "Pr";

			String cadena_1 = "";
			String cadena_2 = "";
			
			
			if(cadena.length() >= cadena2.length()) {
				cadena_1 = cadena;
				cadena_2 = cadena2;
			}
			else {
				cadena_1 = cadena2;
				cadena_2 = cadena;
			}
			*/
			
			
			/*
			boolean estaContenida = (cadena.contains(cadena2) ||  cadena2.contains(cadena));
			logger.debug("estaContenida: " + estaContenida);

			
					
			String arrayCadena[] = "SERGIO".split(" ");
			String cadena_Nueva  = "SERGI ALEXANDRE";
			//			
			for(int i = 0;i<arrayCadena.length;i++){
				if(cadena_Nueva.indexOf(arrayCadena[i], 0) != -1) {
					System.out.println(arrayCadena[i]+"---Existe en cadena 2");
				}
				else {
					System.out.println(arrayCadena[i]+"---NO Existe en cadena 2");
				}
			} 
			*/
			
			
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	public int obtenerPosicionIguales(String nombre, String nombreComparar) throws Exception {
		//
		int contadorPosicionIguales = 0;
		//
		try {
			logger.debug("INICIO");
			//
			char[] aCaracteres1 = nombre.toCharArray();
			char[] aCaracteres2 = nombreComparar.toCharArray();
			//
			for (int i=0;i<aCaracteres1.length && i<aCaracteres2.length ;i++) {
				//
				char a = aCaracteres1[i];
				char b = aCaracteres2[i];
				//
				if(a==b) {
					contadorPosicionIguales++;
					logger.debug("Iguales");
				}
			}	
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return contadorPosicionIguales;
	}
	
	
	public int obtenerLetrasIguales(String nombre, String nombreComparar) throws Exception {
		//
		int contadorLetrasIguales = 0;
		//
		try {
			logger.debug("INICIO");
			//
			char[] aCaracteres1 = nombre.toCharArray();
			char[] aCaracteres2 = nombreComparar.toCharArray();
			//
			for (int i=0;i<aCaracteres1.length && i<aCaracteres2.length ;i++) {
				//
				char a = aCaracteres1[i];
				char b = aCaracteres2[i];
				//
				boolean encontrado = false;
				//
				for (int j=0;j<aCaracteres2.length && !encontrado ;j++) {
					char c = aCaracteres2[j];
					if(a==c) {
						contadorLetrasIguales++;
						aCaracteres2[j] = '$';
						encontrado = true;
					}
				}
			}
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return contadorLetrasIguales;
	}
	
	
	
	
}
