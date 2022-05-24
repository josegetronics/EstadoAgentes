package sns.model;

import gasai.util.Misc;

import java.util.Vector;

public class ListaCamposAfectados  implements java.io.Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
protected Vector vListaCamposAfectados;
  protected Vector vIdCampos;

  public ListaCamposAfectados() {
    vListaCamposAfectados=new Vector();
    vIdCampos=new Vector();
  }

  public boolean isEmpty() {
    return vListaCamposAfectados.isEmpty();
  }

  public void addCampo(CamposAfectados camposAfectados) {
    if (!Misc.nz(camposAfectados.getValorAnterior()).trim().equals(Misc.nz(camposAfectados.getValorSolicitud()))) {
      this.vListaCamposAfectados.addElement(camposAfectados);
      this.vIdCampos.addElement(camposAfectados.getIdCampo());
    }
  }

  public Vector getListaIdCampos() {
    return this.vIdCampos;
  }

  public String getIdCampos() {
    StringBuffer campos=new StringBuffer();
    CamposAfectados camposAfectados=null;
    for (int i=0;i<this.vListaCamposAfectados.size();i++) {
      camposAfectados=(CamposAfectados)vListaCamposAfectados.elementAt(i);
      campos.append("<campo>");
      campos.append(Misc.nz(camposAfectados.getIdCampo()));
      campos.append("</campo>");
    }
    return campos.toString();
  }

  public String getValoresAnteriores() {
    StringBuffer valoresAnteriores=new StringBuffer();
    CamposAfectados camposAfectados=null;
    for (int i=0;i<this.vListaCamposAfectados.size();i++) {
      camposAfectados=(CamposAfectados)vListaCamposAfectados.elementAt(i);
      valoresAnteriores.append("<valor>");
      valoresAnteriores.append(Misc.nz(camposAfectados.getValorAnterior()));
      valoresAnteriores.append("</valor>");
    }
    return valoresAnteriores.toString();
  }

  public String getValoresSolicitud() {
    StringBuffer valoresSolicitud=new StringBuffer();
    CamposAfectados camposAfectados=null;
    for (int i=0;i<this.vListaCamposAfectados.size();i++) {
      camposAfectados=(CamposAfectados)vListaCamposAfectados.elementAt(i);
      valoresSolicitud.append("<valor>");
      valoresSolicitud.append(Misc.nz(camposAfectados.getValorSolicitud()));
      valoresSolicitud.append("</valor>");
    }
    return valoresSolicitud.toString();
  }

  public boolean existeCampo(String idCampo) {
    return (this.vIdCampos.indexOf(idCampo)!=-1);
  }

  public String getValorCampo(String idCampo) {
    if (!existeCampo(idCampo)) {
      return "";
    }else {
      CamposAfectados camposAfectados=(CamposAfectados)this.vListaCamposAfectados.elementAt(this.vIdCampos.indexOf(idCampo));
      if (Misc.esVacio(camposAfectados)) {
        return "";
      }else{
        return Misc.nz(camposAfectados.getValorSolicitud());
      }

    }
  }

  public String getValorCampoAnterior(String idCampo) {
	    if (!existeCampo(idCampo)) {
	      return "";
	    }else {
	      CamposAfectados camposAfectados=(CamposAfectados)this.vListaCamposAfectados.elementAt(this.vIdCampos.indexOf(idCampo));
	      if (Misc.esVacio(camposAfectados)) {
	        return "";
	      }else{
	        return Misc.nz(camposAfectados.getValorAnterior());
	      }

	    }
	  }

  public Vector getListaCamposAfectados() {
    return this.vListaCamposAfectados;
  }

  public Vector concat(ListaCamposAfectados listaCamposAfectados) {
    Vector vListaCamposAfectadosAux=listaCamposAfectados.getListaCamposAfectados();
    for (int i=0;i<vListaCamposAfectadosAux.size();i++) {
      this.vListaCamposAfectados.addElement(vListaCamposAfectadosAux.elementAt(i));
    }
    Vector vIdCamposAux=listaCamposAfectados.getListaIdCampos();
    for (int i=0;i<vIdCamposAux.size();i++) {
      this.vIdCampos.addElement(vIdCamposAux.elementAt(i));
    }
    return this.vListaCamposAfectados;
  }
}
