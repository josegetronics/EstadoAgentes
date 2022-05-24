package sns.estadoagentes.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.log4j.Logger;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import sns.comun.config.Inicializacion;
import sns.comun.util.InfOperacion;
import sns.comun.util.Util;
import sns.comun.util.mail.CuerpoMailBean;

public class InfoEstadoAgentes{
	
	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	public CuerpoMailBean getInfoEstadoAgentes(String pathFicheroEstadisticas, AccesoBD bd) throws Exception {
		
		CuerpoMailBean cuerpoMailBean=new CuerpoMailBean();
		Util util = new Util();
		boolean escribirFichero = true;
		FileWriter fileWriterEstadisticas = null;
		try {
			fileWriterEstadisticas = new FileWriter(pathFicheroEstadisticas, true);
		} catch (IOException ex1) {
			escribirFichero = false;
		}

		HashMap estadoAgentes = new HashMap();

		int numAgentes = 0;

		long dias_dif = 0L;

		numAgentes = Inicializacion.codAgentes.size();

 	    Calendar c=Calendar.getInstance();
// 	    c.set(Calendar.HOUR_OF_DAY, 8);
// 	    c.set(Calendar.MINUTE, 1);
// 	    c.set(Calendar.MONTH, 4);
// 	    c.set(Calendar.DAY_OF_MONTH, 1);
// 	    c.set(Calendar.YEAR, 2013);
 	    Date fecha = c.getTime(); 
 	    Vector ultimaOpAsincronaAux = new Vector();
 	    Vector ultimaOpSincronaAux = new Vector();
 	    EstadoAgentesHelper estadoAgentesHelper = new EstadoAgentesHelper();
 	    
 	    
 	   for (int i = 0; i < numAgentes; i++) {
			logger.debug("---------------------------------------------------------------------------" + fecha);
			logger.debug("Examinando agente " + Inicializacion.LITERALAGENTES[Integer.parseInt((String) Inicializacion.codAgentes.elementAt(i))]);

			String[] estados = new String[2];
			InfOperacion infOperacionAgente;
			try {
				infOperacionAgente = estadoAgentesHelper.comprobarOpAgente((String) Inicializacion.codAgentes.elementAt(i), 
														Inicializacion.operacionesAsincronas, 
														(String) Inicializacion.ultimaOpAsincrona.elementAt(i),bd);

				logger.debug("infOperacionAgente (ASINCRONO): " + infOperacionAgente);
				if (infOperacionAgente != null) {
					dias_dif = infOperacionAgente.diferencia(new Timestamp(fecha.getTime()));
					logger.debug("Dias de diferencia: " + dias_dif);
					estados[0] = new String("" + dias_dif);
					ultimaOpAsincronaAux.addElement(new String("" + infOperacionAgente.getCodOperacion()));
				} else {
					estados[0] = "NO HAY REFERENCIA";
					ultimaOpAsincronaAux.addElement((String) Inicializacion.ultimaOpAsincrona.elementAt(i));
				}
			} catch (SQLException sq) {
				estados[0] = ("ERR.- " + sq.getMessage());
				ultimaOpAsincronaAux.addElement((String) Inicializacion.ultimaOpAsincrona.elementAt(i));
			}
			logger.debug("Estado Asincrono: " + estados[0]);
			try {
				infOperacionAgente = estadoAgentesHelper.comprobarOpAgente((String) Inicializacion.codAgentes.elementAt(i), Inicializacion.operacionesSincronas, (String) Inicializacion.ultimaOpSincrona.elementAt(i),bd);

				logger.debug("infOperacionAgente (SINCRONO): " + infOperacionAgente);
				if (infOperacionAgente != null) {
					dias_dif = infOperacionAgente.diferencia(new Timestamp(fecha.getTime()));
					estados[1] = new String("" + dias_dif);
					ultimaOpSincronaAux.addElement(new String("" + infOperacionAgente.getCodOperacion()));
				} else {
					estados[1] = "NO HAY REFERENCIA";
					ultimaOpSincronaAux.addElement((String) Inicializacion.ultimaOpSincrona.elementAt(i));
				}
			} catch (SQLException sq) {
				ultimaOpSincronaAux.addElement((String) Inicializacion.ultimaOpSincrona.elementAt(i));
				estados[1] = ("ERR.- " + sq.getMessage());
			}

			logger.debug("Estado Sincrono: " + estados[1]);

			estadoAgentes.put((String) Inicializacion.codAgentes.elementAt(i), estados);
		}
		
 	   	cuerpoMailBean.setUltimaOpAsincronaAux(ultimaOpAsincronaAux);
 	    cuerpoMailBean.setUltimaOpSincronaAux(ultimaOpSincronaAux);
 	   	cuerpoMailBean.setActividadAgentes("<table><tr><td>" + Inicializacion.mensajeEntero + "</td></tr></table>");
		cuerpoMailBean.appendActividadAgentes("<table border=\"1\">");
		cuerpoMailBean.appendActividadAgentes("<tr class=\"narr_tr_n\"><th class=\"narr_th_n\">AGENTES</th><th class=\"narr_th_n\">SISTEMA ASÍNCRONO</th><th class=\"narr_th_n\">SISTEMA SÍNCRONO</th></tr>");
		
		for (int i = 0; i < numAgentes; i++) {
			String[] estados = new String[2];
			StringTokenizer str = new StringTokenizer(Inicializacion.LITERALAGENTES[Integer.parseInt((String) Inicializacion.codAgentes.elementAt(i))], ">");
			String nombreAgente = str.nextToken();
			cuerpoMailBean.appendActividadAgentes("<tr class=\"narr_tr_n\"><td>" + nombreAgente + "</td>");
			estados = (String[]) estadoAgentes.get((String) Inicializacion.codAgentes.elementAt(i));
			cuerpoMailBean.appendActividadAgentes("<td>" + estados[0] + "</td>");
			cuerpoMailBean.appendActividadAgentes("<td>" + estados[1] + "</td></tr>");

			if (!escribirFichero)
				continue;
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(util.cadenaFecha() + "|");
			stringBuffer.append((String) Inicializacion.codAgentes.elementAt(i) + "|");
			stringBuffer.append(estados[0] + "|");
			stringBuffer.append(estados[1] + "|" + "\n");
			try {
				fileWriterEstadisticas.write(stringBuffer.toString());
				fileWriterEstadisticas.flush();
			} catch (IOException ioe) {
				logger.error("IOException al obtener la tabla de los mensajes bloqueados.", ioe);
			}

		}
		cuerpoMailBean.appendActividadAgentes("</table>");
		
		try {
			if (fileWriterEstadisticas != null)
				fileWriterEstadisticas.close();
		} catch (Exception e3) {
			fileWriterEstadisticas = null;
		}

		return cuerpoMailBean;
	}


