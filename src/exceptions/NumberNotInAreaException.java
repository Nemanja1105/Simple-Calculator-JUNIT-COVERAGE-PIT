package exceptions;

/**
 * The NumberNotInAreaException is thrown to indicate that a number is not within an allowable range.
 * Extends Exception class.
 * @author Nemanja Milošević
 * @version 1.0
 * @since 24-11-2023
 */
public class NumberNotInAreaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE="Broj nije u dozvoljenom opsegu";
	/**
     * Constructs a new NumberNotInAreaException with a default error message.
     */
	public NumberNotInAreaException() {
		super(MESSAGE);
	}
	
	 /**
     * Constructs a new NumberNotInAreaException with a specified error message.s
     * @param message The detail message explaining the exception.
     */
	public NumberNotInAreaException(String message) {
		super(message);
	}
}
