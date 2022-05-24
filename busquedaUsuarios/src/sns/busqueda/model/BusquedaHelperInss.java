package sns.busqueda.model;

import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBd;
import sns.comun.bd.AccesoBdConsulta;
import sns.comun.bean.InssBean;
import sns.comun.bean.ResultadosCampoBean;
import sns.comun.bean.SnsBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import gasai.util.Misc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;


public class BusquedaHelperInss extends BusquedaHelper{

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public void managerBBDD(String path, String ficheroDestino) throws Exception {
		//
		int contadorRegistros = 0;
		//
		AccesoBd accesoBd                 = null;
		//
		FileWriter fileWriter          = null;
		//
		LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda = new LinkedHashMap <String, ResultadosCampoBean> ();
		//
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd         = new AccesoBd();
			//
			fileWriter     = new FileWriter(path + ficheroDestino);
			//
			//
			// Se Inicializa el map para los resultados y para los campos de consulta
			//
            Set <String> setCamposBusqueda           = InicializacionBusqueda.MAP_CAMPO.keySet();
            Iterator <String> iteratorCamposBusqueda = setCamposBusqueda.iterator();
            //           
            while (iteratorCamposBusqueda.hasNext()) {
            	String camposBusqueda  = (String) iteratorCamposBusqueda.next();
                //
                if(InicializacionBusqueda.MAP_CAMPOS_BUSQUEDA_INSS.containsKey(camposBusqueda)) {
                	mapCamposBusqueda.put(camposBusqueda, new ResultadosCampoBean());
                	logger.debug("camposBusqueda: " + camposBusqueda);
                }   
            }
			//
            /*
			sbQuery.append(" select COD_USUARIO_SNS, ");
			sbQuery.append("        COD_ESTADO_BUS    COD_ESTADO, ");
			sbQuery.append("        ID_EN_SSALUD_BUS  ID_EN_SSALUD, ");
			sbQuery.append("        COD_CA_ISO_BUS    CODCAISO, ");
			sbQuery.append("        NOMBRE_BUS        NOMBRE, ");
			sbQuery.append("        APELLIDO1_BUS,     APELLIDO1, ");
			sbQuery.append("        APELLIDO2_BUS     APELLIDO2, ");
			sbQuery.append("        COD_SEXO_BUS      COD_SEXO, ");
			sbQuery.append("        TO_CHAR(FECHA_NAC_BUS, 'yyyy-mm-dd') FECHA_NAC, ");					
			sbQuery.append("        RAIZ_BUS          RAIZ, ");
			sbQuery.append("        DNI_NIE_BUS       DNI_NIE, ");
			sbQuery.append("        PASAPORTE_BUS     PASAPORTE, ");
			sbQuery.append("        NAF_BUS           NAF, ");
			sbQuery.append("        NAF_TITULAR_BUS   NAF_TITULAR, ");
			sbQuery.append("        COD_TITULO_BUS    COD_TITULO, ");
			sbQuery.append("        COD_SITUACION_BUS COD_SITUACION, ");
			sbQuery.append("        COD_INDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_SUBINDICADOR_DE_FARMACIA, ");
			sbQuery.append("        COD_TIPO_PROCEDENCIA ");
			sbQuery.append(" from   CRUCE_PAIS_VASCO_NUEVO    ");
			*/
            /*
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select      u.COD_USUARIO_SNS,  ");
            stringBuffer.append("             (SELECT COD_CA_ISO FROM   snsalud.CA_PRESTACION_SERVICIO cps, snsalud.COMUNIDADES_AUTONOMAS ca WHERE  u.COD_PRESTACION_SERVICIO = cps.COD_PRESTACION_SERVICIO  AND cps.COD_COMUNIDAD = ca.COD_COMUNIDAD) CODCAISO,  ");
            stringBuffer.append("             u.COD_ESTADO,  ");
            stringBuffer.append("             iu.ID_EN_SSALUD,  ");
            stringBuffer.append("             dp.DNI_NIE,  ");
            stringBuffer.append("             dp.PASAPORTE,  ");
            stringBuffer.append("             dp.NOMBRE,  ");
            stringBuffer.append("             dp.APELLIDO1,  ");
            stringBuffer.append("             dp.APELLIDO2,  ");
            stringBuffer.append("             dc.NAF,  ");
            stringBuffer.append("             dc.NAF_TITULAR,  ");
            stringBuffer.append("             dp.COD_SEXO,  ");
            stringBuffer.append("             to_char(dp.FECHA_NAC, 'yyyy-mm-dd') FECHA_NAC, ");
            stringBuffer.append("             dc.COD_TITULO,  ");
            stringBuffer.append("             dc.COD_SITUACION,  ");
            stringBuffer.append("             dp.RAIZ,  ");
            stringBuffer.append("             df.COD_INDICADOR_DE_FARMACIA,  ");
            stringBuffer.append("             df.COD_SUBINDICADOR_DE_FARMACIA,  ");
            stringBuffer.append("             df.COD_TIPO_PROCEDENCIA  ");
            stringBuffer.append(" from        Z_TITULOS_ANTIGUOS_3    ta, ");
            stringBuffer.append("             datos_cobertura         dc, ");
            stringBuffer.append("             usuarios                 u,   ");
            stringBuffer.append("             datos_personales        dp,    ");
            stringBuffer.append("             datos_farmacia          df,   ");
            stringBuffer.append("             identificadores_usuario iu,   ");
            stringBuffer.append("             desglose_agentes        da           ");
            stringBuffer.append(" where       ta.COD_USUARIO_SNS          = dc.COD_USUARIO_SNS  ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = u.COD_USUARIO_SNS  ");
            stringBuffer.append(" and         u.COD_ESTADO                = 0  ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = dp.COD_USUARIO_SNS             ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = df.COD_USUARIO_SNS     ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = iu.COD_USUARIO_SNS  ");
            stringBuffer.append(" and         U.COD_PRESTACION_SERVICIO   = DA.COD_PRESTACION_SERVICIO   ");
            stringBuffer.append(" and         DA.COD_AGENTE               = IU.COD_AGENTE  ");
            stringBuffer.append(" order by    dc.COD_TITULO  ");
            */
            
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" select      u.COD_USUARIO_SNS,   ");
            stringBuffer.append("             (SELECT COD_CA_ISO FROM   snsalud.CA_PRESTACION_SERVICIO cps, snsalud.COMUNIDADES_AUTONOMAS ca WHERE  u.COD_PRESTACION_SERVICIO = cps.COD_PRESTACION_SERVICIO  AND cps.COD_COMUNIDAD = ca.COD_COMUNIDAD) CODCAISO,   ");
            stringBuffer.append("             u.COD_ESTADO,   ");
            stringBuffer.append("             iu.ID_EN_SSALUD,   ");
            stringBuffer.append("             dp.DNI_NIE, dp.PASAPORTE,   ");
            stringBuffer.append("             dp.NOMBRE, dp.APELLIDO1, dp.APELLIDO2,   ");
            stringBuffer.append("             dc.NAF, dc.NAF_TITULAR,   ");
            stringBuffer.append("             dp.COD_SEXO, to_char(dp.FECHA_NAC, 'yyyy-mm-dd') FECHA_NAC,  ");
            stringBuffer.append("             dc.COD_TITULO, dc.COD_SITUACION,   ");
            stringBuffer.append("             dp.RAIZ, df.COD_INDICADOR_DE_FARMACIA, df.COD_SUBINDICADOR_DE_FARMACIA, df.COD_TIPO_PROCEDENCIA   ");
            stringBuffer.append(" from        datos_farmacia          df,   ");
            stringBuffer.append("             usuarios                 u,  ");
            stringBuffer.append("             datos_cobertura         dc,  ");
            stringBuffer.append("             datos_personales        dp,       ");
            stringBuffer.append("             identificadores_usuario iu,    ");
            stringBuffer.append("             desglose_agentes        da            ");
            stringBuffer.append(" where       DF.COD_TIPO_PROCEDENCIA     = 5  ");
            stringBuffer.append(" and         df.COD_USUARIO_SNS          = u.COD_USUARIO_SNS  ");
            stringBuffer.append(" and         u.COD_ESTADO                = 0   ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = u.COD_USUARIO_SNS   ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = dp.COD_USUARIO_SNS                ");
            stringBuffer.append(" and         dc.COD_USUARIO_SNS          = iu.COD_USUARIO_SNS   ");
            stringBuffer.append(" and         U.COD_PRESTACION_SERVICIO   = DA.COD_PRESTACION_SERVICIO    ");
            stringBuffer.append(" and         DA.COD_AGENTE               = IU.COD_AGENTE   ");
                      
            
            /*
            stringBuffer.append("  select     z.COD_USUARIO_SNS,   ");
            stringBuffer.append("             z.CODCAISO,   ");
            stringBuffer.append("             z.COD_ESTADO,   ");
            stringBuffer.append("             z.ID_EN_SSALUD, ");
            stringBuffer.append("             z.DNI_NIE,   ");
            stringBuffer.append("             z.PASAPORTE,   ");
            stringBuffer.append("             z.NOMBRE,   ");
            stringBuffer.append("             z.APELLIDO1,   ");
            stringBuffer.append("             z.APELLIDO2,   ");
            stringBuffer.append("             z.NAF,   ");
            stringBuffer.append("             z.NAF_TITULAR,   ");
            stringBuffer.append("             z.COD_SEXO,   ");
            stringBuffer.append("             z.FECHA_NAC,  ");
            stringBuffer.append("             z.COD_TITULO,   ");
            stringBuffer.append("             z.COD_SITUACION,   ");
            stringBuffer.append("             z.RAIZ,   ");
            stringBuffer.append("             z.COD_INDICADOR_DE_FARMACIA,   ");
            stringBuffer.append("             z.COD_SUBINDICADOR_DE_FARMACIA,   ");
            stringBuffer.append("             z.COD_TIPO_PROCEDENCIA   ");
            stringBuffer.append(" from        Z_TITULOS_ANTIGUOS_2 z  ");
            //stringBuffer.append(" where       z.cod_usuario_sns = 'BBBBBBBBBB096172' ");
             /*
            stringBuffer.append(" select U.COD_USUARIO_SNS,  ");
            stringBuffer.append("        CA.COD_CA_ISO CODCAISO,  ");
            stringBuffer.append("        U.COD_ESTADO,  ");
            stringBuffer.append("        IU.ID_EN_SSALUD,  ");
            stringBuffer.append("        DP.DNI_NIE,  ");
            stringBuffer.append("        DP.PASAPORTE,  ");
            stringBuffer.append("        DP.NOMBRE,  ");
            stringBuffer.append("        DP.APELLIDO1,  ");
            stringBuffer.append("        DP.APELLIDO2, ");
            stringBuffer.append("        DC.NAF,  ");
            stringBuffer.append("        DC.NAF_TITULAR,  ");
            stringBuffer.append("        DP.COD_SEXO,  ");
            stringBuffer.append("        to_char(dp.FECHA_NAC, 'yyyy-mm-dd') FECHA_NAC,  ");
            stringBuffer.append("        DC.COD_TITULO,  ");
            stringBuffer.append("        '' AS COD_SITUACION,  ");
            stringBuffer.append("        DP.RAIZ,  ");
            stringBuffer.append("        DF.COD_INDICADOR_DE_FARMACIA, ");
            stringBuffer.append("        DF.COD_SUBINDICADOR_DE_FARMACIA,  ");
            stringBuffer.append("        DF.COD_TIPO_PROCEDENCIA ");
            stringBuffer.append(" from   snsalud.usuarios u,  ");
            stringBuffer.append("        snsalud.ca_prestacion_servicio cp,  ");
            stringBuffer.append("        SNSALUD.COMUNIDADES_AUTONOMAS ca,  ");
            stringBuffer.append("        snsalud.desglose_agentes da,  ");
            stringBuffer.append("        snsalud.identificadores_usuario iu,  ");
            stringBuffer.append("        snsalud.datos_personales dp,  ");
            stringBuffer.append("        snsalud.datos_cobertura dc, ");
            stringBuffer.append("        snsalud.datos_farmacia df ");
            stringBuffer.append(" where u.COD_USUARIO_SNS        in ('BBBBBBBBBD983090','BBBBBBBBCB252647','BBBBBBBBDH282119','BBBBBBBBDH576605','BBBBBBBBDJ376767','BBBBBBBBDJ763423','BBBBBBBBDH072310','BBBBBBBBDH746737','BBBBBBBBDL041849','BBBBBBBBDH561221','BBBBBBBBDJ376769','BBBBBBBBBY029239','BBBBBBBBDG916630','BBBBBBBBDH949544','BBBBBBBBDG783203','BBBBBBBBDG924396','BBBBBBBBDJ413486','BBBBBBBBDJ760914','BBBBBBBBDL044970','BBBBBBBBDH772860','BBBBBBBBDH794951','BBBBBBBBDJ763754','BBBBBBBBDJ677406','BBBBBBBBDJ764942','BBBBBBBBBC218506','BBBBBBBBBD141583','BBBBBBBBBD717260','BBBBBBBBBF377462','BBBBBBBBBF829078','BBBBBBBBBF914003','BBBBBBBBBP566559','BBBBBBBBBQ481943','BBBBBBBBBW497845','BBBBBBBBBZ847395','BBBBBBBBBZ870685','BBBBBBBBCB434595','BBBBBBBBCB899719','BBBBBBBBCC030996','BBBBBBBBCH045317','BBBBBBBBCL488209','BBBBBBBBCR900706','BBBBBBBBCV723351','BBBBBBBBCX636810','BBBBBBBBDF111182','BBBBBBBBDG772491','BBBBBBBBDG806130','BBBBBBBBDG844843','BBBBBBBBDG869846','BBBBBBBBDG876537','BBBBBBBBDG917777','BBBBBBBBDG936180','BBBBBBBBDG946031','BBBBBBBBDG978828','BBBBBBBBDG983273','BBBBBBBBDG984474','BBBBBBBBDH020560','BBBBBBBBDH032968','BBBBBBBBDH037377','BBBBBBBBDH049629','BBBBBBBBDH062133','BBBBBBBBDH063652','BBBBBBBBDH097852','BBBBBBBBDH102193','BBBBBBBBDH104909','BBBBBBBBDH124458','BBBBBBBBDH163391','BBBBBBBBDH194037','BBBBBBBBDH218228','BBBBBBBBDH228120','BBBBBBBBDH280891','BBBBBBBBDH300532','BBBBBBBBDH335402','BBBBBBBBDH350140','BBBBBBBBDH355000','BBBBBBBBDH363379','BBBBBBBBDH367574','BBBBBBBBDH381962','BBBBBBBBDH432015','BBBBBBBBDH498281','BBBBBBBBDH498833','BBBBBBBBDH510249','BBBBBBBBDH517959','BBBBBBBBDH540201','BBBBBBBBDH560433','BBBBBBBBDH562099','BBBBBBBBDH573635','BBBBBBBBDH602459','BBBBBBBBDH625119','BBBBBBBBDH629610','BBBBBBBBDH658270','BBBBBBBBDH665302','BBBBBBBBDH691946','BBBBBBBBDH699416','BBBBBBBBDH701004','BBBBBBBBDH749031','BBBBBBBBDH776258','BBBBBBBBDH779195','BBBBBBBBDH790436','BBBBBBBBDH791113','BBBBBBBBDH798295','BBBBBBBBDH807856','BBBBBBBBDH825033','BBBBBBBBDH829729','BBBBBBBBDH831137','BBBBBBBBDH838624','BBBBBBBBDH942369','BBBBBBBBDH947291','BBBBBBBBDH950059','BBBBBBBBDH955638','BBBBBBBBDH970696','BBBBBBBBDH974058','BBBBBBBBDH974992','BBBBBBBBDH975714','BBBBBBBBDH987689','BBBBBBBBDH987848','BBBBBBBBDH992096','BBBBBBBBDJ048682','BBBBBBBBDJ066598','BBBBBBBBDJ071030','BBBBBBBBDJ092735','BBBBBBBBDJ115486','BBBBBBBBDJ120362','BBBBBBBBDJ123284','BBBBBBBBDJ125732','BBBBBBBBDJ137537','BBBBBBBBDJ147448','BBBBBBBBDJ155518','BBBBBBBBDJ197772','BBBBBBBBDJ215241','BBBBBBBBDJ215723','BBBBBBBBDJ232581','BBBBBBBBDJ267824','BBBBBBBBDJ302146','BBBBBBBBDJ308623','BBBBBBBBDJ315784','BBBBBBBBDJ323591','BBBBBBBBDJ342541','BBBBBBBBDJ342955','BBBBBBBBDJ535560','BBBBBBBBDJ560835','BBBBBBBBDJ763177','BBBBBBBBDJ763481','BBBBBBBBDJ852467','BBBBBBBBBC347606','BBBBBBBBBG728241','BBBBBBBBBX675922','BBBBBBBBBX766644','BBBBBBBBBZ743640','BBBBBBBBCC166427','BBBBBBBBCC656602','BBBBBBBBCQ482421','BBBBBBBBCV729185','BBBBBBBBDG761736','BBBBBBBBDG795287','BBBBBBBBDG906492','BBBBBBBBDG908558','BBBBBBBBDH046418','BBBBBBBBDH093843','BBBBBBBBDH155122','BBBBBBBBDH168518','BBBBBBBBDH234130','BBBBBBBBDH234876','BBBBBBBBDH261519','BBBBBBBBDH443596','BBBBBBBBDH446614','BBBBBBBBDH454814','BBBBBBBBDH465111','BBBBBBBBDH504852','BBBBBBBBDH513263','BBBBBBBBDH527093','BBBBBBBBDH562739','BBBBBBBBDH619494','BBBBBBBBDH673019','BBBBBBBBDH679055','BBBBBBBBDH712974','BBBBBBBBDH726313','BBBBBBBBDH744106','BBBBBBBBDH744387','BBBBBBBBDH770384','BBBBBBBBDH847600','BBBBBBBBDH869425','BBBBBBBBDH923298','BBBBBBBBDH927241','BBBBBBBBDH965238','BBBBBBBBDH977645','BBBBBBBBDH984656','BBBBBBBBDH987265','BBBBBBBBDJ037992','BBBBBBBBDJ051951','BBBBBBBBDJ052404','BBBBBBBBDJ115388','BBBBBBBBDJ149930','BBBBBBBBDJ190600','BBBBBBBBDJ196521','BBBBBBBBDJ218477','BBBBBBBBDJ222465','BBBBBBBBDJ249909','BBBBBBBBDJ293840','BBBBBBBBDJ541738','BBBBBBBBDJ607428','BBBBBBBBDJ623174','BBBBBBBBDJ703171','BBBBBBBBDJ713535','BBBBBBBBDJ758051','BBBBBBBBDJ763913','BBBBBBBBDJ765013','BBBBBBBBDJ979367','BBBBBBBBDK163770','BBBBBBBBDK498123','BBBBBBBBDK859899','BBBBBBBBBF374300','BBBBBBBBBQ636658','BBBBBBBBCB262140','BBBBBBBBCB742545','BBBBBBBBCC009458','BBBBBBBBCC250402','BBBBBBBBCC770934','BBBBBBBBCS252251','BBBBBBBBCX189801','BBBBBBBBDG801340','BBBBBBBBDG830120','BBBBBBBBDG843065','BBBBBBBBDG882465','BBBBBBBBDG902879','BBBBBBBBDG921210','BBBBBBBBDG923491','BBBBBBBBDG923766','BBBBBBBBDG942903','BBBBBBBBDG991000','BBBBBBBBDH012712','BBBBBBBBDH021835','BBBBBBBBDH025866','BBBBBBBBDH038604','BBBBBBBBDH075407','BBBBBBBBDH077507','BBBBBBBBDH147905','BBBBBBBBDH169447','BBBBBBBBDH191128','BBBBBBBBDH205038','BBBBBBBBDH225399','BBBBBBBBDH269218','BBBBBBBBDH366207','BBBBBBBBDH369239','BBBBBBBBDH377060','BBBBBBBBDH408222','BBBBBBBBDH475958','BBBBBBBBDH483160','BBBBBBBBDH485470','BBBBBBBBDH489154','BBBBBBBBDH499447','BBBBBBBBDH559253','BBBBBBBBDH642616','BBBBBBBBDH660512','BBBBBBBBDH681794','BBBBBBBBDH702789','BBBBBBBBDH725936','BBBBBBBBDH727728','BBBBBBBBDH772481','BBBBBBBBDH776828','BBBBBBBBDH813400','BBBBBBBBDH828390','BBBBBBBBDH866852','BBBBBBBBDH894102','BBBBBBBBDH899508','BBBBBBBBDH902023','BBBBBBBBDH917984','BBBBBBBBDH934900','BBBBBBBBDH937452','BBBBBBBBDH956039','BBBBBBBBDJ013612','BBBBBBBBDJ037249','BBBBBBBBDJ043247','BBBBBBBBDJ068590','BBBBBBBBDJ089268','BBBBBBBBDJ108719','BBBBBBBBDJ116480','BBBBBBBBDJ164030','BBBBBBBBDJ167218','BBBBBBBBDJ177267','BBBBBBBBDJ185722','BBBBBBBBDJ202086','BBBBBBBBDJ227708','BBBBBBBBDJ240353','BBBBBBBBDJ255118','BBBBBBBBDJ263456','BBBBBBBBDJ305143','BBBBBBBBDJ307668','BBBBBBBBDJ345604','BBBBBBBBDJ367412','BBBBBBBBDJ385349','BBBBBBBBDJ427561','BBBBBBBBDJ429087','BBBBBBBBDJ489888','BBBBBBBBDJ531478','BBBBBBBBDJ532268','BBBBBBBBDJ534888','BBBBBBBBDJ553942','BBBBBBBBDJ759332','BBBBBBBBDJ759597','BBBBBBBBBP158821','BBBBBBBBBP380346','BBBBBBBBBP742419','BBBBBBBBBX013146','BBBBBBBBBX396333','BBBBBBBBBX756488','BBBBBBBBCB453336','BBBBBBBBCB688959','BBBBBBBBCB908862','BBBBBBBBCG645836','BBBBBBBBCG841757','BBBBBBBBCW323794','BBBBBBBBCW465264','BBBBBBBBCZ548987','BBBBBBBBDG768455','BBBBBBBBDG964495','BBBBBBBBDG999075','BBBBBBBBDH017811','BBBBBBBBDH041152','BBBBBBBBDH042300','BBBBBBBBDH048861','BBBBBBBBDH083990','BBBBBBBBDH087050','BBBBBBBBDH134453','BBBBBBBBDH161501','BBBBBBBBDH188980','BBBBBBBBDH211246','BBBBBBBBDH211316','BBBBBBBBDH249802','BBBBBBBBDH313650','BBBBBBBBDH326641','BBBBBBBBDH339339','BBBBBBBBDH368509','BBBBBBBBDH373475','BBBBBBBBDH384210','BBBBBBBBDH385689','BBBBBBBBDH402963','BBBBBBBBDH442512','BBBBBBBBDH460110','BBBBBBBBDH476618','BBBBBBBBDH483675','BBBBBBBBDH494867','BBBBBBBBDH517594','BBBBBBBBDH570009','BBBBBBBBDH598741','BBBBBBBBDH600947','BBBBBBBBDH616538','BBBBBBBBDH625776','BBBBBBBBDH665342','BBBBBBBBDH666173','BBBBBBBBDH669761','BBBBBBBBDH701968','BBBBBBBBDH754398','BBBBBBBBDH763179','BBBBBBBBDH787297','BBBBBBBBDH803235','BBBBBBBBDH813735','BBBBBBBBDH853841','BBBBBBBBDH864655','BBBBBBBBDH891923','BBBBBBBBDH908965','BBBBBBBBDH928669','BBBBBBBBDH936455','BBBBBBBBDH998494','BBBBBBBBDJ007369','BBBBBBBBDJ011345','BBBBBBBBDJ015784','BBBBBBBBDJ024752','BBBBBBBBDJ027605','BBBBBBBBDJ083298','BBBBBBBBDJ147498','BBBBBBBBDJ191248','BBBBBBBBDJ197166','BBBBBBBBDJ202719','BBBBBBBBDJ235830','BBBBBBBBDJ277378','BBBBBBBBDJ301033','BBBBBBBBDJ313583','BBBBBBBBDJ381969','BBBBBBBBDJ384495','BBBBBBBBDJ401745','BBBBBBBBDJ408399','BBBBBBBBDJ428365','BBBBBBBBDJ570034','BBBBBBBBDJ601175','BBBBBBBBDJ605604','BBBBBBBBDJ638051','BBBBBBBBDJ689831','BBBBBBBBDJ764966','BBBBBBBBDH367851','BBBBBBBBDH639564','BBBBBBBBDH528344','BBBBBBBBBC294301','BBBBBBBBBC304612','BBBBBBBBBP687946','BBBBBBBBBR367889','BBBBBBBBBT114008','BBBBBBBBDG697860','BBBBBBBBDG719941','BBBBBBBBDG817642','BBBBBBBBDG828316','BBBBBBBBDG948964','BBBBBBBBDG990799','BBBBBBBBDH057991','BBBBBBBBDH069938','BBBBBBBBDH095495','BBBBBBBBDH179389','BBBBBBBBDH211089','BBBBBBBBDH273635','BBBBBBBBDH279259','BBBBBBBBDH281922','BBBBBBBBDH282235','BBBBBBBBDH318910','BBBBBBBBDH350270','BBBBBBBBDH371249','BBBBBBBBDH426872','BBBBBBBBDH473853','BBBBBBBBDH555118','BBBBBBBBDH579032','BBBBBBBBDH594925','BBBBBBBBDH618729','BBBBBBBBDH619617','BBBBBBBBDH628925','BBBBBBBBDH668084','BBBBBBBBDH853967','BBBBBBBBDH905856','BBBBBBBBDH934384','BBBBBBBBDJ002645','BBBBBBBBDJ067334','BBBBBBBBDJ163352','BBBBBBBBDJ198581','BBBBBBBBDJ204798','BBBBBBBBDJ224092','BBBBBBBBDJ272532','BBBBBBBBDJ295747','BBBBBBBBDJ299620','BBBBBBBBDJ318966','BBBBBBBBDJ403642','BBBBBBBBDJ550219','BBBBBBBBDJ582704','BBBBBBBBDJ687703','BBBBBBBBDJ688904','BBBBBBBBDJ737480','BBBBBBBBDJ753804','BBBBBBBBDJ760806','BBBBBBBBDL051454','BBBBBBBBBC716214','BBBBBBBBBL313414','BBBBBBBBBM837758','BBBBBBBBBT617011','BBBBBBBBBZ918821','BBBBBBBBBZ918824','BBBBBBBBCB013519','BBBBBBBBCB298042','BBBBBBBBCH039572','BBBBBBBBCM833345','BBBBBBBBCS269261','BBBBBBBBCX230512','BBBBBBBBCX232052','BBBBBBBBDG797271','BBBBBBBBDG848489','BBBBBBBBDG898566','BBBBBBBBDG918719','BBBBBBBBDG937139','BBBBBBBBDG942364','BBBBBBBBDG954593','BBBBBBBBDG977064','BBBBBBBBDG996780','BBBBBBBBDH015906','BBBBBBBBDH031126','BBBBBBBBDH088614','BBBBBBBBDH121470','BBBBBBBBDH121508','BBBBBBBBDH178595','BBBBBBBBDH206834','BBBBBBBBDH273637','BBBBBBBBDH273639','BBBBBBBBDH304236','BBBBBBBBDH320644','BBBBBBBBDH341316','BBBBBBBBDH370138','BBBBBBBBDH385414','BBBBBBBBDH397078','BBBBBBBBDH450507','BBBBBBBBDH482591','BBBBBBBBDH511523','BBBBBBBBDH524432','BBBBBBBBDH555671','BBBBBBBBDH557473','BBBBBBBBDH599509','BBBBBBBBDH627634','BBBBBBBBDH627707','BBBBBBBBDH632467','BBBBBBBBDH649842','BBBBBBBBDH656342','BBBBBBBBDH708072','BBBBBBBBDH758547','BBBBBBBBDH770439','BBBBBBBBDH775237','BBBBBBBBDH804941','BBBBBBBBDH811326','BBBBBBBBDH817079','BBBBBBBBDH820020','BBBBBBBBDH837437','BBBBBBBBDH854281','BBBBBBBBDH882855','BBBBBBBBDH906143','BBBBBBBBDH906934','BBBBBBBBDH928641','BBBBBBBBDJ040498','BBBBBBBBDJ107126','BBBBBBBBDJ107335','BBBBBBBBDJ130360','BBBBBBBBDJ137301','BBBBBBBBDJ217777','BBBBBBBBDJ218785','BBBBBBBBDJ221752','BBBBBBBBDJ283043','BBBBBBBBDJ287496','BBBBBBBBDJ288015','BBBBBBBBDJ323715','BBBBBBBBDJ347551','BBBBBBBBDJ354150','BBBBBBBBDJ374539','BBBBBBBBDJ374558','BBBBBBBBDJ449055','BBBBBBBBDJ470013','BBBBBBBBDJ516160','BBBBBBBBDJ521082','BBBBBBBBDJ552408','BBBBBBBBDJ586028','BBBBBBBBDJ599021','BBBBBBBBDJ623155','BBBBBBBBDJ629848','BBBBBBBBDJ655602','BBBBBBBBDJ665454','BBBBBBBBDJ682572','BBBBBBBBDJ735253','BBBBBBBBDJ760831','BBBBBBBBDJ761124','BBBBBBBBDJ761125','BBBBBBBBDL011367','BBBBBBBBDL034393','BBBBBBBBDL039703','BBBBBBBBDL046106','BBBBBBBBDL051483','BBBBBBBBBC353770','BBBBBBBBBC559087','BBBBBBBBBD469323','BBBBBBBBBK603246','BBBBBBBBBW954782','BBBBBBBBBZ918816','BBBBBBBBCQ595394','BBBBBBBBCV400916','BBBBBBBBDG694128','BBBBBBBBDG699520','BBBBBBBBDG752626','BBBBBBBBDG789494','BBBBBBBBDG797314','BBBBBBBBDG805416','BBBBBBBBDG900150','BBBBBBBBDG906639','BBBBBBBBDG915726','BBBBBBBBDG943243','BBBBBBBBDH006936','BBBBBBBBDH006938','BBBBBBBBDH053658','BBBBBBBBDH072160','BBBBBBBBDH099802','BBBBBBBBDH117422','BBBBBBBBDH131709','BBBBBBBBDH169653','BBBBBBBBDH203385','BBBBBBBBDH216898','BBBBBBBBDH263840','BBBBBBBBDH287786','BBBBBBBBDH302435','BBBBBBBBDH307850','BBBBBBBBDH358344','BBBBBBBBDH389633','BBBBBBBBDH407643','BBBBBBBBDH411422','BBBBBBBBDH421977','BBBBBBBBDH425013','BBBBBBBBDH425380','BBBBBBBBDH450827','BBBBBBBBDH505132','BBBBBBBBDH527937','BBBBBBBBDH535932','BBBBBBBBDH568892','BBBBBBBBDH627722','BBBBBBBBDH660095','BBBBBBBBDH664581','BBBBBBBBDH701012','BBBBBBBBDH710443','BBBBBBBBDH713884','BBBBBBBBDH775244','BBBBBBBBDH800416','BBBBBBBBDH813452','BBBBBBBBDH828149','BBBBBBBBDH847519','BBBBBBBBDH871476','BBBBBBBBDH932314','BBBBBBBBDH943782','BBBBBBBBDH972691','BBBBBBBBDH986271','BBBBBBBBDH987377','BBBBBBBBDJ116516','BBBBBBBBDJ130116','BBBBBBBBDJ148848','BBBBBBBBDJ173047','BBBBBBBBDJ229426','BBBBBBBBDJ463037','BBBBBBBBDJ540791','BBBBBBBBDJ542345','BBBBBBBBDJ544582','BBBBBBBBDJ593681','BBBBBBBBDJ593884','BBBBBBBBDJ593886','BBBBBBBBDJ599023','BBBBBBBBDJ612118','BBBBBBBBDJ622549','BBBBBBBBDJ655511','BBBBBBBBDJ729898','BBBBBBBBDJ748683','BBBBBBBBDJ755879','BBBBBBBBDJ758660','BBBBBBBBDJ759349','BBBBBBBBDJ760578','BBBBBBBBDJ763363','BBBBBBBBDJ765530','BBBBBBBBDK441082','BBBBBBBBDL049896','BBBBBBBBBS031325','BBBBBBBBBX149441','BBBBBBBBBX149449','BBBBBBBBBX735310','BBBBBBBBCH039524','BBBBBBBBCW040967','BBBBBBBBDG685802','BBBBBBBBDG734677','BBBBBBBBDG803766','BBBBBBBBDG864374','BBBBBBBBDG879055','BBBBBBBBDG895759','BBBBBBBBDG910162','BBBBBBBBDG923628','BBBBBBBBDG947202','BBBBBBBBDG947494','BBBBBBBBDG964552','BBBBBBBBDH013692','BBBBBBBBDH016196','BBBBBBBBDH018323','BBBBBBBBDH037409','BBBBBBBBDH040435','BBBBBBBBDH065777','BBBBBBBBDH084545','BBBBBBBBDH134436','BBBBBBBBDH142091','BBBBBBBBDH145030','BBBBBBBBDH192677','BBBBBBBBDH255329','BBBBBBBBDH283181','BBBBBBBBDH299477','BBBBBBBBDH328981','BBBBBBBBDH358527','BBBBBBBBDH398749','BBBBBBBBDH401635','BBBBBBBBDH411905','BBBBBBBBDH442674','BBBBBBBBDH447591','BBBBBBBBDH485413','BBBBBBBBDH555932','BBBBBBBBDH561424','BBBBBBBBDH616852','BBBBBBBBDH633209','BBBBBBBBDH657034','BBBBBBBBDH664541','BBBBBBBBDH804062','BBBBBBBBDH876873','BBBBBBBBDH890229','BBBBBBBBDH998442','BBBBBBBBDJ017827','BBBBBBBBDJ022991','BBBBBBBBDJ079725','BBBBBBBBDJ100900','BBBBBBBBDJ103144','BBBBBBBBDJ120085','BBBBBBBBDJ144647','BBBBBBBBDJ227800','BBBBBBBBDJ288148','BBBBBBBBDJ298389','BBBBBBBBDJ305389','BBBBBBBBDJ374545','BBBBBBBBDJ386181','BBBBBBBBDJ390628','BBBBBBBBDJ401205','BBBBBBBBDJ483775','BBBBBBBBDJ488395','BBBBBBBBDJ519774','BBBBBBBBDJ543006','BBBBBBBBDJ576399','BBBBBBBBDJ593875','BBBBBBBBDJ613401','BBBBBBBBDJ627081','BBBBBBBBDJ630580','BBBBBBBBDJ675251','BBBBBBBBDJ687576','BBBBBBBBDJ762038','BBBBBBBBDJ762054','BBBBBBBBDJ763441','BBBBBBBBDK875028','BBBBBBBBDL030946','BBBBBBBBDG713908','BBBBBBBBBD376274','BBBBBBBBCG892403','BBBBBBBBDJ185617','BBBBBBBBDH080428','BBBBBBBBDJ075572','BBBBBBBBBF364359','BBBBBBBBDH884470','BBBBBBBBDJ421606','BBBBBBBBDH045169','BBBBBBBBDH466075','BBBBBBBBCC960490','BBBBBBBBCH124529','BBBBBBBBCR378961','BBBBBBBBDH007025','BBBBBBBBDH011224','BBBBBBBBDH155378','BBBBBBBBDH174382','BBBBBBBBDH175329','BBBBBBBBDH222667','BBBBBBBBDH281794','BBBBBBBBDH298997','BBBBBBBBDH302866','BBBBBBBBDH326538','BBBBBBBBDH437497','BBBBBBBBDH469509','BBBBBBBBDH527353','BBBBBBBBDH554877','BBBBBBBBDH587205','BBBBBBBBDH753879','BBBBBBBBDH754397','BBBBBBBBDH769572','BBBBBBBBDH780763','BBBBBBBBDH867228','BBBBBBBBDH884966','BBBBBBBBDH931738','BBBBBBBBDJ039452','BBBBBBBBDJ060922','BBBBBBBBDJ063149','BBBBBBBBDJ081681','BBBBBBBBDJ120304','BBBBBBBBDJ125932','BBBBBBBBDJ229815','BBBBBBBBDJ241204','BBBBBBBBDJ248647','BBBBBBBBDJ248653','BBBBBBBBDJ248687','BBBBBBBBDJ374715','BBBBBBBBDJ443028','BBBBBBBBDJ694334','BBBBBBBBDK498122','BBBBBBBBDG780984','BBBBBBBBDG820399','BBBBBBBBDG875680','BBBBBBBBDH002121','BBBBBBBBDH002524','BBBBBBBBDH091396','BBBBBBBBDH208297','BBBBBBBBDH239613','BBBBBBBBDH400295','BBBBBBBBDH576741','BBBBBBBBDH648310','BBBBBBBBDJ054714','BBBBBBBBDJ109320','BBBBBBBBDJ117786','BBBBBBBBDJ621290','BBBBBBBBDJ760194','BBBBBBBBBP360434','BBBBBBBBBT942991','BBBBBBBBCC767012','BBBBBBBBCG912304','BBBBBBBBDG840884','BBBBBBBBDH038444','BBBBBBBBDH072364','BBBBBBBBDH173659','BBBBBBBBDH347311','BBBBBBBBDH374887','BBBBBBBBDH400652','BBBBBBBBDH534835','BBBBBBBBDH611234','BBBBBBBBDH635225','BBBBBBBBDH866518','BBBBBBBBDH909767','BBBBBBBBDH968389','BBBBBBBBDH971316','BBBBBBBBDH983267','BBBBBBBBDJ058122','BBBBBBBBDJ058661','BBBBBBBBDJ059563','BBBBBBBBDJ074206','BBBBBBBBDJ074704','BBBBBBBBDJ074920','BBBBBBBBDJ154647','BBBBBBBBDJ178073','BBBBBBBBDJ223178','BBBBBBBBDJ273917','BBBBBBBBDJ418193','BBBBBBBBDJ540480','BBBBBBBBBX493400','BBBBBBBBCB461213','BBBBBBBBDG962733','BBBBBBBBDH026924','BBBBBBBBDH033130','BBBBBBBBDH321131','BBBBBBBBDH374151','BBBBBBBBDH374783','BBBBBBBBDH611273','BBBBBBBBDH749948','BBBBBBBBDH759146','BBBBBBBBDH796782','BBBBBBBBDH810872','BBBBBBBBDH828175','BBBBBBBBDH851181','BBBBBBBBDH884385','BBBBBBBBDH889620','BBBBBBBBDH908603','BBBBBBBBDH935055','BBBBBBBBDJ025299','BBBBBBBBDJ027225','BBBBBBBBDJ141466','BBBBBBBBDJ199563','BBBBBBBBDJ227562','BBBBBBBBDJ256884','BBBBBBBBDJ267870','BBBBBBBBDJ384082','BBBBBBBBDJ678810','BBBBBBBBBX125609','BBBBBBBBDH323175','BBBBBBBBDH525840','BBBBBBBBDH663527','BBBBBBBBDH881024','BBBBBBBBDH960541','BBBBBBBBDJ085121','BBBBBBBBDJ525045','BBBBBBBBDH023206','BBBBBBBBDH094766','BBBBBBBBDH277149','BBBBBBBBDH460156','BBBBBBBBDH460158','BBBBBBBBDH831004','BBBBBBBBDH899762','BBBBBBBBDH988843','BBBBBBBBDH992472','BBBBBBBBDJ236313','BBBBBBBBDJ321149','BBBBBBBBDJ338558','BBBBBBBBDJ346223','BBBBBBBBDJ755900','BBBBBBBBBF464118','BBBBBBBBCW651876','BBBBBBBBDG806838','BBBBBBBBDH316846','BBBBBBBBDH358366','BBBBBBBBDH370247','BBBBBBBBDH422251','BBBBBBBBDH458404','BBBBBBBBDH460159','BBBBBBBBDH465589','BBBBBBBBDH527069','BBBBBBBBDH590482','BBBBBBBBDH660655','BBBBBBBBDH842003','BBBBBBBBDJ184518','BBBBBBBBDJ290518','BBBBBBBBDJ540661','BBBBBBBBDJ612014','BBBBBBBBBZ617494','BBBBBBBBCW651882','BBBBBBBBDG946669','BBBBBBBBDH169234','BBBBBBBBDH385453','BBBBBBBBDH465594','BBBBBBBBDH563678','BBBBBBBBDH690707','BBBBBBBBDJ203771','BBBBBBBBDJ354989','BBBBBBBBDJ429413','BBBBBBBBDH424819','BBBBBBBBDG714346','BBBBBBBBDH787874','BBBBBBBBDJ757912','BBBBBBBBDH256234') ");
            stringBuffer.append(" and CP.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO ");
            stringBuffer.append(" and CA.COD_COMUNIDAD           = CP.COD_COMUNIDAD ");
            stringBuffer.append(" and DA.COD_PRESTACION_SERVICIO = U.COD_PRESTACION_SERVICIO ");
            stringBuffer.append(" and IU.COD_USUARIO_SNS         = U.COD_USUARIO_SNS ");
            stringBuffer.append(" and IU.COD_AGENTE              = DA.COD_AGENTE ");
            stringBuffer.append(" and DP.COD_USUARIO_SNS         = U.COD_USUARIO_SNS ");
            stringBuffer.append(" and DC.COD_USUARIO_SNS         = U.COD_USUARIO_SNS ");
            stringBuffer.append(" and DF.COD_USUARIO_SNS         = U.COD_USUARIO_SNS ");
            */
            
            
            
