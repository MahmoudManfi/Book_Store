package com.library.Controller;

import com.library.Model.databaseTables.Tuple;
import com.library.Model.databaseTables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminHome {

    public TextField username;

    public void promoteButtonClicked(ActionEvent actionEvent) {
        if (loadUser()) {
            String query = "UPDATE book_store.user SET status = 'Manager' WHERE user_name = '" + username.getText() + "'";
            DatabaseConnector.getInstance().executeUpdate(query);
        }
    }



    public void editButtonClicked(ActionEvent actionEvent) throws IOException {
        if(loadUser()) {
            Parent root = FXMLLoader.load(getClass().getResource("../View/EditPersonalInformation.fxml"));
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 1206, 588));
            window.setResizable(false);
        }
    }

    private boolean loadUser() {
        String query = "SELECT * FROM book_store.user WHERE user_name = '" + username.getText() + "'";

        List<Tuple> tuples = DatabaseConnector.getInstance().executeQuery(query, "user");
        if (tuples.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in user name");
            alert.setContentText("This user name doesn't exist");
            return false;
        } else {
            LoginController.setUser((User)tuples.get(0));
            return true;
        }
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/WelcomeAdmin.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
        LoginController.clearUser();
    }

}
