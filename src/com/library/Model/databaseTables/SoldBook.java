package com.library.Model.databaseTables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SoldBook implements Tuple {

    private Integer number;
    private String ISBNNumber;
    private String userName;
    private String date;
    private Integer numberCopies;
    private Integer price;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setISBNNumber(String ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumberCopies(Integer numberCopies) {
        this.numberCopies = numberCopies;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public String getDate() {
        return date;
    }

    public Integer getNumberCopies() {
        return numberCopies;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public void build(ResultSet resultSet) throws SQLException {
        setNumber(resultSet.getInt(1));
        setISBNNumber(resultSet.getString(2));
        setUserName(resultSet.getString(3));
        setDate(resultSet.getString(4));
        setNumberCopies(resultSet.getInt(5));
        setPrice(resultSet.getInt(6));
    }
}
