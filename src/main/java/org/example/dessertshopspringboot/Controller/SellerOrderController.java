package org.example.dessertshopspringboot.Controller;

import org.example.dessertshopspringboot.Domain.Result;
import org.example.dessertshopspringboot.Domain.SellerOrder;
import org.example.dessertshopspringboot.Domain.SellerOrderDetail;
import org.example.dessertshopspringboot.Service.SellerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerOrderController {
    @Autowired
    SellerOrderService sellerOrderService;

    @GetMapping
    public Result<List<SellerOrder>> orders(){
        return Result.success(sellerOrderService.orders());
    }

    @PostMapping("/detail")
    public Result<List<SellerOrderDetail>> detail(@RequestParam("orderId") String orderId){
        List<SellerOrderDetail>detail=sellerOrderService.detail(orderId);
        return Result.success(detail);
    }

    @PostMapping("/dispatch")
    public Result dispatch(@RequestParam("orderId") String orderId){
        sellerOrderService.dispatch(orderId);
        return Result.success();
    }
}
