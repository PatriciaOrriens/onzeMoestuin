package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.Before;
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
 * Test class for webdriver test of the administrator dashboard
 */
@SpringBootTest
@WebAppConfiguration
public class AdminDashboardControllerWebDriverTest {

    private User admin = new User();

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String NAME = "administrator";
    private static final String PASSWORD = "adminpassword";
    private static final String EMAIL = "testgebruiker1@test.nl";
    private static final String ROLE = "ROLE_ADMIN";

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        optionalUser = userRepository.findByEmail(EMAIL);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        admin.setUsername(NAME);
        admin.setPassword(passwordEncoder.encode(PASSWORD));
        admin.setEmail(EMAIL);
        Role role = new Role();
        role.setRoleName(ROLE);
        admin.getRole().add(role);
        userRepository.save(admin);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        optionalUser = userRepository.findByEmail(EMAIL);
        optionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    void testAdminDashboard() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminDashboard";

        // Activate
        loginAsAdministrator();
        this.driver.get(expectedUrl);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSelectManageUser() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/manageUsers";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/adminDashboard");
        Thread.sleep(500);
        driver.findElement(By.name("selectManageUser")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSelectManagePlantInformation() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminManagePlantInformation";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/adminDashboard");
        Thread.sleep(500);
        driver.findElement(By.name("selectManagePlantInformation")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSubmitLogoutButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/";

        // Activate
        loginAsAdministrator();
        this.driver.get("http://localhost:8080/adminDashboard");
        Thread.sleep(500);
        driver.findElement(By.name("logout")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    private void loginAsAdministrator() {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }
}