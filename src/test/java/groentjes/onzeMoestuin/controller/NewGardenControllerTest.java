package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.*;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NewGardenController.class)
public class NewGardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantRepository plantRepository;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskPlantRepository taskPlantRepository;

    @MockBean
    private TaskGardenRepository taskGardenRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private RoleRepository roleRepository;

//    @MockBean
//    GardenUserDetailsService gardenUserDetailsService;

    @Test
    @WithMockUser(roles = "USER")
    void testNewGardenPage() throws Exception {
        final ResultActions result = mockMvc.perform(get("/garden/add"))

                .andExpect(forwardedUrl("/WEB-INF/views/newGarden.jsp"));
    }
}
