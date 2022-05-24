package snsadmin.estadisticas.bean.report;

import java.util.HashMap;
import sns.logging.Logger;


/**
 * Contiene la informacion para una tabla de una hoja Excel.
 *
 */
public class TableBean {

  // Titulos de las columnas para excel separados por ';'
  private String cabecera;
  // Titulo de la tabla
  private String tituloTabla;
  // Posicion de la fila de la cabecera de la tabla dentro de la pagina Excel
  private int numFilaCabecera;
  // Posicion de la fila inicial de la tabla dentro de la pagina Excel
  private int numFilaInicial;
  // Posicion de la fila inicial de la tabla dentro de la pagina Excel
  private int posicionHojaExcell;
  // tipo
  private String tipo;
  // Codigo de la consulta
  private String select;
  // Parametros de la consulta
  private HashMap parametrosSelect;

  // Indica si la ultima fila tiene algun tipo de funcion de SUM, ...
  // Se indica entre ';'
  private String functions;
  // Indica las posiciones de las funciones
  // Se indica entre ';'
  private String positionFunctions;


  // BEAN PARA ESTILOS


  public String getSelect() {
    return select;
  }

  public String getTipo() {
    return tipo;
  }

  public String getCabecera() {
    return cabecera;
  }

  public String getTituloTabla() {
    return tituloTabla;
  }

  public int getNumFilaInicial() {
    return numFilaInicial;
  }

  public int getNumFilaCabecera() {
    return numFilaCabecera;
  }

  public int getPosicionHojaExcell() {
    return posicionHojaExcell;
  }

  public void setParametrosSelect(HashMap parametrosSelect) {
    this.parametrosSelect = parametrosSelect;
  }

  public void setSelect(String select) {
    this.select = select;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setCabecera(String cabecera) {
    this.cabecera = cabecera;
  }

  public void setTituloTabla(String tituloTabla) {
    this.tituloTabla = tituloTabla;
  }

  public void setNumFilaInicial(int numFilaInicial) {
    this.numFilaInicial = numFilaInicial;
  }

  public void setNumFilaCabecera(int numFilaCabecera) {
    this.numFilaCabecera = numFilaCabecera;
  }

  public void setPosicionHojaExcell(int posicionHojaExcell) {
    this.posicionHojaExcell = posicionHojaExcell;
  }

  public void setFunctions(String functions) {
    this.functions = functions;
  }

  public void setPositionFunctions(String positionFunctions) {
    this.positionFunctions = positionFunctions;
  }

  public HashMap getParametrosSelect() {
    return parametrosSelect;
  }

  public String getFunctions() {
    return functions;
  }

  public String getPositionFunctions() {
    return positionFunctions;
  }

  public void view (){
    Logger logger = new Logger("snsadmin.estadisticas.bean.report.TableBean.view");
    logger.debug("tituloTabla: " + tituloTabla);
    logger.debug("cabecera: " + cabecera);
    logger.debug("numFilaCabecera: " + numFilaCabecera + ", numFilaInicial: " + numFilaInicial + ", posicionHojaExcell: " + posicionHojaExcell);
    logger.debug("select: " + select);
    logger.debug("parametrosSelect: " + parametrosSelect);
    logger.debug("functions: " + functions + ", positionFunctions: " + positionFunctions);
  }


}
