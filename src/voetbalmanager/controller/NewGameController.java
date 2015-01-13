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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.transform.stream.StreamResult;





import voetbalmanager.Main;
//import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class NewGameController implements Initializable, ControlledScreen{

   @FXML private Button Next;
   @FXML private Button Back;
   @FXML private Button Back2;
   @FXML private Button StartManagement;
   @FXML private TextField TekstVeld;
   ScreensController myController;

   public void setScreenParent(ScreensController screenParent){
       myController = screenParent;
   }
   
   @FXML
   public void handleBack(ActionEvent event) throws IOException {
	   myController.setScreen(Main.screen1ID);
	   /*	Parent root = FXMLLoader.load(Main.class.getResource("view/MainMenu.fxml"));
		Scene scene = new Scene(root);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	*/
    }
   
   @FXML
   public void handleNext(ActionEvent event) throws IOException {
	  
	   String naam = TekstVeld.getText();
	   String loc = "./saves/" + naam + ".xml";
	   
	   myController.setScreen(Main.screen3ID);

	  /*Competitie competitie = XMLLoader.creeerCompetitie("eredivisie");
	   //TODO geef huidige competitie door aan centrale spelbeheerder.
	   
	   XMLWriter.saveCompetitie(competitie, new StreamResult(new File(loc)));
			   
	   Parent root = FXMLLoader.load(Main.class.getResource("view/NewGameNext.fxml"));
	   Scene scene = new Scene(root);
	   Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
		*/	
    }
   
   @FXML
   public void handleBack2(ActionEvent event) throws IOException {
	   myController.setScreen(Main.screen3ID);

	   	/*Parent root = FXMLLoader.load(Main.class.getResource("view/NewGame.fxml"));
		Scene scene = new Scene(root);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	*/
    }
   
   @FXML
   public void handleStartManagement(ActionEvent event) throws IOException {
	   myController.setScreen(Main.screen3ID);

	  /* 	Parent root = FXMLLoader.load(Main.class.getResource("view/ManagementMain.fxml"));
		Scene scene = new Scene(root);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	*/
    }
   
  
 

   @Override
   public void initialize(URL location, ResourceBundle resources){
	   //TODO Code hier
   }

}
