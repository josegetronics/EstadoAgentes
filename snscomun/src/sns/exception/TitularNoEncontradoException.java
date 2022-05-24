package sns.exception;

public class TitularNoEncontradoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5363456844478015748L;
	public TitularNoEncontradoException(String nafTitular) {
		super("TitularNoEncontradoException -> " + nafTitular);
	}

}
