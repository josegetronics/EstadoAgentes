package sns.model.inss.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import sns.model.inss.IInss;
import sns.util.Misc;

public class InssPasoTitBenExt extends InssPasoTitBen implements IInss {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5087688412131473334L;

	public InssPasoTitBenExt() {
		super();
	}

	public InssPasoTitBenExt(HashMap hd) {
		super(hd);
	}

	public InssPasoTitBenExt(ResultSet rs) throws SQLException {
		super(rs);
	}

	public String getCodTipoMotivoBaja() {
		return super.getMotivoBaja();
	}

	public String getFechaNac() {
		return super.getFechaNacimientoRaw();
	}

	public String getCodSexo() {
		return Misc.nz(super.getSexo());
	}

}
