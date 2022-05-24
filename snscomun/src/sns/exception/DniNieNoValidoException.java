package sns.exception;

public class DniNieNoValidoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4684830448281040011L;

	public DniNieNoValidoException(String dniNie) {
		super("DniNieNoValidoException para el dniNie -> [" + dniNie + "]");
	}
}
