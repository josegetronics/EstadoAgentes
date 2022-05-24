package sns.util;

public class ValidaCip {

  public ValidaCip(){
  }

    public static void main(String[] args) {
     ValidaCip cip = new ValidaCip();
     System.out.println(cip.valida(args[0], args[1], args[2]));
  }

  public boolean valida(String sCip, String sApe1, String sApe2) {

    sApe1 = sApe1.toUpperCase();
    sApe2 = sApe2.toUpperCase();
    String sConsonantes = "BCDFGHJKLMNPQRSTVWXYZ";
    int nlong= 0;
    int ncip=0;
    String sCipGen = "";
    boolean lSalida = true;

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

      // System.out.println(sCip);
      if (!sCip.equals(sCipGen))
        lSalida =  false;
    }
    return lSalida;
  }
}


