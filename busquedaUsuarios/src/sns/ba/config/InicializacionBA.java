package sns.ba.config;

import gasai.util.Misc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.apache.log4j.Logger;


public class InicializacionBA {
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(InicializacionBA.class);
	private static Logger logger             = Logger.getLogger(InicializacionBA.LOGGER_NAME);
	private static String PATH_REPO          = "D:/Procesos/BusquedaAproximacion/";
	public static final String LOGGER_NAME   = "busqueda";
	////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//  VARIABLES DEL PROPERTIES  
	public static String BUSQUEDA_APROXIMADA_SEXO                            = "";
	public static String BUSQUEDA_APROXIMADA_FECHA                           = "";
	//
	public static int    BA_FECHA_NUMERO_MAX_DIAS                            = 0;
	public static int    BA_FECHA_NUMERO_MAX_MESES                           = 0;
	public static int    BA_FECHA_NUMERO_MAX_ANIOS                           = 0;
	//
	public static int    VALOR_CAMPO_IGUAL_CAMPO            	             = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO                        = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO_SEXO                   = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO_FECHA                  = 0;
	//
	public static int    CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE         = 0;
	public static int    CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS      = 0;
	public static String CAMPOS_OBLIGATORIOS                                 = ""; 
	//  VARIABLES DEL PROPERTIES  
	//	
	//
	public static LinkedHashMap <String, Properties> MAP_PROPERTIES             = new LinkedHashMap <String, Properties> ();
	//
	//
	public static LinkedHashMap <String, String> MAP_CAMPOS_OBLIGATORIOS        = new LinkedHashMap <String, String> ();
	// 
	public static LinkedHashMap <String, String> MAP_LETRAS                     = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_CADENAS_ESPECIALES  = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_VOCALES             = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_REPETIDAS           = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_CARACTERES_EXTRA    = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_PRE_CONFIGURAR      = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_LETRAS_PARTICULAS          = new LinkedHashMap <String, String> ();

	
	
	public static void init() {
		//
		try {
			logger.debug("INICIO");
			//
			cargarConfiguracion("");
			cargarCamposObligatorios();
			//
			cargarLetras();
			cargarCadenasEspeciales();
			cargarRepetidos();
			cargarVocales();			
			cargarCaracteresConfigurar();
			cargarCaracteresExt();
			cargarParticulas();
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Cargando la configuracion inicial", e);
		}
	}
	

