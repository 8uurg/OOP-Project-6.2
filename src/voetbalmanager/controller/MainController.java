package voetbalmanager.controller;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.transform.stream.StreamResult;

import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class MainController implements Initializable{

   @FXML private Button NewGame;
   @FXML private Button LoadGame;
   @FXML private Button Exit;
   @FXML private Button Help;
   @FXML private Button Next;
   @FXML private Button Back;
   @FXML private Button Back2;
   @FXML private Button StartManagement;
   @FXML private TextField TekstVeld;

   
   @FXML
   public void handleNewGame(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleLoadGame(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleHelp(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleExit(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleBack(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("MainMenu.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleNext(ActionEvent event) throws IOException {
	  
	   String naam = TekstVeld.getText();
	   
	   String loc = "./saves/" + naam + ".xml";
	   
	   Competitie competitie = XMLLoader.creeerCompetitie("eredivisie");
	   // TODO geef huidige competitie door aan centrale spelbeheerder.
	   
	   XMLWriter.saveCompetitie(competitie, new StreamResult(new File(loc)));
			   
	   Parent root = FXMLLoader.load(MainController.class.getResource("NewGameNext.fxml"));
	   Scene scene = new Scene(root);
	   Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
			
    }
   
   @FXML
   public void handleBack2(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleStartManagement(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("ManagementMain.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
  
 

   @Override
   public void initialize(URL location, ResourceBundle resources){
	   
   }

}
