import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.CalculatorAdvanced;
import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

class CalculatorAdvancedTest {
	private CalculatorAdvanced calculator = new CalculatorAdvanced();

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

	@ParameterizedTest
	@MethodSource("testCalculateAdvancedParams")
	@DisplayName("Test action metode parametrizovana bez izuzetaka")
	void testCalculateAdvanced(Double value, char action, Double result) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(value);
	 	calculator.calculateAdvanced(action);
	 	assertThat(calculator.getCurrentValue(), is(result));
	}

	@ParameterizedTest
	@MethodSource("testCalculateAdvancedWithExceptionParams")
	@DisplayName("Test action metode parametrizovana sa izuzecima")
	void testCalculateAdvancedWithException(Double value, char action, Class<? extends Exception> exception) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(value);
	 	var exc=assertThrows(exception, ()->calculator.calculateAdvanced(action));
	 	assertThat(exc, is(instanceOf(exception)));
	}
	
	@ParameterizedTest
	@MethodSource("testHasCharacteristicParams")
	@DisplayName("Test hasCharacteristic metode parametrizovana bez izuzetaka")
	void testHasCharacteristic(Double value, char action, Boolean result) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(value);
	 	assertThat(calculator.hasCharacteristic(action), is(result));
	}
	
	@ParameterizedTest
	@MethodSource("testHasCharacteristicWithExceptionParams")
	@DisplayName("Test hasCharacteristic metode parametrizovana sa izuzecima")
	void testHasCharacteristicWithException(Double value, char action, Class<? extends Exception> exception) throws NotSupportedOperationException, NumberNotInAreaException {
		calculator.setCurrentValue(value);
	 	var exc=assertThrows(exception, ()->calculator.hasCharacteristic(action));
	 	assertThat(exc, is(instanceOf(exception)));
	}
	
	private static Stream<Arguments> testCalculateAdvancedParams() {
		return Stream.of(Arguments.of(5.0,'3',125.0),Arguments.of(5.0,'0',1.0),Arguments.of(1.0,'9',1.0),Arguments.of(0.0,'0',1.0),
				Arguments.of(5.0,'!',120.0),Arguments.of(0.0,'!',1.0),Arguments.of(10.0,'!',3628800.0));
	}

	private static Stream<Arguments> testCalculateAdvancedWithExceptionParams() {
		return Stream.of(Arguments.of(5.0,'/',NotSupportedOperationException.class),Arguments.of(5.0,':',NotSupportedOperationException.class),
				Arguments.of(-0.001,'!',NumberNotInAreaException.class),Arguments.of(10.0001,'!',NumberNotInAreaException.class));
	}
	
	private static Stream<Arguments> testHasCharacteristicParams() {
		return Stream.of(Arguments.of(1.0,'A',true),Arguments.of(153.0,'A',true),Arguments.of(49.0,'A',false),Arguments.of(28.0,'P',true),Arguments.of(10.0,'P',false));
	}
	
	private static Stream<Arguments> testHasCharacteristicWithExceptionParams() {
		return Stream.of(Arguments.of(5.0,'a',NotSupportedOperationException.class),Arguments.of(5.0,'p',NotSupportedOperationException.class),Arguments.of(0.999,'A',NumberNotInAreaException.class));
	}

}
