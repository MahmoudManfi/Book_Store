package com.library.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface KingSelect {

    public void build(ResultSet resultSet) throws SQLException;

}
