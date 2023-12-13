package leave.meet.playbours.manage.member.user.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.dto.PagingDto;
import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.member.user.service.MaUserDto;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.service.MaCodeDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class MaUserController {

    private final MaUserRepository userRepository;

    private final MaCodeRepository codeRepository;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    public MaUserController(MaUserRepository userRepository, MaCodeRepository codeRepository, PagingService pagingService) {
        this.userRepository = userRepository;
        this.codeRepository = codeRepository;
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
        userDto.setProcType(procType);
        model.addAttribute("userDto", userDto);

        return HTML_PATH + "form";
    }

    @ResponseBody
    @RequestMapping(FOLDER_PATH + "chkOverlap")
    public HashMap<String, String> chkOverlap(@RequestBody HashMap<String, Object> body) {

        HashMap<String, String> returnMap = new HashMap<>();
        int count = userRepository.countById((String) body.get("userId"));

        if(count > 0) {
            returnMap.put("result", "false");
        } else {
            returnMap.put("result", "true");
        }

        return returnMap;
    }

    @RequestMapping(FOLDER_PATH + "view")
    public String view(@ModelAttribute("maUserDto") MaUserDto maUserDto,Model model) {

        maUserDto = userRepository.findOne(maUserDto);
        model.addAttribute("maUserDto", maUserDto);
        return HTML_PATH + "view";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maUserDto") MaUserDto maUserDto,@PathVariable String procType,Model model) {

        if("insert".equals(procType)){
            userRepository.insert(maUserDto);
            return "redirect:" + HTML_PATH + "list";
        }else if("update".equals(procType)){
            userRepository.update(maUserDto);
            return "redirect:" + HTML_PATH + "view";
        }
        return HTML_PATH + "form";
    }


}
