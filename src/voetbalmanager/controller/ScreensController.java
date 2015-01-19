package voetbalmanager.controller;

import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Ravi
 */
public class ScreensController extends StackPane {
	// Creeer een "map" om schermen te houden die tentoongesteld kunnen worden.

	private HashMap<String, Node> screens = new HashMap<>();

	public ScreensController() {
		super();
	}

	// Voeg scherm aan collectie toe
	public void addScreen(String name, Node screen) {
		screens.put(name, screen);
	}

	// Retouneeert de Node met de gevraagde naam
	public Node getScreen(String name) {
		return screens.get(name);
	}

	// Laad de fxml file en dan voeg de scherm to aan de collectie van schermen
	// en uiteindelijk tentoonstellen

	public boolean loadScreen(String name, String resource) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
					resource));
			Parent loadScreen = (Parent) myLoader.load();
			ControlledScreen myScreenControler = ((ControlledScreen) myLoader
					.getController());
			myScreenControler.setScreenParent(this);
			addScreen(name, loadScreen);
			return true;
		} catch (Exception e) {
			System.out.println("Het inladen van "+ resource +" is niet gelukt. Onderzoek de fout bij de fxml,controller of bij de Main");
			return false;
		}
	}

	/*
	 * Probeert scherm te voorschijn te brengen met een aangegeven naam. Maakt
	 * eerst zeker dat de scherm al ingeladen is,en als er meer schermen zijn
	 * dan 1 word de eerste verwijdert en de tweede weergegeven. Als er geen
	 * scherm is om tentoon te stellen wordt het gewoon toegevoegd aan de root.
	 */
	public boolean setScreen(final String name) {
		if (screens.get(name) != null) { // als scherm wel ingeladen is.
			final DoubleProperty opacity = opacityProperty();

			if (!getChildren().isEmpty()) { // als er meer dan 1 scherm aanwezig
											// is.
				Timeline fade = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(
						1000), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent t) {
						getChildren().remove(0); // verwijder de huidige scherm
						getChildren().add(0, screens.get(name)); // plaats de
																	// nieuwe
																	// scherm
						Timeline fadeIn = new Timeline(new KeyFrame(
								Duration.ZERO, new KeyValue(opacity, 0.0)),
								new KeyFrame(new Duration(800), new KeyValue(
										opacity, 1.0)));
						fadeIn.play();
					}
				},new KeyValue(opacity, 0.0)));
				fade.play();

			} else {
				setOpacity(0.0);
				getChildren().add(screens.get(name)); // als er geen ander
														// eerder te voorschijn
														// is, wijz gewoon.
				Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO,
						new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(
						25), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			return true;
		} else {
			System.out.println("screen hasn't been loaded!!! \n");
			return false;
		}

		/*
		 * Node screenToRemove; if(screens.get(name) != null){ //screen loaded
		 * if(!getChildren().isEmpty()){ //if there is more than one screen
		 * getChildren().add(0, screens.get(name)); //add the screen
		 * screenToRemove = getChildren().get(1); getChildren().remove(1);
		 * //remove the displayed screen }else{
		 * getChildren().add(screens.get(name)); //no one else been displayed,
		 * then just show } return true; }else {
		 * System.out.println("screen hasn't been loaded!!! \n"); return false;
		 * }
		 */
	}

	/**
	 * Verwijdert de aangegeven scherm van het lijst van schermen
	 * 
	 * @param name
	 * @return
	 */
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen didn't exist");
			return false;
		} else {
			return true;
		}
	}
}
