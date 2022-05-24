package sns.model.inss;

import java.io.Serializable;

public class CriteriosBusqueda implements Serializable {

	
	public CriteriosBusqueda copia() {
		CriteriosBusqueda criteriosBusqueda=new CriteriosBusqueda();
		criteriosBusqueda.setDniNie(dniNie);
		criteriosBusqueda.setCodUsuarioSns(codUsuarioSns);
		criteriosBusqueda.setNaf(naf);
		criteriosBusqueda.setNafTitular(nafTitular);
		criteriosBusqueda.setPasaporte(pasaporte);
		return criteriosBusqueda;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2292323045852086161L;
	private String codUsuarioSns;
	private String dniNie;
	private String pasaporte;
	private String naf;
	private String nafTitular;
	private String codSituacion;
	private String ipf;
	
	/**
	 * para saber si hay que sacar todos o solo los que se indique en esta variable
	 */
	private int numeroMaximoResultados;
	
	
	public String getIpf() {
		return ipf;
	}
	public void setIpf(String ipf) {
		this.ipf = ipf;
	}
	public int getNumeroMaximoResultados() {
		return numeroMaximoResultados;
	}
	public void setNumeroMaximoResultados(int numeroMaximoResultados) {
		this.numeroMaximoResultados = numeroMaximoResultados;
	}
	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}
	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}
	public String getDniNie() {
		return dniNie;
	}
	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}
	public String getPasaporte() {
		return pasaporte;
	}
	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}
	public String getNaf() {
		return naf;
	}
	public void setNaf(String naf) {
		this.naf = naf;
	}
	public String getNafTitular() {
		return nafTitular;
	}
	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}
	
	public String getCodSituacion() {
		return codSituacion;
	}
	public void setCodSituacion(String codSituacion) {
		this.codSituacion = codSituacion;
	}
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("CriteriosBusqueda [codUsuarioSns=");
		buffer.append(codUsuarioSns);
		buffer.append(", ipf=");
		buffer.append(ipf);
		buffer.append(", dniNie=");
		buffer.append(dniNie);
		buffer.append(", naf=");
		buffer.append(naf);
		buffer.append(", nafTitular=");
		buffer.append(nafTitular);
		buffer.append(", pasaporte=");
		buffer.append(pasaporte);
		buffer.append(", codSituacion=");
		buffer.append(codSituacion);
		buffer.append("]");
		return buffer.toString();
	}
	
	
}
