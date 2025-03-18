package org.example.dessertshopspringboot.Controller;

import org.example.dessertshopspringboot.Domain.CartItem;
import org.example.dessertshopspringboot.Domain.Result;
import org.example.dessertshopspringboot.Domain.ShopCart;
import org.example.dessertshopspringboot.Exception.InvalidDataException;
import org.example.dessertshopspringboot.Persistence.ShopCartMapper;
import org.example.dessertshopspringboot.Service.OrderService;
import org.example.dessertshopspringboot.Service.ShopCartService;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopCart")
@Validated
public class ShopCartController {
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    OrderService orderService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ShopCartController.class);

    @GetMapping
    Result<ShopCart> getShopCart(){

        String username=ThreadLocalUtil.getUsername();
        ShopCart shopCart=shopCartService.getShopCart(username);
        return Result.success(shopCart);
    }

    /*
    结账会结算所有购物车中选中商品
    并把结算的商品从购物车中移除
    同时生成订单
    另外把商家商品的剩余剩余数目减少对应的数量
     */
    @PostMapping("/checkout")
    Result checkout(@RequestBody Map<String,Object> params){
        List<Map<String,Object>> list= (List) params.get("items");
        String username=ThreadLocalUtil.getUsername();

        try {
            List<CartItem> cartItemList= shopCartService.getCheckoutItem(username,list);
            if(cartItemList==null){
                return Result.error("选中商品不能为空");
            }
            logger.info("用户{}进行了结账，结账信息{}",username,cartItemList);
            orderService.generateOrder(new ShopCart(cartItemList));

        } catch (InvalidDataException e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @PatchMapping("/update")
    Result update(@RequestParam("id")Integer id,@RequestParam("quantity")Integer quantity){
        if(quantity<0)
            return Result.error("商品数目小于0");
        boolean check=shopCartService.update(id,quantity);
        if(check){
            return Result.success();
        }
        return Result.error("购物车商品数量不能超过商品剩余数量");
    }

    @PatchMapping("/delete")
    Result delete(@RequestParam("id")Integer id){
        shopCartService.delete(id);
        logger.info("用户{}将商品id为{}的商品从购物车中移除",ThreadLocalUtil.getUsername(),id);
        return Result.success();
    }

    @PostMapping("/add")
    Result add(@RequestParam("id")Integer id){
        if(shopCartService.find(id)){
            return Result.success("商品已在购物车中存在");
        }
        shopCartService.add(id);
        logger.info("用户{}将商品id为{}的商品添加进购物车",ThreadLocalUtil.getUsername(),id);
        return Result.success();
    }


}
