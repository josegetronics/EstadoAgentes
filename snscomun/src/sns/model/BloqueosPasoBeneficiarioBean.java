package sns.model;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar Thu Jun 28 11:59:59 CEST 2012
/ ******************************************************/
public class BloqueosPasoBeneficiarioBean { 

  private boolean vacio=false;
  private java.math.BigDecimal codOperacion;
  private java.math.BigDecimal codAgenteOrigen;
  private java.sql.Timestamp fechaOperacion;
  private java.math.BigDecimal bloqueoActivo;
  private java.lang.String codUsuarioSns;
  private java.sql.Timestamp fechaOperacionRealizada;
  private java.math.BigDecimal codOperacionRealizada;

  public static final String NOMBRE_TABLA="BLOQUEADOS_PASO_BENEFICIARIO";


  public static final String INDICE_DELETE_COD_OPERACION="1";
  public static final String QUERY_DELETE="DELETE FROM " + NOMBRE_TABLA + " where COD_OPERACION = ?";


  public static final String INDICE_UPDATE_COD_AGENTE_ORIGEN="1";
  public static final String INDICE_UPDATE_FECHA_OPERACION="2";
  public static final String INDICE_UPDATE_BLOQUEO_ACTIVO="3";
  public static final String INDICE_UPDATE_COD_USUARIO_SNS="4";
  public static final String INDICE_UPDATE_FECHA_OPERACION_REALIZADA="5";
  public static final String INDICE_UPDATE_COD_OPERACION_REALIZADA="6";
  public static final String INDICE_UPDATE_COD_OPERACION="7";
  public static final String QUERY_UPDATE="UPDATE " + NOMBRE_TABLA + " set COD_AGENTE_ORIGEN = ?, FECHA_OPERACION = ?, BLOQUEO_ACTIVO = ?, COD_USUARIO_SNS = ?, FECHA_OPERACION_REALIZADA = ?, COD_OPERACION_REALIZADA = ? where COD_OPERACION = ?";


  public static final String INDICE_INSERT_COD_OPERACION="1";
  public static final String INDICE_INSERT_COD_AGENTE_ORIGEN="2";
  public static final String INDICE_INSERT_FECHA_OPERACION="3";
  public static final String INDICE_INSERT_BLOQUEO_ACTIVO="4";
  public static final String INDICE_INSERT_COD_USUARIO_SNS="5";
  public static final String INDICE_INSERT_FECHA_OPERACION_REALIZADA="6";
  public static final String INDICE_INSERT_COD_OPERACION_REALIZADA="7";
  public static final String QUERY_INSERT="INSERT INTO BLOQUEADOS_PASO_BENEFICIARIO (COD_OPERACION,COD_AGENTE_ORIGEN,FECHA_OPERACION,BLOQUEO_ACTIVO,COD_USUARIO_SNS,FECHA_OPERACION_REALIZADA,COD_OPERACION_REALIZADA) VALUES (?,?,?,?,?,?,?)";

  public BloqueosPasoBeneficiarioBean() {this.vacio=true;}
  public BloqueosPasoBeneficiarioBean(java.util.HashMap hd) {
	  this.vacio=false;
	 this.codOperacion=(java.math.BigDecimal)hd.get("COD_OPERACION");
	 this.codAgenteOrigen=(java.math.BigDecimal)hd.get("COD_AGENTE_ORIGEN");
	 this.fechaOperacion=(java.sql.Timestamp)hd.get("FECHA_OPERACION");
	 this.bloqueoActivo=(java.math.BigDecimal)hd.get("BLOQUEO_ACTIVO");
	 this.codUsuarioSns=(java.lang.String)hd.get("COD_USUARIO_SNS");
	 this.fechaOperacionRealizada=(java.sql.Timestamp)hd.get("FECHA_OPERACION_REALIZADA");
	 this.codOperacionRealizada=(java.math.BigDecimal)hd.get("COD_OPERACION_REALIZADA");
  }

  public BloqueosPasoBeneficiarioBean(java.sql.ResultSet rs) throws java.sql.SQLException {
	  this.vacio=false;
	 this.codOperacion=rs.getBigDecimal("COD_OPERACION");
	 this.codAgenteOrigen=rs.getBigDecimal("COD_AGENTE_ORIGEN");
	 this.fechaOperacion=rs.getTimestamp("FECHA_OPERACION");
	 this.bloqueoActivo=rs.getBigDecimal("BLOQUEO_ACTIVO");
	 this.codUsuarioSns=rs.getString("COD_USUARIO_SNS");
	 this.fechaOperacionRealizada=rs.getTimestamp("FECHA_OPERACION_REALIZADA");
	 this.codOperacionRealizada=rs.getBigDecimal("COD_OPERACION_REALIZADA");
  }

  public java.math.BigDecimal getCodOperacion() {
	 return this.codOperacion;
  }
  public void setCodOperacion(java.math.BigDecimal codOperacion) {
	 this.codOperacion=codOperacion;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodAgenteOrigen() {
	 return this.codAgenteOrigen;
  }
  public void setCodAgenteOrigen(java.math.BigDecimal codAgenteOrigen) {
	 this.codAgenteOrigen=codAgenteOrigen;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaOperacion() {
	 return this.fechaOperacion;
  }
  public void setFechaOperacion(java.sql.Timestamp fechaOperacion) {
	 this.fechaOperacion=fechaOperacion;
	 this.vacio=false;
  }
  public java.math.BigDecimal getBloqueoActivo() {
	 return this.bloqueoActivo;
  }
  public void setBloqueoActivo(java.math.BigDecimal bloqueoActivo) {
	 this.bloqueoActivo=bloqueoActivo;
	 this.vacio=false;
  }
  public java.lang.String getCodUsuarioSns() {
	 return this.codUsuarioSns;
  }
  public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
	 this.codUsuarioSns=codUsuarioSns;
	 this.vacio=false;
  }
  public java.sql.Timestamp getFechaOperacionRealizada() {
	 return this.fechaOperacionRealizada;
  }
  public void setFechaOperacionRealizada(java.sql.Timestamp fechaOperacionRealizada) {
	 this.fechaOperacionRealizada=fechaOperacionRealizada;
	 this.vacio=false;
  }
  public java.math.BigDecimal getCodOperacionRealizada() {
	 return this.codOperacionRealizada;
  }
  public void setCodOperacionRealizada(java.math.BigDecimal codOperacionRealizada) {
	 this.codOperacionRealizada=codOperacionRealizada;
	 this.vacio=false;
  }

  public boolean isVacio() {
	 return this.vacio;
  }}
