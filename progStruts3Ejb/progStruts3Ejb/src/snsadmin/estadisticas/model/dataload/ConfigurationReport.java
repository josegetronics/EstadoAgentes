package snsadmin.estadisticas.model.dataload;


import snsadmin.estadisticas.bean.report.ReportFileBean;
import sns.logging.Logger;
import sns.config.Constantes;
import gasai.util.out.Salida;
import gasai.util.Misc;


public class ConfigurationReport {


  public ConfigurationReport() {
  }


  public Salida initialConfiguration(String initialDate, String finalDate, String reportIdServicio) {

    ReportFileBean reportFileBean = new ReportFileBean ();
    Salida salida = new Salida ();

    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.ConfigurationReport.initialConfiguration");

    try {
      logger.debug("INICIO");

      // Se establecen los datos iniciales
      reportFileBean.setInitialDate(Misc.nz(initialDate));
      reportFileBean.setFinalDate(Misc.nz(finalDate));
      reportFileBean.setReportIdServicio(Misc.nz(reportIdServicio));

      // La plantilla para el excel a generar debe ser vacia
      reportFileBean.setPlantilla(Constantes.ADMIN_EXCEL_PLANTILLA);
      reportFileBean.setRutaPlantilla(Constantes.ADMIN_EXCEL_RUTA_PLANTILLA);
      // Ruta y Nombre del fichero
      reportFileBean.setRuta(Constantes.ADMIN_EXCEL_RUTA);

      // Se obtiene el nombre del fichero Excel
      // Fecha inicial
      String dateInitial = this.getDateName(initialDate);
      // Fecha inicial
      String dateFinal   = this.getDateName(finalDate);

      StringBuffer strNameFile = new StringBuffer();
      strNameFile.append(dateInitial);
      strNameFile.append(Constantes.ADMIN_SEPARATOR_NAME_FILE);
      strNameFile.append(dateFinal);
      strNameFile.append(Constantes.ADMIN_SEPARATOR_NAME_FILE);
      strNameFile.append(Constantes.ADMIN_REPORT_POSTNAME_FILE);
      strNameFile.append(Constantes.ADMIN_POINT);
      strNameFile.append(Constantes.ADMIN_REPORT_EXTENSION_FILE);
      reportFileBean.setNombreFicheroUnico(strNameFile.toString());

      salida.setValor(reportFileBean);

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


  private String getDateName(String date) throws Exception {

    String dateResult = "";
    Logger logger = new Logger("snsadmin.util.UtilReport.getDateName");

    try {
      logger.debug("INICIO");

      if(date.length() >= 10){
        String day   = Misc.nz(date.substring(0, 2));
        String month = Misc.nz(date.substring(3, 5));
        String year  = Misc.nz(date.substring(6, 10));

        if(Misc.esDigito(day) && Misc.esDigito(month) && Misc.esDigito(year)) {
          System.out.println("day: " + day + ", month: " + month + ", year: " + year);
          StringBuffer stringBuffer = new StringBuffer ();
          stringBuffer.append(year);
          stringBuffer.append(month);
          stringBuffer.append(day);
          dateResult = stringBuffer.toString();
        }
      }
      logger.debug("dateResult: " + dateResult);

      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.debug("Error: " + e.getMessage());
      throw e;
    }
    return dateResult;
  }



}
