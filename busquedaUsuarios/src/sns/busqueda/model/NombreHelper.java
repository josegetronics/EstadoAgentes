package sns.busqueda.model;

import org.apache.log4j.Logger;
import sns.ba.config.InicializacionBA;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.busqueda.model.BusquedaAproximadaUtil;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.ConstantesBusqueda;
import sns.util.Misc;


public class NombreHelper {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);
	
		
	public NombreComparacionBean managerNombre(String nombre, String nombreComparar) throws Exception {
		//
		//
		boolean inclusion = false;	
		//
		NombreComparacionBean nombreComparacionBean = null;
		//
		try {
			//logger.debug("INICIO");
			//
			boolean borrarLetraN       = BusquedaAproximadaHelper.comprobarLetraN(nombre, nombreComparar);
			String nombreAprox         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombre), borrarLetraN);
			String nombreCompararAprox = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombreComparar), borrarLetraN);
			//			
			//logger.debug("nombre: " + nombre + ", nombreComparar: " + nombreComparar);
			//logger.debug("nombreAprox: " + nombreAprox + ", nombreCompararAprox: " + nombreCompararAprox);
			//
			nombreComparacionBean = new NombreComparacionBean (nombre, nombreComparar);
			//
			// Si el Nombre esta compuesto por varias palabras, se mira primero la inclusion
			if(nombre.indexOf(" ")!=-1 || nombreComparar.indexOf(" ")!=-1){
				// INCLUSION del nombre  en el otro
				if (!Misc.esVacio(nombreAprox) && !Misc.esVacio(nombreCompararAprox)) {
					if (nombreAprox.indexOf(nombreCompararAprox) != -1 || nombreCompararAprox.indexOf(nombreAprox) != -1) {
						inclusion = true;	
					}
				}
			}	
			if(inclusion){
				nombreComparacionBean.setInclusionCadenaAproximadas(ConstantesBusqueda.INCLUSION_CADENA_APROX_ESTUDIO_NOMBRE);
				//
				nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS);
				nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_IDENTIFICADOS);	
			}
			else {			
				// Si el Nombre esta compuesto por varias palabras, las palabras iguales en las dos se borran
				if(nombre.indexOf(" ")!=-1 && nombreComparar.indexOf(" ")!=-1){
					this.compararPalabras (nombreComparacionBean);
				}	
				//
				nombre         = nombreComparacionBean.getNombre_1();
				nombreComparar = nombreComparacionBean.getNombre_2();
				//
				if(Misc.esVacio(nombre) && Misc.esVacio(nombreComparar)) {
					//
					nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_VACIOS);
					nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_VACIOS);
				}
				else {
					// Si una de las dos palabras es vacia, la palabra era compuesta, y los nombres vienen del metodo 'compararPalabras()'
					if(Misc.esVacio(nombre) || Misc.esVacio(nombreComparar)) {
						//
						// No se tiene en cuenta la eliminacion de las palabras por estos casos:
						// JUANA MARIA CARMEN --> ]MARIA[
						// JUANA CARMEN       --> ][
						// Se toman los nombres Originales para normalizarlos
						//
						nombre         = nombreComparacionBean.getNombreOriginal_1();
						nombreComparar = nombreComparacionBean.getNombreOriginal_2();												
					}
					//logger.debug("0000 nombre: " + nombre + ", nombreComparar: " + nombreComparar);
					// 
					// La palabra era simple los nombres vienen de los originales, Se Normaliza
					nombre                = nombreAprox;
					nombreComparar        = nombreCompararAprox;
					//
					//////////////////////////////////////////////////////////////
					//
					if(Misc.esVacio(nombre) || Misc.esVacio(nombreComparar)) {
						//
						nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_VACIOS);
						nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_VACIOS);
					}
					else {
						//
						nombreComparacionBean.setNombre_1(nombre);
						nombreComparacionBean.setNombre_2(nombreComparar);
						//
						// Se obtienen las consonantes
						this.obtenerConsonantes (nombreComparacionBean);							
						//
						// INCLUSION DEL NOMBRE
						if (nombre.indexOf(nombreComparar) != -1 || nombreComparar.indexOf(nombre) != -1) {
							//								
							String consonantes_1  = this.obtenerConsonantes (nombreComparacionBean.getNombre_1());
							String consonantes_2  = this.obtenerConsonantes (nombreComparacionBean.getNombre_2());
							//	
							long longitudConsonantes_1 = consonantes_1.length();
							long longitudConsonantes_2 = consonantes_2.length();
							//
							if((longitudConsonantes_1 > 1 && longitudConsonantes_2 > 1) || longitudConsonantes_1 == 0 || longitudConsonantes_2 == 0) {
								inclusion = true;
							}						
						}
						if (inclusion) {
							nombreComparacionBean.setInclusionCadenaAproximadas(ConstantesBusqueda.INCLUSION_CADENA_APROX_ESTUDIO_NOMBRE);
							//
							nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS);
							nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_IDENTIFICADOS);						
						}
						else {
							//
							// Solo hay una palabra se comprueba el genero (Ultima Letra Diferente), sino es igual ya no se continua mirando
							boolean genero = BusquedaAproximadaHelper.esIgualGenero(nombre, nombreComparar);
							//						
							if(genero) {
							 	nombreComparacionBean.setUltimaLetraPalabrasSimples(ConstantesBusqueda.ULTIMA_LETRA_IGUAL);														
							 	//
								// Se comprueba que la primera letra sea igual
								boolean esPrimeraLetraIgual = obtenerSiPrimeraLetraEsIgual (nombre, nombreComparar);
								if(esPrimeraLetraIgual){
									nombreComparacionBean.setPrimeraLetraIgual(ConstantesBusqueda.PRIMERA_LETRA_IGUAL);
									//
									// Se comparan todas las letras del NOMBRE
									this.obtenerInfoNombreLetras (nombreComparacionBean);
									//
									// Se comparan todas las consonantes del NOMBRE
									this.obtenerInfoNombreConsonantes (nombreComparacionBean);
									//
									//  Validacion de los porcentajes obtenidos anteriormente
									this.obtenerPorcentajesValidos(nombreComparacionBean);						
									//
									//nombreComparacionBean.view();
								}		
								else {
									// Primera Letra diferente
									nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_DIFERENTES);
									nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_DIFERENTES);
								}
							}
							else {
								// Genero diferente (Ultima Letra Diferente)
								nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_DIFERENTES);
								nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_DIFERENTES);
							}
						}
					}
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("nombre: " + nombre + ", nombreComparar: " + nombreComparar + ", ");
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return nombreComparacionBean;
	}
		

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private void compararPalabras (NombreComparacionBean nombreComparacionBean) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//			
			String nombre         = nombreComparacionBean.getNombreOriginal_1();
			String nombreComparar = nombreComparacionBean.getNombreOriginal_2();
			//			
			String arrayCadena_1[] = nombre.split(" ");
			String arrayCadena_2[] = nombreComparar.split(" ");
			//			
			for(int i=0 ; i<arrayCadena_1.length ; i++){
				String cadena_1 = Misc.nz(arrayCadena_1[i]);
				//logger.debug("i: " + i + ", cadena_1: " + cadena_1);
				//
				boolean encontrado = false;
				//
				for (int j=0 ; j<arrayCadena_2.length && !encontrado ; j++) {
					String cadena_2 = Misc.nz(arrayCadena_2[j]);
					//
					if(cadena_1.equals(cadena_2)) {
						// Se borra la subcadena de las dos cadenas iniciales
						//
						nombre         = Misc.remplazar(nombre, cadena_1, "");
						nombreComparar = Misc.remplazar(nombreComparar, cadena_1, "");
						//logger.debug("i: " + i + ", cadena_1: " + cadena_1 + " , j: " + j + ", cadena_2: " + cadena_2);
						encontrado = true;
					}
				}
			} 
			//
			nombreComparacionBean.setNombre_1(nombre.trim());
			nombreComparacionBean.setNombre_2(nombreComparar.trim());
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
		
	private void obtenerInfoNombreLetras (NombreComparacionBean nombreComparacionBean) throws Exception {
		//
		int porcentajePosicionIguales_1 = 0;
		int porcentajePosicionIguales_2 = 0;
		int porcentajePosicionIguales   = 0;
		int porcentajeLetrasIguales_1   = 0;
		int porcentajeLetrasIguales_2   = 0;
		int porcentajeLetrasIguales     = 0;		
		//
		try {
			//logger.debug("INICIO");
			//		
			String nombre         = nombreComparacionBean.getNombre_1();
			String nombreComparar = nombreComparacionBean.getNombre_2();
			//		
			// Se comparan de dos maneras las letras de las cadenas
			//
			// 1) Se comprueban por posiciones a ver si coinciden la letras (contadorPosicionIguales)
			int contadorPosicionIguales_1 = this.obtenerPosicionIguales(nombre, nombreComparar);
			int contadorPosicionIguales_2 = this.obtenerPosicionIguales(nombreComparar, nombre);
			// Se obtiene el valor total
			int contadorPosicionIguales     = (contadorPosicionIguales_1 + contadorPosicionIguales_2) / 2;			
			//
			// Se obtienen los porcentajes
			if(contadorPosicionIguales_1 > 0) {
				porcentajePosicionIguales_1 = contadorPosicionIguales_1*100 / nombreComparacionBean.getNombre_1().length();
			}
			if(contadorPosicionIguales_2 > 0) {		
				porcentajePosicionIguales_2 = contadorPosicionIguales_2*100 / nombreComparacionBean.getNombre_2().length();
			}
			// Se obtienen los porcentajes totales
			if(porcentajePosicionIguales_1 > 0 || contadorPosicionIguales_2 > 0) {
				porcentajePosicionIguales   = (porcentajePosicionIguales_1 + porcentajePosicionIguales_2) / 2;
			}
			//
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//
			// 2) Se buscan cuantas letras de una cadena estan en la otra    (contadorLetrasIguales)
			int contadorLetrasIguales_1   = this.obtenerLetrasIguales(nombre, nombreComparar);
			int contadorLetrasIguales_2   = this.obtenerLetrasIguales(nombreComparar, nombre);
			// Se obtiene el valor total	
			int contadorLetrasIguales       = (contadorLetrasIguales_1   + contadorLetrasIguales_2)   / 2;
			//
			// Se obtienen los porcentajes
			if(contadorLetrasIguales_1 > 0) {
				porcentajeLetrasIguales_1   = contadorLetrasIguales_1*100   / nombreComparacionBean.getNombre_1().length();
			}
			if(contadorLetrasIguales_2 > 0) {		
				porcentajeLetrasIguales_2   = contadorLetrasIguales_2*100   / nombreComparacionBean.getNombre_2().length();
			}
			// Se obtienen los porcentajes totales
			if(contadorLetrasIguales_1 > 0 || contadorLetrasIguales_2 > 0) {
				porcentajeLetrasIguales     = (porcentajeLetrasIguales_1   + porcentajeLetrasIguales_2)   / 2;
			}
			//
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//
			// Se almacena los datos obtenidos
			// Posicion
			nombreComparacionBean.setContadorPosicionIguales_1(contadorPosicionIguales_1);
			nombreComparacionBean.setContadorPosicionIguales_2(contadorPosicionIguales_2);
			nombreComparacionBean.setContadorPosicionIguales(contadorPosicionIguales);
			nombreComparacionBean.setPorcentajePosicionIguales_1(porcentajePosicionIguales_1);
			nombreComparacionBean.setPorcentajePosicionIguales_2(porcentajePosicionIguales_2);
			nombreComparacionBean.setPorcentajePosicionIguales(porcentajePosicionIguales);
			// Letras
			nombreComparacionBean.setContadorLetrasIguales_1(contadorLetrasIguales_1);
			nombreComparacionBean.setContadorLetrasIguales_2(contadorLetrasIguales_2);
			nombreComparacionBean.setContadorLetrasIguales(contadorLetrasIguales);
			nombreComparacionBean.setPorcentajeLetrasIguales_1(porcentajeLetrasIguales_1);
			nombreComparacionBean.setPorcentajeLetrasIguales_2(porcentajeLetrasIguales_2);
			nombreComparacionBean.setPorcentajeLetrasIguales(porcentajeLetrasIguales);
			//			
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	private void obtenerInfoNombreConsonantes (NombreComparacionBean nombreComparacionBean) throws Exception {
		//
		int consonantesInclusion             = 0;
		//
		int porcentajeConsonantesOrden_1     = 0;
		int porcentajeConsonantesOrden_2     = 0;
		int porcentajeConsonantesOrden       = 0;
		int porcentajeConsonantesIguales_1   = 0;
		int porcentajeConsonantesIguales_2   = 0;
		int porcentajeConsonantesIguales     = 0;
		//
		try {
			//logger.debug("INICIO");
			//		
			String consonantes_1  = nombreComparacionBean.getCadenaConsonantes1();
			String consonantes_2  = nombreComparacionBean.getCadenaConsonantes2();			
			// Se Comprueban las consonantes
			// 1) Primero se comprueba si el orden estricto de las consonantes es igual
			// 2) Se comprueba si las consonantes estan contenidas en la otras
			// 3) Se comprueba la inclusion
			//
			// 1) 
			//
			int contadorConsonantesOrden_1 = this.obtenerPosicionIguales(consonantes_1, consonantes_2);
			int contadorConsonantesOrden_2 = this.obtenerPosicionIguales(consonantes_2, consonantes_1);
			//			
			// Se obtienen los porcentajes
			if(contadorConsonantesOrden_1 > 0) {
				porcentajeConsonantesOrden_1   = contadorConsonantesOrden_1*100 / consonantes_1.length();
			}
			if(contadorConsonantesOrden_2 > 0) {
				porcentajeConsonantesOrden_2   = contadorConsonantesOrden_2*100 / consonantes_2.length();		
			}
			// Se obtienen los porcentajes totales
			if(porcentajeConsonantesOrden_1 > 0 || porcentajeConsonantesOrden_2 > 0) {
				porcentajeConsonantesOrden   = (porcentajeConsonantesOrden_1 + porcentajeConsonantesOrden_2) / 2;
			}
			//
			// 2)
			//
			// Se obtienen los valores
			int contadorConsonantesIguales_1     = this.obtenerLetrasIguales(consonantes_1, consonantes_2);
			int contadorConsonantesIguales_2     = this.obtenerLetrasIguales(consonantes_2, consonantes_1);		
			// Se obtienen los porcentajes
			if(contadorConsonantesIguales_1 > 0) {
				porcentajeConsonantesIguales_1   = contadorConsonantesIguales_1*100 / consonantes_1.length();
			}
			if(contadorConsonantesIguales_2 > 0) {
				porcentajeConsonantesIguales_2   = contadorConsonantesIguales_2*100 / consonantes_2.length();		
			}
			// Se obtienen los porcentajes totales
			if(porcentajeConsonantesIguales_1 > 0 || contadorConsonantesIguales_2 > 0) {
				porcentajeConsonantesIguales   = (porcentajeConsonantesIguales_1 + porcentajeConsonantesIguales_2) / 2;
			}
			//
			// 3)
			//
			// Se comprueba la INCLUSION de unas consonantes dentro de otras
			if (!Misc.esVacio(consonantes_1) && !Misc.esVacio(consonantes_2)) {
				if (consonantes_1.indexOf(consonantes_2) != -1 || consonantes_2.indexOf(consonantes_1) != -1) {
					consonantesInclusion = 1;
				}
			}			
			//
			//			
			// Se almacena los datos obtenidos
			nombreComparacionBean.setContadorConsonantesOrden_1(contadorConsonantesOrden_1);
			nombreComparacionBean.setContadorConsonantesOrden_2(contadorConsonantesOrden_2);
			nombreComparacionBean.setPorcentajeConsonantesOrden_1(porcentajeConsonantesOrden_1);
			nombreComparacionBean.setPorcentajeConsonantesOrden_2(porcentajeConsonantesOrden_2);
			nombreComparacionBean.setPorcentajeConsonantesOrden(porcentajeConsonantesOrden);
			//
			nombreComparacionBean.setContadorConsonantesIguales_1(contadorConsonantesIguales_1);
			nombreComparacionBean.setContadorConsonantesIguales_1(contadorConsonantesIguales_2);
			nombreComparacionBean.setPorcentajeConsonantesIguales_1(porcentajeConsonantesIguales_1);
			nombreComparacionBean.setPorcentajeConsonantesIguales_2(porcentajeConsonantesIguales_2);
			nombreComparacionBean.setPorcentajeConsonantesIguales(porcentajeConsonantesIguales);
			//
			nombreComparacionBean.setConsonantesInclusion(consonantesInclusion);
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	private void obtenerPorcentajesValidos(NombreComparacionBean nombreComparacionBean) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			int porcentajeLetrasIguales = nombreComparacionBean.getPorcentajeLetrasIguales();
			//
			if(porcentajeLetrasIguales > 85) {
				nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS);
				nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_IDENTIFICADOS);
			}
			//
			int porcentajeConsonantesOrden      = nombreComparacionBean.getPorcentajeConsonantesOrden();
			int porcentajeConsonantesIguales    = nombreComparacionBean.getPorcentajeConsonantesIguales();
			//
			if(porcentajeLetrasIguales > 79 && porcentajeConsonantesOrden > 55 && porcentajeConsonantesIguales > 55) {
				//
				// Comprobamos que las cadenas de consonantes sean iguales, o con una longitud mayor de 2, sino a revision
				//
				int longitudConsonantes_1 = nombreComparacionBean.getCadenaConsonantes1().length();
				int longitudConsonantes_2 = nombreComparacionBean.getCadenaConsonantes2().length();
				//
				if((longitudConsonantes_1 > 2 && longitudConsonantes_2 > 2) || longitudConsonantes_1 == longitudConsonantes_2 ) {
					nombreComparacionBean.setRevisionInt(ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS);
					nombreComparacionBean.setRevision(ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_IDENTIFICADOS);
				}
				//
				//logger.debug("cadenaConsonantes1: " + nombreComparacionBean.getCadenaConsonantes1() + ",tamaño: " + nombreComparacionBean.getCadenaConsonantes1().length());
				//logger.debug("cadenaConsonantes2: " + nombreComparacionBean.getCadenaConsonantes2() + ",tamaño: " + nombreComparacionBean.getCadenaConsonantes2().length());
			}			
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private int obtenerPosicionIguales(String nombre, String nombreComparar) throws Exception {
		//
		int contadorPosicionIguales = 0;
		//
		try {
			//logger.debug("INICIO");
			//
			char[] arrayCaracteres1 = nombre.toCharArray();
			char[] arrayCaracteres2 = nombreComparar.toCharArray();
			//
			for (int i=0;i<arrayCaracteres1.length && i<arrayCaracteres2.length ;i++) {
				//
				char a = arrayCaracteres1[i];
				char b = arrayCaracteres2[i];
				//
				if(a==b) {
					contadorPosicionIguales++;
				}
			}	
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return contadorPosicionIguales;
	}
	
	
	private int obtenerLetrasIguales(String nombre, String nombreComparar) throws Exception {
		//
		int contadorLetrasIguales = 0;
		//
		try {
			//logger.debug("INICIO");
			//
			char[] arrayCaracteres1 = nombre.toCharArray();
			char[] arrayCaracteres2 = nombreComparar.toCharArray();
			//
			for (int i=0;i<arrayCaracteres1.length && i<arrayCaracteres2.length ;i++) {
				//
				char a = arrayCaracteres1[i];
				//char b = arrayCaracteres2[i];
				//
				boolean encontrado = false;
				//
				for (int j=0;j<arrayCaracteres2.length && !encontrado ;j++) {
					char c = arrayCaracteres2[j];
					if(a==c) {
						contadorLetrasIguales++;
						arrayCaracteres2[j] = '$';
						encontrado = true;
					}
				}
			}
			//
			//for (int i=0;i<arrayCaracteres1.length && i<arrayCaracteres2.length ;i++) {
			//	char a = arrayCaracteres1[i];
			//	char b = arrayCaracteres2[i];
				//logger.debug("a: " + a + ", b: " + b);
			//}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return contadorLetrasIguales;
	}

	private void obtenerConsonantes (NombreComparacionBean nombreComparacionBean) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//			
			String consonantes_1  = this.obtenerConsonantes (nombreComparacionBean.getNombre_1());
			String consonantes_2  = this.obtenerConsonantes (nombreComparacionBean.getNombre_2());
			//	
			// Se almacena los datos obtenidos
			nombreComparacionBean.setCadenaConsonantes1(consonantes_1);
			nombreComparacionBean.setCadenaConsonantes2(consonantes_2);
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}	
	
	
	private String obtenerConsonantes (String cadena) throws Exception {
		//
		String consonantes = "";
		//
		try {
			//logger.debug("INICIO");
			//		
			char[] arrayCaracteres = cadena.toCharArray();
			//			
			for (int i=0;i<arrayCaracteres.length;i++) {
				//
				char a = arrayCaracteres[i];
				//
				if(a!='A' && a!='E' && a!='I' && a!='O' && a!='U') {
					consonantes = consonantes + a;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return consonantes;
	}
	

	private boolean obtenerSiPrimeraLetraEsIgual (String nombre, String nombreComparar) throws Exception {
		//
		boolean esPrimeraLetraIgual = false;
		//
		try {
			//logger.debug("INICIO");
			//		
			char caracter         = nombre.charAt(0);
			char caracterComparar = nombreComparar.charAt(0);
			//			
			if(caracter == caracterComparar) {
				esPrimeraLetraIgual = true;
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return esPrimeraLetraIgual;
	}
	
}
