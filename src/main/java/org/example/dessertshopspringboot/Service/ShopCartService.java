package org.example.dessertshopspringboot.Service;

import org.example.dessertshopspringboot.Domain.CartItem;
import org.example.dessertshopspringboot.Domain.ShopCart;
import org.example.dessertshopspringboot.Exception.InvalidDataException;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

public interface ShopCartService {

    List<CartItem> getCheckoutItem(String username,List<Map<String, Object>> list)throws InvalidDataException;

    ShopCart getShopCart(String username);


    boolean update(Integer id, Integer quantity);

    void delete(Integer id);

    void add(Integer id);

    boolean find(Integer id);
}
