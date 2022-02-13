package com.example.alreef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connecter {
    private String dbURL;
    private String dbUsername = "root";
    private String dbPassword = "Bzu-0569555534";
    private String URL = "127.0.0.1";
    private String port = "3306";
    private String dbName = "db_project";
    private Connection con;

    public Connection connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        Class.forName("com.mysql.jdbc.Driver");

        return con = DriverManager.getConnection(dbURL, p);

    }
}
