package snsadmin.controller.bean;

import java.util.ArrayList;

public class FileNamesBean {

  ArrayList fileNames = null;

  public FileNamesBean() {
  }

  public FileNamesBean(ArrayList fileNames) {
    this.fileNames =  fileNames;
  }


  public void setFileNames(ArrayList fileNames) {
    this.fileNames = fileNames;
  }

  public ArrayList getFileNames() {
    return fileNames;
  }


}
