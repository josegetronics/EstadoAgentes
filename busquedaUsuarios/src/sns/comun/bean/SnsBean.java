package sns.comun.bean;

import gasai.util.Misc;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class SnsBean {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	private String codUsuario = "";
	private String codEstado = "";
	private String idssalud = "";
	private String codCaIso = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String nombre = "";
	private String codSexo = "";
	private String fechaNacimiento = "";
	private String fechaUltimaActualizacion = "";
	private String dniNie = "";
	private String pasaporte = "";
	private String naf = "";
	private String nafTitular = "";
	private String codTitulo = "";
	private String codSituacion = "";
	private String raiz = "";
	private String codIndicadorFarmacia = "";
	private String codSubindicadorFarmacia = "";
	private String codProcedencia = "";

	
	public SnsBean () {
	}
	
	
	public SnsBean (ResultSet resultSet) throws Exception {
		//
		try {
			codUsuario               = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
			codCaIso                 = Misc.nz(resultSet.getString("CODCAISO"));
			codEstado                = Misc.nz(resultSet.getString("COD_ESTADO"));
			//
			//fechaUltimaActualizacion = Misc.nz(resultSet.getString("FECHA_ULT_ACTUALIZACION"));
			//			
			idssalud                 = Misc.nz(resultSet.getString("ID_EN_SSALUD"));
			dniNie                   = Misc.nz(resultSet.getString("DNI_NIE"));
			pasaporte                = Misc.nz(resultSet.getString("PASAPORTE"));
			nombre                   = Misc.nz(resultSet.getString("NOMBRE"));
			apellido1                = Misc.nz(resultSet.getString("APELLIDO1"));
			apellido2                = Misc.nz(resultSet.getString("APELLIDO2"));
			naf                      = Misc.nz(resultSet.getString("NAF"));
			nafTitular               = Misc.nz(resultSet.getString("NAF_TITULAR"));
			codSexo                  = Misc.nz(resultSet.getString("COD_SEXO"));
			fechaNacimiento          = Misc.nz(resultSet.getString("FECHA_NAC"));
			codTitulo                = Misc.nz(resultSet.getString("COD_TITULO"));
			codSituacion             = Misc.nz(resultSet.getString("COD_SITUACION"));
			raiz                     = Misc.nz(resultSet.getString("RAIZ"));			
			//
			codIndicadorFarmacia     = Misc.nz(resultSet.getString("COD_INDICADOR_DE_FARMACIA"));
			codSubindicadorFarmacia  = Misc.nz(resultSet.getString("COD_SUBINDICADOR_DE_FARMACIA"));
			codProcedencia           = Misc.nz(resultSet.getString("COD_TIPO_PROCEDENCIA"));		
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	
	
	
	public SnsBean (HashMap <String, String> map) throws Exception {
		//
		try {
			codUsuario               = Misc.nz(map.get("COD_USUARIO_SNS"));
			codCaIso                 = Misc.nz(map.get("CODCAISO"));
			codEstado                = Misc.nz(map.get("COD_ESTADO"));
			//
			fechaUltimaActualizacion = Misc.nz(map.get("FECHA_ULT_ACTUALIZACION"));
			//		
			idssalud                 = Misc.nz(map.get("ID_EN_SSALUD"));
			dniNie                   = Misc.nz(map.get("DNI_NIE"));
			pasaporte                = Misc.nz(map.get("PASAPORTE"));
			nombre                   = Misc.nz(map.get("NOMBRE"));
			apellido1                = Misc.nz(map.get("APELLIDO1"));
			apellido2                = Misc.nz(map.get("APELLIDO2"));
			naf                      = Misc.nz(map.get("NAF"));
			nafTitular               = Misc.nz(map.get("NAF_TITULAR"));
			codSexo                  = Misc.nz(map.get("COD_SEXO"));
			fechaNacimiento          = Misc.nz(map.get("FECHA_NAC"));
			codTitulo                = Misc.nz(map.get("COD_TITULO"));
			codSituacion             = Misc.nz(map.get("COD_SITUACION"));
			raiz                     = Misc.nz(map.get("RAIZ"));
			//
			codIndicadorFarmacia     = Misc.nz(map.get("COD_INDICADOR_DE_FARMACIA"));
			codSubindicadorFarmacia  = Misc.nz(map.get("COD_SUBINDICADOR_DE_FARMACIA"));
			codProcedencia           = Misc.nz(map.get("COD_TIPO_PROCEDENCIA"));		
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	
	
	
	public SnsBean (String line) throws Exception {
		//
		try {
			//
			for (int i = 0; i < 19; i++) {
				//
				String value = line.substring(0, line.indexOf("|"));
				line = line.substring(line.indexOf("|") + 1);
				//
				switch (i) {
				//
				case 0:
					this.setCodUsuario(Misc.nz(value).trim());
					break;
				case 1:
					this.setCodEstado(Misc.nz(value).trim());
					break;
				case 2:
					this.setIdssalud(Misc.nz(value).trim());
					break;
				case 3:
					this.setCodCaIso(Misc.nz(value).trim());
					break;
				case 4:
					this.setApellido1(Misc.nz(value).trim());
					break;
				case 5:
					this.setApellido2(Misc.nz(value).trim());
					break;
				case 6:
					this.setNombre(Misc.nz(value).trim());
					break;
				case 7:
					this.setCodSexo(Misc.nz(value).trim());
					break;
				case 8:
					this.setFechaNacimiento(Misc.nz(value).trim());
					break;
				case 9:
					this.setRaiz(Misc.nz(value).trim());
					break;
				case 10:
					this.setDniNie(Misc.nz(value).trim());
					break;
				case 11:
					this.setPasaporte(Misc.nz(value).trim());
					break;
				case 12:
					this.setCodTitulo(Misc.nz(value).trim());
					break;
				case 13:
					this.setCodSituacion(Misc.nz(value).trim());
					break;
				case 14:
					this.setNaf(Misc.nz(value).trim());
					break;
				case 15:
					this.setNafTitular(Misc.nz(value).trim());
					break;
				case 16:
					this.setCodIndicadorFarmacia(Misc.nz(value).trim());
					break;
				case 17:
					this.setCodSubindicadorFarmacia(Misc.nz(value).trim());
					break;
				case 18:
					this.setCodProcedencia(Misc.nz(value).trim());
					break;
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			logger.error("line: " + line);
			throw e;
		}
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


	public String getIdssalud() {
		return idssalud;
	}


	public void setIdssalud(String idssalud) {
		this.idssalud = idssalud;
	}


	public String getCodCaIso() {
		return codCaIso;
	}


	public void setCodCaIso(String codCaIso) {
		this.codCaIso = codCaIso;
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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public String getNaf() {
		return naf;
	}


	public void setNaf(String naf) {
		this.naf = naf;
	}


	public String getNafTitular() {
		return nafTitular;
	}


	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}

	
	public String getCodTitulo() {
		return codTitulo;
	}

	public void setCodTitulo(String codTitulo) {
		this.codTitulo = codTitulo;
	}

	public String getC() {
		return codSituacion;
	}

	public void setCodSituacion(String codSituacion) {
		this.codSituacion = codSituacion;
	}

	public String getRaiz() {
		return raiz;
	}

	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}		
		
	public String getCodIndicadorFarmacia() {
		return codIndicadorFarmacia;
	}

	public void setCodIndicadorFarmacia(String codIndicadorFarmacia) {
		this.codIndicadorFarmacia = codIndicadorFarmacia;
	}

	public String getCodSubindicadorFarmacia() {
		return codSubindicadorFarmacia;
	}

	public void setCodSubindicadorFarmacia(String codSubindicadorFarmacia) {
		this.codSubindicadorFarmacia = codSubindicadorFarmacia;
	}

	public String getCodProcedencia() {
		return codProcedencia;
	}

	public void setCodProcedencia(String codProcedencia) {
		this.codProcedencia = codProcedencia;
	}

	public String getCodSituacion() {
		return codSituacion;
	}
	
	public String getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}


	public String carga () {
		//
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(Misc.nz(this.getCodUsuario())              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodEstado())               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getIdssalud())                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodCaIso())                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getFechaUltimaActualizacion())+ ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNombre())                  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getApellido1())               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getApellido2())               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodSexo())                 + ConstantesBusqueda.SEPARADOR_CAMPOS);		
		strBuffer.append(Misc.nz(this.getFechaNacimiento())         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getRaiz())                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getDniNie())                  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getPasaporte())               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNaf())                     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNafTitular())              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodTitulo())               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodIndicadorFarmacia())    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodSubindicadorFarmacia()) + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodProcedencia())          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return strBuffer.toString();
	}
	
	
	public String cabeceraToString () {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("COD_USUARIO_SNS" + ConstantesBusqueda.SEPARADOR_CAMPOS);         
		strBuffer.append("COD_ESTADO"      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("ID_EN_SSALUD"    + ConstantesBusqueda.SEPARADOR_CAMPOS);      
		strBuffer.append("COD_CA_ISO"      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		strBuffer.append("FECHA_ULT_ACTUALIZACION" + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		strBuffer.append("NOMBRE"          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("APELLIDO1"       + ConstantesBusqueda.SEPARADOR_CAMPOS);               
		strBuffer.append("APELLIDO2"       + ConstantesBusqueda.SEPARADOR_CAMPOS);               
		strBuffer.append("COD_SEXO"        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("FECHA_NAC"       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("RAIZ"            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("DNI_NIE"         + ConstantesBusqueda.SEPARADOR_CAMPOS);                 
		strBuffer.append("PASAPORTE"       + ConstantesBusqueda.SEPARADOR_CAMPOS);               
		strBuffer.append("NAF"             + ConstantesBusqueda.SEPARADOR_CAMPOS);                     
		strBuffer.append("NAF_TITULAR"     + ConstantesBusqueda.SEPARADOR_CAMPOS);		             
		strBuffer.append("COD_TITULO"      + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//
		strBuffer.append("COD_INDICADOR_DE_FARMACIA"    + ConstantesBusqueda.SEPARADOR_CAMPOS);		             
		strBuffer.append("COD_SUBINDICADOR_DE_FARMACIA" + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append("COD_TIPO_PROCEDENCIA"         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return strBuffer.toString();
	}
	
	
	public String toString() {
		//
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(Misc.nz(this.getCodUsuario())      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodEstado())       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getIdssalud())        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodCaIso())        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNombre())          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getApellido1())       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getApellido2())       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodSexo())         + ConstantesBusqueda.SEPARADOR_CAMPOS);		
		strBuffer.append(Misc.nz(this.getFechaNacimiento()) + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getRaiz())            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getDniNie())          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getPasaporte())       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNaf())             + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getNafTitular())      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodTitulo())       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodIndicadorFarmacia())    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodSubindicadorFarmacia()) + ConstantesBusqueda.SEPARADOR_CAMPOS);
		strBuffer.append(Misc.nz(this.getCodProcedencia())          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return strBuffer.toString();
	}
	

}
