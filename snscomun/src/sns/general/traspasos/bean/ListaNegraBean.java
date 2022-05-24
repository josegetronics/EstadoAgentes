package sns.general.traspasos.bean;

import gasai.util.Misc;
import sns.config.Constantes;


public class ListaNegraBean {

  private String codListaNegra = "";
  private String codUsusarioSns    = "";

  private String codAgente         = "";
  private String fechaPeticion     = "";

  private boolean activar;

  private String codTipoAnotacionListaNegra = Constantes.LISTA_NEGRA_TIPO_ANOTACION_MANUAL;

  public ListaNegraBean() {
  }


  public ListaNegraBean(String[] args) {
    //
    for (int i = 0; i < args.length; i++) {
      //COD_USUARIO_SNS|COD_AGENTE|
      //
      switch (i) {
        case 0:
          this.codUsusarioSns    = Misc.nz(args[i]);
          break;
        case 1:
          this.codAgente         = Misc.nz(args[i]);
          break;
      }
    }
  }

  public String getCodUsusarioSns() {
    return codUsusarioSns;
  }

  public String getCodAgente() {
    return codAgente;
  }

  public void setFechaPeticion(String fechaPeticion) {
    this.fechaPeticion = fechaPeticion;
  }

  public void setCodUsusarioSns(String codUsusarioSns) {
    this.codUsusarioSns = codUsusarioSns;
  }

  public void setCodAgente(String codAgente) {
    this.codAgente = codAgente;
  }

  public void setCodListaNegra(String codListaNegra) {
    this.codListaNegra = codListaNegra;
  }

  public void setActivar(boolean activar) {
    this.activar = activar;
  }

  public void setCodTipoAnotacionListaNegra(String codTipoAnotacionListaNegra) {
    this.codTipoAnotacionListaNegra = codTipoAnotacionListaNegra;
  }

  public String getFechaPeticion() {
    return fechaPeticion;
  }

  public String getCodListaNegra() {
    return codListaNegra;
  }

  public boolean isActivar() {
    return activar;
  }

  public String getCodTipoAnotacionListaNegra() {
    return codTipoAnotacionListaNegra;
  }
}
