package leave.meet.playbours.manage.sys.code.controller;
import jakarta.servlet.http.HttpServletRequest;
import leave.meet.playbours.manage.support.board.dto.MaBoardDto;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;

import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class MaCodeController {

    private final MaCodeRepository codeRepository;

    public MaCodeController(MaCodeRepository codeRepository) {

        this.codeRepository = codeRepository;
    }

    private final static String FOLDER_PATH = "/ma/sys/code/";


    @ResponseBody
    @RequestMapping(FOLDER_PATH + "get{procType}CodeList")
    public HashMap<String, Object> getCodeList(@RequestBody HashMap<String, Object> body, @PathVariable String procType) {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<MaCodeDto> codeList =  null;
        if("Code".equals(procType)){
            codeList = codeRepository.findCodeList1((String) body.get("parentCode"));
        }else{
            codeList = codeRepository.findCodeList2((String) body.get("parentCode"));
        }
        returnMap.put("codeList",codeList);
        return returnMap;
    }

    @RequestMapping(FOLDER_PATH + "list")
    public String list(@ModelAttribute("maCodeDto") MaCodeDto maCodeDto, Model model) {

        List<MaCodeDto> codeList = codeRepository.findAll( maCodeDto);
        model.addAttribute("resultList", codeList);
        return "/pages/manage/sys/code/list";
    }

    @ResponseBody
    @RequestMapping(FOLDER_PATH + "chkOverlap")
    public HashMap<String, String> chkOverlap(@RequestBody HashMap<String, Object> body) {

        HashMap<String, String> returnMap = new HashMap<>();
        int count = codeRepository.countByCode((String) body.get("code"));

        if(count > 0) {
            returnMap.put("result", "false");
        } else {
            returnMap.put("result", "true");
        }

        return returnMap;
    }

    @ResponseBody
    @RequestMapping(FOLDER_PATH + "getCodeDetail")
    public HashMap<String, Object> getCodeDetail(@RequestBody HashMap<String, Object> body) {

        HashMap<String, Object> returnMap = new HashMap<>();
        MaCodeDto maCodeDto = codeRepository.findCodeDetail((String) body.get("code"));
        returnMap.put("codeDetail",maCodeDto);
        return returnMap;
    }

    @RequestMapping(FOLDER_PATH + "{procType}Proc")
    public String proc(@ModelAttribute("maCodeDto") MaCodeDto maCodeDto, @PathVariable String procType, Model model) {

        try{
            if("insert".equals(procType)){
                codeRepository.update(maCodeDto);
            }else if("delete".equals(procType)){
                codeRepository.delete(maCodeDto);
            }
            model.addAttribute("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("result", "fail");
        }
        return "redirect:/ma/sys/code/list";
    }

}
