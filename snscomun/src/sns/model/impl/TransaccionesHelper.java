package sns.model.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import sns.bd.AccesoBd;
import sns.config.Constantes;
import sns.config.Inicializacion;
import sns.mail.MailHelper;
import sns.model.ITransaccionesHelper;
import sns.util.out.Salida;

public class TransaccionesHelper implements ITransaccionesHelper {
  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(TransaccionesHelper.class);
//  static org.apache.log4j.Logger logger =org.apache.log4j.Logger.getLogger("MantenimientoLog");

  private final static String QUERY_INSERT="insert into CONTROL_TRASACCIONES (COD_USUARIO_SNS,COD_OPERACION,FECHA_OPERACION) values (?,?,sysdate)";
  private final static String QUERY_DELETE="DELETE FROM CONTROL_TRASACCIONES WHERE COD_USUARIO_SNS=? and COD_OPERACION=?";
  private final static String QUERY_DELETE_SEMAFORO="DELETE FROM CONTROL_TRASACCIONES WHERE COD_USUARIO_SNS=?  and FECHA_OPERACION <= (sysdate - 5/(24*60)) ";

  public TransaccionesHelper() {
  }
  
  private boolean releaseByTimeOut(String codUsuarioSns,Integer operacionMaestra, AccesoBd bd) {
	  boolean borrado=false;
	  
	  try {
	      HashMap hParam = new HashMap();
	      hParam.put("1", codUsuarioSns);
	      logger.debug("Intentando releaseByTimeOut para el [usuario, operacionMaestra] -> [" + codUsuarioSns + "," + operacionMaestra + "]");
		  Vector vDatos=bd.actualizaSinCommit(QUERY_DELETE_SEMAFORO, hParam);
		  Integer valor=(Integer)vDatos.elementAt(0);
		  if (valor.intValue()>0) {
			  borrado=true;
		      logger.debug("Se quita el semaforo anterior para el [usuario] -> [" + codUsuarioSns + "]");
		        hParam.put("2", operacionMaestra);
		        bd.actualizaConCommit(QUERY_INSERT, hParam);
	          logger.debug("Se activa el semaforo para el usuario [usuario, operacionMaestra] -> [" + codUsuarioSns + "," + operacionMaestra + "]");
		  }else{
		      logger.debug("Semaforo activo. No hay timeout para el [usuario] -> [" + codUsuarioSns + "]");
		  }
		  bd.commit();
	  }catch (SQLException e) {
		  logger.error("Error intentando liberar semaforo por timeout",e);
		  try {
			  bd.rollback();
		  }catch (SQLException e2) {
			  logger.error("Error intentando hacer rollback",e);
		  }
	  }
	  
	  return borrado;
  }

  public Salida start(String codUsuarioSns,Integer operacionMaestra) {
    Salida salida=new Salida();
    logger.debug("Iniciando control de transaccion para el [usuario, operacionMaestra] -> [" + codUsuarioSns + "," + operacionMaestra + "]");
    AccesoBd bd=null;
    try {
      if (Inicializacion.ACTIVADO_CONTROL_TRANSACCIONES) {
        bd = new AccesoBd();
        HashMap hParam = new HashMap();
        hParam.put("1", codUsuarioSns);
        hParam.put("2", operacionMaestra);
        bd.actualizaConCommit(QUERY_INSERT, hParam);
      }
      salida.setOk();
      return salida;
    }catch (SQLException se) {
      if (se.getErrorCode()==1) {
        String textoError="Operacion ["+ operacionMaestra + "] bloqueada por conflicto de concurrencia para el usuario -> [" + codUsuarioSns + "]";
        logger.debug(textoError);
        boolean borrado=releaseByTimeOut(codUsuarioSns,operacionMaestra,bd);
        if (!borrado) {
	        salida.setError(true);
	        salida.setValor(Constantes.ERROR_EG013);
	        salida.setMsgException("Operacion bloqueada por conflicto de concurrencia");
	        salida.setMsg("Operacion bloqueada por conflicto de concurrencia");
	        //notificar("start",textoError,se);
        }else{
            salida.setOk();
        }
        return salida;
      }else{
        String textoError="Error comenzando transaccion para operacion ["+ operacionMaestra + "] y usuario -> [" + codUsuarioSns + "]";
        logger.error(textoError,se);
        //notificar("start",textoError,se);
        salida.setOk();
      }
    }catch (Throwable e) {
      String textoError="Error comenzando transaccion para operacion ["+ operacionMaestra + "] y usuario -> [" + codUsuarioSns + "]";
      logger.error(textoError,e);
      //notificar("start",textoError,e);
      salida.setOk();
    }finally {
      if (bd!=null) {
        bd.cerrar();
      }
    }
    return salida;
  }

  public void release(String codUsuarioSns,Integer operacionMaestra) {

    Salida salida=new Salida();
    logger.debug("Liberando control de transaccion para operacion ["+ operacionMaestra + "] y usuario -> [" + codUsuarioSns + "]");
    AccesoBd bd=null;
    try {
      bd = new AccesoBd();
      HashMap hParam = new HashMap();
      hParam.put("1", codUsuarioSns);
      hParam.put("2", operacionMaestra);
      bd.actualizaConCommit(QUERY_DELETE, hParam);
    }
    catch (Throwable e) {
      String textoError="Error liberando transaccion para el usuario -> [" + codUsuarioSns + "]";
      logger.error(textoError,e);
      //notificar("release",textoError,e);
      salida.setOk();
    }finally {
      if (bd!=null) {
        bd.cerrar();
      }
    }
    logger.debug("FIN Liberando control de transaccion para operacion ["+ operacionMaestra + "] y usuario -> [" + codUsuarioSns + "]");
   }

  private void notificar(String accion, String texto,Throwable e) {
   try {
      String asuntoEmail = "Transacciones -> " + accion;

      MailHelper mailHelper=new MailHelper(asuntoEmail,texto,Constantes.PUNTO_EMAIL_SEMAFORO_TRANSACCIONES,e);
      mailHelper.enviar();

    } catch (Throwable e2) {
      logger.error("Error enviando notificacion de error de transacciones",e2);
    }
  }
}
