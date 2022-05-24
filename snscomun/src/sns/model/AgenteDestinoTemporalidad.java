package sns.model;

public class AgenteDestinoTemporalidad {

	private String codAgente;
	private String codPrestacionServicioTemporal;
	private int codTipoFinTemporalidad;

	public AgenteDestinoTemporalidad (String codAgente,String codPrestacionServicioTemporal) {
		this.codAgente=codAgente;
		this.codPrestacionServicioTemporal=codPrestacionServicioTemporal;
	}
	
	/**
	 * 
	 * @param codAgente al que va a notificar
	 * @param codPrestacionServicioTemporal de la comunidad dentro del nodo fin de temporalidad 
	 * @param codTipoFinTemporalidad
	 */
	public AgenteDestinoTemporalidad (String codAgente,String codPrestacionServicioTemporal,int codTipoFinTemporalidad) {
		this.codAgente=codAgente;
		this.codPrestacionServicioTemporal=codPrestacionServicioTemporal;
		this.codTipoFinTemporalidad=codTipoFinTemporalidad;
	}

	public int getCodTipoFinTemporalidad() {
		return codTipoFinTemporalidad;
	}

	public void setCodTipoFinTemporalidad(int codTipoFinTemporalidad) {
		this.codTipoFinTemporalidad = codTipoFinTemporalidad;
	}

	public String getCodAgente() {
		return codAgente;
	}
	public void setCodAgente(String codAgente) {
		this.codAgente = codAgente;
	}

	public String getCodPrestacionServicioTemporal() {
		return codPrestacionServicioTemporal;
	}

	public void setCodPrestacionServicioTemporal(String codPrestacionServicioTemporal) {
		this.codPrestacionServicioTemporal = codPrestacionServicioTemporal;
	}

}
