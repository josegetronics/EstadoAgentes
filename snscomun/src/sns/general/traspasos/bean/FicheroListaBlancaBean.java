package sns.general.traspasos.bean;

public class FicheroListaBlancaBean {

  private String nombreFichero;
  private String nombreFicheroNormalizado;
  private String rutaFicheroNormalizado;
  private String mensaje;


  public FicheroListaBlancaBean() {
  }


  public String getRutaFicheroNormalizado() {
    return rutaFicheroNormalizado;
  }

  public String getNombreFicheroNormalizado() {
    return nombreFicheroNormalizado;
  }

  public void setNombreFichero(String nombreFichero) {
    this.nombreFichero = nombreFichero;
  }

  public void setRutaFicheroNormalizado(String rutaFicheroNormalizado) {
    this.rutaFicheroNormalizado = rutaFicheroNormalizado;
  }

  public void setNombreFicheroNormalizado(String nombreFicheroNormalizado) {
    this.nombreFicheroNormalizado = nombreFicheroNormalizado;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getNombreFichero() {
    return nombreFichero;
  }

  public String getMensaje() {
    return mensaje;
  }

}
