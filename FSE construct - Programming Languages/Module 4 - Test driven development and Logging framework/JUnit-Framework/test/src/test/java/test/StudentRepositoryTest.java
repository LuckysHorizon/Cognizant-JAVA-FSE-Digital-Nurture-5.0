package test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
public class StudentRepositoryTest {
    @Test
    void testVoidMethod()
    {
        StudentRepository repository = mock(StudentRepository.class);
        doNothing().when(repository).saveStudent();
        repository.saveStudent();
        verify(repository,times(1)).saveStudent();
    }

}
