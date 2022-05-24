package sns.config;

import gasai.util.Misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import sns.general.bean.RegistroBean;
import sns.model.RegulaTitulosBean;

public class Inicializacion {

	private static boolean logConfigurado = false;
	private static ArrayList aDebugEspecifico = new ArrayList();

	public static String PATH_FICHEROS_SIN_PROCESAR = "";
	public static String MAX_USUARIOS_CONSULTAS = "";
	public static String COD_USUARIO_SNS_TESTEO = "";
	public static String XML_RESPUESTA_TESTEO = "";

	public static ArrayList INFTABLASAFECTADAS = new ArrayList();
	public static ArrayList DATOSTABLASAFECTADAS = new ArrayList();

	public static HashMap hREGULA_TITULOS = new HashMap();
	public static Vector vCCC_REGULA_TITULOS = new Vector();

	// CORREO DE COMUNICACIONES

	// acceso a SNSADMIN
	public static String USUARIOSNSADMIN = "";
	public static String PASSWORDSNSADMIN = "";

	public static String PATH_REPOSITORIO_COMPARTIDO = "";
	public static long UMBRAL_CACHEO_SERVICIO = 0;
	public static boolean ACTIVAR_SOPORTE_CACHEO = false;

	public static String PATH_FICHERO_BAJAS_JUSTICIA = "";
	public static String FICHERO_BAJAS_JUSTICIA = "";

	public static String FILERECOGEXML = "";
	public static String FILECONFIG = "config.properties";

	public static boolean SEGURIDAD_WEBLOGIC = false;

	public static boolean PINTAR_XML = false;
	public static boolean REVOCADOS_WEBSERVICES = false;

	public static boolean FIJARCLASSLOADER = true;
	public static boolean ELIMINAR_TAG_CIP = false;
	public static boolean ABIERTO_COMUNIDADES_DEFUNCION = false;

	public static boolean ACTIVAR_AUDITORIA_LOPD = false;

	public static String WSDL_REVOCADOS_WEBSERVICES = "";
	public static String IDAPP_REVOCADOS_WEBSERVICES = "";

	public static String SERVIDORDTDS = "http://sns.msc.es/dtds/";

	// los agentes que tienen el formato del NIE con 7 digitos
	public static Vector vAGENTES_NIE = new Vector();

	// los agentes que tienen admiten el nuevo N010
	public static Vector vAGENTES_N010 = new Vector();

	// los agentes que tienen admiten el nuevo N011
	public static Vector vAGENTES_N011 = new Vector();

	// los agentes que van con el A007
	public static Vector vAGENTES_A007 = new Vector();

	// los agentes que tienen IDENTIFICADORES_USUARIO_SEC
	public static Vector vAGENTES_IU_SEC = new Vector();

	// los agentes que tienen implementado la extension de los proveedores
	public static Vector vAGENTES_EXT_PROVEEDORES = new Vector();

	public static LinkedHashMap HAGENTES = new LinkedHashMap();

	public static HashMap hASEGURADORAS = new HashMap();
	public static HashMap hASEGURADORAS_GESTORAS = new HashMap();
	public static HashMap hASEGURADORAS_COLABORADORAS = new HashMap();
	public static HashMap hCA_PRESTACION_SERVICIO = new HashMap();
	public static HashMap hCOBERTURAS = new HashMap();
	public static HashMap hGESTORAS_PROVEEDORES = new HashMap();
	public static HashMap hCOLABORADORAS = new HashMap();
	public static HashMap hCOLABORADORAS_PROVEEDORES = new HashMap();
	public static HashMap hDESGLOSE_AGENTES = new HashMap();
	public static HashMap hESTADOS = new HashMap();
	public static HashMap hGESTORAS = new HashMap();
	public static HashMap hPAIS = new HashMap();
	public static HashMap hPROVEEDOR = new HashMap();
	public static HashMap hPROVINCIAS = new HashMap();
	public static HashMap hSITUACIONES = new HashMap();
	public static HashMap hTITULOS = new HashMap();
	public static HashMap hTIPOS_VIA = new HashMap();
	public static ArrayList aINDICADORES_FARMACIA = new ArrayList();

	public static HashMap SERVICIOPRESTACION_AGENTES = new HashMap();
	public static HashMap AGENTES_SERVICIOPRESTACION = new HashMap();

	public static HashMap CAISO_AGENTES = new HashMap();
	public static HashMap CITE_AGENTES = new HashMap();
	public static HashMap CA_CAISO = new HashMap();
	public static HashMap CAISO_CA = new HashMap();
	public static HashMap CA_AGENTECAISO = new HashMap();
	public static HashMap AGENTECAISO_CA = new HashMap();
	public static HashMap AGENTECANOISO_CA = new HashMap();

	public static HashMap TIPO_ASEGURAMIENTO_TITULO = new HashMap();
	public static HashMap TITULO_TIPO_ASEGURAMIENTO = new HashMap();

	public static String PATHLOGS;

	public static long TIEMPOBLOQUEOCOLAOUTERROR;

	public static boolean ACTIVADO_CORREO;
	public static String HOST_SMTP = "";
	public static String EMAIL_SOPORTE = "";
	public static String EMAIL_REMITENTE = "";

	public static String EMAIL_TRASPASOS = "";
	public static String EMAIL_BAJAS_MJU = "";
	public static String ASUNTO_EMAIL_BAJAS_MJU = "";


	public static boolean ACTIVAR_GUARDAR_ALERTAS_SISTEMA = false;
	public static boolean ACTIVAR_EMAIL_ALERTAS_SISTEMA = false;
	public static String EMAIL_ALERTAS_SISTEMA = "";
	public static String ASUNTO_EMAIL_ALERTAS_SISTEMA = "";

	public static boolean STOP = false;
	public static boolean ACTIVADO_POR_CERTIFICADO = false;

	public static Vector CAMPOSNOVISIBLES = new Vector();
	public static Vector AGENTESCAMPOSNOVISIBLES = new Vector();
	public static Vector CAMPOSHISTORICO = new Vector();
	public static Vector CAMPOSHISTORICOALIAS = new Vector();

	public static String PREFIJOAPP = "";

	public static String LISTA_ESTADOS_CONSULTA = "";

	public static Vector LISTA_ESTADOS_VISIBLES = new Vector();

	// operaciones
	public static Vector LISTA_OP_CONSULTAS = new Vector();
	public static Vector LISTA_OP_ASINCRONAS = new Vector();
	public static Vector LISTA_OP_ALTAS = new Vector();

	public static String COLAASYNINTERCAMBIADOR = "";
	public static String URLSINTERCAMBIADOR = "";

	public static int MODOACCESOINTERCAMBIADOR;
	public static String WSDLURLSINTERCAMBIADOR;
	public static String IDSERVICIO = "";

	public static boolean ACTIVADO_CONTROL_TRANSACCIONES;

	public static boolean ACTIVADO_NIE_SIETE;
	public static boolean ACTIVADO_SIN_RECURSOS_MAYORES_65;

	public static boolean PONERDTD;

	public static Vector LISTA_CONSULTA_TABLAS = new Vector();
	public static Vector LISTA_TABLAS = new Vector();

	public static Vector LISTA_SERVIDORES_COLA_ENTRADA = new Vector();

	public static int NUMERO_REINTENTOS_ENTRADA = 0;

	public static String URLINIT = "/initAction.do";

	public static String CCC_ESTANDAR;
	public static Vector LISTA_AGENTES_NO_N010;
	public static String CCC_MUGEJU;
	public static String CCC_MUFACE;
	public static String CCC_ISFASA;
	public static String CCC_ISFASB;
	public static String CCC_ISFASC;

	public static boolean BLOQUEAR_TRASPASOS_ERRONEOS;
	public static boolean NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS;
	public static String TEXTO_BLOQUEAR_TRASPASOS_ERRONEOS;
	public static int NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO;
	public static boolean REVISAR_NAF;
	public static boolean REVISAR_DNI;
	public static boolean REVISAR_NAF_TITULAR;
	public static boolean BLOQUEAR_TRASPASOS_AUTOMATICAMENTE;
	public static boolean ACTIVO_REVISAR_TRASPASOS;
	public static String URL_REVISAR_TRASPASOS;

	public static boolean BLOQUEAR_TRASPASOS_LISTA_NEGRA;
	public static boolean NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA;
	public static String TEXTO_BLOQUEAR_TRASPASOS_LISTA_NEGRA;

	public static String TEXTO_BLOQUEAR_POR_CAMBIOS_CIP;
	public static boolean ACTIVO_REVISAR_CAMBIOS_CIP;

	// ////////////////// TRASPASOS LISTA BLANCA
	public static String RESPOSITORIO_FICHEROS_LISTABLANCA;
	public static boolean BORRAR_FICHEROS_LISTABLANCA;
	//
	public static HashMap HASH_TRASPASOS_MOTIVOS = new HashMap();
	public static ArrayList ARRAY_TRASPASOS_MOTIVOS = new ArrayList();
	public static HashMap HASH_AGENTES_TRASPASOS = new HashMap();
	public static ArrayList ARRAY_AGENTES_TRASPASOS = new ArrayList();
	//
	// ////////////////// PAGINACION TRASPASOS LISTA BLANCA
	// Para cargar numero registros por pagina
	public static ArrayList AL_NUM_REGISTROS = new ArrayList();
	// Para cargar dias al combo
	public static ArrayList AL_FECHA_DIAS = new ArrayList();
	// Para cargar meses al combo
	public static ArrayList AL_FECHA_MESES = new ArrayList();
	// Para cargar años al combo
	public static ArrayList AL_FECHA_ANYOS = new ArrayList();
	// Para cargar años al combo
	public static ArrayList AL_FECHA_HORAS = new ArrayList();
	// Para cargar años al combo
	public static ArrayList AL_FECHA_MINUTOS = new ArrayList();
	//

	public static HashMap hPREFIJOS_AGENTES = new HashMap();

	public static LinkedHashMap H_AGENTES_RDL=new LinkedHashMap();

	public static int LONGITUD_PREFIJOS_AGENTES;

	public static int NIVEL_LOG;
	public static String DEBUG_ESPECIFICO = "";

	public static ArrayList AL_USUARIOS = new ArrayList();

	public static boolean ACTIVAR_RESTRICCION_BAJA_DUPLICIDAD;
	public static boolean ACTIVAR_RESTRICCION_BAJAS_HOSPITALARIAS = false;
	public static boolean BLOQUEAR_BAJAS_DEFUNCION_OTRAS_CA = false;

	public static boolean ACTIVAR_BAJAS_PROCEDENTES_INSS = false;

	public static long TIME_TO_DELIVER_COLAERROR;
	public static long TIME_TO_DELIVER_COLAGESTION;
	
	public static String TEXTO_BLOQUEAR_PASO_BENEFICIARIO_TITULAR;
	public static long TIEMPO_CUARENTENA_PASO_BENEFICIARIO_TITULAR;
	
	public static String NOMBRE_TABLA_INSS;
	public static String NOMBRE_TABLA_INSS_SNS;
	public static String NOMBRE_TABLA_INSS_BAJAS;

	public static String PATH_IFI;
	public static String PATH_RELATIVO_RECIBIDOS_IFI;
	public static String PATH_RELATIVO_ARCHIVADOS_IFI;

	
	public static String PREFIJO_FICHERO_IFI;
	public static String NOMBRE_FICHERO_SINCRONIZACION_INSS_SNS;
	
	public static long HOLGURA_FECHA_INICIO_TEMPORALIDAD;
	
	public static boolean ENVIAR_ACTUALIZACION_IFI = false;
	public static boolean ACTIVAR_COLA_JMS_OSB = false;

	public static boolean OSB_ACTIVAR_COPIAR_COLA_JMS = false;
	public static String OSB_NOMBRE_COLA_JMS;

	public static boolean INSS_PERMITIR_VINCULAR_SIN_ESTADO =false;
	
	private static synchronized void initLog(String pathConfig) {
		if (!logConfigurado) {
			File directorioLog4properties = new File(sns.config.Inicializacion.PATHLOGS);
			System.setProperty("tarjeta.path.log", sns.config.Inicializacion.PATHLOGS);
			ClassLoader classLoader = Inicializacion.class.getClassLoader();

			URL url =  classLoader.getResource("tarjeta_log4j.xml");

			DOMConfigurator.configure(url);


			if (directorioLog4properties.exists() && directorioLog4properties.isDirectory()) {
				Enumeration e = org.apache.log4j.Logger.getRootLogger().getAllAppenders();
				while (e.hasMoreElements()) {
					org.apache.log4j.Appender apAux = (org.apache.log4j.Appender) e.nextElement();
					if (!"TARJETA_FILE".equals(apAux.getName())) {
						org.apache.log4j.Logger.getRootLogger().removeAppender(apAux);
					}
				}
//				try {
//
////					org.apache.log4j.FileAppender ap = (org.apache.log4j.FileAppender) (org.apache.log4j.Logger.getRootLogger().getAppender("RfileAppender"));
////					ap.setFile(sns.config.Inicializacion.PATHLOGS + "/tarjeta_" + System.getProperty("weblogic.Name") + ".log");
////					ap.setImmediateFlush(true);
////					ap.activateOptions();
//
//				} catch (Exception ex) {
//					ex.printStackTrace();
//					BasicConfigurator.configure();
//				}
			} else {
				BasicConfigurator.configure();
			}

			logConfigurado = true;
		}
	}

	public static Logger getLogger(Class clase) {
		return getLogger(clase.getName());
	}

