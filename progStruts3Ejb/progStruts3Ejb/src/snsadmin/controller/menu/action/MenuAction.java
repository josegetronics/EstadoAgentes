package snsadmin.controller.menu.action;


import javax.servlet.http.*;
import org.apache.struts.action.*;
import snsadmin.controller.bean.MensajePageBean;
import sns.logging.Logger;
import snsadmin.controller.menu.model.MenuForm;
import gasai.util.Misc;

public class MenuAction extends Action {

  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    Logger logger = new Logger("snsadmin.controller.menu.action.MenuAction.execute");

    try {
      logger.debug("INICIO");

      MenuForm menuForm = (MenuForm) actionForm;

      logger.debug("Opcion: " + menuForm.getOption());

      logger.debug("FIN");

      String opcion = Misc.nz(menuForm.getOption());

      if (opcion.equals("1")) {
        return actionMapping.findForward("report");
      }
      else {
        return actionMapping.findForward("historico");
      }

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
