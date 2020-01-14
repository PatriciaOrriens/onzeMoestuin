package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Eric van Dalen
 * Test class for login
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GardenUserDetailsService gardenUserDetailsService;

    @Test
    void testLogin() throws Exception {
        final ResultActions result = mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
    }

    @Test
    void testLoginfailed() throws Exception {
        final ResultActions result = mockMvc.perform(get("/loginfailed")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/loginFailed.jsp"));
    }
}