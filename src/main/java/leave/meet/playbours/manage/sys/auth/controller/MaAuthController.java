package leave.meet.playbours.manage.sys.auth.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/auth/")
public class MaAuthController {

    private final MaAuthService maAuthService;

    @Resource(name = "pagingService")
    private PagingService pagingService;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO) {
        return "pages/manage/sys/menu/list";
    }

    @RequestMapping(value = "{procType:view|list}AddList")
    public String addList(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO, @PathVariable String procType, Model model) {

        int pageNo = maAuthVO.getPageNo();
        int pageSize = 10;
        List<MaAuthVO> resultList = maAuthService.selectList(maAuthVO);

        model.addAttribute("resultList", resultList);

        return "pages/manage/sys/menu/addList";
    }

    @RequestMapping(value = "{procType:insert|lowerInsert|update|lowerUpdate}Form")
    public String form(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO, @PathVariable String procType, Model model) {

        MaAuthVO authVO = new MaAuthVO();
        // 하위등록 취소 시 view로 돌아가기 위한 파라미터
        authVO.setSeq(maAuthVO.getSeq());

        if(procType.equals("update") || procType.equals("lowerUpdate")) {
            //menuVO = menuRepository.findOne(maMenuEntity);
        }

        model.addAttribute("maAuthVO", maAuthVO);

        return "pages/manage/sys/menu/form";
    }

    @RequestMapping("{procType:insert|lowerInsert|update|lowerUpdate|delete|lowerDelete}Proc")
    public String proc(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO, BindingResult bindingResult, @PathVariable String procType, RedirectAttributes attributes) {

        /*validator.validate(maAuthVO, bindingResult, maAuthVO.insert.class);
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
        }*/

        switch (procType) {
            case "insert" -> {
                maAuthService.insertContents(maAuthVO);
                return "redirect:/ma/sys/menu/list";
            }
            /*
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
            }*/
        }

        return "pages/manage/sys/menu/list";
    }

    @RequestMapping("{procType:view|list}View")
    public String view(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO, @PathVariable String procType, Model model) {

        if(procType.equals("view")) {
            //maAuthVO = menuRepository.findOneByCode(maMenuEntity);
        } else {
            maAuthVO = maAuthService.selectContents(maAuthVO);
        }

        // 검색 조건 저장
        maAuthVO.setSearch(maAuthVO);
        model.addAttribute("maAuthVO", maAuthVO);

        return "pages/manage/sys/menu/view";
    }

}
