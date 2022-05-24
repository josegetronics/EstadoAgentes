package snsadmin.controller.login.model;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import gasai.util.Misc;


public class LoginActionForm  extends ActionForm {

  private String usuario;
  private String password;

  public String getPassword() {
    return password;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setPassword(String passwordUser) {
    this.password = passwordUser;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

    ActionErrors errors = new ActionErrors();

    // Usuario
    if (Misc.esVacio(usuario)) {
      errors.add("usuario", new ActionError("error.usuario.required"));
    }

    // Password
    if (Misc.esVacio(password)) {
      errors.add("password", new ActionError("error.password.required"));
    }

    //No es necesario generar el PAGEBEAN
    return errors;
  }


  public void reset(ActionMapping actionMapping,  HttpServletRequest httpServletRequest) {
  }

}
