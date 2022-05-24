package sns.model.inss.dto;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Mon Jul 16 20:45:41 CEST 2012
/ ******************************************************/
public class InssCamposAfectadosBean { 

  private boolean vacio=false;
  private java.math.BigDecimal idInssHistorico;
  private java.math.BigDecimal codCampo;
  private java.lang.String valorAnterior;
  private java.lang.String valorNuevo;

  public static final String NOMBRE_TABLA="INSS_CAMPOS_AFECTADOS";


  public static final String INDICE_DELETE_ID_INSS_HISTORICO="1";
  public static final String INDICE_DELETE_COD_CAMPO="2";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where ID_INSS_HISTORICO = ? and COD_CAMPO = ?";


  public static final String INDICE_UPDATE_VALOR_ANTERIOR="1";
  public static final String INDICE_UPDATE_VALOR_NUEVO="2";
  public static final String INDICE_UPDATE_ID_INSS_HISTORICO="3";
  public static final String INDICE_UPDATE_COD_CAMPO="4";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set VALOR_ANTERIOR = ?, VALOR_NUEVO = ? where ID_INSS_HISTORICO = ? and COD_CAMPO = ?";


  public static final String INDICE_INSERT_ID_INSS_HISTORICO="1";
  public static final String INDICE_INSERT_COD_CAMPO="2";
  public static final String INDICE_INSERT_VALOR_ANTERIOR="3";
  public static final String INDICE_INSERT_VALOR_NUEVO="4";
  public static final String QUERY_INSERT="INSERT INTO INSS_CAMPOS_AFECTADOS (ID_INSS_HISTORICO,COD_CAMPO,VALOR_ANTERIOR,VALOR_NUEVO) VALUES (?,?,?,?)";

  public InssCamposAfectadosBean() {this.vacio=true;}
  public InssCamposAfectadosBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.idInssHistorico=(java.math.BigDecimal)hd.get("ID_INSS_HISTORICO");
	 this.codCampo=(java.math.BigDecimal)hd.get("COD_CAMPO");
	 this.valorAnterior=(java.lang.String)hd.get("VALOR_ANTERIOR");
	 this.valorNuevo=(java.lang.String)hd.get("VALOR_NUEVO");
  }

  public InssCamposAfectadosBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.idInssHistorico=rs.getBigDecimal("ID_INSS_HISTORICO");
	 this.codCampo=rs.getBigDecimal("COD_CAMPO");
	 this.valorAnterior=rs.getString("VALOR_ANTERIOR");
	 this.valorNuevo=rs.getString("VALOR_NUEVO");
  }

  public java.math.BigDecimal getIdInssHistorico() {
	 return this.idInssHistorico;
  }
  public void setIdInssHistorico(java.math.BigDecimal idInssHistorico) {
	 this.idInssHistorico=idInssHistorico;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodCampo() {
	 return this.codCampo;
  }
  public void setCodCampo(java.math.BigDecimal codCampo) {
	 this.codCampo=codCampo;
	 this.vacio=false;
  }
  public java.lang.String getValorAnterior() {
	 return this.valorAnterior;
  }
  public void setValorAnterior(java.lang.String valorAnterior) {
	 this.valorAnterior=valorAnterior;
	 this.vacio=false;
  }
  public java.lang.String getValorNuevo() {
	 return this.valorNuevo;
  }
  public void setValorNuevo(java.lang.String valorNuevo) {
	 this.valorNuevo=valorNuevo;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }}
