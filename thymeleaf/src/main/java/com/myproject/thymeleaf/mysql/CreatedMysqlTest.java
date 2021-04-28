package com.myproject.thymeleaf.mysql;

import java.sql.*;

/**
 * @author zhanjianjian
 * @since 2021/3/4
 */
public class CreatedMysqlTest {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://212.129.135.144:3306/thymeleaf?serverTimezone=UTC";
        String userName = "root";
        String password = "Zz565143354@";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.prepareStatement("select * from sys_user where id = '2'");
            resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    System.out.println("resultSet = " + resultSet.getObject(i));
                }
            }
        } catch (SQLException e) {
            System.out.println("e = " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
