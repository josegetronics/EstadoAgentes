package sns.comun.bd;

import gasai.bd.AccesoBD;

import java.util.Properties;

import org.apache.log4j.Logger;

public class AccesoBdFactory {
	  private static Logger logger=Logger.getLogger("BajasMjuLog");
	  public static String SISTEMA_HIST_TSI="SISTEMA_HIST_TSI";
	  public static String SISTEMA_CRUCES_TSI="SISTEMA_CRUCES_TSI";
	  public static String SISTEMA_TSI="SISTEMA_TSI";
	  public static String SISTEMA_INTERCAMBIADOR="SISTEMA_INTERCAMBIADOR";
	  
	  public static AccesoBD getInstance(String sistema) {
		  try {
			  Properties propAux=new Properties();
			  propAux.load(ClassLoader.getSystemResourceAsStream( "sns/Factorias.properties" ));
			  Class clase = Class.forName( propAux.getProperty(sistema) );
				//// Creo una instancia
			  return (AccesoBD) clase.newInstance();
			  
		  }catch (Exception e) {
			  logger.error("Error cargando el AccesoBd para el sistema -> [" + sistema + "]",e);
			  return null;
		  }
	  }

}
