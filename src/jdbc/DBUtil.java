package jdbc;

import java.sql.*;

public class DBUtil {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/demo";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConnection() throws Exception {
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
    public static void closeStatement(Statement stmt) throws SQLException {
        stmt.close();
    }
    public static void closeResultSet(ResultSet rs) throws SQLException {
        rs.close();
    }
}
