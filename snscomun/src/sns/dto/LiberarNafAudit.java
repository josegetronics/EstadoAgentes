package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Thu Feb 07 12:11:27 CET 2013
/ ******************************************************/
public class LiberarNafAudit { 

  private boolean vacio=false;
  private java.math.BigDecimal idLiberarNafAudit;
  private java.math.BigDecimal codUsuario;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaOperacion;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_LIBERAR_NAF_AUDIT";


  public static final String NOMBRE_TABLA="LIBERAR_NAF_AUDIT";


  public static final String INDICE_SELECT_BY_PK_ID_LIBERAR_NAF_AUDIT="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_LIBERAR_NAF_AUDIT = ?";


  public static final String INDICE_DELETE_ID_LIBERAR_NAF_AUDIT="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_LIBERAR_NAF_AUDIT = ?";


  public static final String INDICE_UPDATE_COD_USUARIO="1";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="2";
  public static final String INDICE_UPDATE_FECHA_OPERACION="3";
  public static final String INDICE_UPDATE_ID_LIBERAR_NAF_AUDIT="4";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO = ?, COD_USUARIO_SNS = ?, FECHA_OPERACION = ? where ID_LIBERAR_NAF_AUDIT = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_LIBERAR_NAF_AUDIT,idLiberarNafAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_LIBERAR_NAF_AUDIT,idLiberarNafAudit);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_FECHA_OPERACION,fechaOperacion);
	  hParam.put(INDICE_UPDATE_ID_LIBERAR_NAF_AUDIT,idLiberarNafAudit);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_LIBERAR_NAF_AUDIT="1";
  public static final String INDICE_INSERT_COD_USUARIO="2";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="3";
  public static final String INDICE_INSERT_FECHA_OPERACION="4";
  public static final String QUERY_INSERT="INSERT INTO LIBERAR_NAF_AUDIT (ID_LIBERAR_NAF_AUDIT,COD_USUARIO,COD_USUARIO_SNS,FECHA_OPERACION) VALUES (?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_LIBERAR_NAF_AUDIT,idLiberarNafAudit);
	  hParam.put(INDICE_INSERT_COD_USUARIO,codUsuario);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_FECHA_OPERACION,fechaOperacion);
	  return hParam;
  }

  public LiberarNafAudit() {this.vacio=true;}
  public LiberarNafAudit(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idLiberarNafAudit=(java.math.BigDecimal)hd.get("ID_LIBERAR_NAF_AUDIT");
	 this.codUsuario=(java.math.BigDecimal)hd.get("COD_USUARIO");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaOperacion=(java.sql.Timestamp)hd.get("FECHA_OPERACION");
  }

  public LiberarNafAudit(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idLiberarNafAudit=rs.getBigDecimal("ID_LIBERAR_NAF_AUDIT");
	 this.codUsuario=rs.getBigDecimal("COD_USUARIO");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaOperacion=rs.getTimestamp("FECHA_OPERACION");
  }

  public java.math.BigDecimal getIdLiberarNafAudit() {
	 return this.idLiberarNafAudit;
  }
  public void setIdLiberarNafAudit(java.math.BigDecimal idLiberarNafAudit) {
	 this.idLiberarNafAudit=idLiberarNafAudit;
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
	 buffer.append("LiberarNafAudit [");
	 buffer.append("idLiberarNafAudit = ").append(idLiberarNafAudit);
	 buffer.append("codUsuario = ").append(codUsuario);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("fechaOperacion = ").append(fechaOperacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

