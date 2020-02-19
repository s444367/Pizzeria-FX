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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SecondaryController {

	/*Lista dzielnic w aktualnym menu*/
	ObservableList<String> DistrictList = FXCollections.observableArrayList("W lokalu", 
			"Grunwald", "Stare miasto/Wilda/Jeżyce", "Nowe miasto");
	
	/*Okno na zamówienia*/
	@FXML
	private VBox OrdersVBox;
	
	/*Dane do przedstawiania informacji o dzielnicy*/
	@FXML
	private ComboBox<String> PickedDistrict;
	@FXML
	private Label DeliveryPrice;
	
	/*Dane do przedstawiania informacji o zniżce studenckiej (potwierdzana w trakcie odbioru pizzy odpowiednim dokumentem)*/
	@FXML
	private CheckBox DiscountForStudentsCheckBox;
	
	/*Dane do przedstawiania informacji o liczbie zamówionych pizz i cenie zamówienia*/
	@FXML
	private Label PizzaCountLabel;
	@FXML
	private Label PriceCountLabel;
	
	/*Dane zamówień i dzielnic w ogólnej bazie danych*/
	private List<Order> ListOfOrders;
	private List<District> ListOfDistricts;
	
	/*Dane liczbowe dla ceny za dzielnice, liczby pizz i ceny ogólnej*/
	private double DistrictPriceDouble;
	private int PizzaCountInt;
	private double PriceCountDouble;
	
	/*Funkcja danych startowych*/
	@FXML
	private void initialize()
	{
		FillDistrictsList();
		/*Ustawianie startowej dzielnicy(domyślnie lokal)*/
		PickedDistrict.setValue(DistrictList.get(0));
		PickedDistrict.setItems(DistrictList);
		
		/*Pobieranie zamówień i przedstawianie ich na ekranie*/
		ListOfOrders=PrimaryController.ListOfOrders;
		ListOfOrders.forEach(singleOrder -> {
			/*Ustawianie danych tekstowych*/
			Label tempLabel = new Label();
			String tempOrderString = "";
			tempOrderString+=("Pizza: ["+singleOrder.GetPizza().GetName()+"] Ciasto: ");
			if(singleOrder.GetThicknessOfDough())
			{
				tempOrderString+="[Grube]";
			}
			else
			{
				tempOrderString+="[Normalne]";
			}
			if(singleOrder.GetAdditives().isEmpty()!=true)
			{
				StringJoiner tempAdditivesString = new StringJoiner(", ");
				singleOrder.GetAdditives().forEach(singleAdditives -> {
					tempAdditivesString.add(singleAdditives.GetName());
				});
				tempOrderString+=(" Dodatki: ["+tempAdditivesString.toString()+"]");
			}
			tempOrderString+=(" Cena: ["+String.format("%.2f",singleOrder.GetPrice())+"PLN]");
			tempLabel.setWrapText(true);
			tempLabel.setText(tempOrderString);
			tempLabel.setMaxWidth(Double.MAX_VALUE);
			tempLabel.setAlignment(Pos.CENTER_LEFT);
			
			/*Tworzenie guzika umożliwiającego usunięcie zamówienia*/
			Button tempButton = new Button();
			tempButton.setText("Usuń");
			tempButton.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
			HBox tempHBox = new HBox();
			tempHBox.setPadding(new Insets(5, 5, 0, 5));
			tempButton.setOnAction(new EventHandler<ActionEvent>() {
				/*Funkcja usuwająca zamówienie z listy wewnętrznej, z widoku w oknie oraz ponowne przeliczanie liczby pizz, ceny ogólnej i zniżki studenckiej*/
				@Override
				public void handle(ActionEvent event) {
					ListOfOrders.remove(singleOrder);
					OrdersVBox.getChildren().remove(tempHBox);
					ReCount();
				}});
			/*Dodanie danych do widoku w oknie*/
			tempHBox.getChildren().addAll(tempLabel,tempButton);
			HBox.setHgrow(tempLabel, Priority.ALWAYS);
			HBox.setHgrow(tempButton, Priority.NEVER);
			OrdersVBox.getChildren().add(tempHBox);
		});
		/*Ponowne przeliczanie liczby pizz, ceny ogólnej i zniżki studenckiej*/
		ReCount();
	}
	
	/*Funckja przliczjąca ponownie liczbę pizz, cenę ogólną i zniżke studencką*/
	private void ReCount()
	{
		PizzaCountInt = 0;
		PriceCountDouble = 0;
		ListOfOrders.forEach(singleOrder ->{
			PizzaCountInt++;
			PriceCountDouble+=singleOrder.GetPrice();
		});
		PriceCountDouble+=DistrictPriceDouble;
		if(DiscountForStudentsCheckBox.isSelected())
		{
			double tempDouble=(0.2*PriceCountDouble);
			DiscountForStudentsCheckBox.setText("(- "+String.format("%.2f",tempDouble)+"PLN)");
			PriceCountDouble-=tempDouble;
		}
		else
		{
			DiscountForStudentsCheckBox.setText("(- 0.00PLN)");
		}
		String varietyOfPizza;
		if(PizzaCountInt==1)
		{
			varietyOfPizza=" Pizza";
		}
		else
		{
			if(PizzaCountInt>1 && PizzaCountInt<5)
			{
				varietyOfPizza=" Pizze";
			}
			else
			{
				varietyOfPizza=" Pizz";
			}
		}
		PizzaCountLabel.setText("Razem: "+String.valueOf(PizzaCountInt)+varietyOfPizza);
		PriceCountLabel.setText("Do zapłaty: "+String.format("%.2f",PriceCountDouble)+"PLN");
	}
	/*Wypełnienie listy dzielnic danymi bazy ogólnej*/
	private void FillDistrictsList() 
	{
		ListOfDistricts=new ArrayList<District>();
		
		/*Tworzenie danych bazy dzielnic*/
		District wLokalu=new District("W lokalu",0);
		District grunwald=new District("Grunwald",4);
		District stareMiasto_wilda_jeżyce=new District("Stare miasto/Wilda/Jeżyce",5);
		District noweMiasto=new District("Nowe miasto",6);
		
		/*Dodanie dzielnic do listy*/
		ListOfDistricts.add(wLokalu);
		ListOfDistricts.add(grunwald);
		ListOfDistricts.add(stareMiasto_wilda_jeżyce);
		ListOfDistricts.add(noweMiasto);
	}
	
	/*Funkcja wywołana w trakcie zmiany dzielnicy -> zmienia cenę dowozu do dzielnicy oraz ponownie przlicza liczbę pizz, cenę ogólną i zniżke studencką*/
	@FXML
	private void DistrictListChange()
	{
		ListOfDistricts.forEach(singleDistrict -> {
			if(singleDistrict.GetName()==PickedDistrict.getValue())
			{
				DistrictPriceDouble=singleDistrict.GetPrice();
				DeliveryPrice.setText(String.format("(+ %.2f",singleDistrict.GetPrice())+"PLN)");
				ReCount();
			}
		});
	}
	/*Funkcja wywołana w trakcie zmiany checkboxa zniżki studenckiej -> ponownie przlicza liczbę pizz, cenę ogólną i zniżke studencką*/
	@FXML
	private void DiscountForStudentsChange()
	{
		ReCount();
	}
	
	/*Funkcja guzika wracającego do możliwości dodawania zamówień*/
    @FXML
    private void switchToMenu() throws IOException {
    	PrimaryController.ChangeOrder(ListOfOrders);
        App.setRoot("primary");
    }
    /*Funkcja guzika(jeżeli w koszyku jest jakakolwiek pizza) kończy zamawianie przechodząc do informacji o zakończonym składaniu zamówień*/
    @FXML
    private void CompleteOrder() throws IOException {
    	if(PizzaCountInt!=0)
    	{
    		ThirdController.FinalDistrictString=PickedDistrict.getValue();
        	ThirdController.FinalPriceDouble=PriceCountDouble;
        	App.setRoot("third");
    	}
    }
    
}