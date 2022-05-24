package snsadmin.estadisticas;

import gasai.util.out.Salida;
import sns.logging.Logger;
import snsadmin.controller.bean.MensajePageBean;
import snsadmin.controller.fichero.model.ReportingForm;
import snsadmin.estadisticas.model.excel.ExcelHelpers;

public class ReportingMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = new Logger("snsadmin.estadisticas.ReportingMain.main");
	    Salida salida = new Salida ();
	    
	    try {
	        logger.debug("INICIO");
	        

	        String initialDate = "01/10/2018";
	        String finalDate = "31/10/2018";
	        String reportIdServicio = "1";
	        ExcelHelpers excelHelpers = new ExcelHelpers();
	        salida = excelHelpers.getExcel(initialDate, finalDate, reportIdServicio);    
	        
	      }
	      catch (Exception e) {
	        e.printStackTrace();
	        logger.error("ReportingAction.execute(): Exception: " + e.getMessage());
	      }
		

	}

}
