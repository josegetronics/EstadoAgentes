package sns.comun.bean;

import gasai.util.Misc;
import java.util.HashMap;
import java.sql.ResultSet;
import org.apache.log4j.Logger;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class DatosLecturaBean {
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	private int    secuencial = 0;
	private String codUsuarioSns = "";
	private String dniNie = "";
	private String dniDup = "";
	private String pasaporte = "";
	private String naf = "";
	private String nafTitular = "";
	private String nombre = "";
	private String apellido1 = "";
	private String apellido2 = "";
	private String sexo = "";
	private String fechaNac = "";
	private String idSsalud = "";
	private String extranjero = "";
	private String fechaAlta = "";
	private String fechaModificacion = "";
	private String paisNacimiento = "";
	private String caNac = "";
	private String tarjetaIdentidad = "";
	private String codNacionalidad = "";
	private String tipoVia = "";
	private String nombrevia = "";
	private String numero = "";
	private String bis = "";
	private String bloque = "";
	private String escalera = "";
	private String piso = "";
	private String puerta = "";
	private String municipio = "";
	private String provincia = "";
	private String codigoPostal = "";
	private String codigoAseguradora = "";
	private String codigoGestora = "";
	private String codigoColaboradora = "";
	private String codigoProveedor = "";
	private String codigoTitulo = "";
	private String codigoSituacion = "";
	//
	private String raiz        = "";
	private String codAgente   = "";
	private String codEstado   = "";
	
	private boolean encontrado  = false;	
	
	
	public DatosLecturaBean() {
	}
	

	public DatosLecturaBean (HashMap<String, String> mapDatos, int secuencial) throws Exception {
		//
		try {
			if (mapDatos.containsKey("COD_USUARIO_SNS")) {
				this.codUsuarioSns = Misc.nz(mapDatos.get("COD_USUARIO_SNS"));
			}
			this.dniNie = Misc.nz(mapDatos.get("DNI_NIE"));						
			this.dniDup = Misc.nz(mapDatos.get("DNIDUP"));
			this.pasaporte = Misc.nz(mapDatos.get("PASAPORTE"));
			this.naf = Misc.nz(mapDatos.get("NAF"));
			this.nafTitular = Misc.nz(mapDatos.get("NAF_TITULAR"));
			this.nombre = Misc.nz(mapDatos.get("NOMBRE"));
			this.apellido1 = Misc.nz(mapDatos.get("APELLIDO1"));
			this.apellido2 = Misc.nz(mapDatos.get("APELLIDO2"));
			this.sexo = Misc.nz(mapDatos.get("SEXO"));
			this.fechaNac = Misc.nz(mapDatos.get("FECHA_NAC"));
			this.idSsalud = Misc.nz(mapDatos.get("ID_SSALUD"));
			this.extranjero = Misc.nz(mapDatos.get("EXTRANJERO"));
			this.fechaAlta = Misc.nz(mapDatos.get("FECHA_ALTA"));
			this.fechaModificacion = Misc.nz(mapDatos.get("FECHA_ULT_MOD"));
			this.paisNacimiento = Misc.nz(mapDatos.get("PAIS_NACIMIENTO"));
			this.caNac = Misc.nz(mapDatos.get("CA_NAC"));
			this.tarjetaIdentidad = Misc.nz(mapDatos.get("TARJETA_IDENTIDAD"));
			this.codNacionalidad = Misc.nz(mapDatos.get("COD_NACIONALIDAD"));
			this.tipoVia = Misc.nz(mapDatos.get("TIPO_VIA"));
			this.nombrevia = Misc.nz(mapDatos.get("NOMBREVIA"));
			this.numero = Misc.nz(mapDatos.get("NUMERO"));
			this.bis = Misc.nz(mapDatos.get("BIS"));
			this.bloque = Misc.nz(mapDatos.get("BLOQUE"));
			this.escalera = Misc.nz(mapDatos.get("ESCALERA"));
			this.piso = Misc.nz(mapDatos.get("PISO"));
			this.puerta = Misc.nz(mapDatos.get("PUERTA"));
			this.municipio = Misc.nz(mapDatos.get("MUNICIPIO"));
			this.provincia = Misc.nz(mapDatos.get("PROVINCIA"));
			this.codigoPostal = Misc.nz(mapDatos.get("CODIGO_POSTAL"));
			this.codigoAseguradora = Misc.nz(mapDatos.get("CODIGO_ASEGURADORA"));
			this.codigoGestora = Misc.nz(mapDatos.get("CODIGO_GESTORA"));
			this.codigoColaboradora = Misc.nz(mapDatos.get("CODIGO_COLABORADORA"));
			this.codigoProveedor = Misc.nz(mapDatos.get("CODIGO_PROVEEDOR"));
			this.codigoTitulo = Misc.nz(mapDatos.get("CODIGO_TITULO"));
			this.codigoSituacion = Misc.nz(mapDatos.get("CODIGO_SITUACION"));
			//
			this.secuencial = secuencial;
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}

	public DatosLecturaBean (ResultSet resultSet, int secuencial) throws Exception {
		//
		try {
			this.codUsuarioSns = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
			this.idSsalud      = Misc.nz(resultSet.getString("ID_EN_SSALUD"));
			this.codAgente     = Misc.nz(resultSet.getString("COD_AGENTE"));
			this.codEstado     = Misc.nz(resultSet.getString("COD_ESTADO"));
			this.dniNie        = Misc.nz(resultSet.getString("DNI_NIE"));
			this.pasaporte     = Misc.nz(resultSet.getString("PASAPORTE"));
			this.naf           = Misc.nz(resultSet.getString("NAF"));
			this.nombre        = Misc.nz(resultSet.getString("NOMBRE"));
			this.apellido1     = Misc.nz(resultSet.getString("APELLIDO1"));
			this.apellido2     = Misc.nz(resultSet.getString("APELLIDO2"));
			this.sexo          = Misc.nz(resultSet.getString("SEXO"));
			this.fechaNac      = Misc.nz(resultSet.getString("FECHA_NACIMIENTO_RAW"));
			this.nafTitular    = Misc.nz(resultSet.getString("NAF_TITULAR"));
			this.secuencial    = secuencial;
			//
			if(!Misc.esVacio(this.fechaNac)) {
				String day    = fechaNac.substring(6, 8);
				String month  = fechaNac.substring(4, 6);
				String year   = fechaNac.substring(0, 4);
				this.fechaNac = year + "-" + month + "-" + day;
			}
			else {
				logger.debug("fecha nac es vacia");
			}
			//
     		//
			//this.dniDup = Misc.nz(resultSet.getString("DNIDUP"));
			//this.nafTitular = Misc.nz(resultSet.getString("NAF_TITULAR"));
			//this.idSsalud = Misc.nz(resultSet.getString("ID_SSALUD"));
			//this.extranjero = Misc.nz(resultSet.getString("EXTRANJERO"));
			//this.fechaAlta = Misc.nz(resultSet.getString("FECHA_ALTA"));
			//this.fechaModificacion = Misc.nz(resultSet.getString("FECHA_ULT_MOD"));
			//this.paisNacimiento = Misc.nz(resultSet.getString("PAIS_NACIMIENTO"));
			//this.caNac = Misc.nz(resultSet.getString("CA_NAC"));
			//this.tarjetaIdentidad = Misc.nz(resultSet.getString("TARJETA_IDENTIDAD"));
			//this.codNacionalidad = Misc.nz(resultSet.getString("COD_NACIONALIDAD"));
			//this.tipoVia = Misc.nz(resultSet.getString("TIPO_VIA"));
			//this.nombrevia = Misc.nz(resultSet.getString("NOMBREVIA"));
			//this.numero = Misc.nz(resultSet.getString("NUMERO"));
			//this.bis = Misc.nz(resultSet.getString("BIS"));
			//this.bloque = Misc.nz(resultSet.getString("BLOQUE"));
			//this.escalera = Misc.nz(resultSet.getString("ESCALERA"));
			//this.piso = Misc.nz(resultSet.getString("PISO"));
			//this.puerta = Misc.nz(resultSet.getString("PUERTA"));
			//this.municipio = Misc.nz(resultSet.getString("MUNICIPIO"));
			//this.provincia = Misc.nz(resultSet.getString("PROVINCIA"));
			//this.codigoPostal = Misc.nz(resultSet.getString("CODIGO_POSTAL"));
			//this.codigoAseguradora = Misc.nz(resultSet.getString("CODIGO_ASEGURADORA"));
			//this.codigoGestora = Misc.nz(resultSet.getString("CODIGO_GESTORA"));
			//this.codigoColaboradora = Misc.nz(resultSet.getString("CODIGO_COLABORADORA"));
			//this.codigoProveedor = Misc.nz(resultSet.getString("CODIGO_PROVEEDOR"));
			//this.codigoTitulo = Misc.nz(resultSet.getString("CODIGO_TITULO"));
			//this.codigoSituacion = Misc.nz(resultSet.getString("CODIGO_SITUACION"));
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw e;
		}
	}
	

	public DatosLecturaBean (String line, int secuencial) throws Exception {
		//
		String linea = line;
		//
		try {
			//
			this.secuencial = secuencial;
			//
			for (int i = 0; i < 36; i++) {
				//
				String value = line.substring(0, line.indexOf("|"));
				line = line.substring(line.indexOf("|") + 1);
				//
				switch (i) {
				case 0:
					this.setCodUsuarioSns(Misc.nz(value).trim());
				break;
				case 1:
					String dni = Misc.nz(value).trim();
					this.setDniNie(dni);
					break;
				case 2:
					this.setDniDup(Misc.nz(value).trim());
					break;
				case 3:
					this.setPasaporte(Misc.nz(value).trim());
					break;
				case 4:
					this.setNaf(Misc.nz(value).trim());
					break;
				case 5:
					this.setNafTitular(Misc.nz(value).trim());
					break;
				case 6:
					this.setNombre(Misc.nz(value).trim());
					break;
				case 7:
					this.setApellido1(Misc.nz(value).trim());
					break;
				case 8:
					this.setApellido2(Misc.nz(value).trim());
					break;
				case 9:
					this.setSexo(Misc.nz(value).trim());
					//
					if(this.getSexo().equals("0")){
						//this.setSexo("2");
						//logger.debug("cambio sexo");
					}
					//
					break;
				case 10:
					this.setFechaNac(Misc.nz(value).trim());
					break;
				case 11:
					this.setIdSsalud(Misc.nz(value).trim());
					break;
				case 12:
					this.setExtranjero(Misc.nz(value).trim());
					break;
				case 13:
					this.setFechaAlta(Misc.nz(value).trim());
					break;
				case 14:
					this.setFechaModificacion(Misc.nz(value).trim());
					break;
				case 15:
					this.setPaisNacimiento(Misc.nz(value).trim());
					break;
				case 16:
					this.setCaNac(Misc.nz(value).trim());
					break;
				case 17:
					this.setTarjetaIdentidad(Misc.nz(value).trim());
					break;
				case 18:
					this.setCodNacionalidad(Misc.nz(value).trim());
					break;
				case 19:
					this.setTipoVia(Misc.nz(value).trim());
					break;
				case 20:
					this.setNombrevia(Misc.nz(value).trim());
					break;
				case 21:
					this.setNumero(Misc.nz(value).trim());
					break;
				case 22:
					this.setBis(Misc.nz(value).trim());
					break;
				case 23:
					this.setBloque(Misc.nz(value).trim());
					break;
				case 24:
					this.setEscalera(Misc.nz(value).trim());
					break;
				case 25:
					this.setPiso(Misc.nz(value).trim());
					break;
				case 26:
					this.setPuerta(Misc.nz(value).trim());
					break;
				case 27:
					this.setMunicipio(Misc.nz(value).trim());
					break;
				case 28:
					this.setProvincia(Misc.nz(value).trim());
					break;
				case 29:
					this.setCodigoPostal(Misc.nz(value).trim());
					break;
				case 30:
					this.setCodigoAseguradora(Misc.nz(value).trim());
					break;
				case 31:
					this.setCodigoGestora(Misc.nz(value).trim());
					break;
				case 32:
					this.setCodigoColaboradora(Misc.nz(value).trim());
					break;
				case 33:
					this.setCodigoProveedor(Misc.nz(value).trim());
					break;
				case 34:
					this.setCodigoTitulo(Misc.nz(value).trim());
					break;
				case 35:
					this.setCodigoSituacion(Misc.nz(value).trim());
					break;
				}
			}
			//
			raiz = sns.util.OperacionesCodSS.genera(apellido1, apellido2, sexo, fechaNac);
			//
			if (Misc.nz(raiz).length()<10){
				logger.error("-------------------------------------------");
				logger.error("-------------------------------------------");
				logger.error("raiz: " + raiz + "linea: " + linea);
				logger.error("-------------------------------------------");
				logger.error("-------------------------------------------");
			}			
		}
		catch (Exception e) {
			logger.error("-------------------------------------------");
			logger.error("-------------------------------------------");
			this.view();
			logger.error("raiz: " + raiz);
			logger.error("Exception: " + e.getMessage());
			logger.error("linea: " + linea);
			logger.error("-------------------------------------------");
			logger.error("-------------------------------------------");
			throw e;
		}
	}

	public DatosLecturaBean (String line, int secuencial, Boolean ss) throws Exception {
		//
		String linea = line;
		//
		try {
			//
			this.secuencial = secuencial;
			//
			String[] array = line.split("\\|");
			//
			for (int i = 0; i < array.length; i++) {
				//
				switch (i) {
				case 0:
					this.setDniNie(Misc.nz(array[i]));
					break;
				case 1:
					this.setDniDup(Misc.nz(array[i]));
					break;
				case 2:
					this.setPasaporte(Misc.nz(array[i]));
					break;
				case 3:
					this.setNaf(Misc.nz(array[i]));
					break;
				case 4:
					this.setNafTitular(Misc.nz(array[i]));
					break;
				case 5:
					this.setNombre(Misc.nz(array[i]));
					break;
				case 6:
					this.setApellido1(Misc.nz(array[i]));
					break;
				case 7:
					this.setApellido2(Misc.nz(array[i]));
					break;
				case 8:
					this.setSexo(Misc.nz(array[i]));
					break;
				case 9:
					this.setFechaNac(Misc.nz(array[i]));
					break;
				case 10:
					this.setIdSsalud(Misc.nz(array[i]));
					break;
				case 11:
					this.setExtranjero(Misc.nz(array[i]));
					break;
				case 12:
					this.setFechaAlta(Misc.nz(array[i]));
					break;
				case 13:
					this.setFechaModificacion(Misc.nz(array[i]));
					break;
				case 14:
					this.setPaisNacimiento(Misc.nz(array[i]));
					break;
				case 15:
					this.setCaNac(Misc.nz(array[i]));
					break;
				case 16:
					this.setTarjetaIdentidad(Misc.nz(array[i]));
					break;
				case 17:
					this.setCodNacionalidad(Misc.nz(array[i]));
					break;
				case 18:
					this.setTipoVia(Misc.nz(array[i]));
					break;
				case 19:
					this.setNombrevia(Misc.nz(array[i]));
					break;
				case 20:
					this.setNumero(Misc.nz(array[i]));
					break;
				case 21:
					this.setBis(Misc.nz(array[i]));
					break;
				case 22:
					this.setBloque(Misc.nz(array[i]));
					break;
				case 23:
					this.setEscalera(Misc.nz(array[i]));
					break;
				case 24:
					this.setPiso(Misc.nz(array[i]));
					break;
				case 25:
					this.setPuerta(Misc.nz(array[i]));
					break;
				case 26:
					this.setMunicipio(Misc.nz(array[i]));
					break;
				case 27:
					this.setProvincia(Misc.nz(array[i]));
					break;
				case 28:
					this.setCodigoPostal(Misc.nz(array[i]));
					break;
				case 29:
					this.setCodigoAseguradora(Misc.nz(array[i]));
					break;
				case 30:
					this.setCodigoGestora(Misc.nz(array[i]));
					break;
				case 31:
					this.setCodigoColaboradora(Misc.nz(array[i]));
					break;
				case 32:
					this.setCodigoProveedor(Misc.nz(array[i]));
					break;
				case 33:
					this.setCodigoTitulo(Misc.nz(array[i]));
					break;
				case 34:
					this.setCodigoSituacion(Misc.nz(array[i]));
					break;
				}
				raiz = sns.util.OperacionesCodSS.genera(apellido1, apellido2, sexo, fechaNac);
			}
		} catch (Exception e) {
			logger.error("-------------------------------------------");
			logger.error("-------------------------------------------");
			this.view();
			logger.error("raiz: " + raiz);
			logger.error("Exception: " + e.getMessage());
			logger.error("linea: " + linea);
			logger.error("-------------------------------------------");
			logger.error("-------------------------------------------");
			throw e;
		}
	}

	public String getExtranjero() {
		return extranjero;
	}

	public String getBloque() {
		return bloque;
	}

	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public String getCaNac() {
		return caNac;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public String getCodigoAseguradora() {
		return codigoAseguradora;
	}

	public String getNumero() {
		return numero;
	}

	public String getCodigoSituacion() {
		return codigoSituacion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNafTitular() {
		return nafTitular;
	}

	public String getNaf() {
		return naf;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEscalera() {
		return escalera;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCodigoGestora() {
		return codigoGestora;
	}

	public String getCodigoTitulo() {
		return codigoTitulo;
	}

	public String getBis() {
		return bis;
	}

	public String getTarjetaIdentidad() {
		return tarjetaIdentidad;
	}

	public String getPuerta() {
		return puerta;
	}

	public String getCodNacionalidad() {
		return codNacionalidad;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public String getNombrevia() {
		return nombrevia;
	}

	public String getDniNie() {
		return dniNie;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getPiso() {
		return piso;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public String getIdSsalud() {
		return idSsalud;
	}

	public void setCodigoColaboradora(String codigoColaboradora) {
		this.codigoColaboradora = codigoColaboradora;
	}

	public void setExtranjero(String extranjero) {
		this.extranjero = extranjero;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public void setCaNac(String caNac) {
		this.caNac = caNac;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setCodigoAseguradora(String codigoAseguradora) {
		this.codigoAseguradora = codigoAseguradora;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCodigoSituacion(String codigoSituacion) {
		this.codigoSituacion = codigoSituacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCodigoGestora(String codigoGestora) {
		this.codigoGestora = codigoGestora;
	}

	public void setCodigoTitulo(String codigoTitulo) {
		this.codigoTitulo = codigoTitulo;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public void setTarjetaIdentidad(String tarjetaIdentidad) {
		this.tarjetaIdentidad = tarjetaIdentidad;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public void setNombrevia(String nombrevia) {
		this.nombrevia = nombrevia;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setIdSsalud(String idSsalud) {
		this.idSsalud = idSsalud;
	}

	public void setDniDup(String dniDup) {
		this.dniDup = dniDup;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String getCodigoColaboradora() {
		return codigoColaboradora;
	}

	public String getDniDup() {
		return dniDup;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public String getRaiz() {
		return raiz;
	}

	public void setRaiz(String raiz) {
		this.raiz = raiz;
	}

	
	
	
	

	public boolean isEncontrado() {
		return encontrado;
	}


	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}


	public String view() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("dniNie: " + dniNie);
		stringBuffer.append(", DdniDup: " + dniDup);
		stringBuffer.append(", pasaporte: " + pasaporte);
		stringBuffer.append(", naf: " + naf);
		stringBuffer.append(", nafTitular: " + nafTitular);
		stringBuffer.append(", nombre: " + nombre);
		stringBuffer.append(", apellido1: " + apellido1);
		stringBuffer.append(", apellido2: " + apellido2);
		stringBuffer.append(", sexo: " + sexo);
		stringBuffer.append(", fechaNac: " + fechaNac);
		stringBuffer.append(", idSsalud: " + idSsalud);
		stringBuffer.append(", extranjero: " + extranjero);
		stringBuffer.append(", fechaAlta: " + fechaAlta);
		stringBuffer.append(", fechaModificacion: " + fechaModificacion);
		stringBuffer.append(", paisNacimiento: " + paisNacimiento);
		stringBuffer.append(", caNac: " + caNac);
		stringBuffer.append(", tarjetaIdentidad: " + tarjetaIdentidad);
		stringBuffer.append(", codNacionalidad: " + codNacionalidad);
		stringBuffer.append(", tipoVia: " + tipoVia);
		stringBuffer.append(", nombrevia: " + nombrevia);
		stringBuffer.append(", numero: " + numero);
		stringBuffer.append(", bis: " + bis);
		stringBuffer.append(", bloque: " + bloque);
		stringBuffer.append(", escalera: " + escalera);
		stringBuffer.append(", piso: " + piso);
		stringBuffer.append(", puerta: " + puerta);
		stringBuffer.append(", municipio: " + municipio);
		stringBuffer.append(", provincia: " + provincia);
		stringBuffer.append(", codigoPostal: " + codigoPostal);
		stringBuffer.append(", codigoAseguradora: " + codigoAseguradora);
		stringBuffer.append(", codigoGestora: " + codigoGestora);
		stringBuffer.append(", codigoColaboradora: " + codigoColaboradora);
		stringBuffer.append(", codigoProveedor: " + codigoProveedor);
		stringBuffer.append(", codigoTitulo: " + codigoTitulo);
		stringBuffer.append(", codigoSituacion: " + codigoSituacion);
		String cadena = stringBuffer.toString();
		return cadena;
	}

	public String toLinea() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		//
		if (!Misc.esVacio(codUsuarioSns)) {
			stringBuffer.append(codUsuarioSns + "|");
		}
		stringBuffer.append(dniNie + "|");
		//stringBuffer.append(dniDup + "|");
		stringBuffer.append(pasaporte + "|");
		stringBuffer.append(naf + "|");
		stringBuffer.append(nafTitular + "|");
		stringBuffer.append(nombre + "|");
		stringBuffer.append(apellido1 + "|");
		stringBuffer.append(apellido2 + "|");
		stringBuffer.append(sexo + "|");
		stringBuffer.append(fechaNac + "|");
		stringBuffer.append(idSsalud + "|");
		stringBuffer.append(extranjero + "|");
		stringBuffer.append(fechaAlta + "|");
		stringBuffer.append(fechaModificacion + "|");
		stringBuffer.append(paisNacimiento + "|");
		stringBuffer.append(caNac + "|");
		stringBuffer.append(tarjetaIdentidad + "|");
		stringBuffer.append(codNacionalidad + "|");
		stringBuffer.append(tipoVia + "|");
		stringBuffer.append(nombrevia + "|");
		stringBuffer.append(numero + "|");
		stringBuffer.append(bis + "|");
		stringBuffer.append(bloque + "|");
		stringBuffer.append(escalera + "|");
		stringBuffer.append(piso + "|");
		stringBuffer.append(puerta + "|");
		stringBuffer.append(municipio + "|");
		stringBuffer.append(provincia + "|");
		stringBuffer.append(codigoPostal + "|");
		stringBuffer.append(codigoAseguradora + "|");
		stringBuffer.append(codigoGestora + "|");
		stringBuffer.append(codigoColaboradora + "|");
		stringBuffer.append(codigoProveedor + "|");
		stringBuffer.append(codigoTitulo + "|");
		stringBuffer.append(codigoSituacion + "|");
		String cadena = stringBuffer.toString();
		return cadena;
	}

	
	public String toLineaDup() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(secuencial    + "|");
		stringBuffer.append(codUsuarioSns + "|");
		stringBuffer.append(codEstado     + "|");
		stringBuffer.append(dniNie        + "|");
		stringBuffer.append(naf           + "|");
		stringBuffer.append(nafTitular    + "|");
		stringBuffer.append(nombre        + "|");
		stringBuffer.append(apellido1     + "|");
		stringBuffer.append(apellido2     + "|");
		stringBuffer.append(sexo          + "|");
		stringBuffer.append(fechaNac      + "|");
		stringBuffer.append(idSsalud      + "|");
		stringBuffer.append(encontrado    + "|");
		return stringBuffer.toString();
	}
	
	
	public String toLinea22() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		//
		stringBuffer.append(dniNie + "|");
		stringBuffer.append(pasaporte + "|");
		stringBuffer.append(naf + "|");
		stringBuffer.append(nafTitular + "|");
		stringBuffer.append(nombre + "|");
		stringBuffer.append(apellido1 + "|");
		stringBuffer.append(apellido2 + "|");
		stringBuffer.append(sexo + "|");
		stringBuffer.append(fechaNac + "|");
		stringBuffer.append(idSsalud + "|");
		stringBuffer.append(codigoTitulo + "|");
		stringBuffer.append(codigoSituacion + "|");
		String cadena = stringBuffer.toString();
		return cadena;
	}

	public static String cabecera2() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("DNI_NIE" + "|");
		stringBuffer.append("PASAPORTE" + "|");
		stringBuffer.append("NAF" + "|");
		stringBuffer.append("NAF_TITULAR" + "|");
		stringBuffer.append("NOMBRE" + "|");
		stringBuffer.append("APELLIDO1" + "|");
		stringBuffer.append("APELLIDO2" + "|");
		stringBuffer.append("SEXO" + "|");
		stringBuffer.append("FECHA_NAC" + "|");
		stringBuffer.append("ID_SSALUD" + "|");
		stringBuffer.append("CODIGO_TITULO" + "|");
		stringBuffer.append("CODIGO_SITUACION" + "|");
		String cadena = stringBuffer.toString();
		return cadena;
	}

	public static String cabecera() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("DNI_NIE" + "|");
		//stringBuffer.append("DNIDUP" + "|");
		stringBuffer.append("PASAPORTE" + "|");
		stringBuffer.append("NAF" + "|");
		stringBuffer.append("NAF_TITULAR" + "|");
		stringBuffer.append("NOMBRE" + "|");
		stringBuffer.append("APELLIDO1" + "|");
		stringBuffer.append("APELLIDO2" + "|");
		stringBuffer.append("SEXO" + "|");
		stringBuffer.append("FECHA_NAC" + "|");
		stringBuffer.append("ID_SSALUD" + "|");
		//stringBuffer.append("EXTRANJERO" + "|");
		//stringBuffer.append("FECHA_ALTA" + "|");
		//stringBuffer.append("FECHA_ULT_MOD" + "|");
		//stringBuffer.append("PAIS_NACIMIENTO" + "|");
		stringBuffer.append("CA_NAC" + "|");
		//stringBuffer.append("TARJETA_IDENTIDAD" + "|");
		//stringBuffer.append("COD_NACIONALIDAD" + "|");
		//stringBuffer.append("TIPO_VIA" + "|");
		//stringBuffer.append("NOMBREVIA" + "|");
		//stringBuffer.append("NUMERO" + "|");
		//stringBuffer.append("BIS" + "|");
		//stringBuffer.append("BLOQUE" + "|");
		//stringBuffer.append("ESCALERA" + "|");
		//stringBuffer.append("PISO" + "|");
		//stringBuffer.append("PUERTA" + "|");
		//stringBuffer.append("MUNICIPIO" + "|");
		//stringBuffer.append("PROVINCIA" + "|");
		//stringBuffer.append("CODIGO_POSTAL" + "|");
		stringBuffer.append("CODIGO_ASEGURADORA" + "|");
		stringBuffer.append("CODIGO_GESTORA" + "|");
		//stringBuffer.append("CODIGO_COLABORADORA" + "|");
		stringBuffer.append("CODIGO_PROVEEDOR" + "|");
		stringBuffer.append("CODIGO_TITULO" + "|");
		stringBuffer.append("CODIGO_SITUACION" + "|");
		String cadena = stringBuffer.toString();
		return cadena;
	}

	public static String cabeceraSns() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("CODIGO_USUARIO_SNS" + "|");
		stringBuffer.append("DNI_NIE" + "|");
		//stringBuffer.append("DNIDUP" + "|");
		stringBuffer.append("PASAPORTE" + "|");
		stringBuffer.append("NAF" + "|");
		stringBuffer.append("NAF_TITULAR" + "|");
		stringBuffer.append("NOMBRE" + "|");
		stringBuffer.append("APELLIDO1" + "|");
		stringBuffer.append("APELLIDO2" + "|");
		stringBuffer.append("SEXO" + "|");
		stringBuffer.append("FECHA_NAC" + "|");
		stringBuffer.append("ID_SSALUD" + "|");
		//stringBuffer.append("EXTRANJERO" + "|");
		//stringBuffer.append("FECHA_ALTA" + "|");
		//stringBuffer.append("FECHA_ULT_MOD" + "|");
		//stringBuffer.append("PAIS_NACIMIENTO" + "|");
		stringBuffer.append("CA_NAC" + "|");
		//stringBuffer.append("TARJETA_IDENTIDAD" + "|");
		//stringBuffer.append("COD_NACIONALIDAD" + "|");
		//stringBuffer.append("TIPO_VIA" + "|");
		//stringBuffer.append("NOMBREVIA" + "|");
		//stringBuffer.append("NUMERO" + "|");
		//stringBuffer.append("BIS" + "|");
		//stringBuffer.append("BLOQUE" + "|");
		//stringBuffer.append("ESCALERA" + "|");
		//stringBuffer.append("PISO" + "|");
		//stringBuffer.append("PUERTA" + "|");
		//stringBuffer.append("MUNICIPIO" + "|");
		//stringBuffer.append("PROVINCIA" + "|");
		//stringBuffer.append("CODIGO_POSTAL" + "|");
		stringBuffer.append("CODIGO_ASEGURADORA" + "|");
		stringBuffer.append("CODIGO_GESTORA" + "|");
		//stringBuffer.append("CODIGO_COLABORADORA" + "|");
		stringBuffer.append("CODIGO_PROVEEDOR" + "|");
		stringBuffer.append("CODIGO_TITULO" + "|");
		stringBuffer.append("CODIGO_SITUACION" + "|");
		String cadena = stringBuffer.toString();
		return cadena;
	}
	

	public String getLineaEntrada() {
		//
		StringBuffer str = new StringBuffer();
		str.append(secuencial          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 0
		str.append(codUsuarioSns       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 1
		str.append(dniNie              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 2
		str.append(pasaporte           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 3
		str.append(naf                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 4
		str.append(nafTitular          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 5
		str.append(nombre              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 6
		str.append(apellido1           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 7
		str.append(apellido2           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 8
		str.append(sexo                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 9
		str.append(fechaNac            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 10
		str.append(idSsalud            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 33
		//str.append(codAgente           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		str.append(codEstado           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}
	
	
	public String getLineaEntrada2() {
		//
		StringBuffer str = new StringBuffer();
		// 0
		str.append(dniNie              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 1
		str.append(dniDup              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 2
		str.append(pasaporte           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 3
		str.append(naf                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 4
		str.append(nafTitular          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 5
		str.append(nombre              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 6
		str.append(apellido1           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 7
		str.append(apellido2           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 8
		str.append(sexo                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 9
		str.append(fechaNac            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 10
		str.append(idSsalud            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 11
		str.append(extranjero          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 12
		str.append(fechaAlta           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 13
		str.append(fechaModificacion   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 14
		str.append(paisNacimiento      + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 15
		str.append(caNac               + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 16
		str.append(tarjetaIdentidad    + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 17
		str.append(codNacionalidad     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 18
		str.append(tipoVia             + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 19
		str.append(nombrevia           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 20
		str.append(numero              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 21
		str.append(bis                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 22
		str.append(bloque              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 23
		str.append(escalera            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 24
		str.append(piso                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 25
		str.append(puerta              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 26
		str.append(municipio           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 27
		str.append(provincia           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 28
		str.append(codigoPostal        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 29
		str.append(codigoAseguradora   + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 30
		str.append(codigoGestora       + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 31
		str.append(codigoColaboradora  + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 32
		str.append(codigoProveedor     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 33
		str.append(codigoTitulo        + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 34
		str.append(codigoSituacion     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		//
		return str.toString();
	}
	
	
	public String getCarga () {
		// 
		StringBuffer str = new StringBuffer();
		// 1
		str.append(dniNie              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 2
		str.append(pasaporte           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 3
		str.append(naf                 + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 4
		str.append(nafTitular          + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 5
		str.append(nombre              + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 6
		str.append(apellido1           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 7
		str.append(apellido2           + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 8
		str.append(sexo                + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 9
		str.append(fechaNac            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		// 10
		str.append(idSsalud            + ConstantesBusqueda.SEPARADOR_CAMPOS);
		//
		return str.toString();
	}
	
	
	
	
	

}
