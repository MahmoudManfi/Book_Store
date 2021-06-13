package com.library.Controller;

import com.library.Model.databaseTables.Tuple;
import com.library.Model.databaseTables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LoginController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button signInButton;
    public Label label;
    public Button signUpButton;
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void clearUser() {
        user = null;
    }

    public void signInButtonClicked(ActionEvent actionEvent) throws IOException  {
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        String query = "SELECT * FROM book_store.user " +
                "WHERE user_name = '" + usernameTextField.getText() + "' AND password = '" + passwordTextField.getText() +"'";
        List<Tuple> tuples = databaseConnector.executeQuery(query, "user");
        if (tuples.size() == 0) {
            invalidUserNameOrPassword();
        } else {
            user = (User) tuples.get(0);
            switchScene(actionEvent);
        }
    }

    private void switchScene(ActionEvent actionEvent) throws IOException  {
        Parent root;
        if (user.isManager()) {
            root = FXMLLoader.load(getClass().getResource("../View/WelcomeAdmin.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        }
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    private void invalidUserNameOrPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid user name or password");
        alert.setContentText("Please check your user name or password and try again");
        alert.showAndWait();
    }

    public void signUpButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SignUp.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }
}
