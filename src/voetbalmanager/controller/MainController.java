package voetbalmanager.controller;


import java.awt.Event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.event.HyperlinkEvent.EventType;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.ui.Application.EventHandler;
import com.sun.javafx.scene.EventHandlerProperties;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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

@SuppressWarnings("unused")
public class MainController implements Initializable, ControlledScreen{

   @FXML private Button NewGame;
   @FXML private Button LoadGame;
   @FXML private Button Exit;
   @FXML private Button Help;
     
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
	      
    }
   
   @FXML
   public void handleHelp(ActionEvent event) throws IOException {
	      myController.setScreen(Main.HelpMe);
    }
   
@FXML
   public void handleExit(ActionEvent event) throws IOException {
	Main.getStage().close();	
    }
   
   @Override
   public void initialize(URL location, ResourceBundle resources){
	   //TODO Code hier
   }

}
