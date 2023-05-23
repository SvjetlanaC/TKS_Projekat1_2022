package exceptions;

/**
 * NumberNotInAreaException class defines Exception that will be thrown if given number is not in valid area.
 * @author Svjetlana
 * @version 1.0
 * @since 2022-11-25
 */
public class NumberNotInAreaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Number is not in a valid range";
	
	
	/**
	 * NumberNotInAreaException class constructor.
	 */
	public NumberNotInAreaException() {
		super(MESSAGE);
	}
	
}
