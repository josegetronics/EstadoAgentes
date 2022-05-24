package sns.util;

import java.math.BigDecimal;
public class OperacionesCip {

  private String elCip="";

  public OperacionesCip(String elCip){
     this.elCip=elCip;
  }
  public String genera(String sApe1, String sApe2, int cSexo, String cFecha, BigDecimal caComunidad, String caNac, String paisNac)
   {

    String raiz="";
    raiz = sns.util.OperacionesCodSS.genera(sApe1, sApe2,  cSexo,  cFecha,  caNac.toString());

    // cambiar letras a números
    String sNumCip = "";
    String sCip= "";
    // Calcular el dígito de control
    int dControl= 0;
    String autonomia="";

    //habra que validar que el pais sea españa u otro contra la bd
    if (caNac!=null && !caNac.equals(""))
      autonomia="9" + caNac;
    else
      autonomia=rellenarCeros(paisNac,3);

    sCip = raiz.substring(0,raiz.length()-3) + autonomia + "01";

    int n = 0;
    int nTotal = 0;

    // Pasar letras a números
    int letra1 = 0;
    letra1 = 1070 + (30 * Character.getNumericValue(sCip.charAt(0)) -9);
    letra1 += letra1 + Character.getNumericValue(sCip.charAt(1)) -9;
    letra1 = letra1 -1000;
    int letra2 = 0;
    letra2 += letra2 + (30 * Character.getNumericValue(sCip.charAt(2)) -9);
    letra2 += letra2 + Character.getNumericValue(sCip.charAt(3)) -9;
    letra2 = letra2 -1000;
    //

    sNumCip = Integer.toString(letra1) + Integer.toString(letra2) + sCip.substring(4);
    for (int x=0;x<= 16;x++){
      if (x % 2 == 0){
        n = (Integer.parseInt(sNumCip.substring(x, x+1))) * 2;
      }
      else {
        n = (Integer.parseInt(sNumCip.substring(x, x+1)));
      } // resto ver posición
      nTotal +=n;
    }// for de la cadena

    int nlong = String.valueOf(nTotal).length();
    dControl = 10 - ((int)String.valueOf(nTotal).charAt(nlong));

    // Añadir el dígito de control al Cip
    sCip = sCip + String.valueOf(dControl);

    // System.out.println(sCip);
    this.elCip = sCip;

    //System.out.println(sCip.substring(15));
    //System.out.println(sCip.substring(13,14));
    return sCip;

  }

   public boolean valida(String sApe1, String sApe2, int cSexo, String cFecha, BigDecimal caComunidad) {
    String sCip = this.elCip;
    sApe1 = sApe1.toUpperCase();
    sApe2 = sApe2.toUpperCase();
    String cComunidad = caComunidad.toString();
    String sConsonantes = "BCDFGHJKLMNPQRSTVWXYZ";

    // la posición corresponde a lo grabado en la base de datos, la segunda
    // dimensión es el cód de comunidad para el cip
    /*
    aComunidad[ 9, 1] = "CALATUNYA";
    aComunidad[15, 2] = "EUSKADI";
    aComunidad[11, 3] = "GALICIA";
    aComunidad[ 1, 4] = "ANDALUCIA";
    aComunidad[ 3, 5] = "ASTURIAS";
    aComunidad[ 6, 6] = "CANTABRIA";
    aComunidad[16, 7] = "LA RIOJA";
    aComunidad[13, 8] = "MURCIA";
    aComunidad[17, 9] = "VALENCIA";
    aComunidad[ 2,10] = "ARAGON";
    aComunidad[ 7,11] = "CAST. MANCHA";
    aComunidad[ 5,12] = "CANARIAS";
    aComunidad[10,13] = "EXTREMADURA";
    aComunidad[ 4,14] = "BALEARES";
    aComunidad[14,15] = "NAVARRA";
    aComunidad[12,16] = "MADRID";
    aComunidad[ 8,17] = "CAST. LEON";
    aComunidad[18,18] = "CEUTA";
    aComunidad[19,19] = "MELILLA";
    */
    String aComunidad[] = new String[20];
    aComunidad[ 1] = "04";
    aComunidad[ 2] = "10";
    aComunidad[ 3] = "05";
    aComunidad[ 4] = "14";
    aComunidad[ 5] = "12";
    aComunidad[ 6] = "06";
    aComunidad[ 7] = "11";
    aComunidad[ 8] = "17";
    aComunidad[ 9] = "01";
    aComunidad[10] = "13";
    aComunidad[11] = "03";
    aComunidad[12] = "16";
    aComunidad[13] = "08";
    aComunidad[14] = "15";
    aComunidad[15] = "02";
    aComunidad[16] = "07";
    aComunidad[17] = "09";
    aComunidad[18] = "18";
    aComunidad[19] = "19";

    int nlong= 0;
    int ncip=0;
    String sCipGen = "";
    boolean lSalida = true;

    // System.out.println(sCip);

    if (sCip.length() != 4 )
      lSalida = false;

    if (lSalida){
      // Recorrer el apellido buscando consonantes
      nlong = sApe1.length();
      for (int nCont = 0; nCont < nlong ;nCont++){
          // System.out.println(sApe1.substring(nCont,nCont+1));
          if (sConsonantes.indexOf(sApe1.substring(nCont,nCont+1)) != -1) {
            sCipGen = sCipGen + sApe1.substring(nCont,nCont+1);
            ncip++;
            if (ncip == 2)
              break;
          }
      }
      // Completar dos caracteres por apellido, si no hay consonantes se pone X
      for (int nCont = ncip; nCont < 2;nCont++)
          sCipGen = sCipGen + "X";

      ncip = 0;
      nlong = sApe2.length();
      for (int nCont = 0; nCont < nlong ;nCont++){
          // System.out.println(sApe2.substring(nCont,nCont+1));
          if (sConsonantes.indexOf(sApe2.substring(nCont,nCont+1)) != -1) {
            sCipGen = sCipGen + sApe2.substring(nCont,nCont+1);
            ncip++;
            if (ncip == 2)
              break;
          }
      }
      // Completar dos caracteres por apellido, si no hay consonantes se pone X
      for (int nCont =ncip; nCont < 2;nCont++)
        sCipGen = sCipGen + "X";

        // Añadir los 2 últimos dígitos del año de nacimiento, el mes y el día
        // Si es mujer (cSexo=2), se suman 40 al día. La fecha ya está validada
        // fecha=yyyy-mm-dd
        sCipGen = sCipGen + cFecha.substring(2,4) + cFecha.substring(5,7);

        // Ya no se suman 40, se concatenamos el sexo
        // if (cSexo==2)
        //  nDias = Integer.parseInt(cFecha.substring(8)) + Integer.parseInt("40");
        // else
        int nDias = Integer.parseInt(cFecha.substring(8));
        sCipGen = sCipGen + Integer.toString(nDias) + cSexo + "9" + cComunidad + "01" ;


      // System.out.println(sCipGen);
      if (!sCip.equals(sCipGen))
        lSalida =  false;
    }
    return lSalida;
  }

    public static String rellenarCeros(String numero,int numCeros) {
      String result=numero;
      for (int i=0;i<(numCeros-numero.length());i++)
        result = "0" + result;
      return result;
  }

}


