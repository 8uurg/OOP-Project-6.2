package voetbalmanager;

	
import java.io.IOException;

import voetbalmanager.view.*;
import voetbalmanager.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


@SuppressWarnings("unused")
public class Main extends Application {
	
	private Stage primaryStage;
    private BorderPane RootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Total Ball Control");

        initRootLayout();

        showMainMenu();
    }
    

    /**
     * Initialiseer de RootLayout.
     */
    public void initRootLayout() {
        try {
            // Laad root layout van fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            RootLayout = (BorderPane) loader.load();

            // Laat de scene zien met de RootLayout
            Scene scene = new Scene(RootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MainMenu in rootlayout
     */
    public void showMainMenu() {
        try {
            // Laad het MainMenu.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MainMenu.fxml"));
            BorderPane MainMenu = (BorderPane) loader.load();

            // MainMenu in het midden van de RootLayout.
            RootLayout.setCenter(MainMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * @return de Primarystage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
	
	/*@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/MainMenu.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			this.primaryStage = primaryStage;
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			primaryStage.setTitle("Total Control");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	} */
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
