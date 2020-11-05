package dao.impl;

import connection.InitConnection;
import dao.UserDao;
import model.Applicant;
import model.Letter;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIMPL  implements UserDao {
    private InitConnection initConnection = new InitConnection();

    @Override
    public List<User> findAll() throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try {
            PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                results.add(new User(resultSet.getInt("id_applicant"),
                        resultSet.getString("id_card"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("deleted")));
            }
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return results.isEmpty() ? null : results;
    }

    @Override
    public User findById(int id) throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = "SELECT *FROM user WHERE id_applicant=?";

        try {
            PreparedStatement statement = initConnection.prepareSQL(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(new User());
            }
        } catch (SQLException e) {
            return null;
        }

        return results.isEmpty() ? null : results.get(0);

    }

    @Override
    public User insert(User user) throws SQLException {
        User user1 = new User();

        String sql = "INSERT INTO applicant (id_applicant, id_card, name, password) value (?,?,?,?)";
        PreparedStatement preparedStatement = initConnection.prepareSQL(sql);
        preparedStatement.setInt(1, user.getIdApplicant());
        preparedStatement.setString(2, user.getIdCard());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setBoolean(4, user.isDeleted());

        int isDone = preparedStatement.executeUpdate(); //  > 0 khi insert thành công
        if (isDone > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); // lấy ra id của bản ghi đã thêm vào
            user1 = findById((int) resultSet.getLong(1)); // tìm kiếm lại dderr kiểm tra
        }

        return user1;
    }

    @Override
    public boolean update(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }
}
