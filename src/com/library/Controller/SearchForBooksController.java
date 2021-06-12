package com.library.Controller;

import com.library.Model.databaseTables.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchForBooksController implements Initializable {
    public TableView searchTableView;
    TableView.TableViewSelectionModel<Book> selectionModel;

    public TextField priceTextField;
    public TextField numOfCopiesTextField;
    public TextField thresholdTextField;
    public Button addToCartButton;
    public Button modifyButton;
    ObservableList<String> options =
            FXCollections.observableArrayList("Science", "Art", "Religion","History", "Geography");
    public TextField isbnNumberTextField;
    public TextField titleTextField;
    @FXML
    public ComboBox<String> categoryComboBox;
    @FXML
    public TextField authorTextField;
    @FXML
    public TextField publisherNameTextField;
    @FXML
    public Button goBackButton;
    @FXML
    public Button searchButton;
    @FXML
    public TextField publicationYearTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TableColumn<Book, String>> tableColumns= searchTableView.getColumns();
        tableColumns.get(0).setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tableColumns.get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumns.get(2).setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tableColumns.get(3).setCellValueFactory(new PropertyValueFactory<>("publisherName"));
        tableColumns.get(4).setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        tableColumns.get(5).setCellValueFactory(new PropertyValueFactory<>("price"));
        tableColumns.get(6).setCellValueFactory(new PropertyValueFactory<>("category"));
        tableColumns.get(7).setCellValueFactory(new PropertyValueFactory<>("numberCopies"));
        tableColumns.get(8).setCellValueFactory(new PropertyValueFactory<>("threshold"));

        categoryComboBox.getItems().addAll(options);
        //addToCartButton.setDisable(true);
        //modifyButton.setDisable(true);
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        Stage window = (Stage) goBackButton.getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        searchTableView.getItems().clear();
        System.out.println("Searching for books in progress....");
        Book newBook = null;
        try {
            newBook = extractBookFields();
            List<Book> resultBooks = DatabaseConnector.getInstance().searchForBooks(newBook);
            for(Book book:resultBooks){
                searchTableView.getItems().add(new Book(book.getIsbn(), book.getTitle(), book.getAuthorName(),
                        book.getPublisherName(), book.getPublicationYear(), book.getPrice(), book.getCategory(),
                        book.getNumberCopies(), book.getThreshold()));
            }
        } catch (NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Input");
            alert.showAndWait();
        } catch (RuntimeException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Input");
            alert.setContentText("You can't insert strings in integer places ");
            alert.showAndWait();
        }
    }
    Book extractBookFields() throws NullPointerException {
        Book book = new Book();
        book.setIsbn(isbnNumberTextField.getText().trim());
        book.setTitle(titleTextField.getText().trim());
        book.setAuthorName(authorTextField.getText().trim());
        book.setPublisherName(publisherNameTextField.getText().trim());
        book.setPublicationYear(publicationYearTextField.getText().trim());
        book.setCategory((String) categoryComboBox.getValue());
        return book;
    }

    public void categoryComboBoxClicked(ActionEvent actionEvent) {
    }


    public void addToCartButtonHandler(ActionEvent actionEvent) {

    }

    public void modifyButtonHandler(ActionEvent actionEvent) {
        System.out.println("Updating existing book in progress....");
        Book newBook;
        newBook = generateFromLabels();
        ObservableList<Book> selectedItems = selectionModel.getSelectedItems();
        selectedItems = (ObservableList<Book>) newBook;
        try{
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
        book.setCategory(categoryComboBox.getValue());
        book.setNumberCopies(Integer.valueOf(numOfCopiesTextField.getText().trim()));
        book.setPublisherName(publisherNameTextField.getText().trim());
        book.setPrice(Integer.valueOf(priceTextField.getText().trim()));
        book.setThreshold(Integer.valueOf(thresholdTextField.getText().trim()));
        return book;
    }
}
