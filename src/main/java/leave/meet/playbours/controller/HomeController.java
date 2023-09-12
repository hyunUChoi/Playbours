package leave.meet.playbours.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "pages/index";
    }

    @RequestMapping("/browse")
    public String browse() {
        return "pages/browse";
    }
}
