package org.example.dessertshopspringboot.Service.Impl;

import org.example.dessertshopspringboot.Domain.*;
import org.example.dessertshopspringboot.Persistence.OrderMapper;
import org.example.dessertshopspringboot.Persistence.SellerOrderMapper;
import org.example.dessertshopspringboot.Service.CategoryService;
import org.example.dessertshopspringboot.Service.OrderService;
import org.example.dessertshopspringboot.Service.SellerOrderService;
import org.example.dessertshopspringboot.Service.UserService;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SellerOrderService sellerOrderService;
    @Autowired
    private SellerOrderMapper sellerOrderMapper;

    @Override
    public void generateOrder(ShopCart shopCart) {

        User user=userService.findUserByUserName(ThreadLocalUtil.getUsername());
        String address=user.getProvince()+user.getCity()+user.getAddress();
        Order order=new Order(null,shopCart.getTotalPrice(),user.getUsername(),address,"未完成","未支付" ,null,LocalDateTime.now());
        //生成订单信息
        orderMapper.generateOrder(order);
        Integer order_id=order.getOrderId();

        //生成订单详细信息
        for(CartItem cartItem :shopCart.getItems()){
            OrderDetail orderDetail=new OrderDetail(null,order_id,cartItem.getCategory().getId(),cartItem.getQuantity(),cartItem.getCategory().getPrice());
            orderMapper.insertOrderDetail(orderDetail);
        }

        //将订单提交给商家
        sellerOrderService.generateSellerOrder(shopCart.getItems(),order_id);

    }

    @Override
    public List<Order> getAllOrder() {
        List<Order>orders=orderMapper.getAllOrder(ThreadLocalUtil.getUsername());

        for(Order order:orders){
            List<CartItem>cartItems=new ArrayList<>();
            List<OrderDetail>orderDetails=orderMapper.getOrdersDetail(order.getOrderId());
            for(OrderDetail orderDetail:orderDetails){
                //获取订单详细对应的商品
                Category category=categoryService.getCategoryById(orderDetail.getGoodsId());
                //价格设置为购买时价格
                category.setPrice(orderDetail.getPrice());
                cartItems.add(new CartItem(category,orderDetail.getQuantity()));
            }
            order.setCartItem(cartItems);
        }

        return orders;
    }

    @Override
    public boolean checkout(Integer orderId) {
        //校验支付
        //
        //
        orderMapper.checkout(orderId);
        sellerOrderMapper.checkout(orderId);
        return true;
    }
}
