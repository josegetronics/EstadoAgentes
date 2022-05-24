package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Fri Nov 16 12:17:37 CET 2012
/ ******************************************************/
public class NafControl { 

  private boolean vacio=false;
  private java.lang.String naf;
  private java.lang.String codUsuarioSns;

  public static final String NOMBRE_TABLA="NAF_CONTROL";


  public static final String INDICE_SELECT_BY_PK_NAF="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where NAF = ?";


  public static final String INDICE_DELETE_NAF="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where NAF = ?";


  public static final String INDICE_UPDATE_COD_USUARIO_SNS="1";
  public static final String INDICE_UPDATE_NAF="2";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_USUARIO_SNS = ? where NAF = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_NAF,naf);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_NAF,naf);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_NAF,naf);
	  return hParam;
  }
  public static final String INDICE_INSERT_NAF="1";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="2";
  public static final String QUERY_INSERT="INSERT INTO NAF_CONTROL (NAF,COD_USUARIO_SNS) VALUES (?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_NAF,naf);
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }

  public NafControl() {this.vacio=true;}
  public NafControl(java.util.HashMap hd) {
	  this.vacio=false;
	 this.naf=(java.lang.String)hd.get("NAF");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
  }

  public NafControl(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.naf=rs.getString("NAF");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
  }

  public java.lang.String getNaf() {
	 return this.naf;
  }
  public void setNaf(java.lang.String naf) {
	 this.naf=naf;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("NafControl [");
	 buffer.append("naf = ").append(naf);
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("]");
	 return buffer.toString();
  }
}

