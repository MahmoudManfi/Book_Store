package com.library.Model;

public class FactorySelect {

    public KingSelect getTable(String tableName) {
        if (tableName.compareTo("book") == 0) {
            return new Book();
        } else {
            return null;
        }
    }

}
