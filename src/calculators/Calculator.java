package calculators;

import exceptions.DivisionByZeroException;
import exceptions.NotSupportedOperationException;


/**
 * The Calculator program implements an application that performs four simple mathematical operations, such as addition, subtraction, division
 * and multiplication, on double type numbers.
 * Based on chosen operator it is possible to add current value to given number, subtract given number from current value, and divide or multiply
 * current value by given number.
 * 
 * @author Svjetlana
 * @version 1.0
 * @since 2022-11-25
 */
public class Calculator {

	private Double currentValue;
	
	/**
	 * Calculator class constructor.
	 */
	public Calculator() {
		super();
		currentValue = 0.0;
	}

	/**
	 * Method that performs mathematical operations such as -, +, * and / on currentValue.
	 * @param value Value that will be added to currentValue, subtracted from currentValue, or the value that will divide or multiply currentValue with.
	 * @param operator Operator that indicates which operation will be executed. Valid operators are -, +, * and /.
	 * @throws DivisionByZeroException Exception thrown if it is attempted to divide currentValue by zero.
	 * @throws NotSupportedOperationException Exception thrown if operator is not valid. Valid operators are -, +, * and /.
	 */
	public void calculate(Double value, char operator) throws DivisionByZeroException, NotSupportedOperationException {

		switch(operator) {
			case '+':
				currentValue = currentValue + value;
				break;
			case '-':
				currentValue = currentValue - value;
				break;
			case '/':
				if(value==0)
					throw new DivisionByZeroException();
				currentValue = currentValue / value;
				break;
			case '*':
				currentValue = currentValue * value;
				break;
			default:
				throw new NotSupportedOperationException();
		}
	
	}
	
	/**
	 * Gets currentValue.
	 * @return currentValue Current value.
	 */
	public Double getCurrentValue() {
		return currentValue;
	}

	/**
	 * Sets currentValue.
	 * @param currentValue Value that should be set to currentValue.
	 */
	public void setCurrentValue(Double currentValue) {
		this.currentValue = currentValue;
	}
}
