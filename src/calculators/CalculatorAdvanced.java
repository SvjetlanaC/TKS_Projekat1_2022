package calculators;

import exceptions.NotSupportedOperationException;
import exceptions.NumberNotInAreaException;

/**
 * The CalculatorAdvanced class extends Calculator class and allows to perform more actions on the currentValue
 * and to check for specific characteristics of the currentValue.
 * The CalculatorAdvanced class allows actions such as finding factorial or exponentiation of a currentValue.
 * It also allows to check if currentValue has specific characteristic. It is possible to check if curretValue
 * is Armstrong number or perfect number.
 * 
 * @author Svjetlana
 * @version 1.0
 * @since 2022-11-25
 */
public class CalculatorAdvanced extends Calculator{
	
	/**
	 * CalculatorAdvanced class calculator.
	 */
	public CalculatorAdvanced() {
		super();
	}

	/**
	 * Method that can perform actions such as finding factorial or exponentiation of integer part of currentValue.
	 * @param action Represents action that is going to be performed. Valid actions are characters 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 for exponentiation and ! for factorial.
	 * @throws NotSupportedOperationException Exception thrown if action is not valid. Valid actions are characters 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 and !.
	 * @throws NumberNotInAreaException Exception thrown if currentValue is not valid area. Valid values are numbers in a range between 0.0 and 10.0.
	 */
	public void calculateAdvanced(char action) throws NotSupportedOperationException, NumberNotInAreaException {
		if(action >= '0' && action <= '9') {
			Double result = 1.0;
			if(Integer.valueOf(Character.toString(action)) == 0) {
				setCurrentValue(1.0);
			}else if (Integer.valueOf(Character.toString(action)) == 1) {
				//currentValue does not change its value
				setCurrentValue(Double.valueOf(getCurrentValue().intValue()));
			}else {
				for(int i=0; i < Integer.valueOf(Character.toString(action)); i++) {
					result *= getCurrentValue().intValue();
				}
				setCurrentValue(result);
			}
				
		}else if(action == '!') {
			if (getCurrentValue() >= 0.0 && getCurrentValue() <= 10.0) {
				Double result = 1.0;
				if(getCurrentValue().intValue() == 0) {
					setCurrentValue(1.0);
				}else if (getCurrentValue().intValue() == 1) {
					
					setCurrentValue(Double.valueOf(getCurrentValue().intValue()));
					
				}else {
					for(int i=1; i <= getCurrentValue().intValue(); i++) {
						result *= Double.valueOf(i);
					}
					setCurrentValue(result);
				}
			}else
				throw new NumberNotInAreaException();
		}else
			throw new NotSupportedOperationException();
	}

	/**
	 * Method that allows to check if currentValue has given characteristic.
	 * @param value Character that represents characteristic that is going to be checked. Valid characters are A for checking if currentNuber is Armstrong number
	 * and P for checking if currentValue is perfect number.
	 * @return Boolean value that represents if currentValue has given characteristic.
	 * @throws NotSupportedOperationException Exception thrown if value is not valid. Valid values are characters A and P.
	 * @throws NumberNotInAreaException Exception thrown if currentValue is not valid area. Valid values are numbers greater than 1 including 1.
	 */
	public Boolean hasCharacteristic(char value) throws NotSupportedOperationException, NumberNotInAreaException {
		if(value == 'A') {
			if(getCurrentValue().intValue() < 1)
				throw new NumberNotInAreaException();
			
			Integer tmp = getCurrentValue().intValue();
			int sum = 0;
			int numberOfDigits = String.valueOf(tmp).length();
			
			while(tmp > 0) {
				Integer digit = tmp % 10;
				tmp /=10;
				
				Integer pow = 1;
				for(int i = 0; i < numberOfDigits; i++) {
					pow *= digit;
				}
				sum += pow;
			}
			
			if(sum == getCurrentValue().intValue()) {
				return true;
			}else 
				return false;
			
		}else if (value == 'P') {
			if(getCurrentValue().intValue() < 1)
				throw new NumberNotInAreaException();
			
			Integer tmp = getCurrentValue().intValue();
			int sum = 0;
			for(int i=1; i < getCurrentValue().intValue(); i++) {
				if(tmp % i == 0) {
					sum += i;
				}
			}
			if(sum == getCurrentValue().intValue())
				return true;
			else 
				return false;
			
		}else
			throw new NotSupportedOperationException();
		
	}
}
