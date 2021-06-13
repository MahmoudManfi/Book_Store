package com.library.Model;

import com.library.Controller.DatabaseConnector;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PublisherOrder {

    public void takeAction(String ISBN_number, String publisherName, String bookTitle, int numberCopies) {
        insertOrder(ISBN_number, numberCopies);
        showConfirmAlert(publisherName, bookTitle, numberCopies);
    }

    private void insertOrder(String ISBN_number, int numberCopies) {
        System.out.println("Insert order for confirmation");

        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

        String query = "INSERT INTO book_store.book_order (ISBN_number, number_copies) " +
                "VALUES ('" + ISBN_number + "', " + numberCopies + ")";

        System.out.println(query);
        databaseConnector.executeUpdate(query);
    }


    private void showConfirmAlert(String publisherName, String bookTitle, int numberCopies){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Order from " + publisherName);
        alert.setContentText("Book title: " + bookTitle + "\n" + "Quantity: " + numberCopies);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            deleteOrder();
        }
    }

    private void deleteOrder() {
        System.out.println("Delete order after confirmation");
        DatabaseConnector databaseConnector = DatabaseConnector.getInstance();
        String query = "DELETE FROM book_store.book_order " +
                "WHERE (number) IN (@@identity)";
        databaseConnector.executeUpdate(query);
    }
}
