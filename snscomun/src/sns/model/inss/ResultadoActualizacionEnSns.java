package sns.model.inss;

import java.util.ArrayList;

import sns.model.ListaCamposAfectadosVisiblesToComunidad;
import sns.model.inss.dto.DatosSnsBean;

public class ResultadoActualizacionEnSns {

	private ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitular;
	private DatosSnsBean datosSnsBean=new DatosSnsBean();
	private ArrayList listaBeneficiariosToNotificar=new ArrayList();
	public ArrayList getListaBeneficiariosToNotificar() {
		return listaBeneficiariosToNotificar;
	}
	public void addBeneficiarioToNotificar(ListaCamposAfectadosVisiblesToComunidad ben) {
		listaBeneficiariosToNotificar.add(ben);
	}
	public ListaCamposAfectadosVisiblesToComunidad getListaCamposAfectadosTitular() {
		return listaCamposAfectadosTitular;
	}
	public void setListaCamposAfectadosTitular(ListaCamposAfectadosVisiblesToComunidad listaCamposAfectadosTitular) {
		this.listaCamposAfectadosTitular = listaCamposAfectadosTitular;
	}
	public DatosSnsBean getDatosSnsBean() {
		return datosSnsBean;
	}
	public void setDatosSnsBean(DatosSnsBean datosSnsBean) {
		this.datosSnsBean = datosSnsBean;
	}

}
