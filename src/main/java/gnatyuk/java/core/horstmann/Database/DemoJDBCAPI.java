package gnatyuk.java.core.horstmann.Database;


import gnatyuk.java.core.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJDBCAPI {
    private static final String url = Utils.getPropertyValue("database.properties", "database.url");
    private static final String user = Utils.getPropertyValue("database.properties", "database.user");
    private static final String pass = Utils.getPropertyValue("database.properties", "database.password");

    public static void main(String[] args) {
        registerMySqlDriver();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void registerMySqlDriver(){
        //            Class.forName("com.mysql.jdbc.Driver");
        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
    }

    private static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
