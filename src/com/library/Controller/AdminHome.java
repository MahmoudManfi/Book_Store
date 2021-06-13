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
        String query = "UPDATE book_store.user SET status = 'Manager' WHERE user_name = '" + username.getText() + "'";
        int res = DatabaseConnector.getInstance().executeUpdate(query);
        if (res == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in user name");
            alert.setContentText("This user name doesn't exist");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("The user is promoted correctly");
            alert.showAndWait();
        }
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/WelcomeManager.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

}
