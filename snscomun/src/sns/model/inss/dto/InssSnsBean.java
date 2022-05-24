package sns.model.inss.dto;

import java.io.Serializable;

/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:15 CET 2012
/ ******************************************************/
public class InssSnsBean implements Serializable { 

  /**
	 * 
	 */
	private static final long serialVersionUID = -7425333204178330564L;
private boolean vacio=false;
  private java.math.BigDecimal idInss;
  private java.lang.String codUsuarioSns;
  private java.lang.String criterioIdentificacionSns;

  public static final String NOMBRE_TABLA="INSS_SNS_TIT";


  public static final String INDICE_SELECT_BY_PK_ID_INSS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS = ?";


  public static final String INDICE_DELETE_ID_INSS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS = ?";


  public static final String INDICE_UPDATE_COD_USUARIO_SNS="1";
  public static final String INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS="2";
  public static final String INDICE_UPDATE_ID_INSS="3";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO_SNS = ?, CRITERIO_IDENTIFICACION_SNS = ? where ID_INSS = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS,idInss);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS,idInss);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS="1";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="2";
  public static final String INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS="3";
  public static final String QUERY_INSERT="INSERT INTO INSS_SNS_TIT (ID_INSS,COD_USUARIO_SNS,CRITERIO_IDENTIFICACION_SNS) VALUES (?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_CRITERIO_IDENTIFICACION_SNS,criterioIdentificacionSns);
	  return hParam;
  }

  public InssSnsBean() {this.vacio=true;}
  public InssSnsBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.criterioIdentificacionSns=(java.lang.String)hd.get("CRITERIO_IDENTIFICACION_SNS");
  }

  public InssSnsBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.criterioIdentificacionSns=rs.getString("CRITERIO_IDENTIFICACION_SNS");
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
  public java.lang.String getCriterioIdentificacionSns() {
	 return this.criterioIdentificacionSns;
  }
  public void setCriterioIdentificacionSns(java.lang.String criterioIdentificacionSns) {
	 this.criterioIdentificacionSns=criterioIdentificacionSns;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssSnsBean [");
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("criterioIdentificacionSns = ").append(criterioIdentificacionSns);
	 buffer.append("]");
	 return buffer.toString();
  }
}

