package sns.comun.config;

import gasai.util.Misc;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.apache.log4j.Logger;


public class InicializacionBusqueda {
	//
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	//
	public static final String LOGGER_NAME          = "busqueda";
	//
	private static String PATH_LOCAL                = "D:/Procesos/BusquedaAproximacion/";
	private static String FILE_CONFIG_LOCAL         = "busqueda.properties";
	//
	public static String PATH_LOCAL_DESCARGAS       = "D:/Procesos/Beneficiarios 7/";
	
	//"D:/Procesos/BusquedaAproximacion/2013-10-18 Valencia/Descargas/";
	//D:\Procesos\BusquedaAproximacion\2014-03-10  Pais Vasco\Descargas2
	public static String PLANTILLA_EXCEL_NOMBRE     = "plantilla.xls";
	public static String EXCEL_NOMBRE               = "prueba.xls";
	//
	//
	//
	// VARIABLES DE ENTRADA AL PROCESO PROPERTIES
	public static String COD_CA_ISO         = "";
	public static String DISTINGUIR_CA      = "";	//distinguirCA
	public static String PATH_FICHEROS      = "";
	public static String FICHEROS_ORIGEN    = "";
	public static String FICHEROS_DESTINO   = "";	
	// 
	public static String OBTENER_CAMPOS_VACIOS = ""; //campoVacio	
	
