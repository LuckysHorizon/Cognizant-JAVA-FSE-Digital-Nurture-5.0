package junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Nested Tests for UserValidator")
class NestedTestExamples {

    private UserValidator validator;

    @BeforeEach
    void setUp() {
        validator = new UserValidator();
    }

    @Nested
    @DisplayName("Email Validation")
    class EmailValidation {

        @Test
        void validEmailReturnsTrue() {
            assertTrue(validator.isValidEmail("user@domain.com"));
        }

        @Test
        void nullEmailReturnsFalse() {
            assertFalse(validator.isValidEmail(null));
        }

        @Test
        void emptyEmailReturnsFalse() {
            assertFalse(validator.isValidEmail(""));
        }
    }

    @Nested
    @DisplayName("Password Validation")
    class PasswordValidation {

        @Test
        void validPasswordReturnsTrue() {
            assertTrue(validator.isValidPassword("MyPass123"));
        }

        @Test
        void shortPasswordReturnsFalse() {
            assertFalse(validator.isValidPassword("Ab1"));
        }

        @Test
        void noUppercaseReturnsFalse() {
            assertFalse(validator.isValidPassword("mypass123"));
        }
    }

    @Nested
    @DisplayName("Age Validation")
    class AgeValidation {

        @Test
        void validAgeReturnsTrue() {
            assertTrue(validator.isValidAge(25));
        }

        @Test
        void underageReturnsFalse() {
            assertFalse(validator.isValidAge(15));
        }
    }
}
