package leave.meet.playbours.manage.sys.auth.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import leave.meet.playbours.manage.sys.auth.MaAuthRepository;
import leave.meet.playbours.manage.sys.auth.service.MaAuthService;
import leave.meet.playbours.manage.sys.menu.dto.MaMenuDto;
import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class MaAuthController {

    private final MaAuthService authService;
    private final MaMenuRepository menuRepository;
    @Resource(name= "pagingService")
    private PagingService pagingService;

    public MaAuthController(MaAuthService authService, MaMenuRepository menuRepository, PagingService pagingService) {
        this.authService = authService;
        this.menuRepository = menuRepository;
        this.pagingService = pagingService;
    }

    private final static String FOLDER_PATH = "/ma/sys/auth/";


    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto) {
        return "/pages/manage/sys/auth/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto, Model model) {

        //int pageNo = maAuthDto.getPageNo();
        //int pageSize = 10;
        //List<MaAuthDto> resultList = authService.findAll();

        //PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);

        //model.addAttribute("resultList", resultList);
        //model.addAttribute("paging", paging);

        return "pages/manage/sys/auth/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto, @PathVariable String procType, Model model) {

        if("update".equals(procType)){
            //maAuthDto = authService.findById(maAuthDto.getSeq());
        }

        // 1차 메뉴
        MaMenuDto menuDto = new MaMenuDto();
        menuDto.setMenuClCd("ma");
        menuDto.setUpperCd("");
        List<MaMenuDto> menuList1 = menuRepository.findMenuList(menuDto);
        menuDto.setMenuList(menuList1);

        // 2차 메뉴
        for(MaMenuDto dto2 : menuDto.getMenuList()) {
            dto2.setMenuClCd("ma");
            dto2.setUpperCd(dto2.getMenuCd());
            List<MaMenuDto> menuList2 = menuRepository.findMenuList(dto2);
            dto2.setMenuList(menuList2);

            // 3차 메뉴
            for(MaMenuDto dto3 : dto2.getMenuList()) {
                dto3.setMenuClCd("ma");
                dto3.setUpperCd(dto3.getMenuCd());
                List<MaMenuDto> menuList3 = menuRepository.findMenuList(dto3);
                dto3.setMenuList(menuList3);
                dto2.setMaMenuDto(dto3);
            }

            menuDto.setMaMenuDto(dto2);
        }
        //maAuthDto.setProcType(procType);
        model.addAttribute("menuList", menuDto.getMenuList());

        return "pages/manage/sys/auth/form";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto, @PathVariable String procType, Model model) {


        try{
            if("insert".equals(procType)){
                authService.createMaAuth(maAuthDto.toEntity());
            }else if("update".equals(procType)){
                authService.createMaAuth(maAuthDto.toEntity());
            }else if("delete".equals(procType)){
                //authService.delete(maAuthDto);
            }
            model.addAttribute("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        return "redirect:/ma/sys/auth/list";
    }

}
