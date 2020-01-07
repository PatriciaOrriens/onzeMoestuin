package groentjes.onzeMoestuin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontPageController {

    @GetMapping("/")
    public String getFrontPage() {
        return "frontPage";
    }

}
