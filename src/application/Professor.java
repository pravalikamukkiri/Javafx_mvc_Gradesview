package application;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Professor {
	private ArrayList<Student> students;
	private String userID;
	public Professor(String userID) {
		this.userID=userID;
		this.students= new ArrayList<Student>();
	}
	
	public String getuserId() {
		return this.userID;
	}
	
	public List<Student> getStudents(){
		return this.students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students=students;
	}
	public void addStudent(Student student) {
		this.students.add(student);
	}
}
