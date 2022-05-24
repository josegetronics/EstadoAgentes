package sns.model.inss.dto;

import java.io.Serializable;

/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:16 CET 2012
/ ******************************************************/
public class InssBean implements Serializable { 

  /**
	 * 
	 */
  private static final long serialVersionUID = 113950616850044835L;

  private boolean vacio=false;
  private java.math.BigDecimal idInss;
  private java.lang.String codTipoAsegurado;
  private java.lang.String ipf;
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
  private java.sql.Timestamp fechaNacimiento;
  private java.math.BigDecimal sexo;
  private java.lang.String indicativoDomicilio;
  private java.lang.String domicilio;
  private java.lang.String tipoAseguramiento;
  private java.lang.String codIndicadorDeFarmacia;
  private java.lang.String codSubindicadorDeFarmacia;
  private java.lang.String codSituacion;
  private java.sql.Timestamp fechaEfectoSituacion;
  private java.math.BigDecimal codTipoBeneficiario;
  private java.lang.String ipfTitular;
  private java.lang.String nafTitular;
  private java.math.BigDecimal numeroSecuencia;
  private java.lang.String fechaNacimientoRaw;
  private java.lang.String codTipoMotivoBaja;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_ID_INSS";


  public static final String NOMBRE_TABLA="INSS_TIT";


  public static final String INDICE_SELECT_BY_PK_ID_INSS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS = ?";


  public static final String INDICE_DELETE_ID_INSS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS = ?";


