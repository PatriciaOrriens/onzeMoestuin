package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.controller.TaskController;
import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.*;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
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

    private static final int FIRST_GARDEN_ID = 1;
    private Garden firstGarden;

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    GardenUserDetailsService gardenUserDetailsService;

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
        firstGarden = new Garden();
        firstGarden.setGardenId(FIRST_GARDEN_ID);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllTasks() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        Mockito.when((gardenRepository.findById(FIRST_GARDEN_ID))).thenReturn(Optional.empty());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        final ResultActions result = mockMvc.perform(get("/userTaskOverview/" + gardenId)
                .sessionAttr("gardenId", gardenId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}