//package groentjes.onzeMoestuin.controller;
//
//import groentjes.onzeMoestuin.model.Garden;
//import groentjes.onzeMoestuin.model.Message;
//import groentjes.onzeMoestuin.model.User;
//import groentjes.onzeMoestuin.repository.GardenRepository;
//import groentjes.onzeMoestuin.repository.MessageRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
//import net.sf.cglib.core.Local;
//import org.apache.catalina.core.ApplicationContext;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Wim Kruizinga
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = MessagesRestController.class)
//public class MessageRestControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ApplicationContext context;
//
//    @MockBean
//    private MessageRepository messageRepository;
//
//    @MockBean
//    private GardenRepository gardenRepository;
//
//    @Mock
//    Garden mockGarden;
//
//    @Mock
//    PageRequest mockPage;
//
//    @MockBean
//    User mockUser;
//
//    @MockBean
//    GardenUserDetailsService gardenUserDetailsService;
//
////    @BeforeEach
////    void setUp() {
////
////
////
////    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void testMessageRest() throws Exception {
//        LocalDateTime time = LocalDateTime.now();
//
//        List<Message> messages = new ArrayList<>();
//
//        messages.add(new Message(1, mockUser, mockGarden, "test message", time));
//
//        Mockito.when(messageRepository.findAllByGarden(mockGarden, mockPage)).thenReturn(messages);
//
//        mockMvc.perform(get("/garden/{id}/messages/{page}", 1, 1)).andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//
//    }
//
//
//
//
//
//
//}
