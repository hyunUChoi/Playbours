package leave.meet.playbours.manage.login;

import leave.meet.playbours.manage.login.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MaLoginController {

    @RequestMapping("/athnLogin")
    public String login() {
        return "pages/manage/authentication-login";
    }

    @PostMapping("/maLoginProcess")
    public void maLoginProcess() {
    }

}
