package org.openjfx.pizzeriafx;

public class Ingredient {

	private String name;
	private double price;
	
	public Ingredient(String ingredientName,double ingredientPrice)
	{
		name=ingredientName;
		price=ingredientPrice;
	}
	public String GetName()
	{
		return name;
	}
	public double GetPrice()
	{
		return price;
	}
}
