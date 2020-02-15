package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.GardenRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import groentjes.onzeMoestuin.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import javax.servlet.RequestDispatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

/**
 * @author Eric van Dalen
 * Test class for the CustomErrorController
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ErrorController.class)
class CustomErrorControllerTest {

    @Autowired
    private CustomErrorController customErrorController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GardenRepository gardenRepository;

//    @MockBean
//    GardenUserDetailsService gardenUserDetailsService;

    @MockBean
    private RoleRepository roleRepository;

    @Test
    @WithMockUser(roles = "USER")
    public void testHandleErrorForErrorStatusCode403() throws Exception {
        final ResultActions result403 = mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 403))
                .andExpect(forwardedUrl("/WEB-INF/views/403error.jsp"));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testHandleErrorForErrorStatusCode404() throws Exception {
        final ResultActions result404 = mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404))
                .andExpect(forwardedUrl("/WEB-INF/views/404error.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testHandleErrorForErrorStatusCode405() throws Exception {
        final ResultActions result405 = mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 405))
                .andExpect(forwardedUrl("/WEB-INF/views/405error.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testHandleErrorForErrorStatusCode500() throws Exception {
        final ResultActions result500 = mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500))
                .andExpect(forwardedUrl("/WEB-INF/views/500error.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testHandleErrorForErrorStatusCodeOther() throws Exception {
        final ResultActions result500 = mockMvc.perform(get("/error")
                .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 501))
                .andExpect(forwardedUrl("/WEB-INF/views/error.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetErrorPath() {
        // Arrange
        String expectedString = "/error";

        //Activate
        String actualString = customErrorController.getErrorPath();

        //Assert
        Assertions.assertEquals(expectedString, actualString);
    }
}