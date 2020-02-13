package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Message;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenRepository;
import groentjes.onzeMoestuin.repository.MessageRepository;
import groentjes.onzeMoestuin.repository.RoleRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
import net.sf.cglib.core.Local;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wim Kruizinga
 */
// MockitoJUNitRunner adds functionality to SpringRunner - initializes all fields with @Mock (no need to call Mockito.initMocks()
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = MessagesRestController.class)
public class MessageRestControllerTest {

    private MockMvc mvc;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private GardenRepository gardenRepository;

    @InjectMocks // ensures  that mocked repo is injected into the controller
    private MessagesRestController messageController;

    @MockBean
    private User mockUser;

    @Mock
    private Garden mockGarden;

    @MockBean
    private RoleRepository roleRepository;

    private LocalDateTime time;

    // ?
    private JacksonTester<ArrayList<Message>> jsonMessage;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(messageController)
                .build();

        time = LocalDateTime.now();
        mockUser = new User();
        mockUser.setUsername("testuser");

    }

    @Test
    public void canGetMessagesByGardenWhenExists() throws Exception {
        // given
        given(messageRepository.findAllByGarden(any(Garden.class), any(Pageable.class)))
                .willReturn(new ArrayList<Message>(Arrays.asList(
                    new Message(1, mockUser, mockGarden, "Test message", time),
                    new Message(2, mockUser, mockGarden, "Test message 2", time)
                )));
        given(gardenRepository.findById(any(Integer.class)))
                .willReturn(java.util.Optional.of(mockGarden));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/garden/1/messages/0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        String expected = "[{\"messageId\": 1, \"sender\": { \"username\": \"testuser\" }, \"messageBody\": \"Test message\" }," +
                " {\"messageId\": 2, \"sender\": { \"username\": \"testuser\" }, \"messageBody\": \"Test message 2\" }]";

        JSONAssert.assertEquals(expected, response.getContentAsString(), JSONCompareMode.LENIENT);

    }
}
