package sns.config;

import java.math.BigDecimal;

public interface Constantes {

	public static final String VERSION_TARJETA_SANITARIA = "6.0.27";
	public static final String FECHA_VERSION_TARJETA_SANITARIA = "17-04-2013";

	public final static int MODO_ACCESO_JMS = 0;
	public final static int MODO_ACCESO_SOAP = 1;

	//USUARIO INTERNO O NO
	public static final String USUARIO_INTERNO="S";
	public static final String USUARIO_REAL="N";
	
	// Situaciones de los usuarios del SNS
	public static final int ACTIVO = 1;
	public static final int PENSIONISTA = 2;
	public static final int ACTIVO_PENSIONISTA = 3;
	public static final int PRORROGA = 4;
	public static final int PRORROGA_DEFINITIVA = 5;
	public static final int MAYOR_65 = 6;
	public static final int MENOR_65 = 7;
	public static final int INDETERMINADA = 8;

	public static final int AGENTEJUSTICIA = 98;
	
	public static final String RESPUESTA_CONSULTA_EXCEDIDO="EXCEDIDO";
	public static final String RESPUESTA_CONSULTA_OK="OK";

	// JNDI NAME
	public static final String JNDI_DATASOURCE_NONXA = "java:comp/env/jdbc/snsDataSource";
	public static final String JNDI_DATASOURCE_XA = "java:comp/env/jdbc/snsDataSourceTx";

	public static final String COLA_JMS_TOPIC_INICIALIZACION = "java:comp/env/jms/snsInicializacionJMSTopic";
	public static final String COLA_JMS_AUDITORIA = "java:comp/env/jms/snsAuditQueue";
	public static final String COLA_JMS_ENTRADA = "java:comp/env/jms/snsRecibir";
	public static final String COLA_JMS_ENTRADA_ERROR = "java:comp/env/jms/snsRecibirError";
	public static final String COLA_JMS_SALIDA = "java:comp/env/jms/snsEnviar";
	public static final String COLA_JMS_SALIDA_ERROR = "java:comp/env/jms/snsEnviarError";
	public static final String COLA_JMS_GESTION = "java:comp/env/jms/snsGestion";

	public static final String COLA_JMS_SALIDA_OSB = "java:comp/env/jms/TarjetaAsincronoOut";

	public static final String JMS_PROPIEDAD_COD_XML_ENTRADA="codXmlEntrada";
	public static final String JMS_PROPIEDAD_COD_OPERACION_MAESTRA="codOperacionMaestra";
	public static final String JMS_PROPIEDAD_ID="id";
	public static final String JMS_PROPIEDAD_ID_SERVICIO="idServicio";
	public static final String JMS_PROPIEDAD_FIRMA="firma";
	public static final String JMS_PROPIEDAD_XML="xml";
	public static final String JMS_PROPIEDAD_ERROR="error";
	public static final String JMS_PROPIEDAD_REINTENTOS="reintentos";
	public static final String JMS_PROPIEDAD_COD_AGENTE="codAgente";
	public static final String JMS_PROPIEDAD_COD_TIPO_OPERACION="codTipoOperacion";
	public static final String JMS_PROPIEDAD_TM_FECHA_OPERACION="tmFechaOperacion";

	public static final String GESTION_ID_TRATAMIENTO_APORTACION_BENEFICIARIOS="G001";
	public static final String GESTION_ID_TRATAMIENTO_LINEA_INSS="G002";
	public static final String GESTION_ID_TRATAMIENTO_FICHERO_INSS="G003";
	public static final String GESTION_ID_TRATAMIENTO_MOVIMIENTOS_INSS="G004";
	public static final String GESTION_ID_ACTUALIZACION_SNS_INSS="G005";
	public static final String GESTION_ID_ACTUALIZACION_REGISTRO_SNS_INSS="G006";
	public static final String GESTION_ID_TRATAMIENTO_NOTIFICAR_TITULO="G007";
	
