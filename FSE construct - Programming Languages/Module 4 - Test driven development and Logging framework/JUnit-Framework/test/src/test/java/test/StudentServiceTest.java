package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
public class StudentServiceTest {
    @Test
    public void testStudentName()
    {
        //Creating Mock object
        Database database = mock(Database.class);

        //Stubbing
        when(database.getStudentName()).thenReturn("Manikanta");
        StudentService service = new StudentService(database);
        //Executing 
        String result = service.getStudent();
        //Assertion
        assertEquals("Manikanta",result);
        //Verifying the Interaction
        verify(database).getStudentName(); 
    }
}
