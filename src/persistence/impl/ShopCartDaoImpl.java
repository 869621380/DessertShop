package persistence.impl;

import domain.Goods;
import domain.Order;
import domain.Pair;
import domain.ShopCart;
import persistence.DBUtil;
import persistence.ShopCartDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShopCartDaoImpl implements ShopCartDao {
    private static final String Insert_Goods=
            "INSERT INTO `shopping_cart`(username, goods_name, description,quantity, price, img_src) " +
                    "VALUES (?,?,?,?,?,?);";
    private static final String Delete_Goods=
            "DELETE FROM shopping_cart WHERE id=?; ";
    private static final String Find_ShopCart=
            "SELECT * FROM `shopping_cart` where username= ? ;";
    private static final String Change_Quantity=
            "UPDATE shopping_cart SET quantity =? WHERE id= ?;";
    private static final String Check_Goods=
            "SELECT * FROM `shopping_cart` where goods_name= ? AND username=?;";
    private static final String TOTAL_ROWS=
            "SELECT COUNT(*) AS totalRows FROM shopping_cart WHERE username =?;";

    private static final String Create_Order=
            "INSERT INTO orders (username, order_date, address, total_price, status)" +
                    "VALUES (?, ?, ?, ?, 'pending');";
    private static final String Insert_Goods_Order=
            "insert into order_details(order_id, product_name, quantity, price, img_src,description)" +
                    " VALUES (?,?,?,?,?,?)";
    private static final String Find_Order=
            "SELECT * FROM `orders` where order_id= ?;";
    private static final String Find_Order_detail=
            "SELECT * FROM `order_details` where order_id= ?;";
    private static final String Find_ALL_Order=
            "SELECT * FROM `orders` where username= ?;";



    @Override
    public boolean insertGoods(String username,Goods goods) {
        if(checkGoods(goods.getName(),username))
            return false;
        boolean result=false;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Insert_Goods);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,goods.getName());
            preparedStatement.setString(3,goods.getDescription());
            preparedStatement.setInt(4,1);
            preparedStatement.setDouble(5,goods.getPrice());
            preparedStatement.setString(6,goods.getImgUrl());
            int rows=preparedStatement.executeUpdate();
            if(rows==1)result=true;

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);


        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteGoods(int id) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Delete_Goods);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) result = true;

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean changGoodsQuantity(int id, int newQuantity) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Change_Quantity);
            // 修正参数设置顺序，先设置 quantity 字段对应的值（newQuantity）
            preparedStatement.setInt(1, newQuantity);
            // 再设置 WHERE 条件中 id 字段对应的值（id）
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                result = true;
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int createOrder(String username, String address, ShopCart shopCart) {
        int orderId=0;
        try {
            if(shopCart.shopCarts.size()==0)return 0;
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Create_Order,PreparedStatement.RETURN_GENERATED_KEYS);
            //获取当前时间
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);

            preparedStatement.setString(1, username);
            preparedStatement.setTimestamp(2,  timestamp);
            preparedStatement.setString(3,address);
            preparedStatement.setDouble(4,shopCart.getTotalPrice());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                // 获取生成的订单 ID
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
                insertGoodsToOrderDetail(orderId,shopCart);
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public int getTotalRows(String username) {
        int result = -1;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(TOTAL_ROWS);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("totalRows");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public ShopCart findShopCart(String username){
        ShopCart shopCart=null;
        ArrayList<Pair<Goods,Integer>>goodsList=new ArrayList<>();
        double total_price=0;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Find_ShopCart);
            preparedStatement.setString(1,username);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Pair<Goods,Integer> result=this.resultSetToGoodsInfo(resultSet);
                goodsList.add(result);
                total_price+=result.getKey().getPrice()*result.getValue();
            }
            shopCart=new ShopCart(goodsList,total_price);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return shopCart;
    }

    @Override
    public ArrayList<Integer> findAllOrderId(String username) {
        ArrayList<Integer>arrayList=new ArrayList<>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Find_ALL_Order);
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
                arrayList.add(resultSet.getInt("order_id"));
            resultSet.close();
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

    //检验当前货物是否已经被添加进购物车
    public boolean checkGoods(String good_name,String username){
        boolean result=false;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Check_Goods);
            preparedStatement.setString(2,username);
            preparedStatement.setString(1,good_name);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
                result=true;
            resultSet.close();
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Order getOrder(int id) {
        Order order=null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Find_Order);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                String username=resultSet.getString("username");
                String address=resultSet.getString("address");
                Timestamp timestamp=resultSet.getTimestamp("order_date");
                double total_price=resultSet.getDouble("total_price");
                String status=resultSet.getString("status");
                ArrayList<Pair<Goods,Integer>>arrayList=new ArrayList<>();
                preparedStatement=connection.prepareStatement(Find_Order_detail);
                preparedStatement.setInt(1,id);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    String goods_name=resultSet.getString("product_name");
                    int quantity=resultSet.getInt("quantity");
                    String description=resultSet.getString("description");
                    double price=resultSet.getDouble("price");
                    String img_src=resultSet.getString("img_src");
                    Goods goods=new Goods(0,goods_name,description,img_src,price);
                    arrayList.add(new Pair<>(goods,quantity));
                }
                order=new Order(id,arrayList,username,timestamp,address,total_price,status);
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    private Pair<Goods,Integer> resultSetToGoodsInfo (ResultSet resultSet) throws SQLException {
        int id=resultSet.getInt("id");
        String goods_name=resultSet.getString("goods_name");
        String description=resultSet.getString("description");
        Integer  quantity=resultSet.getInt("quantity");
        double price=resultSet.getDouble("price");
        String img_src=resultSet.getString("img_src");
        Goods goods=new Goods(id,goods_name,description,img_src,price);

        return new Pair<>(goods, quantity);
    }

    //"insert into order_details(order_id, product_name, quantity, price, img_src,description)" +
    //                    " VALUES (?,?,?,?,?,?)";
    private boolean insertGoodsToOrderDetail(int order_id,ShopCart shopCart){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Goods_Order);

            for(int i=0;i<shopCart.getShopCarts().size();i++){
                Goods goods= shopCart.getShopCarts().get(i).getKey();
                preparedStatement.setInt(1,order_id);
                preparedStatement.setString(2,goods.getName());
                preparedStatement.setInt(3,shopCart.shopCarts.get(i).getValue());
                preparedStatement.setDouble(4,goods.getPrice());
                preparedStatement.setString(5,goods.getImgUrl());
                preparedStatement.setString(6,goods.getDescription());
                preparedStatement.executeUpdate();
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
