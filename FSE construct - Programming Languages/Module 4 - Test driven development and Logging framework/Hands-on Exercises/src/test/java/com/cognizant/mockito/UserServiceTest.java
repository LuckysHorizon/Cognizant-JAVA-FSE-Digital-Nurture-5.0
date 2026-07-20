package com.cognizant.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserName_MockingAndStubbing() {
        User mockUser = new User("U100", "Alice Smith");
        when(userRepository.findById("U100")).thenReturn(mockUser);

        String name = userService.getUserName("U100");

        assertEquals("Alice Smith", name);
    }

    @Test
    void testRegisterNewUser_VerifyingInteractions() {
        userService.registerNewUser("U101", "Bob Johnson");

        verify(userRepository, times(1)).save(any(User.class));
    }
}
