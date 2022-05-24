package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:28 CET 2012
/ ******************************************************/
public class DatosCobertura { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.lang.String codUsuarioSnsTitular;
  private java.math.BigDecimal codTitulo;
  private java.math.BigDecimal codTituloHeredable;
  private java.math.BigDecimal codAseguradora;
  private java.math.BigDecimal codGestora;
  private java.math.BigDecimal codColaboradora;
  private java.math.BigDecimal codProveedor;
  private java.math.BigDecimal codSituacion;
  private java.lang.String naf;
  private java.math.BigDecimal flagssocial;
  private java.lang.String nafTitular;
  private java.lang.String interno;
  private java.math.BigDecimal codProveedorAp;
  private java.math.BigDecimal codProveedorAe;
  private java.math.BigDecimal codProveedorFar;
  private java.math.BigDecimal codProveedorUrg;

  public static final String NOMBRE_TABLA="DATOS_COBERTURA";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_UPDATE_COD_USUARIO_SNS_TITULAR="1";
  public static final String INDICE_UPDATE_COD_TITULO="2";
  public static final String INDICE_UPDATE_COD_TITULO_HEREDABLE="3";
  public static final String INDICE_UPDATE_COD_ASEGURADORA="4";
  public static final String INDICE_UPDATE_COD_GESTORA="5";
  public static final String INDICE_UPDATE_COD_COLABORADORA="6";
  public static final String INDICE_UPDATE_COD_PROVEEDOR="7";
  public static final String INDICE_UPDATE_COD_SITUACION="8";
  public static final String INDICE_UPDATE_NAF="9";
  public static final String INDICE_UPDATE_FLAGSSOCIAL="10";
  public static final String INDICE_UPDATE_NAF_TITULAR="11";
  public static final String INDICE_UPDATE_INTERNO="12";
  public static final String INDICE_UPDATE_COD_PROVEEDOR_AP="13";
  public static final String INDICE_UPDATE_COD_PROVEEDOR_AE="14";
  public static final String INDICE_UPDATE_COD_PROVEEDOR_FAR="15";
  public static final String INDICE_UPDATE_COD_PROVEEDOR_URG="16";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="17";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO_SNS_TITULAR = ?, COD_TITULO = ?, COD_TITULO_HEREDABLE = ?, COD_ASEGURADORA = ?, COD_GESTORA = ?, COD_COLABORADORA = ?, COD_PROVEEDOR = ?, COD_SITUACION = ?, NAF = ?, FLAGSSOCIAL = ?, NAF_TITULAR = ?, INTERNO = ?, COD_PROVEEDOR_AP = ?, COD_PROVEEDOR_AE = ?, COD_PROVEEDOR_FAR = ?, COD_PROVEEDOR_URG = ? where COD_USUARIO_SNS = ?";


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
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS_TITULAR,codUsuarioSnsTitular);
	  hParam.put(INDICE_UPDATE_COD_TITULO,codTitulo);
	  hParam.put(INDICE_UPDATE_COD_TITULO_HEREDABLE,codTituloHeredable);
	  hParam.put(INDICE_UPDATE_COD_ASEGURADORA,codAseguradora);
	  hParam.put(INDICE_UPDATE_COD_GESTORA,codGestora);
	  hParam.put(INDICE_UPDATE_COD_COLABORADORA,codColaboradora);
	  hParam.put(INDICE_UPDATE_COD_PROVEEDOR,codProveedor);
	  hParam.put(INDICE_UPDATE_COD_SITUACION,codSituacion);
	  hParam.put(INDICE_UPDATE_NAF,naf);
	  hParam.put(INDICE_UPDATE_FLAGSSOCIAL,flagssocial);
	  hParam.put(INDICE_UPDATE_NAF_TITULAR,nafTitular);
	  hParam.put(INDICE_UPDATE_INTERNO,interno);
	  hParam.put(INDICE_UPDATE_COD_PROVEEDOR_AP,codProveedorAp);
	  hParam.put(INDICE_UPDATE_COD_PROVEEDOR_AE,codProveedorAe);
	  hParam.put(INDICE_UPDATE_COD_PROVEEDOR_FAR,codProveedorFar);
	  hParam.put(INDICE_UPDATE_COD_PROVEEDOR_URG,codProveedorUrg);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_USUARIO_SNS_TITULAR="2";
  public static final String INDICE_INSERT_COD_TITULO="3";
  public static final String INDICE_INSERT_COD_TITULO_HEREDABLE="4";
  public static final String INDICE_INSERT_COD_ASEGURADORA="5";
  public static final String INDICE_INSERT_COD_GESTORA="6";
  public static final String INDICE_INSERT_COD_COLABORADORA="7";
  public static final String INDICE_INSERT_COD_PROVEEDOR="8";
  public static final String INDICE_INSERT_COD_SITUACION="9";
  public static final String INDICE_INSERT_NAF="10";
  public static final String INDICE_INSERT_FLAGSSOCIAL="11";
  public static final String INDICE_INSERT_NAF_TITULAR="12";
  public static final String INDICE_INSERT_INTERNO="13";
  public static final String INDICE_INSERT_COD_PROVEEDOR_AP="14";
  public static final String INDICE_INSERT_COD_PROVEEDOR_AE="15";
  public static final String INDICE_INSERT_COD_PROVEEDOR_FAR="16";
  public static final String INDICE_INSERT_COD_PROVEEDOR_URG="17";
  public static final String QUERY_INSERT="INSERT INTO DATOS_COBERTURA (COD_USUARIO_SNS,COD_USUARIO_SNS_TITULAR,COD_TITULO,COD_TITULO_HEREDABLE,COD_ASEGURADORA,COD_GESTORA,COD_COLABORADORA,COD_PROVEEDOR,COD_SITUACION,NAF,FLAGSSOCIAL,NAF_TITULAR,INTERNO,COD_PROVEEDOR_AP,COD_PROVEEDOR_AE,COD_PROVEEDOR_FAR,COD_PROVEEDOR_URG) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS_TITULAR,codUsuarioSnsTitular);
	  hParam.put(INDICE_INSERT_COD_TITULO,codTitulo);
	  hParam.put(INDICE_INSERT_COD_TITULO_HEREDABLE,codTituloHeredable);
	  hParam.put(INDICE_INSERT_COD_ASEGURADORA,codAseguradora);
	  hParam.put(INDICE_INSERT_COD_GESTORA,codGestora);
	  hParam.put(INDICE_INSERT_COD_COLABORADORA,codColaboradora);
	  hParam.put(INDICE_INSERT_COD_PROVEEDOR,codProveedor);
	  hParam.put(INDICE_INSERT_COD_SITUACION,codSituacion);
	  hParam.put(INDICE_INSERT_NAF,naf);
	  hParam.put(INDICE_INSERT_FLAGSSOCIAL,flagssocial);
	  hParam.put(INDICE_INSERT_NAF_TITULAR,nafTitular);
	  hParam.put(INDICE_INSERT_INTERNO,interno);
	  hParam.put(INDICE_INSERT_COD_PROVEEDOR_AP,codProveedorAp);
	  hParam.put(INDICE_INSERT_COD_PROVEEDOR_AE,codProveedorAe);
	  hParam.put(INDICE_INSERT_COD_PROVEEDOR_FAR,codProveedorFar);
	  hParam.put(INDICE_INSERT_COD_PROVEEDOR_URG,codProveedorUrg);
	  return hParam;
  }

  public DatosCobertura() {this.vacio=true;}
  public DatosCobertura(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codUsuarioSnsTitular=(java.lang.String)hd.get("COD_USUARIO_SNS_TITULAR");
	 this.codTitulo=(java.math.BigDecimal)hd.get("COD_TITULO");
	 this.codTituloHeredable=(java.math.BigDecimal)hd.get("COD_TITULO_HEREDABLE");
	 this.codAseguradora=(java.math.BigDecimal)hd.get("COD_ASEGURADORA");
	 this.codGestora=(java.math.BigDecimal)hd.get("COD_GESTORA");
	 this.codColaboradora=(java.math.BigDecimal)hd.get("COD_COLABORADORA");
	 this.codProveedor=(java.math.BigDecimal)hd.get("COD_PROVEEDOR");
	 this.codSituacion=(java.math.BigDecimal)hd.get("COD_SITUACION");
	 this.naf=(java.lang.String)hd.get("NAF");
	 this.flagssocial=(java.math.BigDecimal)hd.get("FLAGSSOCIAL");
	 this.nafTitular=(java.lang.String)hd.get("NAF_TITULAR");
	 this.interno=(java.lang.String)hd.get("INTERNO");
	 this.codProveedorAp=(java.math.BigDecimal)hd.get("COD_PROVEEDOR_AP");
	 this.codProveedorAe=(java.math.BigDecimal)hd.get("COD_PROVEEDOR_AE");
	 this.codProveedorFar=(java.math.BigDecimal)hd.get("COD_PROVEEDOR_FAR");
	 this.codProveedorUrg=(java.math.BigDecimal)hd.get("COD_PROVEEDOR_URG");
  }

  public DatosCobertura(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codUsuarioSnsTitular=rs.getString("COD_USUARIO_SNS_TITULAR");
	 this.codTitulo=rs.getBigDecimal("COD_TITULO");
	 this.codTituloHeredable=rs.getBigDecimal("COD_TITULO_HEREDABLE");
	 this.codAseguradora=rs.getBigDecimal("COD_ASEGURADORA");
	 this.codGestora=rs.getBigDecimal("COD_GESTORA");
	 this.codColaboradora=rs.getBigDecimal("COD_COLABORADORA");
	 this.codProveedor=rs.getBigDecimal("COD_PROVEEDOR");
	 this.codSituacion=rs.getBigDecimal("COD_SITUACION");
	 this.naf=rs.getString("NAF");
	 this.flagssocial=rs.getBigDecimal("FLAGSSOCIAL");
	 this.nafTitular=rs.getString("NAF_TITULAR");
	 this.interno=rs.getString("INTERNO");
	 this.codProveedorAp=rs.getBigDecimal("COD_PROVEEDOR_AP");
	 this.codProveedorAe=rs.getBigDecimal("COD_PROVEEDOR_AE");
	 this.codProveedorFar=rs.getBigDecimal("COD_PROVEEDOR_FAR");
	 this.codProveedorUrg=rs.getBigDecimal("COD_PROVEEDOR_URG");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSnsTitular() {
	 return this.codUsuarioSnsTitular;
  }
  public void setCodUsuarioSnsTitular(java.lang.String codUsuarioSnsTitular) {
	 this.codUsuarioSnsTitular=codUsuarioSnsTitular;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTitulo() {
	 return this.codTitulo;
  }
  public void setCodTitulo(java.math.BigDecimal codTitulo) {
	 this.codTitulo=codTitulo;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTituloHeredable() {
	 return this.codTituloHeredable;
  }
  public void setCodTituloHeredable(java.math.BigDecimal codTituloHeredable) {
	 this.codTituloHeredable=codTituloHeredable;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAseguradora() {
	 return this.codAseguradora;
  }
  public void setCodAseguradora(java.math.BigDecimal codAseguradora) {
	 this.codAseguradora=codAseguradora;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodGestora() {
	 return this.codGestora;
  }
  public void setCodGestora(java.math.BigDecimal codGestora) {
	 this.codGestora=codGestora;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodColaboradora() {
	 return this.codColaboradora;
  }
  public void setCodColaboradora(java.math.BigDecimal codColaboradora) {
	 this.codColaboradora=codColaboradora;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProveedor() {
	 return this.codProveedor;
  }
  public void setCodProveedor(java.math.BigDecimal codProveedor) {
	 this.codProveedor=codProveedor;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodSituacion() {
	 return this.codSituacion;
  }
  public void setCodSituacion(java.math.BigDecimal codSituacion) {
	 this.codSituacion=codSituacion;
	 this.vacio=false;
  }
  public java.lang.String getNaf() {
	 return this.naf;
  }
  public void setNaf(java.lang.String naf) {
	 this.naf=naf;
	 this.vacio=false;
  }
  public java.math.BigDecimal getFlagssocial() {
	 return this.flagssocial;
  }
  public void setFlagssocial(java.math.BigDecimal flagssocial) {
	 this.flagssocial=flagssocial;
	 this.vacio=false;
  }
  public java.lang.String getNafTitular() {
	 return this.nafTitular;
  }
  public void setNafTitular(java.lang.String nafTitular) {
	 this.nafTitular=nafTitular;
	 this.vacio=false;
  }
  public java.lang.String getInterno() {
	 return this.interno;
  }
  public void setInterno(java.lang.String interno) {
	 this.interno=interno;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProveedorAp() {
	 return this.codProveedorAp;
  }
  public void setCodProveedorAp(java.math.BigDecimal codProveedorAp) {
	 this.codProveedorAp=codProveedorAp;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProveedorAe() {
	 return this.codProveedorAe;
  }
  public void setCodProveedorAe(java.math.BigDecimal codProveedorAe) {
	 this.codProveedorAe=codProveedorAe;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProveedorFar() {
	 return this.codProveedorFar;
  }
  public void setCodProveedorFar(java.math.BigDecimal codProveedorFar) {
	 this.codProveedorFar=codProveedorFar;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProveedorUrg() {
	 return this.codProveedorUrg;
  }
  public void setCodProveedorUrg(java.math.BigDecimal codProveedorUrg) {
	 this.codProveedorUrg=codProveedorUrg;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("DatosCobertura [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codUsuarioSnsTitular = ").append(codUsuarioSnsTitular);
	 buffer.append("codTitulo = ").append(codTitulo);
	 buffer.append("codTituloHeredable = ").append(codTituloHeredable);
	 buffer.append("codAseguradora = ").append(codAseguradora);
	 buffer.append("codGestora = ").append(codGestora);
	 buffer.append("codColaboradora = ").append(codColaboradora);
	 buffer.append("codProveedor = ").append(codProveedor);
	 buffer.append("codSituacion = ").append(codSituacion);
	 buffer.append("naf = ").append(naf);
	 buffer.append("flagssocial = ").append(flagssocial);
	 buffer.append("nafTitular = ").append(nafTitular);
	 buffer.append("interno = ").append(interno);
	 buffer.append("codProveedorAp = ").append(codProveedorAp);
	 buffer.append("codProveedorAe = ").append(codProveedorAe);
	 buffer.append("codProveedorFar = ").append(codProveedorFar);
	 buffer.append("codProveedorUrg = ").append(codProveedorUrg);
	 buffer.append("]");
	 return buffer.toString();
  }
}

