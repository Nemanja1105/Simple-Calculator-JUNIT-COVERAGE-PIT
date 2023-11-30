package calculators;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

/**
 * Class represents a simple <b>calculator</b> that has support for simple mathematical operations 
 * such as <b>addition, subtraction, multiplication, division</b>.
 * @author Nemanja Milošević
 * @version 1.0
 * @since 24-11-2023
 */
public class Calculator {
	/**
	 * Represents the current result value stored in the calculator. 
	 * It is used as one operand in the above operations.
	 */
	private Double currentValue;
	
	
	/**
	 * Default constructor of the Calculator class. Sets current value to 0.0 .
	 */
	public Calculator() {
		this.currentValue=0.0;
	}
	
	
	/**
     * Retrieves the current value stored in the Calculator.
     * @return The current value.
     */
	public Double getCurrentValue() {
		return this.currentValue;
	}
	
	/**
	 * Sets the current value in the Calculator.
	 * @param value Represents a value that represents the new current state of the calculator
	 */
	public void setCurrentValue(Double value) {
		this.currentValue=value;
	}
	
	/**
	 * A method that performs simple mathematical operations (which were mentioned at the beginning of the class definition)
	 * based on the operation passed as a character,
	 * while the first parameter of the method and the current state of the calculator are used as operands.
	 * @param value Represents the second operand used in mathematical operations
	 * @param operator Represents the operation that the calculator needs to perform on the operands
	 * @throws DivisionByZeroException  Thrown when attempting division by zero.
	 * @throws NotSupportedOperationException Thrown when the client passes an operation that is not supported (that is not +, -, /, *)
	 */
	public void calculate(Double value, char operator)throws DivisionByZeroException,NotSupportedOperationException {
		switch(operator) {
			case '+':
				this.currentValue+=value;
				break;
			case '-':
				this.currentValue-=value;
				break;
			case '*':
				this.currentValue*=value;
				break;
			case '/':
				if(value==0.0)
					throw new DivisionByZeroException();
				this.currentValue/=value;
				break;
			default:
				throw new NotSupportedOperationException();	
		}
	}
	
}
