//package groentjes.onzeMoestuin.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import groentjes.onzeMoestuin.model.Garden;
//import groentjes.onzeMoestuin.model.Message;
//import groentjes.onzeMoestuin.model.User;
//import groentjes.onzeMoestuin.repository.GardenRepository;
//import groentjes.onzeMoestuin.repository.MessageRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
//import net.sf.cglib.core.Local;
//import org.apache.catalina.core.ApplicationContext;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Wim Kruizinga
// */
//// MockitoJUNitRunner adds functionality to SpringRunner - initializes all fields with @Mock (no need to call Mockito.initMocks()
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(value = MessagesRestController.class)
//public class MessageRestControllerTest {
//
//    private MockMvc mvc;
//
//    @Mock
//    private MessageRepository messageRepository;
//
//    @Mock
//    private GardenRepository gardenRepository;
//
//    @InjectMocks // ensures  that mocked repo is injected into the controller
//    private MessagesRestController messageController;
//
//    @Mock
//    private User mockUser;
//
//    @Mock
//    private Garden mockGarden;
//
//
//
//    private LocalDateTime time;
//
//    // ?
//    private JacksonTester<ArrayList<Message>> jsonMessage;
//
//    @Before
//    public void setup() {
//        JacksonTester.initFields(this, new ObjectMapper());
//        mvc = MockMvcBuilders.standaloneSetup(messageController)
//                .build();
//
//        time = LocalDateTime.now();
//
//
//    }
//
//    @Test
//    public void canGetMessagesByGardenWhenExists() throws Exception {
//
//
//        // given
//        given(messageRepository.findAllByGarden(any(Garden.class), any(Pageable.class)))
//                .willReturn(new ArrayList<Message>(Arrays.asList(
//                    new Message(1, mockUser, mockGarden, "Test message", time),
//                    new Message(2, mockUser, mockGarden, "Test message 2", time)
//                )));
//
//        // when
//        MockHttpServletResponse response = mvc.perform(
//                get("/api/garden/1/messages/0")
//                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(
//                jsonMessage.write(new ArrayList<Message>(Arrays.asList(
//                        new Message(1, mockUser, mockGarden, "Test message", time),
//                        new Message(2, mockUser, mockGarden, "Test message 2", time)
//                ))));
//
//    }
//
//
//
//
//
//
//}