  public static final String INDICE_UPDATE_COD_TIPO_ASEGURADO="1";
  public static final String INDICE_UPDATE_IPF="2";
  public static final String INDICE_UPDATE_DNI_NIE="3";
  public static final String INDICE_UPDATE_PASAPORTE="4";
  public static final String INDICE_UPDATE_NAF="5";
  public static final String INDICE_UPDATE_NAF_SEC1="6";
  public static final String INDICE_UPDATE_NAF_SEC2="7";
  public static final String INDICE_UPDATE_NAF_SEC3="8";
  public static final String INDICE_UPDATE_NAF_SEC4="9";
  public static final String INDICE_UPDATE_NAF_SEC5="10";
  public static final String INDICE_UPDATE_NAF_SEC6="11";
  public static final String INDICE_UPDATE_NAF_SEC7="12";
  public static final String INDICE_UPDATE_NAF_SEC8="13";
  public static final String INDICE_UPDATE_NAF_SEC9="14";
  public static final String INDICE_UPDATE_INDICATIVO_NOMBRE="15";
  public static final String INDICE_UPDATE_APELLIDOS_NOMBRE="16";
  public static final String INDICE_UPDATE_APELLIDO1="17";
  public static final String INDICE_UPDATE_APELLIDO2="18";
  public static final String INDICE_UPDATE_NOMBRE="19";
  public static final String INDICE_UPDATE_NACIONALIDAD="20";
  public static final String INDICE_UPDATE_FECHA_NACIMIENTO="21";
  public static final String INDICE_UPDATE_SEXO="22";
  public static final String INDICE_UPDATE_INDICATIVO_DOMICILIO="23";
  public static final String INDICE_UPDATE_DOMICILIO="24";
  public static final String INDICE_UPDATE_TIPO_ASEGURAMIENTO="25";
  public static final String INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA="26";
  public static final String INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA="27";
  public static final String INDICE_UPDATE_COD_SITUACION="28";
  public static final String INDICE_UPDATE_FECHA_EFECTO_SITUACION="29";
  public static final String INDICE_UPDATE_COD_TIPO_BENEFICIARIO="30";
  public static final String INDICE_UPDATE_IPF_TITULAR="31";
  public static final String INDICE_UPDATE_NAF_TITULAR="32";
  public static final String INDICE_UPDATE_NUMERO_SECUENCIA="33";
  public static final String INDICE_UPDATE_FECHA_NACIMIENTO_RAW="34";
  public static final String INDICE_UPDATE_COD_TIPO_MOTIVO_BAJA="35";
  public static final String INDICE_UPDATE_ID_INSS="36";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_TIPO_ASEGURADO = ?, IPF = ?, DNI_NIE = ?, PASAPORTE = ?, NAF = ?, NAF_SEC1 = ?, NAF_SEC2 = ?, NAF_SEC3 = ?, NAF_SEC4 = ?, NAF_SEC5 = ?, NAF_SEC6 = ?, NAF_SEC7 = ?, NAF_SEC8 = ?, NAF_SEC9 = ?, INDICATIVO_NOMBRE = ?, APELLIDOS_NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, NOMBRE = ?, NACIONALIDAD = ?, FECHA_NACIMIENTO = ?, SEXO = ?, INDICATIVO_DOMICILIO = ?, DOMICILIO = ?, TIPO_ASEGURAMIENTO = ?, COD_INDICADOR_DE_FARMACIA = ?, COD_SUBINDICADOR_DE_FARMACIA = ?, COD_SITUACION = ?, FECHA_EFECTO_SITUACION = ?, COD_TIPO_BENEFICIARIO = ?, IPF_TITULAR = ?, NAF_TITULAR = ?, NUMERO_SECUENCIA = ?, FECHA_NACIMIENTO_RAW = ?, COD_TIPO_MOTIVO_BAJA = ? where ID_INSS = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS,idInss);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS,idInss);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_TIPO_ASEGURADO,codTipoAsegurado);
	  hParam.put(INDICE_UPDATE_IPF,ipf);
	  hParam.put(INDICE_UPDATE_DNI_NIE,dniNie);
	  hParam.put(INDICE_UPDATE_PASAPORTE,pasaporte);
	  hParam.put(INDICE_UPDATE_NAF,naf);
	  hParam.put(INDICE_UPDATE_NAF_SEC1,nafSec1);
	  hParam.put(INDICE_UPDATE_NAF_SEC2,nafSec2);
	  hParam.put(INDICE_UPDATE_NAF_SEC3,nafSec3);
	  hParam.put(INDICE_UPDATE_NAF_SEC4,nafSec4);
	  hParam.put(INDICE_UPDATE_NAF_SEC5,nafSec5);
	  hParam.put(INDICE_UPDATE_NAF_SEC6,nafSec6);
	  hParam.put(INDICE_UPDATE_NAF_SEC7,nafSec7);
	  hParam.put(INDICE_UPDATE_NAF_SEC8,nafSec8);
	  hParam.put(INDICE_UPDATE_NAF_SEC9,nafSec9);
	  hParam.put(INDICE_UPDATE_INDICATIVO_NOMBRE,indicativoNombre);
	  hParam.put(INDICE_UPDATE_APELLIDOS_NOMBRE,apellidosNombre);
	  hParam.put(INDICE_UPDATE_APELLIDO1,apellido1);
	  hParam.put(INDICE_UPDATE_APELLIDO2,apellido2);
	  hParam.put(INDICE_UPDATE_NOMBRE,nombre);
	  hParam.put(INDICE_UPDATE_NACIONALIDAD,nacionalidad);
	  hParam.put(INDICE_UPDATE_FECHA_NACIMIENTO,fechaNacimiento);
	  hParam.put(INDICE_UPDATE_SEXO,sexo);
	  hParam.put(INDICE_UPDATE_INDICATIVO_DOMICILIO,indicativoDomicilio);
	  hParam.put(INDICE_UPDATE_DOMICILIO,domicilio);
	  hParam.put(INDICE_UPDATE_TIPO_ASEGURAMIENTO,tipoAseguramiento);
	  hParam.put(INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA,codIndicadorDeFarmacia);
	  hParam.put(INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA,codSubindicadorDeFarmacia);
	  hParam.put(INDICE_UPDATE_COD_SITUACION,codSituacion);
	  hParam.put(INDICE_UPDATE_FECHA_EFECTO_SITUACION,fechaEfectoSituacion);
	  hParam.put(INDICE_UPDATE_COD_TIPO_BENEFICIARIO,codTipoBeneficiario);
	  hParam.put(INDICE_UPDATE_IPF_TITULAR,ipfTitular);
	  hParam.put(INDICE_UPDATE_NAF_TITULAR,nafTitular);
	  hParam.put(INDICE_UPDATE_NUMERO_SECUENCIA,numeroSecuencia);
	  hParam.put(INDICE_UPDATE_FECHA_NACIMIENTO_RAW,fechaNacimientoRaw);
	  hParam.put(INDICE_UPDATE_COD_TIPO_MOTIVO_BAJA,codTipoMotivoBaja);
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS="1";
  public static final String INDICE_INSERT_COD_TIPO_ASEGURADO="2";
  public static final String INDICE_INSERT_IPF="3";
  public static final String INDICE_INSERT_DNI_NIE="4";
  public static final String INDICE_INSERT_PASAPORTE="5";
  public static final String INDICE_INSERT_NAF="6";
  public static final String INDICE_INSERT_NAF_SEC1="7";
  public static final String INDICE_INSERT_NAF_SEC2="8";
  public static final String INDICE_INSERT_NAF_SEC3="9";
  public static final String INDICE_INSERT_NAF_SEC4="10";
  public static final String INDICE_INSERT_NAF_SEC5="11";
  public static final String INDICE_INSERT_NAF_SEC6="12";
  public static final String INDICE_INSERT_NAF_SEC7="13";
  public static final String INDICE_INSERT_NAF_SEC8="14";
  public static final String INDICE_INSERT_NAF_SEC9="15";
  public static final String INDICE_INSERT_INDICATIVO_NOMBRE="16";
  public static final String INDICE_INSERT_APELLIDOS_NOMBRE="17";
  public static final String INDICE_INSERT_APELLIDO1="18";
  public static final String INDICE_INSERT_APELLIDO2="19";
  public static final String INDICE_INSERT_NOMBRE="20";
  public static final String INDICE_INSERT_NACIONALIDAD="21";
  public static final String INDICE_INSERT_FECHA_NACIMIENTO="22";
  public static final String INDICE_INSERT_SEXO="23";
  public static final String INDICE_INSERT_INDICATIVO_DOMICILIO="24";
  public static final String INDICE_INSERT_DOMICILIO="25";
  public static final String INDICE_INSERT_TIPO_ASEGURAMIENTO="26";
  public static final String INDICE_INSERT_COD_INDICADOR_DE_FARMACIA="27";
  public static final String INDICE_INSERT_COD_SUBINDICADOR_DE_FARMACIA="28";
  public static final String INDICE_INSERT_COD_SITUACION="29";
  public static final String INDICE_INSERT_FECHA_EFECTO_SITUACION="30";
  public static final String INDICE_INSERT_COD_TIPO_BENEFICIARIO="31";
  public static final String INDICE_INSERT_IPF_TITULAR="32";
  public static final String INDICE_INSERT_NAF_TITULAR="33";
  public static final String INDICE_INSERT_NUMERO_SECUENCIA="34";
  public static final String INDICE_INSERT_FECHA_NACIMIENTO_RAW="35";
  public static final String INDICE_INSERT_COD_TIPO_MOTIVO_BAJA="36";
  public static final String QUERY_INSERT="INSERT INTO INSS_TIT (ID_INSS,COD_TIPO_ASEGURADO,IPF,DNI_NIE,PASAPORTE,NAF,NAF_SEC1,NAF_SEC2,NAF_SEC3,NAF_SEC4,NAF_SEC5,NAF_SEC6,NAF_SEC7,NAF_SEC8,NAF_SEC9,INDICATIVO_NOMBRE,APELLIDOS_NOMBRE,APELLIDO1,APELLIDO2,NOMBRE,NACIONALIDAD,FECHA_NACIMIENTO,SEXO,INDICATIVO_DOMICILIO,DOMICILIO,TIPO_ASEGURAMIENTO,COD_INDICADOR_DE_FARMACIA,COD_SUBINDICADOR_DE_FARMACIA,COD_SITUACION,FECHA_EFECTO_SITUACION,COD_TIPO_BENEFICIARIO,IPF_TITULAR,NAF_TITULAR,NUMERO_SECUENCIA,FECHA_NACIMIENTO_RAW,COD_TIPO_MOTIVO_BAJA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_COD_TIPO_ASEGURADO,codTipoAsegurado);
	  hParam.put(INDICE_INSERT_IPF,ipf);
	  hParam.put(INDICE_INSERT_DNI_NIE,dniNie);
	  hParam.put(INDICE_INSERT_PASAPORTE,pasaporte);
	  hParam.put(INDICE_INSERT_NAF,naf);
	  hParam.put(INDICE_INSERT_NAF_SEC1,nafSec1);
	  hParam.put(INDICE_INSERT_NAF_SEC2,nafSec2);
	  hParam.put(INDICE_INSERT_NAF_SEC3,nafSec3);
	  hParam.put(INDICE_INSERT_NAF_SEC4,nafSec4);
	  hParam.put(INDICE_INSERT_NAF_SEC5,nafSec5);
	  hParam.put(INDICE_INSERT_NAF_SEC6,nafSec6);
	  hParam.put(INDICE_INSERT_NAF_SEC7,nafSec7);
	  hParam.put(INDICE_INSERT_NAF_SEC8,nafSec8);
	  hParam.put(INDICE_INSERT_NAF_SEC9,nafSec9);
	  hParam.put(INDICE_INSERT_INDICATIVO_NOMBRE,indicativoNombre);
	  hParam.put(INDICE_INSERT_APELLIDOS_NOMBRE,apellidosNombre);
	  hParam.put(INDICE_INSERT_APELLIDO1,apellido1);
	  hParam.put(INDICE_INSERT_APELLIDO2,apellido2);
	  hParam.put(INDICE_INSERT_NOMBRE,nombre);
	  hParam.put(INDICE_INSERT_NACIONALIDAD,nacionalidad);
	  hParam.put(INDICE_INSERT_FECHA_NACIMIENTO,fechaNacimiento);
	  hParam.put(INDICE_INSERT_SEXO,sexo);
	  hParam.put(INDICE_INSERT_INDICATIVO_DOMICILIO,indicativoDomicilio);
	  hParam.put(INDICE_INSERT_DOMICILIO,domicilio);
	  hParam.put(INDICE_INSERT_TIPO_ASEGURAMIENTO,tipoAseguramiento);
	  hParam.put(INDICE_INSERT_COD_INDICADOR_DE_FARMACIA,codIndicadorDeFarmacia);
	  hParam.put(INDICE_INSERT_COD_SUBINDICADOR_DE_FARMACIA,codSubindicadorDeFarmacia);
	  hParam.put(INDICE_INSERT_COD_SITUACION,codSituacion);
	  hParam.put(INDICE_INSERT_FECHA_EFECTO_SITUACION,fechaEfectoSituacion);
	  hParam.put(INDICE_INSERT_COD_TIPO_BENEFICIARIO,codTipoBeneficiario);
	  hParam.put(INDICE_INSERT_IPF_TITULAR,ipfTitular);
	  hParam.put(INDICE_INSERT_NAF_TITULAR,nafTitular);
	  hParam.put(INDICE_INSERT_NUMERO_SECUENCIA,numeroSecuencia);
	  hParam.put(INDICE_INSERT_FECHA_NACIMIENTO_RAW,fechaNacimientoRaw);
	  hParam.put(INDICE_INSERT_COD_TIPO_MOTIVO_BAJA,codTipoMotivoBaja);
	  return hParam;
  }

  public InssBean() {this.vacio=true;}
  public InssBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.codTipoAsegurado=(java.lang.String)hd.get("COD_TIPO_ASEGURADO");
	 this.ipf=(java.lang.String)hd.get("IPF");
	 this.dniNie=(java.lang.String)hd.get("DNI_NIE");
	 this.pasaporte=(java.lang.String)hd.get("PASAPORTE");
	 this.naf=(java.lang.String)hd.get("NAF");
	 this.nafSec1=(java.lang.String)hd.get("NAF_SEC1");
	 this.nafSec2=(java.lang.String)hd.get("NAF_SEC2");
	 this.nafSec3=(java.lang.String)hd.get("NAF_SEC3");
	 this.nafSec4=(java.lang.String)hd.get("NAF_SEC4");
	 this.nafSec5=(java.lang.String)hd.get("NAF_SEC5");
	 this.nafSec6=(java.lang.String)hd.get("NAF_SEC6");
	 this.nafSec7=(java.lang.String)hd.get("NAF_SEC7");
	 this.nafSec8=(java.lang.String)hd.get("NAF_SEC8");
	 this.nafSec9=(java.lang.String)hd.get("NAF_SEC9");
	 this.indicativoNombre=(java.lang.String)hd.get("INDICATIVO_NOMBRE");
	 this.apellidosNombre=(java.lang.String)hd.get("APELLIDOS_NOMBRE");
	 this.apellido1=(java.lang.String)hd.get("APELLIDO1");
	 this.apellido2=(java.lang.String)hd.get("APELLIDO2");
	 this.nombre=(java.lang.String)hd.get("NOMBRE");
	 this.nacionalidad=(java.lang.String)hd.get("NACIONALIDAD");
	 this.fechaNacimiento=(java.sql.Timestamp)hd.get("FECHA_NACIMIENTO");
	 this.sexo=(java.math.BigDecimal)hd.get("SEXO");
	 this.indicativoDomicilio=(java.lang.String)hd.get("INDICATIVO_DOMICILIO");
	 this.domicilio=(java.lang.String)hd.get("DOMICILIO");
	 this.tipoAseguramiento=(java.lang.String)hd.get("TIPO_ASEGURAMIENTO");
	 this.codIndicadorDeFarmacia=(java.lang.String)hd.get("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=(java.lang.String)hd.get("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codSituacion=(java.lang.String)hd.get("COD_SITUACION");
	 this.fechaEfectoSituacion=(java.sql.Timestamp)hd.get("FECHA_EFECTO_SITUACION");
	 this.codTipoBeneficiario=(java.math.BigDecimal)hd.get("COD_TIPO_BENEFICIARIO");
	 this.ipfTitular=(java.lang.String)hd.get("IPF_TITULAR");
	 this.nafTitular=(java.lang.String)hd.get("NAF_TITULAR");
	 this.numeroSecuencia=(java.math.BigDecimal)hd.get("NUMERO_SECUENCIA");
	 this.fechaNacimientoRaw=(java.lang.String)hd.get("FECHA_NACIMIENTO_RAW");
	 this.codTipoMotivoBaja=(java.lang.String)hd.get("COD_TIPO_MOTIVO_BAJA");
  }

  public InssBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.codTipoAsegurado=rs.getString("COD_TIPO_ASEGURADO");
	 this.ipf=rs.getString("IPF");
	 this.dniNie=rs.getString("DNI_NIE");
	 this.pasaporte=rs.getString("PASAPORTE");
	 this.naf=rs.getString("NAF");
	 this.nafSec1=rs.getString("NAF_SEC1");
	 this.nafSec2=rs.getString("NAF_SEC2");
	 this.nafSec3=rs.getString("NAF_SEC3");
	 this.nafSec4=rs.getString("NAF_SEC4");
	 this.nafSec5=rs.getString("NAF_SEC5");
	 this.nafSec6=rs.getString("NAF_SEC6");
	 this.nafSec7=rs.getString("NAF_SEC7");
	 this.nafSec8=rs.getString("NAF_SEC8");
	 this.nafSec9=rs.getString("NAF_SEC9");
	 this.indicativoNombre=rs.getString("INDICATIVO_NOMBRE");
	 this.apellidosNombre=rs.getString("APELLIDOS_NOMBRE");
	 this.apellido1=rs.getString("APELLIDO1");
	 this.apellido2=rs.getString("APELLIDO2");
	 this.nombre=rs.getString("NOMBRE");
	 this.nacionalidad=rs.getString("NACIONALIDAD");
	 this.fechaNacimiento=rs.getTimestamp("FECHA_NACIMIENTO");
	 this.sexo=rs.getBigDecimal("SEXO");
	 this.indicativoDomicilio=rs.getString("INDICATIVO_DOMICILIO");
	 this.domicilio=rs.getString("DOMICILIO");
	 this.tipoAseguramiento=rs.getString("TIPO_ASEGURAMIENTO");
	 this.codIndicadorDeFarmacia=rs.getString("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=rs.getString("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codSituacion=rs.getString("COD_SITUACION");
	 this.fechaEfectoSituacion=rs.getTimestamp("FECHA_EFECTO_SITUACION");
	 this.codTipoBeneficiario=rs.getBigDecimal("COD_TIPO_BENEFICIARIO");
	 this.ipfTitular=rs.getString("IPF_TITULAR");
	 this.nafTitular=rs.getString("NAF_TITULAR");
	 this.numeroSecuencia=rs.getBigDecimal("NUMERO_SECUENCIA");
	 this.fechaNacimientoRaw=rs.getString("FECHA_NACIMIENTO_RAW");
	 this.codTipoMotivoBaja=rs.getString("COD_TIPO_MOTIVO_BAJA");
  }

  public java.math.BigDecimal getIdInss() {
	 return this.idInss;
  }
  public void setIdInss(java.math.BigDecimal idInss) {
	 this.idInss=idInss;
	 this.vacio=false;
  }
  public java.lang.String getCodTipoAsegurado() {
	 return this.codTipoAsegurado;
  }
  public void setCodTipoAsegurado(java.lang.String codTipoAsegurado) {
	 this.codTipoAsegurado=codTipoAsegurado;
	 this.vacio=false;
  }
  public java.lang.String getIpf() {
	 return this.ipf;
  }
  public void setIpf(java.lang.String ipf) {
	 this.ipf=ipf;
	 this.vacio=false;
  }
  public java.lang.String getDniNie() {
	 return this.dniNie;
  }
  public void setDniNie(java.lang.String dniNie) {
	 this.dniNie=dniNie;
	 this.vacio=false;
  }
  public java.lang.String getPasaporte() {
	 return this.pasaporte;
  }
  public void setPasaporte(java.lang.String pasaporte) {
	 this.pasaporte=pasaporte;
	 this.vacio=false;
  }
  public java.lang.String getNaf() {
	 return this.naf;
  }
  public void setNaf(java.lang.String naf) {
	 this.naf=naf;
	 this.vacio=false;
  }
  public java.lang.String getNafSec1() {
	 return this.nafSec1;
  }
  public void setNafSec1(java.lang.String nafSec1) {
	 this.nafSec1=nafSec1;
	 this.vacio=false;
  }
  public java.lang.String getNafSec2() {
	 return this.nafSec2;
  }
  public void setNafSec2(java.lang.String nafSec2) {
	 this.nafSec2=nafSec2;
	 this.vacio=false;
  }
  public java.lang.String getNafSec3() {
	 return this.nafSec3;
  }
  public void setNafSec3(java.lang.String nafSec3) {
	 this.nafSec3=nafSec3;
	 this.vacio=false;
  }
  public java.lang.String getNafSec4() {
	 return this.nafSec4;
  }
  public void setNafSec4(java.lang.String nafSec4) {
	 this.nafSec4=nafSec4;
	 this.vacio=false;
  }
  public java.lang.String getNafSec5() {
	 return this.nafSec5;
  }
  public void setNafSec5(java.lang.String nafSec5) {
	 this.nafSec5=nafSec5;
	 this.vacio=false;
  }
  public java.lang.String getNafSec6() {
	 return this.nafSec6;
  }
  public void setNafSec6(java.lang.String nafSec6) {
	 this.nafSec6=nafSec6;
	 this.vacio=false;
  }
  public java.lang.String getNafSec7() {
	 return this.nafSec7;
  }
  public void setNafSec7(java.lang.String nafSec7) {
	 this.nafSec7=nafSec7;
	 this.vacio=false;
  }
  public java.lang.String getNafSec8() {
	 return this.nafSec8;
  }
  public void setNafSec8(java.lang.String nafSec8) {
	 this.nafSec8=nafSec8;
	 this.vacio=false;
  }
  public java.lang.String getNafSec9() {
	 return this.nafSec9;
  }
  public void setNafSec9(java.lang.String nafSec9) {
	 this.nafSec9=nafSec9;
	 this.vacio=false;
  }
  public java.lang.String getIndicativoNombre() {
	 return this.indicativoNombre;
  }
  public void setIndicativoNombre(java.lang.String indicativoNombre) {
	 this.indicativoNombre=indicativoNombre;
	 this.vacio=false;
  }
  public java.lang.String getApellidosNombre() {
	 return this.apellidosNombre;
  }
  public void setApellidosNombre(java.lang.String apellidosNombre) {
	 this.apellidosNombre=apellidosNombre;
	 this.vacio=false;
  }
  public java.lang.String getApellido1() {
	 return this.apellido1;
  }
  public void setApellido1(java.lang.String apellido1) {
	 this.apellido1=apellido1;
	 this.vacio=false;
  }
  public java.lang.String getApellido2() {
	 return this.apellido2;
  }
  public void setApellido2(java.lang.String apellido2) {
	 this.apellido2=apellido2;
	 this.vacio=false;
  }
  public java.lang.String getNombre() {
	 return this.nombre;
  }
  public void setNombre(java.lang.String nombre) {
	 this.nombre=nombre;
	 this.vacio=false;
  }
  public java.lang.String getNacionalidad() {
	 return this.nacionalidad;
  }
  public void setNacionalidad(java.lang.String nacionalidad) {
	 this.nacionalidad=nacionalidad;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaNacimiento() {
	 return this.fechaNacimiento;
  }
  public void setFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
	 this.fechaNacimiento=fechaNacimiento;
	 this.vacio=false;
  }
  public java.math.BigDecimal getSexo() {
	 return this.sexo;
  }
  public void setSexo(java.math.BigDecimal sexo) {
	 this.sexo=sexo;
	 this.vacio=false;
  }
  public java.lang.String getIndicativoDomicilio() {
	 return this.indicativoDomicilio;
  }
  public void setIndicativoDomicilio(java.lang.String indicativoDomicilio) {
	 this.indicativoDomicilio=indicativoDomicilio;
	 this.vacio=false;
  }
  public java.lang.String getDomicilio() {
	 return this.domicilio;
  }
  public void setDomicilio(java.lang.String domicilio) {
	 this.domicilio=domicilio;
	 this.vacio=false;
  }
  public java.lang.String getTipoAseguramiento() {
	 return this.tipoAseguramiento;
  }
  public void setTipoAseguramiento(java.lang.String tipoAseguramiento) {
	 this.tipoAseguramiento=tipoAseguramiento;
	 this.vacio=false;
  }
  public java.lang.String getCodIndicadorDeFarmacia() {
	 return this.codIndicadorDeFarmacia;
  }
  public void setCodIndicadorDeFarmacia(java.lang.String codIndicadorDeFarmacia) {
	 this.codIndicadorDeFarmacia=codIndicadorDeFarmacia;
	 this.vacio=false;
  }
  public java.lang.String getCodSubindicadorDeFarmacia() {
	 return this.codSubindicadorDeFarmacia;
  }
  public void setCodSubindicadorDeFarmacia(java.lang.String codSubindicadorDeFarmacia) {
	 this.codSubindicadorDeFarmacia=codSubindicadorDeFarmacia;
	 this.vacio=false;
  }
  public java.lang.String getCodSituacion() {
	 return this.codSituacion;
  }
  public void setCodSituacion(java.lang.String codSituacion) {
	 this.codSituacion=codSituacion;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaEfectoSituacion() {
	 return this.fechaEfectoSituacion;
  }
  public void setFechaEfectoSituacion(java.sql.Timestamp fechaEfectoSituacion) {
	 this.fechaEfectoSituacion=fechaEfectoSituacion;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTipoBeneficiario() {
	 return this.codTipoBeneficiario;
  }
  public void setCodTipoBeneficiario(java.math.BigDecimal codTipoBeneficiario) {
	 this.codTipoBeneficiario=codTipoBeneficiario;
	 this.vacio=false;
  }
  public java.lang.String getIpfTitular() {
	 return this.ipfTitular;
  }
  public void setIpfTitular(java.lang.String ipfTitular) {
	 this.ipfTitular=ipfTitular;
	 this.vacio=false;
  }
  public java.lang.String getNafTitular() {
	 return this.nafTitular;
  }
  public void setNafTitular(java.lang.String nafTitular) {
	 this.nafTitular=nafTitular;
	 this.vacio=false;
  }
  public java.math.BigDecimal getNumeroSecuencia() {
	 return this.numeroSecuencia;
  }
  public void setNumeroSecuencia(java.math.BigDecimal numeroSecuencia) {
	 this.numeroSecuencia=numeroSecuencia;
	 this.vacio=false;
  }
  public java.lang.String getFechaNacimientoRaw() {
	 return this.fechaNacimientoRaw;
  }
  public void setFechaNacimientoRaw(java.lang.String fechaNacimientoRaw) {
	 this.fechaNacimientoRaw=fechaNacimientoRaw;
	 this.vacio=false;
  }
  public java.lang.String getCodTipoMotivoBaja() {
	 return this.codTipoMotivoBaja;
  }
  public void setCodTipoMotivoBaja(java.lang.String codTipoMotivoBaja) {
	 this.codTipoMotivoBaja=codTipoMotivoBaja;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssBean [");
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("codTipoAsegurado = ").append(codTipoAsegurado);
	 buffer.append("ipf = ").append(ipf);
	 buffer.append("dniNie = ").append(dniNie);
	 buffer.append("pasaporte = ").append(pasaporte);
	 buffer.append("naf = ").append(naf);
	 buffer.append("nafSec1 = ").append(nafSec1);
	 buffer.append("nafSec2 = ").append(nafSec2);
	 buffer.append("nafSec3 = ").append(nafSec3);
	 buffer.append("nafSec4 = ").append(nafSec4);
	 buffer.append("nafSec5 = ").append(nafSec5);
	 buffer.append("nafSec6 = ").append(nafSec6);
	 buffer.append("nafSec7 = ").append(nafSec7);
	 buffer.append("nafSec8 = ").append(nafSec8);
	 buffer.append("nafSec9 = ").append(nafSec9);
	 buffer.append("indicativoNombre = ").append(indicativoNombre);
	 buffer.append("apellidosNombre = ").append(apellidosNombre);
	 buffer.append("apellido1 = ").append(apellido1);
	 buffer.append("apellido2 = ").append(apellido2);
	 buffer.append("nombre = ").append(nombre);
	 buffer.append("nacionalidad = ").append(nacionalidad);
	 buffer.append("fechaNacimiento = ").append(fechaNacimiento);
	 buffer.append("sexo = ").append(sexo);
	 buffer.append("indicativoDomicilio = ").append(indicativoDomicilio);
	 buffer.append("domicilio = ").append(domicilio);
	 buffer.append("tipoAseguramiento = ").append(tipoAseguramiento);
	 buffer.append("codIndicadorDeFarmacia = ").append(codIndicadorDeFarmacia);
	 buffer.append("codSubindicadorDeFarmacia = ").append(codSubindicadorDeFarmacia);
	 buffer.append("codSituacion = ").append(codSituacion);
	 buffer.append("fechaEfectoSituacion = ").append(fechaEfectoSituacion);
	 buffer.append("codTipoBeneficiario = ").append(codTipoBeneficiario);
	 buffer.append("ipfTitular = ").append(ipfTitular);
	 buffer.append("nafTitular = ").append(nafTitular);
	 buffer.append("numeroSecuencia = ").append(numeroSecuencia);
	 buffer.append("fechaNacimientoRaw = ").append(fechaNacimientoRaw);
	 buffer.append("codTipoMotivoBaja = ").append(codTipoMotivoBaja);
	 buffer.append("]");
	 return buffer.toString();
  }
}

