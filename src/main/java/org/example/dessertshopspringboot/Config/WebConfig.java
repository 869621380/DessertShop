package org.example.dessertshopspringboot.Config;

import org.example.dessertshopspringboot.Interceptors.LoginInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptors loginInterceptors;

    //不拦截登录注册接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptors)
                .excludePathPatterns("/user/login","/user/register","/user/validateCode");
    }

}