package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomingPageController {
    @FXML
    public Button searchForBookButton;
    @FXML
    public Button PromoteCustomerButton;
    @FXML
    public Button shoppingCartButton;
    @FXML
    public Button editPersonalButton;
    @FXML
    public Button logOutButton;
    public Button reportSalesButton;
    @FXML
    private Button addNewBookButton;
    @FXML
    private Button updateBookButton;

    @FXML
    public void addNewBookHandler(ActionEvent e) {
        System.out.println("Add new book button pressed");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/addNewBooks.fxml"));
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(new Scene(root, 1206, 588));
            window.setResizable(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public void updateExistingBookHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SearchModifyExistingBooks.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1902, 700));
        window.setResizable(false);
    }


    public void PromoteCustomerButtonHandler(ActionEvent actionEvent) {
    }

    public void searchForBookClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/SearchForBooks.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1900, 596));
        window.setResizable(false);
    }
    public void shoppingCartButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1446, 609));
        window.setResizable(false);
    }

    public void editPersonalButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/EditPersonalInformation.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }
    public void logOutButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        LoginController.clearUser();
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void reportSalesButtonHandler(ActionEvent actionEvent) {
    }
}
