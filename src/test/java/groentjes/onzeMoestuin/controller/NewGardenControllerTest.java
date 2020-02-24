package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NewGardenController.class)
public class NewGardenControllerTest {

    private static final int ONCE = 1;
    private static final Integer GARDEN_ID = 4;
    private static final String GARDEN_NAME = "testtuin";
    private static final Integer GARDEN_LENGTH = 2;
    private static final Integer GARDEN_WIDTH = 3;
    private static final String USERNAME = "gebruikersnaam";
    private static final Integer INVALID_GARDEN_LENGTH = 0;
    private static final String PASSWORD = "wachtwoord";
    private static final String EMAIL = "gebruiker@email.com";
    private Garden shownGarden = new Garden();
    private User otherUser = new User();

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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        otherUser.setUsername(USERNAME);
        otherUser.setEmail(EMAIL);
        otherUser.setPassword(PASSWORD);
        otherUser.setUserId(1);
        shownGarden.setGardenId(GARDEN_ID);
        shownGarden.setGardenName(GARDEN_NAME);
        shownGarden.setLength(GARDEN_LENGTH);
        shownGarden.setWidth(GARDEN_WIDTH);
        shownGarden.setUser(otherUser);
        shownGarden.addGardenMember(otherUser);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowGardenIfNoGardenIsPresent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.empty());

        final ResultActions result = mockMvc.perform(get("/garden/" + GARDEN_ID))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowGardenIfGardenIsPresent() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));
        Mockito.when(gardenRepository.findById(GARDEN_ID)).thenReturn(Optional.of(shownGarden));

        final ResultActions result = mockMvc.perform(get("/garden/" + GARDEN_ID))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/showGarden.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowGardenForm() throws Exception {
        final ResultActions result = mockMvc.perform(get("/garden/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/newGarden.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSaveOrUpdateGardenIfGardenIsValid() throws Exception {
        Garden garden = new Garden();
        garden.setGardenId(GARDEN_ID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));

        mockMvc.perform(post("/garden/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gardenName" ,GARDEN_NAME).param("length", String.valueOf(GARDEN_LENGTH))
                .param("width", String.valueOf(GARDEN_WIDTH)).flashAttr("garden", garden).with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/garden/" + GARDEN_ID));

        ArgumentCaptor<Garden> formObjectArgument = forClass(Garden.class);
        verify(gardenRepository, times(ONCE)).save(formObjectArgument.capture());

        Garden formObject = formObjectArgument.getValue();
        Assertions.assertThat(formObject.getGardenName()).isEqualTo(GARDEN_NAME);
        Assertions.assertThat(formObject.getLength()).isEqualTo(GARDEN_LENGTH);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSaveOrUpdateGardenIfGardenLengthIsNotValid() throws Exception {
        Garden garden = new Garden();
        garden.setGardenId(GARDEN_ID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mockito.when(userRepository.findByUsername(authentication.getName())).thenReturn(Optional.of(otherUser));

        mockMvc.perform(post("/garden/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("gardenName" ,GARDEN_NAME).param("length", String.valueOf(INVALID_GARDEN_LENGTH))
                .param("width", String.valueOf(GARDEN_WIDTH)).flashAttr("garden", garden).with(csrf()))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/WEB-INF/views/newGarden.jsp"));
    }

}
