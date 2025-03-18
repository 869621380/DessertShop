package org.example.dessertshopspringboot.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerOrderDetail {
    @JsonIgnore
    Integer id;
    @JsonIgnore
    String sellerOrderId;
    String goodsName;
    Integer quantity;
    Double totalPrice;

}
