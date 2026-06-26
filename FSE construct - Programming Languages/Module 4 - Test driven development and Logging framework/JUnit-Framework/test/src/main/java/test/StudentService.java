package test;

public class StudentService {
    private Database database;
    public StudentService(Database database)
    {
        this.database = database;
    }
    public String getStudent()
    {
        return database.getStudentName();
    }
}
