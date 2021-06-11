package com.library.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;

public class WelcomingPageController {
    @FXML
    private Button addNewBookButton ;
    @FXML
    private Button updateBookButton ;
    @FXML
    private Button deleteBookButton ;

    @FXML
    public void addNewBookHandler(ActionEvent e ) {
        System.out.println("Add new book button pressed" ) ;
    }


    public void deleteBookHandler(ActionEvent actionEvent) {
        System.out.println("Delete the book " ) ;
    }

    public void updateExistingBookHandler(ActionEvent actionEvent) {
        System.out.println("Detected the updateExisted books button" ) ;
    }

}
