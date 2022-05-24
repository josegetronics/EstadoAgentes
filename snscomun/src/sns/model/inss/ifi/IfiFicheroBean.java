package sns.model.inss.ifi;

import gasai.util.Misc;

import java.io.Serializable;

import sns.model.inss.IInss;


public class IfiFicheroBean implements Serializable,IInss{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5515512063641726334L;
	  static org.apache.log4j.Logger logger =org.apache.log4j.Logger.getLogger("MantenimientoLog");

//	static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(IfiFicheroBean.class);
	
	private String linea;
	
	private int numLinea;
	
	
	// T_titular/B_beneficiario
	private String codTipoAsegurado= "";
	//A_Alta, B_Baja, M_Modificacion
	private String tipoMovimiento= "";
	// 
	// IPF
	private String tipoIdentificador= "";
	private String numeroIdentificador= "";
	private String duplicidad= "";
	private String desdoblamiento= "";
	//
	// IPF_ANTERIOR    -   SE INFORMA SOLO SI CAMBIA
	String ipfAnterior="";
//	String tipoIdentificadorAnterior= "";
//	String numeroIdentificadorAnterior= "";
//	String duplicidadAnterior= "";
//	String desdoblamientoAnterior= "";
	//
	// NAF 10 OCURRENCIA
	private String nafOcurrencias= "";
	//
	// DATOS IDENTIFICATIVOS
	private String indicativoFormatoNombre= "";
	private String apellidosNombre= "";
	private String nacionalidad= "";
	private String fechaNac= "";
	private String codSexo= "";
	//
	// DATOS DEL DOMICILIO
	private String indicativoFormatoDomicilio= "";
	private String domicilio= "";
	//
	// DATOS ASISTENCIA SANITARIA
	private String tipoAseguramiento= "";
	private String codIndicadorDeFarmacia= "";
	private String codSubindicadorDeFarmacia= "";
	private String codSituacion= "";
	private String fechaEfectoSituacion= "";
	//
	// TIPO BENEFICIARIO
	private String tipoBeneficiario = "";
	//
	// IPF DEL TITULAR ASOCIADO   -   SE INFORMA SOLO PARA BENEFICIARIOS
	private String ipfTitular="";
//	String tipoIdentificadorTitAsociado= "";
//	String numeroIdentificadorTitAsociado= "";
//	String duplicidadTitAsociado= "";
//	String desdoblamientoTitAsociado= "";
	//
	// NAF TITULAR ASOCIADO   -   SE INFORMA SOLO PARA BENEFICIARIOS
	private String nafTitular= "";
	//
	// NUMERO DE SECUENCIA   -   SE INFORMA SOLO PARA BENEFICIARIOS
	private String numeroSecuencia= "";
	//
	//
	// // // // ATRIBUTOS OBTENIDOS A PARTIR DE VALORES // // // //
	//
	// CAMPO IPF COMPLETO
	private String ipf       = "";
	//
	private String naf       = "";
	private String nafSec1  = "";
	private String nafSec2  = "";
	private String nafSec3  = "";
	private String nafSec4  = "";
	private String nafSec5  = "";
	private String nafSec6  = "";
	private String nafSec7  = "";
	private String nafSec8  = "";
	private String nafSec9  = "";
	//
	private String apellido1 = "";
	private String apellido2 = "";
	private String nombre    = "";
	//
	private String dniNie    = "";  // 1, :DNI_NIE, TIPO_IDENTIFICADOR
	private String pasaporte = "";  // 2, :NUMERO_IDENTIFICADOR
	//
	//
	// DATOS DOMICILIO
//	String tipoVia             = "";
//	String nombreVia           = "";
//	String numeroVia           = "";
//	String bis                 = "";
//	String escalera            = "";
//	String piso                = "";
//	String puerta              = "";
//	String codLocalidad        = "";
//	String localidad           = "";
//	String codPostal           = "";
//	String bloque              = "";
//	String relleno             = "";
//	//
//	String parte1domExtranjero = "";
//	String parte2domExtranjero = "";
//	String parte3domExtranjero = "";
//	String localidadExtranjera = "";
//	String paisResidencia      = "";
//	String descripcionPais     = "";
	
	private String codUsuarioSns ="";
	
