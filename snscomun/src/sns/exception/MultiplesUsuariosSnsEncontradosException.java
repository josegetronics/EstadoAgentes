package sns.exception;

import java.util.ArrayList;

public class MultiplesUsuariosSnsEncontradosException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5578810080814447158L;
	private ArrayList coincidentes;
	
	public MultiplesUsuariosSnsEncontradosException() {
		super("MultiplesUsuariosSnsEncontradosException");
	}
	/**
	 * Los objetos que van dentro del ArrayList son del tipo ResultadoCruceSnsBean
	 * @param coincidentes
	 */
	public MultiplesUsuariosSnsEncontradosException(ArrayList coincidentes) {
		super("MultiplesUsuariosSnsEncontradosException -> " + coincidentes);
		this.coincidentes=coincidentes;
	}

	public ArrayList getCoincidentes() {
		return coincidentes;
	}

}
