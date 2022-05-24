package sns.comun.bean.busqueda;

import java.util.ArrayList;

import sns.comun.config.ConstantesBusqueda;


public class ConsultaBean {
	
	protected	String nombre              = "";
	protected	String apellido1           = "";
	protected	String apellido2           = "";
	protected	String codSexo             = "";
	protected	String fechaNac            = "";
	//
	protected	String dniNie             = "";
	protected	String pasaporte          = "";
	protected	String naf                = "";
	protected	String nafTitular         = "";
	protected	String idssalud           = "";
	//
	private	ArrayList <String> arrayListNafSec = null;
	
	
	public ConsultaBean (){
	}
	
	public ConsultaBean (String nombre, String apellido1, String apellido2, String codSexo, String fechaNac){
		//
		this.nombre              = nombre;
		this.apellido1           = apellido1;
		this.apellido2           = apellido2;
		this.codSexo             = codSexo;
		this.fechaNac            = fechaNac;
		//
		arrayListNafSec          = new ArrayList <String> ();
	}
	
	public ConsultaBean (String nombre, String apellido1, String apellido2, String codSexo, String fechaNac, String dniNie, String naf){
		//
		this.nombre              = nombre;
		this.apellido1           = apellido1;
		this.apellido2           = apellido2;
		this.codSexo             = codSexo;
		this.fechaNac            = fechaNac;
		//
		this.dniNie              = dniNie;
		this.naf                 = naf;
		//
		arrayListNafSec          = new ArrayList <String> ();
	}

	public ConsultaBean (ArrayList <String> arrayListNafSec, String nombre, String apellido1, String apellido2, String codSexo, String fechaNac){
		//
		this.nombre              = nombre;
		this.apellido1           = apellido1;
		this.apellido2           = apellido2;
		this.codSexo             = codSexo;
		this.fechaNac            = fechaNac;
		//
		this.arrayListNafSec     = arrayListNafSec;
	}
	
	public ConsultaBean (ArrayList <String> arrayListNafSec, String nombre, String apellido1, String apellido2, String codSexo, String fechaNac, String dniNie, String naf){
		//
		this.nombre              = nombre;
		this.apellido1           = apellido1;
		this.apellido2           = apellido2;
		this.codSexo             = codSexo;
		this.fechaNac            = fechaNac;
		//
		this.dniNie              = dniNie;
		this.naf                 = naf;
		//
		this.arrayListNafSec     = arrayListNafSec;
	}
	
	public void incluirCampos (String dniNie, String naf){
		this.dniNie              = dniNie;
		this.naf                 = naf;
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

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public ArrayList<String> getArrayListNafSec() {
		return arrayListNafSec;
	}

	public void setArrayListNafSec(ArrayList<String> arrayListNafSec) {
		this.arrayListNafSec = arrayListNafSec;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getNafTitular() {
		return nafTitular;
	}

	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}

	
	public String write (){
		//
		StringBuffer str = new StringBuffer ();
		str.append(dniNie      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2   + ConstantesBusqueda.SEPARADOR_CAMPOS);	
		str.append(codSexo     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNac    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}
	
		
}
