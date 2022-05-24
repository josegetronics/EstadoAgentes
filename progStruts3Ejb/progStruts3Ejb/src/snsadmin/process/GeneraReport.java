package snsadmin.process;

import sns.logging.Logger;
import gasai.util.out.Salida;
import snsadmin.estadisticas.model.excel.ExcelHelpers;
import sns.config.Inicializacion;

public class GeneraReport {


  public GeneraReport() {
  }


  public static void main(String[] args) throws Exception {

    Logger logger = new Logger("snsadmin.process.GeneraReport.main");

    //if (args.length != 3) {
    //  System.out.println("uso: snsadmin.process.GeneraReport initialDate finalDate reportIdServicio");
    //  logger.debug("main: args[0] initialDate:      " + args[0]);
    //  logger.debug("main: args[1] finalDate:        " + args[1]);
    //  logger.debug("main: args[2] reportIdServicio: " + args[2]);
    //  System.exit( -1);
    //}

    String initialDate      = "01/03/2008";
    String finalDate        = "31/03/2008";
    String reportIdServicio = "1";


    try {
      logger.debug("INICIO");

      logger.debug("Inicial: " + initialDate + ", Final: " + finalDate + ", IdServicio: " + reportIdServicio);

      Inicializacion.init();

      ExcelHelpers excelHelpers = new ExcelHelpers();
      Salida salida = excelHelpers.getExcel(initialDate, finalDate, reportIdServicio);

      logger.debug("Error: " + salida.getError() + ", salida.getMsg: " + salida.getMsg());
    }
    catch (Exception e) {
      logger.debug("Exception: " + e.getMessage());
      e.printStackTrace();
    }
  }


}
