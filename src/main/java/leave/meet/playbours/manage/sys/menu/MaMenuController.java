package leave.meet.playbours.manage.sys.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MaMenuController {

    private final static String FOLDER_PATH = "/ma/sys/menu/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list() {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList() {
        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@RequestParam("id") String id, @PathVariable String procType) {
        return "pages/manage/sys/menu/form";
    }

}
