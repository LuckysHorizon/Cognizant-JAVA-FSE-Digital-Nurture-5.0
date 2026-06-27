package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("@InjectMocks Tests")
class InjectMocksTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        when(userRepository.findById(1)).thenReturn("Alice");
        assertEquals("Alice", userService.getUserById(1));
        verify(userRepository).findById(1);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById(99)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> userService.getUserById(99));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList("Alice", "Bob"));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void testCreateUser() {
        when(userRepository.save("Charlie")).thenReturn(true);
        assertTrue(userService.createUser("Charlie"));
        verify(userRepository).save("Charlie");
    }

    @Test
    void testCreateUserBlankName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(""));
        verify(userRepository, never()).save(anyString());
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.deleteById(1)).thenReturn(true);
        assertTrue(userService.deleteUser(1));
        verify(userRepository).deleteById(1);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.existsById(99)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> userService.deleteUser(99));
    }
}
