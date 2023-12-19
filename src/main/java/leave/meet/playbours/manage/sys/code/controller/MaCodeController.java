package leave.meet.playbours.manage.sys.code.controller;
import leave.meet.playbours.manage.support.board.dto.MaBoardDto;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;

import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
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
    @RequestMapping(FOLDER_PATH + "getCodeList")
    public HashMap<String, Object> getCodeList(@RequestBody HashMap<String, Object> body) {
        HashMap<String, Object> returnMap = new HashMap<>();
        String useYn = (String) body.get("useYn");
        List<MaCodeDto> codeList = codeRepository.findCodeList((String) body.get("parentCode"), useYn);
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
