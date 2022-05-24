package sns.ba.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import sns.ba.IRespuestaBa;
import sns.ba.config.InicializacionBA;


public class RespuestaBaImpl implements IRespuestaBa {

	private static Logger logger = Logger.getLogger(InicializacionBA.LOGGER_NAME);	
	
	private int camposCoincidentes                        = 0;
	private ArrayList<String> camposDiferentes            = null;
	private ArrayList<String>  camposIgualesAproximacion  = null;
	private String criterio                               = "";
	private ArrayList<String> camposCriterio              = null;
	private int porcentaje                                = 0;
	//
	private int camposCoincidentesCompleto                = 0;
	private ArrayList<String> camposDiferentesCompleto    = null;
	private String criterioCompleto                       = "";
	private ArrayList<String> camposCriterioCompleto      = null;
	private int porcentajeCompleto                        = 0;
	//
	boolean esMismaPersona                                = false;
	
	
	
	public	RespuestaBaImpl (){
	}	
	
	
	public String toString2() {
		StringBuilder builder = new StringBuilder();
		builder.append("RespuestaBaImpl [camposCoincidentes=");
		builder.append(camposCoincidentes);
		builder.append(", camposCoincidentesCompleto=");
		builder.append(camposCoincidentesCompleto);
		builder.append(", camposCriterio=");
		builder.append(camposCriterio);
		builder.append(", camposCriterioCompleto=");
		builder.append(camposCriterioCompleto);
		builder.append(", camposDiferentes=");
		builder.append(camposDiferentes);
		builder.append(", camposDiferentesCompleto=");
		builder.append(camposDiferentesCompleto);
		builder.append(", camposIgualesAproximacion=");
		builder.append(camposIgualesAproximacion);
		builder.append(", criterio=");
		builder.append(criterio);
		builder.append(", criterioCompleto=");
		builder.append(criterioCompleto);
		builder.append(", porcentaje=");
		builder.append(porcentaje);
		builder.append(", porcentajeCompleto=");
		builder.append(porcentajeCompleto);
		builder.append("]");
		return builder.toString();
	}


	public String toString() {
		// 
		try {
			StringBuffer stringBuffer = new StringBuffer ();
			stringBuffer.append("camposCoincidentes: " + camposCoincidentes);
			stringBuffer.append(", camposDiferentes: ");
			for(int i=0 ; i<camposDiferentes.size() ; i++){
				stringBuffer.append(camposDiferentes.get(i) + ", ");
			}
			logger.debug(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			//
			stringBuffer.append("camposIgualesAproximacion: ");
			for(int i=0 ; i<camposIgualesAproximacion.size() ; i++){
				stringBuffer.append(camposIgualesAproximacion.get(i) + ", ");
			}
			logger.debug(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			//
			logger.debug("criterio: " + criterio);
			stringBuffer.append("camposCriterio: ");
			for(int i=0 ; i<camposCriterio.size() ; i++){
				stringBuffer.append(camposCriterio.get(i) + ", ");
			}
			logger.debug(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			//
			logger.debug("porcentaje: " + porcentaje);
			//
			logger.debug("camposCoincidentesCompleto: " + camposCoincidentesCompleto);
			stringBuffer.append("camposDiferentesCompleto: ");
			for(int i=0 ; i<camposDiferentesCompleto.size() ; i++){
				stringBuffer.append(camposDiferentesCompleto.get(i) + ", ");
			}
			logger.debug(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			//
			logger.debug("criterioCompleto: " + criterioCompleto);
			stringBuffer.append("camposCriterioCompleto: ");
			for(int i=0 ; i<camposCriterioCompleto.size() ; i++){
				stringBuffer.append(camposCriterioCompleto.get(i) + ", ");
			}
			logger.debug(stringBuffer.toString());
			//	
			logger.debug("porcentajeCompleto: " + porcentajeCompleto);
			logger.debug("esMismaPersona: " + esMismaPersona);
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}
		
	
	public void rellenarCampos (int camposCoincidentes, ArrayList<String> camposDiferentes, ArrayList<String> camposIgualesAproximacion,
								String criterio, ArrayList<String> camposCriterio, int porcentaje, boolean esMismaPersona){
		this.camposCoincidentes        = camposCoincidentes;
		this.camposDiferentes          = camposDiferentes;
		this.camposIgualesAproximacion = camposIgualesAproximacion;
		//
		this.criterio                  = criterio;
		this.camposCriterio            = camposCriterio;
		this.porcentaje                = porcentaje;
		//
		this.esMismaPersona            = esMismaPersona;
	}
		
	public void rellenarCamposCompleto (int camposCoincidentesCompleto, ArrayList<String> camposDiferentesCompleto, String criterioCompleto, 
			                            ArrayList<String> camposCriterioCompleto, int porcentajeCompleto){
		this.camposCoincidentesCompleto        = camposCoincidentesCompleto;
		this.camposDiferentesCompleto          = camposDiferentesCompleto;
		//
		this.criterioCompleto                  = criterioCompleto;
		this.camposCriterioCompleto            = camposCriterioCompleto;
		this.porcentajeCompleto                = porcentajeCompleto;
	}

	
	public int getCamposCoincidentes() {
		return camposCoincidentes;
	}

	public ArrayList<String> getCamposDiferentes() {
		return camposDiferentes;
	}

	public ArrayList<String> getCamposIgualesPorAproximacion() {
		return camposIgualesAproximacion;
	}

	public String getCriterio() {
		return criterio;
	}
	
	public ArrayList<String> getCamposCriterio() {
		return camposCriterio;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public int getCamposCoincidentesCompleto() {
		return camposCoincidentesCompleto;
	}

	public ArrayList<String> getCamposDiferentesCompleto() {
		return camposDiferentesCompleto;
	}

	public ArrayList<String> getCamposCriterioCompleto() {
		return camposCriterioCompleto;
	}

	public String getCriterioCompleto() {
		return criterioCompleto;
	}

	public int getPorcentajeCompleto() {
		return porcentajeCompleto;
	}

	public boolean isIgual() {
		return esMismaPersona;
	}


}
