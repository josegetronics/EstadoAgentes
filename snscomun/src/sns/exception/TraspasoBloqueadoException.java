package sns.exception;

import sns.model.NotificacionTraspasosBloqueados;



public class TraspasoBloqueadoException extends TsiException {


	private NotificacionTraspasosBloqueados notificacionTraspasosBloqueados;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943841610858544086L;
	
	public TraspasoBloqueadoException(NotificacionTraspasosBloqueados notificacionTraspasosBloqueados) {
		super("TraspasoBloqueadoException");
		this.notificacionTraspasosBloqueados=notificacionTraspasosBloqueados;
	}

	public NotificacionTraspasosBloqueados getNotificacionTraspasosBloqueados() {
		return notificacionTraspasosBloqueados;
	}

}
