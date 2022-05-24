package snsadmin.estadisticas.model.dataload.bloqueos;

import sns.logging.Logger;
import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import java.util.ArrayList;
import snsadmin.estadisticas.bean.report.PageBean;
import snsadmin.estadisticas.bean.report.TableBean;
import sns.config.Inicializacion;
import java.util.HashMap;
import sns.model.AgenteBean;


/**
 *
 *
 *
 */
public class ManagerAgentes {


  public ManagerAgentes() {
  }


  /**
   * Para cada una de los agentes de Inicializacion.LIST_COMUNIDADES, se genera la informacion
   * para obtener los PageBean y TableBean, para los bloqueos por agente y por dia,
   * toda esta informacion se añade al ReportFileBean.
   *
   * @param reportFileBean ReportFileBean Para añadir  toda la informacion a recopilar
   * @return Salida
   */
  public Salida agentesToReportFileBean(ReportFileBean reportFileBean) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.bloqueos.ManagerAgentes.agentesToReportFileBean");

    try {
      logger.debug("INICIO");

      ArrayList pages = null;

      if(reportFileBean.getPages() != null){
        pages = reportFileBean.getPages();
      }
      else{
        pages = new ArrayList ();
      }
      //
      ArrayList agentesArray = Inicializacion.ADMIN_LIST_COMUNIDADES;
      //
      int numAgentes = agentesArray.size();
      //if (agentesArray.size() > Inicializacion.NUM_MAX_COMUNIDADES) {
      //  numAgentes = Inicializacion.NUM_MAX_COMUNIDADES;
      //}

      int positionInBook = 0;
      //Posicion inicial de las paginas
      if(pages.size()>0){
        positionInBook = pages.size()-1;
      }
      //
      logger.debug("*****");
      //
      for (int i = 0; i < numAgentes ; i++) {
        AgenteBean agenteBean = (AgenteBean) agentesArray.get(i);

        // // // //   Obtener la informacion de los PageBean      // // // // //
        PageBean pageBean = new PageBean();
        int numTables = 1;
        String tamPage = "1000";
        positionInBook ++;
        String namePage = agenteBean.getDescripcion2();
        //
        pageBean.setNumTables(numTables);
        pageBean.setNombreHoja(namePage);
        logger.debug("Nombre de la Hoja: " + namePage);
        pageBean.setNumFilasHojaExcel(Integer.parseInt(tamPage));
        pageBean.setPositionBook(positionInBook);
        //logger.debug("positionInBook: " + positionInBook);
        //logger.debug("namePage: " + namePag);
        // // // // // // // // // // // // // // // // // // // /// // // // //

        // // // //   Obtener la informacion de los TableBean     // // // // //
        ArrayList tables = new ArrayList();
        TableBean tableBean = new TableBean();
        // Consulta
        StringBuffer str = new StringBuffer();
        str.append(" SELECT   r.COD_AGENTE, TO_CHAR(FECHA,'dd/mm/yyyy') as fecha_ag, sum(contador_conn_timeout) conn_timeout, sum(contador_read_timeout) read_timeout, sum(contador_errores_http) errores_http, sum(contador_otros) otros ");
        str.append(" FROM     CONTROL_BLOQUEO_REPORT_DIARIO r, agentes a ");
        str.append(" WHERE    r.fecha between to_date(?, 'dd/mm/yyyy') AND to_date(?, 'dd/mm/yyyy') ");
        str.append(" AND      r.COD_AGENTE = a.COD_AGENTE ");
        str.append(" AND      r.ID_SERVICIO = ? ");
        str.append(" AND      r.COD_AGENTE  = ? ");
        str.append(" GROUP BY r.COD_AGENTE, TO_CHAR(FECHA,'dd/mm/yyyy') ");
        str.append(" ORDER BY r.COD_AGENTE, fecha_ag ");

        tableBean.setSelect(str.toString());
        // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
        HashMap map = new HashMap();
        map.put("1", reportFileBean.getInitialDate());
        map.put("2", reportFileBean.getFinalDate());
        map.put("3", reportFileBean.getReportIdServicio());
        map.put("4", new Integer(agenteBean.getCodAgente()));
        tableBean.setParametrosSelect(map);

        tableBean.setNumFilaCabecera(1);
        tableBean.setNumFilaInicial(2);
        //tableBean.setPosicionHojaExcell(1);

        // Se indican las funciones para contar las estadisticas
        tableBean.setFunctions("SUM;SUM;SUM;SUM");
        tableBean.setPositionFunctions("2;3;4;5");

        // Se insertan el TableBean en el ArrayList
        tables.add(tableBean);
        // // // // // // // // // // // // // // // // // // // /// // // // //
        // Se inserta el ArrayList con las tablas en el PageBean
        pageBean.setTables(tables);
        // Se insertan el PageBean en el ArrayList
        pages.add(pageBean);
      }
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
    finally {
    }
    return salida;
  }


}
