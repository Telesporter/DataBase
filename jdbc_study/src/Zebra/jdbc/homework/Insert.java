package Zebra.jdbc.homework;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.ldap.spi.LdapDnsProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/24
 * Time: 10:18
 * Description: 新增貂蝉同学的借阅记录：诗经，从2019年9月25日17:50到2019年10月25日17:50
 */
public class Insert {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/ebook?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = dataSource.getConnection();

            String sql = "insert into borrow_info (book_id,student_id,start_time,end_time)" +
                    "values(10,3,'2019-09-25 17:50:00','2019-10-25 17:50:00')";
            statement = connection.prepareStatement(sql);

            statement.executeUpdate();
            System.out.println("1 row changed");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            }  catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
