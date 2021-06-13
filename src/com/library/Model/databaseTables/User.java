package com.library.Model.databaseTables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements Tuple {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String shippingAddress;
    private String status;
    List<Book> cartBooks = new ArrayList<>();
    private int totalBookPrice;

    public int getTotalBookPrice() {
        return totalBookPrice;
    }

    public void setTotalBookPrice(int totalBookPrice) {
        this.totalBookPrice = totalBookPrice;
    }


    public List<Book> getCart() {
        return cartBooks;
    }

    public void addToCart(Book book) {
        this.cartBooks.add(book);
        this.totalBookPrice+= book.getPrice();
    }


    public void removeFromCart(Book book) {
        this.cartBooks.remove(book);
        this.totalBookPrice-= book.getPrice();
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getStatus() {
        return status;
    }

    public boolean isManager() {
        return status.compareTo("Manager") == 0;
    }

    @Override
    public void build(ResultSet resultSet) throws SQLException {
        setUserName(resultSet.getString(1));
        setPassword(resultSet.getString(2));
        setLastName(resultSet.getString(3));
        setFirstName(resultSet.getString(4));
        setEmail(resultSet.getString(5));
        setPhoneNumber(resultSet.getString(6));
        setShippingAddress(resultSet.getString(7));
        setStatus(resultSet.getString(8));
    }
}