	private String codigoBadas   = "";
	private String codTipoMotivoBaja    = "";
	//

	
	public IfiFicheroBean (){
	}

	
	
	public IfiFicheroBean (int numLinea,String linea) throws Exception{
		//tipoAsegurado                  = Misc.nz(linea.substring()).trim();
		try {
			this.linea=linea;
			this.numLinea=numLinea;
			
			codTipoAsegurado                     = Misc.nz(Misc.nz(linea.substring(0,1)).trim(),"N");
			tipoMovimiento                    = Misc.nz(linea.substring(1,2)).trim();
			tipoIdentificador                 = Misc.nz(linea.substring(2,3)).trim();
			numeroIdentificador               = Misc.nz(linea.substring(3,13)).trim();
			duplicidad                        = Misc.nz(linea.substring(13,15)).trim();
			desdoblamiento                    = Misc.nz(linea.substring(15,17)).trim();
			ipfAnterior						  = Misc.nz(linea.substring(17,32)).trim();
//			tipoIdentificadorAnterior         = Misc.nz(linea.substring(17,18)).trim();
//			numeroIdentificadorAnterior       = Misc.nz(linea.substring(18,28)).trim();
//			duplicidadAnterior                = Misc.nz(linea.substring(28,30)).trim();
//			desdoblamientoAnterior            = Misc.nz(linea.substring(30,32)).trim();

//			nafOcurrencias                    = Misc.nz(linea.substring(32,152)).trim();
			nafOcurrencias                    = Misc.nz(linea.substring(32,152));
			
			indicativoFormatoNombre           = Misc.nz(linea.substring(152,153)).trim();
			apellidosNombre                   = Misc.nz(linea.substring(153,252));
			nacionalidad                      = Misc.nz(linea.substring(252,255)).trim();
			fechaNac		                  = Misc.nz(linea.substring(255,263)).trim();
			codSexo                           = Misc.nz(linea.substring(263,264)).trim();
			indicativoFormatoDomicilio        = Misc.nz(linea.substring(264,265)).trim();
			domicilio                         = Misc.nz(linea.substring(265,448));
			tipoAseguramiento                 = Misc.nz(linea.substring(448,452)).trim();
			codIndicadorDeFarmacia            = Misc.nz(linea.substring(452,459)).trim();
			codSubindicadorDeFarmacia         = Misc.nz(linea.substring(459,461)).trim();
			codSituacion                         = Misc.nz(Misc.nz(linea.substring(461,462)).trim(),"N");
			fechaEfectoSituacion              = Misc.nz(linea.substring(462,470)).trim();
			tipoBeneficiario                  = Misc.nz(Misc.nz(linea.substring(470,472)).trim(),"00");
//			tipoIdentificadorTitAsociado      = Misc.nz(linea.substring(472,473)).trim();
//			numeroIdentificadorTitAsociado    = Misc.nz(linea.substring(473,483)).trim();
//			duplicidadTitAsociado             = Misc.nz(linea.substring(483,485)).trim();
//			desdoblamientoTitAsociado         = Misc.nz(linea.substring(485,487)).trim();
			ipfTitular					  = Misc.nz(linea.substring(472,487)).trim();
			nafTitular                    = Misc.remplazar(Misc.nz(linea.substring(487,499)).trim(),"000000000000","");
			numeroSecuencia                   = Misc.nz(linea.substring(499,501)).trim();
			try {
				codUsuarioSns = Misc.nz(linea.substring(501,517)).trim();
			}catch (Exception e) {
				logger.debug("Error obteniendo el codSns de la linea [" + linea + "]");
				
			}
			//
			try {
				codigoBadas = Misc.nz(linea.substring(517, 533)).trim();
			} catch (Exception e) {
				logger.debug("Error obteniendo el codigoBadas de la linea [" + linea + "]");
				codigoBadas="";
			}
			try {
				codTipoMotivoBaja = Misc.nz(linea.substring(533, 535)).trim();
			} catch (Exception e) {
				logger.debug("Error obteniendo el motivoBaja de la linea [" + linea + "]");
				codTipoMotivoBaja="";
			}
			//			
			//
			//			
//			ipf       = Misc.nz(linea.substring(2,17)).trim();
			ipf       = Misc.nz(linea.substring(2,17));
			//
			naf       = Misc.remplazar(Misc.nz(nafOcurrencias.substring(0,   12)).trim(),"000000000000","");
			nafSec1  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(12,  24)).trim(),"000000000000","");
			nafSec2  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(24,  36)).trim(),"000000000000","");
			nafSec3  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(36,  48)).trim(),"000000000000","");
			nafSec4  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(48,  60)).trim(),"000000000000","");
			nafSec5  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(60,  72)).trim(),"000000000000","");
			nafSec6  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(72,  84)).trim(),"000000000000","");
			nafSec7  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(84,  96)).trim(),"000000000000","");
			nafSec8  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(96,  108)).trim(),"000000000000","");
			nafSec9  = Misc.remplazar(Misc.nz(nafOcurrencias.substring(108, 120)).trim(),"000000000000","");
			//
			if(indicativoFormatoNombre.equals("S")) {
				apellido1 = Misc.nz(apellidosNombre.substring(0,  33)).trim();
				apellido2 = Misc.nz(apellidosNombre.substring(33, 66)).trim();
				nombre    = Misc.nz(apellidosNombre.substring(66, 99)).trim();
			}
			//
			if(tipoIdentificador.equals("1") || tipoIdentificador.equals("6")) {
				dniNie    = Misc.nz(ipf.substring(2,  11)).trim();  // 1, :DNI_NIE, TIPO_IDENTIFICADOR
			}
			else {
				if(tipoIdentificador.equals("2")) {
					pasaporte = Misc.nz(ipf.substring(2,  11)).trim();  // 2, :NUMERO_IDENTIFICADOR
				}
			}		
			//			
