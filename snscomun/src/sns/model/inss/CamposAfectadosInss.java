package sns.model.inss;

public class CamposAfectadosInss {
	public static final String ID_INSS = "1";
	public static final String COD_TIPO_ASEGURADO = "2";
	public static final String IPF = "3";
	public static final String DNI_NIE = "4";
	public static final String PASAPORTE = "5";
	public static final String NAF = "6";
	public static final String NAF_SEC1 = "7";
	public static final String NAF_SEC2 = "8";
	public static final String NAF_SEC3 = "9";
	public static final String NAF_SEC4 = "10";
	public static final String NAF_SEC5 = "11";
	public static final String NAF_SEC6 = "12";
	public static final String NAF_SEC7 = "13";
	public static final String NAF_SEC8 = "14";
	public static final String NAF_SEC9 = "15";
	public static final String INDICATIVO_NOMBRE = "16";
	public static final String APELLIDOS_NOMBRE = "17";
	public static final String APELLIDO1 = "18";
	public static final String APELLIDO2 = "19";
	public static final String NOMBRE = "20";
	public static final String NACIONALIDAD = "21";
	public static final String FECHA_NACIMIENTO = "22";
	public static final String SEXO = "23";
	public static final String INDICATIVO_DOMICILIO = "24";
	public static final String DOMICILIO = "25";
	public static final String TIPO_ASEGURAMIENTO = "26";
	public static final String COD_INDICADOR_DE_FARMACIA = "27";
	public static final String COD_SUBINDICADOR_DE_FARMACIA = "28";
	public static final String COD_SITUACION = "29";
	public static final String FECHA_EFECTO_SITUACION = "30";
	public static final String COD_TIPO_BENEFICIARIO = "31";
	public static final String IPF_TITULAR = "32";
	public static final String NAF_TITULAR = "33";
	public static final String NUMERO_SECUENCIA = "34";
	public static final String COD_USUARIO_SNS = "35";
	public static final String CRITERIO_IDENTIFICACION_SNS = "36";
	public static final String COD_TIPO_MOTIVO_BAJA = "37";

	private String codCampo;
	private String valorAnterior;
	private String valorNuevo;


	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setCodCampo(String codCampo) {
		this.codCampo = codCampo;
	}


	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getCodCampo() {
		return codCampo;
	}

	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

	public CamposAfectadosInss(String codCampo, String valorAnterior, String valorNuevo) {
		this.codCampo = codCampo;
		this.valorAnterior = valorAnterior;
		this.valorNuevo = valorNuevo;
	}

}
