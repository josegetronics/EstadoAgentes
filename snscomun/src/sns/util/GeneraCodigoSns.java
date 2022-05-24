package sns.util;

/*
Genera el código SNS a partir de un número que le llega por parámetro.
El código SNS consta de 16 caracteres, 10 letras y 6 dígitos:
Los 6 últimos iguales a los 6 últimos del parámetro.
Los primeros hasta completar 16 caracteres "B"s.
Las letras de en medio :
Ir concatenando de izquierda a derecha las letras cuya posición en la cadena :
  "BCDFGHJKLMNPQRSTVWXYZ"
sea el resto de dividir el número entre 21, el resultado otra vez entre 21...
**
Para comprobarlo mirar de izq a dcha el número de la posición de la letra en la
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

    // Si el número es <= 999.999, la parte de letras será "BBBBBBBBBB"
    if (nlong <= 6){
      for (int x=nlong;x<6;x++)
        sCodigo = "0" + sCodigo;
      sSns = "BBBBBBBBBB" + sCodigo;
    }
    else{
      // final del código
      sSns = sCodigo.substring(nlong-6);
      // búsqueda de las letras
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


