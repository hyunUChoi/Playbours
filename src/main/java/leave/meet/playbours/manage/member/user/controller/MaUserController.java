package leave.meet.playbours.manage.member.user.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        paging.setTotalRecord(userRepository.countTotalRecords(maUserDto));

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return "/pages/manage/member/user/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Form")
    public String form(@ModelAttribute("maUserDto") MaUserDto maUserDto,@PathVariable String procType,Model model) {

        MaUserDto userDto = new MaUserDto();
        if("update".equals(procType)){
            userDto = userRepository.findOne(maUserDto);
        }
        userDto.setProcType(procType);
        model.addAttribute("userDto", userDto);

        return "/pages/manage/member/user/form";
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

        System.out.println(maUserDto.getSeq());
        maUserDto = userRepository.findOne(maUserDto);
        model.addAttribute("maUserDto", maUserDto);
        return "/pages/manage/member/user/view";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maUserDto") MaUserDto maUserDto, @PathVariable String procType, RedirectAttributes attributes) {

        if("insert".equals(procType)){
            userRepository.insert(maUserDto);
            return "redirect:/ma/member/user/list";
        }else if("update".equals(procType)){
            System.out.println(procType);
            userRepository.update(maUserDto);
            attributes.addFlashAttribute("maUserDto", maUserDto);
            return "redirect:/ma/member/user/view";
        }else if ("delete".equals(procType)){
            userRepository.delete(maUserDto);
            return "redirect:/ma/member/user/list";
        }
        return "redirect:/ma/member/user/form";
    }


}
