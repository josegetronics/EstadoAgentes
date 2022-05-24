package sns.estadoagentes.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import gasai.util.out.Salida;
import sns.comun.bean.CambiosTraspasosBean;
import sns.comun.bean.InfoCambiosTraspasosBean;
import sns.comun.config.Inicializacion;
import sns.comun.config.QueryEstadoAgentes;
import sns.comun.util.Util;

public class InfoTraspasos extends Info {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	@Override
	public CambiosTraspasosBean getInfoTraspasos(String path, int numCambiosVisualizar, AccesoBD bd) {

		CambiosTraspasosBean cambiosTraspasosBean = null;
		//
		Salida salida = new Salida();

		try {
			logger.debug("getInfoTraspasos: INICIO");
			logger.debug("getInfoTraspasos: -------------------------------------");
			//
			salida = this.getInfoBBDD(bd, path, numCambiosVisualizar);
			if (!salida.getError()) {
				cambiosTraspasosBean = (CambiosTraspasosBean) salida.getValor();
				HashMap mapInfo = cambiosTraspasosBean.getMapInformacion();
				//
				salida = this.getInfoBBDDTraspasosTotal(bd);
				if (!salida.getError()) {
					HashMap mapInformacionTraspasos = (HashMap) salida.getValor();
					//
					salida = this.getUnionInfo(mapInfo, mapInformacionTraspasos);
					if (!salida.getError()) {
						cambiosTraspasosBean.setMapInformacion(mapInfo);
						cambiosTraspasosBean.setNumeroRegistrosXml(Inicializacion.numDeTraspasosXml);
					}
				}
			} else {
				throw new Exception(Misc.nz(salida.getMsg()));
			}
			logger.debug("getInfoTraspasos: numCambiosVisualizar: " + Inicializacion.numCambiosVisualizar);
			logger.debug("getInfoTraspasos: numDeTraspasosXml:    " + Inicializacion.numDeTraspasosXml);
			logger.debug("getInfoTraspasos: salida.getError:      " + salida.getError());
			logger.debug("getInfoTraspasos: salida.getMsg:        " + salida.getMsg());
			logger.debug("getInfoTraspasos: -------------------------------------");
			logger.debug("getInfoTraspasos: FIN");
		} catch (Exception e) {
			salida.setError(true);
			salida.setMsg(e.getMessage());
			salida.setExcepcion(e);
			logger.error("getInfoTraspasos: Exception: " + e.getMessage(), e);
		} 
		return cambiosTraspasosBean;
	}

