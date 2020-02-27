package groentjes.onzeMoestuin.controller;

import groentjes.onzeMoestuin.repository.PlantInformationRepository;
import groentjes.onzeMoestuin.repository.PlantRepository;
import groentjes.onzeMoestuin.repository.RoleRepository;
import groentjes.onzeMoestuin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Eric van Dalen
 * Test class for managing PlantInformation
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PlantInformationController.class)
class PlantInformationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private PlantInformationRepository plantInformationRepository;

    @MockBean
    private PlantRepository plantRepository;

    private String plantName = "plantnaam";
    private String latinName = "Latijnse plantnaam";
    private Integer plantingDistance = 1;
    private String lighting = "zon";
    private String soilType = "kleigrond";
    private String sowingStart = "januari";
    private String sowingEnd =  "februari";
    private String plantingStart = "maart";
    private String plantingEnd = "april";
    private String harvestingStart = "mei";
    private String harvestingEnd = "juni";
    private Integer growTime = 2;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testManagePlantInfoWithRoleAdministrator() throws Exception {
        final ResultActions result = mockMvc.perform(get("/adminManagePlantInformation")).andExpect(status()
                .isOk()).andExpect(forwardedUrl("/WEB-INF/views/adminManagePlantInformation.jsp"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetPlantInfoFormWithRoleAdministrator() throws Exception {
        final ResultActions result = mockMvc.perform(get("/admincreateplantinfo")).andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/adminCreatePlantInformation.jsp"));
    }
}