package sns.comun.bean.indef;

import gasai.util.Misc;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class IndefBean {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	private String nombre = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String codSexo = "";
	private String fechaNacimiento = "";
	private String year = "";
	private String mes  = "";
	private String dia  = "";
	private String dniNie = "";

	
	public IndefBean () {
	}
	
	
	public IndefBean (HashMap <String, String> map) throws Exception {
		//
		try {
			dniNie            = Misc.nz(map.get("DNI"));
			//
			nombre            = Misc.nz(map.get("NOMB"));
			apellido1         = Misc.nz(map.get("APE1"));
			apellido2         = Misc.nz(map.get("APE2"));
			//
			year              = Misc.nz(map.get("ANON"));
			mes               = Misc.nz(map.get("MESN"));
			dia               = Misc.nz(map.get("DIAN"));
			//			
			fechaNacimiento   = year + "-" + mes + "-" + dia;
			//
			codSexo           = Misc.nz(map.get("SEXO"));
			//
			if(codSexo.equals("M")) {
				codSexo           = "2";
			}
			else {
				codSexo           = "1";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}
	

	public IndefBean (ResultSet resulSet) throws Exception {
		dniNie            = Misc.nz(resulSet.getString("DNI"));
		//
		nombre            = Misc.nz(resulSet.getString("NOMB"));
		apellido1         = Misc.nz(resulSet.getString("APE1"));
		apellido2         = Misc.nz(resulSet.getString("APE2"));
		//
		year              = Misc.nz(resulSet.getString("ANON"));
		mes               = Misc.nz(resulSet.getString("MESN"));
		dia               = Misc.nz(resulSet.getString("DIAN"));
		//			
		fechaNacimiento   = year + "-" + mes + "-" + dia;
		//
		codSexo           = Misc.nz(resulSet.getString("SEXO"));
		//
		if(codSexo.equals("M")) {
			codSexo           = "2";
		}
		else {
			codSexo           = "1";
		}

	}


	public String toString () {
		//
		StringBuffer str = new StringBuffer ();
		str.append(dniNie          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2       + ConstantesBusqueda.SEPARADOR_CAMPOS);	
		str.append(codSexo         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNacimiento + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
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


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getMes() {
		return mes;
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public String getDniNie() {
		return dniNie;
	}


	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}	
	
	

}
