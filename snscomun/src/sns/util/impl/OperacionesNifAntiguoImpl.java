package sns.util.impl;

import gasai.util.Misc;
import gasai.util.out.Salida;
import sns.util.IOperacionesNif;

public class OperacionesNifAntiguoImpl implements IOperacionesNif {

	public OperacionesNifAntiguoImpl() {
	}

	public static void main(String[] args) {

		args = new String[2];
		// args[0]="X45211";
		// args[0]="Y0077949Q";
		// args[0]="Y0077949P";
		// args[0]="Y0077949";
		// args[0]="16298428";
		args[0] = "X6592236";

		// args[1]="validar";
		args[1] = "generar";

		if (args.length != 2) {
			System.out.println("uso: java sns.util.OperacionesNif nif_nie generar|validar");
			System.exit(-1);
		}
		Salida salida = new Salida();
		OperacionesNifAntiguoImpl nif = new OperacionesNifAntiguoImpl();
		if (args[1].equals("validar")) {
			String nieOut = "";
			salida = nif.valida(args[0]);
			// System.out.println("X45211Q");
			System.out.println("ERROR: " + salida.getError());
			nieOut = (String) salida.getValor();
			System.out.println(nieOut);
			System.out.println(nif.formateoSieteDigitos(nieOut));

		}
		if (args[1].equals("generar")) {
			System.out.println("nif de entrada -> " + args[0]);
			System.out.println(nif.genera(args[0]));
		}

	}

	public String formateoSieteDigitos(String nie) {
		if (nie != null) {

			String nieFormateado = nie.charAt(0) + nie.substring(2, nie.length());
			return nieFormateado;
		}
		return "";

	}

	public String genera(String sdni) {

		int dni = 0;
		int resto = 0;

		String letra = "";

		if (gasai.util.Misc.esDigito(sdni)) {
			dni = Integer.parseInt(sdni);
		} else {
			if (!gasai.util.Misc.esVacio(sdni)) {
				letra = gasai.util.Misc.izquierda(sdni, 1);
				if (letra.equals("X")) {
					dni = Integer.parseInt(sdni.substring(1, sdni.length()));
				} else if (letra.equals("Y")) {
					dni = Integer.parseInt("1" + sdni.substring(1, sdni.length()));
				} else if (letra.equals("Z")) {
					dni = Integer.parseInt("2" + sdni.substring(1, sdni.length()));
				}
			}
		}

		String l = "";
		if ((dni > 0) && (dni <= 99999999)) {
			resto = dni % 23;
			switch (resto) {
			case 0:
				l = "T";
				break;
			case 1:
				l = "R";
				break;
			case 2:
				l = "W";
				break;
			case 3:
				l = "A";
				break;
			case 4:
				l = "G";
				break;
			case 5:
				l = "M";
				break;
			case 6:
				l = "Y";
				break;
			case 7:
				l = "F";
				break;
			case 8:
				l = "P";
				break;
			case 9:
				l = "D";
				break;
			case 10:
				l = "X";
				break;
			case 11:
				l = "B";
				break;
			case 12:
				l = "N";
				break;
			case 13:
				l = "J";
				break;
			case 14:
				l = "Z";
				break;
			case 15:
				l = "S";
				break;
			case 16:
				l = "Q";
				break;
			case 17:
				l = "V";
				break;
			case 18:
				l = "H";
				break;
			case 19:
				l = "L";
				break;
			case 20:
				l = "C";
				break;
			case 21:
				l = "K";
				break;
			case 22:
				l = "E";
				break;
			case 23:
				l = "U";
				break;
			}
		}
		if (gasai.util.Misc.esVacio(letra)) {
			sdni = gasai.util.Misc.rellenarIzq(sdni + l, "0", 9);
		} else {
			String dniAux = Integer.toString(dni);
			if (letra.equals("X")) {
				// si es X se deja tal cual porque el X = 0
				sdni = letra + gasai.util.Misc.rellenarIzq(dniAux, "0", 8) + l;
			} else {
				// si es Y o Z hay que coger el dni con el que hemos calculado y
				// quitarle el 1 o el 2 correspondiente a Y y Z
				sdni = letra + gasai.util.Misc.rellenarIzq(dniAux.substring(1, dniAux.length()), "0", 8) + l;
			}
		}

		/*
		 * if (elNif.charAt(0) == 'Y' || elNif.charAt(0) == 'Z') { valorDevuelto
		 * +=
		 * gasai.util.Misc.rellenarIzq(nifValidar.substring(1,nifValidar.length
		 * ()), "0", 9); }else{ valorDevuelto += nifValidar; }
		 * salida.setValor(valorDevuelto);
		 */
		return sdni;
	}

