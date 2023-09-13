package leave.meet.playbours.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Thymeleaf 3.1 Update
 * The #request, #response, #session, and #servletContext are no longer available to expressions in Thymeleaf 3.1.
 * 그렇기 때문에 각 URL 값을 가져오기 위해 서버 전반적으로 사용할 수 있는 controller 생성
 * use : ${requestURI}
 * **/
@ControllerAdvice
public class GlobalController {

    @ModelAttribute("requestURI")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }
}
