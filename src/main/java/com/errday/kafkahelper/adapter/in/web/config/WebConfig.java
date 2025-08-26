package com.errday.kafkahelper.adapter.in.web.config;

import com.errday.kafkahelper.adapter.in.web.interceptor.RequestLogRegisterInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RequestLogRegisterInterceptor requestLogRegisterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLogRegisterInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**",  "/js/**", "/images/**", "/fonts/**", "/favicon.ico");
    }
}
