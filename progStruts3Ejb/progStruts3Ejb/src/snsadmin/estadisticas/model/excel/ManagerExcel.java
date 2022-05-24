package snsadmin.estadisticas.model.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import gasai.bd.AccesoBD;
import gasai.util.out.Salida;
import java.io.FileInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFFont;
import java.util.StringTokenizer;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashMap;
import snsadmin.estadisticas.bean.report.ReportFileBean;
import sns.config.Constantes;
import sns.config.Inicializacion;
import snsadmin.estadisticas.bean.report.TableBean;
import gasai.util.Misc;
import java.util.ArrayList;
import snsadmin.estadisticas.bean.report.PageBean;
import java.sql.ResultSetMetaData;
import sns.logging.Logger;
import sns.bd.AccesoBd;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import sns.bd.AccesoBdIntercambiador;


/**
 *
 *
 */
public class ManagerExcel {


  // Libro de Excel
  private HSSFWorkbook hSSFWorkbook = null;

  // Estilos de la hoja
  private HSSFCellStyle cs1, cs2, cs3, cs4;


  /**
   * Genera un fichero Excel a partir de un ReportFileBean
   *
   * @param reportFileBean ReportFileBean Informacion completa del informe.
   * @return Salida
   */
  public Salida generateExcelFile(AccesoBD accesoBd, ReportFileBean reportFileBean, String proceso) {

    HSSFSheet hoja = null;
    HSSFRow   fila = null;
    HSSFCell celda = null;

    FileInputStream  fileInputStream   = null;
    FileOutputStream fileOutputStream  = null;
    POIFSFileSystem  pOIFSFileSystem   = null;

    Salida salida = new Salida();

    Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.generateExcelFile");

    try {
      logger.debug("INICIO");

      if(reportFileBean == null){
        throw new Exception ("El reportFileBean es null");
      }

      //////////////////////////////////////////////////////////////////////////
      // Abrimos la plantilla si hay plantilla
      if (!Misc.esVacio(reportFileBean.getPlantilla())) {
        fileInputStream = new FileInputStream(reportFileBean.getRutaPlantilla() + reportFileBean.getPlantilla());
        pOIFSFileSystem = new POIFSFileSystem(fileInputStream);
      }
      else {
        pOIFSFileSystem = new POIFSFileSystem();
      }
      // Workbook
      hSSFWorkbook = new HSSFWorkbook(pOIFSFileSystem);
      //////////////////////////////////////////////////////////////////////////

      // PAGINAS
      ArrayList pages = reportFileBean.getPages();
      if (pages != null) {
        logger.debug("pages.size(): " + pages.size());
        //
        // Se recorren las paginas del reportFileBean
        for (int i=0; i<pages.size(); i++) {
          int paginaPintada = i+1;
          PageBean page = (PageBean) pages.get(i);
          //
          if (page != null) {
            //
            ArrayList tables = page.getTables();
            // TABLAS
            if (tables != null) {
              //logger.debug("table.size(): " + tables.size());
              //
              // Se crea la Hoja
              hoja = createPage(page.getNombreHoja(), page.getPositionBook());
              // Se modifican los estilos segun la hoja
              // Por si por properties se insertaran los estilos
              salida = createStyles(hoja, fila, celda);
              //
              // Se recorren las tablas de las paginas
              for (int j=0; j<tables.size(); j++) {
                int tablaPintada = j+1;
                TableBean table = (TableBean) tables.get(j);
                if (table != null) {
                  //
                  //logger.debug("Pintar la Tabla: " + tablaPintada);
                  //logger.debug("Numero Lineas: "   + page.getPositionCelda());

                  // Se comprueba que la fila donde se insertara la cabecera de la siguiente tabla
                  // no es menor, que el puntero de la fila actual de la hoja.
                  //
                  // posicionLinea  = Posicion que ocuparia la cabecera.
                  int posicionLinea = table.getNumFilaCabecera();
                  // countLines     = Posicion de la celda actual dentro de la hoja.
                  int countLines    = page.getPositionCelda();
                  //
                  if (countLines >= posicionLinea) {
                    // La posicion de la fila de la cabecera es menor, se modifica la posicion de la cabecera
                    // y de la fila inicial de la tabla.
                    logger.debug("Se cambia el numero de linea para la cabecera");
                    countLines++;
                    table.setNumFilaCabecera(countLines);
                    countLines++;
                    table.setNumFilaInicial(countLines);
                  }
                  //
                  if (!salida.getError()) {
                    // Se pinta la tabla
                    salida = managerDrawTable(accesoBd, page, table, hoja, fila, celda); //

                    if(salida.getError()){
                      logger.error("Tabla: " + tablaPintada + ", mensaje: " + salida.getMsg());
                      throw new Exception (salida.getExcepcion());
                    }
                  }
                  logger.debug("Pintada la Tabla: " + tablaPintada + ", Hoja: " + page.getNombreHoja());
                }
                else {
                  logger.debug("table es null, paginaPintada: " + paginaPintada + ", tablaPintada: " + tablaPintada);
                }
              }
            }
            else {
              logger.debug("tables es null");
            }
          }
          else {
            logger.debug("page es null");
          }
        }
      }
      else {
        logger.debug("pages es null");
      }
      // Se escribe el fichero
      if (!salida.getError()) {
        logger.debug("Almacena: " + reportFileBean.getRuta() + reportFileBean.getNombreFicheroUnico());
        fileOutputStream = new FileOutputStream(reportFileBean.getRuta() + proceso + reportFileBean.getNombreFicheroUnico());
        hSSFWorkbook.write(fileOutputStream);
        salida.setOk();
      }
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
      e.printStackTrace();
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    finally {
      try {
        if (fileInputStream != null) {
          fileInputStream.close();
        }
      }
      catch(Exception e1){}
      try{
        if(fileOutputStream != null){
          fileOutputStream.close();
        }
      }
      catch(Exception e1){}
    }
    return salida;
  }


  /**
   * Se crea una nueva hoja del fichero Excel
   *
   * @param nombre String Nombre de la hoja
   * @param numeroHoja int Posicion de la hoja dentro del libro excel
   * @return HSSFSheet Hoja del fichero excel
   */
  private HSSFSheet createPage(String name, int pageNumber) {

    Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.createPage");

    logger.debug("INICIO");

    HSSFSheet hojaAux;

    //logger.debug("numeroHoja: " + pageNumber);

    //Obtiene una hoja del libro de excel de las tres por defecto que vienen
    if (pageNumber < 3) {
      hojaAux = hSSFWorkbook.getSheetAt(pageNumber);
      //logger.debug("Se toma una hoja de las 3 ya existentes");
    }
    else { //Crea una nueva hoja en el libro
      hojaAux = hSSFWorkbook.createSheet(name);
      //logger.debug("Crea una nueva hoja");
    }
    //Se pone el nombre a la hoja
    hSSFWorkbook.setSheetName(pageNumber, name);
    logger.debug("FIN");

    return hojaAux;
  }


  /**
   * Se establecen los estilos de la hoja Excel
   *
   * @param hoja HSSFSheet Hoja del fichero excel
   * @param fila HSSFRow Fila de la hoja Excel
   * @param celda HSSFCell Celda del fichero excel
   * @return Salida
   */
  private Salida createStyles(HSSFSheet hoja, HSSFRow fila, HSSFCell celda) {

      Salida salida = new Salida();

      Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.createStyles");

      try {
         // creamos 3 tipos de estilos de celdas
         cs1 = hSSFWorkbook.createCellStyle();
         cs2 = hSSFWorkbook.createCellStyle();
         cs3 = hSSFWorkbook.createCellStyle();
         cs4 = hSSFWorkbook.createCellStyle();

         // creamos 3 tipos de fuentes
         HSSFFont f1 = hSSFWorkbook.createFont();
         HSSFFont f2 = hSSFWorkbook.createFont();
         HSSFFont f3 = hSSFWorkbook.createFont();
         HSSFFont f4 = hSSFWorkbook.createFont();

         //establecemos las fuentes (f1, f2, f3)
         f1.setFontHeightInPoints( (short) 10);
         f1.setBoldweight(f1.BOLDWEIGHT_BOLD);

         f2.setFontHeightInPoints( (short) 10);

         f3.setFontHeightInPoints( (short) 10);
         f3.setBoldweight(f3.BOLDWEIGHT_BOLD);

         f4.setFontHeightInPoints( (short) 8);
         f4.setFontName("Arial Narrow");

         //establecemos los estilos de las celdas (cs1, cs2, cs3, cs4)
         cs1.setFont(f1);
         cs2.setFont(f2);
         cs3.setFont(f3);

         cs4.setFont(f4);
         cs4.setBorderBottom(cs4.BORDER_THIN);
         cs4.setBorderTop(cs4.BORDER_THIN);
         cs4.setBorderRight(cs4.BORDER_THIN);
         cs4.setBorderLeft(cs4.BORDER_THIN);
         cs4.setAlignment(cs4.ALIGN_CENTER);
         cs4.setFillPattern( (short) 1);
         cs4.setFillForegroundColor( (short) 40);
         //
         salida.setOk();
      }
      catch (Exception e) {
         logger.error("Exception: " + e.getMessage());
         salida.setError(true);
         salida.setMsg(e.getMessage());
         salida.setExcepcion(e);
      }
      return salida;
   }


  /**
   * Inserta una tabla en la hoja Excel, con los datos del PageBean y del TableBeanpasados por parametro.
   *
   * @param objAccesoBd AccesoBD
   * @param pageBean PageBean Informacion de la pagina en la que esta la tabla a insertar en la hoja Excel
   * @param tableBean TableBean Informacion de la tabla a insertar en la hoja Excel
   * @param hoja HSSFSheet Hoja del fichero excel
   * @param fila HSSFRow Fila de la hoja Excel
   * @param celda HSSFCell Celda del fichero excel
   * @return Salida
   */
  private Salida managerDrawTable(AccesoBD objAccesoBd, PageBean pageBean, TableBean tableBean, HSSFSheet hoja, HSSFRow fila, HSSFCell celda) {

     // Posicion de la ultima fila utilizada
     int countLinesPage = 0;

     // Para poner funciones al final de la tabla, idica comienzo y final de la tabla
     int tableInitialPosition = -1;
     int tableFinalPosition   = -1;

     String query          = "";
     HashMap mapParametros = new HashMap ();

     ResultSet resultSet                 = null;
     PreparedStatement preparedStatement = null;

     Salida salida = new Salida();

     Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.managerDrawTable");

     try {
       logger.debug(" --- INICIO --- ");

       int posicionInicial = tableBean.getNumFilaCabecera(); // Fila de titulo cabecera
       countLinesPage = countLinesPage + Math.abs(countLinesPage - posicionInicial);

       /////////////////////////////////////////////////////////////////////////
       // TITULO DE TABLA
       if (posicionInicial > 0) {
         countLinesPage++;
         fila  = hoja.createRow( (short) (posicionInicial - 1));
         celda = fila.createCell( (short) 0);
         celda.setCellType(HSSFCell.CELL_TYPE_STRING);
         HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(tableBean.getTituloTabla());
         celda.setCellValue(hSSFRichTextString.toString());
       }
       /////////////////////////////////////////////////////////////////////////

       //
       //Contenido de las columnas:
       int linea            = tableBean.getNumFilaInicial(); //Comienzo de las filas + 1
       countLinesPage       = countLinesPage + Math.abs(linea - posicionInicial);

       // Se realiza la consulta a BBDD
       HashMap hRegistros = new HashMap();
       if (tableBean.getParametrosSelect() != null && tableBean.getParametrosSelect().size() > 0) {
         //
         // Se obtiene el numero de '?' de la query para pasarle el numero de parametros exactos.
         //
         query              = tableBean.getSelect();
         int numOcurrencias = repetitionCharacterInString(query, '?');

         tableBean.getParametrosSelect();
         //
         if (numOcurrencias > 0) {
           for (int i = 1; i <= numOcurrencias; i++) {
             StringBuffer strBuffer = new StringBuffer();
             strBuffer.append(i);
             String key = strBuffer.toString();

             if (tableBean.getParametrosSelect().containsKey(key)) {
               mapParametros.put(key, tableBean.getParametrosSelect().get(key));
             }
           }
         }
         //
         hRegistros = objAccesoBd.consultaRaw(query, mapParametros);
       }
       else {
         hRegistros = objAccesoBd.consultaRaw(tableBean.getSelect());
       }
       preparedStatement = (PreparedStatement) hRegistros.get("ps");
       resultSet         = (ResultSet) hRegistros.get("rs");
       // Para obtener einformacion sobre las columnas de la consulta
       ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

       /////////////////////////////////////////////////////////////////////////
       // CABECERA DE LA TABLA
       String cabeceraTabla = tableBean.getCabecera();
       drawTableHead(hoja, fila, celda, resultSetMetaData, cabeceraTabla, posicionInicial);
       /////////////////////////////////////////////////////////////////////////

       // Posicion que tendra la 1ª fila de la tabla
       tableInitialPosition = countLinesPage;

       /////////////////////////////////////////////////////////////////////////
       //
       //  CUERPO DE LA TABLA
       //
       //Nº de columnas de la Consulta
       int numCols = resultSetMetaData.getColumnCount();

       while (resultSet.next()) {
         //
         int columnas = 0;
         fila = hoja.createRow( (short) (linea));
         countLinesPage++;

         for (int j = 0; j < numCols; j++) {
           celda = fila.createCell( (short) columnas);
           columnas++;
           celda.setCellStyle(cs2);

           int tipo = resultSetMetaData.getColumnType(j + 1);

           if (tipo == java.sql.Types.INTEGER) {
             celda.setCellValue(resultSet.getInt(j + 1));
           }
           else if (tipo == java.sql.Types.DATE) {
             if (resultSet.getDate(j + 1) != null) {
               celda.setCellType(HSSFCell.CELL_TYPE_STRING);
               HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(resultSet.getDate(j + 1).toString());
               celda.setCellValue(hSSFRichTextString.toString());
             }
             else {
               HSSFRichTextString hSSFRichTextString = new HSSFRichTextString("");
               celda.setCellValue(hSSFRichTextString.toString());
             }
           }
           else if (tipo == java.sql.Types.DOUBLE || tipo == java.sql.Types.NUMERIC) {
             celda.setCellValue(resultSet.getDouble(j + 1)); // Double
           }
           else if (tipo == java.sql.Types.FLOAT) {
             celda.setCellValue(resultSet.getFloat(j + 1));
           }
           else {
             String campo = resultSet.getString(j + 1);

             if (campo == null || campo.trim().equals("")) {
               HSSFRichTextString hSSFRichTextString = new HSSFRichTextString("Sin Especificar");
               celda.setCellValue(hSSFRichTextString.toString());
             }
             else {
               HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(campo);
               celda.setCellValue(hSSFRichTextString.toString());
             }
           }
         }
         linea++;
       }
       /////////////////////////////////////////////////////////////////////////

       // Se indica el final de la ultima linea
       tableFinalPosition = linea;

       /////////////////////////////////////////////////////////////////////////
       // FUNCIONES DE LA TABLA
       // -> Por si la tabla es vacia
       if(tableFinalPosition >= tableInitialPosition) {
         Salida salida2 = insertFunctionsInTable(hoja, tableBean, tableInitialPosition, tableFinalPosition);
         if (!salida2.getError()) {
           countLinesPage++;
           countLinesPage++;
         }
         else {
           countLinesPage++;
         }
       }
       /////////////////////////////////////////////////////////////////////////

       //  ## ## ## Pongo en la pagina la posicion de la ultima fila utilizada por si hay mas de una tabla.
       pageBean.setPositionCelda(countLinesPage);

       salida.setOk();
       logger.debug(" --- FIN --- ");
     }
     catch (java.sql.SQLException sqlE) {
       logger.error("SQLException: query: " + query);
       logger.error("SQLException: mapParametros: " + mapParametros);
       logger.error("SQLException: " + sqlE.getMessage());
       salida.setError(true);
     }
     catch (Exception e) {
       String mensaje = e.getMessage();
       logger.error("Exception: " + mensaje);
       salida.setError(true);
       salida.setMsg(mensaje);
       salida.setExcepcion(e);
     }
     finally {
       try {
         if (resultSet != null) {
           resultSet.close();
         }
         if (preparedStatement != null) {
           preparedStatement.close();
         }
       }
       catch (Exception e) {
         String mensaje = e.getMessage();
         logger.error("Exception: " + mensaje);
         salida.setError(true);
         salida.setMsg(mensaje);
         salida.setExcepcion(e);
       }
     }
     return salida;
   }


   /**
    * Se crea la cabecera de la tabla a partir de la cabecera "head" pasada por parametro,
    * sino es vacia, con los campos separados por comas, o si es vacia a traves del ResultSetMetaData
    * se obtienen los nombres de las columnas de la consulta.
    *
    * @param hoja HSSFSheet Hoja del fichero excel
    * @param fila HSSFRow Fila de la hoja Excel
    * @param celda HSSFCell Celda del fichero excel
    * @param resultSetMetaData ResultSetMetaData Nos indicara el nombre de las columnas de la consulta
    * @param head String Campos de la cabecera separados por ";"
    * @param headPosition int Posicion de la cabecera en la hoja
    */
   private Salida drawTableHead(HSSFSheet hoja, HSSFRow fila, HSSFCell celda, ResultSetMetaData resultSetMetaData, String head, int headPosition) {
     //
     Salida salida = new Salida ();
     Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.drawTableHead");

     try {
       logger.debug("INICIO");
       // Si la cabecera es vacia, se obtiene de los nombres de las columnas de la consulta
       if (Misc.esVacio(head)) {
         StringBuffer str = new StringBuffer();
         int numberOfColumns = resultSetMetaData.getColumnCount();
         //
         for (int i = 1; i <= numberOfColumns; i++) {
           String nombreCol = Misc.campoCapitalized(resultSetMetaData.getColumnName(i));
           str.append(nombreCol.toUpperCase());
           if (i != numberOfColumns) {
             str.append(Constantes.ADMIN_SEPARATOR_REGISTER);
           }
         }
         head = str.toString();
       }
       else {
         logger.debug("Se obtiene la cabecera por los nombres de las columnas de la consulta");
       }
       //
       //
       // La cabecera con los campos separados por comas
       StringTokenizer stringTokenizer = new StringTokenizer(head, Constantes.ADMIN_SEPARATOR_HEAD_REGISTER);

       fila = hoja.createRow( (short) (headPosition));
       int i = 0;
       while (stringTokenizer.hasMoreTokens()) {
         celda = fila.createCell( (short) i);
         celda.setCellStyle(cs4);
         //
         HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(stringTokenizer.nextToken());
         celda.setCellValue(hSSFRichTextString.toString());
         i++;
       }
       logger.debug("FIN");
     }
     catch (Exception e) {
       String mensaje = e.getMessage();
       logger.error("Exception: " + mensaje);
       salida.setError(true);
       salida.setMsg(mensaje);
       salida.setExcepcion(e);
     }
     return salida;
   }


  /**
   * Inserta en la fila siguiente al cuerpo de la tabla, las funciones de SUMA, ACUMULADO, etc..
   *
   * @param hoja HSSFSheet Hoja del fichero excel
   * @param tableBean TableBean Contiene informacion de la tabla para generar las formulas de las funciones
   * @param tableInitialPosition int Posicion de la fila inicial del cuerpo de la tabla
   * @param tableFinalPosition int Posicion de la fila final del cuerpo de la tabla
   * @return Salida
   */
  private Salida insertFunctionsInTable(HSSFSheet hoja, TableBean tableBean, int tableInitialPosition, int tableFinalPosition) {

       Salida salida = new Salida();

       Logger logger = new Logger("snsadmin.estadisticas.model.excel.ManagerExcel.insertFunctionsInTable");

       try {
          logger.debug("INICIO");

          // Se ponen las funciones en la linea despues de la tabla (SUM,......)
          if( !Misc.esVacio(tableBean.getFunctions()) && !Misc.esVacio(tableBean.getPositionFunctions()) ){
            // Contiene las funciones
            StringTokenizer tokenizerFunction = new StringTokenizer(tableBean.getFunctions(), Constantes.ADMIN_SEPARATOR_FUNCTIONS);
            // Contiene las posiciones de las columnas para las funciones anteriores
            StringTokenizer tokenizerPosFunct = new StringTokenizer(tableBean.getPositionFunctions(), Constantes.ADMIN_SEPARATOR_FUNCTIONS);

            // Mismo numero de funciones y de posiciones para las funciones
            if(tokenizerFunction.countTokens() == tokenizerPosFunct.countTokens()){
              int countTokens = 0;
              //
              while (tokenizerFunction.hasMoreTokens()) {
                // Funcion: SUM, AVERAGE, ....
                String function = tokenizerFunction.nextToken().toUpperCase();
                // Posicion de la columna para la funcion
                String posFunct = tokenizerPosFunct.nextToken();

                if (function!=null && posFunct!=null) {
                  //logger.debug("funcion: " + function + ", posicionFuncion: " + posFunct);
                  countTokens++;
                  // Se obtiene la correspondencia entre la posicion y como se denomina la columna en una hoja Excel (1 -> B)
                  String charCell = (String) Inicializacion.ADMIN_MAP_CORRESPONDENCIA_CELDAS.get(posFunct);
                  //
                  if(!Misc.esVacio(charCell)){
                    //
                    int positionIntCell =  Integer.parseInt(posFunct);
                    if(-1 < positionIntCell && positionIntCell<Inicializacion.ADMIN_MAP_CORRESPONDENCIA_CELDAS.size()){
                      // SUM(A1:A3)
                      StringBuffer strFunctionCell = new StringBuffer();
                      strFunctionCell.append(function);
                      strFunctionCell.append(Constantes.ADMIN_BRACKET_OPEN);                     //(
                      strFunctionCell.append(charCell);
                      strFunctionCell.append(tableInitialPosition);
                      strFunctionCell.append(Constantes.ADMIN_SEPARATOR_FUNCTIONS_CELL);         //:
                      strFunctionCell.append(charCell);
                      strFunctionCell.append(tableFinalPosition);
                      strFunctionCell.append(Constantes.ADMIN_BRACKET_CLOSE);                    //)
                      //logger.debug("SUM(B" + tableInitialPosition + ":B" + tableFinalPosition + ")");

                      // SUMMARY ROW
                      HSSFRow rowSummary = hoja.createRow(tableFinalPosition);

                      // Se pone en la celda anterior de la funcion: Total:,
                      if (positionIntCell>0 && countTokens==1) {
                        int positionTitle = positionIntCell - 1;
                        HSSFCell cellSum0 = rowSummary.createCell( (short) positionTitle);
                        cellSum0.setCellValue("Total:");
                      }

                      // Posicion de la celda
                      HSSFCell cellB = rowSummary.createCell( (short) positionIntCell);
                      // A, B, C, D, .......
                      //logger.debug("Formula para la celda: " + strFunctionCell.toString());
                      cellB.setCellFormula(strFunctionCell.toString());
                    }
                    else {
                      salida.setError(true);
                      logger.debug("La posicion de la funcion en la tabla no esta entre 1 y 25");
                    }
                  }
                  else {
                  salida.setError(true);
                  logger.debug("ELSE: Columna (Letra) de la hoja:  " + charCell);
                  }
                }
                else {
                  salida.setError(true);
                  logger.debug("ELSE: funcion: " + function + ", posicionFuncion: " + posFunct);
                }
              }
            }
            else {
              salida.setError(true);
              logger.error("Tabla: " + tableBean.getTituloTabla() + ", No coincide el numero de funciones para la tabla y el numero de posiciones para la funciones.");
            }
          }
          else {
            logger.debug("ELSE: Sin funciones de tabla.");
            //logger.debug("tableBean.getFunctions(): " + tableBean.getFunctions() + ", tableBean.getPositionFunctions(): " + tableBean.getPositionFunctions());
          }

          logger.debug("FIN");
       }
       catch (Exception e) {
          logger.error("Exception: " + e.getMessage());
          salida.setError(true);
          salida.setMsg(e.getMessage());
          salida.setExcepcion(e);
       }
       return salida;
    }


    /**
     * repetitionCharacterInString
     * Obtiene el numero de apariciones del caracter en la palabra
     *
     * @param name String
     * @param character char
     * @return int Numero de apariciones del caracter en la palabra
     */
    public static int repetitionCharacterInString(String name, char character) {
      int cont = 0;
      while (name.indexOf(character) != -1) {
        cont++;
        name = name.substring(name.indexOf(character) + 1, name.length());
        //logger.debug("name: " + name);
      }
      return cont;
    }



}
