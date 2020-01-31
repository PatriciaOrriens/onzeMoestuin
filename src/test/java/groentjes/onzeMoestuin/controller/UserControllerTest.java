package groentjes.onzeMoestuin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import groentjes.onzeMoestuin.model.*;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

/**
 * @author Eric van Dalen
 * Test class for management of Users.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    private static final String USERNAME = "gebruikersnaam";
    private static final String PASSWORD = "wachtwoord";
    private static final String EMAIL = "gebruiker@email.com";
    private User otherUser = new User();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    GardenUserDetailsService gardenUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        otherUser.setUsername(USERNAME);
        otherUser.setEmail(EMAIL);
        otherUser.setPassword(PASSWORD);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testFindAllWithRoleAdminstrator() throws Exception {
        final ResultActions result = mockMvc.perform(get("/manageUsers")).andExpect(status()
                .isOk()).andExpect(forwardedUrl("/WEB-INF/views/manageUsers.jsp"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowNewUserFormWithRoleAdminstrator() throws Exception {
        final ResultActions result = mockMvc.perform(get("/user/new")).andExpect(status()
                .isOk()).andExpect(forwardedUrl("/WEB-INF/views/adminCreateUser.jsp"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDoDeleteUserWithRoleAdminstrator() throws Exception {
        String userName = "test1";
        final ResultActions result = mockMvc.perform(get("/user/delete/" + userName)
                .sessionAttr("userName", userName)).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/manageUsers"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSaveOrUpdateUserByAdminstrator() throws Exception {

        Mockito.when((userRepository.findByUsername(USERNAME))).thenReturn(Optional.empty());

        mockMvc.perform(post("/user/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username" , USERNAME).param("email", EMAIL).param("password", PASSWORD)
                .flashAttr("user", new User()).with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/manageUsers"))
                .andExpect(redirectedUrl("/manageUsers"));

        ArgumentCaptor<User> formObjectArgument = forClass(User.class);
        verify(userRepository, times(1)).save(formObjectArgument.capture());

        User formObject = formObjectArgument.getValue();
        Assertions.assertThat(formObject.getUsername()).isEqualTo(USERNAME);
        Assertions.assertThat(formObject.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testSaveOrUpdateUserWithAnExistingUsernameByAdminstrator() throws Exception {

        Mockito.when((userRepository.findByUsername(USERNAME))).thenReturn(Optional.of(otherUser));

        mockMvc.perform(post("/user/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username" , USERNAME).param("email", EMAIL).param("password", PASSWORD)
                .flashAttr("user", new User()).with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("adminCreateUser"))
                .andExpect(forwardedUrl("/WEB-INF/views/adminCreateUser.jsp"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testFindAllWithRoleUser() throws Exception {
        final ResultActions result = mockMvc.perform(get("/manageUsers")).andExpect(status()
                .isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testShowNewUserFormWithRoleUser() throws Exception {
        final ResultActions result = mockMvc.perform(get("/user/new")).andExpect(status()
                .isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDoDeleteUserWithRoleUser() throws Exception {
        String userName = "test1";
        final ResultActions result = mockMvc.perform(get("/user/delete/" + userName)
                .sessionAttr("userName", userName)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testSaveOrUpdateUserWithRoleUser() throws Exception {
        String username = "gebruiker";
        String password = "wachtwoord";
        mockMvc.perform(post("/user/new").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username" , username).param("password", password)
                .flashAttr("user", new User()).with(csrf())).andExpect(status().isForbidden());
    }
}