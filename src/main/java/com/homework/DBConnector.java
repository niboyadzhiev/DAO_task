package com.homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    String driver;
    String DBUrl;
    String userName;
    String password;
    String schema;


    public DBConnector(String driver, String DBUrl, String userName, String password, String schema) {
        this.driver = driver;
        this.DBUrl = DBUrl;
        this.userName = userName;
        this.password = password;
        this.schema = schema;
    }

    public Connection getConnection () throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://"+DBUrl+"/"+schema+"?" + "user="+userName+"&password="+password);
    }



}
