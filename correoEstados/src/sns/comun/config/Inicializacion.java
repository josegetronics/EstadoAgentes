package sns.comun.config;

import gasai.util.Misc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import sns.comun.util.Util;

public class Inicializacion {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	public static final String LOGGER_NAME          = "AlertaAgentesLog";

	public static final String FICHEROALERTAS = "ALERTACORREOESTADOS.properties";
	//public static final String PATH_LOCAL = "//Nas12-03.sanidad.msc/desarrollo/Tarjeta/09 - Varios/Soporte/Correo Estado Agentes/HERRAMIENTAS/";
	public static final String PATH_LOCAL = "C:/EstadoAgentes/";
	public static String pathDirCambiosTraspasos = "";
	public static String pathFicheroEstadisticas = "";
	public static String pathFicheroTraspasosRealizados = "";
	public static int numCambiosVisualizar  = -1;
	public static int numCambiosVisualizarDos = -1;
	public static int numDeTraspasosXml = 0;
	
	public static String asunto = "";
	public static String smtp = "";
	public static String mensajeEnteroCambiosTraspasos = "";
	public static String mensajeCambiosTraspasosBusquedaAproximada = "";

	public static String destinatarios = "";
	public static String mensajeEntero = "";
	public static String mensajeEnteroBloqueos = "";
	
	//INFORMACION QUE SE PINTA EN EL CORREO
	public static String TRASPASOS_NUEVOS = "";
	public static String TRASPASOS = "";
	public static String TRASPASOS_BUSQUEDA_APROXIMADA = "";
	public static String ESTADO_AGENTES = "";
	public static String BLOQUEADOS = "";

	// TSI
	public static String ipServidor = "";
	public static String sid = "";
	public static String puerto = "";
	public static String usuario = "";
	public static String password = "";

	// INTERCAMBIADOR
	public static String ipServidorInter = "";
	public static String sidInter = "";
	public static String puertoInter = "";
	public static String usuarioInter = "";
	public static String passwordInter = "";

	public static String operacionesAsincronas = "";
	public static String operacionesSincronas = "";
	
	public static final String SEPARATOR_WRITER = ";";

	public static Vector codAgentes = new Vector();

	public static Vector ultimaOpAsincrona = new Vector();
	public static Vector ultimaOpAsincronaAux = new Vector();
	public static Vector ultimaOpSincrona = new Vector();
	public static Vector ultimaOpSincronaAux = new Vector();

	public static final String SUFIJO_NOMBRE_FICHERO_BLOQUEOS = "_bloqueos.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_MISMA_CA= "_mismaCA.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_COD_SNS_BUSQUEDA = "_cambioCA.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_ALTAS_INDEBIDAS = "_altasTitulosNA.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_ALTAS_MUFACE = "_altasMuface.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_RESUMEN_N012 = "_resumenMensajeriaN012.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_ALTAS_EXTRANJEROS_2_20 = "_altasExtranjeroDosVeinte.csv";
	public static final String SUFIJO_NOMBRE_FICHERO_ALTAS_SNS_GENERADAS = "_altasCodigoSNSGenerados.csv";
	
	public static final String[] LITERALAGENTES = {
		"00.- AGENTE DE TESTEO DEL SNS",
		"01.- SEGURIDAD SOCIAL",
		"02.- SERVICIO ANDALUZ DE SALUD. SAS",
		"03.- SERVICIO CANARIO DE SALUD",
		"04.- INSTITUTO CATALAN DE LA SALUD. ICS",
		"05.- SERVICIO GALLEGO DE SALUD. SERGAS",
		"06.- SERVICIO NAVARRO DE SALUD. OSASUNBIDEA",
		"07.- SERVICIO VASCO DE SALUD. OSAKIDETZA",
		"08.- SERVICIO VALENCIANO DE SALUD. SERVASA",
		"09.- MUFACE",
		"10.- MUGEJU",
		"11.- ISFAS",
		"12.- INSALUD -> snsinsalud.insalud.es/msc/msc.asp",
		"13.- SERVICIO ARAGONES DE SALUD. SALUD -> mirambel.salud.aragob.es/msc/msc.asp",
		"14.- SERVICIO DE SALUD DEL PRINCIPADO DE ASTURIAS -> sespasns.sespa.es/msc/msc.asp",
		"15.- SERVEI DE SALUT DES ILLES BALEARS. IBSALUT -> lappits1.caib.es/msc/msc.asp",
		"16.- SERVICIO CANTABRO DE SALUD -> scsalud3.scsalud.es/msc/msc.asp",
		"17.- SERVICIO DE SALUD DE CASTILLA-LA MANCHA. SESCAM -> 10.60.16.7/msc/msc.asp",
		"18.- SANIDAD DE CASTILLA Y LEON. SACYL -> grsgscvant0101.sacyl.es/msc/msc.asp",
		"19.- INGESA -> snsinsalud.insalud.es/msc/msc.asp",
		"20.- SERVICIO EXTREMEÑO DE SALUD. SES -> 10.165.4.42/msc/msc.asp",
		"21.- SERVICIO RIOJANO DE SALUD -> tsi1_larioja.seris.es/msc/msc.asp",
		"22.- SERVICIO MURCIANO DE SALUD -> filemon.carm.es/msc/msc.asp",
		"23.- CONSEJERIA DE SANIDAD DE LA COMUNIDAD DE MADRID -> salud.madrid.org/msc/msc.asp" };

