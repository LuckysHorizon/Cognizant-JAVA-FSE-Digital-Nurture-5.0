package com.example.crud;
import java.sql.*;
public class JdbcUtil {
    static
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/jdbclearning";
        String user = "root";
        String password = "Lucky@123";
        return DriverManager.getConnection(url,user,password);
    }
    public static void closeConnection(Connection connect,Statement stmt) throws SQLException
    {
        connect.close();
        stmt.close();
    }
    public static void closeConnection(Connection connect, PreparedStatement pstmt) throws SQLException
    {
        connect.close();
        pstmt.close();
    }
}
