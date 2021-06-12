package com.library.Model.databaseTables;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Tuple {

    public void build(ResultSet resultSet) throws SQLException;

}
