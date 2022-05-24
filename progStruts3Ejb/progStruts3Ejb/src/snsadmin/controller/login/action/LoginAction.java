package snsadmin.controller.login.action;

import java.util.*;
import javax.naming.*;
import javax.servlet.http.*;
import org.apache.commons.beanutils.*;
import org.apache.struts.action.*;
import javax.rmi.PortableRemoteObject;
import snsadmin.controller.bean.MensajePageBean;
import sns.logging.Logger;


public class LoginAction extends Action {

  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    Logger logger = new Logger("snsadmin.controller.login.action.LoginAction.execute");

    try {
      logger.debug("INICIO");

      String user     = (String) PropertyUtils.getSimpleProperty(actionForm, "usuario");
      String password = (String) PropertyUtils.getSimpleProperty(actionForm, "password");

      String code = "User: " + user + ", Password: " + password;
      logger.debug(code);
      httpServletRequest.setAttribute("Mensaje", code);

      boolean login = true;

      logger.debug("FIN");

      if(login){
        return actionMapping.findForward("sucess");
      }
      else{
        return actionMapping.findForward("fail");
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


  private boolean getLogin(String login, String password) throws Exception {

    boolean access = false;

    Logger logger = new Logger("snsadmin.controller.login.action.LoginAction.getLogin");

    try {
      Context context = getInitialContext();
      Object ref = context.lookup("Prueba");
      //PruebaHome pruebaHome = (PruebaHome) PortableRemoteObject.narrow(ref, PruebaHome.class);
      //Prueba     prueba     = (Prueba)     PortableRemoteObject.narrow(pruebaHome.create(), Prueba.class);

      //Se llama al metodo del stateless que compueba si el login y password coinciden con BBDD
      //access = prueba.consultarUsuario(login, password);
      context.close();
    }
    catch (Exception e) {
      logger.error("Exception: " +  e.getMessage());
      throw e;
    }
    return access;
  }


  private Context getInitialContext() throws Exception {

    String url            = "t3://localhost:7001";
    String user           = "weblogic";
    String password       = "weblogic";
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
      logger.error("Imposible conectar con el servidor WebLogic en " +  url);
      logger.error("Por favor, cerciórese de que el servidor está en ejecución.");
      throw e;
    }
  }


}
