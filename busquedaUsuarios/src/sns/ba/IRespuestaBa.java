package sns.ba;

import java.util.ArrayList;

public interface IRespuestaBa {
	public int getCamposCoincidentes();
	public ArrayList<String> getCamposDiferentes();
	public ArrayList<String> getCamposIgualesPorAproximacion();
	public ArrayList<String> getCamposCriterio();
	public String getCriterio();
	public int getPorcentaje();
	
	public int getCamposCoincidentesCompleto();//valores 0, 1 o 2
	public ArrayList<String> getCamposDiferentesCompleto();
	public ArrayList<String> getCamposCriterioCompleto();
	public String getCriterioCompleto();
	public int getPorcentajeCompleto();
	
	public boolean isIgual();
	public String toString();
}