	public static final String JNDI_EJB_SNS_AUDITORIAPOOLJMSLOCALHOME = "sns.jms.AuditoriaPoolJmsLocalHome";
	public static final String JNDI_EJB_SNS_LISTANEGRAFACADELOCALHOME = "sns.ListaNegraFacadeLocalHome";
	public static final String JNDI_EJB_SNS_INFUSUARIOPRESTACIONESLOCALHOME = "sns.InfUsuarioPrestacionesLocalHome";
	public static final String JNDI_EJB_SNS_ALTAONLINELOCALHOME = "sns.AltaOnlineLocalHome";
	public static final String JNDI_EJB_SNS_VALIDACIONESLOCALHOME = "sns.ValidacionesLocalHome";
	public static final String JNDI_EJB_SNS_GESTIONCENTRALCIPLOCALHOME = "sns.GestionCentralCipLocalHome";
	public static final String JNDI_EJB_SNS_GENERACIPLOCALHOME = "sns.GeneraCipLocalHome";
	public static final String JNDI_EJB_SNS_MODINFUSUARIOLOCALHOME = "sns.ModInfUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_INFUSUARIOLOCALHOME = "sns.InfUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_INFHISTUSUARIOLOCALHOME = "sns.InfHistUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_INFUSUARIOSEGSOCIALLOCALHOME = "sns.InfUsuarioSegSocialLocalHome";
	public static final String JNDI_EJB_SNS_INFUSUARIOINSSLOCALHOME = "sns.InfUsuarioInssLocalHome";
	public static final String JNDI_EJB_SNS_CODIFICACIONLOCALHOME = "sns.CodificacionLocalHome";
	public static final String JNDI_EJB_SNS_OPASOCIADASLOCALHOME = "sns.OpAsociadasLocalHome";
	public static final String JNDI_EJB_SNS_PRESTACIONUSUARIOLOCALHOME = "sns.PrestacionUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_ALTAUSUARIOLOCALHOME = "sns.AltaUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_BAJAUSUARIOLOCALHOME = "sns.BajaUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_RELLENARLOCALIZADORLOCALHOME = "sns.RellenarLocalizadorLocalHome";
	public static final String JNDI_EJB_SNS_BUSCAUSUARIOLOCALHOME = "sns.BuscaUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_NOTIFICARLOCALHOME = "sns.NotificarLocalHome";
	public static final String JNDI_EJB_SNS_TEMPORALIDADUSUARIOLOCALHOME = "sns.TemporalidadUsuarioLocalHome";
	public static final String JNDI_EJB_SNS_SINCRONIZACION_INSS_LOCALHOME = "sns.SincronizacionInssLocalHome";
	public static final String JNDI_EJB_SNS_CONTROLADORSINCRONIZACION_INSS_LOCALHOME = "sns.ControladorSincronizacionInssLocalHome";
	public static final String JNDI_EJB_SNS_JMS_COLALOCALHOME = "sns.jms.ColaLocalHome";
	public static final String JNDI_EJB_SNS_XML_ALTAONLINEXMLLOCALHOME = "sns.xml.AltaOnlineXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_MODINFUSUARIOXMLLOCALHOME = "sns.xml.ModInfUsuarioXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_LOCALIZADORUSUARIOXMLLOCALHOME = "sns.xml.LocalizadorUsuarioXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_LOCALIZADORUSUARIOSEGSOCIALXMLLOCALHOME = "sns.xml.LocalizadorUsuarioSegSocialXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_LOCALIZADORUSUARIOINSSXMLLOCALHOME = "sns.xml.LocalizadorUsuarioInssXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_LOCALIZADOROPERACIONXMLLOCALHOME = "sns.xml.LocalizadorOperacionXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_IDENTIFICADORCODIFICACIONXMLLOCALHOME = "sns.xml.IdentificadorCodificacionXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_BAJAUSUARIOXMLLOCALHOME = "sns.xml.BajaUsuarioXmlLocalHome";
	public static final String JNDI_EJB_SNS_XML_TEMPORALIDADUSUARIOXMLLOCALHOME = "sns.xml.TemporalidadUsuarioXmlLocalHome";
	public static final String JNDI_EJB_SNS_ENTITY_HISTORICOCIPSHOME = "sns.entity.HistoricoCipsHome";
	public static final String JNDI_EJB_SNS_ENTITY_NAFSECUNDARIOSHOME = "sns.entity.NafSecundariosHome";
	public static final String JNDI_EJB_SNS_ENTITY_NAFCONTROLHOME = "sns.entity.NafControlHome";
	public static final String JNDI_EJB_SNS_ENTITY_RELACIONCIPHOME = "sns.entity.RelacionCipHome";
	public static final String JNDI_EJB_SNS_ENTITY_SECUENCIAHOME = "sns.entity.SecuenciaHome";
	public static final String JNDI_EJB_SNS_ENTITY_DATOSCOBERTURAHOME = "sns.entity.DatosCoberturaHome";
	public static final String JNDI_EJB_SNS_ENTITY_DATOSFARMACIAHOME = "sns.entity.DatosFarmaciaHome";
	public static final String JNDI_EJB_SNS_ENTITY_DATOSDOMICILIOHOME = "sns.entity.DatosDomicilioHome";
	public static final String JNDI_EJB_SNS_ENTITY_DATOSPERSONALESHOME = "sns.entity.DatosPersonalesHome";
	public static final String JNDI_EJB_SNS_ENTITY_IDENTIFICADORESUSUARIOHOME = "sns.entity.IdentificadoresUsuarioHome";
	public static final String JNDI_EJB_SNS_ENTITY_MUNICIPIOSHOME = "sns.entity.MunicipiosHome";
	public static final String JNDI_EJB_SNS_ENTITY_REGISTROOPERACIONESHOME = "sns.entity.RegistroOperacionesHome";
	public static final String JNDI_EJB_SNS_ENTITY_REGISTROOPERACIONESXMLHOME = "sns.entity.RegistroOperacionesXmlHome";
	public static final String JNDI_EJB_SNS_ENTITY_USUARIOSHOME = "sns.entity.UsuariosHome";
	public static final String JNDI_EJB_SNS_ENTITY_HISTORICOMODUSUARIOS1HOME = "sns.entity.HistoricoModUsuarios1Home";
	public static final String JNDI_EJB_SNS_ENTITY_REGISTROBAJASHOSPITALARIASHOME = "sns.entity.RegistroBajasHospitalariasHome";
	public static final String JNDI_EJB_SNS_ENTITY_LISTANEGRAHOME = "sns.entity.ListaNegraHome";
	public static final String JNDI_EJB_SNS_ENTITY_COLAENTRADAHOME = "sns.entity.ColaEntradaHome";
	public static final String JNDI_EJB_SNS_APORTACIONFARMACIALOCALHOME="sns.AportacionFarmaciaLocalHome";

