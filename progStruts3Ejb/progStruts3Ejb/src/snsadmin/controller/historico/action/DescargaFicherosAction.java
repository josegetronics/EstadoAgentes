package snsadmin.controller.historico.action;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import snsadmin.controller.bean.MensajePageBean;
import sns.logging.Logger;
import snsadmin.controller.historico.model.HistoricoForm;
import gasai.util.Misc;


public class DescargaFicherosAction extends Action {

  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    Logger logger = new Logger("snsadmin.controller.historico.action.DescargaFicherosAction.execute");

    try {
      logger.debug("INICIO");

      HistoricoForm historicoForm = (HistoricoForm) actionForm;

      String nameFile = Misc.nz(historicoForm.getNameFile());
      logger.debug("name: " + nameFile);

      MensajePageBean mensajePageBean = new MensajePageBean ();
      mensajePageBean.setMensaje("Se realizo el reporting con exito.");
      mensajePageBean.setEnlace("/WebModuloStruts3/");
      httpServletRequest.setAttribute("PageBean", mensajePageBean);

      logger.debug("FIN");
      return actionMapping.findForward("resultado");
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
