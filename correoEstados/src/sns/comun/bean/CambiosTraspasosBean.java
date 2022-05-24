package sns.comun.bean;

import java.util.HashMap;

public class CambiosTraspasosBean {
	
	private int numeroRegistrosXml = 0;
	  private String nombreFichero;
	  private String nombreFicheroCodSns;
	  private String pathFichero;
	  private HashMap MapInformacion;


	  public HashMap getMapInformacion() {
	    return MapInformacion;
	  }

	  public String getPathFichero() {
	    return pathFichero;
	  }

	  public void setNombreFichero(String nombreFichero) {
	    this.nombreFichero = nombreFichero;
	  }

	  public void setMapInformacion(HashMap MapInformacion) {
	    this.MapInformacion = MapInformacion;
	  }

	  public void setPathFichero(String pathFichero) {
	    this.pathFichero = pathFichero;
	  }

	  public void setNumeroRegistrosXml(int numeroRegistrosXml) {
	    this.numeroRegistrosXml = numeroRegistrosXml;
	  }

	  public void setNombreFicheroCodSns(String nombreFicheroCodSns) {
	    this.nombreFicheroCodSns = nombreFicheroCodSns;
	  }

	  public String getNombreFichero() {
	    return nombreFichero;
	  }

	  public int getNumeroRegistrosXml() {
	    return numeroRegistrosXml;
	  }

	  public String getNombreFicheroCodSns() {
	    return nombreFicheroCodSns;
	  }

	  public CambiosTraspasosBean() {
	  }

}
