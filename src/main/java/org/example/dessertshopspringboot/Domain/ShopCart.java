package org.example.dessertshopspringboot.Domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;


import java.util.List;

@Data
@NoArgsConstructor
public class ShopCart {
    private double totalPrice;        // 购物车总价
    private List<CartItem> items;     // 商品项列表

    public ShopCart(List<CartItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    // 计算总价
    private double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getCategory().getPrice() * item.getQuantity())
                .sum();
    }
}