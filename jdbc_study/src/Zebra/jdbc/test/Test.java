package Zebra.jdbc.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/16
 * Time: 21:13
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
        Class.forName("com.mysql.jdbc.Driver");
        // 创建数据库连接
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8");
        System.out.println(connection);
    }
}
