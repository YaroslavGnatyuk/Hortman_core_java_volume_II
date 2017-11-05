package gnatyuk.java.core.jdbc;

import com.mysql.jdbc.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

/**
 * Created by yaroslav on 30/05/16.
 */
public class DemoJdbc {
    public static void main(String[] args) {
        try {
         /*   Class.forName("com.mysql.jdbcDriver");
            Connection connection = DriverManager.getConnection();*/

            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://178.219.93.93");
            mysqlDataSource.setDatabaseName("temperature");
            mysqlDataSource.setLoginTimeout(3);
            mysqlDataSource.setUser("123");
            mysqlDataSource.setPassword("123");

            DataSource ds = mysqlDataSource;

            Connection connection1 = ds.getConnection();
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *FROM temperature.temperature WHERE id > 108000");


            while(resultSet.next()) {
                System.out.println("id " + resultSet.getInt("id") +
                        " date "+resultSet.getTimestamp("date") +
                        " t_inside " + resultSet.getFloat("t_inside_1") +
                        " t-Outside " + resultSet.getFloat("t_outside")) ;
            }

//            PreparedStatement preparedStatement = connection.prepareStatement();
//            preparedStatement.setInt(1,100000);
//            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
