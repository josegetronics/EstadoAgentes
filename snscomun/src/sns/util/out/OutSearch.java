package sns.util.out;

/**
 * Título: Descripcion: Copyright: Copyright (c) 2001 Empresa:
 * 
 * @author
 * @version 1.0
 */

public class OutSearch implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean existe;
	private boolean existeMultiple;
	private boolean error;
	private int codError;
	private String usuario;
	private String[] usuarios;

	// Array de String con los mensajes de error para hacer el setMsg() con el
	// codigo de error que nos devuelva outSerch.getCodError
	public final static String Errores[] = { "Busqueda del usuario. Coincide por dni y busqueda avanzada pero no por naf", "Busqueda del usuario. Coincide por dni pero no por busqueda avanzada",
			"Busqueda del usuario. Coincide por naf y busqueda avanzada pero no por dni", "El naf y el dni pertenecen a usuarios diferentes. No se encuentra el usuario por la busqueda avanzada",
			"No se ha encontrado el usuario con el dni especificado. El usuario encontrado por la busqueda avanzada no tiene el naf especificado" };

	public final static int ERROR_0 = 0;
	public final static int ERROR_1 = 1;
	public final static int ERROR_2 = 2;
	public final static int ERROR_3 = 3;
	public final static int ERROR_4 = 4;

	public final static int NOT_ERROR = -1;

	public OutSearch() {
		this.codError = -1;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public boolean getExiste() {
		return this.existe;
	}

	public void setExisteMultiple(boolean existe) {
		this.existeMultiple = existe;
	}

	public boolean getExisteMultiple() {
		return this.existeMultiple;
	}

	public int getCodError() {
		return this.codError;
	}

	public void setCodError(int codigo) {
		this.codError = codigo;
	}

	public void setUsuario(String user) {
		this.usuario = user;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuarios(String[] usuarios) {
		this.usuarios = usuarios;
	}

	public String[] getUsuarios() {
		return this.usuarios;
	}
}