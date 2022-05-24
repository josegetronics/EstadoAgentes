package sns.comun.util;

import gasai.util.Misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import sns.comun.config.Inicializacion;

public class Util {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	public static String generateFecha(String separador) throws Exception {

		String fecha = new String();

		try {
			logger.debug("generateFecha: INICIO");
			//
			// SE OBTIENE LA FECHA PARA LA QUERY
			// Se obtiene la fecha actual
			GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
			// Se resta un dia a la fecha actual
			gregorianCalendar.add(Calendar.DATE, -1);
			//
			String anio = "";
			String mes = "";
			String dia = "";
			//
			int y = gregorianCalendar.get(java.util.Calendar.YEAR);
			anio = String.valueOf(y);
			anio = anio.substring(2, 4);
			//
			int m = gregorianCalendar.get(java.util.Calendar.MONTH);
			m++;
			if (m < 10) {
				mes = "0" + String.valueOf(m);
			} else {
				mes = String.valueOf(m);
			}
			//
			int d = gregorianCalendar.get(java.util.Calendar.DATE);
			if (d < 10) {
				dia = "0" + String.valueOf(d);
			} else {
				dia = String.valueOf(d);
			}
			//
			fecha = "20" + anio + separador + mes + separador + dia;
			//
			logger.debug("generateFecha: fecha: " + fecha);
			logger.debug("generateFecha: FIN");
		} catch (Exception e) {
			logger.error("generateFecha: Exception: " + e.getMessage(), e);
			throw e;
		}
		
		fecha = fechaDiaAnterior();
		
		return fecha;
	}

	public static String cadenaFecha() {
		String cadenaFecha = "";
		try {
			Calendar calendar = Calendar.getInstance();
			int dia = calendar.get(5);
			int mes = calendar.get(2) + 1;
			int anio = calendar.get(1);

			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(Misc.rellenarIzq(new Integer(anio).toString(), "0", 4));
			stringBuffer.append(Misc.rellenarIzq(new Integer(mes).toString(), "0", 2));
			stringBuffer.append(Misc.rellenarIzq(new Integer(dia).toString(), "0", 2));
			cadenaFecha = stringBuffer.toString();
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
		}
		return cadenaFecha;
	}
	
	public static String cadenaFechaAyer(String separador) {
		String cadenaFecha = "";
		try {
			Calendar calendar = Calendar.getInstance();
			int dia = calendar.get(5) - 1;
			int mes = calendar.get(2) + 1;
			int anio = calendar.get(1);

			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(Misc.rellenarIzq(new Integer(anio).toString(), "0", 4));
			stringBuffer.append(separador);
			stringBuffer.append(Misc.rellenarIzq(new Integer(mes).toString(), "0", 2));
			stringBuffer.append(separador);
			stringBuffer.append(Misc.rellenarIzq(new Integer(dia).toString(), "0", 2));
			stringBuffer.append(separador);
			cadenaFecha = stringBuffer.toString();
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
		}
		return cadenaFecha;
	}
	
	public static HashMap inicializarHashMap(){
		HashMap hMap = new HashMap();
		hMap.put("1", "");
		hMap.put("2", "");
		hMap.put("3", "");
		hMap.put("4", "");
		hMap.put("5", "");
		hMap.put("6", "");
		hMap.put("8", "");
		hMap.put("9", "");
		hMap.put("10", "");
		hMap.put("13", "");
		hMap.put("14", "");
		hMap.put("15", "");
		hMap.put("16", "");
		hMap.put("17", "");
		hMap.put("18", "");
		hMap.put("19", "");
		hMap.put("TOTAL", "0");
		return hMap;
		
	}
	
	public static LinkedHashMap inicializarRecorreMap(){
		LinkedHashMap hMap = new LinkedHashMap();
		hMap.put("1", "1");
		hMap.put("2", "2");
		hMap.put("3", "3");
		hMap.put("4", "4");
		hMap.put("5", "5");
		hMap.put("6", "6");
		hMap.put("7", "7");
		hMap.put("8", "8");
		hMap.put("9", "9");
		hMap.put("10", "10");
		hMap.put("11", "11");
		hMap.put("12", "12");
		hMap.put("13", "13");
		hMap.put("14", "14");
		hMap.put("15", "15");
		hMap.put("16", "16");
		hMap.put("17", "17");
		hMap.put("18", "18");
		hMap.put("TOTAL", "TOTAL");
		return hMap;
		
	}
	
	public static int[] inicializarArrayTotales(){
		int totales[] = new int[9];
		totales[0] = 0;
		totales[1] = 0;
		totales[2] = 0;
		totales[3] = 0;
		totales[4] = 0;
		totales[5] = 0;
		totales[6] = 0;
		totales[7] = 0;
		totales[8] = 0;
		return totales;
		
	}
	
	public static String fechaDiaAnterior () {
		Date date = new Date();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		calendario.add(Calendar.DATE, -1);
		date = calendario.getTime();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaDiaAnterior = formato.format(date);
		
		return fechaDiaAnterior;
	}
	

}

