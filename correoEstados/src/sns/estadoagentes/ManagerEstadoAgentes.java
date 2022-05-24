package sns.estadoagentes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import sns.comun.bd.AccesoBdFactory;
import sns.comun.bean.CambiosTraspasosBean;
import sns.comun.bean.TraspasosNuevoBean;
import sns.comun.config.Inicializacion;
import sns.comun.scheduler.ConfiguracionQuartz;
import sns.comun.util.Util;
import sns.comun.util.mail.CuerpoMailBean;
import sns.comun.util.mail.MailHelper;
import sns.estadoagentes.model.EstadoAgentesHelper;
import sns.estadoagentes.model.Info;
import sns.estadoagentes.model.InfoBloqueos;
import sns.estadoagentes.model.InfoEstadoAgentes;
import sns.estadoagentes.model.InfoTraspasos;
import sns.estadoagentes.model.InfoTraspasosBusquedaAproximada;
import sns.estadoagentes.model.InfoTraspasosNuevo;
public class ManagerEstadoAgentes {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	public static void main(String[] args) throws SchedulerException {
		
		try {					
			
			ConfiguracionQuartz con = new ConfiguracionQuartz();
			con.configuracionQuartz();			
			
			/*
			  ManagerEstadoAgentes managerEstadoAgentes = new ManagerEstadoAgentes();
			  managerEstadoAgentes.getInfo();
			*/		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}

	
	public void getInfo() throws Exception {
				

		//Acceso BD
		AccesoBD bd = null;
		AccesoBD bdInt = null;
		
		logger.debug("Probando el scheduler");
		
		try {
			
		bd = AccesoBdFactory.getInstance(AccesoBdFactory.SISTEMA_TSI);
		bdInt = AccesoBdFactory.getInstance(AccesoBdFactory.SISTEMA_INTERCAMBIADOR);
		
		logger.debug("sns.estadoagentes.ManagerEstadoAgentes.getInfo: INICIO" + Inicializacion.TRASPASOS_NUEVOS);
		CuerpoMailBean cuerpoMailBean = new CuerpoMailBean();
		CambiosTraspasosBean cambiosTraspasosBean = null;
		CambiosTraspasosBean cambiosTraspasosBusquedaBean = null;
		CambiosTraspasosBean cambiosTraspasosTablaBean = null;
		CambiosTraspasosBean cambiosNuevoTraspasosBean = null;
		HashMap hTraspasosRealizados = null;
		HashMap hAltasIndebidas = null;
		HashMap hAltasMuface = null;
		HashMap hMensajesN012 = null;
		HashMap hAltasExtranjerosDosVeinte = null;
		HashMap hAltasCodigoSNSGenerados = null;
		EstadoAgentesHelper estadoAgentesHelper = null;
		InfoEstadoAgentes infoEstadoAgentes = null;
		InfoBloqueos infoBloqueos = null;
		Info info = null;
		
		// Si en el properties ESTADO_AGENTES viene a 1 pintamos la parte de
		// Estado Agentes
		// Pinta la tabla de los Estados de los Agentes
		if (Misc.nz(Inicializacion.ESTADO_AGENTES).equals("1")) {
			try {
				infoEstadoAgentes = new InfoEstadoAgentes();
				cuerpoMailBean = infoEstadoAgentes.getInfoEstadoAgentes(Inicializacion.pathFicheroEstadisticas, bd);
			} catch (Exception e) {
				logger.error("Exception: " + e.getMessage());
				throw e;
			}
			try {
				infoEstadoAgentes.guardarConfiguracion(cuerpoMailBean);
			} catch (IOException ex) {
				logger.error("Error actualizando la configuracion", ex);
				cuerpoMailBean.setErrores("<table border=\"1\"><tr><td>## NOTA: Se han producido errores al guardar el fichero properties ##</td></tr></table>");
			}
		}
		
		// Si en el properties BLOQUEADOS viene a 1 pintamos la parte de
		// Bloqueados
		// Pinta la tabla de los mensajes bloqueados
		if (Misc.nz(Inicializacion.BLOQUEADOS).equals("1")) {
			infoBloqueos = new InfoBloqueos();
			try {
				String mensajesBloqueados = infoBloqueos.obtenerMensajeBloqueos(bdInt);
				cuerpoMailBean.setMensajesBloqueados(mensajesBloqueados);
			} catch (Exception e) {
				logger.error("Exception al obtener la tabla de los mensajes bloqueados: " + e.getMessage());
				throw e;
			}
		}

		// Si en el properties TRASPASOS viene a 1 pintamos la parte de
		// Traspasos
		// Pinta la tabla de los traspasos y número de cambios
		if (Misc.nz(Inicializacion.TRASPASOS).equals("1")) {
			info = new InfoTraspasos();
			try {
				cambiosTraspasosBean = info.getInfoTraspasos(Inicializacion.pathDirCambiosTraspasos, Inicializacion.numCambiosVisualizar, bd);
				HashMap mapInformacion = cambiosTraspasosBean.getMapInformacion();
				if (mapInformacion != null) {
					cuerpoMailBean.setTraspasosBloqueados(info.viewInfoMapHorizontal(mapInformacion).toString());
				}
			} catch (Exception e) {
				logger.error("Exception: " + e.getMessage());
				throw e;
			}
		}

		// Si en el properties TRASPASOS_NUEVOS viene a 1 pintamos la parte de
		// Traspasos Nuevos
		// Pinta la tabla nueva y genera el fichero aaaa-mm-dd_mismaCA.csv con las modificaciones en una misma CCAA
		if (Misc.nz(Inicializacion.TRASPASOS_NUEVOS).equals("1")) {
			TraspasosNuevoBean traspasosNuevoBean = new TraspasosNuevoBean();
			estadoAgentesHelper = new EstadoAgentesHelper();
			HashMap mapInformacionNuevo = null;
			try {
				info = new InfoTraspasosNuevo();
				//Con este metodo se calcula las modificaciones dentro de una misma CCAA con los campos que provocan bloqueo, genera el fichero 
				//aaaa-mm-dd_mismaCA.csv y se rellena en el bean CambiosTraspasosBean toda la informacion de BBDD que hemos utilizado para sacar 
				//el fichero y que se necesitara para calcular la tabla nueva
				cambiosNuevoTraspasosBean = info.getInfoModificaciones(Inicializacion.pathDirCambiosTraspasos, Inicializacion.numCambiosVisualizarDos,bd);
			
				//A partir de aqui se calcula la tabla nueva
				
				//En un HashMap auxiliar metemos la informacion que hemos utilizado para calcular el fichero para utilizarla ahora para calcular
				//el apartado de la tabla nueva TOTAL CAMBIOS 3 O MAS AGENTE (2)
				mapInformacionNuevo = cambiosNuevoTraspasosBean.getMapInformacion();
				//Con el HashMap auxiliar con la informacion calculamos el apartado de la tabla nueva TOTAL CAMBIOS 3 O MAS AGENTE (2)
				traspasosNuevoBean.sethModificaciones(estadoAgentesHelper.contarTresMas(mapInformacionNuevo));
				
				//Vamos a calcular los valores de trasapasos para la tabla nueva
				info = new InfoTraspasos();
				//calculamos todos los traspasos con los campos que provocan bloqueo
				cambiosTraspasosTablaBean = info.getInfoTraspasos(Inicializacion.pathDirCambiosTraspasos, Inicializacion.numCambiosVisualizar,bd);
				//En la HashMap auxiliar metemos la informacion que hemos calculado antes para calcular varios apartados de la tabla nueva
				mapInformacionNuevo = cambiosTraspasosTablaBean.getMapInformacion();
				//Con la informacion del HashMap auxiliar calculamos el apartado de la tabla nueva TOTAL TRASPASOS
				traspasosNuevoBean.sethTotalTraspasos(estadoAgentesHelper.contarTotal(mapInformacionNuevo));
				//Con la informacion del HashMap auxiliar calculamos el apartado de la tabla nueva TRASPASOS 3 O MAS (1)
				traspasosNuevoBean.sethTraspasos(estadoAgentesHelper.contarTresMas(mapInformacionNuevo));
				//Aqui calculamos el apartado de la tabla nueva DESBLOQUEOS REALIZADOS
				traspasosNuevoBean.sethDesbloqueos(estadoAgentesHelper.getDesbloqueos(bd));
				//Aqui calculamos el apartado de la tabla nueva BLOQUEOS DIARIOS
				traspasosNuevoBean.sethBloqueos(estadoAgentesHelper.getBloqueos(bd));
				//Aqui calculamos el apartado de la tabla nueva CODIGOS SNS GENERADOS
				traspasosNuevoBean.sethUsuarios(estadoAgentesHelper.getUsuarios(bd));
				//Aqui ya con toda la información pintamos la tabla nueva
				cuerpoMailBean.setTraspasosBloqueadosNuevo(estadoAgentesHelper.pintarTraspasosNuevos(traspasosNuevoBean).toString());
			} catch (Exception e) {
				logger.error("Exception: " + e.getMessage());
				throw e;
			}
			
			
		}
		
		// Si en el properties TRASPASOS_BUSQUEDA_APROXIMADA viene a 1 pintamos la parte de
		// Traspasos Busqueda Aproximada
		//Genera fichero saaaa-mm-dd_cambioCA.csv y aaaa-mm-dd_bloqueos.csv
		if (Misc.nz(Inicializacion.TRASPASOS_BUSQUEDA_APROXIMADA).equals("1")) {
			info = new InfoTraspasosBusquedaAproximada();
			try {
				//Con los traspasos genera dos ficheros aaaa-mm-dd_cambioCA.csv y aaaa-mm-dd_bloqueos.csv
				cambiosTraspasosBusquedaBean = info.getInfoTraspasos(Inicializacion.pathDirCambiosTraspasos, Inicializacion.numCambiosVisualizarDos,bd);				
			} catch (Exception e) {
				logger.error("Exception: " + e.getMessage());
				throw e;
			}
		}
		
		//Altas No Asegurado
		//Genera el fichero aaaa-mm-dd_altasTitulosNA.csv con las altas de los No Asegurados
		try {
			hAltasIndebidas = estadoAgentesHelper.ficheroAltasNA(Inicializacion.pathFicheroTraspasosRealizados, bd);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		
		//Altas Muface
		//Genera el fichero aaaa-mm-dd_altasMuface.csv con las altas de los No Asegurados
		try {
			hAltasMuface = estadoAgentesHelper.ficheroAltasMuface(Inicializacion.pathFicheroTraspasosRealizados, bd);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		
		//Resumen Mensajeria N012
		//Genera el fichero aaaa-mm-dd_resumenMensajeriaN012.csv
		try {
			hMensajesN012 = estadoAgentesHelper.ficheroResumenMensajeriaN012(Inicializacion.pathFicheroTraspasosRealizados, bd);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}

		//Altas Extranjeros Asegurados con edad entre dos y veinte años
		//Genera el fichero aaaa-mm-dd_altasExtranjeroDosVeinte.cs con las altas de los No Asegurados
		try {
			hAltasExtranjerosDosVeinte = estadoAgentesHelper.ficheroAltasExtranjerosDosVeinte(Inicializacion.pathFicheroTraspasosRealizados, bd);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		
		//Altas generadas con un nuevo codigo SNS NO incluidas en altasTitulosNA y altasExtranjeroDosVeinte
		//Genera el fichero aaaa-mm-dd_altasCodigoSNSGenerados.cs con las altas no incluidas los ficheros 
		try {
			hAltasCodigoSNSGenerados = estadoAgentesHelper.ficheroAltasCodigoSNSGenerados(Inicializacion.pathFicheroTraspasosRealizados, bd);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}

		boolean correoConAdjunto = false;

		ArrayList arrayListNombreFichero = new ArrayList();
		ArrayList arrayListPathFichero = new ArrayList();
		
		if (cambiosNuevoTraspasosBean != null) {
			if ((!Misc.esVacio(cambiosNuevoTraspasosBean.getPathFichero())) && (!Misc.esVacio(cambiosNuevoTraspasosBean.getNombreFicheroCodSns()))) {
				correoConAdjunto = true;
				arrayListNombreFichero.add(cambiosNuevoTraspasosBean.getNombreFicheroCodSns());
				arrayListPathFichero.add(cambiosNuevoTraspasosBean.getPathFichero() + "/" + cambiosNuevoTraspasosBean.getNombreFicheroCodSns());
			}
		}		
		
		if (cambiosTraspasosBusquedaBean != null) {
			if ((!Misc.esVacio(cambiosTraspasosBusquedaBean.getPathFichero())) && (!Misc.esVacio(cambiosTraspasosBusquedaBean.getNombreFicheroCodSns()))) {
				correoConAdjunto = true;
				arrayListNombreFichero.add(cambiosTraspasosBusquedaBean.getNombreFichero());
				arrayListPathFichero.add(cambiosTraspasosBusquedaBean.getPathFichero() + "/" + cambiosTraspasosBusquedaBean.getNombreFichero());
				arrayListNombreFichero.add(cambiosTraspasosBusquedaBean.getNombreFicheroCodSns());
				arrayListPathFichero.add(cambiosTraspasosBusquedaBean.getPathFichero() + "/" + cambiosTraspasosBusquedaBean.getNombreFicheroCodSns());
			}
		}
		
		if (hTraspasosRealizados != null) {
				correoConAdjunto = true;
				arrayListNombreFichero.add((String)hTraspasosRealizados.get("NombreFichero"));
				arrayListPathFichero.add((String)hTraspasosRealizados.get("PathFichero") + "/" + (String)hTraspasosRealizados.get("NombreFichero"));
		}
		
		if (hAltasIndebidas != null) {
			correoConAdjunto = true;
			arrayListNombreFichero.add((String)hAltasIndebidas.get("NombreFichero"));
			arrayListPathFichero.add((String)hAltasIndebidas.get("PathFichero") + "/" + (String)hAltasIndebidas.get("NombreFichero"));
		}
		
		if (hAltasMuface != null) {
			correoConAdjunto = true;
			arrayListNombreFichero.add((String)hAltasMuface.get("NombreFichero"));
			arrayListPathFichero.add((String)hAltasMuface.get("PathFichero") + "/" + (String)hAltasMuface.get("NombreFichero"));
		}
		
		if (hMensajesN012 != null) {
			correoConAdjunto = true;
			arrayListNombreFichero.add((String)hMensajesN012.get("NombreFichero"));
			arrayListPathFichero.add((String)hMensajesN012.get("PathFichero") + "/" + (String)hMensajesN012.get("NombreFichero"));
		}

		if (hAltasExtranjerosDosVeinte != null) {
			correoConAdjunto = true;
			arrayListNombreFichero.add((String)hAltasExtranjerosDosVeinte.get("NombreFichero"));
			arrayListPathFichero.add((String)hAltasExtranjerosDosVeinte.get("PathFichero") + "/" + (String)hAltasExtranjerosDosVeinte.get("NombreFichero"));
		}
		
		if (hAltasCodigoSNSGenerados != null) {
			correoConAdjunto = true;
			arrayListNombreFichero.add((String)hAltasCodigoSNSGenerados.get("NombreFichero"));
			arrayListPathFichero.add((String)hAltasCodigoSNSGenerados.get("PathFichero") + "/" + (String)hAltasCodigoSNSGenerados.get("NombreFichero"));
		}

		String[] arrayNombreFicheros = null;
		String[] arrayPath = null;
		if (correoConAdjunto) {
			arrayNombreFicheros = new String[arrayListNombreFichero.size()];
			arrayPath = new String[arrayListNombreFichero.size()];
			// Correo correo = new Correo(this.destinatarios, "sns@msc.es",
			// this.smtp, this.asunto, this.mensajeEntero, true, true);

			for (int i = 0; i < arrayListNombreFichero.size(); i++) {
				arrayNombreFicheros[i] = Misc.nz(arrayListNombreFichero.get(i));
				arrayPath[i] = Misc.nz(arrayListPathFichero.get(i));
			}
		}
		
		Util util = new Util();
		MailHelper mailHelper = new MailHelper();
		mailHelper.enviarMail(cuerpoMailBean, Inicializacion.destinatarios, arrayNombreFicheros, arrayPath, CuerpoMailBean.CUERPO_MAIL_MINISTERIO,util.generateFecha("-"));
		logger.debug("sns.estadoagentes.getInfo: FIN");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		} finally {
			//
			try {
				if (bd != null) {
					bd.cerrar();
					logger.debug("getInfoTraspasos: CONENECTION_SNS CLOSE");
				}
			} catch (Exception e2) {
				bd = null;
			}
			try {
				if (bdInt != null) {
					bdInt.cerrar();
					logger.debug("getInfoTraspasos: CONENECTION_SNS CLOSE_INTERCAM");
				}
			} catch (Exception e3) {
				bdInt = null;
			}
		}

	}

}
