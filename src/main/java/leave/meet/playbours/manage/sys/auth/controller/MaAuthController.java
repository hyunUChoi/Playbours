package leave.meet.playbours.manage.sys.auth.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.sys.auth.dto.MaAuthDto;
import leave.meet.playbours.manage.sys.auth.repository.MaAuthRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class MaAuthController {

    private final MaAuthRepository authRepository;
    @Resource(name= "pagingService")
    private PagingService pagingService;

    public MaAuthController(MaAuthRepository authRepository, PagingService pagingService) {
        this.authRepository = authRepository;
        this.pagingService = pagingService;
    }

    private final static String FOLDER_PATH = "/ma/sys/auth/";


    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto) {
        return "/pages/manage/sys/auth/list";
    }

    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto, Model model) {

        int pageNo = maAuthDto.getPageNo();
        int pageSize = 10;
        Page<MaAuthDto> resultList = authRepository.findByPagingAndFiltering(pageNo, pageSize, maAuthDto);

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return "pages/manage/sys/auth/addList";
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maAuthDto") MaAuthDto maAuthDto, @PathVariable String procType, Model model) {

        try{
            if("insert".equals(procType)){
                authRepository.update(maAuthDto);
            }else if("delete".equals(procType)){
                authRepository.delete(maAuthDto);
            }
            model.addAttribute("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        return "redirect:/ma/sys/auth/list";
    }

}
