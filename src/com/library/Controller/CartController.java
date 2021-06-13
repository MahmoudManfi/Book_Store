package com.library.Controller;

import com.library.Model.databaseTables.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    
    public TableView cartTbableView;
    public TableView.TableViewSelectionModel selectionModel;
    public Button removeButton;
    public Button checkoutButton;
    public Button goHomeButton;
    public TextField priceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TableColumn<Book, String>> tableColumns = cartTbableView.getColumns();
        tableColumns.get(0).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableColumns.get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumns.get(2).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tableColumns.get(3).setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        tableColumns.get(4).setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        tableColumns.get(5).setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumns.get(6).setCellValueFactory(new PropertyValueFactory<>("category"));
        tableColumns.get(7).setCellValueFactory(new PropertyValueFactory<>("numberCopies"));
        tableColumns.get(8).setCellValueFactory(new PropertyValueFactory<>("threshold"));

        cartTbableView.getItems().addAll(LoginController.getUser().getCart());

        priceLabel.setText(String.valueOf(LoginController.getUser().getTotalBookPrice()));

        removeButton.setDisable(true);
    }


    public void removeButtonClicked(ActionEvent actionEvent) {
        selectionModel = cartTbableView.getSelectionModel();
        Book selectBook = (Book)selectionModel.getSelectedItem();
        if(selectBook != null) {
            LoginController.getUser().removeFromCart(selectBook);
            cartTbableView.getItems().remove(selectBook);
            priceLabel.setText(String.valueOf(LoginController.getUser().getTotalBookPrice()));
        }
    }

    public void checkoutButtonClicked(ActionEvent actionEvent) {

    }

    public void goHomeButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root;
        if (LoginController.getAdmin()) {
            root = FXMLLoader.load(getClass().getResource("../View/WelcomeAdmin.fxml"));
        } else if(LoginController.getUser().isManager()) {
            root = FXMLLoader.load(getClass().getResource("../View/ManagerHome.fxml"));
        }else{
            root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        }
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void tableViewHandler(MouseEvent mouseEvent) {
        selectionModel = cartTbableView.getSelectionModel();
        Book selectBook = (Book)selectionModel.getSelectedItem();
        if(selectBook!=null){
            removeButton.setDisable(false);
        }else{
            removeButton.setDisable(true);
        }
    }
}
