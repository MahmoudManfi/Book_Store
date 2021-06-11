package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientHomeController {
    public Button addABookButton;
    public Button searchForBookButton;
    public Button modifyABookButton;
    public Button placeOrdersButton;
    public Button confirmOrdersButton;
    public Button logOutButton;

    public void addABookClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/AddNewBooks.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void searchForBookClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SearchForBooks.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void modifyABookClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ModifyExistingBooks.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void placeOrdersClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/PlaceOrderOnBooks.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void confirmOrdersClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ConfirmOrdersOnBooks.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void logOutButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        Stage window = (Stage) addABookButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }
}
