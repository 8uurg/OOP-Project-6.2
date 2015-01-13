package voetbalmanager;


import voetbalmanager.controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String screen1ID = "main";
    public static final String screen1File = "MainMenu.fxml";
    
    public static String screen2ID = "screen2";
    public static final String screen2File = "NewGame.fxml";
    
    public static String screen3ID = "screen3";
    public static final String screen3File = "NewGameNext.fxml";
    /*
    public static final String Help    = "Help.fxml";
    public static final String Klassement = "Klassement.fxml";
    public static final String LaadGame = "LaadGame.fxml"; 
    public static final String ManagmentMain = "ManagmentMain.fxml";
    public static final String Market = "Market.fxml";
    public static final String Opstelling = "Opstelling.fxml";
    public static final String StartMatch = "StartMatch.fxml";
    public static final String Statistieken = "Statistieken.fxml";
    */
    
    @Override
    public void start(Stage primaryStage) {
        //Geef aan de verschillende schermen die er zijn
    	//TODO De rest van de schermen ook functionaliteit zetten net als deze.
    	//TODO Scherm nog kunnen laten resizen.
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);
        
        mainContainer.setScreen(Main.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root,670,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}