package snsadmin.estadisticas.bean.report;

import java.io.Serializable;
import java.util.ArrayList;
import sns.logging.Logger;


/**
 * Contiene toda la informacion de un informe.
 *
 */
public class ReportFileBean implements Serializable {

  // Fecha inicial del informe
  private String initialDate;
  // Fecha final del informe
  private String finalDate;
  // Identificador del servicio
  private String reportIdServicio;


  //plantilla excel a descargar
  private String plantilla;
  private String rutaPlantilla;

  // Nombre del fichero Excel
  private String nombreFicheroUnico;
  // Direccion de la carpeta donde se almacenara el fichero.
  private String ruta ;

  // Numero de paginas
  private int numPaginas;
  // Paginas que contiene el informe
  private ArrayList pages;



  public void setNumPaginas(int numPaginas) {
    this.numPaginas = numPaginas;
  }

  public void setPages(ArrayList pages) {
    this.pages = pages;
  }

  public int getNumPaginas() {
    return numPaginas;
  }

  public ArrayList getPages() {
    return pages;
  }

  public String getPlantilla() {
    return plantilla;
  }

  public String getRuta() {
    return ruta;
  }

  public String getRutaPlantilla() {
    return rutaPlantilla;
  }

  public void setNombreFicheroUnico(String nombreFicheroUnico) {
    this.nombreFicheroUnico = nombreFicheroUnico;
  }

  public void setPlantilla(String plantilla) {
    this.plantilla = plantilla;
  }

  public void setRuta(String ruta) {
    this.ruta = ruta;
  }

  public void setRutaPlantilla(String rutaPlantilla) {
    this.rutaPlantilla = rutaPlantilla;
  }

  public void setFinalDate(String finalDate) {
    this.finalDate = finalDate;
  }

  public void setInitialDate(String initialDate) {
    this.initialDate = initialDate;
  }

  public void setReportIdServicio(String reportIdServicio) {
    this.reportIdServicio = reportIdServicio;
  }

  public String getNombreFicheroUnico() {
    return nombreFicheroUnico;
  }

  public String getFinalDate() {
    return finalDate;
  }

  public String getInitialDate() {
    return initialDate;
  }

  public String getReportIdServicio() {
    return reportIdServicio;
  }


  public void view (){

    Logger logger = new Logger("snsadmin.estadisticas.bean.report.ReportFileBean.view");
    logger.debug("INICIO");
    logger.debug("plantilla: " + plantilla + ", rutaPlantilla: " + rutaPlantilla);
    logger.debug("nombreFicheroUnico: " + nombreFicheroUnico + ", ruta: " +  ruta);
    logger.debug("");
    logger.debug("numPaginas: " + numPaginas);
    //
    if (pages != null) {
      logger.debug("pages.size(): " + pages.size());
      //
      for (int i = 0; i < pages.size(); i++) {
        PageBean page = (PageBean) pages.get(i);
        if (page != null) {
          page.view();
        }
      }
    }
    else {
      logger.debug("tables es null");
    }
    logger.debug("FIN");
  }



}
