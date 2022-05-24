package sns.util;

import gasai.util.Misc;
import sns.model.inss.IInss;
import sns.model.inss.dto.ResultadoCruceExtSnsBean;


public class ComparaDatosSns {

	
	
	public static int compararDatos (IInss inssBean, ResultadoCruceExtSnsBean resultadoCruceExtSnsBean, String criterioEntrada) throws Exception {
		//
		int camposCoincidentes = 0;
		String criterio  = criterioEntrada + "|" ;
		//
		try {
			//logger.debug("INICIO");
			//
			String nombre               = cambiaNombre(Misc.nz(inssBean.getNombre()));
			String apellido1            = cambiaNombre(Misc.nz(inssBean.getApellido1()));
			String apellido2            = cambiaNombre(Misc.nz(inssBean.getApellido2()));
			String codSexo              = Misc.nz(inssBean.getCodSexo());
			String fechaNac             = Misc.nz(inssBean.getFechaNac());
			//
			String nombreInss           = cambiaNombre(Misc.nz(resultadoCruceExtSnsBean.getNombre()));
			String apellido1Inss        = cambiaNombre(Misc.nz(resultadoCruceExtSnsBean.getApellido1()));
			String apellido2Inss        = cambiaNombre(Misc.nz(resultadoCruceExtSnsBean.getApellido2()));
			String codSexoInss          = Misc.nz(resultadoCruceExtSnsBean.getCodSexo());
			String fechaNacInss         = Misc.nz(resultadoCruceExtSnsBean.getFechaNacimiento());
			//
			//logger.debug("nombre:        " + nombre        + ", apellido1:   " + apellido1 +      ", apellido2:     " + apellido2     + ", codSexo: " + codSexo+          ", fechaNac:     " + fechaNac);
			//logger.debug("nombreInss:    " + nombreInss  + ", apellido1Inss: " + apellido1Inss +  ", apellido2Inss: " + apellido2Inss + ", codSexoInss: " + codSexoInss + ", fechaNacInss: " + fechaNacInss);
			//
			boolean nombreIgual    = false;
			boolean apellido1Igual = false;
			boolean apellido2Igual = false;
			boolean codSexoIgual   = false;
			boolean fechaNacIgual  = false;
			//
			if(nombre.equals(nombreInss)) {
				nombreIgual = true;
			}
			if(apellido1.equals(apellido1Inss)) {
				apellido1Igual = true;
			}
			if(apellido2.equals(apellido2Inss)) {
				apellido2Igual = true;
			}
			if(codSexo.equals(codSexoInss)) {
				codSexoIgual = true;
			}
			if(fechaNac.equals(fechaNacInss)) {
				fechaNacIgual = true;
			}
			//
			//
			// NUEVO
			//
			// Mirar inclusion del nombre o de los apellidos en el otro
			if (!nombreIgual && !Misc.esVacio(nombre) && !Misc.esVacio(nombreInss) ){
				if (nombre.indexOf(nombreInss) != -1  ||  nombreInss.indexOf(nombre) != -1) {
					nombreIgual = true;
				}
			}
			if (!apellido1Igual && !Misc.esVacio(apellido1) && !Misc.esVacio(apellido1Inss) ){
				if (apellido1.indexOf(apellido1Inss) != -1  ||  apellido1Inss.indexOf(apellido1) != -1) {
					apellido1Igual = true;
				}
			}
			if (!apellido2Igual && !Misc.esVacio(apellido2) && !Misc.esVacio(apellido2Inss) ){
				if (apellido2.indexOf(apellido2Inss) != -1  ||  apellido2Inss.indexOf(apellido2) != -1) {
					apellido2Igual = true;
				}
			}
			//
			//
			/////////////////////////////////////////////////////////////////////////////////////
			//
			//		APELLIDOS
			//	
			if(apellido1Igual && apellido2Igual) {
				//
				criterio = criterio + "APELLIDO1|APELLIDO2";
				camposCoincidentes = 2;
				//
				if(nombreIgual){
					criterio = criterio + "|NOMBRE";
					camposCoincidentes++;
				}
				if(codSexoIgual || nombreIgual){
					criterio = criterio + "|COD_SEXO";
					camposCoincidentes++;
				}
				if(fechaNacIgual){
					criterio = criterio + "|FECHA_NAC";
					camposCoincidentes++;
				}					
			} // Nuevo
			else {
				if(apellido1Igual && nombreIgual && fechaNacIgual) {
					//
					criterio = "APELLIDO1|NOMBRE|COD_SEXO|FECHA_NAC";
					camposCoincidentes = 4;
				}			
			}
			//
			resultadoCruceExtSnsBean.setCriterioIdentificacion(criterio);
			resultadoCruceExtSnsBean.setCamposCoincidentes(camposCoincidentes);
			//
			//logger.debug("criterio: " + criterio + ", camposCoincidentes: " + camposCoincidentes);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			throw e;
		}
		return camposCoincidentes;
	}	
	
	
	public static String cambiaNombre (String valor) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			if(Misc.nz(valor).equals("XX")) {
				valor = "";
			}
			if(Misc.nz(valor).equals("X")) {
				valor = "";
			}
			//
			if(valor.startsWith("A.")){
				valor = Misc.remplazar(valor, "A.", "");
			}
			//
			if(valor.startsWith("B.")){
				valor = Misc.remplazar(valor, "B.", "");
			}
			//
			if(valor.startsWith("C.")){
				valor = Misc.remplazar(valor, "C.", "");
			}
			//
			if(valor.startsWith("D.")){
				valor = Misc.remplazar(valor, "D.", "");
			}
			//
			if(valor.startsWith("E.")){
				valor = Misc.remplazar(valor, "E.", "");
			}
			//
			if(valor.startsWith("F.")){
				valor = Misc.remplazar(valor, "F.", "");
			}
			//
			if(valor.startsWith("G.")){
				valor = Misc.remplazar(valor, "G.", "");
			}
			//
			if(valor.startsWith("H.")){
				valor = Misc.remplazar(valor, "H.", "");
			}
			//
			if(valor.startsWith("I.")){
				valor = Misc.remplazar(valor, "I.", "");
			}
			//
			if(valor.startsWith("J.")){
				valor = Misc.remplazar(valor, "J.", "");
			}
			//
			if(valor.startsWith("K.")){
				valor = Misc.remplazar(valor, "K.", "");
			}
			//
			if(valor.startsWith("L.")){
				valor = Misc.remplazar(valor, "L.", "");
			}
			//
			if(valor.startsWith("M.")){
				valor = Misc.remplazar(valor, "M.", "");
			}
			//
			if(valor.startsWith("N.")){
				valor = Misc.remplazar(valor, "N.", "");
			}
			//
			if(valor.startsWith("O.")){
				valor = Misc.remplazar(valor, "O.", "");
			}
			//
			if(valor.startsWith("P.")){
				valor = Misc.remplazar(valor, "P.", "");
			}
			//
			if(valor.startsWith("Q.")){
				valor = Misc.remplazar(valor, "Q.", "");
			}
			//
			if(valor.startsWith("R.")){
				valor = Misc.remplazar(valor, "R.", "");
			}
			//
			if(valor.startsWith("S.")){
				valor = Misc.remplazar(valor, "S.", "");
			}
			//
			if(valor.startsWith("T.")){
				valor = Misc.remplazar(valor, "T.", "");
			}
			//
			if(valor.startsWith("U.")){
				valor = Misc.remplazar(valor, "U.", "");
			}
			//
			if(valor.startsWith("V.")){
				valor = Misc.remplazar(valor, "V.", "");
			}
			//
			if(valor.startsWith("W.")){
				valor = Misc.remplazar(valor, "W.", "");
			}
			//
			if(valor.startsWith("Y.")){
				valor = Misc.remplazar(valor, "Y.", "");
			}
			//
			if(valor.startsWith("X.")){
				valor = Misc.remplazar(valor, "X.", "");
			}
			//
			if(valor.startsWith("Z.")){
				valor = Misc.remplazar(valor, "Z.", "");
			}			
			// NUEVO
			valor = Misc.remplazar(valor, "GU", "G");
			valor = Misc.remplazar(valor, "GÜ", "G");
			//
			valor = Misc.remplazar(valor, "G",  "J");
			//
			//
			//
			valor = Misc.remplazar(valor, "Ü", "U");
			valor = Misc.remplazar(valor, "?", "");
			valor = Misc.remplazar(valor, "Ñ", "N");
			valor = Misc.remplazar(valor, "ñ", "");
			valor = Misc.remplazar(valor, "ª", "");
			valor = Misc.remplazar(valor, "º", "");
			valor = Misc.remplazar(valor, "-", " ");
			valor = Misc.remplazar(valor, "_", " ");
			valor = Misc.remplazar(valor, "Á", "A");
			valor = Misc.remplazar(valor, "É", "E");
			valor = Misc.remplazar(valor, "Í", "I");
			valor = Misc.remplazar(valor, "Ó", "O");
			valor = Misc.remplazar(valor, "Ú", "U");
			//
			valor = Misc.remplazar(valor, "À", "A");
			valor = Misc.remplazar(valor, "È", "E");
			valor = Misc.remplazar(valor, "Ì", "I");
			valor = Misc.remplazar(valor, "Ò", "O");
			valor = Misc.remplazar(valor, "Ù", "U");
			//
			valor = Misc.remplazar(valor, "Ä", "A");
			valor = Misc.remplazar(valor, "Ë", "E");
			valor = Misc.remplazar(valor, "Ï", "I");
			valor = Misc.remplazar(valor, "Ö", "O");
			valor = Misc.remplazar(valor, "Ü", "U");
			//
			valor = Misc.remplazar(valor, "Â", "A");
			valor = Misc.remplazar(valor, "Ê", "E");
			valor = Misc.remplazar(valor, "Î", "I");
			valor = Misc.remplazar(valor, "Ô", "O");
			valor = Misc.remplazar(valor, "Û", "U");
			//
			//
			valor = Misc.remplazar(valor, "'", " ");
			valor = Misc.remplazar(valor, "ç", "C");	
			valor = Misc.remplazar(valor, "Ã`", "");
			//
			valor = Misc.remplazar(valor, "TX",  "");
			valor = Misc.remplazar(valor, "TS",  "");
			valor = Misc.remplazar(valor, "CH",  "");
			//
			valor = Misc.remplazar(valor, ".",  "");
			valor = Misc.remplazar(valor, " LA ",  " ");
			valor = Misc.remplazar(valor, " LAS ", " ");
			valor = Misc.remplazar(valor, " LO ",  " ");
			valor = Misc.remplazar(valor, " LOS ", " ");
			valor = Misc.remplazar(valor, " EL ",  " ");
			//
			valor = Misc.remplazar(valor, "FCO. ", "");
			valor = Misc.remplazar(valor, "FCO ", "");
			valor = Misc.remplazar(valor, "DE ", "");
			valor = Misc.remplazar(valor, "DE. ", "");
			valor = Misc.remplazar(valor, "DEL ", "");
			valor = Misc.remplazar(valor, "DEL. ", "");
			//valor = Misc.remplazar(valor, "INA ", "A ");
			//
			if(valor.startsWith("A ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("B ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("C ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("D ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("E ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("F ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("G ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("H ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("I ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("J ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("K ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("L ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("M ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("N ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("O ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("P ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("Q ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("R ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("S ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("T ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("U ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("V ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("W ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("Y ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("X ")){
				valor = valor.substring(2, valor.length());
			}
			//
			if(valor.startsWith("Z ")){
				valor = valor.substring(2, valor.length());
			}
			//
			valor = Misc.remplazar(valor, "H", "");
			// NUEVO
			valor = Misc.remplazar(valor, "AAA", "A");
			valor = Misc.remplazar(valor, "BBB", "B");
			valor = Misc.remplazar(valor, "CCC", "C");
			valor = Misc.remplazar(valor, "DDD", "D");
			valor = Misc.remplazar(valor, "EEE", "E");
			valor = Misc.remplazar(valor, "FFF", "F");
			valor = Misc.remplazar(valor, "GGG", "G");
			valor = Misc.remplazar(valor, "III", "I");
			valor = Misc.remplazar(valor, "JJJ", "J");
			valor = Misc.remplazar(valor, "KKK", "K");
			valor = Misc.remplazar(valor, "LLL", "L");
			valor = Misc.remplazar(valor, "MMM", "M");
			valor = Misc.remplazar(valor, "NNN", "N");
			valor = Misc.remplazar(valor, "OOO", "O");
			valor = Misc.remplazar(valor, "PPP", "P");
			valor = Misc.remplazar(valor, "QQQ", "Q");
			valor = Misc.remplazar(valor, "RRR", "R");
			valor = Misc.remplazar(valor, "SSS", "S");
			valor = Misc.remplazar(valor, "TTT", "T");
			valor = Misc.remplazar(valor, "UUU", "U");
			valor = Misc.remplazar(valor, "VVV", "V");
			valor = Misc.remplazar(valor, "YYY", "Y");
			valor = Misc.remplazar(valor, "XXX", "X");
			valor = Misc.remplazar(valor, "ZZZ", "Z");
			//
			valor = Misc.remplazar(valor, "AA", "A");
			valor = Misc.remplazar(valor, "BB", "B");
			valor = Misc.remplazar(valor, "CC", "C");
			valor = Misc.remplazar(valor, "DD", "D");
			valor = Misc.remplazar(valor, "EE", "E");
			valor = Misc.remplazar(valor, "FF", "F");
			valor = Misc.remplazar(valor, "GG", "G");
			valor = Misc.remplazar(valor, "II", "I");
			valor = Misc.remplazar(valor, "JJ", "J");
			valor = Misc.remplazar(valor, "KK", "K");
			valor = Misc.remplazar(valor, "LL", "L");
			valor = Misc.remplazar(valor, "MM", "M");
			valor = Misc.remplazar(valor, "NN", "N");
			valor = Misc.remplazar(valor, "OO", "O");
			valor = Misc.remplazar(valor, "PP", "P");
			valor = Misc.remplazar(valor, "QQ", "Q");
			valor = Misc.remplazar(valor, "RR", "R");
			valor = Misc.remplazar(valor, "SS", "S");
			valor = Misc.remplazar(valor, "TT", "T");
			valor = Misc.remplazar(valor, "UU", "U");
			valor = Misc.remplazar(valor, "VV", "V");
			valor = Misc.remplazar(valor, "YY", "Y");
			valor = Misc.remplazar(valor, "XX", "X");
			valor = Misc.remplazar(valor, "ZZ", "Z");
			//
			valor = Misc.remplazar(valor, " ",  "");
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			throw e;
		}
		return valor;
	}
	
	
	
}
