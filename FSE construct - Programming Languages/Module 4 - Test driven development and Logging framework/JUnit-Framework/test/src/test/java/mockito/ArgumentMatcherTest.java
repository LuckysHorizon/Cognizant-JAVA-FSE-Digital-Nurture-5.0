package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Argument Matchers")
class ArgumentMatcherTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testAnyInt() {
        when(userRepository.findById(anyInt())).thenReturn("MockedUser");
        assertEquals("MockedUser", userRepository.findById(42));
        assertEquals("MockedUser", userRepository.findById(999));
    }

    @Test
    void testAnyString() {
        when(productRepository.findByName(anyString())).thenReturn("MockedProduct");
        assertEquals("MockedProduct", productRepository.findByName("Laptop"));
        assertEquals("MockedProduct", productRepository.findByName("Phone"));
    }

    @Test
    void testEq() {
        when(userRepository.findById(eq(5))).thenReturn("SpecificUser");
        assertEquals("SpecificUser", userRepository.findById(5));
    }

    @Test
    void testArgThat() {
        when(userRepository.findById(intThat(id -> id > 0))).thenReturn("ValidUser");
        assertEquals("ValidUser", userRepository.findById(10));
    }

    @Test
    void testAnyWithCategory() {
        when(productRepository.findByCategory(any())).thenReturn(Arrays.asList("Product1", "Product2"));
        assertEquals(2, productRepository.findByCategory("Electronics").size());
    }

    @Test
    void testVerifyWithMatchers() {
        when(userRepository.save(anyString())).thenReturn(true);
        userRepository.save("Alice");
        userRepository.save("Bob");
        verify(userRepository, times(2)).save(anyString());
    }
}
