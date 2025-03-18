package org.example.dessertshopspringboot.Controller;

import org.example.dessertshopspringboot.Domain.Order;
import org.example.dessertshopspringboot.Domain.Result;
import org.example.dessertshopspringboot.Domain.ShopCart;
import org.example.dessertshopspringboot.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    Result<List<Order>> getAllOrders() {
        List<Order>orders=orderService.getAllOrder();
        return Result.success(orders);
    }

    @PostMapping("/checkout")
    Result<List<Order>> checkout(@RequestParam("orderId") Integer orderId) {
        if(orderService.checkout(orderId)){
            return Result.success();
        }
        return Result.error("顾客未支付");
    }


}
