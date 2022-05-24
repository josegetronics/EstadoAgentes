package sns.exception;

import javax.ejb.EJBException;

public class TarjetaEJBException extends EJBException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6847951153704056691L;
	private Exception excepcion;
	private String codUsuarioSns;
	
	public TarjetaEJBException() {
	}

	public TarjetaEJBException(String message) {
		super(message);
	}

	public TarjetaEJBException(Exception ex) {
		super(ex);
		this.excepcion=ex;
	}

	public TarjetaEJBException(Exception ex,String codUsuarioSns) {
		super(ex);
		this.excepcion=ex;
		this.codUsuarioSns=codUsuarioSns;
	}

	public TarjetaEJBException(String message, Exception ex) {
		super(message, ex);
		this.excepcion=ex;
	}

	public Exception getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}

	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

}
