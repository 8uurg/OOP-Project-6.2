package application.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagementController implements Initializable{
	
	@FXML private Button StartMatch;
    @FXML private Button Opstelling;
    @FXML private Button Spelersmarkt;
    @FXML private Button Klassement;
    @FXML private Button Statistieken;
    @FXML private Button Opslaan;
    @FXML private Button Exit;
	 
	
	@FXML
	public void handleStartMatch(){
		
	} 
	
	@FXML
	public void handleOpstelling(){
		
	} 
	
	@FXML
	public void handleSpelersmarkt() throws IOException{
		Parent root = FXMLLoader.load(MainController.class.getResource("Market.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
		
	} 
	
	@FXML
	public void handleKlassement(){
		
	} 
	
	@FXML
	public void handleStatistieken(){
		
	} 
	
	@FXML
	public void handleOpslaan(){
		
	} 
	
	@FXML
	public void handleExit() throws IOException{
		Parent root = FXMLLoader.load(MainController.class.getResource("MainMenu.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	} 
	
	/** 
		 * @FXML
		 * private Button button;
		 * 
		 * @FXML
		 * public void handlebutton(){} 
		 **/
	
	
	

	   @Override
	   public void initialize(URL location, ResourceBundle resources){
		   
	   }
}