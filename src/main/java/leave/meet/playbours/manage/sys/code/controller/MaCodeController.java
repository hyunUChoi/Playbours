package leave.meet.playbours.manage.sys.code.controller;

import leave.meet.playbours.common.service.PagingService;
import leave.meet.playbours.manage.member.user.repository.MaUserRepository;
import leave.meet.playbours.manage.member.user.service.MaUserDto;
import leave.meet.playbours.manage.sys.code.repository.MaCodeRepository;
import leave.meet.playbours.manage.sys.code.service.MaCodeDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        System.out.println(body.get("code"));
        System.out.println(body.get("code"));
        System.out.println(body.get("code"));
        System.out.println(body.get("code"));
        System.out.println(body.get("code"));
        List<MaCodeDto> codeList = codeRepository.findCodeList((String) body.get("code"));
        returnMap.put("codeList",codeList);
        return returnMap;
    }

}