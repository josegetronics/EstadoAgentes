package sns.comun.config;

import org.apache.log4j.Logger;

import sns.comun.util.Util;


public class QueryEstadoAgentes {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	public static String generateQueryUsuariosModificados() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryUsuariosModificados: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT /*+ PARALLEL(8) */ R.COD_AGENTE_ORIGEN, ");
			sbQuery.append(" DP.NOMBRE, DP.APELLIDO1, DP.APELLIDO2,  TO_CHAR(dp.FECHA_NAC, 'dd/mm/yyyy') FECHA_NAC,  DP.COD_SEXO SEXO, ");
			sbQuery.append(" DP.DNI_NIE,  DC.NAF, DC.NAF_TITULAR, H.* ");
			sbQuery.append(" FROM  snsalud.historico_mod_usuarios h, snsalud.registro_operaciones r, ");
			sbQuery.append(" SNSALUD.DATOS_PERSONALES DP, SNSALUD.DATOS_COBERTURA DC ");
			sbQuery.append(" WHERE H.COD_USUARIO_SNS = DP.COD_USUARIO_SNS AND H.COD_USUARIO_SNS =  DC.COD_USUARIO_SNS ");
			sbQuery.append(" AND H.COD_TIPO_MODIFICACION = 0 ");
			sbQuery.append(" AND H.FECHA_APLICACION_SOLICITUD between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND  (   h.campos_afectados LIKE '%nombre<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%sexo%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%fecha_nac%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%dni<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf_titular%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf<%' ");
			//sbQuery.append("       OR h.campos_afectados LIKE '%pasaporte%' ");
			sbQuery.append("      ) ");
			sbQuery.append(" AND R.COD_OPERACION_MAESTRA = H.COD_OPERACION ");
			sbQuery.append(" AND R.FECHA_OPERACION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND R.COD_TIPO_OPERACION in ('A003','A006','A007') ");
			//Filtramos para que quite las modificaciones realizadas desde el Ministerio (codigo agente 99)
			sbQuery.append(" AND R.COD_AGENTE_ORIGEN not in ('99') ");
			sbQuery.append(" AND not exists (Select 'X' from snsalud.registro_operaciones r2 ");
			sbQuery.append(" where R2.COD_OPERACION_MAESTRA = R.COD_OPERACION_MAESTRA and R2.COD_TIPO_OPERACION = 'A005') ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryUsuariosModificados: query: " + query);
			logger.debug("generateQueryUsuariosModificados: FIN");
		} catch (Exception e) {
			logger.error("generateQueryUsuariosModificados: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	
	public static String generateQueryTraspasosBloqueados() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryTraspasosBloqueados: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT /*+ PARALLEL(8) */ CA.COD_CA_ISO AS CA_ACTUAL, TN.COD_AGENTE_SOLICITANTE AS CA_ORIGEN_XML, TN.COD_USUARIO_SNS, TBC.NOMBRE_CAMPO, TBC.VALOR_ANTERIOR, TBC.VALOR_NUEVO ");
			sbQuery.append(" FROM snsalud.TRASPASOS_LISTA_NEGRA tn, snsalud.traspasos_bloqueados tb, snsalud.usuarios u, snsalud.ca_prestacion_servicio cp, ");
			sbQuery.append(" snsalud.comunidades_autonomas ca, snsalud.traspasos_bloqueados_campos tbc ");
			sbQuery.append(" WHERE TN.ACTIVO = 1 ");
			sbQuery.append(" AND TN.FECHA_PETICION between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND TB.COD_USUARIO_SNS = TN.COD_USUARIO_SNS ");
			sbQuery.append(" AND to_date(TB.FECHA_OPERACION, 'dd/mm/yyyy hh24:mi') = to_date(TN.FECHA_PETICION, 'dd/mm/yyyy hh24:mi') ");
			sbQuery.append(" AND TBC.COD_OPERACION = TB.COD_OPERACION ");
			sbQuery.append(" AND U.COD_USUARIO_SNS = TB.COD_USUARIO_SNS  ");
			sbQuery.append(" AND CP.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" AND CA.COD_COMUNIDAD = CP.COD_COMUNIDAD  ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryTraspasosBloqueados: query: " + query);
			logger.debug("generateQueryTraspasosBloqueados: FIN");
		} catch (Exception e) {
			logger.error("generateQueryTraspasosBloqueados: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	public static String generateQueryTraspasosDesbloqueados() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryTraspasosDesbloqueados: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" select /*+ PARALLEL(8) */ CA.COD_CA_ISO AS CA_ACTUAL, CA2.COD_CA_ISO AS CA_ANTERIOR, TB.COD_USUARIO_SNS, ");
			sbQuery.append(" TBC.NOMBRE_CAMPO, TBC.VALOR_ANTERIOR, TBC.VALOR_NUEVO ");
			sbQuery.append(" from snsalud.traspasos_lista_blanca lb, snsalud.traspasos_lista_negra ln, snsalud.traspasos_bloqueados tb, snsalud.usuarios u, snsalud.ca_prestacion_servicio cp, snsalud.comunidades_autonomas ca, ");
			sbQuery.append(" snsalud.desglose_agentes da, snsalud.ca_prestacion_servicio cp2, snsalud.comunidades_autonomas ca2, snsalud.traspasos_bloqueados_campos tbc ");
			sbQuery.append(" where LB.FECHA_REALIZADO between to_date('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" and LN.COD_USUARIO_SNS = LB.COD_USUARIO_SNS  ");
			sbQuery.append(" and LN.ACTIVO = 0 ");
			sbQuery.append(" and TB.COD_USUARIO_SNS = LN.COD_USUARIO_SNS ");
			sbQuery.append(" and to_date(TB.FECHA_OPERACION, 'dd/mm/yyyy hh24:mi') = to_date(LN.FECHA_PETICION, 'dd/mm/yyyy hh24:mi') ");
			sbQuery.append(" and TB.BLOQUEO_ACTIVO = 1 ");
			sbQuery.append(" and U.COD_USUARIO_SNS = TB.COD_USUARIO_SNS ");
			sbQuery.append(" and CP.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" and CA.COD_COMUNIDAD = CP.COD_COMUNIDAD ");
			sbQuery.append(" and DA.COD_AGENTE = TB.COD_AGENTE_ORIGEN ");
			sbQuery.append(" and CP2.COD_PRESTACION_SERVICIO = DA.COD_PRESTACION_SERVICIO ");
			sbQuery.append(" and CA2.COD_COMUNIDAD = CP2.COD_COMUNIDAD ");
			sbQuery.append(" and TBC.COD_OPERACION = TB.COD_OPERACION ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryTraspasosDesbloqueados: query: " + query);
			logger.debug("generateQueryTraspasosDesbloqueados: FIN");
		} catch (Exception e) {
			logger.error("generateQueryTraspasosDesbloqueados: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	
	public static String generateQueryCambiosTraspasos() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryCambiosTraspasos: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT /*+ PARALLEL(8) */ replace(replace(h2.valor_solicitud,'<valor>',''),'</valor>','') ca_actual,replace(replace(h2.valor_anterior,'<valor>',''),'</valor>','') ca_anterior, h.*, ");
			sbQuery.append(" dp.NOMBRE, dp.APELLIDO1, dp.APELLIDO2, dp.COD_SEXO, TO_CHAR(dp.FECHA_NAC, 'dd/mm/yyyy') FECHA ");
			sbQuery.append(" FROM  snsalud.registro_operaciones r, snsalud.historico_mod_usuarios h , snsalud.historico_mod_usuarios h2, snsalud.datos_personales dp ");
			sbQuery.append(" WHERE r.FECHA_OPERACION BETWEEN TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			// sbQuery.append(" WHERE r.FECHA_OPERACION BETWEEN TO_DATE('2008-06-12 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('2008-06-12 23:59','YYYY-MM-DD HH24:MI') ");
			sbQuery.append(" AND   h.COD_USUARIO_SNS    = dp.COD_USUARIO_SNS ");			
			sbQuery.append(" AND   r.COD_TIPO_OPERACION = 'A005' ");
			sbQuery.append(" AND   h.COD_OPERACION      = r.COD_OPERACION_MAESTRA ");
			sbQuery.append(" AND   h2.COD_OPERACION     = r.COD_OPERACION ");
			//Filtramos para que quite los traspasos de Muface ya que muface no tiene traspasos
			sbQuery.append(" AND replace(replace(h2.valor_solicitud,'<valor>',''),'</valor>','') <> '21' ");
			sbQuery.append(" AND  (   h.campos_afectados LIKE '%nombre<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%sexo%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%fecha_nac%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%dni<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf_titular%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf<%' ");
			//sbQuery.append("       OR h.campos_afectados LIKE '%pasaporte%' ");
			sbQuery.append("      ) ");
			// sbQuery.append(" AND ROWNUM < 20  ");
			sbQuery.append(" ORDER BY 1 ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryCambiosTraspasos: query: " + query);
			logger.debug("generateQueryCambiosTraspasos: FIN");
		} catch (Exception e) {
			logger.error("generateQueryCambiosTraspasos: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	public static String generateQueryCambiosTraspasosSinDesbloqueados() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryCambiosTraspasosSinDesbloqueados: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT /*+ PARALLEL(8) */ replace(replace(h2.valor_solicitud,'<valor>',''),'</valor>','') ca_actual,replace(replace(h2.valor_anterior,'<valor>',''),'</valor>','') ca_anterior, h.*, ");
			sbQuery.append(" dp.NOMBRE, dp.APELLIDO1, dp.APELLIDO2, dp.COD_SEXO SEXO, TO_CHAR(dp.FECHA_NAC, 'dd/mm/yyyy') FECHA_NAC, dp.DNI_NIE, dc.NAF, dc.NAF_TITULAR ");
			sbQuery.append(" FROM  snsalud.registro_operaciones r, snsalud.historico_mod_usuarios h , snsalud.historico_mod_usuarios h2, snsalud.datos_personales dp, snsalud.datos_cobertura dc");
			sbQuery.append(" WHERE r.FECHA_OPERACION BETWEEN TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND   h.COD_USUARIO_SNS    = dp.COD_USUARIO_SNS ");
			sbQuery.append(" AND   h.COD_USUARIO_SNS    = dc.COD_USUARIO_SNS ");
			sbQuery.append(" AND   r.COD_TIPO_OPERACION = 'A005' ");
			sbQuery.append(" AND   h.COD_OPERACION      = r.COD_OPERACION_MAESTRA ");
			sbQuery.append(" AND   h2.COD_OPERACION     = r.COD_OPERACION ");
			//Filtramos para que quite los traspasos de Muface ya que muface no tiene traspasos
			sbQuery.append(" AND replace(replace(h2.valor_solicitud,'<valor>',''),'</valor>','') <> '21' ");
			sbQuery.append(" AND  (   h.campos_afectados LIKE '%nombre<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%apel%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%sexo%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%fecha_nac%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%dni<%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf_titular%' ");
			sbQuery.append("       OR h.campos_afectados LIKE '%naf<%' ");
			//sbQuery.append("       OR h.campos_afectados LIKE '%pasaporte%' ");
			sbQuery.append("      ) ");
			sbQuery.append(" AND NOT EXISTS (SELECT 'x' ");
			sbQuery.append(" FROM snsalud.traspasos_lista_blanca t ");
			sbQuery.append(" WHERE t.cod_usuario_sns = H.COD_USUARIO_SNS ");
			sbQuery.append(" AND T.FECHA_REALIZADO is not null ");
			sbQuery.append(" AND  t.FECHA_PETICION BETWEEN TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha); // 2008-05-27
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI') )");
			sbQuery.append(" ORDER BY 1 ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryCambiosTraspasosSinDesbloqueados: query: " + query);
			logger.debug("generateQueryCambiosTraspasosSinDesbloqueados: FIN");
		} catch (Exception e) {
			logger.error("generateQueryCambiosTraspasosSinDesbloqueados: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	public static String generateQueryCambiosTraspasosDesbloqueados() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryCambiosTraspasosDesbloqueados: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT /*+ PARALLEL(8) */ replace(replace(h2.valor_solicitud,'<valor>',''),'</valor>','') ca_actual,replace(replace(h2.valor_anterior,'<valor>',''),'</valor>','') ca_anterior, h.* ");
			sbQuery.append(" FROM  snsalud.TRASPASOS_LISTA_BLANCA lb, snsalud.historico_mod_usuarios h, snsalud.historico_mod_usuarios h2,snsalud.registro_operaciones r ");
			sbQuery.append(" WHERE lb.FECHA_REALIZADO BETWEEN TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			// sbQuery.append(" WHERE r.FECHA_OPERACION BETWEEN TO_DATE('2008-06-12 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('2008-06-12 23:59','YYYY-MM-DD HH24:MI') ");
			sbQuery.append(" AND H.COD_USUARIO_SNS = LB.COD_USUARIO_SNS ");
			sbQuery.append(" AND H.COD_OPERACION = LB.COD_OPERACION ");
			sbQuery.append(" AND r.COD_OPERACION_MAESTRA = h.COD_OPERACION ");
			sbQuery.append(" AND r.COD_TIPO_OPERACION = 'A005' ");
			sbQuery.append(" AND h2.COD_OPERACION = r.COD_OPERACION ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryCambiosTraspasosDesbloqueados: query: " + query);
			logger.debug("generateQueryCambiosTraspasosDesbloqueados: FIN");
		} catch (Exception e) {
			logger.error("generateQueryCambiosTraspasosDesbloqueados: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	
	
	
	public static String generateQueryTotalTraspasos() throws Exception {

		String query = "";

		Util util = new Util();

		try {
			logger.debug("generateQueryTotalTraspasos: INICIO");
			
			// SE OBTIENE LA FECHA PARA LA QUERY
			String fecha = util.generateFecha("-");
			StringBuffer sbQuery = new StringBuffer();
			
			// QUERY
			sbQuery.append(" SELECT   /*+ PARALLEL(8) */ ca.COD_CA_ISO, count(1) CONTADOR  ");
			sbQuery.append(" FROM     snsalud.registro_operaciones r, snsalud.registro_operaciones r2, snsalud.desglose_agentes da, snsalud.ca_prestacion_servicio cap, snsalud.comunidades_autonomas ca ");
			sbQuery.append(" WHERE r.FECHA_OPERACION BETWEEN TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 00:00','YYYY-MM-DD HH24:MI') AND TO_DATE('");
			sbQuery.append(fecha);
			sbQuery.append(" 23:59','YYYY-MM-DD HH24:MI')");
			sbQuery.append(" AND      r.COD_TIPO_OPERACION        = 'A005' ");
			sbQuery.append(" AND      r2.COD_OPERACION            = r.COD_OPERACION_MAESTRA ");
			sbQuery.append(" AND      da.COD_AGENTE               = r2.COD_AGENTE_ORIGEN ");
			sbQuery.append(" AND      cap.COD_PRESTACION_SERVICIO = da.COD_PRESTACION_SERVICIO  ");
			sbQuery.append(" AND      ca.COD_COMUNIDAD            = cap.COD_COMUNIDAD ");
			sbQuery.append(" GROUP BY ca.COD_CA_ISO ");
			
			query = sbQuery.toString();
			logger.debug("generateQueryTotalTraspasos: query: " + query);
			logger.debug("generateQueryTotalTraspasos: FIN");
		} catch (Exception e) {
			logger.error("generateQueryTotalTraspasos: Exception: " + e.getMessage(), e);
			throw e;
		}
		return query;
	}
	

}

