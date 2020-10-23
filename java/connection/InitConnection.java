package connection;

import constance.AppConfig;

import java.sql.*;

public class InitConnection {
    private Connection connection;

    public void testDriver() throws ClassNotFoundException{
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException ex){
            System.out.println("error when load driver");
            ex.printStackTrace();
        }
    }

    public Connection getConnect() throws ClassNotFoundException {
        // đã chuyển ra package constance
        String dbName = "testse";
        String connectionURL = AppConfig.URL_DATABASE + dbName;

        if(connection == null){
            testDriver();
            try {
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
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException ex){
            System.out.println("can't query");
            ex.printStackTrace();
            return null;
        }
    }

    // dùng cho update để lấy id
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
