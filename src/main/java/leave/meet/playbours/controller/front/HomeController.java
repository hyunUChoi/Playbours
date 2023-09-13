package leave.meet.playbours.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String home() {
        return "pages/front/index";
    }

    @RequestMapping("/browse")
    public String browse() {
        return "pages/front/browse";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "pages/front/profile";
    }

    @RequestMapping("/details")
    public String details() {
        return "pages/front/details";
    }

    @RequestMapping("/streams")
    public String streams() {
        return "pages/front/streams";
    }
}
