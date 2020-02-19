package org.openjfx.pizzeriafx;

import java.util.List;

public class Pizza {
	
	private String name;
	private List<Ingredient> ingredients;
	private double price;
	
	public Pizza(String pizzaName,List<Ingredient> pizzaIngredients)
	{
		name=pizzaName;
		ingredients=pizzaIngredients;
		price=10.0;
		ingredients.forEach(singleIngredient -> {
			price+=singleIngredient.GetPrice();
		});
	}
	public String GetName()
	{
		return name;
	}
	public List<Ingredient> GetIngredients()
	{
		return ingredients;
	}
	public double GetPrice()
	{
		return price;
	}
}
