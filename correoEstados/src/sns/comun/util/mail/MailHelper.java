package sns.comun.util.mail;

import java.io.FileWriter;

import org.apache.log4j.Logger;

import sns.comun.config.Inicializacion;
import sns.comun.util.Util;
import sns.estadoagentes.ManagerEstadoAgentes;



public class MailHelper {
	
	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	private String getCss() {
		String css="<style type=\"text/css\">";
		css+="body {";
		css+="  color: #003366;";
		css+="  background-color: #FFFFFF;";
		css+="  font-family: Verdana, Tahoma, sans-serif;";
		css+="  font-size: 11px;";
		css+="  width:80%;";
		css+="}";
		css+="";
		css+="a {";
		css+="  color: #003366;";
		css+="  float:left;";
		css+="  margin-left:1em;";
		css+="}";
		css+="";
		css+="h1 {";
		css+="  font-size: 1.4em;";
		css+="  font-weight: bold;";
		css+="  clear:both;";
		css+="}";
		css+="";
		css+="h2 {";
		css+="  font-size: 1.4em;";
		css+="  font-weight: bold;";
		css+="}";
		css+="";
		css+="h3 {";
		css+="  font-size: 1em;";
		css+="  font-weight: bold;";
		css+="}";
		css+="";
		css+="h4 {";
		css+="  font-size: 1em;";
		css+="  font-weight: bold;";
		css+="}";
		css+="";
		css+="div {";
		css+="  width: 80%;";
		css+="}";
		css+="";
		css+="table {";
		css+="  line-height: 1em;";
		css+="  width: 100%;";
		css+="  border-spacing:0;";
		css+="  border-collapse:collapse;";
		css+="}";
		css+="";
		css+="tr {";
		css+="  background-color: #ccccff;";
		css+="}";
		css+="";
		css+="td {";
		css+="  padding: 0.5em 0.4em;";
		css+="  vertical-align: top;";
		css+="  border: 2px solid #FFFFFF;";
		css+="}";
		css+="";
		css+=".logo {";
		css+="  height: 5em;";
		css+="  float: right;";
		css+="  margin-left: 1em;";
		css+="  }";
		css+="  ";
		css+=".h1center {";
		css+="  font-size: 1.6em;";
		css+="  font-weight: bold;";
		css+="  text-align: center;";
		css+="  width: 80%;";
		css+="}";
		css+="";
		css+=".header_table{";
		css+="  background-color: #ccccff;";
		css+="}";
		css+="";
		css+=".narr_table {";
		css+="  width: 100%;";
		css+="}";
		css+="";
		css+=".narr_tr {";
		css+="  background-color: #ffffcc;";
		css+="}";
		css+="";
		css+=".narr_th {";
		css+="  background-color: #ffd700;";
		css+="}";
		css+="";
		css+=".narr_tr_n {";
		css+="  background-color: #EEEEFF;";
		css+="  border: 2px solid #FFFFFF;";
		css+="}";
		css+="";
		css+=".narr_th_n {";
		css+="  background-color: #CCCCFF;";
		css+="}";
		css+="";
		css+=".td_label{";
		css+="  font-weight: bold;";
		css+="  color: white;";
		css+="}";
		css+=".titulo";
		css+="{";
		css+="  background-color:#19355E;";
		css+="  color:#FFFFFF;";
		css+="  font-weight:bold;";
		css+="  font-family: Arial, Helvetica, sans-serif;";
		css+="  font-size: 24px;";
		css+="}";
		css+=".right";
		css+="{";
		css+="  float:right;";
		css+="}";
		css+=".centrado";
		css+="{";
		css+="  text-align:center;";
		css+="}";
		css+=".oculto";
		css+="{";
		css+="  visibility:hide;";
		css+="  display:none;";
		css+="}";
		css+=".error";
		css+="{";
		css+="  color:red;";
		css+="  float:left;";
		css+="  clear:both;";
		css+="}";
		css+="#cabecera {";
		css+="  background-color:#19355E;";
		css+="  text-align:center;";
		css+="  height:5.3em;";
		css+="  width:100%;";
		css+="}";
		css+="#extendedContact";
		css+="{";
		css+="  display:none;";
		css+="}";
		css+="#documentInfo";
		css+="{";
		css+="  display:none;";
		css+="}";
		css+="</style>";
		return css;
	}
	
