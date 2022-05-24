package sns.model.tgss;

import java.util.HashMap;

public class SegSocialNafTitularBean extends SegSocialBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8288202524076676281L;
	private String nafTitular;

	public SegSocialNafTitularBean() {
		super();
	}
	public SegSocialNafTitularBean(HashMap hd) {
		super(hd);
		this.nafTitular=getNaf();
	}
	public String getNafTitular() {
		return nafTitular;
	}
	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}
}
