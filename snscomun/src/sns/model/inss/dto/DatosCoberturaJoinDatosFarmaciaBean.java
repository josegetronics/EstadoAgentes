package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Sat Jun 30 22:15:53 CEST 2012
/ ******************************************************/
public class DatosCoberturaJoinDatosFarmaciaBean { 

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
  private java.lang.String codIndicadorDeFarmacia;
  private java.lang.String codSubindicadorDeFarmacia;
  private java.math.BigDecimal codTipoProcedencia;


  public DatosCoberturaJoinDatosFarmaciaBean() {this.vacio=true;}
  public DatosCoberturaJoinDatosFarmaciaBean(java.util.HashMap hd) {
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
	 this.codIndicadorDeFarmacia=(java.lang.String)hd.get("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=(java.lang.String)hd.get("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codTipoProcedencia=(java.math.BigDecimal)hd.get("COD_TIPO_PROCEDENCIA");
  }

  public DatosCoberturaJoinDatosFarmaciaBean(java.sql.ResultSet rs) throws java.sql.SQLException {
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
	 this.codIndicadorDeFarmacia=rs.getString("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=rs.getString("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codTipoProcedencia=rs.getBigDecimal("COD_TIPO_PROCEDENCIA");
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
  public java.math.BigDecimal getCodTipoProcedencia() {
	 return this.codTipoProcedencia;
  }
  public void setCodTipoProcedencia(java.math.BigDecimal codTipoProcedencia) {
	 this.codTipoProcedencia=codTipoProcedencia;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }}
