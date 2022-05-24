package sns.busqueda.model;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import gasai.util.Misc;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class BusquedaAproximadaHelper{
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public BusquedaAproxResultadoBean compararDatos (ConsultaBean consultaBean, ConsultaBean consultaBeanComparar) throws Exception {
		//
		BusquedaAproxResultadoBean busquedaAproxResultadoBean = null;
		//
		try{
			busquedaAproxResultadoBean = this.obtenerAproximacion(consultaBean, consultaBeanComparar);
			this.compararDatosSegundoNivel(consultaBean, consultaBeanComparar, busquedaAproxResultadoBean);
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return busquedaAproxResultadoBean;
	}
	
	
	/**
	 * 
	 *  Los camopos son iguales si coinciden, si son vacios son diferentes
	 * 
	 * 
	 * @param consultaBean
	 * @param consultaBeanComparar
	 * @return
	 * @throws Exception
	 */
	public void compararDatosSegundoNivel (ConsultaBean consultaBean, ConsultaBean consultaBeanComparar, BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
		//
		ArrayList <String> arrayList = consultaBeanComparar.getArrayListNafSec();
		//
		String dniNie            = consultaBean.getDniNie();
		String pasaporte         = consultaBean.getPasaporte();
		String naf               = consultaBean.getNaf();
		//
		String dniNieComparar    = consultaBeanComparar.getDniNie();
		String pasaporteComparar = consultaBeanComparar.getPasaporte();
		String nafComparar       = consultaBeanComparar.getNaf();
		//
		String criterioCompleto = "";
		//
		try{
			boolean dniEncontrado = true;
			// Si los dos se toman como Iguales si no son vacios
			if(!Misc.esVacio(dniNie) && !Misc.esVacio(dniNieComparar)){
				if(dniNie.equals(dniNieComparar)) {
					criterioCompleto += ConstantesBusqueda.CADENA_DNI_NIE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				}
			}
			if(!dniEncontrado && !Misc.esVacio(pasaporte) && !Misc.esVacio(pasaporteComparar)){
				if(pasaporte.equals(pasaporteComparar)) {
					criterioCompleto += ConstantesBusqueda.CADENA_PASAPORTE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				}
			}
			if(!Misc.esVacio(naf) && !Misc.esVacio(nafComparar)){
				if(naf.equals(nafComparar)) {
					criterioCompleto += ConstantesBusqueda.CADENA_NAF + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				}
			}
			if(arrayList !=null && arrayList.size()>0 && !Misc.esVacio(naf)){
				//logger.error("naf " + naf);
				for(int i=0 ; i<arrayList.size() ; i++) {
					String nafSec = arrayList.get(i);
					//logger.error("nafSec " + nafSec);
					if(naf.equals(nafSec)) {
						criterioCompleto += ConstantesBusqueda.CADENA_NAF_SEC + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
					}
				}
				//logger.error("criterioCompleto: " + criterioCompleto);
			}
			//
			criterioCompleto += busquedaAproxResultadoBean.getCriterio();
			busquedaAproxResultadoBean.setCriterioCompleto(criterioCompleto);
			//
			this.obtenerPorcentajeCompleto (busquedaAproxResultadoBean);
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	public void obtenerPorcentajeCompleto (BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
		//
		int camposCoincidentesCompleto = 0; 
		//
		try{
			//
			String criterioCompleto = Misc.nz(busquedaAproxResultadoBean.getCriterioCompleto());
			int porcentaje          = busquedaAproxResultadoBean.getPorcentaje();
			double mult             = 0.4;
			double porcent          = porcentaje * mult;
			porcentaje              = (int)porcent;
			int porcentajeCompleto  = 0; 
			//
			if(criterioCompleto.indexOf(ConstantesBusqueda.VALOR_BUSQUEDA_DNI)!=-1 || criterioCompleto.indexOf(ConstantesBusqueda.VALOR_BUSQUEDA_PASAPORTE)!=-1){
				porcentajeCompleto += 30; 
				camposCoincidentesCompleto++;
			}
			if(criterioCompleto.indexOf(ConstantesBusqueda.VALOR_BUSQUEDA_NAF)!=-1){
				porcentajeCompleto += 30;
				camposCoincidentesCompleto++;
			}		
			//
			porcentajeCompleto += porcentaje;
			busquedaAproxResultadoBean.setPorcentajeCompleto(porcentajeCompleto);
			busquedaAproxResultadoBean.setCamposCoincidentesCompleto(camposCoincidentesCompleto);
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}

	}	
	
	
	public BusquedaAproxResultadoBean obtenerAproximacion (ConsultaBean consultaBean, ConsultaBean consultaBeanComparar) throws Exception {
		
		String nombre             = consultaBean.getNombre();
		String nombreComparar     = consultaBeanComparar.getNombre();
		String apellido1	      = consultaBean.getApellido1();
		String apellido1Comparar  = consultaBeanComparar.getApellido1();
		String apellido2          = consultaBean.getApellido2();
		String apellido2Comparar  = consultaBeanComparar.getApellido2();
		String codSexo            = consultaBean.getCodSexo();
		String codSexoComparar    = consultaBeanComparar.getCodSexo();
		String fechaNac           = consultaBean.getFechaNac();
		String fechaNacComparar   = consultaBeanComparar.getFechaNac();
		//
		boolean nombreIgual       = false;
		boolean apellido1Igual    = false;
		boolean apellido2Igual    = false;
		boolean codSexoIgual      = false;
		boolean fechaNacIgual     = false;
		//
		boolean borrarLetraN      = false;
		//
		boolean generoNombreIgual = false;
		//
		String criterio                  = "";
		String diferencia                = "";
		int camposCoincidentes           = 0;
		String camposIgualesAproximacion = "";
		int porcentaje                   = 0;
		//
		BusquedaAproxResultadoBean busquedaAproxResultadoBean = null;	
		NombreComparacionBean nombreComparacionBean           = new NombreComparacionBean ();
		//
		try {
			// logger.debug("INICIO");
			//
			busquedaAproxResultadoBean = new BusquedaAproxResultadoBean();
			//
			// Se comprueba si directamente son iguales, sino lo son se sustituyen las subcadenas
			if (nombre.equals(nombreComparar)) {
				nombreIgual       = true;
				porcentaje        = InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
				//
				// Si el nombre es Igual el Sexo es Igual
				codSexoIgual      = true;
				porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
			}
			//
			//
			if (apellido1.equals(apellido1Comparar)) {
				apellido1Igual    = true;
				porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
			}
			else{
				// Se comprueba si las cadenas tienen Ñ, y como tendra que ser su tratamiento
				borrarLetraN      = comprobarLetraN(apellido1, apellido1Comparar);
				//		
				apellido1         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido1), borrarLetraN);
				apellido1Comparar = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido1Comparar), borrarLetraN);
			}	
			//
			// Apellido2 relleno e Igual
			if (!Misc.esVacio(apellido2) && apellido2.equals(apellido2Comparar)) {
				apellido2Igual    = true;
				porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
			}
			else{
				// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
				borrarLetraN      = comprobarLetraN(apellido2, apellido2Comparar);
				//				
				apellido2         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido2), borrarLetraN);
				apellido2Comparar = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido2Comparar), borrarLetraN);
				//
				// 
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Si los apellidos despues de quitar espacios, X y demas, son vacios, los apellidos son iguales, esto es:
				// 
				// Apellido2_1: XXXXXX    |    Apellido2_1: XXXXXX
				// Apellido2_2:           |    Apellido2_2: XXX
				//
				//if(Misc.esVacio(apellido2) && Misc.esVacio(apellido2Comparar)) {
				//	apellido2Igual    = true;
				//	porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
				//}
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
			if (!nombreIgual && codSexo.equals(codSexoComparar)) {
				codSexoIgual = true;
				porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
			}
			if (fechaNac.equals(fechaNacComparar)) {
				fechaNacIgual = true;
				porcentaje   += InicializacionBusqueda.VALOR_CAMPO_IGUAL_CAMPO;
			}
			//
			//
			// poner condicion de q pueda ser aproximado
			if (!Misc.esVacio(InicializacionBusqueda.BUSQUEDA_APROXIMADA_NOMBRE)){
				//											
				// INCLUSION del nombre  en el otro, con valores en los NOMBRE originales
				//if (!nombreIgual && !Misc.esVacio(nombre) && !Misc.esVacio(nombreComparar)) {
				//	if (nombre.indexOf(nombreComparar) != -1 || nombreComparar.indexOf(nombre) != -1) {
				//		nombreIgual   = true;
				//		porcentaje   += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
				//		camposIgualesAproximacion += ConstantesBusqueda.CADENA_NOMBRE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				//	}
				//}
				if (!nombreIgual) {
					//
					// Se comprueba que los NOMBRES no sean de generos diferentes
					generoNombreIgual = this.esIgualGenero(nombre, nombreComparar);
					//
					// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
					borrarLetraN      = comprobarLetraN(nombre, nombreComparar);
					//
					nombre            = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombre), borrarLetraN);
					nombreComparar    = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombreComparar), borrarLetraN);
				}
				//
				// Se vuelve a comparar APROXIMADO, sino se mira la inclusion
				if (generoNombreIgual && !nombreIgual){
					//
					// Sino hay cambio de genero se comprueba la aproximacion				
					//
					if(nombre.equals(nombreComparar)) {
						nombreIgual                = true;
						porcentaje                += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
						camposIgualesAproximacion += ConstantesBusqueda.CADENA_NOMBRE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
					}
					//
					if (!nombreIgual){									
						//
						// Si el NOMBRE no es IGUAL ni Aproximado, se raliza un estudio del NOMBRE
						boolean estudioNombre = false;
						//
						//logger.debug("-- -- -- revisionNombre:  " +  getRevisionNombre());
						//					
						if(!Misc.esVacio(InicializacionBusqueda.REVISION_DEL_NOMBRE) && codSexoIgual){
							//
							NombreHelper nombreHelper = new NombreHelper ();						
							nombreComparacionBean = nombreHelper.managerNombre(consultaBean.getNombre(), consultaBeanComparar.getNombre());	
							busquedaAproxResultadoBean.setNombreComparacionBean(nombreComparacionBean);
							//
							if(    nombreComparacionBean.getRevisionInt() == ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS
							    || nombreComparacionBean.getRevisionInt() == ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_VACIOS 	){
								//
								estudioNombre = true;
							}
						}
						//					
						if(estudioNombre) {				
							nombreIgual                = true;		
							porcentaje                += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_ESTUDIO_NOMBRE;
							camposIgualesAproximacion += ConstantesBusqueda.CADENA_NOMBRE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
						}	
					}
					//
				}
			}
			if (!apellido1Igual) {
				if (apellido1.equals(apellido1Comparar)) {
					apellido1Igual = true;
					porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
					camposIgualesAproximacion += ConstantesBusqueda.CADENA_APELLIDO1 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				}
			}
			//	
			// El Apellido2 se comprueba despues, pq es necesario conocer si la fecha de Nacimiento es Igual, para Apellidos2 vacios
			//
			// Se comprueba la fecha aproximada
			boolean esFechaAproximada = false;
			if(!fechaNacIgual && !Misc.esVacio(InicializacionBusqueda.BUSQUEDA_APROXIMADA_FECHA)) {
					fechaNacIgual = BusquedaAproximadaUtil.comprobarFecha(fechaNac, fechaNacComparar);
					if(fechaNacIgual) {
						esFechaAproximada = true;
						porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_FECHA;
						//camposIgualesAproximacion += ConstantesBusqueda.CADENA_FECHA_NAC + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
					}	
			}
			// Se comprueba el Sexo aproximado
			boolean esCodSexoAproximado = false;
			if (!codSexoIgual && !Misc.esVacio(InicializacionBusqueda.BUSQUEDA_APROXIMADA_SEXO)) {
				if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && nombreIgual) {
					codSexoIgual = true;
					esCodSexoAproximado = true;
					porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO_SEXO;
					//camposIgualesAproximacion += ConstantesBusqueda.CADENA_COD_SEXO + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				}
			}
			//
			//
			//if (!apellido2Igual) {
			//	if (apellido2.equals(apellido2Comparar)) {
			//		apellido2Igual = true;
			//		porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
			//		camposIgualesAproximacion += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			//	}
			//}				
			//
			if (!apellido2Igual) {	
				//								
				//logger.debug("apellido2:         [" + apellido2 + "], apellido2Comparar: [" + apellido2Comparar + "]");
				//
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// Si los apellidos despues de quitar espacios, X y demas, son vacios, los apellidos son iguales, esto es:
				// 
				// Apellido2_1: XXXXXX    |    Apellido2_1: XXXXXX
				// Apellido2_2:           |    Apellido2_2: XXXX
				//
				if(Misc.esVacio(apellido2) && Misc.esVacio(apellido2Comparar)) {				
					// segun el properties se revisa o no que para que los apellidos2 
					// sean vacios e iguales la FECHA_NAC y el NOMBRE deben de ser iguales
					//
					if(!Misc.esVacio(InicializacionBusqueda.APELLIDO2_REVISAR)) {
						if((fechaNacIgual || esFechaAproximada)  && nombreIgual && apellido1Igual) {
							apellido2Igual    = true;
							porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;	
							camposIgualesAproximacion += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
							//logger.debug("Apellido2 validacion vacios");
						}
						//else{
							//logger.debug("fecha, nombre o apellido1 son diferentes");
						//}
					}
					else {					
						apellido2Igual    = true;
						porcentaje       += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;	
						camposIgualesAproximacion += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
						//logger.debug("Sin revision del Apellido2");
					}					
				}
				else {
					//
					// Los apellido2 no son vacios pueden ser aproximados
					//
					if (!apellido2Igual) {				
						if (apellido2.equals(apellido2Comparar)) {
							apellido2Igual = true;
							porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;	
							camposIgualesAproximacion += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
						}
					}
				}
			}	
			//
			//
			if(esFechaAproximada) {
				camposIgualesAproximacion += ConstantesBusqueda.CADENA_FECHA_NAC + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}			
			if(esCodSexoAproximado) {
				camposIgualesAproximacion += ConstantesBusqueda.CADENA_COD_SEXO + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			////////////////   FIN COMPROBAR APROXIMACION
			//
			// 
			//
			//
			//
			if (nombreIgual) {
				criterio += ConstantesBusqueda.CADENA_NOMBRE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCoincidentes++;
			}
			else {
				diferencia += ConstantesBusqueda.CADENA_NOMBRE + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			if (apellido1Igual) {
				criterio += ConstantesBusqueda.CADENA_APELLIDO1 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCoincidentes++;
			}
			else {
				diferencia += ConstantesBusqueda.CADENA_APELLIDO1 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			if (apellido2Igual) {
				criterio += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCoincidentes++;
			}
			else {
				diferencia += ConstantesBusqueda.CADENA_APELLIDO2 + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			if (codSexoIgual) {
				criterio += ConstantesBusqueda.CADENA_COD_SEXO + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCoincidentes++;
			}
			else {
				diferencia += ConstantesBusqueda.CADENA_COD_SEXO + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			if (fechaNacIgual) {
				criterio+= ConstantesBusqueda.CADENA_FECHA_NAC + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCoincidentes++;
			}
			else {
				diferencia += ConstantesBusqueda.CADENA_FECHA_NAC + ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO;
			}
			//
			busquedaAproxResultadoBean.setCriterio(criterio);
			busquedaAproxResultadoBean.setDiferencia(diferencia);
			busquedaAproxResultadoBean.setCamposCoincidentes(camposCoincidentes);
			busquedaAproxResultadoBean.setCamposIgualesAproximacion(camposIgualesAproximacion);
			busquedaAproxResultadoBean.setPorcentaje(porcentaje);
			//
			//logger.debug("apellido1:                 " + apellido1);
			//logger.debug("apellido1Comparar:         " + apellido1Comparar);
			//logger.debug("criterio:                  " + criterio);
			//logger.debug("camposCoincidentes:        " + camposCoincidentes);
			//logger.debug("diferencia:                " + diferencia);
			//logger.debug("camposIgualesAproximacion: " + camposIgualesAproximacion);
			//logger.debug("porcentaje:                " + porcentaje);
			//
			//
			//	
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// A partir de los campos coincidentes y del atributo con los campos minimos obtenemos, si es la misma persona
			boolean esMismaPersona = this.esMismaPersona(busquedaAproxResultadoBean);
			busquedaAproxResultadoBean.setEsMismaPersona(esMismaPersona);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//
			/*	
			logger.debug(consultaBean.write());
			logger.debug(consultaBeanComparar.write());
			logger.debug(busquedaAproxResultadoBean.toString2());
			logger.debug(busquedaAproxResultadoBean.getNombreComparacionBean().write());
			*/
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return busquedaAproxResultadoBean;
	}
		
	
	public static boolean comprobarLetraN (String cadena, String cadenaComparar) throws Exception {
		//
		boolean borrarLetraN = false;
		//
		try{
			//
			// Es el caso en el que una de las dos cadenas contiene una Ñ
			//
			if(cadena.indexOf(ConstantesBusqueda.LETRA_ENIE)!=-1 || cadenaComparar.indexOf(ConstantesBusqueda.LETRA_ENIE)!=-1){
				//
				if(!cadena.equals(cadenaComparar)) {
					//logger.error("diferentes");
					//
					if(cadena.length() ==  cadenaComparar.length()) {
						//logger.error("Mismo ancho");
						//
						borrarLetraN = true;
					}
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return borrarLetraN;
	}	
	
	
	public static boolean esAproximadaCadena(String cadena, String cadenaComparar, String campo) throws Exception {
		//
		boolean cadenaIgual  = false;
		boolean borrarLetraN = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
			borrarLetraN = comprobarLetraN(cadena, cadenaComparar);
			//
			if(!Misc.esVacio(cadena)) {
				cadena         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(cadena), borrarLetraN);
			}
			if(!Misc.esVacio(cadenaComparar)) {
				cadenaComparar = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(cadenaComparar), borrarLetraN);
			}
			//
			// Se compara APROXIMADO, sino se mira la inclusion
			//
			if (cadena.equals(cadenaComparar)) {
				cadenaIgual = true;
			}
			else {
				// INCLUSION del nombre 
				if (!cadenaIgual && !Misc.esVacio(cadena) && !Misc.esVacio(cadenaComparar) && Misc.nz(campo).equals(ConstantesBusqueda.CADENA_NOMBRE_COMPARACION) ) {
					//
					if (cadena.indexOf(cadenaComparar) != -1 || cadenaComparar.indexOf(cadena) != -1) {
						cadenaIgual = true;
					}
				}
			}
			//
			logger.error("cadena: " + cadena + ", cadenaComparar: " + cadenaComparar + ", borrarLetraN:  " + borrarLetraN);
			//
			// logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return cadenaIgual;
	}
	
	
	public static boolean esAproximadoSexo(String codSexo, String codSexoComparar, String cadena, String cadenaComparar) throws Exception {
		//
		boolean codSexoIgual   = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba si el nombre es igual
			boolean esNombreIgual = esAproximadaCadena(cadena, cadenaComparar, ConstantesBusqueda.CADENA_NOMBRE_COMPARACION);
			//
			// Se comprueba el Sexo aproximado
			if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && esNombreIgual) {
					codSexoIgual = true;
			} // Se podria quitar el else
			else {
				if (codSexo.equals(codSexoComparar)) {
					codSexoIgual = true;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return codSexoIgual;
	}
	
	
	public static boolean esAproximadoSexo(String codSexo, String codSexoComparar, boolean esNombreIgual) throws Exception {
		//
		boolean codSexoIgual   = false;
		//
		try {
			// logger.debug("INICIO");
			//
			// Se comprueba el Sexo aproximado
			if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && esNombreIgual) {
					codSexoIgual = true;
			} // Se podria quitar el else
			else {
				if (codSexo.equals(codSexoComparar)) {
					codSexoIgual = true;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return codSexoIgual;
	}
	
	
	public static boolean esIgualGenero(String cadena, String cadenaComparar) throws Exception {
		//
		boolean esIgualGenero  = true;
		//
		try {
			// logger.debug("INICIO");
			//
			//
			String cadena_1         = cadena;
			String cadenaComparar_1 = cadenaComparar; 
			//
			// Solo se puede obtener el genero mirando si la palabra es simple, no es compuesta, es decir es una sola palabra
			// si es la misma palabra
			// 
			// Si una de las cadenas es vacia se descarta continuar
			//
			if(!Misc.esVacio(cadena_1) && !Misc.esVacio(cadenaComparar_1)) {
				//
				// Si hay espacios en blanco es que la palabara no es simple
				//
				if (cadena_1.indexOf(ConstantesBusqueda.ESPACIO_EN_BLANCO) == -1 && cadenaComparar_1.indexOf(ConstantesBusqueda.ESPACIO_EN_BLANCO) == -1) {
					// Se eliminan las XXXX y los ----
					if (cadena_1.startsWith(ConstantesBusqueda.LETRA_EQUIS)) {
						cadena_1 = BusquedaAproximadaUtil.quitarCadenaIgualEquis (cadena_1);
					}
					if (cadenaComparar_1.startsWith(ConstantesBusqueda.LETRA_EQUIS)) {
						cadenaComparar_1 = BusquedaAproximadaUtil.quitarCadenaIgualEquis (cadenaComparar_1);
					}
					//
					if (cadena_1.indexOf(".") != -1) {
						cadena_1 = Misc.remplazar(cadena_1, ".", "");
					}
					if (cadenaComparar_1.indexOf(".") != -1) {
						cadenaComparar_1 = Misc.remplazar(cadenaComparar_1, ".", "");
					}
					//
					if (cadena_1.indexOf("-") != -1) {
						cadena_1 = Misc.remplazar(cadena_1, "-", "");
					}
					if (cadenaComparar_1.indexOf("-") != -1) {
						cadenaComparar_1 = Misc.remplazar(cadenaComparar_1, "-", "");
					}
					//
					// Si una de las cadenas es vacia se descarta continuar
					//
					if(!Misc.esVacio(cadena_1) && !Misc.esVacio(cadenaComparar_1)) {
						//
						// Ya comparamos si la ultima letra es igual
						//
						String letraCadena         = cadena_1.substring(cadena_1.length()-1,cadena_1.length());		
						String letracadenaComparar = cadenaComparar_1.substring(cadenaComparar_1.length()-1,cadenaComparar_1.length());
						//
						if(!letraCadena.equals(letracadenaComparar)) {
							esIgualGenero  = false;
						}
					}
				}
				//if(!esIgualGenero){
				//	logger.debug("esIgualGenero: "+ esIgualGenero + ", cadena: " + cadena + ", cadenaComparar: " + cadenaComparar);
				//}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
		return esIgualGenero;
	}
	
	
	private boolean esMismaPersona (BusquedaAproxResultadoBean busquedaAproxResultadoBean) throws Exception {
		//
		boolean criterioEsMismaPersona = true;  
		//
		try {
			//logger.debug("INICIO");
			//
			if(busquedaAproxResultadoBean != null) {
				//
				String criterio        = Misc.nz(busquedaAproxResultadoBean.getCriterio());
				int camposCoincidentes = busquedaAproxResultadoBean.getCamposCoincidentes();
				int porcentaje         = busquedaAproxResultadoBean.getPorcentaje();
				//
				//public static String CAMPOS_OBLIGATORIOS                                 = "";
				//				
				if(camposCoincidentes < InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_NUMERO_CAMPOS) {
					criterioEsMismaPersona = false;
				}
				//
				if(!Misc.esVacio(InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE)) {
					if(porcentaje < InicializacionBusqueda.CRITERIO_IDENTIFICACION_IGUALDAD_PORCENTAJE) {	
						criterioEsMismaPersona = false;
					}
				}
				//
				if(Misc.esVacio(criterio) || criterio.indexOf(InicializacionBusqueda.CAMPOS_OBLIGATORIOS) == -1 ){
					criterioEsMismaPersona = false;
				}
			}
			else {
				criterioEsMismaPersona = false;
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return criterioEsMismaPersona;
	}
	

	
	
}
