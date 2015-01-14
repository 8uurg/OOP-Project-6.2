package voetbalmanager;


import voetbalmanager.controller.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import voetbalmanager.view.*;

public class Main extends Application {
	public static final String MainMenu = "main";
    public static final String screen1ID = "MainMenu.fxml";
    
    public static final String NewGame = "screen";
    public static final String screen2ID = "NewGame.fxml";
    
    public static final String ChooseTeam = "screen3";
    public static final String screen3ID = "NewGameNext.fxml";
    
    public static  String LoadGame = "laadScreen";
    public static  String Screen4ID ="LoadGame.fxml";
    
    public static final String screenHelp = "helpScreen";
    public static final String HelpMe= "Help.fxml";
    
    public static final String screenKlass= "klassScreen";
    public static final String Klassement = "Klassement.fxml";
    
    
    
    public static final String ManagmentMain = "ManagmentMain.fxml";
    public static final String Market = "Market.fxml";
    public static final String Opstelling = "Opstelling.fxml";
    public static final String StartMatch = "StartMatch.fxml";
    public static final String Statistieken = "Statistieken.fxml";
    ScreensController mainContainer;
    
    public ScreensController getController(){
    	return mainContainer;
    }
    @Override
    public void start(Stage primaryStage) {
        //Geef aan de verschillende schermen die er zijn
    	//TODO De rest van de schermen ook functionaliteit zetten net als deze.
    	//TODO Scherm nog kunnen laten resizen.
   
    	mainContainer = new ScreensController();
   
    	//plaats de beschikbare schermen "ready to use"
        mainContainer.loadScreen(Main.MainMenu, Main.screen1ID);
        mainContainer.loadScreen(Main.NewGame, Main.screen2ID);
        mainContainer.loadScreen(Main.ChooseTeam, Main.screen3ID);
        mainContainer.loadScreen(Main.LoadGame, Main.Screen4ID);
        //mainContainer.loadScreen(screenHelp, HelpMe);
        
        mainContainer.setScreen(Main.MainMenu); //Zet de eerste scherm te voorschijn
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}