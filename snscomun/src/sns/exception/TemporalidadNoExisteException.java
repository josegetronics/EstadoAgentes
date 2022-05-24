package sns.exception;

public class TemporalidadNoExisteException extends TsiException {



	/**
	 * 
	 */
	private static final long serialVersionUID = -2597597400822021910L;

	public TemporalidadNoExisteException() {}
	
	public TemporalidadNoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public TemporalidadNoExisteException(Throwable cause) {
		super(cause);
	}

}
