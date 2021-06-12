package com.library.Model.databaseTables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItem implements Tuple{

    private Integer number;
    private String ISBNNumber;
    private String userName;
    private Integer numberCopies;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setISBNNumber(String ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNumberCopies(Integer numberCopies) {
        this.numberCopies = numberCopies;
    }

    public Integer getNumber() {
        return number;
    }

    public String getISBNNumber() {
        return ISBNNumber;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getNumberCopies() {
        return numberCopies;
    }

    @Override
    public void build(ResultSet resultSet) throws SQLException {
        setNumber(resultSet.getInt(1));
        setISBNNumber(resultSet.getString(2));
        setUserName(resultSet.getString(3));
        setNumberCopies(resultSet.getInt(4));
    }
}
