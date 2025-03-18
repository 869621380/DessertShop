package org.example.dessertshopspringboot.Persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.dessertshopspringboot.Domain.User;

@Mapper
public interface UserMapper {

    @Select("select * from users where username=#{username}")
    public User getUserByUsername(String username);

    @Insert("insert into users(username,password)"+
            " values (#{username},#{password})")
    public void registerUser(String username, String password);

    @Update("UPDATE users " +
            "SET nickname = #{nickname}, phone = #{phone}, province = #{province}, address = #{address} " +
            "WHERE username = #{username}")
    public void changeUser(String username,String nickname, String phone, String province, String address);
}
