package automation.pages;

public class LoginPage {

    private String usernameField;
    private String passwordField;
    private String loginResult;

    public void enterUsername(String username) {
        this.usernameField = username;
    }

    public void enterPassword(String password) {
        this.passwordField = password;
    }

    public String clickLogin() {
        if ("admin".equals(usernameField) && "admin123".equals(passwordField)) {
            loginResult = "Login Successful";
        } else {
            loginResult = "Invalid Credentials";
        }
        return loginResult;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public boolean isLoggedIn() {
        return "Login Successful".equals(loginResult);
    }
}
