package Zebra.jdbc.student_test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
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
public class DataSource_Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        printList(query("计算机系2019级1班"));
        printList(query("计算机系2019级1班' or '1'='1"));
    }
    public static List<Info> query(String name) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/db2?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = ds.getConnection();

            System.out.println(connection);

            //statement = connection.createStatement();  //创建简单的Statement对象
            String sql = "select cla.name,stu.name,cou.name,sco.score from student stu\n" +
                    "left join classes cla on cla.id =stu.classes_id\n" +
                    "join score sco on sco.student_id = stu.id\n" +
                    "join course cou on cou.id = sco.course_id\n" +
                    "where cla.name =?" ;//指定多个占位符？，在执行sql的时候，替换值，不需要单引号了
//                    "'"+name +"'";

            //preparedStatement预编译的操作命令对象：注意使用String sql传入参数
            //发送sql，让数据库预编译，语法分析，顺序分析，执行优化
            statement = connection.prepareStatement(sql);
            //替换占位符：指定占位符的位置（默认从1开始），数据类型
            statement.setString(1,name); //这里不需要单引号了

//            resultSet = statement.executeQuery(sql);
            resultSet = statement.executeQuery();//因为前面已经使用preparedStatement预编译过了，不需要再次传入sql了
            System.out.println(statement);
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
