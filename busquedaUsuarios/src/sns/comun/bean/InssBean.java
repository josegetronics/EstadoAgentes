package sns.comun.bean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.util.Fecha;
import gasai.util.Misc;


public class InssBean {

	private String idInss;
	private String codTipoAsegurado;
	private String tipoMovimiento;
	private String ipf;
	private String dniNie;
	private String pasaporte;
	private String naf;
	private String nafSec1;
	private String nafSec2;
	private String nafSec3;
	private String nafSec4;
	private String nafSec5;
	private String nafSec6;
	private String nafSec7;
	private String nafSec8;
	private String nafSec9;
	private String indicativoNombre;
	private String apellidosNombre;
	private String apellido1;
	private String apellido2;
	private String nombre;
	private String nacionalidad;
	private String fechaNacimiento;
	private String sexo;
	private String indicativoDomicilio;
	private String domicilio;
	private String tipoAseguramiento;
	private String codIndicadorDeFarmacia;
	private String codSubindicadorDeFarmacia;
	private String codSituacion;
	private String fechaEfectoSituacion;
	private String codTipoBeneficiario;
	private String ipfTitular;
	private String nafTitular;
	private String numeroSecuencia;
	private String fechaNacimientoRaw;
	private String ipfAnterior;
	private String codigoBadas;
	private String motivoBaja;
	private String codUsuarioSns;
	private String criterioIdentificacion;
	//
	private	ArrayList <String> arrayListNafSec = new ArrayList <String> ();


	public InssBean() {
	}

