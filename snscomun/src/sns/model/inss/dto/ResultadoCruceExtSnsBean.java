package sns.model.inss.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ResultadoCruceExtSnsBean extends ResultadoCruceSnsBean {

	private String codSexo;
	private String fechaNacimiento;
	//
	private int camposCoincidentes = 0;
	private String valorNafCoincidente = "";
	

	public ResultadoCruceExtSnsBean() {
		super();
	}

	
	public ResultadoCruceExtSnsBean(HashMap hd) {
		super(hd);
		this.codSexo         = (java.lang.String) hd.get("COD_SEXO");
		this.fechaNacimiento = (java.lang.String) hd.get("FECHA_NAC");
	}

	
	public ResultadoCruceExtSnsBean(ResultSet rs) throws SQLException {
		super(rs);
		this.codSexo         = rs.getString("COD_SEXO");
		this.fechaNacimiento = rs.getString("FECHA_NAC");
	}

	
	public String getCodSexo() {
		return codSexo;
	}

	public void setCodSexo(String codSexo) {
		this.codSexo = codSexo;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getCamposCoincidentes() {
		return camposCoincidentes;
	}


	public void setCamposCoincidentes(int camposCoincidentes) {
		this.camposCoincidentes = camposCoincidentes;
	}


	public String getValorNafCoincidente() {
		return valorNafCoincidente;
	}


	public void setValorNafCoincidente(String valorNafCoincidente) {
		this.valorNafCoincidente = valorNafCoincidente;
	}
	
	

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoCruceSnsBean [codSexo()=");
		buffer.append(getCodSexo());
		buffer.append(", fechaNacimiento()=");
		buffer.append(getFechaNacimiento());
		//
		buffer.append(", criterioIdentificacion()=");
		buffer.append(getCriterioIdentificacion());
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
