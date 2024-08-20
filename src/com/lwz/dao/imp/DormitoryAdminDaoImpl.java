package com.lwz.dao.imp;

import com.lwz.dao.DormitoryAdminDao;
import com.lwz.entity.DormitoryAdmin;
import com.lwz.util.JDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//数据表实现类
public class DormitoryAdminDaoImpl implements DormitoryAdminDao {

//从数据库查阅员工信息展示到页面中

    /**
     * 从数据库中检索所有员工信息。
     * 此方法通过查询数据库表中的所有记录来实现。它首先建立数据库连接，然后准备一个SQL查询语句，
     * 并执行该查询以获取结果集。随后，它遍历结果集，将每个管理员的详细信息封装到一个DormitoryAdmin对象中，
     * 最后将这些对象添加到一个列表中返回。
     *
     * @return 包含所有管理员信息的列表。
     */
    @Override
    public List<DormitoryAdmin> lint() {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 定义SQL查询语句
        String sql = "select * from shu_ju";
        // 创建PreparedStatement对象，用于执行SQL查询
        PreparedStatement statement = null;
        // 创建ResultSet对象，用于存储查询结果
        ResultSet resultSet = null;

        // 初始化一个列表，用于存储查询到的管理员信息
        List<DormitoryAdmin> list = new ArrayList<>();

        try {
            // 准备SQL查询语句
            statement = connection.prepareStatement(sql);
            // 打印SQL语句，用于调试
            System.out.println("sql语句输出：" + sql);
            // 执行查询并获取结果集
            resultSet = statement.executeQuery();
            // 遍历结果集，提取每条记录的信息
            while (resultSet.next()) {
                // 从结果集中获取管理员的各个属性
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String telephone = resultSet.getString(5);
                String gender = resultSet.getString(6);
                // 创建一个新的DormitoryAdmin对象，并将属性值赋给它
                list.add(new DormitoryAdmin(id, username, password, name, telephone, gender));
            }
        } catch (SQLException throwables) {
            // 如果发生SQL异常，打印堆栈跟踪
            throwables.printStackTrace();
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet，以释放资源
            JDBConnection.release(connection, statement, resultSet);
        }
        // 返回包含所有管理员信息的列表
        return list;
    }


//搜索
    /**
     * 根据关键字和值查询管理员信息。
     * 该方法通过连接数据库，执行SQL查询语句，获取匹配条件的所有管理员数据，并将这些数据封装成DormitoryAdmin对象的列表返回。
     *
     * @param key   查询关键字，对应数据库表中的某一列。
     * @param value 查询值，关键字对应的列的值中包含此值的记录将被查询出来。
     * @return 返回一个DormitoryAdmin对象的列表，包含所有匹配查询条件的管理员信息。
     */
    @Override
    public List<DormitoryAdmin> saearch(String key, String value) {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 构造SQL查询语句，使用LIKE操作符匹配关键字和值
        String sql = "select * from test07.shu_ju where " + key + " like '%" + value + "%'";//sql语句
        System.out.println("sql语句输出：" + sql);
        // 定义PreparedStatement对象，用于执行SQL查询
        PreparedStatement statement = null;
        // 定义ResultSet对象，用于存储查询结果
        ResultSet resultSet = null;
        // 初始化DormitoryAdmin对象的列表，用于存储查询结果
        List<DormitoryAdmin> list = new ArrayList<>();
        try {
            // 准备SQL查询语句
            statement = connection.prepareStatement(sql);
                // 执行查询操作
                resultSet = statement.executeQuery();
                // 遍历查询结果集，提取每条记录的数据，并创建DormitoryAdmin对象，添加到列表中
                while (resultSet.next()) {
                // 提取id字段的值
                int id = resultSet.getInt(1);//id
                // 提取username字段的值
                String username = resultSet.getString(2);//username
                // 提取password字段的值
                String password = resultSet.getString(3);//password
                // 提取name字段的值
                String name = resultSet.getString(4);//name
                // 提取telephone字段的值
                String telephone = resultSet.getString(5);//telephone
                // 提取gender字段的值
                String gender = resultSet.getString(6);
                // 创建DormitoryAdmin对象，并填充数据
                list.add(new DormitoryAdmin(id, username, password, name, telephone, gender));
            }
        } catch (SQLException throwables) {
            // 打印SQL异常信息
            throwables.printStackTrace();
        } finally {
            // 关闭数据库连接、PreparedStatement和ResultSet对象
            JDBConnection.release(connection, statement, resultSet);
        }
        // 返回DormitoryAdmin对象的列表
        return list;
    }


//注册登录账户
    /**
     * 注册管理员账户。
     * 该方法通过向数据库中的zhang_hu表插入新的记录来注册管理员账户。
     *
     * @param dormitoryAdmin 包含待注册管理员的用户名、密码和姓名的对象。
     * @return 返回受影响的行数，通常情况下，如果注册成功，该值为1。
     */
    @Override
    public Integer zhuce(DormitoryAdmin dormitoryAdmin) {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 定义SQL语句，用于插入新的管理员信息
        String sql = "insert into test07.zhang_hu(username,password,name) values (?,?,?)";//sql语句
        System.out.println("sql语句输出：" + sql);
        PreparedStatement statement = null;
        Integer result = null;
        try {
            // 准备SQL语句，设置参数
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            // 执行更新操作，返回受影响的行数
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            // 捕获并打印SQL异常
            throwables.printStackTrace();
        } finally {
            // 释放资源
            JDBConnection.release(connection, statement, null);
        }
        return result;
    }


//添加下属信息
    /**
     * 保存管理员信息到数据库。
     * 通过SQL语句更新指定ID的管理员的用户名、密码、姓名、性别和电话信息。
     * @param dormitoryAdmin 管理员对象，包含需要保存的信息。
     * @return 返回影响的行数，表示保存操作的结果。
     */

