package Zebra.jdbc.exception_test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 13498
 * Date: 2022/1/22
 * Time: 22:38
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(test(0));
    }
    public static int test(int i){
        try{
            if (i == 0)
                throw new SQLException();
            if (i == 1)
                return 1;
            if(i == 2)
                throw new ArrayIndexOutOfBoundsException();
            return 0; //注意逻辑分支一定要覆盖完整，这里要是没有return的话会编译报错，因为出现i不适0,1,2的情况就没有返回值了。
        }catch (SQLException e){
            throw new RuntimeException("1");
        }catch (Exception e){
            throw new RuntimeException("2");
        }finally {
            //这里要是写上return，一定会被最后执行，前面try里面的return全部作废
        }
    }
}
