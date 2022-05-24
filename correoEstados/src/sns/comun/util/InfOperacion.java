package sns.comun.util;

import java.util.Calendar;
import java.util.Date;

public class InfOperacion {

	private long codOperacion;
	private java.sql.Timestamp fechaOperacion;

	public InfOperacion(long codOperacion, java.sql.Timestamp fechaOperacion) {

		this.codOperacion = codOperacion;
		this.fechaOperacion = fechaOperacion;
	}

	public long getCodOperacion() {
		return this.codOperacion;
	}

	public void setCodOperacion(long codigo) {
		this.codOperacion = codigo;
	}

	public java.sql.Timestamp getFechaOperacion() {
		return this.fechaOperacion;
	}

	public void setFechaOperacion(java.sql.Timestamp fecha) {
		this.fechaOperacion = fecha;
	}

	public long diferencia(java.sql.Timestamp fecha) {
		long resultado = 0;
		if (this.fechaOperacion.before(new Date(fecha.getTime()))) {
			Calendar c = Calendar.getInstance();
			long aux = fecha.getTime() - this.fechaOperacion.getTime();
			Date t3 = new Date(aux);
			c.setTime(t3);

			resultado = (c.get(c.DAY_OF_YEAR) - 1);
			if (resultado < 0) {
				resultado = 0;
			}
		}
		return resultado;

	}

	public String toString() {
		return this.codOperacion + "|" + this.fechaOperacion;
	}
}
