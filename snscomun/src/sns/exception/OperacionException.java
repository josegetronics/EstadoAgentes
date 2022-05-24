package sns.exception;

public class OperacionException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3475387086496600143L;

	public OperacionException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperacionException(Throwable cause) {
		super(cause);
	}

}
