package sns.comun.bean.duplicados;

import gasai.util.Misc;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class SnsBean {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	private String codUsuario = "";
	private String codEstado = "";
	private String nombre = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String codSexo = "";
	private String fechaNacimiento = "";
	private String naf = "";
	private String dniNie = "";
	private String pasaporte = "";
	private String codCaIso = "";
	private String raiz = "";
	
	
	public SnsBean () {
	}
	
	
	public SnsBean (HashMap <String, String> map) throws Exception {
		//
		try {
			codUsuario               = Misc.nz(map.get("COD_USUARIO_SNS"));
			codCaIso                 = Misc.nz(map.get("CODCAISO"));
			codEstado                = Misc.nz(map.get("COD_ESTADO"));
			dniNie                   = Misc.nz(map.get("DNI_NIE"));
			pasaporte                = Misc.nz(map.get("PASAPORTE"));
			nombre                   = Misc.nz(map.get("NOMBRE"));
			apellido1                = Misc.nz(map.get("APELLIDO1"));
			apellido2                = Misc.nz(map.get("APELLIDO2"));
			naf                      = Misc.nz(map.get("NAF"));
			codSexo                  = Misc.nz(map.get("SEXO"));
			fechaNacimiento          = Misc.nz(map.get("FECHA_NAC"));
			raiz                     = Misc.nz(map.get("RAIZ"));
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}

	
	public String toString () {
		//
		StringBuffer str = new StringBuffer ();
		str.append(codUsuario      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codEstado       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codCaIso        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2       + ConstantesBusqueda.SEPARADOR_CAMPOS);	
		str.append(codSexo         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNacimiento + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(raiz            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(dniNie          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(pasaporte       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(naf             + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}	
	
	
	public String getCodUsuario() {
		return codUsuario;
	}


	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}


	public String getCodEstado() {
		return codEstado;
	}


	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
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


	public String getPasaporte() {
		return pasaporte;
	}


	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}


	public String getCodCaIso() {
		return codCaIso;
	}


	public void setCodCaIso(String codCaIso) {
		this.codCaIso = codCaIso;
	}


	public String getRaiz() {
		return raiz;
	}


	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}	
	
	

	



}
