package sns.comun.config;

import org.apache.log4j.Logger;

public class QuerysDescargas {

	private static Logger logger=Logger.getLogger("crucesPoblacionalesLog");

	protected String cabecera = "";
	
	protected boolean soloConNombresEnCriterio = true;
	
	protected String tabla   =   "Z_Z_Z_CRUCE_BEN_NO_ENC"; //"Z_Z_Z_CRUCE_BEN"; // = "CRUCE_CA_SNS_INSS"; // "CRUCE_CA_SNS_INSS_BENEF";//
	
	public QuerysDescargas (){
		StringBuffer sbQuery = new StringBuffer();
		sbQuery.append(" ID_SSALUD, ");
		sbQuery.append(" DNI_NIE, ");
		sbQuery.append(" PASAPORTE, ");
		sbQuery.append(" NAF, ");
		sbQuery.append(" NAF_TITULAR, ");
		sbQuery.append(" NOMBRE, ");
		sbQuery.append(" APELLIDO1, ");
		sbQuery.append(" APELLIDO2, ");
		sbQuery.append(" SEXO, ");
		sbQuery.append(" to_char(FECHA_NAC, 'yyyy-mm-dd') as FECHA_NAC, ");
		//
		sbQuery.append(" COD_USUARIO_SNS_BUS, ");
		sbQuery.append(" ID_EN_SSALUD_BUS, ");
		sbQuery.append(" COD_ESTADO_BUS, ");
		sbQuery.append(" COD_CA_ISO_BUS, ");
		sbQuery.append(" to_char(FECHA_ULT_ACTUALIZACION_BUS, 'yyyy-mm-dd') as FECHA_ULT_ACTUALIZACION_BUS, ");
		sbQuery.append(" NOMBRE_BUS, ");
		sbQuery.append(" APELLIDO1_BUS, ");
		sbQuery.append(" APELLIDO2_BUS, ");
		sbQuery.append(" COD_SEXO_BUS, ");
		sbQuery.append(" to_char(FECHA_NAC_BUS, 'yyyy-mm-dd') as FECHA_NAC_BUS, ");
		sbQuery.append(" RAIZ_BUS, ");
		sbQuery.append(" DNI_NIE_BUS, ");
		sbQuery.append(" PASAPORTE_BUS, ");
		sbQuery.append(" NAF_BUS, ");
		sbQuery.append(" NAF_TITULAR_BUS, ");
		sbQuery.append(" COD_TITULO_BUS, ");
		sbQuery.append(" COD_INDICADOR_DE_FARMACIA ,");
		sbQuery.append(" COD_SUBINDICADOR_DE_FARMACIA, ");
		sbQuery.append(" CRITERIO  ");
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		StringBuffer sbQueryPedro = new StringBuffer();
		sbQueryPedro.append(" COD_USUARIO_SNS,  ");
		sbQueryPedro.append(" DNI_NIE,  ");
		sbQueryPedro.append(" PASAPORTE,  ");
		sbQueryPedro.append(" NAF,  ");
		sbQueryPedro.append(" NAF_TITULAR,  ");
		sbQueryPedro.append(" NOMBRE,  ");
		sbQueryPedro.append(" APELLIDO1,  ");
		sbQueryPedro.append(" APELLIDO2,  ");
		sbQueryPedro.append(" SEXO,  ");
		sbQueryPedro.append(" FECHA_NAC,  ");
		sbQueryPedro.append(" ID_SSALUD,  ");
		sbQueryPedro.append(" CODIGO_TITULO,  ");
		sbQueryPedro.append(" COD_USUARIO_SNS_BUS,  ");
		sbQueryPedro.append(" COD_ESTADO_BUS,  ");
		sbQueryPedro.append(" ID_EN_SSALUD_BUS,  ");
		sbQueryPedro.append(" COD_CA_ISO_BUS,  ");
		sbQueryPedro.append(" FECHA_ULT_ACTUALIZACION_BUS,  ");
		sbQueryPedro.append(" NOMBRE_BUS,  ");
		sbQueryPedro.append(" APELLIDO1_BUS,  ");
		sbQueryPedro.append(" APELLIDO2_BUS,  ");
		sbQueryPedro.append(" COD_SEXO_BUS,  ");
		sbQueryPedro.append(" FECHA_NAC_BUS,  ");
		sbQueryPedro.append(" RAIZ_BUS,  ");
		sbQueryPedro.append(" DNI_NIE_BUS,  ");
		sbQueryPedro.append(" PASAPORTE_BUS,  ");
		sbQueryPedro.append(" NAF_BUS,  ");
		sbQueryPedro.append(" NAF_TITULAR_BUS,  ");
		sbQueryPedro.append(" COD_TITULO_BUS,  ");
		sbQueryPedro.append(" COD_INDICADOR_DE_FARMACIA,  ");
		sbQueryPedro.append(" COD_SUBINDICADOR_DE_FARMACIA,  ");
		sbQueryPedro.append(" COD_TIPO_PROCEDENCIA,  ");
		sbQueryPedro.append(" CAMPOSCOINCIDENTES,  ");
		sbQueryPedro.append(" DIFERENCIA,  ");
		sbQueryPedro.append(" CAMPOSIGUALESAPROXIMACION,  ");
		sbQueryPedro.append(" CRITERIO,  ");
		sbQueryPedro.append(" PORCENTAJE,  ");
		sbQueryPedro.append(" CRITERIO_COMPLETO,  ");
		sbQueryPedro.append(" PORCENTAJE_COMPLETO,  ");
		sbQueryPedro.append(" REVISION_NOMBRE,  ");
		sbQueryPedro.append(" TIPOCAMPO,  ");
		sbQueryPedro.append(" TIPORESULTADO,  ");
		sbQueryPedro.append(" ID_INSS,  ");
		sbQueryPedro.append(" COD_TIPO_ASEGURADO,  ");
		sbQueryPedro.append(" IPF,  ");
		sbQueryPedro.append(" DNI_NIE_INSS,  ");
		sbQueryPedro.append(" PASAPORTE_INSS,  ");
		sbQueryPedro.append(" NAF_INSS,  ");
		sbQueryPedro.append(" NAF_SEC1,  ");
		sbQueryPedro.append(" NAF_SEC2,  ");
		sbQueryPedro.append(" NAF_SEC3,  ");
		sbQueryPedro.append(" NAF_SEC4,  ");
		sbQueryPedro.append(" NAF_SEC5,  ");
		sbQueryPedro.append(" NAF_SEC6,  ");
		sbQueryPedro.append(" NAF_SEC7,  ");
		sbQueryPedro.append(" NAF_SEC8,  ");
		sbQueryPedro.append(" NAF_SEC9,  ");
		sbQueryPedro.append(" NOMBRE_INSS,  ");
		sbQueryPedro.append(" APELLIDO1_INSS,  ");
		sbQueryPedro.append(" APELLIDO2_INSS,  ");
		sbQueryPedro.append(" SEXO_INSS,  ");
		sbQueryPedro.append(" TIPO_ASEGURAMIENTO,  ");
		sbQueryPedro.append(" COD_INDICADOR_INSS,  ");
		sbQueryPedro.append(" COD_SUBINDICADOR_INSS,  ");
		sbQueryPedro.append(" COD_SITUACION,  ");
		sbQueryPedro.append(" FECHA_EFECTO_SITUACION,  ");
		sbQueryPedro.append(" FECHA_NACIMIENTO_RAW,  ");
		sbQueryPedro.append(" COD_USUARIO_SNS_INSS,  ");
		sbQueryPedro.append(" CRITERIO_IDENTIFICACION_SNS,  ");
		sbQueryPedro.append(" CAMPOSCOINCIDENTES_INSS,  ");
		sbQueryPedro.append(" DIFERENCIA_INSS,  ");
		sbQueryPedro.append(" CAMPOSIGUALESAPROX_INSS,  ");
		sbQueryPedro.append(" CRITERIO_INSS,  ");
		sbQueryPedro.append(" PORCENTAJE_INSS,  ");
		sbQueryPedro.append(" CRITERIO_COMPLETO_INSS,  ");
		sbQueryPedro.append(" PORCENTAJE_COMPLETO_INSS,  ");
		sbQueryPedro.append(" REVISION_NOMBRE_INSS,  ");
		sbQueryPedro.append(" TIPORESULTADO_INSS,  ");
		sbQueryPedro.append(" INDEF  ");
		//
		cabecera = sbQueryPedro.toString();
		//
		//cabecera = sbQuery.toString();
		
		/////////////////////////////////////////////////////////  Se incluyen los nombres en el resultado
		soloConNombresEnCriterio = true;
		
    }
	
	
	public String getQueryCoincidentesId () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();	
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		and         tipocampo     = 'IDSSALUD'            ");
			sbQuery.append(" 		AND         INDEF         = 0             ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public String getQueryCoincidentesMismaCA_CodSns () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'CODSNS'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//	
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	public String getQueryCoincidentesMismaCA_Dni () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'DNI_NIE'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	public String getQueryCoincidentesMismaCA_Naf () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			//
			// 1
			/*
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'NAF'  ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");		
			*/
			//
			sbQuery.append(" SELECT      C.*  ");	
			sbQuery.append(" FROM       " + tabla + "   c  ");	
			sbQuery.append(" where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'  ");	
			sbQuery.append(" AND         tipocampo            = 'NAF'    ");	
			sbQuery.append(" AND         INDEF                = 0   ");	
			sbQuery.append(" and         C.CAMPOSCOINCIDENTES > 3  ");	
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			sbQuery.append(" and         not exists (  ");	
			sbQuery.append("                         SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "   z    ");	                                                                                            
			sbQuery.append("                         where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                         and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                         aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                         AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                         and     Z.ID                            = C.ID     ");	
			sbQuery.append("                        )   ");	
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesMismaCA_Raiz () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			/*
			// 1
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			*/
			
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" AND         tipocampo            = 'NAFT_RAIZ' ");
			sbQuery.append(" AND         INDEF                = 0 ");
			sbQuery.append(" and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			sbQuery.append(" and         not exists (  ");	
			sbQuery.append("                         SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "   z    ");	                                                                                            
			sbQuery.append("                         where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                         and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                         aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                         AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                         and     Z.ID                            = C.ID     ");	
			sbQuery.append("                        )   ");	
			sbQuery.append(" 		AND         rownum              < 2001 ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesDiferenteCA_CodSns () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'CODSNS'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//sbQuery.append(" 		AND         C.NAF_BUS            = C.NAF_TITULAR_BUS ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	public String getQueryCoincidentesDiferenteCA_Dni () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'DNI_NIE'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//sbQuery.append(" 		AND         C.NAF_BUS            = C.NAF_TITULAR_BUS ");
			//
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	public String getQueryCoincidentesDiferenteCA_Naf () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			/*
			// 1
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'NAF'  ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//sbQuery.append(" 		AND         C.NAF_BUS            = C.NAF_TITULAR_BUS ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			*/
			
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'NAF'  ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "   z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesDiferenteCA_Raiz () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			/*
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			//
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			*/
			
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'NAFT_RAIZ' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	

	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	public String getQueryNoCoincidentesBACodSns () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" WHERE       tiporesultado          = 'NO_COINCIDENTES_BA' ");
			sbQuery.append(" AND         INDEF                  = 0 ");
			sbQuery.append(" AND         tipocampo              = 'CODSNS' ");
			sbQuery.append(" 		AND         rownum              < 2001 ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	public String getQueryDniNafIguales () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       C.TIPORESULTADO = 'NO_COINCIDENTES_DNINAF_BA'");
			sbQuery.append(" 		AND         INDEF         = 0             ");
			//////////////sbQuery.append("        and         C.COD_USUARIO_SNS_BUS NOT IN  ('BBBBBBBBCB028254', 'BBBBBBBBDM586156', 'BBBBBBBBBB876919', 'BBBBBBBBBP171438', 'BBBBBBBBBW609846', 'BBBBBBBBCX084766', 'BBBBBBBBBY832550', 'BBBBBBBBCT979323', 'BBBBBBBBDK308388', 'BBBBBBBBBY112611', 'BBBBBBBBCW037860', 'BBBBBBBBCC094086', 'BBBBBBBBCT748108', 'BBBBBBBBBG409401', 'BBBBBBBBBX706248', 'BBBBBBBBCQ289340', 'BBBBBBBBCB231491', 'BBBBBBBBCB730046', 'BBBBBBBBCW384946', 'BBBBBBBBCQ737248', 'BBBBBBBBCX077881', 'BBBBBBBBCB436721', 'BBBBBBBBCV756001', 'BBBBBBBBBX631563', 'BBBBBBBBCG725874', 'BBBBBBBBCW461502', 'BBBBBBBBDK252911', 'BBBBBBBBCG626812', 'BBBBBBBBBD723601', 'BBBBBBBBCQ161657', 'BBBBBBBBCB727711', 'BBBBBBBBBX487515', 'BBBBBBBBDG613239', 'BBBBBBBBCX301628', 'BBBBBBBBDG492906', 'BBBBBBBBCB396357', 'BBBBBBBBCG952638', 'BBBBBBBBCH395995', 'BBBBBBBBCT005749', 'BBBBBBBBBY075054', 'BBBBBBBBCS114785', 'BBBBBBBBDL682495', 'BBBBBBBBCW971561', 'BBBBBBBBBY030241', 'BBBBBBBBCW884039', 'BBBBBBBBDF629386', 'BBBBBBBBBV339764', 'BBBBBBBBCJ130010', 'BBBBBBBBCW667908', 'BBBBBBBBCC179333', 'BBBBBBBBCQ104965', 'BBBBBBBBCT649819', 'BBBBBBBBCW805624', 'BBBBBBBBCV360273', 'BBBBBBBBCS395411', 'BBBBBBBBCB931404', 'BBBBBBBBCW200167', 'BBBBBBBBCV052639', 'BBBBBBBBBX168246', 'BBBBBBBBCB684785', 'BBBBBBBBBX781652', 'BBBBBBBBDG488473', 'BBBBBBBBBW972156', 'BBBBBBBBCW839317', 'BBBBBBBBCW859630', 'BBBBBBBBDB840037', 'BBBBBBBBCG961523', 'BBBBBBBBBV196602', 'BBBBBBBBCV140130', 'BBBBBBBBCD824436', 'BBBBBBBBBP463956', 'BBBBBBBBDG489809', 'BBBBBBBBBX596216', 'BBBBBBBBBX529780', 'BBBBBBBBBS218982', 'BBBBBBBBCB232945', 'BBBBBBBBCY911584', 'BBBBBBBBBD446947', 'BBBBBBBBBZ954704', 'BBBBBBBBCV706155', 'BBBBBBBBCC918651', 'BBBBBBBBBV119963', 'BBBBBBBBBK815893', 'BBBBBBBBBJ938154', 'BBBBBBBBDC122630', 'BBBBBBBBBM051679', 'BBBBBBBBCV662513', 'BBBBBBBBBK731486', 'BBBBBBBBBN907532') ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	// Ya no se utiliza 
	public String getQueryNoCoincidentesInssVinculados () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");                                           
	        sbQuery.append(" 		where       tiporesultado                  = 'NO_COINCIDENTES_BA'     ");                                                         
	        sbQuery.append(" 		and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'    ");                                                  
	        sbQuery.append(" 		and         C.CRITERIO_IDENTIFICACION_SNS  is not null   ");                                                                      
	        sbQuery.append(" 		AND         INDEF                          = 0     ");    
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");
			//       
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryNoCoincidentesInssVinculadosDni () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");                                           
	        sbQuery.append(" 		where       tiporesultado                  = 'NO_COINCIDENTES_BA'     ");
	        sbQuery.append(" 		and         tipocampo                      = 'DNI_NIE'   ");
	        sbQuery.append(" 		and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'    ");                                                  
	        sbQuery.append(" 		and         C.CRITERIO_IDENTIFICACION_SNS  is not null   ");                                                                      
	        sbQuery.append(" 		AND         INDEF                          = 0     ");    
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			//     
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryNoCoincidentesInssVinculadosOtros () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			/*
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");                                           
	        sbQuery.append(" 		where       tiporesultado                  = 'NO_COINCIDENTES_BA'     ");       
	        sbQuery.append(" 		and         tipocampo                      <> 'DNI_NIE'   ");
	        sbQuery.append(" 		and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'    ");                                                  
	        sbQuery.append(" 		and         C.CRITERIO_IDENTIFICACION_SNS  is not null   ");                                                                      
	        sbQuery.append(" 		AND         INDEF                          = 0     ");    
	        //sbQuery.append(" 		and         C.CRITERIO    like '%NOMBRE%'  ");       
	        */
	        
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");                                           
	        sbQuery.append(" 		where       tiporesultado                  = 'NO_COINCIDENTES_BA'     ");       
	        sbQuery.append(" 		and         tipocampo                      <> 'DNI_NIE'   ");
	        sbQuery.append(" 		and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'    ");                                                  
	        sbQuery.append(" 		and         C.CRITERIO_IDENTIFICACION_SNS  is not null   ");                                                                      
	        sbQuery.append(" 		AND         INDEF                          = 0     ");    
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			// 
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesMismaCA () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo           <> 'IDSSALUD'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}		
	

	public String getQueryCoincidentesDiferenteCA () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	

	public String getQueryNoCoincidentesBADni () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" WHERE       tiporesultado          = 'NO_COINCIDENTES_BA' ");
			sbQuery.append(" AND         INDEF                  = 0 ");
			sbQuery.append(" AND         tipocampo              = 'DNI_NIE' ");
			sbQuery.append(" AND         C.TIPORESULTADO_INSS   in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS') ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryNoCoincidentesBAOtros () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'NO_COINCIDENTES_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ'    ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryIndiceDef () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       INDEF  = 1");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	

	public String getQueryDuplicados () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado       = 'DUPLICADOS' ");
			sbQuery.append(" 		AND         INDEF               = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}		
	
	public String getQueryNoEncontrados () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado       = 'NO_ENCONTRADOS' ");
			sbQuery.append(" 		AND         INDEF               = 0 ");
			sbQuery.append(" 		AND         rownum              < 50001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	public String getQueryVacios () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado       = 'VACIOS' ");
			sbQuery.append(" 		AND         INDEF               = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	
	public String getQueryRevisarNombreDatos () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where    C.PORCENTAJE         > 69 ");
			sbQuery.append(" 		and      C.CAMPOSCOINCIDENTES > 3 ");
			sbQuery.append(" 		and      C.CRITERIO  not like '%NOMBRE%' ");
			sbQuery.append(" 		AND      TIPORESULTADO NOT LIKE '%DUPLICADO%' "); 
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}		

	
	public String getQueryRevisarNombreDniNaf () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       C.TIPORESULTADO        = 'NO_COINCIDENTES_DNINAF_BA'  ");                                                          
			sbQuery.append(" 		AND         INDEF                  = 0 ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			// 
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}		
	
	public String getQueryRevisarNombreVinculados () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");                                                                                             
			sbQuery.append(" 		where       tiporesultado                  = 'NO_COINCIDENTES_BA' ");                                                       
			sbQuery.append(" 		and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA' ");                            
			sbQuery.append(" 		and         INDEF                          = 0   ");
			sbQuery.append(" 		and         (camposcoincidentes < 4 or  CRITERIO is null OR CRITERIO not  like '%NOMBRE%') ");
			sbQuery.append(" 		AND         rownum              < 2001 ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public String getQueryCoincidentes_NaftTitular_Nombre () throws Exception {
		String query = "";
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'NAFT_NOMBRE' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			/*
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			//sbQuery.append(" 		AND         rownum              < 2001 ");
			 * 			
			 */
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentes_Raiz_Nombre () throws Exception {
		String query = "";
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ_NOMBRE' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			/*
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			//sbQuery.append(" 		AND         rownum              < 2001 ");
			*/
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}
	
	
	public String getQueryCoincidentes_Raiz_Apellidos () throws Exception {
		String query = "";
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ_APELLIDOS' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			/*
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			//sbQuery.append(" 		AND         rownum              < 2001 ");		
			*/
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}
	
	
	public String getQueryCoincidentes_Raiz_Apellido1 () throws Exception {
		String query = "";
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'OTROS' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			//
			/*
			sbQuery.append(" and    not exists (  ");	
			sbQuery.append("                    SELECT  'x'      ");	                                     
			sbQuery.append(" 		FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                    where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");		
			sbQuery.append("                    aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                    AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                    and     Z.ID                            = C.ID     ");	
			sbQuery.append("                    )   ");	
			//sbQuery.append(" 		AND         rownum              < 2001 ");			
			 */
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}
	
	
	
	
	
		
}
