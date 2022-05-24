package sns.comun.bean.duplicados;

import gasai.util.Misc;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class EstudioDuplicadosBean extends ConsultaBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	private String idInss         = "";
	private String codUsuariosSns = "";
	private String nombre         = "";
	private String apellido1      = "";
	private String apellido2      = "";
	private String sexo           = "";
	private String fechaNac       = "";
	private String naf            = "";
	private String dniNie         = "";
	
	
	public EstudioDuplicadosBean() {
	}
		
	public EstudioDuplicadosBean (ResultSet resultSet) throws Exception {
		idInss         = Misc.nz(resultSet.getString("ID_INSS"));
		codUsuariosSns = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
		nombre         = Misc.nz(resultSet.getString("NOMBRE"));
		apellido1      = Misc.nz(resultSet.getString("APELLIDO1"));
		apellido2      = Misc.nz(resultSet.getString("APELLIDO2"));
		sexo           = Misc.nz(resultSet.getString("SEXO"));
		fechaNac       = Misc.nz(resultSet.getString("FECHA_NAC"));
		naf            = Misc.nz(resultSet.getString("NAF"));
		dniNie         = Misc.nz(resultSet.getString("DNI_NIE"));
	}

	
	public void view (){
		logger.debug("idInss: " + idInss + ", codUsuariosSns: " + codUsuariosSns);
		logger.debug("nombre: " + nombre + ", apellido1: " + apellido1 + ", apellido2: " + apellido2);
		logger.debug("codSexo: " + codSexo + ", fechaNac: " + fechaNac);
		logger.debug("dniNie: " + dniNie + ", naf: " + naf);
	}
	
	
	public String toString () {
		StringBuffer str = new StringBuffer ();
		str.append(idInss        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codUsuariosSns + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(sexo       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNac      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(dniNie        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(naf           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		return str.toString();
	}

	
	public String getIdInss() {
		return idInss;
	}

	public void setIdInss(String idInss) {
		this.idInss = idInss;
	}

	public String getCodUsuariosSns() {
		return codUsuariosSns;
	}

	public void setCodUsuariosSns(String codUsuariosSns) {
		this.codUsuariosSns = codUsuariosSns;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}	
		
}

