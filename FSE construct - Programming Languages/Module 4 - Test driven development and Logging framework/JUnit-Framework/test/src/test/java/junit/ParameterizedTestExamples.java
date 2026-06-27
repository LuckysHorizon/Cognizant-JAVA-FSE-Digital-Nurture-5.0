package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parameterized Tests")
class ParameterizedTestExamples {

    private final MathService mathService = new MathService();
    private final UserValidator validator = new UserValidator();

    @ParameterizedTest(name = "{0} is even")
    @ValueSource(ints = {2, 4, 6, 8, 10, 100})
    void testEvenNumbers(int number) {
        assertTrue(mathService.isEven(number));
    }

    @ParameterizedTest(name = "{0} is odd")
    @ValueSource(ints = {1, 3, 5, 7, 9, 99})
    void testOddNumbers(int number) {
        assertFalse(mathService.isEven(number));
    }

    @ParameterizedTest(name = "{0} is a valid email")
    @ValueSource(strings = {"test@example.com", "user@domain.org", "admin@company.co"})
    void testValidEmails(String email) {
        assertTrue(validator.isValidEmail(email));
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 2, 3",
            "5, 5, 10",
            "-1, 1, 0",
            "100, 200, 300"
    })
    void testAdditionWithCsv(int a, int b, int expected) {
        assertEquals(expected, mathService.add(a, b));
    }

    @ParameterizedTest(name = "Age {0} -> Category {1}")
    @CsvSource({
            "5, Child",
            "15, Teenager",
            "30, Adult",
            "70, Senior"
    })
    void testAgeCategoryWithCsv(int age, String expectedCategory) {
        assertEquals(expectedCategory, validator.getAgeCategory(age));
    }

    @ParameterizedTest(name = "factorial({0}) = {1}")
    @MethodSource("factorialProvider")
    void testFactorialWithMethodSource(int input, int expected) {
        assertEquals(expected, mathService.factorial(input));
    }

    static Stream<Arguments> factorialProvider() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(5, 120),
                Arguments.of(6, 720)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testInvalidEmailsNullAndEmpty(String email) {
        assertFalse(validator.isValidEmail(email));
    }
}
