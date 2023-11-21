package leave.meet.playbours.manage.sys.user.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.sys.menu.repository.MaMenuRepository;
import leave.meet.playbours.manage.sys.menu.service.MaMenuDto;
import leave.meet.playbours.manage.sys.user.repository.MaUserRepository;
import leave.meet.playbours.manage.sys.user.service.dto.MaUserDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MaUserController {

    /*private final MaUserRepository userRepository;*/

    @Resource(name= "pagingService")
    private PagingService pagingService;

    /*public MaUserController(MaUserRepository userRepository, PagingService pagingService) {
        this.userRepository = userRepository;
        this.pagingService = pagingService;
    }*/

    private final static String FOLDER_PATH = "/ma/sys/user/";
    private final static String HTML_PATH = "/pages/manage/sys/user/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list(MaUserDto maUserDto) {
        return  HTML_PATH +"list";
    }

    /*@RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maUserDto") MaUserDto maUserDto, @PathVariable String procType, Model model) {

        int pageNo = maUserDto.getPageNo();
        int pageSize = 10;
        Page<MaMenuDto> resultList = userRepository.findByPagingAndFiltering(pageNo, pageSize, maUserDto, procType);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", pagingService.getPageInfo(resultList, pageNo, pageSize));
        return HTML_PATH + "addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@PathVariable String procType) {

        if("insert".equals(procType)){

        }else if("update".equals(procType)){

        }
        return HTML_PATH + "form";
    }*/

}
