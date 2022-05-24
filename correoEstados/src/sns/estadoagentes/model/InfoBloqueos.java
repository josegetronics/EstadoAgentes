package sns.estadoagentes.model;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import sns.comun.bean.ListaBlancaBean;
import sns.comun.config.Inicializacion;
import sns.comun.util.LineasMemoriaWriter;
import sns.comun.util.Util;

public class InfoBloqueos{

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	public String getInfoDesbloqueos(String nombreFicheroDesbloqueados, AccesoBD bd) {

		PrintWriter pTraspaso = null;
		Util util = new Util();
		String traspasosDesbloqueados = "";

		try {
			logger.debug("fichero de desbloqueos -> " + Inicializacion.pathDirCambiosTraspasos + nombreFicheroDesbloqueados);

			ArrayList resultado = this.getDesbloqueos(bd);
			logger.debug("Lineas obtenidas -> [" + resultado.size() + "]");
			if (resultado.size() > 1) {
				pTraspaso = new PrintWriter(new FileOutputStream(Inicializacion.pathDirCambiosTraspasos + "/" + nombreFicheroDesbloqueados));
				String linea = (String) resultado.get(0);
				pTraspaso.print(linea);
				String[] campos = linea.split(";");
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("<br><br><p><table><tr class=\"narr_tr_n\"><th  class=\"narr_th_n\">Desbloqueos realizados</th></tr></table>");
				stringBuffer.append("<table border=\"1\">");
				stringBuffer.append(lineaToHtml(campos, "class=\"narr_tr_n\"", "class=\"narr_th_n\""));
				for (int i = 1; i < resultado.size(); i++) {
					linea = (String) resultado.get(i);
					pTraspaso.print(linea);
					campos = linea.split(";");
					stringBuffer.append(lineaToHtml(campos, "class=\"narr_tr_n\"", ""));
				}
				stringBuffer.append("</table>");
				traspasosDesbloqueados = stringBuffer.toString();
			} else {
				nombreFicheroDesbloqueados = "";
			}

		} catch (Exception ex) {
			logger.error("Error obteniendo los desbloqueos", ex);
		} finally {
			try {
				if (pTraspaso != null) {
					pTraspaso.flush();
					pTraspaso.close();
				}
			} catch (Exception e) {
			}
		}
		return traspasosDesbloqueados;
	}

