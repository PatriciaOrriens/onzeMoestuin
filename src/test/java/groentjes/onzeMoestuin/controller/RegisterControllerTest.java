package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.User;
import groentjes.onzeMoestuin.repository.GardenInvitationRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import groentjes.onzeMoestuin.service.GardenUserDetailsService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Wim Kruizinga and Eric van Dalen
 * Tests concerning registration of users
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisterController.class)
class RegisterControllerTest {

    private static final String USERNAME = "gebruikersnaam";
    private static final String PASSWORD = "wachtwoord";
    private static final String EMAIL = "gebruiker@email.com";
    private User otherUser = new User();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    GardenUserDetailsService gardenUserDetailsService;

    @MockBean
    GardenInvitationRepository gardenInvitationRepository;

    @BeforeEach
    void setUp() {
        otherUser.setUsername(USERNAME);
        otherUser.setEmail(EMAIL);
        otherUser.setPassword(PASSWORD);
    }

    @Test
    void testGetRegisterUserForm() throws Exception {
        final ResultActions result = mockMvc.perform(get("/registerUser"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/register.jsp"));
    }

    @Test
    void testSaveNewUserWithAnExistingUsername() throws Exception {
        Mockito.when((userRepository.findByUsername(USERNAME))).thenReturn(Optional.of(otherUser));

        mockMvc.perform(post("/registerUser").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username" , USERNAME).param("email", EMAIL).param("password", PASSWORD)
                .flashAttr("user", new User()).with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(forwardedUrl("/WEB-INF/views/register.jsp"));
    }

    @Test
    void testSaveNewUser() throws Exception {

        Mockito.when((userRepository.findByUsername(USERNAME))).thenReturn(Optional.empty());

        mockMvc.perform(post("/registerUser").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username" , USERNAME).param("email", EMAIL).param("password", PASSWORD)
                .flashAttr("user", new User()).with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(redirectedUrl("/login"));

        ArgumentCaptor<User> formObjectArgument = forClass(User.class);
        verify(userRepository, times(1)).save(formObjectArgument.capture());

        User formObject = formObjectArgument.getValue();
        Assertions.assertThat(formObject.getUsername()).isEqualTo(USERNAME);
        Assertions.assertThat(formObject.getEmail()).isEqualTo(EMAIL);
    }
}