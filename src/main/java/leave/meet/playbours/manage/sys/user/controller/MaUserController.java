package leave.meet.playbours.manage.sys.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MaUserController {

    private final static String FOLDER_PATH = "/ma/sys/user/";
    private final static String HTML_PATH = "/pages/manage/sys/user/";
    @RequestMapping(FOLDER_PATH + "list")
    public String list() {
        return  "/pages/manage/sys/user/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList() {
        return HTML_PATH + "addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@PathVariable String procType) {

        if("insert".equals(procType)){

        }else if("update".equals(procType)){

        }
        return HTML_PATH + "form";
    }

}
