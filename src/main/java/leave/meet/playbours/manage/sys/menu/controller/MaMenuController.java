package leave.meet.playbours.manage.sys.menu.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/menu/")
public class MaMenuController {

    private final MaMenuService maMenuService;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO) {
        return "pages/manage/sys/menu/list";
    }
    /*
    @RequestMapping("{procType:view|list}AddList")
    public String addList(@ModelAttribute("maMenuDto") MaMenuEntity maMenuEntity, @PathVariable String procType, Model model) {

        int pageNo = maMenuEntity.getPageNo();
        int pageSize = 10;
        Page<MaMenuEntity> resultList = menuRepository.findByPagingAndFiltering(pageNo, pageSize, maMenuEntity, procType);

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping("{procType:insert|lowerInsert|update|lowerUpdate}Form")
    public String form(@ModelAttribute("maMenuDto") MaMenuEntity maMenuEntity, @PathVariable String procType, Model model) {

        MaMenuEntity menuDto = new MaMenuEntity();
        *//* 하위등록 취소 시 view로 돌아가기 위한 파라미터 *//*
        *//*menuDto.setSeq(maMenuEntity.getSeq());
        menuDto.setUpperCd(maMenuEntity.getUpperCd());*//*

        if(procType.equals("update") || procType.equals("lowerUpdate")) {
            menuDto = menuRepository.findOne(maMenuEntity);
        }

        model.addAttribute("menuDto", menuDto);

        return "pages/manage/sys/menu/form";
    }

    @ResponseBody
    @RequestMapping("chkOverlap")
    public HashMap<String, String> chkOverlap(@RequestBody HashMap<String, Object> body) {

        HashMap<String, String> returnMap = new HashMap<>();
        int count = menuRepository.countByCode((String) body.get("menuCd"));

        if(count > 0) {
            returnMap.put("result", "false");
        } else {
            returnMap.put("result", "true");
        }

        return returnMap;
    }

    @RequestMapping("{procType:insert|lowerInsert|update|lowerUpdate|delete|lowerDelete}Proc")
    public String proc(@ModelAttribute("maMenuDto") MaMenuEntity maMenuEntity, @PathVariable String procType, RedirectAttributes attributes) {

        switch (procType) {
            case "insert" -> {
                menuRepository.insert(maMenuEntity);
                return "redirect:/ma/sys/menu/list";
            }
            case "lowerInsert" -> {
                menuRepository.insert(maMenuEntity);
                attributes.addFlashAttribute("maMenuDto", maMenuEntity);
                return "redirect:/ma/sys/menu/listView";
            }
            case "update", "lowerUpdate" -> {
                menuRepository.update(maMenuEntity);
                attributes.addFlashAttribute("maMenuDto", maMenuEntity);
                return "redirect:/ma/sys/menu/listView";
            }
            case "delete" -> {
                menuRepository.delete(maMenuEntity);
                return "redirect:/ma/sys/menu/list";
            }
            case "lowerDelete" -> {
                menuRepository.delete(maMenuEntity);
                attributes.addFlashAttribute("maMenuDto", maMenuEntity);
                return "redirect:/ma/sys/menu/viewView";
            }
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping("{procType:view|list}View")
    public String view(@ModelAttribute("maMenuDto") MaMenuEntity maMenuEntity, @PathVariable String procType, Model model) {

        MaMenuEntity searchVO = maMenuEntity;

        if(procType.equals("view")) {
            maMenuEntity = menuRepository.findOneByCode(maMenuEntity);
        } else {
            maMenuEntity = menuRepository.findOne(maMenuEntity);
        }

        // 검색 조건 저장
        maMenuEntity.setSearch(searchVO);
        model.addAttribute("maMenuDto", maMenuEntity);

        return "pages/manage/sys/menu/view";
    }*/

}