	//	//  // // // // // // //  //  // // // // // // //  //  // // // // // // //  
	//  // // // // // // //    VARIABLES DEL PROPERTIES    //  // // // // // // //  
	//  //  // // // // // // //  //  // // // // // // //  //  // // // // // // //  
	public static String CONSULTAR_SNS            = "";
	public static String CONSULTAR_INSS           = "";
	//
	public static String COMPROBAR_CODSNS         = "";
	public static String COMPROBAR_IDSSALUD       = "";
	public static String COMPROBAR_DNI            = "";
	public static String COMPROBAR_NAF            = "";
	public static String COMPROBAR_NAFTITULAR     = "";
	public static String COMPROBAR_NAFT_RAIZ      = "";
	//
	public static String COMPROBAR_NAFT_NOMBRE    = "";
	public static String COMPROBAR_RAIZ_NOMBRE    = "";
	public static String COMPROBAR_RAIZ_APELLIDOS = "";
	public static String COMPROBAR_OTROS          = "";
	public static String COMPROBAR_NAFT_PREF_RAIZ = "";
	//
	//
	public static String COMPROBAR_CODSNS_INSS    = "";
	public static String COMPROBAR_DNI_INSS       = "";
	public static String COMPROBAR_NAF_INSS       = "";
	public static String COMPROBAR_NAF_SEC_INSS   = "";
	//	
	// BUSQUEDA_APROXIMADA
	public static String BUSQUEDA_APROXIMADA_SNS                             = "";
	public static String BUSQUEDA_APROXIMADA_INSS                            = "";
	public static String BUSQUEDA_APROXIMADA_SEXO                            = "";
	public static String BUSQUEDA_APROXIMADA_PORC                            = "";
	//
	public static String BUSQUEDA_APROXIMADA_FECHA                           = "";
	public static int    BA_FECHA_NUMERO_MAX_DIAS                            = 0;
	public static int    BA_FECHA_NUMERO_MAX_MESES                           = 0;
	public static int    BA_FECHA_NUMERO_MAX_ANIOS                           = 0;
	//
	public static int    VALOR_CAMPO_IGUAL_CAMPO            	             = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO                        = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO_ESTUDIO_NOMBRE         = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO_SEXO                   = 0;
	public static int    VALOR_CAMPO_IGUAL_APROXIMADO_FECHA                  = 0;
	//
	public static String BUSQUEDA_APROXIMADA_NOMBRE                          = "";
	public static String REVISION_DEL_NOMBRE                                 = "";
	public static String APELLIDO2_REVISAR                                   = "";
	//
	public static int    CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE         = 0;
	public static int    CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS      = 0;
	public static String CAMPOS_OBLIGATORIOS                                 = "";
	//	//  // // // // // // //  //  // // // // // // //  //  // // // // // // //  
	//  // // // // // // //    VARIABLES DEL PROPERTIES    //  // // // // // // //  
	//  //  // // // // // // //  //  // // // // // // //  //  // // // // // // //  
 	//
	//
	//
	//
	public static LinkedHashMap <String, String> MAP_CAMPO = new LinkedHashMap <String, String> ();
	//
	public static LinkedHashMap <String, String> MAP_CAMPOS_BUSQUEDA_SNS  = new LinkedHashMap <String, String> ();
	public static LinkedHashMap <String, String> MAP_CAMPOS_BUSQUEDA_INSS = new LinkedHashMap <String, String> ();
	//
	public static LinkedHashMap <String, String> EXCEL_MAP_ESTADOS = new LinkedHashMap <String, String> ();
	//
	public static LinkedHashMap <String, String> MAP_CAMPOS_OBLIGATORIOS = new LinkedHashMap <String, String> ();
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
			cargarCampos();
			inicializarInformacion();
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
		Properties properties = new Properties();
		InputStream defaultStream = null;	
		FileInputStream fileInputStream = null;
		//
		try {
			logger.debug("INICIO");
			ClassLoader classLoader = sns.comun.config.InicializacionBusqueda.class.getClassLoader();
			defaultStream = classLoader.getResourceAsStream(sns.comun.config.InicializacionBusqueda.FILE_CONFIG_LOCAL);
			//
			if(defaultStream == null) {
				if(!Misc.esVacio(path)) {
					fileInputStream = new FileInputStream(path);
					properties.load(fileInputStream);
					fileInputStream.close();
				}
				else {
					fileInputStream = new FileInputStream(sns.comun.config.InicializacionBusqueda.PATH_LOCAL + sns.comun.config.InicializacionBusqueda.FILE_CONFIG_LOCAL);
					properties.load(fileInputStream);
					fileInputStream.close();
				}
			}
			else {
				logger.info("default 1:" + defaultStream);
				properties.load(defaultStream);
				defaultStream.close();
			}
			//
			sns.comun.config.InicializacionBusqueda.COD_CA_ISO                = Misc.nz(properties.getProperty("codCaIso"));
			sns.comun.config.InicializacionBusqueda.DISTINGUIR_CA             = Misc.nz(properties.getProperty("distinguirCA"));
			sns.comun.config.InicializacionBusqueda.PATH_FICHEROS             = Misc.nz(properties.getProperty("path"));
			sns.comun.config.InicializacionBusqueda.FICHEROS_ORIGEN           = Misc.nz(properties.getProperty("ficheroOrigen"));
			sns.comun.config.InicializacionBusqueda.FICHEROS_DESTINO          = Misc.nz(properties.getProperty("ficheroDestino"));
			//
			sns.comun.config.InicializacionBusqueda.OBTENER_CAMPOS_VACIOS     = Misc.nz(properties.getProperty("campoVacio"));
			//
			sns.comun.config.InicializacionBusqueda.CONSULTAR_SNS             = Misc.nz(properties.getProperty("consultarSns"));
			sns.comun.config.InicializacionBusqueda.CONSULTAR_INSS            = Misc.nz(properties.getProperty("consultarInss"));
			//
			sns.comun.config.InicializacionBusqueda.COMPROBAR_CODSNS          = Misc.nz(properties.getProperty("comprobarCodSns"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_IDSSALUD        = Misc.nz(properties.getProperty("comprobarIdssalud"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_DNI             = Misc.nz(properties.getProperty("comprobarDni"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF             = Misc.nz(properties.getProperty("comprobarNaf"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFTITULAR      = Misc.nz(properties.getProperty("comprobarNafTitular"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_RAIZ       = Misc.nz(properties.getProperty("comprobarNafTRaiz"));
			//
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_NOMBRE     = Misc.nz(properties.getProperty("comprobarNafTitNombre"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_RAIZ_NOMBRE     = Misc.nz(properties.getProperty("comprobarRaizNombre"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_RAIZ_APELLIDOS  = Misc.nz(properties.getProperty("comprobarRaizApellidos"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_OTROS           = Misc.nz(properties.getProperty("comprobarOtros"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_PREF_RAIZ  = Misc.nz(properties.getProperty("comprobarNaftPrefijoRaiz"));
			//
			sns.comun.config.InicializacionBusqueda.COMPROBAR_CODSNS_INSS     = Misc.nz(properties.getProperty("comprobarCodSnsInss"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_DNI_INSS        = Misc.nz(properties.getProperty("comprobarDniInss"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_INSS        = Misc.nz(properties.getProperty("comprobarNafInss"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_SEC_INSS    = Misc.nz(properties.getProperty("comprobarNafSecInss"));
			//
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_INSS        = Misc.nz(properties.getProperty("comprobarNafInss"));
			sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_SEC_INSS    = Misc.nz(properties.getProperty("comprobarNafSecInss"));
			//
			//
			sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_SNS   = Misc.nz(properties.getProperty("busquedaAproximadaSns"));
			sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_INSS  = Misc.nz(properties.getProperty("busquedaAproximadaInss"));
			//
			sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_SEXO  = Misc.nz(properties.getProperty("busquedaAproximadaConSexo"));
			//
			sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_FECHA = Misc.nz(properties.getProperty("busquedaAproximadaConFecha"));
			if(Misc.esDigito(properties.getProperty("numeroMaximoDias"))){
				sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_DIAS  = Integer.parseInt(Misc.nz(properties.getProperty("numeroMaximoDias")));
			}
			if(Misc.esDigito(properties.getProperty("numeroMaximoMeses"))){
				sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_MESES  = Integer.parseInt(Misc.nz(properties.getProperty("numeroMaximoMeses")));
			}
			if(Misc.esDigito(properties.getProperty("numeroMaximoAnios"))){
				sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_ANIOS  = Integer.parseInt(Misc.nz(properties.getProperty("numeroMaximoAnios")));
			}
			//
			if(Misc.esDigito(properties.getProperty("igualCampo"))){
				sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO                         = Integer.parseInt(Misc.nz(properties.getProperty("igualCampo")));
			}
			if(Misc.esDigito(properties.getProperty("igualAproximado"))){
				sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO                    = Integer.parseInt(Misc.nz(properties.getProperty("igualAproximado")));
			}			
			
			if(Misc.esDigito(properties.getProperty("igualEstudioNombre"))){
				sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_ESTUDIO_NOMBRE     = Integer.parseInt(Misc.nz(properties.getProperty("igualEstudioNombre")));
			}	
			
			if(Misc.esDigito(properties.getProperty("igualAproximadoFecha"))){
				sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_SEXO               = Integer.parseInt(Misc.nz(properties.getProperty("igualAproximadoFecha")));
			}
			if(Misc.esDigito(properties.getProperty("igualAproximadoSexo"))){
				sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_FECHA              = Integer.parseInt(Misc.nz(properties.getProperty("igualAproximadoSexo")));
			}
			//
			if(Misc.esDigito(properties.getProperty("criterioIdentificacionIgualdadPorcentaje"))){
				sns.comun.config.InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE     = Integer.parseInt(Misc.nz(properties.getProperty("criterioIdentificacionIgualdadPorcentaje")));
			}
			if(Misc.esDigito(properties.getProperty("criterioIdentificacionIgualdadNumeroCamposIgual"))){
				sns.comun.config.InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS  = Integer.parseInt(Misc.nz(properties.getProperty("criterioIdentificacionIgualdadNumeroCamposIgual")));
			}
			//			
			sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_NOMBRE                          = Misc.nz(properties.getProperty("busquedaAproximadaConNombre"));
			sns.comun.config.InicializacionBusqueda.REVISION_DEL_NOMBRE                                 = Misc.nz(properties.getProperty("revisionNombre"));
			sns.comun.config.InicializacionBusqueda.APELLIDO2_REVISAR                                   = Misc.nz(properties.getProperty("apellido2VaciosRevisar"));
			//			
			sns.comun.config.InicializacionBusqueda.CAMPOS_OBLIGATORIOS                                 = Misc.nz(properties.getProperty("camposObligatoriosCoincidentes"));
			//
			//
			
			logger.debug("PATH_FICHEROS:                                  " + sns.comun.config.InicializacionBusqueda.PATH_FICHEROS);
			logger.debug("FICHEROS_ORIGEN:                                " + sns.comun.config.InicializacionBusqueda.FICHEROS_ORIGEN);
			logger.debug("FICHEROS_DESTINO:                               " + sns.comun.config.InicializacionBusqueda.FICHEROS_DESTINO);
			logger.debug("DISTINGUIR_CA:                                  " + sns.comun.config.InicializacionBusqueda.DISTINGUIR_CA);
			logger.debug("COD_CA_ISO:                                     " + sns.comun.config.InicializacionBusqueda.COD_CA_ISO);
			//
			logger.debug("OBTENER_CAMPOS_VACIOS:                          " + sns.comun.config.InicializacionBusqueda.OBTENER_CAMPOS_VACIOS);
			//
			logger.debug("CONSULTAR_CODSNS:                               " + sns.comun.config.InicializacionBusqueda.CONSULTAR_SNS);
			logger.debug("CONSULTAR_INSS:                                 " + sns.comun.config.InicializacionBusqueda.CONSULTAR_INSS);
			//
			logger.debug("COMPROBAR_CODSNS:                               " + sns.comun.config.InicializacionBusqueda.COMPROBAR_CODSNS);
			logger.debug("COMPROBAR_IDSSALUD:                             " + sns.comun.config.InicializacionBusqueda.COMPROBAR_IDSSALUD);
			logger.debug("COMPROBAR_DNI:                                  " + sns.comun.config.InicializacionBusqueda.COMPROBAR_DNI);
			logger.debug("COMPROBAR_NAF:                                  " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF);
			logger.debug("COMPROBAR_NAFTITULAR:                           " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFTITULAR);
			logger.debug("COMPROBAR_NAFT_RAIZ:                            " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_RAIZ);
			//
			logger.debug("COMPROBAR_NAFT_NOMBRE:                          " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_NOMBRE);
			logger.debug("COMPROBAR_RAIZ_NOMBRE:                          " + sns.comun.config.InicializacionBusqueda.COMPROBAR_RAIZ_NOMBRE);
			logger.debug("COMPROBAR_RAIZ_APELLIDOS:                       " + sns.comun.config.InicializacionBusqueda.COMPROBAR_RAIZ_APELLIDOS);
			logger.debug("COMPROBAR_OTROS:                                " + sns.comun.config.InicializacionBusqueda.COMPROBAR_OTROS);
			logger.debug("COMPROBAR_NAFT_PREF_RAIZ:                       " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAFT_PREF_RAIZ);
			//
			logger.debug("COMPROBAR_CODSNS_INSS:                          " + sns.comun.config.InicializacionBusqueda.COMPROBAR_CODSNS_INSS);
			logger.debug("COMPROBAR_DNI_INSS:                             " + sns.comun.config.InicializacionBusqueda.COMPROBAR_DNI_INSS);
			logger.debug("COMPROBAR_NAF_INSS:                             " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_INSS);
			logger.debug("COMPROBAR_NAF_SEC_INSS:                         " + sns.comun.config.InicializacionBusqueda.COMPROBAR_NAF_SEC_INSS);
			//
			logger.debug("BUSQUEDA_APROXIMADA_SNS:                        " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_SNS);
			logger.debug("BUSQUEDA_APROXIMADA_INSS:                       " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_INSS);
			logger.debug("BUSQUEDA_APROXIMADA_SEXO :                      " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_SEXO);
			logger.debug("BUSQUEDA_APROXIMADA_PORC:                       " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_PORC);
			//
			logger.debug("BUSQUEDA_APROXIMADA_FECHA:                      " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_FECHA);
			logger.debug("BA_FECHA_NUMERO_MAX_DIAS:                       " + sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_DIAS);
			logger.debug("BA_FECHA_NUMERO_MAX_MESES:                      " + sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_MESES);
			logger.debug("BA_FECHA_NUMERO_MAX_ANIOS:                      " + sns.comun.config.InicializacionBusqueda.BA_FECHA_NUMERO_MAX_ANIOS);
			//
			logger.debug("VALOR_CAMPO_IGUAL_CAMPO:                        " + sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO);
			logger.debug("VALOR_CAMPO_IGUAL_APROXIMADO:                   " + sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO);
			logger.debug("VALOR_CAMPO_IGUAL_APROXIMADO_ESTUDIO_NOMBRE:    " + sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_ESTUDIO_NOMBRE);			
			logger.debug("VALOR_CAMPO_IGUAL_APROXIMADO_SEXO:              " + sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_SEXO);
			logger.debug("VALOR_CAMPO_IGUAL_APROXIMADO_FECHA:             " + sns.comun.config.InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_FECHA);
			//
			logger.debug("CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE:    " + sns.comun.config.InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE);
			logger.debug("CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS: " + sns.comun.config.InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS);
			//
			logger.debug("VALOR_CAMPO_IGUAL_APROXIMADO_NOMBRE             " + sns.comun.config.InicializacionBusqueda.BUSQUEDA_APROXIMADA_NOMBRE);
			logger.debug("REVISION_DEL_NOMBRE:                            " + sns.comun.config.InicializacionBusqueda.REVISION_DEL_NOMBRE);
			logger.debug("APELLIDO2_REVISAR:                              " + sns.comun.config.InicializacionBusqueda.APELLIDO2_REVISAR);
			//
			logger.debug("CAMPOS_OBLIGATORIOS:                            " + sns.comun.config.InicializacionBusqueda.CAMPOS_OBLIGATORIOS);
			//
			
			//
			// VALIDACION
			//
			//File fileDir = new File(sns.comun.config.InicializacionCruce.PATH_DIRECTORIO_DESTINO);
			//if (!fileDir.isDirectory()) {
			//	throw new Exception ("Argumento 'pathDirectorioDestino' incorrecto.");
			//}
			//if(!Misc.esDigito(sns.comun.config.InicializacionCruce.COD_CA_ISO)) {
			//	throw new Exception ("Argumento 'codCaIso' incorrecto.");
			//}	
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
	
	
	public static void inicializarInformacion () {
		//
		try {
			logger.debug("INICIO");
			//
			LinkedHashMap <String, String> mapCamposBusqueda     = new LinkedHashMap <String, String> ();
			LinkedHashMap <String, String> mapCamposBusquedaInss = new LinkedHashMap <String, String> ();
			//
			// Se rellenan los campos de Busqueda en el SNS
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_CODSNS)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_IDSSALUD)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_DNI)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_DNI, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAF)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAFTITULAR)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAFT_RAIZ)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ, null);
			}
			//
			//
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAFT_NOMBRE)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_RAIZ_NOMBRE)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_RAIZ_APELLIDOS)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_OTROS)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_OTROS, null);
			}			
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAFT_PREF_RAIZ)) {
				mapCamposBusqueda.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ, null);
			}
			//
			MAP_CAMPOS_BUSQUEDA_SNS  = mapCamposBusqueda;
			//
			//
			//
			//
			// Se rellenan los campos de Busqueda en el INSS
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_CODSNS_INSS)) {
				mapCamposBusquedaInss.put(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_DNI_INSS)) {
				mapCamposBusquedaInss.put(ConstantesBusqueda.VALOR_BUSQUEDA_DNI, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAF_INSS)) {
				mapCamposBusquedaInss.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF, null);
			}
			if(!Misc.esVacio(InicializacionBusqueda.COMPROBAR_NAF_SEC_INSS)) {
				mapCamposBusquedaInss.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC, null);
			}
			MAP_CAMPOS_BUSQUEDA_INSS  = mapCamposBusquedaInss;
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	
	

	private static void cargarCampos () throws Exception {
		try {
			LinkedHashMap <String, String> mapCampos = new LinkedHashMap <String, String> ();
			//
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS,          ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD,        ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_DNI,             ConstantesBusqueda.VALOR_BUSQUEDA_DNI);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF,             ConstantesBusqueda.VALOR_BUSQUEDA_NAF);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR,      ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ,       ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC,         ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC);
			//
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE,  ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE,     ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS,  ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_OTROS,           ConstantesBusqueda.VALOR_BUSQUEDA_OTROS);
			mapCampos.put(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ,ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ);
			//
			MAP_CAMPO = mapCampos;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarCamposObligatorios () throws Exception {
		try {
			LinkedHashMap <String, String> map = new LinkedHashMap <String, String> ();
			//	
			if(!Misc.esVacio(InicializacionBusqueda.CAMPOS_OBLIGATORIOS)) {
				String [] subCadena = InicializacionBusqueda.CAMPOS_OBLIGATORIOS.split("\\|");
				//
				for (int i=0 ; i<subCadena.length ; i++) {
					map.put(Misc.nz(subCadena[i]), Misc.nz(subCadena[i]));
				}	
			}
			//
			InicializacionBusqueda.MAP_CAMPOS_OBLIGATORIOS = map;
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
	}	
	
	
	private static void cargarEstados () throws Exception {
		try {
			LinkedHashMap <String, String> mapEstadosAux = new LinkedHashMap <String, String>();
			mapEstadosAux.put("0", "0 - NORMAL");
			mapEstadosAux.put("1", "1 - BAJA EN SERVICIO DE SALUD");
			mapEstadosAux.put("2", "2 - BAJA POR DEFUNCION");
			mapEstadosAux.put("3", "3 - BAJA POR ERROR");
			mapEstadosAux.put("4", "4 - BAJA POR DUPLICIDAD EN FUSION");
			mapEstadosAux.put("5", "5 - BAJA POR DUPLICIDAD");
			//
			EXCEL_MAP_ESTADOS = mapEstadosAux;
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
			mapCadenasEspeciales.put(" ",   ""); // NO-BREAK SPACE -- &nbsp;
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
			mapRepetidos.put("HH", "I");
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
			mapCaracteresExtra.put("^T", "");
			mapCaracteresExtra.put("^t", "");
			mapCaracteresExtra.put("", "");	
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
			//
			// Prueba a cambio de Y por I
			mapCaracteresConf.put("Y", "I");
			mapCaracteresConf.put("H", "");
			//
			mapCaracteresConf.put("FCO ", "");        
			//                   
			mapCaracteresConf.put("TX", "");          
			mapCaracteresConf.put("TS", "");          
			//          
			mapCaracteresConf.put("ç",  "C");
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
	
	
	static {
		sns.comun.config.InicializacionBusqueda.init();
	}	
	
	
}
