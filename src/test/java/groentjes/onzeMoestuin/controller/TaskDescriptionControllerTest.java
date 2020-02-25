package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.TaskDescription;
import groentjes.onzeMoestuin.repository.RoleRepository;
import groentjes.onzeMoestuin.repository.TaskDescriptionRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
 * @author Patricia Orriens-Spuij
 * Tests the functionality of the TaskDescriptionController (admin page)
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskDescriptionController.class)
public class TaskDescriptionControllerTest {

    // Arrange for test
    private static final int ONE = 1;
    private static final String TASKNAME = "testTaak";

    private TaskDescription taskDescription;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private TaskDescriptionRepository taskDescriptionRepository;

    @BeforeEach
    void setUp() {
        taskDescription = new TaskDescription();
        taskDescription.setTaskDescriptionId(ONE);
        taskDescription.setTaskName(TASKNAME);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testManageTaskDescriptions() throws Exception {
        final ResultActions result = mockMvc.perform(get("/adminManageTasks")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/adminManageTasks.jsp"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSaveNewTaskDescription() throws Exception {
        mockMvc.perform(post("/adminManageTasks").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("taskName", TASKNAME).flashAttr("newTask", new TaskDescription()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/adminManageTasks"))
                .andExpect(redirectedUrl("/adminManageTasks"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteTaskDescription() throws Exception {
        Mockito.when((taskDescriptionRepository.findById(ONE))).thenReturn(Optional.of(taskDescription));

        final ResultActions result = mockMvc.perform(get("/task/delete/" + ONE)
                .sessionAttr("taskId", ONE)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/adminManageTasks"));
    }
}
