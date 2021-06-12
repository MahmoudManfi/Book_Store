package com.library.Controller;

import com.library.Model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
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
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/addNewBooks.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 1206, 588));
            window.setResizable(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void deleteBookHandler(ActionEvent actionEvent) {
        System.out.println("Delete the book is not availabe :D  ");

    }
    public void updateExistingBookHandler(ActionEvent actionEvent) {
        System.out.println("Detected the updateExisted books button");
    }


}
