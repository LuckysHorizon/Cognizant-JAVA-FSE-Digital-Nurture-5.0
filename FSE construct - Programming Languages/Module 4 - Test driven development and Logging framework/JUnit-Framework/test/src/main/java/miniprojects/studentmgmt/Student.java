package miniprojects.studentmgmt;

public class Student {
    private int id;
    private String name;
    private String course;
    private double grade;

    public Student(int id, String name, String course, double grade) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
}
