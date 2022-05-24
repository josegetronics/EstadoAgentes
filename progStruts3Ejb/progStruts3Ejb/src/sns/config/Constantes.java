package sns.config;



public class Constantes {


  //////////////////////////////////////////////////////////////////////////////
  // Configuracion por properties
  // Direccion del properties
  //public static final String URL_DIR = "C:/Documents and Settings/amartinezma/jbproject/progStruts3Ejb/reporting.properties";
  // Nombre del fichero properties
  //public static final String FILE_PROPERTIES = "reporting.properties";

  // Variables utilizadas para leer del fichero properties //
  //public static final String EXCEL_PLANTILLA_CONFIG = "plantilla";
  //public static final String EXCEL_RUTA_PLANTILLA_CONFIG = "rutaPlantilla";
  //public static final String EXCEL_RUTA_CONFIG = "rutaDirectorioDestino";
  //public static final String EXCEL_NOMBRE_FICHERO_CONFIG = "nombreFicheroExcel";
  //
  //public static final String PAGE                        = "page";
  //public static final String TABLE                       = "table";
  //
  //public static final String EXCEL_CABECERA              = "cabecera";
  //public static final String EXCEL_NOMBRE_HOJA           = "nombreHoja";
  //public static final String EXCEL_NUM_FILAS_HOJA_EXCEL  = "numFilasHojaExcel";

  //public static final String EXCEL_TIPO                  = "tipo";
  //public static final String EXCEL_NUM_FILA_CABECERA     = "numFilaCabecera";
  //public static final String EXCEL_NUM_FILA_INICIAL      = "numFilaInicial";
  //public static final String EXCEL_TITULO_TABLA          = "tituloTabla";

  //public static final String EXCEL_POSICION_HOJA_EXCEL   = "posicionHojaExcel";
  //public static final String EXCEL_NUMERO_PAGINAS        = "pages";
  //public static final String EXCEL_QUERY_SQL             = "select";
  //public static final String EXCEL_PAGE_POSITION         = "position";
  //public static final String EXCEL_TABLE_FUNCTIONS       = "function";
  //public static final String EXCEL_TABLE_POS_FUNCTIONS   = "positionFunction";
  //////////////////////////////////////////////////////////////////////////////


  // Data Source de Produccion
  public static final String ADMIN_DATASOURCE = "SnSalud9ProduccionDataSource";

  // Si el servidor esta en modo debug
  public static boolean ADMIN_DEBUG = true;

  // // // // // // // //      ADMINISTRACION      // // // // // // // // // //
  // URL de la plantilla
  public static final String ADMIN_EXCEL_RUTA_PLANTILLA     = "//Nas12-03.sanidad.msc/desarrollo/Tarjeta/09 - Varios/Soporte/Estadísticas/Estadísticas Informe Actividad Técnico TSI//plantilla/";
  // Nombre de la plantilla Excel
  public static final String ADMIN_EXCEL_PLANTILLA          = "Excel.xls";
  // URL del directorio para alamacenar los ficherose la plantilla
  public static final String ADMIN_EXCEL_RUTA               = "//Nas12-03.sanidad.msc/desarrollo/Tarjeta/09 - Varios/Soporte/Estadísticas/Estadísticas Informe Actividad Técnico TSI/excelGenerados/";
  // Sufijo para el nombre del Excel
  public static final String ADMIN_REPORT_POSTNAME_FILE     = "Reporting";
  // Extension excel
  public static final String ADMIN_REPORT_EXTENSION_FILE    = "xls";


  public static final String ADMIN_BRACKET_OPEN             = "(";
  public static final String ADMIN_BRACKET_CLOSE            = ")";
  public static final String ADMIN_SEPARATOR_FUNCTIONS_CELL = ":";
  public static final String ADMIN_POINT                    = ".";
  public static final String ADMIN_SEPARATOR_REGISTER       = ";";
  public static final String ADMIN_SEPARATOR_HEAD_REGISTER  = ";";
  public static final String ADMIN_SEPARATOR_FUNCTIONS      = ";";
  public static final String ADMIN_SEPARATOR_NAME_FILE      = "_";
  public static final String SALTO_LINEA = "\r\n";
  // // // // // // // // // // // // // // // // // // // // // // // // // //


}
