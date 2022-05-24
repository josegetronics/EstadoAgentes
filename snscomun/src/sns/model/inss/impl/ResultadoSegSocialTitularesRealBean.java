package sns.model.inss.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import sns.model.inss.IResultadoInss;
import sns.model.inss.dto.SegSocialTitularesRealBean;

public class ResultadoSegSocialTitularesRealBean extends SegSocialTitularesRealBean implements IResultadoInss {

	private String nafIdentificado;

	
	public ResultadoSegSocialTitularesRealBean() {
		super();
	}

	public ResultadoSegSocialTitularesRealBean(HashMap hd) {
		super(hd);
	}

	public ResultadoSegSocialTitularesRealBean(ResultSet rs) throws SQLException {
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
		buffer.append(", getCodSexo()=");
		buffer.append(getCodSexo());
		buffer.append(", getCodigoFarmacia()=");
		buffer.append(getCodigoFarmacia());
		buffer.append(", getCodigoLimiteMensual()=");
		buffer.append(getCodigoLimiteMensual());
		buffer.append(", getComunidadAutonoma()=");
		buffer.append(getComunidadAutonoma());
		buffer.append(", getDniNie()=");
		buffer.append(getDniNie());
		buffer.append(", getFechaNac()=");
		buffer.append(getFechaNac());
		buffer.append(", getGrupoAseguramiento()=");
		buffer.append(getGrupoAseguramiento());
		buffer.append(", getIdUnico()=");
		buffer.append(getIdUnico());
		buffer.append(", getIndicativoNombre()=");
		buffer.append(getIndicativoNombre());
		buffer.append(", getNacionalidad()=");
		buffer.append(getNacionalidad());
		buffer.append(", getNaf()=");
		buffer.append(getNaf());
		buffer.append(", getNafRepetido()=");
		buffer.append(getNafRepetido());
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
		buffer.append(", getNombre()=");
		buffer.append(getNombre());
		buffer.append(", getNumLinea()=");
		buffer.append(getNumLinea());
		buffer.append(", getNumeroIdentificador()=");
		buffer.append(getNumeroIdentificador());
		buffer.append(", getPasaporte()=");
		buffer.append(getPasaporte());
		buffer.append(", getRaizap()=");
		buffer.append(getRaizap());
		buffer.append(", getTipoIdentificador()=");
		buffer.append(getTipoIdentificador());
		buffer.append("]");
		return buffer.toString();
	}

	public String getCodIndicadorDeFarmacia() {
		return getCodigoFarmacia();
	}

	public String getCodSubindicadorDeFarmacia() {
		return getCodigoLimiteMensual();
	}

	public String getCodUsuarioSns() {
		return "";
	}

	public String getIpf() {
		return getTipoIdentificador() + getNumeroIdentificador();
	}

	public String getTipoAseguramiento() {
		return getGrupoAseguramiento();
	}

}
