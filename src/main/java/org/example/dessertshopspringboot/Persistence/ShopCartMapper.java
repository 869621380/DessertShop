package org.example.dessertshopspringboot.Persistence;



import org.apache.ibatis.annotations.*;
import org.example.dessertshopspringboot.Domain.CartItem;
import org.example.dessertshopspringboot.Domain.Category;

import java.util.List;

@Mapper
public interface ShopCartMapper {
    @Select("SELECT " +
            "sc.goods_id, sc.quantity, " +
            "c.name, c.price, c.imgURL, c.description, c.seller, c.remain " +
            "FROM shopping_cart sc " +
            "JOIN category c ON sc.goods_id = c.id " +
            "WHERE sc.username = #{username}")
    @Results({
            @Result(property = "quantity", column = "quantity"),  // 直接映射数量
            @Result(property = "category",                       // 嵌套映射 Category
                    column = "goods_id",                         // 传递 goods_id 参数
                    one = @One(select = "getCategoryById"))      // 调用 getCategoryById 方法
    })
    List<CartItem> getCartItemsByUsername(String username);  // 返回 CartItem 列表

    // 查询单个商品
    @Select("SELECT * FROM category WHERE id = #{goodsId}")
    Category getCategoryById(@Param("goodsId") int goodsId);


    @Select({
            "<script>",
            "SELECT MAX(quantity) FROM shopping_cart WHERE username = #{username} AND goods_id IN ",
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            " GROUP BY username, goods_id",
            "</script>"
    })//获取购物车选中的商品的数目
    List<Integer> getCartItemCount(@Param("username") String username, @Param("ids") List<Integer> ids);

    @Delete({
            "<script>",
            "DELETE FROM shopping_cart WHERE username = #{username} AND goods_id IN ",
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"
    })//删除购物车选中的商品的数目
    void deleteCartItem( String username, List<Integer> ids);

    @Update("UPDATE shopping_cart SET quantity = #{quantity} WHERE username = #{username} AND goods_id = #{id}")
    void update(String username,Integer id, Integer quantity);

    @Delete("DELETE FROM shopping_cart WHERE username = #{username} AND goods_id = #{id}")
    void delete(String username, Integer id);

    @Insert("INSERT INTO shopping_cart (username, goods_id, quantity)"+
            "VALUES (#{username}, #{id}, 1)")
    void add(String username, Integer id);

    @Select("SELECT EXISTS (SELECT 1 FROM shopping_cart WHERE username = #{username} AND goods_id = #{id} LIMIT 1)")
    boolean find(String username, Integer id);


}

