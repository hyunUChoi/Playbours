package leave.meet.playbours.manage.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaMainController {

    private final static String FOLDER_PATH = "/ma/main/";

    @RequestMapping(FOLDER_PATH + "main")
    public String maHome() {
        return "pages/manage/main/main";
    }

}
