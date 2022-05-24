package sns.exception;

public class TitularInssSinNafException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241920947459265486L;
	public TitularInssSinNafException() {
		super("TitularInssSinNafException");
	}
	public TitularInssSinNafException(String tipoAseguramiento) {
		super("TitularInssSinNafException -> " + tipoAseguramiento);
	}

}
