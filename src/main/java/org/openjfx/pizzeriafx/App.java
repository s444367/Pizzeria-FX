package org.openjfx.pizzeriafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Scene scene;
    /*Listy ogólnej bazy danych*/
    public static List<Ingredient> ListOfAllIngredients;
    public static List<Pizza> ListOfAllPizzas;
    
    @Override
    public void start(Stage newStage) throws IOException {
    	
    	FillLists();
    	
    	stage=newStage;
        scene = new Scene(loadFXML("primary"), 640, 640);
        stage.setTitle("Pizzeria FX");
        stage.setScene(scene);
        stage.show();
    }
    
    /*Wypełnienie list danymi bazy ogólnej*/
    private void FillLists()
    {
    	ListOfAllIngredients=new ArrayList<Ingredient>();
    	ListOfAllPizzas=new ArrayList<Pizza>();
    	
    	/*Tworzenie danych bazy składników*/
    	Ingredient papryka = new Ingredient("papryka",0.80);
    	Ingredient czosnek = new Ingredient("czosnek",0.90);
    	Ingredient pieczarki = new Ingredient("pieczarki",1.10);
    	Ingredient ananas = new Ingredient("ananas",1.40);
    	Ingredient mozzerella = new Ingredient("mozzerella",1.50);
    	Ingredient salami = new Ingredient("salami",1.60);
    	Ingredient karczochy = new Ingredient("karczochy",1.70);
    	Ingredient kukurydza = new Ingredient("kukurydza",1.90);
    	Ingredient sosPomidorowy = new Ingredient("sos pomidorowy",2.00);
    	Ingredient bekon = new Ingredient("bekon",2.10);
    	Ingredient szynka = new Ingredient("szynka",2.20);
    	Ingredient sosCzosnkowy = new Ingredient("sos czosnkowy",2.50);
    	Ingredient oliwaZOliwek = new Ingredient("oliwa z oliwek",3.00);
    	Ingredient czarneOliwki = new Ingredient("czarne oliwki",3.20);
    	
    	/*Dodanie składników do listy*/
    	ListOfAllIngredients.add(papryka);
    	ListOfAllIngredients.add(czosnek);
    	ListOfAllIngredients.add(pieczarki);
    	ListOfAllIngredients.add(ananas);
    	ListOfAllIngredients.add(mozzerella);
    	ListOfAllIngredients.add(salami);
    	ListOfAllIngredients.add(karczochy);
    	ListOfAllIngredients.add(kukurydza);
    	ListOfAllIngredients.add(sosPomidorowy);
    	ListOfAllIngredients.add(bekon);
    	ListOfAllIngredients.add(szynka);
    	ListOfAllIngredients.add(sosCzosnkowy);
    	ListOfAllIngredients.add(oliwaZOliwek);
    	ListOfAllIngredients.add(czarneOliwki);
    	
    	/*Tworzenie danych bazy pizz*/
    	Pizza MARGERITTA = new Pizza("MARGERITTA",List.of(
    			sosPomidorowy, mozzerella));
    	Pizza MARINARA = new Pizza("MARINARA",List.of(
    			sosPomidorowy, mozzerella, czosnek));
    	Pizza NAPOLETANA = new Pizza("NAPOLETANA",List.of(
    			sosPomidorowy, mozzerella, czarneOliwki));
    	Pizza HAWAJSKA = new Pizza("HAWAJSKA",List.of(
    			sosPomidorowy, mozzerella, ananas));
    	Pizza FUNGHI = new Pizza("FUNGHI",List.of(
    			sosPomidorowy, mozzerella, pieczarki));
    	Pizza QUATRO = new Pizza("QUATRO",List.of(
    			sosPomidorowy, mozzerella, szynka, karczochy, papryka));
    	Pizza CAPRICCIOSA = new Pizza("CAPRICCIOSA",List.of(
    			sosPomidorowy, mozzerella, szynka, pieczarki));
    	Pizza DINAMITE = new Pizza("DINAMITE",List.of(
    			sosPomidorowy, mozzerella, salami));
    	
    	/*Dodanie pizz do listy*/
    	ListOfAllPizzas.add(MARGERITTA);
    	ListOfAllPizzas.add(MARINARA);
    	ListOfAllPizzas.add(NAPOLETANA);
    	ListOfAllPizzas.add(HAWAJSKA);
    	ListOfAllPizzas.add(FUNGHI);
    	ListOfAllPizzas.add(QUATRO);
    	ListOfAllPizzas.add(CAPRICCIOSA);
    	ListOfAllPizzas.add(DINAMITE);
    }

    /*Zmiana sceny*/
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    /*Zmiana rozmiaru okna*/
    static void ReSizeWindow(int height,int width) throws IOException {
        stage.setWidth(width);
        stage.setHeight(height);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}