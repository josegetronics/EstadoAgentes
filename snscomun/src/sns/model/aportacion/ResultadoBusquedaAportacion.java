package sns.model.aportacion;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResultadoBusquedaAportacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8169387762028924786L;
	private boolean encontrado;
	private String codIndicadorDeFarmacia;
	private String codSubIndicadorDeFarmacia;
	private String tipoIdentificacion;
	private BigDecimal codTipoProcedencia;
	
	public BigDecimal getCodTipoProcedencia() {
		return codTipoProcedencia;
	}
	public void setCodTipoProcedencia(BigDecimal codTipoProcedencia) {
		this.codTipoProcedencia = codTipoProcedencia;
	}
	public boolean isEncontrado() {
		return encontrado;
	}
	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}
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
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoBusquedaAportacion [codIndicadorDeFarmacia=");
		buffer.append(codIndicadorDeFarmacia);
		buffer.append(", codSubIndicadorDeFarmacia=");
		buffer.append(codSubIndicadorDeFarmacia);
		buffer.append(", codTipoProcedencia=");
		buffer.append(codTipoProcedencia);
		buffer.append(", encontrado=");
		buffer.append(encontrado);
		buffer.append(", tipoIdentificacion=");
		buffer.append(tipoIdentificacion);
		buffer.append("]");
		return buffer.toString();
	}

	
}
