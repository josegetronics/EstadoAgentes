package sns.model.inss.dto;

public class DatosCoberturaSnsBean {

	protected boolean vacio = false;
	private java.lang.String codUsuarioSns;
	private java.lang.String codUsuarioSnsTitular;
	private java.lang.String naf;
	private java.lang.String nafTitular;
	private java.lang.String interno;
	private java.math.BigDecimal codTitulo;

	public DatosCoberturaSnsBean() {
		this.vacio = true;
	}

	public DatosCoberturaSnsBean(java.util.HashMap hd) {
		this.vacio = false;
		this.codUsuarioSns = (java.lang.String) hd.get("COD_USUARIO_SNS");
		this.codUsuarioSnsTitular = (java.lang.String) hd.get("COD_USUARIO_SNS_TITULAR");
		this.naf = (java.lang.String) hd.get("NAF");
		this.nafTitular = (java.lang.String) hd.get("NAF_TITULAR");
		this.interno = (java.lang.String) hd.get("INTERNO");
		this.codTitulo = (java.math.BigDecimal) hd.get("COD_TITULO");
	}

	public DatosCoberturaSnsBean(java.sql.ResultSet rs) throws java.sql.SQLException {
		this.vacio = false;
		this.codUsuarioSns = rs.getString("COD_USUARIO_SNS");
		this.codUsuarioSnsTitular = rs.getString("COD_USUARIO_SNS_TITULAR");
		this.naf = rs.getString("NAF");
		this.nafTitular = rs.getString("NAF_TITULAR");
		this.interno = rs.getString("INTERNO");
		this.codTitulo = rs.getBigDecimal("COD_TITULO");
	}

	public java.math.BigDecimal getCodTitulo() {
		return codTitulo;
	}

	public void setCodTitulo(java.math.BigDecimal codTitulo) {
		this.codTitulo = codTitulo;
		this.vacio = false;
	}

	public java.lang.String getCodUsuarioSns() {
		return this.codUsuarioSns;
	}

	public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
		this.vacio = false;
	}

	public java.lang.String getCodUsuarioSnsTitular() {
		return this.codUsuarioSnsTitular;
	}

	public void setCodUsuarioSnsTitular(java.lang.String codUsuarioSnsTitular) {
		this.codUsuarioSnsTitular = codUsuarioSnsTitular;
		this.vacio = false;
	}

	public java.lang.String getNaf() {
		return this.naf;
	}

	public void setNaf(java.lang.String naf) {
		this.naf = naf;
		this.vacio = false;
	}

	public java.lang.String getNafTitular() {
		return this.nafTitular;
	}

	public void setNafTitular(java.lang.String nafTitular) {
		this.nafTitular = nafTitular;
		this.vacio = false;
	}

	public java.lang.String getInterno() {
		return this.interno;
	}

	public void setInterno(java.lang.String interno) {
		this.interno = interno;
		this.vacio = false;
	}

	public boolean isVacio() {
		return this.vacio;
	}
}
