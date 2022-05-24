package sns.model;

import sns.config.Constantes;
import sns.mail.MailHelper;

public class NotificacionTraspasosBloqueados {

	private boolean vacio;
	
	private String cuerpo;
	private String asunto;
	
	public NotificacionTraspasosBloqueados() {
		this.vacio=true;
	}
	public boolean isVacio() {
		return vacio;
	}
	public void setVacio(boolean vacio) {
		this.vacio = vacio;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
		this.vacio=false;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
		this.vacio=false;
	}

	public void notificar() {
	  if (!isVacio()) {
	      MailHelper mailHelper=new MailHelper(getAsunto(),getCuerpo(),Constantes.PUNTO_EMAIL_TRASPASOS_BLOQUEADOS);
	      mailHelper.enviar();
	  }
	}
	
	
}
