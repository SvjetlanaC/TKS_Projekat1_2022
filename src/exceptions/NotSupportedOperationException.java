package exceptions;

/**
 * NotSupportedOperationException class defines Exception that will be thrown if given operation is not supported.
 * @author Svjetlana
 * @version 1.0
 * @since 2022-11-25
 */
public class NotSupportedOperationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Operation not supported!";
	
	/**
	 * NotSupportedOperationException class constructor.
	 */
	public NotSupportedOperationException() {
		super(MESSAGE);
	}
}
