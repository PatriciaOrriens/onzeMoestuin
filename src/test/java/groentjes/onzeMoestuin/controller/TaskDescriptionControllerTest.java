package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.model.TaskDescription;
import groentjes.onzeMoestuin.repository.TaskDescriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Patricia Orriens-Spuij
 * Tests the functionality of the TaskDescriptionController (admin page)
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskDescriptionController.class)
public class TaskDescriptionControllerTest {

    // Arrange for test
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final String taskName1 = "testTaak1";
    private static final String taskName2 = "testTaak2";

    private TaskDescription taskDescriptionA;
    private TaskDescription taskDescriptionB;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDescriptionRepository taskDescriptionRepository;

    @BeforeEach
    void setUp() {
        taskDescriptionA = new TaskDescription();
        taskDescriptionA.setTaskDescriptionId(ONE);
        taskDescriptionA.setTaskName(taskName1);

        taskDescriptionB = new TaskDescription();
        taskDescriptionB.setTaskDescriptionId(TWO);
        taskDescriptionB.setTaskName(taskName2);
    }

    //Activate test
    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowTaskForUpdate() throws Exception {
        String taskDescriptionId = taskDescriptionA.getTaskDescriptionId().toString();

        boolean expectedIsCompleted = true;

        Mockito.when(taskDescriptionRepository.findById(ONE))
                .thenReturn(Optional.ofNullable(taskDescriptionA));

        final ResultActions result = mockMvc.perform(get("/task/update/" + taskDescriptionId)
                .sessionAttr("taskId", taskDescriptionId)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    //Assert test

}
