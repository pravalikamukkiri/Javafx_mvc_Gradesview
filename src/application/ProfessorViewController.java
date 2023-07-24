package application;

import javafx.fxml.FXML;
import java.util.Map;
import java.util.Set;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;

import javafx.beans.property.ReadOnlyObjectWrapper;

public class ProfessorViewController {
	@FXML
    private TableView<Student> gradeTable; //Tabular view of the student's grades data
	@FXML
    private TableColumn<Student, String> nameColumn; //This column represents the Names of students.
    @FXML
    private TableColumn<Student, String> userIDColumn; //This column represents the IDs of students.
    @FXML
    private TableColumn<Student, Map<String,Integer>> gradesColumn; //This column represents the Grades of students.
    @FXML
    private ComboBox<Student> studentComboBox; // To select the student while adding the grade.
	
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField userIDTextField;
	@FXML
	private TextField gradeTextField;
	@FXML
	private TextField subjectTextField;
	@FXML
	public ObservableList<Student> studentList = FXCollections.observableArrayList();

	@FXML
	private ObservableList<SubjectEntry> subjectsList = FXCollections.observableArrayList();
	@FXML
    private TableView<SubjectEntry> subjectTable; //Tabular view of the  grades data
	@FXML
	private TableColumn<SubjectEntry, String> subjectColumn;
	@FXML
	private TableColumn<SubjectEntry, Integer> minGradeColumn;
	@FXML
	private TableColumn<SubjectEntry, Integer> maxGradeColumn;
	@FXML
	private TableColumn<SubjectEntry, Double> avgGradeColumn;
	
	MainViewController mainViewController;
	private Professor loggedProfessor;
	
	
	public void setProfessorViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
	
	public void setProfessor(Professor professor) {
		this.loggedProfessor=professor;
		for(Student student : this.loggedProfessor.getStudents()) {
			this.studentList.add(student);
			gradeTable.getItems().add(student);
		}
		initializeStatistics();
		
	}
	
	@FXML
    private void initialize() {
		nameColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        userIDColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getuserId()));
        gradesColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGrades()));
        studentComboBox.setItems(studentList);
        
        subjectColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getSubject()));
        minGradeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getmin()));
        maxGradeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getmax()));
        avgGradeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getavg()));
        
        
       }

    @FXML
    private void addStudent() {
    	String name = nameTextField.getText();
    	String userID = userIDTextField.getText();
    	for(Student student : studentList) {
    		if(userID.equals(student.getuserId())) {
    			return;
    		}
    	}
    	Student student = new Student(name,userID); // creating new student object with given Name and ID.
    	
    	gradeTable.getItems().add(student);
    	studentList.add(student);
    	mainViewController.addStudent(student);
    	this.loggedProfessor.addStudent(student);
    	nameTextField.clear();
    	userIDTextField.clear();
    }
    @FXML
    private void addGrade() {
    	Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
    	String gradeString = gradeTextField.getText();
    	String subjectString = subjectTextField.getText();
    	if(selectedStudent != null && gradeString != "" && subjectString != "") {
    		try {
	            int grade = Integer.parseInt(gradeString);
	            if(selectedStudent.getGrades().containsKey(subjectString)) {
	            	selectedStudent.updateGrade(subjectString, grade);
	            }
	            else {
	            	selectedStudent.addGrade(subjectString,grade);
	            }
    		} catch (NumberFormatException e) {
                System.out.println("Error parsing the string to integer: " + e.getMessage());
            }
    		
            gradeTable.refresh();
    	}
    	gradeTextField.clear();
    	subjectTextField.clear();
    	initializeStatistics();
    	subjectTable.refresh();
    	  	
    }

    @FXML
    private void initializeStatistics() {
    	subjectTable.getItems().clear();
    	Set<String> subjects = new HashSet<String>();
    	for(Student student: studentList) {
    		for(String subject : student.getGrades().keySet()) {
    			subjects.add(subject);
    		}
    	}
    	for(String subject: subjects) {
    		List<Integer> gradesList = new ArrayList<Integer>();
    		for(Student student: studentList) {
        		for(String sub : student.getGrades().keySet()) {
        			if(sub.equals(subject)) {
        				gradesList.add(student.getGrades().get(subject));
        			}
        		}
        	}
    		
    		SubjectEntry subjectObject = new SubjectEntry(subject,gradesList);
    		subjectTable.getItems().add(subjectObject);
    	}
    }


}

