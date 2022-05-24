package sns.comun.config;

import org.apache.log4j.Logger;

public class QuerysDescargasRevisionCA extends QuerysDescargas {

	private static Logger logger=Logger.getLogger("crucesPoblacionalesLog");

	
	public QuerysDescargasRevisionCA (){
		super();
    }
	
	
	public String getQueryCoincidentesMismaCA_Naf_Revision () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'  ");	
			sbQuery.append(" AND         tipocampo            = 'NAF'    ");	
			sbQuery.append(" AND         INDEF                = 0   ");	
			sbQuery.append(" and         C.CAMPOSCOINCIDENTES > 3  ");	
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" and         exists (  ");	
			sbQuery.append("                         SELECT  'x'      ");	                                     
			sbQuery.append(" 		                 FROM       " + tabla + "   z    ");	                                                                                            
			sbQuery.append("                         where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                         and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                         aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                         AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                         and     Z.ID                            = C.ID     ");	
			sbQuery.append("                        )   ");			
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesMismaCA_Raiz_Revision () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     " + cabecera);
			sbQuery.append(" 		FROM       " + tabla + " C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'RAIZ' ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			sbQuery.append(" 		and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" and         exists (  ");	
			sbQuery.append("                         SELECT  'x'      ");	                                     
			sbQuery.append(" 		                 FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                         where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                         and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                         aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                         AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                         and     Z.ID                            = C.ID     ");	
			sbQuery.append("                        )   ");	
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
	
	public String getQueryCoincidentesDiferenteCA_Naf_Revision () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" AND         tipocampo            = 'NAF'  ");
			sbQuery.append(" AND         INDEF                = 0 ");
			sbQuery.append(" And         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" and         exists (  ");	
			sbQuery.append("                     SELECT  'x'      ");	                                     
			sbQuery.append(" 		             FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                     where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                     and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                     aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                     AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                     and     Z.ID                            = C.ID     ");	
			sbQuery.append("                     )   ");				
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
	
		
	public String getQueryCoincidentesDiferenteCA_Raiz_Revision () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");
			sbQuery.append(" where       tiporesultado        = 'COINCIDENTES_DIFERENTE_CA_BA'");
			sbQuery.append(" AND         tipocampo            = 'RAIZ' ");
			sbQuery.append(" AND         INDEF                = 0 ");
			sbQuery.append(" and         C.CAMPOSCOINCIDENTES > 3  ");
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}
			sbQuery.append(" and         exists (  ");	
			sbQuery.append("                     SELECT  'x'      ");	                                     
			sbQuery.append(" 	                 FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                     where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                     and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                     aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                     AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                     and     Z.ID                            = C.ID     ");	
			sbQuery.append("                     )   ");	
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	
		
	
	public String getQueryNoCoincidentesInssVinculadosOtros_Revision () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" SELECT     " + cabecera);
			sbQuery.append(" FROM       " + tabla + " C  ");                                           
	        sbQuery.append(" where       tiporesultado                  = 'NO_COINCIDENTES_BA'     ");       
	        sbQuery.append(" and         tipocampo                      <> 'DNI_NIE'   ");
	        sbQuery.append(" and         tiporesultado_inss             = 'COINCIDENTES_VINCULADOS_BA'    ");                                                  
	        sbQuery.append(" and         C.CRITERIO_IDENTIFICACION_SNS  is not null   ");                                                                      
	        sbQuery.append(" AND         INDEF                          = 0     "); 
			sbQuery.append(" and         exists (  ");	
			sbQuery.append("                     SELECT  'x'      ");	                                     
			sbQuery.append(" 		             FROM       " + tabla + "  z    ");	                                                                                            
			sbQuery.append("                     where   z.tiporesultado                 = 'NO_COINCIDENTES_BA'   ");	
			sbQuery.append("                     and     z.tipocampo                     = 'DNI_NIE'  ");	
			sbQuery.append("                     aND     z.INDEF                         = 0    ");	    
			sbQuery.append("                     AND     z.TIPORESULTADO_INSS            in ('COINCIDENTES_NO_VINCULADOS_BA', 'DUPLICADOS', 'NO_COINCIDENTES_BA', 'NO_ENCONTRADOS')  ");	
			sbQuery.append("                     and     Z.ID                            = C.ID     ");	
			sbQuery.append("                     )   ");		        
			//
			if(soloConNombresEnCriterio) {
				sbQuery.append(" 		and         C.CRITERIO  like '%NOMBRE#%'  ");
			}      
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	



}
