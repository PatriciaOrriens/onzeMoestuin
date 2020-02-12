package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  @author Gjalt Wybenga and Eric van Dalen
 *  Code based on NewGardenControllerWebDriverTest
 *  Tests navigation to addGarden (implicitly), tests garden addition, tests the removal of a garden
 */

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class GardenControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String NAME = "gebruiker1";
    private static final String EMAIL = "testgebruiker1@test.nl";
    private static final String PASSWORD = "wachtwoord1";
    private static final String GARDEN1 = "tuin1";
    private static final String GARDEN1LENGTH = "1";
    private static final String GARDEN1WIDTH = "1";
    private static final String GARDEN2 = "tuin2";
    private static final String GARDEN2LENGTH = "2";
    private static final String GARDEN2WIDTH = "2";
    private static final int THOUSAND = 1000;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(value -> gardenRepository.delete(value));
        Optional<Garden> garden2 = gardenRepository.findByGardenName(GARDEN2);
        garden2.ifPresent(value -> gardenRepository.delete(value));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        User registeredUser = new User();
        registeredUser.setUsername(NAME);
        registeredUser.setPassword(passwordEncoder.encode(PASSWORD));
        registeredUser.setEmail(EMAIL);
        userRepository.save(registeredUser);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<Garden> garden2 = gardenRepository.findByGardenName(GARDEN2);
        garden2.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    void testAddGarden() throws Exception {
        // Arrange
        boolean expectedFound = true;

        // Activate
        loginAsAUser();
        createGarden(GARDEN1, GARDEN1LENGTH, GARDEN1WIDTH);
        createGarden(GARDEN2, GARDEN2LENGTH, GARDEN2WIDTH);
        driver.findElement(By.name("logout")).click();

        // Assert
        Assertions.assertAll("test whether both gardens were successfully created",
                () -> assertEquals(expectedFound, gardenRepository.findByGardenName(GARDEN1).isPresent()),
                () -> assertEquals(expectedFound, gardenRepository.findByGardenName(GARDEN2).isPresent()));
    }

    @Test
    void testRemoveGarden() throws Exception {
        // Arrange

        // Arrange code replaced with assertTrue/assertFalse statement in Assert

        // Activate
        loginAsAUser();
        createGarden(GARDEN1, GARDEN1LENGTH, GARDEN1WIDTH);
        createGarden(GARDEN2, GARDEN2LENGTH, GARDEN2WIDTH);
        driver.findElement(By.name("returntooverview")).click();
        driver.findElement(By.name("verwijderen")).click();
        Thread.sleep(THOUSAND);
        driver.findElement(By.name("modal-verwijderen")).click();
        Thread.sleep(THOUSAND);
        driver.findElement(By.name("logout")).click();

        // Assert
        Assertions.assertAll("test whether garden1 was successfully removed",
                () -> assertFalse(gardenRepository.findByGardenName(GARDEN1).isPresent()),
                () -> assertTrue(gardenRepository.findByGardenName(GARDEN2).isPresent()));
    }

    private void loginAsAUser() {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }

    private void createGarden(String gardenName, String gardenLength, String gardenWidth) {
        driver.findElement(By.name("tuintoevoegen")).click();
        driver.findElement(By.name("gardenName")).sendKeys(gardenName);
        driver.findElement(By.name("length")).sendKeys(gardenLength);
        driver.findElement(By.name("width")).sendKeys(gardenWidth);
        driver.findElement(By.name("opslaanTuin")).submit();
    }
}