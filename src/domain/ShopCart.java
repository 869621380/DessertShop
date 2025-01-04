package domain;

import java.util.List;

public class ShopCart {
    double totalPrice;
    public List<Pair<Goods,Integer>>shopCarts;
    public ShopCart(List<Pair<Goods,Integer>>s,double totalPrice){
        shopCarts=s;
        this.totalPrice=totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Pair<Goods, Integer>> getShopCarts() {
        return shopCarts;
    }

    void changQuantity(int i,int quantity){
        shopCarts.get(i).setValue(quantity);
    }

    void deleteGoods(int i){
        shopCarts.remove(i);
    }

    public String showInfo(){
        String s="";
    for(int i=0;i<shopCarts.size();i++) {
        s=s+shopCarts.get(i).getKey().getName()+"      "+shopCarts.get(i).getKey().price+"  X  "+shopCarts.get(i).getValue()+'\n';
    }
    s+="总价"+totalPrice;
    return s;
    }


}
