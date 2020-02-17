package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.RoleRepository;
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
 * Test class for webdriver test of the administrator dashboard
 */
@SpringBootTest
@WebAppConfiguration
public class AdminDashboardControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String NAME = "administrator";
    private static final String ROLE = "ROLE_ADMIN";
    private static final String PASSWORD = "adminpassword";

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();

        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));

        User registeredUser = new User();
        registeredUser.setUsername(NAME);
        registeredUser.setPassword(passwordEncoder.encode(PASSWORD));

        Role role = new Role();
        role.setRoleName(ROLE);
        registeredUser.getRole().add(role);

        userRepository.save(registeredUser);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;

        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));

        Optional<Role> optionalRole = roleRepository.findByRoleName(ROLE);
        optionalRole.ifPresent(role -> roleRepository.delete(role));
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