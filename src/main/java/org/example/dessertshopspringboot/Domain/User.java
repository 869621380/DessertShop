package org.example.dessertshopspringboot.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class User {
    @NotEmpty
    private String username;//用户名
    @JsonIgnore
    private String password;//密码
    private String nickName="";//姓名
    private String phone="";//电话
    private String province="";//省份
    private String city="";//城市
    private String address="";//详细地址
    private boolean state;//在线状态
}
