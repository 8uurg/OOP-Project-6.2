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

import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class OpstellingController implements Initializable, ControlledScreen {
	
	@FXML private Button Terug;
	@FXML private TextField tekst;
	
	ScreensController myController;
   
	@Override
   public void initialize(URL location, ResourceBundle resources){
	   
   }
	public void setScreenParent(ScreensController screen){
		myController = screen;
	}
	 @FXML
	   public void handleTerug(ActionEvent event)throws IOException{
		   myController.setScreen(Main.ManagementMain);
	   }

}