	public void enviarMail(CuerpoMailBean cuerpoMailBean,String destinatarios,String[] arrayNombreFicheros,String[] arrayPath,String role,String fecha) throws Exception {
		FileWriter fileWriterCuerpoMailBean = null;
		StringBuffer textHtml=new StringBuffer();
		textHtml.append("<html>");
		textHtml.append("<head>");
		textHtml.append(getCss());
		textHtml.append("</head>");
		textHtml.append("<body>");
		textHtml.append(cuerpoMailBean.getCuerpo(role));
		textHtml.append("</body>");
		textHtml.append("</html>");
		logger.debug("Enviando correo a -> [" + destinatarios + "]");
		try {
			Util util = new Util();
			
			StringBuffer stringBufferNombreFicheroCuerpoMail = new StringBuffer();
			stringBufferNombreFicheroCuerpoMail.append(util.generateFecha(""));
			stringBufferNombreFicheroCuerpoMail.append("_CuerpoMail.txt");
			StringBuffer stringBufferPathCompletaFicheroCuerpoMail = new StringBuffer();
			stringBufferPathCompletaFicheroCuerpoMail.append(Inicializacion.PATH_LOCAL);
			stringBufferPathCompletaFicheroCuerpoMail.append(stringBufferNombreFicheroCuerpoMail.toString());
			fileWriterCuerpoMailBean = new FileWriter(stringBufferPathCompletaFicheroCuerpoMail.toString());
	
			fileWriterCuerpoMailBean.write(textHtml.toString());
			fileWriterCuerpoMailBean.flush();
			//Correo correo = new Correo(destinatarios, "sns@msc.es", Inicializacion.smtp, Inicializacion.asunto+" "+fecha, textHtml.toString(), true, true);
			if (arrayNombreFicheros!=null && arrayPath!=null) {
				logger.debug("sns.AlertaAgentes.tramitar: -- LOG -- ENVIANDO CORREO CON FICHEROS ");
				logger.debug("sns.AlertaAgentes.tramitar: ---------------------------------------------------");
				logger.debug("sns.AlertaAgentes.tramitar: ---------------------------------------------------");
				//Salida salidaCorreo = correo.enviarConFicheros(arrayNombreFicheros, arrayPath);
				logger.debug("sns.AlertaAgentes.tramitar: ---------------------------------------------------");
				logger.debug("sns.AlertaAgentes.tramitar: ---------------------------------------------------");
				logger.debug("sns.AlertaAgentes.tramitar: -- FIN  LOG -- ENVIANDO CORREO CON FICHEROS ");
				//logger.debug("sns.AlertaAgentes.tramitar: getError: " + salidaCorreo.getError());
				//logger.debug("sns.AlertaAgentes.tramitar: getMsg:   " + salidaCorreo.getMsg());
			}else{
				logger.debug("ENVIANDO CORREO SIN FICHEROS");
				//correo.enviar();
			}
			
		}catch (Exception e) {
				logger.debug("Exception: " + e.getMessage());
				e.printStackTrace();
				throw e;
		}
		finally {
		
			try {
				if (fileWriterCuerpoMailBean != null) {
					fileWriterCuerpoMailBean.close();
				}
			}
			catch (Exception e3) {
				fileWriterCuerpoMailBean = null;
			}
		}
	}
	
	public static void main(String[] args) {
		//  
		try {
			logger.debug("INICIO");
			//
			MailHelper mailHelper = new MailHelper();
			mailHelper.enviarMail(new CuerpoMailBean(), Inicializacion.destinatarios, null, null, CuerpoMailBean.CUERPO_MAIL_MINISTERIO,Util.generateFecha("-"));
			//
			logger.debug("FIN");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
	}
	
}
