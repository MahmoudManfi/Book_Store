package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddNewBooksController {
    public TextField isbnNumberTextField;
    public TextField titleTextField;
    public TextField authorTextField;
    public TextField publisherNameTextField;
    public TextField publicationYearTextField;
    public TextField sellingPriceTextField;
    public TextField categoryTextField;
    public TextField numOfCopiesTextField;
    public Button goBackButton;
    public Button addButton;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) addButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void addButtonClicked(ActionEvent actionEvent) {
    }
}
