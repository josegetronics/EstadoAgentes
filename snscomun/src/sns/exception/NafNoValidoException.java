package sns.exception;

public class NafNoValidoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6324669296991337269L;

	public NafNoValidoException(String naf) {
		super("NafNoValidoException para el naf -> [" + naf + "]");
	}
}
