package sns.comun.bean;

import org.apache.log4j.Logger;

import sns.comun.config.InicializacionBusqueda;

public class ResultadosCampoBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);	
	
	// Usuarios
	private int noEncontrados                     = 0;
	private int vacios                            = 0;
	//
	private int coincidente                       = 0;
	private int coincidenteMismaCA                = 0;
	private int coincidenteDiferenteCA            = 0;
	//
	private int noCoincidente                     = 0;
	private int noCoincidenteTotal                = 0;
	private int noCoincidenteDniNaf               = 0;
	//
	private int duplicados                        = 0;
	private int duplicadosEstudio                 = 0;
	//
	private int incorrectos                       = 0;
	//
	//
	// Usuarios INSS
	private int noEncontradosInss                 = 0;
	private int vaciosInss                        = 0;
	//
	private int coincidenteInss                   = 0;
	private int coincidenteMismaPersonaInss       = 0;
	//
	private int noCoincidenteInss                 = 0;
	private int noCoincidenteDniNafInss           = 0;
	//
	private int duplicadosInss                    = 0;
	private int duplicadosEstudioInss             = 0;
	//
	private int incorrectosInss                   = 0;
	//	
	//
	// Indica de que tipo es el ultimo elemento revisado
	private String ultimoCaso                     = "";
	

	public ResultadosCampoBean (){
	}

	
	public void incrementarNoEncontrados() {
		this.noEncontrados++;
	}

	public void incrementarVacios() {
		this.vacios++;
	}
	
	public void incrementarCoincidente() {
		this.coincidente++;
	}
	
	public void incrementarCoincidenteMismaCA() {
		this.coincidenteMismaCA++;
	}
	
	public void incrementarCoincidenteDiferenteCA() {
		this.coincidenteDiferenteCA++;
	}
	
	public void incrementarNoCoincidente() {
		this.noCoincidente++;
	}
	
	public void incrementarDuplicados() {
		this.duplicados++;
	}
	public void incrementarIncorrectos() {
		this.incorrectos++;
	}
	public void incrementarDuplicadosEstudio() {
		this.duplicadosEstudio++;
	}
	public void incrementarNoCoincidenteDniNaf() {
		this.noCoincidenteDniNaf++;
	}
	
	public void incrementarNoCoincidenteTotal() {
		this.noCoincidenteTotal++;
	}
	
	
	//
	//
	//
	
	
	public void incrementarNoEncontradosInss() {
		this.noEncontradosInss++;
	}

	public void incrementarVaciosInss() {
		this.vaciosInss++;
	}
	
	public void incrementarCoincidenteInss() {
		this.coincidenteInss++;
	}
	
	public void incrementarCoincidenteMismaPersonaInss() {
		this.coincidenteMismaPersonaInss++;
	}	
	
	public void incrementarNoCoincidenteInss() {
		this.noCoincidenteInss++;
	}
	
	public void incrementarDuplicadosInss() {
		this.duplicadosInss++;
	}
	public void incrementarIncorrectosInss() {
		this.incorrectosInss++;
	}
	public void incrementarDuplicadosEstudioInss() {
		this.duplicadosEstudioInss++;
	}
	
	
	//
	//
	//
	
	
	public int getNoEncontrados() {
		return noEncontrados;
	}

	public void setNoEncontrados(int noEncontrados) {
		this.noEncontrados = noEncontrados;
	}

	public int getVacios() {
		return vacios;
	}

	public void setVacios(int vacios) {
		this.vacios = vacios;
	}

	public int getCoincidente() {
		return coincidente;
	}

	public void setCoincidente(int coincidente) {
		this.coincidente = coincidente;
	}

	public int getCoincidenteMismaCA() {
		return coincidenteMismaCA;
	}

	public void setCoincidenteMismaCA(int coincidenteMismaCA) {
		this.coincidenteMismaCA = coincidenteMismaCA;
	}

	public int getCoincidenteDiferenteCA() {
		return coincidenteDiferenteCA;
	}

	public void setCoincidenteDiferenteCA(int coincidenteDiferenteCA) {
		this.coincidenteDiferenteCA = coincidenteDiferenteCA;
	}

	public int getNoCoincidente() {
		return noCoincidente;
	}

	public void setNoCoincidente(int noCoincidente) {
		this.noCoincidente = noCoincidente;
	}

	public int getDuplicados() {
		return duplicados;
	}

	public void setDuplicados(int duplicados) {
		this.duplicados = duplicados;
	}

	public int getDuplicadosEstudio() {
		return duplicadosEstudio;
	}

	public void setDuplicadosEstudio(int duplicadosEstudio) {
		this.duplicadosEstudio = duplicadosEstudio;
	}

	public int getIncorrectos() {
		return incorrectos;
	}

	public void setIncorrectos(int incorrectos) {
		this.incorrectos = incorrectos;
	}

	public String getUltimoCaso() {
		return ultimoCaso;
	}

	public void setUltimoCaso(String ultimoCaso) {
		this.ultimoCaso = ultimoCaso;
	}

	public int getNoCoincidenteDniNaf() {
		return noCoincidenteDniNaf;
	}

	public void setNoCoincidenteDniNaf(int noCoincidenteDniNaf) {
		this.noCoincidenteDniNaf = noCoincidenteDniNaf;
	}

	public int getNoEncontradosInss() {
		return noEncontradosInss;
	}

	public void setNoEncontradosInss(int noEncontradosInss) {
		this.noEncontradosInss = noEncontradosInss;
	}

	public int getVaciosInss() {
		return vaciosInss;
	}

	public void setVaciosInss(int vaciosInss) {
		this.vaciosInss = vaciosInss;
	}

	public int getCoincidenteInss() {
		return coincidenteInss;
	}

	public void setCoincidenteInss(int coincidenteInss) {
		this.coincidenteInss = coincidenteInss;
	}

	public int getNoCoincidenteInss() {
		return noCoincidenteInss;
	}

	public void setNoCoincidenteInss(int noCoincidenteInss) {
		this.noCoincidenteInss = noCoincidenteInss;
	}

	public int getNoCoincidenteDniNafInss() {
		return noCoincidenteDniNafInss;
	}

	public void setNoCoincidenteDniNafInss(int noCoincidenteDniNafInss) {
		this.noCoincidenteDniNafInss = noCoincidenteDniNafInss;
	}

	public int getDuplicadosInss() {
		return duplicadosInss;
	}

	public void setDuplicadosInss(int duplicadosInss) {
		this.duplicadosInss = duplicadosInss;
	}

	public int getDuplicadosEstudioInss() {
		return duplicadosEstudioInss;
	}

	public void setDuplicadosEstudioInss(int duplicadosEstudioInss) {
		this.duplicadosEstudioInss = duplicadosEstudioInss;
	}

	public int getIncorrectosInss() {
		return incorrectosInss;
	}

	public void setIncorrectosInss(int incorrectosInss) {
		this.incorrectosInss = incorrectosInss;
	}


	public int getCoincidenteMismaPersonaInss() {
		return coincidenteMismaPersonaInss;
	}


	public void setCoincidenteMismaPersonaInss(int coincidenteMismaPersonaInss) {
		this.coincidenteMismaPersonaInss = coincidenteMismaPersonaInss;
	}


	public int getNoCoincidenteTotal() {
		return noCoincidenteTotal;
	}


	public void setNoCoincidenteTotal(int noCoincidenteTotal) {
		this.noCoincidenteTotal = noCoincidenteTotal;
	}
	
	
	
	
}
