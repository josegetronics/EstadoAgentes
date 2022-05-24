package sns.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class ComunUtilidades {

  public ComunUtilidades() {
  }


  public static boolean fechaOk(String fechaIn, String mascara, String separador) throws Exception {
    try {
      Calendar c = Calendar.getInstance();
      String[] fecha = new String[3];

      StringTokenizer fechaC = new StringTokenizer(fechaIn, separador);
      if (fechaC.countTokens() != 3) {
        return false;
      }
      int posicionF = 0;
      String trozo = "";
      while (fechaC.hasMoreTokens()) {
        trozo = fechaC.nextToken();
        fecha[posicionF] = trozo;
        posicionF++;
      }

      StringTokenizer cadena = new StringTokenizer(mascara, separador);
      int anio = 0;
      int dia = 0;
      int mes = 0;

      if (cadena.countTokens() != 3) {
        return false;
      }
      int posicion = 0;
      while (cadena.hasMoreTokens()) {
        trozo = cadena.nextToken();
        if (trozo.toLowerCase().startsWith("yy")) {
          c.set(Calendar.YEAR, Integer.parseInt(fecha[posicion]));
          anio = Integer.parseInt(fecha[posicion]);
        }
        if (trozo.toLowerCase().startsWith("mm")) {
          c.set(Calendar.MONTH, Integer.parseInt(fecha[posicion]) - 1);
          mes = Integer.parseInt(fecha[posicion]);
        }
        if (trozo.toLowerCase().startsWith("dd")) {
          c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha[posicion]));
          dia = Integer.parseInt(fecha[posicion]);
        }
        posicion++;
      }
      if (anio == c.get(Calendar.YEAR) && mes == (c.get(Calendar.MONTH) + 1) && dia == c.get(Calendar.DAY_OF_MONTH)) {
        return true;
      }
      else {
        return false;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }


  public boolean fechaFinOk(String diaInicio, String mesInicio, String anyoInicio, String horaInicio, String minutoInicio, String diaFin, String mesFin, String anyoFin, String horaFin, String minutoFin) throws Exception {
    try {
      Date fechaInicio = new Date(Integer.parseInt(anyoInicio), Integer.parseInt(mesInicio), Integer.parseInt(diaInicio), Integer.parseInt(horaInicio), Integer.parseInt(minutoInicio));
      Date fechaFin = new Date(Integer.parseInt(anyoFin), Integer.parseInt(mesFin), Integer.parseInt(diaFin), Integer.parseInt(horaFin), Integer.parseInt(minutoFin));
      if (fechaFin.before(fechaInicio)) {
        return false;
      }
      else {
        return true;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }


  public boolean fechaFinOkSinHora(String diaInicio, String mesInicio, String anyoInicio, String diaFin, String mesFin, String anyoFin) throws Exception {
    try {
      Date fechaInicio = new Date(Integer.parseInt(anyoInicio), Integer.parseInt(mesInicio), Integer.parseInt(diaInicio));
      Date fechaFin = new Date(Integer.parseInt(anyoFin), Integer.parseInt(mesFin), Integer.parseInt(diaFin));
      if (fechaFin.before(fechaInicio)) {
        return false;
      }
      else {
        return true;
      }
    }
    catch (Exception e) {
        e.printStackTrace();
      return false;
    }
  }


  public static String getFechaMilisegundos() throws Exception {
    String fecha = new String();
    java.util.Calendar c = java.util.Calendar.getInstance();
    String sMon = "";
    String sDay = "";
    String sHora = "";
    String sMin = "";
    String sSec = "";
    String sMilSec = "";
    //
    int y = c.get(java.util.Calendar.YEAR);
    String sYear = String.valueOf(y);
    sYear = sYear.substring(2, 4);
    //
    int m = c.get(java.util.Calendar.MONTH);
    m++;
    if (m < 10) sMon = "0" + String.valueOf(m);
    else sMon = String.valueOf(m);
    //
    int d = c.get(java.util.Calendar.DATE);
    if (d < 10) sDay = "0" + String.valueOf(d);
    else sDay = String.valueOf(d);
    //
    int h = c.get(java.util.Calendar.HOUR_OF_DAY);
    if (h < 10) sHora = "0" + String.valueOf(h);
    else sHora = String.valueOf(h);
    //
    int min = c.get(java.util.Calendar.MINUTE);
    if (min < 10) sMin = "0" + String.valueOf(min);
    else sMin = String.valueOf(min);
    //
    int s = c.get(java.util.Calendar.SECOND);
    if (s < 10) sSec = "0" + String.valueOf(s);
    else sSec = String.valueOf(s);
    //
    int ms = c.get(java.util.Calendar.MILLISECOND);
    if (ms < 10) sMilSec = "00" + String.valueOf(ms);
    else if (ms < 100) sMilSec = "0" + String.valueOf(ms);
    else sMilSec = String.valueOf(ms);
    //
    fecha = sYear + sMon + sDay + sHora + sMin + sSec + sMilSec;
    return fecha;
  }


  /**
    * Devuelve un arrayList con los elementos indicados en 'sCadena'
    * que se encontraban separados por 'sToken'
    *
    * @param sCadena String
    * @param sToken String
    * @return ArrayList
    */
   public ArrayList getTokens(String sCadena, String sToken) throws Exception {
      ArrayList alTokens = new ArrayList();
      if (sCadena != null && sToken != null) {
         int posToken;
         while(!sCadena.equals("")){
            posToken = sCadena.indexOf(sToken);
            if(posToken != -1){
               alTokens.add(sCadena.substring(0,posToken));
               sCadena = sCadena.substring(posToken+sToken.length(),sCadena.length());
            }
            else{
               alTokens.add(sCadena);
               sCadena = "";
            }
         }
      }
      return alTokens;
   }



}
