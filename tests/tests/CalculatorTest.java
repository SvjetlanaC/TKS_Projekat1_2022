package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.Calculator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.MatcherAssert.*;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;


/**
 * The CalculatorTest class is a class used for testing Calculator class.
 * @author Svjetlana
 * @see Calculator class
 * @since 2022-11-25
 */
class CalculatorTest {

	private Calculator calculator = new Calculator();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets currentValue to 0.0 before every test.
	 * @throws Exception Exception thrown.
	 */
	@BeforeEach
	void setUp() throws Exception {
		calculator.setCurrentValue(0.0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Method that tests Calculator class constructor.
	 */
	@Test
	void testCalculator() {
		assertThat(calculator,notNullValue());
	}


	/**
	 * Method that tests method calculate from Calculator class.
	 * Tests valid inputs(without exceptions).
	 * 
	 * @param value Value that will be added to currentValue, subtracted from currentValue, divided or multiplied by currentValue.
	 * @param operator Operator used for testing calculate method.
	 * @param initialValue Value used to set currentValue of calculator before calling calculate method.
	 * @param result Expected output of calculate method.
	 * @throws DivisionByZeroException Exception thrown if division by zero is performed.
	 * @throws NotSupportedOperationException Exception thrown if operation is not supported.
	 */
	@ParameterizedTest
	@MethodSource("testCalculate")
	public void testCalculateParametrized(Double value, char operator,Double initialValue, Double result) throws DivisionByZeroException, NotSupportedOperationException {
	
		calculator.setCurrentValue(initialValue);
		calculator.calculate(value, operator);
			
		assertThat(result,equalTo(calculator.getCurrentValue()));
	}
	
	/**
	 * Method that has all needed parameters for testing all valid operations of calculate method.
	 * @return Stream of arguments for method testCalculateParametrized.
	 */
	private static Stream<Arguments> testCalculate() {
		return Stream.of(
		Arguments.of(2.0,'+',0.0,2.0),
		Arguments.of(3.0,'+',2.0,5.0),
		Arguments.of(-2.53,'+',0.0,-2.53),
		
		
		Arguments.of(2.0,'-',0.0,-2.0),
		Arguments.of(3.0,'-',5.0,2.0),
		Arguments.of(-2.53,'-',0.0,2.53),
		
		Arguments.of(2.0,'*',0.0,0.0),
		Arguments.of(3.0,'*',2.0,6.0),
		Arguments.of(2.3,'*',5.0,11.5),
		Arguments.of(-2.53,'*',1.0,-2.53),
		
		Arguments.of(2.0,'/',0.0,0.0),
		Arguments.of(2.0,'/',6.0,3.0),
		Arguments.of(2.0,'/',-5.0,-2.5),
		Arguments.of(5.0,'/',5.0,1.0)			
		);
	}
	
	/**
	 * Method used for testing if and when are defined exceptions NotSupportedOperationException and DivisionByZeroException
	 * thrown while testing Calculator method calculate.
	 * @param value Value that will be added to currentValue, subtracted from currentValue, divided or multiplied by currentValue.
	 * @param operator Operator used for testing calculate method.
	 * @param result Class of expected exception that should be thrown.
	 */
	@ParameterizedTest
	@MethodSource("testCalculateException")
	public void testCalculateExceptionParametrized(Double value, char operator, Class<Exception> result) {	
	
		Exception exception1 = assertThrows(
        		result, 
	            () -> calculator.calculate(value,operator));
        assertThat(exception1, is(instanceOf(result)));		
		
	}
	
	/**
	 * Method that has all needed parameters for testing if exception are thrown while executing calculate method.
	 * @return Stream of arguments for method testCalculateExceptionParametrized.
	 */
	private static Stream<Arguments> testCalculateException() {
		return Stream.of(
		Arguments.of(0.0,'#',NotSupportedOperationException.class),
		Arguments.of(0.0,'/',DivisionByZeroException.class),
			
		Arguments.of(-2.0,'#',NotSupportedOperationException.class),
		Arguments.of(100.0,'#',NotSupportedOperationException.class)
		);
	}
	
	/**
	 * Method that tests Calculator class getter for currentValue.
	 */
	@Test
	void testGetCurrentValue() {
	
		assertThat(0.0,equalTo(calculator.getCurrentValue()));
	}

	/**
	 * Method that tests Calculator class setter for currentValue.
	 */
	@Test
	void testSetCurrentValue() {
		
		calculator.setCurrentValue(13.0);
		assertThat(13.0,equalTo(calculator.getCurrentValue()));
	}

}
