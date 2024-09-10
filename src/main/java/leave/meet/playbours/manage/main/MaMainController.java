package leave.meet.playbours.manage.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ma/main/")
public class MaMainController {

    @RequestMapping(value = "main")
    public String maHome() {
        return "pages/manage/main/main";
    }

}
