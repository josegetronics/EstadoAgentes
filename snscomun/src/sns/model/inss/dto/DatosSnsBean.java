package sns.model.inss.dto;

/******************************************************
 * / Clase generada con GeneraBean de gasai.jar Wed Jul 18 14:39:06 CEST 2012 /
 ******************************************************/
public class DatosSnsBean extends DatosCoberturaSnsBean implements IDatosSns{

	private java.math.BigDecimal codPrestacionServicio;
	private java.math.BigDecimal codEstado;
	private java.lang.String dniNie;
	private java.lang.String pasaporte;
	private java.lang.String nombre;
	private java.lang.String apellido1;
	private java.lang.String apellido2;
	private java.lang.String codIndicadorDeFarmacia;
	private java.lang.String codSubindicadorDeFarmacia;
	private java.math.BigDecimal codTipoProcedencia;
	private String idEnSsalud;
	private java.math.BigDecimal codAgente;

	public DatosSnsBean() {
		super();
	}

	public DatosSnsBean(java.util.HashMap hd) {
		super(hd);
		this.codPrestacionServicio = (java.math.BigDecimal) hd.get("COD_PRESTACION_SERVICIO");
		this.codEstado = (java.math.BigDecimal) hd.get("COD_ESTADO");
		this.dniNie = (java.lang.String) hd.get("DNI_NIE");
		this.pasaporte = (java.lang.String) hd.get("PASAPORTE");
		this.nombre = (java.lang.String) hd.get("NOMBRE");
		this.apellido1 = (java.lang.String) hd.get("APELLIDO1");
		this.apellido2 = (java.lang.String) hd.get("APELLIDO2");
		this.codIndicadorDeFarmacia = (java.lang.String) hd.get("COD_INDICADOR_DE_FARMACIA");
		this.codSubindicadorDeFarmacia = (java.lang.String) hd.get("COD_SUBINDICADOR_DE_FARMACIA");
		this.codTipoProcedencia = (java.math.BigDecimal) hd.get("COD_TIPO_PROCEDENCIA");
		this.idEnSsalud = (String) hd.get("ID_EN_SSALUD");
		this.codAgente= (java.math.BigDecimal) hd.get("COD_AGENTE");
	}

	public DatosSnsBean(java.sql.ResultSet rs) throws java.sql.SQLException {
		super(rs);
		this.codPrestacionServicio = rs.getBigDecimal("COD_PRESTACION_SERVICIO");
		this.codEstado = rs.getBigDecimal("COD_ESTADO");
		this.dniNie = rs.getString("DNI_NIE");
		this.pasaporte = rs.getString("PASAPORTE");
		this.nombre = rs.getString("NOMBRE");
		this.apellido1 = rs.getString("APELLIDO1");
		this.apellido2 = rs.getString("APELLIDO2");
		this.codIndicadorDeFarmacia = rs.getString("COD_INDICADOR_DE_FARMACIA");
		this.codSubindicadorDeFarmacia = rs.getString("COD_SUBINDICADOR_DE_FARMACIA");
		this.codTipoProcedencia = rs.getBigDecimal("COD_TIPO_PROCEDENCIA");
		this.idEnSsalud = rs.getString("ID_EN_SSALUD");
		this.codAgente= rs.getBigDecimal("COD_AGENTE");
	}

	public java.math.BigDecimal getCodAgente() {
		return codAgente;
	}

	public void setCodAgente(java.math.BigDecimal codAgente) {
		this.codAgente = codAgente;
		this.vacio = false;
	}

	public String getIdEnSsalud() {
		return idEnSsalud;
	}

	public void setIdEnSsalud(String idEnSsalud) {
		this.idEnSsalud = idEnSsalud;
		this.vacio = false;
	}

	public java.math.BigDecimal getCodPrestacionServicio() {
		return this.codPrestacionServicio;
	}

	public void setCodPrestacionServicio(java.math.BigDecimal codPrestacionServicio) {
		this.codPrestacionServicio = codPrestacionServicio;
		this.vacio = false;
	}

	public java.math.BigDecimal getCodEstado() {
		return this.codEstado;
	}

	public void setCodEstado(java.math.BigDecimal codEstado) {
		this.codEstado = codEstado;
		this.vacio = false;
	}

	public java.lang.String getDniNie() {
		return this.dniNie;
	}

	public void setDniNie(java.lang.String dniNie) {
		this.dniNie = dniNie;
		this.vacio = false;
	}

	public java.lang.String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(java.lang.String pasaporte) {
		this.pasaporte = pasaporte;
		this.vacio = false;
	}

	public java.lang.String getNombre() {
		return this.nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
		this.vacio = false;
	}

	public java.lang.String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(java.lang.String apellido1) {
		this.apellido1 = apellido1;
		this.vacio = false;
	}

	public java.lang.String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(java.lang.String apellido2) {
		this.apellido2 = apellido2;
		this.vacio = false;
	}

	public java.lang.String getCodIndicadorDeFarmacia() {
		return this.codIndicadorDeFarmacia;
	}

	public void setCodIndicadorDeFarmacia(java.lang.String codIndicadorDeFarmacia) {
		this.codIndicadorDeFarmacia = codIndicadorDeFarmacia;
		this.vacio = false;
	}

	public java.lang.String getCodSubindicadorDeFarmacia() {
		return this.codSubindicadorDeFarmacia;
	}

	public void setCodSubindicadorDeFarmacia(java.lang.String codSubindicadorDeFarmacia) {
		this.codSubindicadorDeFarmacia = codSubindicadorDeFarmacia;
		this.vacio = false;
	}

	public java.math.BigDecimal getCodTipoProcedencia() {
		return this.codTipoProcedencia;
	}

	public void setCodTipoProcedencia(java.math.BigDecimal codTipoProcedencia) {
		this.codTipoProcedencia = codTipoProcedencia;
		this.vacio = false;
	}

	public String getCodUsuarioSnsFarmacia() {
		return this.getCodUsuarioSns();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("DatosSnsBean [apellido1=");
		buffer.append(apellido1);
		buffer.append(", apellido2=");
		buffer.append(apellido2);
		buffer.append(", codAgente=");
		buffer.append(codAgente);
		buffer.append(", codEstado=");
		buffer.append(codEstado);
		buffer.append(", codIndicadorDeFarmacia=");
		buffer.append(codIndicadorDeFarmacia);
		buffer.append(", codPrestacionServicio=");
		buffer.append(codPrestacionServicio);
		buffer.append(", codSubindicadorDeFarmacia=");
		buffer.append(codSubindicadorDeFarmacia);
		buffer.append(", codTipoProcedencia=");
		buffer.append(codTipoProcedencia);
		buffer.append(", dniNie=");
		buffer.append(dniNie);
		buffer.append(", idEnSsalud=");
		buffer.append(idEnSsalud);
		buffer.append(", nombre=");
		buffer.append(nombre);
		buffer.append(", pasaporte=");
		buffer.append(pasaporte);
		buffer.append("]");
		return buffer.toString();
	}


}
