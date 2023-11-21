package leave.meet.playbours.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class ManageInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("========================= MANAGE INTERCEPTOR START =========================");
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        session.setAttribute("requestURI", requestURI);
        LOGGER.info("========================= MANAGE INTERCEPTOR END =========================");
        return true;
    }
}
