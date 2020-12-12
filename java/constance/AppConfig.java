package constance;

import javafx.util.Pair;

import java.util.Dictionary;
import java.util.List;

public class AppConfig {
    public static final String DRIVER = "com.mysql.jdbc.Driver"; // chứa đường dẫn đến thư viện jdbc
    public static final String URL_DATABASE = "jdbc:mysql://localhost:3306/"; // đường dẫn để kết nối đến schema
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456"; // mật khẩu của mysql

    public  static  final String CATE_LIST[]={"phananh","kiennghi"};
//    public static  final Dictionary<Integer,String> STATUS=

}
