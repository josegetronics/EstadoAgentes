package sns.model.inss.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import sns.model.inss.IInss;
import sns.util.Misc;

public class ResultadoInssExtBean extends ResultadoInssBean implements IInss {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4281444161121032542L;
	private java.lang.String codUsuarioSns;
	private java.lang.String criterioIdentificacionSns;
	private String nafIgualEnInss;

	public java.lang.String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(java.lang.String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public java.lang.String getCriterioIdentificacionSns() {
		return criterioIdentificacionSns;
	}

	public String getNafIgualEnInss() {
		return nafIgualEnInss;
	}

	public void setNafIgualEnInss(String nafIgualEnInss) {
		this.nafIgualEnInss = nafIgualEnInss;
	}

	public void setCriterioIdentificacionSns(java.lang.String criterioIdentificacionSns) {
		this.criterioIdentificacionSns = criterioIdentificacionSns;
	}

	public ResultadoInssExtBean() {
		super();
	}

	public ResultadoInssExtBean(HashMap hd) {
		super(hd);
		this.codUsuarioSns = (java.lang.String) hd.get("COD_USUARIO_SNS");
		this.criterioIdentificacionSns = (java.lang.String) hd.get("CRITERIO_IDENTIFICACION_SNS");
	}

	public ResultadoInssExtBean(ResultSet rs) throws SQLException {
		super(rs);
		this.codUsuarioSns = rs.getString("COD_USUARIO_SNS");
		this.criterioIdentificacionSns = rs.getString("CRITERIO_IDENTIFICACION_SNS");
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoInssExtBean [codUsuarioSns=");
		buffer.append(codUsuarioSns);
		buffer.append(", criterioIdentificacionSns=");
		buffer.append(criterioIdentificacionSns);
		buffer.append(", getNafIdentificado()=");
		buffer.append(getNafIdentificado());
		buffer.append(", getApellido1()=");
		buffer.append(getApellido1());
		buffer.append(", getApellido2()=");
		buffer.append(getApellido2());
		buffer.append(", getApellidosNombre()=");
		buffer.append(getApellidosNombre());
		buffer.append(", getCodIndicadorDeFarmacia()=");
		buffer.append(getCodIndicadorDeFarmacia());
		buffer.append(", getCodSituacion()=");
		buffer.append(getCodSituacion());
		buffer.append(", getCodSubindicadorDeFarmacia()=");
		buffer.append(getCodSubindicadorDeFarmacia());
		buffer.append(", getCodTipoAsegurado()=");
		buffer.append(getCodTipoAsegurado());
		buffer.append(", getCodTipoBeneficiario()=");
		buffer.append(getCodTipoBeneficiario());
		buffer.append(", getDniNie()=");
		buffer.append(getDniNie());
		buffer.append(", getDomicilio()=");
		buffer.append(getDomicilio());
		buffer.append(", getFechaEfectoSituacion()=");
		buffer.append(getFechaEfectoSituacion());
		buffer.append(", getFechaNacimiento()=");
		buffer.append(getFechaNacimiento());
		buffer.append(", getFechaNacimientoRaw()=");
		buffer.append(getFechaNacimientoRaw());
		buffer.append(", getIdInss()=");
		buffer.append(getIdInss());
		buffer.append(", getIndicativoDomicilio()=");
		buffer.append(getIndicativoDomicilio());
		buffer.append(", getIndicativoNombre()=");
		buffer.append(getIndicativoNombre());
		buffer.append(", getIpf()=");
		buffer.append(getIpf());
		buffer.append(", getIpfTitular()=");
		buffer.append(getIpfTitular());
		buffer.append(", getNacionalidad()=");
		buffer.append(getNacionalidad());
		buffer.append(", getNaf()=");
		buffer.append(getNaf());
		buffer.append(", getNafSec1()=");
		buffer.append(getNafSec1());
		buffer.append(", getNafSec2()=");
		buffer.append(getNafSec2());
		buffer.append(", getNafSec3()=");
		buffer.append(getNafSec3());
		buffer.append(", getNafSec4()=");
		buffer.append(getNafSec4());
		buffer.append(", getNafSec5()=");
		buffer.append(getNafSec5());
		buffer.append(", getNafSec6()=");
		buffer.append(getNafSec6());
		buffer.append(", getNafSec7()=");
		buffer.append(getNafSec7());
		buffer.append(", getNafSec8()=");
		buffer.append(getNafSec8());
		buffer.append(", getNafSec9()=");
		buffer.append(getNafSec9());
		buffer.append(", getNafTitular()=");
		buffer.append(getNafTitular());
		buffer.append(", getNombre()=");
		buffer.append(getNombre());
		buffer.append(", getNumeroSecuencia()=");
		buffer.append(getNumeroSecuencia());
		buffer.append(", getPasaporte()=");
		buffer.append(getPasaporte());
		buffer.append(", getSexo()=");
		buffer.append(getSexo());
		buffer.append(", getTipoAseguramiento()=");
		buffer.append(getTipoAseguramiento());
		buffer.append(", isVacio()=");
		buffer.append(isVacio());
		buffer.append("]");
		return buffer.toString();
	}

	public String getCodSexo() {
		return Misc.nz(super.getSexo());
	}

	public String getFechaNac() {
		return Misc.nz(super.getFechaNacimientoRaw());
	}

}
