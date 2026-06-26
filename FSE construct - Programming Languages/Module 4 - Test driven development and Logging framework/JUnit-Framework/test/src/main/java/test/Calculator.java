package test;

import java.util.Random;

public class Calculator {
	public int add(int firstNumber, int secondNumber)
	{
		return firstNumber + secondNumber;
	}
	
	public int multiply(int num1, int num2)
	{
		return num1*num2;
	}
	public int random()
	{
		return new Random().nextInt(100);
	}
}
