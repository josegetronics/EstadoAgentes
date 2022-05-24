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
public class BusquedaAproximadaTraspasosImpl extends BaseBusquedaAproximadaImpl {

	@Override
	protected Properties getConfiguracion() {
		return InicializacionBA.MAP_PROPERTIES.get(ConstantesBA.BA_CONFIG_TRASPASOS);
	}
}
