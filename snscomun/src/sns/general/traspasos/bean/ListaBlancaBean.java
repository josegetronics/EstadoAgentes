package sns.general.traspasos.bean;

import gasai.util.Misc;


public class ListaBlancaBean {

  private String codListaBlanca    = "";
  private String codUsusarioSns    = "";
  private String codMotivoTraspaso = "";
  private String motivoTraspaso    = "";
  private String codAgente         = "";
  private String fechaPeticion     = "";
  private String fechaSolicitante  = "";
  private String codOperacion      = "";


  public ListaBlancaBean() {
  }


  public ListaBlancaBean(String[] args) {
    //
    for (int i = 0; i < args.length; i++) {
      //COD_USUARIO_SNS|COD_MOTIVO|COD_AGENTE|
      //
      switch (i) {
        case 0:
          this.codUsusarioSns    = Misc.nz(args[i]);
          break;
        case 1:
          this.codMotivoTraspaso = Misc.nz(args[i]);
          break;
        case 2:
          this.codAgente         = Misc.nz(args[i]);
          break;
      }
    }
  }


  public String getFechaSolicitante() {
    return fechaSolicitante;
  }

  public String getCodOperacion() {
    return codOperacion;
  }

  public String getCodMotivoTraspaso() {
    return codMotivoTraspaso;
  }

  public String getCodUsusarioSns() {
    return codUsusarioSns;
  }

  public String getCodListaBlanca() {
    return codListaBlanca;
  }

  public String getCodAgente() {
    return codAgente;
  }

  public void setFechaPeticion(String fechaPeticion) {
    this.fechaPeticion = fechaPeticion;
  }

  public void setFechaSolicitante(String fechaSolicitante) {
    this.fechaSolicitante = fechaSolicitante;
  }

  public void setCodOperacion(String codOperacion) {
    this.codOperacion = codOperacion;
  }

  public void setCodMotivoTraspaso(String codMotivoTraspaso) {
    this.codMotivoTraspaso = codMotivoTraspaso;
  }

  public void setCodUsusarioSns(String codUsusarioSns) {
    this.codUsusarioSns = codUsusarioSns;
  }

  public void setCodListaBlanca(String codListaBlanca) {
    this.codListaBlanca = codListaBlanca;
  }

  public void setCodAgente(String codAgente) {
    this.codAgente = codAgente;
  }

  public void setMotivoTraspaso(String motivoTraspaso) {
    this.motivoTraspaso = motivoTraspaso;
  }

  public String getFechaPeticion() {
    return fechaPeticion;
  }

  public String getMotivoTraspaso() {
    return motivoTraspaso;
  }

}
