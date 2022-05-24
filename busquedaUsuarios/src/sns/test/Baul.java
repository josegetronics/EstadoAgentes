package sns.test;

import sns.busqueda.model.BusquedaAproximadaUtil;
import sns.util.Misc;

public class Baul {

	/*
	 	public static String sustituirSubcadenas2(String valor, boolean borrarLetraN) throws Exception {
		//
		try {
			// logger.debug("INICIO");
			//
			//////////////  Ñ  ////////////////////////////
			valor = Misc.remplazar(valor, "Ñ", "");	
			//
			if (borrarLetraN) {
				valor = Misc.remplazar(valor, "N", "");
			}
			///////////////////////////////////////////////
			//
			//
			if (Misc.nz(valor).equals("XX")) {
				valor = "";
			}
			if (Misc.nz(valor).equals("X")) {
				valor = "";
			}
			if(Misc.nz(valor).indexOf("XXX")!=-1){
				valor = BusquedaAproximadaUtil.quitarCadenaIgualEquis (valor);
			}
			//
			if (valor.startsWith("A.")) {
				valor = Misc.remplazar(valor, "A.", "");
			}
			//
			if (valor.startsWith("B.")) {
				valor = Misc.remplazar(valor, "B.", "");
			}
			//
			if (valor.startsWith("C.")) {
				valor = Misc.remplazar(valor, "C.", "");
			}
			//
			if (valor.startsWith("D.")) {
				valor = Misc.remplazar(valor, "D.", "");
			}
			//
			if (valor.startsWith("E.")) {
				valor = Misc.remplazar(valor, "E.", "");
			}
			//
			if (valor.startsWith("F.")) {
				valor = Misc.remplazar(valor, "F.", "");
			}
			//
			if (valor.startsWith("G.")) {
				valor = Misc.remplazar(valor, "G.", "");
			}
			//
			if (valor.startsWith("H.")) {
				valor = Misc.remplazar(valor, "H.", "");
			}
			//
			if (valor.startsWith("I.")) {
				valor = Misc.remplazar(valor, "I.", "");
			}
			//
			if (valor.startsWith("J.")) {
				valor = Misc.remplazar(valor, "J.", "");
			}
			//
			if (valor.startsWith("K.")) {
				valor = Misc.remplazar(valor, "K.", "");
			}
			//
			if (valor.startsWith("L.")) {
				valor = Misc.remplazar(valor, "L.", "");
			}
			//
			if (valor.startsWith("M.")) {
				valor = Misc.remplazar(valor, "M.", "");
			}
			//
			if (valor.startsWith("N.")) {
				valor = Misc.remplazar(valor, "N.", "");
			}
			//
			if (valor.startsWith("O.")) {
				valor = Misc.remplazar(valor, "O.", "");
			}
			//
			if (valor.startsWith("P.")) {
				valor = Misc.remplazar(valor, "P.", "");
			}
			//
			if (valor.startsWith("Q.")) {
				valor = Misc.remplazar(valor, "Q.", "");
			}
			//
			if (valor.startsWith("R.")) {
				valor = Misc.remplazar(valor, "R.", "");
			}
			//
			if (valor.startsWith("S.")) {
				valor = Misc.remplazar(valor, "S.", "");
			}
			//
			if (valor.startsWith("T.")) {
				valor = Misc.remplazar(valor, "T.", "");
			}
			//
			if (valor.startsWith("U.")) {
				valor = Misc.remplazar(valor, "U.", "");
			}
			//
			if (valor.startsWith("V.")) {
				valor = Misc.remplazar(valor, "V.", "");
			}
			//
			if (valor.startsWith("W.")) {
				valor = Misc.remplazar(valor, "W.", "");
			}
			//
			if (valor.startsWith("Y.")) {
				valor = Misc.remplazar(valor, "Y.", "");
			}
			//
			if (valor.startsWith("X.")) {
				valor = Misc.remplazar(valor, "X.", "");
			}
			//
			if (valor.startsWith("Z.")) {
				valor = Misc.remplazar(valor, "Z.", "");
			}
			//
			if (valor.startsWith("A ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("B ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("C ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("D ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("E ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("F ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("G ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("H ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("I ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("J ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("K ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("L ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("M ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("N ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("O ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("P ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("Q ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("R ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("S ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("T ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("U ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("V ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("W ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("Y ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("X ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			if (valor.startsWith("Z ")) {
				valor = valor.substring(2, valor.length());
			}
			//
			//
			//
			///////////////////////////////////////
			//
			//   PUNTO Y ESPACIO EN BLANCO
			//
			valor = Misc.remplazar(valor, ".", "");
			valor = Misc.remplazar(valor, " ", "");
			///////////////////////////////////////
			//
			//
			//
			//
			// // // // //   VOCALES    // // // //
			//
			valor = Misc.remplazar(valor, "Ü", "U");
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
			//
			//
			//
			// // // // // // // PARTICULAS // // // // //
			if (valor.startsWith("DE ")) {
				valor = valor.substring(3, valor.length());
			}
			if (valor.startsWith("DEL ")) {
				valor = valor.substring(4, valor.length());
			}
			if (valor.startsWith("EL ")) {
				valor = valor.substring(3, valor.length());
			}
			if (valor.startsWith("LA ")) {
				valor = valor.substring(3, valor.length());
			}
			if (valor.startsWith("LO ")) {
				valor = valor.substring(3, valor.length());
			}
			if (valor.startsWith("LAS ")) {
				valor = valor.substring(4, valor.length());
			}
			if (valor.startsWith("LOS ")) {
				valor = valor.substring(4, valor.length());
			}			
			if (valor.endsWith(" DE")) {
				valor = valor.substring(0, valor.length()-3);
			}
			if (valor.endsWith(" DEL")) {
				valor = valor.substring(0, valor.length()-4);
			}
			if (valor.endsWith(" EL")) {
				valor = valor.substring(0, valor.length()-3);
			}
			if (valor.endsWith(" LA")) {
				valor = valor.substring(0, valor.length()-3);
			}
			if (valor.endsWith(" LO")) {
				valor = valor.substring(0, valor.length()-3);
			}
			if (valor.endsWith(" LAS")) {
				valor = valor.substring(0, valor.length()-4);
			}
			if (valor.endsWith(" LOS")) {
				valor = valor.substring(0, valor.length()-4);
			}
			valor = Misc.remplazar(valor, " DE ",  "");
			valor = Misc.remplazar(valor, " DEL ", "");
			valor = Misc.remplazar(valor, " LA ",  "");
			valor = Misc.remplazar(valor, " LAS ", "");
			valor = Misc.remplazar(valor, " LO ",  "");
			valor = Misc.remplazar(valor, " LOS ", "");
			valor = Misc.remplazar(valor, " EL ",  "");
			valor = Misc.remplazar(valor, " LA ",  "");
			valor = Misc.remplazar(valor, " LAS ", "");
			valor = Misc.remplazar(valor, " LO ",  "");
			valor = Misc.remplazar(valor, " LOS ", "");
			valor = Misc.remplazar(valor, " EL ",  "");
			/////////////////////////////////////////////
			//
			//
			// // // CARACTERES REPETIDOS // // // // 
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
			valor = Misc.remplazar(valor, "HH", "I");
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
			/////////////////////////////////////////
			// 
			//
			//
			// // // // // CARACTERES EXTRAÑOS // // 
			//
			//
			valor = Misc.remplazar(valor, "?", "");
			valor = Misc.remplazar(valor, "ñ", "");
			valor = Misc.remplazar(valor, "ª", "");
			valor = Misc.remplazar(valor, "º", "");
			valor = Misc.remplazar(valor, "-", " ");
			valor = Misc.remplazar(valor, "'", " ");
			valor = Misc.remplazar(valor, "ç", "C");
			valor = Misc.remplazar(valor, "Ç", "C");
			valor = Misc.remplazar(valor, "Ã`", "");
			valor = Misc.remplazar(valor, "I¿½", "Ñ");
			valor = Misc.remplazar(valor, "¿", "Ñ");
			valor = Misc.remplazar(valor, "¿", "");
			valor = Misc.remplazar(valor, "½", "");
			valor = Misc.remplazar(valor, "ï", "");
			valor = Misc.remplazar(valor, "*", "");
			valor = Misc.remplazar(valor, "¥", "");
			valor = Misc.remplazar(valor, "Ê", "");
			valor = Misc.remplazar(valor, "@", "");
			valor = Misc.remplazar(valor, "§", "");
			valor = Misc.remplazar(valor, "¦", "");
			valor = Misc.remplazar(valor, "+", "");
			valor = Misc.remplazar(valor, "^", "");
			valor = Misc.remplazar(valor, "Ð", "");
			valor = Misc.remplazar(valor, "µ", "");
			valor = Misc.remplazar(valor, "!", "");
			valor = Misc.remplazar(valor, "¡", "");
			valor = Misc.remplazar(valor, "û", "");
			valor = Misc.remplazar(valor, "", "");
			valor = Misc.remplazar(valor, "å", "");
			valor = Misc.remplazar(valor, "ê", "");
			valor = Misc.remplazar(valor, "ü", "");
			valor = Misc.remplazar(valor, "¨", "");
			valor = Misc.remplazar(valor, "¤", "");
			valor = Misc.remplazar(valor, "ø", "");
			valor = Misc.remplazar(valor, "×", "");
			valor = Misc.remplazar(valor, "?", "");
			valor = Misc.remplazar(valor, "Ì", "");
			valor = Misc.remplazar(valor, "~", "");
			valor = Misc.remplazar(valor, "Ø", "");
			valor = Misc.remplazar(valor, ">", "");
			valor = Misc.remplazar(valor, "", "");
			valor = Misc.remplazar(valor, "=", "");
			valor = Misc.remplazar(valor, "Å", "");
			valor = Misc.remplazar(valor, "$", "");
			valor = Misc.remplazar(valor, "Ã", "");
			valor = Misc.remplazar(valor, "ý", "");
			valor = Misc.remplazar(valor, "ã", "");
			valor = Misc.remplazar(valor, "é", "");
			valor = Misc.remplazar(valor, "%", "");
			valor = Misc.remplazar(valor, "<", "");
			valor = Misc.remplazar(valor, "ç", "");
			valor = Misc.remplazar(valor, "]", "");
			valor = Misc.remplazar(valor, "å", "");
			valor = Misc.remplazar(valor, "[", "");
			valor = Misc.remplazar(valor, "&", "");
			valor = Misc.remplazar(valor, "_", "");
			valor = Misc.remplazar(valor, "´", "");
			valor = Misc.remplazar(valor, "\"", "");
			valor = Misc.remplazar(valor, "'", "");
			valor = Misc.remplazar(valor, "{", "");
			valor = Misc.remplazar(valor, "`", "");
			//
			// CONFIGURAR
			// 
			//valor = Misc.remplazar(valor, "GU", "G");
			//valor = Misc.remplazar(valor, "GÜ", "G");
			//valor = Misc.remplazar(valor, "H", "");
			valor = Misc.remplazar(valor, "G",  "");
			valor = Misc.remplazar(valor, "J",  "");
			valor = Misc.remplazar(valor, "FCO ", "");
			//
			valor = Misc.remplazar(valor, "TX", "");
			valor = Misc.remplazar(valor, "TS", "");
			valor = Misc.remplazar(valor, "CH", "");
			//
			//
			//
			// logger.debug("FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return valor;
	} 
	 
	 */
	
	
	
	
}
