package com.library.Controller;

import com.library.Model.PublisherOrder;
import com.library.Model.databaseTables.Book;
import com.library.Model.databaseTables.Tuple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.List;
import java.util.ResourceBundle;

public class SearchForBooksController implements Initializable {
    public TableView searchTableView;
    public TableView.TableViewSelectionModel selectionModel;
    public TextField priceTextField;
    public TextField numOfCopiesTextField;
    public TextField thresholdTextField;
    public Button addToCartButton;
    public Button modifyButton;
    public Button goToCartButton;
    public Button clearButton;
    ObservableList<String> options =
            FXCollections.observableArrayList("Science", "Art", "Religion", "History", "Geography");
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
        ObservableList<TableColumn<Book, String>> tableColumns = searchTableView.getColumns();
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
        addToCartButton.setDisable(true);
    }

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root;
        if(LoginController.getUser().isManager()) {
            root = FXMLLoader.load(getClass().getResource("../View/WelcomeManager.fxml"));
        }else{
            root = FXMLLoader.load(getClass().getResource("../View/ClientHome.fxml"));
        }
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1206, 588));
        window.setResizable(false);
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        searchTableView.getItems().clear();
        System.out.println("Searching for books in progress....");
        Book newBook;
        try {
            newBook = extractBookFields();
            List<Tuple> resultBooks = DatabaseConnector.getInstance().searchForBooks(newBook);
            for (Tuple tuple : resultBooks) {
                Book book = (Book)tuple;
                searchTableView.getItems().add(new Book(book.getIsbn(), book.getTitle(), book.getAuthorName(),
                        book.getPublisherName(), book.getPublicationYear(), book.getPrice(), book.getCategory(),
                        book.getNumberCopies(), book.getThreshold()));
            }
            if(searchTableView.getItems().size()>0){
                addToCartButton.setDisable(false);
                if(LoginController.getUser().isManager()) {
                    modifyButton.setDisable(false);
                }
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
    public void addToCartButtonHandler(ActionEvent actionEvent) {
        selectionModel = searchTableView.getSelectionModel();
        Book selectedBook = (Book)selectionModel.getSelectedItem();
        if(selectedBook!=null){
            LoginController.getUser().addToCart(selectedBook);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add To Cart");
            alert.setContentText("Successfully Added to your Cart ");
            alert.showAndWait();
        }
    }

    public void modifyButtonHandler(ActionEvent actionEvent) {
        System.out.println("Updating existing book in progress....");
        Book newBook;
        try{
            newBook = generateFromLabels();
            selectionModel = searchTableView.getSelectionModel();
            Book selectBook = (Book)selectionModel.getSelectedItem();
            DatabaseConnector.getInstance().updateBook(newBook);
            searchTableView.getItems().remove(selectBook);
            searchTableView.getItems().add(newBook);
            if (!newBook.validate()) {
                PublisherOrder publisherOrder = new PublisherOrder();
                publisherOrder.takeAction(newBook.getIsbn(), newBook.getPublisherName(), newBook.getTitle(), newBook.getThreshold() - newBook.getNumberCopies());
            }

        } catch (RuntimeException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong Input");
            alert.setContentText("You can't insert strings in integer places ");
            alert.showAndWait();
        }
    }

    public void tableViewHandler(MouseEvent mouseEvent) {
        selectionModel = searchTableView.getSelectionModel();
        Book selectBook = (Book)selectionModel.getSelectedItem();
        if(selectBook!=null){
            updateTextFields(selectBook);
            addToCartButton.setDisable(false);
        }else{
            addToCartButton.setDisable(true);
        }
    }    public void goToCartButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1446, 609));
        window.setResizable(false);
    }

    public void clearButtonClicked(ActionEvent actionEvent) {
        addToCartButton.setDisable(true);
        searchTableView.getItems().clear();
        isbnNumberTextField.clear();
        titleTextField.clear();
        authorTextField.clear();
        publisherNameTextField.clear();
        publicationYearTextField.clear();
        if(LoginController.getUser().isManager()){
            numOfCopiesTextField.clear();
            priceTextField.clear();
            thresholdTextField.clear();
            modifyButton.setDisable(true);
        }
    }

    Book extractBookFields() throws NullPointerException {
        Book book = new Book();
        book.setIsbn(isbnNumberTextField.getText().trim());
        book.setTitle(titleTextField.getText().trim());
        book.setAuthorName(authorTextField.getText().trim());
        book.setPublisherName(publisherNameTextField.getText().trim());
        book.setPublicationYear(publicationYearTextField.getText().trim());
        book.setCategory(categoryComboBox.getValue());
        return book;
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


    private void updateTextFields(Book book){
        isbnNumberTextField.setText(book.getIsbn());
        titleTextField.setText(book.getTitle());
        authorTextField.setText(book.getAuthorName());
        publicationYearTextField.setText(book.getPublicationYear());
        categoryComboBox.setValue(book.getCategory());
        publisherNameTextField.setText(book.getPublisherName());
        if(LoginController.getUser().isManager()) {
            numOfCopiesTextField.setText(String.valueOf(book.getNumberCopies()));
            priceTextField.setText(String.valueOf(book.getPrice()));
            thresholdTextField.setText(String.valueOf(book.getThreshold()));
        }
    }

}
