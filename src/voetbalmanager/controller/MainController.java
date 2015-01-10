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
import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class MainController implements Initializable{

   @FXML private Button NewGame;
   @FXML private Button LoadGame;
   @FXML private Button Exit;
   @FXML private Button Help;
   
  
   
   @FXML
   public void handleNewGame(ActionEvent event) throws IOException {
	  
	   Parent root = FXMLLoader.load(Main.class.getResource("view/NewGame.fxml"));
		Scene scene = new Scene(root);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleLoadGame(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(Main.class.getResource("view/LaadGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleHelp(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(Main.class.getResource("view/Help.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
   @FXML
   public void handleExit(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("view/NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
		Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
	
    }
   
  
  
 

   @Override
   public void initialize(URL location, ResourceBundle resources){
	   
   }

}
