package sns.mail;

import gasai.util.Misc;
import gasai.util.out.Salida;
import sns.config.Constantes;
import sns.config.Inicializacion;
import sns.model.ErroresHelper;

public class MailHelper extends gasai.mail.Correo {

  private int puntoEmision;

  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(MailHelper.class);

  ErroresHelper erroresHelper=new ErroresHelper();

  public MailHelper(String emailOmovil,String asunto, String mensaje,int puntoEmision) {
    super(emailOmovil,Inicializacion.EMAIL_REMITENTE,Inicializacion.HOST_SMTP,asunto,mensaje,false,false);
    inicializar(asunto,mensaje,puntoEmision,null);
  }

  public MailHelper(String asunto, String mensaje,int puntoEmision) {
    super("",Inicializacion.EMAIL_REMITENTE,Inicializacion.HOST_SMTP,asunto,mensaje,false,false);
    inicializar(asunto,mensaje,puntoEmision,null);
  }

  public MailHelper(String asunto, String mensaje,int puntoEmision,Throwable e) {
    super("",Inicializacion.EMAIL_REMITENTE,Inicializacion.HOST_SMTP,asunto,mensaje,false,false);
    inicializar(asunto,mensaje,puntoEmision,e);
  }

  private void inicializar(String asunto,String mensaje,int puntoEmision,Throwable e) {
    this.puntoEmision=puntoEmision;
	  String asuntoEmail = asunto;
	  if (!Misc.esVacio(sns.config.Inicializacion.PREFIJOAPP)) {
	    asuntoEmail = sns.config.Inicializacion.PREFIJOAPP + "_" + asuntoEmail;
	  }
	  asuntoEmail += " [" + System.getProperty("weblogic.Name") + "]";
	  super.setSubject(asuntoEmail);
	
	  String pilaEjecucion = "";
	  if (e != null) {
	    pilaEjecucion = e.getMessage() + "\r\n" +
	        "-----------------------------------------" + "\r\n" +
	        erroresHelper.getPilaEjecucion(e);
	  }
	  super.setMsgText(mensaje + "\r\n" + pilaEjecucion);
	
	  configurarDestinatarios(puntoEmision);
  }

  private void configurarDestinatarios(int puntoEmision) {
    switch (puntoEmision) {
      case Constantes.PUNTO_EMAIL_TRASPASOS_BLOQUEADOS:
        super.setTo(Inicializacion.EMAIL_TRASPASOS);
        break;
      case Constantes.PUNTO_EMAIL_SEMAFORO_TRANSACCIONES:
        super.setTo(Inicializacion.EMAIL_SOPORTE);
        break;
      case Constantes.PUNTO_EMAIL_NOTIFICACION_AL_INTERCAMBIADOR:
        super.setTo(Inicializacion.EMAIL_ALERTAS_SISTEMA);
        break;
      case Constantes.PUNTO_EMAIL_CONSULTA_HISTORICO:
        super.setTo(Inicializacion.EMAIL_SOPORTE);
        break;
      case Constantes.PUNTO_EMAIL_LISTENER_ENTRADA_NUMERO_INTENTOS_EXCEDIDO:
        super.setTo(Inicializacion.EMAIL_SOPORTE);
        break;
      case Constantes.PUNTO_EMAIL_LISTENER_ENTRADA_PROBLEMAS_PROCESAR:
        super.setTo(Inicializacion.EMAIL_SOPORTE);
        break;
      case Constantes.PUNTO_EMISION_ENTRADA_CONSULTA_HEALTH_CHECK:
        super.setTo(Inicializacion.EMAIL_SOPORTE);
        break;

    }
  }

  public Salida enviar() {
    if (Inicializacion.ACTIVADO_CORREO
        && this.puntoEmision==Constantes.PUNTO_EMAIL_TRASPASOS_BLOQUEADOS) {
      printEmail("enviar");
      return super.enviar();
    }else{
      logger.info("Correo no activado");
      printEmail("enviar");
      Salida salida=new Salida();
      salida.setOk();
      return salida;
    }
  }

  private void printEmail(String origen) {
    try {
      logger.info("Metodo origen: " + origen);
      logger.info("Destinatario: " + super.getTo());
      logger.info("Asunto: " + super.getSubject());
      logger.info("Cuerpo: " + super.getMsgText());
    }catch (Exception e) {
      logger.error("Error pintando el email",e);
    }
  }

  public Salida enviarConAdjuntos(String[] nombreFichero,String[] pathFichero) {
    if (Inicializacion.ACTIVADO_CORREO
        && this.puntoEmision==Constantes.PUNTO_EMAIL_TRASPASOS_BLOQUEADOS) {
      printEmail("enviarConAdjuntos");
      return super.enviarConFicheros(nombreFichero,pathFichero);
    }else{
      logger.info("Correo no activado");
      printEmail("enviarConAdjuntos");
      Salida salida=new Salida();
      salida.setOk();
      return salida;
    }
  }

}
