package snsadmin.controller.bean;


public class MensajePageBean {

  // Enlace del origen del MensajePageBean
  private String enlace;
  // Mensaje que se pasa
  private String mensaje;
  // Descripcion del mensaje
  private String descripcionError;


  public void setEnlace(String enlace) {
    this.enlace = enlace;
  }

  public void setDescripcionError(String descripcionError) {
    this.descripcionError = descripcionError;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getEnlace() {
    return enlace;
  }

  public String getDescripcionError() {
    return descripcionError;
  }

  public String getMensaje() {
    return mensaje;
  }

  public MensajePageBean() {
  }
}
