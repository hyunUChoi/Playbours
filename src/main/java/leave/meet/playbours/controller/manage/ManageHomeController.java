package leave.meet.playbours.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageHomeController {

    @RequestMapping("/ma")
    public String maHome() {
        return "pages/manage/index";
    }

    @RequestMapping("/icon")
    public String icon() {
        return "pages/manage/icon-tabler";
    }

    @RequestMapping("/sample")
    public String sample() {
        return "pages/manage/sample-page";
    }

    @RequestMapping("/alert")
    public String alert() {
        return "pages/manage/ui-alerts";
    }

    @RequestMapping("/button")
    public String button() {
        return "pages/manage/ui-buttons";
    }

    @RequestMapping("/card")
    public String card() {
        return "pages/manage/ui-card";
    }

    @RequestMapping("/form")
    public String form() {
        return "pages/manage/ui-forms";
    }

    @RequestMapping("/ty")
    public String ty() {
        return "pages/manage/ui-typography";
    }

    @RequestMapping("/login")
    public String login() {
        return "pages/manage/authentication-login";
    }

    @RequestMapping("/register")
    public String register() {
        return "pages/manage/authentication-register";
    }

}
