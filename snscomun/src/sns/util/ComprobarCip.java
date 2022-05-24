package sns.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComprobarCip {

  //Path donde se pondrán los ficheros de salida
  private String pathSalida="";

  public ComprobarCip(String path) {
    this.pathSalida=path;
  }

  private Connection getConexion() throws SQLException {

    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver ());
    return DriverManager.getConnection ("jdbc:oracle:thin:@" + sns.config.Constantes.IPSERVIDOR + ":" + sns.config.Constantes.PUERTO + ":" + sns.config.Constantes.SID,sns.config.Constantes.USUARIO,sns.config.Constantes.PASSWORD);
  }

  public String comprobarDigito(String sCip){

    int n = 0;
    int nTotal = 0;
    String sNumCip="";
    int dControl=0;
    boolean caracErroneos=false;

    try {
      //Comprueba que los 4 primeros caracteres sean letras de la A a la Z
      for (int i = 0; i <= 3; i++) {
        if (! (sCip.charAt(i) >= 'A' && sCip.charAt(i) <= 'Z' &&
               sCip.charAt(i) != 'Ñ')) {
          caracErroneos = true;
        }
      }
      if (!caracErroneos) {
        // Pasar letras a números
        int letra1 = 0;
        letra1 = 1070 + (30 * (Character.getNumericValue(sCip.charAt(0)) - 9));
        letra1 += (Character.getNumericValue(sCip.charAt(1)) - 9);
        letra1 = letra1 - 1000;
        int letra2 = 0;
        letra2 += 1070 + (30 * (Character.getNumericValue(sCip.charAt(2)) - 9));
        letra2 += (Character.getNumericValue(sCip.charAt(3)) - 9);
        letra2 = letra2 - 1000;

        sNumCip = String.valueOf(letra1) + String.valueOf(letra2) +
            sCip.substring(4);

        for (int x = 0; x <= 16; x++) {
          if (x % 2 == 0) {
            n = (Integer.parseInt(sNumCip.substring(x, x + 1))) * 2;
          }
          else {
            n = (Integer.parseInt(sNumCip.substring(x, x + 1)));
          } // resto ver posición
          nTotal += n;
        } // for de la cadena

        int nlong = String.valueOf(nTotal).length();
        dControl = 10 -
            (Integer.parseInt(String.valueOf(nTotal).substring(nlong - 1)));
        String sControl = String.valueOf(dControl);

        if (sControl.length() == 2)
          return sControl.substring(1, 2);
        else
          return sControl;
      }
      else { //Devuelve la cadena -1 si hay caracteres erróneos en el cip
        return "-1";
      }
    }catch (Exception e) {
      return "-1";
    }

  }//Fin de comprobarDigito

  public void tramitar() throws IOException, SQLException {

    PrintWriter cipsErroneos=null;
    Connection conexion=null;
    Statement st=null;
    ResultSet rs=null;

    String query="select rc.*,ca.COD_CA_ISO,ca.DESC_COMUNIDAD ";
    query += "from relacion_cip rc, usuarios u,ca_prestacion_servicio cp,comunidades_autonomas ca";
    query += " where rc.ACTUAL=1 and rc.COD_USUARIO_SNS=u.COD_USUARIO_SNS and u.COD_ESTADO=0 and";
    query += " cp.COD_PRESTACION_SERVICIO=u.cod_prestacion_servicio and ca.COD_COMUNIDAD=cp.COD_COMUNIDAD";

    String cip="";
    String digitoControl="";
    String cipSinDigito="";
    String nuevoDigito="";

    try {
      conexion = getConexion();
      st = conexion.createStatement();
      rs = st.executeQuery(query);
      cipsErroneos=new PrintWriter(new FileWriter(this.pathSalida + "/CipsErroneosConComunidad.txt" ));
      long contador=0;

      while (rs.next()){

        contador++;
        try{
          cip = rs.getString("CIP");
          if (cip.length() == 16) { //Longitud del cip correcta
            digitoControl = cip.substring(cip.length() - 1);
            cipSinDigito = cip.substring(0, cip.length() - 1);
            nuevoDigito = comprobarDigito(cipSinDigito);
            if ( (!nuevoDigito.equals(digitoControl)) || nuevoDigito.equals("-1")) {
              //Si los dígitos no son iguales el cip es incorrecto
              //Si es un -1 es que tiene caracteres raros
              cipsErroneos.println(cip + "|" + Misc.nz(rs.getString("FECHA")) + "|" + rs.getString("COD_USUARIO_SNS") + "|" +
                                   rs.getString("EAP") + "|" +
                                   rs.getString("ACTUAL")+ "|" + rs.getString("COD_CA_ISO")+ "|" + rs.getString("DESC_COMUNIDAD"));
            }
          }
          else { //La longitud del cip de la BBDD es distinta de 16, así que es incorrecta
            cipsErroneos.println(cip + "|" + Misc.nz(rs.getString("FECHA")) + "|" + rs.getString("COD_USUARIO_SNS") + "|" +
                                 rs.getString("EAP") + "|" +
                                 rs.getString("ACTUAL")+ "|" + rs.getString("COD_CA_ISO")+ "|" + rs.getString("DESC_COMUNIDAD"));
          }
        }catch (Exception e){
          cipsErroneos.println(cip + "|" + Misc.nz(rs.getString("FECHA")) + "|" + rs.getString("COD_USUARIO_SNS") + "|" +
                                 rs.getString("EAP") + "|" +
                                 rs.getString("ACTUAL")+ "|" + rs.getString("COD_CA_ISO")+ "|" + rs.getString("DESC_COMUNIDAD"));
          e.printStackTrace();
        }
        if ((contador % 5000)==0) {
          System.out.println(new java.util.Date()+"Número de registros leídos: "+contador);
        }
      }
    }
    catch (SQLException ex) {
      System.out.println("Se ha producido un error al conectar con la BBDD[getConexion()]");
      ex.printStackTrace();
      System.exit( -1);
    }catch (Exception ex) {
        System.out.println("Se ha producido un error");
        ex.printStackTrace();
        System.exit(-1);
    }finally{
      if (cipsErroneos!=null){
        cipsErroneos.flush();
        cipsErroneos.close();
      }
      if (st!=null)
        st.close();
      if (conexion!=null)
        conexion.close();
    }

  }//Fin de tramitar

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Uso: java sns.ComprobarCip pathFicheroSalida");
      System.exit(-1);
    }
    ComprobarCip comprobarCip=new ComprobarCip(args[0]);
  //SRDZ73086791101-0
  //L-R´55012550401-6
//    comprobarCip.comprobarMiCip("SRDZ730867911010");
//    comprobarCip.comprobarMiCip("P?FR600951910010");

    try {
      comprobarCip.tramitar();
    }
    catch (IOException ex) {
      System.out.println("Se ha producido un error al tratar los ficheros");
      ex.printStackTrace();
      System.exit(-1);
    }
    catch (SQLException ex) {
      System.out.println("Se ha producido un error al conectar con la BBDD");
      ex.printStackTrace();
      System.exit(-1);
    }

  }//Fin del main

  public void comprobarMiCip(String cip) {
    String digitoControl = cip.substring(cip.length() - 1);
    String cipSinDigito = cip.substring(0, cip.length() - 1);
    String nuevoDigito = comprobarDigito(cipSinDigito);
    System.out.println("nuevoDigito: " + nuevoDigito + " - anterior: " + digitoControl);
  }
}//Fin de la Clase
