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

public class StudentViewController {
    @FXML
    private Label userIDLabel;

    @FXML
    private Label userNameLabel;
    
    @FXML
    private TableView<GradeEntry> gradesTableView;

    @FXML
    private TableColumn<GradeEntry, String> subjectColumn;

    @FXML
    private TableColumn<GradeEntry, String> gradeColumn;
    
    @FXML
    private ComboBox<String> subjectComboBox;
    @FXML
    private Label minGradeLabel;
    @FXML
    private Label maxGradeLabel;
    @FXML
    private Label avgGradeLabel;
   

    private Student student; // Reference to the student data
    ObservableList<String> subjectItems = FXCollections.observableArrayList();
    MainViewController mainViewController ;

    public void setStudentViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void setStudentInformation(Student student) {
        this.student = student;
        userNameLabel.setText(student.getName());
        userIDLabel.setText(student.getuserId());
        System.out.println(this.student.getGrades().toString());
        populateGradesTable();
    }
    
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

