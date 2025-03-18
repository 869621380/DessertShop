package org.example.dessertshopspringboot.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    Integer orderDetailId;
    Integer orderId;
    Integer goodsId;
    Integer quantity;
    Double price;
}
