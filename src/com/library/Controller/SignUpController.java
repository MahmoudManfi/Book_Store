package com.library.Controller;

import com.library.Model.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public Button goBackButton;
    public Button signUpButton;
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField usernameTextField;
    public TextField passwordTextField;
    public TextField emailTextField;
    public TextField phineNumberTextField;
    public TextField sharingAddressTextField;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void signUpButtonClicked(ActionEvent actionEvent) {



    }
}
