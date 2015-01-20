package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;






import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.model.Competitie;
import voetbalmanager.model.Speler;
import voetbalmanager.model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MarktController implements Initializable, ControlledScreen{

	@FXML
	private Button BackMarket;
	ScreensController myController;   
	
	@FXML private ListView<Speler> LijstKopenA;
	@FXML private ListView<Speler> SecLijstKopenA;
	private ObservableList<Speler> KopenAdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstKopenV;
	@FXML private ListView<Speler> SecLijstKopenV;
	private ObservableList<Speler> KopenVdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstKopenD;
	@FXML private ListView<Speler> SecLijstKopenD;
	private ObservableList<Speler> KopenDdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenA;
	@FXML private ListView<Speler> SecLijstVerkopenA;
	private ObservableList<Speler> VerkopenAdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenV;
	@FXML private ListView<Speler> SecLijstVerkopenV;
	private ObservableList<Speler> VerkopenVdata = FXCollections.observableArrayList();
	
	@FXML private ListView<Speler> LijstVerkopenD;
	@FXML private ListView<Speler> SecLijstVerkopenD;
	private ObservableList<Speler> VerkopenDdata = FXCollections.observableArrayList();
	
	
	
	public MarktController(){
		
	}
	

	   @Override
	   public void initialize(URL location, ResourceBundle resources){
		   
	   }
	   
	   
	   public static void addList(ObservableList<Speler> data){
		 //TODO Arthur
	    	//Competitie com = new Competitie("blah");
			//com = XMLLoader.creeerCompetitie("this");
			for(int i=0; i < data.size(); i++)
			data.add(Main.huidigeCompetitie.get);
	   }
	
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 } 
	 

	@FXML
	public void handleKopenA() throws IOException{

	 } 
	
	@FXML
	public void handleKopenV() throws IOException{

	 } 
	
	@FXML
	public void handleKopenD() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenA() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenV() throws IOException{

	 } 
	
	@FXML
	public void handleVerkopenD() throws IOException{

	 } 



}
