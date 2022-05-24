package sns.exception;

public class ModificacionCamposInssException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2256998202488404610L;

	public ModificacionCamposInssException(String campoAfectado) {
		super("Se ha intentado modificar el campo " + campoAfectado);
	}

}
