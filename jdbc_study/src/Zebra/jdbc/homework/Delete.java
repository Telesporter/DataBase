package Zebra.jdbc.homework;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/24
 * Time: 11:27
 * Description: No Description
 */
public class Delete {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://localhost:3306/ebook?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = mysqlDataSource.getConnection();

            String sql = "delete from borrow_info where id = (select max(id) from borrow_info)";
            String sql2 = "delete from borrow_info where id = " +
                    "(select temp.id from (select max(id) id from borrow_info)temp) ";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
            System.out.println("1 row affected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            Close.close(null,preparedStatement,connection);
        }


    }
}
