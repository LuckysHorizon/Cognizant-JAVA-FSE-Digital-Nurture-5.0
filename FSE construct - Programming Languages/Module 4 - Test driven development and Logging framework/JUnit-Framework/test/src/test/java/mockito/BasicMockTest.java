package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Basic Mockito: mock, when, thenReturn, verify, times, never")
class BasicMockTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testMockAndWhenThenReturn() {
        when(userRepository.findById(1)).thenReturn("Alice");
        assertEquals("Alice", userRepository.findById(1));
    }

    @Test
    void testVerifyCalled() {
        when(userRepository.findById(1)).thenReturn("Alice");
        userRepository.findById(1);
        verify(userRepository).findById(1);
    }

    @Test
    void testVerifyTimes() {
        when(userRepository.findById(1)).thenReturn("Alice");
        userRepository.findById(1);
        userRepository.findById(1);
        userRepository.findById(1);
        verify(userRepository, times(3)).findById(1);
    }

    @Test
    void testVerifyNever() {
        verify(userRepository, never()).findById(2);
    }

    @Test
    void testMockReturnsList() {
        List<String> users = Arrays.asList("Alice", "Bob", "Charlie");
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(3, userRepository.findAll().size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testMockReturnsBoolean() {
        when(userRepository.save("NewUser")).thenReturn(true);
        assertTrue(userRepository.save("NewUser"));
    }

    @Test
    void testVerifyNoMoreInteractions() {
        when(userRepository.findById(1)).thenReturn("Alice");
        userRepository.findById(1);
        verify(userRepository).findById(1);
        verifyNoMoreInteractions(userRepository);
    }
}
