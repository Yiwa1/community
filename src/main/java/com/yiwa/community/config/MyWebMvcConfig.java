package com.yiwa.community.config;

import com.yiwa.community.Interceptor.ModifyQuestionInterceptor;
import com.yiwa.community.Interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    UserLoginInterceptor userLoginInterceptor;

    @Autowired
    ModifyQuestionInterceptor modifyQuestionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截未登录用户
        registry.addInterceptor(userLoginInterceptor). addPathPatterns("/publish").addPathPatterns("/profile/**").addPathPatterns("/logout");
        //拦截无权限修改问题用户
        registry.addInterceptor(modifyQuestionInterceptor).addPathPatterns("/publish/*");

    }
}
