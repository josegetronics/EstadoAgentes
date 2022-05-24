package sns.comun.util.mail;

import java.util.Vector;

public class CuerpoMailBean {
	
	public final static String CUERPO_MAIL_SOPORTE = "SOPORTE";
	public final static String CUERPO_MAIL_MINISTERIO = "MINISTERIO";

	private StringBuffer actividadAgentes = new StringBuffer();
	private StringBuffer mensajesBloqueados = new StringBuffer();
	private StringBuffer traspasosBloqueados = new StringBuffer();
	private StringBuffer traspasosBloqueadosBusqueda = new StringBuffer();
	private StringBuffer traspasosBloqueadosNuevo = new StringBuffer();
	private StringBuffer traspasosBloqueadosExtension = new StringBuffer();
	private StringBuffer traspasosDesbloqueados = new StringBuffer();
	private StringBuffer errores = new StringBuffer();
	private Vector ultimaOpSincronaAux = new Vector();
	private Vector ultimaOpAsincronaAux = new Vector();

	public Vector getUltimaOpSincronaAux() {
		return ultimaOpSincronaAux;
	}

	public void setUltimaOpSincronaAux(Vector ultimaOpSincronaAux) {
		this.ultimaOpSincronaAux = ultimaOpSincronaAux;
	}

	public Vector getUltimaOpAsincronaAux() {
		return ultimaOpAsincronaAux;
	}

	public void setUltimaOpAsincronaAux(Vector ultimaOpAsincronaAux) {
		this.ultimaOpAsincronaAux = ultimaOpAsincronaAux;
	}

	public String getActividadAgentes() {
		return actividadAgentes.toString();
	}

	public void setActividadAgentes(String actividadAgentes) {
		this.actividadAgentes = new StringBuffer();
		this.actividadAgentes.append(actividadAgentes);
	}

	public void appendActividadAgentes(String actividadAgentes) {
		this.actividadAgentes.append(actividadAgentes);
	}

	public String getMensajesBloqueados() {
		return mensajesBloqueados.toString();
	}

	public void setMensajesBloqueados(String mensajesBloqueados) {
		this.mensajesBloqueados = new StringBuffer();
		this.mensajesBloqueados.append(mensajesBloqueados);
	}

	public void appendMensajesBloqueados(String mensajesBloqueados) {
		this.mensajesBloqueados.append(mensajesBloqueados);
	}

	public String getTraspasosBloqueados() {
		return traspasosBloqueados.toString();
	}

	public void setTraspasosBloqueados(String traspasosBloqueados) {
		this.traspasosBloqueados = new StringBuffer();
		this.traspasosBloqueados.append(traspasosBloqueados);
	}

	public void appendTraspasosBloqueados(String traspasosBloqueados) {
		this.traspasosBloqueados.append(traspasosBloqueados);
	}

	public String getTraspasosBloqueadosExtension() {
		return traspasosBloqueadosExtension.toString();
	}

	public void setTraspasosBloqueadosExtension(String traspasosBloqueadosExtension) {
		this.traspasosBloqueadosExtension = new StringBuffer();
		this.traspasosBloqueadosExtension.append(traspasosBloqueadosExtension);
	}

	public void appendTraspasosBloqueadosExtension(String traspasosBloqueadosExtension) {
		this.traspasosBloqueadosExtension.append(traspasosBloqueadosExtension);
	}

	public String getTraspasosBloqueadosNuevo() {
		return traspasosBloqueadosNuevo.toString();
	}

	public void setTraspasosBloqueadosNuevo(String traspasosBloqueadosNuevo) {
		this.traspasosBloqueadosNuevo = new StringBuffer();
		this.traspasosBloqueadosNuevo.append(traspasosBloqueadosNuevo);
	}

	public void appendTraspasosBloqueadosNuevo(String traspasosBloqueadosNuevo) {
		this.traspasosBloqueadosNuevo.append(traspasosBloqueadosNuevo);
	}

	public String getTraspasosDesbloqueados() {
		return traspasosDesbloqueados.toString();
	}

	public void setTraspasosDesbloqueados(String traspasosDesbloqueados) {
		this.traspasosDesbloqueados = new StringBuffer();
		this.traspasosDesbloqueados.append(traspasosDesbloqueados);
	}
	
	
	
	public String getTraspasosBloqueadosBusqueda() {
		return traspasosBloqueadosBusqueda.toString();
	}

	public void setTraspasosBloqueadosBusqueda(String traspasosBloqueadosBusqueda) {
		this.traspasosBloqueadosBusqueda = new StringBuffer();
		this.traspasosBloqueadosBusqueda.append(traspasosBloqueadosBusqueda);
	}
	
	public void appendTraspasosDesbloqueados(String traspasosDesbloqueados) {
		this.traspasosDesbloqueados.append(traspasosDesbloqueados);
	}

	public String getErrores() {
		return errores.toString();
	}

	public void setErrores(String errores) {
		this.errores = new StringBuffer();
		this.errores.append(errores);
	}

	public void appendErrores(String errores) {
		this.errores.append(errores);
	}

	public String getCuerpo(String role) {
		if (role.equals(CUERPO_MAIL_SOPORTE)) {
			return actividadAgentes.toString();
		} else {
			StringBuffer cuerpoBuffer = new StringBuffer();
			cuerpoBuffer.append(actividadAgentes);
			cuerpoBuffer.append(mensajesBloqueados);
			cuerpoBuffer.append(traspasosBloqueados);
			cuerpoBuffer.append(traspasosBloqueadosNuevo);
			cuerpoBuffer.append(traspasosBloqueadosBusqueda);
			cuerpoBuffer.append(traspasosBloqueadosExtension);
			cuerpoBuffer.append(traspasosDesbloqueados);
			cuerpoBuffer.append(errores);
			return cuerpoBuffer.toString();
		}
	}

}
