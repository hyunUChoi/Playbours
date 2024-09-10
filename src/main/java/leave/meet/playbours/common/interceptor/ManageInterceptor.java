package leave.meet.playbours.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import leave.meet.playbours.manage.sys.menu.entity.MaMenuEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.List;

@Component
public class ManageInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageInterceptor.class);

    /*private final MaMenuRepository menuRepository;

    public ManageInterceptor(MaMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("========================= MANAGE INTERCEPTOR START =========================");
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue =  request.getParameter(paramName);
            LOGGER.info(paramName + " : " + paramValue);
        }

        // 1차 메뉴
        /*MaMenuEntity menuDto = new MaMenuEntity();
        menuDto.setMenuClCd("ma");
        menuDto.setUpperCd("");
        List<MaMenuEntity> menuList1 = menuRepository.findMenuList(menuDto);
        menuDto.setMenuList(menuList1);

        // 2차 메뉴
        for(MaMenuEntity dto2 : menuDto.getMenuList()) {
            dto2.setMenuClCd("ma");
            dto2.setUpperCd(dto2.getMenuCd());
            List<MaMenuEntity> menuList2 = menuRepository.findMenuList(dto2);
            dto2.setMenuList(menuList2);

            // 3차 메뉴
            for(MaMenuEntity dto3 : dto2.getMenuList()) {
                dto3.setMenuClCd("ma");
                dto3.setUpperCd(dto3.getMenuCd());
                List<MaMenuEntity> menuList3 = menuRepository.findMenuList(dto3);
                dto3.setMenuList(menuList3);
                dto2.setMaMenuDto(dto3);
            }

            menuDto.setMaMenuDto(dto2);
        }

        session.setAttribute("menuList", menuDto.getMenuList());*/
        session.setAttribute("requestURI", requestURI);
        LOGGER.info("========================= MANAGE INTERCEPTOR END =========================");
        return true;
    }
}
