package exceptions;

/** 
 * The NotSupportedOperationException is thrown to indicate that an operation is not supported.
 * Extends Exception class.
 * @author Nemanja Milošević
 * @version 1.0
 * @since 24-11-2023
 */
public class NotSupportedOperationException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE="Operator nije podrzan";
	
	 /**
     * Constructs a new NotSupportedOperationException with a default error message.
     */
	public NotSupportedOperationException() {
		super(MESSAGE);
	}
	
	 /**
     * Constructs a new NotSupportedOperationException with a specified error message. 
     * @param message The detail message explaining the exception.
     */
	public NotSupportedOperationException(String message) {
		super(message);
	}
}
