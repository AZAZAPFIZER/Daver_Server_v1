package com.azazafizer.server_v1.common.config;

import com.azazafizer.server_v1.common.interceptor.BearerAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMVCConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(20);
    }

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(bearerAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/static/**");
    }
}