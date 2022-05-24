package snsadmin.estadisticas.bean.report;

import java.util.ArrayList;
import sns.logging.Logger;


/**
 * Contiene la informacion de una pagina para una hoja Excel.
 *
 */
public class PageBean {

  // Numero de filas de la Hoja Excel
  private int numFilasHojaExcel;
  // Nombre de la Hoja
  private String nombreHoja;
  // Tablas q contiene la pagina
  private ArrayList tables;
  // Numero de los tablas
  private int numTables;
  // Posicion de la pagina dentro del Excel
  private int positionBook;
  // Posicion de la celda actual dentro de la hoja
  private int positionCelda;


  public String getNombreHoja() {
    return nombreHoja;
  }

  public void setNumFilasHojaExcel(int numFilasHojaExcel) {
    this.numFilasHojaExcel = numFilasHojaExcel;
  }

  public void setNombreHoja(String nombreHoja) {
    this.nombreHoja = nombreHoja;
  }

  public void setTables(ArrayList tables) {
    this.tables = tables;
  }

  public void setNumTables(int numTables) {
    this.numTables = numTables;
  }

  public void setPositionBook(int positionBook) {
    this.positionBook = positionBook;
  }

  public void setPositionCelda(int positionCelda) {
    this.positionCelda = positionCelda;
  }

  public int getNumFilasHojaExcel() {
    return numFilasHojaExcel;
  }

  public ArrayList getTables() {
    return tables;
  }

  public int getNumTables() {
    return numTables;
  }

  public int getPositionBook() {
    return positionBook;
  }

  public int getPositionCelda() {
    return positionCelda;
  }

  public void view() {
    //
    Logger logger = new Logger("snsadmin.estadisticas.bean.report.PageBean.view");
    //
    logger.debug("nombreHoja: " + nombreHoja + ", numFilasHojaExcel: " + numFilasHojaExcel);
    logger.debug("numTables: " + numTables + ", positionBook: " + positionBook);
    //
    if(tables != null){
      logger.debug("table.size(): " + tables.size());
      //
      for(int i=0 ; i<tables.size() ; i++){
        TableBean tableBean = (TableBean) tables.get(i);
        if(tableBean != null){
          tableBean.view();
        }
      }
    }
    else{
      logger.debug("tables es null");
    }
  }

}
