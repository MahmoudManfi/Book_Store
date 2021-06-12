package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {
    public TextField isbnNumberTextField;
    public TextField titleTextField;
    public ComboBox categoryComboBox;
    public TextField authorTextField;
    public TextField publisherTextField;
    public TextField publicationYearTextField;
    public TextField priceTextField;
    public TextField numOfCopiesTextField;
    public TextField thresholdTextField;
    public Button addToCartButton;
    public TableView cartTbableView;
    public Button goBackButton;
    public Button removeButton;
    public Button checkoutButton;

    public void addToCartButtonClicked(ActionEvent actionEvent) {
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void removeButtonClicked(ActionEvent actionEvent) {
    }

    public void checkoutButtonClicked(ActionEvent actionEvent) {
    }
}
