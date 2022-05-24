package snsadmin.controller.bean;


public class RegistroBean implements java.io.Serializable {

  private String codigo;
  private String descripcion;
  private String date;


  public String getDescripcion() {
      return descripcion;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCodigo() {
      return codigo;
   }

  public String getDate() {
    return date;
  }

  public RegistroBean() {
   }
}
