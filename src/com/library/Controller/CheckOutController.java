package com.library.Controller;

import com.library.Model.PublisherOrder;
import com.library.Model.databaseTables.Book;
import com.library.Model.databaseTables.CartItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckOutController {
    public TextField cardNoTextField;
    public TextField expiryDateTextField;
    public Button confirmButton;
    public Button goBackButton;

    public void confirmButtonClicked(ActionEvent actionEvent) throws IOException {

        for(Book book: LoginController.getUser().getCart()) {
            if (updateBook(book)) {
                addSoldBook(book);
                alert();
            }
        }

        switchScene(actionEvent);
        LoginController.getUser().getCart().clear();
    }

    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("We are happy to serve you, my dear");
        alert.showAndWait();
    }

    private boolean updateBook(Book book) {
        String query = "UPDATE book_store.book SET number_copies = number_copies - 1" +
                " WHERE ISBN_number = '" + book.getIsbn() + "'";

        int ret = DatabaseConnector.getInstance().executeUpdate(query);
        if (ret > 0) book.setNumberCopies(book.getNumberCopies() - 1);

        if (!book.validate() && ret > 0) {
            PublisherOrder publisherOrder = new PublisherOrder();
            publisherOrder.takeAction(book.getIsbn(), book.getPublisherName(),
                    book.getTitle(), book.getThreshold() - book.getNumberCopies());
        }

        return ret > 0;
    }

    private void addSoldBook(Book book) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String query = "INSERT INTO book_store.sold_book(ISBN_number, user_name, " +
                "date, number_copies, price) VALUES(" +
                "'" + book.getIsbn() + "', " +
                "'" + LoginController.getUser().getUserName() + "'," +
                "'" + dtf.format(now) + "', " +
                1 + ", " + book.getPrice() + ")";

        DatabaseConnector.getInstance().executeUpdate(query);
    }

    private void switchScene(ActionEvent actionEvent) throws IOException {
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

    public void goBackButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Cart.fxml"));
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 1446, 609));
        window.setResizable(false);
    }
}
