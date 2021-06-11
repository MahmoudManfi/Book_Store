package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchForBooksController {
    public TextField isbnNumberTextField;
    public TextField titleTextField;
    public ComboBox categoryComboBox;
    public TextField authorTextField;
    public TextField publisherTextField;
    public Button goBackButton;
    public Button searchButton;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
    }
}
