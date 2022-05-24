package sns.exception;

import sns.model.ListaCamposAfectados;

public class ModificacionNoPermitidaException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7616867347161376752L;

	private String codUsuarioSns;
	private ListaCamposAfectados listaCamposAfectados;
	
	public ModificacionNoPermitidaException(String codUsuarioSns,ListaCamposAfectados listaCamposAfectados) {
		super("Se ha intentado modificar el codigoUsuarioSns " + codUsuarioSns);
		this.codUsuarioSns=codUsuarioSns;
		this.listaCamposAfectados=listaCamposAfectados;
	}

	public ListaCamposAfectados getListaCamposAfectados() {
		return listaCamposAfectados;
	}

	public void setListaCamposAfectados(ListaCamposAfectados listaCamposAfectados) {
		this.listaCamposAfectados = listaCamposAfectados;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}
	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

}
