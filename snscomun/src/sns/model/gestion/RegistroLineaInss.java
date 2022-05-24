package sns.model.gestion;


public class RegistroLineaInss extends RegistroInss {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1594876914473680666L;
	private long numRegistro;

	public void setNumRegistro(long numRegistro) {
		this.numRegistro = numRegistro;
	}

	public long getNumRegistro() {
		return numRegistro;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("RegistroLineaInss [numRegistro=");
		buffer.append(numRegistro);
		buffer.append(", getCodOperacion()=");
		buffer.append(getCodOperacion());
		buffer.append(", getIdInssFichero()=");
		buffer.append(getIdInssFichero());
		buffer.append(", getIfiRegistro()=");
		buffer.append(getIfiRegistro());
		buffer.append(", getNumLineasTotales()=");
		buffer.append(getNumLineasTotales());
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

	
}
