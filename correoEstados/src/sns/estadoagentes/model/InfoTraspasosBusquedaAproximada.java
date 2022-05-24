package sns.estadoagentes.model;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import gasai.util.out.Salida;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.comun.bd.AccesoBdFactory;
import sns.comun.bean.CambiosTraspasosBean;
import sns.comun.bean.InfoCambiosTraspasosBean;
import sns.comun.config.Inicializacion;
import sns.comun.config.QueryEstadoAgentes;
import sns.comun.util.Util;
import sns.comun.util.mail.CuerpoMailBean;

public class InfoTraspasosBusquedaAproximada extends Info {

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);

	@Override
	public CambiosTraspasosBean getInfoTraspasos(String path, int numCambiosVisualizar, AccesoBD bd) {
		// TODO Auto-generated method stub
		CambiosTraspasosBean cambiosTraspasosBean = null;
		//
		Salida salida = new Salida();

		try {
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: INICIO");
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: -------------------------------------");
			//
			salida = this.getInfoBBDD(bd, path, numCambiosVisualizar);
			if (!salida.getError()) {
				cambiosTraspasosBean = (CambiosTraspasosBean) salida.getValor();
				cambiosTraspasosBean.setNumeroRegistrosXml(Inicializacion.numDeTraspasosXml);
			} else {
				throw new Exception(Misc.nz(salida.getMsg()));
			}
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: numCambiosVisualizar: " + Inicializacion.numCambiosVisualizar);
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: numDeTraspasosXml:    " + Inicializacion.numDeTraspasosXml);
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: salida.getError:      " + salida.getError());
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: salida.getMsg:        " + salida.getMsg());
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: -------------------------------------");
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoTraspasos: FIN");
		} catch (Exception e) {
			salida.setError(true);
			salida.setMsg(e.getMessage());
			salida.setExcepcion(e);
			logger.error("InfoTraspasosBusquedaAproximada.getInfoTraspasos: Exception: " + e.getMessage(), e);
		} 
		return cambiosTraspasosBean;
	}

	private Salida getInfoBBDD(AccesoBD bd, String path, int numCambiosVisualizar) {

		HashMap mapInformacion = new HashMap();
		HashMap mapInformacionSinBusqueda = new HashMap();
		HashMap hDatosFichero = new HashMap();
		
		String[] datosFichero = null;
		//
		FileWriter fileWriterCodSnsBusqueda = null;
		FileWriter fileWriterBloqueos = null;
		//
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementBloqueados = null;
		PreparedStatement preparedStatementBloqueos = null;
		ResultSet resultSet = null;
		ResultSet resultSetBloqueados = null;
		ResultSet resultSetBloqueos = null;

		Salida salida = new Salida();
		HashMap hCambios = new HashMap();

		Util util = new Util();
		QueryEstadoAgentes queryEstadoAgentes = new QueryEstadoAgentes();

		try {
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoBBDD: INICIO");
			
			StringBuffer stringBufferNombreFicheroCodSnsBusqueda = new StringBuffer();
			stringBufferNombreFicheroCodSnsBusqueda.append(util.generateFecha(""));
			stringBufferNombreFicheroCodSnsBusqueda.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_COD_SNS_BUSQUEDA);
			StringBuffer stringBufferPathCompletaCodSnsBusqueda = new StringBuffer();
			stringBufferPathCompletaCodSnsBusqueda.append(path);
			stringBufferPathCompletaCodSnsBusqueda.append(stringBufferNombreFicheroCodSnsBusqueda.toString());
			fileWriterCodSnsBusqueda = new FileWriter(stringBufferPathCompletaCodSnsBusqueda.toString());
			
			
			//No se toca, se genera tal cual
			StringBuffer stringBufferNombreFicheroBloqueos = new StringBuffer();
			stringBufferNombreFicheroBloqueos.append(util.generateFecha(""));
			stringBufferNombreFicheroBloqueos.append(Inicializacion.SUFIJO_NOMBRE_FICHERO_BLOQUEOS);
			StringBuffer stringBufferPathCompletaBloqueos= new StringBuffer();
			stringBufferPathCompletaBloqueos.append(path);
			stringBufferPathCompletaBloqueos.append(stringBufferNombreFicheroBloqueos.toString());
			fileWriterBloqueos = new FileWriter(stringBufferPathCompletaBloqueos.toString());
			
			
			
			//
			// Se escribe la cabecera del fichero
			fileWriterCodSnsBusqueda.write("CODIGO_USUARIO_SNS;NUMERO DE CAMBIOS;CA_ACTUAL;CA_ORIGEN_XML;nombre;apellido1;apellido2;f/n;sexo;naf;naf-t;dni-nie;nombre;apellido1;apellido2;f/n;sexo;naf;naf-t;dni-nie;" + "\n");
			fileWriterCodSnsBusqueda.flush();
			
			fileWriterBloqueos.write("CODIGO_USUARIO_SNS;NUMERO DE CAMBIOS;NUMERO DE CAMBIOS BUSQUEDA APROXIMADA;CA_ACTUAL;CA_ORIGEN_XML;nombre;apellido1;apellido2;f/n;sexo;naf;naf-t;dni-nie;" + "\n");
			fileWriterBloqueos.flush();
			

			// Se genera la Query de manera que se ejecutara entre dos fechas
			// Se cojera la fecha actual, se le restara un dia y se tomara desde
			// las 00:00 hasata las 23:59
			String query = Misc.nz(queryEstadoAgentes.generateQueryCambiosTraspasosSinDesbloqueados());
			//
			// Se realiza la consulta
			HashMap hConn = bd.consultaRaw(query);
			preparedStatement = (PreparedStatement) hConn.get("ps");
			resultSet = (ResultSet) hConn.get("rs");

			while (resultSet.next()) {
				//
				datosFichero = new String[4];
				String codUsuarioSns = resultSet.getString("COD_USUARIO_SNS");
				String camposAfectados = resultSet.getString("CAMPOS_AFECTADOS");
				String camposAnteriores = resultSet.getString("VALOR_ANTERIOR");
				String valoresSolicitud = resultSet.getString("VALOR_SOLICITUD");
				String valoresActuales = this.getXmlValoresActuales(resultSet);
				
				int caActual = resultSet.getInt("CA_ACTUAL");
				if(caActual == 19){
					caActual = 18;
				}
				int caAnterior = resultSet.getInt("CA_ANTERIOR");
				if(caAnterior == 19){
					caAnterior = 18;
				}

				if (codUsuarioSns.equals("BBBBBBBBBS586706") && camposAnteriores.indexOf("PERAL ARLANZ<A") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PERAL ARLANZ<A</valor>", "<valor>PERAL ARLANZA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBX371054") && valoresSolicitud.indexOf("CAR<COL") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>ECUADOR  6 PISO 1 EL CAR<COL</valor>", "<valor>ECUADOR  6 PISO 1 EL CAR COL</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBL189276") && camposAnteriores.indexOf("CAÃAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>CAÃAS</valor>", "<valor>CAÑAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBK652424") && valoresSolicitud.indexOf("CABA<LLERO DE GRACIA") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>CABA<LLERO DE GRACIA</valor>", "<valor>CABALLERO DE GRACIA</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCW820059") && valoresSolicitud.indexOf("AVDA<. ESCALERITAS, 33 3º PTA. 9") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>AVDA<. ESCALERITAS, 33 3º PTA. 9</valor>", "<valor>AVDA. ESCALERITAS, 33 3º PTA. 9</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBBD380697") && camposAnteriores.indexOf("&&&&&") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>&&&&&</valor>", "<valor>-----</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDN128702") && valoresSolicitud.indexOf("SEA & SUN HILLS SN B/6 AP 602") != -1) {
					valoresSolicitud = Misc.remplazar(valoresSolicitud, "<valor>SEA & SUN HILLS SN B/6 AP 602</valor>", "<valor>SEA AND SUN HILLS SN B/6 AP 602</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBCJ690535") && camposAnteriores.indexOf("PLAZA FERNANDEZ< VIAGAS") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>PLAZA FERNANDEZ< VIAGAS/valor>", "<valor>PLAZA FERNANDEZ VIAGAS</valor>");
				}
				if (codUsuarioSns.equals("BBBBBBBBDM018526") && camposAnteriores.indexOf("5º IZ<DA.") != -1) {
					camposAnteriores = Misc.remplazar(camposAnteriores, "<valor>5º IZ<DA.</valor>", "<valor>5ºIZ</valor>");
				}
				
			
				// Se construye un Xml a partir de la informacion de la BBDD
				String xml = super.getXml(camposAfectados, camposAnteriores, valoresSolicitud,valoresActuales);
				// Se completa la informacion de los cambios con el xml nuevo
				salida = super.getInfoXmlBusquedaAproximada(mapInformacion, xml);
				hCambios = this.getInfoXmlCambios(mapInformacion, xml);
								

				//
				if (!salida.getError()) {
					Integer integer = (Integer) salida.getValor();
					// Se actualiza el map con el numero de cambios un map
					int numeroCambios = integer.intValue();
					//
					if (numeroCambios > 0) {
						this.addInfoMap(mapInformacion, caActual, numeroCambios);
						// Si el numero de cambios se corresponde con el pedido
						// para visualizar los mensajes
						if (numeroCambios >= numCambiosVisualizar) {						
							this.addRegiterCodSnsBusquedaAproximadaJuanra(fileWriterCodSnsBusqueda, codUsuarioSns, numeroCambios, caActual, caAnterior, 
									(String)Misc.nz(hCambios.get("nombre")), (String)Misc.nz(hCambios.get("apellido1")), (String)Misc.nz(hCambios.get("apellido2")),
									(String)Misc.nz(hCambios.get("fecha_nac")), (String)Misc.nz(hCambios.get("sexo")), (String)Misc.nz(hCambios.get("naf")),
									(String)Misc.nz(hCambios.get("naf_titular")), (String)Misc.nz(hCambios.get("dni")),(HashMap<String,String>) hCambios.get("originales"));							
						}
					}
				} else {
					logger.debug("InfoTraspasosBusquedaAproximada.getInfoBBDD: xx codUsuarioSns: " + codUsuarioSns);
					logger.debug("InfoTraspasosBusquedaAproximada.getInfoBBDD: SalidaMSG: " + salida.getMsg());
					throw new Exception("ERROR " + salida.getMsg());
				}
			}
			
			
			//Ahora sacamos los datos de los bloqueado que tengan tres o mas cambios con y sin busqueda aproximada solamente para el fichero
			String queryBloqueados = Misc.nz(queryEstadoAgentes.generateQueryTraspasosBloqueados());
			//
			// Se realiza la consulta para sacar los traspasos que han sufrido bloqueo en un fichero
			HashMap hConnBloqueados = bd.consultaRaw(queryBloqueados);
			preparedStatementBloqueados = (PreparedStatement) hConnBloqueados.get("ps");
			resultSetBloqueados = (ResultSet) hConnBloqueados.get("rs");
			
			//Se recorre el resultado para buscar el nombre con antelacion para aplicarlo cuando haya que comparar el sexo con la
			//busqueda aproximada
			HashMap hNombres = new HashMap();
			String[] nombres = null;

			while (resultSetBloqueados.next()) {
				String codSns = Misc.nz(resultSetBloqueados.getString("COD_USUARIO_SNS"));
				String nombreCampo = Misc.nz(resultSetBloqueados.getString("NOMBRE_CAMPO"));
				String valorAnterior = Misc.nz(resultSetBloqueados.getString("VALOR_ANTERIOR"));
				String valorNuevo = Misc.nz(resultSetBloqueados.getString("VALOR_NUEVO"));
				if(Misc.nz(nombreCampo).equals("nombre")){
					nombres = new String[2];
					nombres[0] = valorAnterior;
					nombres[1] = valorNuevo;
					hNombres.put(codSns, nombres);
				}
			}
			
			
			HashMap hConnBloqueos = bd.consultaRaw(queryBloqueados);
			preparedStatementBloqueos = (PreparedStatement) hConnBloqueos.get("ps");
			resultSetBloqueos = (ResultSet) hConnBloqueos.get("rs");
			
			String[] datosTrapasosBloqueados = null;
			HashMap hDatosTrapasosBloqueados = new HashMap();
			HashMap hCambiosBloqueos = null;
			HashMap hDatosCambiosBloqueos = new HashMap();
			BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper();
			EstadoAgentesHelper estadosAgentesHelper = new EstadoAgentesHelper();
			boolean campoBusqueda = false;
			boolean cambio = true;
			boolean nafBueno = true;
			int numeroCambios = 0;
			int numeroCambiosSinBusqueda = 0;
			
			while (resultSetBloqueos.next()) {
				campoBusqueda = false;
				cambio = true;
				nafBueno = true;
				numeroCambios = 0;
				numeroCambiosSinBusqueda = 0;
				datosTrapasosBloqueados = new String[4];
				String codSns = Misc.nz(resultSetBloqueos.getString("COD_USUARIO_SNS"));
				String nombreCampo = Misc.nz(resultSetBloqueos.getString("NOMBRE_CAMPO"));
				String valorAnterior = Misc.nz(resultSetBloqueos.getString("VALOR_ANTERIOR"));
				String valorNuevo = Misc.nz(resultSetBloqueos.getString("VALOR_NUEVO"));
				int caActualDesbloqueados = resultSetBloqueos.getInt("CA_ACTUAL");
				int caOrigenXml = Inicializacion.MAP_RELACION_COMUNIDADES.get(resultSetBloqueos.getInt("CA_ORIGEN_XML"));
				datosTrapasosBloqueados[0] = Integer.toString(caActualDesbloqueados);
				datosTrapasosBloqueados[1] = Integer.toString(caOrigenXml);
				
				if (!valorAnterior.equals(valorNuevo)) {
					numeroCambiosSinBusqueda++;
				}
				
				
				//COMO ESTABA ANTES
				/*
				//Si el valor anterior es vacio y el valor solicitud no es vacio, no se considera cambio 
				//y el boolean cambio se pone a false
				if(Misc.nz(valorAnterior).equals("") && !Misc.nz(valorNuevo).equals("")){
					cambio = false;
				}
				*/
				
				
				
				//Para los campos dni, naf y naf_titular
				//si el valor anterior es vacio y el valor solicitud no es vacio, no se considera cambio 
				//y el boolean cambio se pone a false
				if (Misc.nz(nombreCampo).equals("dni") || Misc.nz(nombreCampo).equals("naf") 
						|| Misc.nz(nombreCampo).equals("naf_titular")) {
					
					//Miramo si viene NAF o NAF Titular para evitar los NAF ficticios
					if(Misc.nz(nombreCampo).equals("naf") || Misc.nz(nombreCampo).equals("naf_titular")) {
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
					
					if(Misc.nz(valorAnterior).equals("") && !Misc.nz(valorNuevo).equals("")){
						cambio = false;
					}
				}
				
				//Si hay cambio
				if(cambio && nafBueno){
					//Comprobamos si viene nombre, apellido1 y apellido2 para aplicar la busqueda aproximada en esos casos
					if (Misc.nz(nombreCampo).equals("nombre") || Misc.nz(nombreCampo).equals("apellido1") 
							|| Misc.nz(nombreCampo).equals("apellido2") || Misc.nz(nombreCampo).equals("sexo")) {
						campoBusqueda = true;
					}
					//Si es campoBusqueda aplicamos la Busqueda aproximada
					if(campoBusqueda){
						if (Misc.nz(nombreCampo).equals("nombre") || Misc.nz(nombreCampo).equals("apellido1") 
								|| Misc.nz(nombreCampo).equals("apellido2")){
							boolean esAproximadaCadena = busquedaAproximadaHelper.esAproximadaCadena(Misc.nz(valorAnterior), Misc.nz(valorNuevo), nombreCampo);
							//logger.debug("Pintamos lo que se compara y el resultado:  Valor Anterior[" + valorAnterior + "], Valor Nuevo["+ valorNuevo + "], Resultado =" + esAproximadaCadena);
							if(esAproximadaCadena == false){
								numeroCambios++;
							}
						}
						if (Misc.nz(nombreCampo).equals("sexo")) {
							String nombreAnterior = "";
							String nombreNuevo = "";
							if(hNombres.containsKey(codSns)){
								String[] nombresAux = (String[])hNombres.get(codSns);
								nombreAnterior = (String)nombresAux[0];
								nombreNuevo = (String)nombresAux[1];
							}
							else{
									nombreAnterior = nombreNuevo = "NOMBRE";
							}
							boolean esAproximadoSexo = BusquedaAproximadaHelper.esAproximadoSexo(valorAnterior, valorNuevo, nombreAnterior, nombreNuevo);
							if(esAproximadoSexo == false){
								numeroCambios++;
							}
						}
					}
					else{
						if (!valorAnterior.equals(valorNuevo)) {
							//logger.debug("@@@@@@@@@@@@@@@@@@@@@Es un cambio. Pintamos lo que se compara y el resultado:  Valor Anterior[" + valorAnterior + "], Valor Nuevo["+ valorNuevo+"]");
							numeroCambios++;
						}
					}	
				}
				
				if(hDatosTrapasosBloqueados.containsKey(codSns)){
					String[] datosAux = (String[])hDatosTrapasosBloqueados.get(codSns);
					numeroCambiosSinBusqueda = numeroCambiosSinBusqueda + Integer.parseInt(datosAux[2]);
					numeroCambios = numeroCambios + Integer.parseInt(datosAux[3]);
				}
				
				
				if(hDatosCambiosBloqueos.containsKey(codSns)){
					hCambiosBloqueos = (HashMap)hDatosCambiosBloqueos.get(codSns);
				}
				else{
					hCambiosBloqueos = new HashMap();
				}
				
				hCambiosBloqueos.put(nombreCampo, valorAnterior+" -> "+valorNuevo);
				hDatosCambiosBloqueos.put(codSns, hCambiosBloqueos);
				datosTrapasosBloqueados[2] = Integer.toString(numeroCambiosSinBusqueda);
				datosTrapasosBloqueados[3] = Integer.toString(numeroCambios);
				hDatosTrapasosBloqueados.put(codSns, datosTrapasosBloqueados);
			}
			
			Iterator ite = hDatosTrapasosBloqueados.entrySet().iterator();
			HashMap hAux = null;
			while (ite.hasNext()) {
				Map.Entry e = (Map.Entry)ite.next();
				String[] datos = (String[])e.getValue();
				hAux = (HashMap)hDatosCambiosBloqueos.get((String)e.getKey());
				//Ya no se saca los bloqueos en el fichero codSnsBusquedaAproximada.csv
				//this.addRegiterCodSnsBusquedaAproximada(fileWriterCodSnsBusqueda, (String)e.getKey(), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), (String) datos[0], (String) datos[1]);
				this.addRegiterBloqueos(fileWriterBloqueos, (String)e.getKey(), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), (String) datos[0], (String) datos[1],  (String)Misc.nz(hAux.get("nombre")), (String)Misc.nz(hAux.get("apellido1")), (String)Misc.nz(hAux.get("apellido2")), (String)Misc.nz(hAux.get("fecha_nac")), (String)Misc.nz(hAux.get("sexo")), (String)Misc.nz(hAux.get("naf")), (String)Misc.nz(hAux.get("naf_titular")), (String)Misc.nz(hAux.get("dni")));
			}
			
			
			//
			CambiosTraspasosBean cambiosTraspasosBean = new CambiosTraspasosBean();
			cambiosTraspasosBean.setMapInformacion(mapInformacion);
			cambiosTraspasosBean.setNombreFichero("");
			cambiosTraspasosBean.setNombreFichero(stringBufferNombreFicheroBloqueos.toString());
			cambiosTraspasosBean.setNombreFicheroCodSns("");
			cambiosTraspasosBean.setNombreFicheroCodSns(stringBufferNombreFicheroCodSnsBusqueda.toString());
			cambiosTraspasosBean.setPathFichero(path);
			//
			salida.setValor(cambiosTraspasosBean);
			//
			logger.debug("InfoTraspasosBusquedaAproximada.getInfoBBDD: FIN");
		} catch (Exception e) {
			salida.setExcepcion(e);
			salida.setError(true);
			salida.setMsg(e.getMessage());
			logger.error("InfoTraspasosBusquedaAproximada.getInfoBBDD: Exception: " + e.getMessage(), e);
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
			//
			try {
				if (fileWriterCodSnsBusqueda != null) {
					fileWriterCodSnsBusqueda.close();
				}
			} catch (Exception e1) {
				fileWriterCodSnsBusqueda = null;
			}
			try {
				if (resultSetBloqueados != null) {
					resultSetBloqueados.close();
				}
			} catch (Exception e3) {
				resultSetBloqueados = null;
			}
			//
			try {
				if (preparedStatementBloqueados != null) {
					preparedStatementBloqueados.close();
				}
			} catch (Exception e4) {
				preparedStatementBloqueados = null;
			}
			//
			try {
				if (fileWriterBloqueos != null) {
					fileWriterBloqueos.close();
				}
			} catch (Exception e1) {
				fileWriterBloqueos = null;
			}
			try {
				if (resultSetBloqueos != null) {
					resultSetBloqueos.close();
				}
			} catch (Exception e3) {
				resultSetBloqueos = null;
			}
			//
			try {
				if (preparedStatementBloqueos != null) {
					preparedStatementBloqueos.close();
				}
			} catch (Exception e4) {
				preparedStatementBloqueos = null;
			}
		}
		return salida;
	}

	

	@Override
	public CambiosTraspasosBean getInfoModificaciones(String path,
			int numCambiosVisualizar, AccesoBD bd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer viewInfoMapHorizontal(HashMap mapInformacion)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

