package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/*******
 * <p> Title: Main Class. </p>
 * 
 * <p> Description: A JavaFX demonstration application to GradeTracker: A Student Grade Management System</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */

public class MainApp extends Application{
	/**
	 *  The main method initiates the JavaFX application by calling the launch(args) method.
	 */
	public static void main(String args[]){          
		 launch(args);     
	} 
	/**
	 * The start method overrides the default start behavior and sets up the primary stage to display
	 *  the MainView.fxml file, which serves as the main user interface for the GradeTracker application.   
	 *  
	 */
	@Override     
	public void start(Stage primaryStage) throws Exception { 
		Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		
		primaryStage.setTitle("GradeTracker");
		primaryStage.setScene(new Scene(root));	
		primaryStage.show();
		
		/** The application's primary stage is initialized with the title "GradeTracker" and a Scene object 
		* containing the root node loaded from MainView.fxml. 
		* Once the primary stage is set up, it is displayed to the user using primaryStage.show().
		*/
   }
	
}