	public static final String JNDI_EJB_SNS_ENTITY_TEMPORALIDADUSUARIOHOME = "sns.entity.TemporalidadUsuarioHome";

	public static final String JNDI_EJB_SNS_MJUSTICIA_BAJAS_MJUSERVER_HOME = "sns.mjusticia.BajasMjuServerLocalHome";
	
	// TIPO_PROCEDENCIA_FARMACIA
	public static final BigDecimal TIPO_PROCEDENCIA_FARMACIA_INSS_BENEFICIARIO_SIN_TITULAR_TSI=new BigDecimal("5");
	public static final BigDecimal TIPO_PROCEDENCIA_FARMACIA_INSS_BENEFICIARIO=new BigDecimal("4");
	public static final BigDecimal TIPO_PROCEDENCIA_FARMACIA_MINISTERIO_SANIDAD=new BigDecimal("3");
	public static final BigDecimal TIPO_PROCEDENCIA_FARMACIA_SERVICIO_SALUD=new BigDecimal("2");
	public static final BigDecimal TIPO_PROCEDENCIA_FARMACIA_INSS=new BigDecimal("1");
	
	// PUNTOS DE EMISION DE EMAILS
	public static final int PUNTO_EMAIL_TRASPASOS_BLOQUEADOS = 1;
	public static final int PUNTO_EMAIL_SEMAFORO_TRANSACCIONES = 2;
	public static final int PUNTO_EMAIL_NOTIFICACION_AL_INTERCAMBIADOR = 3;
	public static final int PUNTO_EMAIL_CONSULTA_HISTORICO = 12;
	public static final int PUNTO_EMAIL_LISTENER_ENTRADA_NUMERO_INTENTOS_EXCEDIDO = 13;
	public static final int PUNTO_EMAIL_LISTENER_ENTRADA_PROBLEMAS_PROCESAR = 14;
	public static final int PUNTO_EMISION_ENTRADA_CONSULTA_HEALTH_CHECK = 18;

	public final static String IPSERVIDOR = "";
	public final static String PUERTO = "";
	public final static String SID = "";
	public final static String USUARIO = "";
	public final static String PASSWORD = "";
	public final static String WLSHOME = "";

	// CODIGOS DE ERROR
	public final static String RESPUESTA_OK = "OK";
	public final static String RESPUESTA_OK1 = "OK1";
	public final static String RESPUESTA_OK2 = "OK2";
	
	public final static String ERROR_EG001 = "EG001";
	public final static String ERROR_EG002 = "EG002";
	public final static String ERROR_EG002_TEXTO_USUARIO_NO_ENCONTRADO="El usuario no existe en nuestro sistema";
	public final static String ERROR_EG003 = "EG003";
	public final static String ERROR_EG004 = "EG004";
	public final static String ERROR_EG005 = "EG005";
	public final static String ERROR_EG006 = "EG006";
	public final static String ERROR_EG007 = "EG007";
	public final static String ERROR_EG008 = "EG008";
	public final static String ERROR_EG009 = "EG009";
	public final static String ERROR_EG010 = "EG010";
	public final static String ERROR_EG011 = "EG011";
	public final static String ERROR_EG012 = "EG012";
	public final static String ERROR_EG013 = "EG013"; // error para indicar que
														// se ha bloqueado la
														// operacion por
														// conflicto concurrente
														// con otra
	public final static String ERROR_EG014 = "EG014"; // error para indicar que
														// se ha bloqueado la
														// operacion por no
														// permitirse cambios
														// asincronos que
														// afecten al CIP
	public final static String ERROR_EG015 = "EG015"; // error para indicar que
														// no se puede dar una
														// baja por duplicidad
														// sin codSns
	public final static String ERROR_EG015_TEXTO="Codigo SNS no facilitado para realizar la operacion";
	
