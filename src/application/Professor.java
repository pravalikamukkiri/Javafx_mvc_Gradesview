package application;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/*******
 * <p> Title: Professor Class. </p>
 * 
 * <p> Description: The Professor class represents a professor in the GradeTracker application.
 	It holds information about the professor's unique user ID and the list of students assigned to the professor.</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */

public class Professor {
	private ArrayList<Student> students; // An ArrayList of Student objects to store the students assigned to the professor.
	private String userID; // A String representing the unique user ID of the professor.
	
	/**
	 * Constructs a new Professor object with the given user ID.
	 * The constructor initializes the students list as an empty ArrayList.
	 * @param userID
	 */
	public Professor(String userID) {
		this.userID=userID;
		this.students= new ArrayList<Student>();
	}
	
	/**
	 *  This method returns the user ID of the professor.
	 * @return
	 */
	public String getuserId() {
		return this.userID;
	}
	
	/**
	 *  This method returns the list of students assigned to the professor.
	 *  The method provides access to the students list for external classes to view the students managed by the professor.
	 * @return
	 */ 
	public List<Student> getStudents(){
		return this.students;
	}
	
	/***	 
	 * This method sets the students list of the professor to the provided ArrayList of Student objects.
	 * It allows external classes to update the list of students assigned to the professor.
	 * @param students
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students=students;
	}
	
	/**
	 * This method adds a new student to the students list of the professor.
	 * It is called by external classes, such as the MainViewController, when a professor adds a new student to their class.
	*/ 
	public void addStudent(Student student) {
		this.students.add(student);
	}
}
