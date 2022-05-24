package sns.model.inss;

import gasai.util.Misc;

import java.util.ArrayList;

public class ListaCamposAfectadosInss implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3944579878866029088L;
	private ArrayList vListaCamposAfectados;
	private ArrayList vIdCampos;

	public ListaCamposAfectadosInss() {
		vListaCamposAfectados = new ArrayList();
		vIdCampos = new ArrayList();
	}

	public boolean isEmpty() {
		return vListaCamposAfectados.isEmpty();
	}

	public void addCampo(CamposAfectadosInss camposAfectados) {
		if (!Misc.nz(camposAfectados.getValorAnterior()).trim().equals(Misc.nz(camposAfectados.getValorNuevo()))) {
			this.vListaCamposAfectados.add(camposAfectados);
			this.vIdCampos.add(camposAfectados.getCodCampo());
		}
	}

	public ArrayList getListaCodCampos() {
		return this.vIdCampos;
	}

	public boolean existeCampo(String idCampo) {
		return (this.vIdCampos.indexOf(idCampo) != -1);
	}

	public String getValorCampo(String idCampo) {
		if (!existeCampo(idCampo)) {
			return "";
		} else {
			CamposAfectadosInss camposAfectados = (CamposAfectadosInss) this.vListaCamposAfectados.get(this.vIdCampos.indexOf(idCampo));
			if (Misc.esVacio(camposAfectados)) {
				return "";
			} else {
				return Misc.nz(camposAfectados.getValorNuevo());
			}

		}
	}

	public String getValorCampoAnterior(String idCampo) {
		if (!existeCampo(idCampo)) {
			return "";
		} else {
			CamposAfectadosInss camposAfectados = (CamposAfectadosInss) this.vListaCamposAfectados.get(this.vIdCampos.indexOf(idCampo));
			if (Misc.esVacio(camposAfectados)) {
				return "";
			} else {
				return Misc.nz(camposAfectados.getValorAnterior());
			}

		}
	}

	public ArrayList getListaCamposAfectados() {
		return this.vListaCamposAfectados;
	}

	public ArrayList concat(ListaCamposAfectadosInss listaCamposAfectados) {
		ArrayList vListaCamposAfectadosAux = listaCamposAfectados.getListaCamposAfectados();
		for (int i = 0; i < vListaCamposAfectadosAux.size(); i++) {
			this.vListaCamposAfectados.add(vListaCamposAfectadosAux.get(i));
		}
		ArrayList vIdCamposAux = listaCamposAfectados.getListaCodCampos();
		for (int i = 0; i < vIdCamposAux.size(); i++) {
			this.vIdCampos.add(vIdCamposAux.get(i));
		}
		return this.vListaCamposAfectados;
	}
}
