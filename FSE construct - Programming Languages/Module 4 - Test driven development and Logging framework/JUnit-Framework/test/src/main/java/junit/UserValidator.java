package junit;

import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 20;

    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidPassword(String password) {
        if (password == null) return false;
        if (password.length() < MIN_PASSWORD_LENGTH) return false;
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        return hasUpper && hasLower && hasDigit;
    }

    public boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.length() >= MIN_USERNAME_LENGTH && username.length() <= MAX_USERNAME_LENGTH;
    }

    public boolean isValidAge(int age) {
        return age >= 18 && age <= 120;
    }

    public String getAgeCategory(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        if (age < 13) return "Child";
        if (age < 18) return "Teenager";
        if (age < 60) return "Adult";
        return "Senior";
    }
}
