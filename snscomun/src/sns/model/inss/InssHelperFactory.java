package sns.model.inss;

import sns.model.inss.impl.InssHelperImpl;

public class InssHelperFactory {

	public static IInssHelper getInstance() {
		return new InssHelperImpl();
	}
}
