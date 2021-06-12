package com.library.Model.databaseTables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Publisher implements Tuple {

    private String name;
    private String address;
    private String phoneNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void build(ResultSet resultSet) throws SQLException {
        setName(resultSet.getString(1));
        setAddress(resultSet.getString(2));
        setPhoneNumber(resultSet.getString(3));
    }
}
