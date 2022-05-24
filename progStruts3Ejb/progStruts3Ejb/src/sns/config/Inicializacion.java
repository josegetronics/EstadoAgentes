package sns.config;

import sns.logging.Logger;
import java.util.HashMap;
import gasai.util.Misc;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashMap;
import sns.model.AgenteBean;



public class Inicializacion {

  // // // // // // // //      ADMINISTRACION      // // // // // // // // // //
  // Para almacenar las comunidades y su descripcion
  public static ArrayList ADMIN_LIST_COMUNIDADES;
  // Para almacenar seguna la descripcion de las comunidades su codigo de agente
  public static LinkedHashMap  ADMIN_MAP_AGENTES;
  // Nombres para las comunidades en la tabla CONTROL_BLOQUEO_REPORT_DIARIO
  public static HashMap   ADMIN_MAP_NOMBRES_COMUNIDADES;
  // Asociacion de posiciones para las columnas de una hoja Excel
  public static HashMap   ADMIN_MAP_CORRESPONDENCIA_CELDAS;
  // // // // // // // // // // // // // // // // // // // // // // // // // //

  /**
   * Inicializa la informacion
   */
  public static void init() {
    Logger logger = new Logger("sns.config.Inicializacion.init");
    try {
      cargarTablasAdmin();
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }


  // // // // // // // //      ADMINISTRACION      // // // // // // // // // //
  /**
   * Se genera un ArrayList con la informacion de las comunidades
   */
  public static void cargarTablasAdmin() {

    Logger logger = new Logger("sns.config.Inicializacion.cargarTablasAdmin");

    try {
      logger.debug("INICIO");
      //
      // Se carga la correspondencia entre letras y posiciones, para las columnas de un excel
      cargarCorrespondenciaCeldasAdmin();
      // Se cargan los codigos de los agentes segun la descripcion de las comunidades
      cargarAgentesAdmin();
      // Se cargan los codigos de los agentes segun la descripcion de las comunidades
      cargarNombresComunidadesAdmin();
      // Se carga un ArrayList con la informacion de las agentes y las comunidades
      cargarAgentesComunidadesAdmin();
      //
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }


  /**
   * Se carga la correspondencia entre letras y posiciones, para las columnas de un excel
   */
  private static void cargarCorrespondenciaCeldasAdmin() {

    Logger logger = new Logger("sns.config.Inicializacion.cargarCorrespondenciaCeldasAdmin");

    try {
      HashMap mapCorrespondenciaCeldasAux = new HashMap();
      mapCorrespondenciaCeldasAux.put("0",  "A");
      mapCorrespondenciaCeldasAux.put("1",  "B");
      mapCorrespondenciaCeldasAux.put("2",  "C");
      mapCorrespondenciaCeldasAux.put("3",  "D");
      mapCorrespondenciaCeldasAux.put("4",  "E");
      mapCorrespondenciaCeldasAux.put("5",  "F");
      mapCorrespondenciaCeldasAux.put("6",  "G");
      mapCorrespondenciaCeldasAux.put("7",  "H");
      mapCorrespondenciaCeldasAux.put("8",  "I");
      mapCorrespondenciaCeldasAux.put("9",  "J");
      mapCorrespondenciaCeldasAux.put("10", "K");
      mapCorrespondenciaCeldasAux.put("11", "L");
      mapCorrespondenciaCeldasAux.put("12", "M");
      mapCorrespondenciaCeldasAux.put("13", "N");
      mapCorrespondenciaCeldasAux.put("14", "O");
      mapCorrespondenciaCeldasAux.put("15", "P");
      mapCorrespondenciaCeldasAux.put("16", "Q");
      mapCorrespondenciaCeldasAux.put("17", "R");
      mapCorrespondenciaCeldasAux.put("18", "S");
      mapCorrespondenciaCeldasAux.put("19", "T");
      mapCorrespondenciaCeldasAux.put("20", "U");
      mapCorrespondenciaCeldasAux.put("21", "V");
      mapCorrespondenciaCeldasAux.put("22", "W");
      mapCorrespondenciaCeldasAux.put("23", "X");
      mapCorrespondenciaCeldasAux.put("24", "Y");
      mapCorrespondenciaCeldasAux.put("25", "Z");

      ADMIN_MAP_CORRESPONDENCIA_CELDAS = mapCorrespondenciaCeldasAux;
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }


  /**
   * Carga en una HashMap para la descripcion de las  comunidades su codigo de agente
   */
  private static void cargarAgentesAdmin() {
    //
    Logger logger = new Logger("sns.config.Inicializacion.cargarAgentesAdmin");
    //
    try {
      LinkedHashMap agentesMapAux = new LinkedHashMap();
      agentesMapAux.put("2",  "ANDALUCIA");
      agentesMapAux.put("13", "ARAGON");
      agentesMapAux.put("14", "ASTURIAS");
      agentesMapAux.put("15", "ILLES BALEARS");
      agentesMapAux.put("3",  "CANARIAS");
      agentesMapAux.put("16", "CANTABRIA");
      agentesMapAux.put("17", "CASTILLA LA MANCHA");
      agentesMapAux.put("18", "CASTILLA Y LEON");
      agentesMapAux.put("4",  "CATALUNYA");
      agentesMapAux.put("8",  "COMUNITAT VALENCIANA");
      agentesMapAux.put("20", "EXTREMADURA");
      agentesMapAux.put("5",  "GALICIA");
      agentesMapAux.put("19", "INGESA");
      agentesMapAux.put("6",  "NAVARRA");
      agentesMapAux.put("7",  "PAIS VASCO");
      agentesMapAux.put("23", "MADRID");
      agentesMapAux.put("22", "MURCIA");
      agentesMapAux.put("21", "LA RIOJA");

      ADMIN_MAP_AGENTES = agentesMapAux;
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }


  /**
   * Se carga en una HashMap a partir de la descripcion de las comunidades, otra descripcion
   */
  private static void cargarNombresComunidadesAdmin() {

    Logger logger = new Logger("sns.config.Inicializacion.cargarAgentesAdmin");

    try {
      HashMap nombresComunidadesMapAux = new HashMap();
      nombresComunidadesMapAux.put("ANDALUCIA",            "Andalucia");
      nombresComunidadesMapAux.put("CANARIAS",             "Canarias");
      nombresComunidadesMapAux.put("CATALUNYA",            "Catalunya");
      nombresComunidadesMapAux.put("GALICIA",              "Galicia");
      nombresComunidadesMapAux.put("NAVARRA",              "Navarra");
      nombresComunidadesMapAux.put("PAIS VASCO",           "PaisVasco");
      nombresComunidadesMapAux.put("COMUNITAT VALENCIANA", "Valencia");
      nombresComunidadesMapAux.put("ARAGON",               "Aragon");
      nombresComunidadesMapAux.put("ASTURIAS",             "Asturias");
      nombresComunidadesMapAux.put("ILLES BALEARS",        "Baleares");
      nombresComunidadesMapAux.put("CANTABRIA",            "Cantabria");
      nombresComunidadesMapAux.put("CASTILLA LA MANCHA",   "CastillaLaMancha");
      nombresComunidadesMapAux.put("CASTILLA Y LEON",      "CastillaLeon");
      nombresComunidadesMapAux.put("INGESA",               "Ingesa");
      nombresComunidadesMapAux.put("EXTREMADURA",          "Extremadura");
      nombresComunidadesMapAux.put("LA RIOJA",             "LaRioja");
      nombresComunidadesMapAux.put("MURCIA",               "Murcia");
      nombresComunidadesMapAux.put("MADRID",               "Madrid");

      ADMIN_MAP_NOMBRES_COMUNIDADES = nombresComunidadesMapAux;
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
    }
  }


  private static void cargarAgentesComunidadesAdmin() {

    ArrayList listComunidades = new ArrayList ();

    Logger logger = new Logger("sns.config.Inicializacion.cargarAgentesComunidadesAdmin");

    try {
      Set set = ADMIN_MAP_AGENTES.keySet();
      Iterator iterator = set.iterator();

      while (iterator.hasNext()){
        AgenteBean agenteBean = new AgenteBean();

        String key    = (String) iterator.next();
        String value  = (String) ADMIN_MAP_AGENTES.get(key);
        //logger.debug("key: " + key + ", value: " + value);

        if (!Misc.esVacio(ADMIN_MAP_AGENTES.get(key))) {
          int codAgente = Integer.parseInt(key);
          agenteBean.setCodAgente(codAgente);
          //
          agenteBean.setDescripcion(Misc.nz(value));
          //
          if (!Misc.esVacio(ADMIN_MAP_NOMBRES_COMUNIDADES.get(agenteBean.getDescripcion()))) {
            String nombreComunidad2 = (String) ADMIN_MAP_NOMBRES_COMUNIDADES.get(agenteBean.getDescripcion());
            agenteBean.setDescripcion2(nombreComunidad2);
          }
        }
        listComunidades.add(agenteBean);
      }

      //for (int i = 0; i < listComunidades.size(); i++) {
      //  AgenteBean agente = (AgenteBean) listComunidades.get(i);
      //  agente.view();
      //}

      ADMIN_LIST_COMUNIDADES = listComunidades;
    }
    catch (Exception e) {
      logger.error("Exception: " + e.getMessage());
    }
  }
  // // // // // // // // // // // // // // // // // // // // // // // // // //



  static {
     sns.config.Inicializacion.init();
   }


}
