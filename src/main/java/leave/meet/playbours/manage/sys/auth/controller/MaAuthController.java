package leave.meet.playbours.manage.sys.auth.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/auth/")
public class MaAuthController {

    private final MaAuthService maAuthService;

    @Resource(name = "pagingService")
    private PagingService pagingService;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO, Model model) throws Exception {

        List<MaAuthVO> resultList = maAuthService.selectList();
        int resultCount = maAuthService.selectCount();
        model.addAttribute("resultList",resultList);
        model.addAttribute("resultCount",resultCount);

        return "pages/manage/sys/auth/list";
    }

}
