package sns.util;

public class OperacionesSns {

  private String elSns="";

  public OperacionesSns(String elSns){
     this.elSns=elSns;
  }

  public OperacionesSns(){
  }

  public static void main(String[] args) {

    if (args.length!=2) {
      System.out.println("uso: java sns.util.OperacionesSns generar|convertir codSns|numero");
      System.exit(-1);
    }

    if (args[0].equals("generar")) {
      OperacionesSns sns2 = new OperacionesSns("BBBBBBBBCC691773");
      System.out.println("Generando para el numero " + args[1] + " el codigoSns -> " + OperacionesSns.genera(args[1]));
    }
    if (args[0].equals("convertir")) {
      OperacionesSns sns2 = new OperacionesSns(args[1]);
      System.out.println("Convertiendo el codSns " + args[1] + " al numero -> " + sns2.convierte());
    }
/*
    BufferedReader in=null;
    PrintWriter pout=null;

    try {
     in= new BufferedReader(new FileReader("/tmp/naf2.txt"));
     pout=new PrintWriter(new FileWriter("/tmp/naf_sns.txt"));
     OperacionesSns sns = new OperacionesSns("BBBBBBBBCM751505");
     String snsAux="";
     String linea="";
     long cont=30751505;

     while ((linea=in.readLine())!=null) {
       snsAux=sns.genera("" + (++cont));
       pout.println(snsAux + "|" + linea.trim());
       System.out.println(sns.genera("39757520"));
     }
   }catch (java.io.FileNotFoundException fe) {
      System.out.println("----- Descripcion: " + fe.getMessage());
    }catch (java.io.IOException ie) {
      System.out.println("----- Descripcion: " + ie.getMessage());
    }finally {
      try {
        if (in!=null)
          in.close();
        if (pout!=null) {
          pout.flush();
          pout.close();
        }
      }catch (java.io.IOException jie) {
        System.out.println(jie.getMessage());
      }
    }
*/
  }

  public String getSns() {
    return this.elSns;
  }

  public static String genera(String sCodigo) {

    String sSns = "";
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
//    this.elSns=sSns;
    return sSns;
  }

  public boolean valida(String sSns) {
    try {
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
    }catch (Exception e)
    {
      return false;
    }
  }

  public String convierte() {

    String sSns = this.elSns;
    String sLetras = "BCDFGHJKLMNPQRSTVWXYZ";
    String sNumero = "";
    int nNum = 0;
    int nTotal = 0;

    // Damos por hecho que es un código válido de Sns
    for (int x = 9; x >= 0  ; x--){
        nNum = sLetras.indexOf(sSns.substring(x,x+1));
        // entra una B
        if (nNum != 0){
          for (int y = 0; y < (9-x);y++)
            nNum = nNum * 21;
          nTotal = nTotal + nNum;
        }
    }
    sNumero = String.valueOf(nTotal) + sSns.substring(10);
    return sNumero;
  }
}


