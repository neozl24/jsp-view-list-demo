package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zhongli on 2017/1/18.
 */
public class DBHelper {
    //数据库驱动
    private static final String driver = "com.mysql.jdbc.Driver";

    //连接数据库的URL地址
    private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";

    //数据库的用户名和密码
    private static final String username = "root";
    private static final String password = "77777777";

    private static Connection connection = null;

    //静态代码块负责加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
