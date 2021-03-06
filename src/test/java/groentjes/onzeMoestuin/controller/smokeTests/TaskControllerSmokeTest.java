package groentjes.onzeMoestuin.controller.smokeTests;

import groentjes.onzeMoestuin.controller.TaskController;
import groentjes.onzeMoestuin.controller.UserController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerSmokeTest {

    @Autowired
    private TaskController controller;

    @Test
    public void contextloads() throws Exception {
        Assert.assertNotNull(controller);
    }
}
