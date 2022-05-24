package sns.busqueda.model;

import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bd.AccesoBdIndef;
import sns.comun.bean.DatosLecturaBean;
import sns.comun.bean.InssBean;
import sns.comun.bean.ResultadosCampoBean;
import sns.comun.bean.SnsBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import gasai.util.Misc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


public class BusquedaHelperSns extends BusquedaHelper{

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public void manager(String path, String ficheroOrigen, String ficheroDestino, String codCaIsoOrigen) throws Exception {
		//
		String line = "";
		int bajas             = 0;
		int contadorRegistros = 0;
		//
		AccesoBd accesoBd = null;
		AccesoBdIndef accesoBdIndef = null;
		//
		BufferedReader bufferedReader  = null;
		FileReader fileReader          = null;
		//
		FileWriter fileWriter          = null;
		FileWriter fileWriterNombre    = null;
		//
		LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda = new LinkedHashMap <String, ResultadosCampoBean> ();
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd         = new AccesoBd();
			accesoBdIndef    = new AccesoBdIndef ();
			//
			fileReader       = new FileReader(path + ficheroOrigen);
			bufferedReader   = new BufferedReader(fileReader);
			//
			fileWriter       = new FileWriter(path + ficheroDestino);
			fileWriterNombre = new FileWriter(path + "ficheroNombre.txt");
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
            line = bufferedReader.readLine();
            //
            while (line != null) {
				contadorRegistros++;
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				DatosLecturaBean datosLecturaBean  = new DatosLecturaBean (line, contadorRegistros);
				//if(datosLecturaBean.getRaiz().length()<9) {
				//if(contadorRegistros>5500){
					//logger.debug("xxxxxxxx: " + datosLecturaBean.toLinea());
				//}
            	//}
				//
				boolean encontradoBaja = false;
				//// Se busca en el Indice nacional de defunciones
				IndefBdHelper indefBdHelper = new IndefBdHelper ();
				if(!Misc.esVacio(datosLecturaBean.getDniNie())&& !Misc.nz(datosLecturaBean.getDniNie()).equals("00000000T")){	
					////////////encontradoBaja =  indefBdHelper.getUsuarioInDefByDni (accesoBdIndef, Misc.nz(datosLecturaBean.getDniNie()));
				}
				if(encontradoBaja) {				
					bajas++;
					SnsBean snsBean = new SnsBean ();
					this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, "", "", encontradoBaja);
				}
				else {					
					this.gestionConsultas (accesoBd, fileWriter, fileWriterNombre, mapCamposBusqueda, datosLecturaBean);	
				}
				line = bufferedReader.readLine();
            }
			//
			logger.debug("contadorRegistros: " + contadorRegistros);
			//
			this.verResultados (mapCamposBusqueda, bajas);
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
			try {
				if (fileWriterNombre != null) {
					fileWriterNombre.close();
				}
			}
			catch (Exception e4) {
				fileWriterNombre = null;
			}
		}
	}
	
	
	public void gestionConsultas (AccesoBd accesoBd, FileWriter fileWriter, FileWriter fileWriterNombre, LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda, DatosLecturaBean datosLecturaBean) throws Exception {
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
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo IDSSALUD
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
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
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
				if(!Misc.esVacio(datosLecturaBean.getDniNie()) && !Misc.nz(datosLecturaBean.getDniNie()).equals("00000000T")) {
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
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
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
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
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
				//if(!Misc.esVacio(datosLecturaBean.getNafTitular())) {
				if(!Misc.esVacio(datosLecturaBean.getDniNie())) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR;
					//arrayList = snsBdHelper.getInfoSnsByNaftitular(accesoBd, datosLecturaBean.getNafTitular());
					//arrayList = snsBdHelper.getInfoSnsByNaftitular(accesoBd, datosLecturaBean.getDniNie());
					arrayList = snsBdHelper.getInfoSnsByNaftitular(accesoBd, datosLecturaBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getNafTitular(): " + datosLecturaBean.getNafTitular());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			//
			// Se realiza la busqueda de los campos NAF_TITULAR + RAIZ
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene raiz y nafTitular y beneficiario
				//if(beneficiario && !Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(nafTitular)) {
			    if(!Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(nafTitular)) {
					//
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_RAIZ;
					arrayList = snsBdHelper.getInfoSnsByNafTitRaiz(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  	
			tipoResultado = "";
			//
			//
			/////////////////////////////////////////////////////////////////////////////////   NUEVO
			//
			// Se realiza la busqueda de los campos NAF_TITULAR + NOMBRE
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene nombre y nafTitular y beneficiario
				//if(beneficiario && !Misc.esVacio(datosLecturaBean.getNombre()) && !Misc.esVacio(nafTitular)) {
			    if(!Misc.esVacio(datosLecturaBean.getNombre()) && !Misc.esVacio(nafTitular)) {
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAF_TIT_NOMBRE;
					arrayList = snsBdHelper.getInfoSnsByNafTitNombre(accesoBd, datosLecturaBean.getNombre(), datosLecturaBean.getNafTitular(), datosLecturaBean.getFechaNac());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			//
			// Se realiza la busqueda de los campos RAIZ + NOMBRE
			// 
			//logger.debug("contadorCampos");
			//
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene raiz y nafTitular 
				//if(beneficiario && !Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(nafTitular)) {
			    if(!Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(datosLecturaBean.getNombre())) {
					//
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_NOMBRE;
					arrayList = snsBdHelper.getInfoSnsByRaizNombre(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getNombre(), datosLecturaBean.getFechaNac());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }
			tipoResultado = "";
			//
			//
			// Se realiza la busqueda de los campos RAIZ + APELLIDOS
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene raiz y nafTitular 
				if(!Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(datosLecturaBean.getApellido1()) && !Misc.esVacio(datosLecturaBean.getApellido2())) {
					//
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_RAIZ_APELLIDOS;
					arrayList = snsBdHelper.getInfoSnsByRaizApellidos(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getApellido1() , datosLecturaBean.getApellido2());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  		
			
			
			tipoResultado = "";
			//
			//
			// Se realiza la busqueda de los campos OTROS
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_OTROS)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_OTROS;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_OTROS);
				//
				String naf        = Misc.nz(datosLecturaBean.getNaf());
				String nafTitular = Misc.nz(datosLecturaBean.getNafTitular());
				boolean beneficiario = false;
				if(Misc.esVacio(naf)|| !naf.equals(nafTitular) ){
					beneficiario = true;
				}
				// Miramos si contiene raiz y nafTitular 
				//if(beneficiario && !Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(datosLecturaBean.getApellido1())) {
			    if(!Misc.esVacio(datosLecturaBean.getRaiz()) && !Misc.esVacio(datosLecturaBean.getApellido1())) {
					//
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_OTROS;
					arrayList = snsBdHelper.getInfoSnsByOtros(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getApellido1());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			// Se realiza la busqueda de los campos NAF_TITULAR + Los 4 caracteres de los Apellidos de la RAIZ
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ)) {
				//
				ArrayList <SnsBean> arrayList = new ArrayList <SnsBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				//
				tipoCampo           = "";
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ);
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
					tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ;
					arrayList = snsBdHelper.getInfoSnsByNafTitPrefijoRaiz(accesoBd, datosLecturaBean.getRaiz(), datosLecturaBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getRaiz(): " + datosLecturaBean.getRaiz());
				//
				encontrado = consulta (accesoBd, fileWriter, fileWriterNombre, resultadosCampoBean, datosLecturaBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  	
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	
	
	
	public boolean consulta (AccesoBd accesoBd, FileWriter fileWriter, FileWriter fileWriterNombre, ResultadosCampoBean resultadosCampoBean, DatosLecturaBean datosLecturaBean, ArrayList <SnsBean> arrayList, String tipoCampo, String tipoResultado, boolean esUltimElementoConsulta) throws Exception {
		//
		boolean encontrado     = false;  
		SnsBean snsBean        = new SnsBean ();
		NombreComparacionBean nombreComparacionBean = new NombreComparacionBean ();
		//
		try {
			//logger.debug("INICIO");
			//
			int repeticiones   = arrayList.size();
			//
			//logger.debug("repeticiones: " + repeticiones + ", tipoResultado: " + tipoResultado);
			//
			if (repeticiones == 0) {
				if(tipoResultado.equals( ConstantesBusqueda.TIPO_RESULTADO_VACIOS)) {
					//
					resultadosCampoBean.incrementarVacios();	
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, false);
					}
					//
					if(!Misc.esVacio(InicializacionBusqueda.OBTENER_CAMPOS_VACIOS)) {
						//  VACIOS
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, false);
					}
				}	
				else {
					resultadosCampoBean.incrementarNoEncontrados();
					tipoResultado =  ConstantesBusqueda.TIPO_RESULTADO_NO_ENCONTRADOS;
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoResultado, tipoResultado, false);
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
					this.escribirFichero (fileWriter, datosLecturaBean, snsBean, null, tipoCampo, tipoResultado, false);
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
					// 1) Se realiza la busqueda aproximada
					BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
					BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);
					//
					// 2)  Se comprueban los parametros de que sea la misma persona
					boolean coincidente  = this.esMismaPersona(busquedaAproxResultadoBean);
					//
					// 3)  Si el NOMBRE no es IGUAL ni Aproximado, el registro solo difiere en el NOMBRE
					// Tenemos que realizar una comprobacion para una futura revision de estos registros por las CCAA.
					// Se comprueba el campo NOMBRE por el estudio de los gemelos
					//
					///////////////////////////////if(Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_NOMBRE) == -1) {
					//
					// Si el SEXO es igual se puede realizar el estudio del NOMBRE
					if(Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_COD_SEXO) != -1 &&
					   Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_NOMBRE)   == -1    ) {
						NombreHelper nombreHelper = new NombreHelper ();
						nombreComparacionBean = nombreHelper.managerNombre(consultaBeanLectura.getNombre(), consultaBeanSns.getNombre());
						busquedaAproxResultadoBean.setRevisionNombre(nombreComparacionBean.getRevision());
						//
						nombreComparacionBean.setCodigo(datosLecturaBean.getCodUsuarioSns());
					}
					////////////////////////////////}
					//
					//
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
								this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
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
								
								/*
								if(Misc.nz(busquedaAproxResultadoBean.getCriterioCompleto()).indexOf(ConstantesBusqueda.CRITERIO_COMPLETO_DNI_NAF) != -1 ){
									encontrado = true;
									tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES_DNINAF;
									resultadosCampoBean.incrementarNoCoincidenteDniNaf();	
									this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
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
										encontrado = consultaInss (fileWriter, array, snsBean, resultadosCampoBean, datosLecturaBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
									}
									else {
										resultadosCampoBean.incrementarNoCoincidente();	
										this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
									}									
								}
								
								*/
								
								//									
								///Se busca en el INSS por DNI si esta activada la busqueda
								if(!Misc.esVacio(InicializacionBusqueda.CONSULTAR_INSS)){
									//
									// Se consulta en el INSS, si el usuario esta vinculado lo marcamos como encontrado
									InssBdHelper inssBdHelper = new InssBdHelper ();
									ArrayList <InssBean> array =  inssBdHelper.getInfoInssByDni (accesoBd, Misc.nz(datosLecturaBean.getDniNie()));
									//
									encontrado = consultaInss (fileWriter, array, snsBean, resultadosCampoBean, datosLecturaBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
								}
								else {
									resultadosCampoBean.incrementarNoCoincidente();	
									this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
								}										
								//  Se comenta para que si es no coincidente avance, y se compare con el NAF
								//
								//encontrado = true;
								//
								//
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
										encontrado = consultaInss (fileWriter, array, snsBean, resultadosCampoBean, datosLecturaBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
									}
									else {
										resultadosCampoBean.incrementarNoCoincidente();	
										this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
									}								
								}
								else {
									this.escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado, false);
									resultadosCampoBean.incrementarNoCoincidente();	
								}
							}
						}
					}
					else {
						// DUPLICADOS
						resultadosCampoBean.incrementarDuplicadosEstudio();
						escribirFichero (fileWriter, datosLecturaBean, snsBean, busquedaAproxResultadoBean, tipoCampo, ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS_ESTUDIO, false);
					}
				}
			}
			fileWriter.flush();
			//
			resultadosCampoBean.setUltimoCaso(tipoResultado);
			//
			if(encontrado) {
				//fileWriterNombre
				if(!Misc.esVacio(nombreComparacionBean.getCodigo())) {
					fileWriterNombre.write(nombreComparacionBean.write());
					fileWriterNombre.flush();
				}
			}
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
				this.escribirFichero (fileWriter, datosLecturaBean, snsBean, inssBean, busquedaAproxResultadoBean, null, tipoCampo, tipoResultado, tipoResultadoInss, encontradoBaja);
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
					// 1)
					BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
					BusquedaAproxResultadoBean busquedaAproxResultadoBeanInss = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);
					//logger.debug("busquedaAproxResultadoBean: " + busquedaAproxResultadoBean.toString());
					// 2)
					boolean coincidente = this.esMismaPersona(busquedaAproxResultadoBeanInss);
					//
					// 3)  Si el NOMBRE no es IGUAL ni Aproximado, el registro solo difiere en el NOMBRE
					// Tenemos que realizar una comprobacion para una futura revision de estos registros por las CCAA.
					// Se comprueba el campo NOMBRE por el estudio de los gemelos
					NombreComparacionBean nombreComparacionBean = new NombreComparacionBean ();
					if(Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_NOMBRE) == -1) {
						NombreHelper nombreHelper = new NombreHelper ();
						nombreComparacionBean = nombreHelper.managerNombre(consultaBeanLectura.getNombre(), consultaBeanSns.getNombre());
						busquedaAproxResultadoBeanInss.setRevisionNombre(nombreComparacionBean.getRevision());
						//
						nombreComparacionBean.setCodigo(datosLecturaBean.getCodUsuarioSns());
					}	
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
	
		
	
}
