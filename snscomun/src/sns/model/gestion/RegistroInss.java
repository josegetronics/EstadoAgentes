package sns.model.gestion;

import java.math.BigDecimal;

import sns.model.inss.ifi.IfiFicheroBean;

public class RegistroInss extends GestionGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515498659356367520L;

	private Integer codOperacion;
	private BigDecimal idInssFichero;
	private IfiFicheroBean ifiRegistro;
	private long numLineasTotales;

	
	public long getNumLineasTotales() {
		return numLineasTotales;
	}
	public void setNumLineasTotales(long numLineasTotales) {
		this.numLineasTotales = numLineasTotales;
	}
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
	public IfiFicheroBean getIfiRegistro() {
		return ifiRegistro;
	}
	public void setIfiRegistro(IfiFicheroBean ifiRegistro) {
		this.ifiRegistro = ifiRegistro;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RegistroInss [idInssFichero=");
		buffer.append(idInssFichero);
		buffer.append(", ifiRegistro=");
		buffer.append(ifiRegistro.toString());
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}
	
	
}
