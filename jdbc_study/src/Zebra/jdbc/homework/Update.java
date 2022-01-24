package Zebra.jdbc.homework;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/24
 * Time: 11:16
 * Description: No Description
 */
public class Update {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://localhost:3306/ebook?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = mysqlDataSource.getConnection();

            String sql = "update book set price = 61.20 where name = '深入理解Java虚拟机'";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeUpdate();
            System.out.println("1 row affected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Close.close(null,preparedStatement,connection);
        }

    }
}
