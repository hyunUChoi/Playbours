package leave.meet.playbours.manage.sys.menu.controller;

import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(FOLDER_PATH + "{procType}AddList")
    public String addList(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, Model model, @RequestBody Map<String, Object> body) {

        Page<MaMenuDto> resultList;
        HashMap<String, String> paramMap = new HashMap<>();

        if(procType.equals("list")) {
            paramMap.put("upperCd", "");
        } else {
            paramMap.put("upperCd", (String) body.get("param"));
        }

        resultList = menuRepository.findByPagingAndFiltering(0, 10, paramMap);

        model.addAttribute("resultList", resultList);

        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, Model model) {

        MaMenuDto menuDto = new MaMenuDto();
        menuDto.setSeq(maMenuDto.getSeq());
        menuDto.setUpperCd(maMenuDto.getUpperCd());

        if(procType.equals("update") || procType.equals("lowerUpdate")) {
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
    public String proc(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, RedirectAttributes attributes) {

        switch (procType) {
            case "insert" -> {
                menuRepository.insert(maMenuDto);
                return "redirect:" + FOLDER_PATH + "list";
            }
            case "lowerInsert" -> {
                menuRepository.insert(maMenuDto);
                attributes.addFlashAttribute("maMenuDto", maMenuDto);
                return "redirect:" + FOLDER_PATH + "listView";
            }
            case "update", "lowerUpdate" -> {
                menuRepository.update(maMenuDto);
                attributes.addFlashAttribute("maMenuDto", maMenuDto);
                return "redirect:" + FOLDER_PATH + "listView";
            }
            case "delete" -> {
                menuRepository.delete(maMenuDto);
                return "redirect:" + FOLDER_PATH + "list";
            }
            case "lowerDelete" -> {
                menuRepository.delete(maMenuDto);
                attributes.addFlashAttribute("maMenuDto", maMenuDto);
                return "redirect:" + FOLDER_PATH + "viewView";
            }
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "{procType}View")
    public String view(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, Model model) {

        if(procType.equals("view")) {
            model.addAttribute("maMenuDto", menuRepository.findOneByCode(maMenuDto.getUpperCd()));
        } else {
            model.addAttribute("maMenuDto", menuRepository.findOne(maMenuDto.getSeq()));
        }

        return "pages/manage/sys/menu/view";
    }

}
