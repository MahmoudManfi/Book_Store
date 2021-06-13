package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPersonalInformationController implements Initializable {
    public Button goBackButton;
    public Button saveButton;
    public TextField firstName;
    public TextField lastName;
    public TextField username;
    public PasswordField password;
    public TextField email;
    public TextField phoneNumber;
    public TextField shippingAddress;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root;
        if(LoginController.getUser().isManager()) {
            root = FXMLLoader.load(getClass().getResource("../View/WelcomeManager.fxml"));
        }else{
            root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        }
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        String query = "UPDATE book_store.user SET " +
                "user_name = '" + username.getText() + "', " +
                "password = '" + password.getText() + "', " +
                "last_name = '" + lastName.getText() + "', " +
                "first_name = '" + firstName.getText() + "', " +
                "email = '" + email.getText() + "', " +
                "phone_number = '" + phoneNumber.getText() + "', " +
                "shipping_address = '" + shippingAddress.getText() + "' WHERE " +
                "user_name = '" + LoginController.getUser().getUserName() + "'";

        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        databaseConnector.executeUpdate(query);
        alert();
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Please log out and log in again to save changes");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email.setText(LoginController.getUser().getEmail());
        password.setText(LoginController.getUser().getPassword());
        firstName.setText(LoginController.getUser().getFirstName());
        lastName.setText(LoginController.getUser().getLastName());
        username.setText(LoginController.getUser().getUserName());
        phoneNumber.setText(LoginController.getUser().getPhoneNumber());
        shippingAddress.setText(LoginController.getUser().getShippingAddress());
    }
}
