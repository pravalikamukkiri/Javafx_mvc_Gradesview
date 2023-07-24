package application;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Student {

    private String name;
    private String userID;
    private Map<String,Integer> grades;

    public Student(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.grades = new HashMap<String,Integer>();
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuserId() {
        return userID;
    }

    public void setuserId(String userID) {
        this.userID = userID;
    }

    public Map<String,Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String,Integer> grades) {
        this.grades = grades;
    }
    
    public void addGrade(String subject, int grade) {
    	this.grades.put(subject, grade);
    }
    
    public void updateGrade(String subject, int grade) {
    	this.grades.replace(subject, grade);
    }
    
    //for displaying student names in the dropdown for selecting students to add grade
    @Override
    public String toString() {
    	return this.name;
    }
}

