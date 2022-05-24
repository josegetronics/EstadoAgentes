package sns.util;

import sns.config.Inicializacion;
import sns.util.impl.OperacionesNifAntiguoImpl;
import sns.util.impl.OperacionesNifNuevoImpl;

public class OperacionesNifFactory {

	public static IOperacionesNif getInstance() {
		if (Inicializacion.ACTIVADO_NIE_SIETE) {
			return new OperacionesNifNuevoImpl();
		}else{
			return new OperacionesNifAntiguoImpl();
		}
	}
}
