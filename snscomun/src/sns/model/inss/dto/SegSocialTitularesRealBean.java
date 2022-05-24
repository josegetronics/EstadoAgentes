package sns.model.inss.dto;

/******************************************************
 * / Clase generada con GeneraBean de gasai.jar Tue Jun 26 11:40:59 CEST 2012 /
 ******************************************************/
public class SegSocialTitularesRealBean {

	private boolean vacio = false;
	private java.math.BigDecimal numLinea;
	private java.lang.String tipoIdentificador;
	private java.lang.String numeroIdentificador;
	private java.lang.String dniNie;
	private java.lang.String pasaporte;
	private java.lang.String naf;
	private java.lang.String nafSec1;
	private java.lang.String nafSec2;
	private java.lang.String nafSec3;
	private java.lang.String nafSec4;
	private java.lang.String nafSec5;
	private java.lang.String nafSec6;
	private java.lang.String nafSec7;
	private java.lang.String nafSec8;
	private java.lang.String nafSec9;
	private java.lang.String indicativoNombre;
	private java.lang.String apellidosNombre;
	private java.lang.String apellido1;
	private java.lang.String apellido2;
	private java.lang.String nombre;
	private java.lang.String nacionalidad;
	private java.lang.String codSexo;
	private java.lang.String fechaNac;
	private java.lang.String codigoFarmacia;
	private java.lang.String codigoLimiteMensual;
	private java.lang.String grupoAseguramiento;
	private java.lang.String comunidadAutonoma;
	private java.lang.String raizap;
	private java.math.BigDecimal idUnico;
	private java.math.BigDecimal nafRepetido;

	public static final String NOMBRE_TABLA = "SEGSOCIAL_TITULARES_REAL";

	public static final String QUERY_DELETE = "DELETE FROM " + NOMBRE_TABLA + "";

	public static final String INDICE_UPDATE_NUM_LINEA = "1";
	public static final String INDICE_UPDATE_TIPO_IDENTIFICADOR = "2";
	public static final String INDICE_UPDATE_NUMERO_IDENTIFICADOR = "3";
	public static final String INDICE_UPDATE_DNI_NIE = "4";
	public static final String INDICE_UPDATE_PASAPORTE = "5";
	public static final String INDICE_UPDATE_NAF = "6";
	public static final String INDICE_UPDATE_NAF_SEC1 = "7";
	public static final String INDICE_UPDATE_NAF_SEC2 = "8";
	public static final String INDICE_UPDATE_NAF_SEC3 = "9";
	public static final String INDICE_UPDATE_NAF_SEC4 = "10";
	public static final String INDICE_UPDATE_NAF_SEC5 = "11";
	public static final String INDICE_UPDATE_NAF_SEC6 = "12";
	public static final String INDICE_UPDATE_NAF_SEC7 = "13";
	public static final String INDICE_UPDATE_NAF_SEC8 = "14";
	public static final String INDICE_UPDATE_NAF_SEC9 = "15";
	public static final String INDICE_UPDATE_INDICATIVO_NOMBRE = "16";
	public static final String INDICE_UPDATE_APELLIDOS_NOMBRE = "17";
	public static final String INDICE_UPDATE_APELLIDO1 = "18";
	public static final String INDICE_UPDATE_APELLIDO2 = "19";
	public static final String INDICE_UPDATE_NOMBRE = "20";
	public static final String INDICE_UPDATE_NACIONALIDAD = "21";
	public static final String INDICE_UPDATE_COD_SEXO = "22";
	public static final String INDICE_UPDATE_FECHA_NAC = "23";
	public static final String INDICE_UPDATE_CODIGO_FARMACIA = "24";
	public static final String INDICE_UPDATE_CODIGO_LIMITE_MENSUAL = "25";
	public static final String INDICE_UPDATE_GRUPO_ASEGURAMIENTO = "26";
	public static final String INDICE_UPDATE_COMUNIDAD_AUTONOMA = "27";
	public static final String INDICE_UPDATE_RAIZAP = "28";
	public static final String INDICE_UPDATE_ID_UNICO = "29";
	public static final String INDICE_UPDATE_NAF_REPETIDO = "30";
	public static final String QUERY_UPDATE = "UPDATE "
			+ NOMBRE_TABLA
			+ " set NUM_LINEA = ?, TIPO_IDENTIFICADOR = ?, NUMERO_IDENTIFICADOR = ?, DNI_NIE = ?, PASAPORTE = ?, NAF = ?, NAF_SEC1 = ?, NAF_SEC2 = ?, NAF_SEC3 = ?, NAF_SEC4 = ?, NAF_SEC5 = ?, NAF_SEC6 = ?, NAF_SEC7 = ?, NAF_SEC8 = ?, NAF_SEC9 = ?, INDICATIVO_NOMBRE = ?, APELLIDOS_NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, NOMBRE = ?, NACIONALIDAD = ?, COD_SEXO = ?, FECHA_NAC = ?, CODIGO_FARMACIA = ?, CODIGO_LIMITE_MENSUAL = ?, GRUPO_ASEGURAMIENTO = ?, COMUNIDAD_AUTONOMA = ?, RAIZAP = ?, ID_UNICO = ?, NAF_REPETIDO = ?";

