package org.example.dessertshopspringboot.Service.Impl;

import org.example.dessertshopspringboot.Domain.CartItem;
import org.example.dessertshopspringboot.Domain.Category;
import org.example.dessertshopspringboot.Domain.ShopCart;
import org.example.dessertshopspringboot.Exception.InvalidDataException;
import org.example.dessertshopspringboot.Persistence.ShopCartMapper;
import org.example.dessertshopspringboot.Service.CategoryService;
import org.example.dessertshopspringboot.Service.SellerOrderService;
import org.example.dessertshopspringboot.Service.ShopCartService;
import org.example.dessertshopspringboot.Utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    ShopCartMapper shopCartMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SellerOrderService sellerOrderService;

    //获取购物车商品
    @Override
    public List<CartItem> getCheckoutItem(String username,List<Map<String, Object>> list) throws InvalidDataException {

        List<Integer>id=new ArrayList<>();
        List<Category>categoryList=new ArrayList<>();

        for(Map<String, Object> items : list){
            Integer goodsId=(Integer)items.get("id");
            id.add(goodsId);
            categoryList.add(shopCartMapper.getCategoryById(goodsId));
        }
        List<Integer>quantity=shopCartMapper.getCartItemCount(username,id);

        //校验库存商家库存是否满足
        for(int i=0;i<quantity.size();i++){
            if(categoryList.get(i).getRemain()<quantity.get(i))
                throw new InvalidDataException("”商品数量不足");
        }

        //将商品数目进行对应消减
        for(int i=0;i<quantity.size();i++){
            categoryService.sellGoods(categoryList.get(i).getId(),quantity.get(i));
        }

        List<CartItem>checkoutItemList=new ArrayList<CartItem>();

        //构造结账表
        for(int i=0;i<quantity.size();i++){
            checkoutItemList.add(new CartItem(categoryList.get(i),quantity.get(i)));
        }
        //删除购物车中结账的商品
        shopCartMapper.deleteCartItem(username,id);



        return checkoutItemList;
    }

    @Override
    public ShopCart getShopCart(String username) {
        List<CartItem> cartItems = shopCartMapper.getCartItemsByUsername(username);
        return new ShopCart(cartItems);
    }

    @Override
    public boolean update(Integer id, Integer quantity) {
        if(quantity>categoryService.getRemain(id))
            return false;
        shopCartMapper.update(ThreadLocalUtil.getUsername(),id,quantity);
        return true;
    }

    @Override
    public void delete(Integer id) {
        shopCartMapper.delete(ThreadLocalUtil.getUsername(),id);
    }

    @Override
    public void add(Integer id) {
        shopCartMapper.add(ThreadLocalUtil.getUsername(),id);
    }

    @Override
    public boolean find(Integer id) {
        return shopCartMapper.find(ThreadLocalUtil.getUsername(),id);
    }
}
