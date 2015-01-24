package voetbalmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import voetbalmanager.Main;

public class OpstellingController implements Initializable, ControlledScreen {

	@FXML
	private Button Terug;
	@FXML
	private TextField tekst;
	@FXML
	private BorderPane border;
	
	ScreensController myController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Rectangle2D screen = Screen.getPrimary().getVisualBounds();
		border.setPrefSize(screen.getWidth(), screen.getHeight());
	}

	public void setScreenParent(ScreensController screen) {
		myController = screen;
	}

	@FXML
	public void handleTerug(ActionEvent event) throws IOException {
		myController.setScreen(Main.ManagementMain);
	}

}
