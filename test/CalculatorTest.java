import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.Calculator;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.instanceOf;

class CalculatorTest {
	private Calculator calculator = new Calculator();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test default konstruktora")
	void testCalculator() {
		assertThat(calculator, notNullValue());
		assertThat(calculator.getCurrentValue(), is(0.0));
	}

	@Test
	@DisplayName("Test currentValue getera")
	void testGetCurrentValue() {
		assertThat(calculator.getCurrentValue(), is(0.0));
	}

	@Test
	@DisplayName("Test currentValue setera")
	void testSetCurrentValue() {
		calculator.setCurrentValue(10.0);
		assertThat(calculator.getCurrentValue(), is(10.0));
	}

	@ParameterizedTest
	@MethodSource("testCalculateParams")
	@DisplayName("Test calculate metode parametrizovana bez izuzetaka")
	void testCalculate(Double startValue, Double value, char operator, Double result)
			throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(startValue);
		calculator.calculate(value, operator);
		assertThat(calculator.getCurrentValue(), is(result));
	}
	
	@ParameterizedTest
	@MethodSource("testCalculateExceptionParams")
	@DisplayName("Test calculate metode parametrizovana sa izuzecima")
	void testCalculateWithException(Double startValue, Double value, char operator, Class<? extends Exception> exception)
			throws DivisionByZeroException, NotSupportedOperationException {
		calculator.setCurrentValue(startValue);
		var exc=assertThrows(exception, ()->calculator.calculate(value, operator));
		assertThat(exc, is(instanceOf(exception)));
		
	}
	
	

	private static Stream<Arguments> testCalculateParams() {
		return Stream.of(Arguments.of(10.0, 5.0, '+', 15.0), Arguments.of(15.0, 5.0, '-', 10.0),
				Arguments.of(8.0, 10.0, '*', 80.0), Arguments.of(50.0, 10.0, '/', 5.0));
	}
	private static Stream<Arguments> testCalculateExceptionParams() {
		return Stream.of(
				Arguments.of(10.0,0.0,'/',DivisionByZeroException.class),
				Arguments.of(50.5,11.4,'%',NotSupportedOperationException.class)
				
		);
	}

}
