package services;

import domain.ShopCart;
import persistence.*;
import persistence.impl.ShopCartDaoImpl;

public class shopCartServices {

     public static ShopCart getShopCartMsg(String username){
          ShopCartDao shopCartDao=new ShopCartDaoImpl();
          ShopCart shopCart=shopCartDao.findShopCart(username);
          return shopCart;
     }

}
