package leave.meet.playbours.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ManageHomeController {

    @RequestMapping("/ma")
    public String maHome() {
        return "pages/manage/index";
    }

}
