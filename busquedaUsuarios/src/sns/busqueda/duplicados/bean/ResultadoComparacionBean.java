package sns.busqueda.duplicados.bean;

import sns.comun.bean.DatosLecturaBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.config.ConstantesBusqueda;


public class ResultadoComparacionBean {

	
	private DatosLecturaBean datosLecturaBean                     = null;
	private DatosLecturaBean datosLecturaCompBean                 = null;
	private BusquedaAproxResultadoBean busquedaAproxResultadoBean = null;
    private String tipoCampo                                      = "";
	private String tipoResultado                                  = "";
	
	
	public ResultadoComparacionBean  (){		
	}

	
	public ResultadoComparacionBean  (DatosLecturaBean datosLecturaBean, DatosLecturaBean datosLecturaCompBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoResultado){		
		this.datosLecturaBean           = datosLecturaBean;
		this.datosLecturaCompBean       = datosLecturaCompBean;
		this.busquedaAproxResultadoBean = busquedaAproxResultadoBean;
		this.tipoResultado              = tipoResultado;
	}
		

	public DatosLecturaBean getDatosLecturaBean() {
		return datosLecturaBean;
	}


	public void setDatosLecturaBean(DatosLecturaBean datosLecturaBean) {
		this.datosLecturaBean = datosLecturaBean;
	}


	public DatosLecturaBean getDatosLecturaCompBean() {
		return datosLecturaCompBean;
	}


	public void setDatosLecturaCompBean(DatosLecturaBean datosLecturaCompBean) {
		this.datosLecturaCompBean = datosLecturaCompBean;
	}


	public BusquedaAproxResultadoBean getBusquedaAproxResultadoBean() {
		return busquedaAproxResultadoBean;
	}


	public void setBusquedaAproxResultadoBean(BusquedaAproxResultadoBean busquedaAproxResultadoBean) {
		this.busquedaAproxResultadoBean = busquedaAproxResultadoBean;
	}


	public String getTipoCampo() {
		return tipoCampo;
	}


	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}


	public String getTipoResultado() {
		return tipoResultado;
	}


	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}
	
	
	public String getLinea () throws Exception {
		//
		StringBuffer str = new StringBuffer ();
		//
		try {
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			
			str.append(this.datosLecturaBean.getLineaEntrada());
			str.append(this.datosLecturaCompBean.getLineaEntrada());
			str.append(this.busquedaAproxResultadoBean.toString());
			//str.append(this.tipoCampo         + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append(this.tipoResultado     + ConstantesBusqueda.SEPARADOR_CAMPOS);
		}
		catch (Exception e) {
			throw e;
		}
		return str.toString();
	}	
	
	
	
	
	
	
	
	
	
}