	public ArrayList getDesbloqueos(AccesoBD bd) throws Exception {
		//
		ArrayList resultado = new ArrayList();
		LineasMemoriaWriter lineasMemoriaWriter = new LineasMemoriaWriter();
		//
		try {
			logger.debug("INICIO");
			//
			this.getDesBloqueos(bd, lineasMemoriaWriter);
			lineasMemoriaWriter.flush();
			lineasMemoriaWriter.close();
			resultado = lineasMemoriaWriter.getLineas();
			logger.debug(resultado);
			logger.debug("FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		} 
		return resultado;
	}

	private void getDesBloqueos(AccesoBD accesoBd, Writer writerDesbloqueos) throws Exception {
		//
		ListaBlancaBean listaBlancaBean = new ListaBlancaBean();
		//
		String query = "";
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Util util = new Util();
		//
		try {
			logger.debug("INICIO");
			//
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT   tb.COD_USUARIO_SNS,  ");
			sbQuery.append("          tm.DESC_MOTIVO_TRASPASO,  ");
			sbQuery.append("          DECODE (  ");
			sbQuery.append("          ca.DESC_SERVICIO_SALUD,  ");
			sbQuery.append("          'MADRID','MADRID',  ");
			sbQuery.append("          'ASTURIAS','ASTURIAS',  ");
			sbQuery.append("          'BALEARES','BALEARES',  ");
			sbQuery.append("          'CANARIAS','CANARIAS',  ");
			sbQuery.append("          'CANTABRIA','CANTABRIA',  ");
			sbQuery.append("          'CASTILLA LA MANCHA','CASTILLA LA MANCHA',  ");
			sbQuery.append("          'CASTILLA Y LEON','CASTILLA Y LEON',  ");
			sbQuery.append("          'CATALUNYA','CATALUNYA',  ");
			sbQuery.append("          'EXTREMADURA','EXTREMADURA',  ");
			sbQuery.append("          'GALICIA','GALICIA',  ");
			sbQuery.append("          'MURCIA','MURCIA',  ");
			sbQuery.append("          'NAVARRA','NAVARRA',  ");
			sbQuery.append("          'PAIS VASCO','PAIS VASCO',  ");
			sbQuery.append("          'LA RIOJA','LA RIOJA',  ");
			sbQuery.append("          'COMUNIDAD VALENCIANA','COMUNIDAD VALENCIANA',  ");
			sbQuery.append("          'CEUTA','INGESA',  ");
			sbQuery.append("          'ANDALUCIA','ANDALUCIA',  ");
			sbQuery.append("          'ARAGON','ARAGON') AS DESC_AGENTE_SOLICITANTE,  ");
			sbQuery.append("          TO_CHAR(tb.FECHA_PETICION,'yyyy-mm-dd hh24:mi') FECHA_PETICION,  ");
			sbQuery.append("          TO_CHAR(tb.FECHA_REALIZADO, 'yyyy-mm-dd hh24:mi') FECHA_REALIZADO,  ");
			sbQuery.append("          tb.COD_OPERACION ");
			sbQuery.append(" FROM     snsalud.TRASPASOS_LISTA_BLANCA tb, snsalud.TRASPASOS_MOTIVOS tm, snsalud.desglose_agentes da, snsalud.CA_PRESTACION_SERVICIO ca ");
			sbQuery.append(" WHERE    tb.FECHA_REALIZADO IS NOT NULL  ");
			sbQuery.append(" AND  tb.FECHA_PETICION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha); // 2008-05-27
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND      tm.COD_MOTIVO_TRASPASO     = tb.COD_MOTIVO_TRASPASO ");
			sbQuery.append(" AND      da.COD_AGENTE              = tb.COD_AGENTE_SOLICITANTE ");
			sbQuery.append(" AND      da.COD_PRESTACION_SERVICIO <> 17 ");
			sbQuery.append(" AND      ca.COD_PRESTACION_SERVICIO = da.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" ORDER BY tb.FECHA_PETICION DESC ");
			query = sbQuery.toString();
			//
			logger.debug("query:      " + query);

			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hashMapRaw.get("ps");
			resultSet = (ResultSet) hashMapRaw.get("rs");
			//
			resultSet = preparedStatement.executeQuery();
			//
			writerDesbloqueos.write(listaBlancaBean.cabeceraToString() + "\n");
			writerDesbloqueos.flush();
			//
			while (resultSet.next()) {
				//
				listaBlancaBean = new ListaBlancaBean(resultSet);
				writerDesbloqueos.write(listaBlancaBean.toString() + "\n");
				writerDesbloqueos.flush();
			}
			//
			logger.debug("FIN");
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

	private String lineaToHtml(String[] columnas, String claseTr, String claseTh) {
		String linea = "<tr>";
		if (!Misc.esVacio(claseTr)) {
			linea = "<tr " + claseTr + ">";
		}
		for (int i = 0; i < columnas.length; i++) {
			if (!Misc.esVacio(claseTh)) {
				linea += "<th " + claseTh + ">";
			} else {
				linea += "<td>";
			}
			linea += columnas[i];
			if (!Misc.esVacio(claseTh)) {
				linea += "</th>";
			} else {
				linea += "</td>";
			}
		}
		linea += "</tr>";
		return linea;
	}
	
	public String obtenerMensajeBloqueos(AccesoBD bd) throws Exception {
		String mensajeBloqueos = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuffer stringBufferQuery = new StringBuffer();
			stringBufferQuery.append(" SELECT a.COD_AGENTE ,a.AGENTE, NUMERO_MENSAJES ");
			stringBufferQuery.append(" FROM   INTERCAMBIADOR.AGENTES a, (SELECT   c.COD_AGENTE, COUNT(1) NUMERO_MENSAJES ");
			stringBufferQuery.append("                    FROM     INTERCAMBIADOR.COLATEMPORAL c ");
			stringBufferQuery.append("                    WHERE    c.ID_SERVICIO=1  ");
			stringBufferQuery.append("                    GROUP BY c.COD_AGENTE ");
			//Modificacion para incorporar la mensajeria Bloqueada SOA
			stringBufferQuery.append(" 			  		  UNION					");	
			stringBufferQuery.append("					  SELECT   c.COD_AGENTE, COUNT(1) NUMERO_MENSAJES");
			stringBufferQuery.append("					  FROM INTERCAMBIADOR.MENSAJES_BLOQUEADOS C");
			stringBufferQuery.append("					  WHERE    c.ID_SERVICIO=1");
			stringBufferQuery.append("					  GROUP BY c.COD_AGENTE");
			//Fin de la modificacion para incorporar mensajeria Bloqueada SOA
			stringBufferQuery.append("                   ) c ");
			stringBufferQuery.append(" WHERE  a.COD_AGENTE = c.COD_AGENTE ");

			String query = stringBufferQuery.toString();
			HashMap hConn = bd.consultaRaw(query);
			ps = (PreparedStatement) hConn.get("ps");
			rs = (ResultSet) hConn.get("rs");

			mensajeBloqueos = "<br><table><tr><td>" + Inicializacion.mensajeEnteroBloqueos + "</td></tr></table>";
			mensajeBloqueos = mensajeBloqueos + "<table border=\"1\"><tr><td  align=\"center\"><b>COD AGENTE</b></td><td align=\"center\"><b>AGENTE</b></td><td align=\"center\"><b>NÚMERO DE MENSAJES</b></td></tr>";

			if (rs != null) {
				while (rs.next()) {
					String codAgente = rs.getString("COD_AGENTE");
					String agente = rs.getString("AGENTE");
					String numMensajes = rs.getString("NUMERO_MENSAJES");

					mensajeBloqueos = mensajeBloqueos + "<tr><td align=\"center\">&nbsp;&nbsp;" + codAgente + "</td>";
					mensajeBloqueos = mensajeBloqueos + "<td>&nbsp;&nbsp;" + agente + "</td>";
					mensajeBloqueos = mensajeBloqueos + "<td>&nbsp;&nbsp;" + numMensajes + "</td></tr>";
				}
			}
			mensajeBloqueos = mensajeBloqueos + "</table>";
		} catch (Exception e) {
			logger.error("sns.AlertaAgentes.obtenerMensajeBloqueos: Exception: " + e.getMessage(), e);
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e3) {
				rs = null;
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception e4) {
				ps = null;
			}
		}
		return mensajeBloqueos;
	}

}
