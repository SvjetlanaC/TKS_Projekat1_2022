package tests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import calculators.CalculatorAdvanced;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsNull.notNullValue;


import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * The CalculatorAdvancedTest class is a class used for testing CalculatorAdvanced class.
 * @author Svjetlana
 * @see CalculatorAdvanced class
 * @since 2022-11-25
 */
class CalculatorAdvancedTest {
	
	private CalculatorAdvanced calculator = new CalculatorAdvanced();

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
	 * Method that tests CalculatorAdvanced class constructor.
	 */
	@Test
	void testCalculatorAdvanced() {
		assertThat(calculator,notNullValue());
	}

	
	/**
	 * Method that tests method calculateAdvanced from CalculatorAdvanced class.
	 * Tests valid inputs(without exceptions).
	 * 
	 * @param initialValue Value used to set currentValue of CalculatorAdvanced before calling calculate method.
	 * @param operator Operator used for testing calculateAdvanced method. Valid operators are characters 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 for exponentiation and ! for factorial.
	 * @param result Expected output of calculateAdvanced method.
	 * @throws NotSupportedOperationException Exception thrown if operation is not supported.
	 * @throws NumberNotInAreaException Exception thrown if number is not in valid area.
	 */
	@ParameterizedTest
	@MethodSource("testCalculateAdvanced")
	public void testCalculateAdvancedParametrized(Double initialValue, char operator, Double result) throws NotSupportedOperationException, NumberNotInAreaException {
		
		calculator.setCurrentValue(initialValue);
		calculator.calculateAdvanced(operator);
		
		assertThat(result, equalTo(calculator.getCurrentValue()));
	}
	
	/**
	 * Method that has all needed parameters for testing all valid actions of calculateAdvanced method.
	 * @return Stream of arguments for method testCalculateAdvancedParametrized.
	 */
	private static Stream<Arguments> testCalculateAdvanced() {
		return Stream.of(
		
		Arguments.of(0.0,'0',1.0),
		Arguments.of(5.0,'0',1.0),
		Arguments.of(5.0,'3',125.0),
		Arguments.of(2.0,'5',32.0),
		Arguments.of(2.0,'1',2.0),
		Arguments.of(2.1,'1',2.0),
		Arguments.of(2.0,'8',256.0),
		Arguments.of(2.0,'9',512.0),
		Arguments.of(-2.0,'3',-8.0),
		Arguments.of(-2.1,'2',4.0),
		
		Arguments.of(0.0,'!',1.0),
		Arguments.of(1.0,'!',1.0),
		Arguments.of(1.1,'!',1.0),
		Arguments.of(4.0,'!',24.0),
		Arguments.of(4.3,'!',24.0),
		Arguments.of(5.0,'!',120.0),
		Arguments.of(10.0,'!',3628800.0)

		);
	}
	
	/**
	 * Method used for testing if and when are defined exceptions NotSupportedOperationException and NumberNotInAreaException
	 * thrown, while testing CalculatorAdvanced class's method calculateAdvanced.
	 * Tests invalid inputs.
	 * 
	 * @param initialValue Value used to set currentValue of CalculatorAdvanced before calling calculateAdvanced method.
	 * @param operator Operator used for testing calculateAdvanced method.
	 * @param result Class of expected exception that should be thrown.
	 * @throws NotSupportedOperationException Exception thrown if operation is not supported.
	 * @throws NumberNotInAreaException Exception thrown if number is not in valid area.
	 */
	@ParameterizedTest
	@MethodSource("testCalculateAdvancedException")
	public void testCalculateAdvancedExceptionParametrized(Double initialValue, char operator, Class<Exception> result) throws NotSupportedOperationException, NumberNotInAreaException {
		
		calculator.setCurrentValue(initialValue);
		
		Exception exception1 = assertThrows(
        		result, 
	            () -> calculator.calculateAdvanced(operator));
        assertThat(exception1, is(instanceOf(result)));
	}
	
	/**
	 * Method that has all needed parameters for testing if exception are thrown while executing calculateAdvanced method.
	 * @return Stream of arguments for method testCalculateAdvancedExceptionParametrized.
	 */
	private static Stream<Arguments> testCalculateAdvancedException() {
		
		return Stream.of(
				
		Arguments.of(11.0,'!',NumberNotInAreaException.class),
		Arguments.of(-10.0,'!',NumberNotInAreaException.class),
		Arguments.of(-0.3,'!',NumberNotInAreaException.class),
		
		
		Arguments.of(2.0,'%',NotSupportedOperationException.class),
		Arguments.of(2.0,':',NotSupportedOperationException.class),
		Arguments.of(-2.0,'a',NotSupportedOperationException.class),
		Arguments.of(4.0,'#',NotSupportedOperationException.class)
		);
	}
	
