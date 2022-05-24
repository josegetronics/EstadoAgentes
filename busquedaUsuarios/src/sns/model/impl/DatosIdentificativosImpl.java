package sns.model.impl;

import java.sql.Date;
import sns.model.IDatosIdentificativos;


public class DatosIdentificativosImpl implements IDatosIdentificativos {

	private String  nombre;
	private String  apellido1;
	private String  apellido2;
	private String  codSexo;
	private Date    fechaNac;
	private String  dniNie;
	private String  pasaporte;
	private String  naf;
	private String  nafTitular;

	
	public DatosIdentificativosImpl () {		
	}
	
	
	public DatosIdentificativosImpl (String nombre, String apellido1, String apellido2, String codSexo, Date fechaNac, String dniNie, String pasaporte, String naf, String nafTitular) {
		this.nombre     =  nombre;
		this.apellido1  =  apellido1;
		this.apellido2  =  apellido2;
		this.codSexo    =  codSexo;
		this.fechaNac   =  fechaNac;
		this.dniNie     =  dniNie;
		this.pasaporte  =  pasaporte;
		this.naf        =  naf;
		this.nafTitular = nafTitular;
	}
	
	
	public String getNafTitular() {
		return nafTitular;
	}

	public String getNaf() {
		return naf;
	}

	public String getDniNie() {
		return dniNie;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getCodSexo() {
		return codSexo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

}