	// MAP
	public static LinkedHashMap<String, String> MAP_COMUNIDADES = new LinkedHashMap<String, String>();
	public static LinkedHashMap<Integer, Integer> MAP_RELACION_COMUNIDADES = new LinkedHashMap<Integer, Integer>();
	public static LinkedHashMap<String, String> MAP_CAMPOS = new LinkedHashMap<String, String>();

	public static void init() {
		try {
			cargarConfiguracion("");
			cargarMapComunidades();
			cargarMapRelacionComunidades();
			cargarMapCampos();

		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	public static void cargarConfiguracion(String path) throws Exception {
		String operacionesAsinAux = "";
		String operacionesSinAux = "";
		String codAgentesAux = "";
		String ultOperacionAsinAux = "";
		String ultOperacionSinAux = "";
		//
		InputStream inputStream = null;
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		//
		try {
			//
			logger.debug("INICIO");
			ClassLoader classLoader = Inicializacion.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream(Inicializacion.FICHEROALERTAS);
			//
			if (inputStream == null) {
				if (!Misc.esVacio(path)) {
					fileInputStream = new FileInputStream(path);
					properties.load(fileInputStream);
					fileInputStream.close();
				} else {
					fileInputStream = new FileInputStream(PATH_LOCAL + FICHEROALERTAS);
					properties.load(fileInputStream);
					fileInputStream.close();
				}
			} else {
				logger.info("default 1:" + inputStream);
				properties.load(inputStream);
				inputStream.close();
			}

			
			destinatarios = Misc.nz(properties.getProperty("emailDestinatarios"));
			// this.destinatariosSoporteCcaa =
			// Misc.nz(this.defaultProps.getProperty("emailDestinatariosSoporteCcaa"),SOPORTE_CCAA);

			smtp = Misc.nz(properties.getProperty("smtp"));
			asunto = Misc.nz(properties.getProperty("asunto"));
			mensajeEntero = Misc.nz(properties.getProperty("mensaje"));
			mensajeEnteroBloqueos = Misc.nz(properties.getProperty("mensajeBloqueos"));
			mensajeEnteroCambiosTraspasos = Misc.nz(properties.getProperty("mensajeCambios"));
			mensajeCambiosTraspasosBusquedaAproximada = Misc.nz(properties.getProperty("mensajeCambiosBusqueda"));

			ipServidor = Misc.nz(properties.getProperty("ipServidor"));
			puerto = Misc.nz(properties.getProperty("puerto"));
			sid = Misc.nz(properties.getProperty("sid"));
			usuario = Misc.nz(properties.getProperty("usuario"));
			password = Misc.nz(properties.getProperty("password"));

			ipServidorInter = Misc.nz(properties.getProperty("ipServidorInter"));
			puertoInter = Misc.nz(properties.getProperty("puertoInter"));
			sidInter = Misc.nz(properties.getProperty("sidInter"));
			usuarioInter = Misc.nz(properties.getProperty("usuarioInter"));
			passwordInter = Misc.nz(properties.getProperty("passwordInter"));
			
			pathDirCambiosTraspasos = Misc.nz(properties.getProperty("pathDirCambiosTraspasos"));
			pathFicheroTraspasosRealizados = Misc.nz(properties.getProperty("pathDirCambiosTraspasos"));
			pathFicheroEstadisticas = Misc.nz(properties.getProperty("pathFicheroEstadisticas"));
			numCambiosVisualizar = Integer.parseInt(Misc.nz(properties.getProperty("numCambiosVisualizar")));
			numCambiosVisualizarDos = Integer.parseInt(Misc.nz(properties.getProperty("numCambiosVisualizarDos")));
			
			TRASPASOS_NUEVOS  = Misc.nz(properties.getProperty("traspasosNuevos"));
			ESTADO_AGENTES = Misc.nz(properties.getProperty("estadoAgentes"));
			TRASPASOS = Misc.nz(properties.getProperty("traspasos"));
			BLOQUEADOS = Misc.nz(properties.getProperty("bloqueados"));
			TRASPASOS_BUSQUEDA_APROXIMADA = Misc.nz(properties.getProperty("traspasosBusquedaAproximada"));

			operacionesAsinAux = Misc.nz(properties.getProperty("operacionesAsincronas"));
			if (!Misc.esVacio(operacionesAsinAux)) {
				String[] listaValores = operacionesAsinAux.split("\\|");
				for (int i = 0; i < listaValores.length; i++) {
					if (i != 0) {
						operacionesAsincronas += ",";
					}
					operacionesAsincronas = (operacionesAsincronas + "'" + listaValores[i] + "'");
				}
			}

			operacionesSinAux = Misc.nz(properties.getProperty("operacionesSincronas"));
			if (!Misc.esVacio(operacionesSinAux)) {
				String[] listaValores = operacionesSinAux.split("\\|");
				for (int i = 0; i < listaValores.length; i++) {
					if (i != 0) {
						operacionesSincronas += ",";
					}
					operacionesSincronas = (operacionesSincronas + "'" + listaValores[i] + "'");
				}
			}

			codAgentesAux = Misc.nz(properties.getProperty("codAgentes"));
			if (!Misc.esVacio(codAgentesAux)) {
				String[] listaValores = codAgentesAux.split("\\|");
				for (int i = 0; i < listaValores.length; i++) {
					codAgentes.addElement(listaValores[i]);
				}
			}

			ultOperacionAsinAux = Misc.nz(properties.getProperty("ultimaOpAsincrona"));
			if (!Misc.esVacio(ultOperacionAsinAux)) {
				String[] listaValores = ultOperacionAsinAux.split("\\|");
				for (int i = 0; i < listaValores.length; i++) {
					ultimaOpAsincrona.addElement(listaValores[i]);
				}
			}

			ultOperacionSinAux = Misc.nz(properties.getProperty("ultimaOpSincrona"));
			if (!Misc.esVacio(ultOperacionSinAux)) {
				String[] listaValores = ultOperacionSinAux.split("\\|");
				for (int i = 0; i < listaValores.length; i++)
					ultimaOpSincrona.addElement(listaValores[i]);
			}

		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception e0) {
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e0) {
			}
		}
	}

	private static void cargarMapComunidades() {
		try {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("1", "SERVICIO CATALÁN DE LA SALUD. CATSALUT");
			map.put("2", "SERVICIO VASCO DE SALUD. OSAKIDETZA");
			map.put("3", "SERVICIO GALLEGO DE SALUD. SERGAS");
			map.put("4", "SERVICIO ANDALUZ DE SALUD. SAS");
			map.put("5", "SERVICIO DE SALUD DEL PRINCIPADO DE ASTURIAS");
			map.put("6", "SERVICIO CANTABRO DE SALUD");
			map.put("7", "SERVICIO RIOJANO DE SALUD");
			map.put("8", "SERVICIO MURCIANO DE SALUD");
			map.put("9", "SERVICIO VALENCIANO DE SALUD. SERVASA");
			map.put("10", "SERVICIO ARAGONES DE SALUD. SALUD");
			map.put("11", "SERVICIO DE SALUD DE CASTILLA-LA MANCHA. SESCAM");
			map.put("12", "SERVICIO CANARIO DE SALUD");
			map.put("13", "SERVICIO EXTREMEÑO DE SALUD. SES");
			map.put("14", "SERVEI DE SALUT DES ILLES BALEARS. IBSALUT");
			map.put("15", "SERVICIO NAVARRO DE SALUD. OSASUNBIDEA");
			map.put("16", "CONSEJERIA DE SANIDAD DE LA COMUNIDAD DE MADRID");
			map.put("17", "SANIDAD DE CASTILLA Y LEON. SACYL");
			map.put("18", "INGESA");
			map.put("19", "INGESA");
			map.put("TOTAL", "TOTAL");
			//
			MAP_COMUNIDADES = map;
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarMapRelacionComunidades() {
		try {
			LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
			map.put(new Integer(4), new Integer(1));
			map.put(new Integer(7), new Integer(2));
			map.put(new Integer(5), new Integer(3));
			map.put(new Integer(2), new Integer(4));
			map.put(new Integer(14), new Integer(5));
			map.put(new Integer(16), new Integer(6));
			map.put(new Integer(21), new Integer(7));
			map.put(new Integer(22), new Integer(8));
			map.put(new Integer(8), new Integer(9));
			map.put(new Integer(13), new Integer(10));
			map.put(new Integer(17), new Integer(11));
			map.put(new Integer(3), new Integer(12));
			map.put(new Integer(20), new Integer(13));
			map.put(new Integer(15), new Integer(14));
			map.put(new Integer(6), new Integer(15));
			map.put(new Integer(23), new Integer(16));
			map.put(new Integer(18), new Integer(17));
			map.put(new Integer(19), new Integer(18));
			map.put(new Integer(9), new Integer(22));
			//
			MAP_RELACION_COMUNIDADES = map;
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarMapCampos() {
		try {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("nombre", "nombre");
			map.put("apellido1", "apellido1");
			map.put("apellido2", "apellido2");
			map.put("sexo", "sexo");
			map.put("fecha_nac", "fecha_nac");
			map.put("dni", "dni");
			map.put("naf", "naf");
			map.put("naf_titular", "naf_titular");
			//map.put("pasaporte", "pasaporte");
			//
			MAP_CAMPOS = map;
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	static {
		sns.comun.config.Inicializacion.init();
	}

}

