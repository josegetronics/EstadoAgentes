package sns.model.impl;

import sns.model.ITransaccionesHelper;
import sns.util.out.Salida;

public class TransaccionesHelperFake implements ITransaccionesHelper {
  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(TransaccionesHelperFake.class);

  public TransaccionesHelperFake() {
  }
  
  public Salida start(String codUsuarioSns,Integer operacionMaestra) {
    Salida salida=new Salida();
    logger.debug("Iniciando control de transaccion para el [usuario, operacionMaestra] -> [" + codUsuarioSns + "," + operacionMaestra + "]");
    salida.setOk();
    return salida;
  }

  public void release(String codUsuarioSns,Integer operacionMaestra) {
    logger.debug("FIN Liberando control de transaccion para operacion ["+ operacionMaestra + "] y usuario -> [" + codUsuarioSns + "]");
   }
}