	public InssBean(HashMap<String, String> hd) throws Exception {
		this.idInss = Misc.nz(hd.get("ID_INSS"));
		this.codTipoAsegurado = Misc.nz(hd.get("COD_TIPO_ASEGURADO"));
		this.ipf = Misc.nz(hd.get("IPF"));
		this.dniNie = Misc.nz(hd.get("DNI_NIE"));
		this.pasaporte = Misc.nz(hd.get("PASAPORTE"));
		this.naf = Misc.nz(hd.get("NAF"));
		this.nafSec1 = Misc.nz(hd.get("NAF_SEC1"));
		this.nafSec2 = Misc.nz(hd.get("NAF_SEC2"));
		this.nafSec3 = Misc.nz(hd.get("NAF_SEC3"));
		this.nafSec4 = Misc.nz(hd.get("NAF_SEC4"));
		this.nafSec5 = Misc.nz(hd.get("NAF_SEC5"));
		this.nafSec6 = Misc.nz(hd.get("NAF_SEC6"));
		this.nafSec7 = Misc.nz(hd.get("NAF_SEC7"));
		this.nafSec8 = Misc.nz(hd.get("NAF_SEC8"));
		this.nafSec9 = Misc.nz(hd.get("NAF_SEC9"));
		this.indicativoNombre = Misc.nz(hd.get("INDICATIVO_NOMBRE"));
		this.apellidosNombre = Misc.nz(hd.get("APELLIDOS_NOMBRE"));
		this.apellido1 = Misc.nz(hd.get("APELLIDO1"));
		this.apellido2 = Misc.nz(hd.get("APELLIDO2"));
		this.nombre = Misc.nz(hd.get("NOMBRE"));
		this.nacionalidad = Misc.nz(hd.get("NACIONALIDAD"));
		this.fechaNacimiento = Misc.nz(hd.get("FECHA_NACIMIENTO"));
		this.sexo = Misc.nz(hd.get("SEXO"));
		this.indicativoDomicilio = Misc.nz(hd.get("INDICATIVO_DOMICILIO"));
		this.domicilio = Misc.nz(hd.get("DOMICILIO"));
		this.tipoAseguramiento = Misc.nz(hd.get("TIPO_ASEGURAMIENTO"));
		this.codIndicadorDeFarmacia = Misc.nz(hd.get("COD_INDICADOR_DE_FARMACIA"));
		this.codSubindicadorDeFarmacia = Misc.nz(hd.get("COD_SUBINDICADOR_DE_FARMACIA"));
		this.codSituacion = Misc.nz(hd.get("COD_SITUACION"));
		this.fechaEfectoSituacion = Misc.nz(hd.get("FECHA_EFECTO_SITUACION"));
		this.codTipoBeneficiario = Misc.nz(hd.get("COD_TIPO_BENEFICIARIO"));
		this.ipfTitular = Misc.nz(hd.get("IPF_TITULAR"));
		this.nafTitular = Misc.nz(hd.get("NAF_TITULAR"));
		this.numeroSecuencia = Misc.nz(hd.get("NUMERO_SECUENCIA"));
		this.fechaNacimientoRaw = Misc.nz(hd.get("FECHA_NACIMIENTO_RAW"));
		this.codUsuarioSns = Misc.nz(hd.get("COD_USUARIO_SNS"));
		this.criterioIdentificacion = Misc.nz(hd.get("CRITERIO_IDENTIFICACION_SNS"));
		//
		this.criterioIdentificacion = Misc.remplazar(this.criterioIdentificacion, ConstantesBusqueda.SEPARADOR_CAMPOS, ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO);
		this.fechaNacimientoRaw = Fecha.cambiarFormato (this.fechaNacimientoRaw);
		//
		if(!Misc.esVacio(this.nafSec1)) {
			arrayListNafSec.add(this.nafSec1);
			if(!Misc.esVacio(this.nafSec2)) {
				arrayListNafSec.add(this.nafSec2);
				if(!Misc.esVacio(this.nafSec3)) {
					arrayListNafSec.add(this.nafSec3);
					if(!Misc.esVacio(this.nafSec4)) {
						arrayListNafSec.add(this.nafSec4);
						if(!Misc.esVacio(this.nafSec5)) {
							arrayListNafSec.add(this.nafSec5);
							if(!Misc.esVacio(this.nafSec6)) {
								arrayListNafSec.add(this.nafSec6);
								if(!Misc.esVacio(this.nafSec7)) {
									arrayListNafSec.add(this.nafSec7);
									if(!Misc.esVacio(this.nafSec8)) {
										arrayListNafSec.add(this.nafSec8);
										if(!Misc.esVacio(this.nafSec9)) {
											arrayListNafSec.add(this.nafSec9);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	
	public InssBean(ResultSet rs) throws Exception {
		this.idInss = Misc.nz(rs.getString("ID_INSS"));
		this.codTipoAsegurado = Misc.nz(rs.getString("COD_TIPO_ASEGURADO"));
		this.ipf = Misc.nz(rs.getString("IPF"));
		this.dniNie = Misc.nz(rs.getString("DNI_NIE"));
		this.pasaporte = Misc.nz(rs.getString("PASAPORTE"));
		this.naf = Misc.nz(rs.getString("NAF"));
		this.nafSec1 = Misc.nz(rs.getString("NAF_SEC1"));
		this.nafSec2 = Misc.nz(rs.getString("NAF_SEC2"));
		this.nafSec3 = Misc.nz(rs.getString("NAF_SEC3"));
		this.nafSec4 = Misc.nz(rs.getString("NAF_SEC4"));
		this.nafSec5 = Misc.nz(rs.getString("NAF_SEC5"));
		this.nafSec6 = Misc.nz(rs.getString("NAF_SEC6"));
		this.nafSec7 = Misc.nz(rs.getString("NAF_SEC7"));
		this.nafSec8 = Misc.nz(rs.getString("NAF_SEC8"));
		this.nafSec9 = Misc.nz(rs.getString("NAF_SEC9"));
		this.indicativoNombre = Misc.nz(rs.getString("INDICATIVO_NOMBRE"));
		this.apellidosNombre = Misc.nz(rs.getString("APELLIDOS_NOMBRE"));
		this.apellido1 = Misc.nz(rs.getString("APELLIDO1"));
		this.apellido2 = Misc.nz(rs.getString("APELLIDO2"));
		this.nombre = Misc.nz(rs.getString("NOMBRE"));
		this.nacionalidad = Misc.nz(rs.getString("NACIONALIDAD"));
		this.fechaNacimiento = Misc.nz(rs.getString("FECHA_NACIMIENTO"));
		this.sexo = Misc.nz(rs.getString("SEXO"));
		this.indicativoDomicilio = Misc.nz(rs.getString("INDICATIVO_DOMICILIO"));
		this.domicilio = Misc.nz(rs.getString("DOMICILIO"));
		this.tipoAseguramiento = Misc.nz(rs.getString("TIPO_ASEGURAMIENTO"));
		this.codIndicadorDeFarmacia = Misc.nz(rs.getString("COD_INDICADOR_DE_FARMACIA"));
		this.codSubindicadorDeFarmacia = Misc.nz(rs.getString("COD_SUBINDICADOR_DE_FARMACIA"));
		this.codSituacion = Misc.nz(rs.getString("COD_SITUACION"));
		this.fechaEfectoSituacion = Misc.nz(rs.getString("FECHA_EFECTO_SITUACION"));
		this.codTipoBeneficiario = Misc.nz(rs.getString("COD_TIPO_BENEFICIARIO"));
		this.ipfTitular = Misc.nz(rs.getString("IPF_TITULAR"));
		this.nafTitular = Misc.nz(rs.getString("NAF_TITULAR"));
		this.numeroSecuencia = Misc.nz(rs.getString("NUMERO_SECUENCIA"));
		this.fechaNacimientoRaw = Misc.nz(rs.getString("FECHA_NACIMIENTO_RAW"));
		this.codUsuarioSns = Misc.nz(rs.getString("COD_USUARIO_SNS"));
		this.criterioIdentificacion = Misc.nz(Misc.nz(rs.getString("CRITERIO_IDENTIFICACION_SNS")));
		//
		this.criterioIdentificacion = Misc.remplazar(this.criterioIdentificacion, ConstantesBusqueda.SEPARADOR_CAMPOS, ConstantesBusqueda.SEPARADOR_CAMPOS_ATRIBUTO);
		this.fechaNacimientoRaw = Fecha.cambiarFormato (this.fechaNacimientoRaw);
		//
		if(!Misc.esVacio(this.nafSec1)) {
			arrayListNafSec.add(this.nafSec1);
			if(!Misc.esVacio(this.nafSec2)) {
				arrayListNafSec.add(this.nafSec2);
				if(!Misc.esVacio(this.nafSec3)) {
					arrayListNafSec.add(this.nafSec3);
					if(!Misc.esVacio(this.nafSec4)) {
						arrayListNafSec.add(this.nafSec4);
						if(!Misc.esVacio(this.nafSec5)) {
							arrayListNafSec.add(this.nafSec5);
							if(!Misc.esVacio(this.nafSec6)) {
								arrayListNafSec.add(this.nafSec6);
								if(!Misc.esVacio(this.nafSec7)) {
									arrayListNafSec.add(this.nafSec7);
									if(!Misc.esVacio(this.nafSec8)) {
										arrayListNafSec.add(this.nafSec8);
										if(!Misc.esVacio(this.nafSec9)) {
											arrayListNafSec.add(this.nafSec9);
										}
									}
								}
							}
						}
					}
				}
			}
		}		
	}

	public String getIdInss() {
		return idInss;
	}

	public void setIdInss(String idInss) {
		this.idInss = idInss;
	}

	public String getCodTipoAsegurado() {
		return codTipoAsegurado;
	}

	public void setCodTipoAsegurado(String codTipoAsegurado) {
		this.codTipoAsegurado = codTipoAsegurado;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getIpf() {
		return ipf;
	}

	public void setIpf(String ipf) {
		this.ipf = ipf;
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

	public String getNafSec1() {
		return nafSec1;
	}

	public void setNafSec1(String nafSec1) {
		this.nafSec1 = nafSec1;
	}

	public String getNafSec2() {
		return nafSec2;
	}

	public void setNafSec2(String nafSec2) {
		this.nafSec2 = nafSec2;
	}

	public String getNafSec3() {
		return nafSec3;
	}

	public void setNafSec3(String nafSec3) {
		this.nafSec3 = nafSec3;
	}

	public String getNafSec4() {
		return nafSec4;
	}

	public void setNafSec4(String nafSec4) {
		this.nafSec4 = nafSec4;
	}

	public String getNafSec5() {
		return nafSec5;
	}

	public void setNafSec5(String nafSec5) {
		this.nafSec5 = nafSec5;
	}

	public String getNafSec6() {
		return nafSec6;
	}

	public void setNafSec6(String nafSec6) {
		this.nafSec6 = nafSec6;
	}

	public String getNafSec7() {
		return nafSec7;
	}

	public void setNafSec7(String nafSec7) {
		this.nafSec7 = nafSec7;
	}

	public String getNafSec8() {
		return nafSec8;
	}

	public void setNafSec8(String nafSec8) {
		this.nafSec8 = nafSec8;
	}

	public String getNafSec9() {
		return nafSec9;
	}

	public void setNafSec9(String nafSec9) {
		this.nafSec9 = nafSec9;
	}

	public String getIndicativoNombre() {
		return indicativoNombre;
	}

	public void setIndicativoNombre(String indicativoNombre) {
		this.indicativoNombre = indicativoNombre;
	}

	public String getApellidosNombre() {
		return apellidosNombre;
	}

	public void setApellidosNombre(String apellidosNombre) {
		this.apellidosNombre = apellidosNombre;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIndicativoDomicilio() {
		return indicativoDomicilio;
	}

	public void setIndicativoDomicilio(String indicativoDomicilio) {
		this.indicativoDomicilio = indicativoDomicilio;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTipoAseguramiento() {
		return tipoAseguramiento;
	}

	public void setTipoAseguramiento(String tipoAseguramiento) {
		this.tipoAseguramiento = tipoAseguramiento;
	}

	public String getCodIndicadorDeFarmacia() {
		return codIndicadorDeFarmacia;
	}

	public void setCodIndicadorDeFarmacia(String codIndicadorDeFarmacia) {
		this.codIndicadorDeFarmacia = codIndicadorDeFarmacia;
	}

	public String getCodSubindicadorDeFarmacia() {
		return codSubindicadorDeFarmacia;
	}

	public void setCodSubindicadorDeFarmacia(String codSubindicadorDeFarmacia) {
		this.codSubindicadorDeFarmacia = codSubindicadorDeFarmacia;
	}

	public String getCodSituacion() {
		return codSituacion;
	}

	public void setCodSituacion(String codSituacion) {
		this.codSituacion = codSituacion;
	}

	public String getFechaEfectoSituacion() {
		return fechaEfectoSituacion;
	}

	public void setFechaEfectoSituacion(String fechaEfectoSituacion) {
		this.fechaEfectoSituacion = fechaEfectoSituacion;
	}

	public String getCodTipoBeneficiario() {
		return codTipoBeneficiario;
	}

	public void setCodTipoBeneficiario(String codTipoBeneficiario) {
		this.codTipoBeneficiario = codTipoBeneficiario;
	}

	public String getIpfTitular() {
		return ipfTitular;
	}

	public void setIpfTitular(String ipfTitular) {
		this.ipfTitular = ipfTitular;
	}

	public String getNafTitular() {
		return nafTitular;
	}

	public void setNafTitular(String nafTitular) {
		this.nafTitular = nafTitular;
	}

	public String getNumeroSecuencia() {
		return numeroSecuencia;
	}

	public void setNumeroSecuencia(String numeroSecuencia) {
		this.numeroSecuencia = numeroSecuencia;
	}

	public String getFechaNacimientoRaw() {
		return fechaNacimientoRaw;
	}

	public void setFechaNacimientoRaw(String fechaNacimientoRaw) {
		this.fechaNacimientoRaw = fechaNacimientoRaw;
	}

	public String getIpfAnterior() {
		return ipfAnterior;
	}

	public void setIpfAnterior(String ipfAnterior) {
		this.ipfAnterior = ipfAnterior;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String getCodigoBadas() {
		return codigoBadas;
	}

	public void setCodigoBadas(String codigoBadas) {
		this.codigoBadas = codigoBadas;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
		
	public String getCriterioIdentificacion() {
		return criterioIdentificacion;
	}

	public void setCriterioIdentificacion(String criterioIdentificacion) {
		this.criterioIdentificacion = criterioIdentificacion;
	}

	public ArrayList<String> getArrayListNafSec() {
		return arrayListNafSec;
	}

	public void setArrayListNafSec(ArrayList<String> arrayListNafSec) {
		this.arrayListNafSec = arrayListNafSec;
	}

	
	public String carga () {
		//
		StringBuffer strBuffer = new StringBuffer();                                                          
		strBuffer.append(Misc.nz(this.idInss)                    + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.codTipoAsegurado)          + ConstantesBusqueda.SEPARADOR_CAMPOS);   
		strBuffer.append(Misc.nz(this.ipf)                       + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.dniNie)                    + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.pasaporte)                 + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.naf)                       + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec1)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec2)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec3)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec4)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec5)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec6)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec7)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec8)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nafSec9)                   + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.indicativoNombre)          + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.apellidosNombre)           + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.apellido1)                 + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.apellido2)                 + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.nombre)                    + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.nacionalidad)              + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.fechaNacimiento)           + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.sexo)                      + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.indicativoDomicilio)       + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.domicilio)                 + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.tipoAseguramiento)         + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.codIndicadorDeFarmacia)    + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.codSubindicadorDeFarmacia) + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.codSituacion)              + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.fechaEfectoSituacion)      + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.codTipoBeneficiario)       + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.ipfTitular)                + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.nafTitular)                + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//strBuffer.append(Misc.nz(this.numeroSecuencia)           + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		strBuffer.append(Misc.nz(this.fechaNacimientoRaw)        + ConstantesBusqueda.SEPARADOR_CAMPOS);   
		strBuffer.append(Misc.nz(this.codUsuarioSns)             + ConstantesBusqueda.SEPARADOR_CAMPOS);   
		strBuffer.append(Misc.nz(this.criterioIdentificacion)    + ConstantesBusqueda.SEPARADOR_CAMPOS);  
		//
		return strBuffer.toString();
	}
	
}
