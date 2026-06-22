public class Main {

    public static void main(String[] args) {

        Student student =
                new Student(
                        "Manikanta",
                        "23951A054Z");

        StudentView view =
                new StudentView();

        StudentController controller =
                new StudentController(
                        student,
                        view);

        controller.updateView();
    }
}