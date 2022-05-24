package snsadmin.estadisticas.model.dataload.bloqueos;


import sns.logging.Logger;
import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import java.util.ArrayList;
import snsadmin.estadisticas.bean.report.PageBean;
import snsadmin.estadisticas.bean.report.TableBean;
import java.util.HashMap;


public class ManagerConfBloqueos {


  public ManagerConfBloqueos() {
  }


  public Salida getConfigurationData(ReportFileBean reportFileBean) {

    Salida salida = new Salida ();

    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfigurationBloqueos.getConfigurationData");

    try {
      logger.debug("INICIO");

      // Rellena la informacion de las tablas
      salida = this.getBloqueos(reportFileBean);
      if(!salida.getError()) {
        salida.setValor(reportFileBean);
      }

      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getBloqueos(ReportFileBean reportFileBean) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getBloqueos");

    try {
      logger.debug("INICIO");

      String initialDate       = reportFileBean.getInitialDate();
      String finalDate         = reportFileBean.getFinalDate();
      String reportIdServicio  = reportFileBean.getReportIdServicio();

      ArrayList pages = null;

      if(reportFileBean.getPages() != null){
        pages = reportFileBean.getPages();
      }
      else{
        pages = new ArrayList ();
      }

      int positionInBook = 0;
      //Posicion inicial de las paginas
      if(pages.size()>0){
        positionInBook = pages.size()-1;
      }
      //
      logger.debug("*****");
      //

      // // // //    Obtener el PageBean      // // // // //
      //
      // // // // // // // // // // // // // // // // // // // // // // // // //
      // 1ª Pagina
      PageBean bloqueosPageBean = new PageBean();
      String tamPage  = "5000";
      String namePage = "Primera";
      //
      bloqueosPageBean.setNombreHoja(namePage);
      bloqueosPageBean.setNumFilasHojaExcel(Integer.parseInt(tamPage));
      bloqueosPageBean.setPositionBook(positionInBook);

      logger.debug("Nombre de la Hoja: " + namePage);
      logger.debug("positionInBook: " + positionInBook);


      // // // //   Obtener la informacion de los TableBean     // // // // //
      ArrayList tables = new ArrayList();
      //
      // Se obtiene la informacion de las tablas
      salida = this.getTablaBloqueos(initialDate, finalDate, reportIdServicio);
      if( !salida.getError() ) {
        TableBean bloqueosTableBean = (TableBean)salida.getValor();
        // Se insertan el TableBean en el ArrayList
        tables.add(bloqueosTableBean);
        //
        salida = this.getTablaServidoresAutonomicos(initialDate, finalDate, reportIdServicio);
        if (!salida.getError()) {
          TableBean servidoresAutonomicosTableBean = (TableBean) salida.getValor();
          // Se insertan el TableBean en el ArrayList
          tables.add(servidoresAutonomicosTableBean);
          //
          salida = this.getTablaBloqueoTotal(initialDate, finalDate, reportIdServicio);
          if (!salida.getError()) {
            TableBean bloqueoTotalTableBean = (TableBean) salida.getValor();
            // Se insertan el TableBean en el ArrayList
            tables.add(bloqueoTotalTableBean);
            // YA NO APARECEN LOS ERRORES HTTP
            //salida = this.getTableErroresHttp(initialDate, finalDate, reportIdServicio);
            //if (!salida.getError()) {
            //  TableBean errorresHttpTableBean = (TableBean) salida.getValor();
            //  // Se insertan el TableBean en el ArrayList
            //  tables.add(errorresHttpTableBean);
            //}
          }
        }
      }
      // Se inserta el ArrayList con las tablas en el PageBean
      bloqueosPageBean.setTables(tables);
      // Se insertan el PageBean en el ArrayList
      pages.add(bloqueosPageBean);
      // // // // // // // // // // // // // // // // // // // // // // // // //




      // // // // // // // // // // // // // // // // // // // // // // // // //
      // 2ª Pagina
      positionInBook++;
      PageBean rendimientoPageBean = new PageBean();
      String rendimientoTamPage  = "1000";
      String rendimientoNamePage = "Rendimiento Sincrono";
      //
      rendimientoPageBean.setNombreHoja(rendimientoNamePage);
      rendimientoPageBean.setNumFilasHojaExcel(Integer.parseInt(rendimientoTamPage));
      rendimientoPageBean.setPositionBook(positionInBook);

      logger.debug("Nombre de la Hoja: " + rendimientoNamePage);
      logger.debug("positionInBook: " + positionInBook);
      // // // // // // // // // // // // // // // // // // // //

      // // // //   Obtener la informacion de los TableBean     // // // // //
      ArrayList rendimientoTables = new ArrayList();
      //
      // Se obtiene la informacion de las tablas
      salida = this.getTableRendimientos(initialDate, finalDate, reportIdServicio);
      if( !salida.getError() ) {
        TableBean rendimientoTableBean = (TableBean) salida.getValor();
        // Se insertan el TableBean en el ArrayList
        rendimientoTables.add(rendimientoTableBean);
        //
      }
      // Se inserta el ArrayList con las tablas en el PageBean
      rendimientoPageBean.setTables(rendimientoTables);
      // Se insertan el PageBean en el ArrayList
      pages.add(rendimientoPageBean);
      // // // // // // // // // // // // // // // // // // // // // // // // //

      logger.debug("0000000010111010*****************************************");

      // // // // // // // // // // // // // // // // // // // // // // // // //
      // 3ª Pagina
      positionInBook++;
      PageBean agenteTipo3PageBean = new PageBean();
      String agenteTipo3TamPage = "1000";
      String agenteTipo3NamePage = "AgenteYTipo3";
      //
      agenteTipo3PageBean.setNombreHoja(agenteTipo3NamePage);
      agenteTipo3PageBean.setNumFilasHojaExcel(Integer.parseInt(agenteTipo3TamPage));
      agenteTipo3PageBean.setPositionBook(positionInBook);

      logger.debug("Nombre de la Hoja: " + agenteTipo3NamePage);
      logger.debug("positionInBook: " + positionInBook);
      // // // // // // // // // // // // // // // // // // // //

      // // // //   Obtener la informacion de los TableBean     // // // // //
      ArrayList agenteTipo3Tables = new ArrayList();
      if (!salida.getError()) {
        //
        salida = this.getTablaC00x(initialDate, finalDate);
        if(!salida.getError()){
          TableBean c00xTableBean = (TableBean) salida.getValor();
          //Se inserta el TableBean en el ArrayList
          agenteTipo3Tables.add(c00xTableBean);
        }
      }
      // Se inserta el ArrayList con las tablas en el PageBean
      agenteTipo3PageBean.setTables(agenteTipo3Tables);
      // Se insertan el PageBean en el ArrayList
      pages.add(agenteTipo3PageBean);
      // // // // // // // // // // // // // // // // // // // // // // // // //

      logger.debug("*****");
      //
      // Se insertan el ArrayList en el ReportFileBean
      reportFileBean.setPages(pages);

      logger.debug("FIN");
    }
    catch (Exception e) {
      e.printStackTrace();
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTablaBloqueos(String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTablaBloqueos");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Bloqueo total");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(4);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(5);

      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT r.COD_AGENTE, a.AGENTE, count(r.COD_AGENTE) numero ");
      strQuery.append(" FROM intercambiador.CONTROL_BLOQUEO_REPORTING r, intercambiador.agentes a ");
      strQuery.append(" WHERE fecha between to_date(?, 'dd/mm/yyyy') and to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND r.COD_AGENTE = a.COD_AGENTE ");
      strQuery.append(" AND r.ID_SERVICIO = ? ");
      strQuery.append(" GROUP BY r.COD_AGENTE, a.AGENTE ");
      strQuery.append(" ORDER BY r.COD_AGENTE ");
      tableBean.setSelect(strQuery.toString());

      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);

      // Se indican las funciones para contar las estadisticas
      tableBean.setFunctions("SUM");
      tableBean.setPositionFunctions("2");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTablaServidoresAutonomicos(String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTablaServidoresAutonomicos");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Funcionamiento de los servidores autonómicos (actividad saliente)");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(10);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(11);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT   r.COD_AGENTE, a.AGENTE, sum(contador_conn_timeout), sum(contador_read_timeout), sum(contador_errores_http), sum(contador_otros) ");
      strQuery.append(" FROM     intercambiador.CONTROL_BLOQUEO_REPORTING r, intercambiador.agentes a ");
      strQuery.append(" WHERE    fecha between to_date (?, 'dd/mm/yyyy') and to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND      r.COD_AGENTE = a.COD_AGENTE ");
      strQuery.append(" AND      r.ID_SERVICIO = ? ");
      strQuery.append(" GROUP BY r.COD_AGENTE, a.AGENTE ");
      strQuery.append(" ORDER BY r.COD_AGENTE ");
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);
      //
      // Se indican las funciones para contar las estadisticas
      tableBean.setFunctions("SUM;SUM;SUM;SUM");
      tableBean.setPositionFunctions("2;3;4;5");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTablaBloqueoTotal(String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTablaBloqueoTotal");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Detalle bloqueo total");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(10);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(11);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT id, r.COD_AGENTE, a.AGENTE, TO_CHAR(fecha, 'dd/mm/yyyy') fecha, contador_conn_timeout, contador_read_timeout ,contador_errores_http, contador_otros ");
      strQuery.append(" FROM intercambiador.CONTROL_BLOQUEO_REPORTING r, intercambiador.agentes a  ");
      strQuery.append(" WHERE fecha between to_date(?, 'dd/mm/yyyy') and to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND r.COD_AGENTE = a.COD_AGENTE  ");
      strQuery.append(" AND r.ID_SERVICIO = ? ");
      strQuery.append(" ORDER BY r.COD_AGENTE, id ");
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);
      //
      // Se indican las funciones para contar las estadisticas
      tableBean.setFunctions("SUM;SUM;SUM;SUM");
      tableBean.setPositionFunctions("4;5;6;7");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTableErroresHttp(String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTableErroresHttp");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Detalle de los errores  HTTP");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(10);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(11);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT    r.id, ce.error,count(*) ");
      strQuery.append(" FROM      intercambiador.CONTROL_BLOQUEO_REPORTING r, intercambiador.CONTROL_BLOQUEO_REPORTING_ERR ce ");
      strQuery.append(" WHERE     r.fecha between to_date(? ,'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND       r.contador_errores_http != 0 ");
      strQuery.append(" AND       ce.ID = r.ID ");
      strQuery.append(" AND       ce.cod_tipo_error=3  ");
      strQuery.append(" AND       r.ID_SERVICIO = ? ");
      strQuery.append(" GROUP BY  r.id, ce.error ");
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTableRendimientos (String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTableRendimientos");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Utilización y Rendimiento del sistema síncrono");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(4);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(5);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT   a.agente, rs.COD_TIPO_OPERACION, count(*) total, min(rs.tiempo) respMin, max(rs.tiempo) respMax,round(avg(rs.tiempo)) respMedia ");
      strQuery.append(" FROM     intercambiador.RENDIMIENTO_SERVICIOS rs, intercambiador.AGENTES a ");
      strQuery.append(" WHERE    rs.fecha between to_date(?, 'dd/mm/yyyy') and to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND      rs.cod_agente = a.COD_AGENTE ");
      strQuery.append(" GROUP BY rs.cod_agente, a.agente, rs.COD_TIPO_OPERACION ");
      strQuery.append(" order by rs.cod_agente, a.agente ");
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);
      //
      // Se indican las funciones para contar las estadisticas
      tableBean.setFunctions("SUM;AVERAGE;AVERAGE;AVERAGE");
      tableBean.setPositionFunctions("2;3;4;5");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }



  private Salida getTableRendimientosTarjeta (String initialDate, String finalDate, String reportIdServicio) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTableRendimientosTarjeta");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Utilización y Rendimiento del sistema síncrono");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(4);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(5);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT   a.agente, rs.id, count(*) total, min(rs.tiempo) respMin, max(rs.tiempo) respMax,round(avg(rs.tiempo)) respMedia ");
      strQuery.append(" FROM     intercambiador.RENDIMIENTO_STATS rs, intercambiador.AGENTES a ");
      strQuery.append(" WHERE    rs.fecha between to_date(?, 'dd/mm/yyyy') and to_date(?, 'dd/mm/yyyy') ");
      strQuery.append(" AND      rs.cod_agente = a.COD_AGENTE ");
      strQuery.append(" GROUP BY rs.cod_agente, a.agente, rs.id ");
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", reportIdServicio);
      tableBean.setParametrosSelect(map);
      //
      // Se indican las funciones para contar las estadisticas
      tableBean.setFunctions("SUM;AVERAGE;AVERAGE;AVERAGE");
      tableBean.setPositionFunctions("2;3;4;5");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }


  private Salida getTablaC00x(String initialDate, String finalDate) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos.getTablaC00x");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(6);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(7);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT   s.cod_agente,a.AGENTE, s.COD_TIPO_OPERACION, CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'pc=')+4, ((dbms_lob.instr(mensaje_xml,'pc=')+9) - (dbms_lob.instr(mensaje_xml,'pc=')+4)))) AS VARCHAR2(6)) as pc, count(*) ");
      strQuery.append(" FROM     intercambiador.RENDIMIENTO_SERVICIOS s, intercambiador.agentes a, intercambiador.REGISTRO_XML_ENTRADA rxe ");
      strQuery.append(" WHERE    s.fecha BETWEEN TO_DATE(?, 'dd-mm-yyyy') AND TO_DATE(?, 'dd-mm-yyyy')  ");
      strQuery.append(" AND      s.COD_AGENTE = a.COD_AGENTE ");
      strQuery.append(" AND      s.COD_TIPO_OPERACION like 'C001%' ");
      strQuery.append(" AND      dbms_lob.instr(rxe.MENSAJE_XML, 'pc=')>0 ");
      strQuery.append(" AND      s.COD_XML_ENTRADA = rxe.COD_XML_ENTRADA ");
      strQuery.append(" GROUP BY s.cod_agente,a.AGENTE, s.COD_TIPO_OPERACION, CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'pc=')+4, ((dbms_lob.instr(mensaje_xml,'pc=')+9) - (dbms_lob.instr(mensaje_xml,'pc=')+4)))) AS VARCHAR2(6)) ");
      strQuery.append(" ORDER BY 1, 2, 3, 4 ");
      //
      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      tableBean.setParametrosSelect(map);
      //
      // Se indican las funciones para contar las estadisticas
      //tableBean.setFunctions("SUM;SUM;SUM;SUM");
      //tableBean.setPositionFunctions("2;3;4;5");
      //
      salida.setValor(tableBean);
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }



}
















