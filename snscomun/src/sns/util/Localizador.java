package sns.util;

import gasai.xml.XmlHelpers;

import java.util.HashMap;

import sns.config.Constantes;
import sns.config.Inicializacion;

public class Localizador implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codSns = "";
	private String codSnsPrevalece = "";
	private String dni = "";
	private String dnidup = "";
	private String pasaporte = "";
	private String naf = "";
	private String naf_titular = "";
	private String nombre = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String fecha_nac = "";
	private String sexo = "";
	private String CA_nac = "";
	private String id_ssalud = "";
	private String cip = ""; // añadido nuevo
	private String codidssalud = ""; // añadido nuevo
	private String pais_nac = "";
	private String CA_prestacion = "";
	// public String CAEXTRANJERO="0";
	private String flag_extranjero = "";
	private String cod_nacionalidad = "";
	private String tarjeta_identidad = "";
	// NUEVO CAMPO DE ESTADO
	private String codEstado = "";
	private String fechaUltMod = "";

	// RDL 16/2012
	private String indicadorDeFarmacia = "";
	private String subIndicadorDeFarmacia = "";

	public Localizador(String codSns, String dni, String dnidup, String pasaporte, String naf, String naf_titular, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo,
			String CA_nac, String id_ssalud, String pais_nac, String CA_prestacion, String flag_extranjero, String cod_nacionalidad, String tarjeta_identidad) {

		this.codSns = codSns;
		this.dni = dni;
		this.dnidup = dnidup;
		this.pasaporte = pasaporte;
		this.naf = naf;
		this.naf_titular = naf_titular;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_nac = fecha_nac;
		this.sexo = sexo;
		this.CA_nac = CA_nac;
		this.id_ssalud = id_ssalud;
		this.pais_nac = pais_nac;
		this.CA_prestacion = CA_prestacion;
		this.flag_extranjero = flag_extranjero;
		this.cod_nacionalidad = cod_nacionalidad;
		this.tarjeta_identidad = tarjeta_identidad;
		// cargarExtranjero();
	}

	public Localizador(String codSns, String dni, String dnidup, String pasaporte, String naf, String naf_titular, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo,
			String CA_nac, String id_ssalud, String flag_extranjero, String cod_nacionalidad, String tarjeta_identidad) {

		this.codSns = codSns;
		this.dni = dni;
		this.dnidup = dnidup;
		this.pasaporte = pasaporte;
		this.naf = naf;
		this.naf_titular = naf_titular;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_nac = fecha_nac;
		this.sexo = sexo;
		this.CA_nac = CA_nac;
		this.id_ssalud = id_ssalud;
		this.flag_extranjero = flag_extranjero;
		this.cod_nacionalidad = cod_nacionalidad;
		this.tarjeta_identidad = tarjeta_identidad;
		// cargarExtranjero();
	}

	public Localizador(String codSns, String dni, String dnidup, String pasaporte, String naf, String naf_titular, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo,
			String CA_nac, String id_ssalud, String pais_nac, String CA_prestacion) {

		this.codSns = codSns;
		this.dni = dni;
		this.dnidup = dnidup;
		this.pasaporte = pasaporte;
		this.naf = naf;
		this.naf_titular = naf_titular;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_nac = fecha_nac;
		this.sexo = sexo;
		this.CA_nac = CA_nac;
		this.id_ssalud = id_ssalud;
		this.pais_nac = pais_nac;
		this.CA_prestacion = CA_prestacion;
		// cargarExtranjero();
	}

	public Localizador(String codSns, String dni, String dnidup, String pasaporte, String naf, String naf_titular, String nombre, String apellido1, String apellido2, String fecha_nac, String sexo,
			String CA_nac, String id_ssalud) {

		this.codSns = codSns;
		this.dni = dni;
		this.dnidup = dnidup;
		this.pasaporte = pasaporte;
		this.naf = naf;
		this.naf_titular = naf_titular;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fecha_nac = fecha_nac;
		this.sexo = sexo;
		this.CA_nac = CA_nac;
		this.id_ssalud = id_ssalud;
		// cargarExtranjero();
	}

	public Localizador() {
		// cargarExtranjero();
	}

	/*
	 * private void cargarExtranjero() { try { Properties defaultProps = new
	 * Properties(); InputStream defaultStream =
	 * getClass().getResourceAsStream("LOCALIZAR.properties");
	 * defaultProps.load(defaultStream); defaultStream.close();
	 * CAEXTRANJERO=defaultProps.getProperty("caNac"); }catch (Exception e) {
	 * System.out.println("Error al cargar el dato de comunidad extranjera");
	 * CAEXTRANJERO="99"; } }
	 */

	public boolean esVacio() {
		return (gasai.util.Misc.esVacio(codSns) && gasai.util.Misc.esVacio(dni) && gasai.util.Misc.esVacio(dnidup) && gasai.util.Misc.esVacio(pasaporte) && gasai.util.Misc.esVacio(naf)
				&& gasai.util.Misc.esVacio(naf_titular) && gasai.util.Misc.esVacio(nombre) && gasai.util.Misc.esVacio(apellido1) && gasai.util.Misc.esVacio(apellido2)
				&& gasai.util.Misc.esVacio(fecha_nac) && gasai.util.Misc.esVacio(sexo) && gasai.util.Misc.esVacio(CA_nac) && gasai.util.Misc.esVacio(id_ssalud));
	}

	public boolean hayRaiz() {
		if (!gasai.util.Misc.esVacio(this.codSns) || !gasai.util.Misc.esVacio(this.id_ssalud)) {
			return false;
		}
		return true;
	}

	public int esValidoToAlta() {
		// if ((!gasai.util.Misc.esVacio(naf) ||
		// !gasai.util.Misc.esVacio(naf_titular)) &&
		// !gasai.util.Misc.esVacio(nombre) &&
		// !gasai.util.Misc.esVacio(apellido1) &&
		// (!gasai.util.Misc.esVacio(apellido2) ||
		// (!gasai.util.Misc.esVacio(CA_nac) && CA_nac.equals(CAEXTRANJERO))) &&
		// !gasai.util.Misc.esVacio(fecha_nac) &&
		// !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
		if ((!Misc.esVacio(naf) || !Misc.esVacio(naf_titular)) 
				&& !gasai.util.Misc.esVacio(nombre) 
				&& !gasai.util.Misc.esVacio(apellido1) 
				&& !gasai.util.Misc.esVacio(fecha_nac)
				&& !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return -1;
		} else {
			if (!gasai.util.Misc.esVacio(nombre))
				return 1;
			if (!gasai.util.Misc.esVacio(apellido1))
				return 2;
			if (!gasai.util.Misc.esVacio(apellido2))
				return 3;
			if (!gasai.util.Misc.esVacio(fecha_nac))
				return 4;
			if (!gasai.util.Misc.esVacio(CA_nac))
				return 5;
			if (!gasai.util.Misc.esVacio(sexo))
				return 6;
			if (!gasai.util.Misc.esVacio(naf))
				return 9;
			if (!gasai.util.Misc.esVacio(naf_titular))
				return 10;
		}
		return -1;
	}
	
	private boolean esValidoNafTitularTitulo(int codTitulo) {
	   	   if ((Misc.esVacio(naf_titular)
	    		   && codTitulo>=Constantes.TITULO_PROPIETARIO_SNS_VALOR_MINIMO
	    		   && codTitulo<=Constantes.TITULO_PROPIETARIO_SNS_VALOR_MAXIMO)
	    	  || !Misc.esVacio(naf_titular)) {
	   		   return true;
	   	   }
	   	   return false;
	}

	public int esValidoToAltaCodTitulo(int codTitulo) {
		// if ((!gasai.util.Misc.esVacio(naf) ||
		// !gasai.util.Misc.esVacio(naf_titular)) &&
		// !gasai.util.Misc.esVacio(nombre) &&
		// !gasai.util.Misc.esVacio(apellido1) &&
		// (!gasai.util.Misc.esVacio(apellido2) ||
		// (!gasai.util.Misc.esVacio(CA_nac) && CA_nac.equals(CAEXTRANJERO))) &&
		// !gasai.util.Misc.esVacio(fecha_nac) &&
		// !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
		if ((!Misc.esVacio(naf) || esValidoNafTitularTitulo(codTitulo)) 
				&& !gasai.util.Misc.esVacio(nombre) 
				&& !gasai.util.Misc.esVacio(apellido1) 
				&& !gasai.util.Misc.esVacio(fecha_nac)
				&& !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return -1;
		} else {
			if (!gasai.util.Misc.esVacio(nombre))
				return 1;
			if (!gasai.util.Misc.esVacio(apellido1))
				return 2;
			if (!gasai.util.Misc.esVacio(apellido2))
				return 3;
			if (!gasai.util.Misc.esVacio(fecha_nac))
				return 4;
			if (!gasai.util.Misc.esVacio(CA_nac))
				return 5;
			if (!gasai.util.Misc.esVacio(sexo))
				return 6;
			if (!gasai.util.Misc.esVacio(naf))
				return 9;
			if (!gasai.util.Misc.esVacio(naf_titular))
				return 10;
		}
		return -1;
	}

	public boolean esValido() {
		if (!gasai.util.Misc.esVacio(this.codSns)) {
			return true;
		}
		if (!gasai.util.Misc.esVacio(this.id_ssalud)) {
			return true;
		}
		if (!gasai.util.Misc.esVacio(this.naf)) {
			return true;
		}

		if ((!gasai.util.Misc.esVacio(dni) && !gasai.util.Misc.esVacio(dnidup) || !gasai.util.Misc.esVacio(pasaporte)) && !gasai.util.Misc.esVacio(naf) && !gasai.util.Misc.esVacio(nombre)
				&& !gasai.util.Misc.esVacio(apellido1) && !gasai.util.Misc.esVacio(fecha_nac) && !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return true;
		}
		if (!gasai.util.Misc.esVacio(dni) && !gasai.util.Misc.esVacio(dnidup) && !gasai.util.Misc.esVacio(naf_titular) && !gasai.util.Misc.esVacio(nombre) && !gasai.util.Misc.esVacio(apellido1)
				&& !gasai.util.Misc.esVacio(fecha_nac) && !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return true;
		}
		if ((!gasai.util.Misc.esVacio(dni) && !gasai.util.Misc.esVacio(dnidup) || !gasai.util.Misc.esVacio(pasaporte) || !gasai.util.Misc.esVacio(naf) || !gasai.util.Misc.esVacio(naf_titular))
				&& !gasai.util.Misc.esVacio(nombre) && !gasai.util.Misc.esVacio(apellido1) && !gasai.util.Misc.esVacio(fecha_nac) && !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return true;
		}
		if (!gasai.util.Misc.esVacio(apellido1) && !gasai.util.Misc.esVacio(nombre) && !gasai.util.Misc.esVacio(fecha_nac) && !gasai.util.Misc.esVacio(CA_nac) && !gasai.util.Misc.esVacio(sexo)) {
			return true;
		}

		return false;
	}

	public String escribirCip(String agente) {
		return escribirCip(agente, "usuario_encontrado");
	}

	public String escribirCip(String agente, String tagUsuario) {
		return escribirCip(agente, tagUsuario, false);
	}
	
	public String escribirNodoUsuario(String codTipoOperacion,String agente, String tagUsuario) {
		if (codTipoOperacion.equals(Constantes.C003)
				|| codTipoOperacion.equals(Constantes.C004) 
				|| codTipoOperacion.equals(Constantes.C006) 
				|| codTipoOperacion.equals(Constantes.C009) 
			) {
			return escribirNodoUsuarioRespuestaConsulta(agente,codTipoOperacion, tagUsuario);
		}else if (codTipoOperacion.equals(Constantes.N004)
				|| codTipoOperacion.equals(Constantes.N005)
				|| codTipoOperacion.equals(Constantes.N010) ) {
			return escribirNodoUsuarioRespuestaAsincrono(agente,codTipoOperacion, tagUsuario);
		}else if (codTipoOperacion.equals(Constantes.N008)) {
			return escribirNodoUsuarioExtranjero(agente,codTipoOperacion, tagUsuario);
		}else if (codTipoOperacion.equals(Constantes.N009)) {
			return escribirNodoUsuarioExtranjero(agente,codTipoOperacion, tagUsuario);
		}else if (codTipoOperacion.equals(Constantes.N006)) {
			return escribirNodoUsuarioTemporalidad(agente, tagUsuario);
		}
		return "";
	}
	
	
	
	private String escribirNodoUsuarioRespuestaAsincrono(String agente, String codTipoOperacion, String tagUsuario) {

		StringBuffer sLocalizador = new StringBuffer();
		sLocalizador.append("<").append(tagUsuario).append(">");
		if (this.codSns != null && (!this.codSns.equals(""))) {
			sLocalizador.append("<sns>").append(this.codSns).append("</sns>");
		}
		if (this.dni != null && (!this.dni.equals(""))) {
			sLocalizador.append("<dni>").append(XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni))).append("</dni>");
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador.append("<dnidup>").append(this.dnidup).append("</dnidup>");
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador.append("<pasaporte>").append(XmlHelpers.getTexto(this.pasaporte)).append("</pasaporte>");
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador.append("<naf>").append(XmlHelpers.getTexto(this.naf)).append("</naf>");
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador.append("<naf_titular>").append(XmlHelpers.getTexto(this.naf_titular)).append("</naf_titular>");
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador.append("<nombre>").append(XmlHelpers.getTexto(this.nombre)).append("</nombre>");
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador.append("<apellido1>").append(XmlHelpers.getTexto(this.apellido1)).append("</apellido1>");
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador.append("<apellido2>").append(XmlHelpers.getTexto(this.apellido2)).append("</apellido2>");
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador.append("<fecha_nac>").append(this.fecha_nac).append("</fecha_nac>");
		}
		if (!Misc.esVacio(this.CA_nac) 
				&& !this.CA_nac.equals("-1") 
				&& !this.CA_nac.equals("0")) {
			sLocalizador.append("<CA_nac>").append(this.CA_nac).append("</CA_nac>");
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador.append("<sexo>").append(this.sexo).append("</sexo>");
		}
		if (this.codidssalud != null && !codidssalud.equals(""))
			sLocalizador.append("<codidssalud>").append(XmlHelpers.getTexto(codidssalud)).append("</codidssalud>");

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1"))) {
			sLocalizador.append("<id_ssalud>").append(XmlHelpers.getTexto(this.id_ssalud)).append("</id_ssalud>");
		}

		HashMap hOperaciones=(HashMap)Inicializacion.H_AGENTES_RDL.get(agente);
		String activada=Misc.nz(hOperaciones.get(codTipoOperacion));
		
		if (activada.equals("1")) {
			//RDL 16/2012
			sLocalizador.append("<farmacia>");
			sLocalizador.append("<indicador_farmacia>").append(Misc.nz(this.indicadorDeFarmacia)).append("</indicador_farmacia>");
			sLocalizador.append("<subindicador_farmacia>").append(Misc.nz(this.subIndicadorDeFarmacia)).append("</subindicador_farmacia>");
			sLocalizador.append("</farmacia>");
		}

		sLocalizador.append("</" + tagUsuario + ">");

		return sLocalizador.toString();
	}

	private String escribirNodoUsuarioTemporalidad(String agente, String tagUsuario) {

		StringBuffer sLocalizador = new StringBuffer();
		sLocalizador.append("<").append(tagUsuario).append(">");
		if (this.codSns != null && (!this.codSns.equals(""))) {
			sLocalizador.append("<sns>").append(this.codSns).append("</sns>");
		}
		if (this.dni != null && (!this.dni.equals(""))) {
			sLocalizador.append("<dni>").append(XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni))).append("</dni>");
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador.append("<dnidup>").append(this.dnidup).append("</dnidup>");
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador.append("<pasaporte>").append(XmlHelpers.getTexto(this.pasaporte)).append("</pasaporte>");
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador.append("<naf>").append(XmlHelpers.getTexto(this.naf)).append("</naf>");
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador.append("<naf_titular>").append(XmlHelpers.getTexto(this.naf_titular)).append("</naf_titular>");
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador.append("<nombre>").append(XmlHelpers.getTexto(this.nombre)).append("</nombre>");
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador.append("<apellido1>").append(XmlHelpers.getTexto(this.apellido1)).append("</apellido1>");
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador.append("<apellido2>").append(XmlHelpers.getTexto(this.apellido2)).append("</apellido2>");
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador.append("<fecha_nac>").append(this.fecha_nac).append("</fecha_nac>");
		}
		if (!Misc.esVacio(this.CA_nac) 
				&& !this.CA_nac.equals("-1") 
				&& !this.CA_nac.equals("0")) {
			sLocalizador.append("<CA_nac>").append(this.CA_nac).append("</CA_nac>");
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador.append("<sexo>").append(this.sexo).append("</sexo>");
		}
		if (this.codidssalud != null && !codidssalud.equals(""))
			sLocalizador.append("<codidssalud>").append(XmlHelpers.getTexto(codidssalud)).append("</codidssalud>");

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1"))) {
			sLocalizador.append("<id_ssalud>").append(XmlHelpers.getTexto(this.id_ssalud)).append("</id_ssalud>");
		}
		sLocalizador.append("<extranjero>").append(Misc.nz(this.flag_extranjero)).append("</extranjero>");
		sLocalizador.append("<nacionalidad>").append(Misc.nz(this.cod_nacionalidad)).append("</nacionalidad>");
		sLocalizador.append("<tarjeta_identidad>").append(XmlHelpers.getTexto(Misc.nz(this.tarjeta_identidad))).append("</tarjeta_identidad>");

		sLocalizador.append("</" + tagUsuario + ">");

		return sLocalizador.toString();
	}
	
	private String escribirNodoUsuarioRespuestaConsulta(String agente,String codTipoOperacion, String tagUsuario){
		StringBuffer sLocalizador = new StringBuffer();
		sLocalizador.append("<").append(tagUsuario).append(">");
		if (!Misc.esVacio(this.codSns)) {
			sLocalizador.append("<sns>").append(this.codSns).append("</sns>");
		}
		if (!Misc.esVacio(this.dni)) {
			sLocalizador.append("<dni>").append(XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni))).append("</dni>");
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador.append("<dnidup>").append(this.dnidup).append("</dnidup>");
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador.append("<pasaporte>").append(XmlHelpers.getTexto(this.pasaporte)).append("</pasaporte>");
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador.append("<naf>").append(XmlHelpers.getTexto(this.naf)).append("</naf>");
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador.append("<naf_titular>").append(XmlHelpers.getTexto(this.naf_titular)).append("</naf_titular>");
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador.append("<nombre>").append(XmlHelpers.getTexto(this.nombre)).append("</nombre>");
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador.append("<apellido1>").append(XmlHelpers.getTexto(this.apellido1)).append("</apellido1>");
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador.append("<apellido2>").append(XmlHelpers.getTexto(this.apellido2)).append("</apellido2>");
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador.append("<fecha_nac>").append(this.fecha_nac).append("</fecha_nac>");
		}
		if (this.CA_nac != null && !this.CA_nac.equals("") && !this.CA_nac.equals("-1") && !this.CA_nac.equals("0")) {
			sLocalizador.append("<CA_nac>").append(this.CA_nac).append("</CA_nac>");
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador.append("<sexo>").append(this.sexo).append("</sexo>");
		}
		if (this.codidssalud != null && !codidssalud.equals(""))
			sLocalizador.append("<codidssalud>").append(XmlHelpers.getTexto(codidssalud)).append("</codidssalud>");

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1")))
			sLocalizador.append("<id_ssalud>").append(XmlHelpers.getTexto(this.id_ssalud)).append("</id_ssalud>");

		if (this.cip != null && (!this.cip.equals("")) && (!this.cip.equals("-1")))
			sLocalizador.append("<cip>").append(XmlHelpers.getTexto(this.cip)).append("</cip>");

		if (this.CA_prestacion != null && (!this.CA_prestacion.equals("")) && (!this.CA_prestacion.equals("-1")))
			sLocalizador.append("<CA_prestacion>").append(this.CA_prestacion).append("</CA_prestacion>");

		if (this.pais_nac != null && (!this.pais_nac.equals("")) && (!this.pais_nac.equals("-1")))
			sLocalizador.append("<pais_nac>").append(this.pais_nac).append("</pais_nac>");

		HashMap hOperaciones=(HashMap)Inicializacion.H_AGENTES_RDL.get(agente);
		String activada=Misc.nz(hOperaciones.get(codTipoOperacion));
		
		if (activada.equals("1")) {
			//RDL 16/2012
			sLocalizador.append("<farmacia>");
			sLocalizador.append("<indicador_farmacia>").append(Misc.nz(this.indicadorDeFarmacia)).append("</indicador_farmacia>");
			sLocalizador.append("<subindicador_farmacia>").append(Misc.nz(this.subIndicadorDeFarmacia)).append("</subindicador_farmacia>");
			sLocalizador.append("</farmacia>");
		}
		
		sLocalizador.append("</").append(tagUsuario).append(">");

		return sLocalizador.toString();
	}
	
	private String escribirNodoUsuarioExtranjero(String agente, String codTipoOperacion, String tagUsuario){
		StringBuffer sLocalizador = new StringBuffer();
		sLocalizador.append("<").append(tagUsuario).append(">");
		if (!Misc.esVacio(this.codSns)) {
			sLocalizador.append("<sns>").append(this.codSns).append("</sns>");
		}
		if (!Misc.esVacio(this.dni)) {
			sLocalizador.append("<dni>").append(XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni))).append("</dni>");
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador.append("<dnidup>").append(this.dnidup).append("</dnidup>");
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador.append("<pasaporte>").append(XmlHelpers.getTexto(this.pasaporte)).append("</pasaporte>");
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador.append("<naf>").append(XmlHelpers.getTexto(this.naf)).append("</naf>");
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador.append("<naf_titular>").append(XmlHelpers.getTexto(this.naf_titular)).append("</naf_titular>");
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador.append("<nombre>").append(XmlHelpers.getTexto(this.nombre)).append("</nombre>");
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador.append("<apellido1>").append(XmlHelpers.getTexto(this.apellido1)).append("</apellido1>");
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador.append("<apellido2>").append(XmlHelpers.getTexto(this.apellido2)).append("</apellido2>");
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador.append("<fecha_nac>").append(this.fecha_nac).append("</fecha_nac>");
		}
		if (this.CA_nac != null && !this.CA_nac.equals("") && !this.CA_nac.equals("-1") && !this.CA_nac.equals("0")) {
			sLocalizador.append("<CA_nac>").append(this.CA_nac).append("</CA_nac>");
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador.append("<sexo>").append(this.sexo).append("</sexo>");
		}
		if (this.codidssalud != null && !codidssalud.equals(""))
			sLocalizador.append("<codidssalud>").append(XmlHelpers.getTexto(codidssalud)).append("</codidssalud>");

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1")))
			sLocalizador.append("<id_ssalud>").append(XmlHelpers.getTexto(this.id_ssalud)).append("</id_ssalud>");

		if (this.cip != null && (!this.cip.equals("")) && (!this.cip.equals("-1")))
			sLocalizador.append("<cip>").append(XmlHelpers.getTexto(this.cip)).append("</cip>");

		if (this.CA_prestacion != null && (!this.CA_prestacion.equals("")) && (!this.CA_prestacion.equals("-1")))
			sLocalizador.append("<CA_prestacion>").append(this.CA_prestacion).append("</CA_prestacion>");

		if (this.pais_nac != null && (!this.pais_nac.equals("")) && (!this.pais_nac.equals("-1")))
			sLocalizador.append("<pais_nac>").append(this.pais_nac).append("</pais_nac>");

		sLocalizador.append("<extranjero>").append(Misc.nz(this.flag_extranjero)).append("</extranjero>");
		sLocalizador.append("<nacionalidad>").append(Misc.nz(this.cod_nacionalidad)).append("</nacionalidad>");
		sLocalizador.append("<tarjeta_identidad>").append(XmlHelpers.getTexto(Misc.nz(this.tarjeta_identidad))).append("</tarjeta_identidad>");

		// NUEVO CAMPO DE ESTADO y DE FECHA ULT MODIFICACION
		// Siempre que viene el estado tiene que venir la fecha_ult_mod
		if (!Misc.esVacio(this.codEstado)) {
			sLocalizador.append("<estado>").append(this.codEstado).append("</estado>");
			if (!Misc.esVacio(this.fechaUltMod)) {
				sLocalizador.append("<fecha_ult_mod>").append(this.fechaUltMod).append("</fecha_ult_mod>");
			}
		}
		
		HashMap hOperaciones=(HashMap)Inicializacion.H_AGENTES_RDL.get(agente);
		String activada=Misc.nz(hOperaciones.get(codTipoOperacion));
		
		if (activada.equals("1")) {
			//RDL 16/2012
			sLocalizador.append("<farmacia>");
			sLocalizador.append("<indicador_farmacia>").append(Misc.nz(this.indicadorDeFarmacia)).append("</indicador_farmacia>");
			sLocalizador.append("<subindicador_farmacia>").append(Misc.nz(this.subIndicadorDeFarmacia)).append("</subindicador_farmacia>");
			sLocalizador.append("</farmacia>");
		}

		sLocalizador.append("</").append(tagUsuario).append(">");

		return sLocalizador.toString();
	}
	
	
	
	// se escribe <usuario> y <usuario_encontrado>
	public String escribirCip(String agente, String tagUsuario, boolean pintarExtranjero) {

		String sLocalizador = "";
		sLocalizador = "<" + tagUsuario + ">";
		if (!Misc.esVacio(this.codSns)) {
			sLocalizador += "<sns>" + this.codSns + "</sns>";
		}
		if (!Misc.esVacio(this.dni)) {
			sLocalizador += "<dni>" + XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni)) + "</dni>";
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador += "<dnidup>" + this.dnidup + "</dnidup>";
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador += "<pasaporte>" + XmlHelpers.getTexto(this.pasaporte) + "</pasaporte>";
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador += "<naf>" + XmlHelpers.getTexto(this.naf) + "</naf>";
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador += "<naf_titular>" + XmlHelpers.getTexto(this.naf_titular) + "</naf_titular>";
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador += "<nombre>" + XmlHelpers.getTexto(this.nombre) + "</nombre>";
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador += "<apellido1>" + XmlHelpers.getTexto(this.apellido1) + "</apellido1>";
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador += "<apellido2>" + XmlHelpers.getTexto(this.apellido2) + "</apellido2>";
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador += "<fecha_nac>" + this.fecha_nac + "</fecha_nac>";
		}
		if (this.CA_nac != null && !this.CA_nac.equals("") && !this.CA_nac.equals("-1") && !this.CA_nac.equals("0")) {
			sLocalizador += "<CA_nac>" + this.CA_nac + "</CA_nac>";
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador += "<sexo>" + this.sexo + "</sexo>";
		}
		if (this.codidssalud != null && !codidssalud.equals(""))
			sLocalizador += "<codidssalud>" + XmlHelpers.getTexto(codidssalud) + "</codidssalud>";

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1")))
			sLocalizador += "<id_ssalud>" + XmlHelpers.getTexto(this.id_ssalud) + "</id_ssalud>";

		if (this.cip != null && (!this.cip.equals("")) && (!this.cip.equals("-1")))
			sLocalizador += "<cip>" + XmlHelpers.getTexto(this.cip) + "</cip>";

		if (this.CA_prestacion != null && (!this.CA_prestacion.equals("")) && (!this.CA_prestacion.equals("-1")))
			sLocalizador += "<CA_prestacion>" + this.CA_prestacion + "</CA_prestacion>";

		if (this.pais_nac != null && (!this.pais_nac.equals("")) && (!this.pais_nac.equals("-1")))
			sLocalizador += "<pais_nac>" + this.pais_nac + "</pais_nac>";

		if (pintarExtranjero) {
			sLocalizador += "<extranjero>" + gasai.util.Misc.nz(this.flag_extranjero) + "</extranjero>";
			sLocalizador += "<nacionalidad>" + gasai.util.Misc.nz(this.cod_nacionalidad) + "</nacionalidad>";
			sLocalizador += "<tarjeta_identidad>" + XmlHelpers.getTexto(gasai.util.Misc.nz(this.tarjeta_identidad)) + "</tarjeta_identidad>";
		}

		// NUEVO CAMPO DE ESTADO y DE FECHA ULT MODIFICACION
		// Siempre que viene el estado tiene que venir la fecha_ult_mod
		if (!gasai.util.Misc.esVacio(this.codEstado)) {
			sLocalizador += "<estado>" + this.codEstado + "</estado>";
			if (!gasai.util.Misc.esVacio(this.fechaUltMod)) {
				sLocalizador += "<fecha_ult_mod>" + this.fechaUltMod + "</fecha_ult_mod>";
			}
		}
		sLocalizador += "</" + tagUsuario + ">";

		return sLocalizador;
	}

	public String escribir(String agente) {
		return escribir(agente, "");
	}

	public String escribir(String agente, String codPrestacion) {
		return escribir(agente, codPrestacion, false);
	}

	public String escribir(String agente, String codPrestacion, boolean userCip) {
		return escribir(agente, codPrestacion, userCip, false, "usuario");
	}

	public String escribir(String agente, String codPrestacion, boolean userCip, boolean pintarExtranjero) {
		return escribir(agente, codPrestacion, userCip, pintarExtranjero, "usuario");
	}

	public String escribir(String agente, String codPrestacion, boolean userCip, String tagUsuario) {
		return escribir(agente, codPrestacion, userCip, false, tagUsuario);
	}

	public String escribir(String agente, String codPrestacion, boolean userCip, boolean pintarExtranjero, String tagUsuario) {

		String sLocalizador = "";
		sLocalizador = "<" + tagUsuario + ">";
		if (this.codSns != null && (!this.codSns.equals(""))) {
			sLocalizador += "<sns>" + this.codSns + "</sns>";
		}
		if (this.dni != null && (!this.dni.equals(""))) {
			sLocalizador += "<dni>" + XmlHelpers.getTexto(Misc.formateoNie(agente, this.dni)) + "</dni>";
		}
		if (this.dnidup != null && (!this.dnidup.equals("")) && (!this.dnidup.equals("-1"))) {
			sLocalizador += "<dnidup>" + this.dnidup + "</dnidup>";
		}
		if (this.pasaporte != null && (!this.pasaporte.equals(""))) {
			sLocalizador += "<pasaporte>" + XmlHelpers.getTexto(this.pasaporte) + "</pasaporte>";
		}
		if (this.naf != null && (!this.naf.equals("")) && (!this.naf.equals("-1"))) {
			sLocalizador += "<naf>" + XmlHelpers.getTexto(this.naf) + "</naf>";
		}
		if (this.naf_titular != null && (!this.naf_titular.equals("")) && (!this.naf_titular.equals("-1"))) {
			sLocalizador += "<naf_titular>" + XmlHelpers.getTexto(this.naf_titular) + "</naf_titular>";
		}
		if (this.nombre != null && (!this.nombre.equals(""))) {
			sLocalizador += "<nombre>" + XmlHelpers.getTexto(this.nombre) + "</nombre>";
		}
		if (this.apellido1 != null && (!this.apellido1.equals(""))) {
			sLocalizador += "<apellido1>" + XmlHelpers.getTexto(this.apellido1) + "</apellido1>";
		}
		if (this.apellido2 != null && (!this.apellido2.equals(""))) {
			sLocalizador += "<apellido2>" + XmlHelpers.getTexto(this.apellido2) + "</apellido2>";
		}
		if (this.fecha_nac != null && (!this.fecha_nac.equals("")) && (!this.fecha_nac.equals("0"))) {
			sLocalizador += "<fecha_nac>" + this.fecha_nac + "</fecha_nac>";
		}
		if (codPrestacion.equals("") && this.CA_nac != null && !this.CA_nac.equals("") && !this.CA_nac.equals("-1") && !this.CA_nac.equals("0")) {
			sLocalizador += "<CA_nac>" + this.CA_nac + "</CA_nac>";
		}
		if (this.sexo != null && (!this.sexo.equals("")) && (!this.sexo.equals("-1"))) {
			sLocalizador += "<sexo>" + this.sexo + "</sexo>";
		}
		if (!codPrestacion.equals(""))
			sLocalizador += "<codidssalud>" + codPrestacion + "</codidssalud>";

		if (this.id_ssalud != null && (!this.id_ssalud.equals("")) && (!this.id_ssalud.equals("-1"))) {
			sLocalizador += "<id_ssalud>" + XmlHelpers.getTexto(this.id_ssalud) + "</id_ssalud>";
		}
		if (userCip) {
			sLocalizador += "<CA_prestacion>" + this.CA_prestacion + "</CA_prestacion>";
			sLocalizador += "<pais_nac>" + this.pais_nac + "</pais_nac>";
		}
		if (pintarExtranjero) {
			sLocalizador += "<extranjero>" + gasai.util.Misc.nz(this.flag_extranjero) + "</extranjero>";
			sLocalizador += "<nacionalidad>" + gasai.util.Misc.nz(this.cod_nacionalidad) + "</nacionalidad>";
			sLocalizador += "<tarjeta_identidad>" + XmlHelpers.getTexto(gasai.util.Misc.nz(this.tarjeta_identidad)) + "</tarjeta_identidad>";
		}
		
		sLocalizador += "</" + tagUsuario + ">";

		return sLocalizador;
	}

	public String getCip() {
		return this.cip;
	}

	public String getCodIdssalud() {
		return this.codidssalud;
	}

	public String getSns() {
		return this.codSns;
	}

	public String getDni() {
		return this.dni;
	}

	public String getDnidup() {
		return this.dnidup;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public String getNaf() {
		return this.naf;
	}

	public String getNaf_titular() {
		return this.naf_titular;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public String getFecha_nac() {
		return this.fecha_nac;
	}

	public String getCa_nac() {
		return this.CA_nac;
	}

	public String getSexo() {
		return this.sexo;
	}

	public String getId_ssalud() {
		return this.id_ssalud;
	}

	public String getTarjeta_identidad() {
		return tarjeta_identidad;
	}

	public String getFlag_extranjero() {
		return flag_extranjero;
	}

	public String getCod_nacionalidad() {
		return cod_nacionalidad;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public String getFechaUltMod() {
		return fechaUltMod;
	}

	public String getCodSnsPrevalece() {
		return codSnsPrevalece;
	}

	public String getPaisNac() {
		return this.pais_nac;
	}

	public String getCaPrestacion() {
		return this.CA_prestacion;
	}

	// Sets
	public void setSns(String codSns) {
		this.codSns = codSns;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public void setCodIdssalud(String codidssalud) {
		this.codidssalud = codidssalud;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setDnidup(String dnidup) {
		this.dnidup = dnidup;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public void setNaf_titular(String naf_titular) {
		this.naf_titular = naf_titular;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public void setCa_nac(String CA_nac) {
		this.CA_nac = CA_nac;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setId_ssalud(String id_ssalud) {
		this.id_ssalud = id_ssalud;
	}

	public void setTarjeta_identidad(String tarjeta_identidad) {
		this.tarjeta_identidad = tarjeta_identidad;
	}

	public void setFlag_extranjero(String flag_extranjero) {
		this.flag_extranjero = flag_extranjero;
	}

	public void setCod_nacionalidad(String cod_nacionalidad) {
		this.cod_nacionalidad = cod_nacionalidad;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public void setFechaUltMod(String fechaUltMod) {
		this.fechaUltMod = fechaUltMod;
	}

	public void setCodSnsPrevalece(String codSnsPrevalece) {
		this.codSnsPrevalece = codSnsPrevalece;
	}

	public void setPaisNac(String paisNac) {
		this.pais_nac = paisNac;
	}

	public void setCaPrestacion(String caPrestacion) {
		this.CA_prestacion = caPrestacion;
	}

	public String getIndicadorDeFarmacia() {
		return indicadorDeFarmacia;
	}

	public void setIndicadorDeFarmacia(String indicadorDeFarmacia) {
		this.indicadorDeFarmacia = indicadorDeFarmacia;
	}

	public String getSubIndicadorDeFarmacia() {
		return subIndicadorDeFarmacia;
	}

	public void setSubIndicadorDeFarmacia(String subIndicadorDeFarmacia) {
		this.subIndicadorDeFarmacia = subIndicadorDeFarmacia;
	}

	public String toString() {
		return escribir("");
	}

}
