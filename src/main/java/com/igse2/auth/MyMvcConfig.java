package com.igse2.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/user/login", "/user/logout","/page-login.html"
                        ,"/css/**","/lib/**","/js/**","/page-register.html"
                        ,"/user/signUp","/QRcode.html","/**.js","/igse/propertycount"
                        ,"/customer/add","/igse/{propertyType}/{roomNum}","/rate/update","/homeadd.html","/vehicle_add.html"
                        ,"/favicon.ico","/error","/customer/payBill"
                );
    }
}