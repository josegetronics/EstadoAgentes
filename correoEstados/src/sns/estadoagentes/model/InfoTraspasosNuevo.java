package sns.estadoagentes.model;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import gasai.util.out.Salida;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.log4j.Logger;

import sns.comun.bean.CambiosTraspasosBean;
import sns.comun.bean.InfoCambiosTraspasosBean;
import sns.comun.config.Inicializacion;
import sns.comun.config.QueryEstadoAgentes;
import sns.comun.util.Util;

public class InfoTraspasosNuevo extends Info {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	public CambiosTraspasosBean getInfoModificaciones(String path, int numCambiosVisualizar, AccesoBD bd) {

		CambiosTraspasosBean cambiosTraspasosBean = null;
		//
		Salida salida = new Salida();

		try {
			logger.debug("getInfoModificaciones: INICIO");
			logger.debug("getInfoModificaciones: -------------------------------------");
			//
			salida = this.getInfoBBDD(bd, path, numCambiosVisualizar);
			if (!salida.getError()) {
				cambiosTraspasosBean = (CambiosTraspasosBean) salida.getValor();
				cambiosTraspasosBean.setNumeroRegistrosXml(Inicializacion.numDeTraspasosXml);
			} else {
				throw new Exception(Misc.nz(salida.getMsg()));
			}
			logger.debug("getInfoModificaciones: numCambiosVisualizar: " + Inicializacion.numCambiosVisualizar);
			logger.debug("getInfoModificaciones: numDeTraspasosXml:    " + Inicializacion.numDeTraspasosXml);
			logger.debug("getInfoModificaciones: salida.getError:      " + salida.getError());
			logger.debug("getInfoModificaciones: salida.getMsg:        " + salida.getMsg());
			logger.debug("getInfoModificaciones: -------------------------------------");
			logger.debug("getInfoModificaciones: FIN");
		} catch (Exception e) {
			salida.setError(true);
			salida.setMsg(e.getMessage());
			salida.setExcepcion(e);
			logger.error("getInfoModificaciones: Exception: " + e.getMessage(), e);
		} 
		return cambiosTraspasosBean;
	}

