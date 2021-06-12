package com.library.Model.databaseTables;

public class FactoryTable {

    public Tuple getTable(String tableName) {
        if (tableName.compareTo("book") == 0) {
            return new Book();
        } else if (tableName.compareTo("book_order") == 0) {
            return new BookOrder();
        } else if (tableName.compareTo("cart_item") == 0) {
            return new CartItem();
        } else if (tableName.compareTo("publisher") == 0) {
            return new Publisher();
        } else if (tableName.compareTo("sold_book") == 0) {
            return new SoldBook();
        } else if (tableName.compareTo("user") == 0) {
            return new User();
        } else {
            return null;
        }
    }

}
