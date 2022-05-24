package sns.comun.bean;

public class InfoCambiosTraspasosBean {


	  String camposAfectados;
	  String camposAnteriores;
	  String valoresSolicitud;
	  String valoresActuales;

	  String codUsuarioSns;
	  int caActual      = -1;
	  int caAnterior    = -1 ;
	  int numeroCambios = -1 ;
	  private String grupo;


	  public InfoCambiosTraspasosBean() {
	  }


	  public int getCaActual() {
	    return caActual;
	  }

	  public int getCaAnterior() {
	    return caAnterior;
	  }

	  public String getCamposAnteriores() {
	    return camposAnteriores;
	  }

	  public int getNumeroCambios() {
	    return numeroCambios;
	  }

	  public String getCamposAfectados() {
	    return camposAfectados;
	  }

	  public String getCodUsuarioSns() {
	    return codUsuarioSns;
	  }

	  public void setValoresSolicitud(String valoresSolicitud) {
	    this.valoresSolicitud = valoresSolicitud;
	  }

	  public void setCaActual(int caActual) {
	    this.caActual = caActual;
	  }

	  public void setCaAnterior(int caAnterior) {
	    this.caAnterior = caAnterior;
	  }

	  public void setCamposAnteriores(String camposAnteriores) {
	    this.camposAnteriores = camposAnteriores;
	  }

	  public void setNumeroCambios(int numeroCambios) {
	    this.numeroCambios = numeroCambios;
	  }

	  public void setCamposAfectados(String camposAfectados) {
	    this.camposAfectados = camposAfectados;
	  }

	  public void setCodUsuarioSns(String codUsuarioSns) {
	    this.codUsuarioSns = codUsuarioSns;
	  }

	  public void setGrupo(String grupo) {
	    this.grupo = grupo;
	  }

	  public void setValoresActuales(String valoresActuales) {
	    this.valoresActuales = valoresActuales;
	  }

	  public String getValoresSolicitud() {
	    return valoresSolicitud;
	  }

	  public String getGrupo() {
	    return grupo;
	  }

	  public String getValoresActuales() {
	    return valoresActuales;
	  }

	}

