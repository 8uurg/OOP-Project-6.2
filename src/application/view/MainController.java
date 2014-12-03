package application.view;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

public class MainController implements Initializable{

   @FXML
   private Button NewGame;
   @FXML
   private Button LoadGame;
   @FXML
   private Button Exit;
   @FXML
   private Button Help;
   @FXML
   private Button Next;
   @FXML
   private Button Back;
   @FXML
   private Button Back2;
   @FXML
   private Button StartManagement;
   @FXML
   private TextField TekstVeld;

   
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
	  
	   FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/" + naam + ".xml", true);
	 
	   PrintWriter pw = new PrintWriter(fw);
	   pw.println("<naam = " + naam + ">");
	   //xml files inlezen en toevoegen aan nieuwe xml
			   
	   Parent root = FXMLLoader.load(MainController.class.getResource("NewGameNext.fxml"));
	   Scene scene = new Scene(root);
	   Stage ps = new Stage();
		
		ps.setScene(scene);
		ps.show();
		pw.close();
	
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
