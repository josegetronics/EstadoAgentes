package sns.comun.bean;

import gasai.util.Misc;

import java.sql.ResultSet;



public class RegistroCompletoBean {

	private static final String SEPARADOR         = "";  
	
	private String codUsuarioSns                  = "";   
	private String dniNie                         = "";
	private String dniDup                         = "";
	private String pasaporte                      = "";
	private String naf                            = "";
	private String nafTitular                     = "";
	private String nombre                         = "";
	private String apellido1                      = "";
	private String apellido2                      = "";
	private String sexo                           = "";
	private String fechaNac                       = "";
	private String idSsalud                       = "";
	private String extranjero                     = "";
	private String fechaAlta                      = "";
	private String fechaUltMod                    = "";
	private String paisNacimiento                 = "";
	private String caNac                          = "";
	private String codNacionalidad                = "";
	private String codUusuarioSnsBbus             = "";
	private String codEstadoBus                   = "";
	private String idSssaludBus                   = "";
	private String codCaIsoBus                    = "";
	private String nombreBus                      = "";
	private String apellido1Bus                   = "";
	private String apellido2Bus                   = "";
	private String cod_sexoBus                    = "";
	private String fechaNacBus                    = "";
	private String raizBus                        = "";
	private String dniNieBus                      = "";
	private String pasaporteBus                   = "";
	private String nafBus                         = "";
	private String nafTitularBus                  = "";
	private String codTituloBus                   = "";
	private String codSituacionBus                = "";
	private String codIndicadorFarmacia           = "";
	private String codSubindicadorFarmacia        = "";
	private String codTipoPocedencia              = "";
	//
	private String criterio                       = "";
	private String criterioCompleto               = "";
	private String diferencia                     = "";
	private String camposCoincidentes             = "";
	private String camposIgualesAproximacion      = "";
	private String porcentaje                     = "";
	private String tipocampo                      = "";
	private String tiporesultado                  = "";
	//
	private String tarjetaIdentidad               = "";
	private String tipoVia                        = "";
	private String nombrevia                      = "";
	private String numero                         = "";
	private String bis                            = "";
	private String bloque                         = "";
	private String escalera                       = "";
	private String piso                           = "";
	private String puerta                         = "";
	private String municipio                      = "";
	private String provincia                      = "";
	private String codigoPostal                   = "";
	private String codigoAseguradora              = "";
	private String codigoGestora                  = "";
	private String codigoColaboradora             = "";
	private String codigoProveedor                = "";
	private String codigoTitulo                   = "";
	private String codigoSituacion                = "";

	
	public RegistroCompletoBean (){
	}