	private Salida getInfoBBDD(AccesoBD bd, String path, int numCambiosVisualizar) {

		HashMap mapInformacion = new HashMap();
	

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Salida salida = new Salida();

		Util util = new Util();
		QueryEstadoAgentes queryEstadoAgentes = new QueryEstadoAgentes();

		try {
			logger.debug("getInfoBBDD: INICIO");
			
			//
			// Se genera la Query de manera que se ejecutara entre dos fechas
			// Se cojera la fecha actual, se le restara un dia y se tomara desde
			// las 00:00 hasata las 23:59
			String query = Misc.nz(queryEstadoAgentes.generateQueryCambiosTraspasos());
			//
			// Se realiza la consulta
			HashMap hConn = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hConn.get("ps");
			resultSet = (ResultSet) hConn.get("rs");

			while (resultSet.next()) {
				//
				String codUsuarioSns = resultSet.getString("COD_USUARIO_SNS");
				String camposAfectados = resultSet.getString("CAMPOS_AFECTADOS");
				String camposAnteriores = resultSet.getString("VALOR_ANTERIOR");
				String valoresSolicitud = resultSet.getString("VALOR_SOLICITUD");
				int caActual = resultSet.getInt("CA_ACTUAL");
				if(caActual == 19){
					caActual = 18;
				}
				int caAnterior = resultSet.getInt("CA_ANTERIOR");
				if(caAnterior == 19){
					caAnterior = 18;
				}
				//
				String nombre = resultSet.getString("NOMBRE");
				String apellido1 = resultSet.getString("APELLIDO1");
				String apellido2 = resultSet.getString("APELLIDO2");
				String codSexo = resultSet.getString("COD_SEXO");
				String fecha = resultSet.getString("FECHA");

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
				if (codUsuarioSns.equals("BBBBBBBBBK652424") && valoresSolicitud.indexOf("CABA<LLERO DE GRACIA") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>CABA<LLERO DE GRACIA</valor>", "<valor>CABALLERO DE GRACIA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCW820059") && valoresSolicitud.indexOf("AVDA<. ESCALERITAS, 33 3º PTA. 9") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AVDA<. ESCALERITAS, 33 3º PTA. 9</valor>", "<valor>AVDA. ESCALERITAS, 33 3º PTA. 9</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBD380697") && camposAnteriores.indexOf("&&&&&") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>&&&&&</valor>", "<valor>-----</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDN128702") && valoresSolicitud.indexOf("SEA & SUN HILLS SN B/6 AP 602") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SEA & SUN HILLS SN B/6 AP 602</valor>", "<valor>SEA AND SUN HILLS SN B/6 AP 602</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDM018526") && camposAnteriores.indexOf("5º IZ<DA.") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>5º IZ<DA.</valor>", "<valor>5ºIZ</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCQ218202") && camposAnteriores.indexOf("NUESTRA SEÃORA DE LOS CLARINES") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>NUESTRA SEÃORA DE LOS CLARINES</valor>", "<valor>NUESTRA SENORA DE LOS CLARINES</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCH546200") && camposAnteriores.indexOf("OÃATE") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>OÃATE</valor>", "<valor>ONATE</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDH139005") && camposAnteriores.indexOf("A<") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>A<</valor>", "<valor>A</valor>");
				}

				// Se construye un Xml a partir de la informacion de la BBDD
				String xml = this.getXml(camposAfectados, camposAnteriores, valoresSolicitud);
				// Se completa la informacion de los cambios con el xml nuevo
				salida = this.getInfoXmlBusquedaAproximada(mapInformacion, xml);
				//
				if (!salida.getError()) {
					Integer integer = (Integer) salida.getValor();
					// Se actualiza el map con el numero de cambios un map
					int numeroCambios = integer.intValue();
					//
					if (numeroCambios > 0) {
						this.addInfoMap(mapInformacion, caActual, numeroCambios);
						// Si el numero de cambios se corresponde con el pedido
						// para visualizar los mensajes
						if (numeroCambios >= numCambiosVisualizar) {
							InfoCambiosTraspasosBean infoCambiosTraspasosBean = new InfoCambiosTraspasosBean();
							infoCambiosTraspasosBean.setCamposAfectados(camposAfectados);
							infoCambiosTraspasosBean.setCamposAnteriores(camposAnteriores);
							infoCambiosTraspasosBean.setValoresSolicitud(valoresSolicitud);
							logger.debug("COMPROBAR@@@@@@@@@@"+codUsuarioSns+"-->"+numeroCambios);
							infoCambiosTraspasosBean.setCodUsuarioSns(codUsuarioSns);
							infoCambiosTraspasosBean.setCaActual(caActual);
							infoCambiosTraspasosBean.setCaAnterior(caAnterior);
							infoCambiosTraspasosBean.setNumeroCambios(numeroCambios);
							String valoresActuales = this.getValoresActuales(nombre, apellido1, apellido2, codSexo, fecha);
							infoCambiosTraspasosBean.setValoresActuales(valoresActuales);
						}
					}
				} else {
					logger.debug("getInfoBBDD: xx codUsuarioSns: " + codUsuarioSns);
					logger.debug("getInfoBBDD: SalidaMSG: " + salida.getMsg());
					throw new Exception("ERROR " + salida.getMsg());
				}
			}
			//
			CambiosTraspasosBean cambiosTraspasosBean = new CambiosTraspasosBean();
			cambiosTraspasosBean.setMapInformacion(mapInformacion);
			//
			salida.setValor(cambiosTraspasosBean);
			//
			logger.debug("getInfoBBDD: FIN");
		} catch (Exception e) {
			salida.setExcepcion(e);
			salida.setError(true);
			salida.setMsg(e.getMessage());
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
		}
		return salida;
	}

	@Override
	public StringBuffer viewInfoMapHorizontal(HashMap mapInformacion) throws Exception {
		int numeroAgentes = 0;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			logger.debug("sns.estadoagentes.mode.InfoEstados.viewInfoMapHorizontal: INICIO");

			stringBuffer.append("<br><table><tr><td>" + Inicializacion.mensajeEnteroCambiosTraspasos + "</td></tr></table>");

			Set comprobacionSet = mapInformacion.keySet();
			Iterator comprobacionIterator = comprobacionSet.iterator();
			while (comprobacionIterator.hasNext()) {
				String caActual = (String) comprobacionIterator.next();
				numeroAgentes++;
			}
			if (numeroAgentes <= 0) {
				stringBuffer.append("<table border=\"1\">");
				stringBuffer.append("<tr class=\"narr_tr_n\">");
				stringBuffer.append("<th class=\"narr_th_n\">AGENTES</th>");

				stringBuffer.append("<th class=\"narr_th_n\">NÚMERO DE CAMBIOS EFECTUADOS</th>");
				stringBuffer.append("</tr>");
				stringBuffer.append("</table>");
			} else {
				stringBuffer.append("<table border=\"1\">");
				stringBuffer.append("<tr class=\"narr_tr_n\">");
				stringBuffer.append("<th class=\"narr_th_n\" rowspan=\"2\">AGENTES</th>");
				stringBuffer.append("<th class=\"narr_th_n\" colspan=\"9\">NÚMERO DE CAMBIOS EFECTUADOS</th>");
				stringBuffer.append("<th class=\"narr_th_n\" rowspan=\"2\">TOTAL</th>");
				stringBuffer.append("</tr>");

				stringBuffer.append("<tr class=\"narr_tr_n\">");
				for (int j = 0; j < 9; j++) {
					stringBuffer.append("<th class=\"narr_th_n\">&nbsp;&nbsp;" + j + "</th>");
				}
				stringBuffer.append("</tr>");

				Set set = mapInformacion.keySet();
				Iterator iterator = set.iterator();

				int contadorVertical = 0;

				while (iterator.hasNext()) {
					String caActual = (String) iterator.next();
					int[] arrayCambios = (int[]) mapInformacion.get(caActual);

					String nombreComunidad = "";
					if (Inicializacion.MAP_COMUNIDADES.containsKey(Misc.nz(caActual))) {
						nombreComunidad = Misc.nz(Inicializacion.MAP_COMUNIDADES.get(caActual));
					}
					stringBuffer.append("<tr class=\"narr_tr_n\"><td align=\"center\">&nbsp;&nbsp;" + nombreComunidad + "</td>");

					int contadorHorizontal = 0;

					for (int i = 0; i < 9; i++) {
						String numeroCambio = "";
						if (arrayCambios[i] > 0) {
							numeroCambio = Misc.nz(Integer.toString(arrayCambios[i]));
						}

						if (Misc.esVacio(numeroCambio)) {
							stringBuffer.append("<td>&nbsp;&nbsp;</td>");
						} else {
							stringBuffer.append("<td>" + numeroCambio + "</td>");
						}

						if (Misc.esDigito(numeroCambio)) {
							contadorHorizontal += Integer.parseInt(numeroCambio);
							contadorVertical += Integer.parseInt(numeroCambio);
						}
					}
					stringBuffer.append("<td>" + contadorHorizontal + "</td>");

					stringBuffer.append("</tr>");
				}
				stringBuffer.append("<tr class=\"narr_tr_n\">");
				stringBuffer.append("<th class=\"narr_th_n\" rowspan=\"2\" ></th>");
				stringBuffer.append("<th class=\"narr_th_n\" rowspan=\"2\" colspan=\"9\">TOTAL</th>");
				stringBuffer.append("<th class=\"narr_th_n\" rowspan=\"2\" >" + contadorVertical + "</th>");
				stringBuffer.append("</tr>");
				stringBuffer.append("</table>");
			}
			logger.debug("sns.estadoagentes.mode.InfoEstados.viewInfoMapHorizontal: FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return stringBuffer;
	}

	@Override
	public CambiosTraspasosBean getInfoModificaciones(String path,
			int numCambiosVisualizar, AccesoBD bd) {
		// TODO Auto-generated method stub
		return null;
	}
}

