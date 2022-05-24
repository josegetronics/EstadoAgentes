package sns.comun.bean.busqueda;

import org.apache.log4j.Logger;
import sns.ba.config.InicializacionBA;
import sns.comun.config.ConstantesBusqueda;
import sns.util.Misc;


public class NombreComparacionBean {

	
	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);
		
	private String codigo                       = "";
	
	private String nombreOriginal_1             = "";
	private String nombreOriginal_2             = "";
	//
	private String nombre_1                     = "";
	private String nombre_2                     = "";
	//
	private int contadorPosicionIguales_1       = 0;
	private int porcentajePosicionIguales_1     = 0;
	private int contadorLetrasIguales_1         = 0;
	private int porcentajeLetrasIguales_1       = 0;
	//
	private int contadorPosicionIguales_2       = 0;
	private int porcentajePosicionIguales_2     = 0;
	private int contadorLetrasIguales_2         = 0;
	private int porcentajeLetrasIguales_2       = 0;
	//
	// Global
	private int contadorPosicionIguales         = 0;
	private int porcentajePosicionIguales       = 0;
	private int contadorLetrasIguales           = 0;
	private int porcentajeLetrasIguales         = 0;
	// No tiene en cuenta el genero
	private int inclusionCadenaAproximadas      = 0; 
	//
	// Consonantes
	private String cadenaConsonantes1           = "";
	private String cadenaConsonantes2           = "";
	//
	private int contadorConsonantesOrden_1      = 0;
	private int contadorConsonantesOrden_2      = 0;
	private int porcentajeConsonantesOrden_1    = 0;
	private int porcentajeConsonantesOrden_2    = 0;
	private int porcentajeConsonantesOrden      = 0;
	//
	private int contadorConsonantesIguales_1    = 0;
	private int contadorConsonantesIguales_2    = 0;
	private int porcentajeConsonantesIguales_1  = 0;
	private int porcentajeConsonantesIguales_2  = 0;
	private int porcentajeConsonantesIguales    = 0;
	//
	private int consonantesInclusion            = 0;
	//
	//
	private int primeraLetraIgual               = 0;
	private int ultimaLetraPalabrasSimples      = 0;
	//
	private int revisionInt                      = 0;
	private String revision                     = "";
	
	
	public NombreComparacionBean  (){
		primeraLetraIgual = ConstantesBusqueda.PRIMERA_LETRA_DIFERENTE;
		this.revisionInt  = ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_NO_IDENTIFICADOS;
		this.revision     = ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_NO_IDENTIFICADOS;
	}
	
	
	public NombreComparacionBean  (String nombre, String nombreComparar){
		this.nombreOriginal_1       = Misc.nz(nombre);
		this.nombreOriginal_2       = Misc.nz(nombreComparar);
		//
		this.nombre_1               = Misc.nz(nombre);
		this.nombre_2               = Misc.nz(nombreComparar);
		//
		//////////// primeraLetraIgual           = ConstantesBusqueda.PRIMERA_LETRA_DIFERENTE;
		//////////// ultimaLetraPalabrasSimples  = ConstantesBusqueda.ULTIMA_LETRA_IGUAL;
		//
		primeraLetraIgual           = ConstantesBusqueda.PRIMERA_LETRA_DIFERENTE;
		ultimaLetraPalabrasSimples  = ConstantesBusqueda.ULTIMA_LETRA_DIFERENTE;
		//
		this.revisionInt            = ConstantesBusqueda.RESULTADO_COMPARACION_NOMBRE_NO_IDENTIFICADOS;
		this.revision               = ConstantesBusqueda.RESUL_STR_COMPARACION_NOMBRE_NO_IDENTIFICADOS;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	

	public int getPrimeraLetraIgual() {
		return primeraLetraIgual;
	}


	public void setPrimeraLetraIgual(int primeraLetraIgual) {
		this.primeraLetraIgual = primeraLetraIgual;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public int getInclusionCadenaAproximadas() {
		return inclusionCadenaAproximadas;
	}


	public void setInclusionCadenaAproximadas(int inclusionCadenaAproximadas) {
		this.inclusionCadenaAproximadas = inclusionCadenaAproximadas;
	}

	public String getNombreOriginal_1() {
		return nombreOriginal_1;
	}


	public void setNombreOriginal_1(String nombreOriginal_1) {
		this.nombreOriginal_1 = nombreOriginal_1;
	}


	public String getNombreOriginal_2() {
		return nombreOriginal_2;
	}


	public void setNombreOriginal_2(String nombreOriginal_2) {
		this.nombreOriginal_2 = nombreOriginal_2;
	}


	public String getNombre_1() {
		return nombre_1;
	}


	public void setNombre_1(String nombre_1) {
		this.nombre_1 = nombre_1;
	}


	public String getNombre_2() {
		return nombre_2;
	}


	public void setNombre_2(String nombre_2) {
		this.nombre_2 = nombre_2;
	}


	public int getContadorPosicionIguales_1() {
		return contadorPosicionIguales_1;
	}


	public void setContadorPosicionIguales_1(int contadorPosicionIguales_1) {
		this.contadorPosicionIguales_1 = contadorPosicionIguales_1;
	}


	public int getPorcentajePosicionIguales_1() {
		return porcentajePosicionIguales_1;
	}


	public void setPorcentajePosicionIguales_1(int porcentajePosicionIguales_1) {
		this.porcentajePosicionIguales_1 = porcentajePosicionIguales_1;
	}


	public int getContadorLetrasIguales_1() {
		return contadorLetrasIguales_1;
	}


	public void setContadorLetrasIguales_1(int contadorLetrasIguales_1) {
		this.contadorLetrasIguales_1 = contadorLetrasIguales_1;
	}


	public int getPorcentajeLetrasIguales_1() {
		return porcentajeLetrasIguales_1;
	}


	public void setPorcentajeLetrasIguales_1(int porcentajeLetrasIguales_1) {
		this.porcentajeLetrasIguales_1 = porcentajeLetrasIguales_1;
	}


	public int getContadorPosicionIguales_2() {
		return contadorPosicionIguales_2;
	}


	public void setContadorPosicionIguales_2(int contadorPosicionIguales_2) {
		this.contadorPosicionIguales_2 = contadorPosicionIguales_2;
	}


	public int getPorcentajePosicionIguales_2() {
		return porcentajePosicionIguales_2;
	}


	public void setPorcentajePosicionIguales_2(int porcentajePosicionIguales_2) {
		this.porcentajePosicionIguales_2 = porcentajePosicionIguales_2;
	}


	public int getContadorLetrasIguales_2() {
		return contadorLetrasIguales_2;
	}


	public void setContadorLetrasIguales_2(int contadorLetrasIguales_2) {
		this.contadorLetrasIguales_2 = contadorLetrasIguales_2;
	}


	public int getPorcentajeLetrasIguales_2() {
		return porcentajeLetrasIguales_2;
	}


	public void setPorcentajeLetrasIguales_2(int porcentajeLetrasIguales_2) {
		this.porcentajeLetrasIguales_2 = porcentajeLetrasIguales_2;
	}
	
	
	public int getContadorPosicionIguales() {
		return contadorPosicionIguales;
	}


	public void setContadorPosicionIguales(int contadorPosicionIguales) {
		this.contadorPosicionIguales = contadorPosicionIguales;
	}


	public int getPorcentajePosicionIguales() {
		return porcentajePosicionIguales;
	}


	public void setPorcentajePosicionIguales(int porcentajePosicionIguales) {
		this.porcentajePosicionIguales = porcentajePosicionIguales;
	}


	public int getContadorLetrasIguales() {
		return contadorLetrasIguales;
	}


	public void setContadorLetrasIguales(int contadorLetrasIguales) {
		this.contadorLetrasIguales = contadorLetrasIguales;
	}


	public int getPorcentajeLetrasIguales() {
		return porcentajeLetrasIguales;
	}


	public void setPorcentajeLetrasIguales(int porcentajeLetrasIguales) {
		this.porcentajeLetrasIguales = porcentajeLetrasIguales;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	
	public String getCadenaConsonantes1() {
		return cadenaConsonantes1;
	}


	public void setCadenaConsonantes1(String cadenaConsonantes1) {
		this.cadenaConsonantes1 = cadenaConsonantes1;
	}


	public String getCadenaConsonantes2() {
		return cadenaConsonantes2;
	}


	public void setCadenaConsonantes2(String cadenaConsonantes2) {
		this.cadenaConsonantes2 = cadenaConsonantes2;
	}


	public int getContadorConsonantesIguales_1() {
		return contadorConsonantesIguales_1;
	}


	public void setContadorConsonantesIguales_1(int contadorConsonantesIguales_1) {
		this.contadorConsonantesIguales_1 = contadorConsonantesIguales_1;
	}


	public int getContadorConsonantesIguales_2() {
		return contadorConsonantesIguales_2;
	}


	public void setContadorConsonantesIguales_2(int contadorConsonantesIguales_2) {
		this.contadorConsonantesIguales_2 = contadorConsonantesIguales_2;
	}


	public int getPorcentajeConsonantesIguales_1() {
		return porcentajeConsonantesIguales_1;
	}


	public void setPorcentajeConsonantesIguales_1(int porcentajeConsonantesIguales_1) {
		this.porcentajeConsonantesIguales_1 = porcentajeConsonantesIguales_1;
	}


	public int getPorcentajeConsonantesIguales_2() {
		return porcentajeConsonantesIguales_2;
	}


	public void setPorcentajeConsonantesIguales_2(int porcentajeConsonantesIguales_2) {
		this.porcentajeConsonantesIguales_2 = porcentajeConsonantesIguales_2;
	}
	

	public int getPorcentajeConsonantesIguales() {
		return porcentajeConsonantesIguales;
	}


	public void setPorcentajeConsonantesIguales(int porcentajeConsonantesIguales) {
		this.porcentajeConsonantesIguales = porcentajeConsonantesIguales;
	}
	
	
	public int getContadorConsonantesOrden_1() {
		return contadorConsonantesOrden_1;
	}


	public void setContadorConsonantesOrden_1(int contadorConsonantesOrden_1) {
		this.contadorConsonantesOrden_1 = contadorConsonantesOrden_1;
	}


	public int getContadorConsonantesOrden_2() {
		return contadorConsonantesOrden_2;
	}


	public void setContadorConsonantesOrden_2(int contadorConsonantesOrden_2) {
		this.contadorConsonantesOrden_2 = contadorConsonantesOrden_2;
	}


	public int getPorcentajeConsonantesOrden_1() {
		return porcentajeConsonantesOrden_1;
	}


	public void setPorcentajeConsonantesOrden_1(int porcentajeConsonantesOrden_1) {
		this.porcentajeConsonantesOrden_1 = porcentajeConsonantesOrden_1;
	}


	public int getPorcentajeConsonantesOrden_2() {
		return porcentajeConsonantesOrden_2;
	}


	public void setPorcentajeConsonantesOrden_2(int porcentajeConsonantesOrden_2) {
		this.porcentajeConsonantesOrden_2 = porcentajeConsonantesOrden_2;
	}


	public int getPorcentajeConsonantesOrden() {
		return porcentajeConsonantesOrden;
	}


	public void setPorcentajeConsonantesOrden(int porcentajeConsonantesOrden) {
		this.porcentajeConsonantesOrden = porcentajeConsonantesOrden;
	}	
	
	
	public int getConsonantesInclusion() {
		return consonantesInclusion;
	}


	public void setConsonantesInclusion(int consonantesInclusion) {
		this.consonantesInclusion = consonantesInclusion;
	}
	
	public int getRevisionInt() {
		return revisionInt;
	}


	public void setRevisionInt(int revisionInt) {
		this.revisionInt = revisionInt;
	}


	public String getRevision() {
		return revision;
	}


	public void setRevision(String revision) {
		this.revision = revision;
	}
	
	
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	



	public int getUltimaLetraPalabrasSimples() {
		return ultimaLetraPalabrasSimples;
	}


	public void setUltimaLetraPalabrasSimples(int ultimaLetraPalabrasSimples) {
		this.ultimaLetraPalabrasSimples = ultimaLetraPalabrasSimples;
	}


	public void view() throws Exception {
		//
		try {
			logger.debug("INICIO");
			//
			logger.debug("codigo: ]" + codigo + "[");
			//
			logger.debug("nombreOriginal_1: ]" + nombreOriginal_1 + "[");
			logger.debug("nombreOriginal_2: ]" + nombreOriginal_2 + "[");
			//
			logger.debug("nombre_1: ]" + this.nombre_1 + "[");
			logger.debug("nombre_2: ]" + this.nombre_2 + "[");
			//logger.debug("nombre_1:         ]" + nombre_1 + "[" +  ", longitud: " + nombre_1.length());
			//logger.debug("nombre_2:         ]" + nombre_2 + "[" +  ", longitud: " + nombre_2.length());
			//
			//logger.debug("revisionInt:   " + revisionInt + ", revision:" + revision);
			logger.debug("revision:" + revision);
			//
			logger.debug("primeraLetraIgual:          " + primeraLetraIgual);
			logger.debug("ultimaLetraPalabrasSimples: " + ultimaLetraPalabrasSimples);
			//
			logger.debug("inclusionCadenaAproximadas: " + inclusionCadenaAproximadas);			
			//
			
			logger.debug("-------------------------------------------------------------");
			//
			logger.debug("cadenaConsonantes1: " + this.getCadenaConsonantes1() + ",tamaño: " + cadenaConsonantes1.length());
			logger.debug("cadenaConsonantes2: " + cadenaConsonantes2 + ",tamaño: " + cadenaConsonantes2.length());
			//
			
			logger.debug("--------- Consonantes Orden ");
			logger.debug("contadorConsonantesOrden_1: " + contadorConsonantesOrden_1 + ",porcentajeConsonantesOrden_1: " + porcentajeConsonantesOrden_1);
			logger.debug("contadorConsonantesOrden_2: " + contadorConsonantesOrden_2 + ",porcentajeConsonantesOrden_2: " + porcentajeConsonantesOrden_2);
			logger.debug("porcentajeConsonantesOrden: " + porcentajeConsonantesOrden);		
			//			
			logger.debug("--------- Consonantes Iguales ");
			logger.debug("contadorConsonantesIguales_1: " + contadorConsonantesIguales_1 + ",porcentajeConsonantesIguales_1: " + porcentajeConsonantesIguales_1);
			logger.debug("contadorConsonantesIguales_2: " + contadorConsonantesIguales_2 + ",porcentajeConsonantesIguales_2: " + porcentajeConsonantesIguales_2);
			logger.debug("porcentajeConsonantesIguales: " + porcentajeConsonantesIguales);		
			//
			logger.debug("consonantesInclusion: " + consonantesInclusion);	
			//
			
			logger.debug("-------------------------------------------------------------");
			//
			logger.debug("contadorPosicionIguales_1: " + contadorPosicionIguales_1 + ", porcentajePosicionIguales_1: " + porcentajePosicionIguales_1);
			logger.debug("contadorLetrasIguales_1:   " + contadorLetrasIguales_1   + ", porcentajeLetrasIguales_1:   " + porcentajeLetrasIguales_1);
			//
			logger.debug("contadorPosicionIguales_2: " + contadorPosicionIguales_2 + ", porcentajePosicionIguales_2: " + porcentajePosicionIguales_2);
			logger.debug("contadorLetrasIguales_2:   " + contadorLetrasIguales_2   + ", porcentajeLetrasIguales_2:   " + porcentajeLetrasIguales_2);
			//
			logger.debug("-------------------------------------------------------------");
			//
			logger.debug("contadorPosicionIguales: " + contadorPosicionIguales + ", porcentajePosicionIguales: " + porcentajePosicionIguales);
			logger.debug("contadorLetrasIguales:   " + contadorLetrasIguales   + ", porcentajeLetrasIguales:   " + porcentajeLetrasIguales);
			//
			logger.debug("-------------------------------------------------------------");
			logger.debug("-------------------------------------------------------------");
			
			logger.debug("");
			logger.debug("");
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	public String write() {
		//
		StringBuffer str = new StringBuffer ();
		//
		try {
			str.append(this.getCodigo()                       + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombreOriginal_1()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_1()                     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_1().length()            + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getNombreOriginal_2()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_2()                     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_2().length()            + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales_1()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales_1()  + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales_1()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales_1()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales_2()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales_2()  + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales_2()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales_2()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales()        + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			//
			// Consonantes
			str.append(this.getCadenaConsonantes1()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getCadenaConsonantes2()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorConsonantesOrden_1()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorConsonantesOrden_2()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden_1()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden_2()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorConsonantesIguales_1()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorConsonantesIguales_2()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales_1() + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales_2() + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getConsonantesInclusion()           + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			// La primera Letra es Igual
			str.append(this.getPrimeraLetraIgual()              + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getUltimaLetraPalabrasSimples()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getInclusionCadenaAproximadas()     + ConstantesBusqueda.SEPARADOR_CAMPOS);  ////////////////  NUEVO
			//
			// Encontrado
			str.append(this.getRevisionInt()                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			//
			str.append("\n");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return str.toString();
	}
	
	public String write2() {
		//
		StringBuffer str = new StringBuffer ();
		//
		try {
			str.append(this.getCodigo()                       + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombreOriginal_1()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_1()                     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_1().length()            + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getNombreOriginal_2()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_2()                     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getNombre_2().length()            + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales_1()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales_1()  + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales_1()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales_1()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales_2()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales_2()  + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales_2()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales_2()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorPosicionIguales()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajePosicionIguales()    + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorLetrasIguales()        + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeLetrasIguales()      + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			//
			// Consonantes
			str.append(this.getCadenaConsonantes1()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getCadenaConsonantes2()             + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorConsonantesOrden_1()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorConsonantesOrden_2()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden_1()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden_2()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesOrden()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getContadorConsonantesIguales_1()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getContadorConsonantesIguales_2()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales_1() + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales_2() + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getPorcentajeConsonantesIguales()   + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getConsonantesInclusion()           + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			// La primera Letra es Igual
			str.append(this.getPrimeraLetraIgual()              + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.getUltimaLetraPalabrasSimples()     + ConstantesBusqueda.SEPARADOR_CAMPOS);
			//
			str.append(this.getInclusionCadenaAproximadas()     + ConstantesBusqueda.SEPARADOR_CAMPOS);  ////////////////  NUEVO
			//
			// Encontrado
			str.append(this.getRevisionInt()                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return str.toString();
	}
	
	
	
}
