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
import javafx.beans.property.ReadOnlyObjectWrapper;

/*******
 * <p> Title: ProfessorViewController Class. </p>
 * 
 * <p> Description: The ProfessorViewController class is responsible for controlling the user interface
		for professor in the GradeTracker application. It provides functionalities for a professor to view
		and manage the grades of their assigned students.</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */

public class ProfessorViewController {
	@FXML
    private TableView<Student> gradeTable; // A TableView that displays the student's grades data in a tabular view.
	@FXML
    private TableColumn<Student, String> nameColumn; // A TableColumn that represents the Names of students in the gradeTable.
    @FXML
    private TableColumn<Student, String> userIDColumn; // A TableColumn that represents the IDs of students in the gradeTable.
    @FXML
    private TableColumn<Student, Map<String,Integer>> gradesColumn; // A TableColumn that represents the Grades of students in the gradeTable.
    @FXML
    private ComboBox<Student> studentComboBox; // A ComboBox to select the student while adding the grade.
	
	@FXML
	private TextField nameTextField; // A TextField to enter the name of a new student to be added.
	@FXML
	private TextField userIDTextField; // A TextField to enter the user ID of a new student to be added.
	@FXML
	private TextField gradeTextField; //  A TextField to enter the grade of a student for a particular subject.
	@FXML
	private TextField subjectTextField; // A TextField to enter the subject for which the grade is to be added.
	@FXML
	public ObservableList<Student> studentList = FXCollections.observableArrayList();
	// An ObservableList of Student objects that represents the list of students

	@FXML
	private ObservableList<SubjectEntry> subjectsList = FXCollections.observableArrayList();
	// An ObservableList of SubjectEntry objects that holds statistics related to the subjectTable
	@FXML
    private TableView<SubjectEntry> subjectTable; // A TableView that displays statistics (min, max, avg) for each subject in the subjectColumn
	@FXML
	private TableColumn<SubjectEntry, String> subjectColumn; // A TableColumn that represents the subject name in the subjectTable. 
	@FXML
	private TableColumn<SubjectEntry, Integer> minGradeColumn; // A TableColumn that represents the minimum grade for each subject in the subjectTable
	@FXML
	private TableColumn<SubjectEntry, Integer> maxGradeColumn; // A TableColumn that represents the maximum grade for each subject in the subjectTable
	@FXML
	private TableColumn<SubjectEntry, Double> avgGradeColumn; // // A TableColumn that represents the average grade for each subject in the subjectTable
	
	MainViewController mainViewController; // A reference to the MainViewController class to communicate with the main user interface
	private Professor loggedProfessor; // An instance of the Professor class that represents the currently logged-in professor
	
	
	/***
	 * This method sets the reference to the MainViewController class, allowing communication between this controller
	 * and the main user interface.
	 * @param mainViewController
	 */
	public void setProfessorViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }
	
	/***
	 * This method sets the currently logged-in professor and
	 * initializes the interface with the professor's assigned students and statistics.
	 * @param professor
	 */
	public void setProfessor(Professor professor) {
		this.loggedProfessor=professor;
		for(Student student : this.loggedProfessor.getStudents()) {
			this.studentList.add(student);
			gradeTable.getItems().add(student);
		}
		initializeStatistics();
	}
	
	
	/***
	 * This method is automatically called after the FXML file is loaded. 
	 * It sets up the cell value factories for the TableView columns and initializes the ComboBox with the available list of students.
	 */
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

	
	/**
	 * This method is called when the professor clicks the "Add Student" button.
	 * creates a new Student object with the entered name and user ID and adds it to the studentList and gradeTable.
	 */
    @FXML
    private void addStudent() {
    	String name = nameTextField.getText();
    	String userID = userIDTextField.getText();
    	for(Student student : studentList) {
    		if(userID.equals(student.getuserId())) { // If a userID already exists, we can't add another student with the same userID.
    			return; // Terminating the process here.
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
    
    
    /**
     *  This method is called when the professor clicks the "Add Grade" button.
     *  selectedStudent, subject and grade items are retrived from the student Combobox, subjectTextField and gradeTextfield respectively.
     *  It adds a new grade for the selected student and subject to the student's grades.
     *  It also calculates the statistics for all subjects and refreshes the subjectTable.
     */
    @FXML
    private void addGrade() {
    	Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
    	String subjectString = subjectTextField.getText();
    	String gradeString = gradeTextField.getText();
    	
    	if(selectedStudent != null && gradeString != "" && subjectString != "") {
    		try {
	            int grade = Integer.parseInt(gradeString);
	            if(selectedStudent.getGrades().containsKey(subjectString)) { // If the student already have a grade with this subject, update their grade.
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
    
    
    /***
     * This method initializes the subjectTable with statistics related to student grades for each subject.
     * It updates the subjectTable by retrieving the subjects from all students' grades, and 
     * calculating min, max, and average grades for each subject.
     */
    @FXML
    private void initializeStatistics() {
    	subjectTable.getItems().clear();
    	Set<String> subjects = new HashSet<String>(); // To store the updated subjects list.
    	for(Student student: studentList) {
    		for(String subject : student.getGrades().keySet()) {
    			subjects.add(subject);
    		}
    	}
    	for(String subject: subjects) {
    		// A List to Store the grades for the current iterating subject.
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

