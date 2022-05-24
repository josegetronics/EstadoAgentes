package sns.model.inss.dto;

import java.io.Serializable;

/****************************************************** /
Clase generada con GeneraBean de gasai.jar Thu Dec 13 16:01:07 CET 2012
/ ******************************************************/
public class InssCargaSns implements Serializable { 

  /**
	 * 
	 */
	private static final long serialVersionUID = 3556860916770917071L;
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
  private java.lang.String criterioIdentificacionSns;
  private java.lang.String codUsuarioSns;
  private java.math.BigDecimal codPrestacionServicio;
  private java.math.BigDecimal codEstado;
  private java.lang.String codUsuarioSnsTitular;
  private java.lang.String nafSns;
  private java.lang.String nafTitularSns;
  private java.math.BigDecimal codTitulo;
  private java.lang.String nombreSns;
  private java.lang.String apellido1Sns;
  private java.lang.String apellido2Sns;
  private java.lang.String dniNieSns;
  private java.lang.String pasaporteSns;
  private java.lang.String codUsuarioSnsFarmacia;
  private java.lang.String codIndicadorDeFarmaciaSns;
  private java.lang.String codSubindicadorDeFarSns;
  private java.math.BigDecimal codTipoProcedencia;
  private java.lang.String idEnSsalud;
  private java.math.BigDecimal codAgente;
  private java.math.BigDecimal numeroRegistro;

  public static final String NOMBRE_TABLA="INSS_CARGA_SNS";


  public static final String INDICE_SELECT_BY_PK_NUMERO_REGISTRO="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where NUMERO_REGISTRO = ?";


  public static final String INDICE_DELETE_NUMERO_REGISTRO="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where NUMERO_REGISTRO = ?";


