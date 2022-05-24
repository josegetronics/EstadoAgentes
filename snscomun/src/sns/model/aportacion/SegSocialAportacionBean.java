package sns.model.aportacion;

import sns.model.tgss.SegSocialNafTitularBean;

public class SegSocialAportacionBean extends SegSocialNafTitularBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5284374889623264757L;
	private String codIndicadorDeFarmacia;
	private String codSubIndicadorDeFarmacia;
	private String codTitulo;

	public void setCodTitulo(String codTitulo) {
		this.codTitulo = codTitulo;
	}
	public String getCodTitulo() {
		return codTitulo;
	}
	public String getCodIndicadorDeFarmacia() {
		return codIndicadorDeFarmacia;
	}
	public void setCodIndicadorDeFarmacia(String codIndicadorDeFarmacia) {
		this.codIndicadorDeFarmacia = codIndicadorDeFarmacia;
	}
	public String getCodSubIndicadorDeFarmacia() {
		return codSubIndicadorDeFarmacia;
	}
	public void setCodSubIndicadorDeFarmacia(String codSubIndicadorDeFarmacia) {
		this.codSubIndicadorDeFarmacia = codSubIndicadorDeFarmacia;
	}	
}
