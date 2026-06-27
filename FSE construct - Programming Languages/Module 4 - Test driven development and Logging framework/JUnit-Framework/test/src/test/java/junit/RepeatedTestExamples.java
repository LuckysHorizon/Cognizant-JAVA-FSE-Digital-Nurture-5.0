package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repeated Tests")
class RepeatedTestExamples {

    private final MathService mathService = new MathService();

    @RepeatedTest(value = 5, name = "Addition test - repetition {currentRepetition}/{totalRepetitions}")
    void testAdditionRepeated() {
        assertEquals(10, mathService.add(5, 5));
    }

    @RepeatedTest(3)
    void testWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        int current = repetitionInfo.getCurrentRepetition();
        int total = repetitionInfo.getTotalRepetitions();
        assertTrue(current <= total);
    }
}
