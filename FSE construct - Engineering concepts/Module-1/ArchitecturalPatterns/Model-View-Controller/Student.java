public class Student {

    private String name;
    private String rollNo;

    public Student(
            String name,
            String rollNo) {

        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }
}