package org.example.dessertshopspringboot.Service.Impl;

import org.example.dessertshopspringboot.Domain.*;
import org.example.dessertshopspringboot.Persistence.OrderMapper;
import org.example.dessertshopspringboot.Persistence.SellerOrderMapper;
import org.example.dessertshopspringboot.Persistence.UserMapper;
import org.example.dessertshopspringboot.Service.SellerOrderService;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerOrderServiceImpl implements SellerOrderService {

    @Autowired
    private SellerOrderMapper sellerOrderMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void generateSellerOrder(List<CartItem> checkoutList,Integer user_order_id) {
        Map<String, List<CartItem>> map = new HashMap<>();

        // 根据商家进行分类
        for (CartItem cartItem : checkoutList) {
            String seller = cartItem.getCategory().getSeller();
            map.computeIfAbsent(seller, k -> new ArrayList<>()).add(cartItem);
        }

        // 遍历 map 中的所有数据
        for (Map.Entry<String, List<CartItem>> entry : map.entrySet()) {
            String seller = entry.getKey();
            //创建商家总订单
            double totalPrice = 0;
            List<CartItem> cartItems = entry.getValue();
            for (CartItem item : cartItems) {
                totalPrice+=item.getQuantity()*item.getCategory().getPrice();
            }

            User user=userMapper.getUserByUsername(ThreadLocalUtil.getUsername());
            //生成总订单
            SellerOrder sellerOrder = new SellerOrder(seller, ThreadLocalUtil.getUsername(),totalPrice,user_order_id,
                    user.getProvince()+user.getCity()+user.getAddress());
            sellerOrderMapper.generateOrder(sellerOrder);
            //插入订单详情
            for (CartItem item : cartItems) {
                SellerOrderDetail orderDetail = new SellerOrderDetail(null,sellerOrder.getOrderId(),
                        item.getCategory().getName(), item.getQuantity(),
                        item.getCategory().getPrice()*item.getQuantity());
                sellerOrderMapper.addSellOrderDetail(orderDetail);
            }
        }
    }

    @Override
    public List<SellerOrder> orders() {
        return sellerOrderMapper.findOrderByName(ThreadLocalUtil.getUsername());
    }

    @Override
    public List<SellerOrderDetail> detail(String orderId) {
        List<SellerOrderDetail>details=sellerOrderMapper.detail(orderId);
        return details;
    }

    @Override
    public void dispatch(String orderId) {
        sellerOrderMapper.dispatch(orderId);
        Integer id=sellerOrderMapper.getUserOrderId(orderId);
        orderMapper.dispatch(id);
    }

}
