package voetbalmanager.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;

public class LaadGameController implements Initializable, ControlledScreen{

   @FXML private Button Terug;
   @FXML private Button Doorgaan;
   @FXML private Button Back2;
   @FXML private Button StartManagement;
   @FXML private ListView laadgame;
   @FXML private BorderPane border;
   
   ScreensController myController;

   public void setScreenParent(ScreensController screenParent){
       myController = screenParent;
   }
   
   public void initialize(URL location, ResourceBundle resources){
Rectangle2D screen = Screen.getPrimary().getVisualBounds();
border.setPrefSize(screen.getWidth(), screen.getHeight());
}
   @FXML
   public void handleLaadTerug(ActionEvent event)throws IOException{
	   myController.setScreen(Main.MainMenu);
   }
   @FXML
   public void handleDoorgaan(ActionEvent event)throws IOException{
	   //TODO Arthur
	   //(new File("saves")).listFiles();
	   
	   myController.setScreen(Main.ManagementMain);   
   }
}