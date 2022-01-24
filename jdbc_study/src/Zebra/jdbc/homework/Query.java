package Zebra.jdbc.homework;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/24
 * Time: 10:35
 * Description: No Description
 */
public class Query {
    public static void main(String[] args) {
        printList(query("计算机"));
    }
    public static List<Info> query(String category){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //放到try里面
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://localhost:3306/ebook?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = mysqlDataSource.getConnection();

            String sql = "select bk.name,stu.name,br_if.start_time,br_if.end_time from borrow_info br_if\n" +
                    "join book bk on bk.id = br_if.book_id\n" +
                    "join category cg on cg.id = bk.category_id\n" +
                    "join student stu on stu.id = br_if.student_id\n" +
                    "where cg.name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,category);  //注意这步替换占位符不要忘了

            statement.executeQuery();

            resultSet = statement.getResultSet();

            List<Info> infoList = new ArrayList<>();
            while (resultSet.next()){
                String bookName = resultSet.getString("bk.name");
                String studentName = resultSet.getString("stu.name");
                Timestamp startTime = resultSet.getTimestamp("br_if.start_time");
                Timestamp endTime = resultSet.getTimestamp("br_if.start_time");
                Info info = new Info(bookName,studentName,startTime,endTime);
                infoList.add(info);
            }
            return infoList;

        } catch (Exception throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        } finally {
            Close.close(resultSet,statement,connection);
        }

    }
    public static void printList(List<Info> infoList){
        for (Info info : infoList) {
            System.out.println(info);
        }
    }
}
