package sns.exception;

public class SituacionNoValidoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1205986055490851966L;
	public SituacionNoValidoException(int codSituacion) {
		super("SituacionNoValidoException para el codSituacion -> [" + codSituacion + "]");
	}
	public SituacionNoValidoException(String codSituacion) {
		super("SituacionNoValidoException para el codSituacion -> [" + codSituacion + "]");
	}
}
