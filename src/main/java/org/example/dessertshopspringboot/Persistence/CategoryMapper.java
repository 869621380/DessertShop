package org.example.dessertshopspringboot.Persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.dessertshopspringboot.Domain.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

//    @Select("SELECT id, name, price, imgURl FROM CATEGORY ORDER BY RAND() LIMIT 8")
//    public List<Category> getCategoryList() ;
//
    @Select("SELECT id,name, price, description,imgURl FROM CATEGORY WHERE id = #{id}")
    public Category getCategoryById(int id) ;

    @Select("select * from category ")
    List<Category> getCategoryList();

    @Select("select * from category where id = #{id}")
    Category getCategory(int id);

    @Select("select * from category where name like concat('%', #{keywords}, '%')")
    List<Category> searchProductList(String keywords);

    @Update("UPDATE category SET remain = remain - #{quantity} WHERE id = #{id}")
    void sellGoods(int id, int quantity);

    @Select("SELECT remain from category where id=#{id}")
    int getRemain(int id);
}
