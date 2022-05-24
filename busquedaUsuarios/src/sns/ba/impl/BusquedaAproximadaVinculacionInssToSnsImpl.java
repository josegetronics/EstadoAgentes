/**
 * 
 */
package sns.ba.impl;

import java.util.Properties;

import sns.ba.config.ConstantesBA;
import sns.ba.config.InicializacionBA;

/**
 * @author igarcias
 *
 */
public class BusquedaAproximadaVinculacionInssToSnsImpl extends BaseBusquedaAproximadaImpl {

	@Override
	protected Properties getConfiguracion() {
		return InicializacionBA.MAP_PROPERTIES.get(ConstantesBA.BA_CONFIG_VINCULACION_INSS_TO_SNS);
	}
}
