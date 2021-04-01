package calculator;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTesting {

	@Test
	public void returnZeroOnEmptyString() {
		assertEquals(0, Calculator.add(""));
	}
	
	@Test
	public void returnNumber() {
		assertEquals(1, Calculator.add("1"));
	}
	
	@Test
	public void sumOfTwoNumbersSeperatedByComma() {
		assertEquals(3, Calculator.add("1,2"));
	}
	
	@Test
	public void sumOfMultipleNumbers() {
		assertEquals(6, Calculator.add("1,2,3"));
	}
	
	@Test
	public void newLineAsValidDelimeter() {
		assertEquals(6, Calculator.add("1,2\n3"));
	}
	
	@Test
	public void acceptCustomDelimeter() {
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	
	@Test
	public void CustomDelimeterAsSpecialChar() {
		assertEquals(3, Calculator.add("//.\n1.2"));
	}
	
	@Test
	public void exceptionOnNegatives() {
		try {
			Calculator.add("-1,2,3");
			fail("exception");
		}catch(RuntimeException ex) {
			
		}	
	}
	
	@Test
	public void exceptionMessageOnNegatives() {
		try {
			Calculator.add("-1,-2,3");
			fail("exception");
		}catch(RuntimeException ex) {
			assertEquals("Negatives not allowed: -1, -2",ex.getMessage());
		}	
	}
}