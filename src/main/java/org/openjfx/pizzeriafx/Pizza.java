package org.openjfx.pizzeriafx;

import java.util.List;

public class Pizza {
	
	/*Klasa pizzy zawiera nazwę, listę składników i cenę*/
	private String name;
	private List<Ingredient> ingredients;
	private double price;
	
	/*domyślna pizza ma cenę startową 10PLN(cena normalnego ciasta) + cena składników*/
	public Pizza(String pizzaName,List<Ingredient> pizzaIngredients)
	{
		name=pizzaName;
		ingredients=pizzaIngredients;
		price=10.0;
		ingredients.forEach(singleIngredient -> {
			price+=singleIngredient.GetPrice();
		});
	}
	/*Dane jedynie pobierane za pomocą poniższych funkcji*/
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
