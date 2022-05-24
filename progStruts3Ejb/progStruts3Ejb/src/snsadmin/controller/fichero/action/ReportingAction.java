package snsadmin.controller.fichero.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import gasai.util.out.Salida;
import snsadmin.controller.fichero.model.ReportingForm;
import snsadmin.controller.bean.MensajePageBean;
import sns.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.Context;
import java.util.Properties;
import java.util.HashMap;
import snsadmin.estadisticas.model.excel.ExcelHelpers;

public class ReportingAction extends Action {


  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    Logger logger = new Logger("snsadmin.controller.fichero.action.ReportingAction.execute");
    Salida salida = new Salida ();

    try {
      logger.debug("INICIO");
      //
      //Context context = getInitialContext();
      //
      ReportingForm reportingForm = (ReportingForm) actionForm;

      logger.debug("reportIdServicio: " + reportingForm.getReportIdServicio());

      logger.debug("Inicial: " + reportingForm.getInitialDate() + ", Final: " + reportingForm.getFinalDate() + ", IdServicio: " + reportingForm.getReportIdServicio());

      String initialDate = reportingForm.getInitialDate();
      String finalDate = reportingForm.getFinalDate();
      String reportIdServicio = reportingForm.getReportIdServicio();



      ExcelHelpers excelHelpers = new ExcelHelpers();
      salida = excelHelpers.getExcel(initialDate, finalDate, reportIdServicio);

      if(salida.getError()){
        logger.debug("salida contiene un error, " + salida.getMsg());
        MensajePageBean mensajePageBean = new MensajePageBean();
        mensajePageBean.setDescripcionError(salida.getMsg());
        mensajePageBean.setEnlace("/WebModuloStruts3/error.jsp");
        httpServletRequest.setAttribute("PageBean", mensajePageBean);
        return actionMapping.findForward("exception");
      }

      MensajePageBean mensajePageBean = new MensajePageBean ();
      mensajePageBean.setMensaje("Se realizo el reporting con exito.");
      mensajePageBean.setEnlace("/WebModuloStruts3/menuInicio.jsp");
      httpServletRequest.setAttribute("PageBean", mensajePageBean);

      logger.debug("FIN");

      return actionMapping.findForward("sucess");
    }
    catch (Exception e) {
      e.printStackTrace();
      logger.error("ReportingAction.execute(): Exception: " + e.getMessage());
      MensajePageBean mensajePageBean = new MensajePageBean ();
      mensajePageBean.setDescripcionError(e.getMessage());
      mensajePageBean.setEnlace("/WebModuloStruts3/error.jsp");
      httpServletRequest.setAttribute("PageBean", mensajePageBean);
      return actionMapping.findForward("exception");
    }
 }


 private Context getInitialContext() throws Exception {

   String url = "t3://localhost:7001";
   String user = "weblogic";
   String password = "weblogic";
   Properties properties = null;

   Logger logger = new Logger("snsadmin.controller.login.action.LoginAction.getInitialContext");

   try {
     properties = new Properties();
     properties.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
     properties.put(Context.PROVIDER_URL, url);

     if (user != null) {
       properties.put(Context.SECURITY_PRINCIPAL, user);
       properties.put(Context.SECURITY_CREDENTIALS, password == null ? "" : password);
     }

     return new InitialContext(properties);
   }
   catch (Exception e) {
     logger.error("Imposible conectar con el servidor WebLogic en " + url);
     logger.error("Por favor, cerciórese de que el servidor está en ejecución.");
     throw e;
   }
 }




}
