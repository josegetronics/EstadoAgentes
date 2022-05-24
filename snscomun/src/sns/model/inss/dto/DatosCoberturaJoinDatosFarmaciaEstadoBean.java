package sns.model.inss.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatosCoberturaJoinDatosFarmaciaEstadoBean extends DatosCoberturaJoinDatosFarmaciaBean {

	private java.math.BigDecimal codEstado;

	public DatosCoberturaJoinDatosFarmaciaEstadoBean() {
		super();
	}

	public DatosCoberturaJoinDatosFarmaciaEstadoBean(HashMap hd) {
		super(hd);
		this.codEstado = (java.math.BigDecimal) hd.get("COD_ESTADO");
	}

	public DatosCoberturaJoinDatosFarmaciaEstadoBean(ResultSet rs) throws SQLException {
		super(rs);
		this.codEstado = rs.getBigDecimal("COD_ESTADO");
	}

	public java.math.BigDecimal getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(java.math.BigDecimal codEstado) {
		this.codEstado = codEstado;
	}

}
