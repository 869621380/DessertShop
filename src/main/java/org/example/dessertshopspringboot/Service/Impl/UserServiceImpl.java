package org.example.dessertshopspringboot.Service.Impl;

import org.example.dessertshopspringboot.Domain.User;
import org.example.dessertshopspringboot.Persistence.UserMapper;
import org.example.dessertshopspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void registerUser(String username, String password) {
        userMapper.registerUser(username,password);
    }

    @Override
    public void changUserInfo(String username,String nickname, String phone, String province, String address) {
        userMapper.changeUser(username,nickname,phone,province,address);
    }


}
