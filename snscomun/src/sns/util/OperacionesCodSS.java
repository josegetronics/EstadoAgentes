package sns.util;

public class OperacionesCodSS {

  private String elCod="";

  public OperacionesCodSS(String elCod){
     this.elCod=elCod;
  }

  public static void main(String[] args) {
	    System.out.println(sns.util.OperacionesCodSS.genera("YDE LA TORRE", "JIMENEZ",null,null,null,null));
//	    System.out.println(sns.util.OperacionesCodSS.genera("YDE LA TORRE", "JIMENEZ","2","1967-01-13","4",""));
//      System.out.println(OperacionesCodSS.genera("BEA","DE LA PARRA",null,"1976-12-03",null,null));
     // System.out.println(snsCodigo.genera());
  }

  public static String rellenarCeros(String numero,int numCeros) {
      String result=numero;
      for (int i=0;i<(numCeros-numero.length());i++)
        result = "0" + result;
      return result;
  }

  public static String genera(String sApe1, String sApe2, String cSexo, String cFecha, String cComunidad) {
    return genera(sApe1,sApe2,cSexo,cFecha,cComunidad,"724");
  }

  public static String genera(String sApe1, String sApe2, String cSexo, String cFecha, String cComunidad,String pais) {

    String sCod = "";

    try {
      String nDias;

      String parte1=tratarApellido(sApe1);

      sCod += parte1;

      String parte2=tratarApellido(sApe2);
      sCod += parte2;

      // Añadir los 2 últimos dígitos del año de nacimiento, el mes y el día
      // Si es mujer (cSexo=2), se suman 40 al día. La fecha ya está validada
      // fecha=yyyy-mm-dd

      if (cFecha!=null && !cFecha.equals("")) {
        sCod = sCod + cFecha.substring(2,4) + cFecha.substring(5,7);
        if (cSexo!=null && !cSexo.equals("")) {
          if (cSexo.equals("2"))
            nDias = Integer.toString( Integer.parseInt(cFecha.substring(8, 10)) + 40 );
          else
            nDias = cFecha.substring(8,10);
        }else
            nDias = cFecha.substring(8,10);

        sCod = sCod + rellenarCeros(nDias,2);
      }



      if (pais!=null && !pais.equals("")) {
        if (!pais.equals("724"))
          sCod += rellenarCeros(pais,3);
        else {
          sCod += "9" + rellenarCeros(cComunidad,2);
        }
      }else{
        if (cComunidad!=null && !cComunidad.equals("")){
          sCod += "9" + rellenarCeros(cComunidad,2);
        }
      }

    }catch(Exception e) {
      System.out.println("Error en raiz-> sApe1: #" + sApe1 + "#, sApe2: #" + sApe2 + "#, cSexo: #" + cSexo + "#, cFecha: #" + cFecha + "#, cComunidad: #" + cComunidad + "#" + e.getMessage() + " - " + e.toString());
    }
    return sCod;
  }

  private static String tratarApellido(String apellido) {
    CorregirAcentos acentos = new CorregirAcentos();
    String sApe = acentos.limpiarPalabra2(apellido,"C");
    String sConsonantes = "BCDFGHJKLMNPQRSTVWXYZ";
//    String sVocales = "AEIOUY";
    int nlong= 0;
    int ncip=0;
    String nDias;
    boolean lblanco = false; // si es hay un espacio en blanco
    String apell1Aux="";
    if (!(sApe!=null && !sApe.trim().equals(""))) {
//        sCod = "XX";
      apell1Aux = "XX";
    }
    else {
      nlong = sApe.length();
      String letraActual="";
      for (int nCont = 0; nCont < nlong ;nCont++){
          // System.out.println(sApe1.substring(nCont,nCont+1));
        letraActual=sApe.substring(nCont,nCont+1);
        if (letraActual.equals(" ")) {
          lblanco = true;
          continue;
        }
        if (sConsonantes.indexOf(letraActual) != -1) {
          if (letraActual.equals("Y") && (lblanco || nCont==0)) {
            if (nCont+2 < nlong && sApe.substring(nCont+1,nCont+2).equals(" ")) {
              nCont ++;
              lblanco = false;
              continue;
            }
          }
//            sCod = sCod + sApe1.substring(nCont,nCont+1);
          apell1Aux += letraActual;
          ncip++;
          if (ncip == 2)
            break;
        }
      }
      // Completar dos caracteres por apellido, si no hay consonantes se ponen las primeras vocales
      String letraAnt="";
      String letraSig="";

      for (int nCont = 0; nCont < nlong && ncip < 2; nCont++ ) {
        letraActual=sApe.substring(nCont,nCont+1);
        if (sApe.length()>=(nCont + 2))
          letraSig=sApe.substring(nCont+1,nCont+2);
        else
          letraSig="";

//        if (sVocales.indexOf(letraActual) != -1) {
        if (esVocal(letraActual,letraAnt,letraSig)) {
          apell1Aux += letraActual;
          ncip++;
        }
        letraAnt=letraActual;
      }
    }


    int lenAp1=apell1Aux.length();
    for (int i=lenAp1; lenAp1<2;i++) {
      apell1Aux += "X";
      lenAp1=apell1Aux.length();
    }

    return apell1Aux;

  }

  private static boolean esVocal(String letraActual,String letraAnt, String letraSig) {
    String sVocales = "AEIOUY";
    boolean respuesta=false;
    if (sVocales.indexOf(letraActual) != -1) {
      if (letraActual.equals("Y")) {
        if (letraAnt.equals("") && letraSig.equals(" "))
          respuesta=true;
        if (letraAnt.equals(" ") && letraSig.equals(" "))
          respuesta=true;
        if (letraAnt.equals(" ") && letraSig.equals(""))
          respuesta=true;
      }else
        respuesta=true;
    }else {
      respuesta=false;
    }
    return respuesta;
  }

  public static String genera(String sApe1, String sApe2, String cSexo, String cFecha) {
     return genera(sApe1,sApe2,cSexo,cFecha,"","");
 }

  public static String genera(String sApe1, String sApe2, int codSexo, String cFecha, String cComunidad) {
    return genera(sApe1,sApe2,"" + codSexo,cFecha,cComunidad,"724");
  }


}


