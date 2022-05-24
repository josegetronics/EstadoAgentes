package sns.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.StringTokenizer;

import sns.config.Constantes;
import sns.util.out.Salida;

public class OperacionesFecha {

  private String laFecha="";

  //  Comprobar fecha operación / modifición
  public static Salida comprobarFOperacion(String fechaOp, Timestamp fechaUltAct) {
    Salida salida = new Salida();
//    OperacionesFecha validarFecha = new OperacionesFecha();
    boolean fechaValido = fechaOperacionValida(fechaOp);
    if (!fechaValido) {
      salida.setError(true);
      salida.setMsg("La fecha de operación [" + fechaOp + "] no es valida");
      salida.setValor(Constantes.ERROR_EG007);
      salida.setMsgException("La fecha de operación --[" + fechaOp + "] no es valida");
      return salida;
    }
    Timestamp tmFechaOp = new Timestamp(conviertefechaOperacion(fechaOp));

    if (fechaUltAct==null) {
      salida.setOk();
      salida.setValor(tmFechaOp);
      return salida;
    }


    if (!tmFechaOp.after(fechaUltAct)) {
      salida.setError(true);
      salida.setMsg(
          "La fecha de operacion es anterior a la ultima actualizacion");
      salida.setValor(Constantes.ERROR_EG007);
      salida.setMsgException(
          "La fecha de operacion es anterior a la ultima actualizacion");
    }
    else {
      salida.setOk();
      salida.setValor(tmFechaOp);
    }

    return salida;
  }

  public OperacionesFecha(String laFecha){
     this.laFecha=laFecha;
  }

  public OperacionesFecha(){
     this.laFecha="";
  }

  public static void main(String[] args) {
    comprobarFOperacion("2006-02-23-10-10-34",new java.sql.Timestamp(System.currentTimeMillis()));
     System.out.println(fechaSistemaXml());
  }


  public boolean valida() {
    return valida(this.laFecha,true);
  }

  public boolean valida(String laFechaParam) {
    return valida(laFechaParam,true);
  }

  public boolean valida(boolean hastahoy) {
    return valida(this.laFecha,hastahoy);
  }

  public boolean valida(String laFechaParam,boolean hastahoy) {
  //valida que la fecha tenga el formato yyyy-mm-dd

    // System.out.println("comprobando la fecha: " + this.laFecha);
    Calendar fechaActual = Calendar.getInstance();
    Calendar fechaIn = Calendar.getInstance();
    java.sql.Date dFecha=null;
    java.sql.Date dFechaActual=null;
    boolean lSalida = true;

    try{
      StringTokenizer st=new StringTokenizer(laFechaParam,"-");

      if (laFechaParam.length() != 10)
        lSalida = false;
      if (lSalida && laFechaParam.indexOf("-") != 4)
        lSalida = false;
      if (lSalida && laFechaParam.lastIndexOf("-") != 7)
        lSalida = false;

      if (lSalida && !(Integer.parseInt(laFechaParam.substring(5,7)) >= Integer.parseInt("01") && Integer.parseInt(laFechaParam.substring(5,7)) <= Integer.parseInt("12")))
        lSalida = false;

      if (lSalida && !(Integer.parseInt(laFechaParam.substring(8)) >= Integer.parseInt("01") && Integer.parseInt(laFechaParam.substring(8)) <= Integer.parseInt("31")))
        lSalida = false;

      if (lSalida){
        fechaIn.set(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
        //System.out.println("la fecha in: " + this.laFecha);
        dFecha = new java.sql.Date(fechaIn.getTime().getTime());
        dFecha = java.sql.Date.valueOf(laFechaParam);
        dFechaActual = new java.sql.Date (fechaActual.getTime().getTime());
      }
    } catch (Exception e){
        lSalida= false;
      }

    if (lSalida){
      if (hastahoy)
        if (dFecha.after(dFechaActual))
          lSalida = false;
    }
    return lSalida;
  }


  public long convierte() {
    return convierte(this.laFecha,true);
  }

  public long convierte(String fechaParam) {
    return convierte(fechaParam,true);
  }

  public long convierte(String fechaParam,boolean hastahoy) {

    Calendar fechaIn = Calendar.getInstance();
    java.sql.Date dFecha=null;
    long ndate = 0;
    boolean lSalida = true;
    lSalida = valida(fechaParam,hastahoy);
    if (lSalida){
      try{
        StringTokenizer st=new StringTokenizer(fechaParam,"-");
        fechaIn.set(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
        dFecha = new java.sql.Date(fechaIn.getTime().getTime());
        ndate = dFecha.getTime();
      } catch (Exception e){
          e.printStackTrace();
        }
    }
    return ndate;
  }

  public static String fechaSistemaXml() {
    Calendar fechaIn = Calendar.getInstance();
    return fechaIn.get(Calendar.YEAR) + "-" + rellenarCeros(""+(fechaIn.get(Calendar.MONTH) + 1),2) + "-" + rellenarCeros(""+fechaIn.get(Calendar.DAY_OF_MONTH),2) + "-" + rellenarCeros(""+fechaIn.get(Calendar.HOUR_OF_DAY),2) + "-" + rellenarCeros(""+fechaIn.get(Calendar.MINUTE),2) + "-" + rellenarCeros(""+fechaIn.get(Calendar.SECOND),2);
  }

  public static String rellenarCeros(String numero,int numCeros) {
      String result=numero;
      for (int i=0;i<(numCeros-numero.length());i++)
        result = "0" + result;
      return result;
  }

  public static boolean fechaOperacionValida(String fechaOp) {
// formato yyyy-mm-dd-hh-mm-ss
    boolean valido=true;
    java.sql.Date dFecha=null;
    java.sql.Date dFechaActual=null;

    Calendar fechaIn = Calendar.getInstance();
    Calendar fechaActual = Calendar.getInstance();
    try {
      StringTokenizer st = new StringTokenizer(fechaOp,"-");
      // Mira q la longitud y nº de "-" sea el correcto
      if (fechaOp.length() != 19 || st.countTokens() != 6 )
        valido = false;

      if (valido){
        String valorStr="";
        int cont=0;
        while (valido && st.hasMoreTokens()) {
          try {
            valorStr=st.nextToken();
            if (cont==0) {
              if (valorStr.length()!=4)
                valido=false;
            } else{
              if (valorStr.length()!=2)
                valido=false;
            }
            Integer.parseInt(st.nextToken());
          }catch (Exception e) {
            valido=false;
          }
          cont++;
        }
      }
    } catch (Exception e){
        valido = false;
        System.out.print("Exception");
        //e.printStackTrace();
      }
    return valido;
  }

  public static long conviertefechaOperacion(String fechaOp) {
    // Pasa a long para poner después Timestamp, se supone buena la fecha
    long lfechaOp = 0;
    java.sql.Date dFecha=null;
    Calendar fechaIn = Calendar.getInstance();
    try {
      StringTokenizer st = new StringTokenizer(fechaOp,"-");
      int valores[]={0,0,0,0,0,0};
      int n=0;
      while (st.hasMoreTokens()) {
         valores[n]= new Integer(st.nextToken()).intValue();
         n++;
      }
      fechaIn.set(valores[0],valores[1]-1,valores[2],valores[3],valores[4],valores[5]);
      dFecha = new java.sql.Date(fechaIn.getTime().getTime());
      lfechaOp = dFecha.getTime();

//System.out.println(lfechaOp);

    } catch (Exception e){
        // valido = false;
        lfechaOp = 0;
        System.out.print("Exception");
        //e.printStackTrace();
      }

    return lfechaOp;
  }

}


