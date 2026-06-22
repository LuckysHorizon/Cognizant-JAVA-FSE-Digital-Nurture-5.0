public class Main {
    public static void main(String [] args)
    {
        Student student =
                new Student.StudentBuilder()
                           .setName("Manikanta")
                           .setAge(20)
                           .setBranch("CSE")
                           .setCollege("IARE")
                           .build();
        System.out.println(student);
    }
}
