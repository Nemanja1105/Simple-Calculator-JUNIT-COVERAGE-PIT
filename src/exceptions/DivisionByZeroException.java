package exceptions;

/**
 * The DivisionByZeroException is thrown to indicate that division by zero is not defined.
 * Extends Exception class.
 *
 * @author Nemanja Milošević
 * @version 1.0
 * @since 24-11-2023
 */
public class DivisionByZeroException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="Dijeljenje sa nulom nije definisano";
	
	 /**
     * Constructs a new DivisionByZeroException with a default error message.
     */
	public DivisionByZeroException() {
		super(MESSAGE);
	}
	
	/**
     * Constructs a new DivisionByZeroException with a specified error message.
     * @param message The detail message explaining the exception.
     */
	public DivisionByZeroException(String message) {
		super(message);
	}

}
