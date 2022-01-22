package Zebra.jdbc.test;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/16
 * Time: 21:13
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1.加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");
            // 1.创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            System.out.println(connection);

            //2.创建操作命令
            statement = connection.createStatement();

            //3.执行SQL
            String sql = "select id idid,name from car";
            resultSet = statement.executeQuery(sql);

            List<Car> carList = new ArrayList<>();
            //4.处理结果集（只有查询操作才需要）
            while(resultSet.next()){
                int id = resultSet.getInt("idid");
                String name = resultSet.getString("name");
                //System.out.println(String.format("Car: id=%d, name=%s",id,name));
                Car car = new Car();
                car.setId(id);
                car.setName(name);
                carList.add(car);
                System.out.println(carList);
            }
        } catch (Exception e) {  //我们需要捕获最顶级的异常，因为出现别的异常，也是需要进行释放资源的
            e.printStackTrace();
        } finally {
            //5.无论如何，我们都要释放资源
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * 一般来说，对象中的属性不提供给对外进行修改
     * 1.Getter和Setter方法
     * 2.构造方法进行设置
     */

    private static class Car{
        private Integer id;
        private String name;

        @Override
        public String toString() {
            return "Car{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
