package sns.model.gestion;

import sns.model.inss.impl.ResultadoInssExtBean;


public class EventoRegistroSnsInss extends EventoInss {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4017345747203264356L;
	private ResultadoInssExtBean resultadoInssExtBean;


	public ResultadoInssExtBean getResultadoInssExtBean() {
		return resultadoInssExtBean;
	}


	public void setResultadoInssExtBean(ResultadoInssExtBean resultadoInssExtBean) {
		this.resultadoInssExtBean = resultadoInssExtBean;
	}


	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("EventoInss [idInssFichero=");
		buffer.append(resultadoInssExtBean.getCodUsuarioSns());
		buffer.append(", getAccion()=");
		buffer.append(getAccion());
		buffer.append(", getId()=");
		buffer.append(getId());
		buffer.append("]");
		return buffer.toString();
	}

}
