package com.library.Model;

import com.library.Controller.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("../View/login.fxml"));
        primaryStage.setTitle("BOOK STORE");
        primaryStage.setScene(new Scene(root, 1206, 588));
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    public static void main(String[] args) { launch(args); }

}
