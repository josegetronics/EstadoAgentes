package snsadmin.nuevasEstadisticas;


import java.io.PrintWriter;
import sns.logging.Logger;
import sns.bd.AccesoBd;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashMap;
import sns.config.Constantes;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.sql.*;
import gasai.util.Misc;


public class GenerarEstadisticasSituacionTitulo {

  public static void main(String[] args) throws Exception {
    GenerarEstadisticasSituacionTitulo objGenerarEstadisticasSituacionTitulo = new GenerarEstadisticasSituacionTitulo();

    try {

      objGenerarEstadisticasSituacionTitulo.obtenerDatos();

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }


  private void obtenerDatos()throws Exception{

            Logger logger = new Logger("snsadmin.nuevasEstadisticas.GenerarEstadisticasNuevas.obtenerDatos");
            AccesoBd accesoBd = null;
            ResultSet rs = null;
            PreparedStatement ps = null;

                try{
                        logger.debug("@@## INICIO");
                        //
                        accesoBd = new AccesoBd ();
                        //
                        StringBuffer strQuery = new StringBuffer();
                        strQuery.append(" select ca.COD_CA_ISO, dc.COD_TITULO, dc.COD_SITUACION, count(*) AS NUMERO ");
                        strQuery.append(" from   snsalud.comunidades_autonomas ca, snsalud.ca_prestacion_servicio cps, snsalud.usuarios u, snsalud.datos_cobertura dc ");
                        strQuery.append(" where    ca.COD_COMUNIDAD = cps.COD_COMUNIDAD ");
                        strQuery.append(" and    cps.COD_PRESTACION_SERVICIO = u.COD_PRESTACION_SERVICIO ");
                        strQuery.append(" and    u.COD_ESTADO = 0 ");
                        strQuery.append(" and    u.COD_USUARIO_SNS = dc.COD_USUARIO_SNS ");
                        strQuery.append(" group by ca.COD_CA_ISO, dc.COD_TITULO, dc.COD_SITUACION ");
                        strQuery.append(" order by ca.COD_CA_ISO, dc.COD_TITULO, dc.COD_SITUACION ");

                        /*
                        HashMap map = new HashMap();
                        map.put("1", initialDate);
                        map.put("2", finalDate);
                        map.put("3", initialDate);
                        map.put("4", finalDate);
                        map.put("5", initialDate);
                        map.put("6", finalDate);*/
                        //
                        logger.debug("@@## sentencia:"+strQuery);
                        //
                        HashMap resultadoBd = accesoBd.consultaRaw(strQuery.toString());
                        rs = (ResultSet) resultadoBd.get("rs");
                        ps = (PreparedStatement) resultadoBd.get("ps");
                        logger.debug("@@## FIN");
                        //Se procede a escribir la informacion.
                        escribirArchivoEstadisticas(rs);

                }catch (Exception e){
                        logger.error("@@## SE HA PRODUCIDO UN ERROR AL OBTENER LA INFORMACION");
                        logger.error("Exception e:"+e);
                        e.printStackTrace();
                        throw e;
                }finally{
                      //Se cierran los objetos del accedo a Base de datos.
                 if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                        if(accesoBd != null){
                                accesoBd.cerrar();
                        }
                }
        }


/*
        public void guardaInfo (ResultSet rs)throws Exception {
                Logger logger = new Logger("snsadmin.nuevasEstadisticas.GenerarEstadisticasNuevas.guardaInfo");
                try{
                        logger.debug("@@## INICIO");
                        HashMap hDatos = new HashMap();
                        int aux;
                        hDatos = getEstructuraDatos();
                        int [ ] a =(int [ ])hDatos.get("ANDALUCIA");
                        int [ ] b =(int [ ])hDatos.get("CANARIAS");
                        int [ ] c =(int [ ])hDatos.get("GALICIA");
                        int [ ] d =(int [ ])hDatos.get("NAVARRA");
                        int [ ] e =(int [ ])hDatos.get("VALENCIA");
                        int [ ] f =(int [ ])hDatos.get("ARAGON");
                        int [ ] g =(int [ ])hDatos.get("ASTURIAS");
                        int [ ] h =(int [ ])hDatos.get("BALEARES");
                        int [ ] i =(int [ ])hDatos.get("CANTABRIA");
                        int [ ] j =(int [ ])hDatos.get("CASTILLA-LA MANCHA");
                        int [ ] k =(int [ ])hDatos.get("CASTILLA Y LEON");
                        int [ ] l =(int [ ])hDatos.get("INGESA");
                        int [ ] m =(int [ ])hDatos.get("EXTREMADURA");
                        int [ ] n =(int [ ])hDatos.get("RIOJA");
                        int [ ] o =(int [ ])hDatos.get("MURCIA");
                        int [ ] p =(int [ ])hDatos.get("MADRID");
                        int [ ] q =(int [ ])hDatos.get("PAIS VASCO");
                        int [ ] r =(int [ ])hDatos.get("CATALUNYA");


                        while (rs.next()) {
                          //ANDALUCIA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 20){
                              a[2]=rs.getInt("NUMERO");
                              a[1]=rs.getInt("SITUACION");
                              a[0]=rs.getInt("TITULO");
                          }

                          //CANARIAS
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 4){
                              b[2]=rs.getInt("NUMERO");
                              b[1]=rs.getInt("SITUACION");
                              b[0]=rs.getInt("TITULO");
                          }

                          //GALICIA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 10){
                              c[2]=rs.getInt("NUMERO");
                              c[1]=rs.getInt("SITUACION");
                              c[0]=rs.getInt("TITULO");
                          }

                          //NAVARRA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 12){
                              d[2]=rs.getInt("NUMERO");
                              d[1]=rs.getInt("SITUACION");
                              d[0]=rs.getInt("TITULO");
                          }


                          //VALENCIA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 15){
                              e[2]=rs.getInt("NUMERO");
                              e[1]=rs.getInt("SITUACION");
                              e[0]=rs.getInt("TITULO");
                          }


                          //ARAGON
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 21){
                              f[2]=rs.getInt("NUMERO");
                              f[1]=rs.getInt("SITUACION");
                              f[0]=rs.getInt("TITULO");
                          }


                          //ASTURIAS
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 2){
                              g[2]=rs.getInt("NUMERO");
                              g[1]=rs.getInt("SITUACION");
                              g[0]=rs.getInt("TITULO");
                          }


                          //BALEARES
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 3){
                              h[2]=rs.getInt("NUMERO");
                              h[1]=rs.getInt("SITUACION");
                              h[0]=rs.getInt("TITULO");
                          }


                          //CANTABRIA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 5){
                              i[2]=rs.getInt("NUMERO");
                              i[1]=rs.getInt("SITUACION");
                              i[0]=rs.getInt("TITULO");
                          }


                          //CASTILLA-LA MANCHA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 6){
                              j[2]=rs.getInt("NUMERO");
                              j[1]=rs.getInt("SITUACION");
                              j[0]=rs.getInt("TITULO");
                          }


                          //CASTILLA Y LEON
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 7){
                              k[2]=rs.getInt("NUMERO");
                              k[1]=rs.getInt("SITUACION");
                              k[0]=rs.getInt("TITULO");
                          }


                          //INGESA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 16 || rs.getInt("COD_PRESTACION_SERVICIO") == 17){
                              aux=l[2];
                              l[2]=aux + rs.getInt("NUMERO");
                              aux=l[1];
                              l[1]=aux + rs.getInt("SITUACION");
                              aux=l[0];
                              l[0]=aux + rs.getInt("TITULO");
                          }

                          //EXTREMADURA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 9){
                              m[2]=rs.getInt("NUMERO");
                              m[1]=rs.getInt("SITUACION");
                              m[0]=rs.getInt("TITULO");
                          }


                          //RIOJA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 14){
                              n[2]=rs.getInt("NUMERO");
                              n[1]=rs.getInt("SITUACION");
                              n[0]=rs.getInt("TITULO");
                          }


                          //MURCIA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 11){
                              o[2]=rs.getInt("NUMERO");
                              o[1]=rs.getInt("SITUACION");
                              o[0]=rs.getInt("TITULO");
                          }


                          //MADRID
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 1){
                              p[2]=rs.getInt("NUMERO");
                              p[1]=rs.getInt("SITUACION");
                              p[0]=rs.getInt("TITULO");
                          }


                          //PAIS VASCO
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 13){
                              q[2]=rs.getInt("NUMERO");
                              q[1]=rs.getInt("SITUACION");
                              q[0]=rs.getInt("TITULO");
                          }


                          //CATALUNYA
                          if(rs.getInt("COD_PRESTACION_SERVICIO") == 8){
                              r[2]=rs.getInt("NUMERO");
                              r[1]=rs.getInt("SITUACION");
                              r[0]=rs.getInt("TITULO");
                          }


                       }
                       hDatos.put("ANDALUCIA",a);
                       hDatos.put("CANARIAS",b);
                       hDatos.put("GALICIA",c);
                       hDatos.put("NAVARRA",d);
                       hDatos.put("VALENCIA",e);
                       hDatos.put("ARAGON", f);
                       hDatos.put("ASTURIAS", g);
                       hDatos.put("BALEARES", h);
                       hDatos.put("CANTABRIA", i);
                       hDatos.put("CASTILLA-LA MANCHA", j);
                       hDatos.put("CASTILLA Y LEON", k);
                       hDatos.put("INGESA", l);
                       hDatos.put("EXTREMADURA", m);
                       hDatos.put("RIOJA", n);
                       hDatos.put("MURCIA", o);
                       hDatos.put("MADRID", p);
                       hDatos.put("PAIS VASCO", q);
                       hDatos.put("CATALUNYA", r);

                       escribirArchivoEstadisticas(hDatos);
                       logger.debug("@@## FIN");

                }catch (Exception e){
                        logger.debug("SE HA PRODUCIDO UN ERROR GUARDAR LA INFORMACION");
                        logger.debug("Exception e:"+e);
                        e.printStackTrace();
                        throw e;
                }
        }
*/

/*
        private HashMap getEstructuraDatos (){
          HashMap hDatos = new HashMap();
          int [ ] a = { 0 , 0 , 0};
          hDatos.put("ANDALUCIA",a);
          int [ ] b = { 0 , 0 , 0};
          hDatos.put("CANARIAS",b);
          int [ ] c = { 0 , 0 , 0};
          hDatos.put("GALICIA",c);
          int [ ] d = { 0 , 0 , 0};
          hDatos.put("NAVARRA",d);
          int [ ] e = { 0 , 0 , 0};
          hDatos.put("VALENCIA",e);
          int [ ] f = { 0 , 0 , 0};
          hDatos.put("ARAGON",f);
          int [ ] g = { 0 , 0 , 0};
          hDatos.put("ASTURIAS",g);
          int [ ] h = { 0 , 0 , 0};
          hDatos.put("BALEARES",h);
          int [ ] i = { 0 , 0 , 0};
          hDatos.put("CANTABRIA",i);
          int [ ] j = { 0 , 0 , 0};
          hDatos.put("CASTILLA-LA MANCHA",j);
          int [ ] k = { 0 , 0 , 0};
          hDatos.put("CASTILLA Y LEON",k);
          int [ ] l = { 0 , 0 , 0};
          hDatos.put("INGESA",l);
          int [ ] m = { 0 , 0 , 0};
          hDatos.put("EXTREMADURA",m);
          int [ ] n = { 0 , 0 , 0};
          hDatos.put("RIOJA",n);
          int [ ] o = { 0 , 0 , 0};
          hDatos.put("MURCIA",o);
          int [ ] p = { 0 , 0 , 0};
          hDatos.put("MADRID",p);
          int [ ] q = { 0 , 0 , 0};
          hDatos.put("PAIS VASCO",p);
          int [ ] r = { 0 , 0 , 0};
          hDatos.put("CATALUNYA",p);
          return hDatos;
        }
*/



private HashMap getTraduccion (){
  HashMap hTraduccion = new HashMap();
  hTraduccion.put("4","ANDALUCIA");
  hTraduccion.put("12","CANARIAS");
  hTraduccion.put("3","GALICIA");
  hTraduccion.put("15","NAVARRA");
  hTraduccion.put("9","VALENCIA");
  hTraduccion.put("10","ARAGON");
  hTraduccion.put("5","ASTURIAS");
  hTraduccion.put("14","BALEARES");
  hTraduccion.put("6","CANTABRIA");
  hTraduccion.put("11","CASTILLA-LA MANCHA");
  hTraduccion.put("17","CASTILLA Y LEON");
  hTraduccion.put("18","CEUTA");
  hTraduccion.put("19","MELILLA");
  hTraduccion.put("13","EXTREMADURA");
  hTraduccion.put("7","RIOJA");
  hTraduccion.put("8","MURCIA");
  hTraduccion.put("16","MADRID");
  hTraduccion.put("2","PAIS VASCO");
  hTraduccion.put("1","CATALUNYA");
  return hTraduccion;

}








/*
        public void escribirArchivoEstadisticas(HashMap datos) throws IOException{
          Logger logger = new Logger("snsadmin.nuevasEstadisticas.GenerarEstadisticasNuevas.escribirArchivoEstadisticas");
          FileWriter fileWriter = null;
          String linea="";
          int [ ] a = null;

          try {
            fileWriter = new FileWriter("d:\\Registros\\estadisticas_nuevas.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            String cabeceraCampos = new String("SERVICIO_SALUD"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"ALTAS_CON_CIP"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"ALTAS_SIN_CIP"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"MODIFICACIONES"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"BAJAS"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"TOTAL_TRANSACCIONES");
            printWriter.print(cabeceraCampos+Constantes.SALTO_LINEA);
            printWriter.flush();
            a =(int [ ])datos.get("ANDALUCIA");
            linea = new String("ANDALUCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CANARIAS");
            linea = new String("CANARIAS"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("GALICIA");
            linea = new String("GALICIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("NAVARRA");
            linea = new String("NAVARRA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("VALENCIA");
            linea = new String("VALENCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("ARAGON");
            linea = new String("ARAGON"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("ASTURIAS");
            linea = new String("ASTURIAS"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("BALEARES");
            linea = new String("BALEARES"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CANTABRIA");
            linea = new String("CANTABRIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CASTILLA-LA MANCHA");
            linea = new String("CASTILLA-LA MANCHA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CASTILLA Y LEON");
            linea = new String("CASTILLA Y LEON"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("INGESA");
            linea = new String("INGESA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("EXTREMADURA");
            linea = new String("EXTREMADURA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("RIOJA");
            linea = new String("RIOJA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("MURCIA");
            linea = new String("MURCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("MADRID");
            linea = new String("MADRID"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("PAIS VASCO");
            linea = new String("PAIS VASCO"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CATALUNYA");
            linea = new String("CATALUNYA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[0]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();



    }
    catch (IOException e) {
      System.out.println("No se pudo escribir el archivo");
      e.printStackTrace();
    }
    finally {
      if (fileWriter != null) {
        System.out.println("cierro");
        fileWriter.close();
      }
    }
  }

*/


public void escribirArchivoEstadisticas(ResultSet rs) throws IOException{
  Logger logger = new Logger("snsadmin.nuevasEstadisticas.GenerarEstadisticasNuevas.escribirArchivoEstadisticas");
  FileWriter fileWriter = null;
  String linea="";
  String titulo = "";
  String situacion = "";
  int [ ] a = null;
  HashMap hTraduccion = getTraduccion();

  try {
    fileWriter = new FileWriter("d:\\Registros\\estadisticas_nuevas.txt");
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter printWriter = new PrintWriter(bufferedWriter);
    String cabeceraCampos = new String("COD_CA_ISO"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"COD_TITULO"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"COD_SITUACION"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+"NUMERO");
    printWriter.print(cabeceraCampos+Constantes.SALTO_LINEA);
    printWriter.flush();
    try {
      while (rs.next()) {
        logger.debug("@@@@@@"+rs.getString("COD_TITULO"));
        if(!Misc.nz((rs.getString("COD_TITULO"))).equals("")){
          titulo = Misc.nz(new Integer(rs.getInt("COD_TITULO")).toString());
        }
        else{
          titulo = "";
        }
        if(!Misc.nz((rs.getString("COD_SITUACION"))).equals("")){
          situacion = Misc.nz(new Integer(rs.getInt("COD_SITUACION")).toString());
        }
        else{
          situacion = "";
        }

        titulo =
        linea = new String(hTraduccion.get(new Integer(rs.getInt("COD_CA_ISO")).toString()) + Constantes.ADMIN_SEPARATOR_HEAD_REGISTER + titulo + Constantes.ADMIN_SEPARATOR_HEAD_REGISTER + situacion + Constantes.ADMIN_SEPARATOR_HEAD_REGISTER + new Integer(rs.getInt("NUMERO")).toString());
        printWriter.print(linea + Constantes.SALTO_LINEA);
        printWriter.flush();
      }
    }
    catch (SQLException ex) {
    }




}
catch (IOException e) {
System.out.println("No se pudo escribir el archivo");
e.printStackTrace();
}
finally {
if (fileWriter != null) {
System.out.println("cierro");
fileWriter.close();
}
}
}




}
