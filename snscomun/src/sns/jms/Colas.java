package sns.jms;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class Colas extends Thread
{

  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(Colas.class);

  private String query="";
  public Hashtable hs=new Hashtable();
  private int longitud=0;

  public Colas(String query,int longitud,Hashtable hs)
  {
    this.query=query;
    this.longitud=longitud;
    this.hs=hs;
  }

  public static void main(String[] args) throws Exception
  {
    Hashtable hsMain=cargaAgentes();
    Hashtable hsMain2=(Hashtable)hsMain.clone();
    Colas colas = new Colas("select * from outjmsstore",79,hsMain);
    colas.start();
    Colas colas2 = new Colas("select * from errorjmsstore",88,hsMain2);
    colas2.start();
    boolean salir=false;
    while (colas.isAlive() || colas2.isAlive())
    {
      sleep(1000);
    }
    System.out.println("Cola de salida");
    System.out.println("----------------------------------------------");
    if (colas.hs.isEmpty()) {
      System.out.println("No hay mensajes");
    }else
    {
      Enumeration misKeys=colas.hs.keys();
      while (misKeys.hasMoreElements())
      {
        String key=(String)misKeys.nextElement();
        System.out.println(key + ": " + colas.hs.get(key));
      }
    }

    System.out.println("\n\n\nCola de salida con errores");
    System.out.println("----------------------------------------------");
    if (colas2.hs.isEmpty()) {
      System.out.println("No hay mensajes");
    }else
    {
      Enumeration misKeys=colas2.hs.keys();
      while (misKeys.hasMoreElements())
      {
        String key=(String)misKeys.nextElement();
        System.out.println(key + ": " + colas2.hs.get(key));
        hsMain.put(key,"" + (Long.parseLong((String)hsMain.get(key)) + Long.parseLong((String)colas2.hs.get(key))));
      }
    }
    if (!colas2.hs.isEmpty()) {
      System.out.println("\n\n\nCola de salida Total Mensajes");
      System.out.println("----------------------------------------------");
      Enumeration misKeys=hsMain.keys();
      while (misKeys.hasMoreElements())
      {
        String key=(String)misKeys.nextElement();
        System.out.println(key + ": " + hsMain.get(key));
      }
    }
  }

  public void run()
  {
    Connection conn=null;
    ResultSet rs= null;
    Statement stm=null;
    String mensaje="";
    sns.bd.AccesoBd bd=null;


    try
    {
      bd = new sns.bd.AccesoBd();
      conn = bd.abrirConexion();
//      conn=this.getConexion();
      stm=conn.createStatement();
      rs=stm.executeQuery(this.query);

      InputStream bf=null;
      String destino="";

      while (rs.next())
      {

        bf=rs.getBinaryStream("RECORD");

        int cont;
        byte[] buffer2=new byte[60];
        bf.skip(this.longitud);
        bf.read(buffer2);
        bf.close();
        mensaje=new String(buffer2);
        byte ant=-1;
        boolean salir=false;
        int indiceByte=0;
        for (int i=0;i<buffer2.length && !salir;i++) {
          if (ant==0 && buffer2[i]==102) {
            salir=true;
            indiceByte=i-1;
          }
          ant=buffer2[i];
        }
        destino=new String(buffer2,0,indiceByte);
/*
        System.out.print("#");
        System.out.print(destino);
        System.out.println("#");
*/
logger.debug("****Antes del synchronized");
logger.debug("****Destino: "+destino);
        synchronized (this.hs) {
          if (hs.containsKey(destino))
          {
            hs.put(destino,(Long.parseLong((String)hs.get(destino))+1) + "");
          }else
            hs.put(destino,"1");
        }
      }
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("#" + mensaje + "#");
    }finally{
      try
      {
        if (rs!=null)
          rs.close();
        if (stm!=null)
          stm.close();
        if (conn!=null)
          conn.close();
      }catch (Exception e) {}
      if (bd !=null){
        bd.cerrar();
      }

    }
  }

  private static Connection getConexion() throws Exception
  {
    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver ());
    return DriverManager.getConnection ("jdbc:oracle:thin:@" + sns.config.Constantes.IPSERVIDOR + ":" + sns.config.Constantes.PUERTO + ":" + sns.config.Constantes.SID,sns.config.Constantes.USUARIO,sns.config.Constantes.PASSWORD);
  }

  public static Hashtable cargaAgentes()
  {
    Hashtable hsA=new Hashtable();
    sns.bd.AccesoBd bd =null;

    try
    {
      bd = new sns.bd.AccesoBd();
      Vector resultado = bd.consulta("select CADENA_CONEXION from AGENTES where cod_agente<70 and cadena_conexion is not null order by cod_agente");
      for (int i=0;i<resultado.size();i++){
        HashMap h = new HashMap();
        h = (HashMap)resultado.elementAt(i);
        hsA.put(gasai.util.Misc.nz(h.get("CADENA_CONEXION")),"0");
      }

    }catch (Exception e){
      logger.debug("Excepción encargaAgentes");
    }finally{
      if (bd !=null){
        bd.cerrar();
      }
    }
//    logger.debug("******hsA: "+hsA);
//    logger.debug("Saliendo de cargaAgentes");
    return hsA;
  }

}
