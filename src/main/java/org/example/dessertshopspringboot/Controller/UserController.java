package org.example.dessertshopspringboot.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.example.dessertshopspringboot.Domain.Result;
import org.example.dessertshopspringboot.Domain.User;
import org.example.dessertshopspringboot.Service.UserService;
import org.example.dessertshopspringboot.Utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //获取验证码
    @GetMapping("/validateCode")
    public void getValidateCode(HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        // 生成codeKey并添加到响应头
        String codeKey = UUID.randomUUID().toString();
        response.addHeader("codeKey", codeKey);

        try (OutputStream outputStream = response.getOutputStream()) {
            // 生成验证码图片并获取验证码字符串
            String code = ValidateCodeUtils.generateValidateCodeImage(outputStream);
            // 存储到Redis，设置5分钟过期
            RedisUtil.set(codeKey, code, 5, TimeUnit.MINUTES);
        }
    }


    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{6,16}$")String username,
                        @Pattern(regexp = "^\\S{6,16}$")String password){
//        //验证码校验
//        String checkCode = ValidateCodeUtils.checkValidateCode(code,codeKey);
//        if(!checkCode.equals("success")){
//            return Result.error(checkCode);
//        }

        User user=userService.findUserByUserName(username);
        //查询不到或密码错误
        if (user == null || !Sha256Util.checkPassword(password, user.getPassword())) {
            System.out.println("error");
            return Result.error("账号或密码错误");
        }

        //查询匹配成功生成jwt令牌并返回
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",username);
        String token= JwtUtil.genToken(claims);
        //Redis中添加jwt令牌校验
        RedisUtil.set("username"+username,token,1,TimeUnit.DAYS);
        //日志记录登录操作
        logger.info("用户{}成功进行了登录操作",username);

        return Result.success(token);
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{6,16}$")String username,
                           @Pattern(regexp = "^\\S{6,16}$")String password,
                           @Pattern(regexp = "^\\S{4}")String codes,
                           @RequestParam("codeKey") String codeKey) {
        //验证码校验
        if(codes==null)return Result.error("验证码不能为空");
        String checkCode = ValidateCodeUtils.checkValidateCode(codes,codeKey);
        if(!checkCode.equals("success")){
            return Result.error(checkCode);
        }
        User user=userService.findUserByUserName(username);
        //若用户名已被占用
        if(user!=null){
            return Result.error("用户名已存在");
        }

        //注册账号
        userService.registerUser(username,Sha256Util.getSHA256String(password));
        logger.info("用户{}成功注册",username);
        return Result.success();
    }

    @GetMapping("/userInfo")
    public Result getUserInfo(){

        String username=ThreadLocalUtil.getUsername();
        User user=userService.findUserByUserName(username);
        logger.info("用户{}查看了用户信息",username);
        return Result.success(user);
    }

    @PostMapping("changeUserInfo")
    public Result changeUserInfo(String nickname,String phone ,
                                 String province,String city,String address){

        String username=ThreadLocalUtil.getUsername();

        userService.changUserInfo(username,nickname,phone,province,address);
        logger.info("用户{}修改用户信息为：{昵称：{}，电话：{}，省份:{},城市：{}，地址{}",username,nickname,phone,province,address);
        return Result.success();
    }

}
