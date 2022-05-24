package sns.model.tgss;

import gasai.util.Misc;

import java.io.Serializable;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar
/ ******************************************************/
public class SegSocialBean implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1951201821861380083L;
private java.lang.String naf;
  private java.lang.String naf_sec1;
  private java.lang.String naf_sec2;
  private java.lang.String naf_sec3;
  private java.lang.String naf_sec4;
  private java.lang.String naf_sec5;
  private java.lang.String naf_sec6;
  private java.lang.String naf_sec7;
  private java.lang.String naf_sec8;
  private java.lang.String naf_sec9;
  private java.lang.String tipo_ipf;
  private java.lang.String ipf;
  private java.lang.String dni_nie;
  private java.lang.String pasaporte;
  private java.lang.String tarjeta_comunitario;
  private java.lang.String apellido1;
  private java.lang.String apellido2;
  private java.lang.String nombre;
  private java.sql.Timestamp fecha_nac;
  private java.sql.Timestamp fecha_fall;
  private java.lang.String localidad_nac;
  private java.lang.String nacionalidad;
  private java.lang.String cod_sexo;
  private java.lang.String indicativo_dom;
  private java.lang.String domicilio;
  private java.lang.String ras;
  private java.lang.String far;
  private java.lang.String regimen;
  private java.lang.String situacion;
  private java.sql.Timestamp fecha_situacion;
  private java.lang.String ccc;
  private java.lang.String ccc_convencional;
  private java.lang.String razon_social;
  private java.lang.String entidad_at;
  private java.lang.String mutua_it;
  private java.lang.String cod_entidad_col;
  private java.lang.String tipo_colaboradora;
  private java.lang.String actividad_economica;
  private java.lang.String raizap;
  private java.sql.Timestamp fecha_act;
  private java.lang.String revisar_disc;

  private String direccion_provincial;

  public SegSocialBean() {}
  public SegSocialBean(java.util.HashMap hd) {
	 this.naf=Misc.nz(hd.get("NAF"));
	 this.naf_sec1=Misc.nz(hd.get("NAF_SEC1"));
	 this.naf_sec2=Misc.nz(hd.get("NAF_SEC2"));
	 this.naf_sec3=Misc.nz(hd.get("NAF_SEC3"));
	 this.naf_sec4=Misc.nz(hd.get("NAF_SEC4"));
	 this.naf_sec5=Misc.nz(hd.get("NAF_SEC5"));
	 this.naf_sec6=Misc.nz(hd.get("NAF_SEC6"));
	 this.naf_sec7=Misc.nz(hd.get("NAF_SEC7"));
	 this.naf_sec8=Misc.nz(hd.get("NAF_SEC8"));
	 this.naf_sec9=Misc.nz(hd.get("NAF_SEC9"));
	 this.tipo_ipf=Misc.nz(hd.get("TIPO_IPF"));
	 this.ipf=Misc.nz(hd.get("IPF"));
	 this.dni_nie=Misc.nz(hd.get("DNI_NIE"));
	 this.pasaporte=Misc.nz(hd.get("PASAPORTE"));
	 this.tarjeta_comunitario=Misc.nz(hd.get("TARJETA_COMUNITARIO"));
	 this.apellido1=Misc.nz(hd.get("APELLIDO1"));
	 this.apellido2=Misc.nz(hd.get("APELLIDO2"));
	 this.nombre=Misc.nz(hd.get("NOMBRE"));
	 this.fecha_nac=(java.sql.Timestamp)hd.get("FECHA_NAC");
	 this.fecha_fall=(java.sql.Timestamp)hd.get("FECHA_FALL");
	 this.localidad_nac=Misc.nz(hd.get("LOCALIDAD_NAC"));
	 this.nacionalidad=Misc.nz(hd.get("NACIONALIDAD"));
	 this.cod_sexo=Misc.nz(hd.get("COD_SEXO"));
	 this.indicativo_dom=Misc.nz(hd.get("INDICATIVO_DOM"));
	 this.domicilio=Misc.nz(hd.get("DOMICILIO"));
	 this.ras=Misc.nz(hd.get("RAS"));
	 this.far=Misc.nz(hd.get("FAR"));
	 this.regimen=Misc.nz(hd.get("REGIMEN"));
	 this.situacion=Misc.nz(hd.get("SITUACION"));
	 this.fecha_situacion=(java.sql.Timestamp)hd.get("FECHA_SITUACION");
	 this.ccc=Misc.nz(hd.get("CCC"));
         this.ccc_convencional=Misc.nz(hd.get("CCC_CONVENCIONAL"));
         this.direccion_provincial=Misc.nz(hd.get("DIRECCION_PROVINCIAL"));
	 this.razon_social=Misc.nz(hd.get("RAZON_SOCIAL"));
	 this.entidad_at=Misc.nz(hd.get("ENTIDAD_AT"));
	 this.mutua_it=Misc.nz(hd.get("MUTUA_IT"));
	 this.cod_entidad_col=Misc.nz(hd.get("COD_ENTIDAD_COL"));
	 this.tipo_colaboradora=Misc.nz(hd.get("TIPO_COLABORADORA"));
	 this.actividad_economica=Misc.nz(hd.get("ACTIVIDAD_ECONOMICA"));
	 this.raizap=Misc.nz(hd.get("RAIZAP"));
	 this.fecha_act=(java.sql.Timestamp)hd.get("FECHA_ACT");
	 this.revisar_disc=Misc.nz(hd.get("REVISAR_DISC"));
  }

  public java.lang.String getNaf() {
	 return this.naf;
  }
  public void setNaf(java.lang.String naf) {
	 this.naf=naf;
  }
  public java.lang.String getNaf_sec1() {
	 return this.naf_sec1;
  }
  public void setNaf_sec1(java.lang.String naf_sec1) {
	 this.naf_sec1=naf_sec1;
  }
  public java.lang.String getNaf_sec2() {
	 return this.naf_sec2;
  }
  public void setNaf_sec2(java.lang.String naf_sec2) {
	 this.naf_sec2=naf_sec2;
  }
  public java.lang.String getNaf_sec3() {
	 return this.naf_sec3;
  }
  public void setNaf_sec3(java.lang.String naf_sec3) {
	 this.naf_sec3=naf_sec3;
  }
  public java.lang.String getNaf_sec4() {
	 return this.naf_sec4;
  }
  public void setNaf_sec4(java.lang.String naf_sec4) {
	 this.naf_sec4=naf_sec4;
  }
  public java.lang.String getNaf_sec5() {
	 return this.naf_sec5;
  }
  public void setNaf_sec5(java.lang.String naf_sec5) {
	 this.naf_sec5=naf_sec5;
  }
  public java.lang.String getNaf_sec6() {
	 return this.naf_sec6;
  }
  public void setNaf_sec6(java.lang.String naf_sec6) {
	 this.naf_sec6=naf_sec6;
  }
  public java.lang.String getNaf_sec7() {
	 return this.naf_sec7;
  }
  public void setNaf_sec7(java.lang.String naf_sec7) {
	 this.naf_sec7=naf_sec7;
  }
  public java.lang.String getNaf_sec8() {
	 return this.naf_sec8;
  }
  public void setNaf_sec8(java.lang.String naf_sec8) {
	 this.naf_sec8=naf_sec8;
  }
  public java.lang.String getNaf_sec9() {
	 return this.naf_sec9;
  }
  public void setNaf_sec9(java.lang.String naf_sec9) {
	 this.naf_sec9=naf_sec9;
  }
  public java.lang.String getTipo_ipf() {
	 return this.tipo_ipf;
  }
  public void setTipo_ipf(java.lang.String tipo_ipf) {
	 this.tipo_ipf=tipo_ipf;
  }
  public java.lang.String getIpf() {
	 return this.ipf;
  }
  public void setIpf(java.lang.String ipf) {
	 this.ipf=ipf;
  }
  public java.lang.String getDni_nie() {
	 return this.dni_nie;
  }
  public void setDni_nie(java.lang.String dni_nie) {
	 this.dni_nie=dni_nie;
  }
  public java.lang.String getPasaporte() {
	 return this.pasaporte;
  }
  public void setPasaporte(java.lang.String pasaporte) {
	 this.pasaporte=pasaporte;
  }
  public java.lang.String getTarjeta_comunitario() {
	 return this.tarjeta_comunitario;
  }
  public void setTarjeta_comunitario(java.lang.String tarjeta_comunitario) {
	 this.tarjeta_comunitario=tarjeta_comunitario;
  }
  public java.lang.String getApellido1() {
	 return this.apellido1;
  }
  public void setApellido1(java.lang.String apellido1) {
	 this.apellido1=apellido1;
  }
  public java.lang.String getApellido2() {
	 return this.apellido2;
  }
  public void setApellido2(java.lang.String apellido2) {
	 this.apellido2=apellido2;
  }
  public java.lang.String getNombre() {
	 return this.nombre;
  }
  public void setNombre(java.lang.String nombre) {
	 this.nombre=nombre;
  }
  public java.sql.Timestamp getFecha_nac() {
	 return this.fecha_nac;
  }
  public void setFecha_nac(java.sql.Timestamp fecha_nac) {
	 this.fecha_nac=fecha_nac;
  }
  public java.sql.Timestamp getFecha_fall() {
	 return this.fecha_fall;
  }
  public void setFecha_fall(java.sql.Timestamp fecha_fall) {
	 this.fecha_fall=fecha_fall;
  }
  public java.lang.String getLocalidad_nac() {
	 return this.localidad_nac;
  }
  public void setLocalidad_nac(java.lang.String localidad_nac) {
	 this.localidad_nac=localidad_nac;
  }
  public java.lang.String getNacionalidad() {
	 return this.nacionalidad;
  }
  public void setNacionalidad(java.lang.String nacionalidad) {
	 this.nacionalidad=nacionalidad;
  }
  public java.lang.String getCod_sexo() {
	 return this.cod_sexo;
  }
  public void setCod_sexo(java.lang.String cod_sexo) {
	 this.cod_sexo=cod_sexo;
  }
  public java.lang.String getIndicativo_dom() {
	 return this.indicativo_dom;
  }
  public void setIndicativo_dom(java.lang.String indicativo_dom) {
	 this.indicativo_dom=indicativo_dom;
  }
  public java.lang.String getDomicilio() {
	 return this.domicilio;
  }
  public void setDomicilio(java.lang.String domicilio) {
	 this.domicilio=domicilio;
  }
  public java.lang.String getRas() {
	 return this.ras;
  }
  public void setRas(java.lang.String ras) {
	 this.ras=ras;
  }
  public java.lang.String getFar() {
	 return this.far;
  }
  public void setFar(java.lang.String far) {
	 this.far=far;
  }
  public java.lang.String getRegimen() {
	 return this.regimen;
  }
  public void setRegimen(java.lang.String regimen) {
	 this.regimen=regimen;
  }
  public java.lang.String getSituacion() {
	 return this.situacion;
  }
  public void setSituacion(java.lang.String situacion) {
	 this.situacion=situacion;
  }
  public java.sql.Timestamp getFecha_situacion() {
	 return this.fecha_situacion;
  }
  public void setFecha_situacion(java.sql.Timestamp fecha_situacion) {
	 this.fecha_situacion=fecha_situacion;
  }
  public java.lang.String getCcc() {
	 return this.ccc;
  }
  public void setCcc(java.lang.String ccc) {
	 this.ccc=ccc;
  }
  public java.lang.String getCcc_convencional() {
	 return this.ccc_convencional;
  }
  public void setCcc_convencional(java.lang.String ccc_convencional) {
	 this.ccc_convencional=ccc_convencional;
  }
  public java.lang.String getRazon_social() {
	 return this.razon_social;
  }
  public void setRazon_social(java.lang.String razon_social) {
	 this.razon_social=razon_social;
  }
  public java.lang.String getEntidad_at() {
	 return this.entidad_at;
  }
  public void setEntidad_at(java.lang.String entidad_at) {
	 this.entidad_at=entidad_at;
  }
  public java.lang.String getMutua_it() {
	 return this.mutua_it;
  }
  public void setMutua_it(java.lang.String mutua_it) {
	 this.mutua_it=mutua_it;
  }
  public java.lang.String getCod_entidad_col() {
	 return this.cod_entidad_col;
  }
  public void setCod_entidad_col(java.lang.String cod_entidad_col) {
	 this.cod_entidad_col=cod_entidad_col;
  }
  public java.lang.String getTipo_colaboradora() {
	 return this.tipo_colaboradora;
  }
  public void setTipo_colaboradora(java.lang.String tipo_colaboradora) {
	 this.tipo_colaboradora=tipo_colaboradora;
  }
  public java.lang.String getActividad_economica() {
	 return this.actividad_economica;
  }
  public void setActividad_economica(java.lang.String actividad_economica) {
	 this.actividad_economica=actividad_economica;
  }
  public java.lang.String getRaizap() {
	 return this.raizap;
  }
  public void setRaizap(java.lang.String raizap) {
	 this.raizap=raizap;
  }
  public java.sql.Timestamp getFecha_act() {
	 return this.fecha_act;
  }
  public void setFecha_act(java.sql.Timestamp fecha_act) {
	 this.fecha_act=fecha_act;
  }
  public java.lang.String getRevisar_disc() {
	 return this.revisar_disc;
  }

  public String getDireccion_provincial() {
    return direccion_provincial;
  }

  public void setRevisar_disc(java.lang.String revisar_disc) {
	 this.revisar_disc=revisar_disc;
  }

  public void setDireccion_provincial(String direccion_provincial) {
    this.direccion_provincial = direccion_provincial;
  }

}
