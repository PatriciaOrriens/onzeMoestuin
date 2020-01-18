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
import org.springframework.boot.test.context.assertj.AssertableReactiveWebApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private static final String NAME = "gebruiker1";
    private static final String PASSWORD = "wachtwoord1";
    private static final String GARDEN1 = "tuin1";
    private static final String GARDEN1LENGTH = "1";
    private static final String GARDEN1WIDTH = "1";
    private static final String GARDEN2 = "tuin2";
    private static final String GARDEN2LENGTH = "2";
    private static final String GARDEN2WIDTH = "2";

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
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(value -> gardenRepository.delete(value));
        Optional<Garden> garden2 = gardenRepository.findByGardenName(GARDEN2);
        garden2.ifPresent(value -> gardenRepository.delete(value));
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> optionalGarden1 = gardenRepository.findByGardenName(GARDEN1);
        optionalGarden1.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<Garden> optionalGarden2 = gardenRepository.findByGardenName(GARDEN1);
        optionalGarden2.ifPresent(garden -> gardenRepository.delete(garden));

    }

    @Test
    void testShowGarden() throws Exception {
        // Arrange
        boolean expectedFound = true;


        // Activate
        this.driver.get("http://localhost:8080");
        Thread.sleep(1000);
        clickLoginButton();
        Thread.sleep(1000);
        loginAsAUser();
        Thread.sleep(1000);
        clickAddGardenButton();
        Thread.sleep(1000);
        createGarden1();
        Thread.sleep(1000);
        submitGardenData();
        Thread.sleep(1000);
        clickReturnToOverViewButton();
        Thread.sleep(1000);
        clickAddGardenButton();
        Thread.sleep(1000);
        createGarden2();
        Thread.sleep(1000);
        submitGardenData();
        Thread.sleep(1000);

        boolean garden1 = gardenRepository.findByGardenName(GARDEN1).isPresent();
        boolean garden2 = gardenRepository.findByGardenName(GARDEN2).isPresent();

        // Assert

        Assertions.assertAll("should return true twice",
                () -> assertEquals(true, gardenRepository.findByGardenName(GARDEN1).isPresent()),
                () -> assertEquals(true, gardenRepository.findByGardenName(GARDEN1).isPresent())
        );
    }

    private void loginAsAUser() {
        driver.findElement(By.name("username")).sendKeys(NAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }

    private void clickLoginButton() {
        driver.findElement(By.name("login")).click();
    }

    private void clickAddGardenButton() {
        driver.findElement(By.name("tuintoevoegen")).click();
    }

    private void submitGardenData() {
        driver.findElement(By.name("opslaanTuin")).submit();
    }

    private void clickReturnToOverViewButton() {
        driver.findElement(By.name("returntooverview")).click();
    }

    private void createGarden1() {
        driver.findElement(By.name("gardenName")).sendKeys(GARDEN1);
        driver.findElement(By.name("length")).sendKeys(GARDEN1LENGTH);
        driver.findElement(By.name("width")).sendKeys(GARDEN1WIDTH);
    }

    private void createGarden2() {
        driver.findElement(By.name("gardenName")).sendKeys(GARDEN2);
        driver.findElement(By.name("length")).sendKeys(GARDEN2LENGTH);
        driver.findElement(By.name("width")).sendKeys(GARDEN2WIDTH);
    }


}
