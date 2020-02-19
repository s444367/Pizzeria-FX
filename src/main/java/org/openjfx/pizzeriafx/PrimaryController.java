package org.openjfx.pizzeriafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PrimaryController {
	
	/*Lista pizz w aktualnym menu*/
	ObservableList<String> PizzaList = FXCollections.observableArrayList("MARGERITTA","MARINARA","NAPOLETANA",
			"HAWAJSKA","FUNGHI","QUATRO","CAPRICCIOSA","DINAMITE");
	
	/*Dane do przedstawiania informacji o pizzy*/
	@FXML
	private ComboBox<String> PickedPizza;
	@FXML
	private Label PizzaPrice;
	@FXML
	private Label PizzaIngredients;
	
	/*Dane do przedstawiania informacji o cieście*/
	@FXML
	private CheckBox PickedDough;
	@FXML
	private Label DoughPrice;
	
	/*Dane do przedstawiania informacji o dodatkach*/
	@FXML 
	private VBox IngredienstGrid;
	@FXML 
	private Label IngredienstPrice;
	
	/*Dane do przedstawiania informacji o cenie za daną pizze*/
	@FXML
	private Label AllPrice;
	private double PizzaPriceDouble;
	private double DoughPriceDouble;
	private double IngredienstPriceDouble;
	
	/*Dane pizz oraz składników(z ich checkboxami) w ogólnej bazie danych*/
	private List<Pizza> ListOfAllPizzas;
	private List<Ingredient> ListOfAllIngredients;
	private List<CheckBox> IngredientsCheckBoxes;
	
	/*Obecne zamówienie(jedna pizza)*/
	private Order currentOrder;
	/*Lista zamówień różnych pizz (koszyk)*/
	public static List<Order> ListOfOrders = new ArrayList<Order>();
	
	/*Funkcja danych startowych*/
	@FXML
	private void initialize()
	{
		/*Pobiera listy z bazy*/
		ListOfAllPizzas = App.ListOfAllPizzas;
		ListOfAllIngredients = App.ListOfAllIngredients;
		IngredientsCheckBoxes = new ArrayList<CheckBox>();
		/*Ustawia ceny na 0PLN oraz "tworzy" zamówienie*/
		DoughPriceDouble=0.0;
		IngredienstPriceDouble=0.0;
		currentOrder = new Order();
		
		/*Ustawia pierwszą pizze z bazy na domyślną*/
		Pizza startPizza = ListOfAllPizzas.get(0);
		PickedPizza.setValue(startPizza.GetName());
		PickedPizza.setItems(PizzaList);
		currentOrder.SetPizza(startPizza);
		
		/*Ustawia informacje o pizzy domyślnej(informacje o składnikach oraz cenie)*/
		PizzaPriceDouble=startPizza.GetPrice();
		PizzaPrice.setText(String.format("%.2f",startPizza.GetPrice())+"PLN");
		StringJoiner ingredientsString = new StringJoiner(", ");
		startPizza.GetIngredients().forEach(singleIngredient -> {
			ingredientsString.add(singleIngredient.GetName());
		});
		PizzaIngredients.setText(ingredientsString.toString());
		/*Ustawia ciasto na normalne(domyślne)*/
		PickedDough.setSelected(false);
		
		/*Utworzenie listy dodatków jako lista checkboxów*/
		for(int i=0;i<ListOfAllIngredients.size();i++)
		{
			CheckBox TempCheckBox = new CheckBox();
			TempCheckBox.setText(ListOfAllIngredients.get(i).GetName());
			IngredientsCheckBoxes.add(TempCheckBox);
			TempCheckBox.setOnAction(new EventHandler<ActionEvent>() {
				/*Funkcja wywołana w trakcie zmiany któregoś składnika -> nowe obliczanie ceny składników oraz odświeżanie ceny zamówienia*/
				@Override
				public void handle(ActionEvent event) {
					double TempPrice = 0.0;
					List<Ingredient> tempIngredients=new ArrayList<Ingredient>();
					for(int j=0;j<IngredientsCheckBoxes.size();j++)
					{
						if(IngredientsCheckBoxes.get(j).isSelected())
						{
							tempIngredients.add(ListOfAllIngredients.get(j));
							TempPrice+=ListOfAllIngredients.get(j).GetPrice();
						}
					}
					IngredienstPriceDouble=TempPrice;
					currentOrder.SetAdditives(tempIngredients);
					IngredienstPrice.setText(String.format("%.2f",TempPrice)+"PLN");
					RefreashAllPrice();
				}
			});
			/*Dodanie listy składników do okna*/
			IngredienstGrid.getChildren().add(TempCheckBox);
		}
		/*Odświeżenie ceny zamówienia*/
		RefreashAllPrice();
	}
	
	/*Funkcja wywołana w trakcie zmiany pizzy -> zmienia cenę pizzy, informację o składnikach, resetuje ciasto do normalnego oraz odświeża cenę tego zamówienia*/
	@FXML
	private void PizzaListChange()
	{
		ListOfAllPizzas.forEach(singlePizza -> {
			if(singlePizza.GetName()==PickedPizza.getValue())
			{
				PizzaPriceDouble=singlePizza.GetPrice();
				PizzaPrice.setText(String.format("%.2f",singlePizza.GetPrice())+"PLN");
				StringJoiner ingredientsString = new StringJoiner(", ");
				singlePizza.GetIngredients().forEach(singleIngredient -> {
					ingredientsString.add(singleIngredient.GetName());
				});
				PizzaIngredients.setText(ingredientsString.toString());
				PickedDough.setSelected(false);
				
				currentOrder.SetPizza(singlePizza);
				PizzaDoughChange();
				RefreashAllPrice();
			}
		});
	}
	/*Funkcja wywołana w trakcie zmiany ciasta -> zmienia cenę ciasta oraz odświeża cenę zamówienia*/
	@FXML
	private void PizzaDoughChange()
	{
		if(PickedDough.isSelected())
		{
			DoughPriceDouble=2.0;
			DoughPrice.setText("2.00PLN");
		}
		else
		{
			DoughPriceDouble=0.0;
			DoughPrice.setText("0.00PLN");
		}
		currentOrder.SetThicknessOfDough(PickedDough.isSelected());
		RefreashAllPrice();
	}
	
	/*Funkcja odświeżająca cenę zamówienia*/
	@FXML
	private void RefreashAllPrice()
	{
		double AllPriceDouble=PizzaPriceDouble+DoughPriceDouble+IngredienstPriceDouble;
		currentOrder.SetPrice(AllPriceDouble);
		AllPrice.setText(String.format("%.2f",AllPriceDouble)+"PLN");
	}
	
	/*Funkcja guzika dodającego zamówienie do koszyka*/
    @FXML
    private void AddToBasket() {
    	ListOfOrders.add(currentOrder);
    	Order tempOrder=currentOrder;
    	currentOrder=new Order();
    	currentOrder.SetPizza(tempOrder.GetPizza());
    	currentOrder.SetThicknessOfDough(tempOrder.GetThicknessOfDough());
    	currentOrder.SetAdditives(tempOrder.GetAdditives());
    	currentOrder.SetPrice(tempOrder.GetPrice());
    }
    /*Funkcja guzika służącego do przejścia do koszyka*/
    @FXML
    private void SwitchToBasket() throws IOException {
        App.setRoot("secondary");
    }
    
    /*Funkcja do odzyskiwania zamówień z koszyka w momencie chęci dodadania zamówień*/
    public static void ChangeOrder(List<Order> SecondaryListOfOrders){
    	ListOfOrders=SecondaryListOfOrders;
    }
}
