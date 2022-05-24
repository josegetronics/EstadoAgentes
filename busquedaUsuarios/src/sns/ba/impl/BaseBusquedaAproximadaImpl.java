package sns.ba.impl;

import gasai.util.Misc;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;

import sns.ba.IBusquedaAproximada;
import sns.ba.IRespuestaBa;
import sns.model.IDatosIdentificativos;

import sns.ba.config.ConstantesBA;
import sns.ba.config.InicializacionBA;





public abstract class BaseBusquedaAproximadaImpl implements IBusquedaAproximada {

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(BaseBusquedaAvanzadaImpl.class);
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
		
	protected abstract Properties getConfiguracion();

	
	public IRespuestaBa comparar(IDatosIdentificativos personaActual, IDatosIdentificativos candidatoExistente, ArrayList<String> criterioEmparejamiento) throws Exception  {
		// 
		RespuestaBaImpl respuestaBaImpl = new RespuestaBaImpl ();
		//
		try {
			this.obtenerAproximacion (personaActual, candidatoExistente, respuestaBaImpl);
	        //
			this.compararDatosSegundoNivel(personaActual, candidatoExistente, respuestaBaImpl, criterioEmparejamiento);
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return respuestaBaImpl;
	}
	
	
	
	/**
	 * 
	 *  Los campos son iguales si coinciden, si son vacios son diferentes
	 * 
	 * 
	 * @param consultaBean
	 * @param consultaBeanComparar
	 * @return
	 * @throws Exception
	 */
	private void compararDatosSegundoNivel (IDatosIdentificativos personaActual, IDatosIdentificativos candidatoExistente, RespuestaBaImpl respuestaBaImpl, ArrayList<String> criterioEmparejamiento) throws Exception {
		//
		int camposCoincidentesCompleto                = 0;
		ArrayList<String> camposDiferentesCompleto    = new ArrayList<String> ();
		String criterioCompleto                       = "";
		ArrayList<String> camposCriterioCompleto      = new ArrayList<String> ();
		int porcentajeCompleto                        = 0;
		//
		try{
			//
			boolean encontradoDni      = false;
			boolean encontradoPasporte = false;
			boolean encontradoNaf      = false;
			//
			for(String cadenaCriterio:criterioEmparejamiento){

				if(!Misc.esVacio(cadenaCriterio)) {
					//
					boolean encontrado = false;
					//
					if(!encontradoDni && !encontradoPasporte  && cadenaCriterio.equals(ConstantesBA.CADENA_DNI_NIE)) {
						encontradoDni      = true;
						encontrado         = true;
					}
					if(!encontradoDni && !encontradoPasporte &&cadenaCriterio.equals(ConstantesBA.CADENA_PASAPORTE)) {
						encontradoPasporte = true;
						encontrado         = true;
					}
					if(!encontradoNaf && cadenaCriterio.indexOf(ConstantesBA.CADENA_NAF)!=-1) {
						encontradoNaf      = true;		
						encontrado         = true;
					}
					//
					if(encontrado) {
						camposCoincidentesCompleto++;
						criterioCompleto          += cadenaCriterio + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
						camposCriterioCompleto.add(cadenaCriterio);
						porcentajeCompleto        += 30; 	
					}
				}
			}
			// Diferntes, solo se indica el DNI o el NAF
			if(!encontradoDni && !encontradoPasporte) {
				camposDiferentesCompleto.add(ConstantesBA.CADENA_DNI_NIE);
			}
			if(!encontradoNaf) {
				camposDiferentesCompleto.add(ConstantesBA.CADENA_NAF);
			}
			// Criterio Completo
			criterioCompleto += respuestaBaImpl.getCriterio();
			//
			for(int i=0;i<respuestaBaImpl.getCamposCriterio().size();i++){
				String cadena = (String)respuestaBaImpl.getCamposCriterio().get(i);
				camposCriterioCompleto.add(cadena);
			}
			//
			// Porcentaje Completo
			porcentajeCompleto += (int) respuestaBaImpl.getPorcentaje() * ConstantesBA.PORCENTAJE_APROXIMADO_SOBRE_COMPLETO;
			//
			//
			//
			// Se rellena la respuesta de la BA
			//
			respuestaBaImpl.rellenarCamposCompleto (camposCoincidentesCompleto, camposDiferentesCompleto, criterioCompleto, camposCriterioCompleto, porcentajeCompleto);
			//
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
		
		
	private void obtenerAproximacion (IDatosIdentificativos personaActual, IDatosIdentificativos candidatoExistente, RespuestaBaImpl respuestaBaImpl) throws Exception {
		
		String nombre            = Misc.nz(personaActual.getNombre());
		String nombreComparar    = Misc.nz(candidatoExistente.getNombre());
		String apellido1	     = Misc.nz(personaActual.getApellido1());
		String apellido1Comparar = Misc.nz(candidatoExistente.getApellido1());
		String apellido2         = Misc.nz(personaActual.getApellido2());
		String apellido2Comparar = Misc.nz(candidatoExistente.getApellido2());
		String codSexo           = Misc.nz(personaActual.getCodSexo());
		String codSexoComparar   = Misc.nz(candidatoExistente.getCodSexo());
		String fechaNac          = "";
		String fechaNacComparar  = "";
		//
		boolean nombreIgual    = false;
		boolean apellido1Igual = false;
		boolean apellido2Igual = false;
		boolean codSexoIgual   = false;
		boolean fechaNacIgual  = false;
		//
		boolean borrarLetraN   = false;
		//
		// Respuesta a la BA //
		int camposCoincidentes                        = 0;
		ArrayList<String> camposDiferentes            = new ArrayList<String> ();
		ArrayList<String>  camposIgualesAproximacion  = new ArrayList<String> ();
		String criterio                               = "";
		ArrayList<String> camposCriterio              = new ArrayList<String> ();
		int porcentaje                                = 0;
		boolean esMismaPersona                        = false;
		//
		try {
			// logger.debug("INICIO");
			//
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantesBA.MASCARA_FECHA);
		    fechaNac         = Misc.nz(simpleDateFormat.format(personaActual.getFechaNac()));
		    fechaNacComparar = Misc.nz(simpleDateFormat.format(candidatoExistente.getFechaNac()));
			//
			// Se comprueba si directamente son iguales, sino lo son se sustituyen las subcadenas
			if (nombre.equals(nombreComparar)) {
				nombreIgual       = true;
				porcentaje        = getValorIgualCampo();
			}
			else{
				// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
				borrarLetraN      = BusquedaAproximadaUtil.comprobarLetraN(nombre, nombreComparar);
				//
				nombre            = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombre), borrarLetraN);
				nombreComparar    = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(nombreComparar), borrarLetraN);
			}
			//
			//
			if (apellido1.equals(apellido1Comparar)) {
				apellido1Igual    = true;
				porcentaje       += getValorIgualCampo();
			}
			else{
				// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
				borrarLetraN      = BusquedaAproximadaUtil.comprobarLetraN(apellido1, apellido1Comparar);
				//		
				apellido1         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido1), borrarLetraN);
				apellido1Comparar = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido1Comparar), borrarLetraN);
			}	
			//
			//
			if (apellido2.equals(apellido2Comparar)) {
				apellido2Igual    = true;
				porcentaje       += getValorIgualCampo();
			}
			else{
				// Se comprueba si las cadenas tienen tiene Ñ, y como tendra que ser su tratamiento
				borrarLetraN      = BusquedaAproximadaUtil.comprobarLetraN(apellido2, apellido2Comparar);
				//	
				apellido2         = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido2), borrarLetraN);
				apellido2Comparar = BusquedaAproximadaUtil.sustituirSubcadenas(Misc.nz(apellido2Comparar), borrarLetraN);
				//
				//if(Misc.esVacio(apellido2) && Misc.esVacio(apellido2Comparar)) {
				//	apellido2Igual    = true;
				//	porcentaje       += getValorIgualCampo();
				//}
			}
			if (codSexo.equals(codSexoComparar)) {
				codSexoIgual = true;
				porcentaje += getValorIgualCampo();
			}
			if (fechaNac.equals(fechaNacComparar)) {
				fechaNacIgual = true;
				porcentaje   += getValorIgualCampo();
			}
			//
			// Se vuelve a comparar APROXIMADO, sino se mira la inclusion
			if (!nombreIgual){
				if(nombre.equals(nombreComparar)) {
					nombreIgual = true;
					porcentaje += getValorAproximado();
				}
				else{
					// INCLUSION del nombre o de los apellidos en el otro
					if (!nombreIgual && !Misc.esVacio(nombre) && !Misc.esVacio(nombreComparar)) {
						if (nombre.indexOf(nombreComparar) != -1 || nombreComparar.indexOf(nombre) != -1) {
							nombreIgual = true;
							porcentaje += getValorAproximado();
						}
					}
				}
				if (nombreIgual){
					camposIgualesAproximacion.add(ConstantesBA.CADENA_NOMBRE);
				}
			}
			if (!apellido1Igual) {
				if (apellido1.equals(apellido1Comparar)) {
					apellido1Igual = true;
					porcentaje += getValorAproximado();
					camposIgualesAproximacion.add(ConstantesBA.CADENA_APELLIDO1);
				}
				//else{
				//	// INCLUSION
				//	if (!apellido1Igual && !Misc.esVacio(apellido1) && !Misc.esVacio(apellido1Comparar)) {
				//		if (apellido1.indexOf(apellido1Comparar) != -1 || apellido1Comparar.indexOf(apellido1) != -1) {
				//			apellido1Igual = true;
				//			porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
				//		}
				//	}
				//}
			}
			//
			if (!apellido2Igual) {
				if (apellido2.equals(apellido2Comparar)) {
					apellido2Igual = true;
					porcentaje += getValorAproximado();
					camposIgualesAproximacion.add(ConstantesBA.CADENA_APELLIDO2);
				}
				//else{
					//	// INCLUSION
					//if (!apellido2Igual && !Misc.esVacio(apellido2) && !Misc.esVacio(apellido2Comparar)) {
					//	if (apellido2.indexOf(apellido2Comparar) != -1 || apellido2Comparar.indexOf(apellido2) != -1) {
					//		apellido2Igual = true;
					//		porcentaje += InicializacionBusqueda.VALOR_CAMPO_IGUAL_APROXIMADO;
					//	}
					//}
				//}
			}
			
			logger.debug("getBusquedaAproximadaConFecha(): " + getBusquedaAproximadaConFecha());
			
			// Se comprueba la fecha aproximada
			if(!fechaNacIgual && !Misc.esVacio(getBusquedaAproximadaConFecha())) {
					fechaNacIgual = comprobarFechaAproximada(fechaNac, fechaNacComparar);
					if(fechaNacIgual) {
						porcentaje += getValorAproximadoFecha();
						camposIgualesAproximacion.add(ConstantesBA.CADENA_FECHA_NAC);
					}	
			}
			// Se comprueba el Sexo aproximado
			if (!codSexoIgual && !Misc.esVacio(getBusquedaAproximadaConSexo())) {
				if (!Misc.esVacio(codSexo) && !Misc.esVacio(codSexoComparar) && nombreIgual) {
					codSexoIgual = true;
					porcentaje += getValorAproximadoSexo();
					camposIgualesAproximacion.add(ConstantesBA.CADENA_COD_SEXO);
				}
			}
			//
			//
			if (nombreIgual) {
				criterio += ConstantesBA.CADENA_NOMBRE + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCriterio.add(ConstantesBA.CADENA_NOMBRE);
				camposCoincidentes++;
			}
			else {
				camposDiferentes.add(ConstantesBA.CADENA_NOMBRE);
			}
			if (apellido1Igual) {
				criterio += ConstantesBA.CADENA_APELLIDO1 + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCriterio.add(ConstantesBA.CADENA_APELLIDO1);
				camposCoincidentes++;
			}
			else {
				camposDiferentes.add(ConstantesBA.CADENA_APELLIDO1);
			}
			if (apellido2Igual) {
				criterio += ConstantesBA.CADENA_APELLIDO2 + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCriterio.add(ConstantesBA.CADENA_APELLIDO2);
				camposCoincidentes++;
			}
			else {
				camposDiferentes.add(ConstantesBA.CADENA_APELLIDO2);
			}
			if (codSexoIgual) {
				criterio += ConstantesBA.CADENA_COD_SEXO + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCriterio.add(ConstantesBA.CADENA_COD_SEXO);
				camposCoincidentes++;
			}
			else {
				camposDiferentes.add(ConstantesBA.CADENA_COD_SEXO);
			}
			if (fechaNacIgual) {
				criterio+= ConstantesBA.CADENA_FECHA_NAC + ConstantesBA.SEPARADOR_CAMPOS_ATRIBUTO;
				camposCriterio.add(ConstantesBA.CADENA_FECHA_NAC);
				camposCoincidentes++;
			}
			else {
				camposDiferentes.add(ConstantesBA.CADENA_FECHA_NAC);
			}
			//
			// A partir de los campos coincidentes y del atributo con los campos minimos obtenemos, si es la misma persona
			if(camposCoincidentes >= getCriterioNumeroCamposIgual()) {
				esMismaPersona = true;
			}
			//
			// Se rellena la respuesta de la BA
			respuestaBaImpl.rellenarCampos (camposCoincidentes, camposDiferentes, camposIgualesAproximacion, criterio, camposCriterio, porcentaje, esMismaPersona);
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage(), e);
			throw e;
		}
	}
		
	
	private boolean comprobarFechaAproximada (String fechaNac, String fechaNacComprobar) throws Exception {
		//
		boolean iguales = false;
		//
		try {
			//logger.debug("INICIO");
			//
			if(!Misc.esVacio(fechaNac) && !Misc.esVacio(fechaNacComprobar) && fechaNac.length()>9 && fechaNacComprobar.length()>9) {
				//
				int dia    = Integer.parseInt(Misc.nz(fechaNac.substring(8, 10)));
				int diaTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(8, 10)));
				//
				int mes    = Integer.parseInt(Misc.nz(fechaNac.substring(5, 7)));
				int mesTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(5, 7)));
				//
				int año    = Integer.parseInt(Misc.nz(fechaNac.substring(0, 4)));
				int añoTes = Integer.parseInt(Misc.nz(fechaNacComprobar.substring(0, 4)));
				//
				//logger.debug("fechaNac:             " + fechaNac);
				//logger.debug("dia:    " + dia    + ", mes:    " + mes    + ", año:    " + año);
				//logger.debug("fechaNacComprobar:    " + fechaNacComprobar);
				//logger.debug("diaTes: " + diaTes + ", mesTes: " + mesTes + ", añoTes: " + añoTes);
				//
				int diferenciaDias = 0;
				int diferenciaMes  = 0; 
				int diferenciaAño  = 0;
				//
				if(dia>diaTes) {
					diferenciaDias = dia-diaTes;
				}
				else {
					diferenciaDias = diaTes-dia;
				}
				//
				if(mes>mesTes) {
					diferenciaMes = mes-mesTes;
				}
				else {
					diferenciaMes = mesTes-mes;
				}
				//
				if(mes>mesTes) {
					diferenciaAño = año-añoTes;
				}
				else {
					diferenciaAño = añoTes-año;
				}
				//
				//
				if(diferenciaAño < getNumeroMaximoAniosFecha() &&  mes==mesTes      &&  dia==diaTes) {
					return true;
				}
				if(año==añoTes      &&  diferenciaMes < getNumeroMaximoMesesFecha()  &&  dia==diaTes) {
					return true;
				}
				if (año==añoTes     &&  mes==mesTes      &&  diferenciaDias < getNumeroMaximoDiasFecha()) {
					return true;
				}	
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
		return iguales;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////

	
	private String getBusquedaAproximadaConSexo() {
		return getConfiguracion().getProperty("busquedaAproximadaConSexo");
	}

	private String getBusquedaAproximadaConFecha() {
		return Misc.nz(getConfiguracion().getProperty("busquedaAproximadaConFecha"));
	}
	
	private int getNumeroMaximoDiasFecha() {
		if(Misc.esDigito(getConfiguracion().getProperty("numeroMaximoDias"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("numeroMaximoDias")));
		}
		return 0;
	}

	private int getNumeroMaximoMesesFecha() {
		if(Misc.esDigito(getConfiguracion().getProperty("numeroMaximoMeses"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("numeroMaximoMeses")));
		}
		return 0;
	}
	
	private int getNumeroMaximoAniosFecha() {
		if(Misc.esDigito(getConfiguracion().getProperty("numeroMaximoAnios"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("numeroMaximoAnios")));
		}
		return 0;
	}
	
	private int getValorIgualCampo() {
		if(Misc.esDigito(getConfiguracion().getProperty("igualCampo"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("igualCampo")));
		}
		return 0;
	}
	
	private int getValorAproximado() {
		if(Misc.esDigito(getConfiguracion().getProperty("igualAproximado"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("igualAproximado")));
		}
		return 0;
	}
	
	private int getValorAproximadoFecha() {
		if(Misc.esDigito(getConfiguracion().getProperty("igualAproximadoFecha"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("igualAproximadoFecha")));
		}
		return 0;
	}
	
	private int getValorAproximadoSexo() {
		if(Misc.esDigito(getConfiguracion().getProperty("igualAproximadoSexo"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("igualAproximadoSexo")));
		}
		return 0;
	}
	
	
	private int getCriterioNumeroCamposIgual() {
		if(Misc.esDigito(getConfiguracion().getProperty("criterioIdentificacionIgualdadNumeroCamposIgual"))){
			return Integer.parseInt(Misc.nz(getConfiguracion().getProperty("criterioIdentificacionIgualdadNumeroCamposIgual")));
		}
		return 0;
	}
	


	
}