	public RegistroCompletoBean(ResultSet resulSet) throws Exception {
		codUsuarioSns              = Misc.nz(resulSet.getString("COD_USUARIO_SNS"));
		dniNie                     = Misc.nz(resulSet.getString("DNI_NIE"));
		dniDup                     = Misc.nz(resulSet.getString("DNIDUP"));
		pasaporte                  = Misc.nz(resulSet.getString("PASAPORTE"));
		naf                        = Misc.nz(resulSet.getString("NAF"));
		nafTitular                 = Misc.nz(resulSet.getString("NAF_TITULAR"));
		nombre                     = Misc.nz(resulSet.getString("NOMBRE"));
		apellido1                  = Misc.nz(resulSet.getString("APELLIDO1"));
		apellido2                  = Misc.nz(resulSet.getString("APELLIDO2"));
		sexo                       = Misc.nz(resulSet.getString("SEXO"));
		fechaNac                   = Misc.nz(resulSet.getString("FECHA_NAC"));
		idSsalud                   = Misc.nz(resulSet.getString("ID_SSALUD"));
		extranjero                 = Misc.nz(resulSet.getString("EXTRANJERO"));
		fechaAlta                  = Misc.nz(resulSet.getString("FECHA_ALTA"));
		fechaUltMod                = Misc.nz(resulSet.getString("FECHA_ULT_MOD"));
		paisNacimiento             = Misc.nz(resulSet.getString("PAIS_NACIMIENTO"));
		caNac                      = Misc.nz(resulSet.getString("CA_NAC"));
		codNacionalidad            = Misc.nz(resulSet.getString("COD_NACIONALIDAD"));
		codUusuarioSnsBbus         = Misc.nz(resulSet.getString("COD_USUARIO_SNS_BUS"));
		codEstadoBus               = Misc.nz(resulSet.getString("COD_ESTADO_BUS"));
		idSssaludBus               = Misc.nz(resulSet.getString("ID_EN_SSALUD_BUS"));
		codCaIsoBus                = Misc.nz(resulSet.getString("COD_CA_ISO_BUS"));
		nombreBus                  = Misc.nz(resulSet.getString("NOMBRE_BUS"));
		apellido1Bus               = Misc.nz(resulSet.getString("APELLIDO1_BUS"));
		apellido2Bus               = Misc.nz(resulSet.getString("APELLIDO2_BUS"));
		cod_sexoBus                = Misc.nz(resulSet.getString("COD_SEXO_BUS"));
		fechaNacBus                = Misc.nz(resulSet.getString("FECHA_NAC_BUS"));
		raizBus                    = Misc.nz(resulSet.getString("RAIZ_BUS"));
		dniNieBus                  = Misc.nz(resulSet.getString("DNI_NIE_BUS"));
		pasaporteBus               = Misc.nz(resulSet.getString("PASAPORTE_BUS"));
		nafBus                     = Misc.nz(resulSet.getString("NAF_BUS"));
		nafTitularBus              = Misc.nz(resulSet.getString("NAF_TITULAR_BUS"));
		codTituloBus               = Misc.nz(resulSet.getString("COD_TITULO_BUS"));
		codSituacionBus            = Misc.nz(resulSet.getString("COD_SITUACION_BUS"));
		codIndicadorFarmacia       = Misc.nz(resulSet.getString("COD_INDICADOR_DE_FARMACIA"));
		codSubindicadorFarmacia    = Misc.nz(resulSet.getString("COD_SUBINDICADOR_DE_FARMACIA"));
		codTipoPocedencia          = Misc.nz(resulSet.getString("COD_TIPO_PROCEDENCIA"));
	}
	
	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public String getDniDup() {
		return dniDup;
	}

