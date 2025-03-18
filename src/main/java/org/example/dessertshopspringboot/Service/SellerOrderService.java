package org.example.dessertshopspringboot.Service;

import org.example.dessertshopspringboot.Domain.CartItem;
import org.example.dessertshopspringboot.Domain.OrderDetail;
import org.example.dessertshopspringboot.Domain.SellerOrder;
import org.example.dessertshopspringboot.Domain.SellerOrderDetail;

import java.util.List;

public interface SellerOrderService {

    void generateSellerOrder(List<CartItem> checkoutList,Integer user_order_id);

    List<SellerOrder> orders();

    List<SellerOrderDetail> detail(String orderId);

    void dispatch(String orderId);
}