	public void guardarConfiguracion(CuerpoMailBean cuerpoMailBean) throws IOException {
		String ultimaOpAsin = "";
		String ultimaOpSinc = "";

		OutputStream outStream = null;
		Properties properties = new Properties();
		Vector ultimaOpAsincronaAux = new Vector();
		Vector ultimaOpSincronaAux = new Vector();
		InputStream inputStream = null;
		FileInputStream fileInputStream = null;
		String path = "";

		try {
			
			ClassLoader classLoader = Inicializacion.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream(Inicializacion.FICHEROALERTAS);
			//
			if (inputStream == null) {
				if (!Misc.esVacio(path)) {
					fileInputStream = new FileInputStream(path);
					properties.load(fileInputStream);
					fileInputStream.close();
				} else {
					fileInputStream = new FileInputStream(Inicializacion.PATH_LOCAL + Inicializacion.FICHEROALERTAS);
					properties.load(fileInputStream);
					fileInputStream.close();
				}
			} else {
				logger.info("default 1:" + inputStream);
				properties.load(inputStream);
				inputStream.close();
			}
			
			ultimaOpAsincronaAux = cuerpoMailBean.getUltimaOpAsincronaAux();
			ultimaOpSincronaAux = cuerpoMailBean.getUltimaOpSincronaAux();
			outStream = new FileOutputStream(Inicializacion.PATH_LOCAL + Inicializacion.FICHEROALERTAS);

			for (int i = 0; i < ultimaOpAsincronaAux.size(); i++) {
				if (i != ultimaOpAsincronaAux.size() - 1) {
					ultimaOpAsin = ultimaOpAsin + (String) ultimaOpAsincronaAux.elementAt(i) + "|";
				} else {
					ultimaOpAsin = ultimaOpAsin + (String) ultimaOpAsincronaAux.elementAt(i);
				}
			}
			properties.setProperty("ultimaOpAsincrona", ultimaOpAsin);

			for (int i = 0; i < ultimaOpSincronaAux.size(); i++) {
				if (i != ultimaOpSincronaAux.size() - 1) {
					ultimaOpSinc = ultimaOpSinc + (String) ultimaOpSincronaAux.elementAt(i) + "|";
				} else {
					ultimaOpSinc = ultimaOpSinc + (String) ultimaOpSincronaAux.elementAt(i);
				}
			}

			properties.setProperty("ultimaOpSincrona", ultimaOpSinc);
			properties.store(outStream, "Configuracion de las alertas de agentes");
		} catch (IOException e) {
			throw e;
		} finally {
			if (outStream != null) {
				outStream.flush();
				outStream.close();
			}
		}
	}
	
}