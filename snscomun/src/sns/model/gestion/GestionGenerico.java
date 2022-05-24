package sns.model.gestion;

import java.io.Serializable;

public class GestionGenerico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6506816153905654072L;
	private String accion;
	private String id;
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
