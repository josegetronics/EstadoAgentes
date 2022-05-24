package sns.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSnsTemporal extends UsuarioSnsExt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3091390383817661258L;
	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(UsuarioSnsTemporal.class);

	public static final int INICIO_TEMPORALIDAD=1;
	public static final int FIN_TEMPORALIDAD=2;
	
	private int accion;
	private Date fechaInicioTemporalidad;
	private Date fechaFinEstimadaTemporalidad;
	private Date fechaFinTemporalidad;
	
	private String codidssalud;

	private String agenteTemporal;

	private List listaAgentesDestinoInicioTemporalidad=new ArrayList();
	private List listaAgentesDestinoFinTemporalidad=new ArrayList();

	
	public String getCodidssalud() {
		return codidssalud;
	}
	public void setCodidssalud(String codidssalud) {
		this.codidssalud = codidssalud;
	}
	public String getAgenteTemporal() {
		return agenteTemporal;
	}
	public void setAgenteTemporal(String agenteTemporal) {
		this.agenteTemporal = agenteTemporal;
	}

	public void addListaAgentesDestinoFinTemporalidad(AgenteDestinoTemporalidad agenteDestinoTemporalidad) {
		logger.debug("ListaAgentesDestinoFinTemporalidad añadiendo el codAgente ->" + agenteDestinoTemporalidad.getCodAgente());
		listaAgentesDestinoFinTemporalidad.add(agenteDestinoTemporalidad);
	}
	
	public List getListaAgentesDestinoFinTemporalidad() {
		return listaAgentesDestinoFinTemporalidad;
	}
	public void setListaAgentesDestinoFinTemporalidad(List listaAgentesDestinoFinTemporalidad) {
		this.listaAgentesDestinoFinTemporalidad = listaAgentesDestinoFinTemporalidad;
	}

	public int getAccion() {
		return accion;
	}
	public void setAccion(int accion) {
		this.accion = accion;
	}
	public Date getFechaInicioTemporalidad() {
		return fechaInicioTemporalidad;
	}
	public void setFechaInicioTemporalidad(Date fechaInicioTemporalidad) {
		this.fechaInicioTemporalidad = fechaInicioTemporalidad;
	}
	public Date getFechaFinEstimadaTemporalidad() {
		return fechaFinEstimadaTemporalidad;
	}
	public void setFechaFinEstimadaTemporalidad(Date fechaFinEstimadaTemporalidad) {
		this.fechaFinEstimadaTemporalidad = fechaFinEstimadaTemporalidad;
	}
	public Date getFechaFinTemporalidad() {
		return fechaFinTemporalidad;
	}
	public void setFechaFinTemporalidad(Date fechaFinTemporalidad) {
		this.fechaFinTemporalidad = fechaFinTemporalidad;
	}
	public void setListaAgentesDestinoInicioTemporalidad(List listaAgentesDestinoInicioTemporalidad) {
		this.listaAgentesDestinoInicioTemporalidad = listaAgentesDestinoInicioTemporalidad;
	}
	public List getListaAgentesDestinoInicioTemporalidad() {
		return listaAgentesDestinoInicioTemporalidad;
	}
	public void addListaAgentesDestinoInicioTemporalidad(AgenteDestinoTemporalidad agenteDestinoTemporalidad) {
		logger.debug("ListaAgentesDestinoInicioTemporalidad añadiendo el codAgente ->" + agenteDestinoTemporalidad.getCodAgente());
		listaAgentesDestinoInicioTemporalidad.add(agenteDestinoTemporalidad);
	}
	
}
