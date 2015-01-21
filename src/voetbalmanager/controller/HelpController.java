package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;

public class HelpController implements Initializable, ControlledScreen {
	
	ScreensController myController= new ScreensController();
	@FXML
	BorderPane border;

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}
	
	@FXML
	public void handleHelpTerug(ActionEvent event) throws IOException{
		myController.setScreen(Main.MainMenu);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		border.setPrefSize(screen.getWidth(), screen.getHeight());
	}

}