  public static final String INDICE_UPDATE_ID_INSS="1";
  public static final String INDICE_UPDATE_COD_TIPO_ASEGURADO="2";
  public static final String INDICE_UPDATE_IPF="3";
  public static final String INDICE_UPDATE_DNI_NIE="4";
  public static final String INDICE_UPDATE_PASAPORTE="5";
  public static final String INDICE_UPDATE_NAF="6";
  public static final String INDICE_UPDATE_NAF_SEC1="7";
  public static final String INDICE_UPDATE_NAF_SEC2="8";
  public static final String INDICE_UPDATE_NAF_SEC3="9";
  public static final String INDICE_UPDATE_NAF_SEC4="10";
  public static final String INDICE_UPDATE_NAF_SEC5="11";
  public static final String INDICE_UPDATE_NAF_SEC6="12";
  public static final String INDICE_UPDATE_NAF_SEC7="13";
  public static final String INDICE_UPDATE_NAF_SEC8="14";
  public static final String INDICE_UPDATE_NAF_SEC9="15";
  public static final String INDICE_UPDATE_INDICATIVO_NOMBRE="16";
  public static final String INDICE_UPDATE_APELLIDOS_NOMBRE="17";
  public static final String INDICE_UPDATE_APELLIDO1="18";
  public static final String INDICE_UPDATE_APELLIDO2="19";
  public static final String INDICE_UPDATE_NOMBRE="20";
  public static final String INDICE_UPDATE_NACIONALIDAD="21";
  public static final String INDICE_UPDATE_FECHA_NACIMIENTO="22";
  public static final String INDICE_UPDATE_SEXO="23";
  public static final String INDICE_UPDATE_INDICATIVO_DOMICILIO="24";
  public static final String INDICE_UPDATE_DOMICILIO="25";
  public static final String INDICE_UPDATE_TIPO_ASEGURAMIENTO="26";
  public static final String INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA="27";
  public static final String INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA="28";
  public static final String INDICE_UPDATE_COD_SITUACION="29";
  public static final String INDICE_UPDATE_FECHA_EFECTO_SITUACION="30";
  public static final String INDICE_UPDATE_COD_TIPO_BENEFICIARIO="31";
  public static final String INDICE_UPDATE_IPF_TITULAR="32";
  public static final String INDICE_UPDATE_NAF_TITULAR="33";
  public static final String INDICE_UPDATE_NUMERO_SECUENCIA="34";
  public static final String INDICE_UPDATE_FECHA_NACIMIENTO_RAW="35";
  public static final String INDICE_UPDATE_COD_TIPO_MOTIVO_BAJA="36";
  public static final String INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS="37";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="38";
  public static final String INDICE_UPDATE_COD_PRESTACION_SERVICIO="39";
  public static final String INDICE_UPDATE_COD_ESTADO="40";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS_TITULAR="41";
  public static final String INDICE_UPDATE_NAF_SNS="42";
  public static final String INDICE_UPDATE_NAF_TITULAR_SNS="43";
  public static final String INDICE_UPDATE_COD_TITULO="44";
  public static final String INDICE_UPDATE_NOMBRE_SNS="45";
  public static final String INDICE_UPDATE_APELLIDO1_SNS="46";
  public static final String INDICE_UPDATE_APELLIDO2_SNS="47";
  public static final String INDICE_UPDATE_DNI_NIE_SNS="48";
  public static final String INDICE_UPDATE_PASAPORTE_SNS="49";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS_FARMACIA="50";
  public static final String INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA_SNS="51";
  public static final String INDICE_UPDATE_COD_SUBINDICADOR_DE_FAR_SNS="52";
  public static final String INDICE_UPDATE_COD_TIPO_PROCEDENCIA="53";
  public static final String INDICE_UPDATE_ID_EN_SSALUD="54";
  public static final String INDICE_UPDATE_COD_AGENTE="55";
  public static final String INDICE_UPDATE_NUMERO_REGISTRO="56";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set ID_INSS = ?, COD_TIPO_ASEGURADO = ?, IPF = ?, DNI_NIE = ?, PASAPORTE = ?, NAF = ?, NAF_SEC1 = ?, NAF_SEC2 = ?, NAF_SEC3 = ?, NAF_SEC4 = ?, NAF_SEC5 = ?, NAF_SEC6 = ?, NAF_SEC7 = ?, NAF_SEC8 = ?, NAF_SEC9 = ?, INDICATIVO_NOMBRE = ?, APELLIDOS_NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, NOMBRE = ?, NACIONALIDAD = ?, FECHA_NACIMIENTO = ?, SEXO = ?, INDICATIVO_DOMICILIO = ?, DOMICILIO = ?, TIPO_ASEGURAMIENTO = ?, COD_INDICADOR_DE_FARMACIA = ?, COD_SUBINDICADOR_DE_FARMACIA = ?, COD_SITUACION = ?, FECHA_EFECTO_SITUACION = ?, COD_TIPO_BENEFICIARIO = ?, IPF_TITULAR = ?, NAF_TITULAR = ?, NUMERO_SECUENCIA = ?, FECHA_NACIMIENTO_RAW = ?, COD_TIPO_MOTIVO_BAJA = ?, CRITERIO_IDENTIFICACION_SNS = ?, COD_USUARIO_SNS = ?, COD_PRESTACION_SERVICIO = ?, COD_ESTADO = ?, COD_USUARIO_SNS_TITULAR = ?, NAF_SNS = ?, NAF_TITULAR_SNS = ?, COD_TITULO = ?, NOMBRE_SNS = ?, APELLIDO1_SNS = ?, APELLIDO2_SNS = ?, DNI_NIE_SNS = ?, PASAPORTE_SNS = ?, COD_USUARIO_SNS_FARMACIA = ?, COD_INDICADOR_DE_FARMACIA_SNS = ?, COD_SUBINDICADOR_DE_FAR_SNS = ?, COD_TIPO_PROCEDENCIA = ?, ID_EN_SSALUD = ?, COD_AGENTE = ? where NUMERO_REGISTRO = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_NUMERO_REGISTRO,numeroRegistro);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_NUMERO_REGISTRO,numeroRegistro);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
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
	  hParam.put(INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_UPDATE_COD_ESTADO,codEstado);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS_TITULAR,codUsuarioSnsTitular);
	  hParam.put(INDICE_UPDATE_NAF_SNS,nafSns);
	  hParam.put(INDICE_UPDATE_NAF_TITULAR_SNS,nafTitularSns);
	  hParam.put(INDICE_UPDATE_COD_TITULO,codTitulo);
	  hParam.put(INDICE_UPDATE_NOMBRE_SNS,nombreSns);
	  hParam.put(INDICE_UPDATE_APELLIDO1_SNS,apellido1Sns);
	  hParam.put(INDICE_UPDATE_APELLIDO2_SNS,apellido2Sns);
	  hParam.put(INDICE_UPDATE_DNI_NIE_SNS,dniNieSns);
	  hParam.put(INDICE_UPDATE_PASAPORTE_SNS,pasaporteSns);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS_FARMACIA,codUsuarioSnsFarmacia);
	  hParam.put(INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA_SNS,codIndicadorDeFarmaciaSns);
	  hParam.put(INDICE_UPDATE_COD_SUBINDICADOR_DE_FAR_SNS,codSubindicadorDeFarSns);
	  hParam.put(INDICE_UPDATE_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  hParam.put(INDICE_UPDATE_ID_EN_SSALUD,idEnSsalud);
	  hParam.put(INDICE_UPDATE_COD_AGENTE,codAgente);
	  hParam.put(INDICE_UPDATE_NUMERO_REGISTRO,numeroRegistro);
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
  public static final String INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS="37";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="38";
  public static final String INDICE_INSERT_COD_PRESTACION_SERVICIO="39";
  public static final String INDICE_INSERT_COD_ESTADO="40";
  public static final String INDICE_INSERT_COD_USUARIO_SNS_TITULAR="41";
  public static final String INDICE_INSERT_NAF_SNS="42";
  public static final String INDICE_INSERT_NAF_TITULAR_SNS="43";
  public static final String INDICE_INSERT_COD_TITULO="44";
  public static final String INDICE_INSERT_NOMBRE_SNS="45";
  public static final String INDICE_INSERT_APELLIDO1_SNS="46";
  public static final String INDICE_INSERT_APELLIDO2_SNS="47";
  public static final String INDICE_INSERT_DNI_NIE_SNS="48";
  public static final String INDICE_INSERT_PASAPORTE_SNS="49";
  public static final String INDICE_INSERT_COD_USUARIO_SNS_FARMACIA="50";
  public static final String INDICE_INSERT_COD_INDICADOR_DE_FARMACIA_SNS="51";
  public static final String INDICE_INSERT_COD_SUBINDICADOR_DE_FAR_SNS="52";
  public static final String INDICE_INSERT_COD_TIPO_PROCEDENCIA="53";
  public static final String INDICE_INSERT_ID_EN_SSALUD="54";
  public static final String INDICE_INSERT_COD_AGENTE="55";
  public static final String INDICE_INSERT_NUMERO_REGISTRO="56";
  public static final String QUERY_INSERT="INSERT INTO INSS_CARGA_SNS (ID_INSS,COD_TIPO_ASEGURADO,IPF,DNI_NIE,PASAPORTE,NAF,NAF_SEC1,NAF_SEC2,NAF_SEC3,NAF_SEC4,NAF_SEC5,NAF_SEC6,NAF_SEC7,NAF_SEC8,NAF_SEC9,INDICATIVO_NOMBRE,APELLIDOS_NOMBRE,APELLIDO1,APELLIDO2,NOMBRE,NACIONALIDAD,FECHA_NACIMIENTO,SEXO,INDICATIVO_DOMICILIO,DOMICILIO,TIPO_ASEGURAMIENTO,COD_INDICADOR_DE_FARMACIA,COD_SUBINDICADOR_DE_FARMACIA,COD_SITUACION,FECHA_EFECTO_SITUACION,COD_TIPO_BENEFICIARIO,IPF_TITULAR,NAF_TITULAR,NUMERO_SECUENCIA,FECHA_NACIMIENTO_RAW,COD_TIPO_MOTIVO_BAJA,CRITERIO_IDENTIFICACION_SNS,COD_USUARIO_SNS,COD_PRESTACION_SERVICIO,COD_ESTADO,COD_USUARIO_SNS_TITULAR,NAF_SNS,NAF_TITULAR_SNS,COD_TITULO,NOMBRE_SNS,APELLIDO1_SNS,APELLIDO2_SNS,DNI_NIE_SNS,PASAPORTE_SNS,COD_USUARIO_SNS_FARMACIA,COD_INDICADOR_DE_FARMACIA_SNS,COD_SUBINDICADOR_DE_FAR_SNS,COD_TIPO_PROCEDENCIA,ID_EN_SSALUD,COD_AGENTE,NUMERO_REGISTRO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
	  hParam.put(INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_INSERT_COD_ESTADO,codEstado);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS_TITULAR,codUsuarioSnsTitular);
	  hParam.put(INDICE_INSERT_NAF_SNS,nafSns);
	  hParam.put(INDICE_INSERT_NAF_TITULAR_SNS,nafTitularSns);
	  hParam.put(INDICE_INSERT_COD_TITULO,codTitulo);
	  hParam.put(INDICE_INSERT_NOMBRE_SNS,nombreSns);
	  hParam.put(INDICE_INSERT_APELLIDO1_SNS,apellido1Sns);
	  hParam.put(INDICE_INSERT_APELLIDO2_SNS,apellido2Sns);
	  hParam.put(INDICE_INSERT_DNI_NIE_SNS,dniNieSns);
	  hParam.put(INDICE_INSERT_PASAPORTE_SNS,pasaporteSns);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS_FARMACIA,codUsuarioSnsFarmacia);
	  hParam.put(INDICE_INSERT_COD_INDICADOR_DE_FARMACIA_SNS,codIndicadorDeFarmaciaSns);
	  hParam.put(INDICE_INSERT_COD_SUBINDICADOR_DE_FAR_SNS,codSubindicadorDeFarSns);
	  hParam.put(INDICE_INSERT_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  hParam.put(INDICE_INSERT_ID_EN_SSALUD,idEnSsalud);
	  hParam.put(INDICE_INSERT_COD_AGENTE,codAgente);
	  hParam.put(INDICE_INSERT_NUMERO_REGISTRO,numeroRegistro);
	  return hParam;
  }

  public InssCargaSns() {this.vacio=true;}
  public InssCargaSns(java.util.HashMap hd) {
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
	 this.criterioIdentificacionSns=(java.lang.String)hd.get("CRITERIO_IDENTIFICACION_SNS");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codPrestacionServicio=(java.math.BigDecimal)hd.get("COD_PRESTACION_SERVICIO");
	 this.codEstado=(java.math.BigDecimal)hd.get("COD_ESTADO");
	 this.codUsuarioSnsTitular=(java.lang.String)hd.get("COD_USUARIO_SNS_TITULAR");
	 this.nafSns=(java.lang.String)hd.get("NAF_SNS");
	 this.nafTitularSns=(java.lang.String)hd.get("NAF_TITULAR_SNS");
	 this.codTitulo=(java.math.BigDecimal)hd.get("COD_TITULO");
	 this.nombreSns=(java.lang.String)hd.get("NOMBRE_SNS");
	 this.apellido1Sns=(java.lang.String)hd.get("APELLIDO1_SNS");
	 this.apellido2Sns=(java.lang.String)hd.get("APELLIDO2_SNS");
	 this.dniNieSns=(java.lang.String)hd.get("DNI_NIE_SNS");
	 this.pasaporteSns=(java.lang.String)hd.get("PASAPORTE_SNS");
	 this.codUsuarioSnsFarmacia=(java.lang.String)hd.get("COD_USUARIO_SNS_FARMACIA");
	 this.codIndicadorDeFarmaciaSns=(java.lang.String)hd.get("COD_INDICADOR_DE_FARMACIA_SNS");
	 this.codSubindicadorDeFarSns=(java.lang.String)hd.get("COD_SUBINDICADOR_DE_FAR_SNS");
	 this.codTipoProcedencia=(java.math.BigDecimal)hd.get("COD_TIPO_PROCEDENCIA");
	 this.idEnSsalud=(java.lang.String)hd.get("ID_EN_SSALUD");
	 this.codAgente=(java.math.BigDecimal)hd.get("COD_AGENTE");
	 this.numeroRegistro=(java.math.BigDecimal)hd.get("NUMERO_REGISTRO");
  }

  public InssCargaSns(java.sql.ResultSet rs) throws java.sql.SQLException {
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
	 this.criterioIdentificacionSns=rs.getString("CRITERIO_IDENTIFICACION_SNS");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codPrestacionServicio=rs.getBigDecimal("COD_PRESTACION_SERVICIO");
	 this.codEstado=rs.getBigDecimal("COD_ESTADO");
	 this.codUsuarioSnsTitular=rs.getString("COD_USUARIO_SNS_TITULAR");
	 this.nafSns=rs.getString("NAF_SNS");
	 this.nafTitularSns=rs.getString("NAF_TITULAR_SNS");
	 this.codTitulo=rs.getBigDecimal("COD_TITULO");
	 this.nombreSns=rs.getString("NOMBRE_SNS");
	 this.apellido1Sns=rs.getString("APELLIDO1_SNS");
	 this.apellido2Sns=rs.getString("APELLIDO2_SNS");
	 this.dniNieSns=rs.getString("DNI_NIE_SNS");
	 this.pasaporteSns=rs.getString("PASAPORTE_SNS");
	 this.codUsuarioSnsFarmacia=rs.getString("COD_USUARIO_SNS_FARMACIA");
	 this.codIndicadorDeFarmaciaSns=rs.getString("COD_INDICADOR_DE_FARMACIA_SNS");
	 this.codSubindicadorDeFarSns=rs.getString("COD_SUBINDICADOR_DE_FAR_SNS");
	 this.codTipoProcedencia=rs.getBigDecimal("COD_TIPO_PROCEDENCIA");
	 this.idEnSsalud=rs.getString("ID_EN_SSALUD");
	 this.codAgente=rs.getBigDecimal("COD_AGENTE");
	 this.numeroRegistro=rs.getBigDecimal("NUMERO_REGISTRO");
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
  public java.lang.String getCriterioIdentificacionSns() {
	 return this.criterioIdentificacionSns;
  }
  public void setCriterioIdentificacionSns(java.lang.String criterioIdentificacionSns) {
	 this.criterioIdentificacionSns=criterioIdentificacionSns;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodPrestacionServicio() {
	 return this.codPrestacionServicio;
  }
  public void setCodPrestacionServicio(java.math.BigDecimal codPrestacionServicio) {
	 this.codPrestacionServicio=codPrestacionServicio;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodEstado() {
	 return this.codEstado;
  }
  public void setCodEstado(java.math.BigDecimal codEstado) {
	 this.codEstado=codEstado;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSnsTitular() {
	 return this.codUsuarioSnsTitular;
  }
  public void setCodUsuarioSnsTitular(java.lang.String codUsuarioSnsTitular) {
	 this.codUsuarioSnsTitular=codUsuarioSnsTitular;
	 this.vacio=false;
  }
  public java.lang.String getNafSns() {
	 return this.nafSns;
  }
  public void setNafSns(java.lang.String nafSns) {
	 this.nafSns=nafSns;
	 this.vacio=false;
  }
  public java.lang.String getNafTitularSns() {
	 return this.nafTitularSns;
  }
  public void setNafTitularSns(java.lang.String nafTitularSns) {
	 this.nafTitularSns=nafTitularSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTitulo() {
	 return this.codTitulo;
  }
  public void setCodTitulo(java.math.BigDecimal codTitulo) {
	 this.codTitulo=codTitulo;
	 this.vacio=false;
  }
  public java.lang.String getNombreSns() {
	 return this.nombreSns;
  }
  public void setNombreSns(java.lang.String nombreSns) {
	 this.nombreSns=nombreSns;
	 this.vacio=false;
  }
  public java.lang.String getApellido1Sns() {
	 return this.apellido1Sns;
  }
  public void setApellido1Sns(java.lang.String apellido1Sns) {
	 this.apellido1Sns=apellido1Sns;
	 this.vacio=false;
  }
  public java.lang.String getApellido2Sns() {
	 return this.apellido2Sns;
  }
  public void setApellido2Sns(java.lang.String apellido2Sns) {
	 this.apellido2Sns=apellido2Sns;
	 this.vacio=false;
  }
  public java.lang.String getDniNieSns() {
	 return this.dniNieSns;
  }
  public void setDniNieSns(java.lang.String dniNieSns) {
	 this.dniNieSns=dniNieSns;
	 this.vacio=false;
  }
  public java.lang.String getPasaporteSns() {
	 return this.pasaporteSns;
  }
  public void setPasaporteSns(java.lang.String pasaporteSns) {
	 this.pasaporteSns=pasaporteSns;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSnsFarmacia() {
	 return this.codUsuarioSnsFarmacia;
  }
  public void setCodUsuarioSnsFarmacia(java.lang.String codUsuarioSnsFarmacia) {
	 this.codUsuarioSnsFarmacia=codUsuarioSnsFarmacia;
	 this.vacio=false;
  }
  public java.lang.String getCodIndicadorDeFarmaciaSns() {
	 return this.codIndicadorDeFarmaciaSns;
  }
  public void setCodIndicadorDeFarmaciaSns(java.lang.String codIndicadorDeFarmaciaSns) {
	 this.codIndicadorDeFarmaciaSns=codIndicadorDeFarmaciaSns;
	 this.vacio=false;
  }
  public java.lang.String getCodSubindicadorDeFarSns() {
	 return this.codSubindicadorDeFarSns;
  }
  public void setCodSubindicadorDeFarSns(java.lang.String codSubindicadorDeFarSns) {
	 this.codSubindicadorDeFarSns=codSubindicadorDeFarSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTipoProcedencia() {
	 return this.codTipoProcedencia;
  }
  public void setCodTipoProcedencia(java.math.BigDecimal codTipoProcedencia) {
	 this.codTipoProcedencia=codTipoProcedencia;
	 this.vacio=false;
  }
  public java.lang.String getIdEnSsalud() {
	 return this.idEnSsalud;
  }
  public void setIdEnSsalud(java.lang.String idEnSsalud) {
	 this.idEnSsalud=idEnSsalud;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAgente() {
	 return this.codAgente;
  }
  public void setCodAgente(java.math.BigDecimal codAgente) {
	 this.codAgente=codAgente;
	 this.vacio=false;
  }
  public java.math.BigDecimal getNumeroRegistro() {
	 return this.numeroRegistro;
  }
  public void setNumeroRegistro(java.math.BigDecimal numeroRegistro) {
	 this.numeroRegistro=numeroRegistro;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssCargaSns [");
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
	 buffer.append("criterioIdentificacionSns = ").append(criterioIdentificacionSns);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codPrestacionServicio = ").append(codPrestacionServicio);
	 buffer.append("codEstado = ").append(codEstado);
	 buffer.append("codUsuarioSnsTitular = ").append(codUsuarioSnsTitular);
	 buffer.append("nafSns = ").append(nafSns);
	 buffer.append("nafTitularSns = ").append(nafTitularSns);
	 buffer.append("codTitulo = ").append(codTitulo);
	 buffer.append("nombreSns = ").append(nombreSns);
	 buffer.append("apellido1Sns = ").append(apellido1Sns);
	 buffer.append("apellido2Sns = ").append(apellido2Sns);
	 buffer.append("dniNieSns = ").append(dniNieSns);
	 buffer.append("pasaporteSns = ").append(pasaporteSns);
	 buffer.append("codUsuarioSnsFarmacia = ").append(codUsuarioSnsFarmacia);
	 buffer.append("codIndicadorDeFarmaciaSns = ").append(codIndicadorDeFarmaciaSns);
	 buffer.append("codSubindicadorDeFarSns = ").append(codSubindicadorDeFarSns);
	 buffer.append("codTipoProcedencia = ").append(codTipoProcedencia);
	 buffer.append("idEnSsalud = ").append(idEnSsalud);
	 buffer.append("codAgente = ").append(codAgente);
	 buffer.append("numeroRegistro = ").append(numeroRegistro);
	 buffer.append("]");
	 return buffer.toString();
  }
}

