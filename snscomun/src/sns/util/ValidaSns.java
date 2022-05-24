package sns.util;

public class ValidaSns {


  public ValidaSns(){
  }

  public static void main(String[] args) {
     ValidaSns sns = new ValidaSns();
     System.out.println(sns.valida(args[0]));
  }

  public boolean valida(String sSns) {

    String f = "";
    int nlong;
    boolean lSalida= true;
    String sLetras = "BCDFGHJKLMNPQRSTVWXYZ";
    nlong = sSns.length();
    // Comprobar longitud sea 16
    if (nlong != 16)
      lSalida = false;

    // Comprobar los 10 primeros caracteres sean letras incluidas en las 21 posibles
    if (lSalida){
      for (int nCont = 0; nCont < 10;nCont++){
        // System.out.println(sSns.substring(nCont,nCont+1));
        if (sLetras.indexOf(sSns.substring(nCont,nCont+1)) == -1) {
          lSalida = false;
          break;
        }
      }
    }
    // Comprobar los 6 últimos caracteres sean números
    if (lSalida){
      for (int nCont = 10; nCont < 16;nCont++){
        // System.out.println(sSns.substring(nCont,nCont+1));
        if (!(sSns.charAt(nCont) >= '0' && sSns.charAt(nCont) <= '9')) {
          lSalida = false;
          break;
        }
      }
    }
    // sería 0
    if (sSns.equals("BBBBBBBBBB000000"))
      lSalida = false;

    return lSalida;
  }
}


