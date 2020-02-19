package org.openjfx.pizzeriafx;

public class Ingredient {

	/*Klasa składników zawiera nazwę i cenę składniku*/
	private String name;
	private double price;
	
	public Ingredient(String ingredientName,double ingredientPrice)
	{
		name=ingredientName;
		price=ingredientPrice;
	}
	
	/*Dane jedynie pobierane za pomocą poniższych funkcji*/
	public String GetName()
	{
		return name;
	}
	public double GetPrice()
	{
		return price;
	}
}
