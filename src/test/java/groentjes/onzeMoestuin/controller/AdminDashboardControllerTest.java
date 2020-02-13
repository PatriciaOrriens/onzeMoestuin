//package groentjes.onzeMoestuin.controller;
//
//
//import groentjes.onzeMoestuin.repository.UserRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @author Eric van Dalen
// * Test class for Administrator Dashboard
// */
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = AdminDashboardController.class)
//public class AdminDashboardControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    GardenUserDetailsService gardenUserDetailsService;
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testAdminDashboardPage() throws Exception {
//        final ResultActions result = mockMvc.perform(get("/adminDashboard")).andExpect(status().isOk())
//                .andExpect(forwardedUrl("/WEB-INF/views/adminDashboard.jsp"));
//    }
//}