package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.*;
import groentjes.onzeMoestuin.repository.*;
import org.junit.jupiter.api.Assertions;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eric van Dalen
 * Tests concerning the functionality around task completion
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskPlantController.class)
class TaskPlantControllerTest {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int EIGHT = 8;
    private static final String DATE = "25-01-2020";

    private TaskPlant taskPlant = new TaskPlant();;
    private TaskGarden taskGarden = new TaskGarden();
    private TaskPlantInfo taskPlantInfo = new TaskPlantInfo();;
    private Plant plant = new Plant();;
    private Garden garden = new Garden();;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private PlantRepository plantRepository;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private TaskPlantRepository taskPlantRepository;

    @MockBean
    private TaskGardenRepository taskGardenRepository;

    @MockBean
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskPlant.setTaskId(TWO);
        taskGarden.setTaskId(FOUR);
        taskPlantInfo.setDaysAfterStart(EIGHT);
        taskPlant.setTaskPlantInfo(taskPlantInfo);
        taskPlant.setDueDate(DATE);
        taskGarden.setDueDate(DATE);
        taskPlant.setPlant(plant);
        taskPlant.setCompletedDate(null);
        taskPlant.setUser(null);
        garden.setGardenId(ONE);
        plant.setGarden(garden);
        taskGarden.setGarden(garden);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testProcessCompletedTaskWithExistingTaskPlant() throws Exception {

        String taskId = taskPlant.getTaskId().toString();

        boolean expectedisCompleted = true;

        Mockito.when(taskRepository.findById(TWO)).thenReturn(Optional.ofNullable(taskPlant));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        final ResultActions result = mockMvc.perform(get("/user/task/completed/" + taskId)
                .sessionAttr("taskId", taskId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/garden/" + garden.getGardenId()));

        boolean foundisCompleted = (taskPlant.getCompletedDate() != null);

        Assertions.assertEquals(expectedisCompleted, foundisCompleted);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testProcessCompletedTaskWithNonExistingTaskPlant() throws Exception {

        String taskId = taskPlant.getTaskId().toString();

        boolean expectedisCompleted = false;

        Mockito.when(taskRepository.findById(THREE)).thenReturn(Optional.ofNullable(taskPlant));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        final ResultActions result = mockMvc.perform(get("/user/task/completed/" + taskId)
                .sessionAttr("taskId", taskId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/" ));

        boolean foundisCompleted = (taskPlant.getCompletedDate() != null);

        Assertions.assertEquals(expectedisCompleted, foundisCompleted);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testProcessCompletedTaskWithExistingTaskGarden() throws Exception {

        String taskId = taskGarden.getTaskId().toString();

        boolean expectedisCompleted = true;

        Mockito.when(taskRepository.findById(FOUR)).thenReturn(Optional.of(taskGarden));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        final ResultActions result = mockMvc.perform(get("/user/task/completed/" + taskId)
                .sessionAttr("taskId", taskId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/garden/" + garden.getGardenId()));

        boolean foundisCompleted = (taskGarden.getCompletedDate() != null);

        Assertions.assertEquals(expectedisCompleted, foundisCompleted);
    }
}