package leave.meet.playbours.manage.sys.code.controller;

import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.dto.MaCodeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public HashMap<String, Object> chkOverlap(@RequestBody HashMap<String, Object> body) {
        HashMap<String, Object> returnMap = new HashMap<>();
        List<MaCodeDto> codeList = codeRepository.findCodeList((String) body.get("groupCode"));
        returnMap.put("codeList",codeList);
        return returnMap;
    }

}
