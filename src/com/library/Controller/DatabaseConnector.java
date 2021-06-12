package com.library.Controller;

import com.library.Model.Book;
import com.library.Model.Info;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DatabaseConnector {
    final String myUrl = "jdbc:mysql://localhost:3306/book_store";
    String password = "20101999"; // change to your passowrd
    private static DatabaseConnector databaseConnector;

    private DatabaseConnector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your password: ");
        password = sc.next();
        sc.close();
    }

    static DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    private void execute_select_query(String query) {
        try {
            Connection conn;
            Statement st;
            conn = DriverManager.getConnection(myUrl, "root", password);
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            // iterate through the java resultset
//            while (rs.next()) {
//                String isbn = rs.getString("ISBN_number");
//                String title = rs.getString("title");
//                String author_name = rs.getString("author_name");
//
//                // print the results
//                System.out.format("%s, %s, %s\n", isbn, title, author_name);
//            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    // update or insert
    private void execute_query(String query) {
        try {
            Connection conn;
            Statement st;
            conn = DriverManager.getConnection(myUrl, "root", password);
            st = conn.createStatement();
            int ret = st.executeUpdate(query);
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Query done successfully !! ");
        }
    }


    void addBook(Book book) {
        String query = generateAddQuery(book);
        execute_query(query);
    }

    void updateBook(Book book) {

    }

    private String generateAddQuery(Book book) {
        String query = "insert into book(ISBN_number, title, author_name,publisher_name, " +
                "publication_year, price, category, number_copies, threshold) ";

        query += "values (";
        query += "\"" + book.getIsbn() + "\"" + ", ";
        query += "\"" + book.getTitle() + "\"" + ", ";
        query += "\"" + book.getAuthorName() + "\"" + ", ";
        query += "\"" + book.getPublisherName() + "\"" + ", ";
        query += book.getPublicationYear() + ", ";
        query += book.getPrice() + ", ";
        query += "\"" + book.getCategory() + "\"" + ", ";
        query += book.getNumberCopies() + ", ";
        query += book.getThreshold();
        query += ")";
        System.err.println("Executing query : " + query);
        return query;
    }

    private String generateUpdateQuery(Book book) {
        return null;
    }
}
