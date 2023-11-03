package leave.meet.playbours.manage.sys.menu.controller;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        MaMenuDto menuDto = new MaMenuDto();
        menuDto.setUpperCd(maMenuDto.getUpperCd());

        if(procType.equals("update")) {
            menuDto = menuRepository.findOne(maMenuDto.getSeq());
        }

        model.addAttribute("menuDto", menuDto);

        return "pages/manage/sys/menu/form";
    }

    @ResponseBody
    @RequestMapping(FOLDER_PATH + "chkOverlap")
    public HashMap<String, String> chkOverlap(@RequestBody Map<String, Object> body) {

        HashMap<String, String> returnMap = new HashMap<>();
        int count = menuRepository.countByCode((String) body.get("menuCd"));

        if(count > 0) {
            returnMap.put("result", "false");
        } else {
            returnMap.put("result", "true");
        }

        return returnMap;
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
        model.addAttribute("maMenuDto", menuRepository.findOne(maMenuDto.getSeq()));
        return "pages/manage/sys/menu/view";
    }

}
