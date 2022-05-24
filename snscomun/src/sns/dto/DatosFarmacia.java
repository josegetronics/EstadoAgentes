package sns.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Tue Nov 13 12:11:30 CET 2012
/ ******************************************************/
public class DatosFarmacia { 

  private boolean vacio=false;
  private java.lang.String codUsuarioSns;
  private java.lang.String codIndicadorDeFarmacia;
  private java.lang.String codSubindicadorDeFarmacia;
  private java.math.BigDecimal codTipoProcedencia;

  public static final String NOMBRE_TABLA="DATOS_FARMACIA";


  public static final String INDICE_SELECT_BY_PK_COD_USUARIO_SNS="1";
  public static final String QUERY_SELECT_BY_PK="SELECT * FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_DELETE_COD_USUARIO_SNS="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_USUARIO_SNS = ?";


  public static final String INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA="1";
  public static final String INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA="2";
  public static final String INDICE_UPDATE_COD_TIPO_PROCEDENCIA="3";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="4";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_INDICADOR_DE_FARMACIA = ?, COD_SUBINDICADOR_DE_FARMACIA = ?, COD_TIPO_PROCEDENCIA = ? where COD_USUARIO_SNS = ?";


  public java.util.HashMap getParametrosSelectByPk() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_SELECT_BY_PK_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public java.util.HashMap getParametrosDelete() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_DELETE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public java.util.HashMap getParametrosUpdate() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_UPDATE_COD_INDICADOR_DE_FARMACIA,codIndicadorDeFarmacia);
	  hParam.put(INDICE_UPDATE_COD_SUBINDICADOR_DE_FARMACIA,codSubindicadorDeFarmacia);
	  hParam.put(INDICE_UPDATE_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  hParam.put(INDICE_UPDATE_COD_USUARIO_SNS,codUsuarioSns);
	  return hParam;
  }
  public static final String INDICE_INSERT_COD_USUARIO_SNS="1";
  public static final String INDICE_INSERT_COD_INDICADOR_DE_FARMACIA="2";
  public static final String INDICE_INSERT_COD_SUBINDICADOR_DE_FARMACIA="3";
  public static final String INDICE_INSERT_COD_TIPO_PROCEDENCIA="4";
  public static final String QUERY_INSERT="INSERT INTO DATOS_FARMACIA (COD_USUARIO_SNS,COD_INDICADOR_DE_FARMACIA,COD_SUBINDICADOR_DE_FARMACIA,COD_TIPO_PROCEDENCIA) VALUES (?,?,?,?)";
  public java.util.HashMap getParametrosInsert() {
	  java.util.HashMap hParam=new java.util.HashMap();
	  hParam.put(INDICE_INSERT_COD_USUARIO_SNS,codUsuarioSns);
	  hParam.put(INDICE_INSERT_COD_INDICADOR_DE_FARMACIA,codIndicadorDeFarmacia);
	  hParam.put(INDICE_INSERT_COD_SUBINDICADOR_DE_FARMACIA,codSubindicadorDeFarmacia);
	  hParam.put(INDICE_INSERT_COD_TIPO_PROCEDENCIA,codTipoProcedencia);
	  return hParam;
  }

  public DatosFarmacia() {this.vacio=true;}
  public DatosFarmacia(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.codIndicadorDeFarmacia=(java.lang.String)hd.get("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=(java.lang.String)hd.get("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codTipoProcedencia=(java.math.BigDecimal)hd.get("COD_TIPO_PROCEDENCIA");
  }

  public DatosFarmacia(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.codIndicadorDeFarmacia=rs.getString("COD_INDICADOR_DE_FARMACIA");
	 this.codSubindicadorDeFarmacia=rs.getString("COD_SUBINDICADOR_DE_FARMACIA");
	 this.codTipoProcedencia=rs.getBigDecimal("COD_TIPO_PROCEDENCIA");
  }

  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.lang.String getCodIndicadorDeFarmacia() {
	 return this.codIndicadorDeFarmacia;
  }
  public void setCodIndicadorDeFarmacia(java.lang.String codIndicadorDeFarmacia) {
	 this.codIndicadorDeFarmacia=codIndicadorDeFarmacia;
	 this.vacio=false;
  }
  public java.lang.String getCodSubindicadorDeFarmacia() {
	 return this.codSubindicadorDeFarmacia;
  }
  public void setCodSubindicadorDeFarmacia(java.lang.String codSubindicadorDeFarmacia) {
	 this.codSubindicadorDeFarmacia=codSubindicadorDeFarmacia;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodTipoProcedencia() {
	 return this.codTipoProcedencia;
  }
  public void setCodTipoProcedencia(java.math.BigDecimal codTipoProcedencia) {
	 this.codTipoProcedencia=codTipoProcedencia;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }
  public String toString() {
	 StringBuffer buffer = new StringBuffer();
	 buffer.append("DatosFarmacia [");
	 buffer.append("codUsuarioSns = ").append(codUsuarioSns);
	 buffer.append("codIndicadorDeFarmacia = ").append(codIndicadorDeFarmacia);
	 buffer.append("codSubindicadorDeFarmacia = ").append(codSubindicadorDeFarmacia);
	 buffer.append("codTipoProcedencia = ").append(codTipoProcedencia);
	 buffer.append("]");
	 return buffer.toString();
  }
}

