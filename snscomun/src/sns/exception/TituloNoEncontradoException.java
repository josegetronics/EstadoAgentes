package sns.exception;

public class TituloNoEncontradoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5363456844478015748L;
	public TituloNoEncontradoException() {
		super("TituloNoEncontradoException");
	}
	public TituloNoEncontradoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	public TituloNoEncontradoException(String arg0) {
		super("TituloNoEncontrado para el tipoAseguramiento -> [" + arg0 + "]");
	}
	public TituloNoEncontradoException(Throwable arg0) {
		super(arg0);
	}
	public TituloNoEncontradoException(int codTitulo) {
		super("TituloNoEncontrado para el codTitulo -> [" + codTitulo + "]");
	}

}
