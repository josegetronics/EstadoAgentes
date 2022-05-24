package snsadmin.controller.historico.model;

import org.apache.struts.action.*;


public class HistoricoForm extends ActionForm {


  private String nameFile;


  public void setNameFile(String nameFile) {
    this.nameFile = nameFile;
  }

  public String getNameFile() {
    return nameFile;
  }

}
