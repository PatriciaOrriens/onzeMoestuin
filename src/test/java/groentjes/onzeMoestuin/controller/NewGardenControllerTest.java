package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NewGardenController.class)
public class NewGardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    GardenUserDetailsService gardenUserDetailsService;

    @Test
    @WithMockUser(roles = "USER")
    void testNewGardenPage() throws Exception {
        final ResultActions result = mockMvc.perform(get("/garden/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/newGarden.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testRegisterUser() throws Exception {

        // Create test garden object
        Garden testGarden = new Garden();
        testGarden.setUser(new User());
        testGarden.setGardenName("testGarden");
        testGarden.setLength(10);
        testGarden.setWidth(10);

        mockMvc.perform(post("/registerUser")
                .sessionAttr("garden", testGarden)
                .with(csrf()))
                .andExpect(forwardedUrl("/WEB"));


    }
}
