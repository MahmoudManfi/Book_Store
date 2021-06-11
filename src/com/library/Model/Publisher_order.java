package com.library.Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Publisher_order {

    public void takeAction(String ISBN_number, String publisherName, String bookTitle, int numberCopies) {
        insertOrder(ISBN_number, numberCopies);
        showConfirmAlert(publisherName, bookTitle, numberCopies);
    }

    private void insertOrder(String ISBN_number, int numberCopies) {
        System.out.println("Insert order for confirmation");
    }

    private void showConfirmAlert(String publisherName, String bookTitle, int numberCopies){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Order from " + publisherName);
        alert.setContentText("Book book: " + bookTitle + "\n" + "Quantity: " + numberCopies);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deleteOrder();
        }
    }

    private void deleteOrder() {
        System.out.println("Delete order after confirmation");
    }

}
