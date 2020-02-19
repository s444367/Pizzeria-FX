package org.openjfx.pizzeriafx;

public class District {

	/*Klasa dzielnicy zawiera nazwę i cenę dowozu*/
	private String name;
	private double price;
	
	public District(String districtName, double districtPrice)
	{
		name=districtName;
		price=districtPrice;
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
