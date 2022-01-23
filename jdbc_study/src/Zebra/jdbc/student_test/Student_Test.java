package Zebra.jdbc.student_test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/22
 * Time: 18:53
 * Description: No Description
 */
public class Student_Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        printList(query("计算机系2019级1班"));
    }
    public static List<Info> query(String name) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db2?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            System.out.println(connection);

            statement = connection.createStatement();
            String sql = "select cla.name,stu.name,cou.name,sco.score from student stu\n" +
                    "left join classes cla on cla.id =stu.classes_id\n" +
                    "join score sco on sco.student_id = stu.id\n" +
                    "join course cou on cou.id = sco.course_id\n" +
                    "where cla.name = '"+name +"'";
            resultSet = statement.executeQuery(sql);

            List<Info> infoList = new ArrayList<>();
            while (resultSet.next()){
                String className = resultSet.getString("cla.name");
                String studentName = resultSet.getString("stu.name");
                String courseName = resultSet.getString("cou.name");
                BigDecimal score = resultSet.getBigDecimal("sco.score");
                Info info = new Info(className,studentName,courseName,score);
                infoList.add(info);
            }
            printList(infoList);
            return infoList;

        } catch (Exception e) {
            //执行某个功能，如果出现异常，建议再次抛出异常，返回null是最土的情况。
            e.printStackTrace();
            throw new RuntimeException("查询班级信息出错",e);
        }  finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    private static void printList(List<Info> infoList){
        for (Info info : infoList) {
            System.out.println(info);
        }
    }
}
