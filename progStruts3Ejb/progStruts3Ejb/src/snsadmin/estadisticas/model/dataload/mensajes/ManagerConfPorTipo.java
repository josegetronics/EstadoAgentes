package snsadmin.estadisticas.model.dataload.mensajes;

import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import sns.logging.Logger;
import java.util.ArrayList;
import snsadmin.estadisticas.bean.report.PageBean;
import snsadmin.estadisticas.bean.report.TableBean;
import java.util.HashMap;


public class ManagerConfPorTipo {


  public ManagerConfPorTipo() {
  }


  public Salida getConfigurationData(ReportFileBean reportFileBean) {

    Salida salida = new Salida ();

    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorTipo.getConfigurationData");

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
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorTipo.getBloqueos");

    try {
      logger.debug("INICIO");

      ArrayList pages = null;

      if(reportFileBean.getPages() != null){
        pages = reportFileBean.getPages();
      }
      else{
        pages = new ArrayList ();
      }

      int positionInBook = 1;
      //Posicion inicial de las paginas
      //if(pages.size()>0){
      //  positionInBook = pages.size()-1;
      //}
      //
      logger.debug("*****");
      //
      // // // //    Obtener el PageBean      // // // // //
      PageBean pageBean = new PageBean();
      String tamPage  = "1000";
      String namePage = "PorTipo";
      //
      pageBean.setNombreHoja(namePage);
      pageBean.setNumFilasHojaExcel(Integer.parseInt(tamPage));
      positionInBook ++;
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
      salida = this.getTablaPorTipo(initialDate, finalDate);
      if( !salida.getError() ) {
        TableBean porTipoTableBean = (TableBean)salida.getValor();
        // Se insertan el TableBean en el ArrayList
        tables.add(porTipoTableBean);
        //
        salida = this.getTablaN00x(initialDate, finalDate);
        if (!salida.getError()) {
          TableBean servidoresAutonomicosTableBean = (TableBean) salida.getValor();
          // Se insertan el TableBean en el ArrayList
          tables.add(servidoresAutonomicosTableBean);
          //
        }
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


  private Salida getTablaPorTipo(String initialDate, String finalDate) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorTipo.getTablaPorTipo");

    try {
      logger.debug("INICIO");
      TableBean tableBean = new TableBean();
      //
      // Titulo para la tabla
      tableBean.setTituloTabla("Por Tipo");
      // cabecera
      tableBean.setCabecera("");
      tableBean.setTipo("");
      // Numero de la fila de la cabecera
      tableBean.setNumFilaCabecera(4);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(5);

      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" select count(1),r.cod_tipo_operacion,'' MOTIVO_BAJA,'entrada' ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r  ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1  ");
      strQuery.append(" and r.COD_TIPO_OPERACION in ('A003', 'A004','A006','A007','A008') ");
      strQuery.append(" and exists (select 'x' ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r2  ");
      strQuery.append(" where r2.COD_OPERACION_MAESTRA=r.COD_OPERACION ");
      strQuery.append(" and r2.COD_OPERACION!=r.COD_OPERACION  ");
      strQuery.append(" and r2.COD_TIPO_OPERACION in ('N003','N005','N006','N009')) ");
      strQuery.append(" group by r.cod_tipo_operacion ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1), r.cod_tipo_operacion, CAST(TRIM(SUBSTR (r.mensaje_xml, dbms_lob.instr(r.mensaje_xml,'<motivo_baja>') + 13, ((dbms_lob.instr(r.mensaje_xml,'</motivo_baja>')) - (dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13)))) AS VARCHAR2(30)), 'entrada' ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r, agentes a ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION in ('A002') ");
      strQuery.append(" and exists ( ");
      strQuery.append(" select 'x' ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r2 ");
      strQuery.append(" where r2.COD_OPERACION_MAESTRA=r.COD_OPERACION ");
      strQuery.append(" and r2.COD_OPERACION!=r.COD_OPERACION ");
      strQuery.append(" and r2.COD_TIPO_OPERACION in ('N005','N009')) ");
      strQuery.append(" and a.cod_agente=r.cod_agente_origen ");
      strQuery.append(" group by r.cod_tipo_operacion, CAST(TRIM(SUBSTR (r.mensaje_xml, dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13, ((dbms_lob.instr(r.mensaje_xml,'</motivo_baja>')) - (dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13)))) AS VARCHAR2(30)) ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),decode(r.cod_tipo_operacion,'A002','A002-Mju',r.cod_tipo_operacion),'' MOTIVO_BAJA,'salida' ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and (r.COD_TIPO_OPERACION in ('A001','A005','N001','N002','N003','N004','N005','N006','N007','N008','N009','N010') ");
      strQuery.append(" or r.cod_tipo_operacion = 'A002' and cod_agente_origen=98) ");
      strQuery.append(" group by r.cod_tipo_operacion ");
      tableBean.setSelect(strQuery.toString());

      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", initialDate);
      map.put("4", finalDate);
      map.put("5", initialDate);
      map.put("6", finalDate);
      tableBean.setParametrosSelect(map);

      // Se indican las funciones para contar las estadisticas
      //tableBean.setFunctions("SUM");
      //tableBean.setPositionFunctions("2");
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


  private Salida getTablaN00x(String initialDate, String finalDate) {

    Salida salida = new Salida();
    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfPorTipo.getTablaN00x");

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
      tableBean.setNumFilaCabecera(5);
      // Numero de la fila inicial de la tabla
      tableBean.setNumFilaInicial(6);
      //
      // Consulta
      StringBuffer strQuery = new StringBuffer();
      strQuery.append(" select count(1),'multiples-n009' TIPO,''COD_ERROR,''ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N009' and dbms_lob.instr(mensaje_xml,'usuariosEncontrados')>0 ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'ok-n009' TIPO,''COD_ERROR,''ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N009' and dbms_lob.instr(mensaje_xml,'<codigo>O')>0 ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'errores-n009' TIPO,''COD_ERROR,''ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N009' and dbms_lob.instr(mensaje_xml,'<codigo>E')>0 ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'ok-n005' TIPO,''COD_ERROR,''ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r ");
      strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and (dbms_lob.instr(mensaje_xml,'<codigo>O')>0 or dbms_lob.instr(mensaje_xml,'<codigo></codigo>')>0) ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'errores-n005'TIPO ,CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>') + 37, ((dbms_lob.instr(mensaje_xml,'</codigo><descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>')+37)))) AS VARCHAR2(50)) AS COD_ERROR, 'errores-n005-A002'ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r, agentes a ");
      strQuery.append(" where  r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<codigo>E')>0 ");
      strQuery.append(" and a.cod_agente=r.cod_agente_destino ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<id>A002')>0 ");
      strQuery.append(" group by CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>') + 37, ((dbms_lob.instr(mensaje_xml,'</codigo><descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>')+37)))) AS VARCHAR2(50)) ");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'errores-n005'TIPO , CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>') + 37, ((dbms_lob.instr(mensaje_xml,'</codigo><descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>')+37)))) AS VARCHAR2(50)) AS COD_ERROR, 'errores-n005-A003'ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r, agentes a ");
      strQuery.append(" where  r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<codigo>E')>0 ");
      strQuery.append(" and a.cod_agente=r.cod_agente_destino ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<id>A003')>0 ");
      strQuery.append(" group by CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>') + 37, ((dbms_lob.instr(mensaje_xml,'</codigo><descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo><error><codigo>')+37)))) AS VARCHAR2(50))");
      strQuery.append(" union all ");
      strQuery.append(" select count(1),'errores-n005'TIPO , CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo> <error> <codigo>') + 39, ((dbms_lob.instr(mensaje_xml,'</codigo> <descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo> <error> <codigo>')+39)))) AS VARCHAR2(50)) AS COD_ERROR, 'errores-n005-A004'ERRORES_N005 ");
      strQuery.append(" from MV_REGISTRO_OPERACIONES_ULTMES  r, agentes a ");
      strQuery.append(" where  r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
      strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<codigo>E')>0 ");
      strQuery.append(" and a.cod_agente=r.cod_agente_destino ");
      strQuery.append(" and r.COD_TIPO_OPERACION = 'N005' and dbms_lob.instr(mensaje_xml,'<id>A004')>0  ");
      strQuery.append(" group by CAST(TRIM(SUBSTR (mensaje_xml, dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo> <error> <codigo>') + 39, ((dbms_lob.instr(mensaje_xml,'</codigo> <descripcion>')) - (dbms_lob.instr(mensaje_xml,'<codigo>Error</codigo> <error> <codigo>')+39)))) AS VARCHAR2(50)) ");

      tableBean.setSelect(strQuery.toString());
      //
      // El Map con los parametros para la consulta, todas las consultas contienen los mismos parametros
      HashMap map = new HashMap();
      map.put("1", initialDate);
      map.put("2", finalDate);
      map.put("3", initialDate);
      map.put("4", finalDate);
      map.put("5", initialDate);
      map.put("6", finalDate);
      map.put("7", initialDate);
      map.put("8", finalDate);
      map.put("9", initialDate);
      map.put("10", finalDate);
      map.put("11", initialDate);
      map.put("12", finalDate);
      map.put("13", initialDate);
      map.put("14", finalDate);
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
