package sns.exception;

public class CambioIpfNoPermitidoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7471662123236383211L;
	public CambioIpfNoPermitidoException(String codUsuarioSns) {
		super("CambioIpfNoPermitidoException -> " + codUsuarioSns);
	}

}
