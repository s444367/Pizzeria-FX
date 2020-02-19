package org.openjfx.pizzeriafx;

public class District {

	private String name;
	private double price;
	
	public District(String districtName, double districtPrice)
	{
		name=districtName;
		price=districtPrice;
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
