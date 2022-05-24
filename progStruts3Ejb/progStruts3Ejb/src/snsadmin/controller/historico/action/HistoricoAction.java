package snsadmin.controller.historico.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import snsadmin.controller.bean.MensajePageBean;
import sns.logging.Logger;
import snsadmin.controller.historico.model.HistoricoForm;

import java.util.ArrayList;
import snsadmin.controller.bean.RegistroBean;
import snsadmin.controller.bean.FileNamesBean;



public class HistoricoAction extends Action {


  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    Logger logger = new Logger("snsadmin.controller.historico.action.HistoricoAction.execute");

    try {
      logger.debug("INICIO");

      HistoricoForm historicoForm = new HistoricoForm ();


      ArrayList arrayNames = new ArrayList ();
      RegistroBean registroBean1 = new RegistroBean ();
      registroBean1.setCodigo("20080101_20080131_Reporting.xls");
      registroBean1.setDescripcion("Reporting Tarjeta");
      registroBean1.setDate("Enero 2008");

      RegistroBean registroBean2 = new RegistroBean ();
      registroBean2.setCodigo("20080201_20080229_Reporting.xls");
      registroBean2.setDescripcion("Reporting Tarjeta");
      registroBean2.setDate("Febrero 2008");

      arrayNames.add(registroBean1);
      arrayNames.add(registroBean2);

      FileNamesBean fileNamesBean = new FileNamesBean (arrayNames);

      httpServletRequest.setAttribute("PageBean", fileNamesBean);

      logger.debug("FIN");
      return actionMapping.findForward("historico");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      MensajePageBean mensajePageBean = new MensajePageBean();
      mensajePageBean.setDescripcionError("Intentelo mas tarde");
      mensajePageBean.setEnlace("/WebModuloStruts3/error.jsp");
      httpServletRequest.setAttribute("PageBean", mensajePageBean);
      return actionMapping.findForward("exception");
    }
  }
}
