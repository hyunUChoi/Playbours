package leave.meet.playbours.manage.member.user.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.dto.PagingDto;
import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.member.user.service.MaUserDto;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaUserController {

    private final MaUserRepository userRepository;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    public MaUserController(MaUserRepository userRepository, PagingService pagingService) {
        this.userRepository = userRepository;
        this.pagingService = pagingService;
    }

    private final static String FOLDER_PATH = "/ma/member/user/";
    private final static String HTML_PATH = "/pages/manage/member/user/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maUserDto") MaUserDto maUserDto) {

        return "/pages/manage/member/user/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maUserDto") MaUserDto maUserDto, Model model) {

        int pageNo = maUserDto.getPageNo();
        int pageSize = 10;
        Page<MaUserDto> resultList = userRepository.findByPagingAndFiltering(pageNo, pageSize, maUserDto);

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return "/pages/manage/member/user/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maUserDto") MaUserDto maUserDto,@PathVariable String procType,Model model) {

        if("insert".equals(procType)){

        }else if("update".equals(procType)){

        }
        return HTML_PATH + "form";
    }

}
