//package groentjes.onzeMoestuin.controller;
//
//import groentjes.onzeMoestuin.model.*;
//import groentjes.onzeMoestuin.repository.*;
//import groentjes.onzeMoestuin.service.GardenUserDetailsService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import java.util.Optional;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @author Eric van Dalen
// * Tests concerning the functionality around task plant completion
// */
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = TaskPlantController.class)
//class TaskPlantControllerTest {
//
//    private static final int ONE = 1;
//    private static final int TWO = 2;
//    private static final int THREE = 3;
//    private static final int EIGHT = 8;
//    private static final String DATE = "25-01-2020";
//
//    private TaskPlant taskPlant;
//    private TaskPlantInfo taskPlantInfo;
//    private Plant plant;
//    private Garden garden;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GardenUserDetailsService gardenUserDetailsService;
//
////
//
//
//    @MockBean
//    private PlantRepository plantRepository;
//
//    @MockBean
//    private GardenRepository gardenRepository;
//
//    @MockBean
//    private TaskPlantRepository taskPlantRepository;
//
//    @MockBean
//    private TaskGardenRepository taskGardenRepository;
//
//    @MockBean
//    private TaskRepository taskRepository;
//
//    @BeforeEach
//    void setUp() {
//        taskPlant = new TaskPlant();
//        taskPlant.setTaskId(TWO);
//        taskPlantInfo = new TaskPlantInfo();
//        taskPlantInfo.setDaysAfterStart(EIGHT);
//        taskPlant.setTaskPlantInfo(taskPlantInfo);
//        taskPlant.setDueDate(DATE);
//        taskPlant.setCompletedDate(null);
//        taskPlant.setUser(null);
//        plant = new Plant();
//        taskPlant.setPlant(plant);
//        garden = new Garden();
//        garden.setGardenId(ONE);
//        plant.setGarden(garden);
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void testProcessCompletedTaskPlantWithExistingTaskPlant() throws Exception {
//        String taskId = taskPlant.getTaskId().toString();
//
//        boolean expectedisCompleted = true;
//
//        Mockito.when(taskRepository.findById(TWO))
//                .thenReturn(Optional.ofNullable(taskPlant));
//
//        final ResultActions result = mockMvc.perform(get("/user/task/completed/" + taskId)
//                .sessionAttr("taskId", taskId)).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/garden/" + garden.getGardenId()));
//
//        boolean foundisCompleted = (taskPlant.getCompletedDate() != null);
//
//        Assertions.assertEquals(expectedisCompleted, foundisCompleted);
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    void testProcessCompletedTaskPlantWithNonExistingTaskPlant() throws Exception {
//        String taskId = taskPlant.getTaskId().toString();
//
//        boolean expectedisCompleted = false;
//
//        Mockito.when(taskRepository.findById(THREE))
//                .thenReturn(Optional.ofNullable(taskPlant));
//
//        final ResultActions result = mockMvc.perform(get("/user/task/completed/" + taskId)
//                .sessionAttr("taskId", taskId)).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/" ));
//
//        boolean foundisCompleted = (taskPlant.getCompletedDate() != null);
//
//        Assertions.assertEquals(expectedisCompleted, foundisCompleted);
//    }
//}