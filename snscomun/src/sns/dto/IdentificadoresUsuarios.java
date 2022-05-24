package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:31 CET 2012
/ ******************************************************/
public class IdentificadoresUsuarios { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.math.BigDecimal codAgente;
  private java.lang.String idEnSsalud;

  public static final String NOMBRE_TABLA="IDENTIFICADORES_USUARIO";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String INDICE_SELECT_BY_PK_COD_AGENTE="2";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ? and COD_AGENTE = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String INDICE_DELETE_COD_AGENTE="2";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ? and COD_AGENTE = ?";


  public static final String INDICE_UPDATE_ID_EN_SSALUD="1";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="2";
  public static final String INDICE_UPDATE_COD_AGENTE="3";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set ID_EN_SSALUD = ? where COD_USUARIO_SNS = ? and COD_AGENTE = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_SELECT_BY_PK_COD_AGENTE,codAgente);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_DELETE_COD_AGENTE,codAgente);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_ID_EN_SSALUD,idEnSsalud);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_UPDATE_COD_AGENTE,codAgente);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_AGENTE="2";
  public static final String INDICE_INSERT_ID_EN_SSALUD="3";
  public static final String QUERY_INSERT="INSERT INTO IDENTIFICADORES_USUARIO (COD_USUARIO_SNS,COD_AGENTE,ID_EN_SSALUD) VALUES (?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_AGENTE,codAgente);
	  hParam.put(INDICE_INSERT_ID_EN_SSALUD,idEnSsalud);
	  return hParam;
  }

  public IdentificadoresUsuarios() {this.vacio=true;}
  public IdentificadoresUsuarios(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codAgente=(java.math.BigDecimal)hd.get("COD_AGENTE");
	 this.idEnSsalud=(java.lang.String)hd.get("ID_EN_SSALUD");
  }

  public IdentificadoresUsuarios(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codAgente=rs.getBigDecimal("COD_AGENTE");
	 this.idEnSsalud=rs.getString("ID_EN_SSALUD");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAgente() {
	 return this.codAgente;
  }
  public void setCodAgente(java.math.BigDecimal codAgente) {
	 this.codAgente=codAgente;
	 this.vacio=false;
  }
  public java.lang.String getIdEnSsalud() {
	 return this.idEnSsalud;
  }
  public void setIdEnSsalud(java.lang.String idEnSsalud) {
	 this.idEnSsalud=idEnSsalud;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("IdentificadoresUsuarios [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codAgente = ").append(codAgente);
	 buffer.append("idEnSsalud = ").append(idEnSsalud);
	 buffer.append("]");
	 return buffer.toString();
  }
}