			query = stringBuffer.toString();			
			//
			//parametros.put("1", Misc.nz(""));
			//
			//logger.debug("query:      " + query);
			//logger.debug("parametros: " + parametros);
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet 		   = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				contadorRegistros++;
				//
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
				}		
				SnsBean snsBean  = new SnsBean (resultSet);
				//logger.debug("snsBean: " + snsBean.toString());
				this.gestionConsultas (accesoBd, fileWriter, mapCamposBusqueda, snsBean);
			}
			//
			logger.debug("contadorRegistros: " + contadorRegistros);
			//
			this.verResultados (mapCamposBusqueda, 0);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e1) {
				resultSet = null;
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (Exception e2) {
				preparedStatement = null;
			}
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			}
			catch (Exception e4) {
				fileWriter = null;
			}
		}
	}
	
	
	public void managerFichero(String path, String ficheroOrigen, String ficheroDestino) throws Exception {
		//
		String line = "";
		int contadorRegistros = 0;
		//
		AccesoBd accesoBd = null;
		//
		BufferedReader bufferedReader  = null;
		FileReader fileReader          = null;
		//
		FileWriter fileWriter          = null;
		//
		LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda = new LinkedHashMap <String, ResultadosCampoBean> ();
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd       = new AccesoBd();
			//
			fileReader     = new FileReader(path + ficheroOrigen);
			bufferedReader = new BufferedReader(fileReader);
			//
			fileWriter     = new FileWriter(path + ficheroDestino);
			//
			//
			// Se Inicializa el map para los resultados y para los campos de consulta
			//
            Set <String> setCamposBusqueda          = InicializacionBusqueda.MAP_CAMPO.keySet();
            Iterator <String> iteratorCamposBusqueda = setCamposBusqueda.iterator();
            //           
            while (iteratorCamposBusqueda.hasNext()) {
            	String camposBusqueda  = (String) iteratorCamposBusqueda.next();
                //
                if(InicializacionBusqueda.MAP_CAMPOS_BUSQUEDA_INSS.containsKey(camposBusqueda)) {
                	mapCamposBusqueda.put(camposBusqueda, new ResultadosCampoBean());
                	logger.debug("camposBusqueda: " + camposBusqueda);
                }   
            }
			//
            while ((line = bufferedReader.readLine()) != null) {
				contadorRegistros++;
				if (contadorRegistros % 1000 == 0) {
					logger.debug("Registros procesados: " + contadorRegistros);
				}	
				//
				//logger.debug("line: " + line);
				SnsBean snsBean  = new SnsBean (line);
				//
				this.gestionConsultas (accesoBd, fileWriter, mapCamposBusqueda, snsBean);
            }
			//
			logger.debug("contadorRegistros: " + contadorRegistros);
			//
			this.verResultados (mapCamposBusqueda, 0);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (Exception e2) {
				bufferedReader = null;
			}
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			}
			catch (Exception e3) {
				fileReader = null;
			}		
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			}
			catch (Exception e4) {
				fileWriter = null;
			}
		}
	}

	
	public void gestionConsultas (AccesoBd accesoBd, FileWriter fileWriter, LinkedHashMap <String, ResultadosCampoBean> mapCamposBusqueda, SnsBean snsBean) throws Exception {
		//
		boolean encontrado = false;
		String tipoResultado = "";
		InssBdHelper inssBdHelper = new InssBdHelper ();
		//
		try {
			//logger.debug("INICIO");
			// Para si es el ultimo campo a consultar, en ese caso se escribe en fichero si es no coincidente
			int contadorCampos              = 0;
			int totalCampos                 = mapCamposBusqueda.size();
			//logger.debug("totalCampos: " + totalCampos);
			boolean esUltimElementoConsulta = false;
			//////////////////////////////////////////
			//
			// Para cada linea del fichero se realiza una busqueda por los campos que corresponda
			//
			ResultadosCampoBean resultadosCampoBean = null;
			String tipoCampo                        = "";
			//
			// Se realiza la busqueda del campo CODSNS
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS)) {
				//
				ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo = ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS;
				resultadosCampoBean = mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_CODSNS);
				//
				if(!Misc.esVacio(snsBean.getCodUsuario())) {
					InssBean inssBean = inssBdHelper.getInfoInssByCodUsuario(accesoBd, snsBean.getCodUsuario());
					if (inssBean!= null &&  !Misc.esVacio(inssBean.getCodUsuarioSns())) {
						arrayList.add(inssBean);
					}
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;			
				}
				//
				encontrado = this.consulta (accesoBd, fileWriter, resultadosCampoBean, snsBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo DNI
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_DNI)) {
				//
				ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_DNI;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_DNI);
				//
				if(!Misc.esVacio(snsBean.getDniNie())) {
					arrayList = inssBdHelper.getInfoInssByDni(accesoBd, snsBean.getDniNie());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				//
				//logger.debug("contadorCampos: " + contadorCampos + ", getDniNie(): " + snsBean.getDniNie());
				//
				encontrado = this.consulta (accesoBd, fileWriter, resultadosCampoBean, snsBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
            }  
			tipoResultado = "";
			//
			//
			// Se realiza la busqueda del campo NAF
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAF)) {
				//
				ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAF;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAF);
				//
				if(!Misc.esVacio(snsBean.getNaf())) {
					arrayList = inssBdHelper.getInfoInssByNaf(accesoBd, snsBean.getNaf());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				encontrado = this.consulta (accesoBd, fileWriter, resultadosCampoBean, snsBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
			}
			tipoResultado = "";
			//	
			// Se realiza la busqueda del campo por NAF_SEC
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC)) {
				//
				ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAF_SEC);
				//
				if(!Misc.esVacio(snsBean.getNaf())) {
					arrayList = inssBdHelper.getInfoInssByNafSec(accesoBd, snsBean.getNaf());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				//
				encontrado = this.consulta (accesoBd, fileWriter, resultadosCampoBean, snsBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
	        }
			//
			tipoResultado = "";
			//
			// Se realiza la busqueda del campo NAF_titular
			// 
			if(!encontrado && mapCamposBusqueda.containsKey(ConstantesBusqueda.VALOR_BUSQUEDA_NAFTITULAR)) {
				//
				ArrayList <InssBean> arrayList = new ArrayList <InssBean> ();
				//
				contadorCampos++;
				if(contadorCampos == totalCampos){
					esUltimElementoConsulta = true;
				}
				tipoCampo           = ConstantesBusqueda.VALOR_BUSQUEDA_NAF;
				resultadosCampoBean = (ResultadosCampoBean) mapCamposBusqueda.get(ConstantesBusqueda.VALOR_BUSQUEDA_NAF);
				//
				if(!Misc.esVacio(snsBean.getNaf())) {
					arrayList = inssBdHelper.getInfoInssByNafTit(accesoBd, snsBean.getNafTitular());
				}
				else{
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_VACIOS;	
				}
				encontrado = this.consulta (accesoBd, fileWriter, resultadosCampoBean, snsBean, arrayList, tipoCampo, tipoResultado, esUltimElementoConsulta);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	
	
	
	
	public boolean consulta (AccesoBd accesoBd, FileWriter fileWriter, ResultadosCampoBean resultadosCampoBean, SnsBean snsBean, ArrayList <InssBean> arrayList, String tipoCampo, String tipoResultado, boolean esUltimElementoConsulta) throws Exception {
		//
		boolean encontrado = false;  
		InssBean inssBean    = new InssBean ();
		//
		try {
			//logger.debug("INICIO");
			//
			int repeticiones   = arrayList.size();
			//
			//logger.debug("repeticiones: " + repeticiones + ", tipoResultado: " + tipoResultado);
			//
			if (repeticiones == 0) {
				if(tipoResultado.equals( ConstantesBusqueda.TIPO_RESULTADO_VACIOS)) {
					//
					resultadosCampoBean.incrementarVacios();	
					//
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, snsBean, inssBean, null, tipoCampo, tipoResultado);
					}
					else {
						if(!Misc.esVacio(InicializacionBusqueda.OBTENER_CAMPOS_VACIOS)) {
							//  VACIOS
							this.escribirFichero (fileWriter, snsBean, inssBean, null, tipoCampo, tipoResultado);
						}
					}
				}
				else {
					tipoResultado =  ConstantesBusqueda.TIPO_RESULTADO_NO_ENCONTRADOS;
					resultadosCampoBean.incrementarNoEncontrados();
					//
					if(esUltimElementoConsulta){
						this.escribirFichero (fileWriter, snsBean, inssBean, null, tipoCampo, tipoResultado);
					}
				}	
			}
			else {
				//
				if (repeticiones > 1) {
					// DUPLICADOS
					encontrado = true;
					tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS;
					resultadosCampoBean.incrementarDuplicados();
					this.escribirFichero (fileWriter, snsBean, inssBean, null, tipoCampo, tipoResultado);
				}
				//
				for(int i=0 ; i<arrayList.size() ; i++) {
					//
					inssBean = (InssBean)arrayList.get(i); 
					//
					//
					// // BUSQUEDA APROXIMADA // //  // // // // // // // // // // // //
					//
					//
					// Situamos fuera de (repeticiones == 1) este codigo pq se utilizara para RESULTADO_DUPLICADOS_ESTUDIO
					ConsultaBean consultaBeanLectura = new ConsultaBean (snsBean.getNombre(), snsBean.getApellido1(), snsBean.getApellido2(), snsBean.getCodSexo(), snsBean.getFechaNacimiento());
					ConsultaBean consultaBeanSns     = new ConsultaBean (inssBean.getArrayListNafSec(), inssBean.getNombre(), inssBean.getApellido1(), inssBean.getApellido2(), inssBean.getSexo(), inssBean.getFechaNacimientoRaw());
					consultaBeanLectura.incluirCampos (Misc.nz(snsBean.getDniNie()), Misc.nz(snsBean.getNaf()));
					consultaBeanSns.incluirCampos(Misc.nz(inssBean.getDniNie()), Misc.nz(inssBean.getNaf()));
					//
					BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
					BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBeanLectura, consultaBeanSns);
					//logger.debug("busquedaAproxResultadoBean: " + busquedaAproxResultadoBean.toString());
					//
					boolean coincidente  = this.esMismaPersona(busquedaAproxResultadoBean);
					// // //  // // // // // // // //  // // // // // // // // // // // //
					//
					//
					if (repeticiones == 1) {	
						//
						if(coincidente) {
							// COINCIDENTES
							encontrado = true;
							tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_COINCIDENTES;
							resultadosCampoBean.incrementarCoincidente();
						}
						else {
							// NO_COINCIDENTES
							//
							resultadosCampoBean.incrementarNoCoincidenteTotal();
							//
							if(Misc.nz(busquedaAproxResultadoBean.getCriterioCompleto()).indexOf(ConstantesBusqueda.CRITERIO_COMPLETO_DNI_NAF) != -1 ){
								encontrado = true;
								tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES_DNINAF;
								resultadosCampoBean.incrementarNoCoincidenteDniNaf();	
							}
							else {
								tipoResultado = ConstantesBusqueda.TIPO_RESULTADO_ENC_NO_COINCIDENTES;
								resultadosCampoBean.incrementarNoCoincidente();
							}
						}
						this.escribirFichero (fileWriter, snsBean, inssBean, busquedaAproxResultadoBean, tipoCampo, tipoResultado);
					}
					else {
						// DUPLICADOS
						encontrado = true;
						resultadosCampoBean.incrementarDuplicadosEstudio();
						this.escribirFichero (fileWriter, snsBean, inssBean, busquedaAproxResultadoBean, tipoCampo, ConstantesBusqueda.TIPO_RESULTADO_DUPLICADOS_ESTUDIO);
					}
				}
			}
			fileWriter.flush();
			//
			resultadosCampoBean.setUltimoCaso(tipoResultado);
			//
			//logger.debug("encontrado: " + encontrado + ", repeticiones: " + repeticiones + ",  " + tipoResultado);
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		return encontrado;
	}	
	
}
