package sns.busqueda.model;

public class BusquedaHelperSns2 extends BusquedaHelper{
/*
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public void manager(String path, String ficheroOrigen, String ficheroDestino, String codCaIsoOrigen) throws Exception {
		//
		String line = "";
		int contadorRegistros = 0;
		//
		AccesoBd accesoBd = null;
		AccesoBdIndef accesoBdIndef = null;
		//
		BufferedReader bufferedReader  = null;
		FileReader fileReader          = null;
		//
		FileWriter fileWriter          = null;
		//
		LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda = new LinkedHashMap <String, ResultadosCampoBean> ();
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd       = new AccesoBd();
			accesoBdIndef  = new AccesoBdIndef ();
			//
			fileReader     = new FileReader(path + ficheroOrigen);
			bufferedReader = new BufferedReader(fileReader);
			//
			fileWriter     = new FileWriter(path + ficheroDestino);
			//
			//
			// Se Inicializa el map para los resultados y para los campos de consulta
			//
            Set <String> setCamposBusqueda          = InicializacionBusqueda.MAP_CAMPO.keySet();
            Iterator <String> iteratorCamposBusqueda = setCamposBusqueda.iterator();
            //           
            while (iteratorCamposBusqueda.hasNext()) {
            	String camposBusqueda  = (String) iteratorCamposBusqueda.next();
                //
                if(InicializacionBusqueda.MAP_CAMPOS_BUSQUEDA_SNS.containsKey(camposBusqueda)) {
                	mapCamposBusqueda.put(camposBusqueda,new ResultadosCampoBean());
                	logger.debug("camposBusqueda: " + camposBusqueda);
                }   
            }
			//
            while ((line = bufferedReader.readLine()) != null) {
				contadorRegistros++;
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				DatosLecturaBean datosLecturaBean  = new DatosLecturaBean (line, contadorRegistros);
				//
				this.gestionConsultas (accesoBd, accesoBdIndef, fileWriter, mapCamposBusqueda, datosLecturaBean);
            }
			//
			logger.debug("contadorRegistros: " + contadorRegistros);
			//
			this.verResultados (mapCamposBusqueda);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
			try {
				if (accesoBdIndef != null) {
					accesoBdIndef.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBdIndef = null;
			}
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (Exception e2) {
				bufferedReader = null;
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			}
			catch (Exception e3) {
				fileReader = null;
			}		
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			}
			catch (Exception e4) {
				fileWriter = null;
			}
		}
	}
	
	
	public void gestionConsultas (AccesoBd accesoBd, AccesoBdIndef accesoBdIndef, FileWriter fileWriter, LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda, DatosLecturaBean datosLecturaBean) throws Exception {
		//
		boolean encontrado = false;
		String tipoResultado = "";
		SnsBdHelper snsBdHelper = new SnsBdHelper ();
		//
		try {
			//logger.debug("INICIO");
			//
			// Para si es el ultimo campo a consultar, en ese caso se escribe en fichero si es no coincidente
			int contadorCampos              = 0;
			int totalCampos                 = mapCamposBusqueda.size();
			//logger.debug("totalCampos: " + totalCampos);
			boolean esUltimElementoConsulta = false;
			//
			//////////////////////////////////////////
			// Para cada linea del fichero se realiza una busqueda por los campos que corresponda
			//
			ResultadosCampoBean resultadosCampoBean = null;
			String tipoCampo                        = "";
			//
			// Se realiza la busqueda del campo CODSNS
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS)) {
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS;
				resultadosCampoBean = mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS);
				//
				if(!Misc.esVacio(datosLecturaBean.getCodUsuarioSns())) {
					SnsBean snsBean = snsBdHelper.getInfoSnsByCodUsuario(accesoBd, datosLecturaBean.getCodUsuarioSns());
					if (snsBean!= null &&  !Misc.esVacio(snsBean.getCodUsuario())) {
						arrayList.add(snsBean);
					}
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;			
				}
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", datosLecturaBean.getCodUsuarioSns(): " + datosLecturaBean.getCodUsuarioSns());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo CODSNS
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD)) {
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD);
				//
				if(!Misc.esVacio(datosLecturaBean.getIdSsalud())) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD;
					SnsBean snsBean = snsBdHelper.getInfoSnsByIdSsalud(accesoBd, datosLecturaBean.getIdSsalud());
					if (snsBean!= null &&  !Misc.esVacio(snsBean.getCodUsuario())) {
						arrayList.add(snsBean);
					}
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;		
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getIdSsalud(): " + datosLecturaBean.getIdSsalud());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            } 
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo DNI
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_DNI)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_DNI;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_DNI);
				//
				if(!Misc.esVacio(datosLecturaBean.getDniNie())) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_DNI;
					arrayList = snsBdHelper.getInfoSnsByDni(accesoBd, datosLecturaBean.getDniNie());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getDniNie(): " + datosLecturaBean.getDniNie());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			//
			//					
			//
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo NAF
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAF)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAF;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAF);
				//
				if(!Misc.esVacio(datosLecturaBean.getNaf())) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAF;
					SnsBean snsBean = snsBdHelper.getInfoSnsByNaf(accesoBd, datosLecturaBean.getNaf());
					if (snsBean!= null &&  !Misc.esVacio(snsBean.getCodUsuario())) {
						arrayList.add(snsBean);
					}
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getNaf(): " + datosLecturaBean.getNaf());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            } 
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo NAF_TITULAR
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR);
				//
				if(!Misc.esVacio(datosLecturaBean.getNafTitular())) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR;
					arrayList = snsBdHelper.getInfoSnsByNaftitular(accesoBd, datosLecturaBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getNafTitular(): " + datosLecturaBean.getNafTitular());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			// Se realiza la busqueda de los campos RAIZ
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene raiz y nafTitular y beneficiario
				if(beneficiario && !Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(nafTitular)) {
					//
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ;
					arrayList = snsBdHelper.getInfoSnsByRaiz(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, accesoBdIndef, fileWriter, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  		
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	
	
	
	
	public boolean consulta (AccesoBd accesoBd, AccesoBdIndef accesoBdIndef, FileWriter fileWriter, ResultadosCampoBean resultadosCampoBean, DatosLecturaBean datosLecturaBean, ArrayList <SnsBean> arrayList, String tipoCampo, String tipoResultado, boolean esUltimElementoConsulta) throws Exception {
		//
		boolean encontrado     = false;  
		boolean encontradoBaja = false;
		SnsBean snsBean        = new SnsBean ();
		//
		try {
			//logger.debug("INICIO");
			//
			int repeticiones   = arrayList.size();
			//
			//logger.debug("repeticiones: " + repeticiones + ", tipoResultado: " + tipoResultado);
			//		
			//// Se busca en el Indice nacional de defunciones
			IndefBdHelper indefBdHelper = new IndefBdHelper ();
			if(esUltimElementoConsulta && !Misc.esVacio(datosLecturaBean.getDniNie())){
				encontradoBaja =  indefBdHelper.getUsuarioInDefByDni (accesoBdIndef, Misc.nz(datosLecturaBean.getDniNie()));
			}
			//
			if (repeticiones == 0) {
				if(tipoResultado.equals( ConstantesBusqueda.TIPO_RESULTADO_VACIOS)) {
					//
					resultadosCampoBean.incrementarVacios();	
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, encontradoBaja);
					}
					//
					if(!Misc.esVacio(InicializacionBusqueda.OBTENER_CAMPOS_VACIOS)) {
						//  VACIOS
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, encontradoBaja);
					}
				}	
				else {
					resultadosCampoBean.incrementarNoEncontrados();
					tipoResultado =  ConstantesBusqueda.TIPO_RESULTADO_NO_ENCONTRADOS;
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoResultado, tipoResultado, encontradoBaja);
					}
				}	
			}
			else {
				//
				if (repeticiones > 1) {
					// DUPLICADOS
					encontrado = true;
					resultadosCampoBean.incrementarDuplicados();
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS;
					this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, encontradoBaja);
				}
				//
				for(int i=0 ; i<arrayList.size() ; i++) {
					snsBean = (SnsBean)arrayList.get(i); 
					//
					//
					//
					// // BUSQUEDA APROXIMADA // //  // // // // // // // // // // // //
					//
					//
					// Situamos fuera de (repeticiones == 1) este codigo pq se utilizara para RESULTADO_DUPLICADOS_ESTUDIO
					ConsultaBean consultaBeanLectura = new ConsultaBean (datosLecturaBean.getNombre(), datosLecturaBean.getApellido1(), datosLecturaBean.getApellido2(), datosLecturaBean.getSexo(), datosLecturaBean.getFechaNac());
					ConsultaBean consultaBeanSns     = new ConsultaBean (snsBean.getNombre(), snsBean.getApellido1(), snsBean.getApellido2(), snsBean.getCodSexo(), snsBean.getFechaNacimiento());
					consultaBeanLectura.incluirCampos (Misc.nz(datosLecturaBean.getDniNie()), Misc.nz(datosLecturaBean.getNaf()));
					consultaBeanSns.incluirCampos(Misc.nz(snsBean.getDniNie()), Misc.nz(snsBean.getNaf()));
					//
					BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
					BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);					
					//
					// Se realiza la busqueda aproximada				
					boolean coincidente  = this.esMismaPersona(busquedaAproxResultadoBean);
					// // //  // // // // // // // //  // // // // // // // // // // // //
					//		
					//
					if (repeticiones == 1) {
						//
						// Si esta en la misma CA y la operacion es por ID_SSALUD,debe ser la misma CA para continuar.
						// sino no es necesario
						String  codCaIsoOrigen  = InicializacionBusqueda.COD_CA_ISO;	
						String  codCaIsoSns     = Misc.nz(snsBean.getCodCaIso());
						//logger.debug("codCaIsoOrigen: " + codCaIsoOrigen + ", codCaIsoSns: " + codCaIsoSns);
						//
						boolean mismaCaParaIdssalud = true;
						//
						// Si el campo es el identificador, tienen q estar en la misma CA, ES OBLIGATORIO, sino, es noCoincidente y se continua buscando	
						if(tipoCampo.equals(ConstantesBusqueda.VALOR_BUSQUEDA_IDSSALUD) && !codCaIsoOrigen.equals(codCaIsoSns)) {
							mismaCaParaIdssalud = false;
						}
						//
						// Si es coincidente para cualquier busqueda de un campo, ademas misma CA si es ID_SSALUD
						if(coincidente && mismaCaParaIdssalud) {
							//
							encontrado = true;
							//
							tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_COINCIDENTES;		
							resultadosCampoBean.incrementarCoincidente();	
							//
							// SE DISTINGUE ENTRE SI EL USUARIO PERTENECE O NO A LA MISMA COMUNIDAD
							//
							if(!Misc.esVacio(InicializacionBusqueda.DISTINGUIR_CA) && !Misc.esVacio(InicializacionBusqueda.COD_CA_ISO)) {
								//
								if(codCaIsoOrigen.equals(codCaIsoSns)) {
									// ENC_COINCIDENTES_MISMA_CA
									resultadosCampoBean.incrementarCoincidenteMismaCA();	
									tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_COINCIDENTES_MISMA_CA;
								}
								else {
									// ENC_COINCIDENTES_DIFERENTE_CA
									resultadosCampoBean.incrementarCoincidenteDiferenteCA();
									tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_COINCIDENTES_DIFERENTE_CA;
								}
								this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
							}
						}
						else {
							// NO_COINCIDENTES
							//
							// 		Si el campo es DNI_NIE comprobamos si el criterio completo contiene a DNI Y NAF
							//      si es asi lo damos como encontrado  sino continua el cruce   
							//
							tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES;
							resultadosCampoBean.incrementarNoCoincidenteTotal();
							//
							if(tipoCampo.equals(ConstantesBusqueda.VALOR_BUSQUEDA_DNI)) {
								//
								if(Misc.nz(busquedaAproxResultadoBean.getCriterioCompleto()).indexOf(ConstantesBusqueda.CRITERIO_COMPLETO_DNI_NAF) != -1 ){
									encontrado = true;
									tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES_DNINAF;
									resultadosCampoBean.incrementarNoCoincidenteDniNaf();	
									this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
								}
								else {				
									//									
									///Se busca en el INSS por DNI si esta activada la busqueda
									if(!Misc.esVacio(InicializacionBusqueda.CONSULTAR_INSS)){
										//
										// Se consulta en el INSS, si el usuario esta vinculado lo marcamos como encontrado
										InssBdHelper inssBdHelper = new InssBdHelper ();
										ArrayList <InssBean> array =  inssBdHelper.getInfoInssByDni (accesoBd, Misc.nz(datosLecturaBean.getDniNie()));
										//
										encontrado = consultaInss (fileWriter, array, snsBean, resultadosCampoBean, datosLecturaBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
									}
									else {
										resultadosCampoBean.incrementarNoCoincidente();	
										this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
									}									
								}
							}
							else {
								if(tipoCampo.equals(ConstantesBusqueda.VALOR_BUSQUEDA_NAF)) {
									//
									tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES;
									//
									// Se busca en el INSS por NAF si esta activada la busqueda
									if(!Misc.esVacio(InicializacionBusqueda.CONSULTAR_INSS)){
										//
										// Se consulta en el INSS, si el usuario esta vinculado lo marcamos como encontrado
										InssBdHelper inssBdHelper = new InssBdHelper ();
										ArrayList <InssBean> array =  inssBdHelper.getInfoInssByNaf(accesoBd, Misc.nz(datosLecturaBean.getNaf()));
										// 							
										encontrado = consultaInss (fileWriter, array, snsBean, resultadosCampoBean, datosLecturaBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
									}
									else {
										resultadosCampoBean.incrementarNoCoincidente();	
										this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
									}								
								}
								else {
									this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, encontradoBaja);
									resultadosCampoBean.incrementarNoCoincidente();	
								}
							}
						}
					}
					else {
						// DUPLICADOS
						resultadosCampoBean.incrementarDuplicadosEstudio();
						escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS_ESTUDIO, encontradoBaja);
					}
				}
			}
			fileWriter.flush();
			//
			resultadosCampoBean.setUltimoCaso(tipoResultado);
			//
			//logger.debug("encontrado: " + encontrado + ", repeticiones: " + repeticiones + ",  " + tipoResultado);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return encontrado;
	}	
	
	
	
	public boolean consultaInss (FileWriter fileWriter, ArrayList <InssBean> arrayList, SnsBean snsBean, ResultadosCampoBean resultadosCampoBean, DatosLecturaBean datosLecturaBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoCampo, String tipoResultado, boolean encontradoBaja) throws Exception {
		//
		boolean encontrado       = false;  
		InssBean inssBean        = new InssBean ();
		String tipoResultadoInss = "";
		//
		try {
			//logger.debug("INICIO");
			//
			int repeticiones   = arrayList.size();
			//
			//logger.debug("repeticiones: " + repeticiones + ", tipoResultado: " + tipoResultado);
			//
			if (repeticiones == 0) {
				//TIPO_RESULTADO_VACIOS No puede ser
				resultadosCampoBean.incrementarNoEncontradosInss();
				tipoResultadoInss =  ConstantesBusqueda.TIPO_RESULTADO_NO_ENCONTRADOS;	
			}
			else {
				//
				if (repeticiones > 1) {
					// DUPLICADOS
					tipoResultadoInss = ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS;
					resultadosCampoBean.incrementarDuplicadosInss();
					this.escribirFichero (fileWriter, datosLecturaBean, snsBean, inssBean, busquedaAproxResultadoBean, null, tipoCampo, tipoResultado, tipoResultadoInss, encontradoBaja);
				}
				//
				for(int i=0 ; i<arrayList.size() ; i++) {
					//
					inssBean = (InssBean)arrayList.get(i); 
					//
					//
					// // BUSQUEDA APROXIMADA // //  // // // // // // // // // // // //
					//
					//
					// Situamos fuera de (repeticiones == 1) este codigo pq se utilizara para RESULTADO_DUPLICADOS_ESTUDIO
					ConsultaBean consultaBeanLectura = new ConsultaBean (datosLecturaBean.getNombre(), datosLecturaBean.getApellido1(), datosLecturaBean.getApellido2(), datosLecturaBean.getSexo(), datosLecturaBean.getFechaNac());
					ConsultaBean consultaBeanSns     = new ConsultaBean (inssBean.getArrayListNafSec(), inssBean.getNombre(), inssBean.getApellido1(), inssBean.getApellido2(), inssBean.getSexo(), inssBean.getFechaNacimientoRaw());
					consultaBeanLectura.incluirCampos (Misc.nz(datosLecturaBean.getDniNie()), Misc.nz(datosLecturaBean.getNaf()));
					consultaBeanSns.incluirCampos(Misc.nz(inssBean.getDniNie()), Misc.nz(inssBean.getNaf()));
					//
					BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
					BusquedaAproxResultadoBean busquedaAproxResultadoBeanInss = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);
					//logger.debug("busquedaAproxResultadoBean: " + busquedaAproxResultadoBean.toString());
					//
					boolean coincidente  = this.esMismaPersona(busquedaAproxResultadoBeanInss);
					// // //  // // // // // // // //  // // // // // // // // // // // //
					//
					if (repeticiones == 1) {	
						//
						if(coincidente) {
							// COINCIDENTES
							//logger.error("inssBean.getCodUsuarioSns(): " + inssBean.getCodUsuarioSns() + ", snsBean.getCodUsuario(): " + snsBean.getCodUsuario());
							//						
							if(!Misc.esVacio(inssBean.getCodUsuarioSns()) && !Misc.esVacio(snsBean.getCodUsuario()) && inssBean.getCodUsuarioSns().equals(snsBean.getCodUsuario())) {
								encontrado = true;
								tipoResultadoInss = ConstantesBusqueda.COINCIDENTES_VINCULADOS_BA;
								resultadosCampoBean.incrementarCoincidenteMismaPersonaInss();	
							}
							else {
								tipoResultadoInss = ConstantesBusqueda.COINCIDENTES_NO_VINCULADOS_BA;
								resultadosCampoBean.incrementarCoincidenteInss();	
							}					
						}
						else {
							// NO_COINCIDENTES
							tipoResultadoInss = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES;
							resultadosCampoBean.incrementarNoCoincidenteInss();
						}
						//this.escribirFichero (fileWriter, snsBean, inssBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado);
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, inssBean, busquedaAproxResultadoBean, busquedaAproxResultadoBeanInss, tipoCampo, tipoResultado, tipoResultadoInss, encontradoBaja);
					}
					else {
						// DUPLICADOS
						tipoResultadoInss = ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS_ESTUDIO;
						resultadosCampoBean.incrementarDuplicadosEstudioInss();
						//this.escribirFichero (fileWriter, snsBean, inssBean, busquedaAproxResultadoBean, tipoCampo, ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS_ESTUDIO);
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, inssBean, busquedaAproxResultadoBean, busquedaAproxResultadoBeanInss, tipoCampo, tipoResultado, tipoResultadoInss, encontradoBaja);
					}
				}
			}
			//fileWriter.flush();
			//
			resultadosCampoBean.setUltimoCaso(tipoResultado);
			//
			//logger.debug("encontrado: " + encontrado + ", repeticiones: " + repeticiones + ",  " + tipoResultado);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return encontrado;
	}	
	
		
	*/
}
