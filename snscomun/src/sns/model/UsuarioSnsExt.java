package sns.model;

import sns.util.Localizador;


public class UsuarioSnsExt extends UsuarioSns {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8060004537384843044L;

	private Localizador localizador=new Localizador();

	public void setLocalizador(Localizador localizador) {
		this.localizador = localizador;
	}

	public Localizador getLocalizador() {
		return localizador;
	}
	
	
}
