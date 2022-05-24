package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Wed Feb 20 12:21:01 CET 2013
/ ******************************************************/
public class Temporalidad { 

  private boolean vacio=false;
  private java.math.BigDecimal codTemporalidad;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaInicio;
  private java.sql.Timestamp fechaFinEstimada;
  private java.math.BigDecimal duracionEstimada;
  private java.math.BigDecimal codPrestacionServicio;
  private java.math.BigDecimal codPrestacionServicioOrigen;
  private java.math.BigDecimal codEstadoTemporalidad;
  private java.sql.Timestamp fechaFin;
  private java.math.BigDecimal duracion;
  private java.sql.Timestamp fechaRealInicio;
  private java.sql.Timestamp fechaRealFin;
  private java.math.BigDecimal duracionReal;
  private java.math.BigDecimal codTipoFinTemporalidad;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_TEMPORALIDAD";


  public static final String NOMBRE_TABLA="TEMPORALIDAD";


  public static final String INDICE_SELECT_BY_PK_COD_TEMPORALIDAD="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_TEMPORALIDAD = ?";


  public static final String INDICE_DELETE_COD_TEMPORALIDAD="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_TEMPORALIDAD = ?";


  public static final String INDICE_UPDATE_COD_USUARIO_SNS="1";
  public static final String INDICE_UPDATE_FECHA_INICIO="2";
  public static final String INDICE_UPDATE_FECHA_FIN_ESTIMADA="3";
  public static final String INDICE_UPDATE_DURACION_ESTIMADA="4";
  public static final String INDICE_UPDATE_COD_PRESTACION_SERVICIO="5";
  public static final String INDICE_UPDATE_COD_PRESTACION_SERVICIO_ORIGEN="6";
  public static final String INDICE_UPDATE_COD_ESTADO_TEMPORALIDAD="7";
  public static final String INDICE_UPDATE_FECHA_FIN="8";
  public static final String INDICE_UPDATE_DURACION="9";
  public static final String INDICE_UPDATE_FECHA_REAL_INICIO="10";
  public static final String INDICE_UPDATE_FECHA_REAL_FIN="11";
  public static final String INDICE_UPDATE_DURACION_REAL="12";
  public static final String INDICE_UPDATE_COD_TIPO_FIN_TEMPORALIDAD="13";
  public static final String INDICE_UPDATE_COD_TEMPORALIDAD="14";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO_SNS = ?, FECHA_INICIO = ?, FECHA_FIN_ESTIMADA = ?, DURACION_ESTIMADA = ?, COD_PRESTACION_SERVICIO = ?, COD_PRESTACION_SERVICIO_ORIGEN = ?, COD_ESTADO_TEMPORALIDAD = ?, FECHA_FIN = ?, DURACION = ?, FECHA_REAL_INICIO = ?, FECHA_REAL_FIN = ?, DURACION_REAL = ?, COD_TIPO_FIN_TEMPORALIDAD = ? where COD_TEMPORALIDAD = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_COD_TEMPORALIDAD,codTemporalidad);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_COD_TEMPORALIDAD,codTemporalidad);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_FECHA_INICIO,fechaInicio);
	  hParam.put(INDICE_UPDATE_FECHA_FIN_ESTIMADA,fechaFinEstimada);
	  hParam.put(INDICE_UPDATE_DURACION_ESTIMADA,duracionEstimada);
	  hParam.put(INDICE_UPDATE_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_UPDATE_COD_PRESTACION_SERVICIO_ORIGEN,codPrestacionServicioOrigen);
	  hParam.put(INDICE_UPDATE_COD_ESTADO_TEMPORALIDAD,codEstadoTemporalidad);
	  hParam.put(INDICE_UPDATE_FECHA_FIN,fechaFin);
	  hParam.put(INDICE_UPDATE_DURACION,duracion);
	  hParam.put(INDICE_UPDATE_FECHA_REAL_INICIO,fechaRealInicio);
	  hParam.put(INDICE_UPDATE_FECHA_REAL_FIN,fechaRealFin);
	  hParam.put(INDICE_UPDATE_DURACION_REAL,duracionReal);
	  hParam.put(INDICE_UPDATE_COD_TIPO_FIN_TEMPORALIDAD,codTipoFinTemporalidad);
	  hParam.put(INDICE_UPDATE_COD_TEMPORALIDAD,codTemporalidad);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_TEMPORALIDAD="1";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="2";
  public static final String INDICE_INSERT_FECHA_INICIO="3";
  public static final String INDICE_INSERT_FECHA_FIN_ESTIMADA="4";
  public static final String INDICE_INSERT_DURACION_ESTIMADA="5";
  public static final String INDICE_INSERT_COD_PRESTACION_SERVICIO="6";
  public static final String INDICE_INSERT_COD_PRESTACION_SERVICIO_ORIGEN="7";
  public static final String INDICE_INSERT_COD_ESTADO_TEMPORALIDAD="8";
  public static final String INDICE_INSERT_FECHA_FIN="9";
  public static final String INDICE_INSERT_DURACION="10";
  public static final String INDICE_INSERT_FECHA_REAL_INICIO="11";
  public static final String INDICE_INSERT_FECHA_REAL_FIN="12";
  public static final String INDICE_INSERT_DURACION_REAL="13";
  public static final String INDICE_INSERT_COD_TIPO_FIN_TEMPORALIDAD="14";
  public static final String QUERY_INSERT="INSERT INTO TEMPORALIDAD (COD_TEMPORALIDAD,COD_USUARIO_SNS,FECHA_INICIO,FECHA_FIN_ESTIMADA,DURACION_ESTIMADA,COD_PRESTACION_SERVICIO,COD_PRESTACION_SERVICIO_ORIGEN,COD_ESTADO_TEMPORALIDAD,FECHA_FIN,DURACION,FECHA_REAL_INICIO,FECHA_REAL_FIN,DURACION_REAL,COD_TIPO_FIN_TEMPORALIDAD) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_TEMPORALIDAD,codTemporalidad);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_FECHA_INICIO,fechaInicio);
	  hParam.put(INDICE_INSERT_FECHA_FIN_ESTIMADA,fechaFinEstimada);
	  hParam.put(INDICE_INSERT_DURACION_ESTIMADA,duracionEstimada);
	  hParam.put(INDICE_INSERT_COD_PRESTACION_SERVICIO,codPrestacionServicio);
	  hParam.put(INDICE_INSERT_COD_PRESTACION_SERVICIO_ORIGEN,codPrestacionServicioOrigen);
	  hParam.put(INDICE_INSERT_COD_ESTADO_TEMPORALIDAD,codEstadoTemporalidad);
	  hParam.put(INDICE_INSERT_FECHA_FIN,fechaFin);
	  hParam.put(INDICE_INSERT_DURACION,duracion);
	  hParam.put(INDICE_INSERT_FECHA_REAL_INICIO,fechaRealInicio);
	  hParam.put(INDICE_INSERT_FECHA_REAL_FIN,fechaRealFin);
	  hParam.put(INDICE_INSERT_DURACION_REAL,duracionReal);
	  hParam.put(INDICE_INSERT_COD_TIPO_FIN_TEMPORALIDAD,codTipoFinTemporalidad);
	  return hParam;
  }

  public Temporalidad() {this.vacio=true;}
  public Temporalidad(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codTemporalidad=(java.math.BigDecimal)hd.get("COD_TEMPORALIDAD");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaInicio=(java.sql.Timestamp)hd.get("FECHA_INICIO");
	 this.fechaFinEstimada=(java.sql.Timestamp)hd.get("FECHA_FIN_ESTIMADA");
	 this.duracionEstimada=(java.math.BigDecimal)hd.get("DURACION_ESTIMADA");
	 this.codPrestacionServicio=(java.math.BigDecimal)hd.get("COD_PRESTACION_SERVICIO");
	 this.codPrestacionServicioOrigen=(java.math.BigDecimal)hd.get("COD_PRESTACION_SERVICIO_ORIGEN");
	 this.codEstadoTemporalidad=(java.math.BigDecimal)hd.get("COD_ESTADO_TEMPORALIDAD");
	 this.fechaFin=(java.sql.Timestamp)hd.get("FECHA_FIN");
	 this.duracion=(java.math.BigDecimal)hd.get("DURACION");
	 this.fechaRealInicio=(java.sql.Timestamp)hd.get("FECHA_REAL_INICIO");
	 this.fechaRealFin=(java.sql.Timestamp)hd.get("FECHA_REAL_FIN");
	 this.duracionReal=(java.math.BigDecimal)hd.get("DURACION_REAL");
	 this.codTipoFinTemporalidad=(java.math.BigDecimal)hd.get("COD_TIPO_FIN_TEMPORALIDAD");
  }

  public Temporalidad(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codTemporalidad=rs.getBigDecimal("COD_TEMPORALIDAD");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaInicio=rs.getTimestamp("FECHA_INICIO");
	 this.fechaFinEstimada=rs.getTimestamp("FECHA_FIN_ESTIMADA");
	 this.duracionEstimada=rs.getBigDecimal("DURACION_ESTIMADA");
	 this.codPrestacionServicio=rs.getBigDecimal("COD_PRESTACION_SERVICIO");
	 this.codPrestacionServicioOrigen=rs.getBigDecimal("COD_PRESTACION_SERVICIO_ORIGEN");
	 this.codEstadoTemporalidad=rs.getBigDecimal("COD_ESTADO_TEMPORALIDAD");
	 this.fechaFin=rs.getTimestamp("FECHA_FIN");
	 this.duracion=rs.getBigDecimal("DURACION");
	 this.fechaRealInicio=rs.getTimestamp("FECHA_REAL_INICIO");
	 this.fechaRealFin=rs.getTimestamp("FECHA_REAL_FIN");
	 this.duracionReal=rs.getBigDecimal("DURACION_REAL");
	 this.codTipoFinTemporalidad=rs.getBigDecimal("COD_TIPO_FIN_TEMPORALIDAD");
  }

  public java.math.BigDecimal getCodTemporalidad() {
	 return this.codTemporalidad;
  }
  public void setCodTemporalidad(java.math.BigDecimal codTemporalidad) {
	 this.codTemporalidad=codTemporalidad;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaInicio() {
	 return this.fechaInicio;
  }
  public void setFechaInicio(java.sql.Timestamp fechaInicio) {
	 this.fechaInicio=fechaInicio;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaFinEstimada() {
	 return this.fechaFinEstimada;
  }
  public void setFechaFinEstimada(java.sql.Timestamp fechaFinEstimada) {
	 this.fechaFinEstimada=fechaFinEstimada;
	 this.vacio=false;
  }
  public java.math.BigDecimal getDuracionEstimada() {
	 return this.duracionEstimada;
  }
  public void setDuracionEstimada(java.math.BigDecimal duracionEstimada) {
	 this.duracionEstimada=duracionEstimada;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodPrestacionServicio() {
	 return this.codPrestacionServicio;
  }
  public void setCodPrestacionServicio(java.math.BigDecimal codPrestacionServicio) {
	 this.codPrestacionServicio=codPrestacionServicio;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodPrestacionServicioOrigen() {
	 return this.codPrestacionServicioOrigen;
  }
  public void setCodPrestacionServicioOrigen(java.math.BigDecimal codPrestacionServicioOrigen) {
	 this.codPrestacionServicioOrigen=codPrestacionServicioOrigen;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodEstadoTemporalidad() {
	 return this.codEstadoTemporalidad;
  }
  public void setCodEstadoTemporalidad(java.math.BigDecimal codEstadoTemporalidad) {
	 this.codEstadoTemporalidad=codEstadoTemporalidad;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaFin() {
	 return this.fechaFin;
  }
  public void setFechaFin(java.sql.Timestamp fechaFin) {
	 this.fechaFin=fechaFin;
	 this.vacio=false;
  }
  public java.math.BigDecimal getDuracion() {
	 return this.duracion;
  }
  public void setDuracion(java.math.BigDecimal duracion) {
	 this.duracion=duracion;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaRealInicio() {
	 return this.fechaRealInicio;
  }
  public void setFechaRealInicio(java.sql.Timestamp fechaRealInicio) {
	 this.fechaRealInicio=fechaRealInicio;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaRealFin() {
	 return this.fechaRealFin;
  }
  public void setFechaRealFin(java.sql.Timestamp fechaRealFin) {
	 this.fechaRealFin=fechaRealFin;
	 this.vacio=false;
  }
  public java.math.BigDecimal getDuracionReal() {
	 return this.duracionReal;
  }
  public void setDuracionReal(java.math.BigDecimal duracionReal) {
	 this.duracionReal=duracionReal;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTipoFinTemporalidad() {
	 return this.codTipoFinTemporalidad;
  }
  public void setCodTipoFinTemporalidad(java.math.BigDecimal codTipoFinTemporalidad) {
	 this.codTipoFinTemporalidad=codTipoFinTemporalidad;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("Temporalidad [");
	 buffer.append("codTemporalidad = ").append(codTemporalidad);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("fechaInicio = ").append(fechaInicio);
	 buffer.append("fechaFinEstimada = ").append(fechaFinEstimada);
	 buffer.append("duracionEstimada = ").append(duracionEstimada);
	 buffer.append("codPrestacionServicio = ").append(codPrestacionServicio);
	 buffer.append("codPrestacionServicioOrigen = ").append(codPrestacionServicioOrigen);
	 buffer.append("codEstadoTemporalidad = ").append(codEstadoTemporalidad);
	 buffer.append("fechaFin = ").append(fechaFin);
	 buffer.append("duracion = ").append(duracion);
	 buffer.append("fechaRealInicio = ").append(fechaRealInicio);
	 buffer.append("fechaRealFin = ").append(fechaRealFin);
	 buffer.append("duracionReal = ").append(duracionReal);
	 buffer.append("codTipoFinTemporalidad = ").append(codTipoFinTemporalidad);
	 buffer.append("]");
	 return buffer.toString();
  }
}

