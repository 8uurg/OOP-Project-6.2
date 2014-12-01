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
import javafx.stage.Stage;

public class MainController implements Initializable{

   @FXML
   private Button NewGame;
   private Button LoadGame;
   private Button Exit;
   private Button Help;
   private Button Next;
   private Button Back;
   private Button Back2;
   private Button StartManagement;

   
   @FXML
   public void handleNewGame(ActionEvent event) throws IOException {
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGame.fxml"));
		Scene scene = new Scene(root,800,600);
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
	   	Parent root = FXMLLoader.load(MainController.class.getResource("NewGameNext.fxml"));
		Scene scene = new Scene(root,800,600);
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
