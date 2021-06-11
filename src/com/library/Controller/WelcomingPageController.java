package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.sql.*;

public class WelcomingPageController {
    @FXML
    private Button addNewBookButton;
    @FXML
    private Button updateBookButton;
    @FXML
    private Button deleteBookButton;

    @FXML
    public void addNewBookHandler(ActionEvent e) {
        System.out.println("Add new book button pressed");
        try {
            // create our mysql database connection
//            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/book_store";
//            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "20101999");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM book";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("ISBN_number");
                String isbn = rs.getString("ISBN_number");
                String title = rs.getString("title");
                String author_name = rs.getString("author_name");

                // print the results
                System.out.format("%s, %s, %s\n", isbn, title, author_name);
            }
            st.close();
        } catch (Exception ee) {
            System.err.println("Got an exception! ");
            System.err.println(ee.getMessage());
        }
    }


    public void deleteBookHandler(ActionEvent actionEvent) {
        System.out.println("Delete the book ");
    }

    public void updateExistingBookHandler(ActionEvent actionEvent) {
        System.out.println("Detected the updateExisted books button");
    }

}
