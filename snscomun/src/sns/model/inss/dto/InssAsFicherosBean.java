package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:34:14 CET 2012
/ ******************************************************/
public class InssAsFicherosBean { 

  private boolean vacio=false;
  private java.math.BigDecimal idInss;
  private java.math.BigDecimal idInssFichero;
  private java.math.BigDecimal numeroLinea;

  public static final String NOMBRE_TABLA="INSS_AS_FICHEROS";


  public static final String INDICE_SELECT_BY_PK_ID_INSS="1";
  public static final String INDICE_SELECT_BY_PK_ID_INSS_FICHERO="2";
  public static final String INDICE_SELECT_BY_PK_NUMERO_LINEA="3";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where ID_INSS = ? and ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public static final String INDICE_DELETE_ID_INSS="1";
  public static final String INDICE_DELETE_ID_INSS_FICHERO="2";
  public static final String INDICE_DELETE_NUMERO_LINEA="3";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS = ? and ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public static final String INDICE_UPDATE_ID_INSS="1";
  public static final String INDICE_UPDATE_ID_INSS_FICHERO="2";
  public static final String INDICE_UPDATE_NUMERO_LINEA="3";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " where ID_INSS = ? and ID_INSS_FICHERO = ? and NUMERO_LINEA = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS,idInss);
	  hParam.put(INDICE_SELECT_BY_PK_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_SELECT_BY_PK_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_ID_INSS,idInss);
	  hParam.put(INDICE_DELETE_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_DELETE_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID_INSS,idInss);
	  hParam.put(INDICE_UPDATE_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_UPDATE_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID_INSS="1";
  public static final String INDICE_INSERT_ID_INSS_FICHERO="2";
  public static final String INDICE_INSERT_NUMERO_LINEA="3";
  public static final String QUERY_INSERT="INSERT INTO INSS_AS_FICHEROS (ID_INSS,ID_INSS_FICHERO,NUMERO_LINEA) VALUES (?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID_INSS,idInss);
	  hParam.put(INDICE_INSERT_ID_INSS_FICHERO,idInssFichero);
	  hParam.put(INDICE_INSERT_NUMERO_LINEA,numeroLinea);
	  return hParam;
  }

  public InssAsFicherosBean() {this.vacio=true;}
  public InssAsFicherosBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInss=(java.math.BigDecimal)hd.get("ID_INSS");
	 this.idInssFichero=(java.math.BigDecimal)hd.get("ID_INSS_FICHERO");
	 this.numeroLinea=(java.math.BigDecimal)hd.get("NUMERO_LINEA");
  }

  public InssAsFicherosBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInss=rs.getBigDecimal("ID_INSS");
	 this.idInssFichero=rs.getBigDecimal("ID_INSS_FICHERO");
	 this.numeroLinea=rs.getBigDecimal("NUMERO_LINEA");
  }

  public java.math.BigDecimal getIdInss() {
	 return this.idInss;
  }
  public void setIdInss(java.math.BigDecimal idInss) {
	 this.idInss=idInss;
	 this.vacio=false;
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

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("InssAsFicherosBean [");
	 buffer.append("idInss = ").append(idInss);
	 buffer.append("idInssFichero = ").append(idInssFichero);
	 buffer.append("numeroLinea = ").append(numeroLinea);
	 buffer.append("]");
	 return buffer.toString();
  }
}

