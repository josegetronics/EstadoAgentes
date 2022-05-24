package sns.exception;

import java.sql.SQLException;

public class SincronizacionSQLException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3549264149355965659L;
	
	private SQLException sqlException;

	public SincronizacionSQLException(SQLException e,String codigoSns,String donde) {
		super(e.getMessage() + " - " + donde + " [" + codigoSns + "]");
		this.sqlException=e;
	}

	public int getErrorCode() {
		return sqlException.getErrorCode();
	}
	
	
}
