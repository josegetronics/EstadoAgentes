package snsadmin.estadisticas.model.dataload.mensajes;

import snsadmin.estadisticas.bean.report.ReportFileBean;
import gasai.util.out.Salida;
import sns.logging.Logger;


public class ManagerConfGeneral {


  public ManagerConfGeneral() {
  }


  public Salida getConfigurationData(ReportFileBean reportFileBean) {

    //Estadisticas por tipo
    ManagerConfPorTipo porTipo = new ManagerConfPorTipo();
    //Estadisticas por agente y por agente y tipo
    ManagerConfPorAgenteyTipo porAgenteTipo = new ManagerConfPorAgenteyTipo();
    //Poblacion Activa
    ManagerConfPoblacionActiva poblacionActiva = new ManagerConfPoblacionActiva();

    Salida salida = new Salida ();

    Logger logger = new Logger("snsadmin.estadisticas.model.dataload.mensajes.ManagerConfGeneral.getConfigurationData");

    try {
      logger.debug("INICIO");

      // Rellena la informacion de las tablas
      salida = poblacionActiva.getConfigurationData(reportFileBean);
      if(!salida.getError()) {
        salida = porTipo.getConfigurationData(reportFileBean);
        if(!salida.getError()) {
          salida = porAgenteTipo.getConfigurationData(reportFileBean);
        }
      }
      if(!salida.getError()) {
        salida.setValor(reportFileBean);
      }
      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.error("Exception: " + e);
      salida.setError(true);
      salida.setMsg(e.getMessage());
      salida.setExcepcion(e);
    }
    return salida;
  }

}
