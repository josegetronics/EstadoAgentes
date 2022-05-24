package sns.model.inss.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import sns.model.inss.dto.InssBean;

public class ResultadoInssBean extends InssBean /* implements IResultadoInss */{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7482715386952620184L;
	private String nafIdentificado;

	public ResultadoInssBean() {
		super();
	}

	public ResultadoInssBean(HashMap hd) {
		super(hd);
	}

	public ResultadoInssBean(ResultSet rs) throws SQLException {
		super(rs);
	}

	public String getNafIdentificado() {
		return nafIdentificado;
	}

	public void setNafIdentificado(String nafIdentificado) {
		this.nafIdentificado = nafIdentificado;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("ResultadoInssBean [nafIdentificado=");
		buffer.append(nafIdentificado);
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

}
