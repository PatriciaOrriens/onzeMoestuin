package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.RoleRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
    private RoleRepository roleRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlantInformationRepository plantInformationRepository;

    //admin variables
    private static final String ADMINNAME = "admin";
    private static final String ADMINROLE = "ROLE_ADMIN";
    private static final String ADMINPASSWORD = "admin";

    //plantinformation variables
    private static final String plantName = "tomaat";
    private static final String latinName = "tomatum";
    private static final String plantingDistance = "1";
    private static final String growtime = "1";
    private static final String cabbage = "src/main/webapp/resources/img/cabbage.jpg";

    //user variables
    private static final String REGULARNAME = "gebruiker1";
    private static final String REGULARROLE = "ROLE_USER";
    private static final String REGULARPASSWORD = "wachtwoord1";
    private static final String GARDEN1 = "tuin1";
    private static final String GARDEN1LENGTH = "1";
    private static final String GARDEN1WIDTH = "1";

    //prepare for testing
    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();

        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(value -> gardenRepository.delete(value));

        Optional<User> optionalUser = userRepository.findByUsername(REGULARNAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));

        User regularUser = new User();
        regularUser.setUsername(REGULARNAME);
        regularUser.setPassword(passwordEncoder.encode(REGULARPASSWORD));

        Role regularRole = new Role();
        regularRole.setRoleName(REGULARROLE);
        regularUser.getRole().add(regularRole);

        User adminUser = new User();
        adminUser.setUsername(ADMINNAME);
        adminUser.setPassword(passwordEncoder.encode(ADMINPASSWORD));

        Role adminRole = new Role();
        adminRole.setRoleName(ADMINROLE);
        adminUser.getRole().add(adminRole);

        userRepository.save(regularUser);
        userRepository.save(adminUser);
    }

    //clean up after testing
    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> garden1 = gardenRepository.findByGardenName(GARDEN1);
        garden1.ifPresent(garden -> gardenRepository.delete(garden));

        Optional<User> adminUser = userRepository.findByUsername(ADMINNAME);
        adminUser.ifPresent(user -> userRepository.delete(user));

        Optional<User> regularUser = userRepository.findByUsername(REGULARNAME);
        regularUser.ifPresent(user -> userRepository.delete(user));

        Optional<PlantInformation> optionalPlantInformation =
                plantInformationRepository.findByPlantName(plantName);
        optionalPlantInformation.ifPresent(plantInformation -> plantInformationRepository.delete(plantInformation));

    }

    //actual test
    @Test
    void testImageEquivalence() throws Exception {
        // Arrange
        BufferedImage cabbage = ImageIO.read(new File(AddImageToPlantTest.cabbage));

        // Activate
        loginAsAdministrator();
        administratorAddPlant();
        loginAsAUser();
        createGarden(GARDEN1, GARDEN1LENGTH, GARDEN1WIDTH);
        clickMoreInformation();
        WebElement myWebElement = driver.findElement(By.name("image"));
        driver.manage().window().maximize();

        //take a screenshot
        BufferedImage screenShot = new AShot()
                .takeScreenshot(driver, myWebElement)
                .getImage();

        //establish image difference
        ImageDiff diff = new ImageDiffer().makeDiff(cabbage, screenShot);

        // Assert
        Assertions.assertTrue(diff.hasDiff());
    }

    private void loginAsAdministrator() {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(ADMINNAME);
        driver.findElement(By.name("password")).sendKeys(ADMINPASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }

    private void administratorAddPlant() throws InterruptedException {
        //navigate to adminCreatePlantInformation form
        driver.findElement(By.name("admindashboard")).click();
        driver.findElement(By.name("selectManagePlantInformation")).click();
        driver.findElement(By.name("admincreateplantinfo")).click();

        //add variable values
        driver.findElement(By.name("plantName")).sendKeys(plantName);
        driver.findElement(By.name("latinName")).sendKeys(latinName);
        driver.findElement(By.name("plantingDistance")).sendKeys(plantingDistance);

        driver.findElement(By.name("lighting"));
        Select drpLighting = new Select(driver.findElement(By.name("lighting")));
        drpLighting.selectByVisibleText("zon");

        driver.findElement(By.name("soilType"));
        Select drpSoilType = new Select( driver.findElement(By.name("soilType")));
        drpSoilType.selectByVisibleText("standaard tuingrond");

        driver.findElement(By.name("sowingStart"));
        Select drpSowingStart = new Select( driver.findElement(By.name("sowingStart")));
        drpSowingStart.selectByVisibleText("januari");

        driver.findElement(By.name("sowingEnd"));
        Select drpSowingEnd = new Select( driver.findElement(By.name("sowingEnd")));
        drpSowingEnd.selectByVisibleText("februari");

        driver.findElement(By.name("plantingStart"));
        Select drpPlantingStart = new Select( driver.findElement(By.name("plantingStart")));
        drpPlantingStart.selectByVisibleText("maart");

        driver.findElement(By.name("plantingEnd"));
        Select drpPlantingEnd = new Select( driver.findElement(By.name("plantingEnd")));
        drpPlantingEnd.selectByVisibleText("april");

        driver.findElement(By.name("harvestingStart"));
        Select drpHarvestingStart = new Select( driver.findElement(By.name("harvestingStart")));
        drpHarvestingStart.selectByVisibleText("mei");

        driver.findElement(By.name("harvestingEnd"));
        Select drpHarvestingEnd = new Select( driver.findElement(By.name("harvestingEnd")));
        drpHarvestingEnd.selectByVisibleText("juni");
        driver.findElement(By.name("growTime")).sendKeys(growtime);

        //upload tomato image
        WebElement uploadImage = driver.findElement(By.name("file"));
        File file = new File("src/main/webapp/resources/img/tomato.jpg");
        uploadImage.sendKeys(file.getAbsolutePath());
        driver.findElement(By.name("toevoegen")).click();

        //logout
        driver.findElement(By.name("logout")).click();
    }

    private void loginAsAUser() throws InterruptedException {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(REGULARNAME);
        driver.findElement(By.name("password")).sendKeys(REGULARPASSWORD);
        driver.findElement(By.name("inlogbutton")).submit();
    }

    private void createGarden(String gardenName, String gardenLength, String gardenWidth) {
        driver.findElement(By.name("tuintoevoegen")).click();
        driver.findElement(By.name("gardenName")).sendKeys(gardenName);
        driver.findElement(By.name("length")).sendKeys(gardenLength);
        driver.findElement(By.name("width")).sendKeys(gardenWidth);
        driver.findElement(By.name("opslaanTuin")).submit();
    }

    private void clickMoreInformation() throws InterruptedException {
        driver.findElement(By.name("addplant")).click();
        driver.findElement(By.name("moreinformation")).click();
    }
}