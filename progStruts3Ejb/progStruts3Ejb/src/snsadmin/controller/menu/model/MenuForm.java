package snsadmin.controller.menu.model;


import org.apache.struts.action.*;
import javax.servlet.http.*;


public class MenuForm extends ActionForm {

  private String option;


  public void setOption(String option) {
    this.option = option;
  }


  public String getOption() {
    return option;
  }


  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    return null;
  }


  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
}
