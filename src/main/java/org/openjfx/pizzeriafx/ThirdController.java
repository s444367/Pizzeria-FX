package org.openjfx.pizzeriafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ThirdController {

	/*Dane do przedstawiania informacji o miejscu docelowym i cenie zamówienia*/
	@FXML
	private Label FinalDistrict;
	@FXML
	private Label FinalPrice;
	
	/*Dane liczbowo/tekstowe miejsca docelowego i ceny zamówienia*/
	public static String FinalDistrictString;
	public static double FinalPriceDouble;
	
	/*Funkcja danych startowych*/
	@FXML
	private void initialize() throws IOException
	{
		/*Zmiania rozmiaru okna informacji(nie potrzebna tak wielka jak poprzednia)*/
		App.ReSizeWindow(300, 350);
		/*Wyświetlenie informacji o miejscu docelowym i cenie zamówienia*/
		FinalDistrict.setText(FinalDistrictString);
		FinalPrice.setText(String.format("%.2f",FinalPriceDouble)+"PLN");
	}
}