package sns.model.gestion;

import java.math.BigDecimal;

import sns.config.Constantes;
import sns.model.UsuarioSns;
import sns.model.aportacion.ResultadoBusquedaAportacion;
import sns.model.inss.ifi.IfiFicheroBean;
import sns.model.inss.impl.ResultadoInssExtBean;


public class GestionHelper {

	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(GestionHelper.class);

	public static AportacionBeneficiarios getGestionBeneficiarios(String codSnsTitular,ResultadoBusquedaAportacion resultadoBusquedaAportacion) {
		AportacionBeneficiarios aportacionBeneficiarios=new AportacionBeneficiarios();
		aportacionBeneficiarios.setAccion("Asignar Aportacion Beneficiarios");
		aportacionBeneficiarios.setCodUsuarioSnsTitular(codSnsTitular);
		aportacionBeneficiarios.setCodIndicadorDeFarmacia(resultadoBusquedaAportacion.getCodIndicadorDeFarmacia());
		aportacionBeneficiarios.setCodSubIndicadorDeFarmacia(resultadoBusquedaAportacion.getCodSubIndicadorDeFarmacia());
		aportacionBeneficiarios.setCodTipoProcedencia(resultadoBusquedaAportacion.getCodTipoProcedencia());
		aportacionBeneficiarios.setId(Constantes.GESTION_ID_TRATAMIENTO_APORTACION_BENEFICIARIOS);
		logger.debug(aportacionBeneficiarios.toString());
		return aportacionBeneficiarios;
	}
	
	public static NotificacionTitulo getGestionTitulo(UsuarioSns usuarioSnsIn,BigDecimal agente) {
		NotificacionTitulo notificacionTitulo=new NotificacionTitulo();
		notificacionTitulo.setAccion("Notificacion Titulo");
		notificacionTitulo.setUsuarioSns(usuarioSnsIn);
		notificacionTitulo.setAgente(agente);
		notificacionTitulo.setId(Constantes.GESTION_ID_TRATAMIENTO_NOTIFICAR_TITULO);
//		logger.debug(notificacionTitulo.toString());
		return notificacionTitulo;
	}
	
	public static RegistroInss getGestionRegistroInss(Integer codOperacion,BigDecimal idInssFichero,long numLineas,IfiFicheroBean ifiFicheroBean) {
		RegistroInss registroInss=new RegistroInss();
		registroInss.setAccion("Actualizacion del INSS");
		registroInss.setCodOperacion(codOperacion);
		registroInss.setIdInssFichero(idInssFichero);
		registroInss.setNumLineasTotales(numLineas);
		registroInss.setIfiRegistro(ifiFicheroBean);
		registroInss.setId(Constantes.GESTION_ID_TRATAMIENTO_FICHERO_INSS);
//		logger.debug(registroInss.toString());
		return registroInss;
	}

	public static RegistroLineaInss getGestionRegistroInssActualizar(Integer codOperacion,BigDecimal idInssFichero,long numRegistro,long numTotalRegistros,IfiFicheroBean ifiFicheroBean) {
		RegistroLineaInss registroInss=new RegistroLineaInss();
		registroInss.setAccion("Actualizacion del INSS");
		registroInss.setCodOperacion(codOperacion);
		registroInss.setIdInssFichero(idInssFichero);
		registroInss.setNumRegistro(numRegistro);
		registroInss.setNumLineasTotales(numTotalRegistros);
		registroInss.setIfiRegistro(ifiFicheroBean);
		registroInss.setId(Constantes.GESTION_ID_TRATAMIENTO_LINEA_INSS);
//		logger.debug(registroInss.toString());
		return registroInss;
	}

	public static EventoProcesarRegistroInss getGestionEventoProcesarRegistroInss(Integer codOperacion,BigDecimal idInssFichero,String codTipoMovimiento) {
		EventoProcesarRegistroInss eventoProcesarRegistroInss=new EventoProcesarRegistroInss();
		eventoProcesarRegistroInss.setAccion("Actualizacion del INSS");
		eventoProcesarRegistroInss.setCodOperacion(codOperacion);
		eventoProcesarRegistroInss.setIdInssFichero(idInssFichero);
		eventoProcesarRegistroInss.setId(Constantes.GESTION_ID_TRATAMIENTO_MOVIMIENTOS_INSS);
		eventoProcesarRegistroInss.setCodTipoMovimiento(codTipoMovimiento);
//		logger.debug(registroInss.toString());
		return eventoProcesarRegistroInss;
	}
	public static EventoInss getGestionEventoActualizacionSns(Integer codOperacion,BigDecimal idInssFichero) {
		EventoInss eventoInss=new EventoInss();
		eventoInss.setAccion("Actualizacion del INSS");
		eventoInss.setCodOperacion(codOperacion);
		eventoInss.setIdInssFichero(idInssFichero);
		eventoInss.setId(Constantes.GESTION_ID_ACTUALIZACION_SNS_INSS);
//		logger.debug(registroInss.toString());
		return eventoInss;
	}

	public static EventoRegistroSnsInss getGestionEventoActualizacionRegistroSns(ResultadoInssExtBean resultadoInssExtBean,Integer codOperacion,BigDecimal idInssFichero) {
		EventoRegistroSnsInss eventoInss=new EventoRegistroSnsInss();
		eventoInss.setAccion("Actualizacion del INSS");
		eventoInss.setCodOperacion(codOperacion);
		eventoInss.setIdInssFichero(idInssFichero);
		eventoInss.setResultadoInssExtBean(resultadoInssExtBean);
		eventoInss.setId(Constantes.GESTION_ID_ACTUALIZACION_REGISTRO_SNS_INSS);
		return eventoInss;
	}
	
}
