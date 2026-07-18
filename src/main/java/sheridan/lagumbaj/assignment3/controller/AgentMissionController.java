package sheridan.lagumbaj.assignment3.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AgentMissionController {

    @GetMapping({"/", "/index"})
    public String showIndex() {
        return "index";
    }
}
