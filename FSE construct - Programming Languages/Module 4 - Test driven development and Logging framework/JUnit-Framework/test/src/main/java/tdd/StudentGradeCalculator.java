package tdd;

import java.util.Arrays;
import java.util.List;

public class StudentGradeCalculator {

    public double calculateAverage(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("Scores list cannot be null or empty");
        }
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public String getLetterGrade(double average) {
        if (average < 0 || average > 100) {
            throw new IllegalArgumentException("Average must be between 0 and 100");
        }
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }

    public boolean isPassing(double average) {
        return average >= 60;
    }

    public int getHighestScore(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("Scores list cannot be null or empty");
        }
        return scores.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    public int getLowestScore(List<Integer> scores) {
        if (scores == null || scores.isEmpty()) {
            throw new IllegalArgumentException("Scores list cannot be null or empty");
        }
        return scores.stream().mapToInt(Integer::intValue).min().orElse(0);
    }

    public long countPassingScores(List<Integer> scores) {
        if (scores == null) {
            throw new IllegalArgumentException("Scores list cannot be null");
        }
        return scores.stream().filter(s -> s >= 60).count();
    }
}