	public static void cargarConfiguracion(String path) throws IOException, Exception {
		//
		Properties propertiesTraspasos = new Properties();
		Properties propertiesVinculacionInssToSns = new Properties();
		Properties propertiesVinculacionSnsToInss = new Properties();
		InputStream defaultStream = null;	
		FileInputStream fileInputStream = null;
		//
		try {
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			logger.debug("INICIO");
			/**
			 * para la configuracion de traspasos 
			 */
			fileInputStream = new FileInputStream(InicializacionBA.PATH_REPO + "/config/ba/" + ConstantesBA.FILE_CONFIG_TRASPASOS);
			propertiesTraspasos.load(fileInputStream);
			fileInputStream.close();

			/**
			 * para la configuracion de la vinculacion del INSS al SNS
			 */
			fileInputStream = new FileInputStream(InicializacionBA.PATH_REPO + "/config/ba/" + ConstantesBA.FILE_CONFIG_VINCULACION_INSS_TO_SNS);
			propertiesVinculacionInssToSns.load(fileInputStream);
			fileInputStream.close();
			/**
			 * para la configuracion de la vinculacion del SNS al INSS
			 */
			fileInputStream = new FileInputStream(InicializacionBA.PATH_REPO + "/config/ba/" + ConstantesBA.FILE_CONFIG_VINCULACION_SNS_TO_INSS);
			propertiesVinculacionSnsToInss.load(fileInputStream);
			fileInputStream.close();
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////
			//
			MAP_PROPERTIES.put(ConstantesBA.BA_CONFIG_TRASPASOS,               propertiesTraspasos);
			MAP_PROPERTIES.put(ConstantesBA.BA_CONFIG_VINCULACION_INSS_TO_SNS, propertiesVinculacionInssToSns);
			MAP_PROPERTIES.put(ConstantesBA.BA_CONFIG_VINCULACION_SNS_TO_INSS, propertiesVinculacionSnsToInss);
			//
			logger.debug("FIN");
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if (defaultStream != null)
				defaultStream.close();
		}
	}	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	static {
		sns.ba.config.InicializacionBA.init();
	}	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private static void cargarCamposObligatorios () throws Exception {
		try {
			LinkedHashMap <String, String> map = new LinkedHashMap <String, String> ();
			//	
			if(!Misc.esVacio(InicializacionBA.CAMPOS_OBLIGATORIOS)) {
				String [] subCadena = InicializacionBA.CAMPOS_OBLIGATORIOS.split("\\|");
				//
				for (int i=0 ; i<subCadena.length ; i++) {
					map.put(Misc.nz(subCadena[i]), Misc.nz(subCadena[i]));
				}	
			}
			//
			InicializacionBA.MAP_CAMPOS_OBLIGATORIOS = map;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarLetras () throws Exception {
		try {
			LinkedHashMap <String, String> mapLetras = new LinkedHashMap <String, String>();
			mapLetras.put("A", "");
			mapLetras.put("B", "");
			mapLetras.put("C", "");
			mapLetras.put("D", "");
			mapLetras.put("E", "");
			mapLetras.put("F", "");
			mapLetras.put("G", "");
			mapLetras.put("H", "");
			mapLetras.put("I", "");
			mapLetras.put("J", "");
			mapLetras.put("K", "");
			mapLetras.put("L", "");
			mapLetras.put("M", "");
			mapLetras.put("N", "");
			mapLetras.put("O", "");
			mapLetras.put("P", "");
			mapLetras.put("Q", "");
			mapLetras.put("R", "");
			mapLetras.put("S", "");
			mapLetras.put("T", "");
			mapLetras.put("U", "");
			mapLetras.put("V", "");
			mapLetras.put("W", "");
			mapLetras.put("X", "");
			mapLetras.put("Y", "");
			mapLetras.put("Z", "");
			//
			MAP_LETRAS = mapLetras;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarCadenasEspeciales () throws Exception {
		try {
			LinkedHashMap <String, String> mapCadenasEspeciales = new LinkedHashMap <String, String>();
			mapCadenasEspeciales.put("FCO", "");
			mapCadenasEspeciales.put(".",   "");
			mapCadenasEspeciales.put("-",   "");
			mapCadenasEspeciales.put("_",   "");
			mapCadenasEspeciales.put(" ",   "");
			//
			MAP_LETRAS_CADENAS_ESPECIALES = mapCadenasEspeciales;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarVocales () throws Exception {
		try {
			LinkedHashMap <String, String> mapVocales = new LinkedHashMap <String, String>();
			mapVocales.put("Ü", "U");
			mapVocales.put("Á", "A");
			mapVocales.put("É", "E");
			mapVocales.put("Í", "I");
			mapVocales.put("Ó", "O");
			mapVocales.put("Ú", "U");
			//
			mapVocales.put("À", "A");
			mapVocales.put("È", "E");
			mapVocales.put("Ì", "I");
			mapVocales.put("Ò", "O");
			mapVocales.put("Ù", "U");
			//
			mapVocales.put("Ä", "A");
			mapVocales.put("Ë", "E");
			mapVocales.put("Ï", "I");
			mapVocales.put("Ö", "O");
			mapVocales.put("Ü", "U");
			//
			mapVocales.put("Â", "A");
			mapVocales.put("Ê", "E");
			mapVocales.put("Î", "I");
			mapVocales.put("Ô", "O");
			mapVocales.put("Û", "U");
			//
			MAP_LETRAS_VOCALES = mapVocales;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarRepetidos () throws Exception {
		try {
			LinkedHashMap <String, String> mapRepetidos = new LinkedHashMap <String, String>();
			mapRepetidos.put("AAA", "A");
			mapRepetidos.put("BBB", "B");
			mapRepetidos.put("CCC", "C");
			mapRepetidos.put("DDD", "D");
			mapRepetidos.put("EEE", "E");
			mapRepetidos.put("FFF", "F");
			mapRepetidos.put("GGG", "G");
			mapRepetidos.put("III", "I");
			mapRepetidos.put("JJJ", "J");
			mapRepetidos.put("KKK", "K");
			mapRepetidos.put("LLL", "L");
			mapRepetidos.put("MMM", "M");
			mapRepetidos.put("NNN", "N");
			mapRepetidos.put("OOO", "O");
			mapRepetidos.put("PPP", "P");
			mapRepetidos.put("QQQ", "Q");
			mapRepetidos.put("RRR", "R");
			mapRepetidos.put("SSS", "S");
			mapRepetidos.put("TTT", "T");
			mapRepetidos.put("UUU", "U");
			mapRepetidos.put("VVV", "V");
			mapRepetidos.put("YYY", "Y");
			mapRepetidos.put("XXX", "X");
			mapRepetidos.put("ZZZ", "Z");
			//
			mapRepetidos.put("AA", "A");
			mapRepetidos.put("BB", "B");
			mapRepetidos.put("CC", "C");
			mapRepetidos.put("DD", "D");
			mapRepetidos.put("EE", "E");
			mapRepetidos.put("FF", "F");
			mapRepetidos.put("GG", "G");
			mapRepetidos.put("HH", "H");
			mapRepetidos.put("II", "I");
			mapRepetidos.put("JJ", "J");
			mapRepetidos.put("KK", "K");
			mapRepetidos.put("LL", "L");
			mapRepetidos.put("MM", "M");
			mapRepetidos.put("NN", "N");
			mapRepetidos.put("OO", "O");
			mapRepetidos.put("PP", "P");
			mapRepetidos.put("QQ", "Q");
			mapRepetidos.put("RR", "R");
			mapRepetidos.put("SS", "S");
			mapRepetidos.put("TT", "T");
			mapRepetidos.put("UU", "U");
			mapRepetidos.put("VV", "V");
			mapRepetidos.put("YY", "Y");
			mapRepetidos.put("XX", "X");
			mapRepetidos.put("ZZ", "Z");
			//
			MAP_LETRAS_REPETIDAS = mapRepetidos;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarCaracteresExt () throws Exception {
		try {
			LinkedHashMap <String, String> mapCaracteresExtra = new LinkedHashMap <String, String>();
			mapCaracteresExtra.put("?", "");
			mapCaracteresExtra.put("ñ", "");
			mapCaracteresExtra.put("ª", "");
			mapCaracteresExtra.put("º", "");
			mapCaracteresExtra.put("'", " ");
			mapCaracteresExtra.put("Ã`", "");
			mapCaracteresExtra.put("I¿½", "");
			mapCaracteresExtra.put("¿", "");
			mapCaracteresExtra.put("¿", "");
			mapCaracteresExtra.put("½", "");
			mapCaracteresExtra.put("ï", "");
			mapCaracteresExtra.put("*", "");
			mapCaracteresExtra.put("¥", "");
			mapCaracteresExtra.put("Ê", "");
			mapCaracteresExtra.put("@", "");
			mapCaracteresExtra.put("§", "");
			mapCaracteresExtra.put("¦", "");
			mapCaracteresExtra.put("+", "");
			mapCaracteresExtra.put("^", "");
			mapCaracteresExtra.put("Ð", "");
			mapCaracteresExtra.put("µ", "");
			mapCaracteresExtra.put("!", "");
			mapCaracteresExtra.put("¡", "");
			mapCaracteresExtra.put("û", "");
			mapCaracteresExtra.put("", "");
			mapCaracteresExtra.put("å", "");
			mapCaracteresExtra.put("ê", "");
			mapCaracteresExtra.put("ü", "");
			mapCaracteresExtra.put("¨", "");
			mapCaracteresExtra.put("¤", "");
			mapCaracteresExtra.put("ø", "");
			mapCaracteresExtra.put("×", "");
			mapCaracteresExtra.put("?", "");
			mapCaracteresExtra.put("Ì", "");
			mapCaracteresExtra.put("~", "");
			mapCaracteresExtra.put("Ø", "");
			mapCaracteresExtra.put(">", "");
			mapCaracteresExtra.put("", "");
			mapCaracteresExtra.put("=", "");
			mapCaracteresExtra.put("Å", "");
			mapCaracteresExtra.put("$", "");
			mapCaracteresExtra.put("Ã", "");
			mapCaracteresExtra.put("ý", "");
			mapCaracteresExtra.put("ã", "");
			mapCaracteresExtra.put("é", "");
			mapCaracteresExtra.put("%", "");
			mapCaracteresExtra.put("<", "");
			mapCaracteresExtra.put("ç", "");
			mapCaracteresExtra.put("]", "");
			mapCaracteresExtra.put("å", "");
			mapCaracteresExtra.put("[", "");
			mapCaracteresExtra.put("&", "");
			mapCaracteresExtra.put("´", "");
			mapCaracteresExtra.put("\"", "");
			mapCaracteresExtra.put("'", "");
			mapCaracteresExtra.put("{", "");
			mapCaracteresExtra.put("`", "");
			//
			MAP_LETRAS_CARACTERES_EXTRA = mapCaracteresExtra;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarCaracteresConfigurar () throws Exception {
		try {
			LinkedHashMap <String, String> mapCaracteresConf = new LinkedHashMap <String, String>();
			//mapCaracteresConf.put("GU", "G");         
			//mapCaracteresConf.put("GÜ", "G");         
			//mapCaracteresConf.put("H", "");           
			mapCaracteresConf.put("G",    "");          
			mapCaracteresConf.put("J",    "");          
			mapCaracteresConf.put("FCO ", "");        
			//                   
			mapCaracteresConf.put("TX", "");          
			mapCaracteresConf.put("TS", "");          
			mapCaracteresConf.put("CH", "");          
			mapCaracteresConf.put("ç",  "C");
			//
			mapCaracteresConf.put("Ç",  "C");          
			//
			MAP_LETRAS_PRE_CONFIGURAR = mapCaracteresConf;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}

	
	private static void cargarParticulas () throws Exception {
		try {
			LinkedHashMap <String, String> mapParticulas = new LinkedHashMap <String, String>();
			//
			mapParticulas.put("DE EL", "");
			mapParticulas.put("DE LA", "");
			mapParticulas.put("DE LO", "");
			mapParticulas.put("DE LAS", "");
			mapParticulas.put("DE LOS", "");
			//
			mapParticulas.put("DA",  "");
			mapParticulas.put("DE",  "");          
			mapParticulas.put("DEL", "");          
			mapParticulas.put("EL",  "");      
			mapParticulas.put("LA",  "");          
			mapParticulas.put("LO",  "");          
			mapParticulas.put("LAS", ""); 
			mapParticulas.put("LOS", "");
			//
			MAP_LETRAS_PARTICULAS = mapParticulas;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
}
