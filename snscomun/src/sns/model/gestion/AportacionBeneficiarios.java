package sns.model.gestion;

import java.math.BigDecimal;

public class AportacionBeneficiarios extends GestionGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4273321766594473711L;
	private String codUsuarioSnsTitular;
	private String codIndicadorDeFarmacia;
	private String codSubIndicadorDeFarmacia;
	private BigDecimal codTipoProcedencia;

	
	public String getCodIndicadorDeFarmacia() {
		return codIndicadorDeFarmacia;
	}

	public void setCodIndicadorDeFarmacia(String codIndicadorDeFarmacia) {
		this.codIndicadorDeFarmacia = codIndicadorDeFarmacia;
	}

	public String getCodSubIndicadorDeFarmacia() {
		return codSubIndicadorDeFarmacia;
	}

	public void setCodSubIndicadorDeFarmacia(String codSubIndicadorDeFarmacia) {
		this.codSubIndicadorDeFarmacia = codSubIndicadorDeFarmacia;
	}

	public BigDecimal getCodTipoProcedencia() {
		return codTipoProcedencia;
	}

	public void setCodTipoProcedencia(BigDecimal codTipoProcedencia) {
		this.codTipoProcedencia = codTipoProcedencia;
	}

	public String getCodUsuarioSnsTitular() {
		return codUsuarioSnsTitular;
	}

	public void setCodUsuarioSnsTitular(String codUsuarioSnsTitular) {
		this.codUsuarioSnsTitular = codUsuarioSnsTitular;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("AportacionBeneficiarios [codIndicadorDeFarmacia=");
		buffer.append(codIndicadorDeFarmacia);
		buffer.append(", codSubIndicadorDeFarmacia=");
		buffer.append(codSubIndicadorDeFarmacia);
		buffer.append(", codTipoProcedencia=");
		buffer.append(codTipoProcedencia);
		buffer.append(", codUsuarioSnsTitular=");
		buffer.append(codUsuarioSnsTitular);
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}
	
}
