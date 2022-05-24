package sns.ba;

import sns.ba.impl.BusquedaAproximadaTraspasosImpl;
import sns.ba.impl.BusquedaAproximadaVinculacionInssToSnsImpl;
import sns.ba.impl.BusquedaAproximadaVinculacionSnsToInssImpl;

public class BusquedaAproximadaFactory {
	/**
	 * crea una instancia IBusquedaAvanzada para la funcionalidad de bloqueo de traspaso
	 * @return
	 */
	public static IBusquedaAproximada getInstanceParaTraspaso() {
		return new BusquedaAproximadaTraspasosImpl();
	}

	/**
	 * crea una instancia IBusquedaAvanzada para la funcionalidad de vinculacion de un registro del INSS con uno del SNS
	 * @return
	 */
	public static IBusquedaAproximada getInstanceParaVinculacionInssToSns() {
		return new BusquedaAproximadaVinculacionInssToSnsImpl();
	}

	/**
	 * crea una instancia IBusquedaAvanzada para la funcionalidad de vinculacion de un registro del SNS con uno del INSS
	 * @return
	 */
	public static IBusquedaAproximada getInstanceParaVinculacionSnsToInss() {
		return new BusquedaAproximadaVinculacionSnsToInssImpl();
	}
}
