package com.sh2zqp.jdbc;

import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/how2java?useUnicode=true&characterEncoding=utf-8&useSSL=false";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
            String sql = "select * from category_ where id=?";
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setInt(1, 1);
            // 执行查询
            resultSet = preparedStatement.executeQuery();
            // 处理结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("name"));
            }
        } finally {
            // 关闭连接，释放资源
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
