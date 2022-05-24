package sns.exception;

public class MultipleIpfException extends TsiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7746441057016990860L;

	public MultipleIpfException(String ipf) {
		super("MultipleIpfException: " + ipf);
	}

}
