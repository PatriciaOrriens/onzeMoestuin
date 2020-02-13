//package groentjes.onzeMoestuin.controller;
//
//import groentjes.onzeMoestuin.model.PlantInformation;
//import groentjes.onzeMoestuin.repository.PlantInformationRepository;
//import groentjes.onzeMoestuin.repository.UserRepository;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import static org.mockito.ArgumentCaptor.forClass;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//
///**
// * @author Eric van Dalen
// * Test class for managing PlantInformation
// */
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = PlantInformationController.class)
//class PlantInformationControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private GardenUserDetailsService gardenUserDetailsService;
//
//    @MockBean
//    private PlantInformationRepository plantInformationRepository;
//
//    private String plantName = "plantnaam";
//    private String latinName = "Latijnse plantnaam";
//    private Integer plantingDistance = 1;
//    private String lighting = "zon";
//    private String soilType = "kleigrond";
//    private String sowingStart = "januari";
//    private String sowingEnd =  "februari";
//    private String plantingStart = "maart";
//    private String plantingEnd = "april";
//    private String harvestingStart = "mei";
//    private String harvestingEnd = "juni";
//    private Integer growTime = 2;
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testManagePlantInfoWithRoleAdministrator() throws Exception {
//        final ResultActions result = mockMvc.perform(get("/adminManagePlantInformation")).andExpect(status()
//                .isOk()).andExpect(forwardedUrl("/WEB-INF/views/adminManagePlantInformation.jsp"));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testGetPlantInfoFormWithRoleAdministrator() throws Exception {
//        final ResultActions result = mockMvc.perform(get("/admincreateplantinfo")).andExpect(status().isOk())
//                .andExpect(forwardedUrl("/WEB-INF/views/adminCreatePlantInformation.jsp"));
//    }
//
//    @Test
//    @WithMockUser(roles = "ADMIN")
//    void testSaveNewPlantInfoWithRoleAdminstrator() throws Exception {
//        performMockMvc();
//
//        ArgumentCaptor<PlantInformation> formObjectArgument = forClass(PlantInformation.class);
//        verify(plantInformationRepository, times(1)).save(formObjectArgument.capture());
//        Mockito.verifyNoMoreInteractions(plantInformationRepository);
//
//        PlantInformation formObject = formObjectArgument.getValue();
//        doAssertions(formObject);
//    }
//
//    private void performMockMvc() throws Exception {
//        mockMvc.perform(post("/admincreateplantinfo").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("plantName" , plantName).param("latinName", latinName)
//                .param("plantingDistance", String.valueOf(plantingDistance)).param("lighting", lighting)
//                .param("soilType", soilType).param("sowingStart", sowingStart)
//                .param("sowingEnd", sowingEnd).param("plantingStart", plantingStart)
//                .param("plantingEnd", plantingEnd).param("harvestingStart", harvestingStart)
//                .param("harvestingEnd", harvestingEnd).param("growTime", String.valueOf(growTime))
//                .flashAttr("plantInformation", new PlantInformation()).with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/adminManagePlantInformation"))
//                .andExpect(redirectedUrl("/adminManagePlantInformation"));
//    }
//
//    private void doAssertions(PlantInformation formObject) {
//        Assertions.assertThat(formObject.getPlantName()).isEqualTo(plantName);
//        Assertions.assertThat(formObject.getLatinName()).isEqualTo(latinName);
//        Assertions.assertThat(formObject.getPlantingDistance()).isEqualTo(plantingDistance);
//        Assertions.assertThat(formObject.getLighting()).isEqualTo(lighting);
//        Assertions.assertThat(formObject.getSoilType()).isEqualTo(soilType);
//        Assertions.assertThat(formObject.getSowingStart()).isEqualTo(sowingStart);
//        Assertions.assertThat(formObject.getSowingEnd()).isEqualTo(sowingEnd);
//        Assertions.assertThat(formObject.getPlantingStart()).isEqualTo(plantingStart);
//        Assertions.assertThat(formObject.getPlantingEnd()).isEqualTo(plantingEnd);
//        Assertions.assertThat(formObject.getHarvestingStart()).isEqualTo(harvestingStart);
//        Assertions.assertThat(formObject.getHarvestingEnd()).isEqualTo(harvestingEnd);
//        Assertions.assertThat(formObject.getGrowTime()).isEqualTo(growTime);
//    }
//}