package connection;

import constance.AppConfig;

import java.sql.*;

public class InitConnection {

    private Connection connection;

    public void testDriver() throws ClassNotFoundException{
        // để test driver
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException ex){
            System.out.println("error when load driver");
            ex.printStackTrace();
        }
    }

    public Connection getConnect() throws ClassNotFoundException {
        // thông số đã chuyển ra package constance
        String dbName = "software_engineering";
        String connectionURL = AppConfig.URL_DATABASE + dbName;

        if(connection == null){
            testDriver();
            try {
                // connect
                connection = DriverManager.getConnection(connectionURL, AppConfig.USERNAME, AppConfig.PASSWORD);
                System.out.println("connected to database");
            } catch (Exception ex) {
                System.out.println("can't connect to database!");
                ex.printStackTrace();
            }
        }
        return connection;
    }

    // Thực hiện query
    public PreparedStatement prepareSQL(String sql){
        try{
            System.out.println(">> " + sql);
            return this.getConnect().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet.TYPE_SCROLL_SENSITIVE: cho phép con trỏ resultSet chạy từ bản ghi đầu đến cuối.
            //ResultSet.CONCUR_UPDATABLE: tạo ra một đuối tượng resultSet có thể được cập nhập.
        } catch (SQLException | ClassNotFoundException ex){
            System.out.println("can't query");
            ex.printStackTrace();
            return null;
        }
    }

    // riêng cho update, insert để lấy id của đói tượng đã thao tác
    public PreparedStatement prepareUpdate(String sql){
        try{
            System.out.println(">> " + sql);
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Statement.RETURN_GENERATED_KEYS trả về id của bản ghi vừa insert thành công
        } catch (SQLException ex){
            System.out.println("can't update");
            ex.printStackTrace();
            return null;
        }
    }

    public void closeConnection() throws SQLException {
        if(connection != null){
            System.out.println("Closed connection");
            connection.close();
        }
    }

}