	public static final String INDICE_INSERT_NUM_LINEA = "1";
	public static final String INDICE_INSERT_TIPO_IDENTIFICADOR = "2";
	public static final String INDICE_INSERT_NUMERO_IDENTIFICADOR = "3";
	public static final String INDICE_INSERT_DNI_NIE = "4";
	public static final String INDICE_INSERT_PASAPORTE = "5";
	public static final String INDICE_INSERT_NAF = "6";
	public static final String INDICE_INSERT_NAF_SEC1 = "7";
	public static final String INDICE_INSERT_NAF_SEC2 = "8";
	public static final String INDICE_INSERT_NAF_SEC3 = "9";
	public static final String INDICE_INSERT_NAF_SEC4 = "10";
	public static final String INDICE_INSERT_NAF_SEC5 = "11";
	public static final String INDICE_INSERT_NAF_SEC6 = "12";
	public static final String INDICE_INSERT_NAF_SEC7 = "13";
	public static final String INDICE_INSERT_NAF_SEC8 = "14";
	public static final String INDICE_INSERT_NAF_SEC9 = "15";
	public static final String INDICE_INSERT_INDICATIVO_NOMBRE = "16";
	public static final String INDICE_INSERT_APELLIDOS_NOMBRE = "17";
	public static final String INDICE_INSERT_APELLIDO1 = "18";
	public static final String INDICE_INSERT_APELLIDO2 = "19";
	public static final String INDICE_INSERT_NOMBRE = "20";
	public static final String INDICE_INSERT_NACIONALIDAD = "21";
	public static final String INDICE_INSERT_COD_SEXO = "22";
	public static final String INDICE_INSERT_FECHA_NAC = "23";
	public static final String INDICE_INSERT_CODIGO_FARMACIA = "24";
	public static final String INDICE_INSERT_CODIGO_LIMITE_MENSUAL = "25";
	public static final String INDICE_INSERT_GRUPO_ASEGURAMIENTO = "26";
	public static final String INDICE_INSERT_COMUNIDAD_AUTONOMA = "27";
	public static final String INDICE_INSERT_RAIZAP = "28";
	public static final String INDICE_INSERT_ID_UNICO = "29";
	public static final String INDICE_INSERT_NAF_REPETIDO = "30";
	public static final String QUERY_INSERT = "INSERT INTO SEGSOCIAL_TITULARES_REAL (NUM_LINEA,TIPO_IDENTIFICADOR,NUMERO_IDENTIFICADOR,DNI_NIE,PASAPORTE,NAF,NAF_SEC1,NAF_SEC2,NAF_SEC3,NAF_SEC4,NAF_SEC5,NAF_SEC6,NAF_SEC7,NAF_SEC8,NAF_SEC9,INDICATIVO_NOMBRE,APELLIDOS_NOMBRE,APELLIDO1,APELLIDO2,NOMBRE,NACIONALIDAD,COD_SEXO,FECHA_NAC,CODIGO_FARMACIA,CODIGO_LIMITE_MENSUAL,GRUPO_ASEGURAMIENTO,COMUNIDAD_AUTONOMA,RAIZAP,ID_UNICO,NAF_REPETIDO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public SegSocialTitularesRealBean() {
		this.vacio = true;
	}

	public SegSocialTitularesRealBean(java.util.HashMap hd) {
		this.vacio = false;
		this.numLinea = (java.math.BigDecimal) hd.get("NUM_LINEA");
		this.tipoIdentificador = (java.lang.String) hd.get("TIPO_IDENTIFICADOR");
		this.numeroIdentificador = (java.lang.String) hd.get("NUMERO_IDENTIFICADOR");
		this.dniNie = (java.lang.String) hd.get("DNI_NIE");
		this.pasaporte = (java.lang.String) hd.get("PASAPORTE");
		this.naf = (java.lang.String) hd.get("NAF");
		this.nafSec1 = (java.lang.String) hd.get("NAF_SEC1");
		this.nafSec2 = (java.lang.String) hd.get("NAF_SEC2");
		this.nafSec3 = (java.lang.String) hd.get("NAF_SEC3");
		this.nafSec4 = (java.lang.String) hd.get("NAF_SEC4");
		this.nafSec5 = (java.lang.String) hd.get("NAF_SEC5");
		this.nafSec6 = (java.lang.String) hd.get("NAF_SEC6");
		this.nafSec7 = (java.lang.String) hd.get("NAF_SEC7");
		this.nafSec8 = (java.lang.String) hd.get("NAF_SEC8");
		this.nafSec9 = (java.lang.String) hd.get("NAF_SEC9");
		this.indicativoNombre = (java.lang.String) hd.get("INDICATIVO_NOMBRE");
		this.apellidosNombre = (java.lang.String) hd.get("APELLIDOS_NOMBRE");
		this.apellido1 = (java.lang.String) hd.get("APELLIDO1");
		this.apellido2 = (java.lang.String) hd.get("APELLIDO2");
		this.nombre = (java.lang.String) hd.get("NOMBRE");
		this.nacionalidad = (java.lang.String) hd.get("NACIONALIDAD");
		this.codSexo = (java.lang.String) hd.get("COD_SEXO");
		this.fechaNac = (java.lang.String) hd.get("FECHA_NAC");
		this.codigoFarmacia = (java.lang.String) hd.get("CODIGO_FARMACIA");
		this.codigoLimiteMensual = (java.lang.String) hd.get("CODIGO_LIMITE_MENSUAL");
		this.grupoAseguramiento = (java.lang.String) hd.get("GRUPO_ASEGURAMIENTO");
		this.comunidadAutonoma = (java.lang.String) hd.get("COMUNIDAD_AUTONOMA");
		this.raizap = (java.lang.String) hd.get("RAIZAP");
		this.idUnico = (java.math.BigDecimal) hd.get("ID_UNICO");
		this.nafRepetido = (java.math.BigDecimal) hd.get("NAF_REPETIDO");
	}

	public SegSocialTitularesRealBean(java.sql.ResultSet rs) throws java.sql.SQLException {
		this.vacio = false;
		this.numLinea = rs.getBigDecimal("NUM_LINEA");
		this.tipoIdentificador = rs.getString("TIPO_IDENTIFICADOR");
		this.numeroIdentificador = rs.getString("NUMERO_IDENTIFICADOR");
		this.dniNie = rs.getString("DNI_NIE");
		this.pasaporte = rs.getString("PASAPORTE");
		this.naf = rs.getString("NAF");
		this.nafSec1 = rs.getString("NAF_SEC1");
		this.nafSec2 = rs.getString("NAF_SEC2");
		this.nafSec3 = rs.getString("NAF_SEC3");
		this.nafSec4 = rs.getString("NAF_SEC4");
		this.nafSec5 = rs.getString("NAF_SEC5");
		this.nafSec6 = rs.getString("NAF_SEC6");
		this.nafSec7 = rs.getString("NAF_SEC7");
		this.nafSec8 = rs.getString("NAF_SEC8");
		this.nafSec9 = rs.getString("NAF_SEC9");
		this.indicativoNombre = rs.getString("INDICATIVO_NOMBRE");
		this.apellidosNombre = rs.getString("APELLIDOS_NOMBRE");
		this.apellido1 = rs.getString("APELLIDO1");
		this.apellido2 = rs.getString("APELLIDO2");
		this.nombre = rs.getString("NOMBRE");
		this.nacionalidad = rs.getString("NACIONALIDAD");
		this.codSexo = rs.getString("COD_SEXO");
		this.fechaNac = rs.getString("FECHA_NAC");
		this.codigoFarmacia = rs.getString("CODIGO_FARMACIA");
		this.codigoLimiteMensual = rs.getString("CODIGO_LIMITE_MENSUAL");
		this.grupoAseguramiento = rs.getString("GRUPO_ASEGURAMIENTO");
		this.comunidadAutonoma = rs.getString("COMUNIDAD_AUTONOMA");
		this.raizap = rs.getString("RAIZAP");
		this.idUnico = rs.getBigDecimal("ID_UNICO");
		this.nafRepetido = rs.getBigDecimal("NAF_REPETIDO");
	}

	public java.math.BigDecimal getNumLinea() {
		return this.numLinea;
	}

	public void setNumLinea(java.math.BigDecimal numLinea) {
		this.numLinea = numLinea;
		this.vacio = false;
	}

	public java.lang.String getTipoIdentificador() {
		return this.tipoIdentificador;
	}

	public void setTipoIdentificador(java.lang.String tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
		this.vacio = false;
	}

	public java.lang.String getNumeroIdentificador() {
		return this.numeroIdentificador;
	}

	public void setNumeroIdentificador(java.lang.String numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
		this.vacio = false;
	}

	public java.lang.String getDniNie() {
		return this.dniNie;
	}

	public void setDniNie(java.lang.String dniNie) {
		this.dniNie = dniNie;
		this.vacio = false;
	}

	public java.lang.String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(java.lang.String pasaporte) {
		this.pasaporte = pasaporte;
		this.vacio = false;
	}

	public java.lang.String getNaf() {
		return this.naf;
	}

	public void setNaf(java.lang.String naf) {
		this.naf = naf;
		this.vacio = false;
	}

	public java.lang.String getNafSec1() {
		return this.nafSec1;
	}

	public void setNafSec1(java.lang.String nafSec1) {
		this.nafSec1 = nafSec1;
		this.vacio = false;
	}

	public java.lang.String getNafSec2() {
		return this.nafSec2;
	}

	public void setNafSec2(java.lang.String nafSec2) {
		this.nafSec2 = nafSec2;
		this.vacio = false;
	}

	public java.lang.String getNafSec3() {
		return this.nafSec3;
	}

	public void setNafSec3(java.lang.String nafSec3) {
		this.nafSec3 = nafSec3;
		this.vacio = false;
	}

	public java.lang.String getNafSec4() {
		return this.nafSec4;
	}

	public void setNafSec4(java.lang.String nafSec4) {
		this.nafSec4 = nafSec4;
		this.vacio = false;
	}

	public java.lang.String getNafSec5() {
		return this.nafSec5;
	}

	public void setNafSec5(java.lang.String nafSec5) {
		this.nafSec5 = nafSec5;
		this.vacio = false;
	}

	public java.lang.String getNafSec6() {
		return this.nafSec6;
	}

	public void setNafSec6(java.lang.String nafSec6) {
		this.nafSec6 = nafSec6;
		this.vacio = false;
	}

	public java.lang.String getNafSec7() {
		return this.nafSec7;
	}

	public void setNafSec7(java.lang.String nafSec7) {
		this.nafSec7 = nafSec7;
		this.vacio = false;
	}

	public java.lang.String getNafSec8() {
		return this.nafSec8;
	}

	public void setNafSec8(java.lang.String nafSec8) {
		this.nafSec8 = nafSec8;
		this.vacio = false;
	}

	public java.lang.String getNafSec9() {
		return this.nafSec9;
	}

	public void setNafSec9(java.lang.String nafSec9) {
		this.nafSec9 = nafSec9;
		this.vacio = false;
	}

	public java.lang.String getIndicativoNombre() {
		return this.indicativoNombre;
	}

	public void setIndicativoNombre(java.lang.String indicativoNombre) {
		this.indicativoNombre = indicativoNombre;
		this.vacio = false;
	}

	public java.lang.String getApellidosNombre() {
		return this.apellidosNombre;
	}

	public void setApellidosNombre(java.lang.String apellidosNombre) {
		this.apellidosNombre = apellidosNombre;
		this.vacio = false;
	}

	public java.lang.String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(java.lang.String apellido1) {
		this.apellido1 = apellido1;
		this.vacio = false;
	}

	public java.lang.String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(java.lang.String apellido2) {
		this.apellido2 = apellido2;
		this.vacio = false;
	}

	public java.lang.String getNombre() {
		return this.nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
		this.vacio = false;
	}

	public java.lang.String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(java.lang.String nacionalidad) {
		this.nacionalidad = nacionalidad;
		this.vacio = false;
	}

	public java.lang.String getCodSexo() {
		return this.codSexo;
	}

	public void setCodSexo(java.lang.String codSexo) {
		this.codSexo = codSexo;
		this.vacio = false;
	}

	public java.lang.String getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(java.lang.String fechaNac) {
		this.fechaNac = fechaNac;
		this.vacio = false;
	}

	public java.lang.String getCodigoFarmacia() {
		return this.codigoFarmacia;
	}

	public void setCodigoFarmacia(java.lang.String codigoFarmacia) {
		this.codigoFarmacia = codigoFarmacia;
		this.vacio = false;
	}

	public java.lang.String getCodigoLimiteMensual() {
		return this.codigoLimiteMensual;
	}

	public void setCodigoLimiteMensual(java.lang.String codigoLimiteMensual) {
		this.codigoLimiteMensual = codigoLimiteMensual;
		this.vacio = false;
	}

	public java.lang.String getGrupoAseguramiento() {
		return this.grupoAseguramiento;
	}

	public void setGrupoAseguramiento(java.lang.String grupoAseguramiento) {
		this.grupoAseguramiento = grupoAseguramiento;
		this.vacio = false;
	}

	public java.lang.String getComunidadAutonoma() {
		return this.comunidadAutonoma;
	}

	public void setComunidadAutonoma(java.lang.String comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
		this.vacio = false;
	}

	public java.lang.String getRaizap() {
		return this.raizap;
	}

	public void setRaizap(java.lang.String raizap) {
		this.raizap = raizap;
		this.vacio = false;
	}

	public java.math.BigDecimal getIdUnico() {
		return this.idUnico;
	}

	public void setIdUnico(java.math.BigDecimal idUnico) {
		this.idUnico = idUnico;
		this.vacio = false;
	}

	public java.math.BigDecimal getNafRepetido() {
		return this.nafRepetido;
	}

	public void setNafRepetido(java.math.BigDecimal nafRepetido) {
		this.nafRepetido = nafRepetido;
		this.vacio = false;
	}

	public boolean isVacio() {
		return this.vacio;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("InssBean [apellido1=");
		buffer.append(apellido1);
		buffer.append(", apellido2=");
		buffer.append(apellido2);
		buffer.append(", apellidosNombre=");
		buffer.append(apellidosNombre);
		buffer.append(", codSexo=");
		buffer.append(codSexo);
		buffer.append(", codigoFarmacia=");
		buffer.append(codigoFarmacia);
		buffer.append(", codigoLimiteMensual=");
		buffer.append(codigoLimiteMensual);
		buffer.append(", comunidadAutonoma=");
		buffer.append(comunidadAutonoma);
		buffer.append(", dniNie=");
		buffer.append(dniNie);
		buffer.append(", fechaNac=");
		buffer.append(fechaNac);
		buffer.append(", grupoAseguramiento=");
		buffer.append(grupoAseguramiento);
		buffer.append(", idUnico=");
		buffer.append(idUnico);
		buffer.append(", indicativoNombre=");
		buffer.append(indicativoNombre);
		buffer.append(", nacionalidad=");
		buffer.append(nacionalidad);
		buffer.append(", naf=");
		buffer.append(naf);
		buffer.append(", nafRepetido=");
		buffer.append(nafRepetido);
		buffer.append(", nafSec1=");
		buffer.append(nafSec1);
		buffer.append(", nafSec2=");
		buffer.append(nafSec2);
		buffer.append(", nafSec3=");
		buffer.append(nafSec3);
		buffer.append(", nafSec4=");
		buffer.append(nafSec4);
		buffer.append(", nafSec5=");
		buffer.append(nafSec5);
		buffer.append(", nafSec6=");
		buffer.append(nafSec6);
		buffer.append(", nafSec7=");
		buffer.append(nafSec7);
		buffer.append(", nafSec8=");
		buffer.append(nafSec8);
		buffer.append(", nafSec9=");
		buffer.append(nafSec9);
		buffer.append(", nombre=");
		buffer.append(nombre);
		buffer.append(", numLinea=");
		buffer.append(numLinea);
		buffer.append(", numeroIdentificador=");
		buffer.append(numeroIdentificador);
		buffer.append(", pasaporte=");
		buffer.append(pasaporte);
		buffer.append(", raizap=");
		buffer.append(raizap);
		buffer.append(", tipoIdentificador=");
		buffer.append(tipoIdentificador);
		buffer.append("]");
		return buffer.toString();
	}

}
