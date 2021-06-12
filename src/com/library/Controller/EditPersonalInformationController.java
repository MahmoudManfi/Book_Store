package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPersonalInformationController {
    public Button goBackButton;
    public Button saveButton;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
    }
}