	public final static String ERROR_EG016 = "EG016"; // error para indicar que
														// no se puede dar una
														// baja hospitalaria sin
														// codSns o (cip y cite)

	public final static String ERROR_EG017bis = "EG017bis"; //error para marcar el titular como en cuarentena
	
	public final static String ERROR_EG017 = "EG017"; // error para indicar que
	// no se puede pasar de titular a beneficiario a un tio con beneficiarios

	public final static String ERROR_EG018 = "EG018";
	public final static String ERROR_EG018_TEXTO = "OPERACION NO PERMITIDA POR CAMBIOS EN LOS CAMPOS DEL INSS";
	
	public final static String ERROR_EG019 = "EG019";
	public final static String ERROR_EG019_TEXTO = "OPERACION NO PERMITIDA POR INCONSISTENCIA DE DATOS IDENTIFICATIVOS CON EL INSS";

	public final static String ERROR_EG020 = "EG020";
	public final static String ERROR_EG020_TEXTO = "OPERACION NO PERMITIDA. TITULAR NO ENCONTRADO";

	public final static String ERROR_EG030 = "EG030";
	public final static String ERROR_EG030_TEXTO = "TEMPORALIDAD NO ENCONTRADA";

	public final static String ERROR_EG031 = "EG031";
	public final static String ERROR_EG031_TEXTO = "TEMPORALIDAD NO PERMITIDA, USUARIO CON ESTADO INCOMPATIBLE";

	public final static String ERROR_EG032 = "EG032";
	public final static String ERROR_EG032_TEXTO = "TEMPORALIDAD NO PERMITIDA, USUARIO NO PERTENECE AL AGENTE INDICADO";

	public final static String ERROR_EG033 = "EG033";
	public final static String ERROR_EG033_TEXTO = "TEMPORALIDAD NO PERMITIDA, USUARIO YA PERTENECE AL AGENTE";

	// CAMPOS QUE NO PUEDEN MODIFICARSE DE MANERA ASINCRONA POR AGENTES QUE
	// UTILIZAN LA GENERACION DE CIP CENTRAL A006
	public static final String CIP_APELLIDO1 = "apellido1";
	public static final String CIP_APELLIDO2 = "apellido2";
	public static final String CIP_PAIS_NACIMIENTO = "pais_nacimiento";
	public static final String CIP_CA_NAC = "CA_nac";
	public static final String CIP_FECHA_NAC = "fecha_nac";
	public static final String CIP_SEXO = "sexo";

	public static final String PREFIJO_XML_SERVICIO = "_xml_servicio";
	public static final String EXTENSION_XML_SERVICIO = ".xml";
	public static final String KEY_CONFIG_UMBRAL_CACHEO = "sns.config.UmbralCacheo";
	public static final boolean CACHEO_BORRAR_FICHERO = true;
	public static final boolean CACHEO_NO_BORRAR_FICHERO = false;

	// Literales estados de certificado
	public final static String ESTADONOREVOCADO = "Certificado correcto.";
	public final static String ESTADOREVOCADO = "Certificado revocado.";
	public final static String CERTIFICADONOCORRECTO = "Certificado no correcto.";
	public final static String ERRORCONEXIONES = "Error de Conexiones. No se ha podido establecer el estado.";
	public final static String ESTADOCADUCADO = "Certificado caducado";
	public final static String CANORECONOCIDA = "Ca no reconocida";
	public final static String APLICACIONNORECONOCIDA = "Aplicacion no reconocida";
	public final static String ESTADONODETERMINADO = "Estado no determinado";
	public final static String ERRORINTERNO = "Error enterno";
	public final static String NOEXISTECERTIFICADO = "Para acceder a la aplicación es necesario poseer un Cerificado Digital de usuario.";

	// Constantes estados de certificado
	public final static int CERTIFICADO_ESTADO_OK = 0;
	public final static int CERTIFICADO_ESTADO_REVOCADO = 1;
	public final static int CERTIFICADO_NO_CORRECTO = 2;
	public final static int CERTIFICADO_ERROR_CONEXIONES = 3;
	public final static int CERTIFICADO_ESTADO_CADUCADO = 4;
	public final static int CERTIFICADO_CA_NO_RECONOCIDA = 5;
	public final static int CERTIFICADO_APLICACION_NO_RECONOCIDA = 6;
	public final static int CERTIFICADO_NO_SE_HA_PODIDO_DETERMINAR_EL_ESTADO = 7;
	public final static int CERTIFICADO_ERROR_INTERNO = 99;

	public final static String SNSADMIN_COD_USUARIO = "0";
	// VALOR CAMPO ACTIVAR DE LISTA NEGRA
	public static final String LISTA_NEGRA_ACTIVADO = "1";
	public static final String LISTA_NEGRA_NO_ACTIVADO = "0";

