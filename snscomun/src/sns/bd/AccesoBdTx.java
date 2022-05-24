package sns.bd;

import sns.config.Constantes;

public class AccesoBdTx extends gasai.bd.AccesoBD {

	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(AccesoBdTx.class);

  public AccesoBdTx() {
    super(Constantes.JNDI_DATASOURCE_XA);
//    logger.debug("Datasource -> " + Constantes.JNDI_DATASOURCE_XA);
  }
}
