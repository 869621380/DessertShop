package org.example.dessertshopspringboot.Service;

import org.example.dessertshopspringboot.Domain.User;

public interface UserService {

    User findUserByUserName(String username);

    void registerUser(String username, String password);

    void changUserInfo(String username,String nickname, String phone, String province, String address);
}
