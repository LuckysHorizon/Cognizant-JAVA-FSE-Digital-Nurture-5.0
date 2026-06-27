package tdd;

public class StringUtility {

    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    public int countVowels(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return (int) input.toLowerCase().chars()
                .filter(c -> "aeiou".indexOf(c) >= 0)
                .count();
    }

    public String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public String removeWhitespace(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return input.replaceAll("\\s+", "");
    }
}
