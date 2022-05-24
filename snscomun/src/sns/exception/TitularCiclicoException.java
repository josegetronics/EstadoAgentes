package sns.exception;

public class TitularCiclicoException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5478260301904485227L;
	private String codSnsTitularActual;
	private String codSnsTitularNuevoTentativo;
	private String codSnsTitularNuevoDefinitivo;
	
	public TitularCiclicoException(String mensaje,String codSnsTitularActual,String codSnsTitularNuevoTentativo,String codSnsTitularNuevoDefinitivo) {
		super(mensaje);
		this.codSnsTitularActual=codSnsTitularActual;
		this.codSnsTitularNuevoTentativo=codSnsTitularNuevoTentativo;
		this.codSnsTitularNuevoDefinitivo=codSnsTitularNuevoDefinitivo;
	}

	public String getCodSnsTitularActual() {
		return codSnsTitularActual;
	}

	public void setCodSnsTitularActual(String codSnsTitularActual) {
		this.codSnsTitularActual = codSnsTitularActual;
	}

	public String getCodSnsTitularNuevoTentativo() {
		return codSnsTitularNuevoTentativo;
	}

	public void setCodSnsTitularNuevoTentativo(String codSnsTitularNuevoTentativo) {
		this.codSnsTitularNuevoTentativo = codSnsTitularNuevoTentativo;
	}

	public String getCodSnsTitularNuevoDefinitivo() {
		return codSnsTitularNuevoDefinitivo;
	}

	public void setCodSnsTitularNuevoDefinitivo(String codSnsTitularNuevoDefinitivo) {
		this.codSnsTitularNuevoDefinitivo = codSnsTitularNuevoDefinitivo;
	}

	
}
