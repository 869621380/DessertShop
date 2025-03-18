package org.example.dessertshopspringboot.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Category category;  // 商品信息
    private int quantity;       // 购买数量
}