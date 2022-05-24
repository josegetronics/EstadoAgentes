package sns.comun.bean.duplicados;

import gasai.util.Misc;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class DuplicadosBean extends ConsultaBean {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	private	String idNiss         = "";
	private	String naf_sec1       = "";
	private	String codUsuarioSns  = "";
	private	String criterio       = "";
	
	
	public DuplicadosBean() {
	}
	
		
	public DuplicadosBean (ResultSet resulSet) throws Exception {
		idNiss             = Misc.nz(resulSet.getString("ID_INSS"));
		naf_sec1           = Misc.nz(resulSet.getString("NAF_SEC1"));
		codUsuarioSns      = Misc.nz(resulSet.getString("COD_USUARIO_SNS"));
		criterio           = Misc.nz(resulSet.getString("CRITERIO"));
		criterio           = Misc.remplazar(criterio, "||", "|");
		criterio           = Misc.remplazar(criterio, "|", "#");	
		nombre             = Misc.nz(resulSet.getString("NOMBRE"));
		apellido1          = Misc.nz(resulSet.getString("APELLIDO1"));
		apellido2          = Misc.nz(resulSet.getString("APELLIDO2"));
		codSexo            = Misc.nz(resulSet.getString("SEXO"));
		fechaNac           = Misc.nz(resulSet.getString("FECHA_NACIMIENTO_RAW"));
		fechaNac           = fechaNac.subSequence(0, 4) + "-" + fechaNac.subSequence(4, 6) + "-" + fechaNac.subSequence(6, 8);
		//
		naf                = Misc.nz(resulSet.getString("NAF"));
		dniNie             = Misc.nz(resulSet.getString("DNI_NIE"));
	}

	
	public void view (){
		logger.debug("nombre: " + nombre + ", apellido1: " + apellido1 + ", apellido2: " + apellido2);
		logger.debug("codSexo: " + codSexo + ", fechaNac: " + fechaNac);
		logger.debug("dniNie: " + dniNie + ", naf: " + naf);
	}
	
	
	public String toString () {
		//
		StringBuffer str = new StringBuffer ();
		str.append(idNiss        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codUsuarioSns + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(criterio      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(nombre        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido1     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(apellido2     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(codSexo       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(fechaNac      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(dniNie        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		str.append(naf           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}	
	
	
	public String getIdNiss() {
		return idNiss;
	}

	public void setIdNiss(String idNiss) {
		this.idNiss = idNiss;
	}

	public String getNaf_sec1() {
		return naf_sec1;
	}

	public void setNaf_sec1(String nafSec1) {
		naf_sec1 = nafSec1;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
		
	
}

