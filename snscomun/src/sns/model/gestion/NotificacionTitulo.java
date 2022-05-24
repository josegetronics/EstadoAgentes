package sns.model.gestion;

import java.math.BigDecimal;

import sns.model.UsuarioSns;


public class NotificacionTitulo extends GestionGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1462015774735368233L;

	private UsuarioSns usuarioSns;
	private BigDecimal agente;

	public BigDecimal getAgente() {
		return agente;
	}

	public void setAgente(BigDecimal agente) {
		this.agente = agente;
	}

	public void setUsuarioSns(UsuarioSns usuarioSns) {
		this.usuarioSns = usuarioSns;
	}

	public UsuarioSns getUsuarioSns() {
		return usuarioSns;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("NotificacionTitulo [agente=");
		buffer.append(agente);
		buffer.append(", usuarioSns=");
		buffer.append(usuarioSns);
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}
	
}
