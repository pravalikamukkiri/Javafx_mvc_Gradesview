package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.control.Button;

/*******
 * <p> Title: MainViewController Class. </p>
 * 
 * <p> Description:  The MainViewController class is responsible for controlling the Main user interface
		for a user in the GradeTracker application. </p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */

public class MainViewController {
	
	/**
	 * This class is the controller for the MainView.fxml file, which serves as the main user interface for 
	 * the GradeTracker application. The MainViewController handles user interactions such as logging in and 
	 * displaying the appropriate views based on the selected user role.
	 * It works closely with the ProfessorViewController and StudentViewController to provide a seamless
	 * and efficient experience for professors and students using the GradeTracker system.
	 */
	
	@FXML
    private ComboBox<UserRole> roleComboBox; // A ComboBox that allows the user to select their role (Professor or Student).
	@FXML
    private TextField userIDField; // A TextField where the user enters their user ID.
	@FXML
    private PasswordField passwordField; // A PasswordField where the user enters their password.
	@FXML
    private Button loginButton; // A Button that initiates the login process.
	
	private String loggedInUserID; //  A String that stores the user ID of the logged-in user.
	
	private final List<User> users = new ArrayList<>(); // A List of User objects to store the registered users of the application.
	
    private final List<Professor> professors = new ArrayList<>(); //  A List of Professor objects to store the registered professors of the application.
    
    private final List<Student> students = new ArrayList<>(); // A List of Student objects to store the registered students of the application.
    
    ProfessorViewController professorViewController;
    //  An instance of the ProfessorViewController to handle professor-related views and actions.
    
    StudentViewController studentViewController;
    // An instance of the StudentViewController to handle student-related views and actions.
    
    @FXML
    private void initialize() {
    	roleComboBox.getItems().setAll(UserRole.values());
    	// Initializes the controller by setting up the roleComboBox with available user roles.
    }
    
    /**
     * Handles the login process when the user clicks the loginButton. It authenticates the user based on the entered user ID,
     * password, and selected role. If the authentication is successful, it displays the appropriate view (ProfessorView or StudentView).
     */
    @FXML
    private void login() {
    	// Retrieving the entered user ID, password, and selected role from the UI
    	String userID = userIDField.getText(); 
        String password = passwordField.getText();
        UserRole selectedRole = roleComboBox.getValue();
        
        if (selectedRole != null && userID != "" && password !="") {
        	if(authenticateUser(userID,password,selectedRole)) { // Verifying the user's credentials.
        		loggedInUserID = userID;
	            if (selectedRole == UserRole.PROFESSOR) {
	                showProfessorView(); // 
	             }
	            else if (selectedRole == UserRole.STUDENT) {
	            	 showStudentView();
	             }
        	}
        } 
    }
    
    
    /***
     * This method displays the ProfessorView when a professor logs in. It loads
     * the ProfessorView.fxml file and creates an instance of the ProfessorViewController.
     * Professor's information is retrieved using the getProfessor() method and passed to the ProfessorViewController.
     */
    private void showProfessorView() {
    	try {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfessorView.fxml"));
	        Parent professorViewParent = fxmlLoader.load();
	        professorViewController = fxmlLoader.getController();
	        Professor professor = getProfessor(loggedInUserID);
	        professorViewController.setProfessorViewController(this);
	        professorViewController.setProfessor(professor);
	        
	        // A new stage is created to display the ProfessorView.
	        Stage stage = new Stage();
	        stage.setTitle("Professor View");
	        stage.setScene(new Scene(professorViewParent));
	        stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    
    /***
     * This method displays the StudentView when a student logs in. It loads the
     * StudentView.fxml file and creates an instance of the StudentViewController.
     * The student's information is retrieved using the getStudent() method and passed to the StudentViewController
     */
    private void showStudentView() {
    	try {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
	        Parent studentViewParent = fxmlLoader.load();
	        studentViewController = fxmlLoader.getController();
	        Student student = getStudent(loggedInUserID);
	        studentViewController.setStudentViewController(this);
	        studentViewController.setStudentInformation(student);
	        
	        // A stage is created to display the StudentView.
	        Stage stage = new Stage();
	        stage.setTitle("Student View");
	        stage.setScene(new Scene(studentViewParent));
	        stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    

    /***
     * This method authenticates the user based on the entered user ID, password and selected role.
     * The method returns true if the authentication is successful or the user is newly registered, and false otherwise.
     * @param userID
     * @param password
     * @param role
     * @return
     */
    private boolean authenticateUser(String userID, String password, UserRole role) {
        // It searches the users list to find a matching user.
        for (User user : users) {
        	// If the user is found and their password and role matches, the authentication is successful.
            if (user.getUserID().equals(userID)) {
            	if(user.getPassword().equals(password) && user.getRole() == role) {
            		return true;
            	}
                return false;
            }
        }
        
        // If the user is not found, it creates a new User instance with the provided user ID, password, 
        // and role and adds it to the users list 
        User user = new User(userID, password, role);
        users.add(user);
        return true;
    }
    
    
    /**
     * This method retrieves a Student object based on the given user ID. It iterates through the students list
     * to find a student with a matching user ID.
     * If the student is not found, it creates a new temporary student with a placeholder name ("xx") and the provided user ID.
     * @param userID
     * @return
     */
    private Student getStudent(String userID) {
    	for (Student student : students) {
    		if(student.getuserId().equals(userID)) {
    			return student;
    		}
    	}
    	return new Student("xx",userID);
    }

    
    /***
     * This method retrieves a Professor object based on the given user ID. It iterates through the professors list 
     * to find a professor with a matching user ID.
     * If the professor is not found, it creates a new professor instance with the provided user ID and adds it to the professors list.
     * This newly created professor is used to display information for the currently logged-in professor. 
     * @param userID
     * @return
     */
    private Professor getProfessor(String userID) {
    	for (Professor professor : professors) {
    		if(professor.getuserId().equals(userID)) {
    			return professor;
    		}
    	}
    	Professor newProfessor = new Professor(userID);
    	professors.add(newProfessor);
    	return newProfessor;
    }
    
    
    /***
     * This method adds a new student to the students list.
     * It is called by the ProfessorViewController when a professor adds a new student to their class.
     * @param student
     */
    public void addStudent(Student student) {
    	students.add(student);
    }
    
    
    /***
     * This method retrieves a List of integers representing the grades of all students for a specific subject.
     * It takes the selected subject as input and iterates through the students list to collect the grades for the selected subject. 
     * The method returns a List of grades, which can be used to calculate statistics in the StudentView and the ProfessorView.
     * @param selectedSubject
     * @return
     */
    public List<Integer> getsubjectGrades(String selectedSubject){
    	List<Integer> grades= new ArrayList<Integer> ();
    	for(Student student: students) {
    		for(String subject: student.getGrades().keySet()) {
    			if(subject.equals(selectedSubject)) {
    				grades.add(student.getGrades().get(subject));
    			}
    		}
    	}
    	return grades;
    }

}