	// VALOR CAMPO ACTIVAR DE LISTA NEGRA
	public static final String LISTA_NEGRA_TIPO_ANOTACION_MANUAL = "0";
	public static final String LISTA_NEGRA_TIPO_ANOTACION_AUTOMATICA = "1";

	// BUSQUEDA DE TITULARES
	public static final String VALUE_NIVEL_NO_ENCONTRADO = "0";
	public static final String VALUE_PRIMER_NIVEL = "1";
	public static final String VALUE_SEGUNDO_NIVEL = "2";

	public static final String KEY_NIVEL_ENCONTRADO = "nivelTitular";

	public static final String KEY_COD_USUARIO_TITULAR = "codSnsTitular";
	public static final String KEY_NAF_TITULAR = "naf_titular";
	public static final String KEY_COD_TITULO_TITULAR = "codTituloTitular";
	public static final String KEY_COD_ESTADO_TITULAR = "codEstadoTitular";
	public static final String KEY_INTERNO = "interno";

	// TIPOS_MODIFICACIONES_HISTORICO
	public final static BigDecimal TIPO_CAMBIO_MODIFICACION = new BigDecimal(0);
	public final static BigDecimal TIPO_CAMBIO_BAJA = new BigDecimal(1);
	public final static BigDecimal TIPO_CAMBIO_TRASPASO = new BigDecimal(2);
	public final static BigDecimal TIPO_CAMBIO_INICIO_TEMPORALIDAD = new BigDecimal(3);
	public final static BigDecimal TIPO_CAMBIO_ALTA = new BigDecimal(4);
	public final static BigDecimal TIPO_CAMBIO_FUSION_DE_DUPLICADO_CON_BAJA = new BigDecimal(5);
	public final static BigDecimal TIPO_CAMBIO_FUSION_POR_DUPLICADO = new BigDecimal(6);
	public final static BigDecimal TIPO_CAMBIO_FUSION_POR_DUPLICADO_CON_CAMBIO_DE_TITULAR = new BigDecimal(7);
	public final static BigDecimal TIPO_CAMBIO_MODIFICACION_DE_TITULAR = new BigDecimal(8);
	public final static BigDecimal TIPO_CAMBIO_FALLECIDO_ESTADO = new BigDecimal(9);

	public final static BigDecimal TIPO_CAMBIO_LIBERACION_NAF = new BigDecimal(11);
	public final static BigDecimal TIPO_CAMBIO_FIN_TEMPORALIDAD = new BigDecimal(12);

	// ESTADOS USUARIOS
	public static final int ESTADO_NORMAL = 0;
	public static final int ESTADO_BAJA_SERVICIO_SALUD = 1;
	public static final int ESTADO_BAJA_POR_DEFUNCION = 2;
	public static final int ESTADO_BAJA_POR_ERROR = 3;
	public static final int ESTADO_BAJA_POR_DUPLICIDAD_EN_FUSION = 4;
	public static final int ESTADO_BAJA_POR_DUPLICIDAD = 5;
	public static final int ESTADO_BAJA_POR_DUPLICIDAD_NOTIFICADA = 6;
	public static final int ESTADO_BAJA_POR_FALTA_DE_ASEGURAMIENTO = 7;

	public static final String BAJA_EN_SERVICIO_DE_SALUD = "EN SERVICIO DE SALUD";
	public static final String BAJA_POR_DEFUNCION = "POR DEFUNCION";
	public static final String BAJA_POR_ERROR = "POR ERROR";
	public static final String BAJA_POR_DUPLICIDAD = "POR DUPLICIDAD";
	public static final String BAJA_POR_FALTA_DE_ASEGURAMIENTO = "BAJA. NECESITA ACREDITACION DERECHO EN EL INSS";

	public static final int TIPO_TITULO_ANTIGUO=1;
	public static final int TIPO_TITULO_NUEVO=2;
	public static final int TIPO_TITULO_NA_SNS=3;
	
	public static final int TITULO_ANTIGUO_VALOR_MAXIMO=59;

	public static final int TITULO_NUEVO_VALOR_MINIMO=60;
	public static final int TITULO_NUEVO_VALOR_MAXIMO=75;

	public static final int TITULO_PROPIETARIO_SNS_VALOR_MINIMO=79;
	public static final int TITULO_PROPIETARIO_SNS_VALOR_MAXIMO=85;
	
	// // // // // // // // ADMINISTRACION // // // // // // // // // //
	// Nombre de la plantilla Excel
	public static final String ADMIN_EXCEL_PLANTILLA = "Excel.xls";
	// Sufijo para el nombre del Excel
	public static final String ADMIN_REPORT_POSTNAME_FILE = "Reporting";
	// Extension excel
	public static final String ADMIN_REPORT_EXTENSION_FILE = "xls";

