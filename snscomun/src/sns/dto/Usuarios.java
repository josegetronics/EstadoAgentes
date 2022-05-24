package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:27 CET 2012
/ ******************************************************/
public class Usuarios { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.math.BigDecimal codEstado;
  private java.math.BigDecimal codPrestacionServicio;
  private java.math.BigDecimal codOrigenAlta;
  private java.sql.Timestamp fechaAltaReg;
  private java.sql.Timestamp fechaUltActualizacion;

  public static final String NOMBRE_TABLA="USUARIOS";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_UPDATE_COD_ESTADO="1";
  public static final String INDICE_UPDATE_COD_PRESTACION_SERVICIO="2";
  public static final String INDICE_UPDATE_COD_ORIGEN_ALTA="3";
  public static final String INDICE_UPDATE_FECHA_ALTA_REG="4";
  public static final String INDICE_UPDATE_FECHA_ULT_ACTUALIZACION="5";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="6";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_ESTADO = ?, COD_PRESTACION_SERVICIO = ?, COD_ORIGEN_ALTA = ?, FECHA_ALTA_REG = ?, FECHA_ULT_ACTUALIZACION = ? where COD_USUARIO_SNS = ?";


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
	  hParam.put(INDICE_UPDATE_COD_ESTADO,codEstado);
	  hParam.put(INDICE_UPDATE_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_UPDATE_COD_ORIGEN_ALTA,codOrigenAlta);
	  hParam.put(INDICE_UPDATE_FECHA_ALTA_REG,fechaAltaReg);
	  hParam.put(INDICE_UPDATE_FECHA_ULT_ACTUALIZACION,fechaUltActualizacion);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_ESTADO="2";
  public static final String INDICE_INSERT_COD_PRESTACION_SERVICIO="3";
  public static final String INDICE_INSERT_COD_ORIGEN_ALTA="4";
  public static final String INDICE_INSERT_FECHA_ALTA_REG="5";
  public static final String INDICE_INSERT_FECHA_ULT_ACTUALIZACION="6";
  public static final String QUERY_INSERT="INSERT INTO USUARIOS (COD_USUARIO_SNS,COD_ESTADO,COD_PRESTACION_SERVICIO,COD_ORIGEN_ALTA,FECHA_ALTA_REG,FECHA_ULT_ACTUALIZACION) VALUES (?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_ESTADO,codEstado);
	  hParam.put(INDICE_INSERT_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_INSERT_COD_ORIGEN_ALTA,codOrigenAlta);
	  hParam.put(INDICE_INSERT_FECHA_ALTA_REG,fechaAltaReg);
	  hParam.put(INDICE_INSERT_FECHA_ULT_ACTUALIZACION,fechaUltActualizacion);
	  return hParam;
  }

  public Usuarios() {this.vacio=true;}
  public Usuarios(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codEstado=(java.math.BigDecimal)hd.get("COD_ESTADO");
	 this.codPrestacionServicio=(java.math.BigDecimal)hd.get("COD_PRESTACION_SERVICIO");
	 this.codOrigenAlta=(java.math.BigDecimal)hd.get("COD_ORIGEN_ALTA");
	 this.fechaAltaReg=(java.sql.Timestamp)hd.get("FECHA_ALTA_REG");
	 this.fechaUltActualizacion=(java.sql.Timestamp)hd.get("FECHA_ULT_ACTUALIZACION");
  }

  public Usuarios(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codEstado=rs.getBigDecimal("COD_ESTADO");
	 this.codPrestacionServicio=rs.getBigDecimal("COD_PRESTACION_SERVICIO");
	 this.codOrigenAlta=rs.getBigDecimal("COD_ORIGEN_ALTA");
	 this.fechaAltaReg=rs.getTimestamp("FECHA_ALTA_REG");
	 this.fechaUltActualizacion=rs.getTimestamp("FECHA_ULT_ACTUALIZACION");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodEstado() {
	 return this.codEstado;
  }
  public void setCodEstado(java.math.BigDecimal codEstado) {
	 this.codEstado=codEstado;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodPrestacionServicio() {
	 return this.codPrestacionServicio;
  }
  public void setCodPrestacionServicio(java.math.BigDecimal codPrestacionServicio) {
	 this.codPrestacionServicio=codPrestacionServicio;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodOrigenAlta() {
	 return this.codOrigenAlta;
  }
  public void setCodOrigenAlta(java.math.BigDecimal codOrigenAlta) {
	 this.codOrigenAlta=codOrigenAlta;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaAltaReg() {
	 return this.fechaAltaReg;
  }
  public void setFechaAltaReg(java.sql.Timestamp fechaAltaReg) {
	 this.fechaAltaReg=fechaAltaReg;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaUltActualizacion() {
	 return this.fechaUltActualizacion;
  }
  public void setFechaUltActualizacion(java.sql.Timestamp fechaUltActualizacion) {
	 this.fechaUltActualizacion=fechaUltActualizacion;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("Usuarios [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codEstado = ").append(codEstado);
	 buffer.append("codPrestacionServicio = ").append(codPrestacionServicio);
	 buffer.append("codOrigenAlta = ").append(codOrigenAlta);
	 buffer.append("fechaAltaReg = ").append(fechaAltaReg);
	 buffer.append("fechaUltActualizacion = ").append(fechaUltActualizacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

