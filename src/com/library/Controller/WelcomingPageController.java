package com.library.Controller;

import com.library.Model.Book;
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
        DatabaseConnector.getInstance().execute_query("select * from book ");
    }


    public void deleteBookHandler(ActionEvent actionEvent) {
        System.out.println("Delete the book ");
    }

    public void updateExistingBookHandler(ActionEvent actionEvent) {
        System.out.println("Detected the updateExisted books button");
    }

    Book generateFromLabels() {
        return null;
    }

}