	public static final String ADMIN_BRACKET_OPEN = "(";
	public static final String ADMIN_BRACKET_CLOSE = ")";
	public static final String ADMIN_SEPARATOR_FUNCTIONS_CELL = ":";
	public static final String ADMIN_POINT = ".";
	public static final String ADMIN_SEPARATOR_REGISTER = ";";
	public static final String ADMIN_SEPARATOR_HEAD_REGISTER = ";";
	public static final String ADMIN_SEPARATOR_FUNCTIONS = ";";
	public static final String ADMIN_SEPARATOR_NAME_FILE = "_";
	// // // // // // // // // // // // // // // // // // // // // // // // //
	// //

	// PAGINACION TRASPASOS LISTA BLANCA
	public static int NUM_PAGINAS_POR_BLOQUE = 10;
	public static int BOTON_ANTERIOR = 1;
	public static int BOTON_SIGUIENTE = 2;
	public static int NUM_REGISTROS_PAGINA = 10;
	public static int ANYO_DOS_MIL_NUEVE = 2009;
	// REPOSITORIO FICHEROS LISTA BLANCA
	public static final String FILE_PARENT = "/";
	public static final String FILE_NAME_SEPARATOR = "_";

	public static final String OPGESTIONCIP = "GestionCip";
	public static final String OPALTAONLINE = "AltaOnline";
	public static final String OPALTA = "Alta";
	public static final String OPMODIFICACIONASINCRONA = "Modificacion";
	public static final String OPBAJAASINCRONA = "Baja";

	// Errores http
	public final static int ERRORXML = 510; // Error en el xml
	public final static int ERRORSISTEMA = 511; // Problemas con el sistema
	public final static int ERRORAGENTENOVALIDO = 512; // agente no valido
	public final static int ERRORCOLASATURADA = 513; // cola saturada
	public final static int ERRORSISTEMABLOQUEADO = 514; // Sistema bloqueado
	public final static int ERRORFIRMANOVALIDA = 515; // Error firma no valida
	public final static int ERRORSERVICIO = 516; // Operacion no registrada
	public final static int ERRORAGENTEBLOQUEADO = 517; // Agente bloqueado

	public final static String AGENTESNS = "99";
	
	public final static String COD_PRESTACION_SNS = "99";

	// Agentes del sistema
	public final static String[] LITERALAGENTES = { "00.- AGENTE DE TESTEO DEL SNS", "01.- SEGURIDAD SOCIAL", "02.- SERVICIO ANDALUZ DE SALUD. SAS", "03.- SERVICIO CANARIO DE SALUD",
			"04.- INSTITUTO CATALAN DE LA SALUD. ICS", "05.- SERVICIO GALLEGO DE SALUD. SERGAS", "06.- SERVICIO NAVARRO DE SALUD. OSASUNBIDEA", "07.- SERVICIO VASCO DE SALUD. OSAKIDETZA",
			"08.- SERVICIO VALENCIANO DE SALUD. SERVASA", "09.- MUFACE", "10.- MUGEJU", "11.- ISFAS", "12.- INSALUD -> snsinsalud.insalud.es/msc/msc.asp",
			"13.- SERVICIO ARAGONES DE SALUD. SALUD -> mirambel.salud.aragob.es/msc/msc.asp", "14.- SERVICIO DE SALUD DEL PRINCIPADO DE ASTURIAS -> sespasns.sespa.es/msc/msc.asp",
			"15.- SERVEI DE SALUT DES ILLES BALEARS. IBSALUT -> lappits1.caib.es/msc/msc.asp", "16.- SERVICIO CANTABRO DE SALUD -> scsalud3.scsalud.es/msc/msc.asp",
			"17.- SERVICIO DE SALUD DE CASTILLA-LA MANCHA. SESCAM -> 10.60.16.7/msc/msc.asp", "18.- SANIDAD DE CASTILLA Y LEON. SACYL -> grsgscvant0101.sacyl.es/msc/msc.asp",
			"19.- INGESA -> snsinsalud.insalud.es/msc/msc.asp", "20.- SERVICIO EXTREMEÑO DE SALUD. SES -> 10.165.4.42/msc/msc.asp",
			"21.- SERVICIO RIOJANO DE SALUD -> tsi1_larioja.seris.es/msc/msc.asp", "22.- SERVICIO MURCIANO DE SALUD -> filemon.carm.es/msc/msc.asp",
			"23.- CONSEJERIA DE SANIDAD DE LA COMUNIDAD DE MADRID -> salud.madrid.org/msc/msc.asp", };

	// OPERACIONES DE CONSULTA
	public final static String C001 = "C001"; // Consulta Informacion Actual
												// Usuario PC001,
	// Información Historico del Usuario PC002
	// Información de Prestaciones de usuario PC003
	// Información Actual y Prestaciones del Usuario PC004
	public final static String C002 = "C002"; // Consulta Operaciones Asociadas
	public final static String C007 = "C007"; // Consulta Codificacion
	public final static String C010 = "C010"; // Consulta Seg-Social

