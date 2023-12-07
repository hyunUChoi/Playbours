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

        return HTML_PATH + "list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maUserDto") MaUserDto maUserDto, Model model) {

        int pageNo = maUserDto.getPageNo();
        int pageSize = 10;
        Page<MaUserDto> resultList = userRepository.findByPagingAndFiltering(pageNo, pageSize, maUserDto);

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);
        paging.setTotalRecord(userRepository.countTotalRecords(maUserDto));

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return HTML_PATH + "addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maUserDto") MaUserDto maUserDto,@PathVariable String procType,Model model) {

        MaUserDto userDto = new MaUserDto();
        if("update".equals(procType)){
            userDto = userRepository.findOne(maUserDto);
        }
        model.addAttribute("userDto", userDto);

        return HTML_PATH + "form";
    }

}
