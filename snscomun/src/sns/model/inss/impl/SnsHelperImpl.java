package sns.model.inss.impl;

import gasai.util.Misc;

import org.apache.log4j.Logger;

import sns.config.Inicializacion;
import sns.model.ITransaccionesHelper;
import sns.model.impl.TransaccionesHelperFake;
import sns.model.inss.ISnsHelper;

public class SnsHelperImpl extends BaseSnsHelperImpl implements ISnsHelper {

	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(SnsHelperImpl.class);
	static org.apache.log4j.Logger loggerSnsError = org.apache.log4j.Logger.getLogger("snsError");

	protected Logger getLogger() {
		return logger;
	}

	protected Logger getLoggerSnsError() {
		return loggerSnsError;
	}

	protected String getTituloPorTipoAseguramiento(String tipoAseguramiento) {
		return Misc.nz(Inicializacion.TIPO_ASEGURAMIENTO_TITULO.get(tipoAseguramiento));
	}

	public ITransaccionesHelper getTransaccionesHelper() {
		return new TransaccionesHelperFake();
	}

	protected String getCaIsoFromCaIne(String codCaIne) {
		return Misc.nz(Inicializacion.CA_CAISO.get(codCaIne));
	}
}
