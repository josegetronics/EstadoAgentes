package sns.util;

import java.util.Calendar;
import gasai.util.Misc;
import sns.logging.Logger;


public class UtilReport {


  public UtilReport() {
  }


  public static String getDateName(String date) throws Exception {

    String dateResult = "";
    Logger logger = new Logger("sns.util.UtilReport.getDateName");

    try {
      logger.debug("INICIO");

      if(date.length() >= 10){
        String day   = Misc.nz(date.substring(0, 2));
        String month = Misc.nz(date.substring(3, 5));
        String year  = Misc.nz(date.substring(6, 10));

        if(Misc.esDigito(day) && Misc.esDigito(month) && Misc.esDigito(year)) {
          System.out.println("day: " + day + ", month: " + month + ", year: " + year);
          StringBuffer stringBuffer = new StringBuffer ();
          stringBuffer.append(year);
          stringBuffer.append(month);
          stringBuffer.append(day);
          dateResult = stringBuffer.toString();
        }
      }
      logger.debug("dateResult: " + dateResult);

      logger.debug("FIN");
    }
    catch (Exception e) {
      logger.debug("Error: " + e.getMessage());
      throw e;
    }
    return dateResult;
  }



  /**
   * Devuelve la fecha con formato AAAAMMDD
   *
   * @return la fecha con formato AAAAMMDD
   */
  public static String getFecha() {
    // componemos la fecha con formato AAAAMMDDHHMMSS
    StringBuffer fecha = new StringBuffer();
    Calendar cal = Calendar.getInstance();
    String a = Integer.toString(cal.get(Calendar.YEAR));
    String m = Integer.toString(cal.get(Calendar.MONTH) + 1);
    m = (m.length() == 1) ? "0" + m : m;
    String d = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
    d = (d.length() == 1) ? "0" + d : d;
    //
    fecha.append(a);
    fecha.append(m);
    //fecha.append(d);
    //
    return fecha.toString();
  }



  public static String getFechaMilisegundos() {
    String fecha = new String();
    java.util.Calendar c = java.util.Calendar.getInstance();
    String sMon = "";
    String sDay = "";
    String sHora = "";
    String sMin = "";
    String sSec = "";
    String sMilSec = "";

    int y = c.get(java.util.Calendar.YEAR);
    String sYear = String.valueOf(y);
    sYear = sYear.substring(2, 4);

    int m = c.get(java.util.Calendar.MONTH);
    m++;
    if (m < 10) sMon = "0" + String.valueOf(m);
    else sMon = String.valueOf(m);

    int d = c.get(java.util.Calendar.DATE);
    if (d < 10) sDay = "0" + String.valueOf(d);
    else sDay = String.valueOf(d);

    int h = c.get(java.util.Calendar.HOUR_OF_DAY);
    if (h < 10) sHora = "0" + String.valueOf(h);
    else sHora = String.valueOf(h);

    int min = c.get(java.util.Calendar.MINUTE);
    if (min < 10) sMin = "0" + String.valueOf(min);
    else sMin = String.valueOf(min);

    int s = c.get(java.util.Calendar.SECOND);
    if (s < 10) sSec = "0" + String.valueOf(s);
    else sSec = String.valueOf(s);

    int ms = c.get(java.util.Calendar.MILLISECOND);
    if (ms < 10) sMilSec = "00" + String.valueOf(ms);
    else if (ms < 100) sMilSec = "0" + String.valueOf(ms);
    else sMilSec = String.valueOf(ms);

    fecha = sYear + sMon + sDay + sHora + sMin + sSec + sMilSec;

    return fecha;
  }


}
