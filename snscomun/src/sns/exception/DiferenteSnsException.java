package sns.exception;

import sns.model.inss.dto.ResultadoCruceSnsBean;


public class DiferenteSnsException extends TsiException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4943841610858544086L;
	private String codUsuarioSnsOriginal;
	private ResultadoCruceSnsBean coincidente;
	
	public DiferenteSnsException() {
		super("DiferenteSnsException");
	}
	
	public DiferenteSnsException (String codUsuarioSnsOriginal,ResultadoCruceSnsBean resultadoCruceSnsBean) {
		this.codUsuarioSnsOriginal=codUsuarioSnsOriginal;
		this.coincidente=resultadoCruceSnsBean;
	}

	public String getCodUsuarioSnsOriginal() {
		return codUsuarioSnsOriginal;
	}

	public ResultadoCruceSnsBean getCoincidente() {
		return coincidente;
	}

	
}
