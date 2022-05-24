package sns.exception;

public class UsuarioSegSocialException extends TsiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioSegSocialException(String msg) {
		super(msg);
	}

	public UsuarioSegSocialException() {
		super("UsuarioSegSocialException");
	}

}
