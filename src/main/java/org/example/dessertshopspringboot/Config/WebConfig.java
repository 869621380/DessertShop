package org.example.dessertshopspringboot.Config;

import org.example.dessertshopspringboot.Interceptors.LoginInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptors loginInterceptors;

    //不拦截登录注册接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptors)
                .excludePathPatterns("/user/login","/user/register","/user/validateCode",
                        "/category/getCategoryList", "/category/getCategory",
                        "/images/**");
    }

    //添加图片资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String filePath = "C:\\Users\\lizij\\Desktop\\s\\DessertShop\\src\\main\\resources\\store";
        // 确保文件夹存在
        File file = new File(filePath);
        if (file.exists()) {
            // 将 /images/** 映射
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:" + filePath);
        }
    }
}