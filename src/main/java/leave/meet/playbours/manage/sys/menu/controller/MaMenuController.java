package leave.meet.playbours.manage.sys.menu.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/menu/")
public class MaMenuController {

    private final MaMenuService maMenuService;

    private SmartValidator validator;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(value = "{procType:view|list}AddList")
    public String addList(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, Model model) {

        int pageNo = maMenuVO.getPageNo();
        int pageSize = 10;

        maMenuVO.setProcType(procType);
        if(procType.equalsIgnoreCase("view")) {
            maMenuVO.setUpperCd(maMenuVO.getMenuCd());
        }
        List<MaMenuVO> resultList = maMenuService.selectList(maMenuVO);

        model.addAttribute("resultList", resultList);

        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(value = "{procType:insert|lowerInsert|update|lowerUpdate}Form")
    public String form(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, Model model) {

        MaMenuVO menuVO = new MaMenuVO();
        // 하위등록 취소 시 view로 돌아가기 위한 파라미터
        menuVO.setSeq(maMenuVO.getSeq());
        menuVO.setUpperCd(maMenuVO.getUpperCd());

        if(procType.equals("update") || procType.equals("lowerUpdate")) {
            menuVO = maMenuService.selectContents(maMenuVO);
        }

        model.addAttribute("menuVO", menuVO);

        return "pages/manage/sys/menu/form";
    }

    @ResponseBody
    @RequestMapping(value = "chkOverlap")
    public ResponseEntity<MaMenuVO> chkOverlap(@RequestBody MaMenuVO maMenuVO) {
        HttpStatus status = maMenuService.selectCodeCount(maMenuVO) > 0 ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.OK;
        return new ResponseEntity<>(maMenuVO, status);
    }

    @RequestMapping("{procType:insert|lowerInsert|update|lowerUpdate|delete|lowerDelete}Proc")
    public String proc(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, BindingResult bindingResult, @PathVariable String procType, RedirectAttributes attributes) {

        /*validator.validate(maMenuVO, bindingResult, MaMenuVO.insert.class);
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        }*/

        switch (procType) {
            case "insert" -> {
                maMenuService.insertContents(maMenuVO);
                return "redirect:/ma/sys/menu/list";
            }
            /*
            case "lowerInsert" -> {
                menuRepository.insert(maMenuEntity);
                attributes.addFlashAttribute("maMenuDto", maMenuEntity);
                return "redirect:/ma/sys/menu/listView";
            }
            */
            case "update", "lowerUpdate" -> {
                maMenuService.updateContents(maMenuVO);
                attributes.addFlashAttribute("maMenuVO", maMenuVO);
                return "redirect:/ma/sys/menu/listView";
            }
            /*
            case "delete" -> {
                menuRepository.delete(maMenuEntity);
                return "redirect:/ma/sys/menu/list";
            }
            case "lowerDelete" -> {
                menuRepository.delete(maMenuEntity);
                attributes.addFlashAttribute("maMenuDto", maMenuEntity);
                return "redirect:/ma/sys/menu/viewView";
            }*/
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping("{procType:view|list}View")
    public String view(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, Model model) {

        if(procType.equals("view")) {
            //maMenuVO = menuRepository.findOneByCode(maMenuEntity);
        } else {
            maMenuVO = maMenuService.selectContents(maMenuVO);
        }

        // 검색 조건 저장
        maMenuVO.setSearch(maMenuVO);
        model.addAttribute("maMenuVO", maMenuVO);

        return "pages/manage/sys/menu/view";
    }

}
