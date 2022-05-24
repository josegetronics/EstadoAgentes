package sns.bd;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;

import sns.config.Constantes;

public class AccesoBd extends gasai.bd.AccesoBD {

	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(AccesoBd.class);

  public AccesoBd() {
    super(Constantes.JNDI_DATASOURCE_NONXA);
//    logger.debug("Datasource -> " + Constantes.JNDI_DATASOURCE_NONXA);
  }
  public AccesoBd(Connection conn) {
     super(conn);
  }

  public static Clob getClob(String texto,Connection wConn) throws java.sql.SQLException, IOException {

     oracle.sql.CLOB clob=null;

// no lo encuentro en las librerias del weblogic
//      weblogic.jdbc.pool.Connection wConn=(weblogic.jdbc.pool.Connection)bd.abrirConexion();
//    clob = CLOB.createTemporary(wConn.getVendorConnection(),true,CLOB.DURATION_SESSION);

     clob = oracle.sql.CLOB.createTemporary(wConn,true,oracle.sql.CLOB.DURATION_SESSION);

     clob.open(oracle.sql.CLOB.MODE_READWRITE);
     java.io.Writer w = clob.getCharacterOutputStream();
     w.write(texto);
     w.flush();
     w.close();
     clob.close();

     return clob;
   }
}