	// OPERACIONES DE ALTA
	public final static String A006 = "A006"; // Gestion Centralizada CIP
	public final static String A007 = "A007"; // Alta OnLine
	public final static String A008 = "A008"; // Gestion Centralizada Cip
												// (unificación)

	//alta de titular
	public final static String A001 = "A001"; // Alta usuario internamente

	// OPERACIONES DE ASINCRONAS
	public final static String A002 = "A002"; // Baja usuario
	public final static String A003 = "A003"; // Modificacion informacion
												// usuario
	public final static String A004 = "A004"; // Inicio/Fin temporalidad

	// consultas

	public final static String C001_PC001 = "PC001"; // Consulta Informacion
														// Actual Usuario
	public final static String C001_PC002 = "PC002"; // Información Historico
														// del Usuario
	public final static String C001_PC003 = "PC003"; // Información de
														// Prestaciones de
														// usuario
	public final static String C001_PC004 = "PC004"; // Información Actual y
														// Prestaciones del
														// Usuario

	public final static String C003 = "C003"; // Respuesta Consulta Actual
												// Usuario
	public final static String C004 = "C004"; // Respuesta Consulta Historico
	public final static String C005 = "C005"; // Respuesta Consulta Operaciones
												// Asociadas
	public final static String C006 = "C006"; // Respuesta Consulta Prestaciones
	public final static String C008 = "C008"; // Respuesta Consulta Codificacion
	public final static String C009 = "C009"; // Respuesta Consulta Inf Usuario
												// y Prestaciones
	public final static String C011 = "C011"; // Respuesta Consulta Inf SegSocial

	public final static String C012 = "C012"; // Peticion consulta INSS 
	public final static String C013 = "C013"; // Respuesta INSS

	// notificaciones
	public final static String N001 = "N001"; // Notificacion baja usuario
	public final static String N002 = "N002"; // Notificacion Baja en Servicio
												// Salud
	public final static String N003 = "N003"; // Notificacion Temporalidad
	public final static String N004 = "N004"; // Notificacion Modificacion
												// usuario
	public final static String N005 = "N005"; // Notificacion Resultado
												// Operacion
	public final static String N006 = "N006"; // Notificacion Fin Temporalidad
	public final static String N007 = "N007"; // Notificacion de Seleccion
	public final static String N008 = "N008"; // Notificacion Cambio
												// Codificacion
	public final static String N009 = "N009"; // Notificacion de resultado de
												// alta gestion cip
	public final static String N010 = "N010"; // Notificacion discrepancia
												// Seguridad Social
	public final static String N011 = "N011"; // Notificacion de baja por
												// duplicidad

	
	public final static String M001 = "M001"; //OPERACION DE MANTENIMIENTO
	public final static String M002 = "M002"; //CRON FIN DE TEMPORALIDAD
	
	public final static String RDL_VOLCAR_A_FICHERO ="ZFILE";
	public final static String RDL_SINCRONIZACION ="SINCRONIZACION_INSS";
	//indicadores de farmacia
	public static final String TSI_001="TSI 001";
	public static final String TSI_002="TSI 002";
	public static final String TSI_002_SUB00="00";
	public static final String TSI_002_SUB01="01";
	public static final String TSI_002_SUB02="02";
	public static final String TSI_003="TSI 003";
	public static final String TSI_004="TSI 004";
	public static final String TSI_005="TSI 005";
	public static final String TSI_005_SUB03="03";
	public static final String TSI_006="TSI 006";
	public static final String TSI_F003="F 003";
	public static final String TSI_F004="F 004";
	public static final String TSI_NOFAR="NOFAR";
	 

	
	//tipo procedencia historico del INSS
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_FICHERO=new BigDecimal("1");
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_OTRO_ID_INSS=new BigDecimal("2");
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_DUPLICIDAD_SNS=new BigDecimal("3");
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_IPF_UNICO_CONSTRAINT=new BigDecimal("4");
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_DESVINCULACION_MANUAL=new BigDecimal("5");
	public static final BigDecimal INSS_HISTORICO_PROCEDENCIA_LIBERACION_NAF=new BigDecimal("6");
	
	// temporalidad
	public final static BigDecimal TEMPORALIDAD_COD_ESTADO_VIGENTE = new BigDecimal("1");
	public final static BigDecimal TEMPORALIDAD_COD_ESTADO_FINALIZADA = new BigDecimal("2");
	
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_TIEMPO_EXPIRADO = new BigDecimal("1");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_ALTA_OTRA_COMUNIDAD = new BigDecimal("2");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_BAJA_POR_DEFUNCION = new BigDecimal("3");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_INICIO_TEMPORALIDAD_EN_OTRA_COMUNIDAD = new BigDecimal("4");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_NOTIFICADA = new BigDecimal("5");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_BAJA_SERVICIO_SALUD = new BigDecimal("6");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_BAJA_ASEGURAMIENTO_INSS = new BigDecimal("7");
	public final static BigDecimal TEMPORALIDAD_COD_TIPO_FIN_BAJA_POR_DUPLICIDAD = new BigDecimal("8");
	
