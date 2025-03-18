package org.example.dessertshopspringboot.Persistence;

import org.apache.ibatis.annotations.*;
import org.example.dessertshopspringboot.Domain.SellerOrder;
import org.example.dessertshopspringboot.Domain.SellerOrderDetail;

import java.util.List;

@Mapper
public interface SellerOrderMapper {

    @Insert("INSERT INTO `sellerorder` (order_id,seller_name,customer_name,price,user_order_id,address) " +
            "VALUES (#{orderId},#{sellerName}, #{customerName}, #{price},#{userOrderId},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void generateOrder(SellerOrder sellerOrder);

    @Insert("INSERT INTO `sellerorderdetail` (seller_order_id,goods_name,quantity,total_price) " +
            "VALUES (#{sellerOrderId}, #{goodsName},#{quantity}, #{totalPrice})")
    void addSellOrderDetail(SellerOrderDetail orderDetail);

    @Update("UPDATE sellerorder SET payment_status = '已支付' WHERE user_order_id = #{orderId}")
    void checkout(Integer orderId);

    @Select("SELECT * from sellerorder where seller_name=#{sellerName}")
    List<SellerOrder> findOrderByName(String sellerName);

    @Select("SELECT * from sellerorderdetail where seller_order_id=#{orderId}")
    List<SellerOrderDetail> detail(String orderId);

    @Update("UPDATE sellerorder SET status = '已发货' WHERE order_id = #{orderId}")
    void dispatch(String orderId);

    @Select("SELECT user_order_id from sellerorder where order_id=#{orderId}")
    Integer getUserOrderId(String orderId);
}
