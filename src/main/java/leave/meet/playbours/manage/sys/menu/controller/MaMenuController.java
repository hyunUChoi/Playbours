package leave.meet.playbours.manage.sys.menu.controller;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
public class MaMenuController {

    private final MaMenuRepository menuRepository;

    private final static String FOLDER_PATH = "/ma/sys/menu/";

    public MaMenuController(MaMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, Model model) {

        List<MaMenuDto> resultList = menuRepository.findAll();
        model.addAttribute("resultList", resultList);

        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, Model model) {

        if(procType.equals("update")) {
            maMenuDto = menuRepository.findOne(maMenuDto);
        }

        model.addAttribute("maMenuDto", maMenuDto);

        return "pages/manage/sys/menu/form";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType) {

        switch (procType) {
            case "insert" -> {
                menuRepository.insert(maMenuDto);
                return "redirect:" + FOLDER_PATH + "list";
            }
            case "update" -> {
                menuRepository.update(maMenuDto);
                return "redirect:" + FOLDER_PATH + "view?seq=" + maMenuDto.getSeq();
            }
            case "delete" -> {
                menuRepository.delete(maMenuDto);
                return "redirect:" + FOLDER_PATH + "list";
            }
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "view")
    public String view(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, Model model) {
        model.addAttribute("maMenuDto", menuRepository.findOne(maMenuDto));
        return "pages/manage/sys/menu/view";
    }

}
