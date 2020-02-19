package org.openjfx.pizzeriafx;

import java.util.ArrayList;
import java.util.List;

public class Order {

	/*Klasa zamówienia zawiera pizzę, bool czy pizza ma grube ciasto, listę dodatków i cenę zamówienia*/
	private Pizza pizza;
	private Boolean thickDough;
	private List<Ingredient> additives;
	private double price;
	
	/*domyślne zamówienie nie ma pizzy, normalne ciasto, pustą listę dodatków i cenę zamówienia 0PLN*/
	public Order()
	{
		pizza=null;
		thickDough=false;
		additives=new ArrayList<Ingredient>();
		price=0;
	}
	
	/*Dane ustawiane i pobierane za pomocą poniższych funkcji*/
	public void SetPizza(Pizza orderPizza)
	{
		pizza=orderPizza;
	}
	public void SetThicknessOfDough(Boolean orderThicknessOfDough)
	{
		thickDough=orderThicknessOfDough;
	}
	public void SetAdditives(List<Ingredient> orderAdditives)
	{
		additives=orderAdditives;
	}
	public void SetPrice(double orderPrice)
	{
		price=orderPrice;
	}
	
	public Pizza GetPizza()
	{
		return pizza;
	}
	public Boolean GetThicknessOfDough()
	{
		return thickDough;
	}
	public List<Ingredient> GetAdditives()
	{
		return additives;
	}
	public double GetPrice()
	{
		return price;
	}
}
