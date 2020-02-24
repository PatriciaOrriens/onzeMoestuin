package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.TaskGarden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Eric van Dalen
 * Tests concerning the controller for adding a garden task
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskGardenController.class)
class TaskGardenControllerTest {

    private static final int FIRST_GARDEN_ID = 1;
    private static final String TASK_GARDEN_NAME = "omploegen";
    private static final String INVALID_TASK_GARDEN_NAME = "A";
    private static final String DUE_DATE = "28-02-2020";
    private static final String INVALID_DUE_DATE = "36-02-2020";
    private Garden firstGarden;
    private TaskGarden taskGarden;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskGardenRepository taskGardenRepository;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        firstGarden = new Garden();
        firstGarden.setGardenId(FIRST_GARDEN_ID);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testAddTaskGarden() throws Exception {
            taskGarden = new TaskGarden();
            String gardenId = firstGarden.getGardenId().toString();
            Mockito.when((gardenRepository.findById(FIRST_GARDEN_ID))).thenReturn(Optional.empty());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

            final ResultActions result = mockMvc.perform(get("/garden/" +gardenId + "/addTaskGarden")
                    .sessionAttr("taskGarden", taskGarden)).andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void storeTaskGardenWithInvalidTaskGardenName() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        taskGarden = new TaskGarden();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/garden/" + gardenId + "/addTaskGarden")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("taskGardenName" , INVALID_TASK_GARDEN_NAME).param("dueDate" , DUE_DATE)
                .flashAttr("taskGarden", taskGarden).with(csrf()))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/addTaskGarden.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void storeTaskGardenWithInvalidDueDate() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        taskGarden = new TaskGarden();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/garden/" + gardenId + "/addTaskGarden")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("taskGardenName" , TASK_GARDEN_NAME).param("dueDate" , INVALID_DUE_DATE)
                .flashAttr("taskGarden", taskGarden).with(csrf()))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/addTaskGarden.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void storeTaskGardenWithNoGarden() throws Exception {
        String gardenId = firstGarden.getGardenId().toString();
        taskGarden = new TaskGarden();
        Mockito.when((gardenRepository.findById(FIRST_GARDEN_ID))).thenReturn(Optional.empty());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/garden/" + gardenId + "/addTaskGarden")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("taskGardenName" , TASK_GARDEN_NAME).param("dueDate" , DUE_DATE)
                .flashAttr("taskGarden", taskGarden).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
    }
}