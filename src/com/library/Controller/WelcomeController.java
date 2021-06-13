package com.library.Controller;

import com.library.Model.databaseTables.Tuple;
import com.library.Model.databaseTables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class WelcomeController {
    @FXML
    private Button clientButton;
    @FXML
    private Button AdminButton;
    @FXML
    private Button signUpButton;
    @FXML
    private PasswordField password;

    public void clientButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void adminButtonHandler(ActionEvent actionEvent) throws IOException {

        if (password.getText().compareTo("Salama") == 0 && loadAdmin()) {
            Parent root = FXMLLoader.load(getClass().getResource("../View/WelcomeAdmin.fxml"));
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 1206, 588));
            window.setResizable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin Password is wrong");
            alert.setContentText("Please check the password and try again");
            alert.showAndWait();
        }
    }

    private boolean loadAdmin() {
        String query = "SELECT * FROM book_store.user WHERE user_name = 'Admin'";
        List<Tuple> tuples = DatabaseConnector.getInstance().executeQuery(query, "user");
        if (tuples.size() == 0) {
            return false;
        } else {
            LoginController.setUser((User)tuples.get(0));
            LoginController.setAdmin();
            return true;
        }
    }

    public void signUpButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SignUp.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

}
