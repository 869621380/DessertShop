package persistence;

import java.sql.*;

public class DBUtil {
    private static String Driver="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/demo";
    private static String username="root";
    private static String password="root";

    //获取链接
    public static java.sql.Connection getConnection(){
        java.sql.Connection connection=null;
        try {
            Class.forName(Driver);
            connection= DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    //中断链接
    public static void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void closePreparedStatement(PreparedStatement statement){
        if(statement!=null){
            try {
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement stmt) throws SQLException {
        stmt.close();
    }
}
