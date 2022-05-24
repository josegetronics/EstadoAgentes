package sns.model;

import java.util.Vector;

import sns.util.Localizador;

public class ListaCamposAfectadosVisiblesToComunidad extends ListaCamposAfectados {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1787240476424915233L;

	public ListaCamposAfectadosVisiblesToComunidad() {
	}
	
	private boolean actualizacionVisibleComunidad=false;
	private String codUsuarioSns;
	private Localizador localizador;

	public boolean isActualizacionVisibleComunidad() {
		return actualizacionVisibleComunidad;
	}

	public void setActualizacionVisibleComunidad(boolean actualizacionVisibleComunidad) {
		this.actualizacionVisibleComunidad = actualizacionVisibleComunidad;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	  public Localizador getLocalizador() {
		return localizador;
	}

	public void setLocalizador(Localizador localizador) {
		this.localizador = localizador;
	}

	public Vector concat(ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados) {
		    Vector vListaCamposAfectadosAux=listaCamposAfectados.getListaCamposAfectados();
		    for (int i=0;i<vListaCamposAfectadosAux.size();i++) {
		      vListaCamposAfectados.addElement(vListaCamposAfectadosAux.elementAt(i));
		    }
		    Vector vIdCamposAux=listaCamposAfectados.getListaIdCampos();
		    for (int i=0;i<vIdCamposAux.size();i++) {
		      vIdCampos.addElement(vIdCamposAux.elementAt(i));
		    }
		    if (listaCamposAfectados.isActualizacionVisibleComunidad()) {
		    	setActualizacionVisibleComunidad(true);
		    }
		    return this.vListaCamposAfectados;
		  }

	

}
