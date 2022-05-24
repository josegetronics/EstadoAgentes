package sns.model;

public class CamposAfectados implements java.io.Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public static final String NAF = "naf";
  public static final String NAF_TITULAR = "naf_titular";
  public static final String SITUACION = "situacion";
  public static final String TITULO = "titulo";
  public static final String ID_SSALUD = "id_ssalud";
  public static final String COD_PRESTACION_SERVICIO =
      "cod_prestacion_servicio";
  public static final String FECHA_ALTA_SSALUD = "fecha_alta_ssalud";
  public static final String COD_ESTADO = "cod_estado";
  public static final String COD_PAIS = "cod_pais";
  public static final String TIPO_VIA = "tipo_via";
  public static final String NOMBREVIA = "nombreVia";
  public static final String NUMERO = "numero";
  public static final String BIS = "bis";
  public static final String BLOQUE = "bloque";
  public static final String ESCALERA = "escalera";
  public static final String PISO = "piso";
  public static final String PUERTA = "puerta";
  public static final String CODIGO_POSTAL = "codigo_postal";
  public static final String MUNICIPIO = "municipio";
  public static final String PROVINCIA = "provincia";
  public static final String PROVEEDOR = "proveedor";

  public static final String PROVEEDOR_AE = "proveedor_ae";
  public static final String PROVEEDOR_AP = "proveedor_ap";
  public static final String PROVEEDOR_FAR = "proveedor_far";
  public static final String PROVEEDOR_URG = "proveedor_urg";

  public static final String ASEGURADORA = "aseguradora";
  public static final String GESTORA = "gestora";
  public static final String COLABORADORA = "colaboradora";
  public static final String NOMBRE = "nombre";
  public static final String APELLIDO1 = "apellido1";
  public static final String APELLIDO2 = "apellido2";
  public static final String PAIS_NACIMIENTO = "pais_nacimiento";
  public static final String FLAG_EXTRANJERO = "flag_extranjero";
  public static final String NACIONALIDAD = "nacionalidad";
  public static final String TARJETA_IDENTIDAD = "tarjeta_identidad";
  public static final String CA_NAC = "CA_nac";
  public static final String FECHA_NAC = "fecha_nac";
  public static final String SEXO = "sexo";
  public static final String DNI = "dni";
  public static final String DNIDUP = "dnidup";
  public static final String PASAPORTE = "pasaporte";
  public static final String CODSNSDUP = "codSnsDup";
  public static final String CODSNS = "codSns";
  public static final String CODSNSTITULAR = "codSnsTitular";

  public static final String INDICADOR_DE_FARMACIA = "indicadorDeFarmacia";
  public static final String SUBINDICADOR_DE_FARMACIA = "subIndicadorDeFarmacia";
  public static final String TIPO_PROCEDENCIA_INDICADOR_DE_FARMACIA = "codTipoProcedencia";

  private String idCampo;
  private String valorAnterior;
  private String valorSolicitud;
  public String getValorSolicitud() {
    return valorSolicitud;
  }

  public String getValorAnterior() {
    return valorAnterior;
  }

  public void setIdCampo(String idCampo) {
    this.idCampo = idCampo;
  }

  public void setValorSolicitud(String valorSolicitud) {
    this.valorSolicitud = valorSolicitud;
  }

  public void setValorAnterior(String valorAnterior) {
    this.valorAnterior = valorAnterior;
  }

  public String getIdCampo() {
    return idCampo;
  }

  public CamposAfectados(String idCampo,
                         String valorAnterior,
                         String valorSolicitud) {
    this.idCampo = idCampo;
    this.valorAnterior = valorAnterior;
    this.valorSolicitud = valorSolicitud;
  }
}
