package sns.util;

/*
Genera el c�digo SNS a partir de un n�mero que le llega por par�metro.
El c�digo SNS consta de 16 caracteres, 10 letras y 6 d�gitos:
Los 6 �ltimos iguales a los 6 �ltimos del par�metro.
Los primeros hasta completar 16 caracteres "B"s.
Las letras de en medio :
Ir concatenando de izquierda a derecha las letras cuya posici�n en la cadena :
  "BCDFGHJKLMNPQRSTVWXYZ"
sea el resto de dividir el n�mero entre 21, el resultado otra vez entre 21...
**
Para comprobarlo mirar de izq a dcha el n�mero de la posici�n de la letra en la
cadena y hacer el sumatorio de n + (n1*21) + (n2*21*21) + ...
ej: XV123456 :  394.123.456
      V - 16
      X - 18 * 21 = 378
      16 + 378 = 394
*/

public class GeneraCodigoSns {

  private String elSns="";

  public GeneraCodigoSns(String elSns){
     this.elSns=elSns;
  }

  public static void main(String[] args) {
     GeneraCodigoSns snsCodigo = new GeneraCodigoSns(args[0]);
     // System.out.println(snsCodigo.genera());
  }

  public String genera() {

    String sSns = "";
    String sCodigo = this.elSns;
    String sLetras = "BCDFGHJKLMNPQRSTVWXYZ";
    Integer nCodigo = new Integer(0);
    int resto = 0;
    int nlong = 0;

    nlong = sCodigo.length();

    // Si el n�mero es <= 999.999, la parte de letras ser� "BBBBBBBBBB"
    if (nlong <= 6){
      for (int x=nlong;x<6;x++)
        sCodigo = "0" + sCodigo;
      sSns = "BBBBBBBBBB" + sCodigo;
    }
    else{
      // final del c�digo
      sSns = sCodigo.substring(nlong-6);
      // b�squeda de las letras
      int nn= 0;
      sCodigo = sCodigo.substring(0, nlong-6);
      nCodigo = Integer.valueOf(sCodigo);
      nn = nCodigo.intValue();
      while (true){
        resto = nn % 21;
        sSns = sLetras.substring(resto,resto+1) + sSns;
        nn = nn / 21;
        if (nn <= 21)
          break;
      }
      if (nn > 0){
        resto = nn % 21;
        sSns = sLetras.substring(resto,resto+1) + sSns;
      }
      nlong = sSns.length();
      for (int x=nlong;x<16;x++)
        sSns = "B" + sSns;
    }
    return sSns;
  }
}


