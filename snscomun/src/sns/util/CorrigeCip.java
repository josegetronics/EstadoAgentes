package sns.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CorrigeCip
{
  public CorrigeCip()
  {
  }

  public static void main(String[] args)
  {
    CorrigeCip corrigeCip = new CorrigeCip();
    corrigeCip.corregir();
  }

  public void corregir() {
    Connection conn=null;
    Statement stm=null;
    Statement stm2=null;
    ResultSet rs=null;
    String query="";
    String raiz="";
    String ap1="";
    String ap2="";
    String codSexo="";
    String fechaNac="";
    String caNac="";
    String paisNac="";
    String codSns="";
    String queryUp="";

    try {
      DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver ());
      conn = DriverManager.getConnection ("jdbc:oracle:thin:@" + sns.config.Constantes.IPSERVIDOR + ":" + sns.config.Constantes.PUERTO + ":" + sns.config.Constantes.SID,sns.config.Constantes.USUARIO,sns.config.Constantes.PASSWORD);
      stm=conn.createStatement();
      stm2=conn.createStatement();
      System.out.println("tratando apellido1 " + new java.util.Date());
      query="SELECT COD_USUARIO_SNS,APELLIDO1,APELLIDO2,COD_SEXO,to_char(FECHA_NAC,'yyyy-mm-dd') FECHANAC,c.COD_CA_ISO,COD_PAIS FROM DATOS_PERSONALES d,comunidades_autonomas c WHERE d.APELLIDO1 LIKE 'Y %' AND d.RAIZ LIKE '%Y%' and d.cod_comunidad=c.COD_COMUNIDAD(+)";
      rs=stm.executeQuery(query);
      while (rs.next())
      {
        codSns=rs.getString("COD_USUARIO_SNS");
        ap1=rs.getString("APELLIDO1");
        ap2=rs.getString("APELLIDO2");
        codSexo=rs.getString("COD_SEXO");
        fechaNac=rs.getString("FECHANAC");
        caNac=rs.getString("COD_CA_ISO");
        paisNac=rs.getString("COD_PAIS");

        raiz=OperacionesCodSS.genera(ap1,ap2,codSexo,fechaNac,caNac,paisNac);
        if (raiz.length()==13) {
          queryUp="update datos_personales set raiz='" + raiz + "' where cod_usuario_sns='" + codSns + "'";
          stm2.executeUpdate(queryUp);
          System.out.println(queryUp + ";");
        }else
        {
          System.out.println("error raiz: " + raiz);
        }
        codSns="";
        raiz="";
        ap1="";
        ap2="";
        codSexo="";
        fechaNac="";
        caNac="";
        paisNac="";
        queryUp="";
      }


      System.out.println("tratando apellido2 " + new java.util.Date());

      query="SELECT COD_USUARIO_SNS,APELLIDO1,APELLIDO2,COD_SEXO,to_char(FECHA_NAC,'yyyy-mm-dd') FECHANAC,c.COD_CA_ISO,COD_PAIS FROM DATOS_PERSONALES d,comunidades_autonomas c WHERE d.APELLIDO2 LIKE 'Y %' AND d.RAIZ LIKE '%Y%' and d.cod_comunidad=c.COD_COMUNIDAD(+)";
      rs=stm.executeQuery(query);
      while (rs.next())
      {
        codSns=rs.getString("COD_USUARIO_SNS");
        ap1=rs.getString("APELLIDO1");
        ap2=rs.getString("APELLIDO2");
        codSexo=rs.getString("COD_SEXO");
        fechaNac=rs.getString("FECHANAC");
        caNac=rs.getString("COD_CA_ISO");
        paisNac=rs.getString("COD_PAIS");

        raiz=OperacionesCodSS.genera(ap1,ap2,codSexo,fechaNac,caNac,paisNac);
        if (raiz.length()==13) {
          queryUp="update datos_personales set raiz='" + raiz + "' where cod_usuario_sns='" + codSns + "'";
          stm2.executeUpdate(queryUp);
          System.out.println(queryUp + ";");
        }else
        {
          System.out.println("error raiz: " + raiz);
        }
        codSns="";
        raiz="";
        ap1="";
        ap2="";
        codSexo="";
        fechaNac="";
        caNac="";
        paisNac="";
        queryUp="";
      }
      System.out.println("fin " + new java.util.Date());
    }catch (SQLException sq)
    {
      System.out.println("Error: " + sq.getMessage() + " - " + sq.toString());
    }finally
    {
      try {
        if (rs!=null)
          rs.close();
        if (stm!=null)
          stm.close();
        if (stm2!=null)
          stm2.close();
        if (conn!=null)
          conn.close();
      }catch (SQLException se)
      {
        se.printStackTrace();
      }
    }
  }
}