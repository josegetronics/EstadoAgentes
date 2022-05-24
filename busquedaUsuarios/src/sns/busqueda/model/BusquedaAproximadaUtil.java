package sns.busqueda.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import org.apache.log4j.Logger;

import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import sns.util.Misc;


public class BusquedaAproximadaUtil {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static String sustituirSubcadenas(String valor, boolean borrarLetraN) throws Exception {
		//
		try {		
			// logger.debug("INICIO");
			//
			///////////////////////////////////////
			//
			// CONTIENE LA LETRA Ñ  O TRATAMIENTO PARA POSIBLE CARACTER SUSTITUIDO POR Ñ
			if (valor.indexOf(ConstantesBusqueda.LETRA_ENIE) != -1) {
				valor = Misc.remplazar(valor, ConstantesBusqueda.LETRA_ENIE, "");	
			}
			if (borrarLetraN) {
				valor = Misc.remplazar(valor, ConstantesBusqueda.LETRA_ENE, "");
			}
			//logger.debug("CONTIENE LA LETRA Ñ: valor: [" + valor + "]");
			//
			///////////////////////////////////////////////
			//
			// EMPIEZAN POR X
			//
			if (valor.startsWith(ConstantesBusqueda.LETRA_EQUIS)) {
				valor = BusquedaAproximadaUtil.quitarCadenaIgualEquis (valor);
			}
			//logger.debug("EMPIEZAN POR X: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			// EMPIEZAN POR LETRA+PUNTO O  LETRA+ESPACIO
			//
			valor = BusquedaAproximadaUtil.quitarPrimeraParte (InicializacionBusqueda.MAP_LETRAS, valor);
			//logger.debug("EMPIEZAN POR LETRA+PUNTO O  LETRA+ESPACIO: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			//   PARTICULAS 
			//
			valor = BusquedaAproximadaUtil.quitarParticulas(InicializacionBusqueda.MAP_LETRAS_PARTICULAS, valor);
			//logger.debug("PARTICULAS: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			// CARACTER PUNTO O ESPACIO EN BLANCO Y CADENAS ESPECIALES
			//			
			valor = BusquedaAproximadaUtil.quitarCaraceteres (InicializacionBusqueda.MAP_LETRAS_CADENAS_ESPECIALES, valor);
			//logger.debug("CARACTER PUNTO O ESPACIO EN BLANCO: valor: [" + valor + "]");
			//
			///////////////////////////////////////
			//
			//   VOCALES MAL CODIFICADAS
			//
			valor = BusquedaAproximadaUtil.quitarCaraceteres (InicializacionBusqueda.MAP_LETRAS_VOCALES, valor);
			//logger.debug("VOCALES: valor: [" + valor + "]");
			//
			/////////////////////////////////////////////
			//
			//  CARACTERES REPETIDOS
			//
			valor = BusquedaAproximadaUtil.quitarCaraceteres (InicializacionBusqueda.MAP_LETRAS_REPETIDAS, valor);
			//logger.debug("CARACTERES REPETIDOS: valor: [" + valor + "]");
			//
			/////////////////////////////////////////
			// 
			//  CARACTERES EXTRAÑOS 
			//
			valor = BusquedaAproximadaUtil.quitarCaraceteres (InicializacionBusqueda.MAP_LETRAS_CARACTERES_EXTRA, valor);
			//logger.debug("CARACTERES EXTRAÑOS: valor: [" + valor + "]");
			//
			/////////////////////////////////////////////
			//
			//  CONFIGURAR
			// 
			valor = BusquedaAproximadaUtil.quitarCaraceteres (InicializacionBusqueda.MAP_LETRAS_PRE_CONFIGURAR, valor);
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
				String cadenaLetraPunto   = letra + ConstantesBusqueda.ESPACIO_EN_BLANCO;
				String cadenaLetraEspacio = letra + ConstantesBusqueda.CARACTER_PUNTO;
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
			Set <String> setLetraParticulas           = InicializacionBusqueda.MAP_LETRAS_PARTICULAS.keySet();
			Iterator <String> iteratorLetraParticulas = setLetraParticulas.iterator();
			//
			while (iteratorLetraParticulas.hasNext()) {
				String particula   = (String) iteratorLetraParticulas.next();
				String valorLetra  = (String) InicializacionBusqueda.MAP_LETRAS_PARTICULAS.get(particula);
				//
				String prefijo = particula  +  ConstantesBusqueda.ESPACIO_EN_BLANCO;
				String inter   = ConstantesBusqueda.ESPACIO_EN_BLANCO + particula  +  ConstantesBusqueda.ESPACIO_EN_BLANCO;
				String sufijo  = ConstantesBusqueda.ESPACIO_EN_BLANCO + particula;
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
		
	
	public static boolean comprobarFecha (String fechaNac, String fechaNacComprobar) throws Exception {
		//
		boolean iguales = false;
		//
		try {
			//logger.debug("INICIO");
			//
			if(!Misc.esVacio(fechaNac) && !Misc.esVacio(fechaNacComprobar) && fechaNac.length()>9 && fechaNacComprobar.length()>9) {
				//
				int dia    = Integer.parseInt(Misc.nz(fechaNac.substring(8, 10)));
				int diaTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(8, 10)));
				//
				int mes    = Integer.parseInt(Misc.nz(fechaNac.substring(5, 7)));
				int mesTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(5, 7)));
				//
				int año    = Integer.parseInt(Misc.nz(fechaNac.substring(0, 4)));
				int añoTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(0, 4)));
				//
				//logger.debug("fechaNac:             " + fechaNac);
				//logger.debug("dia:    " + dia    + ", mes:    " + mes    + ", año:    " + año);
				//logger.debug("fechaNacComprobar:    " + fechaNacComprobar);
				//logger.debug("diaTes: " + diaTes + ", mesTes: " + mesTes + ", añoTes: " + añoTes);
				//
				int diferenciaDias = 0;
				int diferenciaMes  = 0; 
				int diferenciaAño  = 0;
				//
				if(dia>diaTes) {
					diferenciaDias = dia-diaTes;
				}
				else {
					diferenciaDias = diaTes-dia;
				}
				//
				if(mes>mesTes) {
					diferenciaMes = mes-mesTes;
				}
				else {
					diferenciaMes = mesTes-mes;
				}
				//
				if(mes>mesTes) {
					diferenciaAño = año-añoTes;
				}
				else {
					diferenciaAño = añoTes-año;
				}
				//
				if(diferenciaAño<InicializacionBusqueda.BA_FECHA_NUMERO_MAX_ANIOS &&  mes==mesTes      &&  dia==diaTes) {
					return true;
				}
				if(año==añoTes      &&  diferenciaMes<InicializacionBusqueda.BA_FECHA_NUMERO_MAX_MESES  &&  dia==diaTes) {
					return true;
				}
				if (año==añoTes     &&  mes==mesTes      &&  diferenciaDias<InicializacionBusqueda.BA_FECHA_NUMERO_MAX_DIAS) {
					return true;
				}	
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return iguales;
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
	

}
