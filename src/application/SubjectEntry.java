package application;

import java.util.Collections;
import java.util.List;

/*******
 * <p> Title: SubjectEntry Class. </p>
 * 
 * <p> Description: The SubjectEntry class represents an entry in the GradeTracker application that contains
		information about a specific subject's grades. It includes the subject name, a list of grades associated
		with the subject, and statistics such as the minimum, maximum, and average grades for that subject.</p>
 * 
 * <p> Copyright: Pravalika Mukkiri © 2023 </p>
 * 
 * @author Pravalika Mukkiri
 * 
 * @version 1.00	2023-08-01 The JavaFX-based GradeTracker
 * 
 */
public class SubjectEntry {
	private String subject; // A String that represents the name of the subject.
    private List<Integer> grades; // A List of Integers that stores the grades associated with the subject.
    private int min; // An int that represents the minimum grade among all the grades for the subject.
    private int max; //  An int that represents the maximum grade among all the grades for the subject.
    private double avg; // A double that represents the average grade among all the grades for the subject.

    /**
     * Initializes a new SubjectEntry with the given subject name and a list of grades.
     * Upon initialization, the minimum, maximum, and average grades for the subject are calculated and 
     * stored in the min, max, and avg fields, respectively.
     * @param subject
     * @param grades
     */
    public SubjectEntry(String subject, List<Integer> grades) {
        this.subject = subject;
        this.grades = grades;
        this.min= Collections.min(grades);
        this.max=Collections.max(grades);
        this.avg=grades.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);;
    }

    // Returns the name of the subject.
    public String getSubject() {
        return subject;
    }
    
    // Sets the name of the subject to the specified value.
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    // Sets the list of grades associated with the subject to the specified value.
    public void setGrades(List<Integer> grades) {
        this.grades=grades;
    }
    
    // Returns the minimum grade among all the grades for the subject.
    public int getmin() {
    	return this.min;
    }
    
    // Returns the maximum grade among all the grades for the subject.
    public int getmax() {
    	return this.max;
    }
    
    //  Returns the average grade among all the grades for the subject.
    public double getavg() {
    	return this.avg;
    }
    
    // Returns the list of grades associated with the subject.
    public List<Integer> getGrades(){
    	return this.grades;
    }

    

}
