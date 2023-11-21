package leave.meet.playbours.common.config;

import leave.meet.playbours.common.interceptor.ManageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ManageInterceptor())
                .excludePathPatterns("/manage/**", "/common/**", "/error");
    }
}
