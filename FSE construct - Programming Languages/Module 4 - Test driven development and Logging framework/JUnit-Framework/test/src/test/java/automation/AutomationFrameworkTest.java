package automation;

import automation.config.FrameworkConfig;
import automation.data.TestData;
import automation.framework.DataDrivenExample;
import automation.framework.HybridFrameworkExample;
import automation.framework.KeywordDrivenExample;
import automation.pages.LoginPage;
import automation.utils.TestUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Automation Framework Tests")
class AutomationFrameworkTest {

    @Nested
    @DisplayName("Framework Configuration")
    class ConfigTest {

        @Test
        void testDefaultBrowser() {
            assertNotNull(FrameworkConfig.getBrowser());
        }

        @Test
        void testDefaultTimeout() {
            assertTrue(FrameworkConfig.getTimeout() > 0);
        }

        @Test
        void testDefaultBaseUrl() {
            assertNotNull(FrameworkConfig.getBaseUrl());
        }
    }

    @Nested
    @DisplayName("Test Data")
    class TestDataTest {

        @Test
        void testValidLoginData() {
            Map<String, String> data = TestData.getTestData("validLogin");
            assertEquals("admin", data.get("username"));
        }

        @Test
        void testInvalidScenario() {
            Map<String, String> data = TestData.getTestData("nonExistent");
            assertTrue(data.isEmpty());
        }
    }

    @Nested
    @DisplayName("Data Driven Testing")
    class DataDrivenTest {

        private final DataDrivenExample dataDriven = new DataDrivenExample();

        @Test
        void testValidLogin() {
            assertEquals("Login Successful", dataDriven.executeLoginTest("validLogin"));
        }

        @Test
        void testInvalidLogin() {
            assertEquals("Invalid Credentials", dataDriven.executeLoginTest("invalidLogin"));
        }
    }

    @Nested
    @DisplayName("Keyword Driven Testing")
    class KeywordDrivenTest {

        private final KeywordDrivenExample keywordEngine = new KeywordDrivenExample();

        @Test
        void testOpenBrowser() {
            String result = keywordEngine.executeKeyword("OPEN_BROWSER", "chrome");
            assertTrue(result.contains("Browser opened"));
        }

        @Test
        void testUnknownKeyword() {
            String result = keywordEngine.executeKeyword("INVALID");
            assertTrue(result.contains("Unknown keyword"));
        }
    }

    @Nested
    @DisplayName("Hybrid Framework Testing")
    class HybridTest {

        @Test
        void testHybridScenario() {
            HybridFrameworkExample hybrid = new HybridFrameworkExample();
            List<String> results = hybrid.executeTestScenario("validLogin");
            assertEquals(7, results.size());
            assertTrue(results.get(0).contains("Browser opened"));
        }
    }

    @Nested
    @DisplayName("Page Object Model")
    class PageObjectTest {

        @Test
        void testLoginPageSuccess() {
            LoginPage loginPage = new LoginPage();
            loginPage.enterUsername("admin");
            loginPage.enterPassword("admin123");
            assertEquals("Login Successful", loginPage.clickLogin());
            assertTrue(loginPage.isLoggedIn());
        }

        @Test
        void testLoginPageFailure() {
            LoginPage loginPage = new LoginPage();
            loginPage.enterUsername("wrong");
            loginPage.enterPassword("wrong");
            assertEquals("Invalid Credentials", loginPage.clickLogin());
            assertFalse(loginPage.isLoggedIn());
        }
    }

    @Nested
    @DisplayName("Test Utilities")
    class UtilityTest {

        @Test
        void testGenerateTimestamp() {
            assertNotNull(TestUtility.generateTimestamp());
        }

        @Test
        void testGenerateRandomEmail() {
            String email = TestUtility.generateRandomEmail();
            assertTrue(email.contains("@test.com"));
        }

        @Test
        void testGenerateRandomString() {
            String result = TestUtility.generateRandomString(10);
            assertEquals(10, result.length());
        }
    }
}
