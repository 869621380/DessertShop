package domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Order {

    int order_id;
    ArrayList<Pair<Goods,Integer>>goodsArrayList;
    String username;
    String address;
    Timestamp timestamp;
    double total_price;
    String order_status;


    public Order(int order_id, ArrayList<Pair<Goods, Integer>> goodsArrayList, String username,
                 Timestamp timestamp, String address, double total_price, String order_status) {
        this.order_id = order_id;
        this.goodsArrayList = goodsArrayList;
        this.username = username;
        this.timestamp = timestamp;
        this.address = address;
        this.total_price = total_price;
        this.order_status = order_status;
    }

    public String getUsername() {
        return username;
    }

    public String getOrder_status() {
        return order_status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Pair<Goods, Integer>> getGoodsArrayList() {
        return goodsArrayList;
    }

    public int getOrder_id() {
        return order_id;
    }



}
