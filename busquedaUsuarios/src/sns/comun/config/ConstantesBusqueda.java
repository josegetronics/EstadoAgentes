package sns.comun.config;


public class ConstantesBusqueda {

	
	public static final String SEPARADOR_CAMPOS                              = "|";
	public static final String SEPARADOR_CAMPOS_ATRIBUTO                     = "#";
	//
	public static final String CADENA_NOMBRE_COMPARACION                     = "nombre";
	public static final String CADENA_APELLIDO1_COMPARACION                  = "apellido1";
	public static final String CADENA_APELLIDO2_COMPARACION                  = "apellido2";
	//
	public static final String CADENA_NOMBRE                                 = "NOMBRE";
	public static final String CADENA_APELLIDO1                              = "APELLIDO1";
	public static final String CADENA_APELLIDO2                              = "APELLIDO2";
	public static final String CADENA_COD_SEXO                               = "COD_SEXO";
	public static final String CADENA_FECHA_NAC                              = "FECHA_NAC";
	public static final String CADENA_DNI_NIE                                = "DNI_NIE";
	public static final String CADENA_PASAPORTE                              = "PASAPORTE";
	public static final String CADENA_NAF                                    = "NAF";
	public static final String CADENA_NAF_SEC                                = "NAF_SEC";
	//
	public static final String VALOR_BUSQUEDA_CODSNS                         = "CODSNS";
	public static final String VALOR_BUSQUEDA_IDSSALUD                       = "IDSSALUD";
	public static final String VALOR_BUSQUEDA_DNI                            = "DNI_NIE";
	public static final String VALOR_BUSQUEDA_PASAPORTE                      = "PASAPORTE";
	public static final String VALOR_BUSQUEDA_NAF                            = "NAF";
	public static final String VALOR_BUSQUEDA_NAFTITULAR                     = "NAF_TITULAR";
	public static final String VALOR_BUSQUEDA_NAFT_RAIZ                      = "NAFT_RAIZ";
	public static final String VALOR_BUSQUEDA_NAF_SEC                        = "NAF_SEC";
	//
	//
	public static final String VALOR_BUSQUEDA_NAF_TIT_NOMBRE                 = "NAFT_NOMBRE";
	public static final String VALOR_BUSQUEDA_RAIZ_NOMBRE                    = "RAIZ_NOMBRE";
	public static final String VALOR_BUSQUEDA_RAIZ_APELLIDOS                 = "RAIZ_APELLIDOS";
	public static final String VALOR_BUSQUEDA_OTROS                          = "OTROS";
	public static final String VALOR_BUSQUEDA_NAFT_T_PREF_RAIZ               = "NAF_TIT_PREF_RAIZ";
	//
	//comprobarRaizApellidos=1
	//comprobarOtros=1
	//
	//
	//
	//
	public static final String TIPO_RESULTADO_ENCONTRADOS                    = "ENCONTRADOS";
	public static final String TIPO_RESULTADO_NO_ENCONTRADOS                 = "NO_ENCONTRADOS";
	public static final String TIPO_RESULTADO_VACIOS                         = "VACIOS";
	public static final String TIPO_RESULTADO_ENC_COINCIDENTES               = "COINCIDENTES_BA";
	public static final String COINCIDENTES_VINCULADOS_BA                    = "COINCIDENTES_VINCULADOS_BA";
	public static final String COINCIDENTES_NO_VINCULADOS_BA                 = "COINCIDENTES_NO_VINCULADOS_BA";
	public static final String TIPO_RESULTADO_ENC_COINCIDENTES_MISMA_CA      = "COINCIDENTES_MISMA_CA_BA";
	public static final String TIPO_RESULTADO_ENC_COINCIDENTES_DIFERENTE_CA  = "COINCIDENTES_DIFERENTE_CA_BA";
	public static final String TIPO_RESULTADO_ENC_NO_COINCIDENTES            = "NO_COINCIDENTES_BA";
	public static final String TIPO_RESULTADO_ENC_NO_COINCIDENTES_DNINAF     = "NO_COINCIDENTES_DNINAF_BA";
	public static final String TIPO_RESULTADO_DUPLICADOS                     = "DUPLICADOS";
	public static final String TIPO_RESULTADO_DUPLICADOS_ESTUDIO             = "DUPLICADOS_ESTUDIO";
	public static final String TIPO_RESULTADO_INCORRECTOS                    = "INCORRECTOS";
	//
	public static final String LETRA_EQUIS                                   = "X";
	public static final String LETRA_ENIE                                    = "Ñ";
	public static final String LETRA_ENE                                     = "N";	
	//
	public static final String ESPACIO_EN_BLANCO                             = " ";
	public static final String CARACTER_PUNTO                                = ".";	
	//
	public static final String SEPARADOR_FECHA                               = "-";	
	//	
	public static final String CRITERIO_COMPLETO_DNI_NAF                     = "DNI_NIE#NAF";
	//	
	public static final int FILA_INICIAL_EXCEL                               = 1;
	public static final int COLUMNA_INICIAL_EXCEL                            = 2;
	//
	//
	//
	//
	//
	public static final int PRIMERA_LETRA_DIFERENTE                          = 0;
	public static final int ULTIMA_LETRA_DIFERENTE                           = 0;
	//
	public static final int PRIMERA_LETRA_IGUAL                              = 1;
	public static final int ULTIMA_LETRA_IGUAL                               = 1;
	//
	public static final int RESULTADO_COMPARACION_NOMBRE_NO_IDENTIFICADOS    = 0;
	public static final int RESULTADO_COMPARACION_NOMBRE_IDENTIFICADOS       = 1;
	public static final int RESULTADO_COMPARACION_NOMBRE_VACIOS              = 2;
	public static final int RESULTADO_COMPARACION_NOMBRE_DIFERENTES          = 3;
	
	public static final int INCLUSION_CADENA_APROX_ESTUDIO_NOMBRE            = 1;
	
	public static final String RESUL_STR_COMPARACION_NOMBRE_NO_IDENTIFICADOS = "REVISAR";
	public static final String RESUL_STR_COMPARACION_NOMBRE_IDENTIFICADOS    = "IDENTIFICADOS";
	public static final String RESUL_STR_COMPARACION_NOMBRE_VACIOS           = "IDENTIFICADOS - REVISION";
	public static final String RESUL_STR_COMPARACION_NOMBRE_DIFERENTES       = "DIFERENTES";
	
}
