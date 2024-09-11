package leave.meet.playbours.manage.sys.auth.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.auth.service.MaAuthVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/ma/sys/menu/")
public class MaAuthController {

    private final MaAuthService maAuthService;

    @Resource(name = "pagingService")
    private PagingService pagingService;

    @RequestMapping(value = "list")
    public String list(@ModelAttribute("maAuthVO") MaAuthVO maAuthVO) {
        return "pages/manage/sys/auth/list";
    }

}
