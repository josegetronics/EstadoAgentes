package sns.comun.bean.busqueda;

import org.apache.log4j.Logger;

import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class BusquedaAproxResultadoBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	private int camposCoincidentes            = 0;
	private String diferencia                 = "";
	private String camposIgualesAproximacion  = "";
	private String criterio                   = "";
	private int porcentaje                    = 0;
	//
	private int camposCoincidentesCompleto    = 0;
	private String criterioCompleto           = "";
	private int porcentajeCompleto            = 0;
	//
	private String revisionNombre             = "";
	//
	private NombreComparacionBean nombreComparacionBean = null;

	private boolean esMismaPersona            = false;
	
	
	
	public BusquedaAproxResultadoBean (){
		nombreComparacionBean           = new NombreComparacionBean ();
	}

	
	public String toString () {
		//
		StringBuffer str = new StringBuffer ();
		str.append(camposCoincidentes        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(camposIgualesAproximacion + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(diferencia                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(criterio                  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(porcentaje                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(criterioCompleto          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(porcentajeCompleto        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(revisionNombre            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//str.append(esMismaPersona            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}

	
	public String toString2 () {
		//
		StringBuffer str = new StringBuffer ();
		str.append("camposCoincidentes: " + camposCoincidentes);
		str.append(", camposIgualesAproximacion: " + camposIgualesAproximacion);
		str.append(", diferencia: " + diferencia);
		str.append(", criterio: " + criterio);
		str.append(", porcentaje: " + porcentaje);
		str.append(", criterioCompleto: " + criterioCompleto);
		str.append(", porcentajeCompleto: " + porcentajeCompleto);
		str.append(", revisionNombre: " + revisionNombre);
		//
		return str.toString();
	}

	
	public void view (){
		logger.debug("camposCoincidentes: " + camposCoincidentes + ", camposIgualesAproximacion: " + camposIgualesAproximacion);
		logger.debug("diferencia: " + diferencia + ", criterio: " + criterio + ", porcentaje: " + porcentaje);
		logger.debug("criterioCompleto: " + criterioCompleto + ", porcentajeCompleto: " + porcentajeCompleto);
		logger.debug("revisionNombre: " + revisionNombre);
	}
	
	
	public int getPorcentajeCompleto() {
		return porcentajeCompleto;
	}


	public void setPorcentajeCompleto(int porcentajeCompleto) {
		this.porcentajeCompleto = porcentajeCompleto;
	}


	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public int getCamposCoincidentes() {
		return camposCoincidentes;
	}

	public void setCamposCoincidentes(int camposCoincidentes) {
		this.camposCoincidentes = camposCoincidentes;
	}

	public String getCamposIgualesAproximacion() {
		return camposIgualesAproximacion;
	}

	public void setCamposIgualesAproximacion(String camposIgualesAproximacion) {
		this.camposIgualesAproximacion = camposIgualesAproximacion;
	}
	
	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getCriterioCompleto() {
		return criterioCompleto;
	}

	public void setCriterioCompleto(String criterioCompleto) {
		this.criterioCompleto = criterioCompleto;
	}

	public int getCamposCoincidentesCompleto() {
		return camposCoincidentesCompleto;
	}

	public void setCamposCoincidentesCompleto(int camposCoincidentesCompleto) {
		this.camposCoincidentesCompleto = camposCoincidentesCompleto;
	}

	public String getRevisionNombre() {
		return revisionNombre;
	}

	public void setRevisionNombre(String revisionNombre) {
		this.revisionNombre = revisionNombre;
	}

	public NombreComparacionBean getNombreComparacionBean() {
		return nombreComparacionBean;
	}

	public void setNombreComparacionBean(NombreComparacionBean nombreComparacionBean) {
		this.nombreComparacionBean = nombreComparacionBean;
	}


	public boolean isEsMismaPersona() {
		return esMismaPersona;
	}

	public void setEsMismaPersona(boolean esMismaPersona) {
		this.esMismaPersona = esMismaPersona;
	}	
	
}
