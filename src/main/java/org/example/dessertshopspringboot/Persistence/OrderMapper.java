package org.example.dessertshopspringboot.Persistence;

import org.apache.ibatis.annotations.*;
import org.example.dessertshopspringboot.Domain.Order;
import org.example.dessertshopspringboot.Domain.OrderDetail;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO `orders` (total_price, username, address, status, create_time) " +
            "VALUES (#{totalPrice}, #{username}, #{address}, #{status}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void generateOrder(Order order);

    @Insert("INSERT INTO `order_details` (order_id, goods_id, quantity, price) " +
            "VALUES (#{orderId}, #{goodsId}, #{quantity}, #{price})")
    void insertOrderDetail(OrderDetail orderDetail);


    @Select("SELECT * from orders where username=#{username} LIMIT 15")
    List<Order> getAllOrder(String username);

    @Select("SELECT * from order_details where order_id=#{orderId}")
    List<OrderDetail> getOrdersDetail(Integer orderId);

    @Update("UPDATE orders SET payment_status = '已支付' WHERE order_id = #{orderId} ")
    void checkout(Integer orderId);

    @Update("UPDATE orders SET status = '已发货' WHERE order_id = #{orderId}")
    void dispatch(Integer orderId);
}
