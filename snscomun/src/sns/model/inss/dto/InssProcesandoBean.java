package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:17 CET 2012
/ ******************************************************/
public class InssProcesandoBean { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssFichero;
  private java.math.BigDecimal numeroLinea;
  private java.lang.String codTipoMovimiento;
  private java.lang.String linea;
  private java.sql.Timestamp fechaEfectoSituacion;

  public static final String NOMBRE_TABLA="INSS_PROCESANDO";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_FICHERO="1";
  public static final String INDICE_SELECT_BY_PK_NUMERO_LINEA="2";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public static final String INDICE_DELETE_ID_INSS_FICHERO="1";
  public static final String INDICE_DELETE_NUMERO_LINEA="2";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public static final String INDICE_UPDATE_COD_TIPO_MOVIMIENTO="1";
  public static final String INDICE_UPDATE_LINEA="2";
  public static final String INDICE_UPDATE_FECHA_EFECTO_SITUACION="3";
  public static final String INDICE_UPDATE_ID_INSS_FICHERO="4";
  public static final String INDICE_UPDATE_NUMERO_LINEA="5";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_TIPO_MOVIMIENTO = ?, LINEA = ?, FECHA_EFECTO_SITUACION = ? where ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_SELECT_BY_PK_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_DELETE_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_TIPO_MOVIMIENTO,codTipoMovimiento);
	  hParam.put(INDICE_UPDATE_LINEA,linea);
	  hParam.put(INDICE_UPDATE_FECHA_EFECTO_SITUACION,fechaEfectoSituacion);
	  hParam.put(INDICE_UPDATE_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_UPDATE_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_FICHERO="1";
  public static final String INDICE_INSERT_NUMERO_LINEA="2";
  public static final String INDICE_INSERT_COD_TIPO_MOVIMIENTO="3";
  public static final String INDICE_INSERT_LINEA="4";
  public static final String INDICE_INSERT_FECHA_EFECTO_SITUACION="5";
  public static final String QUERY_INSERT="INSERT INTO INSS_PROCESANDO (ID_INSS_FICHERO,NUMERO_LINEA,COD_TIPO_MOVIMIENTO,LINEA,FECHA_EFECTO_SITUACION) VALUES (?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_INSERT_NUMERO_LINEA,numeroLinea);
	  hParam.put(INDICE_INSERT_COD_TIPO_MOVIMIENTO,codTipoMovimiento);
	  hParam.put(INDICE_INSERT_LINEA,linea);
	  hParam.put(INDICE_INSERT_FECHA_EFECTO_SITUACION,fechaEfectoSituacion);
	  return hParam;
  }

  public InssProcesandoBean() {this.vacio=true;}
  public InssProcesandoBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssFichero=(java.math.BigDecimal)hd.get("ID_INSS_FICHERO");
	 this.numeroLinea=(java.math.BigDecimal)hd.get("NUMERO_LINEA");
	 this.codTipoMovimiento=(java.lang.String)hd.get("COD_TIPO_MOVIMIENTO");
	 this.linea=(java.lang.String)hd.get("LINEA");
	 this.fechaEfectoSituacion=(java.sql.Timestamp)hd.get("FECHA_EFECTO_SITUACION");
  }

  public InssProcesandoBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssFichero=rs.getBigDecimal("ID_INSS_FICHERO");
	 this.numeroLinea=rs.getBigDecimal("NUMERO_LINEA");
	 this.codTipoMovimiento=rs.getString("COD_TIPO_MOVIMIENTO");
	 this.linea=rs.getString("LINEA");
	 this.fechaEfectoSituacion=rs.getTimestamp("FECHA_EFECTO_SITUACION");
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
  public java.lang.String getCodTipoMovimiento() {
	 return this.codTipoMovimiento;
  }
  public void setCodTipoMovimiento(java.lang.String codTipoMovimiento) {
	 this.codTipoMovimiento=codTipoMovimiento;
	 this.vacio=false;
  }
  public java.lang.String getLinea() {
	 return this.linea;
  }
  public void setLinea(java.lang.String linea) {
	 this.linea=linea;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaEfectoSituacion() {
	 return this.fechaEfectoSituacion;
  }
  public void setFechaEfectoSituacion(java.sql.Timestamp fechaEfectoSituacion) {
	 this.fechaEfectoSituacion=fechaEfectoSituacion;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssProcesandoBean [");
	 buffer.append("idInssFichero = ").append(idInssFichero);
	 buffer.append("numeroLinea = ").append(numeroLinea);
	 buffer.append("codTipoMovimiento = ").append(codTipoMovimiento);
	 buffer.append("linea = ").append(linea);
	 buffer.append("fechaEfectoSituacion = ").append(fechaEfectoSituacion);
	 buffer.append("]");
	 return buffer.toString();
  }
}