	public final static int TEMPORALIDAD_PERIODO_LARGA_DURACION = 30;
	
	public final static String SEPARADOR_INDICADOR_FARMACIA="@#$";
	public final static String SEPARADOR_NUM_LINEA_INSS="@@@";

	public final static String TITULO_SNSADMIN = "Tarjeta Admin";
	
	  public final static String DISC_SS_BD = "BAJA POR DEFUNCION";
	  public final static String DISC_SS_PSD = "PENSIONISTA SIN DERECHO";
	  public final static String DISC_SS_BPTSD = "BENEFICIARIO DE PENSIONISTA CON TITULAR SIN DERECHO"; // -> el campo no está
	  public final static String DISC_SS_AP = "ACTIVO ES PENSIONISTA";
	  public final static String DISC_SS_MSA = "MUTUALISTA SIN ASISTENCIA";
	  public final static String DISC_SS_SA = "SIN ASISTENCIA";

	  //Tipos de discrepancia
	  public final static String DISC_SS_SIN_ASISTENCIA = "20";
	  public final static String DISC_SS_BEN = "BENEFICIARIO ES TITULAR";
	  public final static String DISC_SS_CB = "COBERTURAS";
	  public final static String DISC_SS_ID = "IDENTIFICACION";
	  public final static String DISC_SS_CAMBIO_CB = "CAMBIO PRESTACION FARMACEUTICA";
	  public final static String DISC_SS_CAMBIO_TITULAR = "CAMBIO_TITULAR";
	  public final static String DISC_SS_CAMBIO_ASEGURAMIENTO = "CAMBIO_ASEGURAMIENTO";
	  public final static String DISC_SS_CAMBIO_TITULO_ASEGURAMIENTO = "CAMBIO_TITULO_ASEGURAMIENTO";

	  //CRUCE CON LOS DATOS DE TESORERIA
	  public final static String DISC_SS_BPET = "BENEFICIARIO DE PENSIONISTA ES TITULAR";

	  public final static String DISC_SS_AN = "ALTA NUEVA";
	  public final static String DISC_SS_AM = "ALTA MUTUALISTA";

	  //Criterios de identificacion
	  public final static String DISC_SS_NDL = "NAF_DNI_LETRAS";
	  public final static String DISC_SS_DL = "DNI_LETRAS";
	  public final static String DISC_SS_NF = "NAF_TITULAR";
	  public final static String DISC_SS_NP = "NAF";
	  
	  //Criterios de identificacion en INSS
	  public final static String INSS_IDENTIFICACION_COD_USUARIO_SNS = "COD_USUARIO_SNS";
	  public final static String INSS_IDENTIFICACION_NAF_DNI_NIE = "NAF_DNI_NIE";
	  
	  //MOTIVO DE BAJA 
	  public final static String INSS_MOTIVO_BAJA_DE_ASEGURAMIENTO="01";
	  public final static String INSS_MOTIVO_BAJA_POR_DEFUNCION="02";
	  public final static String INSS_MOTIVO_BAJA_POR_DUPLICIDAD="03";
	  public final static String INSS_MOTIVO_BAJA_POR_ERROR_EN_ALTA="04";
	  public final static String INSS_MOTIVO_BAJA_POR_OPCION="05";
	  public final static String INSS_MOTIVO_BAJA_POR_CAMBIO_DE_CONDICION="06";

	  public static final String INSS_CUARENTENA_CAUSA_COD_SNS_NO_COINCIDENTE="COD_SNS_NO_COINCIDENTE";
	  public static final String INSS_CUARENTENA_CAUSA_MULTIPLES_COINCIDENTES="MULTIPLES_COINCIDENTES";
	  public static final String INSS_CUARENTENA_CAUSA_NO_ENCONTRADO="REGISTRO NO ENCONTRADO";
	  public static final String INSS_CUARENTENA_CAUSA_MULTIPLES_TITULARES_COINCIDENTES_INSS="MULTIPLES_TITULARES_COINCIDENTES_INSS";
	  public static final String INSS_CUARENTENA_CAUSA_NO_ENCONTRADO_TITULAR_INSS="NO_ENCONTRADO_TITULAR_INSS";
	  public static final String INSS_CUARENTENA_CAUSA_MULTIPLES_TITULARES_COINCIDENTES_SNS="MULTIPLES_TITULARES_COINCIDENTES_SNS";

	  public static final String INSS_CRITERIO_VINCULACION_MANUAL="ADM_MANUAL";

}
