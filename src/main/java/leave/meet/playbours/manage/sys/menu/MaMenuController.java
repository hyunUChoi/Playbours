package leave.meet.playbours.manage.sys.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaMenuController {

    private final static String FOLDER_PATH = "/ma/sys/menu/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list() {
        return "pages/manage/sys/menu/tables2";
    }

}
