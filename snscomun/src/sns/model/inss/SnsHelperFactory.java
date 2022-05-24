package sns.model.inss;

import sns.model.inss.impl.SnsHelperImpl;

public class SnsHelperFactory {

	public static ISnsHelper getInstance() {
		return new SnsHelperImpl();
	}
}
