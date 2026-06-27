package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD String Utility Tests")
class StringUtilityTest {

    private StringUtility stringUtility;

    @BeforeEach
    void setUp() {
        stringUtility = new StringUtility();
    }

    @Test
    void testReverse() {
        assertEquals("olleH", stringUtility.reverse("Hello"));
    }

    @Test
    void testReverseEmptyString() {
        assertEquals("", stringUtility.reverse(""));
    }

    @Test
    void testReverseNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> stringUtility.reverse(null));
    }

    @Test
    void testIsPalindrome() {
        assertTrue(stringUtility.isPalindrome("madam"));
    }

    @Test
    void testIsPalindromeWithSpaces() {
        assertTrue(stringUtility.isPalindrome("race car"));
    }

    @Test
    void testIsNotPalindrome() {
        assertFalse(stringUtility.isPalindrome("hello"));
    }

    @Test
    void testCountVowels() {
        assertEquals(2, stringUtility.countVowels("Hello"));
    }

    @Test
    void testCountVowelsNoVowels() {
        assertEquals(0, stringUtility.countVowels("xyz"));
    }

    @Test
    void testCapitalize() {
        assertEquals("Hello", stringUtility.capitalize("hello"));
    }

    @Test
    void testCapitalizeEmpty() {
        assertEquals("", stringUtility.capitalize(""));
    }

    @Test
    void testRemoveWhitespace() {
        assertEquals("HelloWorld", stringUtility.removeWhitespace("Hello World"));
    }
}
