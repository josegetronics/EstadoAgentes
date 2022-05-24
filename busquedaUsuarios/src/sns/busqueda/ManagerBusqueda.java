package sns.busqueda;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import sns.busqueda.model.BusquedaHelperOtros;
import sns.busqueda.model.BusquedaHelperSns;
import sns.busqueda.model.BusquedaHelperSnsNoElMismo;
import sns.comun.config.InicializacionBusqueda;


public class ManagerBusqueda {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public static void main(String[] args) {
		//
		try {
			logger.debug("INICIO");
			//
			String path            = InicializacionBusqueda.PATH_FICHEROS;
			String ficheroOrigen   = InicializacionBusqueda.FICHEROS_ORIGEN;
			String ficheroDestino  = InicializacionBusqueda.FICHEROS_DESTINO;
			String codCaIsoOrigen  = InicializacionBusqueda.COD_CA_ISO;
			//
			//			
			BusquedaHelperSns busquedaHelperSns   = new BusquedaHelperSns ();
			busquedaHelperSns.manager(path, ficheroOrigen, ficheroDestino, codCaIsoOrigen);
			//
			// Para si 
			//BusquedaHelperSnsNoElMismo busquedaHelperSnsNoElMismo   = new BusquedaHelperSnsNoElMismo ();
			//busquedaHelperSnsNoElMismo.manager(path, ficheroOrigen, ficheroDestino, codCaIsoOrigen);			
			//
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////
			// 
			// Busqueda Aproximada de un fichero del INSS
			// La informacion de esntrada del proceso es por Query
			// 
			//BusquedaHelperOtros busquedaHelperOtros = new BusquedaHelperOtros ();
			//busquedaHelperOtros.manager(path, ficheroDestino, codCaIsoOrigen); 
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////		
			//
			//			
			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//java.util.Date date = new java.util.Date();
			//date = new java.util.Date(date.getTime());
			//System.out.println(simpleDateFormat.format(date));			
			//
			logger.debug("FIN"); 
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	
	
	
	/*
	String nombre            = "XXXX";
	String nombreComparar    = "";
	boolean borrarLetraN     = false; 
	boolean nombreIgual      = false;
	//
	logger.debug("nombre:         " + nombre);
	logger.debug("nombreComparar: " + nombreComparar);
	//
	// Se comprueba si directamente son iguales, sino lo son se sustituyen las subcadenas
	if (nombre.equals(nombreComparar)) {
		logger.debug("Igulaes");
	}
	else{
		nombre            = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombre), borrarLetraN);
		nombreComparar    = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombreComparar), borrarLetraN);
		//
		if(nombre.equals(nombreComparar)) {
			logger.debug("Igulaes aproximados");
		}
		else {
			logger.debug("Diferentes");
		}
	}
	logger.debug("nombre:         " + nombre);
	logger.debug("nombreComparar: " + nombreComparar);
	*/
	
	
	/*
			AccesoBdIndef accesoBdIndef = new AccesoBdIndef ();
			//
			IndefBdHelper indefBdHelper = new IndefBdHelper ();
			boolean b =  indefBdHelper.getUsuarioInDefByDni (accesoBdIndef, "16195996F");
			logger.debug("b: " + b);
			//
			accesoBdIndef.cerrar();

	String nombreCampo    = "apellido1"; 
	boolean cambio        = true;
	boolean campoBusqueda = true;
	//		
	BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
	//
	int numeroCambios = 0;
	//
	String valorAnterior = "ANGELES";
	String valorNuevo    = "MARIA ANGELES";
	// valorAnterior: [M ANGELES], valorSolicitud: [MARIA ANGELES]
    //Si hay cambio
    if(cambio){
         //Comprobamos si viene nombre, apellido1 y apellido2 para aplicar la busqueda aproximada en esos casos
         if (   Misc.nz(nombreCampo).equals("nombre") 
             || Misc.nz(nombreCampo).equals("apellido1") 
             || Misc.nz(nombreCampo).equals("apellido2")) {
               campoBusqueda = true;
         }
         //Si es campoBusqueda aplicamos la Busqueda aproximada
         if(campoBusqueda){
               boolean esAproximadaCadena = busquedaAproximadaHelper.esAproximadaCadena(Misc.nz(valorAnterior), Misc.nz(valorNuevo), nombreCampo);
               if(esAproximadaCadena == false){
                     numeroCambios++;
               }     
               logger.debug("esAproximadaCadena: " + esAproximadaCadena);
         }
         else{
               if (!valorAnterior.equals(valorNuevo)) {
                     numeroCambios++;
               }
         }     
    }
    //
    logger.debug("numeroCambios: " + numeroCambios);
    */
	
	
}



