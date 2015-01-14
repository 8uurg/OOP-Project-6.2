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
import javax.xml.transform.stream.StreamResult;
import com.sun.glass.ui.Window;
import voetbalmanager.Main;
import voetbalmanager.XMLLoader;
import voetbalmanager.XMLWriter;
import voetbalmanager.model.Competitie;

public class HelpController implements Initializable, ControlledScreen {
	
	ScreensController myController;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML
	public void handleHelpTerug(ActionEvent event) throws IOException{
		myController.setScreen(Main.MainMenu);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Code hier
	}

}