//			if(indicativoFormatoDomicilio.equals("1")) {
//				nombreVia           = Misc.nz(domicilio.substring(0,   36)).trim();
//				localidad           = Misc.nz(domicilio.substring(36,  76)).trim();
//				codPostal           = Misc.nz(domicilio.substring(76,  81)).trim();
//				relleno             = Misc.nz(domicilio.substring(81, 183)).trim();
//			}
//			else {
//				if(indicativoFormatoDomicilio.equals("2")) {
//					tipoVia             = Misc.nz(domicilio.substring(0,     2)).trim();
//					nombreVia           = Misc.nz(domicilio.substring(2,    38)).trim();
//					numeroVia           = Misc.nz(domicilio.substring(38,   43)).trim();
//					bis                 = Misc.nz(domicilio.substring(43,   45)).trim();
//					escalera            = Misc.nz(domicilio.substring(45,   47)).trim();
//					piso                = Misc.nz(domicilio.substring(47,   49)).trim();
//					puerta              = Misc.nz(domicilio.substring(49,   52)).trim();
//					codLocalidad        = Misc.nz(domicilio.substring(52,   61)).trim();
//					localidad           = Misc.nz(domicilio.substring(61,  101)).trim();
//					codPostal           = Misc.nz(domicilio.substring(101, 106)).trim();
//					bloque              = Misc.nz(domicilio.substring(106, 110)).trim();
//					relleno             = Misc.nz(domicilio.substring(110, 183)).trim();
//				}
//				else {
//					if(indicativoFormatoDomicilio.equals("3")) {
//						parte1domExtranjero = Misc.nz(domicilio.substring(0,   40)).trim();
//						parte2domExtranjero = Misc.nz(domicilio.substring(40,  80)).trim();
//						parte3domExtranjero = Misc.nz(domicilio.substring(80,  120)).trim();
//						localidadExtranjera = Misc.nz(domicilio.substring(120, 160)).trim();
//						paisResidencia      = Misc.nz(domicilio.substring(160, 163)).trim();
//						descripcionPais     = Misc.nz(domicilio.substring(163, 183)).trim();
//					}
//				}
//			}
			//
		}
		catch (Exception e) {
			logger.error("Exception " + e);
			logger.error(linea);
			throw e;
		}
	}
	
	
	
	public void view (){
		//
		try {
			logger.debug("tipoAsegurado                  " +  codTipoAsegurado);
			logger.debug("tipoMovimiento                 " +  tipoMovimiento);
			logger.debug("tipoIdentificador              " +  tipoIdentificador);
			logger.debug("numeroIdentificador            " +  numeroIdentificador);
			logger.debug("duplicidad                     " +  duplicidad);
			logger.debug("desdoblamiento                 " +  desdoblamiento);
			logger.debug("ipfAnterior				     " +  ipfAnterior);
			logger.debug("nafOcurrencias                 " +  nafOcurrencias);
			logger.debug("indicativoFormatoNombre        " +  indicativoFormatoNombre);
			logger.debug("apellidosNombre                " +  apellidosNombre);
			logger.debug("nacionalidad                   " +  nacionalidad);
			logger.debug("fechaNacimiento                " +  fechaNac);
			logger.debug("sexo                           " +  codSexo);
			logger.debug("indicativoFormatoDomicilio     " +  indicativoFormatoDomicilio);
			logger.debug("domicilio                      " +  domicilio);
			logger.debug("tipoAseguramiento              " +  tipoAseguramiento);
			logger.debug("codIndicadorDeFarmacia         " +  codIndicadorDeFarmacia);
			logger.debug("codSubindicadorDeFarmacia      " +  codSubindicadorDeFarmacia);
			logger.debug("situacion                      " +  codSituacion);
			logger.debug("fechaEfectoSituacion           " +  fechaEfectoSituacion);
			logger.debug("tipoBeneficiario               " +  tipoBeneficiario);
			logger.debug("ipfTitAsociado   				 " +  ipfTitular);
			logger.debug("nafTitAsociado                 " +  nafTitular);
			logger.debug("numeroSecuencia                " +  numeroSecuencia);
			logger.debug("codUsuarioSns                  " +  codUsuarioSns);
			//
			logger.debug("codigoBadas                    " +  codigoBadas);
			logger.debug("codTipoMotivoBaja                     " +  codTipoMotivoBaja);
			//
			//
			logger.debug("");
			logger.debug("");
			//
			logger.debug("ipf                            " +  ipf);
			logger.debug("naf                            " +  naf);
			logger.debug("naf_sec1                       " +  nafSec1);
			logger.debug("naf_sec2                       " +  nafSec2);
			logger.debug("naf_sec3                       " +  nafSec3);
			logger.debug("naf_sec4                       " +  nafSec4);
			logger.debug("naf_sec5                       " +  nafSec5);
			logger.debug("naf_sec6                       " +  nafSec6);
			logger.debug("naf_sec7                       " +  nafSec7);
			logger.debug("naf_sec8                       " +  nafSec8);
			logger.debug("naf_sec9                       " +  nafSec9);
			//
			logger.debug("apellido1                      " +  apellido1);
			logger.debug("apellido2                      " +  apellido2);
			logger.debug("nombre                         " +  nombre);
			logger.debug("dniNie                         " +  dniNie);
			logger.debug("pasaporte                      " +  pasaporte);
			//
			
			logger.debug("");
			logger.debug("");
			//			
		}
		catch (Exception e) {
			logger.error("Exception " + e);
		}
	}
	
	
	public String getCodigoBadas() {
		return codigoBadas;
	}



	public void setCodigoBadas(String codigoBadas) {
		this.codigoBadas = codigoBadas;
	}



	public String getCodTipoMotivoBaja() {
		return codTipoMotivoBaja;
	}



	public void setCodTipoMotivoBaja(String codTipoMotivoBaja) {
		this.codTipoMotivoBaja = codTipoMotivoBaja;
	}



	public String getLinea() {
		return this.linea;
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


	public String getTipoIdentificador() {
		return tipoIdentificador;
	}


	public void setTipoIdentificador(String tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}


	public String getNumeroIdentificador() {
		return numeroIdentificador;
	}


	public void setNumeroIdentificador(String numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
	}


	public String getDuplicidad() {
		return duplicidad;
	}


	public void setDuplicidad(String duplicidad) {
		this.duplicidad = duplicidad;
	}


	public String getDesdoblamiento() {
		return desdoblamiento;
	}


	public void setDesdoblamiento(String desdoblamiento) {
		this.desdoblamiento = desdoblamiento;
	}


	public String getNafOcurrencias() {
		return nafOcurrencias;
	}


	public void setNaf(String naf) {
		this.naf = naf;
	}


	public String getIndicativoFormatoNombre() {
		return indicativoFormatoNombre;
	}


	public void setIndicativoFormatoNombre(String indicativoFormatoNombre) {
		this.indicativoFormatoNombre = indicativoFormatoNombre;
	}


	public String getApellidosNombre() {
		return apellidosNombre;
	}


	public void setApellidosNombre(String apellidosNombre) {
		this.apellidosNombre = apellidosNombre;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getFechaNac() {
		return fechaNac;
	}


	public void setFechaNacimiento(String fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getCodSexo() {
		return codSexo;
	}


	public void setSexo(String codSexo) {
		this.codSexo = codSexo;
	}


	public String getIndicativoFormatoDomicilio() {
		return indicativoFormatoDomicilio;
	}


	public void setIndicativoFormatoDomicilio(String indicativoFormatoDomicilio) {
		this.indicativoFormatoDomicilio = indicativoFormatoDomicilio;
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
	
	
	public String getTipoBeneficiario() {
		return tipoBeneficiario;
	}



	public void setTipoBeneficiario(String tipoBeneficiario) {
		this.tipoBeneficiario = tipoBeneficiario;
	}



	public String getIpf() {
		return ipf;
	}



	public void setIpf(String ipf) {
		this.ipf = ipf;
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



	public void setNafOcurrencias(String nafOcurrencias) {
		this.nafOcurrencias = nafOcurrencias;
	}
	
	public int getNumLinea() {
		return numLinea;
	}



	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}



	public String getIpfAnterior() {
		return ipfAnterior;
	}



	public void setIpfAnterior(String ipfAnterior) {
		this.ipfAnterior = ipfAnterior;
	}



	public String getIpfTitular() {
		return ipfTitular;
	}



	public void setIpfTitAsociado(String ipfTitular) {
		this.ipfTitular = ipfTitular;
	}



	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}



	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String toLinea() {
		StringBuffer str=new StringBuffer();
		str.append(Misc.rellenarIzq(this.codTipoAsegurado, " ", 1));
		str.append(Misc.rellenarIzq(this.tipoMovimiento, " ", 1));
		str.append(Misc.rellenarDcha(ipf, " ", 15));
		str.append(Misc.rellenarDcha(ipfAnterior, " ", 15));
		str.append(Misc.rellenarDcha(naf, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec1, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec2, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec3, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec4, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec5, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec6, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec7, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec8, "0", 12));
		str.append(Misc.rellenarDcha(this.nafSec9, "0", 12));
		str.append(Misc.rellenarDcha(this.indicativoFormatoNombre, " ", 1));
		str.append(Misc.rellenarDcha(apellido1, " ", 33));
		str.append(Misc.rellenarDcha(apellido2, " ", 33));
		str.append(Misc.rellenarDcha(nombre, " ", 33));
		str.append(Misc.rellenarDcha(nacionalidad, "0", 3));
		str.append(Misc.rellenarDcha(fechaNac, "0", 8));
		str.append(Misc.rellenarDcha(codSexo, "0", 1));
		str.append(Misc.rellenarDcha(this.indicativoFormatoDomicilio, " ", 1));
		str.append(Misc.rellenarDcha(domicilio, " ", 183));
		str.append(Misc.rellenarDcha(tipoAseguramiento, " ", 4));
		str.append(Misc.rellenarDcha(this.codIndicadorDeFarmacia, " ", 7));
		str.append(Misc.rellenarDcha(this.codSubindicadorDeFarmacia, " ", 2));
		str.append(Misc.rellenarDcha(codSituacion, " ", 1));
		str.append(Misc.rellenarDcha(this.fechaEfectoSituacion, "0", 8));
		str.append(Misc.rellenarDcha(tipoBeneficiario, "0", 2));
		str.append(Misc.rellenarDcha(this.ipfTitular, " ", 15));
		str.append(Misc.rellenarDcha(this.nafTitular, "0", 12));
		str.append(Misc.rellenarDcha(numeroSecuencia, "0", 2));
		str.append(Misc.rellenarDcha(this.codUsuarioSns, " ", 16));
		str.append(Misc.rellenarDcha(this.codigoBadas, " ", 16));
		str.append(Misc.rellenarDcha(this.codTipoMotivoBaja, "0", 2));
		return str.toString();
	}



}
