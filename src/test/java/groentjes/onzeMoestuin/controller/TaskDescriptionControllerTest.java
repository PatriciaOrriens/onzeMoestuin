package groentjes.onzeMoestuin.controller;


import groentjes.onzeMoestuin.repository.TaskDescriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Patricia Orriens-Spuij
 * Tests the functionality of the TaskDescriptionController (admin page)
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskDescriptionController.class)
public class TaskDescriptionControllerTest {

    // Arrange for test


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDescriptionRepository taskDescriptionRepository;



    //Activate test
    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllTasks() throws Exception {

    }


    //Assert test
}
