package sns.estadoagentes.model;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import gasai.util.out.Salida;
import gasai.xml.RecorreArbol;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.comun.bean.CambiosTraspasosBean;
import sns.comun.bean.InfoCambiosTraspasosBean;
import sns.comun.config.Inicializacion;
import sns.comun.config.QueryEstadoAgentes;

public abstract class Info {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	public abstract StringBuffer viewInfoMapHorizontal(HashMap mapInformacion) throws Exception;

	public abstract CambiosTraspasosBean getInfoTraspasos(String path, int numCambiosVisualizar, AccesoBD bd);
	
	public abstract CambiosTraspasosBean getInfoModificaciones(String path, int numCambiosVisualizar, AccesoBD bd);

	public String getXml(String camposAfectados, String camposAnteriores, String valoresSolicitud) throws Exception {
		String xml = "";
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<traspasos>");
			stringBuffer.append("<camposAfectados>");
			stringBuffer.append(Misc.nz(camposAfectados));
			stringBuffer.append("</camposAfectados>");
			stringBuffer.append("<camposAnteriores>");
			stringBuffer.append(Misc.nz(camposAnteriores));
			stringBuffer.append("</camposAnteriores>");
			stringBuffer.append("<valorSolicitud>");
			stringBuffer.append(Misc.nz(valoresSolicitud));
			stringBuffer.append("</valorSolicitud>");
			stringBuffer.append("</traspasos>");
			//
			xml = stringBuffer.toString();
			//
			// logger.debug("getXml: " + xml);
		} catch (Exception e) {
			logger.error("getXml: Exception: " + e.getMessage(), e);
			throw e;
		}
		return xml;
	}
	
	public String getXml(String camposAfectados, String camposAnteriores, String valoresSolicitud, String valoresActuales) throws Exception {
		String xml = "";
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<traspasos>");
			stringBuffer.append("<camposAfectados>");
			stringBuffer.append(Misc.nz(camposAfectados));
			stringBuffer.append("</camposAfectados>");
			stringBuffer.append("<camposAnteriores>");
			stringBuffer.append(Misc.nz(camposAnteriores));
			stringBuffer.append("</camposAnteriores>");
			stringBuffer.append("<valorSolicitud>");
			stringBuffer.append(Misc.nz(valoresSolicitud));
			stringBuffer.append("</valorSolicitud>");
			stringBuffer.append("<valoresActuales>");
			stringBuffer.append(Misc.nz(valoresActuales));
			stringBuffer.append("</valoresActuales>");
			stringBuffer.append("</traspasos>");
			//
			xml = stringBuffer.toString();
			//
			// logger.debug("getXml: " + xml);
		} catch (Exception e) {
			logger.error("getXml: Exception: " + e.getMessage(), e);
			throw e;
		}
		return xml;
	}

	public String getXmlValoresActuales(ResultSet rs) throws Exception {
		String xml = "";
		try {
			StringBuffer stringBuffer = new StringBuffer();			
			stringBuffer.append("<nombre>");
			stringBuffer.append(Misc.nz(rs.getString("NOMBRE")));
			stringBuffer.append("</nombre>");
			
			stringBuffer.append("<apellido1>");
			stringBuffer.append(Misc.nz(rs.getString("APELLIDO1")));
			stringBuffer.append("</apellido1>");

			stringBuffer.append("<apellido2>");
			stringBuffer.append(Misc.nz(rs.getString("APELLIDO2")));
			stringBuffer.append("</apellido2>");

			stringBuffer.append("<fecha_nac>");
			stringBuffer.append(Misc.nz(rs.getString("FECHA_NAC")));
			stringBuffer.append("</fecha_nac>");

			stringBuffer.append("<sexo>");
			stringBuffer.append(Misc.nz(rs.getInt("SEXO")));
			stringBuffer.append("</sexo>");
			
			stringBuffer.append("<dni>");
			stringBuffer.append(Misc.nz(rs.getString("DNI_NIE")));
			stringBuffer.append("</dni>");
			
			stringBuffer.append("<naf>");
			stringBuffer.append(Misc.nz(rs.getString("NAF")));
			stringBuffer.append("</naf>");
			
			stringBuffer.append("<naf_titular>");
			stringBuffer.append(Misc.nz(rs.getString("NAF_TITULAR")));
			stringBuffer.append("</naf_titular>");
			
			xml = stringBuffer.toString();
			//
			// logger.debug("getXml: " + xml);
		} catch (Exception e) {
			logger.error("getXmlValoresActuales: Exception: " + e.getMessage(), e);
			throw e;
		}
		return xml;
	}
	
	public Salida getInfoXml(HashMap mapInformacion, String xml) {
		//
		// Numero de cambios para ese xml
		int numeroCambios = 0;
		//
		boolean cambio = true;
		//
		Salida salida = new Salida();

		try {
			RecorreArbol arbol = new RecorreArbol(xml);
			Properties xmlArb = arbol.recorrer();
			//
			//
			if (arbol.existeNodo("traspasos.camposAfectados") && arbol.existeNodo("traspasos.camposAnteriores") && arbol.existeNodo("traspasos.valorSolicitud")) {
				//
				// logger.debug("getInfoXml: *********************************");
				//
				// Se obtiene el numero de elementos afectados
				String indiceBusqueda = "";
				int contadorBusqueda = 0;
				while (arbol.existeNodo("traspasos.camposAfectados.campo" + indiceBusqueda)) {
					contadorBusqueda++;
					indiceBusqueda = Integer.toString(contadorBusqueda);
				}
				//
				// logger.debug("getInfoXml: indiceBusqueda: " + indiceBusqueda
				// + ", contadorBusqueda: " + contadorBusqueda);
				//
				for (int contador = 0; contador < contadorBusqueda; contador++) {
					//
					// Elemento afectado
					String elementoAfectado = "";
					// Valor campo anterior
					String valorAnterior = "";
					// Valor nuevo
					String valorSolicitud = "";
					cambio = true;

					// Para obtener el indice como cadena del elemento
					String indice = "";
					if (contador != 0) {
						indice = Integer.toString(contador);
					}
					// Se busca el campo afectado por el cambio
					if (arbol.existeNodo("traspasos.camposAfectados.campo" + indice)) {
						elementoAfectado = Misc.nz(xmlArb.get("traspasos.camposAfectados" + ".campo" + indice));
						// logger.debug("getInfoXml: indice: " + indice +
						// ", campo -> " +
						// xmlArb.get("traspasos.camposAfectados" + ".campo" +
						// indice));
						//
						// Si el campo pertenece a los campos a incluir en el
						// informe
						if (Inicializacion.MAP_CAMPOS.containsKey(elementoAfectado)) {
							//
							if (arbol.existeNodo("traspasos.camposAnteriores.valor" + indice)) {
								valorAnterior = Misc.nz(xmlArb.get("traspasos.camposAnteriores" + ".valor" + indice));
								// logger.debug("getInfoXml: indice: " + indice
								// + ", valor -> " +
								// xmlArb.get("traspasos.camposAnteriores" +
								// ".valor" + indice));
							}
							//
							if (arbol.existeNodo("traspasos.valorSolicitud.valor" + indice)) {
								valorSolicitud = Misc.nz(xmlArb.get("traspasos.valorSolicitud" + ".valor" + indice));
								// logger.debug("getInfoXml: indice: " + indice
								// + ", valor -> " +
								// xmlArb.get("traspasos.valorSolicitud" +
								// ".valor" + indice));
							}
							//
							// logger.debug("getInfoXml: elementoAfectado: " +
							// elementoAfectado + ", valorAnterior: " +
							// valorAnterior + ", valorSolicitud: " +
							// valorSolicitud);
							
							
							//REGLAS ANTIGUAS CAMBIADAS//
							/////////////////////////////
							// CONDICIONES DE CAMBIO 
							// valorAnterior relleno y valorSolicitud vacio
							// valorAnterior relleno y valorSolicitud relleno y
							// diferentes
							// if (!Misc.esVacio(valorAnterior)) {
							//if (!valorAnterior.equals(valorSolicitud)) {
								// logger.debug("getInfoXml:  -- CAMBIO --");
								// Se actualiza la estructura de datos global
								// con la informacion del cambio
								//numeroCambios++;
								//
							//}
							// }
							//
							//
							//ASI ESTABA//
							/////////////
							/*
							if (!valorAnterior.equals(valorSolicitud)) {
								numeroCambios++;
							}
							*/
							
							
							//NUEVAS REGLAS
							//Para los campos dni, naf y naf_titular
							//si el valor anterior es vacio y el valor solicitud no es vacio, no se considera cambio 
							//y el boolean cambio se pone a false
							if (Misc.nz(elementoAfectado).equals("dni") || Misc.nz(elementoAfectado).equals("naf") 
									|| Misc.nz(elementoAfectado).equals("naf_titular")) {
								if(Misc.nz(valorAnterior).equals("") && !Misc.nz(valorSolicitud).equals("")){
									cambio = false;
								}
							}
							//Si hay cambio
							if(cambio){
								if (!valorAnterior.equals(valorSolicitud)) {
									numeroCambios++;
								}
							}
							
							
							
							
						}
					}
				}
			}
			salida.setValor(new Integer(numeroCambios));
		} catch (Exception e) {
			logger.debug("getInfoXml: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			salida.setExcepcion(e);
			salida.setError(true);
			salida.setMsg(e.getMessage());
			logger.error("getInfoXml: Exception: " + e.getMessage(), e);
		}
		return salida;
	}
	
	
	public Salida getInfoXmlBusquedaAproximada(HashMap mapInformacion, String xml) {
		
		// Numero de cambios para ese xml
		int numeroCambios = 0;
		boolean campoBusqueda = false;
		boolean cambio = true;
		boolean nafBueno = true;
		//
		Salida salida = new Salida();

		try {
			RecorreArbol arbol = new RecorreArbol(xml);
			Properties xmlArb = arbol.recorrer();
			//
			if (arbol.existeNodo("traspasos.camposAfectados") && arbol.existeNodo("traspasos.camposAnteriores") && arbol.existeNodo("traspasos.valorSolicitud")) {
			
				// Se obtiene el numero de elementos afectados
				String indiceBusqueda = "";
				//Variables auxiliares para buscar el nombre con antelacion para aplicarlo cuando haya que comparar el sexo con la
				//busqueda aproximada
				String campoAfectadoAux = "";
				String nombreAnterior = "";
				String nombreSolicitud = "";
				
				int contadorBusqueda = 0;
				while (arbol.existeNodo("traspasos.camposAfectados.campo" + indiceBusqueda)) {
					campoAfectadoAux = Misc.nz(xmlArb.get("traspasos.camposAfectados" + ".campo" + contadorBusqueda));
					if (Misc.nz(campoAfectadoAux).equals("nombre")){
						nombreAnterior = Misc.nz(xmlArb.get("traspasos.camposAnteriores" + ".valor" + contadorBusqueda));
						nombreSolicitud = Misc.nz(xmlArb.get("traspasos.valorSolicitud" + ".valor" + contadorBusqueda));;
					}
					contadorBusqueda++;
					indiceBusqueda = Integer.toString(contadorBusqueda);
				}
				
				if (Misc.nz(nombreAnterior).equals("") && Misc.nz(nombreSolicitud).equals("")){
					nombreAnterior = nombreSolicitud = "NOMBRE";
				}
		
				//
				for (int contador = 0; contador < contadorBusqueda; contador++) {
					//
					// Elemento afectado
					String elementoAfectado = "";
					// Valor campo anterior
					String valorAnterior = "";
					// Valor nuevo
					String valorSolicitud = "";
					//
					campoBusqueda = false;
					cambio = true;
					nafBueno = true;

					// Para obtener el indice como cadena del elemento
					String indice = "";
					if (contador != 0) {
						indice = Integer.toString(contador);
					}
					// Se busca el campo afectado por el cambio
					if (arbol.existeNodo("traspasos.camposAfectados.campo" + indice)) {
						elementoAfectado = Misc.nz(xmlArb.get("traspasos.camposAfectados" + ".campo" + indice));
					
						// Si el campo pertenece a los campos a incluir en el
						// informe
						if (Inicializacion.MAP_CAMPOS.containsKey(elementoAfectado)) {
							//
							if (arbol.existeNodo("traspasos.camposAnteriores.valor" + indice)) {
								valorAnterior = Misc.nz(xmlArb.get("traspasos.camposAnteriores" + ".valor" + indice));
							}
							//
							if (arbol.existeNodo("traspasos.valorSolicitud.valor" + indice)) {
								valorSolicitud = Misc.nz(xmlArb.get("traspasos.valorSolicitud" + ".valor" + indice));
							}
							
							
							
							//COMO ESTABA ANTES
							/*
							//Si el valor anterior es vacio y el valor solicitud no es vacio, no se considera cambio 
							//y el boolean cambio se pone a false
							if(Misc.nz(valorAnterior).equals("") && !Misc.nz(valorSolicitud).equals("")){
								cambio = false;
							}
							*/
							
							
							
							
							//Para los campos dni, naf y naf_titular
							//si el valor anterior es vacio y el valor solicitud no es vacio, no se considera cambio 
							//y el boolean cambio se pone a false
							if (Misc.nz(elementoAfectado).equals("dni") || Misc.nz(elementoAfectado).equals("naf") 
									|| Misc.nz(elementoAfectado).equals("naf_titular")) {
								
								//Miramo si viene NAF o NAF Titular para evitar los NAF ficticios
								if(Misc.nz(elementoAfectado).equals("naf") || Misc.nz(elementoAfectado).equals("naf_titular")) {
									//miramos si el valor anterior no es vacio
									if(!Misc.nz(valorAnterior).equals("")){
										//Cogemos los dos primeros digitos del NAF
										String comienzoNaf = valorAnterior.substring(0,2); 
										//Parseamos a int para comprobar si es ficticio
										int comienzoNafInt = Integer.parseInt(comienzoNaf);
										//Si los dos primeros digitos del NAF estan entre 79 y 88 es ficticio por lo que el NAF no es bueno
										if(comienzoNafInt >= 79 && comienzoNafInt <= 88){
											System.out.println("comienzo NAF --> "+comienzoNafInt);
											nafBueno = false;
										}
									}
								}
								
								
								if(Misc.nz(valorAnterior).equals("") && !Misc.nz(valorSolicitud).equals("")){
									cambio = false;
								}
							}
							
							//Si hay cambio
							if(cambio && nafBueno){
								//Comprobamos si viene nombre, apellido1, apellido2 y sexo para aplicar la busqueda aproximada en esos casos
								if (Misc.nz(elementoAfectado).equals("nombre") || Misc.nz(elementoAfectado).equals("apellido1") 
										|| Misc.nz(elementoAfectado).equals("apellido2") || Misc.nz(elementoAfectado).equals("sexo")) {
									campoBusqueda = true;
								}
								//Si es campoBusqueda aplicamos la Busqueda aproximada
								if(campoBusqueda){
									if (Misc.nz(elementoAfectado).equals("nombre") || Misc.nz(elementoAfectado).equals("apellido1") 
											|| Misc.nz(elementoAfectado).equals("apellido2")){
										boolean esAproximadaCadena = BusquedaAproximadaHelper.esAproximadaCadena(valorAnterior, valorSolicitud, elementoAfectado);
										logger.debug("---------------%% valorAnterior: [" + valorAnterior + "], valorSolicitud: [" + valorSolicitud + "], esAproximadaCadena: " + esAproximadaCadena);
										if(esAproximadaCadena == false){
											numeroCambios++;
										}	
									}
									if (Misc.nz(elementoAfectado).equals("sexo")) {
										boolean esAproximadoSexo = BusquedaAproximadaHelper.esAproximadoSexo(valorAnterior, valorSolicitud, nombreAnterior, nombreSolicitud);
										if(esAproximadoSexo == false){
											numeroCambios++;
										}
									}
								}
								else{
									if (!valorAnterior.equals(valorSolicitud)) {
										numeroCambios++;
									}
								}	
							}
						}
					}
				}
			}
			salida.setValor(new Integer(numeroCambios));
		} catch (Exception e) {
			logger.debug("getInfoXmlBusquedaAproximada: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			salida.setExcepcion(e);
			salida.setError(true);
			salida.setMsg(e.getMessage());
			logger.error("getInfoXmlBusquedaAproximada: Exception: " + e.getMessage(), e);
		}
		return salida;
	}
	
	public HashMap getInfoXmlCambios(HashMap mapInformacion, String xml) {
		//HashMap con cambios
		HashMap hCambios = new HashMap();
		HashMap hOriginales = new HashMap();

		try {
			RecorreArbol arbol = new RecorreArbol(xml);
			Properties xmlArb = arbol.recorrer();
			//
			//
			if (arbol.existeNodo("traspasos.camposAfectados") && arbol.existeNodo("traspasos.camposAnteriores") 
					&& arbol.existeNodo("traspasos.valorSolicitud") && arbol.existeNodo("traspasos.valoresActuales")) {
			
				// Se obtiene el numero de elementos afectados
				String indiceBusqueda = "";
				//Variables auxiliares para buscar el nombre con antelacion para aplicarlo cuando haya que comparar el sexo con la
				//busqueda aproximada
				String campoAfectadoAux = "";
				String nombreAnterior = "";
				String nombreSolicitud = "";
				
				
				if (arbol.existeNodo("traspasos.valoresActuales.nombre")) {
					hOriginales.put("nombre", Misc.nz(xmlArb.get("traspasos.valoresActuales.nombre")));
				}
				if (arbol.existeNodo("traspasos.valoresActuales.apellido1")) {
					hOriginales.put("apellido1", Misc.nz(xmlArb.get("traspasos.valoresActuales.apellido1")));
				}
				if (arbol.existeNodo("traspasos.valoresActuales.apellido2")) {
					hOriginales.put("apellido2", Misc.nz(xmlArb.get("traspasos.valoresActuales.apellido2")));
				}
				if (arbol.existeNodo("traspasos.valoresActuales.fecha_nac")) {
					hOriginales.put("fecha_nac", Misc.nz(xmlArb.get("traspasos.valoresActuales.fecha_nac")));
				}
				if (arbol.existeNodo("traspasos.valoresActuales.sexo")) {
					hOriginales.put("sexo", Misc.nz(xmlArb.get("traspasos.valoresActuales.sexo")));
				}
				
				if (arbol.existeNodo("traspasos.valoresActuales.naf")) {
					hOriginales.put("naf", Misc.nz(xmlArb.get("traspasos.valoresActuales.naf")));
				}
				
				if (arbol.existeNodo("traspasos.valoresActuales.naf_titular")) {
					hOriginales.put("naf_titular", Misc.nz(xmlArb.get("traspasos.valoresActuales.naf_titular")));
				}
				
				if (arbol.existeNodo("traspasos.valoresActuales.dni")) {
					hOriginales.put("dni", Misc.nz(xmlArb.get("traspasos.valoresActuales.dni")));
				}
				
				
				int contadorBusqueda = 0;
				while (arbol.existeNodo("traspasos.camposAfectados.campo" + indiceBusqueda)) {
					campoAfectadoAux = Misc.nz(xmlArb.get("traspasos.camposAfectados" + ".campo" + contadorBusqueda));
					if (Misc.nz(campoAfectadoAux).equals("nombre")){
						nombreAnterior = Misc.nz(xmlArb.get("traspasos.camposAnteriores" + ".valor" + contadorBusqueda));
						nombreSolicitud = Misc.nz(xmlArb.get("traspasos.valorSolicitud" + ".valor" + contadorBusqueda));
					}
					contadorBusqueda++;
					indiceBusqueda = Integer.toString(contadorBusqueda);
				}
				
				if (Misc.nz(nombreAnterior).equals("") && Misc.nz(nombreSolicitud).equals("")){
					nombreAnterior = nombreSolicitud = "NOMBRE";
				}
		
				//
				for (int contador = 0; contador < contadorBusqueda; contador++) {
					//
					// Elemento afectado
					String elementoAfectado = "";
					// Valor campo anterior
					String valorAnterior = "";
					// Valor nuevo
					String valorSolicitud = "";
					// Para obtener el indice como cadena del elemento
					String indice = "";
					if (contador != 0) {
						indice = Integer.toString(contador);
					}
					// Se busca el campo afectado por el cambio
					if (arbol.existeNodo("traspasos.camposAfectados.campo" + indice)) {
						elementoAfectado = Misc.nz(xmlArb.get("traspasos.camposAfectados" + ".campo" + indice));
					
						// Si el campo pertenece a los campos a incluir en el
						// informe
						if (Inicializacion.MAP_CAMPOS.containsKey(elementoAfectado)) {
							//
							if (arbol.existeNodo("traspasos.camposAnteriores.valor" + indice)) {
								valorAnterior = Misc.nz(xmlArb.get("traspasos.camposAnteriores" + ".valor" + indice));
							}
							//
							if (arbol.existeNodo("traspasos.valorSolicitud.valor" + indice)) {
								valorSolicitud = Misc.nz(xmlArb.get("traspasos.valorSolicitud" + ".valor" + indice));
							}
							//metemos en una HashMap la clave el campo modificado y el valor los cambios que ha tenido
							hCambios.put(elementoAfectado, valorAnterior+" -> "+valorSolicitud);
							
							if (hOriginales.containsKey(elementoAfectado)) {
								hOriginales.put(elementoAfectado, valorAnterior);
							}
							
						}
					}
				}
			}
			
			hCambios.put("originales",hOriginales);
		
		} catch (Exception e) {
			logger.debug("getInfoXmlCambios: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			logger.error("getInfoXmlBusquedaAproximada: Exception: " + e.getMessage(), e);
		}		
		return hCambios;
	}
	

	public void addInfoMap(HashMap mapInformacion, int caActual, int numeroCambios) throws Exception {

		try {
			//
			int[] arrayCambios = null;
			//
			// logger.debug("addInfoMap: numeroCambios: " + numeroCambios +
			// ", caActual: " + caActual);
			if (mapInformacion.containsKey(Integer.toString(caActual))) {
				arrayCambios = (int[]) mapInformacion.get(Integer.toString(caActual));
			} else {
				arrayCambios = new int[10];
			}
			if (numeroCambios > 0 && numeroCambios < 10) {
				int valor = arrayCambios[numeroCambios];
				valor++;
				arrayCambios[numeroCambios] = valor;
				//
				mapInformacion.put(Integer.toString(caActual), arrayCambios);
			}
		} catch (Exception e) {
			logger.error("addInfoMap: Exception: " + e.getMessage(), e);
			throw e;
		}
	}

	public String getValoresActuales(String nombre, String apellido1, String apellido2, String codSexo, String fecha) throws Exception {
		String xml = "";
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<nombre>");
			stringBuffer.append(Misc.nz(nombre));
			stringBuffer.append("</nombre>");
			stringBuffer.append("<apellido1>");
			stringBuffer.append(Misc.nz(apellido1));
			stringBuffer.append("</apellido1>");
			stringBuffer.append("<apellido2>");
			stringBuffer.append(Misc.nz(apellido2));
			stringBuffer.append("</apellido2>");
			stringBuffer.append("<codSexo>");
			stringBuffer.append(Misc.nz(codSexo));
			stringBuffer.append("</codSexo>");
			stringBuffer.append("<fecha_nac>");
			stringBuffer.append(Misc.nz(fecha));
			stringBuffer.append("</fecha_nac>");
			//
			xml = stringBuffer.toString();
			//
		} catch (Exception e) {
			logger.error("getValoresActuales: Exception: " + e.getMessage(), e);
			throw e;
		}
		return xml;
	}

	public void addRegiterInFile(FileWriter fileWriter, InfoCambiosTraspasosBean infoCambiosTraspasosBean) throws Exception {
		try {
			//
			// CABECERA
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<traspasos>");
			//
			stringBuffer.append("<numero_cambios>");
			stringBuffer.append(infoCambiosTraspasosBean.getNumeroCambios());
			stringBuffer.append("</numero_cambios>");
			//
			stringBuffer.append("<codigo_usuario_sns>");
			stringBuffer.append(Misc.nz(infoCambiosTraspasosBean.getCodUsuarioSns()));
			stringBuffer.append("</codigo_usuario_sns>");
			//
			stringBuffer.append("<caActual>");
			stringBuffer.append(infoCambiosTraspasosBean.getCaActual());
			stringBuffer.append("</caActual>");
			//
			stringBuffer.append("<caAnterior>");
			stringBuffer.append(infoCambiosTraspasosBean.getCaAnterior());
			stringBuffer.append("</caAnterior>");
			//
			stringBuffer.append("<camposAfectados>");
			stringBuffer.append(Misc.nz(infoCambiosTraspasosBean.getCamposAfectados()));
			stringBuffer.append("</camposAfectados>");
			stringBuffer.append("<camposAnteriores>");
			stringBuffer.append(Misc.nz(infoCambiosTraspasosBean.getCamposAnteriores()));
			stringBuffer.append("</camposAnteriores>");
			stringBuffer.append("<valorSolicitud>");
			stringBuffer.append(Misc.nz(infoCambiosTraspasosBean.getValoresSolicitud()));
			stringBuffer.append("</valorSolicitud>");
			//
			/*
			 * stringBuffer.append("<valorActual>");
			 * stringBuffer.append(Misc.nz(
			 * infoCambiosTraspasosBean.getValoresActuales()));
			 * stringBuffer.append("</valorActual>");
			 */
			//
			stringBuffer.append("</traspasos>");
			//
			stringBuffer.append("\n");
			//
			Inicializacion.numDeTraspasosXml++;
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
			//
		} catch (Exception e) {
			logger.error("addInfoMap: Exception: " + e.getMessage(), e);
			throw e;
		}
	}

	public Salida getUnionInfo(HashMap mapInformacion, HashMap mapTotal) {

		Salida salida = new Salida();

		try {
			logger.debug("getUnionInfo: INICIO");
			//
			// Se añade a la informacion de los cambios en los campos basicos,
			// la informacion de los traspasos
			// totales. Pero no se indicara el total, sino se obtiene el numero
			// de traspasos sin cambios, para
			// incluirlo dentro del array en la posicion 0.
			// Se coje el total de cada CCAA, y se pone en la posicion 0 del
			// array de la otra HashMap el
			// total menos el numero de cambios.
			Set set = mapTotal.keySet();
			Iterator iterator = set.iterator();
			//
			while (iterator.hasNext()) {
				//
				String caActual = (String) iterator.next();
				String valorTotalCadena = "";
				// logger.debug("getUnionInfo: CCAA: " + caActual);
				//
				if (!Misc.esVacio(caActual)) {
					if (mapTotal.containsKey(caActual)) {
						valorTotalCadena = (String) mapTotal.get(caActual);
					}
				}
				//
				if (Misc.esDigito(valorTotalCadena)) {
					//
					int valorTotalTraspasos = Integer.parseInt(valorTotalCadena);
					//
					if (mapInformacion.containsKey(caActual)) {
						int[] arrayCambios = (int[]) mapInformacion.get((caActual));
						int numeroCambios = 0;
						for (int i = 1; i < 9; i++) {
							numeroCambios = numeroCambios + arrayCambios[i];
						}
						if (numeroCambios > 0) {
							int traspasosSinCambios = valorTotalTraspasos - numeroCambios;
							arrayCambios[0] = traspasosSinCambios;
							mapInformacion.put(caActual, arrayCambios);
						}
					} else {
						int[] arrayCambios = new int[10];
						arrayCambios[0] = valorTotalTraspasos;
						mapInformacion.put(caActual, arrayCambios);
					}
				}
			}
			// Se elimina de la HashMap uno de los 2 elementos de Ingesa
			if (mapInformacion.containsKey("18") && mapInformacion.containsKey("19")) {
				mapInformacion.remove("19");
			}
			//
			logger.debug("getUnionInfo: FIN");
		} catch (Exception e) {
			salida.setError(true);
			salida.setMsg(e.getMessage());
			salida.setExcepcion(e);
			logger.error("getUnionInfo: Exception: " + e.getMessage(), e);
		}
		return salida;
	}

	public void addRegiterCodSns(FileWriter fileWriter, String codSns, int numeroCambios, int caActual, int caAnterior) throws Exception {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(codSns + ";");
			stringBuffer.append(numeroCambios + ";");
			stringBuffer.append(caActual + ";");
			stringBuffer.append(caAnterior + ";");
			stringBuffer.append("\n");
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
		} catch (Exception e) {
			logger.error("addRegiterCodSns: Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	public void addRegiterCodSnsBusquedaAproximada(FileWriter fileWriter, String codSns, int numeroCambios, int numeroCambiosBusquedaAproximada,String caActual, String caAnterior) throws Exception {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(codSns + ";");
			stringBuffer.append(numeroCambios + ";");
			stringBuffer.append(numeroCambiosBusquedaAproximada + ";");
			stringBuffer.append(caActual + ";");
			stringBuffer.append(caAnterior + ";");
			stringBuffer.append("\n");
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
		} catch (Exception e) {
			logger.error("addRegiterCodSnsBusquedaAproximada: Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	public void addRegiterBloqueos(FileWriter fileWriter, String codSns, int numeroCambios, int numeroCambiosBusquedaAproximada,String caActual, String caAnterior, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo, String naf, String naf_titular, String dni) throws Exception {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(codSns + ";");
			stringBuffer.append(numeroCambios + ";");
			stringBuffer.append(numeroCambiosBusquedaAproximada + ";");
			stringBuffer.append(caActual + ";");
			stringBuffer.append(caAnterior + ";");
			stringBuffer.append(nombre + ";");
			stringBuffer.append(apellido1 + ";");
			stringBuffer.append(apellido2 + ";");
			stringBuffer.append(fecha_nac + ";");
			stringBuffer.append(sexo + ";");
			stringBuffer.append(naf + ";");
			stringBuffer.append(naf_titular + ";");
			stringBuffer.append(dni + ";");
			stringBuffer.append("\n");
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
		} catch (Exception e) {
			logger.error("addRegiterBloqueos: Exception: " + e.getMessage(), e);
			throw e;
		}
	}

	public void addRegiterCodSnsBusquedaAproximadaJuanra(FileWriter fileWriter, String codSns, int numeroCambios, int caActual, int caAnterior, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo, String naf, String naf_titular, String dni, HashMap valoresOriginales) throws Exception {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(codSns + ";");
			stringBuffer.append(numeroCambios + ";");
			stringBuffer.append(caActual + ";");
			stringBuffer.append(caAnterior + ";");
			stringBuffer.append(nombre + ";");
			stringBuffer.append(apellido1 + ";");
			stringBuffer.append(apellido2 + ";");
			stringBuffer.append(fecha_nac + ";");
			stringBuffer.append(sexo + ";");
			stringBuffer.append(naf + ";");
			stringBuffer.append(naf_titular + ";");
			stringBuffer.append(dni + ";");
			//Campos sin Cambios - Control					
			stringBuffer.append(Misc.nz(valoresOriginales.get("nombre")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("apellido1")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("apellido2")) + ";");			
			stringBuffer.append(Misc.nz(valoresOriginales.get("fecha_nac"))+ ";");			
			stringBuffer.append(Misc.nz(valoresOriginales.get("sexo")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("naf")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("naf_titular")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("dni")) + ";");
			stringBuffer.append("\n");         
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
		} catch (Exception e) {
			logger.error("addRegiterCodSnsBusquedaAproximada: Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	public void addRegiterCodSnsModificaciones(FileWriter fileWriter, String codSns, int numeroCambios, int caActual, int caAnterior, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo, String naf, String naf_titular, String dni, HashMap valoresOriginales) throws Exception {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			//Campos con Cambios
			stringBuffer.append(codSns + ";");
			stringBuffer.append(numeroCambios + ";");
			stringBuffer.append(caActual + ";");
			stringBuffer.append(caAnterior + ";");
			stringBuffer.append(nombre + ";");
			stringBuffer.append(apellido1 + ";");
			stringBuffer.append(apellido2 + ";");
			stringBuffer.append(fecha_nac + ";");
			stringBuffer.append(sexo + ";");
			stringBuffer.append(naf + ";");
			stringBuffer.append(naf_titular + ";");
			stringBuffer.append(dni + ";");
			//Campos sin Cambios - Control					
			stringBuffer.append(Misc.nz(valoresOriginales.get("nombre")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("apellido1")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("apellido2")) + ";");			
			stringBuffer.append(Misc.nz(valoresOriginales.get("fecha_nac"))+ ";");			
			stringBuffer.append(Misc.nz(valoresOriginales.get("sexo")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("naf")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("naf_titular")) + ";");
			stringBuffer.append(Misc.nz(valoresOriginales.get("dni")) + ";");
			stringBuffer.append("\n");         
			//
			fileWriter.write(stringBuffer.toString());
			fileWriter.flush();
		} catch (Exception e) {
			logger.error("addRegiterCodSnsModificaciones: Exception: " + e.getMessage(), e);
			throw e;
		}
	}

	public Salida getInfoBBDDTraspasosTotal(AccesoBD bd) {

		HashMap mapInformacion = new HashMap();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Salida salida = new Salida();
		QueryEstadoAgentes queryEstadoAgentes = new QueryEstadoAgentes();

		try {
			logger.debug("getInfoBBDDTraspasosTotal: INICIO");
			//
			// Se genera la Query de manera que se ejecutara entre dos fechas
			// Se cojera la fecha actual, se le restara un dia y se tomara desde
			// las 00:00 hasata las 23:59
			String query = Misc.nz(queryEstadoAgentes.generateQueryTotalTraspasos());
			//
			// Se realiza la consulta
			HashMap hConn = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hConn.get("ps");
			resultSet = (ResultSet) hConn.get("rs");

			while (resultSet.next()) {
				//
				int caActual = resultSet.getInt("COD_CA_ISO");
				int contador = resultSet.getInt("CONTADOR");
				mapInformacion.put(Integer.toString(caActual), Integer.toString(contador));
			}
			//
			salida.setValor(mapInformacion);
			//
			logger.debug("getInfoBBDDTraspasosTotal: FIN");
		} catch (Exception e) {
			salida.setExcepcion(e);
			salida.setError(true);
			salida.setMsg(e.getMessage());
			logger.error("getInfoBBDDTraspasosTotal: Exception: " + e.getMessage(), e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e3) {
				resultSet = null;
			}
			//
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e4) {
				preparedStatement = null;
			}
		}
		return salida;
	}

}
