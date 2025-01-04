package persistence;

import domain.Goods;
import domain.Order;
import domain.ShopCart;

import java.util.ArrayList;

public interface ShopCartDao {
    public boolean insertGoods(String username,Goods goods);
    public boolean deleteGoods(int id);
    public ShopCart findShopCart(String username);
    public boolean changGoodsQuantity(int id,int newQuantity);
    public int getTotalRows(String username);//获取总行数


    public int createOrder(String username, String address, ShopCart shopCart);
    public Order getOrder(int id);
    public ArrayList<Integer> findAllOrderId(String username);
}

