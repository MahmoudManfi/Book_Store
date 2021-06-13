package com.library.Controller;

import com.library.Model.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public Button goBackButton;
    public Button signUpButton;
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public PasswordField password;
    public TextField email;
    public TextField phoneNumber;
    public TextField shippingAddress;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void signUpButtonClicked(ActionEvent actionEvent) throws IOException {

        String query = "INSERT INTO book_store.user VALUES ('" +
                username.getText() + "', '" + password.getText() + "', '" +
                lastName.getText() + "', '" + firstName.getText() + "', '" +
                email.getText() + "', '" + phoneNumber.getText() + "', '" +
                shippingAddress.getText() + "', 'Client')";

        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        databaseConnector.executeUpdate(query);

        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }
}
