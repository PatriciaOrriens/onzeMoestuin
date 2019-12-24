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
 * Test class for webdriver test to register a new user
 */
@SpringBootTest
@WebAppConfiguration
public class RegisterControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    void testRegisterControllerBeforeSubmit() throws Exception {
        // Arrange
        String expectedUsername = NAME;
        String expectedPassword = PASSWORD;

        // Activate
        this.driver.get("http://localhost:8080/registerUser");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        String actualName = driver.findElement(By.name("username")).getAttribute("value");
        String actualPassword = driver.findElement(By.name("password")).getAttribute("value");

        // Assert
        Assertions.assertEquals(expectedUsername, actualName);
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testRegisterControllerAfterSubmit() throws Exception {
        // Arrange
        boolean expectedFound = true;

        // Activate
        this.driver.get("http://localhost:8080/registerUser");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.className("registerbutton")).submit();
        boolean actualFound = userRepository.findByUsername(NAME).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
        Assertions.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/login");
    }
}