package com.example.crud;
import java.util.*;
import java.sql.*;
public class StudentInsert {
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        Connection connect = null;
        PreparedStatement pstmt = null;
        try
        {
            connect = JdbcUtil.getConnection();
            String sql = "INSERT INTO studentInfo(id,sname,sage,scity) VALUES(?, ?, ?, ?)";
            pstmt = connect.prepareStatement(sql);
            System.out.println("Enter The following details to insert: ");
            System.out.print("Enter Number of rows to Insert: ");
            int n = sc.nextInt();
            while(n-- >0) {
                System.out.print("Enter Id: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter City: ");
                String city = sc.nextLine();

                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, city);

                int rowAffected = pstmt.executeUpdate();
                if(rowAffected > 0) System.out.println("Inserted Record Succesfully, Rows Affected: "+rowAffected);
                else System.out.println("Unable to Insert Records");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            sc.close();
            try
            {
                JdbcUtil.closeConnection(connect,pstmt);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
