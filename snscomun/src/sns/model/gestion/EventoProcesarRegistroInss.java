package sns.model.gestion;


public class EventoProcesarRegistroInss extends EventoInss {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5138789012321562353L;
	private String codTipoMovimiento;
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RegistroInss [idInssFichero=");
		buffer.append(super.getIdInssFichero());
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}
	public void setCodTipoMovimiento(String codTipoMovimiento) {
		this.codTipoMovimiento = codTipoMovimiento;
	}
	public String getCodTipoMovimiento() {
		return codTipoMovimiento;
	}
	
	
}