    @Override
    public Integer save(DormitoryAdmin dormitoryAdmin) {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 定义SQL语句，用于插入数据
        String sql = "insert into test07.shu_ju(username,password,name,gender,telephone) values (?,?,?,?,?)";

        PreparedStatement statement = null;
        Integer result = null;
        try {
            // 准备SQL语句，设置参数
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());

            // 执行更新操作，返回影响的行数
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            // 捕获SQL异常，打印堆栈跟踪
            throwables.printStackTrace();
        } finally {
            // 释放资源
            JDBConnection.release(connection, statement, null);
        }
        return result;
    }

//修改员工信息
    /**
     * 更新员工信息。
     * 通过SQL语句更新指定ID员工的用户名、密码、姓名、性别和电话信息。
     *
     * @param dormitoryAdmin 包含待更新管理员信息的对象。
     * @return 返回更新操作影响的行数，用于判断更新操作是否成功。
     */
    @Override
    public Integer update(DormitoryAdmin dormitoryAdmin) {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 定义更新员工信息的SQL语句
        String sql = "UPDATE test07.shu_ju SET username = ?, password = ?, name = ?, gender = ?, telephone = ? WHERE id = ?";
        PreparedStatement statement = null;
        Integer result = null;
        try {
            // 准备SQL语句，设置参数
            statement = connection.prepareStatement(sql);
            statement.setString(1, dormitoryAdmin.getUsername());
            statement.setString(2, dormitoryAdmin.getPassword());
            statement.setString(3, dormitoryAdmin.getName());
            statement.setString(4, dormitoryAdmin.getGender());
            statement.setString(5, dormitoryAdmin.getTelephone());
            statement.setInt(6, dormitoryAdmin.getId());
            // 执行更新操作，返回影响的行数
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            // 捕获并打印SQL异常
            throwables.printStackTrace();
        } finally {
            // 释放数据库资源
            JDBConnection.release(connection, statement, null);
        }
        return result;
    }



//删除用户
    /**
     * 根据ID删除数据。
     *
     * @param id 要删除的数据的ID。
     * @return 返回删除操作影响的行数。
     */
    @Override
    public Integer delete(Integer id) {
        // 获取数据库连接
        Connection connection = JDBConnection.getConnection();
        // 组织SQL语句，删除指定ID的数据
        String sql = "delete from test07.shu_ju where id = " + id;
        // 定义PreparedStatement对象，用于执行SQL语句
        PreparedStatement statement = null;
        // 定义结果变量，用于存储删除操作影响的行数
        Integer result = null;
        try {
            // 预编译SQL语句，并执行删除操作
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            // 捕获并打印SQL异常
            throwables.printStackTrace();
        } finally {
            // 释放数据库资源
            JDBConnection.release(connection, statement, null);
        }
        // 返回删除操作影响的行数
        return result;
    }

}




