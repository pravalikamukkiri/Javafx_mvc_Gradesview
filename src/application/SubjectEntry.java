package application;

import java.util.Collections;
import java.util.List;

public class SubjectEntry {
	private String subject;
    private List<Integer> grades;
    private int min;
    private int max;
    private double avg;

    public SubjectEntry(String subject, List<Integer> grades) {
        this.subject = subject;
        this.grades = grades;
        this.min= Collections.min(grades);
        this.max=Collections.max(grades);
        this.avg=grades.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setGrades(List<Integer> grades) {
        this.grades=grades;
    }
    public int getmin() {
    	return this.min;
    }
    public int getmax() {
    	return this.max;
    }
    public double getavg() {
    	return this.avg;
    }
    public List<Integer> getGrades(){
    	return this.grades;
    }

    

}
