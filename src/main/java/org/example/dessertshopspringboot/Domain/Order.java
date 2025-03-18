package org.example.dessertshopspringboot.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dessertshopspringboot.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Double totalPrice;
    private String username;
    private String address;
    private String status;
    private String paymentStatus;
    private List<CartItem> cartItem;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private LocalDateTime createTime;
}