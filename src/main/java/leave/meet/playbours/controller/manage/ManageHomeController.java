package leave.meet.playbours.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageHomeController {

    @RequestMapping("/ma")
    public String maHome() {
        return "pages/manage/index";
    }
}
