package sns.comun.bean.beneficiarios;

import gasai.util.Misc;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class BeneficiarioBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
		
	private	String idBen                      = "";
	private	String dniNie                     = "";
	private	String naf                        = "";
	private	String nafTitular                 = "";
	private	String ipfTitular                 = "";
	private	String nombre                     = "";
	private	String apellido1                  = "";
	private	String apellido2                  = "";
	private	String sexo                       = "";
	private	String fechaNacimientoRaw         = "";
	private	String codIndicadorFarmacia       = "";
	private	String codSubindicadorFarmacia    = "";
	private	String tipoAseguramiento          = "";
	private	String codUsuarioSns              = "";
	private	String dniNieSns                  = "";
	private	String nafSns                     = "";
	private	String nafTitularSns              = "";
	private	String codUsuarioSnsTitular       = "";
	private	String nombreSns                  = "";
	private	String apellido1Sns               = "";
	private	String apellido2Sns               = "";
	private	String codSexoSns                 = "";
	private	String fechaNacSns                = "";
	private	String codIndicadorFarmaciaSns    = "";
	private	String codSubindicadorFarmaciaSns = "";
	private	String procedencia                = "";
	private	String codTitulo                  = "";
	
	
	public BeneficiarioBean() {
	}
	
		
	public BeneficiarioBean (ResultSet resulSet) throws Exception {
		//		
		idBen                     = Misc.nz(resulSet.getString("ID_BEN"));
		dniNie                     = Misc.nz(resulSet.getString("DNI_NIE"));                     
		naf                        = Misc.nz(resulSet.getString("NAF"));                         
		nafTitular                 = Misc.nz(resulSet.getString("NAF_TITULAR"));                 
		ipfTitular                 = Misc.nz(resulSet.getString("IPF_TITULAR"));                 
		nombre                     = Misc.nz(resulSet.getString("NOMBRE"));                      
		apellido1                  = Misc.nz(resulSet.getString("APELLIDO1"));                   
		apellido2                  = Misc.nz(resulSet.getString("APELLIDO2"));                   
		sexo                       = Misc.nz(resulSet.getString("SEXO"));                        
		fechaNacimientoRaw         = Misc.nz(resulSet.getString("FECHA_NACIMIENTO_RAW"));        
		codIndicadorFarmacia       = Misc.nz(resulSet.getString("COD_INDICADOR_DE_FARMACIA"));   
		codSubindicadorFarmacia    = Misc.nz(resulSet.getString("COD_SUBINDICADOR_DE_FARMACIA"));
		tipoAseguramiento          = Misc.nz(resulSet.getString("TIPO_ASEGURAMIENTO"));          
		codUsuarioSns              = Misc.nz(resulSet.getString("COD_USUARIO_SNS"));             
		dniNieSns                  = Misc.nz(resulSet.getString("DNI_NIE_SNS"));                 
		nafSns                     = Misc.nz(resulSet.getString("NAF_SNS"));                     
		nafTitularSns              = Misc.nz(resulSet.getString("NAF_TITULAR_SNS"));             
		codUsuarioSnsTitular       = Misc.nz(resulSet.getString("COD_USUARIO_SNS_TITULAR"));     
		nombreSns                  = Misc.nz(resulSet.getString("NOMBRE_SNS"));                  
		apellido1Sns               = Misc.nz(resulSet.getString("APELLIDO1_SNS"));               
		apellido2Sns               = Misc.nz(resulSet.getString("APELLIDO2_SNS"));               
		codSexoSns                 = Misc.nz(resulSet.getString("COD_SEXO_SNS"));                
		fechaNacSns                = Misc.nz(resulSet.getString("FECHA_NAC_SNS"));               
		codIndicadorFarmaciaSns    = Misc.nz(resulSet.getString("INDICADOR_DE_FARMACIA_SNS"));   
		codSubindicadorFarmaciaSns = Misc.nz(resulSet.getString("SUBINDICADOR_SNS"));            
		procedencia                = Misc.nz(resulSet.getString("COD_TIPO_PROCEDENCIA"));        
		codTitulo                  = Misc.nz(resulSet.getString("COD_TITULO"));     
		//
		fechaNacimientoRaw         = fechaNacimientoRaw.subSequence(0, 4) + "-" + fechaNacimientoRaw.subSequence(4, 6) + "-" + fechaNacimientoRaw.subSequence(6, 8);
		fechaNacSns                = fechaNacSns.subSequence(0, 4) + "-" + fechaNacSns.subSequence(4, 6) + "-" + fechaNacSns.subSequence(6, 8);
	}

	
	public void view (){
		logger.debug("idBen: " + idBen + ", dniNie: " + dniNie + ", naf: " + naf + ", nafTitular: " + nafTitular + ", ipfTitular: " + ipfTitular);
		logger.debug("nombre: " + nombre + ", apellido1: " + apellido1 + ", apellido2: " + apellido2 + ", sexo: " + sexo + ", fechaNacimientoRaw: " + fechaNacimientoRaw);
		logger.debug("codIndicadorFarmacia: " + codIndicadorFarmacia + ", codSubindicadorFarmacia: " + codSubindicadorFarmacia + ", tipoAseguramiento: " + tipoAseguramiento);
		//
		logger.debug("codUsuarioSns: " + codUsuarioSns + ", codUsuarioSnsTitular: " + codUsuarioSnsTitular + ", dniNieSns: " + dniNieSns + ", nafSns: " + nafSns + ", nafTitularSns: " + nafTitularSns);
		logger.debug("nombreSns: " + nombreSns + ", apellido1Sns: " + apellido1Sns + ", apellido2Sns: " + apellido2Sns + ", codSexoSns: " + codSexoSns + ", fechaNacSns: " + fechaNacSns);
		logger.debug("codIndicadorFarmaciaSns: " + codIndicadorFarmaciaSns + ", codSubindicadorFarmaciaSns: " + codSubindicadorFarmaciaSns + ", procedencia: " + procedencia + ", codTitulo: " + codTitulo);
	}
	
	
	public String toString () {
		//
		StringBuffer str = new StringBuffer ();
		str.append(idBen                        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(dniNie                       + ConstantesBusqueda.SEPARADOR_CAMPOS);   
		str.append(naf                          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nafTitular                   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(ipfTitular                   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre                       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(sexo                         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNacimientoRaw           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codIndicadorFarmacia         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codSubindicadorFarmacia      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(tipoAseguramiento            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codUsuarioSns                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(dniNieSns                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nafSns                       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nafTitularSns                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codUsuarioSnsTitular         + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombreSns                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1Sns                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2Sns                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codSexoSns                   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNacSns                  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codIndicadorFarmaciaSns      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codSubindicadorFarmaciaSns   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(procedencia                  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codTitulo                    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
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


	public String getNafTitular() {
		return nafTitular;
	}


	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}


	public String getIpfTitular() {
		return ipfTitular;
	}


	public void setIpfTitular(String ipfTitular) {
		this.ipfTitular = ipfTitular;
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


	public String getFechaNacimientoRaw() {
		return fechaNacimientoRaw;
	}


	public void setFechaNacimientoRaw(String fechaNacimientoRaw) {
		this.fechaNacimientoRaw = fechaNacimientoRaw;
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


	public String getTipoAseguramiento() {
		return tipoAseguramiento;
	}


	public void setTipoAseguramiento(String tipoAseguramiento) {
		this.tipoAseguramiento = tipoAseguramiento;
	}


	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}


	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}


	public String getDniNieSns() {
		return dniNieSns;
	}


	public void setDniNieSns(String dniNieSns) {
		this.dniNieSns = dniNieSns;
	}


	public String getNafSns() {
		return nafSns;
	}


	public void setNafSns(String nafSns) {
		this.nafSns = nafSns;
	}


	public String getNafTitularSns() {
		return nafTitularSns;
	}


	public void setNafTitularSns(String nafTitularSns) {
		this.nafTitularSns = nafTitularSns;
	}


	public String getCodUsuarioSnsTitular() {
		return codUsuarioSnsTitular;
	}


	public void setCodUsuarioSnsTitular(String codUsuarioSnsTitular) {
		this.codUsuarioSnsTitular = codUsuarioSnsTitular;
	}


	public String getNombreSns() {
		return nombreSns;
	}


	public void setNombreSns(String nombreSns) {
		this.nombreSns = nombreSns;
	}


	public String getApellido1Sns() {
		return apellido1Sns;
	}


	public void setApellido1Sns(String apellido1Sns) {
		this.apellido1Sns = apellido1Sns;
	}


	public String getApellido2Sns() {
		return apellido2Sns;
	}


	public void setApellido2Sns(String apellido2Sns) {
		this.apellido2Sns = apellido2Sns;
	}


	public String getCodSexoSns() {
		return codSexoSns;
	}


	public void setCodSexoSns(String codSexoSns) {
		this.codSexoSns = codSexoSns;
	}


	public String getFechaNacSns() {
		return fechaNacSns;
	}


	public void setFechaNacSns(String fechaNacSns) {
		this.fechaNacSns = fechaNacSns;
	}


	public String getCodIndicadorFarmaciaSns() {
		return codIndicadorFarmaciaSns;
	}


	public void setCodIndicadorFarmaciaSns(String codIndicadorFarmaciaSns) {
		this.codIndicadorFarmaciaSns = codIndicadorFarmaciaSns;
	}


	public String getCodSubindicadorFarmaciaSns() {
		return codSubindicadorFarmaciaSns;
	}


	public void setCodSubindicadorFarmaciaSns(String codSubindicadorFarmaciaSns) {
		this.codSubindicadorFarmaciaSns = codSubindicadorFarmaciaSns;
	}


	public String getProcedencia() {
		return procedencia;
	}


	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}


	public String getCodTitulo() {
		return codTitulo;
	}


	public void setCodTitulo(String codTitulo) {
		this.codTitulo = codTitulo;
	}	
		
}

