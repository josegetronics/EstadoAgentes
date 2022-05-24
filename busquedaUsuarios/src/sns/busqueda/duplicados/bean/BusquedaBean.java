package sns.busqueda.duplicados.bean;

import org.apache.log4j.Logger;

import sns.comun.config.InicializacionBusqueda;

public class BusquedaBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	private String cadena       = "";
	private boolean encontrado  = false;
	
	public BusquedaBean () {
		this.cadena        = "";
		this.encontrado    = false;
	}
	
	public BusquedaBean (String cadena) {
		this.cadena        = cadena;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public boolean isEncontrado() {
		return encontrado;
	}

	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}
	
	public void write () {
		logger.debug("cadena: " + cadena + ", encontrado: " + encontrado);
	}
	
}
