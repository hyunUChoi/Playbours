package leave.meet.playbours.manage.sys.menu.controller;

import leave.meet.playbours.manage.sys.menu.service.dto.MaMenuDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaMenuController {

    private final static String FOLDER_PATH = "/ma/sys/menu/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto) {
        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType) {
        return "pages/manage/sys/menu/form";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType) {
        if(procType.equals("insert")) {

        }

        return "pages/manage/sys/menu/form";
    }

}