	public static Logger getLogger(String name) {
		Logger loggerActual = org.apache.log4j.Logger.getLogger(name);
		boolean esEspecifico = false;
		for (int i = 0; i < aDebugEspecifico.size(); i++) {
			if (name.endsWith((String) aDebugEspecifico.get(i))) {
				// ponermos el logger en modod debug
				esEspecifico = true;
				loggerActual.setLevel(Level.DEBUG);
			}
		}
		if (!esEspecifico) {
			loggerActual.setLevel(Level.toLevel(NIVEL_LOG));
		}
		return loggerActual;

	}

	private static void cargarTablasConsultas(sns.bd.AccesoBd bd) throws java.sql.SQLException {
		Vector LISTA_CONSULTA_TABLASAUX = new Vector();
		Vector LISTA_TABLASAUX = new Vector();
		String query = "select TABLA,PERMITIDO from TABLAS_CONSULTAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String nombreTabla = Misc.nz(auxHash.get("TABLA"));
			String permitido = Misc.nz(auxHash.get("PERMITIDO"));
			LISTA_TABLASAUX.addElement(nombreTabla);
			if (permitido.equals("1")) {
				LISTA_CONSULTA_TABLASAUX.addElement(nombreTabla);
			}
		}

		sns.config.Inicializacion.LISTA_CONSULTA_TABLAS = LISTA_CONSULTA_TABLASAUX;
		sns.config.Inicializacion.LISTA_TABLAS = LISTA_TABLASAUX;
	}

	private static HashMap initDatosTablasAfectadas() {
		HashMap hResp = new HashMap();
		hResp.put("columnasAfectadas", new ArrayList());
		hResp.put("datosColumnasAfectadas", new ArrayList());
		return hResp;
	}

	private static HashMap asignarDatosTablasAfectadas(ArrayList aColumnasAfectadas, ArrayList aDatosColumnasAfectadas) {
		HashMap hResp = new HashMap();
		hResp.put("columnasAfectadas", aColumnasAfectadas);
		hResp.put("datosColumnasAfectadas", aDatosColumnasAfectadas);
		return hResp;
	}

	private static HashMap getHashMapDatosColumna(String columna, String tablaOrigen, String columnaOrigen, String columnaOrigenVer) {
		HashMap hDatosColumnasAfectadas = new HashMap();
		hDatosColumnasAfectadas.put("columna", columna);
		hDatosColumnasAfectadas.put("columnaOrigen", columnaOrigen);
		hDatosColumnasAfectadas.put("tablaOrigen", tablaOrigen);
		hDatosColumnasAfectadas.put("columnaVerOrigen", columnaOrigenVer);
		return hDatosColumnasAfectadas;
	}

	private static void cargarTablasBasicas(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		ArrayList infTablasAfectadasAux = new ArrayList();
		ArrayList datosTablasAfectadasAux = new ArrayList();

		String query = "select * from adm_tablas_basicas order by tabla";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String nombreTablaAux = Misc.nz(auxHash.get("TABLA"));
			String nombreColumnaAux = Misc.nz(auxHash.get("COLUMNA"));
			String nombreTablaOrigenAux = Misc.nz(auxHash.get("TABLAORIGEN"));
			String nombreColumnaOrigenAux = Misc.nz(auxHash.get("COLUMNAORIGEN"));
			String nombreColumnaOrigenVerAux = Misc.nz(auxHash.get("COLUMNAVERORIGEN"));
			int indiceInfTablaAfectada = infTablasAfectadasAux.indexOf(nombreTablaAux);
			if (indiceInfTablaAfectada == -1) {
				infTablasAfectadasAux.add(nombreTablaAux);
				datosTablasAfectadasAux.add(initDatosTablasAfectadas());
				indiceInfTablaAfectada = infTablasAfectadasAux.size() - 1;
			}
			HashMap hDatosTabla = (HashMap) datosTablasAfectadasAux.get(indiceInfTablaAfectada);
			ArrayList aColumnasAfectadas = (ArrayList) hDatosTabla.get("columnasAfectadas");
			aColumnasAfectadas.add(nombreColumnaAux);

			ArrayList aDatosColumnasAfectadas = (ArrayList) hDatosTabla.get("datosColumnasAfectadas");
			aDatosColumnasAfectadas.add(getHashMapDatosColumna(nombreColumnaAux, nombreTablaOrigenAux, nombreColumnaOrigenAux, nombreColumnaOrigenVerAux));
			datosTablasAfectadasAux.set(indiceInfTablaAfectada, asignarDatosTablasAfectadas(aColumnasAfectadas, aDatosColumnasAfectadas));
		}

		INFTABLASAFECTADAS = infTablasAfectadasAux;
		DATOSTABLASAFECTADAS = datosTablasAfectadasAux;
	}

	private static void cargarIndicadoresFarmaciaSubIndicadores(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		ArrayList aINDICADORES_FARMACIAAUX = new ArrayList();
		String query = " select i.COD_INDICADOR_DE_FARMACIA,s.COD_SUBINDICADOR_DE_FARMACIA " + " from indicador_de_farmacia i, subindicador_de_farmacia s "
				+ " where s.COD_INDICADOR_DE_FARMACIA(+)=i.COD_INDICADOR_DE_FARMACIA ";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			aINDICADORES_FARMACIAAUX.add(Misc.nz(auxHash.get("COD_INDICADOR_DE_FARMACIA")) + Constantes.SEPARADOR_INDICADOR_FARMACIA + Misc.nz(auxHash.get("COD_SUBINDICADOR_DE_FARMACIA")));
		}
		aINDICADORES_FARMACIAAUX.add("TSI 005" + Constantes.SEPARADOR_INDICADOR_FARMACIA);
		sns.config.Inicializacion.aINDICADORES_FARMACIA = aINDICADORES_FARMACIAAUX;
	}

	private static void cargarTiposVia(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hTIPOS_VIAAUX = new HashMap();
		String query = "select COD_VIA,DESC_VIA from TIPOS_VIA";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			hTIPOS_VIAAUX.put(Misc.nz(auxHash.get("COD_VIA")), Misc.nz(auxHash.get("DESC_VIA")));
		}
		sns.config.Inicializacion.hTIPOS_VIA = hTIPOS_VIAAUX;
	}

	/**
	 * Carga en una Hash, los codAgentes con su nombre
	 * 
	 * @param bd
	 *            AccesoBd
	 * @param logger
	 *            Logger
	 * @throws SQLException
	 */
	private static void cargarPrefijosAgentes(sns.bd.AccesoBd bd,
			org.apache.log4j.Logger logger) throws java.sql.SQLException {
		HashMap hPREFIJOS_AGENTESAUX = new HashMap();
		String query = "SELECT DESC_PREFIJO,COD_AGENTE FROM AGENTES_PREFIJOS";
		logger.debug(query);
		Vector vAux = bd.consulta(query);
		for (int i = 0; i < vAux.size(); i++) {
			HashMap hResp = (HashMap) vAux.elementAt(i);
			hPREFIJOS_AGENTESAUX.put(Misc.nz(hResp.get("DESC_PREFIJO")), Misc.nz(hResp.get("COD_AGENTE")));
		}
		sns.config.Inicializacion.hPREFIJOS_AGENTES = hPREFIJOS_AGENTESAUX;
	}

	private static void cargarAseguradoras(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hASEGURADORASAUX = new HashMap();
		String query = "select COD_ASEGURADORA,DESC_ASEGURADORA from ASEGURADORAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codAseguradoraAux = Misc.nz(auxHash.get("COD_ASEGURADORA"));
			hASEGURADORASAUX.put(codAseguradoraAux, Misc.nz(auxHash.get("DESC_ASEGURADORA")));
			if (codAseguradoraAux.length() != 2)
				hASEGURADORASAUX.put(Misc.rellenarIzq(codAseguradoraAux, "0", 2), Misc.nz(auxHash.get("DESC_ASEGURADORA")));
		}
		sns.config.Inicializacion.hASEGURADORAS = hASEGURADORASAUX;
	}

	private static void cargarAseguradorasGestorasColaboradoras(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hASEGURADORAS_GESTORASAUX = new HashMap();
		HashMap hASEGURADORAS_COLABORADORASAUX = new HashMap();

		String query1 = "select COD_ASEGURADORA,COD_GESTORA from ASEGURADORAS_GESTORAS where cod_gestora is not null";
		Vector vAux = bd.consulta(query1);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codAseguradoraAux = Misc.nz(auxHash.get("COD_ASEGURADORA"));
			String codGestoraAux = Misc.nz(auxHash.get("COD_GESTORA"));
			String key1 = codAseguradoraAux + ":" + codGestoraAux;
			hASEGURADORAS_GESTORASAUX.put(key1, "");
			if (codAseguradoraAux.length() != 2 || codGestoraAux.length() != 2) {
				key1 = Misc.rellenarIzq(codAseguradoraAux, "0", 2) + ":" + Misc.rellenarIzq(codGestoraAux, "0", 2);
				hASEGURADORAS_GESTORASAUX.put(key1, "");
			}
		}
		String query2 = "select COD_ASEGURADORA,COD_COLABORADORA from ASEGURADORAS_GESTORAS where COD_COLABORADORA is not null";
		Vector vAux2 = bd.consulta(query2);
		int sizeAux2 = vAux2.size();
		for (int i = 0; i < sizeAux2; i++) {
			HashMap auxHash = (HashMap) vAux2.elementAt(i);
			String codAseguradoraAux = Misc.nz(auxHash.get("COD_ASEGURADORA"));
			String codColaboradoraAux = Misc.nz(auxHash.get("COD_COLABORADORA"));
			String key2 = codAseguradoraAux + ":" + codColaboradoraAux;
			hASEGURADORAS_COLABORADORASAUX.put(key2, "");
			if (codAseguradoraAux.length() != 2 || codColaboradoraAux.length() != 2) {
				key2 = Misc.rellenarIzq(codAseguradoraAux, "0", 2) + ":" + Misc.rellenarIzq(codColaboradoraAux, "0", 2);
				hASEGURADORAS_COLABORADORASAUX.put(key2, "");
			}
		}

		sns.config.Inicializacion.hASEGURADORAS_GESTORAS = hASEGURADORAS_GESTORASAUX;
		sns.config.Inicializacion.hASEGURADORAS_COLABORADORAS = hASEGURADORAS_COLABORADORASAUX;

	}

	private static void cargarCaPrestacionServicio(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hCA_PRESTACION_SERVICIOAUX = new HashMap();
		String query = "select COD_COMUNIDAD,COD_PRESTACION_SERVICIO from CA_PRESTACION_SERVICIO";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codComunidadAux = Misc.nz(auxHash.get("COD_COMUNIDAD"));
			hCA_PRESTACION_SERVICIOAUX.put(codComunidadAux, Misc.nz(auxHash.get("COD_PRESTACION_SERVICIO")));
			if (codComunidadAux.length() != 2)
				hCA_PRESTACION_SERVICIOAUX.put(Misc.rellenarIzq(codComunidadAux, "0", 2), Misc.nz(auxHash.get("COD_PRESTACION_SERVICIO")));
		}
		sns.config.Inicializacion.hCA_PRESTACION_SERVICIO = hCA_PRESTACION_SERVICIOAUX;
	}

	private static void cargarCoberturas(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hCOBERTURASAUX = new HashMap();
		String query = "select COD_TITULO,COD_TRATAMIENTO,COD_COBERTURA from COBERTURAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codTituloAux = Misc.nz(auxHash.get("COD_TITULO"));
			String codTratamientoAux = Misc.nz(auxHash.get("COD_TRATAMIENTO"));
			String key = codTituloAux + ":" + codTratamientoAux;
			hCOBERTURASAUX.put(key, Misc.nz(auxHash.get("COD_COBERTURA")));
			if (codTituloAux.length() != 2 || codTratamientoAux.length() != 2) {
				key = Misc.rellenarIzq(codTituloAux, "0", 2) + ":" + Misc.rellenarIzq(codTratamientoAux, "0", 2);
				hCOBERTURASAUX.put(key, Misc.nz(auxHash.get("COD_COBERTURA")));
			}
		}
		sns.config.Inicializacion.hCOBERTURAS = hCOBERTURASAUX;
	}

	private static void cargarGestorasProveedores(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hGESTORAS_PROVEEDORESAUX = new HashMap();
		String query = "select COD_GESTORA,COD_PROVEEDOR from GESTORAS_PROVEEDORES";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codGestoraAux = Misc.nz(auxHash.get("COD_GESTORA"));
			String codProveedorAux = Misc.nz(auxHash.get("COD_PROVEEDOR"));
			String key = codGestoraAux + ":" + codProveedorAux;
			hGESTORAS_PROVEEDORESAUX.put(key, "");
			if (codProveedorAux.length() != 2 || codGestoraAux.length() != 2) {
				key = Misc.rellenarIzq(codGestoraAux, "0", 2) + ":" + Misc.rellenarIzq(codProveedorAux, "0", 2);
				hGESTORAS_PROVEEDORESAUX.put(key, "");
			}
		}

		sns.config.Inicializacion.hGESTORAS_PROVEEDORES = hGESTORAS_PROVEEDORESAUX;

	}

	private static void cargarColaboradoras(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hCOLABORADORASAUX = new HashMap();
		String query = "select COD_COLABORADORA,DESC_COLABORADORA from COLABORADORAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codColaboradoraAux = Misc.nz(auxHash.get("COD_COLABORADORA"));
			hCOLABORADORASAUX.put(codColaboradoraAux, Misc.nz(auxHash.get("DESC_COLABORADORA")));
			if (codColaboradoraAux.length() != 2) {
				hCOLABORADORASAUX.put(Misc.rellenarIzq(codColaboradoraAux, "0", 2), Misc.nz(auxHash.get("DESC_COLABORADORA")));
			}
		}
		sns.config.Inicializacion.hCOLABORADORAS = hCOLABORADORASAUX;

	}

	private static void cargarColaboradorasProveedores(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hCOLABORADORAS_PROVEEDORESAUX = new HashMap();
		String query = "select COD_PROVEEDOR,COD_COLABORADORA from COLABORADORAS_PROVEEDORES";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codProveedorAux = Misc.nz(auxHash.get("COD_PROVEEDOR"));
			String codColaboradoraAux = Misc.nz(auxHash.get("COD_COLABORADORA"));
			String key = codProveedorAux + ":" + codColaboradoraAux;
			hCOLABORADORAS_PROVEEDORESAUX.put(key, "");
			if (codProveedorAux.length() != 2 || codColaboradoraAux.length() != 2) {
				key = Misc.rellenarIzq(codProveedorAux, "0", 2) + ":" + Misc.rellenarIzq(codColaboradoraAux, "0", 2);
				hCOLABORADORAS_PROVEEDORESAUX.put(key, "");
			}
		}
		hCOLABORADORAS_PROVEEDORES = hCOLABORADORAS_PROVEEDORESAUX;
	}

	private static void cargarDesgloseAgentes(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hDESGLOSE_AGENTESAUX = new HashMap();
		String query = "select COD_AGENTE,COD_PRESTACION_SERVICIO from DESGLOSE_AGENTES";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codAgenteAux = Misc.nz(auxHash.get("COD_AGENTE"));
			String codPrestacionServicioAux = Misc.nz(auxHash.get("COD_PRESTACION_SERVICIO"));
			String key = codAgenteAux + ":" + codPrestacionServicioAux;

			hDESGLOSE_AGENTESAUX.put(key, codPrestacionServicioAux);

			if (codAgenteAux.length() != 2 || codPrestacionServicioAux.length() != 2) {
				key = Misc.rellenarIzq(codAgenteAux, "0", 2) + ":" + Misc.rellenarIzq(codPrestacionServicioAux, "0", 2);
				hDESGLOSE_AGENTESAUX.put(key, codPrestacionServicioAux);
			}

		}
		sns.config.Inicializacion.hDESGLOSE_AGENTES = hDESGLOSE_AGENTESAUX;
	}

	private static void cargarEstados(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hESTADOSAUX = new HashMap();
		String query = "select COD_ESTADO,DESC_ESTADO from ESTADOS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codEstadoAux = Misc.nz(auxHash.get("COD_ESTADO"));
			hESTADOSAUX.put(codEstadoAux, Misc.nz(auxHash.get("DESC_ESTADO")));
			if (codEstadoAux.length() != 2)
				hESTADOSAUX.put(Misc.rellenarIzq(codEstadoAux, "0", 2), Misc.nz(auxHash.get("DESC_ESTADO")));
		}
		sns.config.Inicializacion.hESTADOS = hESTADOSAUX;
	}

	private static void cargarGestoras(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hGESTORASAUX = new HashMap();
		String query = "select COD_GESTORA,DESC_GESTORA from GESTORAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codGestoraAux = Misc.nz(auxHash.get("COD_GESTORA"));
			hGESTORASAUX.put(codGestoraAux, Misc.nz(auxHash.get("DESC_GESTORA")));
			if (codGestoraAux.length() != 2)
				hGESTORASAUX.put(Misc.rellenarIzq(codGestoraAux, "0", 2), Misc.nz(auxHash.get("DESC_GESTORA")));
		}

		sns.config.Inicializacion.hGESTORAS = hGESTORASAUX;

	}

	private static void cargarPaises(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hPAISAUX = new HashMap();
		String query = "select COD_PAIS,DESC_PAIS from PAIS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codPaisAux = Misc.nz(auxHash.get("COD_PAIS"));
			hPAISAUX.put(codPaisAux, Misc.nz(auxHash.get("DESC_PAIS")));
			if (codPaisAux.length() != 3) {
				hPAISAUX.put(Misc.rellenarIzq(codPaisAux, "0", 3), Misc.nz(auxHash.get("DESC_PAIS")));
			}
		}
		sns.config.Inicializacion.hPAIS = hPAISAUX;
	}

	private static void cargarProveedores(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hPROVEEDORAUX = new HashMap();
		String query = "select COD_PROVEEDOR,DESC_PROVEEDOR from PROVEEDORES";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codProveedorAux = Misc.nz(auxHash.get("COD_PROVEEDOR"));
			hPROVEEDORAUX.put(codProveedorAux, Misc.nz(auxHash.get("DESC_PROVEEDOR")));
			if (codProveedorAux.length() != 2)
				hPROVEEDORAUX.put(Misc.rellenarIzq(codProveedorAux, "0", 2), Misc.nz(auxHash.get("DESC_PROVEEDOR")));
		}
		sns.config.Inicializacion.hPROVEEDOR = hPROVEEDORAUX;
	}

	private static void cargarRegulaTitulos(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hREGULA_TITULOS_AUX = new HashMap();
		Vector vCCC_REGULA_TITULOS_AUX = new Vector();
		String query = "select RAS,CCC,FAR,COD_TITULO,COD_SITUACION,RAS||'-'||FAR||'-'||CCC||'-'||COD_TITULO||'-'||COD_SITUACION CLAVE from REGULA_TITULOS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String clave = Misc.nz(auxHash.get("CLAVE"));
			RegulaTitulosBean regulaTitulosBean = new RegulaTitulosBean();
			regulaTitulosBean.setCcc(Misc.nz(auxHash.get("CCC")));
			regulaTitulosBean.setRas(Misc.nz(auxHash.get("RAS")));
			regulaTitulosBean.setFar(Misc.nz(auxHash.get("FAR")));
			regulaTitulosBean.setCodTitulo(Misc.nz(auxHash.get("COD_TITULO")));
			regulaTitulosBean.setCodSituacion(Misc.nz(auxHash.get("COD_SITUACION")));
			hREGULA_TITULOS_AUX.put(clave, regulaTitulosBean);

		}
		query = "select distinct CCC from REGULA_TITULOS";
		vAux = bd.consulta(query);
		sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			vCCC_REGULA_TITULOS_AUX.addElement(Misc.nz(auxHash.get("CCC")));
		}

		sns.config.Inicializacion.hREGULA_TITULOS = hREGULA_TITULOS_AUX;
		sns.config.Inicializacion.vCCC_REGULA_TITULOS = vCCC_REGULA_TITULOS_AUX;
	}

	private static void cargarProvincias(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hPROVINCIASAUX = new HashMap();
		String query = "select COD_PROVINCIA,DESC_PROVINCIA from PROVINCIAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codProviniciaAux = Misc.nz(auxHash.get("COD_PROVINCIA"));
			hPROVINCIASAUX.put(codProviniciaAux, Misc.nz(auxHash.get("DESC_PROVINCIA")));
			if (codProviniciaAux.length() != 2)
				hPROVINCIASAUX.put(Misc.rellenarIzq(codProviniciaAux, "0", 2), Misc.nz(auxHash.get("DESC_PROVINCIA")));
		}
		sns.config.Inicializacion.hPROVINCIAS = hPROVINCIASAUX;
	}

	private static void cargarSituaciones(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hSITUACIONESAUX = new HashMap();
		String query = "select COD_SITUACION,COD_TRATAMIENTO from SITUACIONES";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codSituacionAux = Misc.nz(auxHash.get("COD_SITUACION"));
			hSITUACIONESAUX.put(codSituacionAux, Misc.nz(auxHash.get("COD_TRATAMIENTO")));
			if (codSituacionAux.length() != 2)
				hSITUACIONESAUX.put(Misc.rellenarIzq(codSituacionAux, "0", 2), Misc.nz(auxHash.get("COD_TRATAMIENTO")));
		}
		sns.config.Inicializacion.hSITUACIONES = hSITUACIONESAUX;
	}

	private static void cargarTitulos(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap hTITULOSAUX = new HashMap();
		String query = "select COD_TITULO,FLAQ_HEREDABLE from TITULOS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codTituloAux = Misc.nz(auxHash.get("COD_TITULO"));
			hTITULOSAUX.put(codTituloAux, Misc.nz(auxHash.get("FLAQ_HEREDABLE")));
			if (codTituloAux.length() != 2)
				hTITULOSAUX.put(Misc.rellenarIzq(codTituloAux, "0", 2), Misc.nz(auxHash.get("FLAQ_HEREDABLE")));
		}
		sns.config.Inicializacion.hTITULOS = hTITULOSAUX;
	}

	private static void cargarTipoAseguramientoTitulo(sns.bd.AccesoBd bd) throws java.sql.SQLException {
		HashMap TIPO_ASEGURAMIENTO_TITULOAUX = new HashMap();
		HashMap TITULO_TIPO_ASEGURAMIENTOAUX = new HashMap();
		String query = "select COD_TIPO_ASEGURAMIENTO,COD_TITULO from INSS_TIPOS_ASEGURAMIENTO";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codTipoAseguramientoAux = Misc.nz(auxHash.get("COD_TIPO_ASEGURAMIENTO"));
			String codTituloAux = Misc.nz(auxHash.get("COD_TITULO"));
			TIPO_ASEGURAMIENTO_TITULOAUX.put(codTipoAseguramientoAux, codTituloAux);
			TITULO_TIPO_ASEGURAMIENTOAUX.put(codTituloAux, codTipoAseguramientoAux);
		}
		sns.config.Inicializacion.TIPO_ASEGURAMIENTO_TITULO = TIPO_ASEGURAMIENTO_TITULOAUX;
		sns.config.Inicializacion.TITULO_TIPO_ASEGURAMIENTO = TITULO_TIPO_ASEGURAMIENTOAUX;
	}

	private static void cargarServicioPrestacionAgentes(sns.bd.AccesoBd bd) throws java.sql.SQLException {
		HashMap SERVICIOPRESTACION_AGENTESAUX = new HashMap();
		HashMap AGENTES_SERVICIOPRESTACIONAUX = new HashMap();
		String query = "select COD_PRESTACION_SERVICIO,COD_AGENTE from desglose_agentes";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codPrestacionServicioAux = Misc.nz(auxHash.get("COD_PRESTACION_SERVICIO"));
			String codAgenteAux = Misc.nz(auxHash.get("COD_AGENTE"));
			SERVICIOPRESTACION_AGENTESAUX.put(codPrestacionServicioAux, codAgenteAux);
			AGENTES_SERVICIOPRESTACIONAUX.put(codAgenteAux, codPrestacionServicioAux);
			if (codPrestacionServicioAux.length() != 2)
				SERVICIOPRESTACION_AGENTESAUX.put(Misc.rellenarIzq(codPrestacionServicioAux, "0", 2), codAgenteAux);
			if (codAgenteAux.length() != 2)
				AGENTES_SERVICIOPRESTACIONAUX.put(Misc.rellenarIzq(codAgenteAux, "0", 2), codPrestacionServicioAux);
		}
		sns.config.Inicializacion.SERVICIOPRESTACION_AGENTES = SERVICIOPRESTACION_AGENTESAUX;
		sns.config.Inicializacion.AGENTES_SERVICIOPRESTACION = AGENTES_SERVICIOPRESTACIONAUX;
	}

	/**
	 * Carga en una Hash, los codAgentes con su nombre
	 * 
	 * @param bd
	 *            AccesoBd
	 * @param logger
	 *            Logger
	 * @throws SQLException
	 */
	private static void cargarAgentes(sns.bd.AccesoBd bd,
			org.apache.log4j.Logger logger) throws java.sql.SQLException {
		LinkedHashMap HAGENTESAUX = new LinkedHashMap();
		String query = "SELECT COD_AGENTE,AGENTE FROM AGENTES order by 1";
		logger.debug(query);
		Vector vAux = bd.consulta(query);
		for (int i = 0; i < vAux.size(); i++) {
			HashMap hResp = (HashMap) vAux.elementAt(i);
			HAGENTESAUX.put(Misc.nz(hResp.get("COD_AGENTE")), Misc.nz(hResp.get("AGENTE")));
		}
		sns.config.Inicializacion.HAGENTES = HAGENTESAUX;
	}

	private static void cargarCaIso(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap CAISO_CAAUX = new HashMap();
		HashMap CA_CAISOAUX = new HashMap();
		String query = "select COD_COMUNIDAD, COD_CA_ISO from COMUNIDADES_AUTONOMAS";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codComunidadAux = Misc.nz(auxHash.get("COD_COMUNIDAD"));
			String codCaIsoAux = Misc.nz(auxHash.get("COD_CA_ISO"));

			CA_CAISOAUX.put(codComunidadAux, codCaIsoAux);
			if (codComunidadAux.length() != 2)
				CA_CAISOAUX.put(Misc.rellenarIzq(codComunidadAux, "0", 2), codCaIsoAux);

			CAISO_CAAUX.put(codCaIsoAux, codComunidadAux);
			if (codCaIsoAux.length() != 2)
				CAISO_CAAUX.put(Misc.rellenarIzq(codCaIsoAux, "0", 2), codComunidadAux);

		}
		sns.config.Inicializacion.CAISO_CA = CAISO_CAAUX;
		sns.config.Inicializacion.CA_CAISO = CA_CAISOAUX;
	}

	private static void cargarCaIsoAgentes(sns.bd.AccesoBd bd) throws java.sql.SQLException {
		HashMap CAISO_AGENTESAUX = new HashMap();
		HashMap CITE_AGENTESAUX = new HashMap();
		String query = "select c.COD_CA_ISO,c.CITE,d.COD_AGENTE from desglose_agentes d,CA_PRESTACION_SERVICIO ca,COMUNIDADES_AUTONOMAS c where c.cod_comunidad=ca.cod_comunidad and ca.COD_PRESTACION_SERVICIO = d.COD_PRESTACION_SERVICIO";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codCaIsoAux = Misc.nz(auxHash.get("COD_CA_ISO"));
			CAISO_AGENTESAUX.put(codCaIsoAux, Misc.nz(auxHash.get("COD_AGENTE")));

			String citeAux = Misc.nz(auxHash.get("CITE"));
			if (!Misc.esVacio(citeAux))
				CITE_AGENTESAUX.put(Misc.nz(auxHash.get("CITE")), Misc.nz(auxHash.get("COD_AGENTE")));

			if (codCaIsoAux.length() != 2)
				CAISO_AGENTESAUX.put(Misc.rellenarIzq(codCaIsoAux, "0", 2), Misc.nz(auxHash.get("COD_AGENTE")));
		}
		sns.config.Inicializacion.CAISO_AGENTES = CAISO_AGENTESAUX;
		sns.config.Inicializacion.CITE_AGENTES = CITE_AGENTESAUX;
	}

	private static void cargarCaAgentesCaIso(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		HashMap CA_AGENTECAISOAUX = new HashMap();
		HashMap AGENTECAISO_CAAUX = new HashMap();
		HashMap AGENTECANOISO_CAAUX = new HashMap();
		String query = "select ca.COD_PRESTACION_SERVICIO,d.COD_AGENTE,c.COD_CA_ISO,c.COD_COMUNIDAD from comunidades_autonomas c, desglose_agentes d,ca_prestacion_servicio ca where d.COD_PRESTACION_SERVICIO=ca.COD_PRESTACION_SERVICIO and c.COD_COMUNIDAD = ca.COD_COMUNIDAD";
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		String[] respuesta = new String[2];
		for (int i = 0; i < sizeAux; i++) {
			respuesta = new String[2];
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codPrestacionServicioAux = Misc.nz(auxHash.get("COD_PRESTACION_SERVICIO"));
			String codComunidadAux = Misc.nz(auxHash.get("COD_COMUNIDAD"));

			respuesta[0] = Misc.nz(auxHash.get("COD_AGENTE"));
			respuesta[1] = Misc.nz(auxHash.get("COD_CA_ISO"));

			CA_AGENTECAISOAUX.put(codPrestacionServicioAux, respuesta);
			if (codPrestacionServicioAux.length() != 2) {
				CA_AGENTECAISOAUX.put(Misc.rellenarIzq(codPrestacionServicioAux, "0", 2), respuesta);
			}

			AGENTECAISO_CAAUX.put(respuesta[0] + ":" + respuesta[1], codPrestacionServicioAux);
			if (respuesta[0].length() != 2 || respuesta[1].length() != 2) {
				AGENTECAISO_CAAUX.put(Misc.rellenarIzq(respuesta[0], "0", 2) + ":" + Misc.rellenarIzq(respuesta[1], "0", 2), codPrestacionServicioAux);
			}

			AGENTECANOISO_CAAUX.put(respuesta[0] + ":" + codComunidadAux, codPrestacionServicioAux);
			if (respuesta[0].length() != 2 || codComunidadAux.length() != 2) {
				AGENTECANOISO_CAAUX.put(Misc.rellenarIzq(respuesta[0], "0", 2) + ":" + Misc.rellenarIzq(codComunidadAux, "0", 2), codPrestacionServicioAux);
			}
		}

		sns.config.Inicializacion.CA_AGENTECAISO = CA_AGENTECAISOAUX;
		sns.config.Inicializacion.AGENTECAISO_CA = AGENTECAISO_CAAUX;
		sns.config.Inicializacion.AGENTECANOISO_CA = AGENTECANOISO_CAAUX;

	}

	private static void cargarAgenteRDL(sns.bd.AccesoBd bd) throws java.sql.SQLException {

		String query = "select COD_AGENTE,COD_TIPO_OPERACION,ACTIVADA from RDL_AGENTES_OPERACIONES order by cod_agente,cod_tipo_operacion";
		LinkedHashMap agentesRDLAux=new LinkedHashMap();
		LinkedHashMap agentesTipoOperacionesRDL=new LinkedHashMap();
		Vector vAux = bd.consulta(query);
		int sizeAux = vAux.size();
		String codAgenteAnterior="";
		for (int i = 0; i < sizeAux; i++) {
			HashMap auxHash = (HashMap) vAux.elementAt(i);
			String codAgente = Misc.nz(auxHash.get("COD_AGENTE"));
			if (!codAgente.equals(codAgenteAnterior)
					&& !codAgenteAnterior.equals("")) {
				agentesRDLAux.put(codAgenteAnterior, agentesTipoOperacionesRDL);
				agentesTipoOperacionesRDL=new LinkedHashMap();
			}
			agentesTipoOperacionesRDL.put(Misc.nz(auxHash.get("COD_TIPO_OPERACION")), Misc.nz(auxHash.get("ACTIVADA")));
			codAgenteAnterior=codAgente;
		}
		agentesRDLAux.put(codAgenteAnterior, agentesTipoOperacionesRDL);
		H_AGENTES_RDL=agentesRDLAux;

	}

	
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////// TRASPASOS LISTA BLANCA
	private static void cargarAgentesTraspasos(sns.bd.AccesoBd bd,org.apache.log4j.Logger logger) {

		ArrayList ARRAY_AGENTES_AUX = new ArrayList();
		HashMap HASH_AGENTES_AUX = new HashMap();
		RegistroBean registroBean;

		try {
			StringBuffer str = new StringBuffer();
			str.append(" SELECT   COD_AGENTE, AGENTE ");
			str.append(" FROM     AGENTES ");
			str.append(" ORDER BY COD_AGENTE ");

			String query = str.toString();

			Vector vAux = bd.consulta(query);
			int sizeAux = vAux.size();

			for (int i = 0; i < sizeAux; i++) {
				HashMap auxHash = (HashMap) vAux.elementAt(i);
				registroBean = new RegistroBean();
				registroBean.setCodigo(Misc.nz(auxHash.get("COD_AGENTE")));
				registroBean.setDescripcion(Misc.nz(auxHash.get("COD_AGENTE")) + "- " + Misc.nz(auxHash.get("AGENTE")));
				ARRAY_AGENTES_AUX.add(registroBean);
				//
				HASH_AGENTES_AUX.put(Misc.nz(auxHash.get("COD_AGENTE")), Misc.nz(auxHash.get("AGENTE")));
			}
			ARRAY_AGENTES_TRASPASOS = ARRAY_AGENTES_AUX;
			HASH_AGENTES_TRASPASOS = HASH_AGENTES_AUX;
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarMotivosTraspasos(sns.bd.AccesoBd bd,org.apache.log4j.Logger logger) throws java.sql.SQLException {
		//
		//
		try {
			HashMap HASH_TRASPASOS_MOTIVOS_AUX = new HashMap();
			ArrayList ARRAY_TRASPASOS_MOTIVOS_AUX = new ArrayList();
			//
			StringBuffer strQuery = new StringBuffer();
			strQuery.append(" SELECT   COD_MOTIVO_TRASPASO, DESC_MOTIVO_TRASPASO ");
			strQuery.append(" FROM     TRASPASOS_MOTIVOS t ");
			strQuery.append(" ORDER BY COD_MOTIVO_TRASPASO ");
			String query = strQuery.toString();
			//
			logger.debug("query: " + query);
			Vector vAux = bd.consulta(query);
			int sizeAux = vAux.size();
			//
			for (int i = 0; i < sizeAux; i++) {
				HashMap auxHash = (HashMap) vAux.elementAt(i);
				RegistroBean registroBean = new RegistroBean();
				registroBean.setCodigo(Misc.nz(auxHash.get("COD_MOTIVO_TRASPASO")));
				registroBean.setDescripcion(Misc.nz(auxHash.get("DESC_MOTIVO_TRASPASO")));
				//
				ARRAY_TRASPASOS_MOTIVOS_AUX.add(registroBean);
				HASH_TRASPASOS_MOTIVOS_AUX.put(Misc.nz(registroBean.getCodigo()), Misc.nz(registroBean.getDescripcion()));
			}
			//
			ARRAY_TRASPASOS_MOTIVOS = ARRAY_TRASPASOS_MOTIVOS_AUX;
			HASH_TRASPASOS_MOTIVOS = HASH_TRASPASOS_MOTIVOS_AUX;
			logger.debug("ARRAY_TRASPASOS_MOTIVOS: " + Inicializacion.ARRAY_TRASPASOS_MOTIVOS.size());
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarRegistrosPorPagina(org.apache.log4j.Logger logger) {
		try {
			if (AL_NUM_REGISTROS.isEmpty()) {
				RegistroBean diezRegistros = new RegistroBean();
				diezRegistros.setCodigo("10");
				diezRegistros.setDescripcion("10");
				AL_NUM_REGISTROS.add(diezRegistros);
				//
				RegistroBean veinteRegistros = new RegistroBean();
				veinteRegistros.setCodigo("20");
				veinteRegistros.setDescripcion("20");
				AL_NUM_REGISTROS.add(veinteRegistros);
				//
				RegistroBean cincuentaRegistros = new RegistroBean();
				cincuentaRegistros.setCodigo("50");
				cincuentaRegistros.setDescripcion("50");
				AL_NUM_REGISTROS.add(cincuentaRegistros);
				//
				RegistroBean cienRegistros = new RegistroBean();
				cienRegistros.setCodigo("100");
				cienRegistros.setDescripcion("100");
				AL_NUM_REGISTROS.add(cienRegistros);
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarDias(org.apache.log4j.Logger logger) {
		ArrayList arrayEstados = new ArrayList();
		RegistroBean registroBean;
		try {
			if (AL_FECHA_DIAS.isEmpty()) {
				for (int i = 1; i <= 31; i++) {
					registroBean = new RegistroBean();
					registroBean.setCodigo(Integer.toString(i));
					registroBean.setDescripcion(Integer.toString(i));
					if (i < 10) {
						registroBean = new RegistroBean();
						registroBean.setCodigo("0" + Integer.toString(i));
						registroBean.setDescripcion("0" + Integer.toString(i));
					}
					arrayEstados.add(registroBean);
				}
				AL_FECHA_DIAS = arrayEstados;
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarMeses(org.apache.log4j.Logger logger) {
		ArrayList arrayEstados = new ArrayList();
		RegistroBean registroBean;
		try {
			if (AL_FECHA_MESES.isEmpty()) {
				for (int i = 1; i <= 12; i++) {
					registroBean = new RegistroBean();
					registroBean.setCodigo(Integer.toString(i));
					registroBean.setDescripcion(Integer.toString(i));
					if (i < 10) {
						registroBean = new RegistroBean();
						registroBean.setCodigo("0" + Integer.toString(i));
						registroBean.setDescripcion("0" + Integer.toString(i));
					}
					arrayEstados.add(registroBean);
				}
				AL_FECHA_MESES = arrayEstados;
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarAnyos(org.apache.log4j.Logger logger) {
		ArrayList arrayEstados = new ArrayList();
		RegistroBean registroBean;
		try {
			int anyoInicio = Constantes.ANYO_DOS_MIL_NUEVE;
			Calendar c = new GregorianCalendar();
			int annio = c.get(Calendar.YEAR);
			int contador = annio - anyoInicio;
			for (int i = 0; i <= contador; i++) {
				registroBean = new RegistroBean();
				registroBean.setCodigo(Integer.toString(anyoInicio + i));
				registroBean.setDescripcion(Integer.toString(anyoInicio + i));
				arrayEstados.add(registroBean);
			}
			AL_FECHA_ANYOS = arrayEstados;
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarHoras(org.apache.log4j.Logger logger) {
		ArrayList arrayEstados = new ArrayList();
		RegistroBean registroBean;
		try {
			if (AL_FECHA_HORAS.isEmpty()) {
				for (int i = 0; i <= 23; i++) {
					registroBean = new RegistroBean();
					registroBean.setCodigo(Integer.toString(i));
					registroBean.setDescripcion(Integer.toString(i));
					if (i < 10) {
						registroBean = new RegistroBean();
						registroBean.setCodigo("0" + Integer.toString(i));
						registroBean.setDescripcion("0" + Integer.toString(i));
					}
					arrayEstados.add(registroBean);
				}
				AL_FECHA_HORAS = arrayEstados;
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarMinutos(org.apache.log4j.Logger logger) {
		ArrayList arrayEstados = new ArrayList();
		RegistroBean registroBean;
		try {
			if (AL_FECHA_MINUTOS.isEmpty()) {
				for (int i = 0; i <= 59; i++) {
					registroBean = new RegistroBean();
					registroBean.setCodigo(Integer.toString(i));
					registroBean.setDescripcion(Integer.toString(i));
					if (i < 10) {
						registroBean = new RegistroBean();
						registroBean.setCodigo("0" + Integer.toString(i));
						registroBean.setDescripcion("0" + Integer.toString(i));
					}
					arrayEstados.add(registroBean);
				}
				AL_FECHA_MINUTOS = arrayEstados;
			}
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarUsuarios(sns.bd.AccesoBd bd,org.apache.log4j.Logger logger) {

		ArrayList AL_USUARIOS_AUX = new ArrayList();
		RegistroBean registroBean;

		try {

			StringBuffer str = new StringBuffer();
			str.append("SELECT U.NOMBRE, U.APELLIDO1, U.APELLIDO2, U.COD_USUARIO ");
			str.append("FROM USUARIOS_SISTEMA U ");
			str.append("ORDER BY U.NOMBRE, U.APELLIDO1, U.APELLIDO2 ");

			String query = str.toString();

			Vector vAux = bd.consulta(query);
			int sizeAux = vAux.size();

			for (int i = 0; i < sizeAux; i++) {
				HashMap auxHash = (HashMap) vAux.elementAt(i);
				registroBean = new RegistroBean();
				registroBean.setCodigo(Misc.nz(auxHash.get("COD_USUARIO")));
				registroBean.setDescripcion(Misc.nz(auxHash.get("NOMBRE")) + " " + Misc.nz(auxHash.get("APELLIDO1")) + " " + Misc.nz(auxHash.get("APELLIDO2")));
				AL_USUARIOS_AUX.add(registroBean);
			}
			AL_USUARIOS = AL_USUARIOS_AUX;

		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	private static void cargarTablasTraspasos(sns.bd.AccesoBd bd,org.apache.log4j.Logger logger) {
		//
		//
		try {
			// METODOS TRASPASOS
			cargarMotivosTraspasos(bd,logger);
			cargarAgentesTraspasos(bd,logger);
			// METODOS PAGINACION TRASPASOS LISTA BLANCA
			cargarRegistrosPorPagina(logger);
			cargarDias(logger);
			cargarMeses(logger);
			cargarAnyos(logger);
			cargarHoras(logger);
			cargarMinutos(logger);
		} catch (Exception e) {
			logger.error("Exception: " + e);
		}
	}

	// ////////////////// TRASPASOS LISTA BLANCA
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	private static void cargarTablas(org.apache.log4j.Logger logger) {
		sns.bd.AccesoBd bd = null;
		try {
			bd = new sns.bd.AccesoBd();
			logger.debug("cargarTablasBasicas");
			cargarTablasBasicas(bd);
			logger.debug("cargarTablasConsultas");
			cargarTablasConsultas(bd);
			logger.debug("cargarRegulaTitulos");
			cargarRegulaTitulos(bd);
			logger.debug("cargarServicioPrestacionAgentes");
			cargarServicioPrestacionAgentes(bd);
			logger.debug("cargarCaIsoAgentes");
			cargarCaIsoAgentes(bd);
			logger.debug("cargarCaIso");
			cargarCaIso(bd);
			logger.debug("cargarCaAgentesCaIso");
			cargarCaAgentesCaIso(bd);
			logger.debug("cargarAseguradoras");
			cargarAseguradoras(bd);
			logger.debug("cargarAseguradorasGestorasColaboradoras");
			cargarAseguradorasGestorasColaboradoras(bd);
			logger.debug("cargarCaPrestacionServicio");
			cargarCaPrestacionServicio(bd);
			logger.debug("cargarCoberturas");
			cargarCoberturas(bd);
			logger.debug("cargarGestorasProveedores");
			cargarGestorasProveedores(bd);
			logger.debug("cargarColaboradoras");
			cargarColaboradoras(bd);
			logger.debug("cargarColaboradorasProveedores");
			cargarColaboradorasProveedores(bd);
			logger.debug("cargarDesgloseAgentes");
			cargarDesgloseAgentes(bd);
			logger.debug("cargarEstados");
			cargarEstados(bd);
			logger.debug("cargarGestoras");
			cargarGestoras(bd);
			logger.debug("cargarPaises");
			cargarPaises(bd);
			logger.debug("cargarProveedores");
			cargarProveedores(bd);
			logger.debug("cargarProvincias");
			cargarProvincias(bd);
			logger.debug("cargarSituaciones");
			cargarSituaciones(bd);
			logger.debug("cargarTitulos");
			cargarTitulos(bd);
			logger.debug("cargarTiposVia");
			cargarTiposVia(bd);
			logger.debug("cargarPrefijosAgentes");
			cargarPrefijosAgentes(bd,logger);
			logger.debug("cargarTablasTraspasos");
			cargarTablasTraspasos(bd,logger);
			logger.debug("cargarAgentes");
			cargarAgentes(bd,logger);
			logger.debug("fin carga tablas maestras");
			cargarUsuarios(bd,logger);
			logger.debug("fin carga usuarios");
			cargarIndicadoresFarmaciaSubIndicadores(bd);
			logger.debug("fin carga IndicadoresFarmaciaSubIndicadores");
			cargarAgenteRDL(bd);
			logger.debug("fin carga cargarAgenteRDL");
			cargarTipoAseguramientoTitulo(bd);
			logger.debug("fin carga cargarTipoAseguramientoTitulo");
		} catch (java.sql.SQLException se) {
			logger.error("Error cargando las tablas iniciales", se);
		} finally {
			if (bd != null)
				bd.cerrar();
		}
	}

	public Inicializacion() {
	}

	public static void init() {
		InputStream defaultStream = null;
		org.apache.log4j.Logger logger =null;
		try {

			logConfigurado = false;
			/**INICIO 8.1***/
			Properties defaultPropsConfig = new Properties();
			ClassLoader classLoader = Inicializacion.class.getClassLoader();

			defaultStream = classLoader.getResourceAsStream(sns.config.Inicializacion.FILECONFIG);

			defaultPropsConfig.load(defaultStream);

			defaultStream.close();

			String pathConfig = new File(Inicializacion.class.getClassLoader().getResource(sns.config.Inicializacion.FILECONFIG).getFile()).getParent();
			/**FIN 8.1**/
			
			/**INICIO 10.3.4***/
//			String pathConfig = EnvEntries.getEnvEntryString("PATH_REPO");
			/**FIN 10.3.4***/

			sns.config.Inicializacion.FILERECOGEXML = pathConfig + "/cfg/RECOGEXML.properties";


			Properties defaultProps = new Properties();

			defaultStream = new FileInputStream(sns.config.Inicializacion.FILERECOGEXML);
			defaultProps.load(defaultStream);

			defaultStream.close();
			defaultStream = null;

			sns.config.Inicializacion.PATHLOGS = Misc.nz(defaultProps.getProperty("pathLogs"), "/aplicaciones/bea8/user_projects/domains/PROD-SNS/repositorio/sns8/ficheros/logs");

			NIVEL_LOG = Integer.parseInt(Misc.nz(defaultProps.getProperty("NIVEL_LOG"), Integer.toString(Level.INFO_INT)));
			DEBUG_ESPECIFICO = Misc.nz(defaultProps.getProperty("DEBUG_ESPECIFICO"));

			initLog(pathConfig);
			
			logger = org.apache.log4j.Logger.getLogger(Inicializacion.class.getName());

			StringTokenizer strDebugEspecifico = new StringTokenizer(DEBUG_ESPECIFICO, "|");
			ArrayList aDebugEspecificoAux = new ArrayList();

			while (strDebugEspecifico.hasMoreElements()) {
				aDebugEspecificoAux.add(strDebugEspecifico.nextToken());
			}

			aDebugEspecifico = aDebugEspecificoAux;

			Enumeration enumLoggers = LogManager.getCurrentLoggers();

			// Add all current loggers to the list
			while (enumLoggers.hasMoreElements()) {

				Logger loggerActual = (Logger) enumLoggers.nextElement();
				String loggerName = loggerActual.getName();
				if (loggerName.startsWith("sns.")) {

					boolean esEspecifico = false;
					for (int i = 0; i < aDebugEspecifico.size(); i++) {
						if (loggerName.endsWith((String) aDebugEspecifico.get(i))) {
							// ponermos el logger en modod debug
							esEspecifico = true;
							loggerActual.setLevel(Level.DEBUG);
						}
					}
					if (!esEspecifico) {
						loggerActual.setLevel(Level.toLevel(NIVEL_LOG));
					}
				} else {
					// System.out.println(new java.util.Date() +
					// " loggerList.getName() [" +loggerName + "]");
				}
			}

			/***************************************************************************************************/
			Vector vAgentesNieAux = new Vector();
			logger.debug("**************************************************************************defaultProps.getProperty(AGENTESNIEFORMATO7): " + defaultProps.getProperty("AGENTESNIEFORMATO7"));
			String sAgentesNie = defaultProps.getProperty("AGENTESNIEFORMATO7");
			if (!Misc.esVacio(sAgentesNie)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesNie, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesNieAux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_NIE = vAgentesNieAux;

			sns.config.Inicializacion.COD_USUARIO_SNS_TESTEO = Misc.nz(defaultProps.getProperty("codUsuarioSnsTesteo"), "BBBBBBBBBB000000");

			sns.config.Inicializacion.XML_RESPUESTA_TESTEO = Misc.nz(defaultProps.getProperty("xmlRespuestaTesteo"));

			// vAGENTES_N010
			Vector vAgentesN010Aux = new Vector();
			String sAgentesN010 = defaultProps.getProperty("AGENTESN010");
			if (!Misc.esVacio(sAgentesN010)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesN010, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesN010Aux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_N010 = vAgentesN010Aux;

			if (Misc.nz(defaultProps.getProperty("activarAuditoriaLopd")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_AUDITORIA_LOPD = true;
			else
				sns.config.Inicializacion.ACTIVAR_AUDITORIA_LOPD = false;

			if (Misc.nz(defaultProps.getProperty("activarAltasPorDefuncion")).equals("on"))
				sns.config.Inicializacion.ABIERTO_COMUNIDADES_DEFUNCION = true;
			else
				sns.config.Inicializacion.ABIERTO_COMUNIDADES_DEFUNCION = false;

			if (Misc.nz(defaultProps.getProperty("activarBajasProcedentesInss")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_BAJAS_PROCEDENTES_INSS = true;
			else
				sns.config.Inicializacion.ACTIVAR_BAJAS_PROCEDENTES_INSS = false;
			
			
			// vAGENTES_N011
			Vector vAgentesN011Aux = new Vector();
			String sAgentesN011 = defaultProps.getProperty("AGENTESN011");
			if (!Misc.esVacio(sAgentesN011)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesN011, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesN011Aux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_N011 = vAgentesN011Aux;

			// vAGENTES_A007
			Vector vAgentesA007Aux = new Vector();
			String sAgentesA007 = Misc.nz(defaultProps.getProperty("AGENTESA007"), "2|3|4|5|6|7|8");
			if (!Misc.esVacio(sAgentesA007)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesA007, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesA007Aux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_A007 = vAgentesA007Aux;

			// vAGENTES_EXT_PROVEEDORES
			Vector vAgentesExtProveedoresAux = new Vector();
			String sAgentesExtProveedores = Misc.nz(defaultProps.getProperty("AGENTES_EXT_PROVEEDORES"), "");
			if (!Misc.esVacio(sAgentesExtProveedores)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesExtProveedores, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesExtProveedoresAux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_EXT_PROVEEDORES = vAgentesExtProveedoresAux;

			// vAGENTES_IU_SEC
			Vector vAgentesIUSecAux = new Vector();
			String sAgentesIUSec = defaultProps.getProperty("AGENTES_IU_SEC");
			if (!Misc.esVacio(sAgentesIUSec)) {
				StringTokenizer strAgentes = new StringTokenizer(sAgentesIUSec, "|");
				String agenteAux = "";
				while (strAgentes.hasMoreElements()) {
					agenteAux = strAgentes.nextToken();
					vAgentesIUSecAux.addElement(agenteAux);
				}
			}
			sns.config.Inicializacion.vAGENTES_IU_SEC = vAgentesIUSecAux;

			sns.config.Inicializacion.MAX_USUARIOS_CONSULTAS = Misc.nz(defaultProps.getProperty("MAX_USUARIOS_CONSULTAS"));

			sns.config.Inicializacion.CCC_ESTANDAR = Misc.nz(defaultProps.getProperty("cccEstandar"), "0000000");
			sns.config.Inicializacion.CCC_MUGEJU = Misc.nz(defaultProps.getProperty("cccMugeju"), "0500200");
			sns.config.Inicializacion.CCC_MUFACE = Misc.nz(defaultProps.getProperty("cccMuface"), "0500500");
			sns.config.Inicializacion.CCC_ISFASA = Misc.nz(defaultProps.getProperty("cccIsfasA"), "0500150");
			sns.config.Inicializacion.CCC_ISFASB = Misc.nz(defaultProps.getProperty("cccIsfasB"), "0500152");
			sns.config.Inicializacion.CCC_ISFASC = Misc.nz(defaultProps.getProperty("cccIsfasC"), "0500151");

			sns.config.Inicializacion.EMAIL_TRASPASOS = Misc.nz(defaultProps.getProperty("usuariosTraspasosMail"));

			sns.config.Inicializacion.PATH_REPOSITORIO_COMPARTIDO = Misc.nz(defaultProps.getProperty("pathRespositorioCompartido"), "/tmp/");

			Vector vListaAgentesNoN010Aux = new Vector();
			String sListaAgentesNoN010 = defaultProps.getProperty("agentesNoN010");
			if (!Misc.esVacio(sListaAgentesNoN010)) {
				StringTokenizer strListaAgentesNoN010 = new StringTokenizer(sListaAgentesNoN010, "|");
				String nombreAux = "";
				while (strListaAgentesNoN010.hasMoreElements()) {
					nombreAux = strListaAgentesNoN010.nextToken();
					vListaAgentesNoN010Aux.addElement(nombreAux);
				}
			}

			sns.config.Inicializacion.LISTA_AGENTES_NO_N010 = vListaAgentesNoN010Aux;

			Vector vColaEntradaAux = new Vector();
			String sListaServidoresColaEntrada = defaultProps.getProperty("LISTA_SERVIDORES_COLA_ENTRADA");
			if (!Misc.esVacio(sListaServidoresColaEntrada)) {
				StringTokenizer strListaServidoresColaEntrada = new StringTokenizer(sListaServidoresColaEntrada, "|");
				String nombreAux = "";
				while (strListaServidoresColaEntrada.hasMoreElements()) {
					nombreAux = strListaServidoresColaEntrada.nextToken();
					vColaEntradaAux.addElement(nombreAux);
				}
			}

			sns.config.Inicializacion.LISTA_SERVIDORES_COLA_ENTRADA = vColaEntradaAux;

			sns.config.Inicializacion.COLAASYNINTERCAMBIADOR = defaultProps.getProperty("colaAsynIntercambiador");
			sns.config.Inicializacion.URLSINTERCAMBIADOR = defaultProps.getProperty("urlsIntercambiador");

			sns.config.Inicializacion.URLINIT = Misc.nz(defaultProps.getProperty("urlInit"), "/initConfigAction.do");

			sns.config.Inicializacion.EMAIL_ALERTAS_SISTEMA = defaultProps.getProperty("emailAlertasSistema");
			sns.config.Inicializacion.ASUNTO_EMAIL_ALERTAS_SISTEMA = defaultProps.getProperty("asuntoEmailAlertasSistema");

			if (Misc.nz(defaultProps.getProperty("activarGuardarAlertasSistema")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_GUARDAR_ALERTAS_SISTEMA = true;
			else
				sns.config.Inicializacion.ACTIVAR_GUARDAR_ALERTAS_SISTEMA = false;

			if (Misc.nz(defaultProps.getProperty("activarEmailAlertasSistema")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_EMAIL_ALERTAS_SISTEMA = true;
			else
				sns.config.Inicializacion.ACTIVAR_EMAIL_ALERTAS_SISTEMA = false;

			if (defaultProps.getProperty("stop").equals("on"))
				sns.config.Inicializacion.STOP = true;
			else
				sns.config.Inicializacion.STOP = false;

			if (Misc.nz(defaultProps.getProperty("eliminarTagCip")).equals("on"))
				sns.config.Inicializacion.ELIMINAR_TAG_CIP = true;
			else
				sns.config.Inicializacion.ELIMINAR_TAG_CIP = false;

			if (Misc.nz(defaultProps.getProperty("activadoPorCertificado")).equals("on"))
				sns.config.Inicializacion.ACTIVADO_POR_CERTIFICADO = true;
			else
				sns.config.Inicializacion.ACTIVADO_POR_CERTIFICADO = false;

			if (Misc.nz(defaultProps.getProperty("activadoSoporteCacheo")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_SOPORTE_CACHEO = true;
			else
				sns.config.Inicializacion.ACTIVAR_SOPORTE_CACHEO = false;

			if (Misc.nz(defaultProps.getProperty("activarNieSiete")).equals("on"))
				sns.config.Inicializacion.ACTIVADO_NIE_SIETE = true;
			else
				sns.config.Inicializacion.ACTIVADO_NIE_SIETE = false;

			if (Misc.nz(defaultProps.getProperty("activarSinRecursosMayor65"),"on").equals("on"))
				sns.config.Inicializacion.ACTIVADO_SIN_RECURSOS_MAYORES_65 = true;
			else
				sns.config.Inicializacion.ACTIVADO_SIN_RECURSOS_MAYORES_65 = false;
			
			sns.config.Inicializacion.NOMBRE_TABLA_INSS= Misc.nz(defaultProps.getProperty("nombreTablaInss"),"INSS_TIT");
			sns.config.Inicializacion.NOMBRE_TABLA_INSS_SNS= Misc.nz(defaultProps.getProperty("nombreTablaInssSns"),"INSS_SNS_TIT");
			sns.config.Inicializacion.NOMBRE_TABLA_INSS_BAJAS= Misc.nz(defaultProps.getProperty("nombreTablaInssBajas"),"INSS_TIT_BAJAS");
			
			
			
			HOLGURA_FECHA_INICIO_TEMPORALIDAD=Long.parseLong(Misc.nz(defaultProps.getProperty("holguraFechaInicioTemporalidad"),"4"));
			
			String sCamposNoVisibles = defaultProps.getProperty("camposNoVisibles");
			Vector camposNoVisiblesAux = new Vector();
			logger.info("sCamposNoVisibles: " + sCamposNoVisibles);
			if (!Misc.esVacio(sCamposNoVisibles)) {
				StringTokenizer strCamposNoVisibles = new StringTokenizer(sCamposNoVisibles, "|");
				String campoNoVisible = "";
				while (strCamposNoVisibles.hasMoreElements()) {
					campoNoVisible = strCamposNoVisibles.nextToken();
					camposNoVisiblesAux.addElement(campoNoVisible);
				}
			}

			sns.config.Inicializacion.CAMPOSNOVISIBLES = camposNoVisiblesAux;

			String sAgentesCamposNoVisibles = defaultProps.getProperty("agentesCamposNoVisibles");
			Vector agentesCamposNoVisiblesAux = new Vector();
			logger.info("sAgentesCamposNoVisibles: " + sAgentesCamposNoVisibles);
			if (!Misc.esVacio(sAgentesCamposNoVisibles)) {
				StringTokenizer strAgentesCamposNoVisibles = new StringTokenizer(sAgentesCamposNoVisibles, "|");
				String agenteCamposNoVisibles = "";
				while (strAgentesCamposNoVisibles.hasMoreElements()) {
					agenteCamposNoVisibles = strAgentesCamposNoVisibles.nextToken();
					agentesCamposNoVisiblesAux.addElement(agenteCamposNoVisibles);
				}
			}
			sns.config.Inicializacion.AGENTESCAMPOSNOVISIBLES = agentesCamposNoVisiblesAux;

			String sCamposHistorico = defaultProps.getProperty("camposHistorico");
			Vector camposHistoricoAux = new Vector();
			logger.info("sCamposHistorico: " + sCamposHistorico);
			if (!Misc.esVacio(sCamposHistorico)) {
				StringTokenizer strCamposHistorico = new StringTokenizer(sCamposHistorico, "|");
				String campoHistorico = "";
				while (strCamposHistorico.hasMoreElements()) {
					campoHistorico = strCamposHistorico.nextToken();
					camposHistoricoAux.addElement(campoHistorico);
				}
			}
			sns.config.Inicializacion.CAMPOSHISTORICO = camposHistoricoAux;

			String sCamposHistoricoAlias = defaultProps.getProperty("camposHistoricoAlias");
			Vector camposHistoricoAliasAux = new Vector();
			logger.info("sCamposHistoricoAlias: " + sCamposHistoricoAlias);
			if (!Misc.esVacio(sCamposHistoricoAlias)) {
				StringTokenizer strCamposHistoricoAlias = new StringTokenizer(sCamposHistoricoAlias, "|");
				String campoHistoricoAlias = "";
				while (strCamposHistoricoAlias.hasMoreElements()) {
					campoHistoricoAlias = strCamposHistoricoAlias.nextToken();
					camposHistoricoAliasAux.addElement(campoHistoricoAlias);
				}
			}
			sns.config.Inicializacion.CAMPOSHISTORICOALIAS = camposHistoricoAliasAux;

			String sListaEstadosVisibles = Misc.nz(defaultProps.getProperty("LISTA_ESTADOS_VISIBLES"), "0,1,2,3");
			Vector vListaEstadosVisiblesAux = new Vector();
			logger.info("ListaEstadosVisibles: " + sListaEstadosVisibles);
			if (!sns.util.Misc.esVacio(sListaEstadosVisibles)) {
				StringTokenizer strEstados = new StringTokenizer(sListaEstadosVisibles, ",");
				String estado = "";
				while (strEstados.hasMoreElements()) {
					estado = strEstados.nextToken();
					vListaEstadosVisiblesAux.addElement(estado);
				}
			}

			// INIT CONSULTA
			LISTA_OP_CONSULTAS = new Vector();
			LISTA_OP_CONSULTAS.add(Constantes.C001);
			LISTA_OP_CONSULTAS.add(Constantes.C002);
			LISTA_OP_CONSULTAS.add(Constantes.C007);
			LISTA_OP_CONSULTAS.add(Constantes.C010);
			LISTA_OP_CONSULTAS.add(Constantes.C012);
			// INIT ASINCRONAS
			LISTA_OP_ASINCRONAS = new Vector();
			LISTA_OP_ASINCRONAS.add(Constantes.A002);
			LISTA_OP_ASINCRONAS.add(Constantes.A003);
			LISTA_OP_ASINCRONAS.add(Constantes.A004);
			// INIT ALTAS
			LISTA_OP_ALTAS = new Vector();
			LISTA_OP_ALTAS.add(Constantes.A006);
			LISTA_OP_ALTAS.add(Constantes.A007);
			LISTA_OP_ALTAS.add(Constantes.A008);

			sns.config.Inicializacion.LISTA_ESTADOS_VISIBLES = vListaEstadosVisiblesAux;

			sns.config.Inicializacion.LISTA_ESTADOS_CONSULTA = Misc.nz(defaultProps.getProperty("LISTA_ESTADOS_CONSULTA"), "0,1,2,4");

			sns.config.Inicializacion.NUMERO_REINTENTOS_ENTRADA = Integer.parseInt(Misc.nz(defaultProps.getProperty("NUMERO_REINTENTOS_ENTRADA"), "10"));

			sns.config.Inicializacion.WSDLURLSINTERCAMBIADOR = Misc.nz(defaultProps.getProperty("wsdlUrlIntercambiador"));
			sns.config.Inicializacion.MODOACCESOINTERCAMBIADOR = Integer.parseInt(Misc.nz(defaultProps.getProperty("modoAccesoIntercambiador"), "0"));
			sns.config.Inicializacion.IDSERVICIO = Misc.nz(defaultProps.getProperty("idServicio"));

			sns.config.Inicializacion.SERVIDORDTDS = Misc.nz(defaultProps.getProperty("SERVIDORDTDS"), "http://sns.msc.es/dtds");

			sns.config.Inicializacion.WSDL_REVOCADOS_WEBSERVICES = Misc.nz(defaultProps.getProperty("WSDL_REVOCADOS_WEBSERVICES"), "http://10.15.5.20:6019/web-cert/Certificacion?WSDL");
			sns.config.Inicializacion.IDAPP_REVOCADOS_WEBSERVICES = Misc.nz(defaultProps.getProperty("IDAPP_REVOCADOS_WEBSERVICES"), "http://10.15.5.20:6019/web-cert/Certificacion?WSDL");

			sns.config.Inicializacion.HOST_SMTP = Misc.nz(defaultProps.getProperty("host"), "10.15.17.111");
			sns.config.Inicializacion.EMAIL_SOPORTE = Misc.nz(defaultProps.getProperty("emailSoporte"), "igarcias@msc.es");

			sns.config.Inicializacion.USUARIOSNSADMIN = Misc.nz(defaultProps.getProperty("usuarioSnsAdmin"));
			sns.config.Inicializacion.PASSWORDSNSADMIN = Misc.nz(defaultProps.getProperty("passwordSnsAdmin"));

			sns.config.Inicializacion.EMAIL_REMITENTE = Misc.nz(defaultProps.getProperty("remitente"), "sns@msc.es");
			sns.config.Inicializacion.PREFIJOAPP = Misc.nz(defaultProps.getProperty("prefijoApp"));

			sns.config.Inicializacion.LONGITUD_PREFIJOS_AGENTES = Integer.parseInt(gasai.util.Misc.nz(defaultProps.getProperty("longitudPrefijosAgentes"), "3"));

			sns.config.Inicializacion.TIEMPOBLOQUEOCOLAOUTERROR = Integer.parseInt(gasai.util.Misc.nz(defaultProps.getProperty("tiempoBloqueoOutError"), "0"));

			sns.config.Inicializacion.RESPOSITORIO_FICHEROS_LISTABLANCA = pathConfig + "/cfg/";

			sns.config.Inicializacion.PATH_FICHEROS_SIN_PROCESAR = Misc.nz(defaultProps.getProperty("pathFicherosSinProcesar"), "/tmp/");

			if (Misc.nz(defaultProps.getProperty("borrarFicherosListaBlanca")).equals("on"))
				sns.config.Inicializacion.BORRAR_FICHEROS_LISTABLANCA = true;
			else
				sns.config.Inicializacion.BORRAR_FICHEROS_LISTABLANCA = false;

			if (Misc.nz(defaultProps.getProperty("ponerDtd")).equals("on"))
				sns.config.Inicializacion.PONERDTD = true;
			else
				sns.config.Inicializacion.PONERDTD = false;

			if (Misc.nz(defaultProps.getProperty("bloquearTraspasosErroneos")).equals("on"))
				sns.config.Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS = true;
			else
				sns.config.Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS = false;

			if (Misc.nz(defaultProps.getProperty("notificarBloquearTraspasosErroneos")).equals("on"))
				sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS = true;
			else
				sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS = false;

			sns.config.Inicializacion.ACTIVAR_RESTRICCION_BAJA_DUPLICIDAD = Misc.nz(defaultProps.getProperty("activarRestriccionBajaDuplicidad")).equals("on");

			sns.config.Inicializacion.ACTIVAR_RESTRICCION_BAJAS_HOSPITALARIAS = Misc.nz(defaultProps.getProperty("activarRestriccionBajasHospitalarias")).equals("on");

			sns.config.Inicializacion.BLOQUEAR_BAJAS_DEFUNCION_OTRAS_CA = Misc.nz(defaultProps.getProperty("bloquearBajasDefuncionOtrasCa")).equals("on");

			sns.config.Inicializacion.NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO = Integer.parseInt(Misc.nz(defaultProps.getProperty("numeroMaximoCambiosBloquearTraspaso"), "3"));
			sns.config.Inicializacion.REVISAR_NAF = Misc.nz(defaultProps.getProperty("revisarNaf")).equals("on");
			sns.config.Inicializacion.REVISAR_DNI = Misc.nz(defaultProps.getProperty("revisarDni")).equals("on");
			sns.config.Inicializacion.REVISAR_NAF_TITULAR = Misc.nz(defaultProps.getProperty("revisarNafTitular")).equals("on");

			sns.config.Inicializacion.ACTIVADO_CONTROL_TRANSACCIONES = Misc.nz(defaultProps.getProperty("activarControlTransacciones")).equals("on");

			sns.config.Inicializacion.BLOQUEAR_TRASPASOS_AUTOMATICAMENTE = Misc.nz(defaultProps.getProperty("bloquearTraspasosAutomaticamente")).equals("on");
			sns.config.Inicializacion.ACTIVO_REVISAR_TRASPASOS = Misc.nz(defaultProps.getProperty("activoRevisarTraspasos")).equals("on");
			sns.config.Inicializacion.URL_REVISAR_TRASPASOS = Misc.nz(defaultProps.getProperty("urlRevisarTraspasos"));

			sns.config.Inicializacion.TEXTO_BLOQUEAR_POR_CAMBIOS_CIP = Misc.nz(defaultProps.getProperty("textoBloquearCambiosCip"));
			sns.config.Inicializacion.ACTIVO_REVISAR_CAMBIOS_CIP = Misc.nz(defaultProps.getProperty("activoRevisarCambiosCip")).equals("on");

			sns.config.Inicializacion.ACTIVADO_CORREO = Misc.nz(defaultProps.getProperty("activarCorreo")).equals("on");

			sns.config.Inicializacion.TEXTO_BLOQUEAR_TRASPASOS_ERRONEOS = Misc.nz(defaultProps.getProperty("textoBloquearTraspasosErroneos"));

			sns.config.Inicializacion.PATH_FICHERO_BAJAS_JUSTICIA = Misc.nz(defaultProps.getProperty("pathFicheroBajasJusticia"));

			sns.config.Inicializacion.FICHERO_BAJAS_JUSTICIA = Misc.nz(defaultProps.getProperty("ficheroBajasJusticia"), "bajasMju.txt");
			sns.config.Inicializacion.EMAIL_BAJAS_MJU = Misc.nz(defaultProps.getProperty("listaDistribucionMju"), "amartinezma@externos.msssi.es;mmacias@msssi.es;soporte_sns@msssi.es");
			sns.config.Inicializacion.ASUNTO_EMAIL_BAJAS_MJU = Misc.nz(defaultProps.getProperty("asuntoDistribucionMju"), "Bajas de justicia");
			
			if (Misc.nz(defaultProps.getProperty("bloquearTraspasosListaNegra")).equals("on"))
				sns.config.Inicializacion.BLOQUEAR_TRASPASOS_LISTA_NEGRA = true;
			else
				sns.config.Inicializacion.BLOQUEAR_TRASPASOS_LISTA_NEGRA = false;

			if (Misc.nz(defaultProps.getProperty("notificarBloquearTraspasosListaNegra")).equals("on"))
				sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA = true;
			else
				sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA = false;

			sns.config.Inicializacion.TEXTO_BLOQUEAR_TRASPASOS_LISTA_NEGRA = Misc.nz(defaultProps.getProperty("textoBloquearTraspasosListaNegra"));

			if (Misc.nz(defaultProps.getProperty("seguridadWeblogic")).equals("on"))
				sns.config.Inicializacion.SEGURIDAD_WEBLOGIC = true;
			else
				sns.config.Inicializacion.SEGURIDAD_WEBLOGIC = false;

			if (Misc.nz(defaultProps.getProperty("pintarXml")).equals("on"))
				sns.config.Inicializacion.PINTAR_XML = true;
			else
				sns.config.Inicializacion.PINTAR_XML = false;

			if (Misc.nz(defaultProps.getProperty("fijarClass")).equals("on"))
				sns.config.Inicializacion.FIJARCLASSLOADER = true;
			else
				sns.config.Inicializacion.FIJARCLASSLOADER = false;

			if (Misc.nz(defaultProps.getProperty("revocadosWebServices")).equals("on"))
				sns.config.Inicializacion.REVOCADOS_WEBSERVICES = true;
			else
				sns.config.Inicializacion.REVOCADOS_WEBSERVICES = false;

			sns.config.Inicializacion.TIME_TO_DELIVER_COLAERROR = Integer.parseInt(Misc.nz(defaultProps.getProperty("timeToDeliverColaError"),"-1"));
			sns.config.Inicializacion.TIME_TO_DELIVER_COLAGESTION = Integer.parseInt(Misc.nz(defaultProps.getProperty("timeToDeliverColaGestion"),"-1"));

			sns.config.Inicializacion.TIEMPO_CUARENTENA_PASO_BENEFICIARIO_TITULAR = Long.parseLong(Misc.nz(defaultProps.getProperty("tiempoCuarentenaTitularBeneficiario"),"20000"));
			sns.config.Inicializacion.TEXTO_BLOQUEAR_PASO_BENEFICIARIO_TITULAR = Misc.nz(defaultProps.getProperty("textoBloquearTitularBeneficiario"));
			
			sns.config.Inicializacion.PATH_IFI = Misc.nz(defaultProps.getProperty("pathIFI"),"/aplicaciones/bea8/user_projects/domains/PROD-SNS/repositorio/sns8/procesosProduccion/ClienteIfi/");

			sns.config.Inicializacion.PATH_RELATIVO_RECIBIDOS_IFI = Misc.nz(defaultProps.getProperty("pathRelativoRecibidosIFI"),"/recibidos/");
			sns.config.Inicializacion.PATH_RELATIVO_ARCHIVADOS_IFI = Misc.nz(defaultProps.getProperty("pathRelativoArchivadosIFI"),"/archivados/");

			sns.config.Inicializacion.PREFIJO_FICHERO_IFI = Misc.nz(defaultProps.getProperty("prefijoFicheroIFI"),"IFI.CFASABMR.D");
			
			sns.config.Inicializacion.NOMBRE_FICHERO_SINCRONIZACION_INSS_SNS = Misc.nz(defaultProps.getProperty("nombreFicheroSincronizacionInssSns"),"sincronizacion.xml");
			

			if (Misc.nz(defaultProps.getProperty("enviarActualizacionIFI")).equals("on")) {
				sns.config.Inicializacion.ENVIAR_ACTUALIZACION_IFI = true;
			}else {
				sns.config.Inicializacion.ENVIAR_ACTUALIZACION_IFI = false;
			}

			if (Misc.nz(defaultProps.getProperty("activarColaJmsOsb")).equals("on"))
				sns.config.Inicializacion.ACTIVAR_COLA_JMS_OSB = true;
			else
				sns.config.Inicializacion.ACTIVAR_COLA_JMS_OSB = false;

			if (Misc.nz(defaultProps.getProperty("activarCopiarColaJmsOsb")).equals("on"))
				sns.config.Inicializacion.OSB_ACTIVAR_COPIAR_COLA_JMS = true;
			else
				sns.config.Inicializacion.OSB_ACTIVAR_COPIAR_COLA_JMS = false;

			sns.config.Inicializacion.OSB_NOMBRE_COLA_JMS = Misc.nz(defaultProps.getProperty("nombreColaJmsOsb"));
			
			if (Misc.nz(defaultProps.getProperty("permitirVinculacionSinEstadoInss")).equals("on"))
				sns.config.Inicializacion.INSS_PERMITIR_VINCULAR_SIN_ESTADO = true;
			else
				sns.config.Inicializacion.INSS_PERMITIR_VINCULAR_SIN_ESTADO = false;

			
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("cargando la configuracion inicial", e);
			// return (e.getMessage() + " - " + e.toString());
		} finally {
			try {
				if (defaultStream != null)
					defaultStream.close();
			} catch (IOException e) {
				logger.error("cargando la configuracion inicial Cerrando el properties", e);
			}
		}

		cargarTablas(logger);

		logger.info("HAGENTES: " + sns.config.Inicializacion.HAGENTES.size());
		logger.info("CA_AGENTECAISO: " + sns.config.Inicializacion.CA_AGENTECAISO.size());
		logger.info("CA_CAISO: " + sns.config.Inicializacion.CA_CAISO.size());
		logger.info("CAISO_CA: " + sns.config.Inicializacion.CAISO_CA.size());
		logger.info("CAISO_CA: " + sns.config.Inicializacion.CAISO_CA);
		logger.info("CAISO_AGENTES: " + sns.config.Inicializacion.CAISO_AGENTES.size());
		logger.info("SERVICIOPRESTACION_AGENTES: " + sns.config.Inicializacion.SERVICIOPRESTACION_AGENTES.size());
		logger.info("ASEGURADORAS: " + sns.config.Inicializacion.hASEGURADORAS.size());
		logger.info("ASEGURADORAS_GESTORAS: " + sns.config.Inicializacion.hASEGURADORAS_GESTORAS.size());
		logger.info("ASEGURADORAS_COLABORADORAS: " + sns.config.Inicializacion.hASEGURADORAS_COLABORADORAS.size());
		logger.info("CA_PRESTACION_SERVICIO: " + sns.config.Inicializacion.hCA_PRESTACION_SERVICIO.size());
		logger.info("COBERTURAS: " + sns.config.Inicializacion.hCOBERTURAS.size());
		logger.info("GESTORAS_PROVEEDORES: " + sns.config.Inicializacion.hGESTORAS_PROVEEDORES.size());
		logger.info("COLABORADORAS: " + sns.config.Inicializacion.hCOLABORADORAS.size());
		logger.info("COLABORADORAS_PROVEEDORES: " + sns.config.Inicializacion.hCOLABORADORAS_PROVEEDORES.size());
		logger.info("DESGLOSE_AGENTES: " + sns.config.Inicializacion.hDESGLOSE_AGENTES.size());
		logger.info("ESTADOS: " + sns.config.Inicializacion.hESTADOS.size());
		logger.info("GESTORAS: " + sns.config.Inicializacion.hGESTORAS.size());
		logger.info("PAISES: " + sns.config.Inicializacion.hPAIS.size());
		logger.info("PROVEEDORES: " + sns.config.Inicializacion.hPROVEEDOR.size());
		logger.info("SITUACIONES: " + sns.config.Inicializacion.hSITUACIONES.size());
		logger.info("TITULOS: " + sns.config.Inicializacion.hTITULOS.size());
		logger.info("TIPOS DE VIA: " + sns.config.Inicializacion.hTIPOS_VIA.size());

		logger.info("FILERECOGEXML: " + sns.config.Inicializacion.FILERECOGEXML);
		logger.info("REVOCADOS_WEBSERVICES: " + sns.config.Inicializacion.REVOCADOS_WEBSERVICES);
		logger.info("WSDL_REVOCADOS_WEBSERVICES: " + sns.config.Inicializacion.WSDL_REVOCADOS_WEBSERVICES);
		logger.info("IDAPP_REVOCADOS_WEBSERVICES: " + sns.config.Inicializacion.IDAPP_REVOCADOS_WEBSERVICES);

		logger.info("SERVIDORDTDS: " + sns.config.Inicializacion.SERVIDORDTDS);

		logger.info("AGENTES CON NIE DE 7: " + sns.config.Inicializacion.vAGENTES_NIE);

		logger.info("HOST_SMTP: " + sns.config.Inicializacion.HOST_SMTP);
		logger.info("EMAIL_SOPORTE: " + sns.config.Inicializacion.EMAIL_SOPORTE);
		logger.info("EMAIL_REMITENTE: " + sns.config.Inicializacion.EMAIL_REMITENTE);

		logger.info("PREFIJOAPP: " + sns.config.Inicializacion.PREFIJOAPP);

		logger.info("TIEMPOBLOQUEOCOLAOUTERROR: " + sns.config.Inicializacion.TIEMPOBLOQUEOCOLAOUTERROR);

		logger.info("CAMPOSNOVISIBLES: " + sns.config.Inicializacion.CAMPOSNOVISIBLES);
		logger.info("AGENTESCAMPOSNOVISIBLES: " + sns.config.Inicializacion.AGENTESCAMPOSNOVISIBLES);
		logger.info("CAMPOSHISTORICO: " + sns.config.Inicializacion.CAMPOSHISTORICO);
		logger.info("CAMPOSHISTORICOALIAS: " + sns.config.Inicializacion.CAMPOSHISTORICOALIAS);

		logger.info("LISTA_ESTADOS_CONSULTA: " + sns.config.Inicializacion.LISTA_ESTADOS_CONSULTA);
		logger.info("LISTA_ESTADOS_VISIBLES: " + sns.config.Inicializacion.LISTA_ESTADOS_VISIBLES);

		logger.info("FIJAR CLASSLOADER: " + FIJARCLASSLOADER);

		logger.info("COLAASYNINTERCAMBIADOR: " + sns.config.Inicializacion.COLAASYNINTERCAMBIADOR);
		logger.info("URLSINTERCAMBIADOR: " + sns.config.Inicializacion.URLSINTERCAMBIADOR);

		logger.info("IDSERVICIO: " + sns.config.Inicializacion.IDSERVICIO);

		logger.info("LISTA_TABLAS: " + sns.config.Inicializacion.LISTA_TABLAS.size());
		logger.info("LISTA_CONSULTA_TABLAS: " + sns.config.Inicializacion.LISTA_CONSULTA_TABLAS.size());

		logger.info("PONERDTD: " + sns.config.Inicializacion.PONERDTD);

		logger.info("WSDLURLSINTERCAMBIADOR: " + sns.config.Inicializacion.WSDLURLSINTERCAMBIADOR);
		logger.info("MODOACCESOINTERCAMBIADOR: " + sns.config.Inicializacion.MODOACCESOINTERCAMBIADOR);

		logger.info("PINTAR_XML: " + sns.config.Inicializacion.PINTAR_XML);

		logger.info("AGENTES N010: " + sns.config.Inicializacion.vAGENTES_N010);

		logger.info("SEGURIDAD_WEBLOGIC: " + sns.config.Inicializacion.SEGURIDAD_WEBLOGIC);

		logger.info("LISTA_SERVIDORES_COLA_ENTRADA: " + sns.config.Inicializacion.LISTA_SERVIDORES_COLA_ENTRADA);

		logger.info("sns.config.Inicializacion.NUMERO_REINTENTOS_ENTRADA: " + sns.config.Inicializacion.NUMERO_REINTENTOS_ENTRADA);

		logger.info("URLINIT: " + sns.config.Inicializacion.URLINIT);

		logger.info("INFTABLASAFECTADAS -> " + sns.config.Inicializacion.INFTABLASAFECTADAS);
		logger.info("DATOSTABLASAFECTADAS -> " + sns.config.Inicializacion.DATOSTABLASAFECTADAS.size());

		logger.info("hREGULA_TITULOS: " + sns.config.Inicializacion.hREGULA_TITULOS);
		logger.info("vCCC_REGULA_TITULOS: " + sns.config.Inicializacion.vCCC_REGULA_TITULOS);

		logger.info("CCC_ESTANDAR: " + sns.config.Inicializacion.CCC_ESTANDAR);
		logger.info("CCC_MUGEJU: " + sns.config.Inicializacion.CCC_MUGEJU);
		logger.info("CCC_MUFACE: " + sns.config.Inicializacion.CCC_MUFACE);
		logger.info("CCC_ISFASA: " + sns.config.Inicializacion.CCC_ISFASA);
		logger.info("CCC_ISFASB: " + sns.config.Inicializacion.CCC_ISFASB);
		logger.info("CCC_ISFASC: " + sns.config.Inicializacion.CCC_ISFASC);

		logger.info("LISTA_AGENTES_NO_N010: " + sns.config.Inicializacion.LISTA_AGENTES_NO_N010);

		logger.info("COD_USUARIO_SNS_TESTEO: " + sns.config.Inicializacion.COD_USUARIO_SNS_TESTEO);

		logger.info("XML_RESPUESTA_TESTEO: " + sns.config.Inicializacion.XML_RESPUESTA_TESTEO);

		logger.info("BLOQUEAR_TRASPASOS_ERRONEOS: " + sns.config.Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS);
		logger.info("NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS: " + sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS);

		logger.info("TEXTO_BLOQUEAR_TRASPASOS_ERRONEOS: " + sns.config.Inicializacion.TEXTO_BLOQUEAR_TRASPASOS_ERRONEOS);

		logger.info("vAGENTES_A007: " + sns.config.Inicializacion.vAGENTES_A007);
		logger.info("vAGENTES_IU_SEC: " + sns.config.Inicializacion.vAGENTES_IU_SEC);

		logger.info("ELIMINAR_TAG_CIP: " + sns.config.Inicializacion.ELIMINAR_TAG_CIP);

		logger.info("vAGENTES_EXT_PROVEEDORES: " + sns.config.Inicializacion.vAGENTES_EXT_PROVEEDORES);

		logger.info("MAX_USUARIOS_CONSULTAS: " + sns.config.Inicializacion.MAX_USUARIOS_CONSULTAS);

		logger.info("RESPOSITORIO_FICHEROS_LISTABLANCA: " + sns.config.Inicializacion.RESPOSITORIO_FICHEROS_LISTABLANCA);
		logger.info("BORRAR_FICHEROS_LISTABLANCA: " + sns.config.Inicializacion.BORRAR_FICHEROS_LISTABLANCA);
		logger.info("PREFIJOS_AGENTES: " + sns.config.Inicializacion.hPREFIJOS_AGENTES);
		logger.info("LONGITUD_PREFIJOS_AGENTES: " + sns.config.Inicializacion.LONGITUD_PREFIJOS_AGENTES);

		logger.info("EMAIL_TRASPASOS: " + sns.config.Inicializacion.EMAIL_TRASPASOS);

		logger.info("AGENTES N011: " + sns.config.Inicializacion.vAGENTES_N011);

		logger.info("BLOQUEAR_TRASPASOS_LISTA_NEGRA: " + sns.config.Inicializacion.BLOQUEAR_TRASPASOS_LISTA_NEGRA);
		logger.info("NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA: " + sns.config.Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA);
		logger.info("TEXTO_BLOQUEAR_TRASPASOS_LISTA_NEGRA: " + sns.config.Inicializacion.TEXTO_BLOQUEAR_TRASPASOS_LISTA_NEGRA);

		logger.info("ACTIVADO_POR_CERTIFICADO: " + sns.config.Inicializacion.ACTIVADO_POR_CERTIFICADO);

		logger.info("PATH_FICHERO_BAJAS_JUSTICIA: " + sns.config.Inicializacion.PATH_FICHERO_BAJAS_JUSTICIA);
		logger.info("FICHERO_BAJAS_JUSTICIA: " + sns.config.Inicializacion.FICHERO_BAJAS_JUSTICIA);

		logger.info("PATH_REPOSITORIO_COMPARTIDO: " + sns.config.Inicializacion.PATH_REPOSITORIO_COMPARTIDO);
		logger.info("ACTIVAR_SOPORTE_CACHEO: " + sns.config.Inicializacion.ACTIVAR_SOPORTE_CACHEO);

		logger.info("ACTIVAR_GUARDAR_ALERTAS_SISTEMA: " + sns.config.Inicializacion.ACTIVAR_GUARDAR_ALERTAS_SISTEMA);
		logger.info("ACTIVAR_EMAIL_ALERTAS_SISTEMA: " + sns.config.Inicializacion.ACTIVAR_EMAIL_ALERTAS_SISTEMA);
		logger.info("ASUNTO_EMAIL_ALERTAS_SISTEMA: " + sns.config.Inicializacion.ASUNTO_EMAIL_ALERTAS_SISTEMA);
		logger.info("EMAIL_ALERTAS_SISTEMA: " + sns.config.Inicializacion.EMAIL_ALERTAS_SISTEMA);

		logger.info("NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO: " + sns.config.Inicializacion.NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO);
		logger.info("REVISAR_NAF: " + sns.config.Inicializacion.REVISAR_NAF);
		logger.info("REVISAR_NAF_TITULAR: " + sns.config.Inicializacion.REVISAR_NAF_TITULAR);
		logger.info("REVISAR_DNI: " + sns.config.Inicializacion.REVISAR_DNI);
		logger.info("BLOQUEAR_TRASPASOS_AUTOMATICAMENTE: " + sns.config.Inicializacion.BLOQUEAR_TRASPASOS_AUTOMATICAMENTE);
		logger.info("ACTIVO_REVISAR_TRASPASOS: " + sns.config.Inicializacion.ACTIVO_REVISAR_TRASPASOS);
		logger.info("URL_REVISAR_TRASPASOS: " + sns.config.Inicializacion.URL_REVISAR_TRASPASOS);

		logger.info("ACTIVADO_CONTROL_TRANSACCIONES: " + sns.config.Inicializacion.ACTIVADO_CONTROL_TRANSACCIONES);

		logger.info("TEXTO_BLOQUEAR_POR_CAMBIOS_CIP: " + sns.config.Inicializacion.TEXTO_BLOQUEAR_POR_CAMBIOS_CIP);
		logger.info("ACTIVO_REVISAR_CAMBIOS_CIP: " + sns.config.Inicializacion.ACTIVO_REVISAR_CAMBIOS_CIP);

		logger.info("ACTIVADO_CORREO: " + sns.config.Inicializacion.ACTIVADO_CORREO);
		logger.info("PATH_FICHEROS_SIN_PROCESAR: " + sns.config.Inicializacion.PATH_FICHEROS_SIN_PROCESAR);
		logger.info("PATH DEL LOG: " + sns.config.Inicializacion.PATHLOGS);

		logger.info("DEBUG_ESPECIFICO -> " + Inicializacion.DEBUG_ESPECIFICO);
		logger.info("NIVEL_LOG APLICACION -> " + Level.toLevel(Inicializacion.NIVEL_LOG).toString());

		logger.info("ARRAY_TRASPASOS_MOTIVOS: " + Inicializacion.ARRAY_TRASPASOS_MOTIVOS.size());
		logger.info("ARRAY_AGENTES_TRASPASOS: " + Inicializacion.ARRAY_AGENTES_TRASPASOS.size());

		logger.info("ABIERTO_COMUNIDADES_DEFUNCION -> " + Inicializacion.ABIERTO_COMUNIDADES_DEFUNCION);

		logger.info("ACTIVAR_RESTRICCION_BAJA_DUPLICIDAD -> " + Inicializacion.ACTIVAR_RESTRICCION_BAJA_DUPLICIDAD);
		logger.info("ACTIVAR_RESTRICCION_BAJAS_HOSPITALARIAS -> " + Inicializacion.ACTIVAR_RESTRICCION_BAJAS_HOSPITALARIAS);
		logger.info("BLOQUEAR_BAJAS_DEFUNCION_OTRAS_CA -> " + Inicializacion.BLOQUEAR_BAJAS_DEFUNCION_OTRAS_CA);

		logger.info("ACTIVAR_AUDITORIA_LOPD -> " + Inicializacion.ACTIVAR_AUDITORIA_LOPD);

		logger.info("H_AGENTES_RDL -> " + Inicializacion.H_AGENTES_RDL);
		logger.info("TIME_TO_DELIVER_COLAERROR -> " + TIME_TO_DELIVER_COLAERROR);
		logger.info("TIME_TO_DELIVER_COLAGESTION -> " + TIME_TO_DELIVER_COLAGESTION);

		logger.info("TIEMPO_CUARENTENA_PASO_BENEFICIARIO_TITULAR -> " + TIEMPO_CUARENTENA_PASO_BENEFICIARIO_TITULAR);
		logger.info("TEXTO_BLOQUEAR_PASO_BENEFICIARIO_TITULAR -> " + TEXTO_BLOQUEAR_PASO_BENEFICIARIO_TITULAR);

		logger.info("ACTIVADO_NIE_SIETE -> " + ACTIVADO_NIE_SIETE);
		logger.info("ACTIVADO_SIN_RECURSOS_MAYORES_65 -> " + ACTIVADO_SIN_RECURSOS_MAYORES_65);
		
		logger.info("NOMBRE_TABLA_INSS -> " + NOMBRE_TABLA_INSS);
		logger.info("NOMBRE_TABLA_INSS_SNS -> " + NOMBRE_TABLA_INSS_SNS);
		logger.info("NOMBRE_TABLA_INSS_BAJAS -> " + NOMBRE_TABLA_INSS_BAJAS);

		logger.info("EMAIL_BAJAS_MJU -> " + EMAIL_BAJAS_MJU);
		logger.info("ASUNTO_EMAIL_BAJAS_MJU -> " + ASUNTO_EMAIL_BAJAS_MJU);
		
		logger.info("PATH_IFI -> " + PATH_IFI);
		logger.info("PATH_RELATIVO_RECIBIDOS_IFI -> " + PATH_RELATIVO_RECIBIDOS_IFI);
		logger.info("PATH_RELATIVO_ARCHIVADOS_IFI -> " + PATH_RELATIVO_ARCHIVADOS_IFI);

		logger.info("PREFIJO_FICHERO_IFI -> " + PREFIJO_FICHERO_IFI);
		
		logger.info("ENVIAR_ACTUALIZACION_IFI -> " + ENVIAR_ACTUALIZACION_IFI);
		
		logger.info("NOMBRE_FICHERO_SINCRONIZACION_INSS_SNS -> " + NOMBRE_FICHERO_SINCRONIZACION_INSS_SNS);
		
		logger.info("ACTIVAR_BAJAS_PROCEDENTES_INSS -> " + ACTIVAR_BAJAS_PROCEDENTES_INSS);

		logger.info("HOLGURA_FECHA_INICIO_TEMPORALIDAD -> " + HOLGURA_FECHA_INICIO_TEMPORALIDAD);
		
		logger.info("ACTIVAR_COLA_JMS_OSB -> " + ACTIVAR_COLA_JMS_OSB);

		logger.info("OSB_ACTIVAR_COPIAR_COLA_JMS -> " + OSB_ACTIVAR_COPIAR_COLA_JMS);
		logger.info("OSB_NOMBRE_COLA_JMS -> " + OSB_NOMBRE_COLA_JMS);
		
		logger.info("INSS_PERMITIR_VINCULAR_SIN_ESTADO -> " + INSS_PERMITIR_VINCULAR_SIN_ESTADO);
		
		logger.info("VERSION_TARJETA_SANITARIA: " + sns.config.Constantes.VERSION_TARJETA_SANITARIA);
		logger.info("FECHA_VERSION_TARJETA_SANITARIA: " + sns.config.Constantes.FECHA_VERSION_TARJETA_SANITARIA);

	}

	// public String startup(String name, java.util.Hashtable args) throws
	// Exception {
	static {
		sns.config.Inicializacion.init();

	}

}
