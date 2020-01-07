package groentjes.onzeMoestuin.controller;

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
    private PasswordEncoder passwordEncoder;

    private static final String NAME = "admin";
    private static final String PASSWORD = "admin";

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
    }


    @Test
    void testAdminDashboard() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminDashboard";

        // Activate
        this.driver.get(expectedUrl);
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("form-signin")).submit();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSelectManageUser() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/manageUsers";

        // Activate
        this.driver.get("http://localhost:8080/adminDashboard");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("form-signin")).submit();
        Thread.sleep(500);
        driver.findElement(By.name("selectManageUser")).click();
        Thread.sleep(2500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSelectManagePlantInformation() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/adminManagePlantInformation";

        // Activate
        this.driver.get("http://localhost:8080/adminDashboard");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("form-signin")).submit();
        Thread.sleep(500);
        driver.findElement(By.name("selectManagePlantInformation")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    void testAdminDashboardSubmitLogoutButton() throws Exception {
        // Arrange
        String expectedUrl = "http://localhost:8080/logout";

        // Activate
        this.driver.get("http://localhost:8080/adminDashboard");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("form-signin")).submit();
        Thread.sleep(500);
        driver.findElement(By.name("adminLogoutButton")).click();
        Thread.sleep(500);

        // Assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}