package sns.model.inss.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ResultadoCruceSnsBean extends DatosSnsBean {
	private String criterioIdentificacion;

	public ResultadoCruceSnsBean() {
		super();
	}

	public ResultadoCruceSnsBean(HashMap hd) {
		super(hd);
	}

	public ResultadoCruceSnsBean(ResultSet rs) throws SQLException {
		super(rs);
	}

	public String getCriterioIdentificacion() {
		return criterioIdentificacion;
	}

	public void setCriterioIdentificacion(String criterioIdentificacion) {
		this.criterioIdentificacion = criterioIdentificacion;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoCruceSnsBean [criterioIdentificacion=");
		buffer.append(criterioIdentificacion);
		buffer.append(", getApellido1()=");
		buffer.append(getApellido1());
		buffer.append(", getApellido2()=");
		buffer.append(getApellido2());
		buffer.append(", getCodEstado()=");
		buffer.append(getCodEstado());
		buffer.append(", getCodIndicadorDeFarmacia()=");
		buffer.append(getCodIndicadorDeFarmacia());
		buffer.append(", getCodPrestacionServicio()=");
		buffer.append(getCodPrestacionServicio());
		buffer.append(", getCodSubindicadorDeFarmacia()=");
		buffer.append(getCodSubindicadorDeFarmacia());
		buffer.append(", getCodTipoProcedencia()=");
		buffer.append(getCodTipoProcedencia());
		buffer.append(", getCodUsuarioSns()=");
		buffer.append(getCodUsuarioSns());
		buffer.append(", getCodUsuarioSnsTitular()=");
		buffer.append(getCodUsuarioSnsTitular());
		buffer.append(", getDniNie()=");
		buffer.append(getDniNie());
		buffer.append(", getInterno()=");
		buffer.append(getInterno());
		buffer.append(", getNaf()=");
		buffer.append(getNaf());
		buffer.append(", getNombre()=");
		buffer.append(getNombre());
		buffer.append(", getPasaporte()=");
		buffer.append(getPasaporte());
		buffer.append(", isVacio()=");
		buffer.append(isVacio());
		buffer.append("]");
		return buffer.toString();
	}

}
