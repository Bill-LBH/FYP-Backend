package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.springboot.config.Interceptor.JwtInterceptor;

public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/", "/register", "/**/export", "/**/import","/verify");
    }

    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
