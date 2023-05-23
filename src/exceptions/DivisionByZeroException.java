package exceptions;

/**
 * DivisionByZeroException class defines Exception that will be thrown if it is attempted to divide number by zero.
 * @author Svjetlana
 * @version 1.0
 * @since 2022-11-25
 */
public class DivisionByZeroException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Divided by zero!";
	
	/**
	 * DivisionByZeroException class constructor.
	 */
	public DivisionByZeroException() {
		super(MESSAGE);
	}

}
