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

public class GenerarEstadisticasNuevas {
  public static void main(String[] args) throws Exception {
    GenerarEstadisticasNuevas objGenerarEstadisticasNuevas = new GenerarEstadisticasNuevas();

    try {
      objGenerarEstadisticasNuevas.obtenerDatos("01-10-2009", "31-10-2009");

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }


  private void obtenerDatos(String initialDate, String finalDate)throws Exception{

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
                        strQuery.append(" select count(1),r.cod_tipo_operacion,a.cod_agente,a.agente,'' MOTIVO_BAJA,'entrada' TIPO ");
                        strQuery.append(" from snsalud.registro_operaciones r, snsalud.agentes a ");
                        strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
                        strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
                        strQuery.append(" and r.COD_TIPO_OPERACION in ('A003','A006','A007','A008') ");
                        strQuery.append(" and exists (select 'x' ");
                        strQuery.append(" from snsalud.registro_operaciones r2 ");
                        strQuery.append(" where r2.COD_OPERACION_MAESTRA=r.COD_OPERACION ");
                        strQuery.append(" and r2.COD_OPERACION!=r.COD_OPERACION ");
                        strQuery.append(" and r2.COD_TIPO_OPERACION in ('N005','N009')) ");
                        strQuery.append(" and a.cod_agente=r.cod_agente_origen ");
                        strQuery.append(" group by r.cod_tipo_operacion,a.cod_agente,a.agente ");
                        strQuery.append(" union all ");
                        strQuery.append(" select count(1), r.cod_tipo_operacion,a.cod_agente, a.agente, CAST(TRIM(SUBSTR (r.mensaje_xml, dbms_lob.instr(r.mensaje_xml,'<motivo_baja>') + 13, ((dbms_lob.instr(r.mensaje_xml,'</motivo_baja>')) - (dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13)))) AS VARCHAR2(30)) MOTIVO_BAJA, 'entrada' TIPO ");
                        strQuery.append(" from snsalud.registro_operaciones r, snsalud.agentes a ");
                        strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
                        strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
                        strQuery.append(" and r.COD_TIPO_OPERACION in ('A002') ");
                        strQuery.append(" and exists ( ");
                        strQuery.append(" select 'x' ");
                        strQuery.append(" from snsalud.registro_operaciones r2 ");
                        strQuery.append(" where r2.COD_OPERACION_MAESTRA=r.COD_OPERACION ");
                        strQuery.append(" and r2.COD_OPERACION!=r.COD_OPERACION ");
                        strQuery.append(" and r2.COD_TIPO_OPERACION in ('N005','N009')) ");
                        strQuery.append(" and a.cod_agente=r.cod_agente_origen ");
                        strQuery.append(" group by r.cod_tipo_operacion,a.agente,a.cod_agente,a.agente, CAST(TRIM(SUBSTR (r.mensaje_xml, dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13, ((dbms_lob.instr(r.mensaje_xml,'</motivo_baja>')) - (dbms_lob.instr(r.mensaje_xml,'<motivo_baja>')+13)))) AS VARCHAR2(30)) ");
                        strQuery.append(" union all ");
                        strQuery.append(" select count(1),r.cod_tipo_operacion,a.cod_agente,a.agente,'' MOTIVO_BAJA,'salida' TIPO ");
                        strQuery.append(" from snsalud.registro_operaciones r, snsalud.agentes a ");
                        strQuery.append(" where r.FECHA_OPERACION between to_date(?,'dd-mm-yyyy') ");
                        strQuery.append(" and to_date(?,'dd-mm-yyyy')+1 ");
                        strQuery.append(" and r.COD_TIPO_OPERACION in ('A001','A005','N001','N002','N004','N005','N008','N009','N010') ");
                        strQuery.append(" and a.cod_agente=r.cod_agente_DESTINO ");
                        strQuery.append(" group by r.cod_tipo_operacion,a.cod_agente,a.agente ");
                        strQuery.append(" order by 3,6,2 ");
                        //
                        HashMap map = new HashMap();
                        map.put("1", initialDate);
                        map.put("2", finalDate);
                        map.put("3", initialDate);
                        map.put("4", finalDate);
                        map.put("5", initialDate);
                        map.put("6", finalDate);
                        //
                        logger.debug("@@## sentencia:"+strQuery);
                        logger.debug("@@## hParametros:"+map);
                        //
                        HashMap resultadoBd = accesoBd.consultaRaw(strQuery.toString(), map);
                        rs = (ResultSet) resultadoBd.get("rs");
                        ps = (PreparedStatement) resultadoBd.get("ps");
                        //
                        //Se procede a escribir la informacion.
                        guardaInfo(rs);
                        //
                        logger.debug("@@## FIN");

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



        public void guardaInfo (ResultSet rs)throws Exception {

                Logger logger = new Logger("snsadmin.nuevasEstadisticas.GenerarEstadisticasNuevas.guardaInfo");

                try{
                        logger.debug("@@## INICIO");
                        HashMap hDatos = new HashMap();
                        //int [ ] a = null;
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

                        while (rs.next()) {
                          //ANDALUCIA
                          if(rs.getString("COD_AGENTE").equals("2")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=a[4];
                              a[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=a[3];
                              a[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=a[2];
                              a[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=a[1];
                              a[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //CANARIAS
                          if(rs.getString("COD_AGENTE").equals("3")){
                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=b[4];
                              b[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=b[3];
                              b[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=b[2];
                              b[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=b[1];
                              b[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //GALICIA
                          if(rs.getString("COD_AGENTE").equals("5")){
                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=c[4];
                              c[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=c[3];
                              c[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=c[2];
                              c[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=c[1];
                              c[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }


                          //NAVARRA
                          if(rs.getString("COD_AGENTE").equals("6")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=d[4];
                              d[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=d[3];
                              d[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=d[2];
                              d[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=d[1];
                              d[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //VALENCIA
                          if(rs.getString("COD_AGENTE").equals("8")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=e[4];
                              e[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=e[3];
                              e[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=e[2];
                              e[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=e[1];
                              e[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //ARAGON
                          if(rs.getString("COD_AGENTE").equals("13")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=f[4];
                              f[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=f[3];
                              f[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=f[2];
                              f[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=f[1];
                              f[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //ASTURIAS
                          if(rs.getString("COD_AGENTE").equals("14")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=g[4];
                              g[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=g[3];
                              g[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=g[2];
                              g[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=g[1];
                              g[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //BALEARES
                          if(rs.getString("COD_AGENTE").equals("15")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=h[4];
                              h[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=h[3];
                              h[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=h[2];
                              h[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=h[1];
                              h[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //CANTABRIA
                          if(rs.getString("COD_AGENTE").equals("16")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=i[4];
                              i[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=i[3];
                              i[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=i[2];
                              i[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=i[1];
                              i[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //CASTILLA-LA MANCHA
                          if(rs.getString("COD_AGENTE").equals("17")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=j[4];
                              j[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=j[3];
                              j[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=j[2];
                              j[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=j[1];
                              j[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //CASTILLA Y LEON
                          if(rs.getString("COD_AGENTE").equals("18")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=k[4];
                              k[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=k[3];
                              k[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=k[2];
                              k[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=k[1];
                              k[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //INGESA
                          if(rs.getString("COD_AGENTE").equals("19")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=l[4];
                              l[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=l[3];
                              l[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=l[2];
                              l[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=l[1];
                              l[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //EXTREMADURA
                          if(rs.getString("COD_AGENTE").equals("20")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=m[4];
                              m[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=m[3];
                              m[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=m[2];
                              m[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=m[1];
                              m[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //RIOJA
                          if(rs.getString("COD_AGENTE").equals("21")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=n[4];
                              n[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=n[3];
                              n[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=n[2];
                              n[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=n[1];
                              n[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //MURCIA
                          if(rs.getString("COD_AGENTE").equals("22")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=o[4];
                              o[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=o[3];
                              o[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=o[2];
                              o[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=o[1];
                              o[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                          }

                          //MADRID
                          if(rs.getString("COD_AGENTE").equals("23")){

                            if(rs.getString("COD_TIPO_OPERACION").equals("A002")){
                              aux=p[4];
                              p[4]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A003")){
                              aux=p[3];
                              p[3]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A007")){
                              aux=p[2];
                              p[2]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
                            if(rs.getString("COD_TIPO_OPERACION").equals("A006")){
                              aux=p[1];
                              p[1]=aux + Integer.parseInt(rs.getString("COUNT(1)"));
                            }
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



                       escribirArchivoEstadisticas(hDatos);
                       logger.debug("@@## FIN");

                }catch (Exception e){
                        logger.debug("SE HA PRODUCIDO UN ERROR GUARDAR LA INFORMACION");
                        logger.debug("Exception e:"+e);
                        e.printStackTrace();
                        throw e;
                }
        }


        private HashMap getEstructuraDatos (){
          HashMap hDatos = new HashMap();
          int [ ] a = { 0 , 0 , 0, 0, 0 };
          hDatos.put("ANDALUCIA",a);
          int [ ] b = { 0 , 0 , 0, 0, 0 };
          hDatos.put("CANARIAS",b);
          int [ ] c = { 0 , 0 , 0, 0, 0 };
          hDatos.put("GALICIA",c);
          int [ ] d = { 0 , 0 , 0, 0, 0 };
          hDatos.put("NAVARRA",d);
          int [ ] e = { 0 , 0 , 0, 0, 0 };
          hDatos.put("VALENCIA",e);
          int [ ] f = { 0 , 0 , 0, 0, 0 };
          hDatos.put("ARAGON",f);
          int [ ] g = { 0 , 0 , 0, 0, 0 };
          hDatos.put("ASTURIAS",g);
          int [ ] h = { 0 , 0 , 0, 0, 0 };
          hDatos.put("BALEARES",h);
          int [ ] i = { 0 , 0 , 0, 0, 0 };
          hDatos.put("CANTABRIA",i);
          int [ ] j = { 0 , 0 , 0, 0, 0 };
          hDatos.put("CASTILLA-LA MANCHA",j);
          int [ ] k = { 0 , 0 , 0, 0, 0 };
          hDatos.put("CASTILLA Y LEON",k);
          int [ ] l = { 0 , 0 , 0, 0, 0 };
          hDatos.put("INGESA",l);
          int [ ] m = { 0 , 0 , 0, 0, 0 };
          hDatos.put("EXTREMADURA",m);
          int [ ] n = { 0 , 0 , 0, 0, 0 };
          hDatos.put("RIOJA",n);
          int [ ] o = { 0 , 0 , 0, 0, 0 };
          hDatos.put("MURCIA",o);
          int [ ] p = { 0 , 0 , 0, 0, 0 };
          hDatos.put("MADRID",p);
          return hDatos;
        }


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
            linea = new String("ANDALUCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CANARIAS");
            linea = new String("CANARIAS"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("GALICIA");
            linea = new String("GALICIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("NAVARRA");
            linea = new String("NAVARRA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("VALENCIA");
            linea = new String("VALENCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("ARAGON");
            linea = new String("ARAGON"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("ASTURIAS");
            linea = new String("ASTURIAS"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("BALEARES");
            linea = new String("BALEARES"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CANTABRIA");
            linea = new String("CANTABRIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CASTILLA-LA MANCHA");
            linea = new String("CASTILLA-LA MANCHA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("CASTILLA Y LEON");
            linea = new String("CASTILLA Y LEON"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("INGESA");
            linea = new String("INGESA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("EXTREMADURA");
            linea = new String("EXTREMADURA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("RIOJA");
            linea = new String("RIOJA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("MURCIA");
            linea = new String("MURCIA"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
            printWriter.print(linea+Constantes.SALTO_LINEA);
            printWriter.flush();
            a=(int [ ])datos.get("MADRID");
            linea = new String("MADRID"+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[1]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[2]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[3]+Constantes.ADMIN_SEPARATOR_HEAD_REGISTER+a[4]);
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













}
