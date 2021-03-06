package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Role;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
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
 *  @author Eric van Dalen
 *  Test class for webdriver test for deleting gardens
 */
@SpringBootTest
@WebAppConfiguration
public class ManageGardenControllerWebDriverTest {

    private WebDriver driver;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private static final Integer THREE = 3;
    private static final String NAME = "testgebruiker1";
    private static final String ROLE = "ROLE_USER";
    private static final String PASSWORD = "testwachtwoord1";
    private static final String SECONDNAME = "testgebruiker2";
    private static final String SECONDPASSWORD = "testwachtwoord2";
    private static final String NAMEGARDEN = "testtuin1";
    private Garden gardenToBeDeleted = new Garden();

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        Optional<Garden> garden = gardenRepository.findByGardenName(NAMEGARDEN);
        garden.ifPresent(value -> gardenRepository.delete(value));
        User user = saveUser(NAME, PASSWORD);
        User owner = userRepository.getOne(user.getUserId());
        gardenToBeDeleted.setGardenName(NAMEGARDEN);
        gardenToBeDeleted.setUser(owner);
        gardenToBeDeleted.addGardenMember(owner);
        gardenToBeDeleted.setLength(THREE);
        gardenToBeDeleted.setWidth(THREE);
        gardenRepository.save(gardenToBeDeleted);
        saveUser(SECONDNAME, SECONDPASSWORD);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
        this.driver = null;
        Optional<Garden> optionalGarden = gardenRepository.findByGardenName(NAMEGARDEN);
        optionalGarden.ifPresent(garden -> gardenRepository.delete(garden));
        Optional<User> optionalUser = userRepository.findByUsername(NAME);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        Optional<User> secondOptionalUser = userRepository.findByUsername(SECONDNAME);
        secondOptionalUser.ifPresent(user -> userRepository.delete(user));
    }

    @Test
    void testDeleteGardenByOwner() throws Exception {

        // Arrange
        boolean expectedFound = false;
        Optional<Garden> garden = gardenRepository.findByGardenName(NAMEGARDEN);
        Integer gardenId = garden.get().getGardenId();

        // Activate
        loginAsAUser(NAME, PASSWORD);
        this.driver.get("http://localhost:8080/user/garden/delete/" + gardenId);
        boolean actualFound = gardenRepository.findByGardenName(NAMEGARDEN).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
    }

    // Test an unauthorized attempt of one user to delete the garden of another user
    @Test
    void testDeleteGardenByAnotherUser() throws Exception {

        // Arrange
        boolean expectedFound = true;
        Optional<Garden> garden = gardenRepository.findByGardenName(NAMEGARDEN);
        Integer gardenId = garden.get().getGardenId();

        // Activate
        loginAsAUser(SECONDNAME, SECONDPASSWORD);
        this.driver.get("http://localhost:8080/user/garden/delete/"+ gardenId);
        boolean actualFound = gardenRepository.findByGardenName(NAMEGARDEN).isPresent();

        // Assert
        Assertions.assertEquals(expectedFound, actualFound);
    }

    private User saveUser(String name, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(name);
        optionalUser.ifPresent(user -> userRepository.delete(user));
        User registeredUser = new User();
        registeredUser.setUsername(name);
        registeredUser.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByRoleName(ROLE).get();
        registeredUser.getRole().add(role);

        userRepository.save(registeredUser);
        return registeredUser;
    }

    private void loginAsAUser(String name, String password) {
        this.driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).sendKeys(name);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("inlogbutton")).submit();
    }


}
