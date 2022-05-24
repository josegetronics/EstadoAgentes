package sns.exception;

public class NafExistenteException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241920947459265486L;
	public NafExistenteException(String naf) {
		super("NafExistenteException -> " + naf);
	}

}
