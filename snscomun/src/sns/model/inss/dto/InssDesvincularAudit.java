package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Jan 15 15:21:57 CET 2013
/ ******************************************************/
public class InssDesvincularAudit { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssDesvincularAudit;
  private java.math.BigDecimal codUsuario;
  private java.math.BigDecimal idInss;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaDesvinculacion;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_DESVINCULAR_AUDIT";


  public static final String NOMBRE_TABLA="INSS_DESVINCULAR_AUDIT";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_DESVINCULAR_AUDIT="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_DESVINCULAR_AUDIT = ?";


  public static final String INDICE_DELETE_ID_INSS_DESVINCULAR_AUDIT="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_DESVINCULAR_AUDIT = ?";


  public static final String INDICE_UPDATE_COD_USUARIO="1";
  public static final String INDICE_UPDATE_ID_INSS="2";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="3";
  public static final String INDICE_UPDATE_FECHA_DESVINCULACION="4";
  public static final String INDICE_UPDATE_ID_INSS_DESVINCULAR_AUDIT="5";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO = ?, ID_INSS = ?, COD_USUARIO_SNS = ?, FECHA_DESVINCULACION = ? where ID_INSS_DESVINCULAR_AUDIT = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_DESVINCULAR_AUDIT,idInssDesvincularAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_DESVINCULAR_AUDIT,idInssDesvincularAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_FECHA_DESVINCULACION,fechaDesvinculacion);
	  hParam.put(INDICE_UPDATE_ID_INSS_DESVINCULAR_AUDIT,idInssDesvincularAudit);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_DESVINCULAR_AUDIT="1";
  public static final String INDICE_INSERT_COD_USUARIO="2";
  public static final String INDICE_INSERT_ID_INSS="3";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="4";
  public static final String INDICE_INSERT_FECHA_DESVINCULACION="5";
  public static final String QUERY_INSERT="INSERT INTO INSS_DESVINCULAR_AUDIT (ID_INSS_DESVINCULAR_AUDIT,COD_USUARIO,ID_INSS,COD_USUARIO_SNS,FECHA_DESVINCULACION) VALUES (?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_DESVINCULAR_AUDIT,idInssDesvincularAudit);
	  hParam.put(INDICE_INSERT_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_FECHA_DESVINCULACION,fechaDesvinculacion);
	  return hParam;
  }

  public InssDesvincularAudit() {this.vacio=true;}
  public InssDesvincularAudit(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssDesvincularAudit=(java.math.BigDecimal)hd.get("ID_INSS_DESVINCULAR_AUDIT");
	 this.codUsuario=(java.math.BigDecimal)hd.get("COD_USUARIO");
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaDesvinculacion=(java.sql.Timestamp)hd.get("FECHA_DESVINCULACION");
  }

  public InssDesvincularAudit(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssDesvincularAudit=rs.getBigDecimal("ID_INSS_DESVINCULAR_AUDIT");
	 this.codUsuario=rs.getBigDecimal("COD_USUARIO");
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaDesvinculacion=rs.getTimestamp("FECHA_DESVINCULACION");
  }

  public java.math.BigDecimal getIdInssDesvincularAudit() {
	 return this.idInssDesvincularAudit;
  }
  public void setIdInssDesvincularAudit(java.math.BigDecimal idInssDesvincularAudit) {
	 this.idInssDesvincularAudit=idInssDesvincularAudit;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodUsuario() {
	 return this.codUsuario;
  }
  public void setCodUsuario(java.math.BigDecimal codUsuario) {
	 this.codUsuario=codUsuario;
	 this.vacio=false;
  }
  public java.math.BigDecimal getIdInss() {
	 return this.idInss;
  }
  public void setIdInss(java.math.BigDecimal idInss) {
	 this.idInss=idInss;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaDesvinculacion() {
	 return this.fechaDesvinculacion;
  }
  public void setFechaDesvinculacion(java.sql.Timestamp fechaDesvinculacion) {
	 this.fechaDesvinculacion=fechaDesvinculacion;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssDesvincularAudit [");
	 buffer.append("idInssDesvincularAudit = ").append(idInssDesvincularAudit);
	 buffer.append("codUsuario = ").append(codUsuario);
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("fechaDesvinculacion = ").append(fechaDesvinculacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

