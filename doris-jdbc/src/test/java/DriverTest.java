import java.sql.*;

public class DriverTest {
    public static void main(String[] args) {
        try{
          //  Class.forName("com.doris.jdbc.DorisDriver");
            Connection conn = DriverManager.getConnection("jdbc:doris://localhost:9030/test??useSSL=true&useUnicode=true&characterEncoding=utf8");
            Statement stmt = conn.createStatement();

            //1. test query
            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
            while (rs.next()){
                System.out.println("name:"+rs.getString(1) + ";age" + rs.getString(2));
            };
            //2. test insert
            String bathInsert =   "insert into  user_info (user_account,user_name,user_age,user_class) values ('00001', '张三 ','20','计算机系'), ('00002', '李四','19','计算机系');";


            stmt.addBatch(bathInsert);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
