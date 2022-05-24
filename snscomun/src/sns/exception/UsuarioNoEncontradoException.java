package sns.exception;

public class UsuarioNoEncontradoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -51952003685170948L;
	public UsuarioNoEncontradoException() {
		super("UsuarioNoEncontradoException");
	}
	public UsuarioNoEncontradoException(String codUsuarioSns) {
		super("UsuarioNoEncontradoException -> " + codUsuarioSns);
	}

}
