package application;

import java.util.Map;
import java.util.HashMap;

/*******
 * <p> Title: Student Class. </p>
 * 
 * <p> Description: The Student class represents a student in the GradeTracker application.
 	It holds information about a student, such as their name, user ID, and grades for different subjects. .</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public class Student {

    private String name; // A String that represents the name of the student.
    private String userID; // A String that represents the unique user ID of the student.
    private Map<String,Integer> grades; // A Map that stores the grades of the student for each subject (Key: Subject name, Value: Grade).

    public Student(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.grades = new HashMap<String,Integer>();
    }

    // Getters and setters
    
    // Returns the name of the student.
    public String getName() {
        return name;
    }
    
    // Sets the name of the student.
    public void setName(String name) {
        this.name = name;
    }
    
    // Returns the user ID of the student.
    public String getuserId() {
        return userID;
    }
    
    // Sets the user ID of the student.
    public void setuserId(String userID) {
        this.userID = userID;
    }
    
    // Returns the Map of grades for the student, where each entry represents a subject and its corresponding grades.
    public Map<String,Integer> getGrades() {
        return grades;
    }
    
    // Sets the Map of grades for the student. It can be used to replace the existing grades with a new set of grades
    public void setGrades(Map<String,Integer> grades) {
        this.grades = grades;
    }
    
    // Adds a new grade for a specific subject to the student's grade Map.
    public void addGrade(String subject, int grade) {
    	this.grades.put(subject, grade);
    }
    
    // Updates an existing grade for a specific subject in the student's grades Map.
    public void updateGrade(String subject, int grade) {
    	this.grades.replace(subject, grade);
    }
    
    // Overrides the toString() method to return the name of the student.
    // This is useful for displaying student names in the dropdown for selecting students to add grade
    @Override
    public String toString() {
    	return this.name;
    }
}

