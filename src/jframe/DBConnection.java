/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;



//import java.sql.Connection;  
//import java.sql.DriverManager;  
//import java.sql.SQLException;
/**
 *
 * @author Administrator
 *//*
public class DBConnection {
    static Connection con = null;

   public static Connection getConnection(){
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
       } catch (Exception e) {
           e.printStackTrace();
       }
       return con;
   }
    
}
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    static Connection con = null;

    public static Connection getConnection() {
        try {
            // Đăng ký driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối MySQL mà không chỉ rõ cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");

            // Tạo cơ sở dữ liệu nếu chưa có
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS library_ms";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(createDatabaseSQL);

            // Kết nối lại với cơ sở dữ liệu vừa tạo
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms", "root", "");

            // Tạo lại đối tượng Statement sau khi kết nối lại với database
            stmt = con.createStatement();

            // Tạo bảng users nếu chưa có
            String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(50), "
                    + "email VARCHAR(100), "
                    + "password VARCHAR(50),"
                    + "contact INT(50))";
                                         
                  
            stmt.executeUpdate(createUsersTableSQL);

            // Tạo bảng book_details nếu chưa có
            String createBooksTableSQL = "CREATE TABLE IF NOT EXISTS book_details ("
                    + "book_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "book_name VARCHAR(255) NOT NULL, "
                    + "author VARCHAR(255) NOT NULL, "
                    + "quantity INT NOT NULL)";
            stmt.executeUpdate(createBooksTableSQL);

            // Tạo bảng student_details nếu chưa có
            String createStudentsTableSQL = "CREATE TABLE IF NOT EXISTS student_details ("
                    + "student_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "course VARCHAR(255) NOT NULL, "
                    + "branch VARCHAR(255) NOT NULL)";
            stmt.executeUpdate(createStudentsTableSQL);

            // Tạo bảng issue_book_details nếu chưa có
            String createIssueBookDetailsTableSQL = "CREATE TABLE IF NOT EXISTS issue_book_details ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "book_id INT NOT NULL, "
                    + "book_name VARCHAR(255) NOT NULL, "
                    + "student_id INT NOT NULL, "
                    + "student_name VARCHAR(255) NOT NULL, "
                    + "issue_date DATE NOT NULL, "
                    + "due_date DATE NOT NULL, "
                    + "status VARCHAR(50) NOT NULL)";
            stmt.executeUpdate(createIssueBookDetailsTableSQL);

            System.out.println("Database and tables created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    
}

