package com.lwz.dao.imp;
//登录实现类

import com.lwz.dao.LogiAdminDao;
import com.lwz.entity.LoginAdmin;
import com.lwz.util.JDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdminDaoImpl implements LogiAdminDao {

    @Override
    public LoginAdmin findByUsername(String username) {
        Connection connection = JDBConnection.getConnection();
        String sql = "select * from test07.zhang_hu where username = '"+username+"'";//sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            System.out.println("sql语句输出："+sql);//控制台输出验证
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);//id
                username = resultSet.getString(2);//username
                String password = resultSet.getString(3);//password
                String name = resultSet.getString(4);//name
                String telephone = resultSet.getString(5);//telephone
                return new LoginAdmin(id,username,password,name,telephone);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBConnection.release(connection, statement, resultSet);
        }
        return null;
    }
}
