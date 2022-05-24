package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:13 CET 2012
/ ******************************************************/
public class InssFicherosBean { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssFichero;
  private java.lang.String nombreFichero;
  private java.sql.Timestamp fechaRecepcion;
  private java.math.BigDecimal codOperacion;
  private java.sql.Timestamp fechaFinLectura;
  private java.math.BigDecimal numeroLineas;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_ID_INSS_FICHERO";


  public static final String NOMBRE_TABLA="INSS_FICHEROS";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_FICHERO="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_FICHERO = ?";


  public static final String INDICE_DELETE_ID_INSS_FICHERO="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_FICHERO = ?";


  public static final String INDICE_UPDATE_NOMBRE_FICHERO="1";
  public static final String INDICE_UPDATE_FECHA_RECEPCION="2";
  public static final String INDICE_UPDATE_COD_OPERACION="3";
  public static final String INDICE_UPDATE_FECHA_FIN_LECTURA="4";
  public static final String INDICE_UPDATE_NUMERO_LINEAS="5";
  public static final String INDICE_UPDATE_ID_INSS_FICHERO="6";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set NOMBRE_FICHERO = ?, FECHA_RECEPCION = ?, COD_OPERACION = ?, FECHA_FIN_LECTURA = ?, NUMERO_LINEAS = ? where ID_INSS_FICHERO = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_FICHERO,idInssFichero);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_FICHERO,idInssFichero);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_NOMBRE_FICHERO,nombreFichero);
	  hParam.put(INDICE_UPDATE_FECHA_RECEPCION,fechaRecepcion);
	  hParam.put(INDICE_UPDATE_COD_OPERACION,codOperacion);
	  hParam.put(INDICE_UPDATE_FECHA_FIN_LECTURA,fechaFinLectura);
	  hParam.put(INDICE_UPDATE_NUMERO_LINEAS,numeroLineas);
	  hParam.put(INDICE_UPDATE_ID_INSS_FICHERO,idInssFichero);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_FICHERO="1";
  public static final String INDICE_INSERT_NOMBRE_FICHERO="2";
  public static final String INDICE_INSERT_FECHA_RECEPCION="3";
  public static final String INDICE_INSERT_COD_OPERACION="4";
  public static final String INDICE_INSERT_FECHA_FIN_LECTURA="5";
  public static final String INDICE_INSERT_NUMERO_LINEAS="6";
  public static final String QUERY_INSERT="INSERT INTO INSS_FICHEROS (ID_INSS_FICHERO,NOMBRE_FICHERO,FECHA_RECEPCION,COD_OPERACION,FECHA_FIN_LECTURA,NUMERO_LINEAS) VALUES (?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_INSERT_NOMBRE_FICHERO,nombreFichero);
	  hParam.put(INDICE_INSERT_FECHA_RECEPCION,fechaRecepcion);
	  hParam.put(INDICE_INSERT_COD_OPERACION,codOperacion);
	  hParam.put(INDICE_INSERT_FECHA_FIN_LECTURA,fechaFinLectura);
	  hParam.put(INDICE_INSERT_NUMERO_LINEAS,numeroLineas);
	  return hParam;
  }

  public InssFicherosBean() {this.vacio=true;}
  public InssFicherosBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssFichero=(java.math.BigDecimal)hd.get("ID_INSS_FICHERO");
	 this.nombreFichero=(java.lang.String)hd.get("NOMBRE_FICHERO");
	 this.fechaRecepcion=(java.sql.Timestamp)hd.get("FECHA_RECEPCION");
	 this.codOperacion=(java.math.BigDecimal)hd.get("COD_OPERACION");
	 this.fechaFinLectura=(java.sql.Timestamp)hd.get("FECHA_FIN_LECTURA");
	 this.numeroLineas=(java.math.BigDecimal)hd.get("NUMERO_LINEAS");
  }

  public InssFicherosBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssFichero=rs.getBigDecimal("ID_INSS_FICHERO");
	 this.nombreFichero=rs.getString("NOMBRE_FICHERO");
	 this.fechaRecepcion=rs.getTimestamp("FECHA_RECEPCION");
	 this.codOperacion=rs.getBigDecimal("COD_OPERACION");
	 this.fechaFinLectura=rs.getTimestamp("FECHA_FIN_LECTURA");
	 this.numeroLineas=rs.getBigDecimal("NUMERO_LINEAS");
  }

  public java.math.BigDecimal getIdInssFichero() {
	 return this.idInssFichero;
  }
  public void setIdInssFichero(java.math.BigDecimal idInssFichero) {
	 this.idInssFichero=idInssFichero;
	 this.vacio=false;
  }
  public java.lang.String getNombreFichero() {
	 return this.nombreFichero;
  }
  public void setNombreFichero(java.lang.String nombreFichero) {
	 this.nombreFichero=nombreFichero;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaRecepcion() {
	 return this.fechaRecepcion;
  }
  public void setFechaRecepcion(java.sql.Timestamp fechaRecepcion) {
	 this.fechaRecepcion=fechaRecepcion;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodOperacion() {
	 return this.codOperacion;
  }
  public void setCodOperacion(java.math.BigDecimal codOperacion) {
	 this.codOperacion=codOperacion;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaFinLectura() {
	 return this.fechaFinLectura;
  }
  public void setFechaFinLectura(java.sql.Timestamp fechaFinLectura) {
	 this.fechaFinLectura=fechaFinLectura;
	 this.vacio=false;
  }
  public java.math.BigDecimal getNumeroLineas() {
	 return this.numeroLineas;
  }
  public void setNumeroLineas(java.math.BigDecimal numeroLineas) {
	 this.numeroLineas=numeroLineas;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssFicherosBean [");
	 buffer.append("idInssFichero = ").append(idInssFichero);
	 buffer.append("nombreFichero = ").append(nombreFichero);
	 buffer.append("fechaRecepcion = ").append(fechaRecepcion);
	 buffer.append("codOperacion = ").append(codOperacion);
	 buffer.append("fechaFinLectura = ").append(fechaFinLectura);
	 buffer.append("numeroLineas = ").append(numeroLineas);
	 buffer.append("]");
	 return buffer.toString();
  }
}

