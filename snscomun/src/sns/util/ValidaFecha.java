package sns.util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class ValidaFecha {

  public ValidaFecha(){
  }

  public static void main(String[] args) {
     ValidaFecha fecha = new ValidaFecha();
     System.out.println(fecha.valida(args[0]));
  }

  public boolean valida(String laFecha) {

    Calendar fechaActual = Calendar.getInstance();
    Calendar fechaIn = Calendar.getInstance();
    DateFormat fecha = DateFormat.getDateInstance();
    java.sql.Date dFecha=null;
    java.sql.Date dFechaActual=null;
    boolean lSalida = true;

    try{
      StringTokenizer st=new StringTokenizer(laFecha,"-");
      if (laFecha.length() != 10)
        lSalida = false;
      if (lSalida){
        fechaIn.set(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
        //System.out.println("la fecha in: " + this.laFecha);
        dFecha = new java.sql.Date(fechaIn.getTime().getTime());
        //System.out.println("la fecha out: " + dFecha);

        dFechaActual = new java.sql.Date (fechaActual.getTime().getTime());
      }
    } catch (Exception e){
        lSalida= false;
      }

    if (lSalida){
      if (dFecha.after(dFechaActual))
        lSalida = false;
    }
    return lSalida;
  }
}


