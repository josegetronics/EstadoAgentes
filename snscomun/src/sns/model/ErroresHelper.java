package sns.model;

import java.util.HashMap;

import sns.bd.AccesoBd;
import sns.config.Constantes;
import sns.config.Inicializacion;
import sns.mail.MailHelper;

public class ErroresHelper {
  public ErroresHelper() {
  }

  public String getPilaEjecucion(java.lang.Throwable e) {
    String pilaExcepcion="";
    StackTraceElement [] stack=e.getStackTrace();
     for(int j=0;j<stack.length;j++){
       pilaExcepcion += stack[j].toString() + "\r\n";
     }
     return pilaExcepcion;
  }

  public void tratar(String codAgente,Integer codOperacion,Integer codXmlEntrada,java.lang.Throwable e) {
    //
    if (Inicializacion.ACTIVAR_EMAIL_ALERTAS_SISTEMA) {

      String bodyEmail =
          "Excepcion producida al enviar la notificacion al intercambiador desde [" +
          System.getProperty("weblogic.Name") + "] para el [agente,codOperacionTarjeta,codXmlEntradaIntercambiador] [" + codAgente + "," + codOperacion + "," + codXmlEntrada + "]\r\n";

      MailHelper mailHelper=new MailHelper(Inicializacion.ASUNTO_EMAIL_ALERTAS_SISTEMA,bodyEmail,Constantes.PUNTO_EMAIL_NOTIFICACION_AL_INTERCAMBIADOR,e);
      mailHelper.enviar();
    }

    if (Inicializacion.ACTIVAR_GUARDAR_ALERTAS_SISTEMA) {
      //
      String pilaEjecucion=e.getMessage() + "\r\n" +
             "-----------------------------------------" + "\r\n" + getPilaEjecucion(e);

      guardarError(codAgente,codOperacion,codXmlEntrada,pilaEjecucion);
    }

  }

  private void guardarError(String codAgente,Integer codOperacion,Integer codXmlEntrada,String pilaEjecucion) {
    AccesoBd bd = null;
    try {
      bd = new AccesoBd();

      String insertar="insert into ERRORES_INTERCAMBIADOR (COD_ERROR_INTERCAMBIADOR,COD_AGENTE,COD_OPERACION_SALIDA,COD_XML_ENTRADA,FECHA_ERROR,EXCEPCION) values (SEQ_ERRORES_INTERCAMBIADOR.nextval,?,?,?,?,?)";

      HashMap hParam=new HashMap();
      hParam.put("1",codAgente);
      hParam.put("2",codOperacion);
      hParam.put("3",codXmlEntrada);
      hParam.put("4",new java.sql.Timestamp(System.currentTimeMillis()));
      hParam.put("5",pilaEjecucion);


      bd.actualizaConCommit(insertar,hParam);
    }catch (java.lang.Throwable e) {
      e.printStackTrace();
    }finally {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }
}
