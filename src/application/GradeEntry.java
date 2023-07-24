package application;

public class GradeEntry {
    private String subject;
    private int grade;

    public GradeEntry(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return String.valueOf(grade);
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
