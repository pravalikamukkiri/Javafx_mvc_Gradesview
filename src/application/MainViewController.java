package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.control.Button;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class MainViewController {
	
	@FXML
    private ComboBox<UserRole> roleComboBox;
	@FXML
    private TextField userIDField;
	@FXML
    private PasswordField passwordField;
	@FXML
    private Button loginButton;
	
	private String loggedInUserID;
	private final List<User> users = new ArrayList<>();
    private final List<Professor> professors = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    ProfessorViewController professorViewController;
    StudentViewController studentViewController;
    
    @FXML
    private void initialize() {
    	roleComboBox.getItems().setAll(UserRole.values());
    }
    
    @FXML
    private void login() {
    	String userID = userIDField.getText();
        String password = passwordField.getText();
        UserRole selectedRole = roleComboBox.getValue();
        if (selectedRole != null && userID != "" && password !="") {
        	if(authenticateUser(userID,password,selectedRole)) {
        		loggedInUserID = userID;
	            if (selectedRole == UserRole.PROFESSOR) {
	                showProfessorView();
	             }
	            else if (selectedRole == UserRole.STUDENT) {
	            	 showStudentView();
	             }
        	}
        } 

    }
    
    private void showProfessorView() {
    	try {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfessorView.fxml"));
	        Parent professorViewParent = fxmlLoader.load();
	        professorViewController = fxmlLoader.getController();
	        Professor professor = getProfessor(loggedInUserID);
	        professorViewController.setProfessorViewController(this);
	        professorViewController.setProfessor(professor);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Professor View");
	        stage.setScene(new Scene(professorViewParent));
	        stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    private void showStudentView() {
    	try {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentView.fxml"));
	        Parent studentViewParent = fxmlLoader.load();
	        studentViewController = fxmlLoader.getController();
	        Student student = getStudent(loggedInUserID);
	        studentViewController.setStudentViewController(this);
	        studentViewController.setStudentInformation(student);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Student View");
	        stage.setScene(new Scene(studentViewParent));
	        stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private boolean authenticateUser(String userID, String password, UserRole role) {
        // Perform authentication using the users list
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
            	if(user.getPassword().equals(password) && user.getRole() == role) {
            		return true;
            	}
                return false;
            }
        }
        // Create a new User instance if the user does not exist.
        User user = new User(userID, password, role);
        users.add(user);
        return true;
    }
    
    
    
    
    private Student getStudent(String userID) {
    	for (Student student : students) {
    		if(student.getuserId().equals(userID)) {
    			return student;
    		}
    	}
    	return new Student("xx",userID);
    }
    
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
    
    public void addStudent(Student student) {
    	students.add(student);
    }
    
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
