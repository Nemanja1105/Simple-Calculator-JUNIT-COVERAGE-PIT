package calculators;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * Class represents an extended calculator that, compared to the Calculator class, has additional specific capabilities 
 * (calculation of powers and factorials of a number, as well as checking whether the number is perfect or Armstrong's).
 *  The class inherits from the Calculator class.
 *  @author Nemanja Milošević
 * 	@version 1.0
 * 	@since 24-11-2023
 */
public class CalculatorAdvanced extends Calculator { 
	
	/**
	 * The method performs calculations on the current value of the calculator depending on the passed parameter. 
	 * If a number in the range from 0 to 9 is passed, the scaling operation is performed, while if the character '!' is passed
	 *  the factorial is performed.
	 * @param action Action applied to the current value of the calculator('0' to '9' for exponentiation, '!' for factorial)
	 * @throws NotSupportedOperationException Thrown when the client passes an operation that is not supported (that is not number in range [0-9] or '!')
	 * @throws NumberNotInAreaException Thrown when the current value of the calculator for the factorial operation is not in the range [0-10]
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		if ((action < '0' || action > '9') && action != '!')
			throw new NotSupportedOperationException();

		if (action != '!') {
			var num = this.getCurrentValue().intValue();
			var exp = Character.getNumericValue(action);
			this.setCurrentValue((double) power(num, exp));
		} else {
			var num = this.getCurrentValue().intValue();
			if (this.getCurrentValue() < 0 || this.getCurrentValue() > 10)
				throw new NumberNotInAreaException();
			this.setCurrentValue((double) fact(num));

		}

	}
	
	/**
	 * The method checks whether the integer part of the current calculator value is an Armstrong number or a perfect number.
	 * The integer part must be greater than or equal to 1
	 * @param value Action determines which property of the number will be checked ('A' to check if the number is Armstrong, 'P' to check if the number is perfect)
	 * @return Returns true if the current value of the calculator meets the given property, false otherwise.
	 * @throws NotSupportedOperationException Thrown when the client passes an operation that is not supported (that is not 'A' or 'P')
	 * @throws NumberNotInAreaException Thrown when the current value of the calculator less than 1
	 */
	public Boolean hasCharacteristic(char value)throws NotSupportedOperationException,NumberNotInAreaException
	{
		if(value !='A' && value !='P')
			throw new NotSupportedOperationException();
		var num=this.getCurrentValue().intValue();
		if(num<1)
			throw new NumberNotInAreaException();
		if(value=='A')
			return isArmstrong(num);
		else
			return isPerfect(num);	
	}
	
	/**
	 * Method checks if the passed number is Armstrong's. 
	 * Armstrong numbers are represented by all n-two-digit natural numbers that are equal to the sum of the nth powers of the digits of that number.
	 * @param num The number we're checking to see if it's Armstrong's.
	 * @return Returns true if the number is Armstrong's, false otherwise.
	 */
	private Boolean isArmstrong(int num) {
		int tmp=num,sum=0;
		int exp=numOfDigit(num);
		while(tmp>0) {
			int digit=tmp%10;
			sum+=power(digit, exp);
			tmp/=10;
		}
		return num==sum;
	}
	
	/**
	 * The method checks whether the passed number is perfect.
	 *  A perfect number is a natural number that is equal to the sum of its positive divisors,
	 * @param num The number we're checking to see if it's perfect.
	 * @return Returns true if the number is perfect, false otherwise.
	 */
	private Boolean isPerfect(int num) {
		int sum=0;
		for(int i=1;i<=num/2;i++)
			if(num%i==0)
				sum+=i;
		return num==sum;
	}

	/**
	 * Method performs exponentiation of a number with a power
	 * @param num Base for the power function.
	 * @param exp Exponent for the power function.
	 * @return Returns a power number
	 */
	private int power(int num, int exp) {
		int result = 1;
		for (int i = 1; i <= exp; i++)
			result *= num;
		return result;
	}

	/**
	 * Method calculates the factorial of the passed number
	 * @param num The number whose factorial is being calculated
	 * @return Returns the factorial of a number
	 */
	private int fact(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++)
			result *= i;
		return result;
	}
	
	/**
	 * The method returns the number of digits of the passed number
	 * @param num The number for which we determine the number of digits
	 * @return The number of digits of the number
	 */
	private int numOfDigit(int num) {
		int result=0;
		while(num>0) {
			num=num/10;
			result++;
		}
		return result;
	}
}