	/**
	 * Method that tests method hasCharacteristic from CalculatorAdvanced class.
	 * Tests valid inputs(without exceptions).
	 * 
	 * @param initialValue Value used to set currentValue of CalculatorAdvanced before calling hasCharacteristic method.
	 * @param characteristic Character that represents characteristic that is going to be checked. Valid characters are A for checking if currentNuber is Armstrong number
	 * and P for checking if currentValue is perfect number.
	 * @param result Expected output of hasCharacteristic method. Boolean value that represents if currentValue has given characteristic.
	 * @throws NotSupportedOperationException Exception thrown if operation is not supported.
	 * @throws NumberNotInAreaException Exception thrown if number is not in valid area.
	 */
	@ParameterizedTest
	@MethodSource("testHasCharacteristic")
	public void testHasCharacteristicParametrized(Double initialValue, char characteristic, Boolean result) throws NotSupportedOperationException, NumberNotInAreaException {	
		calculator.setCurrentValue(initialValue);
		assertThat(result,equalTo(calculator.hasCharacteristic(characteristic)));	
	}
	
	/**
	 * Method that has all needed parameters for testing all valid characteristic of hasCharacteristic method.
	 * @return Stream of arguments for method testHasCharacteristicParametrized.
	 */
	private static Stream<Arguments> testHasCharacteristic() {
		return Stream.of(
		Arguments.of(1.0,'A',true),
		Arguments.of(153.0,'A',true),
		Arguments.of(1634.0,'A',true),
		Arguments.of(1634.60,'A',true),
		Arguments.of(9474.1,'A',true),
		
		Arguments.of(152.0,'A',false),
		Arguments.of(152.20,'A',false),
		
		
		
		
		Arguments.of(6.0,'P',true),
		Arguments.of(28.0,'P',true),
		Arguments.of(28.1,'P',true),
		
		Arguments.of(1.0,'P',false),
		Arguments.of(152.40,'P',false)
		);
	}

	/**
	 * Method used for testing if and when are defined exceptions NotSupportedOperationException and NumberNotInAreaException
	 * thrown while testing CalculatorAdvanced class's method hasCharacteristic.
	 * Tests invalid inputs.
	 * 
	 * @param initialValue Value used to set currentValue of CalculatorAdvanced before calling hasCharacteristic method.
	 * @param characteristic Character that represents characteristic that is going to be checked. Valid characters are A for checking if currentNuber is Armstrong number
	 * and P for checking if currentValue is perfect number.
	 * @param result Class of expected exception that should be thrown.
	 * @throws NotSupportedOperationException Exception thrown if operation is not supported.
	 * @throws NumberNotInAreaException Exception thrown if number is not in valid area.
	 */
	@ParameterizedTest
	@MethodSource("testHasCharacteristicException")
	public void testHasCharacteristicExceptionParametrized(Double initialValue, char characteristic, Class<Exception> result) throws NotSupportedOperationException, NumberNotInAreaException {	
		calculator.setCurrentValue(initialValue);
		
		Exception exception1 = assertThrows(
        		result, 
	            () -> calculator.hasCharacteristic(characteristic));
        assertThat(exception1, is(instanceOf(result)));
	}
	
	/**
	 * Method that has all needed parameters for testing if exception are thrown while executing testHasCharacteristic method.
	 * @return Stream of arguments for method testHasCharacteristicExceptionParametrized.
	 */
	private static Stream<Arguments> testHasCharacteristicException() {
		return Stream.of(
		Arguments.of(0.0,'A',NumberNotInAreaException.class),
		Arguments.of(0.5,'A',NumberNotInAreaException.class),
		Arguments.of(-1634.0,'A',NumberNotInAreaException.class),	
		
		Arguments.of(0.0,'P',NumberNotInAreaException.class),
		Arguments.of(0.3,'P',NumberNotInAreaException.class),
		Arguments.of(-28.0,'P',NumberNotInAreaException.class),
		
		Arguments.of(5.0,'#',NotSupportedOperationException.class),
		Arguments.of(1.0,'#',NotSupportedOperationException.class),
		Arguments.of(-1.0,'#',NotSupportedOperationException.class),
		Arguments.of(0.0,'#',NotSupportedOperationException.class)
		);
	}

}