	public Salida valida(String elNif) {

		Salida salida = new Salida();
		boolean error = false;
		String nifValidar = "";
		String valorDevuelto = "";
		int longitud;

		int dni = 0;
		int resto = 0;
		String l = "";
		String caracterFinal = "";

		try {
			longitud = elNif.length();
			// Formato de nie o nif X12345678Letra ó 12354678Letra
			// comprobamos el número de caracteres, que puede ser 10,9 y mayor
			// que 2 y menor que 9
			// 1R es un nif válido
			if (longitud > 10 || longitud == 0)
				error = true;
			if (!error) {
				if (elNif.charAt(0) == 'X') {

					// Es el nie de un extranjero
					valorDevuelto = Character.toString(elNif.charAt(0));

					nifValidar = elNif.substring(1, longitud);
					if (nifValidar.length() < 9) {
						nifValidar = gasai.util.Misc.rellenarIzq(nifValidar, "0", 9);
					}
					if (nifValidar.charAt(0) != '0') {
						error = true;
					}
				} else if (elNif.charAt(0) == 'Y' || elNif.charAt(0) == 'Z') {
					// vemos si el NIE es Y7777777L o Z7777777L
					if (longitud > 9) {
						error = true;
					} else {
						valorDevuelto = Character.toString(elNif.charAt(0));

						// cogemos el cuerpo del NIE Y-7777777L
						nifValidar = elNif.substring(1, longitud);
						// Si el cuerpo es menos de 8 digitos 7777777L se
						// rellena por la izquierda
						if (nifValidar.length() < 8) {
							nifValidar = gasai.util.Misc.rellenarIzq(nifValidar, "0", 8);
						}
						// si el NIE empieza por Y hay que sustituir Y por 1
						// quedando 17777777L
						if (elNif.charAt(0) == 'Y') {
							// Es el nie de un extranjero
							nifValidar = "1" + nifValidar;
						}
						// si el NIE empieza por Y hay que sustituir Y por 2
						// quedando 27777777L
						if (elNif.charAt(0) == 'Z') {
							nifValidar = "2" + nifValidar;
						}
					}

				} else {
					if (longitud > 9) {
						error = true;
					} else {
						valorDevuelto = "";
						nifValidar = elNif;
						// En principio se considera un nif español
						if (nifValidar.length() < 9) {
							nifValidar = gasai.util.Misc.rellenarIzq(nifValidar, "0", 9);
						}
					}
				}
			}

			// En nifValidar está el nif/e a validar con 9 caracteres

			// Comprobamos que no haya letras intermedias
			if (!error && !gasai.util.Misc.esDigito(nifValidar.substring(0, nifValidar.length() - 1))) {
				error = true;
			}

			// Comprobar último carácter sea letra, si no es letra darlo por
			// bueno
			if (!error) {
				caracterFinal = nifValidar.substring(8);
				if (caracterFinal.charAt(0) >= 'A' && caracterFinal.charAt(0) <= 'Z') {

					dni = Integer.parseInt(nifValidar.substring(0, 8));

					resto = dni % 23;
					switch (resto) {
					case 0:
						l = "T";
						break;
					case 1:
						l = "R";
						break;
					case 2:
						l = "W";
						break;
					case 3:
						l = "A";
						break;
					case 4:
						l = "G";
						break;
					case 5:
						l = "M";
						break;
					case 6:
						l = "Y";
						break;
					case 7:
						l = "F";
						break;
					case 8:
						l = "P";
						break;
					case 9:
						l = "D";
						break;
					case 10:
						l = "X";
						break;
					case 11:
						l = "B";
						break;
					case 12:
						l = "N";
						break;
					case 13:
						l = "J";
						break;
					case 14:
						l = "Z";
						break;
					case 15:
						l = "S";
						break;
					case 16:
						l = "Q";
						break;
					case 17:
						l = "V";
						break;
					case 18:
						l = "H";
						break;
					case 19:
						l = "L";
						break;
					case 20:
						l = "C";
						break;
					case 21:
						l = "K";
						break;
					case 22:
						l = "E";
						break;
					case 23:
						l = "U";
						break;
					}
					if (!caracterFinal.equals(l))
						error = true;
				} else {
					error = true;
				}
			}
			if (!error) {
				salida.setOk();
				if (elNif.charAt(0) == 'Y' || elNif.charAt(0) == 'Z') {
					valorDevuelto += gasai.util.Misc.rellenarIzq(nifValidar.substring(1, nifValidar.length()), "0", 9);
				} else {
					valorDevuelto += nifValidar;
				}
				salida.setValor(valorDevuelto);
			} else {
				salida.setError(true);
			}

		} catch (Exception e) {
			salida.setError(true);
		}
		return salida;
	}

	public String formateoNie(String agente, String nie) {
		if (Misc.esVacio(nie))
			return "";

		if ((!Misc.esVacio(agente) && sns.config.Inicializacion.vAGENTES_NIE.indexOf(agente) != -1 && nie.charAt(0) == 'X') || nie.charAt(0) == 'Y' || nie.charAt(0) == 'Z') {
			String nieFormateado = formateoSieteDigitos(nie);
			return nieFormateado;
		} else {
			return nie;
		}
	}
}
