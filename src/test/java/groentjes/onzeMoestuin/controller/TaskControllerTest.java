package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.*;
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
 * Test class for management of Tasks.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskController.class)
class TaskControllerTest {

    private static final Integer OWNER_ID = 2;
    private static final int FIRST_GARDEN_ID = 1;
    private static final Integer PLANT_ID = 5;
    private static final String USERNAME = "gebruikersnaam";
    private static final String PASSWORD = "wachtwoord";
    private static final String EMAIL = "gebruiker@email.com";
    private Plant plant = new Plant();
    private Garden firstGarden;
    private User owner;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private PlantRepository plantRepository;

    @MockBean
    private TaskPlantRepository taskPlantRepository;

    @MockBean
    private TaskGardenRepository taskGardenRepository;

    @BeforeEach
    void setUp() {
        owner = new User(USERNAME, PASSWORD, EMAIL);
        owner.setUserId(OWNER_ID);
        firstGarden = new Garden();
        firstGarden.setGardenId(FIRST_GARDEN_ID);
        firstGarden.setUser(owner);
        firstGarden.addGardenMember(owner);
        plant.setPlantId(PLANT_ID);
        plant.setGarden(firstGarden);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllTasks() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        Mockito.when((gardenRepository.findById(FIRST_GARDEN_ID))).thenReturn(Optional.of(firstGarden));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));
        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(plant);
        Mockito.when(plantRepository.findAllByGarden(firstGarden)).thenReturn(plants);

        final ResultActions result = mockMvc.perform(get("/userTaskOverview/" + gardenId)
                .sessionAttr("gardenId", gardenId)).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/showTaskOverview.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllTasksWhenGardenIsNotPresent() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        Mockito.when((gardenRepository.findById(FIRST_GARDEN_ID))).thenReturn(Optional.empty());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(owner));

        final ResultActions result = mockMvc.perform(get("/userTaskOverview/" + gardenId)
                .sessionAttr("gardenId", gardenId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}