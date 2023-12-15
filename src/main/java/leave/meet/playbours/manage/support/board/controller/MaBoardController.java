package leave.meet.playbours.manage.support.board.controller;

import jakarta.annotation.Resource;
import leave.meet.playbours.common.paging.dto.PagingDto;
import leave.meet.playbours.common.paging.service.PagingService;
import leave.meet.playbours.manage.support.board.repository.MaBoardRepository;
import leave.meet.playbours.manage.support.board.dto.MaBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        PagingDto paging = pagingService.getPageInfo(resultList, pageNo, pageSize);
        paging.setTotalRecord(boardRepository.countTotalRecords(maBoardDto, boardDivn));

        model.addAttribute("resultList", resultList);
        model.addAttribute("paging", paging);

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

    @RequestMapping(FOLDER_PATH + "{boardDivn:faq|notice|qna|sgs}/{procType:insert|update}Form")
    public String form(@ModelAttribute("maBoardDto") MaBoardDto maBoardDto, @PathVariable String boardDivn, @PathVariable String procType, Model model) {

        MaBoardDto boardDto = new MaBoardDto();

        if("update".equals(procType)) {
            boardDto = boardRepository.findOne(maBoardDto);
        }

        model.addAttribute("boardDto", boardDto);

        switch (boardDivn) {
            case "faq" :
                return "pages/manage/support/board/faq/form";
            case "notice":
                return "pages/manage/support/board/notice/form";
            case "qna":
                return "pages/manage/support/board/qna/form";
            default:
                return "pages/manage/support/board/sgs/form";
        }
    }

    @RequestMapping(FOLDER_PATH + "{boardDivn:faq|notice|qna|sgs}/{procType:insert|update|updateReply|delete|deleteReply}Proc")
    public String proc(@ModelAttribute("maBoardDto") MaBoardDto maBoardDto, @PathVariable String boardDivn, @PathVariable String procType, RedirectAttributes attributes) {

        switch (procType) {
            case "insert" -> {
                boardRepository.insert(maBoardDto, boardDivn);
                return "redirect:" + FOLDER_PATH + boardDivn + "/list";
            }
            case "update" -> {
                boardRepository.update(maBoardDto, boardDivn);
                attributes.addFlashAttribute("maBoardDto", maBoardDto);
                return "redirect:" + FOLDER_PATH + boardDivn + "/view";
            }
            case "updateReply" -> {
                boardRepository.updateReply(maBoardDto);
                attributes.addFlashAttribute("maBoardDto", maBoardDto);
                return "redirect:" + FOLDER_PATH + boardDivn + "/view";
            }
            case "delete" -> {
                boardRepository.delete(maBoardDto);
                return "redirect:" + FOLDER_PATH + boardDivn + "/list";
            }
            case "deleteReply" -> {
                boardRepository.deleteReply(maBoardDto);
                attributes.addFlashAttribute("maBoardDto", maBoardDto);
                return "redirect:" + FOLDER_PATH + boardDivn + "/view";
            }
        }

        return "pages/manage/support/board/qna/list";
    }

    @RequestMapping(FOLDER_PATH + "{boardDivn:faq|notice|qna|sgs}/view")
    public String view(@ModelAttribute("maBoardDto") MaBoardDto maBoardDto, @PathVariable String boardDivn, Model model) {

        MaBoardDto searchVO = maBoardDto;
        maBoardDto = boardRepository.findOne(maBoardDto);
        maBoardDto.setSearch(searchVO);
        model.addAttribute("maBoardDto",  maBoardDto);

        switch (boardDivn) {
            case "faq" :
                return "pages/manage/support/board/faq/view";
            case "notice":
                return "pages/manage/support/board/notice/view";
            case "qna":
                return "pages/manage/support/board/qna/view";
            default:
                return "pages/manage/support/board/sgs/view";
        }
    }
}
