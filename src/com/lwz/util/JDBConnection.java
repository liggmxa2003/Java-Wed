package com.lwz.util;

import java.sql.*;

public class JDBConnection {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/test07?serverTimezone=UTC";
    private static String user = "root";
    private static String password = "200309";

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//测试数库链接
public static void main(String[] args) {
    JDBConnection db = new JDBConnection();
    System.out.println(db.getConnection());
}

}
