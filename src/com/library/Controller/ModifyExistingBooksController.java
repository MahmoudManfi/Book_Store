package com.library.Controller;

import com.library.Model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyExistingBooksController {

    @FXML
    private TextField isbnNumberTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField publisherNameTextField;
    @FXML
    private TextField publicationYearTextField;
    @FXML
    private TextField sellingPriceTextField;
    @FXML
    private TextField categoryTextField;
    @FXML
    private TextField numOfCopiesTextField;
    @FXML
    private TextField thresholdTextField;

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }


    public void saveButtonClicked(ActionEvent event) {
        System.out.println("Updating existing book in progress....");
        Book newBook = null;
        try {
            newBook = generateFromLabels();
            DatabaseConnector.getInstance().updateBook(newBook);
        } catch (RuntimeException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Input");
            alert.setContentText("You can't insert strings in integer places ");
            alert.showAndWait();
        }
    }

    Book generateFromLabels() {
        Book book = new Book();
        book.setIsbn(isbnNumberTextField.getText().trim());
        book.setTitle(titleTextField.getText().trim());
        book.setAuthorName(authorTextField.getText().trim());
        book.setPublicationYear(publicationYearTextField.getText().trim());
        book.setCategory(categoryTextField.getText().trim());
        book.setNumberCopies(Integer.valueOf(numOfCopiesTextField.getText().trim()));
        book.setPublisherName(publisherNameTextField.getText().trim());
        book.setPrice(Integer.valueOf(sellingPriceTextField.getText().trim()));
        book.setThreshold(Integer.valueOf(thresholdTextField.getText().trim()));
        return book;
    }

}
