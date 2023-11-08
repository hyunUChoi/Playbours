package leave.meet.playbours.manage.sys.menu.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MaMenuController {

    private final MaMenuRepository menuRepository;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    private final static String FOLDER_PATH = "/ma/sys/menu/";

    public MaMenuController(MaMenuRepository menuRepository, PagingService pagingService) {
        this.menuRepository = menuRepository;
        this.pagingService = pagingService;
    }

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(FOLDER_PATH + "{procType}AddList")
    public String addList(@ModelAttribute("maMenuDto") MaMenuDto maMenuDto, @PathVariable String procType, Model model, @RequestBody Map<String, Object> body) {

        int pageNo = Integer.parseInt((String)body.get("page"));
        pageNo = pageNo == 0 ? 0 : pageNo - 1;
        int pageSize = 10;
        Page<MaMenuDto> resultList;
        HashMap<String, String> paramMap = new HashMap<>();

        /* 검색 조건 */
        paramMap.put("menuClCd", maMenuDto.getSearch1());
        paramMap.put("option", maMenuDto.getSearchOption());
        System.out.println(maMenuDto.getSearchOption());
        paramMap.put("keyword", maMenuDto.getSearchKeyword());

        if(procType.equals("list")) {
            paramMap.put("upperCd", "");
        } else {
            paramMap.put("upperCd", (String) body.get("menuCd"));
        }

        resultList = menuRepository.findByPagingAndFiltering(pageNo, pageSize, paramMap);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", pagingService.getPageInfo(resultList, pageNo, pageSize));
        model.addAttribute("nowPage", pageNo);

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
