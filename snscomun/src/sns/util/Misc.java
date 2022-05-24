package sns.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

public class Misc {
	public Misc() {
	}

	public static String getKeyXml() {
		long seed = System.currentTimeMillis();
		Random r = new Random(seed);
		long uno = r.nextInt(9999);
		return seed + "" + uno;
	}

	public static String formateoNie(String agente, String nie) {
		if (Misc.esVacio(nie))
			return "";

		if (!Misc.esVacio(agente) && sns.config.Inicializacion.vAGENTES_NIE.indexOf(agente) == -1 && nie.charAt(0) == 'X' && nie.length() == 9) {
			String nieFormateado = "X0" + nie.substring(1, 9);
			return nieFormateado;
		} else {
			return nie;
		}
	}

	public static String remplazar(String cadena, String buscar, String remplazar) {
		int aux = 0;
		int inicio = 0;
		String salida = "";
		while ((aux = cadena.indexOf(buscar, inicio)) != -1) {
			salida += cadena.substring(inicio, aux) + remplazar;
			inicio = (aux + buscar.length());
		}
		return salida + cadena.substring(inicio, cadena.length());

	}

	public static boolean esDigito(String cadena) {
		for (int i = 0; i < cadena.length(); i++) {
			if ((cadena.charAt(i) < '0') || (cadena.charAt(i) > '9')) {
				return false;
			}
		}
		return true;
	}

	public static boolean esVacio(Object txt) {
		if (txt != null) {
			return txt.toString().trim().equals("");
		}
		return true;
	}

	public static String nz(Object elObj) {
		if (elObj != null)
			return elObj.toString();
		else
			return "";
	}

	public static String nz(Object elObj, String valor) {
		if (elObj != null)
			return elObj.toString();
		else
			return valor;
	}

	public static String fechaSinMin(Timestamp fechaConMin) {
		if (fechaConMin != null)
			return (new java.sql.Date(fechaConMin.getTime())).toString();
		else
			return "";

	}

	public static boolean esMenor(long fechaNacimiento) {
		Calendar cFechaNac=Calendar.getInstance();
		cFechaNac.setTimeInMillis(fechaNacimiento);
		Calendar cActual=Calendar.getInstance();
		cFechaNac.add(Calendar.YEAR, 18);
		return cActual.before(cFechaNac);
	}
}
