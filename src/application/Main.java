package application;

	
import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


@SuppressWarnings("unused")
public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("view/MainMenu.fxml"));
			Scene scene = new Scene(root,800,600);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			this.primaryStage = primaryStage;
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
}
