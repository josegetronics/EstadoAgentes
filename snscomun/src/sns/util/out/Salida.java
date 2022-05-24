package sns.util.out;

public class Salida extends Object implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "";
	private String msgException = "";
	private String msgSoporte = "";
	private String msgDis = "";
	private boolean error = false;
	private Object valor = null;
	private Exception exception;

	public Salida() {
	}

	public void setOk() {
		msg = "Ok";
		msgException = "";
		msgSoporte = "";
		error = false;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public void setMsg(String mesg, boolean anadir) {
		if (anadir)
			this.msg += mesg;
		else
			setMsg(mesg);
	}

	public void setMsg(String mesg) {
		this.msg = mesg;
	}

	public void setMsgDis(String mesgDis) {
		this.msgDis = mesgDis;
	}

	public String getMsg() {
		return (msg);
	}

	public void setMsgException(String mesg, boolean anadir) {
		if (anadir)
			this.msgException += mesg;
		else
			setMsgException(mesg);
	}

	public void setMsgException(String mesg) {
		this.msgException = mesg;
	}

	public String getMsgDis() {
		return this.msgDis;
	}

	public String getMsgException() {
		return (msgException);
	}

	public void setMsgSoporte(String mesg, boolean anadir) {
		if (anadir)
			this.msgSoporte += mesg;
		else
			setMsgSoporte(mesg);
	}

	public void setMsgSoporte(String mesg) {
		this.msgSoporte = mesg;
	}

	public String getMsgSoporte() {
		return (msgSoporte);
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean getError() {
		return (error);
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Object getValor() {
		return (valor);
	}
}
