package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculatorTest {
	@Test
	public void TestAddition()
	{
		Calculator calc = new Calculator();
		assertEquals(5,calc.add(3,2));
	}
	@Test
	public void multiplyTest()
	{
		Calculator calc = new Calculator();
		assertEquals(25,calc.multiply(5,5));
	}
	//Boolean Test
	@Test
	public void testBoolean()
	{
		assertTrue(10 > 5);
	}
	
	//Null Test
	@Test
	public void testNull()
	{
		String name = "Java";
		assertNotNull(name);
	}
	//Assert Not Equals
	@Test
	public void testRandom()
	{
		Calculator calc = new Calculator();
		assertNotEquals(10,calc.random());
	}
	
	//check if a condition is false
	@Test
	public void testAddFalse()
	{
		Calculator calc = new Calculator();
		assertFalse(5 == calc.add(3,2));
	}
}
