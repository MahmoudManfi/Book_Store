package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckOutController {
    public TextField cardNoTextField;
    public TextField expiryDateTextField;
    public Button confirmButton;
    public Button goBackButton;

    public void confirmButtonClicked(ActionEvent actionEvent) {
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1903, 704));
        window.setResizable(false);
    }
}
