package leave.meet.playbours.manage.login;

import leave.meet.playbours.manage.login.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaLoginController {

    @RequestMapping("/login")
    public String login() {
        return "pages/manage/login";
    }

    @PostMapping("/maLoginProcess")
    public String maLoginProcess(@ModelAttribute("memberVO") Member memberVO) {
        if(StringUtils.hasText(memberVO.getUserId()) & StringUtils.hasText(memberVO.getPassword())) {
            return "redirect:/ma";
        }
        return "pages/manage/login";
    }

}
