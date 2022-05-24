package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Wed Nov 14 17:51:10 CET 2012
/ ******************************************************/
public class InssCuarentenaCruceSns { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssCuarentena;
  private java.math.BigDecimal idInssFichero;
  private java.math.BigDecimal numeroLinea;
  private java.lang.String ipf;
  private java.lang.String codUsuarioSns;
  private java.lang.String criterioIdentificacionSns;
  private java.lang.String causa;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_ID_INSS_CUARENTENA";


  public static final String NOMBRE_TABLA="INSS_CUARENTENA_CRUCE_SNS";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_CUARENTENA="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_CUARENTENA = ?";


  public static final String INDICE_DELETE_ID_INSS_CUARENTENA="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_CUARENTENA = ?";


  public static final String INDICE_UPDATE_ID_INSS_FICHERO="1";
  public static final String INDICE_UPDATE_NUMERO_LINEA="2";
  public static final String INDICE_UPDATE_IPF="3";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="4";
  public static final String INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS="5";
  public static final String INDICE_UPDATE_CAUSA="6";
  public static final String INDICE_UPDATE_ID_INSS_CUARENTENA="7";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set ID_INSS_FICHERO = ?, NUMERO_LINEA = ?, IPF = ?, COD_USUARIO_SNS = ?, CRITERIO_IDENTIFICACION_SNS = ?, CAUSA = ? where ID_INSS_CUARENTENA = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_CUARENTENA,idInssCuarentena);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_CUARENTENA,idInssCuarentena);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_UPDATE_NUMERO_LINEA,numeroLinea);
	  hParam.put(INDICE_UPDATE_IPF,ipf);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  hParam.put(INDICE_UPDATE_CAUSA,causa);
	  hParam.put(INDICE_UPDATE_ID_INSS_CUARENTENA,idInssCuarentena);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_CUARENTENA="1";
  public static final String INDICE_INSERT_ID_INSS_FICHERO="2";
  public static final String INDICE_INSERT_NUMERO_LINEA="3";
  public static final String INDICE_INSERT_IPF="4";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="5";
  public static final String INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS="6";
  public static final String INDICE_INSERT_CAUSA="7";
  public static final String QUERY_INSERT="INSERT INTO INSS_CUARENTENA_CRUCE_SNS (ID_INSS_CUARENTENA,ID_INSS_FICHERO,NUMERO_LINEA,IPF,COD_USUARIO_SNS,CRITERIO_IDENTIFICACION_SNS,CAUSA) VALUES (?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_CUARENTENA,idInssCuarentena);
	  hParam.put(INDICE_INSERT_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_INSERT_NUMERO_LINEA,numeroLinea);
	  hParam.put(INDICE_INSERT_IPF,ipf);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  hParam.put(INDICE_INSERT_CAUSA,causa);
	  return hParam;
  }

  public InssCuarentenaCruceSns() {this.vacio=true;}
  public InssCuarentenaCruceSns(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssCuarentena=(java.math.BigDecimal)hd.get("ID_INSS_CUARENTENA");
	 this.idInssFichero=(java.math.BigDecimal)hd.get("ID_INSS_FICHERO");
	 this.numeroLinea=(java.math.BigDecimal)hd.get("NUMERO_LINEA");
	 this.ipf=(java.lang.String)hd.get("IPF");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.criterioIdentificacionSns=(java.lang.String)hd.get("CRITERIO_IDENTIFICACION_SNS");
	 this.causa=(java.lang.String)hd.get("CAUSA");
  }

  public InssCuarentenaCruceSns(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssCuarentena=rs.getBigDecimal("ID_INSS_CUARENTENA");
	 this.idInssFichero=rs.getBigDecimal("ID_INSS_FICHERO");
	 this.numeroLinea=rs.getBigDecimal("NUMERO_LINEA");
	 this.ipf=rs.getString("IPF");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.criterioIdentificacionSns=rs.getString("CRITERIO_IDENTIFICACION_SNS");
	 this.causa=rs.getString("CAUSA");
  }

  public java.math.BigDecimal getIdInssCuarentena() {
	 return this.idInssCuarentena;
  }
  public void setIdInssCuarentena(java.math.BigDecimal idInssCuarentena) {
	 this.idInssCuarentena=idInssCuarentena;
	 this.vacio=false;
  }
  public java.math.BigDecimal getIdInssFichero() {
	 return this.idInssFichero;
  }
  public void setIdInssFichero(java.math.BigDecimal idInssFichero) {
	 this.idInssFichero=idInssFichero;
	 this.vacio=false;
  }
  public java.math.BigDecimal getNumeroLinea() {
	 return this.numeroLinea;
  }
  public void setNumeroLinea(java.math.BigDecimal numeroLinea) {
	 this.numeroLinea=numeroLinea;
	 this.vacio=false;
  }
  public java.lang.String getIpf() {
	 return this.ipf;
  }
  public void setIpf(java.lang.String ipf) {
	 this.ipf=ipf;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.lang.String getCriterioIdentificacionSns() {
	 return this.criterioIdentificacionSns;
  }
  public void setCriterioIdentificacionSns(java.lang.String criterioIdentificacionSns) {
	 this.criterioIdentificacionSns=criterioIdentificacionSns;
	 this.vacio=false;
  }
  public java.lang.String getCausa() {
	 return this.causa;
  }
  public void setCausa(java.lang.String causa) {
	 this.causa=causa;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssCuarentenaCruceSns [");
	 buffer.append("idInssCuarentena = ").append(idInssCuarentena);
	 buffer.append("idInssFichero = ").append(idInssFichero);
	 buffer.append("numeroLinea = ").append(numeroLinea);
	 buffer.append("ipf = ").append(ipf);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("criterioIdentificacionSns = ").append(criterioIdentificacionSns);
	 buffer.append("causa = ").append(causa);
	 buffer.append("]");
	 return buffer.toString();
  }
}

