package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Jan 22 12:19:18 CET 2013
/ ******************************************************/
public class FallecidosAudit { 

  private boolean vacio=false;
  private java.math.BigDecimal idFallecidosAudit;
  private java.math.BigDecimal codUsuario;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaOperacion;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_FALLECIDOS_AUDIT";


  public static final String NOMBRE_TABLA="FALLECIDOS_AUDIT";


  public static final String INDICE_SELECT_BY_PK_ID_FALLECIDOS_AUDIT="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_FALLECIDOS_AUDIT = ?";


  public static final String INDICE_DELETE_ID_FALLECIDOS_AUDIT="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_FALLECIDOS_AUDIT = ?";


  public static final String INDICE_UPDATE_COD_USUARIO="1";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="2";
  public static final String INDICE_UPDATE_FECHA_OPERACION="3";
  public static final String INDICE_UPDATE_ID_FALLECIDOS_AUDIT="4";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO = ?, COD_USUARIO_SNS = ?, FECHA_OPERACION = ? where ID_FALLECIDOS_AUDIT = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_FALLECIDOS_AUDIT,idFallecidosAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_FALLECIDOS_AUDIT,idFallecidosAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_FECHA_OPERACION,fechaOperacion);
	  hParam.put(INDICE_UPDATE_ID_FALLECIDOS_AUDIT,idFallecidosAudit);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_FALLECIDOS_AUDIT="1";
  public static final String INDICE_INSERT_COD_USUARIO="2";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="3";
  public static final String INDICE_INSERT_FECHA_OPERACION="4";
  public static final String QUERY_INSERT="INSERT INTO FALLECIDOS_AUDIT (ID_FALLECIDOS_AUDIT,COD_USUARIO,COD_USUARIO_SNS,FECHA_OPERACION) VALUES (?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_FALLECIDOS_AUDIT,idFallecidosAudit);
	  hParam.put(INDICE_INSERT_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_FECHA_OPERACION,fechaOperacion);
	  return hParam;
  }

  public FallecidosAudit() {this.vacio=true;}
  public FallecidosAudit(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idFallecidosAudit=(java.math.BigDecimal)hd.get("ID_FALLECIDOS_AUDIT");
	 this.codUsuario=(java.math.BigDecimal)hd.get("COD_USUARIO");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaOperacion=(java.sql.Timestamp)hd.get("FECHA_OPERACION");
  }

  public FallecidosAudit(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idFallecidosAudit=rs.getBigDecimal("ID_FALLECIDOS_AUDIT");
	 this.codUsuario=rs.getBigDecimal("COD_USUARIO");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaOperacion=rs.getTimestamp("FECHA_OPERACION");
  }

  public java.math.BigDecimal getIdFallecidosAudit() {
	 return this.idFallecidosAudit;
  }
  public void setIdFallecidosAudit(java.math.BigDecimal idFallecidosAudit) {
	 this.idFallecidosAudit=idFallecidosAudit;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodUsuario() {
	 return this.codUsuario;
  }
  public void setCodUsuario(java.math.BigDecimal codUsuario) {
	 this.codUsuario=codUsuario;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaOperacion() {
	 return this.fechaOperacion;
  }
  public void setFechaOperacion(java.sql.Timestamp fechaOperacion) {
	 this.fechaOperacion=fechaOperacion;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("FallecidosAudit [");
	 buffer.append("idFallecidosAudit = ").append(idFallecidosAudit);
	 buffer.append("codUsuario = ").append(codUsuario);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("fechaOperacion = ").append(fechaOperacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

