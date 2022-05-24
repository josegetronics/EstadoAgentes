package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:29 CET 2012
/ ******************************************************/
public class DatosDomicilio { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.math.BigDecimal codMunicipio;
  private java.math.BigDecimal codProvincia;
  private java.lang.String codPais;
  private java.lang.String nombreVia;
  private java.lang.String numero;
  private java.math.BigDecimal bis;
  private java.lang.String escalera;
  private java.lang.String puerta;
  private java.lang.String codigoPostal;
  private java.lang.String piso;
  private java.lang.String bloque;
  private java.lang.String codVia;

  public static final String NOMBRE_TABLA="DATOS_DOMICILIO";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_UPDATE_COD_MUNICIPIO="1";
  public static final String INDICE_UPDATE_COD_PROVINCIA="2";
  public static final String INDICE_UPDATE_COD_PAIS="3";
  public static final String INDICE_UPDATE_NOMBRE_VIA="4";
  public static final String INDICE_UPDATE_NUMERO="5";
  public static final String INDICE_UPDATE_BIS="6";
  public static final String INDICE_UPDATE_ESCALERA="7";
  public static final String INDICE_UPDATE_PUERTA="8";
  public static final String INDICE_UPDATE_CODIGO_POSTAL="9";
  public static final String INDICE_UPDATE_PISO="10";
  public static final String INDICE_UPDATE_BLOQUE="11";
  public static final String INDICE_UPDATE_COD_VIA="12";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="13";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_MUNICIPIO = ?, COD_PROVINCIA = ?, COD_PAIS = ?, NOMBRE_VIA = ?, NUMERO = ?, BIS = ?, ESCALERA = ?, PUERTA = ?, CODIGO_POSTAL = ?, PISO = ?, BLOQUE = ?, COD_VIA = ? where COD_USUARIO_SNS = ?";


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
	  hParam.put(INDICE_UPDATE_COD_MUNICIPIO,codMunicipio);
	  hParam.put(INDICE_UPDATE_COD_PROVINCIA,codProvincia);
	  hParam.put(INDICE_UPDATE_COD_PAIS,codPais);
	  hParam.put(INDICE_UPDATE_NOMBRE_VIA,nombreVia);
	  hParam.put(INDICE_UPDATE_NUMERO,numero);
	  hParam.put(INDICE_UPDATE_BIS,bis);
	  hParam.put(INDICE_UPDATE_ESCALERA,escalera);
	  hParam.put(INDICE_UPDATE_PUERTA,puerta);
	  hParam.put(INDICE_UPDATE_CODIGO_POSTAL,codigoPostal);
	  hParam.put(INDICE_UPDATE_PISO,piso);
	  hParam.put(INDICE_UPDATE_BLOQUE,bloque);
	  hParam.put(INDICE_UPDATE_COD_VIA,codVia);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_MUNICIPIO="2";
  public static final String INDICE_INSERT_COD_PROVINCIA="3";
  public static final String INDICE_INSERT_COD_PAIS="4";
  public static final String INDICE_INSERT_NOMBRE_VIA="5";
  public static final String INDICE_INSERT_NUMERO="6";
  public static final String INDICE_INSERT_BIS="7";
  public static final String INDICE_INSERT_ESCALERA="8";
  public static final String INDICE_INSERT_PUERTA="9";
  public static final String INDICE_INSERT_CODIGO_POSTAL="10";
  public static final String INDICE_INSERT_PISO="11";
  public static final String INDICE_INSERT_BLOQUE="12";
  public static final String INDICE_INSERT_COD_VIA="13";
  public static final String QUERY_INSERT="INSERT INTO DATOS_DOMICILIO (COD_USUARIO_SNS,COD_MUNICIPIO,COD_PROVINCIA,COD_PAIS,NOMBRE_VIA,NUMERO,BIS,ESCALERA,PUERTA,CODIGO_POSTAL,PISO,BLOQUE,COD_VIA) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_MUNICIPIO,codMunicipio);
	  hParam.put(INDICE_INSERT_COD_PROVINCIA,codProvincia);
	  hParam.put(INDICE_INSERT_COD_PAIS,codPais);
	  hParam.put(INDICE_INSERT_NOMBRE_VIA,nombreVia);
	  hParam.put(INDICE_INSERT_NUMERO,numero);
	  hParam.put(INDICE_INSERT_BIS,bis);
	  hParam.put(INDICE_INSERT_ESCALERA,escalera);
	  hParam.put(INDICE_INSERT_PUERTA,puerta);
	  hParam.put(INDICE_INSERT_CODIGO_POSTAL,codigoPostal);
	  hParam.put(INDICE_INSERT_PISO,piso);
	  hParam.put(INDICE_INSERT_BLOQUE,bloque);
	  hParam.put(INDICE_INSERT_COD_VIA,codVia);
	  return hParam;
  }

  public DatosDomicilio() {this.vacio=true;}
  public DatosDomicilio(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codMunicipio=(java.math.BigDecimal)hd.get("COD_MUNICIPIO");
	 this.codProvincia=(java.math.BigDecimal)hd.get("COD_PROVINCIA");
	 this.codPais=(java.lang.String)hd.get("COD_PAIS");
	 this.nombreVia=(java.lang.String)hd.get("NOMBRE_VIA");
	 this.numero=(java.lang.String)hd.get("NUMERO");
	 this.bis=(java.math.BigDecimal)hd.get("BIS");
	 this.escalera=(java.lang.String)hd.get("ESCALERA");
	 this.puerta=(java.lang.String)hd.get("PUERTA");
	 this.codigoPostal=(java.lang.String)hd.get("CODIGO_POSTAL");
	 this.piso=(java.lang.String)hd.get("PISO");
	 this.bloque=(java.lang.String)hd.get("BLOQUE");
	 this.codVia=(java.lang.String)hd.get("COD_VIA");
  }

  public DatosDomicilio(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codMunicipio=rs.getBigDecimal("COD_MUNICIPIO");
	 this.codProvincia=rs.getBigDecimal("COD_PROVINCIA");
	 this.codPais=rs.getString("COD_PAIS");
	 this.nombreVia=rs.getString("NOMBRE_VIA");
	 this.numero=rs.getString("NUMERO");
	 this.bis=rs.getBigDecimal("BIS");
	 this.escalera=rs.getString("ESCALERA");
	 this.puerta=rs.getString("PUERTA");
	 this.codigoPostal=rs.getString("CODIGO_POSTAL");
	 this.piso=rs.getString("PISO");
	 this.bloque=rs.getString("BLOQUE");
	 this.codVia=rs.getString("COD_VIA");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodMunicipio() {
	 return this.codMunicipio;
  }
  public void setCodMunicipio(java.math.BigDecimal codMunicipio) {
	 this.codMunicipio=codMunicipio;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodProvincia() {
	 return this.codProvincia;
  }
  public void setCodProvincia(java.math.BigDecimal codProvincia) {
	 this.codProvincia=codProvincia;
	 this.vacio=false;
  }
  public java.lang.String getCodPais() {
	 return this.codPais;
  }
  public void setCodPais(java.lang.String codPais) {
	 this.codPais=codPais;
	 this.vacio=false;
  }
  public java.lang.String getNombreVia() {
	 return this.nombreVia;
  }
  public void setNombreVia(java.lang.String nombreVia) {
	 this.nombreVia=nombreVia;
	 this.vacio=false;
  }
  public java.lang.String getNumero() {
	 return this.numero;
  }
  public void setNumero(java.lang.String numero) {
	 this.numero=numero;
	 this.vacio=false;
  }
  public java.math.BigDecimal getBis() {
	 return this.bis;
  }
  public void setBis(java.math.BigDecimal bis) {
	 this.bis=bis;
	 this.vacio=false;
  }
  public java.lang.String getEscalera() {
	 return this.escalera;
  }
  public void setEscalera(java.lang.String escalera) {
	 this.escalera=escalera;
	 this.vacio=false;
  }
  public java.lang.String getPuerta() {
	 return this.puerta;
  }
  public void setPuerta(java.lang.String puerta) {
	 this.puerta=puerta;
	 this.vacio=false;
  }
  public java.lang.String getCodigoPostal() {
	 return this.codigoPostal;
  }
  public void setCodigoPostal(java.lang.String codigoPostal) {
	 this.codigoPostal=codigoPostal;
	 this.vacio=false;
  }
  public java.lang.String getPiso() {
	 return this.piso;
  }
  public void setPiso(java.lang.String piso) {
	 this.piso=piso;
	 this.vacio=false;
  }
  public java.lang.String getBloque() {
	 return this.bloque;
  }
  public void setBloque(java.lang.String bloque) {
	 this.bloque=bloque;
	 this.vacio=false;
  }
  public java.lang.String getCodVia() {
	 return this.codVia;
  }
  public void setCodVia(java.lang.String codVia) {
	 this.codVia=codVia;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("DatosDomicilio [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codMunicipio = ").append(codMunicipio);
	 buffer.append("codProvincia = ").append(codProvincia);
	 buffer.append("codPais = ").append(codPais);
	 buffer.append("nombreVia = ").append(nombreVia);
	 buffer.append("numero = ").append(numero);
	 buffer.append("bis = ").append(bis);
	 buffer.append("escalera = ").append(escalera);
	 buffer.append("puerta = ").append(puerta);
	 buffer.append("codigoPostal = ").append(codigoPostal);
	 buffer.append("piso = ").append(piso);
	 buffer.append("bloque = ").append(bloque);
	 buffer.append("codVia = ").append(codVia);
	 buffer.append("]");
	 return buffer.toString();
  }
}

