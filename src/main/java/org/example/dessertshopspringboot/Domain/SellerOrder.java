package org.example.dessertshopspringboot.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SellerOrder {
    @JsonIgnore
    String sellerName;
    String orderId;
    String customerName;
    double price;
    String status;
    String paymentStatus;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    LocalDateTime createTime;
    String address;
    @JsonIgnore
    Integer userOrderId;
    public SellerOrder(String sellerName,String customerName, double price,Integer userOrderId,String address) {
        orderId = UUID.randomUUID().toString();
        this.sellerName = sellerName;
        this.customerName = customerName;
        this.price = price;
        this.userOrderId = userOrderId;
        this.address = address;
    }
}
