package snsadmin.controller.fichero.model;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import gasai.util.Misc;
import sns.logging.Logger;


public class ReportingForm extends ActionForm {

  // Fecha Inicial
  private String initialDate;
  // Fecha Final
  private String finalDate;
  // ID_SERVICIO
  private String reportIdServicio;

  public String getFinalDate() {
    return finalDate;
  }

  public String getInitialDate() {
    return initialDate;
  }

  public String getReportIdServicio() {
    return reportIdServicio;
  }

  public void setFinalDate(String finalDate) {
    this.finalDate = finalDate;
  }

  public void setInitialDate(String initialDate) {
    this.initialDate = initialDate;
  }

  public void setReportIdServicio(String reportIdServicio) {
    this.reportIdServicio = reportIdServicio;
  }


  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

    ActionErrors errors = new ActionErrors();

    Logger logger = new Logger("snsadmin.controller.fichero.model.ReportingForm.validate");

    // Fecha Inicial
    if (Misc.esVacio(initialDate)) errors.add("initialDate",  new ActionError("error.initialDate.required"));
    else {
      try {
        if (initialDate.length() < 10) {
          errors.add("initialDate", new ActionError("error.initialDate.empty"));
        }
      }
      catch (Exception ex) {
        logger.error("Exception en Fecha Inicial: " + ex.getMessage());
      }
    }

    // Fecha Final
    if (Misc.esVacio(finalDate)) errors.add("finalDate",  new ActionError("error.finalDate.required"));
    else {
      try {
        if (finalDate.length() < 10) {
          errors.add("finalDate", new ActionError("error.finalDate.empty"));
        }
      }
      catch (Exception ex) {
        logger.error("Exception en Fecha Final: " + ex.getMessage());
      }
    }
    //No es necesario generar el PAGEBEAN
    return errors;
  }


  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
}
