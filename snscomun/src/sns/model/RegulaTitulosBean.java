package sns.model;

public class RegulaTitulosBean {
  private String ras;
  private String ccc;
  private String far;
  private String codTitulo;
  private String codSituacion;
  public String getCodSituacion() {
    return codSituacion;
  }

  public String getCodTitulo() {
    return codTitulo;
  }

  public String getFar() {
    return far;
  }

  public String getCcc() {
    return ccc;
  }

  public void setRas(String ras) {
    this.ras = ras;
  }

  public void setCodSituacion(String codSituacion) {
    this.codSituacion = codSituacion;
  }

  public void setCodTitulo(String codTitulo) {
    this.codTitulo = codTitulo;
  }

  public void setFar(String far) {
    this.far = far;
  }

  public void setCcc(String ccc) {
    this.ccc = ccc;
  }

  public String getRas() {
    return ras;
  }

  public RegulaTitulosBean() {
  }
}
