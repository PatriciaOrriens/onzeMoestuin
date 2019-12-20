package groentjes.onzeMoestuin.controller;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eric van Dalen
 * Test class for managing gardens by users.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ManageGardenController.class)
class ManageGardenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GardenRepository gardenRepository;

    @MockBean
    GardenUserDetailsService gardenUserDetailsService;

    @Test
    @WithMockUser(roles = "User")
    void testFindAllYourGardens() throws Exception {
        final ResultActions result = mockMvc.perform(get("/userManageGardens")).andExpect(status()
                .isOk()).andExpect(forwardedUrl("/WEB-INF/views//manageGarden.jsp"));
    }
}