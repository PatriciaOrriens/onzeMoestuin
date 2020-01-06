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
 * Test class for webdriver test for management of Users (and later also for the gardens)
 */
@SpringBootTest
@WebAppConfiguration
class AdminManageUsersAndGardensControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String ADMINNAME = "admin";
    private static final String ADMINPASSWORD = "admin";
    private static final String NAME = "testgebruiker1";
    private static final String PASSWORD = "testwaxhtwoord1";

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
    void testManageUsersAndGardens() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminManageUsersAndGardens";

        // Activate
        this.driver.get(expectedUrl);
        loginAsAdministrator();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersAndGardensSubmitGoToAdminDashboardButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminDashboard";

        // Activate
        this.driver.get("http://localhost:8080/adminManageUsersAndGardens");
        loginAsAdministrator();
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminDashboard")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersAndGardensSubmitGoToAdminCreateUserButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/user/new";

        // Activate
        this.driver.get("http://localhost:8080/adminManageUsersAndGardens");
        loginAsAdministrator();
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminCreateUser")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminManageUsersAndGardensControllerAfterCreateUser() throws Exception {
        // Arrange
        boolean expectedFound = true;
        String expectedUrl = "http://localhost:8080/adminManageUsersAndGardens";

        // Activate
        this.driver.get("http://localhost:8080/adminManageUsersAndGardens");
        loginAsAdministrator();
        Thread.sleep(500);
        driver.findElement(By.name("buttonGoToAdminCreateUser")).click();
        Thread.sleep(500);
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("createUserButton")).submit();
        boolean actualFound = userRepository.findByUsername(NAME).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    private void loginAsAdministrator() {
        driver.findElement(By.name("username")).sendKeys(ADMINNAME);
        driver.findElement(By.name("password")).sendKeys(ADMINPASSWORD);
        driver.findElement(By.className("form-signin")).submit();
    }
}