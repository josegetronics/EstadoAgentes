package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:14 CET 2012
/ ******************************************************/
public class InssHistoricoBean { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssHistorico;
  private java.math.BigDecimal idInss;
  private java.sql.Timestamp fechaAplicacionSolicitud;
  private java.math.BigDecimal codTipoProcedencia;
  private java.math.BigDecimal procedencia;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="SEQ_INSS_ID_INSS_HISTORICO";


  public static final String NOMBRE_TABLA="INSS_HISTORICO";


  public static final String INDICE_SELECT_BY_PK_ID_INSS_HISTORICO="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS_HISTORICO = ?";


  public static final String INDICE_DELETE_ID_INSS_HISTORICO="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_HISTORICO = ?";


  public static final String INDICE_UPDATE_ID_INSS="1";
  public static final String INDICE_UPDATE_FECHA_APLICACION_SOLICITUD="2";
  public static final String INDICE_UPDATE_COD_TIPO_PROCEDENCIA="3";
  public static final String INDICE_UPDATE_PROCEDENCIA="4";
  public static final String INDICE_UPDATE_ID_INSS_HISTORICO="5";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set ID_INSS = ?, FECHA_APLICACION_SOLICITUD = ?, COD_TIPO_PROCEDENCIA = ?, PROCEDENCIA = ? where ID_INSS_HISTORICO = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_HISTORICO,idInssHistorico);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS_HISTORICO,idInssHistorico);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  hParam.put(INDICE_UPDATE_FECHA_APLICACION_SOLICITUD,fechaAplicacionSolicitud);
	  hParam.put(INDICE_UPDATE_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  hParam.put(INDICE_UPDATE_PROCEDENCIA,procedencia);
	  hParam.put(INDICE_UPDATE_ID_INSS_HISTORICO,idInssHistorico);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS_HISTORICO="1";
  public static final String INDICE_INSERT_ID_INSS="2";
  public static final String INDICE_INSERT_FECHA_APLICACION_SOLICITUD="3";
  public static final String INDICE_INSERT_COD_TIPO_PROCEDENCIA="4";
  public static final String INDICE_INSERT_PROCEDENCIA="5";
  public static final String QUERY_INSERT="INSERT INTO INSS_HISTORICO (ID_INSS_HISTORICO,ID_INSS,FECHA_APLICACION_SOLICITUD,COD_TIPO_PROCEDENCIA,PROCEDENCIA) VALUES (?,?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS_HISTORICO,idInssHistorico);
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_FECHA_APLICACION_SOLICITUD,fechaAplicacionSolicitud);
	  hParam.put(INDICE_INSERT_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  hParam.put(INDICE_INSERT_PROCEDENCIA,procedencia);
	  return hParam;
  }

  public InssHistoricoBean() {this.vacio=true;}
  public InssHistoricoBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssHistorico=(java.math.BigDecimal)hd.get("ID_INSS_HISTORICO");
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.fechaAplicacionSolicitud=(java.sql.Timestamp)hd.get("FECHA_APLICACION_SOLICITUD");
	 this.codTipoProcedencia=(java.math.BigDecimal)hd.get("COD_TIPO_PROCEDENCIA");
	 this.procedencia=(java.math.BigDecimal)hd.get("PROCEDENCIA");
  }

  public InssHistoricoBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssHistorico=rs.getBigDecimal("ID_INSS_HISTORICO");
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.fechaAplicacionSolicitud=rs.getTimestamp("FECHA_APLICACION_SOLICITUD");
	 this.codTipoProcedencia=rs.getBigDecimal("COD_TIPO_PROCEDENCIA");
	 this.procedencia=rs.getBigDecimal("PROCEDENCIA");
  }

  public java.math.BigDecimal getIdInssHistorico() {
	 return this.idInssHistorico;
  }
  public void setIdInssHistorico(java.math.BigDecimal idInssHistorico) {
	 this.idInssHistorico=idInssHistorico;
	 this.vacio=false;
  }
  public java.math.BigDecimal getIdInss() {
	 return this.idInss;
  }
  public void setIdInss(java.math.BigDecimal idInss) {
	 this.idInss=idInss;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaAplicacionSolicitud() {
	 return this.fechaAplicacionSolicitud;
  }
  public void setFechaAplicacionSolicitud(java.sql.Timestamp fechaAplicacionSolicitud) {
	 this.fechaAplicacionSolicitud=fechaAplicacionSolicitud;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTipoProcedencia() {
	 return this.codTipoProcedencia;
  }
  public void setCodTipoProcedencia(java.math.BigDecimal codTipoProcedencia) {
	 this.codTipoProcedencia=codTipoProcedencia;
	 this.vacio=false;
  }
  public java.math.BigDecimal getProcedencia() {
	 return this.procedencia;
  }
  public void setProcedencia(java.math.BigDecimal procedencia) {
	 this.procedencia=procedencia;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssHistoricoBean [");
	 buffer.append("idInssHistorico = ").append(idInssHistorico);
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("fechaAplicacionSolicitud = ").append(fechaAplicacionSolicitud);
	 buffer.append("codTipoProcedencia = ").append(codTipoProcedencia);
	 buffer.append("procedencia = ").append(procedencia);
	 buffer.append("]");
	 return buffer.toString();
  }
}

