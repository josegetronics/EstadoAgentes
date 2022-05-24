package sns.model.inss.dto;

import java.math.BigDecimal;

public interface IDatosSns {
	public String getDniNie();
	public String getPasaporte();
	public String getCodIndicadorDeFarmacia();
	public String getCodSubindicadorDeFarmacia();
	public BigDecimal getCodTipoProcedencia();
	public BigDecimal getCodEstado();
	public String getCodUsuarioSns();
	public String getCodUsuarioSnsTitular();
	public String getCodUsuarioSnsFarmacia();
	public String getNaf();
	public String getNafTitular();
	public BigDecimal getCodTitulo();
	public String getNombre();
	public String getApellido1();
	public String getApellido2();
	public String getIdEnSsalud();
	public BigDecimal getCodAgente();
	
}
