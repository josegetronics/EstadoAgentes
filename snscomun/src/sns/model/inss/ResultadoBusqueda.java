package sns.model.inss;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultadoBusqueda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -488491793873849009L;
	private ArrayList candidatos;
	private boolean maximoUsuariosExcedido;
	public ArrayList getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(ArrayList candidatos) {
		this.candidatos = candidatos;
	}
	public boolean isMaximoUsuariosExcedido() {
		return maximoUsuariosExcedido;
	}
	public void setMaximoUsuariosExcedido(boolean maximoUsuariosExcedido) {
		this.maximoUsuariosExcedido = maximoUsuariosExcedido;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoBusqueda [candidatos=");
		buffer.append(candidatos);
		buffer.append(", maximoUsuariosExcedido=");
		buffer.append(maximoUsuariosExcedido);
		buffer.append("]");
		return buffer.toString();
	}

	
}
