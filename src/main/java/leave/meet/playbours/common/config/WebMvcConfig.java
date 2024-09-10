package leave.meet.playbours.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*private final MaMenuRepository menuRepository;

    public WebMvcConfig(MaMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ManageInterceptor(menuRepository))
                .addPathPatterns("/ma/**")
                .excludePathPatterns("/manage/**", "/common/**", "/error");
    }*/
}
