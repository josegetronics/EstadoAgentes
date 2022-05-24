package sns.dto;

import gasai.util.Misc;

/****************************************************** /
Clase generada con GeneraBean de gasai.jar Fri Nov 16 12:17:36 CET 2012
/ ******************************************************/
public class RegistroOperaciones { 

  private boolean vacio=false;
  private java.math.BigDecimal codOperacion;
  private java.math.BigDecimal codOperacionMaestra;
  private java.math.BigDecimal codEstado;
  private java.math.BigDecimal codAgenteOrigen;
  private java.math.BigDecimal codAgenteDestino;
  private java.sql.Timestamp fechaOperacion;
  private java.lang.String firmaDigital;
  private java.lang.String codTipoOperacion;
  private String mensajeXml;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="REGOPERACION";


  public static final String NOMBRE_TABLA="REGISTRO_OPERACIONES";


  public static final String INDICE_SELECT_BY_PK_COD_OPERACION="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_OPERACION = ?";


  public static final String INDICE_DELETE_COD_OPERACION="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_OPERACION = ?";


  public static final String INDICE_UPDATE_COD_OPERACION_MAESTRA="1";
  public static final String INDICE_UPDATE_COD_ESTADO="2";
  public static final String INDICE_UPDATE_COD_AGENTE_ORIGEN="3";
  public static final String INDICE_UPDATE_COD_AGENTE_DESTINO="4";
  public static final String INDICE_UPDATE_FECHA_OPERACION="5";
  public static final String INDICE_UPDATE_FIRMA_DIGITAL="6";
  public static final String INDICE_UPDATE_COD_TIPO_OPERACION="7";
  public static final String INDICE_UPDATE_MENSAJE_XML="8";
  public static final String INDICE_UPDATE_COD_OPERACION="9";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_OPERACION_MAESTRA = ?, COD_ESTADO = ?, COD_AGENTE_ORIGEN = ?, COD_AGENTE_DESTINO = ?, FECHA_OPERACION = ?, FIRMA_DIGITAL = ?, COD_TIPO_OPERACION = ?, MENSAJE_XML = ? where COD_OPERACION = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_COD_OPERACION,codOperacion);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_COD_OPERACION,codOperacion);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_OPERACION_MAESTRA,codOperacionMaestra);
	  hParam.put(INDICE_UPDATE_COD_ESTADO,codEstado);
	  hParam.put(INDICE_UPDATE_COD_AGENTE_ORIGEN,codAgenteOrigen);
	  hParam.put(INDICE_UPDATE_COD_AGENTE_DESTINO,codAgenteDestino);
	  hParam.put(INDICE_UPDATE_FECHA_OPERACION,fechaOperacion);
	  hParam.put(INDICE_UPDATE_FIRMA_DIGITAL,firmaDigital);
	  hParam.put(INDICE_UPDATE_COD_TIPO_OPERACION,codTipoOperacion);
	  hParam.put(INDICE_UPDATE_MENSAJE_XML,mensajeXml);
	  hParam.put(INDICE_UPDATE_COD_OPERACION,codOperacion);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_OPERACION="1";
  public static final String INDICE_INSERT_COD_OPERACION_MAESTRA="2";
  public static final String INDICE_INSERT_COD_ESTADO="3";
  public static final String INDICE_INSERT_COD_AGENTE_ORIGEN="4";
  public static final String INDICE_INSERT_COD_AGENTE_DESTINO="5";
  public static final String INDICE_INSERT_FECHA_OPERACION="6";
  public static final String INDICE_INSERT_FIRMA_DIGITAL="7";
  public static final String INDICE_INSERT_COD_TIPO_OPERACION="8";
  public static final String INDICE_INSERT_MENSAJE_XML="9";
  public static final String QUERY_INSERT="INSERT INTO REGISTRO_OPERACIONES (COD_OPERACION,COD_OPERACION_MAESTRA,COD_ESTADO,COD_AGENTE_ORIGEN,COD_AGENTE_DESTINO,FECHA_OPERACION,FIRMA_DIGITAL,COD_TIPO_OPERACION,MENSAJE_XML) VALUES (?,?,?,?,?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_OPERACION,codOperacion);
	  hParam.put(INDICE_INSERT_COD_OPERACION_MAESTRA,codOperacionMaestra);
	  hParam.put(INDICE_INSERT_COD_ESTADO,codEstado);
	  hParam.put(INDICE_INSERT_COD_AGENTE_ORIGEN,codAgenteOrigen);
	  hParam.put(INDICE_INSERT_COD_AGENTE_DESTINO,codAgenteDestino);
	  hParam.put(INDICE_INSERT_FECHA_OPERACION,fechaOperacion);
	  hParam.put(INDICE_INSERT_FIRMA_DIGITAL,firmaDigital);
	  hParam.put(INDICE_INSERT_COD_TIPO_OPERACION,codTipoOperacion);
	  hParam.put(INDICE_INSERT_MENSAJE_XML,mensajeXml);
	  return hParam;
  }

  public RegistroOperaciones() {this.vacio=true;}
  public RegistroOperaciones(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codOperacion=(java.math.BigDecimal)hd.get("COD_OPERACION");
	 this.codOperacionMaestra=(java.math.BigDecimal)hd.get("COD_OPERACION_MAESTRA");
	 this.codEstado=(java.math.BigDecimal)hd.get("COD_ESTADO");
	 this.codAgenteOrigen=(java.math.BigDecimal)hd.get("COD_AGENTE_ORIGEN");
	 this.codAgenteDestino=(java.math.BigDecimal)hd.get("COD_AGENTE_DESTINO");
	 this.fechaOperacion=(java.sql.Timestamp)hd.get("FECHA_OPERACION");
	 this.firmaDigital=(java.lang.String)hd.get("FIRMA_DIGITAL");
	 this.codTipoOperacion=(java.lang.String)hd.get("COD_TIPO_OPERACION");
	 this.mensajeXml=Misc.nz(hd.get("MENSAJE_XML"));
  }

  public RegistroOperaciones(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codOperacion=rs.getBigDecimal("COD_OPERACION");
	 this.codOperacionMaestra=rs.getBigDecimal("COD_OPERACION_MAESTRA");
	 this.codEstado=rs.getBigDecimal("COD_ESTADO");
	 this.codAgenteOrigen=rs.getBigDecimal("COD_AGENTE_ORIGEN");
	 this.codAgenteDestino=rs.getBigDecimal("COD_AGENTE_DESTINO");
	 this.fechaOperacion=rs.getTimestamp("FECHA_OPERACION");
	 this.firmaDigital=rs.getString("FIRMA_DIGITAL");
	 this.codTipoOperacion=rs.getString("COD_TIPO_OPERACION");
	 this.mensajeXml=rs.getString("MENSAJE_XML");
  }

  public java.math.BigDecimal getCodOperacion() {
	 return this.codOperacion;
  }
  public void setCodOperacion(java.math.BigDecimal codOperacion) {
	 this.codOperacion=codOperacion;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodOperacionMaestra() {
	 return this.codOperacionMaestra;
  }
  public void setCodOperacionMaestra(java.math.BigDecimal codOperacionMaestra) {
	 this.codOperacionMaestra=codOperacionMaestra;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodEstado() {
	 return this.codEstado;
  }
  public void setCodEstado(java.math.BigDecimal codEstado) {
	 this.codEstado=codEstado;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAgenteOrigen() {
	 return this.codAgenteOrigen;
  }
  public void setCodAgenteOrigen(java.math.BigDecimal codAgenteOrigen) {
	 this.codAgenteOrigen=codAgenteOrigen;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAgenteDestino() {
	 return this.codAgenteDestino;
  }
  public void setCodAgenteDestino(java.math.BigDecimal codAgenteDestino) {
	 this.codAgenteDestino=codAgenteDestino;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaOperacion() {
	 return this.fechaOperacion;
  }
  public void setFechaOperacion(java.sql.Timestamp fechaOperacion) {
	 this.fechaOperacion=fechaOperacion;
	 this.vacio=false;
  }
  public java.lang.String getFirmaDigital() {
	 return this.firmaDigital;
  }
  public void setFirmaDigital(java.lang.String firmaDigital) {
	 this.firmaDigital=firmaDigital;
	 this.vacio=false;
  }
  public java.lang.String getCodTipoOperacion() {
	 return this.codTipoOperacion;
  }
  public void setCodTipoOperacion(java.lang.String codTipoOperacion) {
	 this.codTipoOperacion=codTipoOperacion;
	 this.vacio=false;
  }
  public String getMensajeXml() {
	 return this.mensajeXml;
  }
  public void setMensajeXml(String mensajeXml) {
	 this.mensajeXml=mensajeXml;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("RegistroOperaciones [");
	 buffer.append("codOperacion = ").append(codOperacion);
	 buffer.append("codOperacionMaestra = ").append(codOperacionMaestra);
	 buffer.append("codEstado = ").append(codEstado);
	 buffer.append("codAgenteOrigen = ").append(codAgenteOrigen);
	 buffer.append("codAgenteDestino = ").append(codAgenteDestino);
	 buffer.append("fechaOperacion = ").append(fechaOperacion);
	 buffer.append("firmaDigital = ").append(firmaDigital);
	 buffer.append("codTipoOperacion = ").append(codTipoOperacion);
	 buffer.append("mensajeXml = ").append(mensajeXml);
	 buffer.append("]");
	 return buffer.toString();
  }
}

