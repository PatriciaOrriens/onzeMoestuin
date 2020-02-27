package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Eric van Dalen
 * Controller tests concerning the functionality around managing plants
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PlantController.class)
class PlantControllerTest {

    private static final Integer PLANT_ID = 1;
    private static final Integer OWNER_ID = 2;
    private static final Integer OTHER_USER_ID = 3;
    private static final Integer GARDEN_ID = 4;
    private static final Integer PLANTINFO_ID = 5;
    private static final String USERNAME = "gebruikersnaam";
    private static final String PASSWORD = "wachtwoord";
    private static final String EMAIL = "gebruiker@email.com";
    private static final String OTHER_USERNAME = "ander gebruikersnaam";
    private static final String OTHER_PASSWORD = "ander wachtwoord";
    private static final String OTHER_EMAIL = "anderegebruiker@email.com";
    private static final String GARDEN_NAME = "testtuin";
    private static final Integer GARDEN_LENGTH = 2;
    private static final Integer GARDEN_WIDTH = 3;
    private Plant plant = new Plant();
    private Garden garden;
    private User owner;
    private User otherUser;
    private PlantInformation plantInformation = new PlantInformation();

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PlantRepository plantRepository;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private PlantInformationRepository plantInformationRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner = new User(USERNAME, PASSWORD, EMAIL);
        owner.setUserId(OWNER_ID);
        otherUser = new User(OTHER_USERNAME, OTHER_PASSWORD, OTHER_EMAIL);
        otherUser.setUserId(OTHER_USER_ID);
        garden = new Garden(GARDEN_NAME, GARDEN_LENGTH, GARDEN_WIDTH);
        garden.setGardenId(GARDEN_ID);
        garden.addGardenMember(owner);
        garden.setUser(owner);
        plantInformation.setPlantInfoId(PLANTINFO_ID);
        plant.setPlantId(PLANT_ID);
        plant.setGarden(garden);
        plant.setPlantInformation(plantInformation);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAddPlantFormWithEmptyPlantInformatiomArray() throws Exception {
        ArrayList<PlantInformation> plantInformations = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findAll()).thenReturn(plantInformations);
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant")
                .sessionAttr("gardenId", GARDEN_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAddPlantFormWhenNoGardenIsPresent() throws Exception {
        ArrayList<PlantInformation> plantInformations = new ArrayList<>();
        plantInformations.add(plantInformation);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findAll()).thenReturn(plantInformations);
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.empty());

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant")
                .sessionAttr("gardenId", GARDEN_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAddPlantFormWhenUserIsNoGardenMember() throws Exception {
        ArrayList<PlantInformation> plantInformations = new ArrayList<>();
        plantInformations.add(plantInformation);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(plantInformationRepository.findAll()).thenReturn(plantInformations);
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant")
                .sessionAttr("gardenId", GARDEN_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAddPlantForm() throws Exception {
        ArrayList<PlantInformation> plantInformations = new ArrayList<>();
        plantInformations.add(plantInformation);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findAll()).thenReturn(plantInformations);
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant")
                .sessionAttr("gardenId", GARDEN_ID)).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/addPlant.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowPlantDetailsIfPlantDoesNotExists() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(plantRepository.findById(PLANT_ID)).thenReturn(Optional.empty());

        final ResultActions result = mockMvc.perform(get("/plant/" + PLANT_ID)).andExpect(status()
                .is3xxRedirection()).andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowPlantDetailsWhenUserIsNoGardenMember() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(plantRepository.findById(PLANT_ID)).thenReturn(Optional.of(plant));

        final ResultActions result = mockMvc.perform(get("/plant/" + PLANT_ID)).andExpect(status()
                .is3xxRedirection()).andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowPlantDetails() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantRepository.findById(PLANT_ID)).thenReturn(Optional.of(plant));

        final ResultActions result = mockMvc.perform(get("/plant/" + PLANT_ID)).andExpect(status()
                .isOk()).andExpect(forwardedUrl("/WEB-INF/views/showPlant.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddPlantToGardenWhenNoGardenIsPresent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findById(PLANTINFO_ID)).thenReturn(Optional.of(plantInformation));
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.empty());

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant/"
                + PLANTINFO_ID).sessionAttr("gardenId", GARDEN_ID)
                .sessionAttr("plantInfoId", PLANTINFO_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddPlantToGardenWhenNoPlantInformationIsAvailable() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findById(PLANTINFO_ID)).thenReturn(Optional.empty());
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant/"
                + PLANTINFO_ID).sessionAttr("gardenId", GARDEN_ID)
                .sessionAttr("plantInfoId", PLANTINFO_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddPlantToGardenWhenUserIsNoGardenMember() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(plantInformationRepository.findById(PLANTINFO_ID)).thenReturn(Optional.of(plantInformation));
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant/"
                + PLANTINFO_ID).sessionAttr("gardenId", GARDEN_ID)
                .sessionAttr("plantInfoId", PLANTINFO_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddPlantToGarden() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantInformationRepository.findById(PLANTINFO_ID)).thenReturn(Optional.of(plantInformation));
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(garden));

        final ResultActions result = mockMvc.perform(get( "/garden/" + GARDEN_ID + "/addPlant/"
                + PLANTINFO_ID).sessionAttr("gardenId", GARDEN_ID)
                .sessionAttr("plantInfoId", PLANTINFO_ID)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/garden/" + GARDEN_ID));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testHarvestPlantWhenUserIsNoGardenMember() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(plantRepository.findById(PLANT_ID)).thenReturn(Optional.empty());

        final ResultActions result = mockMvc.perform(get("/plant/delete/" + PLANT_ID))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testHarvestPlant() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        Mockito.when(plantRepository.findById(PLANT_ID)).thenReturn(Optional.of(plant));

        final ResultActions result = mockMvc.perform(get("/plant/delete/" + PLANT_ID)).andExpect(status()
                .is3xxRedirection()).andExpect(redirectedUrl("/garden/" + GARDEN_ID));
    }
}