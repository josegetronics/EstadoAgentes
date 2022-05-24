package sns.model.inss;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import sns.config.Constantes;
import sns.model.inss.dto.DatosSnsBean;
import sns.model.inss.dto.InssCargaSns;
import sns.model.inss.impl.ResultadoInssExtBean;

public class InssCargaSnsExt extends InssCargaSns {

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("InssCargaSnsExt [toString()=");
		buffer.append(super.toString());
		buffer.append("]");
		return buffer.toString();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5544226968173787554L;

	private ResultadoInssExtBean resultadoInssExtBean;
	
	public InssCargaSnsExt() {
		this.resultadoInssExtBean=new ResultadoInssExtBean();
	}

	public InssCargaSnsExt(HashMap hd) {
		super(hd);
		this.resultadoInssExtBean=new ResultadoInssExtBean(hd);
	}

	public InssCargaSnsExt(ResultSet rs) throws SQLException {
		super(rs);
		this.resultadoInssExtBean=new ResultadoInssExtBean(rs);
	}

	public DatosSnsBean getDatosSns() {
		DatosSnsBean datosSnsBean=new DatosSnsBean();
		datosSnsBean.setApellido1(getApellido1Sns());
		datosSnsBean.setApellido2(getApellido2Sns());
		datosSnsBean.setCodAgente(getCodAgente());
		datosSnsBean.setCodEstado(getCodEstado());
		datosSnsBean.setCodIndicadorDeFarmacia(getCodIndicadorDeFarmaciaSns());
		datosSnsBean.setCodPrestacionServicio(getCodPrestacionServicio());
		datosSnsBean.setCodSubindicadorDeFarmacia(getCodSubindicadorDeFarSns());
		datosSnsBean.setCodTipoProcedencia(getCodTipoProcedencia());
		datosSnsBean.setCodTitulo(getCodTitulo());
		datosSnsBean.setCodUsuarioSns(getCodUsuarioSns());
		datosSnsBean.setCodUsuarioSnsTitular(getCodUsuarioSnsTitular());
		datosSnsBean.setDniNie(getDniNieSns());
		datosSnsBean.setIdEnSsalud(getIdEnSsalud());
		datosSnsBean.setInterno(Constantes.USUARIO_REAL);
		datosSnsBean.setNaf(getNafSns());
		datosSnsBean.setNafTitular(getNafTitularSns());
		datosSnsBean.setNombre(getNombreSns());
		datosSnsBean.setPasaporte(getPasaporteSns());
		return datosSnsBean;
	}

	public ResultadoInssExtBean getResultadoInssExtBean() {
		return resultadoInssExtBean;
	}
	
}
