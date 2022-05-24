package sns.logging;

import weblogic.logging.NonCatalogLogger;
import sns.config.Constantes;


/**
 *
 * Genera los log.
 * Inicializacion.DEBUG indica si se esta en modo debug.
 *
 */
public class Logger {

  private NonCatalogLogger weblogicLogger;

  public Logger(String constructor) {
    weblogicLogger = new NonCatalogLogger(" [" + System.getProperty("weblogic.Name") + "] " + constructor);
  }

  public static void main(String[] args) {
    Logger logger1 = new Logger("logging.Logger");
  }

  public void info (String msg,Throwable e) {
    weblogicLogger.info(msg,e);
  }

  public void info (String msg) {
    weblogicLogger.info(msg);
  }

  public void error (String msg,Throwable e) {
    weblogicLogger.error(msg,e);
  }

  public void error (String msg) {
    weblogicLogger.error(msg);
  }

  public void debug (String msg,Throwable e) {
    if (Constantes.ADMIN_DEBUG)
      weblogicLogger.info("DEBUGREPORTING: " + msg, e);
  }

  public void debug (String msg) {
    if (Constantes.ADMIN_DEBUG)
      weblogicLogger.info("DEBUGREPORTING: " + msg);
  }

  public void warning (String msg,Throwable e) {
    weblogicLogger.warning(msg,e);
  }

  public void warning (String msg) {
    weblogicLogger.warning(msg);
  }

}
