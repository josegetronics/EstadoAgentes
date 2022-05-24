package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:30 CET 2012
/ ******************************************************/
public class DatosPersonales { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.lang.String codPais;
  private java.math.BigDecimal codSexo;
  private java.math.BigDecimal codComunidad;
  private java.lang.String nombre;
  private java.lang.String apellido1;
  private java.lang.String apellido2;
  private java.sql.Timestamp fechaNac;
  private java.lang.String dniNie;
  private java.math.BigDecimal flaqDniDuplicado;
  private java.math.BigDecimal flagExtranjero;
  private java.lang.String pasaporte;
  private java.lang.String raiz;
  private java.lang.String tarjetaIdentidad;
  private java.lang.String codNacionalidad;

  public static final String NOMBRE_TABLA="DATOS_PERSONALES";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_UPDATE_COD_PAIS="1";
  public static final String INDICE_UPDATE_COD_SEXO="2";
  public static final String INDICE_UPDATE_COD_COMUNIDAD="3";
  public static final String INDICE_UPDATE_NOMBRE="4";
  public static final String INDICE_UPDATE_APELLIDO1="5";
  public static final String INDICE_UPDATE_APELLIDO2="6";
  public static final String INDICE_UPDATE_FECHA_NAC="7";
  public static final String INDICE_UPDATE_DNI_NIE="8";
  public static final String INDICE_UPDATE_FLAQ_DNI_DUPLICADO="9";
  public static final String INDICE_UPDATE_FLAG_EXTRANJERO="10";
  public static final String INDICE_UPDATE_PASAPORTE="11";
  public static final String INDICE_UPDATE_RAIZ="12";
  public static final String INDICE_UPDATE_TARJETA_IDENTIDAD="13";
  public static final String INDICE_UPDATE_COD_NACIONALIDAD="14";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="15";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_PAIS = ?, COD_SEXO = ?, COD_COMUNIDAD = ?, NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, FECHA_NAC = ?, DNI_NIE = ?, FLAQ_DNI_DUPLICADO = ?, FLAG_EXTRANJERO = ?, PASAPORTE = ?, RAIZ = ?, TARJETA_IDENTIDAD = ?, COD_NACIONALIDAD = ? where COD_USUARIO_SNS = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_PAIS,codPais);
	  hParam.put(INDICE_UPDATE_COD_SEXO,codSexo);
	  hParam.put(INDICE_UPDATE_COD_COMUNIDAD,codComunidad);
	  hParam.put(INDICE_UPDATE_NOMBRE,nombre);
	  hParam.put(INDICE_UPDATE_APELLIDO1,apellido1);
	  hParam.put(INDICE_UPDATE_APELLIDO2,apellido2);
	  hParam.put(INDICE_UPDATE_FECHA_NAC,fechaNac);
	  hParam.put(INDICE_UPDATE_DNI_NIE,dniNie);
	  hParam.put(INDICE_UPDATE_FLAQ_DNI_DUPLICADO,flaqDniDuplicado);
	  hParam.put(INDICE_UPDATE_FLAG_EXTRANJERO,flagExtranjero);
	  hParam.put(INDICE_UPDATE_PASAPORTE,pasaporte);
	  hParam.put(INDICE_UPDATE_RAIZ,raiz);
	  hParam.put(INDICE_UPDATE_TARJETA_IDENTIDAD,tarjetaIdentidad);
	  hParam.put(INDICE_UPDATE_COD_NACIONALIDAD,codNacionalidad);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_PAIS="2";
  public static final String INDICE_INSERT_COD_SEXO="3";
  public static final String INDICE_INSERT_COD_COMUNIDAD="4";
  public static final String INDICE_INSERT_NOMBRE="5";
  public static final String INDICE_INSERT_APELLIDO1="6";
  public static final String INDICE_INSERT_APELLIDO2="7";
  public static final String INDICE_INSERT_FECHA_NAC="8";
  public static final String INDICE_INSERT_DNI_NIE="9";
  public static final String INDICE_INSERT_FLAQ_DNI_DUPLICADO="10";
  public static final String INDICE_INSERT_FLAG_EXTRANJERO="11";
  public static final String INDICE_INSERT_PASAPORTE="12";
  public static final String INDICE_INSERT_RAIZ="13";
  public static final String INDICE_INSERT_TARJETA_IDENTIDAD="14";
  public static final String INDICE_INSERT_COD_NACIONALIDAD="15";
  public static final String QUERY_INSERT="INSERT INTO DATOS_PERSONALES (COD_USUARIO_SNS,COD_PAIS,COD_SEXO,COD_COMUNIDAD,NOMBRE,APELLIDO1,APELLIDO2,FECHA_NAC,DNI_NIE,FLAQ_DNI_DUPLICADO,FLAG_EXTRANJERO,PASAPORTE,RAIZ,TARJETA_IDENTIDAD,COD_NACIONALIDAD) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_PAIS,codPais);
	  hParam.put(INDICE_INSERT_COD_SEXO,codSexo);
	  hParam.put(INDICE_INSERT_COD_COMUNIDAD,codComunidad);
	  hParam.put(INDICE_INSERT_NOMBRE,nombre);
	  hParam.put(INDICE_INSERT_APELLIDO1,apellido1);
	  hParam.put(INDICE_INSERT_APELLIDO2,apellido2);
	  hParam.put(INDICE_INSERT_FECHA_NAC,fechaNac);
	  hParam.put(INDICE_INSERT_DNI_NIE,dniNie);
	  hParam.put(INDICE_INSERT_FLAQ_DNI_DUPLICADO,flaqDniDuplicado);
	  hParam.put(INDICE_INSERT_FLAG_EXTRANJERO,flagExtranjero);
	  hParam.put(INDICE_INSERT_PASAPORTE,pasaporte);
	  hParam.put(INDICE_INSERT_RAIZ,raiz);
	  hParam.put(INDICE_INSERT_TARJETA_IDENTIDAD,tarjetaIdentidad);
	  hParam.put(INDICE_INSERT_COD_NACIONALIDAD,codNacionalidad);
	  return hParam;
  }

  public DatosPersonales() {this.vacio=true;}
  public DatosPersonales(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codPais=(java.lang.String)hd.get("COD_PAIS");
	 this.codSexo=(java.math.BigDecimal)hd.get("COD_SEXO");
	 this.codComunidad=(java.math.BigDecimal)hd.get("COD_COMUNIDAD");
	 this.nombre=(java.lang.String)hd.get("NOMBRE");
	 this.apellido1=(java.lang.String)hd.get("APELLIDO1");
	 this.apellido2=(java.lang.String)hd.get("APELLIDO2");
	 this.fechaNac=(java.sql.Timestamp)hd.get("FECHA_NAC");
	 this.dniNie=(java.lang.String)hd.get("DNI_NIE");
	 this.flaqDniDuplicado=(java.math.BigDecimal)hd.get("FLAQ_DNI_DUPLICADO");
	 this.flagExtranjero=(java.math.BigDecimal)hd.get("FLAG_EXTRANJERO");
	 this.pasaporte=(java.lang.String)hd.get("PASAPORTE");
	 this.raiz=(java.lang.String)hd.get("RAIZ");
	 this.tarjetaIdentidad=(java.lang.String)hd.get("TARJETA_IDENTIDAD");
	 this.codNacionalidad=(java.lang.String)hd.get("COD_NACIONALIDAD");
  }

  public DatosPersonales(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codPais=rs.getString("COD_PAIS");
	 this.codSexo=rs.getBigDecimal("COD_SEXO");
	 this.codComunidad=rs.getBigDecimal("COD_COMUNIDAD");
	 this.nombre=rs.getString("NOMBRE");
	 this.apellido1=rs.getString("APELLIDO1");
	 this.apellido2=rs.getString("APELLIDO2");
	 this.fechaNac=rs.getTimestamp("FECHA_NAC");
	 this.dniNie=rs.getString("DNI_NIE");
	 this.flaqDniDuplicado=rs.getBigDecimal("FLAQ_DNI_DUPLICADO");
	 this.flagExtranjero=rs.getBigDecimal("FLAG_EXTRANJERO");
	 this.pasaporte=rs.getString("PASAPORTE");
	 this.raiz=rs.getString("RAIZ");
	 this.tarjetaIdentidad=rs.getString("TARJETA_IDENTIDAD");
	 this.codNacionalidad=rs.getString("COD_NACIONALIDAD");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.lang.String getCodPais() {
	 return this.codPais;
  }
  public void setCodPais(java.lang.String codPais) {
	 this.codPais=codPais;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodSexo() {
	 return this.codSexo;
  }
  public void setCodSexo(java.math.BigDecimal codSexo) {
	 this.codSexo=codSexo;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodComunidad() {
	 return this.codComunidad;
  }
  public void setCodComunidad(java.math.BigDecimal codComunidad) {
	 this.codComunidad=codComunidad;
	 this.vacio=false;
  }
  public java.lang.String getNombre() {
	 return this.nombre;
  }
  public void setNombre(java.lang.String nombre) {
	 this.nombre=nombre;
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
  public java.sql.Timestamp getFechaNac() {
	 return this.fechaNac;
  }
  public void setFechaNac(java.sql.Timestamp fechaNac) {
	 this.fechaNac=fechaNac;
	 this.vacio=false;
  }
  public java.lang.String getDniNie() {
	 return this.dniNie;
  }
  public void setDniNie(java.lang.String dniNie) {
	 this.dniNie=dniNie;
	 this.vacio=false;
  }
  public java.math.BigDecimal getFlaqDniDuplicado() {
	 return this.flaqDniDuplicado;
  }
  public void setFlaqDniDuplicado(java.math.BigDecimal flaqDniDuplicado) {
	 this.flaqDniDuplicado=flaqDniDuplicado;
	 this.vacio=false;
  }
  public java.math.BigDecimal getFlagExtranjero() {
	 return this.flagExtranjero;
  }
  public void setFlagExtranjero(java.math.BigDecimal flagExtranjero) {
	 this.flagExtranjero=flagExtranjero;
	 this.vacio=false;
  }
  public java.lang.String getPasaporte() {
	 return this.pasaporte;
  }
  public void setPasaporte(java.lang.String pasaporte) {
	 this.pasaporte=pasaporte;
	 this.vacio=false;
  }
  public java.lang.String getRaiz() {
	 return this.raiz;
  }
  public void setRaiz(java.lang.String raiz) {
	 this.raiz=raiz;
	 this.vacio=false;
  }
  public java.lang.String getTarjetaIdentidad() {
	 return this.tarjetaIdentidad;
  }
  public void setTarjetaIdentidad(java.lang.String tarjetaIdentidad) {
	 this.tarjetaIdentidad=tarjetaIdentidad;
	 this.vacio=false;
  }
  public java.lang.String getCodNacionalidad() {
	 return this.codNacionalidad;
  }
  public void setCodNacionalidad(java.lang.String codNacionalidad) {
	 this.codNacionalidad=codNacionalidad;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("DatosPersonales [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codPais = ").append(codPais);
	 buffer.append("codSexo = ").append(codSexo);
	 buffer.append("codComunidad = ").append(codComunidad);
	 buffer.append("nombre = ").append(nombre);
	 buffer.append("apellido1 = ").append(apellido1);
	 buffer.append("apellido2 = ").append(apellido2);
	 buffer.append("fechaNac = ").append(fechaNac);
	 buffer.append("dniNie = ").append(dniNie);
	 buffer.append("flaqDniDuplicado = ").append(flaqDniDuplicado);
	 buffer.append("flagExtranjero = ").append(flagExtranjero);
	 buffer.append("pasaporte = ").append(pasaporte);
	 buffer.append("raiz = ").append(raiz);
	 buffer.append("tarjetaIdentidad = ").append(tarjetaIdentidad);
	 buffer.append("codNacionalidad = ").append(codNacionalidad);
	 buffer.append("]");
	 return buffer.toString();
  }
}

