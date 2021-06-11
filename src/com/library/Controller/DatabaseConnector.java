package com.library.Controller;

import java.sql.*;
import java.util.Scanner;

public class DatabaseConnector {
    final String myUrl = "jdbc:mysql://localhost:3306/book_store";
    private static DatabaseConnector databaseConnector;


    static DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }
    void execute_query(String query) {
        try {
            Connection conn;
            Statement st;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter root Connection Password: ");
            // change to your passowrd
            String password = scanner.next();
            conn = DriverManager.getConnection(myUrl, "root", password);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset
            while (rs.next()) {
                String isbn = rs.getString("ISBN_number");
                String title = rs.getString("title");
                String author_name = rs.getString("author_name");

                // print the results
                System.out.format("%s, %s, %s\n", isbn, title, author_name);
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}
