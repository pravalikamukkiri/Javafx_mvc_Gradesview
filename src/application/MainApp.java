package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainApp extends Application{
	public static void main(String args[]){          
		 launch(args);     
	} 
		
	@Override     
	public void start(Stage primaryStage) throws Exception { 
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		
		primaryStage.setTitle("Grade View");
		primaryStage.setScene(new Scene(root));	
		primaryStage.show();
   }
}
