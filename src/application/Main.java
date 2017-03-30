package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


//Main class where the exceution of application starts
public class Main extends Application {
	
	//Login page is loaded when main is executed
	@Override
	public void start(Stage stage) throws IOException{
			 Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
		        Scene scene_login = new Scene(root);
		        scene_login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		        stage.setScene(scene_login);
		        stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
