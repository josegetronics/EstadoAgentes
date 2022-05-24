package sns.util.out;

import java.util.Vector;

import sns.model.UsuarioSns;

public class SalidaAlta {
  private UsuarioSns usuarioSns;
  private String msgError;
  private Vector vIdCampos;
  private Vector vValoresCampos;

  public Vector getVValoresCampos() {
    return vValoresCampos;
  }

  public Vector getVIdCampos() {
    return vIdCampos;
  }

  public String getMsgError() {
    return msgError;
  }

  public void setUsuarioSns(UsuarioSns usuarioSns) {
    this.usuarioSns = usuarioSns;
  }

  public void setVValoresCampos(Vector vValoresCampos) {
    this.vValoresCampos = vValoresCampos;
  }

  public void setVIdCampos(Vector vIdCampos) {
    this.vIdCampos = vIdCampos;
  }

  public void setMsgError(String msgError) {
    this.msgError = msgError;
  }

  public UsuarioSns getUsuarioSns() {
    return usuarioSns;
  }

  public SalidaAlta() {
  }
}
