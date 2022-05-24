package sns.model.inss.impl;

import gasai.bd.AccesoBD;
import gasai.util.Misc;
import sns.bd.AccesoBd;
import sns.bd.AccesoBdTx;
import sns.config.Inicializacion;
import sns.model.inss.IInssHelper;

public class InssHelperImpl extends BaseInssHelperImpl implements IInssHelper {

	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(InssHelperImpl.class);

	public InssHelperImpl() {
	}
	
	protected org.apache.log4j.Logger getLogger() {
		return logger;
	}

	protected String getNombreTablaInss() {
		return Inicializacion.NOMBRE_TABLA_INSS;
	}

	protected String getNombreTablaInssSns() {
		return Inicializacion.NOMBRE_TABLA_INSS_SNS;
	}
	protected String getNombreTablaInssBajas() {
		return Inicializacion.NOMBRE_TABLA_INSS_BAJAS;
	}

	protected AccesoBD getAccesoBd() {
		return new AccesoBd();
	}

	protected AccesoBD getAccesoBdTx() {
		return new AccesoBdTx();
	}

	protected String getTituloByTipoAseguramiento(String tipoAseguramiento) {
		return Misc.nz(Inicializacion.TIPO_ASEGURAMIENTO_TITULO.get(tipoAseguramiento));
	}
}
