package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import java.util.Collections;

/*******
 * <p> Title: StudentViewController Class. </p>
 * 
 * <p> Description: The StudentViewController class is responsible for controlling the user interface
		for student in the GradeTracker application. It provides functionalities for a student to view
		the grades of their assigned subjects and respective statistics.</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public class StudentViewController {
    @FXML
    private Label userIDLabel; // A Label that displays the user ID of the student.

    @FXML
    private Label userNameLabel; // A Label that displays the name of the student.
    
    @FXML
    private TableView<GradeEntry> gradesTableView; // A TableView that displays the student's grades for different subjects.

    @FXML
    private TableColumn<GradeEntry, String> subjectColumn; // A TableColumn in the gradesTableView that represents the subject name.

    @FXML
    private TableColumn<GradeEntry, String> gradeColumn; // A TableColumn in the gradesTableView that represents the grade for the subject.
    
    @FXML
    private ComboBox<String> subjectComboBox; // A ComboBox that allows the user to select a subject to view statistics.
    @FXML
    private Label minGradeLabel; // A Label that displays the minimum grade for the selected subject.
    @FXML
    private Label maxGradeLabel; // A Label that displays the maximum grade for the selected subject.
    @FXML
    private Label avgGradeLabel; // A Label that displays the average grade for the selected subject.
   

    private Student student; // A reference to the Student object that contains the student's data, including name, user ID and grades.
    ObservableList<String> subjectItems = FXCollections.observableArrayList(); //  An ObservableList that stores the list of subjects for the subjectComboBox.
    MainViewController mainViewController ; // A reference to the MainViewController object.

    /***
	 * This method sets the reference to the MainViewController class, allowing communication between this controller
	 * and the main user interface.
	 * @param mainViewController
	 */
    public void setStudentViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    /***
     * Initializes the StudentViewController with the data of the selected student.
     * It sets the student's name and user ID to be displayed and populates the gradesTableView 
     * with the student's grades for different subjects.
     * @param student
     */
    public void setStudentInformation(Student student) {
        this.student = student;
        userNameLabel.setText(student.getName());
        userIDLabel.setText(student.getuserId());
        populateGradesTable();
    }
    
    /****
     * Populates the gradesTableView with data from the student's grades.
     * It creates GradeEntry objects to represent each subject and grade pair and adds them to the gradesTableView.
     * It also populates the subjectComboBox with the list of subjects for the student.
     */
    private void populateGradesTable() {
        List<GradeEntry> gradeEntries = new ArrayList<>();
        for (String subject : this.student.getGrades().keySet()) {
            int grade = this.student.getGrades().get(subject);
            gradeEntries.add(new GradeEntry(subject, grade));
            this.subjectItems.add(subject);
        }
        subjectComboBox.setItems(this.subjectItems);
        gradesTableView.getItems().setAll(gradeEntries);
        subjectColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getSubject()));
        gradeColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGrade()));
    }
    
    /***
     * Calculates and displays the statistics for the selected subject in the subjectComboBox.
     * It retrieves the grades for the selected subject from the mainViewController using
     * the getsubjectGrades() method and then calculates the minimum, maximum, and average grades for the subject.
     *  The results are displayed in the minGradeLabel, maxGradeLabel, and avgGradeLabel, respectively.
     */
    @FXML
    private void calculateStatistics() {
    	String selectedSubject = subjectComboBox.getSelectionModel().getSelectedItem();
    	System.out.println(selectedSubject);
    	if(selectedSubject != "") {
	    	List<Integer> selectedSubjectGrades = mainViewController.getsubjectGrades(selectedSubject);
	    	int min = Collections.min(selectedSubjectGrades);
	    	int max = Collections.max(selectedSubjectGrades);
	    	double avg = selectedSubjectGrades.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
	    	minGradeLabel.setText(""+min);
	    	maxGradeLabel.setText(""+max);
	    	avgGradeLabel.setText(""+avg);
    	}
    }
}

