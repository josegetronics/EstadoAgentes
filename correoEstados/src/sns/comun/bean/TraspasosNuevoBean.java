package sns.comun.bean;

import java.util.HashMap;

public class TraspasosNuevoBean {
	
	//Tres o mas Modificaciones
	private HashMap hModificaciones;
	//Tres o mas Traspasos
	private HashMap hTraspasos;
	//Total traspasos
	private HashMap hTotalTraspasos;
	//Total Desbloqueos realizados
	private HashMap hDesbloqueos;
	//Total Bloqueos pendientes
	private HashMap hBloqueos;
	//Usuarios nuevos
	private HashMap hUsuarios;



	public HashMap gethUsuarios() {
		return hUsuarios;
	}

	public void sethUsuarios(HashMap hUsuarios) {
		this.hUsuarios = hUsuarios;
	}

	public HashMap gethBloqueos() {
		return hBloqueos;
	}

	public void sethBloqueos(HashMap hBloqueos) {
		this.hBloqueos = hBloqueos;
	}

	public HashMap gethDesbloqueos() {
		return hDesbloqueos;
	}

	public void sethDesbloqueos(HashMap hDesbloqueos) {
		this.hDesbloqueos = hDesbloqueos;
	}

	public HashMap gethTraspasos() {
		return hTraspasos;
	}

	public void sethTraspasos(HashMap hTraspasos) {
		this.hTraspasos = hTraspasos;
	}

	public HashMap gethTotalTraspasos() {
		return hTotalTraspasos;
	}

	public void sethTotalTraspasos(HashMap hTotalTraspasos) {
		this.hTotalTraspasos = hTotalTraspasos;
	}

	public HashMap gethModificaciones() {
		return hModificaciones;
	}

	public void sethModificaciones(HashMap hModificaciones) {
		this.hModificaciones = hModificaciones;
	}
	
	

}

