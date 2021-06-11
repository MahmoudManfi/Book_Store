package com.library.Controller;

public class DatabaseConnector {
    private static DatabaseConnector databaseConnector;

    static DatabaseConnector getInstance() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    private DatabaseConnector() {

    }
}
