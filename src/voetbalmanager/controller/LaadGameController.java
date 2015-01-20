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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.xml.transform.stream.StreamResult;

import voetbalmanager.Main;
import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class LaadGameController implements Initializable, ControlledScreen{

   @FXML private Button Terug;
   @FXML private Button Doorgaan;
   @FXML private Button Back2;
   @FXML private Button StartManagement;
   @FXML private ListView laadgame;
   ScreensController myController;

   public void setScreenParent(ScreensController screenParent){
       myController = screenParent;
   }
   
   public void initialize(URL location, ResourceBundle resources){
	   //TODO Code hier
   }
   @FXML
   public void handleLaadTerug(ActionEvent event)throws IOException{
	   myController.setScreen(Main.MainMenu);
   }
   @FXML
   public void handleDoorgaan(ActionEvent event)throws IOException{
	   //TODO
	   //(new File("saves")).listFiles();
	   
	   myController.setScreen(Main.ManagementMain);   
   }
}