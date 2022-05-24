package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Fri Feb 15 11:14:37 CET 2013
/ ******************************************************/
public class InssVincularAudit { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssVincularAudit;
  private java.math.BigDecimal codUsuario;
  private java.math.BigDecimal idInss;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaVinculacion;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_VINCULAR_AUDIT";


  public static final String NOMBRE_TABLA="INSS_VINCULAR_AUDIT";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_VINCULAR_AUDIT="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_VINCULAR_AUDIT = ?";


  public static final String INDICE_DELETE_ID_INSS_VINCULAR_AUDIT="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_VINCULAR_AUDIT = ?";


  public static final String INDICE_UPDATE_COD_USUARIO="1";
  public static final String INDICE_UPDATE_ID_INSS="2";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="3";
  public static final String INDICE_UPDATE_FECHA_VINCULACION="4";
  public static final String INDICE_UPDATE_ID_INSS_VINCULAR_AUDIT="5";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO = ?, ID_INSS = ?, COD_USUARIO_SNS = ?, FECHA_VINCULACION = ? where ID_INSS_VINCULAR_AUDIT = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_VINCULAR_AUDIT,idInssVincularAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_VINCULAR_AUDIT,idInssVincularAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_FECHA_VINCULACION,fechaVinculacion);
	  hParam.put(INDICE_UPDATE_ID_INSS_VINCULAR_AUDIT,idInssVincularAudit);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_VINCULAR_AUDIT="1";
  public static final String INDICE_INSERT_COD_USUARIO="2";
  public static final String INDICE_INSERT_ID_INSS="3";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="4";
  public static final String INDICE_INSERT_FECHA_VINCULACION="5";
  public static final String QUERY_INSERT="INSERT INTO INSS_VINCULAR_AUDIT (ID_INSS_VINCULAR_AUDIT,COD_USUARIO,ID_INSS,COD_USUARIO_SNS,FECHA_VINCULACION) VALUES (?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_VINCULAR_AUDIT,idInssVincularAudit);
	  hParam.put(INDICE_INSERT_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_FECHA_VINCULACION,fechaVinculacion);
	  return hParam;
  }

  public InssVincularAudit() {this.vacio=true;}
  public InssVincularAudit(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssVincularAudit=(java.math.BigDecimal)hd.get("ID_INSS_VINCULAR_AUDIT");
	 this.codUsuario=(java.math.BigDecimal)hd.get("COD_USUARIO");
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaVinculacion=(java.sql.Timestamp)hd.get("FECHA_VINCULACION");
  }

  public InssVincularAudit(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssVincularAudit=rs.getBigDecimal("ID_INSS_VINCULAR_AUDIT");
	 this.codUsuario=rs.getBigDecimal("COD_USUARIO");
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaVinculacion=rs.getTimestamp("FECHA_VINCULACION");
  }

  public java.math.BigDecimal getIdInssVincularAudit() {
	 return this.idInssVincularAudit;
  }
  public void setIdInssVincularAudit(java.math.BigDecimal idInssVincularAudit) {
	 this.idInssVincularAudit=idInssVincularAudit;
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
  public java.sql.Timestamp getFechaVinculacion() {
	 return this.fechaVinculacion;
  }
  public void setFechaVinculacion(java.sql.Timestamp fechaVinculacion) {
	 this.fechaVinculacion=fechaVinculacion;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssVincularAudit [");
	 buffer.append("idInssVincularAudit = ").append(idInssVincularAudit);
	 buffer.append("codUsuario = ").append(codUsuario);
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("fechaVinculacion = ").append(fechaVinculacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

