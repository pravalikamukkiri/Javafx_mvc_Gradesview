package application;

/*******
 * <p> Title: GradeEntry Class. </p>
 * 
 * <p> Description: The GradeEntry class represents an entry in the GradeTracker application that contains
		information about a specific subject's grade. It includes the subject name and the grade achieved by the
		student in that subject.</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public class GradeEntry {
    private String subject; // A String that represents the name of the subject for which the grade is recorded.
    private int grade; //  An int that represents the grade achieved by the student in the subject.

    /***
     *  Initializes a new GradeEntry with the given subject name and the grade achieved by the student in that subject.
     * @param subject
     * @param grade
     */
    public GradeEntry(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    // Returns the name of the subject for which the grade is recorded.
    public String getSubject() {
        return subject;
    }

    // Sets the name of the subject to the specified value.
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    // Returns the grade achieved by the student in the subject as a String value.
    public String getGrade() {
        return String.valueOf(grade);
    }

    // Sets the grade achieved by the student in the subject to the specified value.
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
