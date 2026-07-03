package com.example.crud;
import java.util.*;
import java.sql.*;
public class ShowStudents {
    public static void main(String [] args)
    {
        Connection connect = null;
        PreparedStatement stmnt = null;
        try
        {
            //Connect to DB
            connect = JdbcUtil.getConnection();
            String sql = "SELECT id,sname,sage,scity FROM studentInfo";
            stmnt = connect.prepareStatement(sql);

            ResultSet rs = stmnt.executeQuery();
            while(rs.next())
            {
                System.out.println("id: "+ rs.getInt(1) + " Name: "+ rs.getString(2) + " Age: "+ rs.getInt(3)+ " City: "+ rs.getString(4));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                JdbcUtil.closeConnection(connect,stmnt);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
