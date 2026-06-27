package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD Student Grade Calculator Tests")
class StudentGradeCalculatorTest {

    private StudentGradeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StudentGradeCalculator();
    }

    @Test
    void testCalculateAverage() {
        assertEquals(80.0, calculator.calculateAverage(Arrays.asList(70, 80, 90)));
    }

    @Test
    void testCalculateAverageEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateAverage(Collections.emptyList()));
    }

    @Test
    void testGetLetterGradeA() {
        assertEquals("A", calculator.getLetterGrade(95));
    }

    @Test
    void testGetLetterGradeB() {
        assertEquals("B", calculator.getLetterGrade(85));
    }

    @Test
    void testGetLetterGradeF() {
        assertEquals("F", calculator.getLetterGrade(50));
    }

    @Test
    void testIsPassing() {
        assertTrue(calculator.isPassing(75));
    }

    @Test
    void testIsNotPassing() {
        assertFalse(calculator.isPassing(55));
    }

    @Test
    void testHighestScore() {
        assertEquals(95, calculator.getHighestScore(Arrays.asList(70, 95, 80)));
    }

    @Test
    void testLowestScore() {
        assertEquals(70, calculator.getLowestScore(Arrays.asList(70, 95, 80)));
    }

    @Test
    void testCountPassingScores() {
        assertEquals(2, calculator.countPassingScores(Arrays.asList(50, 70, 80)));
    }
}
