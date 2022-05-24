package sns.model;

import gasai.util.Misc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import sns.config.Constantes;
import sns.model.inss.impl.ResultadoInssExtBean;

public class UsuarioSns implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2948115376938398786L;

	private final static int ERRONEO = -100;

	private String codSns = "";
	private String dniNie = "";
	private BigDecimal dniDup;
	private String pasaporte = "";
	private String naf = "";
	private String nafTitular = "";
	private String nombre = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private BigDecimal caNacIso;
	private BigDecimal caNacIne;
	private Date fechaNac;
	private String idSsalud = "";
	private BigDecimal sexo;
	private BigDecimal codEstado;

	private String dniNieNuevo = "";
	private BigDecimal dniDupNuevo;
	private String pasaporteNuevo = "";
	private String nafNuevo = "";
	private String nafTitularNuevo = "";
	private String nafTitularNuevoOriginal = "";
	private String nombreNuevo = "";
	private String apellido1Nuevo = "";
	private String apellido2Nuevo = "";
	private BigDecimal caNacIsoNuevo;
	private BigDecimal caNacIneNuevo;
	private Date fechaNacNuevo;
	private String idSsaludNuevo = "";
	private BigDecimal sexoNuevo;

	private String paisNac = "";

	private BigDecimal flagExtranjero;
	private String codNacionalidad = "";
	private String tarjetaIdentidad = "";

	private String tipoVia = "";
	private String nombreVia = "";
	private String numero = "";
	private BigDecimal bis;
	private String bloque = "";

	private String escalera = "";
	private String piso = "";
	private String puerta = "";
	private BigDecimal municipio;
	private BigDecimal provincia;
	private String codigoPostal = "";

	private BigDecimal codPrestacionServicio;
	private BigDecimal codPrestacionServicioOrigen;

	private BigDecimal codAseguradora;
	private BigDecimal codGestora;
	private BigDecimal codColaboradora;
	private BigDecimal codTitulo;
	private BigDecimal codSituacion;
	private BigDecimal codProveedor;
	private BigDecimal codProveedorAe;
	private BigDecimal codProveedorAp;
	private BigDecimal codProveedorFar;
	private BigDecimal codProveedorUrg;

	private Date fechaAltaSsalud;

	private Timestamp fechaUltMod;

	private boolean interno = false;
	private String codSnsTitular = "";
	private String codSnsTitularOriginal = "";
	private ListaCamposAfectados listaCamposAfectados;

	// RDL 16/2012
	private String indicadorDeFarmacia;
	private String subIndicadorDeFarmacia;
	
	private long fechaRecepcionMensaje;
	private String codTipoOperacion;
	
	private boolean conversionRealizadaTituloNuevoRDL;
	
	private ResultadoInssExtBean resultadoInssExtBean=new ResultadoInssExtBean();
	
	private ResultadoInssExtBean titularResultadoInssExtBean=new ResultadoInssExtBean();
	private String titularInterno=Constantes.USUARIO_REAL;
	
	public ResultadoInssExtBean getTitularResultadoInssExtBean() {
		return titularResultadoInssExtBean;
	}

	public void setTitularResultadoInssExtBean(ResultadoInssExtBean titularResultadoInssExtBean) {
		this.titularResultadoInssExtBean = titularResultadoInssExtBean;
	}

	public ResultadoInssExtBean getResultadoInssExtBean() {
		return resultadoInssExtBean;
	}

	public void setResultadoInssExtBean(ResultadoInssExtBean resultadoInssExtBean) {
		this.resultadoInssExtBean = resultadoInssExtBean;
	}

	public boolean isConversionRealizadaTituloNuevoRDL() {
		return conversionRealizadaTituloNuevoRDL;
	}

	public void setConversionRealizadaTituloNuevoRDL(boolean conversionRealizadaTituloNuevoRDL) {
		this.conversionRealizadaTituloNuevoRDL = conversionRealizadaTituloNuevoRDL;
	}

	public long getFechaRecepcionMensaje() {
		return fechaRecepcionMensaje;
	}

	public void setFechaRecepcionMensaje(long fechaRecepcionMensaje) {
		this.fechaRecepcionMensaje = fechaRecepcionMensaje;
	}

	public String getCodTipoOperacion() {
		return codTipoOperacion;
	}

	public void setCodTipoOperacion(String codTipoOperacion) {
		this.codTipoOperacion = codTipoOperacion;
	}

	public String getBloque() {
		return bloque;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public BigDecimal getMunicipio() {
		return municipio;
	}

	public BigDecimal getCodGestora() {
		return codGestora;
	}

	public BigDecimal getDniDupNuevo() {
		return dniDupNuevo;
	}

	public BigDecimal getCaNacIso() {
		return caNacIso;
	}

	public BigDecimal getCaNacIne() {
		return caNacIne;
	}

	public BigDecimal getCaNacIsoNuevo() {
		return caNacIsoNuevo;
	}

	public BigDecimal getCaNacIneNuevo() {
		return caNacIneNuevo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNaf() {
		return naf;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public BigDecimal getSexoNuevo() {
		return sexoNuevo;
	}

	public BigDecimal getProvincia() {
		return provincia;
	}

	public String getDniNieNuevo() {
		return dniNieNuevo;
	}

	public Date getFechaAltaSsalud() {
		return fechaAltaSsalud;
	}

	public String getCodSns() {
		return codSns;
	}

	public BigDecimal getBis() {
		return bis;
	}

	public String getPuerta() {
		return puerta;
	}

	public BigDecimal getCodAseguradora() {
		return codAseguradora;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getPaisNac() {
		return paisNac;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public Timestamp getFechaUltMod() {
		return fechaUltMod;
	}

	public String getIdSsaludNuevo() {
		return idSsaludNuevo;
	}

	public String getNafTitularNuevo() {
		return nafTitularNuevo;
	}

	public BigDecimal getCodTitulo() {
		return codTitulo;
	}

	public BigDecimal getCodPrestacionServicio() {
		return codPrestacionServicio;
	}

	public String getPiso() {
		return piso;
	}

	public String getIdSsalud() {
		return idSsalud;
	}

	public BigDecimal getFlagExtranjero() {
		return flagExtranjero;
	}

	public String getApellido1Nuevo() {
		return apellido1Nuevo;
	}

	public BigDecimal getCodPrestacionServicioOrigen() {
		return codPrestacionServicioOrigen;
	}

	public String getNumero() {
		return numero;
	}

	public String getNafTitular() {
		return nafTitular;
	}

	public BigDecimal getSexo() {
		return sexo;
	}

	public Date getFechaNacNuevo() {
		return fechaNacNuevo;
	}

	public String getEscalera() {
		return escalera;
	}

	public boolean isInterno() {
		return interno;
	}

	public String getTarjetaIdentidad() {
		return tarjetaIdentidad;
	}

	public BigDecimal getCodEstado() {
		return codEstado;
	}

	public String getCodNacionalidad() {
		return codNacionalidad;
	}

	public String getNombreNuevo() {
		return nombreNuevo;
	}

	public BigDecimal getCodProveedor() {
		return codProveedor;
	}

	public String getDniNie() {
		return dniNie;
	}

	public BigDecimal getDniDup() {
		return dniDup;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2Nuevo() {
		return apellido2Nuevo;
	}

	public String getNafNuevo() {
		return nafNuevo;
	}

	public String getPasaporteNuevo() {
		return pasaporteNuevo;
	}

	public BigDecimal getCodSituacion() {
		return codSituacion;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setCodColaboradora(BigDecimal codColaboradora) {
		this.codColaboradora = codColaboradora;
	}

	public void setBloque(String bloque) {
		this.bloque = Misc.nz(bloque);
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = Misc.nz(nombreVia);
	}

	public void setMunicipio(BigDecimal municipio) {
		this.municipio = municipio;
	}

	public void setCodGestora(BigDecimal codGestora) {
		this.codGestora = codGestora;
	}

	public void setDniDupNuevo(BigDecimal dniDupNuevo) {
		this.dniDupNuevo = dniDupNuevo;
	}

	public void setCaNacIso(BigDecimal caNacIso) {
		this.caNacIso = caNacIso;
		if (!Misc.esVacio(caNacIso)) {
			if (caNacIso.intValue() == -1 || caNacIso.intValue() == ERRONEO) {
				this.caNacIne = caNacIso;
			} else {
				String caNacIneAux = Misc.nz(sns.config.Inicializacion.CAISO_CA.get(Misc.nz(this.caNacIso)));
				if (!Misc.esVacio(caNacIneAux)) {
					this.caNacIne = new BigDecimal(caNacIneAux);
				}
			}
		}
	}

	public void setCaNacIne(BigDecimal caNacIne) {
		this.caNacIne = caNacIne;
	}

	public void setCaNacIsoNuevo(BigDecimal caNacIsoNuevo) {
		this.caNacIsoNuevo = caNacIsoNuevo;
		if (!Misc.esVacio(caNacIsoNuevo)) {
			if (caNacIsoNuevo.intValue() == -1 || caNacIsoNuevo.intValue() == ERRONEO) {
				this.caNacIneNuevo = caNacIsoNuevo;
			} else {
				String caNacIneNuevoAux = Misc.nz(sns.config.Inicializacion.CAISO_CA.get(Misc.nz(this.caNacIsoNuevo)));
				if (!Misc.esVacio(caNacIneNuevoAux)) {
					this.caNacIneNuevo = new BigDecimal(caNacIneNuevoAux);
				}
			}
		}
	}

	public void setCaNacIneNuevo(BigDecimal caNacIneNuevo) {
		this.caNacIneNuevo = caNacIneNuevo;
	}

	public void setNombre(String nombre) {
		this.nombre = Misc.nz(nombre);
	}

	public void setNaf(String naf) {
		this.naf = Misc.nz(naf);
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = Misc.nz(codigoPostal);
	}

	public void setSexoNuevo(BigDecimal sexoNuevo) {
		this.sexoNuevo = sexoNuevo;
	}

	public void setProvincia(BigDecimal provincia) {
		this.provincia = provincia;
	}

	public void setDniNieNuevo(String dniNieNuevo) {
		this.dniNieNuevo = Misc.nz(dniNieNuevo);
	}

	public void setFechaAltaSsalud(Date fechaAltaSsalud) {
		this.fechaAltaSsalud = fechaAltaSsalud;
	}

	public void setCodSns(String codSns) {
		this.codSns = Misc.nz(codSns);
	}

	public void setBis(BigDecimal bis) {
		this.bis = bis;
	}

	public void setPuerta(String puerta) {
		this.puerta = Misc.nz(puerta);
	}

	public void setCodAseguradora(BigDecimal codAseguradora) {
		this.codAseguradora = codAseguradora;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = Misc.nz(apellido2);
	}

	public void setPaisNac(String paisNac) {
		this.paisNac = Misc.nz(paisNac);
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = Misc.nz(pasaporte);
	}

	public void setFechaUltMod(Timestamp fechaUltMod) {
		this.fechaUltMod = fechaUltMod;
	}

	public void setIdSsaludNuevo(String idSsaludNuevo) {
		this.idSsaludNuevo = Misc.nz(idSsaludNuevo);
	}

	public void setNafTitularNuevo(String nafTitularNuevo) {
		this.nafTitularNuevo = Misc.nz(nafTitularNuevo);
	}

	public void setCodTitulo(BigDecimal codTitulo) {
		this.codTitulo = codTitulo;
	}

	public void setCodPrestacionServicio(BigDecimal codPrestacionServicio) {
		this.codPrestacionServicio = codPrestacionServicio;
	}

	public void setPiso(String piso) {
		this.piso = Misc.nz(piso);
	}

	public void setIdSsalud(String idSsalud) {
		this.idSsalud = Misc.nz(idSsalud);
	}

	public void setFlagExtranjero(BigDecimal flagExtranjero) {
		this.flagExtranjero = flagExtranjero;
	}

	public void setApellido1Nuevo(String apellido1Nuevo) {
		this.apellido1Nuevo = Misc.nz(apellido1Nuevo);
	}

	public void setCodPrestacionServicioOrigen(BigDecimal codPrestacionServicioOrigen) {
		this.codPrestacionServicioOrigen = codPrestacionServicioOrigen;
	}

	public void setNumero(String numero) {
		this.numero = Misc.nz(numero);
	}

	public void setNafTitular(String nafTitular) {
		this.nafTitular = Misc.nz(nafTitular);
	}

	public void setSexo(BigDecimal sexo) {
		this.sexo = sexo;
	}

	public void setFechaNacNuevo(Date fechaNacNuevo) {
		this.fechaNacNuevo = fechaNacNuevo;
	}

	public void setEscalera(String escalera) {
		this.escalera = Misc.nz(escalera);
	}

	public void setInterno(boolean interno) {
		this.interno = interno;
	}

	public void setTarjetaIdentidad(String tarjetaIdentidad) {
		this.tarjetaIdentidad = Misc.nz(tarjetaIdentidad);
	}

	public void setCodEstado(BigDecimal codEstado) {
		this.codEstado = codEstado;
	}

	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = Misc.nz(codNacionalidad);
	}

	public void setNombreNuevo(String nombreNuevo) {
		this.nombreNuevo = Misc.nz(nombreNuevo);
	}

	public void setCodProveedor(BigDecimal codProveedor) {
		this.codProveedor = codProveedor;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = Misc.nz(dniNie);
	}

	public void setDniDup(BigDecimal dniDup) {
		this.dniDup = dniDup;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = Misc.nz(apellido1);
	}

	public void setApellido2Nuevo(String apellido2Nuevo) {
		this.apellido2Nuevo = Misc.nz(apellido2Nuevo);
	}

	public void setNafNuevo(String nafNuevo) {
		this.nafNuevo = Misc.nz(nafNuevo);
	}

	public void setPasaporteNuevo(String pasaporteNuevo) {
		this.pasaporteNuevo = Misc.nz(pasaporteNuevo);
	}

	public void setCodSituacion(BigDecimal codSituacion) {
		this.codSituacion = codSituacion;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = Misc.nz(tipoVia);
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setCodSnsTitular(String codSnsTitular) {
		this.codSnsTitular = Misc.nz(codSnsTitular);
	}

	public void setListaCamposAfectados(ListaCamposAfectados listaCamposAfectados) {
		this.listaCamposAfectados = listaCamposAfectados;
	}

	public void setCodProveedorFar(BigDecimal codProveedorFar) {
		this.codProveedorFar = codProveedorFar;
	}

	public void setCodProveedorAe(BigDecimal codProveedorAe) {
		this.codProveedorAe = codProveedorAe;
	}

	public void setCodProveedorUrg(BigDecimal codProveedorUrg) {
		this.codProveedorUrg = codProveedorUrg;
	}

	public void setCodProveedorAp(BigDecimal codProveedorAp) {
		this.codProveedorAp = codProveedorAp;
	}

	public void setNafTitularNuevoOriginal(String nafTitularNuevoOriginal) {
		this.nafTitularNuevoOriginal = nafTitularNuevoOriginal;
	}

	public void setCodSnsTitularOriginal(String codSnsTitularOriginal) {
		this.codSnsTitularOriginal = codSnsTitularOriginal;
	}

	public BigDecimal getCodColaboradora() {
		return codColaboradora;
	}

	public String getCodSnsTitular() {
		return codSnsTitular;
	}

	public ListaCamposAfectados getListaCamposAfectados() {
		return listaCamposAfectados;
	}

	public BigDecimal getCodProveedorFar() {
		return codProveedorFar;
	}

	public BigDecimal getCodProveedorAe() {
		return codProveedorAe;
	}

	public BigDecimal getCodProveedorUrg() {
		return codProveedorUrg;
	}

	public BigDecimal getCodProveedorAp() {
		return codProveedorAp;
	}

	public String getNafTitularNuevoOriginal() {
		return nafTitularNuevoOriginal;
	}

	public String getCodSnsTitularOriginal() {
		return codSnsTitularOriginal;
	}

	public String getIndicadorDeFarmacia() {
		return indicadorDeFarmacia;
	}

	public void setIndicadorDeFarmacia(String indicadorDeFarmacia) {
		this.indicadorDeFarmacia = indicadorDeFarmacia;
	}

	public String getSubIndicadorDeFarmacia() {
		return subIndicadorDeFarmacia;
	}

	public void setSubIndicadorDeFarmacia(String subIndicadorDeFarmacia) {
		this.subIndicadorDeFarmacia = subIndicadorDeFarmacia;
	}

	public UsuarioSns() {
	}

	public String toString() {
		StringBuffer datos = new StringBuffer();

		datos.append("***********************************************************************");
		datos.append("dniNieNuevo=");
		datos.append(dniDupNuevo);
		datos.append("\npasaporteNuevo=");
		datos.append(pasaporteNuevo);
		datos.append("\nnafNuevo=");
		datos.append(nafNuevo);
		datos.append("\nnafTitularNuevo=");
		datos.append(nafTitularNuevo);
		datos.append("\nnafTitularNuevoOriginal=");
		datos.append(nafTitularNuevoOriginal);
		datos.append("\nnombreNuevo=");
		datos.append(nombreNuevo);
		datos.append("\napellido1Nuevo=");
		datos.append(apellido1Nuevo);
		datos.append("\napellido2Nuevo=");
		datos.append(apellido2Nuevo);
		datos.append("\ncaNacIsoNuevo=");
		datos.append(caNacIsoNuevo);
		datos.append("\ncaNacIneNuevo=");
		datos.append(caNacIneNuevo);
		datos.append("\nfechaNacNuevo=");
		datos.append(fechaNacNuevo);
		datos.append("\nidSsaludNuevo=");
		datos.append(idSsaludNuevo);
		datos.append("\nsexoNuevo=");
		datos.append(sexoNuevo);

		datos.append("\ncodSns=");
		datos.append(codSns);
		datos.append("\ncodSnsTitular=");
		datos.append(codSnsTitular);
		datos.append("\ncodSnsTitularOriginal=");
		datos.append(codSnsTitularOriginal);

		datos.append("\ndniNie=");
		datos.append(dniNie);
		datos.append("\ndniDup=");
		datos.append(dniDup);
		datos.append("\npasaporte=");
		datos.append(pasaporte);
		datos.append("\nnaf=");
		datos.append(naf);
		datos.append("\nnafTitular=");
		datos.append(nafTitular);
		datos.append("\nnombre=");
		datos.append(nombre);
		datos.append("\napellido1=");
		datos.append(apellido1);
		datos.append("\napellido2=");
		datos.append(apellido2);
		datos.append("\ncaNacIso=");
		datos.append(caNacIso);
		datos.append("\ncaNacIne=");
		datos.append(caNacIne);
		datos.append("\nfechaNac=");
		datos.append(fechaNac);
		datos.append("\nidSsalud=");
		datos.append(idSsalud);
		datos.append("\nsexo=");
		datos.append(sexo);
		datos.append("\ncodEstado=");
		datos.append(codEstado);
		datos.append("\nfechaAltaSsalud=");
		datos.append(fechaAltaSsalud);
		datos.append("\nfechaUltMod=");
		datos.append(fechaUltMod);
		datos.append("\ncodPrestacionServicio=");
		datos.append(codPrestacionServicio);
		datos.append("\ncodPrestacionServicioOrigen=");
		datos.append(codPrestacionServicioOrigen);
		datos.append("\npaisNac=");
		datos.append(paisNac);
		datos.append("\nflagExtranjero=");
		datos.append(flagExtranjero);
		datos.append("\ncodNacionalidad=");
		datos.append(codNacionalidad);
		datos.append("\ntarjetaIdentidad=");
		datos.append(tarjetaIdentidad);
		datos.append("\ntipoVia=");
		datos.append(tipoVia);
		datos.append("\nnombreVia=");
		datos.append(nombreVia);
		datos.append("\nnumero=");
		datos.append(numero);
		datos.append("\nbis=");
		datos.append(bis);
		datos.append("\nbloque=");
		datos.append(bloque);
		datos.append("\nescalera=");
		datos.append(escalera);
		datos.append("\npiso=");
		datos.append(piso);
		datos.append("\npuerta=");
		datos.append(puerta);
		datos.append("\nmunicipio=");
		datos.append(municipio);
		datos.append("\nprovincia=");
		datos.append(provincia);
		datos.append("\ncodigoPostal=");
		datos.append(codigoPostal);

		datos.append("\ncodAseguradora=");
		datos.append(codAseguradora);
		datos.append("\ncodGestora=");
		datos.append(codGestora);
		datos.append("\ncodColaboradora=");
		datos.append(codColaboradora);
		datos.append("\ncodTitulo=");
		datos.append(codTitulo);
		datos.append("\ncodSituacion=");
		datos.append(codSituacion);
		datos.append("\ncodProveedor=");
		datos.append(codProveedor);

		datos.append("\ninterno=");
		datos.append(interno);
		datos.append("\nindicadorDeFarmacia=");
		datos.append(indicadorDeFarmacia);
		datos.append("\nsubIndicadorDeFarmacia=");
		datos.append(subIndicadorDeFarmacia);

		datos.append("***********************************************************************");

		return datos.toString();
	}

	public void setTitularInterno(String titularInterno) {
		this.titularInterno = titularInterno;
	}

	public String getTitularInterno() {
		return titularInterno;
	}
}
