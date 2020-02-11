package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Optional;

/**
 * @author Eric van Dalen
 * Test class for webdriver test for management of Users.
 */
@SpringBootTest
@WebAppConfiguration
class UserControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String ADMINNAME = "admin";
    private static final String ADMINPASSWORD = "admin";
    private static final String NAME = "testgebruiker1";
    private static final String PASSWORD = "testwachtwoord1";
    private static final String EMAIL = "testgebruiker1@test.nl";

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @AfterEach
    public void tearDown() {
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        this.driver.quit();
        this.driver = null;
    }

    @Test
    void testManageUsers() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/manageUsers";

        // Activate
        loginAsAdministrator();
        this.driver.get(expectedUrl);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersSubmitGoToAdminDashboardButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminDashboard";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/manageUsers");
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminDashboard")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersSubmitGoToAdminCreateUserButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/user/new";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/manageUsers");
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminCreateUser")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersControllerAfterCreateUser() throws Exception {
        // Arrange
        boolean expectedFound = true;
        String expectedUrl = "http://localhost:8080/manageUsers";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/manageUsers");
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminCreateUser")).click();
        Thread.sleep(500);
        createUser();
        boolean actualFound = userRepository.findByUsername(NAME).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    private void loginAsAdministrator() {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(ADMINNAME);
        driver.findElement(By.name("password")).sendKeys(ADMINPASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }

    private void createUser() {
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("createUserButton")).submit();
    }
}