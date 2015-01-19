package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import voetbalmanager.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MarktController implements Initializable, ControlledScreen{

	@FXML
	private Button BackMarket;
	ScreensController myController;   
	public void setScreenParent(ScreensController screenParent){
	       myController = screenParent;
	   }
	   
	
	@FXML
	public void handleBackMarket() throws IOException{
	myController.setScreen(Main.ManagementMain);
	Main.getStage().setTitle("Main Managment");
	 } 
	 




   @Override
   public void initialize(URL location, ResourceBundle resources){
	   
   }
}
