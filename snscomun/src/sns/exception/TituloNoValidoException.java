package sns.exception;

public class TituloNoValidoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1186295586626469130L;

	public TituloNoValidoException(int codTitulo) {
		super("TituloNoValidoException para el codTitulo -> [" + codTitulo + "]");
	}
	public TituloNoValidoException(String codTitulo) {
		super("TituloNoValidoException para el codTitulo -> [" + codTitulo + "]");
	}
}
