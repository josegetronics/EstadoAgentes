package sns.model.gestion;

import java.math.BigDecimal;

public class EventoInss extends GestionGenerico {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6571497022108708789L;
	private Integer codOperacion;
	private BigDecimal idInssFichero;
	
	

	public Integer getCodOperacion() {
		return codOperacion;
	}
	public void setCodOperacion(Integer codOperacion) {
		this.codOperacion = codOperacion;
	}
	public BigDecimal getIdInssFichero() {
		return idInssFichero;
	}
	public void setIdInssFichero(BigDecimal idInssFichero) {
		this.idInssFichero = idInssFichero;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("EventoInss [idInssFichero=");
		buffer.append(idInssFichero);
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

}
