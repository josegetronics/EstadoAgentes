package snsadmin.estadisticas.model.excel;

import snsadmin.estadisticas.model.excel.ManagerExcel;
import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import sns.logging.Logger;
import snsadmin.estadisticas.model.dataload.bloqueos.ManagerAgentes;
import snsadmin.estadisticas.model.dataload.bloqueos.ManagerConfBloqueos;
import snsadmin.estadisticas.model.dataload.ConfigurationReport;
import snsadmin.estadisticas.model.dataload.mensajes.ManagerConfGeneral;
import sns.bd.AccesoBd;
import sns.bd.AccesoBdIntercambiador;
import gasai.bd.AccesoBD;



public class ExcelHelpers {


  public ExcelHelpers() {
  }


  /**
   * Llama a las diferentes funciones para captar la informacion del informe y generarlo.
   *
   * @param reportingForm ReportingForm Formulario para recibir las fechas entre las que estra el informe.
   * @return Salida
   */
  public Salida getExcel(String initialDate, String finalDate, String reportIdServicio) {

    ReportFileBean         reportFileBean          = null;
    //AccesoBd             accesoBd                = null;
    AccesoBD accesoBD      = null;
    AccesoBD accesoBDInter = null;
    //AccesoBdIntercambiador accesoBdIntercambiador  = null;

    Salida salida = new Salida();

    Logger logger = new Logger("snsadmin.estadisticas.model.excel.ExcelHelpers.getExcel");

    try {
      logger.debug("INICIO");
      //
      // Se realiza el procedimiento de creacion de excel dos veces, una para las
      // consultas que se realizan en el sns y otra para las consultas del intercambiador.
      // Podria incluirse una variable en la tabla, para que BBDD se tiene que
      // ejecutar.
      //
      //////////////////////////////////////////////////////////////////////////
      //////////////////////////////////////////////////////////////////////////
      //
      // 1º EXCEL - CONSULTAS INTERCAMBIADOR
      //
      // Se establecen los valores iniciales del informe, fechas, rutas, ....
      ConfigurationReport configurationReport = new ConfigurationReport ();
      Salida salidaInicial = configurationReport.initialConfiguration(initialDate, finalDate, reportIdServicio);

      // // //   SE RELLENA EL REPORTBEAN   // // //
      if (!salidaInicial.getError()) {
        reportFileBean = (ReportFileBean) salidaInicial.getValor();
        //
        // Obtiene la informacion del informe de bloqueos
        ManagerConfBloqueos managerConfBloqueos = new ManagerConfBloqueos();
        salida = managerConfBloqueos.getConfigurationData(reportFileBean);
        //
        if (!salida.getError()) {
          // Obtiene la informacion del informe para los bloqueos de las comunidades
          //logger.debug("-------------------");
          ManagerAgentes managerAgentes = new ManagerAgentes();
          //salida = managerAgentes.agentesToReportFileBean(reportFileBean);
          //logger.debug("-------------------");
          //
          // // //   SE RELLENA EL EXCEL   // // //
          // Si no hay error al rellenar el REPORTBEAN
          if (!salida.getError() && reportFileBean != null) {
            //Se genera el informe
            //
            // Se crea un objeto para BBDD
            accesoBDInter = new AccesoBdIntercambiador ();
            //
            ManagerExcel managerExcel = new ManagerExcel();
            salida = managerExcel.generateExcelFile(accesoBDInter, reportFileBean, "01_");
          }
          else {
            logger.debug("No se genera el Excel_01, error: " + salida.getError() + ", Msg: " + salida.getMsg());
          }
        }
        //
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////
        //
        // 2º EXCEL - CONSULTAS SNS
        //
        if (!salida.getError()) {
          //
          // Se establecen los valores iniciales del informe, fechas, rutas, ....
          Salida salida2 = configurationReport.initialConfiguration(initialDate, finalDate, reportIdServicio);
          //
          for(int i=0;i<10;i++) {
            logger.debug("-- -- -- GENERANDO EL 2 EXCEL -- -- --");
          }
          //
          if (!salida.getError()) {
            ReportFileBean reportFileBean2 = (ReportFileBean) salida2.getValor();
            //
            ManagerConfGeneral managerConfGeneral = new ManagerConfGeneral();
            salida = managerConfGeneral.getConfigurationData(reportFileBean2);
            //logger.debug("-------------------");
            //
            // // //   SE RELLENA EL EXCEL   // // //
            // Si no hay error al rellenar el REPORTBEAN
            if (!salida.getError() && reportFileBean2 != null) {
              //Se genera el informe
              //
              // Se crea un objeto para BBDD
              accesoBD = new AccesoBd();
              //
              ManagerExcel managerExcel = new ManagerExcel();
              salida = managerExcel.generateExcelFile(accesoBD, reportFileBean2, "02_");
            }
            else {
              logger.debug("No se genera el Excel, error: " + salida.getError() + ", Msg: " + salida.getMsg());
            }
          }
        }
        //
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////


      }
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    finally{
      try {
        if (accesoBDInter != null) {
          accesoBDInter.cerrar();
        }
      }
      catch (Exception e) {
        logger.error("Exception: " + e.getMessage());
        accesoBDInter = null;
      }
      try {
        if (accesoBD != null) {
          accesoBD.cerrar();
        }
      }
      catch (Exception e) {
        logger.error("Exception: " + e.getMessage());
        accesoBD = null;
      }
    }
    return salida;
  }


}
