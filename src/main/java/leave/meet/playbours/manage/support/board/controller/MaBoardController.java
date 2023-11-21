package leave.meet.playbours.manage.support.board.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.support.board.repository.MaBoardRepository;
import leave.meet.playbours.manage.support.board.service.MaBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaBoardController {

    private final MaBoardRepository boardRepository;

    @Resource(name= "pagingService")
    private PagingService pagingService;

    private final static String FOLDER_PATH = "/ma/support/board/";

    public MaBoardController(MaBoardRepository boardRepository, PagingService pagingService) {
        this.boardRepository = boardRepository;
        this.pagingService = pagingService;
    }

    @RequestMapping(FOLDER_PATH + "{boardDivn:faq|notice|qna|sgs}/list")
    public String list(@ModelAttribute("maBoardDto") MaBoardDto maBoardDto, @PathVariable String boardDivn) {
        // thymeleaf rendering error
        switch (boardDivn) {
            case "faq" :
                return "pages/manage/support/board/faq/list";
            case "notice":
                return "pages/manage/support/board/notice/list";
            case "qna":
                return "pages/manage/support/board/qna/list";
            default:
                return "pages/manage/support/board/sgs/list";
        }
    }

    @RequestMapping(FOLDER_PATH + "{boardDivn:faq|notice|qna|sgs}/addList")
    public String addList(@ModelAttribute("maBoardDto") MaBoardDto maBoardDto, @PathVariable String boardDivn, Model model) {

        int pageNo = maBoardDto.getPageNo();
        int pageSize = 10;
        Page<MaBoardDto> resultList = boardRepository.findByPagingAndFiltering(pageNo, pageSize, maBoardDto, boardDivn);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", pagingService.getPageInfo(resultList, pageNo, pageSize));

        switch (boardDivn) {
            case "faq" :
                return "pages/manage/support/board/faq/addList";
            case "notice":
                return "pages/manage/support/board/notice/addList";
            case "qna":
                return "pages/manage/support/board/qna/addList";
            default:
                return "pages/manage/support/board/sgs/addList";
        }
    }
}