	private Salida getInfoBBDD(AccesoBD bd, String path, int numCambiosVisualizar) {

		HashMap mapInformacion = new HashMap();
		//
		FileWriter fileWriterCodsns = null;
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Salida salidaConBusquedaAproximada = new Salida();
		HashMap hCambios = new HashMap();

		Util util = new Util();
		QueryEstadoAgentes queryEstadoAgentes = new QueryEstadoAgentes();

		try {
			logger.debug("getInfoBBDD: INICIO");
			//
			StringBuffer stringBufferNombreFicheroCodsns = new StringBuffer();
			stringBufferNombreFicheroCodsns.append(util.generateFecha(""));
			stringBufferNombreFicheroCodsns.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_MISMA_CA);
			StringBuffer stringBufferPathCompletaCodSns = new StringBuffer();
			stringBufferPathCompletaCodSns.append(path);
			stringBufferPathCompletaCodSns.append(stringBufferNombreFicheroCodsns.toString());
			fileWriterCodsns = new FileWriter(stringBufferPathCompletaCodSns.toString());
			//
			// Se escribe la cabecera del fichero
			fileWriterCodsns.write("CODIGO_USUARIO_SNS;NUMERO DE CAMBIOS;CA_ACTUAL;CA_ORIGEN_XML;nombre;apellido1;apellido2;f/n;sexo;naf;naf-t;dni-nie;nombre;apellido1;apellido2;f/n;sexo;naf;naf-t;dni-nie;" + "\n");
			fileWriterCodsns.flush();

			//
			// Se genera la Query de manera que se ejecutara entre dos fechas
			// Se cogera la fecha actual, se le restara un dia y se tomara desde
			// las 00:00 hasata las 23:59
			String query = Misc.nz(queryEstadoAgentes.generateQueryUsuariosModificados());
			//
			// Se realiza la consulta
			HashMap hConn = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hConn.get("ps");
			resultSet = (ResultSet) hConn.get("rs");

			while (resultSet.next()) {
				//
				System.out.println("---Empieza la cosa--");
				String codUsuarioSns = resultSet.getString("COD_USUARIO_SNS");
				String camposAfectados = resultSet.getString("CAMPOS_AFECTADOS");
				String camposAnteriores = resultSet.getString("VALOR_ANTERIOR");
				String valoresSolicitud = resultSet.getString("VALOR_SOLICITUD");
				String valoresActuales = this.getXmlValoresActuales(resultSet);
				
				//
				// Ya que utilizamos el registro_operaciones y es la misma ccaa
				// la que modifica no como el traspaso
				// utilizamos el cod_agente_origen tanto en caActual como en
				// caAnterior
				int caActual = resultSet.getInt("COD_AGENTE_ORIGEN");
				if (Inicializacion.MAP_RELACION_COMUNIDADES.containsKey(new Integer(caActual))) {
					caActual = ((Integer) Inicializacion.MAP_RELACION_COMUNIDADES.get(new Integer(caActual))).intValue();
				} else {
					logger.debug("getInfoBBDD: ERROR: caActual: " + caActual);
				}
				int caAnterior = resultSet.getInt("COD_AGENTE_ORIGEN");
				if (Inicializacion.MAP_RELACION_COMUNIDADES.containsKey(new Integer(caAnterior))) {
					caAnterior = ((Integer) Inicializacion.MAP_RELACION_COMUNIDADES.get(new Integer(caAnterior))).intValue();
				} else {
					logger.debug("getInfoBBDD: ERROR: caAnterior: " + caAnterior);
				}


				if (codUsuarioSns.equals("BBBBBBBBBS586706") && camposAnteriores.indexOf("PERAL ARLANZ<A") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PERAL ARLANZ<A</valor>", "<valor>PERAL ARLANZA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBX371054") && valoresSolicitud.indexOf("CAR<COL") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ECUADOR  6 PISO 1 EL CAR<COL</valor>", "<valor>ECUADOR  6 PISO 1 EL CAR COL</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL189276") && camposAnteriores.indexOf("CAÃAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CAÃAS</valor>", "<valor>CAÑAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCP276092") && camposAnteriores.indexOf("MA<RTIN") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MA<RTIN</valor>", "<valor>MARTIN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBZ738242") && camposAnteriores.indexOf("PEÃA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PEÃA</valor>", "<valor>PEÑA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCN933361") && camposAnteriores.indexOf("LIÃAN") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LIÃAN</valor>", "<valor>LIÑAN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCN279971") && camposAnteriores.indexOf("MARIA PEÃA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MARIA PEÃA</valor>", "<valor>MARIA PEÑA</valor>");
				}				
				if (codUsuarioSns.equals("BBBBBBBBCM701428") && camposAnteriores.indexOf("ORDOÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ORDOÃEZ</valor>", "<valor>ORDOÑEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL107690") && camposAnteriores.indexOf("FRANÃ!A") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>FRANÃ!A</valor>", "<valor>FRANSA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL107690") && camposAnteriores.indexOf("VIÃES") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>VIÃES</valor>", "<valor>VINES</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL107690") && camposAnteriores.indexOf("3Âº-C") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>3Âº-C</valor>", "<valor>3C</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL225404") && camposAnteriores.indexOf("1 Âº  D") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1 Âº  D</valor>", "<valor>1D</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL225404") && camposAnteriores.indexOf("IBAÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IBAÃEZ</valor>", "<valor>IBANESA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK883044") && camposAnteriores.indexOf("MUÃOZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MUÃOZ</valor>", "<valor>MUNOZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL405486") && camposAnteriores.indexOf("NUÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NUÃEZ</valor>", "<valor>NUNEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL327568") && camposAnteriores.indexOf("MENDEZ NUÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MENDEZ NUÃEZ</valor>", "<valor>MENDEZ NUNEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL327568") && camposAnteriores.indexOf("IBAÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IBAÃEZ</valor>", "<valor>IBANEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL333040") && camposAnteriores.indexOf("CAMUÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CAMUÃEZ</valor>", "<valor>CAMUNEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL198372") && camposAnteriores.indexOf("MUÃOZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MUÃOZ</valor>", "<valor>MUNOZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBX352875") && camposAnteriores.indexOf("CAÃABATE") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CAÃABATE</valor>", "<valor>CANABATE</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL290731") && camposAnteriores.indexOf("GOÃALONS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>GOÃALONS</valor>", "<valor>GONALONS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBY890263") && camposAnteriores.indexOf("PLAZA< ISLA< DE LA MADERA ED LA RECOBA PORTAL 5 1º") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PLAZA< ISLA< DE LA MADERA ED LA RECOBA PORTAL 5 1º</valor>", "<valor>PLAZA- ISLA- DE LA MADERA ED LA RECOBA PORTAL 5 1º</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBP077553") && camposAnteriores.indexOf("1Âº D") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1Âº D</valor>", "<valor>1D</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBP077553") && camposAnteriores.indexOf("MARIA BEGOÃA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MARIA BEGOÃA</valor>", "<valor>MARIA BEGOA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCQ591110") && camposAnteriores.indexOf("1Âº") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1Âº</valor>", "<valor>1</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCQ591110") && camposAnteriores.indexOf("RUIZ<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>RUIZ<</valor>", "<valor>RU</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCJ319327") && valoresSolicitud.indexOf("SEA & SUN HILLS SN -  APTO 503") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SEA & SUN HILLS SN -  APTO 503</valor>", "<valor>SEA AND SUN HILLS SN -  APTO 503</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBZ358656") && camposAnteriores.indexOf("BEGOÃA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>BEGOÃA</valor>", "<valor>BEGONA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK652089") && camposAnteriores.indexOf("CAÃELLAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CAÃELLAS</valor>", "<valor>CANELLAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK652424") && camposAnteriores.indexOf("CABA<LLERO DE GRACIA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CABA<LLERO DE GRACIA</valor>", "<valor>CABALLERO DE GRACIA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDN128702") && valoresSolicitud.indexOf("SEA & SUN HILLS SN B/6 AP 602") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SEA & SUN HILLS SN B/6 AP 602</valor>", "<valor>SEA AND SUN HILLS SN B/6 AP 602</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCJ690535") && camposAnteriores.indexOf("PLAZA FERNANDEZ< VIAGAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PLAZA FERNANDEZ< VIAGAS</valor>", "<valor>PLAZA FERNANDEZ VIAGAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDM018526") && camposAnteriores.indexOf("5º IZ<DA.") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>5º IZ<DA.</valor>", "<valor>5ºIZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDL286037") && camposAnteriores.indexOf("ESC IZ<QUIERDA 3 D") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ESC IZ<QUIERDA 3 D</valor>", "<valor>ESC IZQ 3 D</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCQ218202") && camposAnteriores.indexOf("NUESTRA SEÃORA DE LOS CLARINES") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NUESTRA SEÃORA DE LOS CLARINES</valor>", "<valor>NUESTRA SENORA DE LOS CLARINES</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCX154007") && camposAnteriores.indexOf("CA?ADAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CA?ADAS</valor>", "<valor>CANADAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCX154007") && camposAnteriores.indexOf("2? IZ<DA.") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>2? IZ<DA.</valor>", "<valor>2? IZQDA.</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC159409") && camposAnteriores.indexOf("1º A ESC IZ<Q") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1º A ESC IZ<Q</valor>", "<valor>1º A ESC IZQ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCH546200") && camposAnteriores.indexOf("OÃATE") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>OÃATE</valor>", "<valor>ONATE</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK724534") && camposAnteriores.indexOf("PEÃALVER") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PEÃALVER</valor>", "<valor>PENLVER</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK724534") && camposAnteriores.indexOf("1Âº PTA E") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1Âº PTA E</valor>", "<valor>1A PTA E</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBY386680") && camposAnteriores.indexOf("BIENVENIDO PANPLIEGA TOVAR  5  2  IZ<Q  E") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>BIENVENIDO PANPLIEGA TOVAR  5  2  IZ<Q  E</valor>", "<valor>BIENVENIDO PANPLIEGA TOVAR  5  2  IZQ  E</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589277") && valoresSolicitud.indexOf("CLARA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>CLARA<</valor>", "<valor>CLARAN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589277") && camposAnteriores.indexOf("CLARA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CLARA<</valor>", "<valor>CLARAN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589279") && valoresSolicitud.indexOf("ELENA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ELENA<</valor>", "<valor>ELENAN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589279") && camposAnteriores.indexOf("ELENA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ELENA<</valor>", "<valor>ELENAN</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589286") && valoresSolicitud.indexOf("ARITZ<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ARITZ<</valor>", "<valor>ARITZM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589286") && camposAnteriores.indexOf("ARITZ<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ARITZ<</valor>", "<valor>ARITZM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589290") && valoresSolicitud.indexOf("ANDONI<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ANDONI<</valor>", "<valor>ANDONIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589290") && camposAnteriores.indexOf("ANDONI<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ANDONI<</valor>", "<valor>ANDONIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589292") && valoresSolicitud.indexOf("IKER<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>IKER<</valor>", "<valor>IKERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589292") && camposAnteriores.indexOf("IKER<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IKER<</valor>", "<valor>IKERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589294") && valoresSolicitud.indexOf("MAIKEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MAIKEL<</valor>", "<valor>MAIKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589294") && camposAnteriores.indexOf("MAIKEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MAIKEL<</valor>", "<valor>MAIKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591185") && valoresSolicitud.indexOf("MARKEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MARKEL<</valor>", "<valor>MARKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591185") && camposAnteriores.indexOf("MARKEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MARKEL<</valor>", "<valor>MARKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591194") && valoresSolicitud.indexOf("IRENE<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>IRENE<</valor>", "<valor>IRENEM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591194") && camposAnteriores.indexOf("IRENE<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IRENE<</valor>", "<valor>IRENEM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591223") && valoresSolicitud.indexOf("AITOR<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AITOR<</valor>", "<valor>AITORM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591223") && camposAnteriores.indexOf("AITOR<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AITOR<</valor>", "<valor>AITORM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591217") && valoresSolicitud.indexOf("LAURA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591217") && camposAnteriores.indexOf("LAURA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589443") && valoresSolicitud.indexOf("ANA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ANA<</valor>", "<valor>ANAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589443") && camposAnteriores.indexOf("ANA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ANA<</valor>", "<valor>ANAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591203") && valoresSolicitud.indexOf("ALEX<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ALEX<</valor>", "<valor>ALEXM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591203") && camposAnteriores.indexOf("ALEX<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ALEX<</valor>", "<valor>ALEXM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591195") && valoresSolicitud.indexOf("SAIOA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SAIOA<</valor>", "<valor>SAIOAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591195") && camposAnteriores.indexOf("SAIOA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>SAIOA<</valor>", "<valor>SAIOAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589372") && valoresSolicitud.indexOf("AMAIA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AMAIA<</valor>", "<valor>AMAIAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589372") && camposAnteriores.indexOf("AMAIA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AMAIA<</valor>", "<valor>AMAIAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591215") && valoresSolicitud.indexOf("NEREA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>NEREA<</valor>", "<valor>NEREAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591215") && camposAnteriores.indexOf("NEREA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NEREA<</valor>", "<valor>NEREAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591228") && valoresSolicitud.indexOf("ARKAITZ<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ARKAITZ<</valor>", "<valor>ARKAITZM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591228") && camposAnteriores.indexOf("ARKAITZ<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ARKAITZ<</valor>", "<valor>ARKAITZM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589438") && valoresSolicitud.indexOf("AINHOA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AINHOA<</valor>", "<valor>AINHOAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589438") && camposAnteriores.indexOf("AINHOA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AINHOA<</valor>", "<valor>AINHOAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589401") && valoresSolicitud.indexOf("CRISTHIAN<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>CRISTHIAN<</valor>", "<valor>CRISTHIANM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589401") && camposAnteriores.indexOf("CRISTHIAN<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CRISTHIAN<</valor>", "<valor>CRISTHIANM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589399") && valoresSolicitud.indexOf("ION<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ION<</valor>", "<valor>IONM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589399") && camposAnteriores.indexOf("ION<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ION<</valor>", "<valor>IONM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591209") && valoresSolicitud.indexOf("AROA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AROA<</valor>", "<valor>AROAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591209") && camposAnteriores.indexOf("AROA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AROA<</valor>", "<valor>AROAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591218") && valoresSolicitud.indexOf("LAURA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591218") && camposAnteriores.indexOf("LAURA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589717") && valoresSolicitud.indexOf("MIKEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MIKEL<</valor>", "<valor>MIKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589717") && camposAnteriores.indexOf("MIKEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MIKEL<</valor>", "<valor>MIKELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589435") && valoresSolicitud.indexOf("ROBERTO<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ROBERTO<</valor>", "<valor>ROBERTOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589435") && camposAnteriores.indexOf("ROBERTO<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ROBERTO<</valor>", "<valor>ROBERTOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591220") && valoresSolicitud.indexOf("NEKANE<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>NEKANE<</valor>", "<valor>NEKANEM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591220") && camposAnteriores.indexOf("NEKANE<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NEKANE<</valor>", "<valor>NEKANEM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591221") && valoresSolicitud.indexOf("GARAZI<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>GARAZI<</valor>", "<valor>GARAZIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591221") && camposAnteriores.indexOf("GARAZI<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>GARAZI<</valor>", "<valor>GARAZIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589440") && valoresSolicitud.indexOf("SARAI<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SARAI<</valor>", "<valor>SARAIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589440") && camposAnteriores.indexOf("SARAI<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>SARAI<</valor>", "<valor>SARAIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589877") && valoresSolicitud.indexOf("LEYRE<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LEYRE<</valor>", "<valor>LEYREM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589877") && camposAnteriores.indexOf("LEYRE<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LEYRE<</valor>", "<valor>LEYREM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC590169") && valoresSolicitud.indexOf("AITZIBER<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AITZIBER<</valor>", "<valor>AITZIBERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC590169") && camposAnteriores.indexOf("AITZIBER<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AITZIBER<</valor>", "<valor>AITZIBERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589430") && valoresSolicitud.indexOf("ENEKO<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ENEKO<</valor>", "<valor>ENEKOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589430") && camposAnteriores.indexOf("ENEKO<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ENEKO<</valor>", "<valor>ENEKOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589346") && valoresSolicitud.indexOf("IÑIGO<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>IÑIGO<</valor>", "<valor>IÑIGOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589346") && camposAnteriores.indexOf("IÑIGO<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IÑIGO<</valor>", "<valor>IÑIGOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589413") && valoresSolicitud.indexOf("ASIER<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ASIER<</valor>", "<valor>ASIERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC589413") && camposAnteriores.indexOf("ASIER<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ASIER<</valor>", "<valor>ASIERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591211") && valoresSolicitud.indexOf("JOSE MANUEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>JOSE MANUEL<</valor>", "<valor>JOSE MANUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591211") && camposAnteriores.indexOf("JOSE MANUEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>JOSE MANUEL<</valor>", "<valor>JOSE MANUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591211") && valoresSolicitud.indexOf("JOSE MANUEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>JOSE MANUEL<</valor>", "<valor>JOSE MANUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC591211") && camposAnteriores.indexOf("JOSE MANUEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>JOSE MANUEL<</valor>", "<valor>JOSE MANUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC604167") && valoresSolicitud.indexOf("PEIO<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>PEIO<</valor>", "<valor>PEIOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC604167") && camposAnteriores.indexOf("PEIO<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PEIO<</valor>", "<valor>PEIOM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC613439") && valoresSolicitud.indexOf("LETICIA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LETICIA<</valor>", "<valor>LETICIAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC613439") && camposAnteriores.indexOf("LETICIA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LETICIA<</valor>", "<valor>LETICIAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC603352") && valoresSolicitud.indexOf("ALAIN<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ALAIN<</valor>", "<valor>ALAINM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC603352") && camposAnteriores.indexOf("ALAIN<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>ALAIN<</valor>", "<valor>ALAINM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC601120") && valoresSolicitud.indexOf("NAIARA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>NAIARA<</valor>", "<valor>NAIARAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC601120") && camposAnteriores.indexOf("NAIARA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NAIARA<</valor>", "<valor>NAIARAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC602766") && valoresSolicitud.indexOf("IRATI<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>IRATI<</valor>", "<valor>IRATIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC602766") && camposAnteriores.indexOf("IRATI<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IRATI<</valor>", "<valor>IRATIM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC599528") && valoresSolicitud.indexOf("TRISTAN<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>TRISTAN<</valor>", "<valor>TRISTANM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC599528") && camposAnteriores.indexOf("TRISTAN<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>TRISTAN<</valor>", "<valor>TRISTANM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC605310") && valoresSolicitud.indexOf("MIRANDA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MIRANDA<</valor>", "<valor>MIRANDAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC605310") && camposAnteriores.indexOf("MIRANDA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MIRANDA<</valor>", "<valor>MIRANDAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC610219") && valoresSolicitud.indexOf("LAIDA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LAIDA<</valor>", "<valor>LAIDAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC610219") && camposAnteriores.indexOf("LAIDA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LAIDA<</valor>", "<valor>LAIDAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC613878") && valoresSolicitud.indexOf("MERITXELL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MERITXELL<</valor>", "<valor>MERITXELLM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC613878") && camposAnteriores.indexOf("MERITXELL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MERITXELL<</valor>", "<valor>MERITXELLM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC602963") && valoresSolicitud.indexOf("IZASKUN<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>IZASKUN<</valor>", "<valor>IZASKUNM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC602963") && camposAnteriores.indexOf("IZASKUN<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>IZASKUN<</valor>", "<valor>IZASKUNM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDK977036") && valoresSolicitud.indexOf("JON<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>JON<</valor>", "<valor>JONM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDK977036") && camposAnteriores.indexOf("JON<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>JON<</valor>", "<valor>JONM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC610894") && valoresSolicitud.indexOf("MIGUEL<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>MIGUEL<</valor>", "<valor>MIGUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC610894") && camposAnteriores.indexOf("MIGUEL<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MIGUEL<</valor>", "<valor>MIGUELM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC611853") && valoresSolicitud.indexOf("JAVIER<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>JAVIER<</valor>", "<valor>JAVIERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC611853") && camposAnteriores.indexOf("JAVIER<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>JAVIER<</valor>", "<valor>JAVIERM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC600279") && valoresSolicitud.indexOf("LAURA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC600279") && camposAnteriores.indexOf("LAURA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>LAURA<</valor>", "<valor>LAURAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC599520") && valoresSolicitud.indexOf("PAULA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>PAULA<</valor>", "<valor>PAULAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC599520") && camposAnteriores.indexOf("PAULA<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PAULA<</valor>", "<valor>PAULAM</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC600301") && valoresSolicitud.indexOf("FRANCISCO< JAVIER") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>FRANCISCO< JAVIER</valor>", "<valor>FRANCISCOM JAVIER</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC600301") && camposAnteriores.indexOf("FRANCISCO< JAVIER") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>FRANCISCO< JAVIER</valor>", "<valor>FRANCISCOM JAVIER</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCB016297") && camposAnteriores.indexOf("AVDZA<. JOSE MESA Y LOPEZ,  27 - 4º A") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>AVDZA<. JOSE MESA Y LOPEZ,  27 - 4º A</valor>", "<valor>AVDZA+. JOSE MESA Y LOPEZ,  27 - 4º A</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBC230048") && valoresSolicitud.indexOf("1º I<DA<") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>1º I<DA<</valor>", "<valor>1º IZQDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBC230048") && camposAnteriores.indexOf("1º I<DA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1º I<DA</valor>", "<valor>1º IZDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBY913025") && camposAnteriores.indexOf("CASAÃAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CASAÃAS</valor>", "<valor>CASANAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBS063269") && camposAnteriores.indexOf("MONTAÃEZ") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>MONTAÃEZ</valor>", "<valor>MONTANEZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBB631799") && valoresSolicitud.indexOf("2º I<DA") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>2º I<DA</valor>", "<valor>2º IDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCQ058329") && camposAnteriores.indexOf("1&") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>1&</valor>", "<valor>1</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDQ517753") && camposAnteriores.indexOf("3º  IZ<DA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>3º  IZ<DA</valor>", "<valor>3º IDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBZ810616") && camposAnteriores.indexOf("CRTA. VIEJA 40 3º IZ<DA.") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CRTA. VIEJA 40 3º IZ<DA.</valor>", "<valor>CRTA. VIEJA 40 3º IDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCC791348") && camposAnteriores.indexOf("3ºIZ<DA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>3ºIZ<DA</valor>", "<valor>3ºIZDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBN907872") && camposAnteriores.indexOf("3º I<ZDA") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>3º I<ZDA</valor>", "<valor>3ºIZDA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCL967367") && valoresSolicitud.indexOf("&") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor></valor><valor>724</valor><valor>76642588X</valor><valor>UR</valor><valor>ALCAIDESA SUITES GOLF & GARDENS</valor><valor>5</valor><valor>9</valor><valor>AT</valor><valor>11022000102</valor>",
							"<valor></valor><valor>724</valor><valor>76642588X</valor><valor>UR</valor><valor>ALCAIDESA SUITES GOLF AND GARDENS</valor><valor>5</valor><valor>9</valor><valor>AT</valor><valor>11022000102</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDV585044")  && valoresSolicitud.indexOf("&") != -1 ) {
					//camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>3º I<ZDA</valor>", "<valor>3ºIZDA</valor>");
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>78259928J</valor><valor>UR</valor><valor>ALCAIDESA SUITES GOLF & GARDENS</valor>",
							"<valor>78259928J</valor><valor>UR</valor><valor>ALCAIDESA SUITES GOLF AND GARDENS</valor>");				
				}
				// Se sustituye cualquier valor de la cadena que venga mal cerrado.
				valoresSolicitud = Misc.remplazar(valoresSolicitud, "<</valor>", "</valor>");
								
				
				
				// Se construye un Xml a partir de la informacion de la BBDD
				System.out.println("--construye@@@@@@@@@@@@@@@@@@@@@@@@--");
				String xml = this.getXml(camposAfectados, camposAnteriores, valoresSolicitud, valoresActuales);
				// Se completa la informacion de los cambios con el xml nuevo
				System.out.println("---saca los cambios---------");
				salidaConBusquedaAproximada = this.getInfoXmlBusquedaAproximada(mapInformacion, xml);
				System.out.println("---termina con los cambios---------");
				hCambios = this.getInfoXmlCambios(mapInformacion, xml);
				//
				if (!salidaConBusquedaAproximada.getError()) {
					Integer integerBusquedaAproximada = (Integer) salidaConBusquedaAproximada.getValor();
					// Se actualiza el map con el numero de cambios un map
					int numeroCambios = integerBusquedaAproximada.intValue();
					//
					if (numeroCambios > 0) {
						//Se añade la informacion a la HashMap mapInformacion para ser usada posteriormente para calcular datos de la tabla nueva
						this.addInfoMap(mapInformacion, caActual, numeroCambios);
						// Si el numero de cambios se corresponde con el pedido
						// para visualizar los mensajes
						if (numeroCambios >= numCambiosVisualizar) {
							InfoCambiosTraspasosBean infoCambiosTraspasosBean = new InfoCambiosTraspasosBean();
							infoCambiosTraspasosBean.setCamposAfectados(camposAfectados);
							infoCambiosTraspasosBean.setCamposAnteriores(camposAnteriores);
							infoCambiosTraspasosBean.setValoresSolicitud(valoresSolicitud);
							infoCambiosTraspasosBean.setCodUsuarioSns(codUsuarioSns);
							infoCambiosTraspasosBean.setCaActual(caActual);
							infoCambiosTraspasosBean.setCaAnterior(caAnterior);
							infoCambiosTraspasosBean.setNumeroCambios(numeroCambios);
							this.addRegiterCodSnsModificaciones(fileWriterCodsns, infoCambiosTraspasosBean.getCodUsuarioSns(), numeroCambios, caActual, caAnterior, 
									(String)Misc.nz(hCambios.get("nombre")), (String)Misc.nz(hCambios.get("apellido1")), (String)Misc.nz(hCambios.get("apellido2")),
									(String)Misc.nz(hCambios.get("fecha_nac")), (String)Misc.nz(hCambios.get("sexo")), (String)Misc.nz(hCambios.get("naf")), 
									(String)Misc.nz(hCambios.get("naf_titular")), (String)Misc.nz(hCambios.get("dni")), (HashMap<String,String>) hCambios.get("originales"));
						}
					}
				} else {
					logger.debug("getInfoBBDD: xx codUsuarioSns: " + codUsuarioSns);
					logger.debug("getInfoBBDD: SalidaMSG: " + salidaConBusquedaAproximada.getMsg());
					throw new Exception("ERROR " + salidaConBusquedaAproximada.getMsg());
				}
				System.out.println("---termina bucledddddddddddddddddd---------");
			}
			//
			CambiosTraspasosBean cambiosTraspasosBean = new CambiosTraspasosBean();
			cambiosTraspasosBean.setMapInformacion(mapInformacion);
			cambiosTraspasosBean.setNombreFichero("");
			cambiosTraspasosBean.setNombreFicheroCodSns(stringBufferNombreFicheroCodsns.toString());
			cambiosTraspasosBean.setPathFichero(path);
			//
			salidaConBusquedaAproximada.setValor(cambiosTraspasosBean);
			//
			logger.debug("getInfoBBDD: FIN");
		} catch (Exception e) {
			salidaConBusquedaAproximada.setExcepcion(e);
			salidaConBusquedaAproximada.setError(true);
			salidaConBusquedaAproximada.setMsg(e.getMessage());
			logger.error("getInfoBBDD: Exception: " + e.getMessage(), e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e3) {
				resultSet = null;
			}
			//
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e4) {
				preparedStatement = null;
			}
			//
			try {
				if (fileWriterCodsns != null) {
					fileWriterCodsns.close();
				}
			} catch (Exception e1) {
				fileWriterCodsns = null;
			}
		}
		return salidaConBusquedaAproximada;
	}

	


	@Override
	public StringBuffer viewInfoMapHorizontal(HashMap mapInformacion) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CambiosTraspasosBean getInfoTraspasos(String path,
			int numCambiosVisualizar, AccesoBD bd) {
		// TODO Auto-generated method stub
		return null;
	}

	

}

