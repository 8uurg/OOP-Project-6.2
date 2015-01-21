package voetbalmanager.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;

public class MainController implements Initializable, ControlledScreen{

   @FXML private Button NewGame;
   @FXML private Button LoadGame;
   @FXML private Button Exit;
   @FXML private Button Help;
   @FXML private BorderPane borderMain;
     
   ScreensController myController;
  
   public void setScreenParent(ScreensController screenParent){
       myController = screenParent;
   }
   
   @FXML
   public void handleNewGame(ActionEvent event) throws IOException {
      myController.setScreen(Main.NewGame);      
      Main.getStage().setTitle("New Game");

    }
   
   @FXML
   public void handleLoadGame(ActionEvent event) throws IOException {
	      myController.setScreen(Main.LoadGame);
	      Main.getStage().setTitle("Load Game");
	      
    }
   
   @FXML
   public void handleHelp(ActionEvent event) throws IOException {
	      myController.setScreen(Main.HelpMe);
	      Main.getStage().setTitle("Help");
    }
   
@FXML
   public void handleExit(ActionEvent event) throws IOException {
	Main.getStage().close();	
    }
   
   @Override
   public void initialize(URL location, ResourceBundle resources){
	   Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	   borderMain.setPrefSize(screen.getWidth(), screen.getHeight());
   } 
}
