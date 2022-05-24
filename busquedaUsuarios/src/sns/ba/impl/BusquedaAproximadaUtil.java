package sns.ba.impl;

import gasai.util.Misc;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import org.apache.log4j.Logger;
import sns.ba.config.ConstantesBA;
import sns.ba.config.InicializacionBA;


public class BusquedaAproximadaUtil {

	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(BusquedaAproximadaUtil.class);
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static String quitarCaraceteres (LinkedHashMap <String, String> mapCaracteres, String  cadena) throws Exception {
		//
		try{
			Set <String> set           = mapCaracteres.keySet();
			Iterator <String> iterator = set.iterator();
			//		
			while (iterator.hasNext()) {
				String letra      = (String) iterator.next();
				String valorLetra = (String) mapCaracteres.get(letra);
				//
				if (cadena.indexOf(letra) != -1) {
					cadena = Misc.remplazar(cadena, letra, valorLetra);
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return cadena;
	}
	
	
	public static String quitarPrimeraParte (LinkedHashMap <String, String> mapCaracteres, String  cadena) throws Exception {
		//
		try{
			Set <String> set           = mapCaracteres.keySet();
			Iterator <String> iterator = set.iterator();
			//		
			while (iterator.hasNext()) {
				String letra      = (String) iterator.next();
				//
				// Se construyen las cadenas de comparacion 
				String cadenaLetraPunto   = letra + ConstantesBA.ESPACIO_EN_BLANCO;
				String cadenaLetraEspacio = letra + ConstantesBA.CARACTER_PUNTO;
				//
				//
				if (cadena.startsWith(cadenaLetraPunto) || cadena.startsWith(cadenaLetraEspacio)) {
					cadena = cadena.substring(letra.length()+1, cadena.length());
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return cadena;
	}
	
	
	public static String quitarParticulas (LinkedHashMap <String, String> mapCaracteres, String  cadena) throws Exception {
		//
		try{
			Set <String> setLetraParticulas           = InicializacionBA.MAP_LETRAS_PARTICULAS.keySet();
			Iterator <String> iteratorLetraParticulas = setLetraParticulas.iterator();
			//
			while (iteratorLetraParticulas.hasNext()) {
				String particula   = (String) iteratorLetraParticulas.next();
				String valorLetra  = (String) InicializacionBA.MAP_LETRAS_PARTICULAS.get(particula);
				//
				String prefijo = particula  +  ConstantesBA.ESPACIO_EN_BLANCO;
				String inter   = ConstantesBA.ESPACIO_EN_BLANCO + particula  +  ConstantesBA.ESPACIO_EN_BLANCO;
				String sufijo  = ConstantesBA.ESPACIO_EN_BLANCO + particula;
				//
				if (cadena.indexOf(particula) != -1) {
					//
					if (cadena.startsWith(prefijo)) {
						cadena = cadena.substring(prefijo.length(), cadena.length());
						//logger.error("particula: [" + prefijo + "]");
					}
					//
					if (cadena.endsWith(sufijo)) {
						cadena = cadena.substring(0, cadena.length() - sufijo.length());
						//logger.error("particula: [" + sufijo + "]");
					}
					//
					if (cadena.indexOf(inter) != -1) {
						cadena = Misc.remplazar(cadena, inter, valorLetra);
						//logger.error("particula: [" + inter + "]");
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return cadena;
	}
			
	
	public static String quitarCadenaIgualEquis (String cadena) throws Exception {
		//
		String cadenaAux = "";
		//
		try{
			boolean mismaLetra = true;
			//
			cadenaAux = Misc.nz(cadena).toUpperCase();
			//
			char [] array = cadenaAux.toCharArray();
			for(int i=0 ; i<array.length ; i++){
				if(array[i] != 'X'){
					mismaLetra = false;
				}
			}
			if(mismaLetra){
				cadenaAux = "";
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return cadenaAux;
	}
	
	
	public static boolean comprobarLetraN (String cadena, String cadenaComparar) throws Exception {
		//
		boolean borrarLetraN = false;
		//
		try{
			//
			// Es el caso en el que una de las dos cadenas contiene una Ñ
			//
			if(cadena.indexOf(ConstantesBA.LETRA_ENIE)!=-1 || cadenaComparar.indexOf(ConstantesBA.LETRA_ENIE)!=-1){
				//
				if(!cadena.equals(cadenaComparar)) {
					//logger.error("diferentes");
					//
					if(cadena.length() ==  cadenaComparar.length()) {
						//logger.error("Mismo ancho");
						//
						borrarLetraN = true;
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return borrarLetraN;
	}	
	
	
	public static String sustituirSubcadenas(String valor, boolean borrarLetraN) throws Exception {
		//
		try {		
			// logger.debug("INICIO");
			//
			///////////////////////////////////////
			//
			// CONTIENE LA LETRA Ñ  O TRATAMIENTO PARA POSIBLE CARACTER SUSTITUIDO POR Ñ
			if (valor.indexOf(ConstantesBA.LETRA_ENIE) != -1) {
				valor = Misc.remplazar(valor, ConstantesBA.LETRA_ENIE, "");	
			}
			if (borrarLetraN) {
				valor = Misc.remplazar(valor, ConstantesBA.LETRA_ENE, "");
			}
			//logger.debug("CONTIENE LA LETRA Ñ: valor: [" + valor + "]");
			//
			///////////////////////////////////////////////
			//
			// EMPIEZAN POR X
			//
			if (valor.startsWith(ConstantesBA.LETRA_EQUIS)) {
				valor = quitarCadenaIgualEquis (valor);
			}
			//logger.debug("EMPIEZAN POR X: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			// EMPIEZAN POR LETRA+PUNTO O  LETRA+ESPACIO
			//
			valor = quitarPrimeraParte (InicializacionBA.MAP_LETRAS, valor);
			//logger.debug("EMPIEZAN POR LETRA+PUNTO O  LETRA+ESPACIO: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			//   PARTICULAS 
			//
			valor = quitarParticulas(InicializacionBA.MAP_LETRAS_PARTICULAS, valor);
			//logger.debug("PARTICULAS: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			// CARACTER PUNTO O ESPACIO EN BLANCO Y CADENAS ESPECIALES
			//			
			valor = quitarCaraceteres (InicializacionBA.MAP_LETRAS_CADENAS_ESPECIALES, valor);
			//logger.debug("CARACTER PUNTO O ESPACIO EN BLANCO: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			//   VOCALES MAL CODIFICADAS
			//
			valor = quitarCaraceteres (InicializacionBA.MAP_LETRAS_VOCALES, valor);
			//logger.debug("VOCALES: valor: [" + valor + "]");
			//
			/////////////////////////////////////////////
			//
			//  CARACTERES REPETIDOS
			//
			valor = quitarCaraceteres (InicializacionBA.MAP_LETRAS_REPETIDAS, valor);
			//logger.debug("CARACTERES REPETIDOS: valor: [" + valor + "]");
			//
			/////////////////////////////////////////
			// 
			//  CARACTERES EXTRAÑOS 
			//
			valor = quitarCaraceteres (InicializacionBA.MAP_LETRAS_CARACTERES_EXTRA, valor);
			//logger.debug("CARACTERES EXTRAÑOS: valor: [" + valor + "]");
			//
			/////////////////////////////////////////////
			//
			//  CONFIGURAR
			// 
			valor = quitarCaraceteres (InicializacionBA.MAP_LETRAS_PRE_CONFIGURAR, valor);
			//logger.debug("CONFIGURAR: valor: [" + valor + "]");
			//
			// logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return valor;
	}
	
	
	
/*	
 
 	// Metodos Jose Maria
 
	public static boolean esAproximadoSexo(String codSexo, String codSexoComparar, boolean esNombreIgual) throws Exception {
		//
		boolean codSexoIgual   = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba el Sexo aproximado
			if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && esNombreIgual) {
					codSexoIgual = true;
			} // Se podria quitar el else
			else {
				if (codSexo.equals(codSexoComparar)) {
					codSexoIgual = true;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return codSexoIgual;
	}
	
	
	public static boolean esAproximadaCadena(String cadena, String cadenaComparar, String campo) throws Exception {
		//
		boolean cadenaIgual  = false;
		boolean borrarLetraN = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
			borrarLetraN = comprobarLetraN(cadena, cadenaComparar);
			//
			if(!Misc.esVacio(cadena)) {
				cadena         = sustituirSubcadenas(Misc.nz(cadena), borrarLetraN);
			}
			if(!Misc.esVacio(cadenaComparar)) {
				cadenaComparar = sustituirSubcadenas(Misc.nz(cadenaComparar), borrarLetraN);
			}
			//
			// Se compara APROXIMADO, sino se mira la inclusion
			//
			if (cadena.equals(cadenaComparar)) {
				cadenaIgual = true;
			}
			else {
				// INCLUSION del nombre 
				if (!cadenaIgual && !Misc.esVacio(cadena) && !Misc.esVacio(cadenaComparar) && Misc.nz(campo).equals(ConstantesBA.CADENA_NOMBRE_COMPARACION) ) {
					//
					if (cadena.indexOf(cadenaComparar) != -1 || cadenaComparar.indexOf(cadena) != -1) {
						cadenaIgual = true;
					}
				}
			}
			//
			logger.error("cadena: " + cadena + ", cadenaComparar: " + cadenaComparar + ", borrarLetraN:  " + borrarLetraN);
			//
			// logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return cadenaIgual;
	}
	
	
	public static boolean esAproximadoSexo(String codSexo, String codSexoComparar, String cadena, String cadenaComparar) throws Exception {
		//
		boolean codSexoIgual   = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba si el nombre es igual
			boolean esNombreIgual = esAproximadaCadena(cadena, cadenaComparar, ConstantesBA.CADENA_NOMBRE_COMPARACION);
			//
			// Se comprueba el Sexo aproximado
			if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && esNombreIgual) {
					codSexoIgual = true;
			} // Se podria quitar el else
			else {
				if (codSexo.equals(codSexoComparar)) {
					codSexoIgual = true;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return codSexoIgual;
	}	
*/	

	
}
