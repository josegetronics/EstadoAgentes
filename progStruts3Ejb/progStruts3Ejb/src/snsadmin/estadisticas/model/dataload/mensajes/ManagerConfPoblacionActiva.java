package snsadmin.estadisticas.model.dataload.mensajes;

import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import sns.logging.Logger;
import java.util.ArrayList;
import snsadmin.estadisticas.bean.report.PageBean;
import snsadmin.estadisticas.bean.report.TableBean;
import gasai.util.Misc;

public class ManagerConfPoblacionActiva {
  public ManagerConfPoblacionActiva() {
  }

  public Salida getConfigurationData(ReportFileBean reportFileBean) {

    Salida salida = new Salida();

    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorAgente.getConfigurationData");

    try {
      logger.debug("INICIO");

      // Rellena la informacion de las tablas
      salida = this.getPoblacionActiva(reportFileBean);
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

  private Salida getPoblacionActiva(ReportFileBean reportFileBean) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorAgente.getPoblacionActiva");

    try {
      logger.debug("INICIO");

      ArrayList pages = null;

      if (reportFileBean.getPages() != null) {
        pages = reportFileBean.getPages();
      }
      else {
        pages = new ArrayList();
      }

      int positionInBook = 0;
      //Posicion inicial de las paginas
      //if (pages.size() > 0) {
      //  positionInBook = pages.size() - 1;
      //}
      //
      logger.debug("*****");
      //
      // // // //    Obtener el PageBean      // // // // //
      PageBean pageBean = new PageBean();
      String tamPage = "1000";
      String namePage = "PoblacionActiva";
      //
      pageBean.setNombreHoja(namePage);
      pageBean.setNumFilasHojaExcel(Integer.parseInt(tamPage));
      positionInBook++;
      pageBean.setPositionBook(positionInBook);

      logger.debug("Nombre de la Hoja: " + namePage);
      logger.debug("positionInBook: " + positionInBook);
      logger.debug("namePage: " + namePage);
      // // // // // // // // // // // // // // // // // // // //

      String initialDate = reportFileBean.getInitialDate();
      String finalDate   = reportFileBean.getFinalDate();

      // // // //   Obtener la informacion de los TableBean     // // // // //
      ArrayList tables = new ArrayList();
      //
      // Se obtiene la informacion de las tablas
      salida = this.getTablaPoblacionActiva();
      if (!salida.getError()) {
        TableBean porAgentesTableBean = (TableBean) salida.getValor();
        // Se insertan el TableBean en el ArrayList
        tables.add(porAgentesTableBean);
        //
      }
      // // // // // // // // // // // // // // // // // // // /// // // // //

      // Se inserta el ArrayList con las tablas en el PageBean
      pageBean.setTables(tables);
      // Se insertan el PageBean en el ArrayList
      pages.add(pageBean);

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

  private Salida getTablaPoblacionActiva() {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorAgente.getTablaPoblacionActiva");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Poblacion Activa");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(4);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(5);

      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" SELECT   a.agente, COUNT(1) TOTAL ");
      strQuery.append(" FROM     agentes a, desglose_agentes da, mv_estadisticas_usuarios u ");
      strQuery.append(" WHERE    u.COD_ESTADO = 0 ");
      strQuery.append(" AND      u.cod_prestacion_servicio=da.cod_prestacion_servicio ");
      strQuery.append(" AND      a.cod_agente=da.cod_agente ");
      strQuery.append(" GROUP BY a.agente ");
      strQuery.append(" ORDER BY 1 ASC ");

      tableBean.setSelect(strQuery.toString());
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
