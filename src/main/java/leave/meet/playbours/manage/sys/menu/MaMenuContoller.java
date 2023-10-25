package leave.meet.playbours.manage.sys.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaMenuContoller {

    @RequestMapping("/tables")
    public String tables() {
        return "pages/manage/tables2";
    }

}
