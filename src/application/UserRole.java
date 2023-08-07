package application;

/*******
 * <p> Title: UserRole Class. </p>
 * 
 * <p> Description: The UserRole enumeration defines the two roles that a user can have in the GradeTracker
		application - Professor and Student..</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public enum UserRole {
	//  Represents the role of a professor in the system. Professors have privileges to view and
	// manage student data, assign grades, and generate statistics.
	PROFESSOR,
	
	// Represents the role of a student in the system. Students have access to view their own grade
	// information, but they cannot modify or view other students' data.
	STUDENT

}
