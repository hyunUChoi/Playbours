package leave.meet.playbours.manage.member.word.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.member.user.dto.MaUserDto;
import leave.meet.playbours.manage.member.word.dto.MaWordDto;
import leave.meet.playbours.manage.member.word.repository.MaWordRepository;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
public class MaWordController {

    private final MaWordRepository wordRepository;
    @Resource(name= "pagingService")
    private PagingService pagingService;
    public MaWordController(MaWordRepository wordRepository,PagingService pagingService) {

        this.wordRepository = wordRepository;
        this.pagingService = pagingService;
    }

    private final static String FOLDER_PATH = "/ma/member/word/";

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maWordDto") MaWordDto maWordDto, Model model) {

        return "/pages/manage/member/word/list";
    }
    @RequestMapping(FOLDER_PATH + "addList")
    public String addList(@ModelAttribute("maWordDto") MaWordDto maWordDto, Model model) {

        int pageNo = maWordDto.getPageNo();
        int pageSize = 10;
        Page<MaWordDto> resultList = wordRepository.findByPagingAndFiltering(pageNo, pageSize,maWordDto);

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);
        paging.setTotalRecord(wordRepository.countTotalRecords(maWordDto));

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

        return "/pages/manage/member/word/addList";
    }
}
