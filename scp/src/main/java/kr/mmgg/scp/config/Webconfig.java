package kr.mmgg.scp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.mmgg.scp.interceptor.AdminInterceptor;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // CORS 적용할 URL 패턴
                .addMapping("/**")
                // 자원을 공유할 오리진 지정
                .allowedOrigins("*")
                // 요청 허용 메서드 (get,post등등)
                .allowedMethods("*");
        //
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/**");
    }
}
