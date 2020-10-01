package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class InitConn {
    private Connection conn;
    public InitConn(){
        // 4 dong nay sau se dua ra mopt file cau hinh rieng
        String hostName = "localhost";
        String dbName = "testse";
        String userName = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            System.out.println("err when load driver");
            e1.printStackTrace();
        }
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("connected to database");
        } catch (Exception e) {
            System.out.println("cant connect to database!");
            e.printStackTrace();
            conn= null;
        }

        this.conn=conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
