package services;

import domain.Account;
import domain.Goods;
import domain.Pair;
import domain.ShopCart;
import persistence.ShopCartDao;
import persistence.impl.ShopCartDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class checkOutServices {
    String username;
    int orderId;

    public static ShopCart getShopCartInfo(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        if (account != null) {
            List<Pair<Goods, Integer>> cartItems = new ArrayList<>();
            int itemCount = 0;
            ShopCartDao shopCartDao=new ShopCartDaoImpl();
            int flag=shopCartDao.getTotalRows(account.getUsername()) ;
            while (itemCount<flag) {
                try {
                    String itemName = req.getParameter("itemName" + itemCount);

                    String itemDescription = req.getParameter("itemDescription" + itemCount);
                    double itemPrice = Double.parseDouble(req.getParameter("itemPrice" + itemCount));
                    int itemQuantity = Integer.parseInt(req.getParameter("itemQuantity" + itemCount));
                    int id = Integer.parseInt(req.getParameter("itemId" + itemCount));
                    String imgUrl= req.getParameter("itemSrc"+itemCount);
                    Goods goods = new Goods(id, itemName, itemDescription, imgUrl, itemPrice);

                    cartItems.add(new Pair<>(goods, itemQuantity));
                } catch (Exception e) {

                }

                itemCount++;
            }

            double totalPrice = 0;
            for (Pair<Goods, Integer> item : cartItems) {
                totalPrice += item.getKey().getPrice() * item.getValue();
            }
            ShopCart shopCart = new ShopCart(cartItems, totalPrice);
            return shopCart;
        }
        return null;
    }

    public static int checkOutOperator(String username,String address,ShopCart shopCart){
        persistence.ShopCartDao shopCartDao=new ShopCartDaoImpl();
        for(int i=0;i<shopCart.getShopCarts().size();++i){
            shopCartDao.deleteGoods(shopCart.shopCarts.get(i).getKey().getId());
        }
        int id=shopCartDao.createOrder(username,address,shopCart);
        return id;
    }

}
