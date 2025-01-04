package persistence.impl;


import persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FilterDaoImpl {
    final static String add="insert into information(username, action, time,actionType) VALUES (?,?,?,?);";
    public static boolean addToDataBase(String username,String description,String actionType) throws SQLException {
        boolean result=false;
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,description);
        preparedStatement.setTimestamp(3,timestamp);
        preparedStatement.setString(4,actionType);
        int row=preparedStatement.executeUpdate();
        if(row<=0)result=false;
        return result;
    }

}
