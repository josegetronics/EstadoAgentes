package sns.estadoagentes.model;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import sns.comun.bean.TraspasosNuevoBean;
import sns.comun.config.Inicializacion;
import sns.comun.util.InfOperacion;
import sns.comun.util.Util;

public class EstadoAgentesHelper {
	
	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	public HashMap contarTresMas(HashMap mapInformacion) throws Exception {
		
		HashMap hTresMas = new HashMap();
		try {
			logger.debug("sns.estadoagentes.model.EstadoAgentesHelper.contarModificaciones: INICIO");
			Set set = mapInformacion.keySet();
			Iterator iterator = set.iterator();
			String valor = "";

			int contadorVertical = 0;

			while (iterator.hasNext()) {
				valor = "";
				String caActual = (String) iterator.next();
				int[] arrayCambios = (int[]) mapInformacion.get(caActual);
				int contadorHorizontal = 0;
				//El indice del for empieza por 3 porque son 3 o mas cambios los que tenemos que contar
				for (int i = 3; i < 9; i++) {
					String numeroCambio = "";
					if (arrayCambios[i] > 0) {
						numeroCambio = Misc.nz(Integer.toString(arrayCambios[i]));
					}
					if (Misc.esDigito(numeroCambio)) {
						contadorHorizontal += Integer.parseInt(numeroCambio);
						contadorVertical += Integer.parseInt(numeroCambio);
					}
				}
				if(contadorHorizontal > 0){
					valor = Integer.toString(contadorHorizontal);
				}
				hTresMas.put(caActual, valor);
			}
			hTresMas.put("TOTAL", Integer.toString(contadorVertical));
			logger.debug("sns.estadoagentes.model.EstadoAgentesHelper.contarModificaciones: FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return hTresMas;
	}
	
	public HashMap contarTotal(HashMap mapInformacion) throws Exception {
		HashMap hTotal = new HashMap();
		try {
			logger.debug("sns.estadoagentes.mode.EstadoAgentesHelper.contarTotal: INICIO");
			Set set = mapInformacion.keySet();
			Iterator iterator = set.iterator();
			String valor = "";

			int contadorVertical = 0;

			while (iterator.hasNext()) {
				valor = "";
				String caActual = (String) iterator.next();
				int[] arrayCambios = (int[]) mapInformacion.get(caActual);
				int contadorHorizontal = 0;
				// El indice del for empieza por 0 porque queremos contar el total de los cambios
				for (int i = 0; i < 9; i++) {
					String numeroCambio = "";
					if (arrayCambios[i] > 0) {
						numeroCambio = Misc.nz(Integer.toString(arrayCambios[i]));
					}
					if (Misc.esDigito(numeroCambio)) {
						contadorHorizontal += Integer.parseInt(numeroCambio);
						contadorVertical += Integer.parseInt(numeroCambio);
					}
				}
				if(contadorHorizontal > 0){
					valor = Integer.toString(contadorHorizontal);
				}
				hTotal.put(caActual, valor);
			}
			hTotal.put("TOTAL", Integer.toString(contadorVertical));
			logger.debug("sns.estadoagentes.mode.InfoEstados.viewInfoMapHorizontal: FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return hTotal;
	}
	
	public int getCcaaAnterior(String codSns, AccesoBD bd) throws Exception {
		//
		int caAnterior = 0;
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			logger.debug("INICIO");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select /*+ PARALLEL(8) */ replace(replace(h.valor_anterior,'<valor>',''),'</valor>','') ca_anterior  ");
			sbQuery.append(" FROM snsalud.traspasos_bloqueados tb, snsalud.historico_mod_usuarios h  ");
			sbQuery.append(" where TB.COD_USUARIO_SNS = ?  ");
			sbQuery.append(" and TB.BLOQUEO_ACTIVO = 0  ");
			sbQuery.append(" and H.COD_USUARIO_SNS = TB.COD_USUARIO_SNS  ");
			sbQuery.append(" and H.FECHA_APLICACION_SOLICITUD >= TB.FECHA_OPERACION ");
			sbQuery.append(" and H.CAMPOS_AFECTADOS = '<campo>cod_prestacion_servicio</campo>' ");
			query = sbQuery.toString();
			parametros.put("1", Misc.nz(codSns));
			//
			logger.debug("query:      " + query);
			//
			HashMap hashMapRaw = bd.consultaRaw(query, parametros);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			resultSet = preparedStatement.executeQuery();
			//
			while (resultSet.next()) {
				//
				caAnterior = resultSet.getInt("CA_ANTERIOR");
			}
			//
			logger.debug("FIN");
			return caAnterior;
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			logger.debug("query:      " + query);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
	}
	
	public HashMap getDesbloqueos(AccesoBD bd) throws Exception {
		//
		HashMap hDesbloqueos = new HashMap();
		//
		String query = "";
		Util util = new Util();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String valor = "";
		//
		try {
			logger.debug("INICIO");
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT  /*+ PARALLEL(8) */ ca.COD_CA_ISO, count(1) CONTADOR  ");
			sbQuery.append(" FROM 	  snsalud.TRASPASOS_LISTA_BLANCA tb, snsalud.desglose_agentes da, snsalud.CA_PRESTACION_SERVICIO cap, snsalud.comunidades_autonomas ca  ");
			sbQuery.append(" WHERE    tb.FECHA_REALIZADO between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND      da.COD_AGENTE              = tb.COD_AGENTE_SOLICITANTE  ");
			sbQuery.append(" AND      DA.COD_PRESTACION_SERVICIO <> 17  ");
			sbQuery.append(" AND      cap.COD_PRESTACION_SERVICIO = da.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND      ca.COD_COMUNIDAD            = cap.COD_COMUNIDAD ");
			sbQuery.append(" GROUP BY ca.COD_CA_ISO ");
			sbQuery.append(" ORDER BY ca.COD_CA_ISO ASC ");
			query = sbQuery.toString();
			//
			logger.debug("query:      " + query);
			//
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			resultSet = preparedStatement.executeQuery();
			//
			//
			hDesbloqueos = util.inicializarHashMap();
			int total = 0;
			int totalIngesa = 0;
			while (resultSet.next()) {
				//
				hDesbloqueos.put(Misc.nz(resultSet.getString("COD_CA_ISO")), Misc.nz(resultSet.getString("CONTADOR")));
				total += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				if (Misc.nz(resultSet.getString("COD_CA_ISO")).equals("18") || Misc.nz(resultSet.getString("COD_CA_ISO")).equals("19")) {
					totalIngesa += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				}
			}
			
			if(totalIngesa > 0){
				valor = Integer.toString(totalIngesa);
			}
			
			hDesbloqueos.put("18", valor);
			hDesbloqueos.remove("19");
			hDesbloqueos.put("TOTAL", Integer.toString(total));
			//
			logger.debug("FIN");
			return hDesbloqueos;
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			logger.debug("query:      " + query);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
	}
	
	public HashMap getBloqueos(AccesoBD bd) throws Exception {
		//
		HashMap hBloqueos = new HashMap();
		//
		String query = "";
		Util util = new Util();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String valor = "";
		//
		try {
			logger.debug("INICIO");
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT    /*+ PARALLEL(8) */ ca.COD_CA_ISO, count(1) CONTADOR  ");
			sbQuery.append(" FROM     snsalud.TRASPASOS_LISTA_NEGRA tn, snsalud.desglose_agentes da, snsalud.CA_PRESTACION_SERVICIO cap, snsalud.comunidades_autonomas ca  ");
			sbQuery.append(" WHERE    tn.ACTIVO = 1   ");
			sbQuery.append(" AND  tn.FECHA_PETICION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND      da.COD_AGENTE              = tn.COD_AGENTE_SOLICITANTE   ");
			sbQuery.append(" AND      cap.COD_PRESTACION_SERVICIO = da.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND      ca.COD_COMUNIDAD            = cap.COD_COMUNIDAD ");
			sbQuery.append(" GROUP BY ca.COD_CA_ISO ");
			sbQuery.append(" ORDER BY ca.COD_CA_ISO ASC ");
			query = sbQuery.toString();
			//
			logger.debug("query:      " + query);
			//
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			resultSet = preparedStatement.executeQuery();
			//
			//
			hBloqueos = util.inicializarHashMap();
			int total = 0;
			int totalIngesa = 0;
			while (resultSet.next()) {
				//
				hBloqueos.put(Misc.nz(resultSet.getString("COD_CA_ISO")), Misc.nz(resultSet.getString("CONTADOR")));
				total += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				if (Misc.nz(resultSet.getString("COD_CA_ISO")).equals("18") || Misc.nz(resultSet.getString("COD_CA_ISO")).equals("19")) {
					totalIngesa += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				}
			}
			
			if(totalIngesa > 0){
				valor = Integer.toString(totalIngesa);
			}
			
			hBloqueos.put("18", valor);
			hBloqueos.remove("19");
			hBloqueos.put("TOTAL", Integer.toString(total));
			//
			logger.debug("FIN");
			return hBloqueos;
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			logger.debug("query:      " + query);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
	}
	
	
	public HashMap getUsuarios(AccesoBD bd) throws Exception {
		//
		HashMap hUsuarios = new HashMap();
		//
		String query = "";
		Util util = new Util();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String valor = "";
		//
		try {
			logger.debug("INICIO");
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" Select  /*+ PARALLEL(8) */ ca.COD_CA_ISO, count(1) CONTADOR    ");
			sbQuery.append(" from   snsalud.historico_mod_usuarios h, snsalud.registro_operaciones r, snsalud.desglose_agentes da, snsalud.CA_PRESTACION_SERVICIO cap, snsalud.comunidades_autonomas ca  ");
			sbQuery.append(" where  h.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" and H.COD_TIPO_MODIFICACION = 4  ");
			sbQuery.append(" and R.COD_OPERACION = H.COD_OPERACION ");
			sbQuery.append(" and R.COD_TIPO_OPERACION in ('A006','A007','A008') ");
			sbQuery.append(" AND da.COD_AGENTE = R.COD_AGENTE_ORIGEN ");
			sbQuery.append(" AND da.COD_PRESTACION_SERVICIO <> 17 ");
			sbQuery.append(" AND cap.COD_PRESTACION_SERVICIO = da.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND ca.COD_COMUNIDAD = cap.COD_COMUNIDAD ");
			sbQuery.append(" GROUP BY ca.COD_CA_ISO ");
			sbQuery.append(" ORDER BY ca.COD_CA_ISO ASC ");
			query = sbQuery.toString();
			//
			logger.debug("query:      " + query);
			//
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			resultSet = preparedStatement.executeQuery();
			//
			//
			hUsuarios = util.inicializarHashMap();
			int total = 0;
			int totalIngesa = 0;
			while (resultSet.next()) {
				//
				hUsuarios.put(Misc.nz(resultSet.getString("COD_CA_ISO")), Misc.nz(resultSet.getString("CONTADOR")));
				total += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				if (Misc.nz(resultSet.getString("COD_CA_ISO")).equals("18") || Misc.nz(resultSet.getString("COD_CA_ISO")).equals("19")) {
					totalIngesa += Integer.parseInt(Misc.nz(resultSet.getString("CONTADOR")));
				}
			}
			
			if(totalIngesa > 0){
				valor = Integer.toString(totalIngesa);
			}
			
			hUsuarios.put("18", valor);
			hUsuarios.remove("19");
			hUsuarios.put("TOTAL", Integer.toString(total));
			//
			logger.debug("FIN");
			return hUsuarios;
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			logger.debug("query:      " + query);
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
				preparedStatement = null;
			}
		}
	}
	
	public StringBuffer pintarTraspasosNuevos(TraspasosNuevoBean traspasosNuevoBean) throws Exception {
		int numeroAgentes = 0;
		StringBuffer stringBuffer = new StringBuffer();
		HashMap mapInformacion = null;
		LinkedHashMap mapRecorrido = null;
		try {
			logger.debug("sns.estadoagentes.model.InfoTraspasosNuevo.viewInfoMapHorizontal: INICIO");


			mapInformacion = traspasosNuevoBean.gethTotalTraspasos();
			Set comprobacionSet = mapInformacion.keySet();
			
				stringBuffer.append("<br><br><p><table border=\"1\">");
				stringBuffer.append("<tr class=\"narr_tr_n\">");
				stringBuffer.append("<th class=\"narr_th_n\">AGENTES</th>");
				stringBuffer.append("<th class=\"narr_th_n\">TOTAL TRASPASOS</th>");
				stringBuffer.append("<th class=\"narr_th_n\">TRASPASOS 3 O MAS (1)</th>");
				stringBuffer.append("<th class=\"narr_th_n\">DESBLOQUEOS REALIZADOS</th>");
				stringBuffer.append("<th class=\"narr_th_n\">BLOQUEOS DIARIOS</th>");
				stringBuffer.append("<th class=\"narr_th_n\">CODIGOS SNS GENERADOS</th>");
				stringBuffer.append("<th class=\"narr_th_n\">TOTAL CAMBIOS 3 O MAS AGENTE (2)</th>");
				stringBuffer.append("<th class=\"narr_th_n\">PORCENTAJE % 1-2</th>");
				stringBuffer.append("</tr>");
		
			
				mapRecorrido= Util.inicializarRecorreMap();
				Set set = mapRecorrido.keySet();
				Iterator iterator = set.iterator();
				float traspasos = 0;
		        float modificaciones = 0;
		        float resultado =0 ;
		        DecimalFormat df = new DecimalFormat("0.00"); 
				while (iterator.hasNext()) {
					traspasos = 0;
					modificaciones = 0;
					resultado = 0 ;
					String caActual = (String) iterator.next();
					stringBuffer.append("<tr class=\"narr_tr_n\">");
					String nombreComunidad = "";
					if (Inicializacion.MAP_COMUNIDADES.containsKey(Misc.nz(caActual))) {
						nombreComunidad = Misc.nz(Inicializacion.MAP_COMUNIDADES.get(caActual));
					}
					if(!Misc.nz(traspasosNuevoBean.gethTraspasos().get(caActual)).equals("")){
						traspasos = Float.parseFloat(Misc.nz(traspasosNuevoBean.gethTraspasos().get(caActual)));
					}
					if (!Misc.nz(traspasosNuevoBean.gethModificaciones().get(caActual)).equals("")) {
						modificaciones = Float.parseFloat(Misc.nz(traspasosNuevoBean.gethModificaciones().get(caActual)));
					}
					if(Misc.nz(caActual).equals("TOTAL")){
						stringBuffer.append("<th class=\"narr_th_n\">&nbsp;&nbsp;" + nombreComunidad + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethTotalTraspasos().get(caActual)) + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethTraspasos().get(caActual)) + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethDesbloqueos().get(caActual)) + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethBloqueos().get(caActual)) + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethUsuarios().get(caActual)) + "</th>");
						stringBuffer.append("<th class=\"narr_th_n\">" + Misc.nz(traspasosNuevoBean.gethModificaciones().get(caActual)) + "</th>");
						resultado = (traspasos * 100) / (modificaciones + traspasos);
						stringBuffer.append("<th class=\"narr_th_n\">" + df.format(resultado) + " %</th>");
						stringBuffer.append("</tr>");
					}
					else{
						stringBuffer.append("<td align=\"center\">&nbsp;&nbsp;" + nombreComunidad + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethTotalTraspasos().get(caActual)) + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethTraspasos().get(caActual)) + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethDesbloqueos().get(caActual)) + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethBloqueos().get(caActual)) + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethUsuarios().get(caActual)) + "</td>");
						stringBuffer.append("<td>" + Misc.nz(traspasosNuevoBean.gethModificaciones().get(caActual)) + "</td>");
						//para que no salga caracteres raros, comprobamos que llegue uno de los dos valores con los que calculamos el porcentaje
						//si no vieniesen pintaria 0.00
						if(traspasos != 0 || modificaciones != 0){
							resultado = (traspasos * 100) / (modificaciones + traspasos);
						}
						stringBuffer.append("<td>" + df.format(resultado) + " %</td>");
						stringBuffer.append("</tr>");
					}
				}
				
				stringBuffer.append("</table>");
			
			logger.debug("sns.estadoagentes.model.InfoTraspasosNuevo.viewInfoMapHorizontal: FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return stringBuffer;
	}
	
	public InfOperacion comprobarOpAgente(String agente, String operaciones, String codOperacion, AccesoBD bd) throws SQLException {
		String query = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		InfOperacion resultado = null;

		if (Misc.esVacio(codOperacion))
			return null;
		try {
			query = "select /*+ PARALLEL(8) */ R.cod_operacion,R.fecha_operacion from (select max(cod_operacion)";
			query = query + " cod_operacion from snsalud.registro_operaciones where cod_operacion>=" + codOperacion;
			// query = query +
			// " and fecha_operacion < to_date('06-10-2011 00:00','dd-mm-yyyy HH24:MI')";
			query = query + " and cod_agente_origen=" + agente + " and cod_tipo_operacion in (" + operaciones;
			query = query + ")) A,snsalud.registro_operaciones R where R.cod_operacion=A.cod_operacion";
			logger.debug("query comprobarOpAgente: " + query);

			HashMap hConn = bd.consultaRaw(query);
			rs = (ResultSet) hConn.get("rs");
			ps = (PreparedStatement) hConn.get("ps");
			if (rs.next())
				resultado = new InfOperacion(rs.getLong("cod_operacion"), rs.getTimestamp("fecha_operacion"));
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (Exception e) {
			}
		}
		return resultado;
	}
	
	public HashMap ficheroAltasNA(String path, AccesoBD bd) throws Exception {
		//
		HashMap hAltasIndebidas = new HashMap();
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		FileWriter fileWriterAltasIndebidas= null;
		//
		logger.debug("ficheroAltasNA");
		//
		try {
			logger.debug("INICIO - Fichero Altas NA");
			
			String fecha = util.generateFecha("-");
			
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select /*+ PARALLEL(8) */ DC.COD_TITULO, H.COD_USUARIO_SNS, DP.NOMBRE, DP.APELLIDO1, DP.APELLIDO2, to_char(DP.FECHA_NAC,'dd/mm/yyyy') as FECHA_NAC, A.AGENTE ");//DP.FECHA_NAC
			sbQuery.append("  from snsalud.historico_mod_usuarios h, snsalud.datos_cobertura dc, snsalud.usuarios u, snsalud.desglose_agentes da, snsalud.agentes a, snsalud.datos_personales DP ");
			sbQuery.append(" where h.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha); // Formato YYYY-MM-DD
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" and H.COD_TIPO_MODIFICACION = 4  ");
			sbQuery.append(" and DC.COD_USUARIO_SNS = H.COD_USUARIO_SNS ");
			sbQuery.append(" and (DC.COD_TITULO between 79 and 86 or DC.COD_TITULO = 58) ");
			sbQuery.append(" and U.COD_USUARIO_SNS = DC.COD_USUARIO_SNS ");
			//Incluido para incorporar las columnas de Nombre y Apellidos
			sbQuery.append(" and U.COD_USUARIO_SNS = DP.COD_USUARIO_SNS ");
			sbQuery.append(" and DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" and A.COD_AGENTE = DA.COD_AGENTE ");
			
			query = sbQuery.toString();	
			
			logger.debug("Before query");
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			
			resultSet = preparedStatement.executeQuery();
			
			logger.debug("Creates query");
			logger.debug("Query: "+query);
			
			StringBuffer stringBufferNombreFicheroAltasIndebidas = new StringBuffer();
			stringBufferNombreFicheroAltasIndebidas.append(util.generateFecha(""));
			stringBufferNombreFicheroAltasIndebidas.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_ALTAS_INDEBIDAS);
			StringBuffer stringBufferPathCompletaAltasIndebidas = new StringBuffer();
			stringBufferPathCompletaAltasIndebidas.append(path);
			stringBufferPathCompletaAltasIndebidas.append(stringBufferNombreFicheroAltasIndebidas.toString());
			fileWriterAltasIndebidas = new FileWriter(stringBufferPathCompletaAltasIndebidas.toString());
			// Se escribe la cabecera del fichero
			fileWriterAltasIndebidas.write("COD_TITULO;CODIGO_USUARIO_SNS;NOMBRE;APELLIDO1;APELLIDO2;FECHA_NAC;AGENTE;" + "\n");
			fileWriterAltasIndebidas.flush();
			 
			while (resultSet.next()) {
				fileWriterAltasIndebidas.write(Misc.nz(resultSet.getString("COD_TITULO"))+";"+Misc.nz(resultSet.getString("COD_USUARIO_SNS"))+";"+Misc.nz(resultSet.getString("NOMBRE"))+";"+Misc.nz(resultSet.getString("APELLIDO1"))+";"+Misc.nz(resultSet.getString("APELLIDO2"))+";"+Misc.nz(resultSet.getString("FECHA_NAC"))+";"+Misc.nz(resultSet.getString("AGENTE"))+"\n");
				fileWriterAltasIndebidas.flush();
			}
			hAltasIndebidas.put("NombreFichero", stringBufferNombreFicheroAltasIndebidas.toString());
			hAltasIndebidas.put("PathFichero", path);

			//		
			logger.debug("FIN - Fichero Altas NA");
			return hAltasIndebidas;
		}
		catch (Exception e) {
			logger.error("query: " + query);
			logger.debug("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}			
			try {
				if (fileWriterAltasIndebidas != null) {
					fileWriterAltasIndebidas.close();
				}
			}
			catch (Exception e3) {
				fileWriterAltasIndebidas = null;
			}
		}
	}
	
	
	public HashMap ficheroAltasMuface(String path, AccesoBD bd) throws Exception {
		
		HashMap hAltasMuface = new HashMap();
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		FileWriter fileWriterAltasMuface= null;
		
		logger.debug("aaaa-mm-dd_altasMuface.csv");
		
		try {
			logger.debug("INICIO");
			
			String fecha = util.generateFecha("-"); // Formato YYYY-MM-DD
			
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT /*+ PARALLEL(8) */ COD_TITULO, COD_USUARIO_SNS, ");
			sbQuery.append("CASE WHEN KEY_MESSAGE LIKE 'MUT_ALTA_INSS%' THEN 'FICHERO INSS' ELSE 'SERVICIO WEB' ");
			sbQuery.append("END TIPO_ALTA FROM ");
			sbQuery.append("(SELECT COD_OPERACION_MAESTRA, EXTRACTVALUE(xmltype(MENSAJE_XML), '//@key') AS KEY_MESSAGE, EXTRACTVALUE(xmltype(MENSAJE_XML), '//codigo_titulo') AS COD_TITULO "); 
			sbQuery.append("FROM SNSALUD.REGISTRO_OPERACIONES ");
			sbQuery.append("WHERE COD_TIPO_OPERACION = 'A007' AND COD_AGENTE_ORIGEN = 9 ");
			sbQuery.append("AND FECHA_OPERACION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')) ORIGEN, ");
			sbQuery.append("(SELECT COD_OPERACION_MAESTRA, EXTRACTVALUE(xmltype(MENSAJE_XML), '//usuario/sns') AS COD_USUARIO_SNS "); 
			sbQuery.append("FROM SNSALUD.REGISTRO_OPERACIONES ");
			sbQuery.append("WHERE COD_TIPO_OPERACION = 'N009' AND COD_AGENTE_DESTINO = 9 ");
			sbQuery.append("AND MENSAJE_XML not like '%usuarios_encontrados%'");
			sbQuery.append("AND FECHA_OPERACION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')) DESTINO ");
			sbQuery.append("WHERE ORIGEN.COD_OPERACION_MAESTRA = DESTINO.COD_OPERACION_MAESTRA AND COD_USUARIO_SNS IS NOT NULL");
			
			query = sbQuery.toString();	
			
			logger.debug("Antes de ejecutar la query");
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			resultSet = preparedStatement.executeQuery();
			
			logger.debug("Ejecutando la siguiente query");
			logger.debug("Query: "+query);
			
			StringBuffer stringBufferNombreFicheroAltasMuface = new StringBuffer();
			stringBufferNombreFicheroAltasMuface.append(util.generateFecha(""));
			stringBufferNombreFicheroAltasMuface.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_ALTAS_MUFACE);
			StringBuffer stringBufferPathCompletaAltasMuface = new StringBuffer();
			stringBufferPathCompletaAltasMuface.append(path);
			stringBufferPathCompletaAltasMuface.append(stringBufferNombreFicheroAltasMuface.toString());
			fileWriterAltasMuface = new FileWriter(stringBufferPathCompletaAltasMuface.toString());
			
			// Se escribe la cabecera de la tabla del fichero
			fileWriterAltasMuface.write("COD_TITULO;CODIGO_USUARIO_SNS;TIPO ALTA" + "\n");
			fileWriterAltasMuface.flush();
			 
			while (resultSet.next()) {
				fileWriterAltasMuface.write(Misc.nz(resultSet.getString("COD_TITULO"))+";"
											+Misc.nz(resultSet.getString("COD_USUARIO_SNS"))+";"
											+Misc.nz(resultSet.getString("TIPO_ALTA"))+"\n");
				fileWriterAltasMuface.flush();
			}
			
			hAltasMuface.put("NombreFichero", stringBufferNombreFicheroAltasMuface.toString());
			hAltasMuface.put("PathFichero", path);

			logger.debug("FIN");
			return hAltasMuface;
		}
		catch (Exception e) {
			logger.error("query: " + query);
			logger.debug("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}			
			try {
				if (fileWriterAltasMuface != null) {
					fileWriterAltasMuface.close();
				}
			}
			catch (Exception e3) {
				fileWriterAltasMuface = null;
			}
		}
	}
	
	public HashMap ficheroResumenMensajeriaN012(String path, AccesoBD bd) throws Exception {
		//
		HashMap hMensajesN012 = new HashMap();
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		FileWriter fileWriterMensajesN012= null;
		
		logger.debug("ficheroResumenMensajeriaN012");
		
		try {
			logger.debug("INICIO");
			
			String fecha = util.generateFecha("-"); // Formato YYYY-MM-DD
			
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append("SELECT /*+ PARALLEL(8) */ N012.*, ALTAS.AGENTE_ORIGEN, ");
			sbQuery.append("CASE ");
			sbQuery.append("WHEN ALTAS.AGENTE_ORIGEN IN (9,10,11) THEN ");
			sbQuery.append("(SELECT COD_TITULO FROM SNSALUD.MUTUALISTAS WHERE COD_USUARIO_SNS = N012.SNS) ");
			sbQuery.append("ELSE ");
			sbQuery.append("(SELECT COD_TITULO FROM SNSALUD.DATOS_COBERTURA WHERE COD_USUARIO_SNS = N012.SNS) ");
			sbQuery.append("END COD_TITULO ");
			sbQuery.append("FROM( ");
			sbQuery.append("SELECT COD_OPERACION_MAESTRA, FECHA_OPERACION, MENSAJE_XML, ");			
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/nombre') NOMBRE, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/apellido1') APELLIDO1, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/apellido2') APELLIDO2, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/fecha_nac') FECHA_NAC, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/sexo') SEXO, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/id_ssalud') ID_SALUD, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/dni') DNI_NIE, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/naf') NAF, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/naf_titular') NAF_TITULAR, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//usuario/sns') SNS, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//resultado/*[1]') RESULTADO, ");
			sbQuery.append("EXTRACTVALUE(xmltype(\"MENSAJE_XML\"), '//resultado/*[2]/*[1]') CODIGO_ERROR ");
			sbQuery.append("FROM SNSALUD.REGISTRO_OPERACIONES ");
			sbQuery.append("WHERE FECHA_OPERACION > TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00:00','yyyy-mm-dd HH24:MI:ss') ");
			sbQuery.append("AND FECHA_OPERACION < TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59:59','yyyy-mm-dd HH24:MI:ss') ");
			sbQuery.append("AND COD_AGENTE_DESTINO IN (9,10,11) ");
			sbQuery.append("AND COD_TIPO_OPERACION = 'N012' ");
			sbQuery.append(") N012 LEFT JOIN ");
			sbQuery.append("(SELECT COD_OPERACION_MAESTRA, ");
			sbQuery.append("COD_AGENTE_ORIGEN AGENTE_ORIGEN ");
			sbQuery.append("FROM SNSALUD.REGISTRO_OPERACIONES ");
			sbQuery.append("WHERE COD_TIPO_OPERACION IN ('A006','A007', 'A008')) ALTAS ");
			sbQuery.append("ON N012.COD_OPERACION_MAESTRA = ALTAS.COD_OPERACION_MAESTRA ");
			query = sbQuery.toString();	
			
			logger.debug("antes query");
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			
			resultSet = preparedStatement.executeQuery();
			
			logger.debug("hace query");
			logger.debug("Query: "+query);
			
			StringBuilder nombreFicheroResumenN012 = new StringBuilder();
			nombreFicheroResumenN012.append(util.generateFecha(""));
			nombreFicheroResumenN012.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_RESUMEN_N012);
			
			StringBuilder pathCompletaResumenN012 = new StringBuilder();
			pathCompletaResumenN012.append(path);
			pathCompletaResumenN012.append(nombreFicheroResumenN012.toString());
			
			fileWriterMensajesN012 = new FileWriter(pathCompletaResumenN012.toString());
			
			// Se escribe la cabecera del fichero
			fileWriterMensajesN012.write("FECHA_OPERACION;AGENTE_ORIGEN;RESULTADO;CODIGO_ERROR;SNS;NOMBRE;APELLIDO1;APELLIDO2;FECHA_NAC;SEXO;ID_SALUD;DNI_NIE;NAF;NAF_TITULAR;COD_TITULO" + "\n");
			fileWriterMensajesN012.flush();
			 
			while (resultSet.next()) {
				fileWriterMensajesN012.write(Misc.nz(resultSet.getString("FECHA_OPERACION"))+";"
											+Misc.nz(resultSet.getString("AGENTE_ORIGEN"))+";"
											+Misc.nz(resultSet.getString("RESULTADO"))+";"
											+Misc.nz(resultSet.getString("CODIGO_ERROR"))+";"
											+Misc.nz(resultSet.getString("SNS"))+";"
											+Misc.nz(resultSet.getString("NOMBRE"))+";"
											+Misc.nz(resultSet.getString("APELLIDO1"))+";"
											+Misc.nz(resultSet.getString("APELLIDO2"))+";"	
											+Misc.nz(resultSet.getString("FECHA_NAC"))+";"
											+Misc.nz(resultSet.getString("SEXO"))+";"
											+Misc.nz(resultSet.getString("ID_SALUD"))+";"	
											+Misc.nz(resultSet.getString("DNI_NIE"))+";"
											+Misc.nz(resultSet.getString("NAF"))+";"
											+Misc.nz(resultSet.getString("NAF_TITULAR"))+";"
											+Misc.nz(resultSet.getString("COD_TITULO"))										
											+"\n");
				fileWriterMensajesN012.flush();
			}
			hMensajesN012.put("NombreFichero", nombreFicheroResumenN012.toString());
			hMensajesN012.put("PathFichero", path);

			//		
			logger.debug("FIN");
			return hMensajesN012;
		}
		catch (Exception e) {
			logger.error("query: " + query);
			logger.debug("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}			
			try {
				if (fileWriterMensajesN012 != null) {
					fileWriterMensajesN012.close();
				}
			}
			catch (Exception e3) {
				fileWriterMensajesN012 = null;
			}
		}
	}
	
	
	public HashMap ficheroAltasExtranjerosDosVeinte(String path, AccesoBD bd) throws Exception {
		//
		HashMap hAltasMuface = new HashMap();
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		FileWriter fileWriterAltasMuface= null;
		//
		logger.debug("ficheroAltasExtranjerosDosVeinte");
		//
		try {
			logger.debug("INICIO - Fichero Altas Extranjeros Dos Veinte");
			
			String fecha = util.generateFecha("-"); // Formato YYYY-MM-DD
			
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT /*+ PARALLEL (8) */ ");
			sbQuery.append("  A.COD_TITULO, A.COD_USUARIO_SNS, A.NOMBRE, A.APELLIDO1, A.APELLIDO2, to_char(A.FECHA_NAC,'dd/mm/yyyy') as FECHA_NAC, A.AGENTE "); //A.FECHA_NAC
			sbQuery.append(" FROM ( SELECT /*+ PARALLEL (8) */ ");
			sbQuery.append(" DP.COD_NACIONALIDAD, DP.COD_PAIS, DP.NOMBRE, DP.APELLIDO1, DP.APELLIDO2, DP.FECHA_NAC, DP.FLAG_EXTRANJERO, DP.DNI_NIE, DC.COD_TITULO, HM.COD_USUARIO_SNS, CA.AGENTE , TRUNC (MONTHS_BETWEEN (TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'), 'DD/MM/YYYY'), dp.FECHA_NAC) / 12, 0) AS EDAD ");
			sbQuery.append(" FROM SNSALUD.HISTORICO_MOD_USUARIOS HM, SNSALUD.DATOS_COBERTURA DC, SNSALUD.TITULOS T, SNSALUD.USUARIOS NAC, SNSALUD.DATOS_PERSONALES DP, ");
			sbQuery.append(" SNSALUD.DESGLOSE_AGENTES DA, SNSALUD.AGENTES CA ");
			sbQuery.append(" WHERE HM.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI') ");
			sbQuery.append(" AND HM.COD_TIPO_MODIFICACION = 4 ");
			sbQuery.append(" AND DC.COD_USUARIO_SNS = HM.COD_USUARIO_SNS ");
			sbQuery.append(" AND T.COD_TITULO = DC.COD_TITULO ");
			sbQuery.append(" AND T.COD_TIPO_TITULO = 2 ");
			sbQuery.append(" AND NAC.COD_USUARIO_SNS = DC.COD_USUARIO_SNS ");
			sbQuery.append(" AND DP.COD_USUARIO_SNS = NAC.COD_USUARIO_SNS ");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = NAC.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND CA.COD_AGENTE = DA.COD_AGENTE) a ");
			sbQuery.append(" WHERE    ((a.DNI_NIE IS NOT NULL AND (a.DNI_NIE LIKE 'X%' OR a.DNI_NIE LIKE 'Y%' OR a.DNI_NIE LIKE 'Z%')) ");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NOT NULL AND a.COD_NACIONALIDAD <> '724') ");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NULL AND a.COD_PAIS IS NOT NULL AND a.COD_PAIS <> '724') ");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NULL AND a.COD_PAIS IS NULL AND a.FLAG_EXTRANJERO IS NOT NULL AND a.FLAG_EXTRANJERO <> '0')) AND EDAD BETWEEN 2 AND 20 ");
			sbQuery.append(" order by A.AGENTE ");
			
			query = sbQuery.toString();	
			
			logger.debug("antes query");
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			
			resultSet = preparedStatement.executeQuery();
			
			logger.debug("hace query");
			logger.debug("Query: "+query);
			
			StringBuffer stringBufferNombreFicheroAltasMuface = new StringBuffer();
			stringBufferNombreFicheroAltasMuface.append(util.generateFecha(""));
			stringBufferNombreFicheroAltasMuface.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_ALTAS_EXTRANJEROS_2_20);
			StringBuffer stringBufferPathCompletaAltasMuface = new StringBuffer();
			stringBufferPathCompletaAltasMuface.append(path);
			stringBufferPathCompletaAltasMuface.append(stringBufferNombreFicheroAltasMuface.toString());
			fileWriterAltasMuface = new FileWriter(stringBufferPathCompletaAltasMuface.toString());
			
			// Se escribe la cabecera del fichero
			fileWriterAltasMuface.write("COD_TITULO;CODIGO_USUARIO_SNS;NOMBRE;APELLIDO1;APELLIDO2;FECHA_NAC;AGENTE;" + "\n");
			fileWriterAltasMuface.flush();
			 
			while (resultSet.next()) {
				fileWriterAltasMuface.write(Misc.nz(resultSet.getString("COD_TITULO"))+";"+Misc.nz(resultSet.getString("COD_USUARIO_SNS"))+";"+Misc.nz(resultSet.getString("NOMBRE"))+";"+Misc.nz(resultSet.getString("APELLIDO1"))+";"+Misc.nz(resultSet.getString("APELLIDO2"))+";"+Misc.nz(resultSet.getString("FECHA_NAC"))+";"+Misc.nz(resultSet.getString("AGENTE"))+"\n");
				fileWriterAltasMuface.flush();
			}
			hAltasMuface.put("NombreFichero", stringBufferNombreFicheroAltasMuface.toString());
			hAltasMuface.put("PathFichero", path);

			//		
			logger.debug("FIN - Fichero Altas Extranjeros Dos Veinte");
			return hAltasMuface;
		}
		catch (Exception e) {
			logger.error("query: " + query);
			logger.debug("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}			
			try {
				if (fileWriterAltasMuface != null) {
					fileWriterAltasMuface.close();
				}
			}
			catch (Exception e3) {
				fileWriterAltasMuface = null;
			}
		}
	}
	

	public HashMap ficheroAltasCodigoSNSGenerados(String path, AccesoBD bd) throws Exception {
		//
		HashMap hAltasCodigoSNSGenerados = new HashMap();
		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		FileWriter fileWriterAltasSNS= null;
		//
		logger.debug("ficheroAltasCodigoSNSGenerado");
		//
		try {
			logger.debug("INICIO - Fichero Altas Codigos SNS Generados");
			//
			String fecha = util.generateFecha("-"); // Formato YYYY-MM-DD
			//
			StringBuffer sbQuery = new StringBuffer();			
			sbQuery.append("Select /*+ PARALLEL(8) */ DC.COD_TITULO, H.COD_USUARIO_SNS, DP.NOMBRE, DP.APELLIDO1, DP.APELLIDO2, to_char(DP.FECHA_NAC,'dd/mm/yyyy') as FECHA_NAC, A.AGENTE ");
			sbQuery.append("FROM SNSALUD.DATOS_COBERTURA DC, SNSALUD.HISTORICO_MOD_USUARIOS H, SNSALUD.DATOS_PERSONALES DP, SNSALUD.AGENTES A,");
			sbQuery.append(" SNSALUD.registro_operaciones R, SNSALUD.DESGLOSE_AGENTES DA, SNSALUD.USUARIOS U");
			sbQuery.append(" WHERE  H.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND H.COD_TIPO_MODIFICACION = 4  ");
			sbQuery.append(" AND R.COD_TIPO_OPERACION in ('A006','A007','A008')");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO <> 17");
			sbQuery.append(" AND R.COD_OPERACION = H.COD_OPERACION ");
			sbQuery.append("AND DC.COD_USUARIO_SNS = H.COD_USUARIO_SNS");
			sbQuery.append(" AND U.COD_USUARIO_SNS = DC.COD_USUARIO_SNS");
			sbQuery.append(" AND U.COD_USUARIO_SNS = DP.COD_USUARIO_SNS ");
			sbQuery.append(" AND DA.COD_AGENTE = R.COD_AGENTE_ORIGEN ");
			sbQuery.append(" AND A.COD_AGENTE = DA.COD_AGENTE");
			sbQuery.append(" AND H.COD_USUARIO_SNS not in (");
			sbQuery.append(" SELECT /*+ PARALLEL (8) */ ");
			sbQuery.append("  A.COD_USUARIO_SNS");
			sbQuery.append("  FROM ( SELECT /*+ PARALLEL (8) */ ");
			sbQuery.append(" DP.COD_NACIONALIDAD, DP.COD_PAIS, DP.NOMBRE, DP.APELLIDO1, DP.APELLIDO2, DP.FECHA_NAC, DP.FLAG_EXTRANJERO, DP.DNI_NIE, DC.COD_TITULO, HM.COD_USUARIO_SNS, CA.AGENTE , TRUNC (MONTHS_BETWEEN (TO_DATE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'), 'DD/MM/YYYY'), dp.FECHA_NAC) / 12, 0) AS EDAD ");
			sbQuery.append(" FROM SNSALUD.HISTORICO_MOD_USUARIOS HM, SNSALUD.DATOS_COBERTURA DC, SNSALUD.TITULOS T, SNSALUD.USUARIOS NAC, SNSALUD.DATOS_PERSONALES DP, ");
			sbQuery.append("SNSALUD.DESGLOSE_AGENTES DA, SNSALUD.AGENTES CA ");
			sbQuery.append("WHERE HM.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI') ");
			sbQuery.append(" AND HM.COD_TIPO_MODIFICACION = 4 ");
			sbQuery.append(" AND DC.COD_USUARIO_SNS = HM.COD_USUARIO_SNS");
			sbQuery.append(" AND T.COD_TITULO = DC.COD_TITULO");
			sbQuery.append(" AND T.COD_TIPO_TITULO = 2 ");
			sbQuery.append(" AND NAC.COD_USUARIO_SNS = DC.COD_USUARIO_SNS ");
			sbQuery.append(" AND DP.COD_USUARIO_SNS = NAC.COD_USUARIO_SNS");
			sbQuery.append(" AND DA.COD_PRESTACION_SERVICIO = NAC.COD_PRESTACION_SERVICIO");
			sbQuery.append(" AND CA.COD_AGENTE = DA.COD_AGENTE) a");
			sbQuery.append(" WHERE    ((a.DNI_NIE IS NOT NULL AND (a.DNI_NIE LIKE 'X%' OR a.DNI_NIE LIKE 'Y%' OR a.DNI_NIE LIKE 'Z%')) ");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NOT NULL AND a.COD_NACIONALIDAD <> '724')");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NULL AND a.COD_PAIS IS NOT NULL AND a.COD_PAIS <> '724')");
			sbQuery.append(" OR (a.DNI_NIE IS NULL AND a.COD_NACIONALIDAD IS NULL AND a.COD_PAIS IS NULL AND a.FLAG_EXTRANJERO IS NOT NULL AND a.FLAG_EXTRANJERO <> '0')) AND EDAD BETWEEN 2 AND 20)");
			sbQuery.append(" AND H.COD_USUARIO_SNS not in (");
			sbQuery.append(" select H.COD_USUARIO_SNS ");
			sbQuery.append(" from snsalud.historico_mod_usuarios h, snsalud.datos_cobertura dc, snsalud.usuarios u, snsalud.desglose_agentes da, snsalud.agentes a, snsalud.datos_personales DP"); 
			sbQuery.append(" where h.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append("  00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append("  23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" and H.COD_TIPO_MODIFICACION = 4 ");
			sbQuery.append(" and (DC.COD_TITULO between 79 and 86 or DC.COD_TITULO = 58) ");
			sbQuery.append(" and DC.COD_USUARIO_SNS = H.COD_USUARIO_SNS");
			sbQuery.append(" and U.COD_USUARIO_SNS = DC.COD_USUARIO_SNS");
			sbQuery.append(" and U.COD_USUARIO_SNS = DP.COD_USUARIO_SNS ");
			sbQuery.append(" and DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO");
			sbQuery.append(" and A.COD_AGENTE = DA.COD_AGENTE)");

			
			query = sbQuery.toString();	
			
			logger.debug("Antes query ficheroAltasCodigoSNSGenerado");
			HashMap hashMapRaw = bd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			
			resultSet = preparedStatement.executeQuery();
			
			logger.debug("Ejecuta query ficheroAltasCodigoSNSGenerado");
			logger.debug("Query: "+query);
			
			StringBuffer stringBufferNombreFicheroAltasSNS = new StringBuffer();
			stringBufferNombreFicheroAltasSNS.append(util.generateFecha(""));
			stringBufferNombreFicheroAltasSNS.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_ALTAS_SNS_GENERADAS);
			StringBuffer stringBufferPathCompletaAltasSNS = new StringBuffer();
			stringBufferPathCompletaAltasSNS.append(path);
			stringBufferPathCompletaAltasSNS.append(stringBufferNombreFicheroAltasSNS.toString());
			fileWriterAltasSNS = new FileWriter(stringBufferPathCompletaAltasSNS.toString());
			
			// Se escribe la cabecera del fichero
			fileWriterAltasSNS.write("COD_TITULO;CODIGO_USUARIO_SNS;NOMBRE;APELLIDO1;APELLIDO2;FECHA_NAC;AGENTE;" + "\n");
			fileWriterAltasSNS.flush();
			//
			while (resultSet.next()) {
				fileWriterAltasSNS.write(Misc.nz(resultSet.getString("COD_TITULO"))+";"+Misc.nz(resultSet.getString("COD_USUARIO_SNS"))+";"+Misc.nz(resultSet.getString("NOMBRE"))+";"+Misc.nz(resultSet.getString("APELLIDO1"))+";"+Misc.nz(resultSet.getString("APELLIDO2"))+";"+Misc.nz(resultSet.getString("FECHA_NAC"))+";"+Misc.nz(resultSet.getString("AGENTE"))+"\n");
				fileWriterAltasSNS.flush();
			}
			//
			hAltasCodigoSNSGenerados.put("NombreFichero", stringBufferNombreFicheroAltasSNS.toString());
			hAltasCodigoSNSGenerados.put("PathFichero", path);
			//		
			logger.debug("FIN - Fichero Altas Extranjeros Dos Veinte");
			return hAltasCodigoSNSGenerados;
		}
		catch (Exception e) {
			logger.error("query: " + query);
			logger.debug("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}			
			try {
				if (fileWriterAltasSNS != null) {
					fileWriterAltasSNS.close();
				}
			}
			catch (Exception e3) {
				fileWriterAltasSNS = null;
			}
		}
	}
}
