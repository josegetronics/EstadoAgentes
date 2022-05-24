package sns.util;

public class CorregirAcentos {

  public CorregirAcentos(){
  }

  public static void main(String[] args) {
     CorregirAcentos cip = new CorregirAcentos();
  }

  // tipo : "C" : para generar CIP, cambiar — y «
  public String limpiarPalabra(String palabra){
      return limpiarPalabra2(palabra, "");
  }

  public String limpiarPalabra2(String palabra, String tipo)
  {
    try {
      if (palabra == null)
        return null;

      String cadenaA = "¿¡ƒ¬";
      String cadenaE = "»À… ";
      String cadenaI = "ÃÕŒœ";
      String cadenaO = "“”‘÷";
      String cadenaU = "Ÿ⁄€‹";

      palabra = palabra.toUpperCase();
// System.out.println("PALABRA: " + palabra);
      int longitud;
      longitud = palabra.length();
      for (int x = 0; x < longitud; x++) {
        if (cadenaA.indexOf(palabra.substring(x, x + 1)) != -1) {
          palabra = (x > 0 ? palabra.substring(0, x) : "") + "A" +
              palabra.substring(x + 1);
          continue;
        }

        if (cadenaE.indexOf(palabra.substring(x, x + 1)) != -1) {
//System.out.println("letra " + x + ":" + palabra.substring(0,x+1));
          palabra = (x > 0 ? palabra.substring(0, x) : "") + "E" +
              palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
          continue;
        }
        if (cadenaI.indexOf(palabra.substring(x, x + 1)) != -1) {
          palabra = (x > 0 ? palabra.substring(0, x) : "") + "I" +
              palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
          continue;
        }

        if (cadenaO.indexOf(palabra.substring(x, x + 1)) != -1) {
          palabra = (x > 0 ? palabra.substring(0, x) : "") + "O" +
              palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
          continue;
        }

        if (cadenaU.indexOf(palabra.substring(x, x + 1)) != -1) {
          palabra = (x > 0 ? palabra.substring(0, x) : "") + "U" +
              palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
          continue;
        }

        if (tipo.equals("C")) {
          if (palabra.substring(x, x + 1).equals("—")) {
            palabra = (x > 0 ? palabra.substring(0, x) : "") + "X" +
                palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
            continue;
          }
          if (palabra.substring(x, x + 1).equals("«")) {
            palabra = (x > 0 ? palabra.substring(0, x) : "") + "C" +
                palabra.substring(x + 1);
//System.out.println("Cambiado :" + palabra);
            continue;
          }
        }
      }

    }catch (java.lang.Throwable e) {

    }
    return palabra;
  }

}


