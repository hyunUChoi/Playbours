package leave.meet.playbours.manage.sys.menu.controller;

import leave.meet.playbours.manage.sys.menu.service.MaMenuService;
import leave.meet.playbours.manage.sys.menu.service.MaMenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/menu/")
public class MaMenuController {

    private final MaMenuService maMenuService;

    private final SmartValidator validator;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(value = "{procType:view|list}AddList")
    public String addList(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, @PageableDefault(size = 10) Pageable pageable, Model model) {

        maMenuVO.setProcType(procType);
        Object resultList;

        if(procType.equalsIgnoreCase("view")) {
            maMenuVO.setUpperCd(maMenuVO.getMenuCd());
            resultList = maMenuService.selectLowerList(maMenuVO);   // List<MaMenuVO>
        } else {
            resultList = maMenuService.selectList(maMenuVO, pageable);  // Page<MaMenuVO>
        }
        int total = maMenuService.selectCount(maMenuVO);

        model.addAttribute("total", total);
        model.addAttribute("resultList", resultList);
        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(value = "{procType:insert|lowerInsert|update|lowerUpdate}Form")
    public String form(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, Model model) {

        if(model.containsAttribute("bindingResult")) {
            return "pages/manage/sys/menu/form";
        }

        MaMenuVO menuVO = new MaMenuVO();
        // 하위등록 취소 시 view로 돌아가기 위한 파라미터
        menuVO.setSeq(maMenuVO.getSeq());
        menuVO.setUpperCd(maMenuVO.getUpperCd());

        if(procType.equals("update") || procType.equals("lowerUpdate")) {
            menuVO = maMenuService.selectContents(maMenuVO, procType);
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
    public String proc(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, BindingResult bindingResult, @PathVariable String procType, RedirectAttributes redirectAttributes) {

        switch (procType) {
            case "insert" -> validator.validate(maMenuVO, bindingResult, MaMenuVO.insert.class);
            case "lowerInsert" -> validator.validate(maMenuVO, bindingResult, MaMenuVO.lowerInsert.class);
            case "update", "lowerUpdate" -> validator.validate(maMenuVO, bindingResult, MaMenuVO.update.class);
        }

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingResult",
                    bindingResult.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            redirectAttributes.addFlashAttribute("maMenuVO", maMenuVO);

            switch (procType) {
                case "insert" -> {
                    return "redirect:/ma/sys/menu/list";
                }
                case "lowerInsert", "lowerUpdate" -> {
                    return "redirect:/ma/sys/menu/viewView";
                }
                case "update" -> {
                    return "redirect:/ma/sys/menu/listView";
                }
            }
        }

        switch (procType) {
            case "insert" -> {
                maMenuService.insertContents(maMenuVO);
                return "redirect:/ma/sys/menu/list";
            }
            case "lowerInsert" -> {
                maMenuService.insertContents(maMenuVO);
                redirectAttributes.addFlashAttribute("maMenuVO", maMenuVO);
                return "redirect:/ma/sys/menu/listView";
            }
            case "update", "lowerUpdate" -> {
                maMenuService.updateContents(maMenuVO);
                redirectAttributes.addFlashAttribute("maMenuVO", maMenuVO);
                return "redirect:/ma/sys/menu/listView";
            }
            case "delete" -> {
                maMenuService.deleteContents(maMenuVO);
                return "redirect:/ma/sys/menu/list";
            }
            case "lowerDelete" -> {
                maMenuService.deleteContents(maMenuVO);
                redirectAttributes.addFlashAttribute("maMenuVO", maMenuVO);
                return "redirect:/ma/sys/menu/viewView";
            }
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping("{procType:view|list}View")
    public String view(@ModelAttribute("maMenuVO") MaMenuVO maMenuVO, @PathVariable String procType, Model model) {

        if(model.containsAttribute("bindingResult")) {
            return "pages/manage/sys/menu/view";
        }

        MaMenuVO searchVO = maMenuVO;
        maMenuVO = maMenuService.selectContents(maMenuVO, procType);
        // 검색 조건 저장
        maMenuVO.setSearch(searchVO);
        model.addAttribute("maMenuVO", maMenuVO);

        return "pages/manage/sys/menu/view";
    }

}
