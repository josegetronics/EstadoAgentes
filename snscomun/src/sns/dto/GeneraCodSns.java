package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:26 CET 2012
/ ******************************************************/
public class GeneraCodSns { 

  private boolean vacio=false;
  private java.math.BigDecimal id;

  public static final String NOMBRE_SECUENCIA_ASOCIADA="CODIGOSNS";


  public static final String NOMBRE_TABLA="GENERA_COD_SNS";


  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + "";


  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + "";


  public static final String INDICE_UPDATE_ID="1";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set ID = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID,id);
	  return hParam;
  }
  public static final String INDICE_INSERT_ID="1";
  public static final String QUERY_INSERT="INSERT INTO GENERA_COD_SNS (ID) VALUES (?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_ID,id);
	  return hParam;
  }

  public GeneraCodSns() {this.vacio=true;}
  public GeneraCodSns(java.util.HashMap hd) {
	  this.vacio=false;
	 this.id=(java.math.BigDecimal)hd.get("ID");
  }

  public GeneraCodSns(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.id=rs.getBigDecimal("ID");
  }

  public java.math.BigDecimal getId() {
	 return this.id;
  }
  public void setId(java.math.BigDecimal id) {
	 this.id=id;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("GeneraCodSns [");
	 buffer.append("id = ").append(id);
	 buffer.append("]");
	 return buffer.toString();
  }
}

