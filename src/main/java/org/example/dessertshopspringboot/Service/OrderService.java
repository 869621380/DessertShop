package org.example.dessertshopspringboot.Service;

import org.example.dessertshopspringboot.Domain.Order;
import org.example.dessertshopspringboot.Domain.ShopCart;
import org.example.dessertshopspringboot.Domain.User;
import org.example.dessertshopspringboot.Service.Impl.UserServiceImpl;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;

import java.util.List;

public interface OrderService {

    void generateOrder(ShopCart shopCart);

    List<Order> getAllOrder();

    boolean checkout(Integer orderId);
}
