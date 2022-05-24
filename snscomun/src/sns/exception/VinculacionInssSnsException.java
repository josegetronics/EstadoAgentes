package sns.exception;

public class VinculacionInssSnsException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6835257427128591845L;
	public VinculacionInssSnsException(String codUsuarioSns) {
		super("VinculacionInssSnsException -> " + codUsuarioSns);
	}

}
