package sns.util;

public class ValidaDni {

  public ValidaDni(){
  }

  public static void main(String[] args) {
     ValidaDni dni = new ValidaDni();
     System.out.println(dni.valida(args[0]));
  }

  public boolean valida(String sdni) {

    int dni = 0;
    int resto=0;
    String l = "";
    String p = "";
    String f = "";
    int nlong;
    boolean lSalida= true;

    nlong = sdni.length();
    // Comprobar longitud sea 9
    if (nlong > 9 || nlong == 0)
      lSalida = false;

    // Comprobar que la primera letra sea número o X
    if (lSalida){
      p = sdni.substring(0,1);
      if ((p.charAt(0) >= '0' && p.charAt(0) <= '9') || p.charAt(0) == 'X'){}
      else
        lSalida = false;
    }

    // Comprobar no haya letras intermedias
    if (lSalida){
      for (int nCont = 1; nCont < nlong-1;nCont++){
        if (sdni.charAt(nCont) >= 'A' && sdni.charAt(nCont) <= 'Z') {
          lSalida = false;
          break;
        }
      }
    }
    // Comprobar último carácter sea letra
    if (lSalida){
      f = sdni.substring(nlong-1);
      if (f.charAt(0) <'A' || f.charAt(0) > 'Z')
        lSalida = false;
    }

    // Comprobar la letra sea la correcta
    if (lSalida){
      if (p.equals("X"))
        dni = Integer.parseInt(sdni.substring(1, nlong-1));
      else
        dni = Integer.parseInt(sdni.substring(0, nlong-1));
      // System.out.println(dni);
      // if ((dni > 0) && (dni <= 99999999)) {
      resto = dni % 23;
      switch (resto){
           case 0:
                l="T";
                break;
            case 1:
                l="R";
                break;
            case 2:
                l="W";
                break;
            case 3:
                l="A";
                break;
            case 4:
               l="G";
               break;
            case 5:
               l="M";
               break;
            case 6:
               l="Y";
               break;
            case 7:
               l="F";
               break;
            case 8:
               l="P";
               break;
            case 9:
               l="D";
               break;
            case 10:
               l="X";
               break;
            case 11:
               l="B";
               break;
            case 12:
               l="N";
               break;
            case 13:
               l="J";
               break;
            case 14:
               l="Z";
               break;
            case 15:
               l="S";
               break;
            case 16:
               l="Q";
               break;
            case 17:
               l="V";
               break;
            case 18:
               l="H";
               break;
            case 19:
               l="L";
               break;
            case 20:
               l="C";
               break;
            case 21:
               l="K";
               break;
            case 22:
               l="E";
               break;
            case 23:
               l="U";
               break;
      }
      if (!f.equals(l))
           lSalida = false;
    }
    return lSalida;
  }
}


