package sns.model.inss.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import sns.util.Misc;

public class DatosSnsExtBean extends DatosSnsBean {

	private String codSexo;
	private Timestamp fechaNacimiento;
	private String codComunidad;
	private String paisNac;
	
	public DatosSnsExtBean() {
	}

	public DatosSnsExtBean(HashMap hd) {
		super(hd);
		this.codSexo=Misc.nz(hd.get("COD_SEXO"));
		this.fechaNacimiento=(Timestamp)hd.get("FECHA_NAC");
		this.codComunidad=Misc.nz(hd.get("COD_COMUNIDAD"));
		this.paisNac=Misc.nz(hd.get("COD_PAIS"));
		
	}

	public DatosSnsExtBean(ResultSet rs) throws SQLException {
		super(rs);
		this.codSexo=Misc.nz(rs.getString("COD_SEXO"));
		this.fechaNacimiento=rs.getTimestamp("FECHA_NAC");
		this.codComunidad=Misc.nz(rs.getString("COD_COMUNIDAD"));
		this.paisNac=Misc.nz(rs.getString("COD_PAIS"));
	}

	public String getCodSexo() {
		return codSexo;
	}

	public void setCodSexo(String codSexo) {
		this.codSexo = codSexo;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCodComunidad() {
		return codComunidad;
	}

	public void setCodComunidad(String codComunidad) {
		this.codComunidad = codComunidad;
	}

	public String getPaisNac() {
		return paisNac;
	}

	public void setPaisNac(String paisNac) {
		this.paisNac = paisNac;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DatosSnsExtBean [codComunidad=");
		buffer.append(codComunidad);
		buffer.append(", codSexo=");
		buffer.append(codSexo);
		buffer.append(", fechaNacimiento=");
		buffer.append(fechaNacimiento);
		buffer.append(", paisNac=");
		buffer.append(paisNac);
		buffer.append(", getApellido1()=");
		buffer.append(getApellido1());
		buffer.append(", getApellido2()=");
		buffer.append(getApellido2());
		buffer.append(", getCodAgente()=");
		buffer.append(getCodAgente());
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
		buffer.append(", getCodUsuarioSnsFarmacia()=");
		buffer.append(getCodUsuarioSnsFarmacia());
		buffer.append(", getDniNie()=");
		buffer.append(getDniNie());
		buffer.append(", getIdEnSsalud()=");
		buffer.append(getIdEnSsalud());
		buffer.append(", getNombre()=");
		buffer.append(getNombre());
		buffer.append(", getPasaporte()=");
		buffer.append(getPasaporte());
		buffer.append(", getCodTitulo()=");
		buffer.append(getCodTitulo());
		buffer.append(", getCodUsuarioSns()=");
		buffer.append(getCodUsuarioSns());
		buffer.append(", getCodUsuarioSnsTitular()=");
		buffer.append(getCodUsuarioSnsTitular());
		buffer.append(", getInterno()=");
		buffer.append(getInterno());
		buffer.append(", getNaf()=");
		buffer.append(getNaf());
		buffer.append(", getNafTitular()=");
		buffer.append(getNafTitular());
		buffer.append("]");
		return buffer.toString();
	}
}
