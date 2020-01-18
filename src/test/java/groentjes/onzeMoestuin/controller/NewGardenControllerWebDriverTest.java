package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
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
 *  @author Eric van Dalen and Gjalt Wybenga
 *  Test class for webdriver test for adding new gardens
 */
@SpringBootTest
@WebAppConfiguration
public class NewGardenControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String NAME = "testgebruiker1";
    private static final String PASSWORD = "testwaxhtwoord1";
    private static final String NAMEGARDEN = "testtuin1";
    private static final String LENGTH = "1";
    private static final String WIDTH = "1";

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        User registeredUser = new User();
        registeredUser.setUsername(NAME);
        registeredUser.setPassword(passwordEncoder.encode(PASSWORD));
        userRepository.save(registeredUser);
        Optional<Garden> garden = gardenRepository.findByGardenName(NAMEGARDEN);
        garden.ifPresent(value -> gardenRepository.delete(value));
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> optionalGarden = gardenRepository.findByGardenName(NAMEGARDEN);
        optionalGarden.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    void testShowGarden() throws Exception {
        // Arrange
        boolean expectedFound = true;

        // Activate
        this.driver.get("http://localhost:8080/garden/add");
        loginAsAUser();
        Thread.sleep(500);
        addGarden();
        Thread.sleep(500);
        createGarden();
        Thread.sleep(500);
        driver.findElement(By.name("opslaanTuin")).submit();
        boolean actualFound = gardenRepository.findByGardenName(NAMEGARDEN).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
    }

    private void loginAsAUser() {
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();

    }

    private void addGarden() {
        driver.findElement(By.name("tuintoevoegen")).click();
    }


    private void createGarden() {
        driver.findElement(By.name("gardenName")).sendKeys(NAMEGARDEN);
        driver.findElement(By.name("length")).sendKeys(LENGTH);
        driver.findElement(By.name("width")).sendKeys(WIDTH);
    }


}
