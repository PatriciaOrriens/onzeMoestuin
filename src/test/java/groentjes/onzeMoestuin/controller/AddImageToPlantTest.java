package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;

@SpringBootTest
@WebAppConfiguration
public class AddImageToPlantTest {

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
    private static final int THOUSAND = 1000;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(value -> gardenRepository.delete(value));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        User registeredUser = new User();
        registeredUser.setUsername(NAME);
        registeredUser.setPassword(passwordEncoder.encode(PASSWORD));
        userRepository.save(registeredUser);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    void testImageSize() throws Exception {
        // Arrange
        BufferedImage tomatoImage = ImageIO.read(new File("src/main/webapp/resources/img/tomato.jpg"));
        //byte[] expectedArray = ((DataBufferByte) tomatoImage.getData().getDataBuffer()).getData();

        // Activate
        loginAsAUser();
        Thread.sleep(2000);
        createGarden(GARDEN1, GARDEN1LENGTH, GARDEN1WIDTH);
        Thread.sleep(2000);
        driver.findElement(By.name("addplant")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("toevoegen")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("plantlink")).click();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        WebElement myWebElement = driver.findElement(By.name("image"));

        BufferedImage image = new AShot()
                .takeScreenshot(driver, myWebElement)
                .getImage();

        //byte[] actualArray = ((DataBufferByte) image.getData().getDataBuffer()).getData();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(tomatoImage, image);

        // Assert
        Assertions.assertFalse(diff.hasDiff());

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
