package voetbalmanager.controller;

import java.awt.Dialog;
import java.io.File;

import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import org.controlsfx.*;
import org.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.transform.stream.StreamResult;

import com.sun.glass.ui.Window;
import com.sun.javafx.scene.accessibility.Action;

import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;
import voetbalmanager.view.*;
public class NewGameController implements Initializable, ControlledScreen {
	@FXML
	TextField TekstVeld;
	@FXML
	Button closeButton;
	
	ScreensController myController;
	Stage ps;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML
	public void handleBack(ActionEvent event) throws IOException{
		myController.setScreen(Main.MainMenu);
	}

	@FXML
	public void handleNext(ActionEvent event) throws IOException{
		String naam = TekstVeld.getText();
		String loc = "./saves/" + naam + ".xml";
	if(naam==null || naam.isEmpty()){
		Parent root = FXMLLoader.load(Main.class.getResource("view/Popup.fxml"));
		Scene my = new Scene(root);
		ps = new Stage();
		ps.setScene(my);
		ps.show();
	}
	else{
		myController.setScreen(Main.ChooseTeam);
	}
	}
	
	@FXML
	public void handlePopup(ActionEvent event){
		Stage stage = (Stage)closeButton.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Code hier
	}

}
