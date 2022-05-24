package sns.model.inss;

public class CriteriosBusquedaExtNafSec extends CriteriosBusqueda {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5839032224736876400L;

	private String nafSec1;
	private String nafSec2;
	private String nafSec3;
	private String nafSec4;
	private String nafSec5;
	private String nafSec6;
	private String nafSec7;
	private String nafSec8;
	private String nafSec9;
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
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("CriteriosBusquedaExtNafSec [nafSec1=");
		buffer.append(nafSec1);
		buffer.append(", nafSec2=");
		buffer.append(nafSec2);
		buffer.append(", nafSec3=");
		buffer.append(nafSec3);
		buffer.append(", nafSec4=");
		buffer.append(nafSec4);
		buffer.append(", nafSec5=");
		buffer.append(nafSec5);
		buffer.append(", nafSec6=");
		buffer.append(nafSec6);
		buffer.append(", nafSec7=");
		buffer.append(nafSec7);
		buffer.append(", nafSec8=");
		buffer.append(nafSec8);
		buffer.append(", nafSec9=");
		buffer.append(nafSec9);
		buffer.append(", getCodSituacion()=");
		buffer.append(getCodSituacion());
		buffer.append(", getCodUsuarioSns()=");
		buffer.append(getCodUsuarioSns());
		buffer.append(", getDniNie()=");
		buffer.append(getDniNie());
		buffer.append(", getNaf()=");
		buffer.append(getNaf());
		buffer.append(", getNafTitular()=");
		buffer.append(getNafTitular());
		buffer.append(", getNumeroMaximoResultados()=");
		buffer.append(getNumeroMaximoResultados());
		buffer.append(", getPasaporte()=");
		buffer.append(getPasaporte());
		buffer.append("]");
		return buffer.toString();
	}


}