	public void setDniDup(String dniDup) {
		this.dniDup = dniDup;
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

	public String getIdSsalud() {
		return idSsalud;
	}

	public void setIdSsalud(String idSsalud) {
		this.idSsalud = idSsalud;
	}

	public String getExtranjero() {
		return extranjero;
	}

	public void setExtranjero(String extranjero) {
		this.extranjero = extranjero;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaUltMod() {
		return fechaUltMod;
	}

	public void setFechaUltMod(String fechaUltMod) {
		this.fechaUltMod = fechaUltMod;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public String getCaNac() {
		return caNac;
	}

	public void setCaNac(String caNac) {
		this.caNac = caNac;
	}

	public String getCodNacionalidad() {
		return codNacionalidad;
	}

	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}

	public String getCodUusuarioSnsBbus() {
		return codUusuarioSnsBbus;
	}

	public void setCodUusuarioSnsBbus(String codUusuarioSnsBbus) {
		this.codUusuarioSnsBbus = codUusuarioSnsBbus;
	}

	public String getCodEstadoBus() {
		return codEstadoBus;
	}

	public void setCodEstadoBus(String codEstadoBus) {
		this.codEstadoBus = codEstadoBus;
	}

	public String getIdSssaludBus() {
		return idSssaludBus;
	}

	public void setIdSssaludBus(String idSssaludBus) {
		this.idSssaludBus = idSssaludBus;
	}

	public String getCodCaIsoBus() {
		return codCaIsoBus;
	}

	public void setCodCaIsoBus(String codCaIsoBus) {
		this.codCaIsoBus = codCaIsoBus;
	}

	public String getNombreBus() {
		return nombreBus;
	}

	public void setNombreBus(String nombreBus) {
		this.nombreBus = nombreBus;
	}

	public String getApellido1Bus() {
		return apellido1Bus;
	}

	public void setApellido1Bus(String apellido1Bus) {
		this.apellido1Bus = apellido1Bus;
	}

	public String getApellido2Bus() {
		return apellido2Bus;
	}

	public void setApellido2Bus(String apellido2Bus) {
		this.apellido2Bus = apellido2Bus;
	}

	public String getCod_sexoBus() {
		return cod_sexoBus;
	}

	public void setCod_sexoBus(String codSexoBus) {
		cod_sexoBus = codSexoBus;
	}

	public String getFechaNacBus() {
		return fechaNacBus;
	}

	public void setFechaNacBus(String fechaNacBus) {
		this.fechaNacBus = fechaNacBus;
	}

	public String getRaizBus() {
		return raizBus;
	}

	public void setRaizBus(String raizBus) {
		this.raizBus = raizBus;
	}

	public String getDniNieBus() {
		return dniNieBus;
	}

	public void setDniNieBus(String dniNieBus) {
		this.dniNieBus = dniNieBus;
	}

	public String getPasaporteBus() {
		return pasaporteBus;
	}

	public void setPasaporteBus(String pasaporteBus) {
		this.pasaporteBus = pasaporteBus;
	}

	public String getNafBus() {
		return nafBus;
	}

	public void setNafBus(String nafBus) {
		this.nafBus = nafBus;
	}

	public String getNafTitularBus() {
		return nafTitularBus;
	}

	public void setNafTitularBus(String nafTitularBus) {
		this.nafTitularBus = nafTitularBus;
	}

	public String getCodTituloBus() {
		return codTituloBus;
	}

	public void setCodTituloBus(String codTituloBus) {
		this.codTituloBus = codTituloBus;
	}

	public String getCodSituacionBus() {
		return codSituacionBus;
	}

	public void setCodSituacionBus(String codSituacionBus) {
		this.codSituacionBus = codSituacionBus;
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

	public String getCodTipoPocedencia() {
		return codTipoPocedencia;
	}

	public void setCodTipoPocedencia(String codTipoPocedencia) {
		this.codTipoPocedencia = codTipoPocedencia;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public String getCriterioCompleto() {
		return criterioCompleto;
	}

	public void setCriterioCompleto(String criterioCompleto) {
		this.criterioCompleto = criterioCompleto;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public String getCamposCoincidentes() {
		return camposCoincidentes;
	}

	public void setCamposCoincidentes(String camposCoincidentes) {
		this.camposCoincidentes = camposCoincidentes;
	}

	public String getCamposIgualesAproximacion() {
		return camposIgualesAproximacion;
	}

	public void setCamposIgualesAproximacion(String camposIgualesAproximacion) {
		this.camposIgualesAproximacion = camposIgualesAproximacion;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getTipocampo() {
		return tipocampo;
	}

	public void setTipocampo(String tipocampo) {
		this.tipocampo = tipocampo;
	}

	public String getTiporesultado() {
		return tiporesultado;
	}

	public void setTiporesultado(String tiporesultado) {
		this.tiporesultado = tiporesultado;
	}

	public String getTarjetaIdentidad() {
		return tarjetaIdentidad;
	}

	public void setTarjetaIdentidad(String tarjetaIdentidad) {
		this.tarjetaIdentidad = tarjetaIdentidad;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombrevia() {
		return nombrevia;
	}

	public void setNombrevia(String nombrevia) {
		this.nombrevia = nombrevia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCodigoAseguradora() {
		return codigoAseguradora;
	}

	public void setCodigoAseguradora(String codigoAseguradora) {
		this.codigoAseguradora = codigoAseguradora;
	}

	public String getCodigoGestora() {
		return codigoGestora;
	}

	public void setCodigoGestora(String codigoGestora) {
		this.codigoGestora = codigoGestora;
	}

	public String getCodigoColaboradora() {
		return codigoColaboradora;
	}

	public void setCodigoColaboradora(String codigoColaboradora) {
		this.codigoColaboradora = codigoColaboradora;
	}

	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public String getCodigoTitulo() {
		return codigoTitulo;
	}

	public void setCodigoTitulo(String codigoTitulo) {
		this.codigoTitulo = codigoTitulo;
	}

	public String getCodigoSituacion() {
		return codigoSituacion;
	}

	public void setCodigoSituacion(String codigoSituacion) {
		this.codigoSituacion = codigoSituacion;
	}
	
	
	public String view() throws Exception {
		//
		StringBuffer stringBuffer = new StringBuffer ();
		//
		stringBuffer.append("codUsuarioSns: "             + codUsuarioSns              + SEPARADOR);
		stringBuffer.append(",dniNie: "                    + dniNie                    + SEPARADOR);
		stringBuffer.append(",dniDup: "                    + dniDup                    + SEPARADOR);
		stringBuffer.append(",pasaporte: "                 + pasaporte                 + SEPARADOR);
		stringBuffer.append(",naf: "                       + naf                       + SEPARADOR);
		stringBuffer.append(",nafTitular: "                + nafTitular                + SEPARADOR);
		stringBuffer.append(",nombre: "                    + nombre                    + SEPARADOR);
		stringBuffer.append(",apellido1: "                 + apellido1                 + SEPARADOR);
		stringBuffer.append(",apellido2: "                 + apellido2                 + SEPARADOR);
		stringBuffer.append(",sexo: "                      + sexo                      + SEPARADOR);
		stringBuffer.append(",fechaNac: "                  + fechaNac                  + SEPARADOR);
		stringBuffer.append(",idSsalud: "                  + idSsalud                  + SEPARADOR);
		stringBuffer.append(",extranjero: "                + extranjero                + SEPARADOR);
		stringBuffer.append(",fechaAlta: "                 + fechaAlta                 + SEPARADOR);
		stringBuffer.append(",fechaUltMod: "               + fechaUltMod               + SEPARADOR);
		stringBuffer.append(",paisNacimiento: "            + paisNacimiento            + SEPARADOR);
		stringBuffer.append(",caNac: "                     + caNac                     + SEPARADOR);
		stringBuffer.append(",codNacionalidad: "           + codNacionalidad           + SEPARADOR);
		stringBuffer.append(",codUusuarioSnsBbus: "        + codUusuarioSnsBbus        + SEPARADOR);
		stringBuffer.append(",codEstadoBus: "              + codEstadoBus              + SEPARADOR);
		stringBuffer.append(",idSssaludBus: "              + idSssaludBus              + SEPARADOR);
		stringBuffer.append(",codCaIsoBus: "               + codCaIsoBus               + SEPARADOR);
		stringBuffer.append(",nombreBus: "                 + nombreBus                 + SEPARADOR);
		stringBuffer.append(",apellido1Bus: "              + apellido1Bus              + SEPARADOR);
		stringBuffer.append(",apellido2Bus: "              + apellido2Bus              + SEPARADOR);
		stringBuffer.append(",cod_sexoBus: "               + cod_sexoBus               + SEPARADOR);
		stringBuffer.append(",fechaNacBus: "               + fechaNacBus               + SEPARADOR);
		stringBuffer.append(",raizBus: "                   + raizBus                   + SEPARADOR);
		stringBuffer.append(",dniNieBus: "                 + dniNieBus                 + SEPARADOR);
		stringBuffer.append(",pasaporteBus: "              + pasaporteBus              + SEPARADOR);
		stringBuffer.append(",nafBus: "                    + nafBus                    + SEPARADOR);
		stringBuffer.append(",nafTitularBus: "             + nafTitularBus             + SEPARADOR);
		stringBuffer.append(",codTituloBus: "              + codTituloBus              + SEPARADOR);
		stringBuffer.append(",codSituacionBus: "           + codSituacionBus           + SEPARADOR);
		stringBuffer.append(",codIndicadorFarmacia: "      + codIndicadorFarmacia      + SEPARADOR);
		stringBuffer.append(",codSubindicadorFarmacia: "   + codSubindicadorFarmacia   + SEPARADOR);
		stringBuffer.append(",codTipoPocedencia: "         + codTipoPocedencia         + SEPARADOR);
		//
		stringBuffer.append(",criterio: "                  + criterio                  + SEPARADOR);
		stringBuffer.append(",criterioCompleto: "          + criterioCompleto          + SEPARADOR);
		stringBuffer.append(",diferencia: "                + diferencia                + SEPARADOR);
		stringBuffer.append(",camposCoincidentes: "        + camposCoincidentes        + SEPARADOR);
		stringBuffer.append(",camposIgualesAproximacion: " + camposIgualesAproximacion + SEPARADOR);
		stringBuffer.append(",porcentaje: "                + porcentaje                + SEPARADOR);
		stringBuffer.append(",tipocampo: "                 + tipocampo                 + SEPARADOR);
		stringBuffer.append(",tiporesultado: "             + tiporesultado             + SEPARADOR);
		return stringBuffer.toString();
	}
	
	
}
